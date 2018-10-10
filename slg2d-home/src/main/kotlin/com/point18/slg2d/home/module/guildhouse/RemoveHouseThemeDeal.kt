package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HouseThemeDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.RemoveHouseTheme
import pb4client.RemoveHouseThemeRt

// 移除主题
class RemoveHouseThemeDeal : HomeClientMsgDeal, HomeHelperPlus1<HouseThemeDC>(HouseThemeDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { houseThemeDC: HouseThemeDC ->
            val reqMsg = msg as RemoveHouseTheme
            val rtBuilder = this.handleRemoveFurniture(session, reqMsg, houseThemeDC)
            session.sendMsg(MsgType.RemoveHouseTheme_1534, rtBuilder.build())
        }
    }

    private fun handleRemoveFurniture(
        session: PlayerActor, msg: RemoveHouseTheme, houseThemeDC: HouseThemeDC
    ): RemoveHouseThemeRt.Builder {
        val rtBuilder = RemoveHouseThemeRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val themeId = msg.themeId

        val houseTheme = houseThemeDC.houseThemeMap[themeId]
        if (houseTheme == null) {
            normalLog.error("未找到后宅主题$themeId")
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        houseThemeDC.deleteHouseTheme(houseTheme)

        return rtBuilder
    }
}