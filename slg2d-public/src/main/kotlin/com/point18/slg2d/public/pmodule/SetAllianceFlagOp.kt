package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.ADD_ALLIANCE_INFO
import com.point18.slg2d.common.constg.A_LOG_SET_FLAG
import com.point18.slg2d.common.constg.A_RIGHT_FLAG
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.public.common.dealAfterSetAllianceFlag
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.ppm
import pb4server.*

class SetAllianceFlagOp : Home2PublicAskDealBase() {

    override fun dealHomeAsk(
        publicCache: PublicCache,
        req: Home2PublicAskReq,
        resp: Home2PublicAskResp.Builder
    ) {
        val internalMsg = req.setAllianceFlagAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = setAllianceFlag(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setAllianceFlagAskRt = rt.build()
    }

    private fun setAllianceFlag(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: SetAllianceFlagAskReq
    ): SetAllianceFlagAskRt.Builder {

        val rt = SetAllianceFlagAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        // 修改旗帜权限验证
        if (!hasRight(player, A_RIGHT_FLAG)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        // 判断参数有效性
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_ARGS_ERROR.code
            return rt
        }

        // 旗帜有没有发生改变
        val color = req.color
        val style = req.style
        val effect = req.effect
        if (alce.flagColor == color && alce.flagStyle == style && alce.flagEffect == effect) {
            rt.rt = ResultCode.ALLIANCE_FLAG_NOT_SET.code
            return rt
        }

        // 数据保存
        alce.flagColor = color
        alce.flagStyle = style
        alce.flagEffect = effect

        // 修改联盟旗帜的联盟日志
        insertLog(publicCache, player.allianceId, A_LOG_SET_FLAG, player.name, player.allianceNickName)

        val aPlayers = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(alce.id)

        // 向其他联盟成员推送联盟旗帜变化
        val pltAreas = mutableMapOf<Long, Int>()

        for (am in aPlayers) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            dealAfterSetAllianceFlag(publicCache.publicActor, pltAreaId, alce.id, req.color, req.style, req.effect)
        }

        // 推送到公共服
        syncAllianceInfo2AM(
            publicCache,
            alce,
            0,
            0,
            0
        )

        return rt
    }
}