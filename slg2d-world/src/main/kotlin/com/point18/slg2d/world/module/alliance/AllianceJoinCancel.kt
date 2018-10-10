package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceJoinCancel
import pb4client.AllianceJoinCancelRt
import com.point18.slg2d.common.resultcode.ResultCode

//玩家取消加入联盟申请 805
class DealAllianceJoinCancel : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val tempMsg = msg as AllianceJoinCancel
        val allianceId = tempMsg.allianceId

        val rt = this.joinCancel(session, allianceId)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceJoinCancel_805, rt)
        }
    }

    /********************************************* 805 玩家取消加入联盟申请 *********************************************/
    fun joinCancel(session: PlayerSession, aid: Long): (AllianceJoinCancelRt?) {
        val rt = AllianceJoinCancelRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.allianceId = aid

        val player = session.areaCache.playerCache.findPlayerById(session.playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val joinInfo = player.joinAllianceReqs[aid]
        if (joinInfo == null) {
            rt.rt = ResultCode.ALLIANCE_JOIN_REQ_NOT_EXIST.code
            return rt.build()
        }

        com.point18.slg2d.world.common.allianceJoinCancel(session, aid)

        return null
    }
}


