package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceGiftOpenRt
import com.point18.slg2d.common.resultcode.ResultCode

// 打开联盟礼物主界面

class OpenAllianceGiftDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val openGiftRt = this.openAllianceGift(session)
        if (openGiftRt != null) {
            session.sendMsg(MsgType.AllianceGiftOpen_901, openGiftRt)
        }
    }

    fun openAllianceGift(session: PlayerSession): (AllianceGiftOpenRt?) {
        val rt = AllianceGiftOpenRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val player = session.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_QUERY_NOT_EXIST.code)
            return rt.build()
        }

        com.point18.slg2d.world.common.openAllianceGift(session, player.allianceId)

        return null
    }

}


