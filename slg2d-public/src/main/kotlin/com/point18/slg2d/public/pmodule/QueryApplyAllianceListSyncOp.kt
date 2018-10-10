package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*

class QueryApplyAllianceListSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.queryApplyAllianceListAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setQueryApplyAllianceListAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: QueryApplyAllianceListAskReq
    ): QueryApplyAllianceListAskRt.Builder {
        val rt = newQueryApplyAllianceListAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        //查询是否存在请求
        val reqVo =
            publicCache.allianceReqCache.findAllianceReqByAidWithPid(alce.id, playerId)
        if (reqVo == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val allianceQueryListInfo = AllianceQueryListInfoVo.newBuilder()
        allianceQueryListInfo.id = alce.id
        allianceQueryListInfo.name = alce.name
        allianceQueryListInfo.shortName = alce.shortName
        allianceQueryListInfo.reservePlayers = alce.allianceMemberNum
        allianceQueryListInfo.powerValue = publicCache.allianceMemberCache.findAlliancesAllPower(
            alce.id
        ).toLong()
        allianceQueryListInfo.operate = 1
        allianceQueryListInfo.allianceLan = alce.allianceLan
        allianceQueryListInfo.canAddPower = alce.canAddPower
        allianceQueryListInfo.canReqPower = alce.powerLimit
        allianceQueryListInfo.flagColor = alce.flagColor
        allianceQueryListInfo.flagStyle = alce.flagStyle
        allianceQueryListInfo.flagEffect = alce.flagEffect
        allianceQueryListInfo.alliancePower = alce.alliancePower
        allianceQueryListInfo.areaNo = alce.allianceAreaNo

        val giftVo = publicCache.allianceGiftCache.findAllianceGiftById(alce.id)
        if (giftVo != null) {
            allianceQueryListInfo.giftLv = giftVo.giftLv
        }

        rt.setAlliance(allianceQueryListInfo)

        return rt
    }

    fun newQueryApplyAllianceListAskRtBuilder(): QueryApplyAllianceListAskRt.Builder {
        val rt = QueryApplyAllianceListAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}