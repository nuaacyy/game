package com.point18.slg2d.home.module.innerCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.removeAllianceHelp
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import pb4client.CancelUpInnerCity
import pb4client.CancelUpInnerCityRt
import java.util.Arrays.asList

// 取消升级内城建筑
class CancelUpgradeInnerCityDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, InnerCityDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC ->
            val cityId = (msg as CancelUpInnerCity).cityId
            val innerCityId = msg.innerCityId
            val cancelUpInnerCityRt = cancelUpgradeInnerCity(session,
                homePlayerDC, innerCityDC, session, cityId, innerCityId
            )
            session.sendMsg(MsgType.CancelUpInnerCity_53, cancelUpInnerCityRt)
        }
    }

    private fun cancelUpgradeInnerCity(
        session: PlayerActor,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC,
        playerActor: PlayerActor,
        castleId: Long,
        innerCityId: Long
    ): CancelUpInnerCityRt {
        val rt = CancelUpInnerCityRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val playerId = innerCityDC.playerId

        val innerCity = innerCityDC.findInnerCityFromId(innerCityId)
        if (innerCity == null) {
            // 没有该建筑
            rt.rt = ResultCode.INNER_CITY_NOT_FIND_BUILDING.code
            return rt.build()
        }

        if (innerCity.state != UPGRADE) {
            // 状态不对
            rt.rt = ResultCode.INNER_CITY_STATE_ERROR.code
            return rt.build()
        }

        val buildingDataProto = pcs.innerBuildingDataCache.fetchProtoByTypeLv(innerCity.cityType, innerCity.lv + 1)
        if (buildingDataProto == null) {
            // 该建筑模板不存在
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val player = homePlayerDC.player
        // 加奖励
        resHelper.addRes(playerActor, ACTION_CANCEL_UP_INNER_CITY_BUILDING, player, buildingDataProto.cancelRes)


        if (innerCity.helpId != 0L) {
            // 如果在帮助列表中的,要做处理
            removeAllianceHelp(session, player.allianceId, innerCity.helpId)
        }
        innerCity.helperIdMap = hashMapOf()
        innerCity.helpId = 0

        if (innerCity.lv == 0) {
            innerCityDC.updateInnerCityUpgradeState2World(session, innerCity, STABLE, 0, 0)
            innerCityDC.deleteInnerCity(innerCity)

            createInnerCityInfoChangedNotifier(
                DELETE_INNER_CITY, innerCity.cityType, innerCity.id,
                com.point18.slg2d.common.commonfunc.getTimeSec(innerCity.startTime), com.point18.slg2d.common.commonfunc.getTimeSec(innerCity.completeTime),
                innerCity.state, innerCity.positionIndex,
                innerCity.lv, innerCity.helpId
            ).notice(playerActor)

        } else {
            innerCityDC.updateInnerCityUpgradeState2World(session, innerCity, STABLE, 0, 0)

            createInnerCityInfoChangedNotifier(
                CHANGE_INNER_CITY,
                innerCity.cityType,
                innerCity.id,
                (innerCity.startTime / 1000).toInt(),
                (innerCity.completeTime / 1000).toInt(),
                innerCity.state, innerCity.positionIndex,
                innerCity.lv, innerCity.helpId
            ).notice(playerActor)
        }

        return rt.build()
    }

}