package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ADD_ALLIANCE_INFO
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.common.constg.ALLIANCE_COMPETITION_ACTIVITY
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.ppm
import pb4server.*

class RewardAllianceCompetitionQuestOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.rewardAllianceCompetitionQuestAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setRewardAllianceCompetitionQuestAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: RewardAllianceCompetitionQuestAskReq
    ): RewardAllianceCompetitionQuestAskRt.Builder {
        val rt = newRewardAllianceCompetitionQuestAskRtBuilder()

        // todo 是否要异步去看呢?
//        val allianceActivity =
//            publicCache.publicActivityCache.findPublicActivityByActivityId(
//                ALLIANCE_COMPETITION_ACTIVITY
//            )
//        if (allianceActivity == null) {
//            // 活动未开启
//            rt.rt = ResultCode.PUBLIC_ACTIVITY_NO_OPEN_ERROR.code
//            return rt
//        }
//
//        if (allianceActivity.nowState != com.point18.slg2d.common.constg.ALLIANCE_ACTIVITY_GOIN) {
//            rt.rt = ResultCode.ALLIANCE_COMPETITION_NO_REWARD_TIME_ERROR.code
//            return rt
//        }

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        if (alce.allianceCompetitionTicket == com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET) {
            rt.rt = ResultCode.NO_JOIN_ALLIANCE_COMPETITION.code
            return rt
        }

        alce.allianceCompetitionScore += req.addScore
        alce.allianceCompetitionScoreChangeTime = getNowTime()
        val allianceMemberTrace =
            publicCache.allianceMemberTraceCache.findAllianceMemberTraceByAllianceIdAndPlayerId(
                alce.id,
                playerId
            )
        if (allianceMemberTrace == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        allianceMemberTrace.allianceCompetitionScore += req.addScore
        allianceMemberTrace.allianceCompetitionScoreChangeTime = getNowTime()
        allianceMemberTrace.allianceCompetitionQuestSuccessNum += 1

        // 推送到公共服
        syncAllianceInfo2AM(
            publicCache,
            alce,
            0,
            0,
            0
        )

        return rt
    }

    fun newRewardAllianceCompetitionQuestAskRtBuilder(): RewardAllianceCompetitionQuestAskRt.Builder {
        val rt = RewardAllianceCompetitionQuestAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}