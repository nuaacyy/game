package com.point18.slg2d.world.module.transport

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ALLIANCE_POSITION_ASSISTANT
import com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS
import com.point18.slg2d.common.constg.Del
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.msgnotice.createTransportRequestChangeNotifier
import pb4client.DeleteTransportRequest
import pb4client.DeleteTransportRequestRt

class DeleteTransportRequestDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val delMsg = msg as DeleteTransportRequest
        val rt = this.deleteTransportRequest(session, delMsg)
        session.sendMsg(MsgType.DeleteTransportRequest_1263, rt)
    }

    private fun deleteTransportRequest(
        session: PlayerSession,
        delMsg: DeleteTransportRequest
    ): DeleteTransportRequestRt {
        val areaCache = session.areaCache
        val reqId = delMsg.transportReqId

        val rtBuilder = DeleteTransportRequestRt.newBuilder()
        rtBuilder.rt = deleteTransport(areaCache, session.player, reqId)
        return rtBuilder.build()
    }
}

//删除运输
fun deleteTransport(
    areaCache: AreaCache,
    player: Player,
    reqId: Long
): Int {
    //校验运输请求
    val req = areaCache.transportRequestCache.findTransportRequestById(reqId)
    if (req == null) {
        return ResultCode.PARAMETER_ERROR.code
    }

    //校验玩家权限
    if (player.allianceId == 0L) {
        return ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
    }
    if (player.allianceId != req.allianceId) {
        return ResultCode.PARAMETER_ERROR.code
    }
    if (req.playerId != player.id) {
        //删除别人的运输请求
        if (!player.getAlliancePosMapValue(ALLIANCE_POSITION_BOSS) && !player.getAlliancePosMapValue(
                ALLIANCE_POSITION_ASSISTANT)) {
            return ResultCode.ALLIANCE_POSITION_NO_ENERGH.code
        }
    }

    //删除运输请求
    areaCache.transportRequestCache.deleteTransportRequest(req)

    //广播运输请求

    val allianceMembers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
    for (member in allianceMembers) {
        val session = fetchOnlinePlayerSession(areaCache, member.id)
        if (session != null) {
            val notifier = createTransportRequestChangeNotifier(player, req, Del)
            notifier.notice(session)
        }
    }

    return ResultCode.SUCCESS.code
}


