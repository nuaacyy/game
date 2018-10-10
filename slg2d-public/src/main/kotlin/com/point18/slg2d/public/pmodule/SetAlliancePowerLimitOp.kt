package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.SetAlliancePowerLimitAskReq
import pb4server.SetAlliancePowerLimitAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class SetAlliancePowerLimitOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.setAlliancePowerLimitAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setSetAlliancePowerLimitAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: SetAlliancePowerLimitAskReq
    ): SetAlliancePowerLimitAskRt.Builder {
        val rt = newSetAlliancePowerLimitAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)

        if(allianceMember == null){
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        if (!hasRight(allianceMember, com.point18.slg2d.common.constg.ALLIANCE_POWER_FIGHTVALUE)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        alce.powerLimit = req.power
        alce.canAddPower = req.canAddPower

        // 插入日志
        insertLog(publicCache, allianceMember.allianceId, com.point18.slg2d.common.constg.A_LOG_SET_POWER, allianceMember.name, allianceMember.allianceNickName)

        return rt
    }

    fun newSetAlliancePowerLimitAskRtBuilder(): SetAlliancePowerLimitAskRt.Builder {
        val rt = SetAlliancePowerLimitAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}