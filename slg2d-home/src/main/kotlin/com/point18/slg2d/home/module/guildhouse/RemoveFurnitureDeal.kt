package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.FurnitureHelper
import com.point18.slg2d.home.common.refreshProduce
import com.point18.slg2d.home.dc.FurnitureDC
import com.point18.slg2d.home.dc.GuildHouseDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.RemoveFurniture
import pb4client.RemoveFurnitureRt
import java.util.Arrays.asList

// 移除家具
class RemoveFurnitureDeal(
    private val furHelper: FurnitureHelper = FurnitureHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<GuildHouseDC, FurnitureDC>(
    GuildHouseDC::class.java, FurnitureDC::class.java, asList(furHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { guildHouseDC: GuildHouseDC, furnitureDC: FurnitureDC ->
            val reqMsg = msg as RemoveFurniture
            val rtBuilder = this.handleRemoveFurniture(session, reqMsg, guildHouseDC, furnitureDC)
            session.sendMsg(MsgType.RemoveFurniture_1525, rtBuilder.build())

            // 通知产出变化情况
            val rtCode = furHelper.noticeProduce(session)
            if (rtCode.code != ResultCode.SUCCESS.code) {
                com.point18.slg2d.common.commonfunc.normalLog.error("通知家具产出失败$rtCode")
            }
        }
    }

    private fun handleRemoveFurniture(
        session: PlayerActor,
        msg: RemoveFurniture,
        guildHouseDC: GuildHouseDC,
        furnitureDC: FurnitureDC
    ): RemoveFurnitureRt.Builder {
        val rtBuilder = RemoveFurnitureRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val furnitureId = msg.furnitureId

        val guildHouse = guildHouseDC.guildHouse
        val nowFurniture = furnitureDC.nowFurniture

        // 刷新产出(在旧的舒适是刷新)
        var rtCode = refreshProduce(nowFurniture.values, guildHouse.comfort).code
        if (rtCode != ResultCode.SUCCESS.code) {
            com.point18.slg2d.common.commonfunc.normalLog.error("刷新家具产出失败$rtCode")
            rtBuilder.rt = rtCode
            return rtBuilder
        }

        // 获取家具
        val furniture = nowFurniture[furnitureId]
        if (furniture == null) {
            com.point18.slg2d.common.commonfunc.normalLog.error("找不到家具$furnitureId")
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        // 移除指定家具
        rtCode = furHelper.changeFurnitureState(
            session, furniture,
            0, 0, 0, 0,
            com.point18.slg2d.common.constg.REMOVE_FURNITURE
        ).code
        if (rtCode != ResultCode.SUCCESS.code) {
            rtBuilder.rt = rtCode
            return rtBuilder
        }

        return rtBuilder
    }
}