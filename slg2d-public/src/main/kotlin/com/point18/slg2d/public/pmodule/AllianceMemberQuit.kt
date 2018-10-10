package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.AllianceMemberQuitAskReq
import pb4server.AllianceMemberQuitAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import com.point18.slg2d.public.common.allianceMemberInfoChange
import com.point18.slg2d.common.constg.ALLIANCE_MEMBER_FLAG_QUIT
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class AllianceMemberQuitSyncOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.allianceMemberQuitAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setAllianceMemberQuitAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: AllianceMemberQuitAskReq
    ): AllianceMemberQuitAskRt.Builder {
        val rt = newAllianceMemberQuitAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        if (playerId == alce.mainPlayerId) {
            rt.rt = ResultCode.ALLIANCE_QUIT_FORBIDDEN.code
            return rt
        }

        val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (allianceMember == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        resetPlayerAllianceInfo(publicCache, alce, allianceMember, req.isRook)

        // 退出联盟日志
        insertLog(publicCache, alce.id, com.point18.slg2d.common.constg.A_LOG_LEAVE_ALLIANCE, allianceMember.name, allianceMember.allianceNickName)

        // 联盟成员离开推送
        val allianceMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
        val nowPos = LinkedList<Int>()
        val pltAreas = mutableMapOf<Long, Int>()

        for (am in allianceMembers) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            allianceMemberInfoChange(
                    publicCache.publicActor, pltAreaId, allianceId, ALLIANCE_MEMBER_FLAG_QUIT, playerId,
                    allianceMember.name, nowPos, allianceMember.onlineState, allianceMember.photoProtoId
            )
        }

        return rt
    }

    fun newAllianceMemberQuitAskRtBuilder(): AllianceMemberQuitAskRt.Builder {
        val rt = AllianceMemberQuitAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}