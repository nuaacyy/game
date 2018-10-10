package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.MapMgr
import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoCombine
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.msgnotice.createMonsterDamageInfoNotifier
import com.point18.slg2d.world.msgnotice.createWalkGroupChangeNotifier
import pb4client.*
import xyz.ariane.util.lzDebug
import java.util.*

//=====================================自身部队=============================================
//生成WalkGroup
fun createWalkGroupMsg(areaCache: AreaCache, group: WalkForceGroup): WalkGroup.Builder? {
    val walkGroup = WalkGroup.newBuilder()
    walkGroup.groupId = (group.id)
    walkGroup.forceState = (group.runningState)
    var posX = group.nowX
    var posY = group.nowY
    walkGroup.gotoX = (posX)
    walkGroup.gotoY = (posY)
    walkGroup.startRunType = group.gotoRunType

    //添加部队信息
    val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
    for (i in forces.indices) {
        val forceInWalk = ForceInfoInWalk.newBuilder()
        forceInWalk.playerId = forces[i].playerId
        val player = areaCache.playerCache.findPlayerById(forces[i].playerId)
        if (player == null) {
            normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(forces[i].playerId) == null")
            return null
        }

        forceInWalk.playerName = player.name
        forceInWalk.allianceName = player.allianceName
        forceInWalk.allianceShortName = player.allianceShortName
        for (j in forces[i].heroIdList.indices) {
            val hero = areaCache.heroCache.findHeroById(forces[i].playerId, forces[i].heroIdList[j])
            if (hero == null) {
                normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(forces[i].playerId) == null")
                continue
            }

            val tmp = HeroForWalk.newBuilder()
            tmp.awake = hero.awake
            tmp.protoId = hero.protoId
            tmp.starLv = hero.level
            tmp.lv = hero.advLv
            tmp.isLord = boolToInt(hero.mainHeroState != 0)
            forceInWalk.addHeros(j, tmp.build())
        }
        for ((id, num) in forces[i].soliderMap) {
            val tmpSoliderForWalk = SoliderForWalk.newBuilder()
            tmpSoliderForWalk.propId = id
            tmpSoliderForWalk.num = num
            forceInWalk.addSoliders(tmpSoliderForWalk)
        }

        walkGroup.addForces(forceInWalk)
    }

    var findMass: Mass? = null
    val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
    if (player == null) {
        normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(group.mainPlayerId) == null")
        return null
    }

    val allMass = areaCache.massCache.findMassByAllianceId(player.allianceId)
    for (mass in allMass) {
        if (findMass != null) {
            break
        }
        if (mass.massState == Run) {
            if (mass.groupId == group.id) {
                findMass = mass
                break
            }
        } else {
            for (member in mass.memberInfoList) {
                if (member.groupId == group.id) {
                    findMass = mass
                    break
                }
            }
        }
    }
    if (findMass != null) {
        walkGroup.massId = findMass.id
    }

    if (group.runningState == Running) {
        //行军中，填充行军线数据
        val walkLine = areaCache.walkCache.findWalkByGroupId(group.id)
        if (walkLine == null) {
            normalLog.error("找不到行军组对应的行军线:${group.id}")
            return null
        }
        posX = walkLine.marchAimsX
        posY = walkLine.marchAimsY
        walkGroup.startX = walkLine.marchPlaceX
        walkGroup.startY = walkLine.marchPlaceY
        walkGroup.gotoX = posX
        walkGroup.gotoY = posY
        walkGroup.walkOnlyId = walkLine.id
        walkGroup.runType = walkLine.marchState

        walkGroup.startTime = getTimeSec(walkLine.marchTimeOff)
        walkGroup.overTime = getTimeSec(walkLine.marchTimeArrival)
        walkGroup.initialWalkTime = walkLine.initialWalkTime
        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null) {
            normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(group.mainPlayerId) == null")
            return null
        }

        if (walkLine.marchState == WalkTransport) {
            //运输
            var haveRes = LinkedList<ResVo>()
            val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
            for (force in forces) {
                haveRes.addAll(force.resVo)
            }
            haveRes = resVoCombine(haveRes)
            walkGroup.haveRes = resVoToResString(haveRes)
        }
    } else if (group.runningState == MassWaiting) {
        if (findMass != null) {
            walkGroup.startTime = (findMass.massStartTime.toInt() / 1000)
            var finalArriveTime = findMass.goTime
            for (member in findMass.memberInfoList) {
                if (finalArriveTime < member.arriveTime) {
                    finalArriveTime = member.arriveTime
                }
            }
            walkGroup.overTime = (finalArriveTime.toInt() / 1000)
        }
    } else if (group.runningState == Farming) {
        val commonResCell = areaCache.commonResCache.findCommonResByXY(posX, posY)
        if (commonResCell != null) {
            val resProto = pcs.resPointProtoCache.resPointMap[commonResCell.resId]
            if (resProto != null) {
                walkGroup.resType = resProto.resType
                walkGroup.resLv = commonResCell.lv
                walkGroup.farmWeight = calWeight(areaCache, commonResCell.groupId)
                walkGroup.startTime = getTimeSec(commonResCell.farmStartTime)
                walkGroup.overTime = getTimeSec(commonResCell.farmEndTime)
                return walkGroup
            }
        }

        val deadBossResCell = areaCache.deadBossResCache.findDeadBossResByXy(posX, posY)
        if (deadBossResCell != null) {
            val resProto = pcs.resPointProtoCache.resPointMap[deadBossResCell.resId]
            if (resProto != null) {
                walkGroup.resType = resProto.resType
                walkGroup.resLv = resProto.level
                walkGroup.farmWeight = calWeight(areaCache, deadBossResCell.groupId)
                walkGroup.startTime = getTimeSec(deadBossResCell.farmStartTime)
                walkGroup.overTime = getTimeSec(deadBossResCell.farmEndTime)
                return walkGroup
            }
        }
    }

