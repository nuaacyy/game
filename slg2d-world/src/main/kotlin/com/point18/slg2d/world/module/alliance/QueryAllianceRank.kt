package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.AllianceCompetitionRank
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.wpm
import pb4client.QueryAllianceRank
import pb4client.QueryAllianceRankRt
import pb4client.QueryAllianceRankVo

// 查询联盟排行榜
class DealQueryAllianceRank : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rankType = (msg as QueryAllianceRank).rankType
        val page = msg.page
        val num = msg.num
        val rt = this.queryAllianceRank(session, rankType, page, num)
        if (rt != null) {
            session.sendMsg(MsgType.QueryAllianceRank_916, rt)
        }
    }

    fun queryAllianceRank(
        session: PlayerSession,
        rankType: Int,
        page: Int,
        num: Int
    ): (QueryAllianceRankRt?) {
        val rt = QueryAllianceRankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        var myAllianceRank = 0
        var myAllianceScore = 0L
        val ranks = wpm.getWorldManagerInfoFromPublicManager().allianceRankInfo[rankType]
        if (ranks != null) {
            // 如果是联盟总动员的小组排行榜的话 过滤到不是我这组的数据
            var groupId = -1
            if (rankType == AllianceCompetitionRank) {
                for (r in ranks) {
                    if (r.allianceId == session.player.allianceId) {
                        groupId = r.extend1.toInt()
                    }
                }
            }

            var nowIndex = 1
            for (r in ranks) {
                val thisExtend1 = r.extend1.toIntOrNull()
                if (thisExtend1 == null) {
                    continue
                }
                if (rankType == AllianceCompetitionRank && groupId != -1 && r.extend1.toInt() != groupId) {
                    continue
                }
                val queryAllianceRankVo = QueryAllianceRankVo.newBuilder()

                queryAllianceRankVo.allianceName = r.allianceName
                queryAllianceRankVo.allianceShortName = r.allianceShortName
                queryAllianceRankVo.allianceId = r.allianceId
                queryAllianceRankVo.flagColor = r.flagColor
                queryAllianceRankVo.flagStyle = r.flagStyle
                queryAllianceRankVo.flagEffect = r.flagEffect
                queryAllianceRankVo.value = r.value

                rt.addQueryAllianceRankVos(queryAllianceRankVo)

                if (r.allianceId == session.player.allianceId) {
                    myAllianceRank = nowIndex
                    myAllianceScore = r.value
                }

                nowIndex++
            }
        }

        rt.myAllianceRank = myAllianceRank
        rt.myAllianceScore = myAllianceScore

        return rt.build()
    }
}



