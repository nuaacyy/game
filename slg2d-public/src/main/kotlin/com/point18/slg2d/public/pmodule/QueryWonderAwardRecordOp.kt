package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.QueryWonderAwardRecordAskRt
import pb4server.WonderAwardRecordVo
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp

// todo jh 没用到
class QueryWonderAwardRecordOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        req.queryWonderAwardRecordAskReq
        val rt = QueryWonderAwardRecordAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        //校验是否同联盟
        val player = publicCache.allianceMemberCache.findAllianceMemberById(req.playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setQueryWonderAwardRecordAskRt(rt)
            return
        }
        if (player.allianceId == 0L) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            resp.setQueryWonderAwardRecordAskRt(rt)
            return
        }

        val alliance = publicCache.allianceCache.findAllianceById(player.allianceId)
        if (alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setQueryWonderAwardRecordAskRt(rt)
            return
        }

        val awardMap = alliance.wonderAwardMap
        for ((awardId, playerIds) in awardMap) {
            val wonderAwardRecordVo = WonderAwardRecordVo.newBuilder()
            wonderAwardRecordVo.awardId = awardId
            wonderAwardRecordVo.addAllPlayerIds(playerIds)
            rt.addQueryAllianceAwardInfo(wonderAwardRecordVo)
        }

        resp.setQueryWonderAwardRecordAskRt(rt)
        return
    }
}