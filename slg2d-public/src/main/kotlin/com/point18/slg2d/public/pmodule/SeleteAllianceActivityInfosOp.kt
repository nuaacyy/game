package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class SeleteAllianceActivityInfosOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val internalMessage = req.seleteAllianceActivityInfosReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setSeleteAllianceActivityInfosRt(rt)
    }

    fun dealMsg(publicCache: PublicCache,
                allianceId: Long,
                playerId: Long,
                worldId: Long,
                req: SeleteAllianceActivityInfosReq): SeleteAllianceActivityInfosRt.Builder {
        val rt = SeleteAllianceActivityInfosRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val allianceActivityInfos =
            publicCache.everyAllianceActivityCache.findAllianceActivityInfosByAllianceId(allianceId)

        for (activity in allianceActivityInfos) {
            val eventInProto = com.point18.slg2d.common.pc.pcs.eventAllianceInformationProtoCache.protoMapById[activity.activityId]
            if (eventInProto == null) {
                continue
            }

            var myRank = 0
            val allJoinActivityAlliances =
                publicCache.everyAllianceActivityCache.findAllAlliancectivityInfos(activity.activityId)
            for (index in allJoinActivityAlliances.indices) {
                val info = allJoinActivityAlliances[index]
                if (info.allianceId == allianceId) {
                    myRank = index + 1
                    break
                }
            }

            val allianceActivityInfo = AllianceActivityInfoVo.newBuilder()
            allianceActivityInfo.activityId = activity.activityId
            allianceActivityInfo.score = activity.score
            allianceActivityInfo.rank = myRank
            rt.addAllianceActivityInfos(allianceActivityInfo)
        }

        return rt
    }
}
