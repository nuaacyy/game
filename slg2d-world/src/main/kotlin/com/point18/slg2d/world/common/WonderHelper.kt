package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.OfficeProto
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.event.CountryPosChange
import com.point18.slg2d.world.module.walk.doCancelMass
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import com.point18.slg2d.world.msgnotice.createAmnestyCountChangeNotifier
import com.point18.slg2d.world.msgnotice.createLanMsgNotifier
import com.point18.slg2d.world.msgnotice.createOfficeChangeNotifier
import com.point18.slg2d.world.msgnotice.createWonderForceChangeNotifier
import com.point18.slg2d.world.wpm
import java.util.*
import java.util.Arrays.asList

// 获取需要防守的时间
fun getDefTime(warTimeMap: Map<Int, Int>, haveFightTime: Int): Int {
    var needDefTime = -1
    warTimeMap.forEach { a ->
        if (haveFightTime < a.key) {
            return@forEach
        }
        if (needDefTime == -1 || needDefTime > a.value) {
            needDefTime = a.value
        }
    }
    if (needDefTime == -1) {
        needDefTime = 0
    }
    return needDefTime
}

// 指挥官的部队不包含在部队列表中
data class WonderReinforce(
    var commandGroup: WalkForceGroup,
    val reinforceGroups: LinkedList<WalkForceGroup>
)

// 查找所有奇观的增援
fun findAllWonderReinforce(areaCache: AreaCache, wonderId: Int): WonderReinforce? {
    // 从缓存中获取奇观
    val wonder = areaCache.wonderCache.findWonder(wonderId)
    if (wonder == null || wonder.occupyGroupId == 0L) {
        return null
    }

    // 获取奇观模板，得到奇观中心坐标
    val wonderProto = pcs.wonderLocationProtoCache.wonderLocationProtoMap[wonderId]
    if (wonderProto == null) {
        //Assert
        return null
    }

    // 找到所有已经到达的增援
    val centerPos = wonderProto.getCenterPos()

    var commandGroup: WalkForceGroup? = null
    val reinforceGroupList = LinkedList<WalkForceGroup>()

    val reinforceGroups =
        areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(centerPos.x, centerPos.y, Reinforce)
    reinforceGroups.forEach { a ->
        if (a.id == wonder.occupyGroupId) {
            commandGroup = a
            return@forEach
        }
        reinforceGroupList.add(a)
    }

    commandGroup?.let { command ->
        val commandPlayer = areaCache.playerCache.findPlayerById(command.mainPlayerId)
        if (commandPlayer == null) {
            //Assert
            return null
        }

        // 找到行军中的增援
        val gotoWalks = areaCache.walkCache.findWalksByGotoXy(centerPos.x, centerPos.y)
        for (walk in gotoWalks) {
            val walkGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
            if (walkGroup == null) {
                //Assert
                continue
            }
            if (walkGroup.gotoRunType != WalkReinforceWonder) {
                continue
            }
            val reinforcePlayer = areaCache.playerCache.findPlayerById(walkGroup.mainPlayerId)
            if (reinforcePlayer == null) {
                //Assert
                continue
            }
            if (commandPlayer.allianceId == 0L || commandPlayer.allianceId != reinforcePlayer.allianceId) {
                continue
            }
            reinforceGroupList.add(walkGroup)
        }
        return WonderReinforce(command, reinforceGroupList)
    }
    return null
}

// 校验官职功能
fun checkOfficeFunction(OfficeFunction: OfficeFunction, posId: Int): Boolean {
    val officeProto = pcs.officeProtoCache.officeProtoMap[posId] ?: return false
    officeProto.functionList.forEach {
        if (it == OfficeFunction.v) {
            return true
        }
    }
    return false
}

// 通知奇观增援部队
fun noticeWonderReinforce(
    areaCache: AreaCache,
    changType: Int,
    groupId: Long,
    targetPlayerIds: List<Long>
) {
    val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(groupId)
    val notifier = createWonderForceChangeNotifier(changType, forceToMsgBuilder(areaCache, forces))
    for (playerId in targetPlayerIds) {
        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            notifier.notice(session)
        }
    }
}

// 查询官职授予的玩家
fun findPlayerIdByOffice(areaCache: AreaCache, officeId: Int): Long {
    val bigWonder = areaCache.wonderCache.findBigWonder()
    if (bigWonder == null) {
        return 0
    }

    bigWonder.officeMap.forEach {
        if (it.value == officeId) {
            return it.key
        }
    }
    return 0
}

