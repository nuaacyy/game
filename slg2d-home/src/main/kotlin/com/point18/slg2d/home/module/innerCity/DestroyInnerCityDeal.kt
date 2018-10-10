package com.point18.slg2d.home.module.innerCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.RefreshBuildEffectEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import pb4client.DestroyInnerCity
import pb4client.DestroyInnerCityRt
import java.util.*
import java.util.Arrays.asList

// 拆除内城建筑
class DestroyInnerCityDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val innerCityHelper: InnerCityHelper = InnerCityHelper(),
    private val targetHelper: TargetHelper = TargetHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, InnerCityDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java,
    asList(resHelper, effectHelper, innerCityHelper, targetHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC ->
            val cityId = (msg as DestroyInnerCity).cityId
            val innerCityId = msg.innerCityId
            val destroyType = msg.destoryType
            val destroyInnerCityRt = destroyInnerCityDealInnerCity(
                session, cityId, innerCityId, destroyType,
                innerCityDC, homePlayerDC
            )
            session.sendMsg(MsgType.DestroyInnerCity_54, destroyInnerCityRt)
        }
    }

    private fun destroyInnerCityDealInnerCity(
        session: PlayerActor,
        castleId: Long,
        innerCityId: Long,
        destroyType: Int,
        innerCityDC: InnerCityDC,
        homePlayerDC: HomePlayerDC
    ): (DestroyInnerCityRt) {
        val rt = DestroyInnerCityRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        if (destroyType != DESTROY_INNER_CITY_NOW && destroyType != DESTROY_INNER_CITY_NORMAL) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val innerCityQueueCount = innerCityHelper.getInnerCityQueueCount(session, castleId)
        if (innerCityQueueCount >= MAX_INNER_CITY_QUEUE_COUNT && destroyType == DESTROY_INNER_CITY_NORMAL) {
            // 建筑队列已满
            rt.rt = ResultCode.INNER_CITY_QUEUE_ERROR.code
            return rt.build()
        }

        val innerCity = innerCityDC.findInnerCityFromId(innerCityId)
        if (innerCity == null) {
            // 没有该建筑
            rt.rt = ResultCode.INNER_CITY_NOT_FIND_BUILDING.code
            return rt.build()
        }

        // 建筑模板
        val buildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[innerCity.cityType]
        if (buildingProto == null) {
            // 建筑配置没找到
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        if (buildingProto.bornType != BORN_NEED) {
            // 该建筑不是可拆除建筑
            rt.rt = ResultCode.INNER_CITY_CAN_NOT_DESTROY.code
            return rt.build()
        }

        if (innerCity.state != STABLE) {
            // 状态不对
            rt.rt = ResultCode.INNER_CITY_STATE_ERROR.code
            return rt.build()
        }

        val buildingDataProto = pcs.innerBuildingDataCache.fetchProtoByTypeLv(innerCity.cityType, innerCity.lv)
        if (buildingDataProto == null) {
            // 该建筑模板不存在
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val player = homePlayerDC.player

        val nowTime = getNowTime()
        if (destroyType == DESTROY_INNER_CITY_NORMAL) {
            val addNum = effectHelper.getResearchEffectValue(
                session,
                NO_FILTER,
                ResearchEffectQuickBuildingAdd
            )
            var destroySec = (buildingDataProto.destoryTime / (1 + (addNum / 10000.0))).toInt()
            if (destroySec < pcs.basicProtoCache.destoryBuildingMinTime) {
                destroySec = pcs.basicProtoCache.destoryBuildingMinTime
            }

            val destroyTime = nowTime + com.point18.slg2d.common.commonfunc.sec2MilliSec(destroySec)
            innerCityDC.updateInnerCityDestroyState(innerCity, DESTROY, destroyTime, nowTime)

            createInnerCityInfoChangedNotifier(
                CHANGE_INNER_CITY,
                innerCity.cityType,
                innerCity.id,
                com.point18.slg2d.common.commonfunc.getTimeSec(innerCity.startTime),
                com.point18.slg2d.common.commonfunc.getTimeSec(innerCity.destroyTime),
                innerCity.state, innerCity.positionIndex,
                innerCity.lv, innerCity.helpId
            ).notice(session)

            //创建心跳
            forwardHeartDeal2World(session, CreateHeart, InnerCityDestroy, innerCity.id, innerCity.destroyTime)
        } else {
            //花道具直接拆除
            val costProps = pcs.basicProtoCache.destoryBuildingProps
            val checkPropRst = resHelper.checkRes(session, costProps)
            if (checkPropRst) {
                //道具足够，直接扣除
                resHelper.costRes(session, ACTION_DESTROY_INNER_CITY_BUILDING, player, costProps)
            } else {
                //需要的道具转换成钻石
                val totalCostRes = LinkedList<ResVo>()
                for (prop in costProps) {
                    val (rst, costRes) = com.point18.slg2d.common.pc.props2GoldCost(prop)
                    if (rst == ResultCode.SUCCESS && !costRes.isEmpty()) {
                        totalCostRes += costRes
                    }
                }
                val checkResRst = resHelper.checkRes(session, totalCostRes)
                if (!checkResRst) {
                    rt.rt = ResultCode.LESS_RESOUCE.code
                    return rt.build()
                }
                resHelper.costRes(session, ACTION_DESTROY_INNER_CITY_BUILDING, player, totalCostRes)
            }

            innerCityDC.deleteInnerCity(innerCity)

            createInnerCityInfoChangedNotifier(
                DELETE_INNER_CITY,
                innerCity.cityType,
                innerCity.id,
                com.point18.slg2d.common.commonfunc.getTimeSec(innerCity.startTime),
                com.point18.slg2d.common.commonfunc.getTimeSec(innerCity.destroyTime),
                innerCity.state, innerCity.positionIndex,
                innerCity.lv, innerCity.helpId
            ).notice(session)

            fireEvent(session, RefreshBuildEffectEvent(targetHelper, effectHelper))
        }

        return rt.build()
    }

}
