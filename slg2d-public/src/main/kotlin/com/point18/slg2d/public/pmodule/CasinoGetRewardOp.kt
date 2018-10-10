package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.BASE
import pb4server.DrawCasinoAskReq
import pb4server.DrawCasinoAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class CasinoGetRewardOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.drawCasinoAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setDrawCasinoAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: DrawCasinoAskReq
    ): DrawCasinoAskRt.Builder {
        val rate = req.rate
        val areaNo = req.areaNo
        val playerName = req.playerName
        val myPlayerId = req.myPlayerId
        val allianceShortName = req.allianceShortName
        val rt = newDrawCasinoAskRtBuilder()

        // 查询总金额
        val casinosMoney = publicCache.jackpotCache.findJackpot()
        val nowTime = getNowTime()
        val money = com.point18.slg2d.common.pc.pcs.basicProtoCache.palacePoolBase

        // 奖池是否有数据
        if (casinosMoney == null) {
            rt.totalMoney = money
            publicCache.jackpotCache.createJackpot(money, nowTime, 0)
        } else {
            val tempMoney = casinosMoney.totalMoney
            val getMoney = tempMoney * rate / BASE
            rt.totalMoney = getMoney
            val remainMoney = tempMoney - getMoney
            if (remainMoney <= money) {
                casinosMoney.totalMoney = money
            } else {
                casinosMoney.totalMoney = remainMoney
            }
        }

        // 查询中奖的人
        publicCache.casinosWinnersCache.createCasinosWinner(
            areaNo,
            playerName,
            allianceShortName,
            nowTime,
            rt.totalMoney,
            myPlayerId
        )

        return rt
    }

    fun newDrawCasinoAskRtBuilder(): DrawCasinoAskRt.Builder {
        val rt = DrawCasinoAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}