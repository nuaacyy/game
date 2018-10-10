package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ALLIANCE_NAME_TRY_USE
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.CacheAllianceManager
import com.point18.slg2d.public.datacache.PublicManagerCache
import pb4server.Home2PublicManagerAskReq
import pb4server.Home2PublicManagerAskResp
import pb4server.SetAllianceNameAskReq
import pb4server.SetAllianceNameAskRt

class SetAllianceNameCheckOp : Home2PublicManagerAskDealBase() {
    override fun dealHomeAsk(
        publicCache: PublicManagerCache,
        req: Home2PublicManagerAskReq,
        resp: Home2PublicManagerAskResp.Builder
    ) {
        val internalMsg = req.setAllianceNameAskReq
        val rt = dealMsg(publicCache, internalMsg)
        // 设置结果
        resp.setSetAllianceNameAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicManagerCache,
        req: SetAllianceNameAskReq
    ): SetAllianceNameAskRt.Builder {
        val rt = newSetAllianceNameRt()

        if (req.setType == com.point18.slg2d.common.constg.SET_ALLIANCE_NAME) {
            if (publicCache.allianceCache.nowUseNameMap[req.name] != null) {
                // 该名字已存在
                rt.rt = ResultCode.ALLIANCE_NAME_ALREADY_EXISTED.code
                return rt
            } else {
                // 名字验证通过,设立标记
                publicCache.allianceCache.nowUseNameMap[req.name] = CacheAllianceManager.NameUseVo(
                    ALLIANCE_NAME_TRY_USE,
                    getNowTime() + 600000
                )
            }
        } else if (req.setType == com.point18.slg2d.common.constg.SET_ALLIANCE_SHORT_NAME) {
            if (publicCache.allianceCache.nowUseShortNameMap[req.name] != null) {
                // 该名字已存在
                rt.rt = ResultCode.ALLIANCE_SHORT_NAME_ALREADY_EXIST.code
                return rt
            } else {
                publicCache.allianceCache.nowUseShortNameMap[req.name] =
                        CacheAllianceManager.NameUseVo(ALLIANCE_NAME_TRY_USE, getNowTime() + 600000)
            }
        }


        return rt
    }

    fun newSetAllianceNameRt(): SetAllianceNameAskRt.Builder {
        val rt = SetAllianceNameAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}