// 查询玩家官职
fun findOfficeByPlayerId(areaCache: AreaCache, playerId: Long): Int {
    val bigWonder = areaCache.wonderCache.findBigWonder()
    if (bigWonder == null) {
        return 0
    }

    return bigWonder.officeMap[playerId] ?: return 0
}

// 移除官职
fun dismissOffice(areaCache: AreaCache, player: Player, wonder: Wonder) {
    val playerId = player.id

    wonder.officeMap.remove(playerId)

    // 同步官职到home
    syncOffice2Home(areaCache, playerId, 0)

    // 触发官职变化事件
    wpm.es.fire(areaCache, playerId, COUNTRY_POS_CHANGE,
        CountryPosChange(
            playerId, 0
        )
    )

    // 本服同盟玩家推送
    val allianceMembers = areaCache.playerCache.findPlayersByAllianceId(wonder.belongToAllianceId)
    val toNoticePlayerMap = hashMapOf<Long, Int>()
    for (member in allianceMembers) {
        toNoticePlayerMap[member.id] = 1
    }
    for ((id, _) in toNoticePlayerMap) {
        val toNoticeSession = fetchOnlinePlayerSession(areaCache, id)
        if (toNoticeSession != null) {
            val notifier = createOfficeChangeNotifier(playerId, 0)
            notifier.notice(toNoticeSession)
        }
    }

    // 移除官职的玩家不在联盟中的推送
    if (toNoticePlayerMap[playerId] == null) {
        val toNoticeSession = fetchOnlinePlayerSession(areaCache, playerId)
        if (toNoticeSession != null) {
            val notifier = createOfficeChangeNotifier(playerId, 0)
            notifier.notice(toNoticeSession)
        }
    }

    //更新地块
    val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
    if (castle != null) {
        noticeCellUpdate(areaCache, castle.x, castle.y)
    }
}

// 授予官职
fun grantOffice(areaCache: AreaCache, posId: Int, toPlayer: Player): Int {
    val wonder = areaCache.wonderCache.findBigWonder() ?: return ResultCode.WONDER_NOT_EXIST.code
    val beSetOfficeProto = pcs.officeProtoCache.officeProtoMap[posId] ?: return ResultCode.NO_PROTO.code

    val toPlayerId = toPlayer.id

    val oldPlayerId = findPlayerIdByOffice(areaCache, posId)
    val oldPlayer = areaCache.playerCache.findPlayerById(oldPlayerId)
    if (oldPlayer != null) {
        dismissOffice(areaCache, oldPlayer, wonder)

        // 给被撤职的玩家发送lan，谁获得了职位
        val oldSession = fetchOnlinePlayerSession(areaCache, oldPlayerId)
        if (oldSession != null) {
            val notifier = createLanMsgNotifier(
                NOTICE_OTHER_OFFICE,
                asList(toPlayer.name, beSetOfficeProto.name)
            )
            notifier.notice(oldSession)
        }
    }

    //设置官职
    wonder.officeMap[toPlayerId] = posId

    // 同步官职到home
    syncOffice2Home(areaCache, toPlayerId, posId)

    //添加统计
    targetAddVal(
        areaCache,
        toPlayerId,
        HoldCountryPos,
        asList(posId.toLong())
    )

    //触发官职变化事件
    wpm.es.fire(areaCache, toPlayerId, COUNTRY_POS_CHANGE,
        CountryPosChange(
            toPlayerId, posId
        )
    )

    // 本服同盟玩家推送
    val allianceMembers = areaCache.playerCache.findPlayersByAllianceId(wonder.belongToAllianceId)
    val toNoticePlayerMap = hashMapOf<Long, Int>()
    for (member in allianceMembers) {
        toNoticePlayerMap[member.id] = 1
    }
    for ((id, _) in toNoticePlayerMap) {
        val toNoticeSession = fetchOnlinePlayerSession(areaCache, id)
        if (toNoticeSession != null) {
            val notifier = createOfficeChangeNotifier(toPlayerId, posId)
            notifier.notice(toNoticeSession)
        }
    }

    // 授予官职的玩家不是本盟中的推送
    if (toNoticePlayerMap[toPlayerId] == null) {
        val toNoticeSession = fetchOnlinePlayerSession(areaCache, toPlayerId)
        if (toNoticeSession != null) {
            val notifier = createOfficeChangeNotifier(toPlayerId, posId)
            notifier.notice(toNoticeSession)
        }
    }

    // 发送给授予官职玩家Lan消息
    val toNoticeSession = fetchOnlinePlayerSession(areaCache, toPlayerId)
    if (toNoticeSession != null) {
        val notifier = createLanMsgNotifier(NOTICE_OFFICE, asList(beSetOfficeProto.name))
        notifier.notice(toNoticeSession)
    }

    // 更新地块
    val toCastle = areaCache.castleCache.findCastleById(toPlayer.focusCastleId)
    if (toCastle != null) {
        noticeCellUpdate(areaCache, toCastle.x, toCastle.y)
    }

    return ResultCode.SUCCESS.code
}

