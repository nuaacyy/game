package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.MapMgr
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.playerIsHavePos
import com.point18.slg2d.world.msgnotice.UpdateLandBelongNotifier
import pb4client.*
import java.util.*

// 更新地块
fun noticeCellUpdate(
    areaCache: AreaCache,
    x: Int,
    y: Int,
    cb: ((notifier: UpdateLandBelongNotifier) -> Unit)? = null
) {
    //检查地块
    if (!checkLandByXy(areaCache, x, y)) {
        noticeCellRemove(areaCache, x, y)
        return
    }
    // 获取观察者列表
    val gridPos = MapMgr.getScreenGrid(x, y)
    val watcherMap =
        areaCache.mapCellWatcherCache.findWatcherByXy(gridPos.first, gridPos.second)
    if (watcherMap == null) {
        return
    }

    // 推地块
    val land = getEveryLandInfoByXy(areaCache, x, y)
    if (land == null) {
        return
    }

    // 向观察者推送
    val lands = listOf(land)
    val updateLandBelongNotifierForNoHaveShiye = UpdateLandBelongNotifier(lands, null)
    for ((watcher, _) in watcherMap) {
        // 视野推送
        updateLandBelongNotifierForNoHaveShiye.notice(areaCache, watcher)
    }

    // 额外的一些回调处理
    cb?.invoke(updateLandBelongNotifierForNoHaveShiye)
}

// 移除地块
fun noticeCellRemove(areaCache: AreaCache, x: Int, y: Int) {
    val gridPos = MapMgr.getScreenGrid(x, y)
    val watcherMap = areaCache.mapCellWatcherCache.findWatcherByXy(gridPos.first, gridPos.second) ?: return

    // 推地块
    val delList = LinkedList<pb4client.CellPoint.Builder>()
    val cellPointBuilder = pb4client.CellPoint.newBuilder()
    cellPointBuilder.x = x
    cellPointBuilder.y = y
    delList.add(cellPointBuilder)

    val updateLandBelongNotifierForNoHaveShiye = UpdateLandBelongNotifier(null, delList)
    for ((watcher, _) in watcherMap) {
        // 视野推送
        updateLandBelongNotifierForNoHaveShiye.notice(areaCache, watcher)
    }
}

