package com.point18.slg2d.home.module.innerCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.sec2MilliSec
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus6
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.BuildingUpFinishEvent
import com.point18.slg2d.home.module.event.ClearTimeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import pb4client.UpInnerCity
import pb4client.UpInnerCityRt
import pb4server.FriendRefreshNoticeTell
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.*
import java.util.Arrays.asList

// 升级内城建筑
class UpgradeInnerCityDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val innerCityHelper: InnerCityHelper = InnerCityHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val refreshResHelper: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus6<HomePlayerDC, InnerCityDC, HomeMyTargetDC, VipDC, SkinDC, FriendDC>(
        HomePlayerDC::class.java, InnerCityDC::class.java, HomeMyTargetDC::class.java,
        VipDC::class.java, SkinDC::class.java, FriendDC::class.java,
        asList(resHelper, innerCityHelper, effectHelper, targetHelper, refreshResHelper)
    ) {


    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC, homeMyTargetDC: HomeMyTargetDC,
                           vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC ->
            val cityId = (msg as UpInnerCity).cityId
            val innerCityId = msg.innerCityId
            val lvUpType = msg.lvUpType

            val upInnerCityRt = upgradeInnerCity(
                session, cityId, innerCityId, lvUpType,
                homePlayerDC, innerCityDC, homeMyTargetDC,
                vipDC, skinDC, friendDC
            )

            session.sendMsg(MsgType.UpInnerCity_52, upInnerCityRt)
        }
    }

    private fun upgradeInnerCity(
        session: PlayerActor, castleId: Long, innerCityId: Long, lvUpType: Int,
        homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC, homeMyTargetDC: HomeMyTargetDC,
        vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC
    ): UpInnerCityRt {

        val rt = UpInnerCityRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val playerId = homePlayerDC.playerId

        if (lvUpType != RESEARCH_LVUP_NORMAL && lvUpType != RESEARCH_LVUP_RMB) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val innerCityQueueCount = innerCityHelper.getInnerCityQueueCount(session, castleId)
        if (innerCityQueueCount >= MAX_INNER_CITY_QUEUE_COUNT) {
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

        if (innerCity.state != STABLE) {
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

        val result = innerCityHelper.canUpInnerCity(session, castleId, innerCity.cityType, innerCity.lv + 1)
        if (result != ResultCode.SUCCESS) {
            // 条件不满足
            rt.rt = result.code
            return rt.build()
        }

        val player = homePlayerDC.player

        val nowTime = getNowTime()

        val addNum = effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            ResearchEffectQuickBuildingAdd
        )
        val completeTime =
            (nowTime + sec2MilliSec(buildingDataProto.levelUpTime) / (1 + (addNum / 10000.0))).toLong()

        if (lvUpType == RESEARCH_LVUP_NORMAL) {
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

            innerCityDC.updateInnerCityUpgradeState(innerCity, UPGRADE, completeTime, nowTime)

            if (innerCity.helpId != 0L) {
                // 如果在帮助列表中的,要做处理
                removeAllianceHelp(session, player.allianceId, innerCity.helpId)
            }
            innerCity.helperIdMap = hashMapOf()
            innerCity.helpId = 0

            //创建心跳
            forwardHeartDeal2World(session, CreateHeart, InnerCityUpgrade, innerCity.id, completeTime)
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

            innerCity.lv += 1
            innerCityDC.updateInnerCityUpgradeState(innerCity, STABLE, 0, 0)

            // 触发建筑升级完成事件
            fireEvent(
                session, BuildingUpFinishEvent(
                innerCity.cityType,
                innerCity.lv,
                innerCity.id,
                targetHelper,
                refreshResHelper,
                effectHelper,
                innerCity.cityId
            ))
            // tell home 向好友推送
            val vipLv = vipDC.getVipLv()
            var skinType = 1
            val skins = skinDC.findSkinsByPlayerId()
            for (skin in skins) {
                if (skin.isUse == 1) {
                    skinType = skin.skinType
                    break
                }
            }
            if (innerCity.cityType == MainBuilding) {
                val tellHomeMsg = FriendRefreshNoticeTell.newBuilder()
                tellHomeMsg.myPlayerId = player.playerId
                tellHomeMsg.photoProtoId = player.photoProtoId
                tellHomeMsg.name = player.name
                tellHomeMsg.areaNo = player.areaNo
                tellHomeMsg.vipLv = vipLv
                tellHomeMsg.allianceShortName = player.allianceShortName
                tellHomeMsg.state = 0
                tellHomeMsg.castleLv = innerCity.lv
                tellHomeMsg.skinType = skinType
                tellHomeMsg.shortName = player.allianceNickName
                val myFriends = friendDC.findFriendById()

                for (friend in myFriends) {
                    val home2homeTell = session.fillHome2HomeTellMsgHeader(
                        friend.tarPlayerId
                    ) { it.setFriendRefreshNoticeTell(tellHomeMsg) }
                    session.tellHome(home2homeTell)
                }
            }

            val buildingLvs = LinkedList<Int>()
            innerCityDC.findEffectiveInnerBuildingsByType(innerCity.cityType).forEach { buildingLvs.add(it.lv) }
            val askMsg = UpdateInfoByHomeAskReq.newBuilder()
            val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
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
        }

        createInnerCityInfoChangedNotifier(
            CHANGE_INNER_CITY,
            innerCity.cityType,
            innerCity.id,
            (innerCity.startTime / 1000).toInt(),
            (innerCity.completeTime / 1000).toInt(),
            innerCity.state, innerCity.positionIndex,
            innerCity.lv, innerCity.helpId
        ).notice(session)

        return rt.build()
    }

}