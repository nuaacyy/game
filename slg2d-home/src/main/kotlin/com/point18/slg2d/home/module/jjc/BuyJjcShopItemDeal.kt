package com.point18.slg2d.home.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_BUY_IN_JJC_SHOP
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.JjcHomeDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BuyJjcItem
import pb4client.BuyJjcItemRt
import java.util.*
import java.util.Arrays.asList

class BuyJjcShopItemDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, JjcHomeDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, JjcHomeDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC, homeMyTargetDC: HomeMyTargetDC ->
            val receiveMsg = msg as BuyJjcItem
            val gridId = receiveMsg.id
            val rt = dealBuyJjcShopItem(session, gridId, homePlayerDC, jjcHomeDC, homeMyTargetDC)
            session.sendMsg(MsgType.BuyJjcShopItem_727, rt.build())
        }
    }

    private fun dealBuyJjcShopItem(
        session: PlayerActor, gridId: Int, homePlayerDC: HomePlayerDC,
        jjcHomeDC: JjcHomeDC, homeMyTargetDC: HomeMyTargetDC
    ): BuyJjcItemRt.Builder {
        val rt = BuyJjcItemRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val jjcHome = jjcHomeDC.jjcHome
        val itemsInfo = jjcHome.itemsInfo
        val item = itemsInfo.filter { it.gridId == gridId }
        if (item.size != 1) {
            rt.rt = ResultCode.JJC_SHOP_ITEM_NO_EXIST.code
            return rt
        }

        val itemInfo = item[0]
        val protoId = itemInfo.protoId
        val haveBought = itemInfo.haveBought

        if (haveBought == 1) {
            rt.rt = ResultCode.JJC_SHOP_BUY_NOW_FORBIDDEN.code
            return rt
        }

        val proto = pcs.arenaShopProtoCache.mapOfItems[protoId]
        if (proto == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val price = proto.priceVo
        val reward = proto.itemVo
        val cost = LinkedList(asList(price))
        val rewardGet = LinkedList(asList(reward))

        val player = homePlayerDC.player

        if (!resHelper.checkRes(session, cost)) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt
        }

        resHelper.costRes(session, ACTION_BUY_IN_JJC_SHOP, player, cost)
        val addRtSuccess = resHelper.addRes(session, ACTION_BUY_IN_JJC_SHOP, player, rewardGet)
        if (!addRtSuccess) {
            rt.rt = ResultCode.RES_ERROR.code
            return rt
        }
        itemInfo.haveBought = 1

        homeMyTargetDC.targetInfo.jjcShopBuyNum++

        rt.rt = ResultCode.SUCCESS.code
        return rt

    }
}