package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.KING_PROTO_ID
import com.point18.slg2d.common.constg.NO_MOVE_SERVER
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.SetPublicMoveServerStateReq
import pb4server.SetPublicMoveServerStateRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp

class SetPublicMoveServerStateOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.setPublicMoveServerStateReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setSetPublicMoveServerStateRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: SetPublicMoveServerStateReq
    ): SetPublicMoveServerStateRt.Builder {
        val rt = SetPublicMoveServerStateRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        // 玩家是否有联盟
        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        player.inMoveServer = req.state
        if (req.state == NO_MOVE_SERVER) {
            // 修改数据
            player.mapAreaNo = req.areaNo
            player.mapPltAreaId = req.newWorldId

            // 清空官职
            val toClean = arrayListOf<Long>()
            for ((worldId, officeId) in player.officeMap) {
                if (officeId != KING_PROTO_ID) {
                    toClean.add(worldId)
                }
            }
            for (worldId in toClean) {
                player.officeMap.remove(worldId)
            }

            if (alce.mainPlayerId == playerId) {
                alce.allianceAreaNo = req.areaNo
                // 推送到公共服
                syncAllianceInfo2AM(
                    publicCache,
                    alce,
                    0,
                    0,
                    0
                )
            }
        }


        return rt
    }

}