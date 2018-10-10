package com.point18.slg2d.home.module.innerCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.BuildingUpFinishEvent
import com.point18.slg2d.home.module.event.ClearTimeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import pb4client.CreateInnerCity
import pb4client.CreateInnerCityRt
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.*
import java.util.Arrays.asList

// 建造建筑
class CreateInnerCityDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val innerCityHelper: InnerCityHelper = InnerCityHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val refreshResHelper: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, InnerCityDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper, effectHelper, innerCityHelper, targetHelper, refreshResHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC, homeMyTargetDC: HomeMyTargetDC ->
            val cityId = (msg as CreateInnerCity).cityId
            val positionIndex = msg.positionIndex
            val innerCityType = msg.innerCityType
            val createType = msg.createType
            val createInnerCityRt = createInnerCity(
                session, cityId, positionIndex,
                innerCityType, createType,
                innerCityDC, homePlayerDC, homeMyTargetDC
            )
            session.sendMsg(MsgType.CreateInnerCity_50, createInnerCityRt)
        }

    }

    private fun createInnerCity(
        session: PlayerActor,
        castleId: Long,
        positionIndex: Int,
        innerCityType: Int,
        createType: Int,
        innerCityDC: InnerCityDC,
        homePlayerDC: HomePlayerDC,
        homeMyTargetDC: HomeMyTargetDC
    ): CreateInnerCityRt {

        val rt = CreateInnerCityRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val playerId = innerCityDC.playerId

        if (createType != RESEARCH_LVUP_NORMAL && createType != RESEARCH_LVUP_RMB) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val innerCityQueueCount = innerCityHelper.getInnerCityQueueCount(session, castleId)
        if (innerCityQueueCount >= MAX_INNER_CITY_QUEUE_COUNT) {
            // 建筑队列已满
            rt.rt = ResultCode.INNER_CITY_QUEUE_ERROR.code
            return rt.build()
        }

        val innerCity = innerCityDC.findInnerCityFromPositionIndex(castleId, positionIndex)
        if (innerCity != null) {
            // 坑位上已经有建筑了
            rt.rt = ResultCode.INNER_CITY_EXIST_BUILDING.code
            return rt.build()
        }

        val buildingLocationProto = pcs.innerBuildingLocationCache.protoMap[positionIndex]
        if (buildingLocationProto == null) {
            // 没找到坑位
            rt.rt = ResultCode.INNER_CITY_NOT_FIND_POSITION_INDEX.code
            return rt.build()
        }

        val player = homePlayerDC.player

        val unlockArea = player.innerBuildingUnlockAreaMap
        val err = unlockArea[buildingLocationProto.area]
        if (err == null) {
            // 区域未解锁
            rt.rt = ResultCode.INNER_CITY_AREA_LOCK.code
            return rt.build()
        }

        var canBuild = false
        for (vo in buildingLocationProto.interfaceTypeList) {
            if (vo == innerCityType) {
                canBuild = true
                break
            }
        }

        if (!canBuild) {
            // 坑位上不能造
            rt.rt = ResultCode.INNER_CITY_CAN_NOT_BUILD.code
            return rt.build()
        }

        // 建造建筑
        val buildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[innerCityType]
        if (buildingProto == null) {
            // 建筑配置没找到
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        if (buildingProto.bornType != BORN_NEED) {
            // 该建筑不是可造建筑
            rt.rt = ResultCode.INNER_CITY_CAN_NOT_BUILD.code
            return rt.build()
        }

        val buildingDataProto = pcs.innerBuildingDataCache.fetchProtoByTypeLv(innerCityType, 1)
        if (buildingDataProto == null) {
            // 该建筑模板不存在
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val result = innerCityHelper.canUpInnerCity(session, castleId, buildingProto.buildType, 1)
        if (result != ResultCode.SUCCESS) {
            // 条件不满足
            rt.rt = result.code
            return rt.build()
        }

        val nowTime = getNowTime()
        val addNum = effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            ResearchEffectQuickBuildingAdd
        )
        val completeTime = (nowTime + ((buildingDataProto.levelUpTime / (1.toDouble() + (50.toDouble() / 10000.toDouble()))).toLong() * 1000))

        if (createType == RESEARCH_LVUP_NORMAL) {
            // 资源检测
            val checkCost = resHelper.checkRes(session, buildingDataProto.levelUpConsumeRes)
            if (!checkCost) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            // 扣除消耗
            resHelper.costRes(
                session,
                ACTION_CREATE_INNER_CITY_BUILDING,
                player,
                buildingDataProto.levelUpConsumeRes
            )
            val innerCityInfo = innerCityDC.createInnerCity(
                playerId,
                castleId,
                positionIndex,
                innerCityType,
                0,
                nowTime,
                completeTime,
                UPGRADE
            )

            createInnerCityInfoChangedNotifier(
                BUILDING_INNER_CITY,
                innerCityInfo.cityType, innerCityInfo.id,
                (innerCityInfo.startTime / 1000).toInt(),
                (innerCityInfo.completeTime / 1000).toInt(),
                innerCityInfo.state, innerCityInfo.positionIndex,
                innerCityInfo.lv, innerCityInfo.helpId
            ).notice(session)

            forwardHeartDeal2World(session, CreateHeart, InnerCityUpgrade, innerCityInfo.id, completeTime)
        } else {
            val (isCheck, lockVos, haveVos) = resHelper.checkAndTellRes(
                session,
                player,
                buildingDataProto.levelUpConsumeRes
            )
            if (!isCheck) {
                rt.rt = ResultCode.RES_ERROR.code
                return rt.build()
            }

            // 补齐资源的模式
            var allCost = 0.0

            for (lockVo in lockVos) {
                if (lockVo.lackType == RES_BIND_GOLD) {
                    allCost += lockVo.lackNum
                } else {
                    val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_RES, lockVo.lackType, lockVo.lackNum)
                    allCost += cost
                }
            }

            val costs = LinkedList<ResVo>()

            for (haveVo in haveVos) {
                if (haveVo.extend1 == 0) {
                    costs += ResVo(haveVo.lackType, NOT_PROPS_SUB_TYPE, haveVo.lackNum)
                } else {
                    costs += ResVo(haveVo.lackType, haveVo.extend1.toLong(), haveVo.lackNum)
                }
            }

            costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(allCost).toLong())

            val lTime = (buildingDataProto.levelUpTime.toDouble() / (1 + addNum.toDouble() / 10000)).toLong()
            val bTime = (pcs.basicProtoCache.upBuildingFree + effectHelper.getResearchEffectValue(
                session,
                NO_FILTER,
                FreeBuildTime
            ))
            if (lTime > bTime) {
                val clearTimeCost = pcs.resShopCache.needCost(
                    RESSHOP_TYPE_CLEAN_TIME, 1, lTime - bTime
                )
                costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(clearTimeCost).toLong())

                val clearTime = lTime - bTime
                val c = homeMyTargetDC.targetInfo.clearTimeInfo[CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE]
                if (c == null) {
                    homeMyTargetDC.targetInfo.clearTimeInfo[CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE] = clearTime
                } else {
                    homeMyTargetDC.targetInfo.clearTimeInfo[CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE] = c + clearTime
                }

                fireEvent(session, ClearTimeEvent(CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE, clearTime.toInt()))

            }
            // 资源检测
            val checkCost = resHelper.checkRes(session, costs)
            if (!checkCost) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            // 扣除消耗
            resHelper.costRes(session, ACTION_CLEAR_TIME_INNER_CITY_BUILDING, player, costs)
            val innerCityInfo = innerCityDC.createInnerCity(
                playerId, castleId, positionIndex, innerCityType, 1,
                0, 0, STABLE
            )

            createInnerCityInfoChangedNotifier(
                BUILDING_INNER_CITY,
                innerCityInfo.cityType,
                innerCityInfo.id,
                (innerCityInfo.startTime / 1000).toInt(),
                (innerCityInfo.completeTime / 1000).toInt(),
                innerCityInfo.state, innerCityInfo.positionIndex,
                innerCityInfo.lv, innerCityInfo.helpId
            ).notice(session)

            // 触发建筑升级完成事件
            fireEvent(
                session, BuildingUpFinishEvent(
                innerCityInfo.cityType,
                innerCityInfo.lv,
                innerCityInfo.id,
                targetHelper,
                refreshResHelper,
                effectHelper,
                innerCityInfo.cityId
            ))

            val buildingLvs = LinkedList<Int>()
            innerCityDC.findEffectiveInnerBuildingsByType(innerCityInfo.cityType).forEach { buildingLvs.add(it.lv) }

            val askMsg = UpdateInfoByHomeAskReq.newBuilder()
            val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
            updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_BUILD_INFO
            updateInfoByHomeVo.updateValue = toJson(UpdateInfoByHomeBuildInfoVo(innerCityInfo.cityType, buildingLvs))
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
        }

        return rt.build()
    }

}