// 国王变化 —— 大奇观占有发生改变时
fun changeKing(areaCache: AreaCache, newAllianceId: Long) {
    val wonder = areaCache.wonderCache.findBigWonder() ?: return

    if (wonder.belongToAllianceId == newAllianceId) {
        return
    }

    val allianceLeader = areaCache.playerCache.findAllianceLeader(newAllianceId)
    if (allianceLeader == null) {
        return
    }

    // 获取国王官职模板
    var kingProto: OfficeProto? = null
    val officeProtoMap = pcs.officeProtoCache.officeProtoMapByType[KingOffice]
    if (officeProtoMap == null) {
        //Assert
        return
    }
    for ((_, proto) in officeProtoMap) {
        if (proto.subType == OneLvOffice) {
            kingProto = proto
            break
        }
    }
    if (kingProto == null) {
        return
    }

    //清除所有官职
    val tmp = LinkedList<Long>()
    for ((playerId, _) in wonder.officeMap) {
        tmp += playerId
    }
    for (playerId in tmp) {
        val player = areaCache.playerCache.findPlayerById(playerId)

        if (player != null) {
            dismissOffice(areaCache, player, wonder)
        }
    }

    //设置国王
    grantOffice(areaCache, kingProto.id, allianceLeader)
}

// 重置国王官职 —— 转让联盟盟主后重置
fun resetKing(areaCache: AreaCache, playerId: Long) {
    val newKing = areaCache.playerCache.findPlayerById(playerId)
    if (newKing == null) {
        return
    }

    // 获取国王官职模板
    var kingProto: OfficeProto? = null
    val posProtoMap = pcs.officeProtoCache.officeProtoMapByType[KingOffice]
    if (posProtoMap == null) {
        //Assert
        return
    }
    for ((_, proto) in posProtoMap) {
        if (proto.subType == OneLvOffice) {
            kingProto = proto
            break
        }
    }

    if (kingProto == null) {
        return
    }

    //设置国王
    grantOffice(areaCache, kingProto.id, newKing)
}

// 同步官职到home
fun syncOffice2Home(
    areaCache: AreaCache,
    playerId: Long,
    officeId: Int
) {
    val worldId = areaCache.areaConfig.pltAreaNo
    val officeInfo = hashMapOf(Pair(worldId, officeId))
    syncData2Home(areaCache, playerId, OfficeSync, toJson(officeInfo.toMap()))
}

// 判断是否在奇观争夺期间
fun isWonderWar(areaCache: AreaCache): Boolean {
    val nowTime = getNowTime()
    areaCache.wonderCache.wonderMap.map {
        return nowTime >= it.warStartTime && nowTime <= it.warFinishTime
    }
    return false // 数据库没有奇观信息
}

// 奇观是否和平
fun isWonderPeace(wonder: Wonder): Boolean {
    if (wonder.status == PEACE) {
        return true
    }
    return false
}

// 检测奇观时间区间
fun checkWonderWarTime(areaCache: AreaCache): Int {
    val bigWonder = areaCache.wonderCache.findBigWonder()
    if (bigWonder == null) {
        normalLog.error("找不到大奇观")
        return NOT_IN_WONDER_WAR
    }

    val nowTime = getNowTime()
    val protoCache = pcs.wonderLocationProtoCache
    val startProtectTime = protoCache.startWonderProtectTime()
    val finishProtectTime = protoCache.finishWonderProtectTime()

    return if (nowTime > bigWonder.warStartTime - startProtectTime && nowTime < bigWonder.warStartTime) {
        START_WONDER_WAR_PROTECT
    } else if (nowTime < bigWonder.warFinishTime + finishProtectTime && nowTime > bigWonder.warFinishTime) {
        FINISH_WONDER_WAR_PROTECT
    } else if (nowTime >= bigWonder.warStartTime && nowTime <= bigWonder.warFinishTime) {
        IN_WONDER_WAR
    } else {
        NOT_IN_WONDER_WAR
    }
}

