package com.point18.slg2d.home.module.skin

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.updateInUseSkin2World
import com.point18.slg2d.home.dc.SkinDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ChangeSkinRt

// 购买皮肤
class ChangeSkin(
) : HomeClientMsgDeal,
    HomeHelperPlus1<SkinDC>(
        SkinDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val skinType = (msg as pb4client.ChangeSkin).skinType
        prepare(session) { skinDC ->
            val rt = changeSkin(session, skinDC, skinType)
            session.sendMsg(MsgType.ChangeSkin_45, rt)
        }
    }
}

fun changeSkin(session: PlayerActor, skinDC: SkinDC, skinType: Int): ChangeSkinRt {
    val rt = pb4client.ChangeSkinRt.newBuilder()
    rt.rt = ResultCode.SUCCESS.code

    val skin = skinDC.skins.firstOrNull { it.skinType == skinType }
    val oldSkin = skinDC.nowSkin

    if (skin == null) {
        // 没有该皮肤
        rt.rt = ResultCode.SKIN_NO_HAVE_ERROR.code
        return rt.build()
    }

    if (intToBool(skin.isUse)) {
        rt.rt = ResultCode.SKIN_IN_USE_ERROR.code
        return rt.build()
    }

    skin.isUse = boolToInt(true)
    if (oldSkin != null) {
        oldSkin.isUse = boolToInt(false)
    }
    skinDC.nowSkin = skin

    updateInUseSkin2World(session, skinDC)

    return rt.build()
}
