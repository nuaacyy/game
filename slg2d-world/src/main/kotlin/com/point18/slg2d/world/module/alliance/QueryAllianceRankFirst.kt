package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.QueryAllianceRankFirstRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.wpm
import pb4client.QueryAllianceRankFirstVo
import pb4client.QueryAllianceRankVo

// 查询联盟排行榜首页
class DealQueryAllianceRankFirst : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryAllianceRankFirst(session)
        if (rt != null) {
            session.sendMsg(MsgType.QueryAllianceRankFirst_500, rt)
        }
    }

    fun queryAllianceRankFirst(session: PlayerSession): (QueryAllianceRankFirstRt?) {
        val rt = QueryAllianceRankFirstRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val player = session.player

        for ((t, rs) in wpm.getWorldManagerInfoFromPublicManager().allianceRankInfo) {
            if (rs.size == 0){
                continue
            }
            val r = QueryAllianceRankFirstVo.newBuilder()
            r.rankType = t

            for (qrv in rs) {
                val queryAllianceRankVo = QueryAllianceRankVo.newBuilder()
                queryAllianceRankVo.allianceName = qrv.allianceName
                queryAllianceRankVo.allianceShortName = qrv.allianceShortName
                queryAllianceRankVo.allianceId = qrv.allianceId
                queryAllianceRankVo.flagColor = qrv.flagColor
                queryAllianceRankVo.flagStyle = qrv.flagStyle
                queryAllianceRankVo.flagEffect = qrv.flagEffect
                queryAllianceRankVo.value = qrv.value

                r.queryAllianceRankVos = queryAllianceRankVo.build()
            }

            rt.addQueryAllianceRankFirstVos(r.build())

        }

        return rt.build()
    }
}



