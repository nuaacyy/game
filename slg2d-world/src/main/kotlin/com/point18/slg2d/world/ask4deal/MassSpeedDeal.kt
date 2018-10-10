package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.noticeMassGroup
import com.point18.slg2d.world.common.noticeSelfWalkForceGroup
import com.point18.slg2d.world.common.updateWarnForWalk
import com.point18.slg2d.world.module.walk.walkComm.notice2MultiPlayerWalkRobotShow
import com.point18.slg2d.world.msgnotice.createLanMsgNotifier
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.MassSpeedAskRt
import java.util.*
import java.util.Arrays.asList

class MassSpeedDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.massSpeedAskReq
        val playerId = req.playerId
        val groupId = askMsg.groupId
        val speedRate = askMsg.speedRate

        val result = massSpeed(areaCache, playerId, groupId, speedRate)

        val rt = MassSpeedAskRt.newBuilder()
        rt.rt = result.code

        resp.setMassSpeedAskRt(rt)
    }

    private fun massSpeed(areaCache: AreaCache, playerId: Long, groupId: Long, speedRate: Int): ResultCode {
        if (speedRate < 0 || speedRate >= 10000) {
            return ResultCode.PARAMETER_ERROR
        }
        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(groupId)
        if (group == null) {
            return ResultCode.PARAMETER_ERROR
        }
        val walk = areaCache.walkCache.findWalkByGroupId(groupId)
        if (walk == null) {
            return ResultCode.WalkHaveFinished
        }
        val overTime = walk.marchTimeArrival
        val nowTime = getNowTime()
        if (overTime <= nowTime) {
            return ResultCode.WalkHaveFinished
        }

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("找不到加速玩家的信息:%d", playerId)
            return ResultCode.PARAMETER_ERROR
        }
        if (player.allianceId == 0L) {
            return ResultCode.PARAMETER_ERROR
        }
        val mainPlayer = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (mainPlayer == null) {
            normalLog.error("找不到行军组主玩家信息:d", group.mainPlayerId)
            return ResultCode.PARAMETER_ERROR
        }
        if (player.allianceId != mainPlayer.allianceId) {
            return ResultCode.PARAMETER_ERROR
        }

        val runMass = areaCache.massCache.findMassByRunGroupId(groupId)
        if (runMass == null) {
            return ResultCode.PARAMETER_ERROR
        }
        //行军的集结组，需要使用集结加速道具

        //计算时间
        val leftTime = overTime - nowTime
        val speed = walk.walkSpeed
        walk.walkSpeed = speed * 10000 / (10000 - speedRate)

        val leftTimeAfterSpeed = (leftTime * speed / walk.walkSpeed).toLong()
        val newOverTime = nowTime + leftTimeAfterSpeed

        //通知敌军加速
        val posX = walk.marchAimsX
        val posY = walk.marchAimsY
        val noticePlayerIds = hashMapOf<Long, Int>()
        val castle = areaCache.castleCache.findCastleByXy(posX, posY)
        if (castle != null) {
            noticePlayerIds[castle.playerId] = 1
        }
        val groups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(
            posX,
            posY,
            Stationed or Reinforce
        )
        for (eachGroup in groups) {
            noticePlayerIds[eachGroup.mainPlayerId] = 1
        }
        for ((id, _) in noticePlayerIds) {
            if (playerId == id) {
                continue
            }
            val otherPlayer = areaCache.playerCache.findPlayerById(id)
            if (otherPlayer == null) {
                normalLog.error("找不到被加速玩家的信息:%d", id)
                return ResultCode.PARAMETER_ERROR
            }
            if (player.allianceId != 0L && player.allianceId == otherPlayer.allianceId) {
                continue
            }

            // 给被攻击者进行提示
            val session = fetchOnlinePlayerSession(areaCache, id)
            if (session != null) {
                createLanMsgNotifier(ENEMY_SPEED, LinkedList()).notice(session)
            }
        }

        //通知被加速
        for (member in runMass.memberInfoList) {
            if (member.playerId == playerId) {
                continue
            }
            val beSpeedPlayer = areaCache.playerCache.findPlayerById(member.playerId)
            if (beSpeedPlayer == null) {
                normalLog.error("找不到被加速玩家的信息:%d", member.playerId)
                return ResultCode.PARAMETER_ERROR
            }

            val session = fetchOnlinePlayerSession(areaCache, member.playerId)
            if (session != null) {
                createLanMsgNotifier(BE_SPEED, asList(beSpeedPlayer.name)).notice(session)
            }
        }

        runMass.arriveTime = newOverTime
        noticeMassGroup(areaCache, Update, runMass)

        walk.marchTimeArrival = newOverTime

        val nowPos = areaCache.walkCache.calCurrentPos(walk)

        noticeSelfWalkForceGroup(areaCache, Update, group)

        val posMap = hashMapOf<Int, Int>()
        posMap[nowPos.posX.toInt()] = nowPos.posY.toInt()
        posMap[walk.marchPlaceX] = walk.marchPlaceY
        posMap[posX] = posY

        notice2MultiPlayerWalkRobotShow(areaCache, walk, posMap, ADD_WALKROBOTINFO)

        //刷新预警
        updateWarnForWalk(areaCache, walk)
        return ResultCode.SUCCESS
    }
}