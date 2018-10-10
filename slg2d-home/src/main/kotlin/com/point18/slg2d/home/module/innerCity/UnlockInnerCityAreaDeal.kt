package com.point18.slg2d.home.module.innerCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.InnerCityHelper
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.UnlockInnerCityArea
import pb4client.UnlockInnerCityAreaRt
import java.util.Arrays.asList

// 解锁内城区域
class UnlockInnerCityAreaDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val innerCityHelper: InnerCityHelper = InnerCityHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java, asList(resHelper, innerCityHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC ->
            val cityId = (msg as UnlockInnerCityArea).cityId
            val areaId = msg.areaId
            val unlockInnerCityAreaRt = unlockInnerCityArea(
                session,
                cityId,
                areaId,
                homePlayerDC
            )

            session.sendMsg(MsgType.UnlockInnerCityArea_57, unlockInnerCityAreaRt)
        }
    }

    private fun unlockInnerCityArea(
        session: PlayerActor,
        castleId: Long,
        areaId: Int,
        homePlayerDC: HomePlayerDC
    ): UnlockInnerCityAreaRt {

        val rt = UnlockInnerCityAreaRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.areaId = areaId

        val player = homePlayerDC.player

        val unlockArea = player.innerBuildingUnlockAreaMap
        val err = unlockArea[areaId]
        if (err != null) {
            // 区域已解锁
            rt.rt = ResultCode.INNER_CITY_AREA_UnlOCK.code
            return rt.build()
        }

        val result = innerCityHelper.canUnlockInnerCityArea(session, castleId, areaId)
        if (result != ResultCode.SUCCESS) {
            rt.rt = result.code
            return rt.build()
        }

        val buildingAreaProto = pcs.innerBuildingAreaCache.protoMap[areaId]
        if (buildingAreaProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        // 资源检测
        val checkCost = resHelper.checkRes(session, buildingAreaProto.unLockResVos)
        if (!checkCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        // 扣除消耗
        resHelper.costRes(session, com.point18.slg2d.common.constg.ACTION_CREATE_INNER_CITY_BUILDING, player, buildingAreaProto.unLockResVos)

        player.innerBuildingUnlockAreaMap[areaId] = 1

        return rt.build()
    }

}
