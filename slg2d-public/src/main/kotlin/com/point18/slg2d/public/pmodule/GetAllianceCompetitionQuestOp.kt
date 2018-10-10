package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.GetAllianceCompetitionQuestAskReq
import pb4server.GetAllianceCompetitionQuestAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp

class GetAllianceCompetitionQuestOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.getAllianceCompetitionQuestAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setGetAllianceCompetitionQuestAskRt(rt)
    }

     fun dealMsg(
         publicCache: PublicCache,
         allianceId: Long,
         playerId: Long,
         req: GetAllianceCompetitionQuestAskReq
     ): GetAllianceCompetitionQuestAskRt.Builder {
        val rt = newGetAllianceCompetitionQuestAskRtBuilder()

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

        val allianceMemberTrace = publicCache.allianceMemberTraceCache.findAllianceMemberTraceByAllianceIdAndPlayerId(
            alce.id,
            playerId
        )
        if (allianceMemberTrace == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val questProto = com.point18.slg2d.common.pc.pcs.allianceCompetitionQuestProtoCache.protoMapById[quest.questId]

        if (questProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        rt.questId = quest.questId

        quest.questId = 0
        quest.refTime = getNowTime() + com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceCompetitionInitialTime * 1000

        allianceMemberTrace.allianceCompetitionQuestGetNum += 1

        return rt
    }

    fun newGetAllianceCompetitionQuestAskRtBuilder(): GetAllianceCompetitionQuestAskRt.Builder {
        val rt = GetAllianceCompetitionQuestAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}