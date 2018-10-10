package com.point18.slg2d.home.module.innerCity.innerCityEffect

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.UPDATE_INFO_BY_HOME_BUILD_INFO
import com.point18.slg2d.common.constg.UPDATE_INFO_BY_HOME_CASTLE_LV
import com.point18.slg2d.common.constg.UpdateInfoByHomeBuildInfoVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.updateAllianceMemberInfo
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.InnerCity
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.module.innerCity.UpdateEffectHandle
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.*

class AddCastleLv : UpdateEffectHandle {

    override fun updateEffectH(
        session: PlayerActor,
        upEffKey: Int,
        effValue: Int,
        player: HomePlayer,
        building: InnerCity,
        innerCityDC: InnerCityDC
    ) {
        val effBuildMap = pcs.innerBuildingDataCache.fetchSpecBuildingMap(upEffKey) ?: return
        var maxLv = 0
        for (effBuildType in effBuildMap) {
            val builds = innerCityDC.findInnerCityListFromCastleIdAndType(player.focusCastleId, effBuildType)
            for (build in builds) {
                if (build.lv > 0) {
                    val value = pcs.innerBuildingDataCache.getEffValue(effBuildType, build.lv, upEffKey)
                    if (value > maxLv) {
                        maxLv = value
                    }
                }
            }
        }

        player.castleLv = maxLv

        // 把变化后的城池等级同步到世界去
        val buildingLvs = LinkedList<Int>()
        innerCityDC.findEffectiveInnerBuildingsByType(com.point18.slg2d.common.constg.MainBuilding)
            .forEach { buildingLvs.add(it.lv) }

        val askMsg = UpdateInfoByHomeAskReq.newBuilder()
        val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
        updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_CASTLE_LV
        updateInfoByHomeVo.updateValue = toJson(maxLv)
        askMsg.addUpdates(updateInfoByHomeVo)
        val updateInfoByHomeVo2 = pb4server.UpdateInfoByHomeVo.newBuilder()
        updateInfoByHomeVo2.updateType = UPDATE_INFO_BY_HOME_BUILD_INFO
        updateInfoByHomeVo2.updateValue =
                toJson(UpdateInfoByHomeBuildInfoVo(com.point18.slg2d.common.constg.MainBuilding, buildingLvs))
        askMsg.addUpdates(updateInfoByHomeVo2)

        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
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

        if (player.allianceId != 0L) {
            val updateInfoMap = hashMapOf<Int, String>()
            updateInfoMap[com.point18.slg2d.common.constg.UpdateCastleLv] = maxLv.toString()
            updateAllianceMemberInfo(session, player.allianceId, player.playerId, updateInfoMap)
        }

    }

}