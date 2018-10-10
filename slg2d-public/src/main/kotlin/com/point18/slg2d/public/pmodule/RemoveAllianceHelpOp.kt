package com.point18.slg2d.public.pmodule

import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.RemoveAllianceHelpAskReq
import pb4server.RemoveAllianceHelpAskRt
import com.point18.slg2d.public.common.allianceHelpInfoChange
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.constg.REMOVE_ALLIANCE_INFO
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class RemoveAllianceHelpOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.removeAllianceHelpAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setRemoveAllianceHelpAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: RemoveAllianceHelpAskReq
    ): RemoveAllianceHelpAskRt.Builder {

        val rt = newRemoveAllianceHelpAskRtBuilder()

        val helpId = req.helpId

        val alliance = publicCache.allianceCache.findAllianceById(allianceId)
        if (alliance != null) {
            val helpVo = publicCache.allianceHelpCache.findAllianceHelpById(helpId)
            if (helpVo != null) {
                publicCache.allianceHelpCache.deleteAllianceHelp(helpVo)

                val helpAllianceMember =
                        publicCache.allianceMemberCache.findAllianceMemberById(playerId)
                if (helpAllianceMember == null) {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    return rt
                }
                val allAllianceMembers = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(
                        helpVo.allianceId
                )

                val hIds = LinkedList<Long>()
                hIds.add(helpId)
                val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

                for (am in allAllianceMembers) {
                    val ps = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
                    if (helpAllianceMember.mapPltAreaId == am.mapPltAreaId && helpAllianceMember.id != am.id) {
                        ps.add(am.id)
                    }
                }

                for ((pltAreaId, playerIds) in pltAreas) {
                    allianceHelpInfoChange(
                            publicCache.publicActor,
                            pltAreaId,
                            playerIds,
                            REMOVE_ALLIANCE_INFO,
                            hIds
                    )
                }
            }
        }

        return rt
    }

    fun newRemoveAllianceHelpAskRtBuilder(): RemoveAllianceHelpAskRt.Builder {
        val rt = RemoveAllianceHelpAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}