package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.AllianceImpeachAskReq
import pb4server.AllianceImpeachAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import com.point18.slg2d.public.common.getNewAlliancePos
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class AllianceImpeachOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.allianceImpeachAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setAllianceImpeachAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: AllianceImpeachAskReq
    ): AllianceImpeachAskRt.Builder {
        val rt = newAllianceImpeachAskRtBuilder()

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

        var needHours = -1
        for ((p, _) in allianceMember.alliancePosMap) {
            val nh = com.point18.slg2d.common.pc.pcs.basicProtoCache.impeachmentTime[p]
            if (nh == null) {
                continue
            }
            needHours = nh
        }

        if (needHours == -1) {
            rt.rt = ResultCode.IM_ERROR_NO_FIND_TIME.code
            return rt
        }

        val mainAllianceMember =
            publicCache.allianceMemberCache.findAllianceMemberById(alce.mainPlayerId)
        if (mainAllianceMember == null) {
            rt.rt = ResultCode.ALLIANCE_ARGS_ERROR.code
            return rt
        }

//        val timeIsOk = checkChangeMainPlayer(publicCache, allianceMember, mainAllianceMember)
//        if (!timeIsOk) {
//            rt.rt = ResultCode.ALLIANCE_IMPEACH_TIME_ERROR.code
//            return rt
//        }

        if (mainAllianceMember.onlineState == 1) {
            rt.rt = ResultCode.IM_ERROR_TIME_NO_ENOUGH.code
            return rt
        }

        if (mainAllianceMember.lastLeaveTime == 0L) {
            rt.rt = ResultCode.IM_ERROR_TIME_NO_ENOUGH.code
            return rt
        }

        if (((getNowTime() - mainAllianceMember.lastLeaveTime) / 3600000) < needHours) {
            rt.rt = ResultCode.IM_ERROR_TIME_NO_ENOUGH.code
            return rt
        }

        val posProto =
            com.point18.slg2d.common.pc.pcs.posRightCache.posName[com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS]
        if (posProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        // 弹劾帮主
        // 盟主变成R2
        val mainPlayerOldPos = LinkedList<Int>()
        for ((p, _) in mainAllianceMember.alliancePosMap) {
            mainPlayerOldPos.add(p)
        }

        mainAllianceMember.delWrapPosition(com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS)
        // 安排到R2
        mainAllianceMember.setWrapPosition(com.point18.slg2d.common.constg.ALLIANCE_POSITION_ASSISTANT)

        // 盟主的帮帮主丢失了
        getNewAlliancePos(
            publicCache.publicActor,
            mainAllianceMember.mapPltAreaId, mainAllianceMember.id,
            allianceId, toJson(mainAllianceMember.alliancePosMap), alce.name, alce.shortName
        )

        // 弹劾者变成帮主
        // 验证职位的不可并存
        var deletePos = 0
        for ((p, _) in posProto.solePositionMap) {
            val ex = allianceMember.alliancePosMap[p]
            if (ex != null) {
                deletePos = p
                break
            }
        }

        if (deletePos != 0) {
            allianceMember.delWrapPosition(deletePos)
        }

        // 职位任命
        allianceMember.setWrapPosition(com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS)

        val nowPos = LinkedList<Int>()
        for ((p, _) in allianceMember.alliancePosMap) {
            nowPos.add(p)
        }

        // 通知这个玩家被设置了新职位
        getNewAlliancePos(
            publicCache.publicActor,
            allianceMember.mapPltAreaId, playerId, allianceId, toJson(allianceMember.alliancePosMap),
            alce.name, alce.shortName
        )

        // 联盟帮主变化
        alce.mainPlayerId = playerId

        insertLog(
            publicCache,
            allianceId,
            com.point18.slg2d.common.constg.A_LOG_IMPEACH,
            allianceMember.name, allianceMember.allianceNickName,
            mainAllianceMember.name, mainAllianceMember.allianceNickName
        )

        return rt
    }

    fun newAllianceImpeachAskRtBuilder(): AllianceImpeachAskRt.Builder {
        val rt = AllianceImpeachAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}