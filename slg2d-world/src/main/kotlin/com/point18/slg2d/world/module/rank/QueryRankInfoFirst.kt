package com.point18.slg2d.world.module.rank

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.uiCondition.uiConditionCheck
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.RANK_OPEN
import pb4client.QueryRankFirstRt
import pb4client.QueryRankFirstVo
import pb4client.QueryRankInfoRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.findOfficeByPlayerId
import java.util.*

// 查看排行榜首页
class QueryRankFirstDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        // 数据返回定义
        val rt = queryRankFirst(session)

        // 发送数据
        session.sendMsg(MsgType.QueryRankFirst_501, rt)
    }

    fun queryRankFirst(session: PlayerSession): (QueryRankFirstRt) {
        val rt = QueryRankFirstRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache

        val player = session.player

        // 功能开启
        val uiConditionRt = uiConditionCheck(areaCache, player, RANK_OPEN)
        if (uiConditionRt != ResultCode.SUCCESS.code) {
            rt.rt = uiConditionRt
            return rt.build()
        }

        val rrs = LinkedList<QueryRankFirstVo>()

        for ((rankType, rankMap) in areaCache.rankCache.rankMap) {
            val ranks = rankMap.queryValue(1)
            if (ranks.size != 1) {
                continue
            }

            val p = areaCache.playerCache.findPlayerById(ranks[0].playerId)
            if (p == null) {
                continue
            }
            val currentPos = findOfficeByPlayerId(areaCache, ranks[0].playerId)
            val queryRankInfoRt = QueryRankInfoRt.newBuilder()
            queryRankInfoRt.name = p.name
            queryRankInfoRt.photoProtoId = p.photoProtoId
            queryRankInfoRt.score = rankMap.findTByV(ranks[0]).toInt()
            queryRankInfoRt.allianceShortName = p.allianceShortName
            queryRankInfoRt.playerId = p.id
            queryRankInfoRt.curentPos = currentPos
            queryRankInfoRt.nickName = p.allianceNickName
            queryRankInfoRt.robotProtoId = 0

            val queryRankFirstVo = QueryRankFirstVo.newBuilder()
            queryRankFirstVo.rankType = rankType
            queryRankFirstVo.rankInfo = queryRankInfoRt.build()

            rrs.add(queryRankFirstVo.build())
        }

        rt.addAllQueryRankFirstVos(rrs)
        return rt.build()
    }

}