package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.W2HAsk
import pb4server.PlunderAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp
import java.util.*
import java.util.Arrays.asList

class PlunderDeal(
    private val refreshResHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : W2HAsk,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        asList(refreshResHelper, resHelper, effectHelper)
    ) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val msg = req.plunderAskReq
        val totalWeight = msg.totalWeight

        prepare(session) { homePlayerDC ->
            refreshResHelper.refreshResource(session, AllResYield)

            val protectRes = 1L * effectHelper.getResearchEffectValue(
                session,
                NO_FILTER,
                ResProtectWithOutCoinAdd
            ) * (10000 + effectHelper.getResearchEffectValue(
                session,
                NO_FILTER,
                ResProtectAdd
            )) / 10000
            val protectGold =
                1L * effectHelper.getResearchEffectValue(
                    session,
                    NO_FILTER,
                    CoinProtectAdd
                ) * (10000 + effectHelper.getResearchEffectValue(
                    session,
                    NO_FILTER,
                    ResProtectAdd
                )) / 10000

            val player = homePlayerDC.player
            val canPlunderCoin = player.coin - protectGold
            val canPlunderFood = player.food - protectRes
            val canPlunderWood = player.wood - protectRes
            val canPlunderStone = player.stone - protectRes
            val canPlunderIron = player.iron - protectRes

            val plunderCanGetMap = hashMapOf<Int, Double>()
            if (canPlunderCoin > 0) {
                plunderCanGetMap[RES_COIN] = canPlunderCoin.toDouble()
            }
            if (canPlunderFood > 0) {
                plunderCanGetMap[RES_FOOD] = canPlunderFood.toDouble()
            }
            if (canPlunderWood > 0) {
                plunderCanGetMap[RES_WOOD] = canPlunderWood.toDouble()
            }
            if (canPlunderStone > 0) {
                plunderCanGetMap[RES_STONE] = canPlunderStone.toDouble()
            }
            if (canPlunderIron > 0) {
                plunderCanGetMap[RES_IRON] = canPlunderIron.toDouble()
            }

            val plunderMap = this.calPlunder(plunderCanGetMap, totalWeight)
            val plunderRes = LinkedList<ResVo>()
            for ((resType, num) in plunderMap) {
                plunderRes += ResVo(resType, NOT_PROPS_SUB_TYPE, num)
            }
            resHelper.costRes(session, ACTION_FIGHT_CASTLE, player, plunderRes)

            val rt = PlunderAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            rt.loseRes = toJson(plunderMap)
            resp.setPlunderAskRt(rt)
        }
    }

    private fun calPlunder(canPlunderMap: HashMap<Int, Double>, totalWeight: Long): HashMap<Int, Long> {
        val tempCanPlunderMap = hashMapOf<Int, Double>()
        for ((resType, num) in canPlunderMap) {
            tempCanPlunderMap[resType] = num
        }
        var index = 0
        var tempTotalWeight = totalWeight.toDouble()
        while (true) {
            if (tempTotalWeight <= 0) {
                break
            }
            var toAvgNum = 0
            for ((_, num) in tempCanPlunderMap) {
                if (num > 0) {
                    toAvgNum++
                }
            }
            if (toAvgNum == 0) {
                break
            }
            val avgWeight = tempTotalWeight / toAvgNum
            tempTotalWeight = 0.0
            for ((resType, num) in tempCanPlunderMap) {
                if (num == 0.0) {
                    continue
                }
                val resWeight = pcs.basicProtoCache.resWeight[resType] ?: 0.0
                if (resWeight * num > avgWeight) {
                    //负重不足以完全掠夺
                    tempCanPlunderMap[resType] = num - avgWeight / resWeight
                } else {
                    //负重多余
                    tempTotalWeight += avgWeight - resWeight * num
                    tempCanPlunderMap[resType] = 0.0
                }
            }
            index++
            if (index > 100000) {
                normalLog.error("掠夺计算触发了保险丝！")
                break
            }
        }
        val finalPlunderMap = hashMapOf<Int, Long>()
        for ((resType, num) in canPlunderMap) {
            val leftNum = tempCanPlunderMap[resType] ?: 0.0
            if (num == leftNum) {
                continue
            }
            finalPlunderMap[resType] = (num - leftNum).toLong()
        }
        return finalPlunderMap
    }
}