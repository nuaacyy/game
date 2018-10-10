package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.commonfunc.getNowTime
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class SelectActivityHistoryOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.selectActivityHistoryReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setSelectActivityHistoryRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        worldId: Long,
        req: SelectActivityHistoryReq
    ): SelectActivityHistoryRt.Builder {
        val rt = SelectActivityHistoryRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce != null) {
            val delInfo = LinkedList<AllianceJoinActivity>()
            for (joinActivity in alce.joinActivity) {
                // 验证是否记录已经过期了
                val rankInfo =
                    publicCache.allianceActivityRankCache.findActivityRankById(joinActivity.rankId)
                if (rankInfo == null) {
                    // 这条记录已经过期了
                    delInfo.add(joinActivity)
                    continue
                } else {
                    if (getNowTime() > rankInfo.overTime) {
                        delInfo.add(joinActivity)
                        publicCache.allianceActivityRankCache.deleteAllianceActivityRankById(rankInfo)
                        continue
                    }
                }

                val ahVo = ActivityHistoryVo.newBuilder()
                ahVo.activityProtoId = joinActivity.activityProtoId
                ahVo.overTime = (joinActivity.overTime / 1000).toInt()
                ahVo.myScore = joinActivity.myScore
                ahVo.myRank = joinActivity.myRank
                ahVo.rankId = joinActivity.rankId
                rt.addHistoryInfos(ahVo)
            }


            for (del in delInfo) {
                alce.joinActivity.remove(del)
            }
        }

        return rt
    }
}