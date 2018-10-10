package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.MOVE_FURNITURE
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.FurnitureHelper
import com.point18.slg2d.home.dc.FurnitureDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.MoveFurniture
import pb4client.MoveFurnitureRt
import java.util.Arrays.asList

// 移动家具
class MoveFurnitureDeal(
    private val furHelper: FurnitureHelper = FurnitureHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<FurnitureDC>(FurnitureDC::class.java, asList(furHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { furnitureDC: FurnitureDC ->
            val reqMsg = msg as MoveFurniture
            val rtBuilder = this.handleMoveFurniture(session, reqMsg, furnitureDC)
            session.sendMsg(MsgType.MoveFurniture_1524, rtBuilder.build())
        }
    }

    private fun handleMoveFurniture(
        session: PlayerActor,
        msg: MoveFurniture,
        furnitureDC: FurnitureDC
    ): MoveFurnitureRt.Builder {
        val rtBuilder = MoveFurnitureRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val furnitureId = msg.furnitureId
        val x = msg.x
        val y = msg.y
        val direction = msg.direction

        val nowFurniture = furnitureDC.nowFurniture

        val f = nowFurniture[furnitureId]
        if (f == null) {
            normalLog.error("找不到家具$furnitureId")
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        // 移动位置
        val rtCode = furHelper.changeFurnitureState(
            session,
            f,
            f.floorIdx,
            x,
            y,
            direction,
            MOVE_FURNITURE
        ).code
        if (rtCode != ResultCode.SUCCESS.code) {
            rtBuilder.rt = rtCode
            return rtBuilder
        }

        return rtBuilder
    }
}