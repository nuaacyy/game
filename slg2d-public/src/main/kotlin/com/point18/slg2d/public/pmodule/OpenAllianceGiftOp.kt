package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class OpenAllianceGiftOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.openAllianceGiftAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = openAllianceGift(publicCache, allianceId, playerId, internalMessage)

        resp.setOpenAllianceGiftAskRt(rt)
    }

    fun openAllianceGift(
        publicCache: PublicCache, allianceId: Long,
        playerId: Long,
        req: OpenAllianceGiftAskReq
    ): OpenAllianceGiftAskRt.Builder {

        val rt = newOpenAllianceGiftAskRtBuilder()

        val allianceGiftInfo = publicCache.allianceGiftCache.findAllianceGiftById(allianceId)
        if (allianceGiftInfo != null) {
            val abgv = AllianceBigGiftVo.newBuilder()
            abgv.bigGiftId = allianceGiftInfo.bigGiftId
            abgv.bigGiftExp = allianceGiftInfo.bigGiftExp
            abgv.giftLv = allianceGiftInfo.giftLv
            abgv.giftExp = allianceGiftInfo.giftExp
            rt.setAllianceBigGiftVo(abgv)
        }

        return rt
    }

    fun newOpenAllianceGiftAskRtBuilder(): OpenAllianceGiftAskRt.Builder {
        val rt = OpenAllianceGiftAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}