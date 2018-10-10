package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.ALLIANCE_MEMBER_FLAG_REMOVE
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.common.allianceMemberInfoChange
import com.point18.slg2d.public.common.kickAllianceMemberSuccess
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.RemoveAllianceMemberAskReq
import pb4server.RemoveAllianceMemberAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import java.util.*

class RemoveAllianceMemberSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.removeAllianceMemberAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setRemoveAllianceMemberAskRt(rt)
    }

    private fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: RemoveAllianceMemberAskReq
    ): RemoveAllianceMemberAskRt.Builder {
        val rt = newRemoveAllianceMemberAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        // 从联盟成员中移除该成员
        val rmPlayer = publicCache.allianceMemberCache.findAllianceMemberById(req.removePlayerId)
        if (rmPlayer == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        // 判断职位大小
        var rmPos = 0
        for ((p, _) in rmPlayer.alliancePosMap) {
            rmPos = p
        }

        var myPos = 0
        for ((p, _) in player.alliancePosMap) {
            myPos = p
        }

        val rmPosProto = com.point18.slg2d.common.pc.pcs.posRightCache.posName[rmPos]
        val myPosProto = com.point18.slg2d.common.pc.pcs.posRightCache.posName[myPos]
        if (rmPosProto == null || myPosProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        if (rmPosProto.step <= myPosProto.step) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        // 验证是否属于相同联盟
        if (player.allianceId != rmPlayer.allianceId) {
            rt.rt = ResultCode.ALLIANCE_REMOVE_FORBIDDEN.code
            return rt
        }

        resetPlayerAllianceInfo(publicCache, alce, rmPlayer, req.isRook)

        // 剔除成员联盟日志
        insertLog(publicCache, player.allianceId, com.point18.slg2d.common.constg.A_LOG_EXPEL_MEMBER, player.name, player.allianceNickName, rmPlayer.name, rmPlayer.allianceNickName)

        kickAllianceMemberSuccess(
            publicCache.publicActor,
            rmPlayer.mapPltAreaId,
            req.removePlayerId,
            allianceId,
            player.name
        )

        // 联盟成员离开推送
        val allianceMembers =
            publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
        val nowPos = LinkedList<Int>()
        for ((p, _) in rmPlayer.alliancePosMap) {
            nowPos.add(p)
        }

        val pltAreas = mutableMapOf<Long, Int>()

        for (am in allianceMembers) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            allianceMemberInfoChange(
                publicCache.publicActor,
                pltAreaId, allianceId, ALLIANCE_MEMBER_FLAG_REMOVE,
                req.removePlayerId, rmPlayer.name, nowPos, rmPlayer.onlineState, rmPlayer.photoProtoId
            )
        }

        return rt
    }

    private fun newRemoveAllianceMemberAskRtBuilder(): RemoveAllianceMemberAskRt.Builder {
        val rt = RemoveAllianceMemberAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}