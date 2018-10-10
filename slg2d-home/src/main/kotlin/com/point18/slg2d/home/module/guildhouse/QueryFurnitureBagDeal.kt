package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FurnitureDC
import com.point18.slg2d.home.dc.HouseThemeDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.FurnitureBagInfo
import pb4client.HouseThemeInfo
import pb4client.QueryFurnitureBagRt

// 请求家具背包
class QueryFurnitureBagDeal : HomeClientMsgDeal, HomeHelperPlus2<FurnitureDC, HouseThemeDC>(
    FurnitureDC::class.java, HouseThemeDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { furnitureDC: FurnitureDC, houseThemeDC: HouseThemeDC ->
            val rtBuilder = handleQueryFurnitureBag(
                session, furnitureDC, houseThemeDC
            )
            session.sendMsg(MsgType.QueryFurnitureBag_1522, rtBuilder.build())
        }
    }

    private fun handleQueryFurnitureBag(
        session: PlayerActor,
        furnitureDC: FurnitureDC,
        houseThemeDC: HouseThemeDC
    ): QueryFurnitureBagRt.Builder {
        val rtBuilder = QueryFurnitureBagRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val furnitureBag = furnitureDC.furnitureBag
        val nowFurniture = furnitureDC.nowFurniture

        // 从背包获取家具类型/数目
        furnitureBag.forEach { protoId, typeList ->
            if (typeList.size == 0) {
                furnitureBag.remove(protoId)
                return@forEach
            }
            val furnitureInfoBuilder = FurnitureBagInfo.newBuilder()
            furnitureInfoBuilder.protoId = protoId
            // 家具数量
            furnitureInfoBuilder.remain = typeList.size
            rtBuilder.addFurnitures(furnitureInfoBuilder)
        }

        // 玩家后宅所有主题的Id
        val houseThemes = houseThemeDC.houseThemeMap
        houseThemes.forEach { id, theme ->
            val themeInfoBuilder = HouseThemeInfo.newBuilder()
            themeInfoBuilder.themeId = id
            themeInfoBuilder.name = theme.themeName
            for (key in pcs.furnitureSubjectProtoCache.subjectProtoMap.keys) {
                if (theme.subjectId != 0) {
                    themeInfoBuilder.protoId = key
                    rtBuilder.addSubjectId(theme.subjectId)  // TODO 协议中这两个字段的意义是重复的，前端现在以这个字段为准
                }
            }
            rtBuilder.addThemes(themeInfoBuilder)
        }

        return rtBuilder
    }
}