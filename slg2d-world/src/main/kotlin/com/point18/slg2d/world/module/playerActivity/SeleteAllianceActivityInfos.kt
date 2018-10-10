package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.SeleteAllianceActivityInfosRt
import com.point18.slg2d.common.resultcode.ResultCode

// 打开活动总界面,只请求联盟活动的数据
class SelectAllianceActivityInfoDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val (rt, isRpc) = selectAllianceActivityInfo(session)

        if (!isRpc && rt != null) {
            session.sendMsg(MsgType.SeleteAllianceActivityInfos_1334, rt)
        }
    }

    data class SelectAllianceActivityInfoReturn(
        val selectAllianceActivityInfoRt: SeleteAllianceActivityInfosRt?,
        val isRpc: Boolean
    )

    fun selectAllianceActivityInfo(session: PlayerSession): (SelectAllianceActivityInfoReturn) {

        val rt = SeleteAllianceActivityInfosRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player

        if (player.allianceId == 0L) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return SelectAllianceActivityInfoReturn(rt.build(), false)
        }

        com.point18.slg2d.world.common.selectAllianceActivityInfo(session, player.allianceId)

        return SelectAllianceActivityInfoReturn(null, true)
    }
}
