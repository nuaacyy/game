package com.point18.slg2d.home.module.skin

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.SkinDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetNewSkinEvent
import com.point18.slg2d.home.module.fireEvent
import java.util.*

// 购买皮肤
class BuySkinDeal(
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<SkinDC>(
        SkinDC::class.java,
        Arrays.asList(effectHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val skinType = (msg as pb4client.BuySkin).skinType
        prepare(session) { skinDC ->
            val rt = skin(session, skinType, skinDC)
            session.sendMsg(MsgType.BuySkin_44, rt.build())
        }
    }

    fun skin(session: PlayerActor, skinType: Int, skinDC: SkinDC): pb4client.BuySkinRt.Builder {
        val rt = pb4client.BuySkinRt.newBuilder()
        rt.skinType = skinType
        rt.rt = ResultCode.SUCCESS.code

        if (!com.point18.slg2d.common.pc.pcs.skinAttributeCache.skinAttributeProtoMapBySkinType.containsKey(skinType)) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val skinExist = skinDC.skins.firstOrNull { it.skinType == skinType } != null

        if (skinExist) {
            rt.rt = ResultCode.SKIN_HAVE_ERROR.code
            return rt
        }

        skinDC.createSkin(skinType, 0)

        effectHelper.syncEffect2World(session, com.point18.slg2d.common.constg.SKIN_EFFECT)

        fireEvent(session, GetNewSkinEvent(skinType))
        return rt
    }
}


