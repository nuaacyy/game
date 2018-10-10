package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.FurnitureHelper
import com.point18.slg2d.home.common.refreshProduce
import com.point18.slg2d.home.dc.Furniture
import com.point18.slg2d.home.dc.FurnitureDC
import com.point18.slg2d.home.dc.GuildHouseDC
import com.point18.slg2d.home.dc.HouseThemeDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.FurnitureInfo
import pb4client.UseHouseTheme
import pb4client.UseHouseThemeRt
import java.util.*
import java.util.Arrays.asList

// 使用主题
class UseHouseThemeDeal(
    private val furHelper: FurnitureHelper = FurnitureHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HouseThemeDC, GuildHouseDC, FurnitureDC>(
    HouseThemeDC::class.java, GuildHouseDC::class.java, FurnitureDC::class.java,
    asList(furHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { houseThemeDC: HouseThemeDC, guildHouseDC: GuildHouseDC, furnitureDC: FurnitureDC ->
            val reqMsg = msg as UseHouseTheme
            val rtBuilder = this.handleUseHouseTheme(
                session, reqMsg, houseThemeDC, guildHouseDC, furnitureDC
            )
            session.sendMsg(MsgType.UseHouseTheme_1527, rtBuilder.build())

            // 通知产出变化情况
            val rtCode = furHelper.noticeProduce(session)
            if (rtCode.code != ResultCode.SUCCESS.code) {
                normalLog.error("通知家具产出失败$rtCode")
            }
        }
    }

    private fun handleUseHouseTheme(
        session: PlayerActor,
        msg: UseHouseTheme,
        houseThemeDC: HouseThemeDC,
        guildHouseDC: GuildHouseDC,
        furnitureDC: FurnitureDC
    ): UseHouseThemeRt.Builder {
        val rtBuilder = UseHouseThemeRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val themeId = msg.themeId
        val floorIdx = msg.floorIdx

        val houseTheme = houseThemeDC.houseThemeMap[themeId]
        if (houseTheme == null) {
            normalLog.error("找不到玩家[${houseThemeDC.playerId}]的主题[$themeId]")
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        // 找到后宅缓存
        val guildHouse = guildHouseDC.guildHouse
        val nowFurniture = furnitureDC.nowFurniture
        val furnitureBag = furnitureDC.furnitureBag

        // 将指定楼层的家具放入删除列表
        val delList = LinkedList<Furniture>()
        for((_, furniture) in nowFurniture) {
            if (furniture.floorIdx == floorIdx) {
                delList.add(furniture)
            }
        }

        // 校验家具是否有模板
        delList.forEach {
            val proto = pcs.furnitureProtoCache.furnitureProtoMap[it.protoId]
            if (proto == null) {
                rtBuilder.rt = ResultCode.NO_FURNITURE_PROTO.code
                return rtBuilder
            }
        }
        houseTheme.furnitureInfoList.forEach {
            val proto = pcs.furnitureProtoCache.furnitureProtoMap[it.protoId]
            if (proto == null) {
                rtBuilder.rt = ResultCode.NO_FURNITURE_PROTO.code
                return rtBuilder
            }
        }

        // 校验是否拥有足够要使用的主题中的家具
        val ownMap = mutableMapOf<Int, Int>()
        houseTheme.furnitureInfoList.forEach {
            val typeList = furnitureBag[it.protoId]
            val needOwnNum = (ownMap[it.protoId] ?: 0) + 1
            if (typeList == null || typeList.size < needOwnNum) {
                normalLog.error("该类型[${it.protoId}]的家具数量不足")
                rtBuilder.rt = ResultCode.HOUSE_THEME_ERROR.code
                return rtBuilder
            }
        }

        // 刷新产出(在旧的舒适度刷新)
        var rtCode = refreshProduce(nowFurniture.values, guildHouse.comfort).code
        if (rtCode != ResultCode.SUCCESS.code) {
            normalLog.error("刷新家具产出失败$rtCode")
            rtBuilder.rt = rtCode
            return rtBuilder
        }

        // 移除在删除列表中的家具
        delList.forEach {
            // 移除指定家具
            rtCode = furHelper.changeFurnitureState(
                session, it,
                0, 0, 0, 0,
                com.point18.slg2d.common.constg.REMOVE_FURNITURE
            ).code
            if (rtCode != ResultCode.SUCCESS.code) {
                // 进入这里，要么行为参数传错了，要么家具模板没找到，但是家具状态已经改了，所以提前校验下能不能获取模板
                rtBuilder.rt = rtCode
                return rtBuilder
            }
        }

        // 根据模板放置家具
        houseTheme.furnitureInfoList.forEach {
            val typeList = furnitureBag[it.protoId]
            if (typeList == null || typeList.size == 0) {
                // 在之前校验过是否拥有足够数量的家具，因此这里应该不会走到
                normalLog.error("找不到该类型[${it.protoId}]的家具")
                rtBuilder.rt = ResultCode.HOUSE_THEME_ERROR.code
                return rtBuilder
            }
            // 获取要摆的家具
            val furniture = typeList[0]
            // 摆放
            rtCode = furHelper.changeFurnitureState(
                session, furniture,
                floorIdx, it.x, it.y, it.direction,
                com.point18.slg2d.common.constg.PUT_FURNITURE
            ).code
            if (rtCode != ResultCode.SUCCESS.code) {
                // TODO 摆放失败是否要回复楼层的初始状态
                rtBuilder.rt = rtCode
                return rtBuilder
            }
        }

        nowFurniture.forEach { _, furniture ->
            if (furniture.floorIdx == floorIdx) {
                val furBuilder = FurnitureInfo.newBuilder()
                furBuilder.id = furniture.id
                furBuilder.protoId = furniture.protoId
                furBuilder.floorIdx = floorIdx
                furBuilder.x = furniture.x
                furBuilder.y = furniture.y
                furBuilder.direction = furniture.direction
                furBuilder.startTime = (furniture.startTime / 1000).toInt()
                furBuilder.endTime = (furniture.endTime / 1000).toInt()
                rtBuilder.addFurnitures(furBuilder)
            }
        }

        return rtBuilder
    }
}