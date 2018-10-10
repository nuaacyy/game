package com.point18.slg2d.world.module.walk.walkComm

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.sec2MilliSec
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.msgnotice.createValueChgNotifier

// 半路回家
fun halfWayGoHome(areaCache: AreaCache, walk: Walk) {

    if (walk.marchState == WalkGoHome) {
        //部队已经在回城中
        return
    }

    val atkGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
    if (atkGroup == null) {
        normalLog.error("行军线对应的行军组不存在,行军线Id:${walk.id}")
        return
    }

    // 计算坐标的中心点，因为有可能来源或目的地不是单个cell。
    val startPos = areaCache.walkCache.calCenter(walk.marchAimsX, walk.marchAimsY)
    val gotoPos = areaCache.walkCache.calCenter(walk.marchPlaceX, walk.marchPlaceY)
    val nowPos = areaCache.walkCache.calCurrentPos(walk)

    val allWalkTime = walk.initialWalkTime
    val allDistance = calDistance(startPos, gotoPos)
    val walkSpeed = allDistance / allWalkTime

    val progressNum = calDistance(startPos, nowPos) / allDistance
    val startTime = getNowTime() - sec2MilliSec(allWalkTime) * progressNum
    val arriveTime = getNowTime() + sec2MilliSec(allWalkTime) * (1 - progressNum)

    // 先删除行军线
    deleteWalkOnMap(areaCache, walk)

    val walkForces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(atkGroup.id)
    if (walkForces.count() == 1 && atkGroup.massId == 0L) {
        // 单人行军，使用原行军组
        val player = areaCache.playerCache.findPlayerById(atkGroup.mainPlayerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:${atkGroup.mainPlayerId}")
            return
        }

        val atkCastle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (atkCastle == null) {
            normalLog.error("找不到玩家对应的城池信息:${player.focusCastleId}")
            return
        }

        areaCache.walkForceGroupCache.changeGroupState(atkGroup, Running)

        createWalkOnMap(
            areaCache, WalkGoHome, arriveTime.toLong(), walkSpeed,
            startPos.posX.toInt(), startPos.posY.toInt(), atkCastle.x, atkCastle.y, startTime.toLong(), atkGroup.id, 0
        )

        // 通知新组的行军组信息
        noticeSelfWalkForceGroup(areaCache, Update, atkGroup)

    } else {
        areaCache.walkForceGroupCache.delWalkForceGroup(atkGroup)
        // 重组
        for (force in walkForces) {
            val newAtkGroup = areaCache.walkForceGroupCache.createWalkForceGroup(
                force.playerId,
                0,
                Running,
                atkGroup.gotoRunType,
                atkGroup.nowX,
                atkGroup.nowY
            )
            areaCache.walkForceCache.updateWalkForceGroupId(force, newAtkGroup.id)
            val player = areaCache.playerCache.findPlayerById(force.playerId)
            if (player == null) {
                normalLog.error("找不到玩家信息:${force.playerId}")
                return
            }

            val atkCastle = areaCache.castleCache.findCastleById(player.focusCastleId)
            if (atkCastle === null) {
                normalLog.error("找不到玩家对应的城池信息:${player.focusCastleId}")
                return
            }

            val now = getNowTime()
            val walkTimeRt = calWalkTime(
                areaCache,
                newAtkGroup.mainPlayerId,
                atkGroup.gotoRunType,
                force.initialSoliderMap,
                nowPos.posX.toInt(),
                nowPos.posY.toInt(),
                atkCastle.x,
                atkCastle.y,
                false,
                false,
                true
            )
            createWalkOnMap(
                areaCache,
                WalkGoHome,
                now + walkTimeRt.walkTime * 1000,
                walkTimeRt.walkSpeed,
                nowPos.posX.toInt(),
                nowPos.posY.toInt(),
                atkCastle.x,
                atkCastle.y,
                now,
                newAtkGroup.id,
                0
            )
            //通知新组的行军组信息
            noticeSelfWalkForceGroup(areaCache, Update, newAtkGroup)
        }

        // 删除旧行军组
        noticeSelfWalkForceGroup(areaCache, Del, atkGroup)
    }

    // 刷新预警
    updateWarnWithPos(areaCache, startPos.posX.toInt(), startPos.posY.toInt())

    // 通知行军目标联盟成员更新
    if (atkGroup.gotoRunType == WalkReinforce) {
        val allMass = areaCache.massCache.findAllMassByPos(walk.marchAimsX, walk.marchAimsY)
        for (mass in allMass) {
            if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
                noticeReinforceMassGroup(areaCache, Update, mass)
            }
        }
    }
}

