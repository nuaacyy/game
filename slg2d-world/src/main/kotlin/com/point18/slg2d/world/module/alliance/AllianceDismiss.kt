//联盟解散
package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.allianceDismiss
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.AllianceDismissRt

//联盟解散 817
class DealAllianceDismiss : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.dismiss(session)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceDismiss_817, rt)
        }
    }

    private fun dismiss(session: PlayerSession): (AllianceDismissRt?) {
        val rt = AllianceDismissRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player

        allianceDismiss(session, player.allianceId)

        return null
    }

}