//    val strongholdCell = findStrongHoldCell(areaCache, posX, posY)
//    if (strongholdCell != null) {
//        walkGroup.resLv = (strongholdCell.lv)
//        val farmData = findFarmByPos(areaCache, posX, posY)
//        if (farmData != null) {
//            walkGroup.startTime = (farmData.farmStartTime / 1000).toInt()
//            walkGroup.overTime = (farmData.farmEndTime / 1000).toInt()
//        }
//        return walkGroup
//    }

    val commonBossCell = areaCache.commonBossCache.findCommonBossByXY(posX, posY)
    if (commonBossCell != null) {
        walkGroup.bossId = commonBossCell.bossId
        return walkGroup
    }

    val callBossCell = areaCache.callBossCache.findCallBossByXy(posX, posY)
    if (callBossCell != null) {
        walkGroup.bossId = callBossCell.bossId
        return walkGroup
    }

    // 玩家城
    val castle = areaCache.castleCache.findCastleByXy(posX, posY)
    if (castle != null) {
        val targetPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        if (targetPlayer == null) {
            normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(castle.playerId) == null")
            return null
        }
        walkGroup.taregetPlayerName = targetPlayer.name
        return walkGroup
    }

    val relicCell = areaCache.relicCache.findRelicByXY(posX, posY)
    if (relicCell != null) {
        walkGroup.relicId = (relicCell.relicId)
        return walkGroup
    }
    return walkGroup
}

//通知自身部队
fun noticeSelfWalkForceGroup(areaCache: AreaCache, changeType: Int, group: WalkForceGroup) {
    val walkGroup: WalkGroup.Builder?
    if (changeType == Del) {
        walkGroup = WalkGroup.newBuilder()
        walkGroup.groupId = group.id
    } else {
        walkGroup = createWalkGroupMsg(areaCache, group)
    }
    if (walkGroup == null) {
        normalLog.error("WalkGroupHelper.kt : WalkGroup.newBuilder(tmp) == null")
        return
    }

    val totalPlayerIds = hashMapOf<Long, Int>()
    totalPlayerIds[group.mainPlayerId] = 1
    val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
    for (force in forces) {
        totalPlayerIds[force.playerId] = 1
    }

    val notifier = createWalkGroupChangeNotifier(changeType, SelfWalkGroup, walkGroup)
    for ((id, _) in totalPlayerIds) {
        val session = fetchOnlinePlayerSession(areaCache, id)
        if (session != null) {
            notifier.notice(session)
        }
    }
}

