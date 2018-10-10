package com.point18.slg2d.public.pmodule

import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.SendAllianceHelpAskReq
import pb4server.SendAllianceHelpAskRt
import com.point18.slg2d.public.common.allianceHelpInfoChange
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.constg.ADD_ALLIANCE_INFO
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class SendAllianceHelpOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.sendAllianceHelpAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setSendAllianceHelpAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: SendAllianceHelpAskReq
    ): SendAllianceHelpAskRt.Builder {
        val rt = newSendAllianceHelpAskRtBuilder()

        val alliance = publicCache.allianceCache.findAllianceById(allianceId)
        if (alliance == null) {
            rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
            return rt
        }

        val helpType = req.helpType
        val helpValue1 = req.helpValue1
        val helpValue2 = req.helpValue2
        val helpValue3 = req.helpValue3
        val helpValue4 = req.helpValue4
        val nowHelpNum = req.nowHelpNum

        // 推送小红点给帮友
        val helpAllianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (helpAllianceMember == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val allianceHelpVo = publicCache.allianceHelpCache.createAllianceHelp(
                helpType,
                playerId,
                helpValue1,
                helpValue2,
                helpValue3,
                helpValue4,
                alliance.id
        )
        allianceHelpVo.nowHelpNum = nowHelpNum

        val allAllianceMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)

        val hIds = LinkedList<Long>()
        hIds.add(allianceHelpVo.id)

        val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

        for (am in allAllianceMembers) {
            val ps = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
            if (helpAllianceMember.mapPltAreaId == am.mapPltAreaId && helpAllianceMember.id != am.id) {
                ps.add(am.id)
            }
        }

        for ((pltAreaId, playerIds) in pltAreas) {
            allianceHelpInfoChange(publicCache.publicActor, pltAreaId, playerIds, ADD_ALLIANCE_INFO, hIds)
        }

        rt.helpId = allianceHelpVo.id

        return rt
    }

    fun newSendAllianceHelpAskRtBuilder(): SendAllianceHelpAskRt.Builder {
        val rt = SendAllianceHelpAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}