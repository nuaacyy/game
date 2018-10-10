package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.FurnitureProto
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.FurnitureDC
import com.point18.slg2d.home.dc.FurnitureInfo
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HouseThemeDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BuyHouseTheme
import pb4client.BuyHouseThemeRt
import pb4client.HouseThemeInfo
import java.util.*
import java.util.Arrays.asList

// 购买主题家具(家具套餐)
class BuyHouseThemeDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, FurnitureDC, HouseThemeDC>(
    HomePlayerDC::class.java, FurnitureDC::class.java, HouseThemeDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, furnitureDC: FurnitureDC, houseThemeDC: HouseThemeDC ->
            val reqMsg = msg as BuyHouseTheme
            val rtBuilder = handleBuyHouseTheme(
                session, reqMsg, furnitureDC, homePlayerDC, houseThemeDC
            )
            session.sendMsg(MsgType.BuyHouseTheme_1533, rtBuilder.build())
        }
    }

    private fun handleBuyHouseTheme(
        session: PlayerActor,
        msg: BuyHouseTheme,
        furnitureDC: FurnitureDC,
        homePlayerDC: HomePlayerDC,
        houseThemeDC: HouseThemeDC
    ): BuyHouseThemeRt.Builder {
        val rtBuilder = newBuyHouseThemeRtBuilder()

        val subjectId = msg.protoId  //主题模板Id

        val furnitureBag = furnitureDC.furnitureBag
        val nowFurniture = furnitureDC.nowFurniture
        val furnitureProtoMap = pcs.furnitureProtoCache.furnitureProtoMap

        // 获取当前玩家
        val player = homePlayerDC.player

        // 获取商店主题协议
        val subjectProto = pcs.furnitureSubjectProtoCache.subjectProtoMap[subjectId]
        if (subjectProto == null) {
            rtBuilder.rt = ResultCode.NO_SUBJECT_PROTO.code
            return rtBuilder
        }

        // 解析主题家具
        val protoBuyMap = mutableMapOf<FurnitureProto, Int>()
        val furnitureInfoList: LinkedList<FurnitureInfo> = LinkedList()
        subjectProto.subjectTemplate.forEach {
            val protoId = it.protoId
            val furnitureProto = furnitureProtoMap[protoId]
            if (furnitureProto == null) {
                rtBuilder.rt = ResultCode.NO_FURNITURE_PROTO.code
                return rtBuilder
            }
            val buyNum = protoBuyMap[furnitureProto]
            if (buyNum == null) {
                protoBuyMap[furnitureProto] = 1
            } else {
                protoBuyMap[furnitureProto] = buyNum + 1
            }
            furnitureInfoList.add(FurnitureInfo(it.x, it.y, it.direction, protoId))
        }

        // 计算花费
        val costAllRes = LinkedList<ResVo>()
        for ((furnitureProto, buyNum) in protoBuyMap) {
            val costRes = furnitureProto.costRes
            val protoId = furnitureProto.id

            // 计算已拥有
            var ownNum = furnitureBag[protoId]?.size ?: 0
            nowFurniture.values.forEach {
                if (protoId == it.protoId) {
                    ownNum++
                }
            }
            // 已拥有超过主题中要购买的数量，就移除掉
            if (ownNum < buyNum) {
                val trueBuyNum = buyNum - ownNum
                protoBuyMap[furnitureProto] = trueBuyNum
                for (i in 1..trueBuyNum) {
                    // 累计资源
                    costAllRes += costRes
                }
            } else {
                protoBuyMap[furnitureProto] = 0
            }
        }

        // 校验资源
        val checkCost = resHelper.checkRes(session, costAllRes)
        if (!checkCost) {
            rtBuilder.rt = ResultCode.LESS_RESOUCE.code
            return rtBuilder
        }

        val playerId = player.playerId
        protoBuyMap.forEach { proto, buyNum ->
            for (i in 1..buyNum) {
                // 扣除资源
                resHelper.costRes(
                    session,
                    com.point18.slg2d.common.constg.ACTION_BUY_FURNITURE,
                    player,
                    proto.costRes
                )
                furnitureDC.createFurniture(playerId, proto.id)
            }
        }

        val houseTheme = houseThemeDC.createHouseTheme(
            playerId,
            subjectProto.subject,
            subjectId, // 是购买主题
            furnitureInfoList
        )

        val themeInfoBuilder = HouseThemeInfo.newBuilder()
        themeInfoBuilder.themeId = houseTheme.id
        themeInfoBuilder.name = houseTheme.themeName
        themeInfoBuilder.name = subjectId.toString()
        themeInfoBuilder.protoId = subjectId
        rtBuilder.setTheme(themeInfoBuilder)

        return rtBuilder
    }

    private fun newBuyHouseThemeRtBuilder(): BuyHouseThemeRt.Builder {
        val rtBuilder = BuyHouseThemeRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        val themeInfoBuilder = HouseThemeInfo.newBuilder()
        themeInfoBuilder.themeId = 0
        themeInfoBuilder.name = ""
        rtBuilder.setTheme(themeInfoBuilder)

        return rtBuilder
    }
}