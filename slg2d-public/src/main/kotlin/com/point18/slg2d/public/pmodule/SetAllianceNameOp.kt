package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.public.common.dealAfterSetAllianceName
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.ppm
import pb4server.*

class SetAllianceNameOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.setAllianceNameAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setSetAllianceNameAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: SetAllianceNameAskReq
    ): SetAllianceNameAskRt.Builder {
        val rt = newSetAllianceNameAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
            return rt
        }

        val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (allianceMember == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        // 找到需要推送名称变化的成员
        val aPlayers = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(alce.id)
        val pltAreas = mutableMapOf<Long, Int>()
        for (am in aPlayers) {
            pltAreas[am.mapPltAreaId] = 1
        }

        when {
            req.setType == SET_ALLIANCE_NAME -> {
                if (!hasRight(allianceMember, ALLIANCE_POWER_NAME)) {
                    rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
                    return rt
                }
                val oldName = alce.name

                publicCache.allianceCache.updateAllianceName(oldName, req.name)

                insertLog(
                    publicCache,
                    alce.id,
                    A_LOG_SET_ALLIANCE_NAME,
                    allianceMember.name,
                    allianceMember.allianceNickName
                )

                for ((pltAreaId, _) in pltAreas) {
                    dealAfterSetAllianceName(publicCache.publicActor, pltAreaId, alce.id, req.setType, req.name)
                }

                // 推送到公共服
                val unLockTell = UnLockAllianceNameTell.newBuilder()
                unLockTell.changeType = ALLIANCE_NAME_TRY2USE
                unLockTell.allianceName = req.name
                unLockTell.allianceShortName = ""

                val p2pmTell =
                    ppm.fillPublic2PublicManagerTellMsgHeader { it.unLockAllianceNameTell = unLockTell.build() }
                ppm.tell2PublicManager(p2pmTell)

                val delTell = UnLockAllianceNameTell.newBuilder()
                delTell.changeType = ALLIANCE_NAME_DEL_USE
                delTell.allianceName = oldName
                delTell.allianceShortName = ""

                val p2pdelTell =
                    ppm.fillPublic2PublicManagerTellMsgHeader { it.unLockAllianceNameTell = delTell.build() }
                ppm.tell2PublicManager(p2pdelTell)

                // 推送到公共服
                syncAllianceInfo2AM(
                    publicCache,
                    alce,
                    0,
                    0,
                    0
                )
            }
            req.setType == SET_ALLIANCE_SHORT_NAME -> {
                if (!hasRight(allianceMember, ALLIANCE_POWER_NICKNAME)) {
                    rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
                    return rt
                }
                val oldName = alce.shortName

                publicCache.allianceCache.updateAllianceShortName(oldName, req.name)

                insertLog(
                    publicCache,
                    alce.id,
                    A_LOG_SET_ALLIANCE_SHORT_NAME,
                    allianceMember.name,
                    allianceMember.allianceNickName
                )

                for ((pltAreaId, _) in pltAreas) {
                    dealAfterSetAllianceName(publicCache.publicActor, pltAreaId, alce.id, req.setType, req.name)
                }

                // 推送到公共服
                val unLockTell = UnLockAllianceNameTell.newBuilder()
                unLockTell.changeType = ALLIANCE_NAME_TRY2USE
                unLockTell.allianceName = ""
                unLockTell.allianceShortName = req.name

                val p2pmTell =
                    ppm.fillPublic2PublicManagerTellMsgHeader { it.unLockAllianceNameTell = unLockTell.build() }
                ppm.tell2PublicManager(p2pmTell)

                val delTell = UnLockAllianceNameTell.newBuilder()
                delTell.changeType = ALLIANCE_NAME_DEL_USE
                delTell.allianceName = ""
                delTell.allianceShortName = oldName

                val p2pdelTell =
                    ppm.fillPublic2PublicManagerTellMsgHeader { it.unLockAllianceNameTell = delTell.build() }
                ppm.tell2PublicManager(p2pdelTell)

                // 推送到公共服
                syncAllianceInfo2AM(
                    publicCache,
                    alce,
                    0,
                    0,
                    0
                )
            }
            req.setType == SET_ALLIANCE_LAN -> {
                // 修改语种
                if (!hasRight(allianceMember, ALLIANCE_POWER_LANGUAGE)) {
                    rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
                    return rt
                }

                val newLan = req.name.toIntOrNull()
                if (newLan == null) {
                    rt.rt = ResultCode.ALLIANCE_ARGS_ERROR.code
                    return rt
                }

                alce.allianceLan = newLan
                insertLog(
                    publicCache,
                    allianceMember.allianceId,
                    A_LOG_SET_ALLIANCE_LAN,
                    allianceMember.name,
                    allianceMember.allianceNickName
                )
            }
            else -> {
                rt.rt = ResultCode.ALLIANCE_ARGS_ERROR.code
                return rt
            }
        }

        return rt
    }

    fun newSetAllianceNameAskRtBuilder(): SetAllianceNameAskRt.Builder {
        val rt = SetAllianceNameAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}