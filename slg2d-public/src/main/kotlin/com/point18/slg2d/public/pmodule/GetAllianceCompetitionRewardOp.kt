package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.GetAllianceCompetitionRewardAskReq
import pb4server.GetAllianceCompetitionRewardAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp

class GetAllianceCompetitionRewardOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.getAllianceCompetitionRewardAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setGetAllianceCompetitionRewardAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: GetAllianceCompetitionRewardAskReq
    ): GetAllianceCompetitionRewardAskRt.Builder {
        val rt = newGetAllianceCompetitionRewardAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)

        if (alce == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        if (alce.allianceCompetitionTicket == com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET) {
            rt.rt = ResultCode.NO_JOIN_ALLIANCE_COMPETITION.code
            return rt
        }

        if (alce.allianceCompetitionScore <= req.maxScore) {
            rt.rt = ResultCode.GET_ALLIANCE_COMPETITION_REWARD_NO_SCORE_ERROR.code
            return rt
        }

        return rt
    }

    fun newGetAllianceCompetitionRewardAskRtBuilder(): GetAllianceCompetitionRewardAskRt.Builder {
        val rt = GetAllianceCompetitionRewardAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}