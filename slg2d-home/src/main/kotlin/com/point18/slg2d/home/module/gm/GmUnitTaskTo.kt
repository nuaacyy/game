package com.point18.slg2d.home.module.gm

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.UPDATE_INFO_BY_HOME_UNITTASK
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.event.GetUnitTaskRewardEvent
import com.point18.slg2d.home.module.fireEvent
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq

class GmUnitTaskTo : GmCommand, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun exec(session: PlayerActor, message: String) {
        prepare(session) { homePlayerDC: HomePlayerDC ->

            val player = homePlayerDC.player

            val messages = message.split(" ")
            if (messages.size == 1) {
                return@prepare
            }

            if (messages.size != 3) {
                return@prepare
            }

            val unitTaskId = messages[2].toIntOrNull()
            if (unitTaskId == null) {
                return@prepare
            }

            val effectHelper = ResearchEffectHelper()
            player.unitTaskId = unitTaskId
            fireEvent(session, GetUnitTaskRewardEvent(effectHelper))

            val askMsg = UpdateInfoByHomeAskReq.newBuilder()
            val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
            updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_UNITTASK
            updateInfoByHomeVo.updateValue = toJson(unitTaskId)
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

    }
}