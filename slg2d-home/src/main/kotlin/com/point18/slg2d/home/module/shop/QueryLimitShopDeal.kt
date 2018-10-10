package com.point18.slg2d.home.module.shop

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BuyLimitShopTotalRt
import pb4client.LimitGoodsVo
import java.text.SimpleDateFormat
import java.util.*

// 挑战商店
class QueryLimitShopDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
            // 数据返回定义
            val queryGoodsRt = queryGoods(homePlayerDC)
            // 发送数据
            session.sendMsg(MsgType.ShopLimitTotalBuy_1312, queryGoodsRt)
        }
    }

    fun queryGoods(homePlayerDC: HomePlayerDC): (BuyLimitShopTotalRt) {
        val rt = BuyLimitShopTotalRt.newBuilder()
        // 物品返回值格式
        val limitGoodsBuilder = LimitGoodsVo.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player

        // 玩家数据库存储限时商品
        val buyLimitCache = player.buyLimitInfo
        if (buyLimitCache.isEmpty()) {
            return rt.build()
        }

        // 数值表限时商品
        val shopLimitProto = pcs.shopTotalProtoCache.shopTotalProtoMap
        if (shopLimitProto.isEmpty()) {
            buyLimitCache.clear()
            return rt.build()
        }

        //时间格式化
        val df = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
        val nowTime = Date()

        // 判断玩家存的商品是否在数值表中，不在：移除
        val deleteList = LinkedList<Int>()
        for ((protoId, _) in buyLimitCache) {
            if (!shopLimitProto.containsKey(protoId)) {
                deleteList.add(protoId)
            }
        }
        if (deleteList.size > 0) {
            for (protoId in deleteList) {
                buyLimitCache.remove(protoId)
            }
        }

        // 判断数值表中商品是否在数据库中，存在并且不在时间范围内：移除
        for ((protoId, protoTemp) in shopLimitProto) {
            if (buyLimitCache.containsKey(protoTemp.id)) {
                if (protoTemp.limitNumb == 0 || nowTime.before(df.parse(protoTemp.openTime)) || nowTime.after(
                    df.parse(
                        protoTemp.endTime
                    )
                )
                    ) {
                    buyLimitCache.remove(protoId)
                }
            }
        }

        // 返回值
        for ((protoId, num) in buyLimitCache) {
            limitGoodsBuilder.shopId = protoId
            limitGoodsBuilder.limitNumb = num
            rt.addLimitGoodsVos(limitGoodsBuilder)
        }

        player.buyLimitInfo = buyLimitCache
        return rt.build()
    }
}