fun getEveryLandInfoByXy(
    areaCache: AreaCache,
    x: Int,
    y: Int,
    grid: Pair<Int, Int>? = null
): NewEveryLandInfo.Builder? {
    val landInfo = NewEveryLandInfo.newBuilder()
    landInfo.x = x
    landInfo.y = y
    var gridPos = grid
    if (gridPos == null) {
        gridPos = MapMgr.getScreenGrid(x, y)
    }
    landInfo.gridX = gridPos.first
    landInfo.gridY = gridPos.second

    // 给客户端发的地图格子有多种被创建的可能
    // 1 资源点
    val commonResCell = areaCache.commonResCache.findCommonResByXY(x, y)
    if (commonResCell != null) {
        val resProto = pcs.resPointProtoCache.resPointMap[commonResCell.resId]
        if (resProto == null) {
            normalLog.error("找不到资源点配置:${commonResCell.resId}")
            return null
        }
        landInfo.cellType = CELL_RESOURCE
        val resInfoBuilder = ResLandInfo.newBuilder()
        resInfoBuilder.lv = commonResCell.lv
        resInfoBuilder.resType = resProto.type
        resInfoBuilder.resNum = commonResCell.nowResNum
        if (commonResCell.groupId != 0L) {
            val farmGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(commonResCell.groupId)
            if (farmGroup != null) {
                resInfoBuilder.forceState = Farming
                val farmPlayer = areaCache.playerCache.findPlayerById(farmGroup.mainPlayerId)
                if (farmPlayer != null) {
                    resInfoBuilder.playerId = farmPlayer.id
                    resInfoBuilder.allianceId = farmPlayer.allianceId
                }
            }
        }
        landInfo.setResInfo(resInfoBuilder)
        return landInfo
    }

    // 12 尸体资源地
    val bossDieRes = areaCache.deadBossResCache.findDeadBossResByXy(x, y)
    if (bossDieRes != null) {
        val resProto = pcs.resPointProtoCache.resPointMap[bossDieRes.resId]
        if (resProto == null) {
            normalLog.error("找不到资源点配置:${bossDieRes.resId}")
            return null
        }
        landInfo.cellType = CELL_BOSS_DEAD_RESOURCE
        val resInfoBuilder = ResLandInfo.newBuilder()
        resInfoBuilder.resType = resProto.type
        resInfoBuilder.resNum = bossDieRes.nowResNum
        resInfoBuilder.lv = resProto.level
        resInfoBuilder.disappearTime = getTimeSec(bossDieRes.overTime)

        if (bossDieRes.groupId != 0L) {
            val farmGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(bossDieRes.groupId)
            if (farmGroup != null) {
                resInfoBuilder.forceState = Farming
                val farmPlayer = areaCache.playerCache.findPlayerById(farmGroup.mainPlayerId)
                if (farmPlayer != null) {
                    resInfoBuilder.playerId = farmPlayer.id
                    resInfoBuilder.allianceId = farmPlayer.allianceId
                }
            }
        }
        landInfo.setResInfo(resInfoBuilder)
        return landInfo
    }

    // 2 普通魔物
    val commonBossCell = areaCache.commonBossCache.findCommonBossByXY(x, y)
    if (commonBossCell != null) {
        landInfo.cellType = CELL_MONSTER
        val bossInfoBuilder = BossLandInfo.newBuilder()
        bossInfoBuilder.bossId = commonBossCell.bossId
        bossInfoBuilder.bossHp = commonBossCell.nowHp
        bossInfoBuilder.disappearTime = getTimeSec(areaCache.commonBossCache.calOverTime(x, y))
        landInfo.setBossLandInfo(bossInfoBuilder)
        return landInfo
    }

    // 11 个人召唤魔物
    val callBoss = areaCache.callBossCache.findCallBossByXy(x, y)
    if (callBoss != null) {
        val callBossProto = pcs.monsterProtoCache.findMonsterProto(callBoss.bossId)
        if (callBossProto == null) {
            normalLog.error("找不到召唤魔物配置:${callBoss.bossId}")
            return null
        }
        landInfo.cellType = CELL_CALL_BOSS
        val bossInfoBuilder = BossLandInfo.newBuilder()
        bossInfoBuilder.bossHp = callBoss.nowHp
        bossInfoBuilder.disappearTime = getTimeSec(callBoss.overTime)
        bossInfoBuilder.unlockTime = getTimeSec(callBoss.protectOverTime)
        bossInfoBuilder.bossId = callBoss.bossId
        bossInfoBuilder.allianceId = callBoss.allianceId
        val member = areaCache.playerCache.findFirstPlayerByAllianceId(callBoss.allianceId)
        if (member != null) {
            bossInfoBuilder.allianceShortName = member.allianceShortName
        }
        bossInfoBuilder.playerId = callBoss.playerId
        val callPlayer = areaCache.playerCache.findPlayerById(callBoss.playerId)
        if (callPlayer != null) {
            bossInfoBuilder.playerName = callPlayer.name
            bossInfoBuilder.photoId = callPlayer.photoProtoId
        }
        bossInfoBuilder.helpMemberCount = callBoss.helpMemberMap.count()
        landInfo.setBossLandInfo(bossInfoBuilder)
        return landInfo
    }

    // 3 遗迹
    val mapRelic = areaCache.relicCache.findRelicByXY(x, y)
    if (mapRelic != null) {
        landInfo.cellType = CELL_RELIC
        val relicInfoBuilder = RelicLandInfo.newBuilder()
        relicInfoBuilder.relicId = mapRelic.relicId
        landInfo.setRelicInfo(relicInfoBuilder)
        return landInfo
    }

    // 4 玩家城池
    val castle = areaCache.castleCache.findCastleByXy(x, y)
    if (castle != null) {
        val player = areaCache.playerCache.findPlayerById(castle.playerId)
        if (player == null) {
            normalLog.error("找不到城池${castle.id}对应的玩家${castle.playerId}")
            return null
        }
        landInfo.cellType = CELL_CASTLE
        val castleInfoBuilder = CastleLandInfo.newBuilder()
        castleInfoBuilder.castleStatus = castle.castleState
        castleInfoBuilder.playerId = player.id
        castleInfoBuilder.playerName = player.name
        castleInfoBuilder.allianceId = player.allianceId
        castleInfoBuilder.allianceName = player.allianceName
        castleInfoBuilder.allianceShortName = player.allianceShortName
        castleInfoBuilder.lv = castle.lv
        castleInfoBuilder.currentPos = findOfficeByPlayerId(areaCache, castle.playerId)

        val (_, coverOverTime) = areaCache.buffCache.isHaveCoverTypeBuff(player.id)
        castleInfoBuilder.coverOverTime = (coverOverTime / 1000).toInt()

        val prisons = areaCache.prisonCache.findPrisonsByPlayerId(player.id)
        for (prison in prisons) {
            val prisonPlayer = areaCache.playerCache.findPlayerById(prison.prisonPlayerId)
            if (prisonPlayer == null) {
                continue
            }
            val cellPrisonInfoBuilder = CellPrisonInfo.newBuilder()
            cellPrisonInfoBuilder.playerId = prisonPlayer.id
            cellPrisonInfoBuilder.rewardGold = prison.rewardGold
            cellPrisonInfoBuilder.playerName = prisonPlayer.name
            cellPrisonInfoBuilder.allianceShortName = prisonPlayer.allianceShortName
            castleInfoBuilder.addPrisonInfos(cellPrisonInfoBuilder)
        }
        // todo prison buff
        if (player.maxLvPrisonBuffEndTime > getNowTime()) {
            val cellPrisonInfoBuilder = CellPrisonInfo.newBuilder()
            cellPrisonInfoBuilder.playerId = -1
            cellPrisonInfoBuilder.rewardGold = 0
            cellPrisonInfoBuilder.playerName = "buff"
            cellPrisonInfoBuilder.allianceShortName = "buff"
            castleInfoBuilder.addPrisonInfos(cellPrisonInfoBuilder)
        }

        landInfo.setCastleInfo(castleInfoBuilder)

        return landInfo
    }

    // 5 奇观
    val area = pcs.wonderLocationProtoCache.findInWonderType(x, y)
    if (area.int == WONDER_BASE) {
        landInfo.cellType = CELL_WONDER
        val wonderProto = area.wonderLocationProto
        if (wonderProto == null) {
            normalLog.error("WalkGroupHelper.kt : area.wonderLocationProto == null")
            return null
        }
        val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
        if (wonder == null) {
            normalLog.error("WalkGroupHelper.kt : areaCache.wonderCache.findWonder(tmpWonderLocationProto.id) == null")
            return null
        }
        val wonderInfoBuilder = WonderLandInfo.newBuilder()
        if (wonder.belongToAllianceId != 0L) {
            val allianceMembers = areaCache.playerCache.findPlayersByAllianceId(wonder.belongToAllianceId)
            // 占领联盟盟主信息玩家
            for (member in allianceMembers) {
                if (playerIsHavePos(member, ALLIANCE_POSITION_BOSS)) {
                    wonderInfoBuilder.playerId = member.id
                    wonderInfoBuilder.playerName = member.name
                    wonderInfoBuilder.allianceId = member.allianceId
                    wonderInfoBuilder.allianceName = member.allianceName
                    wonderInfoBuilder.allianceShortName = member.allianceShortName
                    break
                }
            }
        }
        wonderInfoBuilder.wonderStatus = wonder.status
        wonderInfoBuilder.statusOverTime = (wonder.statusOverTime / 1000).toInt()
        landInfo.setWonderInfo(wonderInfoBuilder)
        return landInfo
    }

    // 13 活动魔物
    val activityBoss = areaCache.activityBossCache.findActivityBossByXy(x, y)
    if (activityBoss != null) {
        val bossInfoBuilder = BossLandInfo.newBuilder()
        for ((_, locationProto) in pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoMap) {
            if (x == locationProto.baseIndex[0] && y == locationProto.baseIndex[1]) {
                // 找到当前活动魔物的地区模板
                val activityBossArea =
                    areaCache.activityBossAreaCache.findActivityBossAreaByLocationId(locationProto.id)
                if (activityBossArea == null) {
                    normalLog.error("activityBossArea == null")
                    return null
                }
                bossInfoBuilder.disappearTime = (activityBossArea.finishTime / 1000).toInt() //活动魔物消失时间
                bossInfoBuilder.unlockTime = (activityBossArea.refreshTime / 1000).toInt() //Boss解锁攻击限制的时间(开始后+5s动画时间)
                break
            }
        }
        landInfo.cellType = CELL_ACTIVITY_BOSS
        if (activityBoss.nowHp > 0) {
            bossInfoBuilder.bossHp = activityBoss.nowHp
            bossInfoBuilder.bossId = activityBoss.bossId
        } else {
            bossInfoBuilder.bossHp = 0
            bossInfoBuilder.bossId = 0
        }
        landInfo.setBossLandInfo(bossInfoBuilder)
        return landInfo
    }

    // 14 属地 暂时归属某个玩家
    val belongCell = areaCache.belongCellCache.findBelongCellByXy(x, y)
    if (belongCell != null && getNowTime() < belongCell.overTime) {
        landInfo.cellType = CELL_BELONG
        val belongInfoBuilder = BelongLandInfo.newBuilder()
        belongInfoBuilder.playerId = belongCell.playerId
        val player = areaCache.playerCache.findPlayerById(belongCell.playerId)
        val defPlayer = areaCache.playerCache.findPlayerById(belongCell.defPlayerId)
        val battleInfoBuilder = BattleSimpleRs.newBuilder()
        if (player != null) {
            belongInfoBuilder.playerName = player.name
            belongInfoBuilder.allianceShortName = player.allianceShortName
            belongInfoBuilder.photoId = player.photoProtoId
            battleInfoBuilder.atkerPlayerId = player.id
            battleInfoBuilder.atkerPhotoId = player.photoProtoId
            battleInfoBuilder.atkerAllianceShortName = player.allianceShortName
            battleInfoBuilder.atkerName = player.name
        }
        if (defPlayer != null) {
            battleInfoBuilder.deferPlayerId = defPlayer.id
            battleInfoBuilder.deferPhotoId = defPlayer.photoProtoId
            battleInfoBuilder.deferAllianceShortName = defPlayer.allianceShortName
            battleInfoBuilder.deferName = defPlayer.name
        }

        belongInfoBuilder.belongOverTime = (belongCell.overTime / 1000).toInt()
        battleInfoBuilder.atkerBattleRs = belongCell.atkBattleRs
        battleInfoBuilder.deferBattleRs = belongCell.defBattleRs
        belongInfoBuilder.setBattleSimpleRs(battleInfoBuilder)
        landInfo.setBelongInfo(belongInfoBuilder)
        return landInfo
    }

    return null
}

