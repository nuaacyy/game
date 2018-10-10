package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class QueryAllianceMemberSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.queryAllianceMemberAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setQueryAllianceMemberAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: QueryAllianceMemberAskReq
    ): QueryAllianceMemberAskRt.Builder {
        val rt = newQueryAllianceMemberAskRtBuilder()

        var qty = 0
        if (allianceId > 0) {
            val alce = publicCache.allianceCache.findAllianceById(allianceId)
            if (alce == null) {
                rt.rt = ResultCode.ALLIANCE_ARGS_ERROR.code
                return rt
            }

            // 获取联盟成员的玩家ID列表
            for (member in publicCache.allianceMemberCache.findAllianceMembersByAllianceId(
                allianceId
            )) {
                // 判断年和周是否发生变化

                if (member.onlineState == com.point18.slg2d.common.constg.OnlineType) {
                    qty++
                }

                val positions = LinkedList<Int>()
                for ((p, _) in member.alliancePosMap) {
                    positions.add(p)
                }

                // 玩家是否沦陷标记位
                val memberVoBuilder = QueryAllianceMemberVo.newBuilder()
                memberVoBuilder.id = member.id
                memberVoBuilder.name = member.name
                memberVoBuilder.photoProtoId = member.photoProtoId
                memberVoBuilder.ctrbtTotal = 0
                memberVoBuilder.ctrbtWeek = 0
                memberVoBuilder.powerValue = member.memPower
                memberVoBuilder.landX = 0
                memberVoBuilder.landY = 0
                memberVoBuilder.fightValue = 0
                memberVoBuilder.isOnline = member.onlineState
                memberVoBuilder.tsLandX = 0
                memberVoBuilder.tsLandY = 0
                memberVoBuilder.occupiedFlag = 0
                memberVoBuilder.makeOverTime = 0
                memberVoBuilder.nickName = member.allianceNickName
                memberVoBuilder.lastOffTime = member.lastLeaveTime
                memberVoBuilder.mapAreaNo = member.mapAreaNo
                memberVoBuilder.monsterScore = member.monsterScore
                memberVoBuilder.lastGetMonsterScore = member.lastGetMonsterScore
                memberVoBuilder.addAllPositions(positions)
                // 如果是联盟盟主，并且盟主进行了盟主转让操作，需要显示执行转让时间
                if (member.id == alce.mainPlayerId || member.id == alce.makeOverPid) {
                    memberVoBuilder.makeOverTime = alce.makeOverTime
                }

                rt.addQueryAllianceMemberVos(memberVoBuilder)
                rt.onlineQty = qty
            }
        }

        return rt
    }

    fun newQueryAllianceMemberAskRtBuilder(): QueryAllianceMemberAskRt.Builder {
        val rt = QueryAllianceMemberAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.onlineQty = 0
        return rt
    }
}