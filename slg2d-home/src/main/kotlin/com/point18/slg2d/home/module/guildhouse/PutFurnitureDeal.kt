package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.FurnitureHelper
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.refreshProduce
import com.point18.slg2d.home.dc.Furniture
import com.point18.slg2d.home.dc.FurnitureDC
import com.point18.slg2d.home.dc.GuildHouseDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.FurnitureInfo
import pb4client.PutFurniture
import pb4client.PutFurnitureRt
import java.util.Arrays.asList

// 放置家具
class PutFurnitureDeal(
    private val furHelper: FurnitureHelper = FurnitureHelper(),
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<GuildHouseDC, FurnitureDC>(
    GuildHouseDC::class.java, FurnitureDC::class.java, asList(furHelper, resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { guildHouseDC: GuildHouseDC, furnitureDC: FurnitureDC ->
            val reqMsg = msg as PutFurniture

            val rtBuilder = this.handlePutFurniture(session, reqMsg, guildHouseDC, furnitureDC)
            session.sendMsg(MsgType.EnterGuildHouse_1521, rtBuilder.build())

            // 通知产出变化情况
            val rtCode = furHelper.noticeProduce(session)
            if (rtCode.code != ResultCode.SUCCESS.code) {
                com.point18.slg2d.common.commonfunc.normalLog.error("通知家具产出失败$rtCode")
            }
        }

    }

    private fun handlePutFurniture(
        session: PlayerActor,
        msg: PutFurniture,
        guildHouseDC: GuildHouseDC,
        furnitureDC: FurnitureDC
    ): PutFurnitureRt.Builder {
        val rtBuilder = newPutFurnitureRtBuilder()

        val protoId = msg.protoId
        val x = msg.x
        val y = msg.y
        val direction = msg.direction
        val floorIdx = msg.floorIdx
        val buyNum = msg.buyNum

        val guildHouse = guildHouseDC.guildHouse
        val nowFurniture = furnitureDC.nowFurniture
        val furnitureBag = furnitureDC.furnitureBag

        // 刷新产出(在旧的舒适度刷新)
        var rtCode = refreshProduce(nowFurniture.values, guildHouse.comfort).code
        if (rtCode != ResultCode.SUCCESS.code) {
            com.point18.slg2d.common.commonfunc.normalLog.error("刷新家具产出失败$rtCode")
            rtBuilder.rt = rtCode
            return rtBuilder
        }

        // 获取背包中获取该类型的家具
        var typeList = furnitureBag[protoId]
        var furniture: Furniture? = null
        if (typeList == null || typeList.size == 0) { // 没有该类型的家具
            if (buyNum > 0) { // 若需要购买，购买后摆放；若不需要，直接返回错误码
                // 购买之前校验是否可摆放
                rtCode = furHelper.putFurnitureCheck(
                    session,
                    protoId,
                    floorIdx,
                    x,
                    y,
                    direction
                ).code
                if (rtCode != ResultCode.SUCCESS.code) {
                    rtBuilder.rt = rtCode
                    return rtBuilder
                }
                // 购买(创建)家具
                rtCode = furHelper.createAllFurniture(session, resHelper, protoId, buyNum).code
                if (rtCode != ResultCode.SUCCESS.code) {
                    rtBuilder.rt = rtCode
                    return rtBuilder
                }
                // 重新获取家具
                typeList = furnitureBag[protoId]
                if (typeList == null || typeList.size == 0) {
                    com.point18.slg2d.common.commonfunc.normalLog.error("购买后仍然找不到该类型[$protoId]的家具")
                    rtBuilder.rt = ResultCode.CANT_FIND_FURNITURE.code
                    return rtBuilder
                }
                furniture = typeList[0]
                // 设置摆放状态
                rtCode = furHelper.putFurniture(
                    session,
                    furniture,
                    floorIdx,
                    x,
                    y,
                    direction
                ).code
                if (rtCode != ResultCode.SUCCESS.code) {
                    com.point18.slg2d.common.commonfunc.normalLog.error("摆放未成功")
                    rtBuilder.rt = rtCode
                    return rtBuilder
                }
            } else {
                com.point18.slg2d.common.commonfunc.normalLog.error("找不到该类型[$protoId]的家具")
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
        } else { // 拥有该类型的家具
            furniture = typeList[0]
            // 设置摆放状态
            rtCode = furHelper.changeFurnitureState(
                session, furniture,
                floorIdx, x, y, direction,
                com.point18.slg2d.common.constg.PUT_FURNITURE
            ).code
            if (rtCode != ResultCode.SUCCESS.code) {
                com.point18.slg2d.common.commonfunc.normalLog.error("摆放未成功")
                rtBuilder.rt = rtCode
                return rtBuilder
            }
            // 若需要购买，则购买
            if (buyNum > 0) {
                // 购买(创建)家具
                rtCode = furHelper.createAllFurniture(session, resHelper, protoId, buyNum).code
                if (rtCode != ResultCode.SUCCESS.code) {
                    rtBuilder.rt = rtCode
                    // TODO 若购买失败是否要移除刚才摆的家具
                    return rtBuilder
                }
            }
        }

        // 设置返回消息中的家具状态
        val furBuilder = FurnitureInfo.newBuilder()
        furBuilder.id = furniture.id
        furBuilder.protoId = furniture.protoId
        furBuilder.floorIdx = furniture.floorIdx
        furBuilder.x = furniture.x
        furBuilder.y = furniture.y
        furBuilder.direction = furniture.direction
        furBuilder.startTime = (furniture.startTime / 1000).toInt()
        furBuilder.endTime = (furniture.endTime / 1000).toInt()
        rtBuilder.setFurniture(furBuilder)

        return rtBuilder
    }

    private fun newPutFurnitureRtBuilder(): PutFurnitureRt.Builder {
        val rtBuilder = PutFurnitureRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        val furBuilder = FurnitureInfo.newBuilder()
        furBuilder.id = 0L
        furBuilder.protoId = 0
        furBuilder.floorIdx = 0
        furBuilder.x = 0
        furBuilder.y = 0
        furBuilder.direction = 0
        furBuilder.startTime = 0
        furBuilder.endTime = 0
        rtBuilder.setFurniture(furBuilder)
        return rtBuilder
    }
}