//========================================预警=======================================================
fun createWarnGroup(areaCache: AreaCache, walkLine: Walk, defPlayer: Player): WalkGroup.Builder? {
    val posX = walkLine.marchAimsX
    val posY = walkLine.marchAimsY
    val group = areaCache.walkForceGroupCache.findWalkForceGroupById(walkLine.walkForceGroupId)
    if (group == null) {
        //无获得预警的建筑效果
        normalLog.error("WalkGroupHelper.kt : areaCache.walkForceGroupCache.findWalkForceGroupById(walkLine.walkForceGroupId) == null")
        return null
    }
    val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
    if (player == null) {
        normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(group.mainPlayerId) == null")
        return null
    }
    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv1) == 0) {
        return null
    }

    val soliderMap = hashMapOf<Int, Int>()
    val soliderTypeMap = hashMapOf<Int, Int>()
    val heroList = LinkedList<Hero>()

    //添加部队信息
    val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
    for (force in forces) {
        force.heroIdList.mapNotNullTo(heroList) { areaCache.heroCache.findHeroById(force.playerId, it) }

        for ((soliderId, soliderNum) in force.soliderMap) {
            val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
            if (soliderProto == null) {
                continue
            }
            soliderMap[soliderId] = (soliderMap[soliderId] ?: 0) + soliderNum
            soliderTypeMap[soliderProto.armyType] = (soliderTypeMap[soliderProto.armyType] ?: 0) + soliderNum
        }
    }

    val warningInfoInWalkBuilder = WarningInfoInWalk.newBuilder()
    val walkGroup = WalkGroup.newBuilder()

    walkGroup.groupId = walkLine.walkForceGroupId
    walkGroup.gotoX = posX
    walkGroup.gotoY = posY

    //开启预警功能，显示预警部队类型和目标
    walkGroup.runType = walkLine.marchState

    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv2) != 0) {
        //显示预警部队所属玩家名称和所属联盟简称
        warningInfoInWalkBuilder.playerId = player.id
        warningInfoInWalkBuilder.playerName = player.name
        warningInfoInWalkBuilder.playerPhoto = player.photoProtoId
        warningInfoInWalkBuilder.allianceName = defPlayer.allianceName
        warningInfoInWalkBuilder.allianceShortName = defPlayer.allianceShortName
    }

    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv3) != 0) {
        //显示预警部队所属玩家的城堡坐标
        walkGroup.startX = walkLine.marchPlaceX
        walkGroup.startY = walkLine.marchPlaceY
    }

    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv4) != 0) {
        //显示预警部队到达目标点需要的时间
        walkGroup.startTime = getTimeSec(walkLine.marchTimeOff)
        walkGroup.overTime = getTimeSec(walkLine.marchTimeArrival)
    }

    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv5) != 0) {
        //显示预警部队的兵力总数估算值，和英雄数量
        warningInfoInWalkBuilder.soliderNum = getEvaluateNum(soliderMap.values.sum())
        warningInfoInWalkBuilder.heroNum = heroList.count()
    }

    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv6) != 0) {
        //显示领主是否随军出征，和兵种构成以及对应的估算值
        warningInfoInWalkBuilder.isLordInForce = boolToInt(heroList.any { it.id == player.mainHeroId })

        for ((armyType, soliderNum) in soliderTypeMap) {
            val soliderByArmyTypeBuilder = SoliderByArmyType.newBuilder()
            soliderByArmyTypeBuilder.armyType = armyType
            soliderByArmyTypeBuilder.soliderCount = getEvaluateNum(soliderNum)
            warningInfoInWalkBuilder.addSoldiersByType(soliderByArmyTypeBuilder)
        }
    }

    val soliderBuilderList = LinkedList<SoliderForWalk.Builder>()
    val heroBuilderList = LinkedList<HeroForWalk.Builder>()

    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv7) != 0) {
        //显示预警部队的详细兵种，和对应估算值
        var totalEvaluateNum = 0
        for ((soliderId, soliderNum) in soliderMap) {
            val evaluateNum = getEvaluateNum(soliderNum)
            totalEvaluateNum += evaluateNum
            val soliderForWalkBuilder = SoliderForWalk.newBuilder()
            soliderForWalkBuilder.propId = soliderId
            soliderForWalkBuilder.num = evaluateNum
            soliderBuilderList.add(soliderForWalkBuilder)
        }
        warningInfoInWalkBuilder.soliderNum = totalEvaluateNum
    }

    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv8) != 0) {
        //显示预警部队参战的英雄形象（不显示等级阶级品质那种）
        for (hero in heroList) {
            val heroForWalkBuilder = HeroForWalk.newBuilder()
            heroForWalkBuilder.protoId = hero.protoId
            heroBuilderList.add(heroForWalkBuilder)
        }
    }

    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv9) != 0) {
        //显示预警部队里各兵种的详细数量
        soliderBuilderList.clear()
        var totalNum = 0
        for ((soliderId, soliderNum) in soliderMap) {
            totalNum += soliderNum
            val soliderForWalkBuilder = SoliderForWalk.newBuilder()
            soliderForWalkBuilder.propId = soliderId
            soliderForWalkBuilder.num = soliderNum
            soliderBuilderList.add(soliderForWalkBuilder)
        }
        warningInfoInWalkBuilder.soliderNum = totalNum
    }

    if (getResearchEffectValue(areaCache, NO_FILTER, defPlayer, ShowWarnResearchLv10) != 0) {
        //显示预警部队参战英雄的全部信息（包含等级阶级品质）
        heroBuilderList.clear()
        for (hero in heroList) {
            val heroForWalkBuilder = HeroForWalk.newBuilder()
            heroForWalkBuilder.protoId = hero.protoId
            heroForWalkBuilder.lv = hero.level
            heroForWalkBuilder.starLv = hero.advLv
            heroForWalkBuilder.awake = hero.awake
            heroForWalkBuilder.isLord = boolToInt(hero.id == player.mainHeroId)
            heroBuilderList.add(heroForWalkBuilder)
        }
    }

    soliderBuilderList.forEach { warningInfoInWalkBuilder.addExactSoliders(it) }
    heroBuilderList.forEach { warningInfoInWalkBuilder.addHeros(it) }

    walkGroup.setWarnInfo(warningInfoInWalkBuilder)
    return walkGroup
}

