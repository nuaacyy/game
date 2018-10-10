package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus6
import com.point18.slg2d.home.module.event.BuildingUpFinishEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import pb4server.FriendRefreshNoticeTell
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import pb4server.UpdateInfoByHomeVo
import java.util.*
import java.util.Arrays.asList

class InnerCityHelper(
    private val targetHelper: TargetHelper = TargetHelper(),
    private val refreshResHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val appNoticeHelper: AppNoticeHelper = AppNoticeHelper()
) : HomeHelperPlus6<HomePlayerDC, HomeSyncDC, InnerCityDC, VipDC, SkinDC, FriendDC>(
    HomePlayerDC::class.java, HomeSyncDC::class.java, InnerCityDC::class.java,
    VipDC::class.java, SkinDC::class.java, FriendDC::class.java,
    asList(targetHelper, refreshResHelper, effectHelper, appNoticeHelper)
) {

    // 获取内城建筑队列
    fun getInnerCityQueueCount(
        session: PlayerActor,
        castleId: Long
    ): Int {
        return prepare(session) { homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC, innerCityDC: InnerCityDC, vipDC: VipDC, skinDC: SkinDC,
                                  friendDC: FriendDC ->
            val allBuilding = innerCityDC.findInnerCityListFromCastleId(castleId)
            // 尝试从缓存中获取
            var count = 0
            for (build in allBuilding) {
                if (build.state == UPGRADE || build.state == DESTROY) {
                    count++
                }
            }

            return@prepare count
        }
    }

    // 判断内城建筑解锁条件
    fun canUpInnerCity(
        session: PlayerActor,
        castleId: Long,
        innerCityType: Int,
        lv: Int
    ): ResultCode {
        return prepare(session) { homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC, innerCityDC: InnerCityDC, vipDC: VipDC, skinDC: SkinDC,
                                  friendDC: FriendDC ->
            val buildingDataProto = pcs.innerBuildingDataCache.fetchProtoByTypeLv(innerCityType, lv)
            if (buildingDataProto == null) {
                return@prepare ResultCode.NO_PROTO
            }

            return@prepare unlockCondition(innerCityDC, session, castleId, buildingDataProto.unLockMap)
        }
    }

    // 判断内城建筑区域解锁条件
    fun canUnlockInnerCityArea(
        session: PlayerActor,
        castleId: Long,
        area: Int
    ): (ResultCode) {
        return prepare(session) { homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC, innerCityDC: InnerCityDC, vipDC: VipDC, skinDC: SkinDC,
                                  friendDC: FriendDC ->
            val buildingAreaProto = pcs.innerBuildingAreaCache.protoMap[area]
            if (buildingAreaProto == null) {
                return@prepare ResultCode.NO_PROTO
            }

            return@prepare unlockCondition(innerCityDC, session, castleId, buildingAreaProto.unLockMap)
        }
    }

    private fun unlockCondition(
        innerCityDC: InnerCityDC,
        session: PlayerActor,
        castleId: Long,
        unlockMap: Map<Int, Map<Int, Int>>
    ): ResultCode {
        for ((unlockType, subMap) in unlockMap) {
            if (unlockType == UNLOCK_BY_BUILDING_LV) {
                for ((type, level) in subMap) {
                    val innerCityInfoList =
                        innerCityDC.findInnerCityListFromCastleIdAndType(castleId, type)

                    var canUnlock = false
                    for (innerCityInfo in innerCityInfoList) {
                        if (innerCityInfo.lv >= level) {
                            canUnlock = true
                            break
                        }
                    }

                    if (!canUnlock) {
                        return ResultCode.INNER_CITY_LOCK
                    }
                }
            }
        }
        return ResultCode.SUCCESS
    }

    // 处理建筑升级
    fun buildLvUp(session: PlayerActor, innerCityInfo: InnerCity) {
        return prepare(session) { homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC, innerCityDC: InnerCityDC, vipDC: VipDC, skinDC: SkinDC,
                                  friendDC: FriendDC ->

            val player = homePlayerDC.player
            innerCityInfo.lv += 1
            if (innerCityInfo.helpId != 0L) {
                // 如果在帮助列表中的,要做处理
                removeAllianceHelp(session, player.allianceId, innerCityInfo.helpId)
            }
            innerCityInfo.helperIdMap = hashMapOf()
            innerCityInfo.helpId = 0

            createInnerCityInfoChangedNotifier(
                CHANGE_INNER_CITY,
                innerCityInfo.cityType,
                innerCityInfo.id,
                (innerCityInfo.startTime / 1000).toInt(),
                (innerCityInfo.completeTime / 1000).toInt(),
                innerCityInfo.state, innerCityInfo.positionIndex,
                innerCityInfo.lv, innerCityInfo.helpId
            ).notice(session)

            // 触发建筑升级完成事件
            fireEvent(
                session,
                BuildingUpFinishEvent(
                    innerCityInfo.cityType,
                    innerCityInfo.lv,
                    innerCityInfo.id,
                    targetHelper,
                    refreshResHelper,
                    effectHelper,
                    innerCityInfo.cityId
                )
            )

            // 应用通知推送
            val buildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[innerCityInfo.cityType]
            if (buildingProto != null) {
                appNoticeHelper.pushAppNotice(
                    session,
                    BUILDING_UP_SETTING,
                    0,
                    buildingProto.name
                )
            }

            if (innerCityInfo.cityType == MainBuilding) {
                val vipLv = vipDC.getVipLv()
                var skinType = 1
                val skins = skinDC.findSkinsByPlayerId()
                for (skin in skins) {
                    if (skin.isUse == 1) {
                        skinType = skin.skinType
                        break
                    }
                }
                val tellHomeMsg = FriendRefreshNoticeTell.newBuilder()
                tellHomeMsg.myPlayerId = player.playerId
                tellHomeMsg.photoProtoId = player.photoProtoId
                tellHomeMsg.name = player.name
                tellHomeMsg.areaNo = player.areaNo
                tellHomeMsg.vipLv = vipLv
                tellHomeMsg.allianceShortName = player.allianceShortName
                tellHomeMsg.state = 0
                tellHomeMsg.castleLv = innerCityInfo.lv
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
            innerCityDC.findEffectiveInnerBuildingsByType(innerCityInfo.cityType).forEach { buildingLvs += it.lv }
            val askMsg = UpdateInfoByHomeAskReq.newBuilder()
            val updateInfoByHomeVo = UpdateInfoByHomeVo.newBuilder()
            updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_BUILD_INFO
            updateInfoByHomeVo.updateValue = toJson(UpdateInfoByHomeBuildInfoVo(innerCityInfo.cityType, buildingLvs))
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
    }
}

fun dealCastleInnerCityFinish(
        player : HomePlayer,
    innerCityDC: InnerCityDC,
    castleId: Long,
    playerId: Long
) {

    // 内城
    for (vo in pcs.innerBuildingLocationCache.protoList) {

        if (vo.interfaceTypeList.size != 1) {
            continue
        }

        val buildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[vo.interfaceTypeList[0]]
        if (buildingProto == null) {
            continue
        }

        if (buildingProto.bornType != BORN_HAVE && buildingProto.bornType != BORN_LOCK) {
            continue
        }

        if (buildingProto.bornType == BORN_HAVE) {
            innerCityDC.createInnerCity(
                playerId,
                castleId,
                vo.buildType,
                buildingProto.buildType,
                1,
                0,
                0,
                STABLE
            )
        } else if (buildingProto.bornType == BORN_LOCK) {
            innerCityDC.createInnerCity(
                playerId,
                castleId,
                vo.buildType,
                buildingProto.buildType,
                0,
                0,
                0,
                LOCK
            )
        } else {
            return
        }
    }

    // 区域
    for (vo in pcs.innerBuildingAreaCache.protoMap) {

        if (vo.value.unLock != "0") {
            continue
        }

        player.innerBuildingUnlockAreaMap[vo.value.area] = 1
    }
}