fun checkLandByXy(areaCache: AreaCache, x: Int, y: Int): Boolean {
    // 给客户端发的地图格子有多种被创建的可能
    // 第一种,资源点/魔物
    val bossCell = areaCache.commonBossCache.findCommonBossByXY(x, y)
    if (bossCell != null) {
        return true
    }

    val callBoss = areaCache.callBossCache.findCallBossByXy(x, y)
    if (callBoss != null) {
        return true
    }

    val farmCell = areaCache.commonResCache.findCommonResByXY(x, y)
    if (farmCell != null) {
        return true
    }

    val deadRes = areaCache.deadBossResCache.findDeadBossResByXy(x, y)
    if (deadRes != null) {
        return true
    }

//    val strongHoldCell = findStrongHoldCell(areaCache, x, y)
//    if (strongHoldCell != null) {
//        return true
//    }

    // 第二种,遗迹
    val mapRelic = areaCache.relicCache.findRelicByXY(x, y)
    if (mapRelic != null) {
        return true
    }

    // 第三种,玩家城池
    val castle = areaCache.castleCache.findCastleByXy(x, y)
    if (castle != null) {
        return true
    }

    // 第四种,空地，上面有部队在上面
    val walkForceGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(x, y, Stationed)
    if (walkForceGroups.count() > 0) {
        return true
    }

    val activityBosses = areaCache.activityBossCache.findActivityBossByXy(x, y)
    if (activityBosses != null) {
        return true
    }

    val area = pcs.wonderLocationProtoCache.findInWonderType(x, y)
    if (area.int == WONDER_BASE) {
        return true
    }

    val snowArea = pcs.monsterActivityLocationProtoCache.findActivityBossAreaType(x, y)
    if (snowArea.int == SNOW_BASE) {
        return true
    }

    val belongArea = areaCache.belongCellCache.findBelongCellByXy(x, y)
    if (belongArea != null) {
        return true
    }
    return false
}

