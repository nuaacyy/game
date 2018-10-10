package com.point18.slg2d.home.module.innerCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.InnerCityHelper
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.BuildingUpFinishEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import pb4client.UnlockInnerCity
import pb4client.UnlockInnerCityRt
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.*
import java.util.Arrays.asList

// 建造建筑
class UnlockInnerCityDeal(
    private val innerCityHelper: InnerCityHelper = InnerCityHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val refreshResHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, InnerCityDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java,
    asList(innerCityHelper, targetHelper, refreshResHelper, effectHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC ->
            val cityId = (msg as UnlockInnerCity).cityId
            val innerCityId = msg.innerCityId
            val unlockInnerCityRt = unlockInnerCity(
                session, cityId, innerCityId, homePlayerDC, innerCityDC
            )
            session.sendMsg(MsgType.UnlockInnerCity_51, unlockInnerCityRt)
        }
    }

    private fun unlockInnerCity(
        session: PlayerActor,
        castleId: Long,
        innerCityId: Long,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC
    ): UnlockInnerCityRt {

        val rt = UnlockInnerCityRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val innerCity = innerCityDC.findInnerCityFromId(innerCityId)
        if (innerCity == null) {
            // 没有该建筑
            rt.rt = ResultCode.INNER_CITY_NOT_FIND_BUILDING.code
            return rt.build()
        }

        if (innerCity.cityType == Lianjinzhe) {
            // 炼金所只能通过打遗迹解锁
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (innerCity.state != LOCK) {
            // 状态不对
            rt.rt = ResultCode.INNER_CITY_STATE_ERROR.code
            return rt.build()
        }

        val result = innerCityHelper.canUpInnerCity(session, homePlayerDC.player.focusCastleId, innerCity.cityType, 1)
        if (result != ResultCode.SUCCESS) {
            // 条件不满足
            rt.rt = result.code
            return rt.build()
        }

        innerCity.state = STABLE
        innerCity.lv = 1

        createInnerCityInfoChangedNotifier(
            CHANGE_INNER_CITY,
            innerCity.cityType,
            innerCity.id,
            (innerCity.startTime / 1000).toInt(),
            (innerCity.completeTime / 1000).toInt(),
            innerCity.state,
            innerCity.positionIndex,
            innerCity.lv,
            innerCity.helpId
        ).notice(session)

        // 触发建筑升级完成事件
        fireEvent(
            session,
            BuildingUpFinishEvent(
                innerCity.cityType,
                innerCity.lv,
                innerCity.id,
                targetHelper,
                refreshResHelper,
                effectHelper,
                innerCity.cityId
            )
        )

        val buildingLvs = LinkedList<Int>()
        innerCityDC.findEffectiveInnerBuildingsByType(innerCity.cityType).forEach { buildingLvs.add(it.lv) }
        val askMsg = UpdateInfoByHomeAskReq.newBuilder()
        val updateInfoByHomeVo  = pb4server.UpdateInfoByHomeVo.newBuilder()
        updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_BUILD_INFO
        updateInfoByHomeVo.updateValue = toJson(UpdateInfoByHomeBuildInfoVo(innerCity.cityType, buildingLvs))
        askMsg.addUpdates(updateInfoByHomeVo)

        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askRt, askErr ->
            try {
                when {
                    askErr != null -> {
                        // TODO
                    }

                    askRt == null -> {
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

        return rt.build()
    }
}
