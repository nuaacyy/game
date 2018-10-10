package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.ALLIANCE_POWER_NICKNAME
import com.point18.slg2d.common.constg.ALLIANCE_REMOVE_ALLIANCE_WAIJIAO
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.RemoveAllianceWaijiaoReq
import pb4server.RemoveAllianceWaijiaoRt

class RemoveAllianceWaijiaoOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(
        publicCache: PublicCache,
        req: Home2PublicAskReq,
        resp: Home2PublicAskResp.Builder
    ) {
        val internalMsg = req.removeAllianceWaijiaoReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setRemoveAllianceWaijiaoRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: RemoveAllianceWaijiaoReq
    ): RemoveAllianceWaijiaoRt.Builder {
        val rt = newRemoveAllianceWaijiaoRtBuilder()

        // 验证权限
        val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (allianceMember == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        if (!hasRight(allianceMember, ALLIANCE_REMOVE_ALLIANCE_WAIJIAO)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        val removeOverIds = mutableListOf<Long>() // 通过验证会被删除掉的IDS
        for (id in req.removeIdsList) {
            val delWaijiao = publicCache.allianceWaijiaoCache.findAllianceWaijiaoByid(id)
            if (delWaijiao != null) {
                removeOverIds.add(id)
                publicCache.allianceWaijiaoCache.deleteAllianceWaijiaoById(delWaijiao)
            }
        }

        rt.addAllRemoveIds(removeOverIds)
        return rt
    }

    fun newRemoveAllianceWaijiaoRtBuilder(): RemoveAllianceWaijiaoRt.Builder {
        val rt = RemoveAllianceWaijiaoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}