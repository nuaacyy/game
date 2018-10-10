package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HouseThemeDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ChangeThemeName
import pb4client.ChangeThemeNameRt

// 修改主题属性
class ChangeThemeNameDeal : HomeClientMsgDeal, HomeHelperPlus1<HouseThemeDC>(HouseThemeDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { houseThemeDC: HouseThemeDC ->
            val rtBuilder = this.handleChangeThemeName(
                session, msg as ChangeThemeName, houseThemeDC
            )
            session.sendMsg(MsgType.ChangeThemeName_1537, rtBuilder.build())
        }
    }

    private fun handleChangeThemeName(
        session: PlayerActor,
        msg: ChangeThemeName,
        houseThemeDC: HouseThemeDC
    ): ChangeThemeNameRt.Builder {
        val rtBuilder = ChangeThemeNameRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val name = msg.name
        val themeId = msg.themeId

        // 获取后宅主题缓存
        val houseTheme = houseThemeDC.houseThemeMap[themeId]
        if (houseTheme == null) {
            com.point18.slg2d.common.commonfunc.normalLog.error("未找到后宅主题$themeId")
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        houseTheme.themeName = name

        return rtBuilder
    }
}