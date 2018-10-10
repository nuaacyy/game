package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.CancelAllianceCompetitionQuestAskReq
import pb4server.CancelAllianceCompetitionQuestAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp

class CancelAllianceCompetitionQuestOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.cancelAllianceCompetitionQuestAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setCancelAllianceCompetitionQuestAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: CancelAllianceCompetitionQuestAskReq
    ): CancelAllianceCompetitionQuestAskRt.Builder {
        val rt = newCancelAllianceCompetitionQuestAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        if (alce.allianceCompetitionTicket == com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET) {
            rt.rt = ResultCode.NO_JOIN_ALLIANCE_COMPETITION.code
            return rt
        }

        val quest = publicCache.allianceCompetitionQuestCache.findAllianceCompetitionQuestByAllianceIdAndIndex(
            allianceId,
            req.inedx
        )

        if (quest == null || quest.questId == 0 || quest.refTime != 0L) {
            rt.rt = ResultCode.ALLIANCE_COMPETITION_QUEST_CAN_NO_GET.code
            return rt
        }

        quest.questId = 0
        quest.refTime = getNowTime() + (com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceCompetitionInitialTime * 1000)

        return rt
    }

    fun newCancelAllianceCompetitionQuestAskRtBuilder(): CancelAllianceCompetitionQuestAskRt.Builder {
        val rt = CancelAllianceCompetitionQuestAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}