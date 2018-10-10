package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.QueryInAllianceRank
import pb4client.QueryInAllianceRankRt
import com.point18.slg2d.common.resultcode.ResultCode

//查询联盟内部数据排行榜
class DealQueryInAllianceRank : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rankType = (msg as QueryInAllianceRank).rankType
        val rt = this.queryInAllianceRank(session, rankType)
        if (rt != null) {
            session.sendMsg(MsgType.QueryInAllianceRank_915, rt)
        }
    }

    fun queryInAllianceRank(session: PlayerSession, rankType: Int): (QueryInAllianceRankRt?) {
        val rt = QueryInAllianceRankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player

        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_PLAYER_NO_JOIN.code)
            return rt.build()
        }

        com.point18.slg2d.world.common.queryInAllianceRank(session, player.allianceId, rankType)

        return null
    }

}