fun walkWrap2WalkRobot(areaCache: AreaCache, walk: Walk): WalkRobot.Builder? {
    val walkForceGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
    if (walkForceGroup == null) {
        normalLog.error("WalkGroupHelper.kt : areaCache.walkForceGroupCache.findWalkForceGroupById(change.walkForceGroupId) == null ：${walk.walkForceGroupId}")
        return null
    }
    val mainPlayer = areaCache.playerCache.findPlayerById(walkForceGroup.mainPlayerId)
    if (mainPlayer == null) {
        normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(walkForceGroupCache.mainPlayerId) == null :${walkForceGroup.mainPlayerId}")
        return null
    }
    val posX = walk.marchAimsX
    val posY = walk.marchAimsY
    var targetAllianceId = 0L
    val walkType = walk.marchState
    when (walkType) {
        WalkFightPlayer, WalkScout -> {
            val castle = areaCache.castleCache.findCastleByXy(posX, posY)
            if (castle != null) {
                val player = areaCache.playerCache.findPlayerById(castle.playerId)
                if (player == null) {
                    normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(castle.playerId) == null")
                    return null
                }
                targetAllianceId = player.allianceId
            }
        }

    }
    when (walkType) {
        WalkFarm,
        WalkOccupyCell,
        WalkScout -> {
            val groups =
                areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(posX, posY, Farming or Stationed)
            for (group in groups) {
                val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
                if (player == null) {
                    normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(group.mainPlayerId) == null")
                    return null
                }
                if (targetAllianceId == 0L) {
                    targetAllianceId = player.allianceId
                }
            }
        }
    }

    var isGetResHome = 0
    if (walkType == WalkGoHome) {
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(walk.walkForceGroupId)
        isGetResHome =
                boolToInt(forces.any { force -> force.resVo.any { res -> isTransportResource(res.resType) && res.num > 0 } })
    }

    val walkRobotBuilder = WalkRobot.newBuilder()
    walkRobotBuilder.runType = walk.marchState
    walkRobotBuilder.goRunType = walkForceGroup.gotoRunType
    walkRobotBuilder.startX = walk.marchPlaceX
    walkRobotBuilder.startY = walk.marchPlaceY
    walkRobotBuilder.gotoX = walk.marchAimsX
    walkRobotBuilder.gotoY = walk.marchAimsY
    walkRobotBuilder.startTime = walk.marchTimeOff
    walkRobotBuilder.overTime = walk.marchTimeArrival
    walkRobotBuilder.initialWalkTime = walk.initialWalkTime
    walkRobotBuilder.isGetMainHero = walkForceGroup.isGetMainHero
    walkRobotBuilder.playerId = walkForceGroup.mainPlayerId
    walkRobotBuilder.allianceId = mainPlayer.allianceId
    walkRobotBuilder.playerName = mainPlayer.name
    walkRobotBuilder.allianceShortName = mainPlayer.allianceShortName
    walkRobotBuilder.walkOnlyId = walk.id
    walkRobotBuilder.groupId = walk.walkForceGroupId
    walkRobotBuilder.isAtkMonsterHome = walk.isAtkMonsterHome
    walkRobotBuilder.targetAllianceId = targetAllianceId
    walkRobotBuilder.isGetResHome = isGetResHome
    walkRobotBuilder.photoId = mainPlayer.photoProtoId
    walkRobotBuilder.currentSpeed = walk.walkSpeed.toFloat()
    walkRobotBuilder.massId = walkForceGroup.massId

    val mainHero = areaCache.heroCache.findHeroById(mainPlayer.id, mainPlayer.mainHeroId)
    if (mainHero == null) {
        normalLog.error("找不到玩家的领主，玩家Id${mainPlayer.id}，领主Id:${mainPlayer.mainHeroId}")
        return null
    }
    walkRobotBuilder.kingHeroProtoId = mainHero.protoId

    //判断地块
    val commonResCell = areaCache.commonResCache.findCommonResByXY(posX, posY)
    if (commonResCell != null) {
        val resProto = pcs.resPointProtoCache.resPointMap[commonResCell.resId]
        if (resProto != null) {
            walkRobotBuilder.cellType = CELL_RESOURCE
            walkRobotBuilder.reslv = commonResCell.lv
            walkRobotBuilder.resType = resProto.type
            return walkRobotBuilder
        }
    }

    //尸体资源地
    val bossDieRes = areaCache.deadBossResCache.findDeadBossResByXy(posX, posY)
    if (bossDieRes != null) {
        val resPorto = pcs.resPointProtoCache.resPointMap[bossDieRes.resId]
        if (resPorto != null) {
            walkRobotBuilder.cellType = CELL_BOSS_DEAD_RESOURCE
            walkRobotBuilder.reslv = resPorto.level
            walkRobotBuilder.resType = resPorto.type
            return walkRobotBuilder
        }
    }

    //普通魔物
    val commonBossCell = areaCache.commonBossCache.findCommonBossByXY(posX, posY)
    if (commonBossCell != null) {
        walkRobotBuilder.cellType = CELL_MONSTER
        walkRobotBuilder.bossId = commonBossCell.bossId
        return walkRobotBuilder
    }

    //个人召唤魔物
    val callBoss = areaCache.callBossCache.findCallBossByXy(posX, posY)
    if (callBoss != null) {
        val callBossProto = pcs.monsterProtoCache.findMonsterProto(callBoss.bossId)
        if (callBossProto == null) {
            normalLog.error("WalkGroupHelper.kt : pcs.monsterWorldProtoCache.monsterWorldProtoMap[worldBosses.bossId] == null")
            return null
        }
        walkRobotBuilder.cellType = CELL_CALL_BOSS
        walkRobotBuilder.bossId = callBoss.bossId
        return walkRobotBuilder
    }

    // 第二种,遗迹
    val mapRelic = areaCache.relicCache.findRelicByXY(posX, posY)
    if (mapRelic != null) {
        walkRobotBuilder.cellType = CELL_RELIC
        walkRobotBuilder.relicId = mapRelic.relicId
        return walkRobotBuilder
    }

    // 第三种,玩家城池
    val castle = areaCache.castleCache.findCastleByXy(posX, posY)
    if (castle != null) {
        val player = areaCache.playerCache.findPlayerById(castle.playerId)
        if (player == null) {
            //Assert
            return null
        }
        walkRobotBuilder.cellType = CELL_CASTLE
        walkRobotBuilder.targetPlayerName = player.name
        walkRobotBuilder.targetPlayerShortName = player.allianceShortName
        return walkRobotBuilder
    }

    return walkRobotBuilder
}