package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ALLIANCE_NAME_TRY_USE
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.CacheAllianceManager
import com.point18.slg2d.public.datacache.PublicManagerCache
import pb4server.CreateAllianceAskReq
import pb4server.CreateAllianceAskRt
import pb4server.World2PublicManagerAskReq
import pb4server.World2PublicManagerAskResp

class CheckAllianceNameOp : World2PublicManagerAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicManagerCache,
        req: World2PublicManagerAskReq,
        resp: World2PublicManagerAskResp.Builder
    ) {
        val internalMsg = req.createAllianceAskReq
        val playerId = req.playerId
        val rt = dealMsg(publicCache, internalMsg)
        // 设置结果
        resp.setCreateAllianceAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicManagerCache,
        req: CreateAllianceAskReq
    ): CreateAllianceAskRt.Builder {
        val rt = newCreateAllianceAskRtBuilder()

        if (publicCache.allianceCache.nowUseNameMap[req.name] != null) {
            // 该名字已存在
            rt.rt = ResultCode.ALLIANCE_NAME_ALREADY_EXISTED.code
            return rt
        }

        if (publicCache.allianceCache.nowUseShortNameMap[req.shortName] != null) {
            // 该名字已存在
            rt.rt = ResultCode.ALLIANCE_SHORT_NAME_ALREADY_EXIST.code
            return rt
        }

        // 名字验证通过,设立标记
        publicCache.allianceCache.nowUseNameMap[req.name] = CacheAllianceManager.NameUseVo(
            ALLIANCE_NAME_TRY_USE,
            getNowTime() + 600000
        )
        publicCache.allianceCache.nowUseShortNameMap[req.shortName] =
                CacheAllianceManager.NameUseVo(ALLIANCE_NAME_TRY_USE, getNowTime() + 600000)

        return rt
    }

    fun newCreateAllianceAskRtBuilder(): CreateAllianceAskRt.Builder {
        val rt = CreateAllianceAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}