package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.getProduceEndTime
import com.point18.slg2d.home.dc.FurnitureDC
import com.point18.slg2d.home.dc.GuildHouseDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.dc.ThumbInfoDC
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.EnterGuildHouse
import pb4client.EnterGuildHouseRt
import pb4client.FurnitureInfo

// 进入后宅处理
class EnterGuildHouseDeal : HomeClientMsgDeal, HomeHelperPlus4<GuildHouseDC, FurnitureDC, ThumbInfoDC, InnerCityDC>(
    GuildHouseDC::class.java, FurnitureDC::class.java, ThumbInfoDC::class.java, InnerCityDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { guildHouseDC: GuildHouseDC, furnitureDC: FurnitureDC,
                           thumbInfoDC: ThumbInfoDC, innerCityDC: InnerCityDC ->
            val playerId = (msg as EnterGuildHouse).playerId

            var rtBuilder: EnterGuildHouseRt.Builder? = null
            if (playerId == session.playerId) {
                // 访问自己的后宅
                rtBuilder = enterMyHouse(session, msg, innerCityDC, guildHouseDC, thumbInfoDC, furnitureDC)
            } else if (playerId == 0L) {
                // 随机访问
                rtBuilder = randomEnterHouse(session, msg)
            } else {
                // TODO 访问他人后宅
            }

            if (rtBuilder != null) {
                session.sendMsg(MsgType.EnterGuildHouse_1521, rtBuilder.build())
            }
        }
    }

    private fun enterMyHouse(
        session: PlayerActor,
        msg: EnterGuildHouse,
        innerCityDC: InnerCityDC,
        guildHouseDC: GuildHouseDC,
        thumbInfoDC: ThumbInfoDC,
        furnitureDC: FurnitureDC
    ): EnterGuildHouseRt.Builder {
        val rtBuilder = this.newEnterGuildHouseRt()

        val playerId = msg.playerId

        // 校验后宅等级
        val buildingList =
            innerCityDC.findEffectiveInnerBuildingsByType(com.point18.slg2d.common.constg.GuildHouse)
        if (buildingList.count() == 0) {
            rtBuilder.rt = ResultCode.INNER_CITY_NOT_FIND_BUILDING.code
            return rtBuilder
        }
        val guildHouseBuilding = buildingList[0]
        val houseLv = guildHouseBuilding.lv

        // TODO 筛选楼层建筑
        val openFloor = pcs.innerBuildingDataCache.getEffValue(com.point18.slg2d.common.constg.GuildHouse, houseLv, com.point18.slg2d.common.constg.LV_EFF_FLOOR_OPEN)

        // 获取后宅缓存
        val guildHouse = guildHouseDC.guildHouse
        rtBuilder.name = guildHouse.name
        rtBuilder.comfort = guildHouse.comfort

        // 获取点赞信息缓存
        val thumbInfo = thumbInfoDC.thumbInfo
        var thumbInNew = 0
        thumbInfo.thumbIn.forEach { _, thumb ->
            if (thumb.thumbTime > thumbInfo.checkTime) {
                thumbInNew++
            }
        }
        rtBuilder.thumbedUpNew = thumbInNew

        // 场景中的家具
        val nowFurniture = furnitureDC.nowFurniture

        // 当前后宅家具信息Builder
        nowFurniture.forEach {
            val furBuilder = FurnitureInfo.newBuilder()
            val fur = it.value
            furBuilder.id = fur.id
            furBuilder.protoId = fur.protoId
            furBuilder.floorIdx = fur.floorIdx
            furBuilder.x = fur.x
            furBuilder.y = fur.y
            furBuilder.direction = fur.direction
            furBuilder.startTime = (fur.startTime / 1000).toInt()
            furBuilder.endTime = (fur.endTime / 1000).toInt()

            // 获取家具模板
            val furProto = pcs.furnitureProtoCache.furnitureProtoMap[fur.protoId]
            if (furProto == null) {
                rtBuilder.rt = ResultCode.NO_FURNITURE_PROTO.code
                return rtBuilder
            }

            // 获取家具产出的endTime
            if (!furProto.produceRes.isEmpty() && fur.startTime != 0L) { // 通过家具的产出非空，来判断是否是功能家具
                val (
                        rtCode: ResultCode,
                        endTime: Long
                ) = getProduceEndTime(fur, guildHouse.comfort)
                if (rtCode.code != ResultCode.SUCCESS.code) {
                    normalLog.error("获取家具产出失败${fur.id}")
                    rtBuilder.rt = rtCode.code
                    return rtBuilder
                }
                furBuilder.endTime = (endTime / 1000).toInt()
            }
            rtBuilder.addFurnitures(furBuilder)
        }

        return rtBuilder
    }

    private fun randomEnterHouse(
        session: PlayerActor,
        msg: EnterGuildHouse
    ): EnterGuildHouseRt.Builder {
        val rtBuilder = this.newEnterGuildHouseRt()
        return rtBuilder
    }

    private fun newEnterGuildHouseRt(): EnterGuildHouseRt.Builder {
        val rtBuilder = EnterGuildHouseRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        rtBuilder.name = "" // 后宅名称
        rtBuilder.comfort = 0  // 舒适度
        rtBuilder.thumbedUpNew = 0 // 被赞总数

        return rtBuilder
    }
}