// 更新奇观开始时间
fun updateWonderNextTime(wonder: Wonder) {
    /** 根据basic配置设置时间 **/
    val startWeek = pcs.basicProtoCache.wonderWarOpen[0]
    val endWeek = pcs.basicProtoCache.wonderWarClose[0]
    val startHour = pcs.basicProtoCache.wonderWarOpen[1]
    val endHour = pcs.basicProtoCache.wonderWarClose[1]

    // 根据配置计算奇观开始结束时间与当前状态
    val warStartTime = getWeekDateZeroTime(0, startWeek) + startHour * 3600 * 1000
    val warFinishTime = getWeekDateZeroTime(0, endWeek) + endHour * 3600 * 1000

    val nextStartTime = warStartTime + (7 * 24) * 3600 * 1000
    val nextFinishTime = warFinishTime + (7 * 24) * 3600 * 1000

    wonder.warStartTime = nextStartTime
    wonder.occupyStartTime = maxTime.time
    wonder.occupyOverTime = maxTime.time
    wonder.warFinishTime = nextFinishTime
}

// 解散对该奇观的军事行为
fun dismissWonderArmy(areaCache: AreaCache, wonder: Wonder) {
    val wonderProto = pcs.wonderLocationProtoCache.wonderLocationProtoMap[wonder.wonderProtoId]
    if (wonderProto === null) {
        normalLog.error("找不到奇观配置：${wonder.wonderProtoId}")
        return
    }

    val centerPos = wonderProto.getCenterPos()
    val groups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(centerPos.x, centerPos.y, Reinforce)
    for (group in groups) {
        goHome(areaCache, centerPos.x, centerPos.y, group)
    }
    wonder.occupyGroupId = 0

    // 解散所有对该奇观的集结
    val wonderX = wonderProto.getCenterPos().x
    val wonderY = wonderProto.getCenterPos().y
    for (mass in areaCache.massCache.findAllMassByPos(wonderX, wonderY)) {
        if (mass.fightType == WalkOccupyWonder) {
            doCancelMass(areaCache, mass.mainPlayerId, mass.id)
        }
    }

    // 遣返所有到该奇观的行军
    val gotoWalks = areaCache.walkCache.findWalksByGotoXy(wonderX, wonderY)
    for (walk in gotoWalks) {
        halfWayGoHome(areaCache, walk)
    }
}

// 奇观争夺结束后的处理
fun dealAfterWonderWar(areaCache: AreaCache, bigWonder: Wonder) {
    val wonderProtoCache = pcs.wonderLocationProtoCache

    //重置天下大赦次数
    val leader = areaCache.playerCache.findAllianceLeader(bigWonder.belongToAllianceId)
    bigWonder.pardonCount = pcs.basicProtoCache.pardom
    if (leader != null) {
        val session = fetchOnlinePlayerSession(areaCache, leader.id)
        if (session != null) {
            val notifier = createAmnestyCountChangeNotifier(bigWonder.pardonCount)
            notifier.notice(session)
        }
    }

    //更新名人堂
    val wonderRankVo = bigWonder.rankInfoMap[bigWonder.belongToAllianceId]
    if (leader != null && wonderRankVo != null) {
        areaCache.fameHallCache.createFameHall(
            leader.allianceName,
            leader.allianceShortName,
            leader.name,
            leader.photoProtoId,
            wonderRankVo.score,
            getNowTime(),
            leader.allianceId,
            leader.id
        )
    }

    // 获取奇观排行奖励
    val rankList = LinkedList<WonderRankVo>(bigWonder.rankInfoMap.values)
    rankList.sortByDescending { it.score }
    for ((i, rankVo) in rankList.withIndex()) {
        val rank = i + 1
        val award = wonderProtoCache.findRankRewardByRank(
            bigWonder.wonderProtoId,
            rank
        )
        if (award == null) {
            continue
        }
        val allAward = LinkedList<ResVo>()
        for (dropId in award.rewardBags) {
            val drop = pcs.dropBagCache.dropBagMap[dropId]
            if (drop == null) {
                continue
            }
            allAward += drop.dropMap
        }

        // 发送联盟奖励邮件
        sendAllianceAwardMail(
            areaCache,
            rankVo.allianceId,
            TEXT_READ_LAN,
            WONDER_ALLIANCE_RANK_TITLE,
            asList(rank.toString()),
            WONDER_ALLIANCE_RANK_CONTENT,
            asList(rank.toString()),
            resVoToResString(allAward)
        )
    }
}