package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.AllianceRecallPosAskReq
import pb4server.AllianceRecallPosAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.public.common.getNewAlliancePos
import com.point18.slg2d.public.common.posChangeNoticAllAlliance
import com.point18.slg2d.common.constg.REMOVE_POS
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class AllianceRecallPosOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
            publicCache: PublicCache,
            req: World2PublicAskReq,
            resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.allianceRecallPosAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setAllianceRecallPosAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: AllianceRecallPosAskReq
    ): AllianceRecallPosAskRt.Builder {
        val rt = newAllianceRecallPosAskRtBuilder()

        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        val setPlayer = publicCache.allianceMemberCache.findAllianceMemberById(req.setPid)
        if (setPlayer == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        val pos = req.posId
        val ex = setPlayer.alliancePosMap[pos]
        if (ex == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        //是否是相同联盟
        if (player.allianceId != setPlayer.allianceId) {
            rt.rt = ResultCode.ALLIANCE_SET_POSITION_FORBIDDEN.code
            return rt
        }

        val oldPos = LinkedList<Int>()
        for ((p, _) in setPlayer.alliancePosMap) {
            oldPos.add(p)
        }

        //验证玩家的职位是否拥有该模块操作权限
        if (!hasRight(player, com.point18.slg2d.common.constg.A_RIGHT_SET_POS)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        // 职位罢免日志
        setPlayer.delWrapPosition(pos)

        val nowPos = LinkedList<Int>()
        for ((p, _) in setPlayer.alliancePosMap) {
            nowPos.add(p)
        }

        // 通知这个玩家被设置了新职位
        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        getNewAlliancePos(
                publicCache.publicActor,
                setPlayer.mapPltAreaId, req.setPid, allianceId, toJson(setPlayer.alliancePosMap),
                alce.name, alce.shortName
        )

        val allAllianceMember =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)

        val pltAreas = mutableMapOf<Long, Int>()

        for (am in allAllianceMember) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            // 邮件通知帮里其他人
            posChangeNoticAllAlliance(
                    publicCache.publicActor, pltAreaId, setPlayer.allianceId, pos, player.name, setPlayer.name,
                    REMOVE_POS, nowPos,
                    req.setPid, setPlayer.onlineState, setPlayer.photoProtoId
            )
        }

        return rt
    }

    fun newAllianceRecallPosAskRtBuilder(): AllianceRecallPosAskRt.Builder {
        val rt = AllianceRecallPosAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}