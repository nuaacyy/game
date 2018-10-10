package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.constg.Del
import com.point18.slg2d.common.constg.WalkGoHome
import com.point18.slg2d.common.constg.WalkReinforceWonder
import com.point18.slg2d.common.constg.Running
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.common.noticeWonderReinforce
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import pb4server.HalfWayHomeAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import java.util.*

class HalfWayHomeDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.halfWayHomeAskReq
        val playerId = req.playerId
        val groupId = askMsg.groupId

        val result = halfWayHome(areaCache, playerId, groupId)

        val rt = HalfWayHomeAskRt.newBuilder()
        rt.rt = result.code

        resp.setHalfWayHomeAskRt(rt)
    }

    // 半路回家
    private fun halfWayHome(areaCache: AreaCache, playerId: Long, groupId: Long): ResultCode {
        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(groupId)
        if (group == null) {
            // 找不到行军组
            return ResultCode.PARAMETER_ERROR
        }
        if (group.mainPlayerId != playerId) {
            // 不是当前玩家的
            return ResultCode.UnableOperateGroup
        }
        if (group.runningState != Running) {
            // 部队已不在行军中
            return ResultCode.WalkHaveFinished
        }
        val walk = areaCache.walkCache.findWalkByGroupId(groupId)
        if (walk == null) {
            // 找不到目标行军
            return ResultCode.WalkHaveFinished
        }
        if (walk.marchState == WalkGoHome) {
            // 部队已在回城路途中
            return ResultCode.HasInWalkHome
        }

        // 判断是否是集结行军
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return ResultCode.PARAMETER_ERROR
        }
        if (player.allianceId != 0L) {
            val allMass = areaCache.massCache.findMassByAllianceId(player.allianceId)
            for (mass in allMass) {
                if (mass.groupId == groupId) {
                    return ResultCode.MassForceUnableSelfHome
                }

                val member = mass.findMassMember(playerId)
                if (member == null) {
                    continue
                }
                if (member.groupId == groupId) {
                    return ResultCode.MassForceUnableSelfHome
                }
            }
        }

        // 判断是否为奇观增援行军
        if (group.gotoRunType == WalkReinforceWonder) {
            //通知奇观增援部队变化
            val allAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
            val memberIds = LinkedList<Long>()
            for (member in allAllianceMembers) {
                memberIds.add(member.id)
            }
            noticeWonderReinforce(areaCache, Del, group.id, memberIds)
        }

        halfWayGoHome(areaCache, walk)

        return ResultCode.SUCCESS
    }
}