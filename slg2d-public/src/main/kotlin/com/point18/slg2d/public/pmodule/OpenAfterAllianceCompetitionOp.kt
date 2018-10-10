package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.ALLIANCE_COMPETITION_ACTIVITY
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicManagerCache
import pb4server.Home2PublicManagerAskReq
import pb4server.Home2PublicManagerAskResp
import pb4server.OpenAfterAllianceCompetitionAskReq
import pb4server.OpenAfterAllianceCompetitionAskRt

class OpenAfterAllianceCompetitionOp : Home2PublicManagerAskDealBase() {
    override fun dealHomeAsk(
        publicCache: PublicManagerCache,
        req: Home2PublicManagerAskReq,
        resp: Home2PublicManagerAskResp.Builder
    ) {
        val internalMsg = req.openAfterAllianceCompetitionAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setOpenAfterAllianceCompetitionAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicManagerCache,
        allianceId: Long,
        playerId: Long,
        req: OpenAfterAllianceCompetitionAskReq
    ): OpenAfterAllianceCompetitionAskRt.Builder {
        val rt = newOpenAfterAllianceCompetitionAskRtBuilder()

        val allianceActivity = publicCache.publicActivityManagerCache.findPublicActivityByActivityId(
            ALLIANCE_COMPETITION_ACTIVITY
        )
        if (allianceActivity == null) {
            // 活动未开启
            rt.rt = ResultCode.PUBLIC_ACTIVITY_NO_OPEN_ERROR.code
            return rt
        }

        val alceGroup =
            publicCache.allianceCompetitionGroupCacheManager.findAllianceCompetitionGroupByAllianceId(allianceId)

        if (alceGroup != null) {
            rt.beforeRankLv = alceGroup.stateRankLv
            rt.afterRankLv = alceGroup.overRankLv
            rt.score = alceGroup.score
        }
        return rt
    }

    fun newOpenAfterAllianceCompetitionAskRtBuilder(): OpenAfterAllianceCompetitionAskRt.Builder {
        val rt = OpenAfterAllianceCompetitionAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}