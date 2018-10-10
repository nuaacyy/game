package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.common.commonfunc.getNowTime
import java.util.*
import pb4client.CasinosWinner
import pb4server.GetCasinoAskReq
import pb4server.GetCasinoAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class CasinoInfoOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.getCasinoAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setGetCasinoAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: GetCasinoAskReq
    ): GetCasinoAskRt.Builder {
        val rt = newGetCasinoAskRtBuilder()

        // 查询总金额
        val casinosMoney = publicCache.jackpotCache.findJackpot()
        val nowTime = getNowTime()
        var totalMoney: Long = 0
        val increment = com.point18.slg2d.common.pc.pcs.palaceCrystalProtoCache.palaceCrystalProtoMap
        val ran = Random()
        var money = 0
        val baseMoney = com.point18.slg2d.common.pc.pcs.basicProtoCache.palacePoolBase

        // 奖池是否有数据
        if (casinosMoney == null) {
            publicCache.jackpotCache.createJackpot(baseMoney, nowTime, 0)
        } else {
            // 增长区间 排序
            val keyList = LinkedList<Int>()
            for ((key, _) in increment) {
                keyList.add(key)
            }
            keyList.sortDescending()

            // 上一次请求与现在相差的时间/自增长钻石的时间间隔
            val difTime = nowTime - casinosMoney.lastTime
            val palaceIncrementTime = com.point18.slg2d.common.pc.pcs.basicProtoCache.palaceIncrementTime * 1000

            val timesTime = difTime / palaceIncrementTime

            // 修改上次增加钻石的时间
            if (timesTime > 0) {
                casinosMoney.lastTime = casinosMoney.lastTime + timesTime * palaceIncrementTime
            }

            // 间隔的倍数，加几次钻石
            for (i in 1..timesTime) {
                var key = -1
                for (num in keyList) {
                    if (num > casinosMoney.totalMoney) {
                        key = num
                    }
                }
                if (key != -1) {
                    val section = increment[key]
                    if (section != null) {
                        money = ran.nextInt(section[1] - section[0]) + section[0]
                        casinosMoney.totalMoney += money
                    }
                }
            }

            totalMoney = casinosMoney.totalMoney
        }

        rt.totalMoney = totalMoney

        // 查询中奖的人
        val casinoPlayers = publicCache.casinosWinnersCache.findCasinosWinner()

        if (casinoPlayers == null) {
            return rt
        }

        val keyTimeList = LinkedList<Long>()
        for (casinoPlayer in casinoPlayers) {
            keyTimeList.add(casinoPlayer.rewardTime)
        }
        keyTimeList.sortDescending()
        var index = 0

        for (key in keyTimeList) {
            for (casinoPlayer in casinoPlayers) {
                if (index < 4) {
                    if (key == casinoPlayer.rewardTime) {
                        val casinoBuilder = CasinosWinner.newBuilder()
                        casinoBuilder.playerId = casinoPlayer.playerId
                        casinoBuilder.playerName = casinoPlayer.playerName
                        casinoBuilder.areaNo = casinoPlayer.areaNo
                        casinoBuilder.allianceShortName = casinoPlayer.allianceShortName
                        casinoBuilder.giftNum = casinoPlayer.giftNum
                        casinoBuilder.date = (casinoPlayer.rewardTime / 1000).toInt()
                        rt.addCasinosWinner(casinoBuilder)
                        index++
                    }
                }
            }
        }

        return rt
    }

    fun newGetCasinoAskRtBuilder(): GetCasinoAskRt.Builder {
        val rt = GetCasinoAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}