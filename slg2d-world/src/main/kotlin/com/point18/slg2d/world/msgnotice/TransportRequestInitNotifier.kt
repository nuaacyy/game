package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.TransportRequest
import pb4client.TransportRequestInit

class TransportRequestInitNotifier(
    val msg: TransportRequestInit.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.TransportRequestInit_3300, this.msg.build())
    }
}

// 初始化客户端数据的推送
fun createTransportRequestInitNotifier(
    reqPlayer: Player,
    reqList: List<TransportRequest>
): TransportRequestInitNotifier {
    val transportRequestChangeBuilder = TransportRequestInit.newBuilder()
    reqList.forEach { req ->
        val transportRequestBuilder = pb4client.TransportRequest.newBuilder()
        transportRequestBuilder.playerId = reqPlayer.id
        transportRequestBuilder.name = reqPlayer.name
        transportRequestBuilder.photo = reqPlayer.photoProtoId
        transportRequestBuilder.transportReqId = req.id
        transportRequestBuilder.res = resVoToResString(req.resVo)
        transportRequestChangeBuilder.addReq(transportRequestBuilder)
    }
    return TransportRequestInitNotifier(transportRequestChangeBuilder)
}
