package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.TransportRequest
import pb4client.TransportRequestChange

class TransportRequestChangeNotifier(
    val msg: TransportRequestChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.TransportRequestChange_3150, this.msg.build())
    }
}

fun createTransportRequestChangeNotifier(
    reqPlayer: Player,
    req: TransportRequest,
    changeType: Int
): TransportRequestChangeNotifier {

    val transportRequestChangeBuilder = TransportRequestChange.newBuilder()
    transportRequestChangeBuilder.changeType = changeType
    val transportRequestBuilder = pb4client.TransportRequest.newBuilder()
    transportRequestBuilder.playerId = reqPlayer.id
    transportRequestBuilder.name = reqPlayer.name
    transportRequestBuilder.photo = reqPlayer.photoProtoId
    transportRequestBuilder.transportReqId = req.id
    transportRequestBuilder.res = resVoToResString(req.resVo)
    transportRequestChangeBuilder.setReq(transportRequestBuilder)
    return TransportRequestChangeNotifier(transportRequestChangeBuilder)
}
