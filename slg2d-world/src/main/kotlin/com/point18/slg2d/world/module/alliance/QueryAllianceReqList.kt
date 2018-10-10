//查询申请加入联盟的玩家信息
package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.common.queryAllianceReqList
import pb4client.AllianceQueryReqListRt
import com.point18.slg2d.common.resultcode.ResultCode

//查询申请加入联盟的玩家信息 810
class DealQueryAllianceReqList : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryReqList(session)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceQueryReqList_810, rt)
        }
    }

    fun queryReqList(session: PlayerSession): (AllianceQueryReqListRt?) {
        val rt = AllianceQueryReqListRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_PLAYER_NO_JOIN.code)
            return rt.build()
        }

        queryAllianceReqList(session, player.allianceId)

        return null
    }
}




