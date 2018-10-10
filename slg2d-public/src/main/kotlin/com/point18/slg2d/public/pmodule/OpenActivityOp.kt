package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class OpenActivityOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val internalMessage = req.openActivityAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setOpenActivityAskRt(rt)
    }

    fun dealMsg(publicCache: PublicCache,
                allianceId: Long,
                playerId: Long,
                worldId: Long,
                req: OpenActivityAskReq
    ): OpenActivityAskRt.Builder {

        val rt = OpenActivityAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val allianceActivityInfoss =
            publicCache.everyAllianceActivityCache.findAllianceActivityInfosByAllianceId(allianceId)
        allianceActivityInfoss.sortByDescending { it.score }
        for (allianceActivityInfos in allianceActivityInfoss) {
            val openActivityAskVo = OpenActivityAskVo.newBuilder()
            var myRank = 0
            val allJoinActivityAlliances = publicCache.everyAllianceActivityCache.findAllAlliancectivityInfos(
                allianceActivityInfos.activityId
            )
            var nowRank = 1
            for (info in allJoinActivityAlliances) {
                if (info.allianceId == allianceId) {
                    myRank = nowRank
                    break
                }

                nowRank++
            }
            openActivityAskVo.myRank = myRank
            openActivityAskVo.score = allianceActivityInfos.score
            openActivityAskVo.activityId = allianceActivityInfos.activityId
            openActivityAskVo.castleLv = 0
            rt.addInfos(openActivityAskVo.build())
        }

        return rt
    }
}