// 整个行军组的部队回家
fun goHome(
    areaCache: AreaCache,
    startX: Int,
    startY: Int,
    walkForceGroup: WalkForceGroup,
    isAtkMonsterHome: Boolean = false // 是否是攻击魔物后回家
) {
    val now = getNowTime()

    // 找到行军组对应的部队
    val forceList = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(walkForceGroup.id)
    if (forceList.count() == 1) {
        // 单人行军，使用原行军组
        val player = areaCache.playerCache.findPlayerById(walkForceGroup.mainPlayerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:${walkForceGroup.mainPlayerId}")
            return
        }

        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle === null) {
            normalLog.error("找不到玩家对应的城池信息:${player.focusCastleId}")
            return
        }

        // 直接将行军组修改为行军中
        areaCache.walkForceGroupCache.changeGroupState(walkForceGroup, Running)

        // 计算行军时间
        val walkTimeRt = calWalkTime(
            areaCache,
            walkForceGroup.mainPlayerId,
            walkForceGroup.gotoRunType,
            forceList[0].soliderMap,
            startX,
            startY,
            castle.x,
            castle.y,
            isAtkMonsterHome,
            false,
            true
        )

        if (walkTimeRt.walkTime <= 0) {
            //立即返回
            noticeSelfWalkForceGroup(areaCache, Del, walkForceGroup)
            removeForce(areaCache, forceList[0])
            areaCache.walkForceGroupCache.delWalkForceGroup(walkForceGroup)

        } else {
            val arrivalsTime = now + walkTimeRt.walkTime * 1000

            // 创建行军
            createWalkOnMap(
                areaCache, WalkGoHome, arrivalsTime, walkTimeRt.walkSpeed,
                startX, startY, castle.x, castle.y, now, walkForceGroup.id, boolToInt(isAtkMonsterHome)
            )

            // 通知新组的行军组信息
            noticeSelfWalkForceGroup(areaCache, Update, walkForceGroup)
        }

    } else {
        // 重组
        noticeSelfWalkForceGroup(areaCache, Del, walkForceGroup)

        areaCache.walkForceGroupCache.delWalkForceGroup(walkForceGroup)

        for (force in forceList) {
            if (!force.checkHaveSolider()) {
                //无兵则立即回城
                removeForce(areaCache, force)
                continue
            }

            val newGroup = areaCache.walkForceGroupCache.createWalkForceGroup(
                force.playerId,
                0,
                Running,
                walkForceGroup.gotoRunType,
                walkForceGroup.nowX,
                walkForceGroup.nowY
            )
            areaCache.walkForceCache.updateWalkForceGroupId(force, newGroup.id)

            val player = areaCache.playerCache.findPlayerById(force.playerId)
            if (player == null) {
                normalLog.error("找不到玩家信息:${force.playerId}")
                return
            }

            val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
            if (castle === null) {
                normalLog.error("找不到玩家对应的城池信息:${player.focusCastleId}")
                return
            }

            // 计算行军时间
            val walkTimeRt = calWalkTime(
                areaCache,
                newGroup.mainPlayerId,
                newGroup.gotoRunType,
                force.soliderMap,
                startX,
                startY,
                castle.x,
                castle.y,
                isAtkMonsterHome,
                false,
                true
            )
            val arrivalsTime = now + walkTimeRt.walkTime * 1000

            createWalkOnMap(
                areaCache,
                WalkGoHome,
                arrivalsTime,
                walkTimeRt.walkSpeed,
                startX,
                startY,
                castle.x,
                castle.y,
                now,
                newGroup.id,
                boolToInt(isAtkMonsterHome)
            )

            noticeSelfWalkForceGroup(areaCache, Add, newGroup)
        }
    }

    // 通知行军目标联盟成员更新
    if (walkForceGroup.gotoRunType == WalkReinforce) {
        val allMass = areaCache.massCache.findAllMassByPos(startX, startY)
        for (mass in allMass) {
            if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
                noticeReinforceMassGroup(areaCache, Update, mass)
            }
        }
    }

    // 刷新预警
    updateWarnWithPos(areaCache, startX, startY)
}

// 领主回家  释放回家
fun mainHeroHome(areaCache: AreaCache, startX: Int, startY: Int, playerId: Long) {
    val player = areaCache.playerCache.findPlayerById(playerId)
    if (player == null) {
        normalLog.error("找不到玩家信息:$playerId")
        return
    }

    val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
    if (castle === null) {
        normalLog.error("找不到玩家对应的城池信息:${player.focusCastleId}")
        return
    }

    val group =
        areaCache.walkForceGroupCache.createWalkForceGroup(playerId, 0, Running, WalkMainHeroGoHome, startX, startY)
    val now = getNowTime()
    val walkTimeRt = calWalkTime(
        areaCache,
        group.mainPlayerId,
        WalkMainHeroGoHome,
        hashMapOf(),
        startX,
        startY,
        castle.x,
        castle.y,
        false,
        false,
        true
    )
    val arrivalsTime = now + walkTimeRt.walkTime * 1000
    createWalkOnMap(
        areaCache,
        WalkMainHeroGoHome,
        arrivalsTime,
        walkTimeRt.walkSpeed,
        startX,
        startY,
        castle.x,
        castle.y,
        now,
        group.id,
        0
    )

    // 通知新组的行军组信息
    noticeSelfWalkForceGroup(areaCache, Add, group)
}

//删除部队
fun removeForce(areaCache: AreaCache, force: WalkForce) {
    // 删除部队
    areaCache.walkForceCache.delWalkForce(force)

    // 返回士兵
    for ((soliderId, soliderNum) in force.soliderMap) {
        //到家了,把剩余的兵还给玩家
        addSolider(areaCache, force.playerId, soliderId, soliderNum)
    }

    // 修改英雄位置状态
    val heroChangeMsg = createValueChgNotifier()
    for (heroId in force.heroIdList) {
        val hero = areaCache.heroCache.findHeroById(force.playerId, heroId)
        if (hero == null) {
            normalLog.error("找不到英雄信息:$heroId")
            continue
        }
        hero.posState = IN_CITY // 修改英雄状态！
        heroChangeMsg.append(hero.id, HERO_POS_STATE, hero.posState.toLong())
    }

    fetchOnlinePlayerSession(areaCache, force.playerId)?.let {
        heroChangeMsg.notice(it)
    }
}