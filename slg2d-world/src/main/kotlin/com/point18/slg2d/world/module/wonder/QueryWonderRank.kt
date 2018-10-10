package com.point18.slg2d.world.module.wonder

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.WonderRankVo
import com.point18.slg2d.world.common.isWonderPeace
import com.point18.slg2d.world.module.ReqDealWithConn
import pb4client.AllianceActivityRankVo
import pb4client.QueryWonderRank
import pb4client.QueryWonderRankRt
import xyz.ariane.util.lzInfo
import java.util.*

// 查询奇观争夺联盟排行
class QueryWonderRankDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val rt = queryWonderRank(cache, channelActor, msg as QueryWonderRank, playerId)
        if (rt != null) {
            val scMsg =
                ScMessageAtSend(MsgType.QueryWonderRank_1572, cache.currentClientMsgNo, rt.build())
            channelActor.tellNoSender(scMsg)
        }
    }

    private fun queryWonderRank(areaCache: AreaCache, channelActor: ActorRef, msg: QueryWonderRank, playerId: Long): QueryWonderRankRt.Builder? {
        val rtBuilder = newQueryWonderRankRtBuilder()
        val protoId = msg.protoId
        val allianceId = msg.allianceId

        val wonder = areaCache.wonderCache.findWonder(protoId)
        if (wonder == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        if (!isWonderPeace(wonder)) {
            rtBuilder.rt = ResultCode.WONDER_NOT_PEACE.code
            return rtBuilder
        }
        if (wonder.rankInfoMap.isEmpty()) {
            return rtBuilder
        }

        // TODO jh 排行榜优化
        val rankList = LinkedList<WonderRankVo>()
        wonder.rankInfoMap.values.forEach {
            rankList.add(it)
        }
        rankList.sortByDescending { it.score }

        var limitIndex = 10 - 1
        if (rankList.size < 10) {
            limitIndex = rankList.size - 1
        }
        for (i in 0..limitIndex) {
            val rankBuilder = AllianceActivityRankVo.newBuilder()
            val rankVo = rankList[i]
            rankBuilder.allianceId = rankVo.allianceId

            // 本服没有这个联盟的玩家，用排行榜记录中的联盟信息
            val recordAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(rankVo.allianceId)
            if (recordAllianceMembers.isEmpty()) {
                normalLog.lzInfo { "找不到排行榜记录的联盟信息:${rankVo.allianceId}" }
                rankBuilder.shortName = rankVo.allianceShortName
                rankBuilder.allianceName = rankVo.allianceName
                rankBuilder.flagColor = rankVo.flagColor
                rankBuilder.flagStyle = rankVo.flagStyle
                rankBuilder.flagEffect = rankVo.flagEffect
                rankBuilder.myScore = (rankVo.score / 1000).toInt()
                rtBuilder.addTopRankVos(rankBuilder)
                continue
            }
            val recordPlayer = recordAllianceMembers[0]

            rankBuilder.shortName = recordPlayer.allianceShortName
            rankBuilder.allianceName = recordPlayer.allianceName
            rankBuilder.flagColor = recordPlayer.flagColor
            rankBuilder.flagStyle = recordPlayer.flagStyle
            rankBuilder.flagEffect = recordPlayer.flagEffect
            rankBuilder.myScore = (rankVo.score / 1000).toInt()
            rtBuilder.addTopRankVos(rankBuilder)
        }

        val rankVo = wonder.rankInfoMap[allianceId] ?: return rtBuilder

        val rankBuilder = AllianceActivityRankVo.newBuilder()
        rankBuilder.allianceId = allianceId

        val recordAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(allianceId)
        if (recordAllianceMembers.isEmpty()) {
            normalLog.lzInfo { "找不到排行榜记录的联盟信息:${rankVo.allianceId}" }
            rankBuilder.shortName = rankVo.allianceShortName
            rankBuilder.allianceName = rankVo.allianceName
            rankBuilder.flagColor = rankVo.flagColor
            rankBuilder.flagStyle = rankVo.flagStyle
            rankBuilder.flagEffect = rankVo.flagEffect
            rankBuilder.myScore = (rankVo.score / 1000).toInt()
            rtBuilder.setSelfRankVo(rankBuilder)
            return rtBuilder
        }
        val recordPlayer = recordAllianceMembers[0]

        rankBuilder.shortName = recordPlayer.allianceShortName
        rankBuilder.allianceName = recordPlayer.allianceName
        rankBuilder.flagColor = recordPlayer.flagColor
        rankBuilder.flagStyle = recordPlayer.flagStyle
        rankBuilder.flagEffect = recordPlayer.flagEffect
        rankBuilder.myScore = (rankVo.score / 1000).toInt()
        rtBuilder.setSelfRankVo(rankBuilder)

        return rtBuilder
    }

    private fun newQueryWonderRankRtBuilder(): QueryWonderRankRt.Builder {
        val rtBuilder = QueryWonderRankRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        return rtBuilder
    }

}