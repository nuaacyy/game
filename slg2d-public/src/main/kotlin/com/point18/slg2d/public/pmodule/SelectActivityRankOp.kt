package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class SelectActivityRankOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.selectActivityRankAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setSelectActivityRankAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        worldId: Long,
        req: SelectActivityRankAskReq
    ): SelectActivityRankAskRt.Builder {

        val rt = SelectActivityRankAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val rankId = req.rankId
        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce != null) {
            val rankInfo = publicCache.allianceActivityRankCache.findActivityRankById(rankId)
            if (rankInfo == null) {
                // 这条记录已经过期了
                rt.rt = ResultCode.ACTIVITY_RANK_TIME_OVER.code
                return rt
            } else {
                if (getNowTime() > rankInfo.overTime) {
                    publicCache.allianceActivityRankCache.deleteAllianceActivityRankById(rankInfo)
                    rt.rt = ResultCode.ACTIVITY_RANK_TIME_OVER.code
                    return rt
                }
            }

            for ((_, rInfo) in rankInfo.rankInfoMap) {
                val par = AllianceActivityRankAskVo.newBuilder()
                par.allianceId = rInfo.allianceId
                par.allianceName = rInfo.allianceName
                par.shortName = rInfo.allianceShortName
                par.myScore = rInfo.score
                par.flagColor = rInfo.flagColor
                par.flagStyle = rInfo.flagStyle
                par.flagEffect = rInfo.flagEffect

                rt.addAllianceActivityRankVos(par.build())
            }
        }

        return rt
    }
}