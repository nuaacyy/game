package com.point18.slg2d.home.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.ONE_DAY_MILLS
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.getJjcShopRefreshTime
import com.point18.slg2d.home.common.jjcShopRefresh
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.JjcHomeDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GetJjcShopInfoRt
import pb4client.ShopItemInfo
import java.util.*

class QueryJjcShopInfoDeal : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, JjcHomeDC>(
    HomePlayerDC::class.java, JjcHomeDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC ->
            val queryRt = queryJjcShop(session, jjcHomeDC, homePlayerDC)
            session.sendMsg(MsgType.QueryJjcShopInfo_726, queryRt.build())
        }
    }

    private fun queryJjcShop(
        session: PlayerActor, jjcHomeDC: JjcHomeDC, homePlayerDC: HomePlayerDC
    ): GetJjcShopInfoRt.Builder {
        val queryRt = GetJjcShopInfoRt.newBuilder()
        queryRt.rt = ResultCode.SUCCESS.code
        queryRt.times = 0
        queryRt.refreshTime = 0
        val itemsInfoList = LinkedList<ShopItemInfo>()
        queryRt.addAllItems(LinkedList())

        val jjcHome = jjcHomeDC.jjcHome
        val homePlayer = homePlayerDC.player

        val lastRefreshTime = getJjcShopRefreshTime(jjcHome.refreshShopTime)
        val nowRefreshTime = getJjcShopRefreshTime(getNowTime())
        if (nowRefreshTime > lastRefreshTime) {
            val afterRefresh = jjcShopRefresh(jjcHome, session.playerId, homePlayer.kingLv)
            if (afterRefresh == null) {
                queryRt.rt = ResultCode.NO_XML_PROTO.code
                return queryRt
            }
            jjcHome.refreshShopTime = getNowTime()
            jjcHome.arenaRefreshShopTimes = 0
        }

        for (eachItem in jjcHome.itemsInfo) {
            val itemInfo = ShopItemInfo.newBuilder()
            itemInfo.id = eachItem.gridId       // 槽位id
            itemInfo.protoId = eachItem.protoId // 模板id
            if (com.point18.slg2d.common.pc.pcs.arenaShopProtoCache.mapOfItems[itemInfo.protoId] == null) {
                queryRt.rt = ResultCode.NO_XML_PROTO.code
                return queryRt
            }
            itemInfo.haveBought = eachItem.haveBought // 是否已经购买
            itemsInfoList.add(itemInfo.build())
        }
        queryRt.addAllItems(itemsInfoList)
        queryRt.refreshTime = (nowRefreshTime + ONE_DAY_MILLS) / 1000   // 下次的刷新时间
        queryRt.times = jjcHome.arenaRefreshShopTimes           // 这个周期已经有的刷新
        return queryRt
    }

}