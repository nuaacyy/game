package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class SelectNowRankOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val internalMessage = req.selectNowRankReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setSelectNowRankRt(rt)
    }

    fun dealMsg(publicCache: PublicCache,
                allianceId: Long,
                playerId: Long,
                worldId: Long,
                req: SelectNowRankReq): SelectNowRankRt.Builder {

        val activityId = req.activityId
        val rt = SelectNowRankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val eventInProto = com.point18.slg2d.common.pc.pcs.eventAllianceInformationProtoCache.protoMapById[activityId]
        if (eventInProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        val allJoinActivityAlliances =
            publicCache.everyAllianceActivityCache.findAllAlliancectivityInfos(activityId)
        for (info in allJoinActivityAlliances) {
            val par = AllianceActivityRankAskVo.newBuilder()
            val rInfo = publicCache.allianceCache.findAllianceById(info.allianceId)
            if (rInfo == null) {
                continue
            }
            par.allianceId = rInfo.id
            par.allianceName = rInfo.name
            par.shortName = rInfo.shortName
            par.myScore = info.score
            par.flagColor = rInfo.flagColor
            par.flagStyle = rInfo.flagStyle
            par.flagEffect = rInfo.flagEffect

            rt.addAllianceActivityRankVos(par)
        }
        return rt
    }
}