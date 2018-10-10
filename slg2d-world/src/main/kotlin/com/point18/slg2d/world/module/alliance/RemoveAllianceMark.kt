package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceRemoveMark
import pb4client.AllianceRemoveMarkRt
import com.point18.slg2d.common.resultcode.ResultCode

//删除联盟标记/集结请求标记 823
class DealRemoveAllianceMark : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val markId = (msg as AllianceRemoveMark).markId
        val rt = this.removeMark(session, markId)

        if (rt != null) {
            session.sendMsg(MsgType.AllianceRemoveMark_823, rt)
        }
    }

    //823：删除联盟标记
    private fun removeMark(session: PlayerSession, markId: Long): (AllianceRemoveMarkRt?) {
        val rt = AllianceRemoveMarkRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // 删除联盟标记
        val player = session.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_ARGS_ERROR.code)
            return rt.build()
        }

        com.point18.slg2d.world.common.removeAllianceMark(session, player.allianceId, markId)

        return null
    }

}


