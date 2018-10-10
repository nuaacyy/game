package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.AllianceInviteAskReq
import pb4server.AllianceInviteAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class AllianceInviteOp : World2PublicAskDealBase() {

    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.allianceInviteAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = allianceInvite(publicCache, allianceId, playerId, internalMessage)

        resp.allianceInviteAskRt = rt.build()
    }

    private fun allianceInvite(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: AllianceInviteAskReq
    ): AllianceInviteAskRt.Builder {

        val rt = newAllianceInviteAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (allianceMember == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        //验证玩家的职位是否拥有该模块操作权限
        if (!hasRight(allianceMember, com.point18.slg2d.common.constg.A_RIGHT_MEMBER_MANAGER)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        val invitePlayer = publicCache.allianceMemberCache.findAllianceMemberById(req.invitePlayerId)

        if (invitePlayer != null) {
            rt.rt = ResultCode.ALLIANCE_ALLOWED_ALREADY_EXIST.code
            return rt
        }

        return rt
    }

    fun newAllianceInviteAskRtBuilder(): AllianceInviteAskRt.Builder {
        val rt = AllianceInviteAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}