package com.point18.slg2d.home.module.vip

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.event.VipLvChangeEvent
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq

class VipChangeEventHandler : IEventHandler<VipLvChangeEvent>, HomeHelperPlus1<VipDC>(VipDC::class.java) {

    override fun handleEvent(session: PlayerActor, event: VipLvChangeEvent) {
        prepare(session) { vipDC: VipDC ->
            val effectMap = event.effectHelper.getVipEffectInfoMapByPlayerId(vipDC)
            if (effectMap.containsKey(ResearchEffectFoodAdd) ||
                effectMap.containsKey(ResearchEffectWoodAdd) ||
                effectMap.containsKey(ResearchEffectStoneAdd) ||
                effectMap.containsKey(ResearchEffectIronAdd)
            ) {
                event.refreshResHelper.refreshResource(session, VipResYield)
            }

            val askMsg = UpdateInfoByHomeAskReq.newBuilder()
            val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
            updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_VIP_LV
            updateInfoByHomeVo.updateValue = toJson(event.newVipLv)
            askMsg.addUpdates(updateInfoByHomeVo)

            session.createACS<Home2WorldAskResp>()
                .ask(
                    session.worldShardProxy,
                    session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
                    Home2WorldAskResp::class.java
                )
                .whenCompleteKt { askResp, askErr ->
                    try {
                        when {
                            askErr != null -> {
                                // TODO
                            }

                            askResp == null -> {
                                // TODO
                            }

                            else -> {
                                // TODO
                            }
                        }

                    } catch (e: Exception) {
                        // TODO
                    }
                }
        }
    }
}