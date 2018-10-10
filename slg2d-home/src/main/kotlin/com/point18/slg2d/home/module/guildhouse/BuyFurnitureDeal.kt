package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.FurnitureHelper
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BuyFurniture
import pb4client.BuyFurnitureRt
import java.util.Arrays.asList

// 购买家具
class BuyFurnitureDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val furHelper: FurnitureHelper = FurnitureHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java, asList(resHelper, furHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val reqMsg = msg as BuyFurniture

        prepare(session) {
            val rtBuilder = BuyFurnitureRt.newBuilder()
            rtBuilder.rt = furHelper.createAllFurniture(
                session,
                resHelper,
                reqMsg.protoId,
                reqMsg.buyNum
            ).code
            session.sendMsg(MsgType.BuyFurniture_1526, rtBuilder.build())
        }
    }
}