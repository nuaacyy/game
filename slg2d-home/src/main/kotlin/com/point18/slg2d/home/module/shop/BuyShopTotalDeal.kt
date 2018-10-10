package com.point18.slg2d.home.module.shop

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_SHOP_TOTAL_BUY
import com.point18.slg2d.common.constg.ALLIANCE_SHOP
import com.point18.slg2d.common.constg.DIAMOND_SHOP
import com.point18.slg2d.common.constg.LIMIT_SHOP
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoAddX
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.BuyShopTotalEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.BuyShopTotal
import pb4client.BuyShopTotalRt
import java.text.SimpleDateFormat
import java.util.*

// 购买商品(联盟币商店那个)
class BuyShopTotalDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, HomeMyTargetDC>(
        HomePlayerDC::class.java,
        HomeMyTargetDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, homeMyTargetDC ->
            val buyId = (msg as BuyShopTotal).id
            val buyNum = msg.num
            val buyShopRt = this.buyShop(session, buyId, buyNum, homePlayerDC, homeMyTargetDC)
            session.sendMsg(MsgType.ShopTotalBuy_1311, buyShopRt)
        }
    }

    private fun buyShop(
        session: PlayerActor,
        buyId: Int,
        buyNum: Int,
        homePlayerDC: HomePlayerDC,
        homeMyTargetDC: HomeMyTargetDC
    ): BuyShopTotalRt {
        val rt = pb4client.BuyShopTotalRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        if (buyNum <= 0) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        val action = ACTION_SHOP_TOTAL_BUY
        val player = homePlayerDC.player
        val shopTotalProto = pcs.shopTotalProtoCache.shopTotalProtoMap[buyId]

        if (null == shopTotalProto) {
            rt.rt = (ResultCode.NO_PROTO.code)
            return rt.build()
        }


        if (shopTotalProto.type == ALLIANCE_SHOP && player.allianceId == 0.toLong()) {
            rt.rt = (ResultCode.ALLIANCE_PLAYER_NO_JOIN.code)
            return rt.build()
        }

        val df = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
        val nowTime = Date()
        if (shopTotalProto.openTime != "0" && shopTotalProto.endTime != "0") {
            val openTime: Date = df.parse(shopTotalProto.openTime)
            val endTime: Date = df.parse(shopTotalProto.endTime)
            if (nowTime < openTime || nowTime > endTime) {
                //不在期间之内
                rt.rt = (ResultCode.OVER_TIME_SHOP.code)
                return rt.build()
            }
        }

        if (shopTotalProto.type == LIMIT_SHOP && shopTotalProto.limitNumb != 0) {
            val limitShop = player.buyLimitInfo
            if (limitShop.containsKey(shopTotalProto.id)) {
                for ((id, num) in limitShop) {
                    if (id == shopTotalProto.id && num - buyNum < 0) {
                        //限购数量上限
                        rt.rt = (ResultCode.LIMIT_SHOP.code)
                        return rt.build()
                    }
                }
            } else {
                if (shopTotalProto.limitNumb - buyNum < 0) {
                    //限购数量上限
                    rt.rt = (ResultCode.LIMIT_SHOP.code)
                    return rt.build()
                }
            }
        }

        // 重新计算需要总资源量
        val (ok, newCostRes) = resVoAddX(LinkedList(shopTotalProto.buyCostResVo), buyNum)

        if (!ok) {
            rt.rt = (ResultCode.BARRACK_GO_ALL_RES_ERROR.code)
            return rt.build()
        }

        val checkCost = resHelper.checkRes(session, newCostRes)

        if (!checkCost) {
            rt.rt = (ResultCode.LESS_RESOUCE.code)
            return rt.build()
        }

        resHelper.costRes(session, action, player, newCostRes)
        if (shopTotalProto.type == LIMIT_SHOP && shopTotalProto.limitNumb != 0) {
            val limitShop = player.buyLimitInfo
            if (limitShop.containsKey(shopTotalProto.id)) {
                for ((id, num) in limitShop) {
                    if (id == shopTotalProto.id) {
                        limitShop[id] = num - buyNum
                        break
                    }
                }
            } else {
                limitShop[shopTotalProto.id] = shopTotalProto.limitNumb - buyNum
            }

            player.buyLimitInfo = limitShop
        }

        // 获得物品
        // 重新计算需要总资源量
        val (ok2, newAddRes) = resVoAddX(LinkedList(shopTotalProto.propsResVo), buyNum)

        if (!ok2) {
            rt.rt = (ResultCode.BARRACK_GO_ALL_RES_ERROR.code)
            return rt.build()
        }

        resHelper.addRes(session, action, player, newAddRes)

        if (shopTotalProto.type == DIAMOND_SHOP) {
            homeMyTargetDC.targetInfo.diamondShopBuyNum++
        } else if (shopTotalProto.type == ALLIANCE_SHOP) {
            homeMyTargetDC.targetInfo.allianceShopBuyNum++
        } else if (shopTotalProto.type == LIMIT_SHOP) {
            homeMyTargetDC.targetInfo.activityShopBuyNum++
        }

        fireEvent(session, BuyShopTotalEvent(shopTotalProto.type))

        return rt.build()
    }
}
