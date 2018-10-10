package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.area4data.Wonder
import com.point18.slg2d.world.common.findAllWonderReinforce
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.noticeWonderReinforce
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.cave.caveOver
import com.point18.slg2d.world.module.farm.onStopFarmCommonRes
import com.point18.slg2d.world.module.farm.onStopFarmDeadBossRes
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import pb4client.GoBackHomeRt
import xyz.ariane.util.lzWarn
import java.util.*

// 召回部队
class CallBackHomeDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.goHome(session, msg as pb4client.GoBackHome)
        session.sendMsg(MsgType.GoBackHome_1253, rt.build())
    }

    private fun goHome(session: PlayerSession, goHomeMsg: pb4client.GoBackHome): GoBackHomeRt.Builder {
        val rtBuilder = GoBackHomeRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId
        val groupId = goHomeMsg.groupId
        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(groupId)
        if (group == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        if (group.mainPlayerId != playerId) {
            rtBuilder.rt = ResultCode.UnableOperateGroup.code
            return rtBuilder
        }

        // 根据行军组自身的状态来决定做什么处理。
        when (group.runningState) {
            Running,
            WaitFight -> {
                rtBuilder.rt = ResultCode.ForceNotStatic.code
                return rtBuilder
            }
            Farming -> {
                // 停止采集
                return stopFarm(areaCache, group.nowX, group.nowY, rtBuilder)
            }
            Hiding -> {
                // 停止藏兵
                // 注意：这边不会走下面的goHome等逻辑，因为部队本身就在家里。
                return stopHiding(areaCache, playerId, rtBuilder)
            }
            MassWaiting -> {
                // 如果是发起集结者，直接解散集结
                return stopMass(areaCache, playerId, group, rtBuilder)
            }
            Reinforce -> {
                //如果是奇观指挥官， 奇观部队解散
                return stopReinforce(areaCache, group, session.player.allianceId, rtBuilder)
            }
            Stationed -> {
                //停止驻扎
                return stopStation(areaCache, group, rtBuilder)
            }
            else -> {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
        }
    }

    /**
     * 停止藏兵
     */
    private fun stopHiding(
        areaCache: AreaCache,
        playerId: Long,
        rtBuilder: GoBackHomeRt.Builder
    ): GoBackHomeRt.Builder {
        val caveWrap = areaCache.caveCache.findCaveByPlayerId(playerId)
        if (caveWrap == null) {
            rtBuilder.rt = ResultCode.NO_CAVE_FORCE_GROUP.code
            return rtBuilder
        }
        rtBuilder.rt = caveOver(areaCache, caveWrap)
        return rtBuilder
    }

    /**
     * 停止采集
     */
    private fun stopFarm(areaCache: AreaCache, x: Int, y: Int, rtBuilder: GoBackHomeRt.Builder): GoBackHomeRt.Builder {
        // 判断是否是普通资源
        val commonRes = areaCache.commonResCache.findCommonResByXY(x, y)
        if (commonRes != null) {
            onStopFarmCommonRes(areaCache, commonRes)
            return rtBuilder
        }

        val deadBossRes = areaCache.deadBossResCache.findDeadBossResByXy(x, y)
        if (deadBossRes != null) {
            onStopFarmDeadBossRes(areaCache, deadBossRes)
            return rtBuilder
        }
        rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
        return rtBuilder
    }

    /**
     * 停止集结
     */
    private fun stopMass(
        areaCache: AreaCache,
        playerId: Long,
        group: WalkForceGroup,
        rtBuilder: GoBackHomeRt.Builder
    ): GoBackHomeRt.Builder {
        val allMass = areaCache.massCache.findMassByPlayerId(playerId)
        for (mass in allMass) {
            for (member in mass.memberInfoList) {
                if (member.groupId == group.id) {
                    rtBuilder.rt = doCancelMass(areaCache, playerId, mass.id)
                    return rtBuilder
                }
            }
        }
        rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
        return rtBuilder
    }

    /**
     * 停止增援
     */
    private fun stopReinforce(
        areaCache: AreaCache,
        group: WalkForceGroup,
        allianceId: Long,
        rtBuilder: GoBackHomeRt.Builder
    ): GoBackHomeRt.Builder {
        // 解散奇观行军组
        val goHomeWonders = LinkedList<Wonder>()
        val wonders = areaCache.wonderCache.findAllWonders()
        for (wonder in wonders) {
            if (wonder.occupyGroupId == group.id) {
                goHomeWonders.add(wonder)
            }
        }

        // 如果goHomeWonders中有元素，说明消息中的groupId是指挥官的行军组，该行为是指挥官解散
        for (wonder in goHomeWonders) {
            //奇观内部队全撤回
            val wonderProto = pcs.wonderLocationProtoCache.wonderLocationProtoMap[wonder.wonderProtoId]
            if (wonderProto == null) {
                normalLog.lzWarn { "找不到奇观配置:$wonder.wonderProtoId}" }
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val rst = findAllWonderReinforce(areaCache, wonder.wonderProtoId) ?: continue

            //解散整个增援
            wonder.occupyGroupId = 0
            for (eachGroup in rst.reinforceGroups) {
                if (eachGroup.runningState == Running) {
                    val walk = areaCache.walkCache.findWalkByGroupId(eachGroup.id)
                    if (walk != null) {
                        halfWayGoHome(areaCache, walk)
                    }
                } else {
                    goHome(areaCache, eachGroup.nowX, eachGroup.nowY, eachGroup)
                }
            }

            // 通知奇观增援部队变化（指挥官解散行为）
            val allAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(allianceId)
            val memberIds = LinkedList<Long>()
            for (member in allAllianceMembers) {
                memberIds.add(member.id)
            }
            noticeWonderReinforce(areaCache, Del, group.id, memberIds)
        }

        goHome(areaCache, group.nowX, group.nowY, group)

        // 刷新地块
        noticeCellUpdate(areaCache, group.nowX, group.nowY)

        rtBuilder.rt = ResultCode.SUCCESS.code
        return rtBuilder
    }

    /**
     * 停止驻扎
     */
    private fun stopStation(
        areaCache: AreaCache,
        group: WalkForceGroup,
        rtBuilder: GoBackHomeRt.Builder
    ): GoBackHomeRt.Builder {
        goHome(areaCache, group.nowX, group.nowY, group)

        // 刷新地块
        noticeCellUpdate(areaCache, group.nowX, group.nowY)

        rtBuilder.rt = ResultCode.SUCCESS.code
        return rtBuilder
    }
}


