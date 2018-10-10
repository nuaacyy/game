package com.point18.slg2d.home.module.innerCity.innerCityEffect

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.InnerCity
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.module.innerCity.UpdateEffectHandle
import pb4server.Home2WorldAskResp
import pb4server.RefreshWarnAskReq
import xyz.ariane.util.lzWarn

class PreWarnEffectAction : UpdateEffectHandle {

    override fun updateEffectH(
        session: PlayerActor,
        upEffKey: Int,
        effValue: Int,
        player: HomePlayer,
        building: InnerCity,
        innerCityDC: InnerCityDC
    ) {
        // 计算特定建筑到目标等级的更新效果合并值
        val updateMap = pcs.innerBuildingDataCache.calUpdateEffMap(building.cityType, building.lv)
        player.putInnerBuildingEffectInfoMap(updateMap)

        // 刷新玩家预警
        val askMsg = RefreshWarnAskReq.newBuilder()
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                it.setRefreshWarnAskReq(askMsg)
            }, Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, err ->
            try {
                when {
                    err != null -> {
                        normalLog.lzWarn { "通知world刷新预警错误{$err}" }
                        return@whenCompleteKt
                }

                    askResp == null -> {
                        normalLog.lzWarn { "通知world刷新预警错误{$err}" }
                        return@whenCompleteKt
                    }

                    else -> {
                        if (askResp.refreshWarnAskRt.rt != ResultCode.SUCCESS.code) {
                            normalLog.lzWarn { "通知world刷新预警失败{${askResp.refreshWarnAskRt.rt}}" }
                            return@whenCompleteKt
                        }
                    }
                }

            } catch (e: Exception) {
                // TODO
            }

        }

    }

}