package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceQueryReqListInfo
import pb4client.AllianceReqInfoChange

// 联盟申请信息变化
class AllianceReqInfoChangeNotifier(
    val msg: AllianceReqInfoChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AllianceReqInfoChange_3152, this.msg.build())
    }
}

fun createAllianceReqInfoChangeNotifier(
    changeType: Int,
    req: AllianceQueryReqListInfo.Builder
): AllianceReqInfoChangeNotifier {
    val allianceReqInfoChangeBuilder = AllianceReqInfoChange.newBuilder()

    allianceReqInfoChangeBuilder.changeInfo = changeType
    allianceReqInfoChangeBuilder.setAllianceQueryReqListInfo(req)
    return AllianceReqInfoChangeNotifier(allianceReqInfoChangeBuilder)

}


