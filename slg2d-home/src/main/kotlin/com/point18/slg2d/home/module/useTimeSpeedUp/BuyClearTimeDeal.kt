package com.point18.slg2d.home.module.useTimeSpeedUp

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_BUY_RESSHOP
import com.point18.slg2d.common.constg.NOT_PROPS_SUB_TYPE
import com.point18.slg2d.common.constg.RES_BIND_GOLD
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BuyResShop
import pb4client.BuyShopRt
import java.util.*
import java.util.Arrays.asList

// 购买加速物品
class BuyClearTimeDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val reqMsg = msg as BuyResShop
        val buyId = reqMsg.buyId
        val buyNum = reqMsg.buyNum
        buyShop(session, buyId, buyNum)
    }

    private fun buyShop(session: PlayerActor, buyId: Int, buyNum: Int) {
        prepare(session) { homePlayerDC ->
            val rtBuilder = BuyShopRt.newBuilder()
            rtBuilder.rt = ResultCode.SUCCESS.code
            rtBuilder.shopAddress = 0

            val player = homePlayerDC.player

            // 资源检测
            val shop = pcs.resShopCache.resShopProtoMap[buyId]
            if (shop == null) {
                rtBuilder.rt = ResultCode.NO_PROTO.code
                session.sendMsg(MsgType.BuyResShop_1062, rtBuilder.build())
                return@prepare
            }

            //扣减玩家资源
            val costs = ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, (shop.price * buyNum).toLong())

            //检测资源
            val checkCost = resHelper.checkRes(session, costs)
            if (!checkCost) {
                rtBuilder.rt = ResultCode.ALLIANCE_RESOURCE_NOT_ENOUGH.code
                session.sendMsg(MsgType.BuyResShop_1062, rtBuilder.build())
                return@prepare
            }

            val action = ACTION_BUY_RESSHOP

            //扣除资源
            resHelper.costRes(session, action, player, costs)

            // 发送奖励
            val adds = LinkedList(asList(ResVo(RES_PROPS, shop.goodsId.toLong(), buyNum.toLong())))
            resHelper.addRes(session, action, player, adds)

            session.sendMsg(MsgType.BuyResShop_1062, rtBuilder.build())
            return@prepare
        }
    }
}



