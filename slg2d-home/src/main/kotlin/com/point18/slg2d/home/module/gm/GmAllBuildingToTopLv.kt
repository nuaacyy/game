package com.point18.slg2d.home.module.gm

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.CHANGE_INNER_CITY
import com.point18.slg2d.common.constg.STABLE
import com.point18.slg2d.common.constg.UPDATE_INFO_BY_HOME_BUILD_INFO
import com.point18.slg2d.common.constg.UpdateInfoByHomeBuildInfoVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.event.GmRefTaskEvent
import com.point18.slg2d.home.module.event.RefreshBuildEffectEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.*
import java.util.Arrays.asList

class GmAllBuildingToTopLv(
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : GmCommand, HomeHelperPlus2<HomePlayerDC, InnerCityDC>(
    HomePlayerDC::class.java,
    InnerCityDC::class.java,
    asList(targetHelper, effectHelper)
) {

    override fun exec(session: PlayerActor, message: String) {
        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC ->

            // 格式1： -gm add 类型 数量
            // 格式2： -gm changeCD building
            val messages = message.split(" ")
            if (messages.size == 1) {
                return@prepare
            }

            val player = homePlayerDC.player

            val innerCityBuildings = innerCityDC.findInnerCityListFromCastleId(player.focusCastleId)
            for (b in innerCityBuildings) {
                val buildType = b.cityType
                // 建造建筑
                val buildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[buildType]
                if (buildingProto == null) {
                    // 建筑配置没找到
                    continue
                }

                b.lv = buildingProto.topLevel
                innerCityDC.updateInnerCityUpgradeState(b, STABLE, 0, 0)
                createInnerCityInfoChangedNotifier(
                    CHANGE_INNER_CITY,
                    b.cityType,
                    b.id,
                    (b.startTime / 1000).toInt(),
                    (b.completeTime / 1000).toInt(),
                    b.state, b.positionIndex,
                    b.lv, b.helpId
                ).notice(session)

                // 用了GM还想完成任务
                val buildingLvs = LinkedList<Int>()
                innerCityDC.findEffectiveInnerBuildingsByType(b.cityType).forEach { buildingLvs.add(it.lv) }

                val askMsg = UpdateInfoByHomeAskReq.newBuilder()
                val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
                updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_BUILD_INFO
                updateInfoByHomeVo.updateValue = toJson(UpdateInfoByHomeBuildInfoVo(b.cityType, buildingLvs))
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

            fireEvent(session, RefreshBuildEffectEvent(targetHelper, effectHelper))
            fireEvent(session, GmRefTaskEvent())
        }
    }
}