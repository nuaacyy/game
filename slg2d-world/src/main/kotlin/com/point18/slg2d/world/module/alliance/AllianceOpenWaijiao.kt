package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import pb4client.AllianceOpenWaijiao
import pb4client.AllianceOpenWaijiaoRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.openAllianceWaijiao

// 打开联盟外交界面
class AllianceOpenWaijiao : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val tmpMsg = msg as AllianceOpenWaijiao
        val allianceId = tmpMsg.allianceId
        val rt = this.openWaijiao(session, allianceId)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceOpenWaijiao_895, rt)
        }
    }

    fun openWaijiao(session: PlayerSession, allianceId: Long): (AllianceOpenWaijiaoRt?) {
        val rt = AllianceOpenWaijiaoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val player = session.player

        // 判断玩家每日次数
        if (isAfterGameRefTime(player.lastWaijiaoCount)) {
            player.waijiaoCount = 0
            player.lastWaijiaoCount = getNowTime()
        }

        openAllianceWaijiao(session, allianceId)

        return null
    }
}




