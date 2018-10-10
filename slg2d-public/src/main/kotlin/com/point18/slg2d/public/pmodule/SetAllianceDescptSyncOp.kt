package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.SetAllianceDescptAskReq
import pb4server.SetAllianceDescptAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class SetAllianceDescptSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
            publicCache: PublicCache,
            req: World2PublicAskReq,
            resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.setAllianceDescptAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setSetAllianceDescptAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: SetAllianceDescptAskReq
    ): SetAllianceDescptAskRt.Builder {
        val rt = newSetAllianceDescptAskRtBuilder()

        val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (allianceMember == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        val alliance = publicCache.allianceCache.findAllianceById(allianceMember.allianceId)

        if (allianceMember.allianceId == 0L || alliance == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        if (req.despType == 1) {
            // 公告
            // 验证玩家的职位是否拥有该模块操作权限
            if (!hasRight(allianceMember, com.point18.slg2d.common.constg.A_RIGHT_SET_DESCRIPTION)) {
                rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
                return rt
            }

            //修改公告
            alliance.description = req.desp

            //修改联盟公告日志
            insertLog(publicCache, alliance.id, com.point18.slg2d.common.constg.A_LOG_MODIFY_NOTICE, allianceMember.name, allianceMember.allianceNickName)

        } else {
            // 标语
            if (!hasRight(allianceMember, com.point18.slg2d.common.constg.ALLIANCE_POWER_SLOGAN)) {
                rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
                return rt
            }

            //修改公告
            alliance.slogan = req.desp

            //修改联盟公告日志
            insertLog(publicCache, alliance.id, com.point18.slg2d.common.constg.A_LOG_SET_BIAOYU, allianceMember.name, allianceMember.allianceNickName)

        }

        return rt
    }

    fun newSetAllianceDescptAskRtBuilder(): SetAllianceDescptAskRt.Builder {
        val rt = SetAllianceDescptAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}