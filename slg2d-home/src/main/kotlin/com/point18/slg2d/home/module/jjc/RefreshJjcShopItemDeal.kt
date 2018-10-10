package com.point18.slg2d.home.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_BUY_IN_JJC_SHOP
import com.point18.slg2d.common.constg.NOT_PROPS_SUB_TYPE
import com.point18.slg2d.common.constg.RES_GOLD
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.jjcShopRefresh
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.JjcHomeDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.JjcShopRefEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.RefreshJjcShopItemRt
import pb4client.ShopItemInfo
import java.util.*
import java.util.Arrays.asList

class RefreshJjcShopItemDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, JjcHomeDC>(
    HomePlayerDC::class.java, JjcHomeDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC ->
            val rt = dealRefreshJjcShopItem(session, homePlayerDC, jjcHomeDC)
            session.sendMsg(MsgType.RefreshJjcShopItem_728, rt.build())
        }

    }

    private fun dealRefreshJjcShopItem(
        session: PlayerActor, homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC
    ): RefreshJjcShopItemRt.Builder {
        val dealRt = RefreshJjcShopItemRt.newBuilder()
        dealRt.rt = ResultCode.SUCCESS.code
        dealRt.times = 0
        dealRt.addAllItems(LinkedList())

        val player = homePlayerDC.player
        val jjcHome = jjcHomeDC.jjcHome

        val times = jjcHome.arenaRefreshShopTimes
        val diamondConsume = pcs.diamondConsumeCache.diamondConsumeMap[times + 1]
        if (diamondConsume == null) {
            dealRt.rt = ResultCode.NO_XML_PROTO.code
            return dealRt
        }
        val arenaShopTimesConsume = diamondConsume.arenaShopTimes.toLong()
        val resVo = ResVo(RES_GOLD, NOT_PROPS_SUB_TYPE, arenaShopTimesConsume)

        if (!resHelper.checkRes(session, resVo)) {
            dealRt.rt = ResultCode.LESS_RESOUCE.code
            return dealRt
        }
        val afterRefresh = jjcShopRefresh(jjcHome, session.playerId, player.kingLv)
        if (afterRefresh == null) {
            dealRt.rt = ResultCode.NO_XML_PROTO.code
            return dealRt
        }

        resHelper.costRes(session, ACTION_BUY_IN_JJC_SHOP, player, resVo)
        jjcHome.arenaRefreshShopTimes += 1

        val itemsInfoList = LinkedList<ShopItemInfo>()
        for (eachItem in jjcHome.itemsInfo) {
            val itemInfo = ShopItemInfo.newBuilder()
            if (eachItem.gridId > pcs.basicProtoCache.arenaShopNum || eachItem.gridId < 0) {
                dealRt.rt = ResultCode.PARAMETER_ERROR.code
                return dealRt
            }
            itemInfo.id = eachItem.gridId // 槽位id
            itemInfo.protoId = eachItem.protoId // 模板id // todo 验证模板
            if (pcs.arenaShopProtoCache.mapOfItems[itemInfo.protoId] == null) {
                dealRt.rt = ResultCode.NO_PROTO.code
                return dealRt
            }
            itemInfo.haveBought = eachItem.haveBought // 是否已经购买
            itemsInfoList.add(itemInfo.build())
        }

        dealRt.rt = ResultCode.SUCCESS.code
        dealRt.addAllItems(itemsInfoList)
        dealRt.times = jjcHome.arenaRefreshShopTimes

        fireEvent(session, JjcShopRefEvent())
        return dealRt
    }
}