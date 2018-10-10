package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.Del
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.findAllWonderReinforce
import com.point18.slg2d.world.common.noticeWonderReinforce
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import pb4client.SendWonderWarPlayerHome
import pb4client.SendWonderWarPlayerHomeRt
import java.util.*

// 奇观参战成员遣返回家
class SendWonderWarPlayerHomeDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.sendWonderWarPlayerHome(session, msg as SendWonderWarPlayerHome)
        session.sendMsg(MsgType.SendWonderWarPlayerHome_1264, rt.build())
    }

    private fun sendWonderWarPlayerHome(
            session: PlayerSession,
            sendHomeMsg: SendWonderWarPlayerHome
    ): SendWonderWarPlayerHomeRt.Builder {
        val rtBuilder = SendWonderWarPlayerHomeRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val wonderProtoId = sendHomeMsg.wonderId
        val targetPlayerIds = sendHomeMsg.playerIdsList

        val areaCache = session.areaCache
        val playerId = session.playerId

        val rst = findAllWonderReinforce(areaCache, wonderProtoId)
        if (rst == null) {
            rtBuilder.rt = ResultCode.NO_COMMAND_IN_WONDER.code
            return rtBuilder
        }

        val commandGroup = rst.commandGroup
        if (targetPlayerIds.size == 0) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        // 发送请求的玩家不是指挥官玩家或需要解散的行军组归属的玩家
        if (playerId != commandGroup.mainPlayerId && (targetPlayerIds.size > 1 || playerId != targetPlayerIds[0])) {
            rtBuilder.rt = ResultCode.NOT_COMMAND.code
            return rtBuilder
        }

        val player = session.player
        if (player.allianceId == 0L) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        val groupMap = hashMapOf<Long, WalkForceGroup>()
        for (group in rst.reinforceGroups) {
            groupMap[group.mainPlayerId] = group
        }

        val goHomeGroup = LinkedList<WalkForceGroup>()
        for (tarPlayerId in targetPlayerIds) {
            if (playerId == commandGroup.mainPlayerId && playerId == tarPlayerId) {
                // playerId 是指挥官Id，不能遣散指挥官自己的部队
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
            val g = groupMap[tarPlayerId]
            if (g == null) {
                rtBuilder.rt = ResultCode.REINFORCE_NOT_IN_WONDER.code
                return rtBuilder
            }
            goHomeGroup.add(g)
        }

        for (group in goHomeGroup) {
            val walk = areaCache.walkCache.findWalkByGroupId(group.id)
            if (walk != null) {
                halfWayGoHome(areaCache, walk)
            } else {
                goHome(areaCache, group.nowX, group.nowY, group)
            }

            //通知奇观增援部队变化
            val allAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
            val memberIds = LinkedList<Long>()
            for (member in allAllianceMembers) {
                memberIds.add(member.id)
            }
            noticeWonderReinforce(areaCache, Del, group.id, memberIds)
        }

        return rtBuilder
    }

}


