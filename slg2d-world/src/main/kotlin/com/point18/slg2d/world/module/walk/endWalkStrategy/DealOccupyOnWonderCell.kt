package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.WonderLocationProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.module.fightdomain.createFightDataByForceGroup
import com.point18.slg2d.world.module.fightdomain.createFightDataByForceGroupList
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.doCancelMass
import com.point18.slg2d.world.module.walk.fightStrategy.soliderFight
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import com.point18.slg2d.world.msgnotice.WonderOccupiedNotifier
import com.point18.slg2d.world.msgnotice.createWonderOccupiedNotifier
import java.util.*

class DealOccupyOnWonderCell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        //校验是否是奇观
        val rst =
            pcs.wonderLocationProtoCache.findInWonderType(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        val wonderProto = rst.wonderLocationProto
        if (rst.int != WONDER_BASE || wonderProto == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        //校验奇观状态
        val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
            ?: return ResultCode.PARAMETER_ERROR.code
        if (isWonderPeace(wonder)) {
            return ResultCode.WONDER_IN_PEACE.code
        }

        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null) {
            normalLog.error("找不到行军组中的玩家信息,行军组Id:${group.id}")
            return ResultCode.PARAMETER_ERROR.code
        }
        if (player.allianceId == 0L) {
            return ResultCode.ONLY_ALLIANCE_CAN_OCCUPY_WONDER.code
        }

        //校验是否已被盟友占领（换防指挥官没有解散）
        if (wonder.belongToAllianceId == player.allianceId && wonder.occupyGroupId != 0L) {
            // 奇观换防失败邮件
            return ResultCode.HAVE_OCCUPY_WONDER.code
        }
        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        //如果奇观内有部队，则与奇观内部队进行战斗
        val rst =
            pcs.wonderLocationProtoCache.findInWonderType(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        val wonderProto = rst.wonderLocationProto ?: return

        val centerPos = wonderProto.getCenterPos()
        val posX = centerPos.x
        val posY = centerPos.y
        val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
        if (wonder == null) {
            normalLog.error("找不到奇观配置对应的奇观数据:${wonderProto.id}")
            return
        }

        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null) {
            normalLog.error("行军组对应的玩家不存在,玩家Id:${group.mainPlayerId}")
            return
        }

        //进行战斗
        val fightResult = fightWithWonderForce(areaCache, group, wonderProto, wonder)
        if (fightResult == FIGHT_RESULT_LOSE) {
            return
        }

        // 奇观易主
        val wonderOccupiedNotifier = createWonderOccupiedNotifier(
            player.allianceId,
            player.allianceShortName,
            player.allianceName,
            player.id,
            player.name,
            wonderProto.id
        )

        // 通知旧的占领联盟奇观被占领
        sendWonderOccupiedNotice(areaCache, wonder.belongToAllianceId, wonderOccupiedNotifier)

        // 旧占领方计算占领时长，用于增加奇观进行曲积分
        val nowTime = getNowTime()
        val wonderRankVo = wonder.rankInfoMap[wonder.belongToAllianceId]
        if (wonderRankVo != null) {
            wonderRankVo.score += nowTime - wonder.occupyStartTime
        }

        // 如果联盟本次第一次占领到奇观，加入排行榜
        // 否则，不会对排行榜分数进行修改。只有在被替换和结束时才会对分数进行维护
        wonder.rankInfoMap.getOrPut(player.allianceId) {
            WonderRankVo(
                player.allianceId,
                player.allianceShortName,
                player.allianceName,
                player.flagColor,
                player.flagStyle,
                player.flagEffect,
                0L
            )
        }

        // 大奇观易主需要改变国王
        if (wonder.wonderProtoId == BIG_WONDER) {
            changeKing(areaCache, player.allianceId)
        }

        // 同步旧奇观信息至pub
        if (wonder.belongToAllianceId != 0L) {
            updateWonderInfo(areaCache, Del, wonder.belongToAllianceId, wonder)
        }

        // 改变奇观状态
        wonder.status = WAR_WIN
        areaCache.worldActor.wonder = wonder.status
        syncInfo2WorldManager(areaCache)
        wonder.statusOverTime = nowTime + pcs.basicProtoCache.wonderFireTime * 1000
        wonder.belongToAllianceId = player.allianceId

        // 同步新奇观信息至pub
        updateWonderInfo(areaCache, Add, wonder.belongToAllianceId, wonder)

        // 更新防守时间
        val occupyStartTime = getNowTime()
        val needDefTime = getDefTime(
            wonderProto.warTimeMap,
            ((occupyStartTime - wonder.warStartTime) / 1000).toInt()
        )
        val occupyOverTime = occupyStartTime + needDefTime * 1000
        wonder.occupyStartTime = occupyStartTime
        wonder.occupyOverTime = occupyOverTime

        // 占领和换防，重置占领部队id
        wonder.occupyGroupId = group.id

        // 解散本联盟对该奇观的集结,并通知
        for (mass in areaCache.massCache.findMassByAllianceId(player.allianceId)) {
            if (mass.fightType == WalkOccupyWonder && mass.gotoX == posX && mass.gotoY == posY) {
                doCancelMass(areaCache, mass.mainPlayerId, mass.id)
            }
        }

        // 所有【当前联盟】的【占领奇观】行军返回，并通知
        callBackSameAllianceWalks(areaCache, posX, posY, WalkOccupyWonder, player.allianceId)

        // 通知新的占领联盟奇观被占领
        sendWonderOccupiedNotice(areaCache, wonder.belongToAllianceId, wonderOccupiedNotifier)

        // 修改奇观部队状态
        areaCache.walkForceGroupCache.changeGroupState(group, Reinforce)

        // 奇观部队分散
        groupDisperse(areaCache, group)

        // 重新检查本部的奇观部队，全死的遣返
        checkWonderGroups(areaCache, wonder, posX, posY)

        // 刷新奇观地块
        noticeCellUpdate(areaCache, posX, posY)
    }

    private fun callBackSameAllianceWalks(areaCache: AreaCache, posX: Int, posY: Int, runState: Int, allianceId: Long) {
        val gotoWalks = areaCache.walkCache.findWalksByGotoXy(posX, posY)
        for (walk in gotoWalks) {
            val walkGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
            if (walkGroup == null) {
                continue
            }
            if (walkGroup.gotoRunType != runState) {
                continue
            }
            val reinforcePlayer = areaCache.playerCache.findPlayerById(walkGroup.mainPlayerId)
            if (reinforcePlayer == null) {
                continue
            }
            if (allianceId != reinforcePlayer.allianceId) {
                // 不是本联盟的不需要返回
                continue
            }
            halfWayGoHome(areaCache, walk)
        }
    }

    // 联盟占领通知
    private fun sendWonderOccupiedNotice(
        areaCache: AreaCache,
        allianceId: Long,
        wonderOccupiedNotifier: WonderOccupiedNotifier
    ) {
        val allianceMembers = areaCache.playerCache.findPlayersByAllianceId(allianceId)
        allianceMembers.forEach {
            // 发送奇观被占领消息
            fetchOnlinePlayerSession(areaCache, it.id)?.let { session ->
                wonderOccupiedNotifier.notice(session)
            }
        }
    }

    //群组分散
    private fun groupDisperse(areaCache: AreaCache, group: WalkForceGroup) {
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
        if (forces.count() > 1) {
            for (force in forces) {
                if (force.playerId == group.mainPlayerId) {
                    continue
                }
                val newGroup = areaCache.walkForceGroupCache.createWalkForceGroup(
                    force.playerId,
                    0,
                    group.runningState,
                    group.gotoRunType,
                    group.nowX,
                    group.nowY
                )
                areaCache.walkForceCache.updateWalkForceGroupId(force, newGroup.id)
                noticeSelfWalkForceGroup(areaCache, Add, newGroup)
            }
        }
        noticeSelfWalkForceGroup(areaCache, Update, group)
    }

    //检查奇观内部队，无兵的立即遣返，指挥官无兵则全部遣返
    private fun checkWonderGroups(areaCache: AreaCache, wonder: Wonder, posX: Int, posY: Int) {
        val commandGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(wonder.occupyGroupId)
        val atkCommandHaveSolider = commandGroup != null && commandGroup.checkHaveSolider(areaCache)
        val reinforceGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(posX, posY, Reinforce)
        for (group in reinforceGroups) {
            if (atkCommandHaveSolider && group.checkHaveSolider(areaCache)) {
                continue
            }
            goHome(areaCache, posX, posY, group)
        }
        if (!atkCommandHaveSolider) {
            wonder.occupyGroupId = 0
        }
    }

    //和奇观部队战斗
    private fun fightWithWonderForce(
        areaCache: AreaCache,
        group: WalkForceGroup,
        wonderProto: WonderLocationProto,
        wonder: Wonder
    ): Int {
        val centerPos = wonderProto.getCenterPos()
        val posX = centerPos.x
        val posY = centerPos.y

        val atkFightData = createFightDataByForceGroup(areaCache, group)

        if (wonder.occupyGroupId == 0L) {
            //奇观木有其他人
            //发送成功占领奇观的战报
            val report = ReportInfo(
                areaCache,
                posX,
                posY,
                atkFightData,
                null,
                null,
                null,
                "".toByteArray()
            )
            report.genFightPlayerReport(
                null,
                null,
                null,
                group.massId != 0L,
                false,
                false,
                wonderProto.id,
                FIGHT_RESULT_WIN
            )
            return FIGHT_RESULT_WIN
        }

        // 奇观有部队占领，防守方合并成一队发生战斗
        val defMainGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(wonder.occupyGroupId)
        if (defMainGroup == null) {
            normalLog.error("找不到奇观上占领的行军组信息:${wonder.occupyGroupId}")
            return FIGHT_RESULT_WIN
        }
        val defGroups =
            areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(posX, posY, Reinforce)

        val defFightData = createFightDataByForceGroupList(
            areaCache,
            defGroups,
            defMainGroup.mainPlayerId
        )
        val atkForceList = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
        val defForceList = LinkedList<WalkForce>()
        defGroups.forEach {
            val forceList = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(it.id)
            defForceList += forceList
        }
        val fightResultData = soliderFight.doSoliderFight(
            areaCache,
            PVP_FIGHT_GROUP_ACTION,
            WalkOccupyWonder,
            FIGHT_PLAYER_REPORT,
            group.massId != 0L,
            posX,
            posY,
            atkForceList,
            defForceList,
            atkFightData,
            defFightData
        )
        val fightResult = fightResultData.fightResult

        if (fightResult == FIGHT_RESULT_LOSE) {
            wonder.status = WAR_LOSE
            areaCache.worldActor.wonder = wonder.status
            syncInfo2WorldManager(areaCache)
            wonder.statusOverTime = getNowTime() + pcs.basicProtoCache.wonderSmokeTime * 1000
            //刷新奇观地块
            noticeCellUpdate(areaCache, posX, posY)

            // 输了，我的部队回家
            atkFightData.checkPrison(
                areaCache,
                group,
                defFightData.playerId
            )

            goHome(areaCache, posX, posY, group)

            checkWonderGroups(areaCache, wonder, posX, posY)
            return FIGHT_RESULT_LOSE
        }

        defFightData.checkPrison(areaCache, null, atkFightData.playerId)
        // 赢了，别人回家
        for (defGroup in defGroups) {
            goHome(areaCache, posX, posY, defGroup)
        }

        return FIGHT_RESULT_WIN
    }
}