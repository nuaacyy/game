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
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.FurnitureBagInfo
import pb4client.RemoveTypeFurniture
import pb4client.RemoveTypeFurnitureRt
import java.util.*

// 一键移除同一类型的家具家具
class RemoveTypeFurnitureDeal(
    private val furHelper: FurnitureHelper = FurnitureHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<GuildHouseDC, FurnitureDC>(
    GuildHouseDC::class.java, FurnitureDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { guildHouseDC: GuildHouseDC, furnitureDC: FurnitureDC ->
            val reqMsg = msg as RemoveTypeFurniture
            val rtBuilder = this.handleRemoveTypeFurniture(
                session, reqMsg, guildHouseDC, furnitureDC
            )
            session.sendMsg(MsgType.RemoveTypeFurniture_1535, rtBuilder.build())

            // 通知产出变化情况
            val rtCode = furHelper.noticeProduce(session)
            if (rtCode.code != ResultCode.SUCCESS.code) {
                normalLog.error("通知家具产出失败$rtCode")
            }
        }
    }

    private fun handleRemoveTypeFurniture(
        session: PlayerActor,
        msg: RemoveTypeFurniture,
        guildHouseDC: GuildHouseDC,
        furnitureDC: FurnitureDC
    ): RemoveTypeFurnitureRt.Builder {
        val rtBuilder = RemoveTypeFurnitureRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val protoTypeList = msg.protoTypeList
        val floorIdx = msg.floorIdx

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

        val delList = LinkedList<Furniture>()
        for((_, furniture) in nowFurniture) {
            // 获取家具模板
            val proto = pcs.furnitureProtoCache.furnitureProtoMap[furniture.protoId]
            if(proto == null) {
                com.point18.slg2d.common.commonfunc.normalLog.error("刷新家具产出失败$rtCode")
                rtBuilder.rt = ResultCode.NO_FURNITURE_PROTO.code
                return rtBuilder
            }
            if (proto.type in protoTypeList && floorIdx == furniture.floorIdx) {
                delList.add(furniture)
            }
        }

        delList.forEach {
            // 移除指定家具
            rtCode = furHelper.changeFurnitureState(
                session, it,
                0, 0, 0, 0,
                com.point18.slg2d.common.constg.REMOVE_FURNITURE
            ).code
            if (rtCode != ResultCode.SUCCESS.code) {
                // TODO 未成功是否要把家具摆回原位置
                rtBuilder.rt = rtCode
                return rtBuilder
            }
            rtBuilder.addFurnitureId(it.id)
        }

        // 将背包中家具模板Id和该类家具数量填充到返回消息中
        for ((protoId, typeList) in  furnitureBag) {
            if (typeList.size == 0) {
                furnitureBag.remove(protoId)
                continue
            }
            val furnitureInfoBuilder = FurnitureBagInfo.newBuilder()
            furnitureInfoBuilder.protoId = protoId
            furnitureInfoBuilder.remain = typeList.size
            rtBuilder.addFurnitures(furnitureInfoBuilder)
        }

        return rtBuilder
    }
}