// 获取预警数据
fun getWarnData(areaCache: AreaCache, player: Player): List<WalkGroup.Builder> {
    val warnGroups = LinkedList<WalkGroup.Builder>()

    // 预警
    val warnDataList = areaCache.warnCache.findWarnWalksByPlayerId(player.id)
    for (warnData in warnDataList) {
        val warnGroup = createWarnGroup(areaCache, warnData.walk, player) ?: continue
        warnGroups.add(warnGroup)
    }
    return warnGroups
}

//坐标刷新预警
fun updateWarnWithPos(areaCache: AreaCache, posX: Int, posY: Int) {
    val changeWalkDataList = areaCache.warnCache.updateWarnByPos(posX, posY)
    for (warnData in changeWalkDataList.addWalkDataList) {
        val player = areaCache.playerCache.findPlayerById(warnData.playerId)
        if (player == null) {
            normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(warnData.playerId) == null")
            continue
        }

        normalLog.lzDebug { "Add Warn To Player:${player.name}" }
        val walkGroupV = createWarnGroup(areaCache, warnData.walk, player)
        if (walkGroupV == null) {
            continue
        }

        val walkGroup = WalkGroup.newBuilder(walkGroupV.build())
        val session = fetchOnlinePlayerSession(areaCache, warnData.playerId)
        if (session != null) {
            createWalkGroupChangeNotifier(Add, WarnWalkGroup, walkGroup).notice(session)
        }
    }
    for (warnData in changeWalkDataList.delWalkDataList) {
        val player = areaCache.playerCache.findPlayerById(warnData.playerId)
        if (player == null) {
            normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(warnData.playerId) == null")
            continue
        }
        normalLog.lzDebug { "Del Warn To Player:${player.name}" }
        val walkGroup = WalkGroup.newBuilder()
        walkGroup.groupId = warnData.walk.walkForceGroupId
        val session = fetchOnlinePlayerSession(areaCache, warnData.playerId)
        if (session != null) {
            createWalkGroupChangeNotifier(Del, WarnWalkGroup, walkGroup).notice(session)
        }
    }
}

//行军线刷新预警（行军线存在）
fun updateWarnForWalk(areaCache: AreaCache, walk: Walk) {
    val walkDataList = areaCache.warnCache.createWarnByWalk(walk)
    for (warnData in walkDataList) {
        val player = areaCache.playerCache.findPlayerById(warnData.playerId)
        if (player == null) {
            normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(warnData.playerId) == null")
            continue
        }

        val walkGroupV = createWarnGroup(areaCache, warnData.walk, player)
        if (walkGroupV == null) {
            continue
        }

        val walkGroup = WalkGroup.newBuilder(walkGroupV.build())
        val session = fetchOnlinePlayerSession(areaCache, warnData.playerId)
        if (session != null) {
            createWalkGroupChangeNotifier(Add, WarnWalkGroup, walkGroup).notice(session)
        }
    }
}

//=======================================魔物扣血信息==============================================
fun noticeMonsterDamageInfo(
    areaCache: AreaCache,
    posX: Int,
    posY: Int,
    bossId: Int,
    costHp: Int,
    groupId: Long
) {
    val gridPos = MapMgr.getScreenGrid(posX, posY)
    val watcherMap = areaCache.mapCellWatcherCache.findWatcherByXy(gridPos.first, gridPos.second) ?: return
    val notifier = createMonsterDamageInfoNotifier(posX, posY, bossId, costHp, groupId)
    watcherMap.forEach {
        notifier.notice(areaCache, it.key)
    }
}
