package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.UPDATE_INFO_BY_HOME_SKIN
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.SkinDC
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq

fun updateInUseSkin2World(session: PlayerActor, skinDC: SkinDC) {

    val skin = skinDC.skins.firstOrNull { intToBool(it.isUse) } ?: return
    val askMsg = UpdateInfoByHomeAskReq.newBuilder()
    val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
    updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_SKIN
    updateInfoByHomeVo.updateValue = toJson(skin.skinType)
    askMsg.addUpdates(updateInfoByHomeVo)

    session.createACS<Home2WorldAskResp>()
        .ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
            Home2WorldAskResp::class.java
        )
        .whenCompleteKt { rt, askErr ->

            try {
                when {
                    askErr != null -> {
                    }
                    rt == null -> {

                    }
                    else -> {
                        val updateInfoByHomeAskRt = rt.updateInfoByHomeAskRt
                        if (updateInfoByHomeAskRt.rt != ResultCode.SUCCESS.code) {

                        } else {

                        }
                    }
                }

            } catch (e: Exception) {
            }
        }
}