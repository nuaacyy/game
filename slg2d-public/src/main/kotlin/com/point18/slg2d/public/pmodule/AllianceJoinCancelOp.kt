package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.public.common.allianceReqInfoChange
import com.point18.slg2d.common.constg.REMOVE_ALLIANCE_INFO
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class AllianceJoinCancelOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
            publicCache: PublicCache,
            req: World2PublicAskReq,
            resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.allianceJoinCancelAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setAllianceJoinCancelAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: AllianceJoinCancelAskReq
    ): AllianceJoinCancelAskRt.Builder {

        val rt = newAllianceJoinCancelAskRtBuilder()

        // 验证是否存在对应的请求
        val reqV = publicCache.allianceReqCache.findAllianceReqByAidWithPid(allianceId, playerId)
        if (reqV == null) {
            rt.rt = ResultCode.ALLIANCE_JOIN_REQ_NOT_EXIST.code
            return rt
        }

        // 删除请求
        publicCache.allianceReqCache.deleteAllianceReq(reqV)

        // val cReq = AllianceQueryReqListInfoVo()
        val cReq = AllianceQueryReqListInfoVo.newBuilder()
        cReq.id = reqV.playerId
        cReq.name = reqV.playerName
        cReq.photoProtoId = reqV.playerPhoto
        cReq.fightValue = reqV.playerFightValue

        // 推送
        val allAllianceMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
        val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

        for (am in allAllianceMembers) {
            if (hasRight(am, com.point18.slg2d.common.constg.A_RIGHT_MEMBER_MANAGER)) {
                val ps = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
                ps.add(am.id)
            }
        }

        for ((pltAreaId, playerIds) in pltAreas) {
            // allianceReqInfoChange(pltAreaId, playerIds, constg.REMOVE_ALLIANCE_INFO, cReq)
            allianceReqInfoChange(
                    publicCache.publicActor,
                    pltAreaId,
                    playerIds,
                    REMOVE_ALLIANCE_INFO,
                    cReq.build()
            )
        }

        return rt
    }

    fun newAllianceJoinCancelAskRtBuilder(): AllianceJoinCancelAskRt.Builder {
        val rt = AllianceJoinCancelAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}