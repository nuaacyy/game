package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Mass
import com.point18.slg2d.world.area4data.WalkForce
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.createMassGroupChangeNotifier
import pb4client.*
import java.util.*

//生成MassGroup
fun createMassGroupBuilder(areaCache: AreaCache, mass: Mass): MassGroup.Builder? {
    val massGroupBuilder = MassGroup.newBuilder()
    massGroupBuilder.massId = mass.id
    massGroupBuilder.mainPlayerId = mass.mainPlayerId
    massGroupBuilder.runType = mass.fightType
    massGroupBuilder.massName = mass.massName
    massGroupBuilder.startX = mass.startX
    massGroupBuilder.startY = mass.startY
    massGroupBuilder.gotoX = mass.gotoX
    massGroupBuilder.gotoY = mass.gotoY
    massGroupBuilder.massState = mass.massState
    massGroupBuilder.massTime = getTimeSec(mass.goTime)
    massGroupBuilder.massStartTime = getTimeSec(mass.massStartTime)
    massGroupBuilder.arriveTime = getTimeSec(mass.arriveTime)
    massGroupBuilder.groupId = mass.groupId
    massGroupBuilder.selfPlayer = getMassPlayer(areaCache, mass.mainPlayerId)
    val mainPlayer = areaCache.playerCache.findPlayerById(mass.mainPlayerId)
    if (mainPlayer == null) {
        normalLog.error("MassGroupHelper.kt:  mainPlayer == null ")
        return null
    }
    var addedSoliderNum = 0
    val groupId: Long
    if (mass.massState == Run) {
        groupId = mass.groupId
        val walkLine = areaCache.walkCache.findWalkByGroupId(groupId)
        if (walkLine != null) {
            massGroupBuilder.initialWalkTime = (walkLine.initialWalkTime / 1000)
        }
    } else {
        val member = mass.findMassMember(mass.mainPlayerId)
        if (member == null) {
            normalLog.error("MassGroupHelper.kt:  member == null ")
            return null
        }
        groupId = member.groupId
    }
    val targetForces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(groupId)
    for (force in targetForces) {
        if (force.playerId != mass.mainPlayerId) {
            continue
        }
        for ((_, num) in force.soliderMap) {
            addedSoliderNum += num
        }
    }

    val baseNum = getResearchEffectValue(areaCache, NO_FILTER, mainPlayer, JijieTroopMaxAdd)
    massGroupBuilder.maxSolider = baseNum + addedSoliderNum
    when (mass.fightType) {
        WalkRelic -> {
            val cell = areaCache.relicCache.findRelicByXY(mass.gotoX, mass.gotoY)
            if (cell != null) {
                massGroupBuilder.targetId = cell.relicId.toLong()
            }
        }
        WalkFightPlayer -> {
            val castle = areaCache.castleCache.findCastleByXy(mass.gotoX, mass.gotoY)
            if (castle != null) {
                massGroupBuilder.targetPlayer = getMassPlayer(areaCache, castle.playerId)
            }
        }
        WalkOccupyWonder -> {
        }
    }
    val forces = LinkedList<WalkForce>()
    if (mass.massState == Run) {
        forces += areaCache.walkForceCache.findWalkForceByWalkForceGroupId(mass.groupId)
    } else {
        for (member in mass.memberInfoList) {
            forces += areaCache.walkForceCache.findWalkForceByWalkForceGroupId(member.groupId)
        }
    }
    //添加部队信息
    forceToMsgBuilder(areaCache, forces).forEach { massGroupBuilder.addForces(it) }
    return massGroupBuilder
}

// 给集结方联盟成员发MassGroupChange_3143
fun noticeMassGroup(areaCache: AreaCache, changeType: Int, mass: Mass) {
    val massGroup: MassGroup.Builder
    if (changeType == Del) {
        val builder = MassGroup.newBuilder()
        builder.massId = mass.id
        massGroup = builder
    } else {
        val tmp = createMassGroupBuilder(areaCache, mass)
        if (tmp == null) {
            normalLog.error("MassGroupHelper.kt:  createMassGroupBuilder(areaCache, mass) == null ")
            return
        }
        massGroup = tmp
    }
    val player = areaCache.playerCache.findPlayerById(mass.mainPlayerId)
    if (player == null) {
        normalLog.error("MassGroupHelper.kt:  areaCache.playerCache.findPlayerById(mass.mainPlayerId) == null ")
        return
    }

    val members = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
    for (member in members) {
        val session = fetchOnlinePlayerSession(areaCache, member.id)
        if (session != null) {
            createMassGroupChangeNotifier(changeType, massGroup).notice(session)
        }

        // 集结玩家的联盟其他成员app通知
        if ((mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) && changeType == Add) {
            val action = if (mass.goTime - mass.massStartTime < 3600 * 1000) {
                ALLIANCE_MASS_1H
            } else {
                ALLIANCE_MASS_8H
            }
            areaCache.pushAppNotice(
                member.id,
                action,
                0
            )
        }
        // 集结遗迹app通知
        if (mass.fightType == WalkRelic && changeType == Add) {
            areaCache.pushAppNotice(
                member.id,
                ALLIANCE_MASS_RELIC,
                0
            )
        }
    }
}

fun noticeDelMassGroup(areaCache: AreaCache, massId: Long, playerIds: List<Long>) {
    val builder = MassGroup.newBuilder()
    builder.massId = massId
    for (playerId in playerIds) {
        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            createMassGroupChangeNotifier(Del, builder).notice(session)
        }
    }
}

fun createReinforceMassGroupBuilder(areaCache: AreaCache, mass: Mass): MassGroup.Builder? {
    val builder = MassGroup.newBuilder()
    builder.massId = mass.id
    builder.mainPlayerId = mass.mainPlayerId
    builder.runType = mass.fightType
    builder.massName = mass.massName
    builder.startX = mass.startX
    builder.startY = mass.startY
    builder.gotoX = mass.gotoX
    builder.gotoY = mass.gotoY
    builder.massState = mass.massState
    builder.massTime = getTimeSec(mass.goTime)
    builder.massStartTime = getTimeSec(mass.massStartTime)
    builder.arriveTime = getTimeSec(mass.arriveTime)
    builder.groupId = mass.id

    builder.selfPlayer = getMassPlayer(areaCache, mass.mainPlayerId)

    when (mass.fightType) {
        WalkFightPlayer -> {
            val castle = areaCache.castleCache.findCastleByXy(mass.gotoX, mass.gotoY)
            if (castle == null) {
                normalLog.error("MassGroupHelper.kt:  areaCache.castleCache.findCastleByXy(mass.gotoX, mass.gotoY) == null ")
                return null
            }
            val targetPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
            if (targetPlayer == null) {
                normalLog.error("MassGroupHelper.kt:  areaCache.playerCache.findPlayerById(castle.playerId) == null ")
                return null
            }
            builder.maxSolider = getResearchEffectValue(areaCache, NO_FILTER, targetPlayer, ReinforceTroopsMaxAdd)
            builder.targetPlayer = getMassPlayer(areaCache, castle.playerId)
        }
        WalkOccupyWonder -> {
            val (wonderProto, _) = pcs.wonderLocationProtoCache.findInWonderType(mass.gotoX, mass.gotoY)
            val wonder = if (wonderProto != null) {
                areaCache.wonderCache.findWonder(wonderProto.id)
            } else null
            if (wonder == null) {
                normalLog.error("MassGroupHelper.kt: 集结目标点不是奇观")
                return null
            }
            val occupyGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(wonder.occupyGroupId)
            if (occupyGroup == null) {
                builder.maxSolider = 0
            } else {
                val commandPlayer = areaCache.playerCache.findPlayerById(occupyGroup.mainPlayerId)
                if (commandPlayer == null) {
                    normalLog.error("MassGroupHelper.kt:  commandPlayer == null ")
                    return null
                }
                builder.maxSolider = getResearchEffectValue(areaCache, NO_FILTER, commandPlayer, ReinforceTroopsMaxAdd)
            }
        }
    }

    //添加部队信息
    addReinforce(
            areaCache,
            mass.gotoX,
            mass.gotoY,
            Running or Reinforce
    ).forEach { builder.addForces(it) }
    return builder
}

fun addReinforce(areaCache: AreaCache, posX: Int, posY: Int, state: Int): List<MassForce.Builder> {
    //添加部队信息
    val totalForces = LinkedList<WalkForce>()
    val groups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(posX, posY, state)
    for (group in groups) {
        if (group.runningState == Running) {
            val walk = areaCache.walkCache.findWalkByGroupId(group.id)
            if (walk == null) {
                //Assert
                continue
            }
            // 行军中的，要判断出征状态
            var stateOk = false
            if (walk.marchState == WalkReinforce && state and Reinforce == Reinforce) {
                // 出征的状态是增援, 查询的状态也包含增援
                stateOk = true
            } else if (walk.marchState == WalkStation && state and Stationed == Stationed) {
                // 出征的状态是驻扎, 查询的状态也包含驻扎
                stateOk = true
            } else if (walk.marchState == WalkReinforceWonder && state and Reinforce == Reinforce) {
                // 出征状态是奇观增援，查询的状态包含增援
                stateOk = true
            } else {
                // 其他状态暂不做处理
            }
            if (!stateOk) {
                continue
            }
        }
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
        totalForces += forces
    }
    return forceToMsgBuilder(areaCache, totalForces)
}

// 给被集结玩家联盟成员发送MassGroupChange_3143
fun noticeReinforceMassGroup(areaCache: AreaCache, changeType: Int, mass: Mass) {

    val castle = areaCache.castleCache.findCastleByXy(mass.gotoX, mass.gotoY)
    val (wonderProto, _) = pcs.wonderLocationProtoCache.findInWonderType(mass.gotoX, mass.gotoY)
    val wonder = if (wonderProto != null) {
        areaCache.wonderCache.findWonder(wonderProto.id)
    } else null

    val allianceId = when {
        castle != null -> {
            val targetPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
            if (targetPlayer == null) {
                normalLog.error("MassGroupHelper.kt:  找不到城堡玩家")
                return
            }
            targetPlayer.allianceId
        }
        wonder != null -> wonder.belongToAllianceId
        else -> {
            normalLog.error("MassGroupHelper.kt:  集结目标点既不是城堡又不是奇观")
            return
        }
    }

    val massGroup: MassGroup.Builder?
    if (changeType == Del) {
        val builder = MassGroup.newBuilder()
        builder.massId = mass.id
        massGroup = builder
    } else {
        massGroup = createReinforceMassGroupBuilder(areaCache, mass)
    }
    if (massGroup == null) {
        normalLog.error("MassGroupHelper.kt:  massGroup == null ")
        return
    }

    val members = areaCache.playerCache.findPlayersByAllianceId(allianceId)
    for (member in members) {
        val session = fetchOnlinePlayerSession(areaCache, member.id)
        if (session != null) {
            createMassGroupChangeNotifier(changeType, massGroup).notice(session)
        }

        // 遭到集结的联盟其他成员app通知
        if (mass.fightType == WalkFightPlayer && changeType == Add && castle != null) {
            if (castle.playerId != member.id) {
                val action = if (mass.goTime - mass.massStartTime < 3600 * 1000) {
                    ALLIANCE_BE_MASSED_1H
                } else {
                    ALLIANCE_BE_MASSED_8H
                }
                areaCache.pushAppNotice(
                    member.id,
                    action,
                    0
                )
            }
        }
        if (mass.fightType == WalkOccupyWonder && changeType == Add) {
            areaCache.pushAppNotice(
                member.id,
                WONDER_BE_MASSED,
                0
            )
        }
    }
}

//获取集结成员信息
fun getMassPlayer(areaCache: AreaCache, playerId: Long): MassPlayer? {
    val targetPlayer = areaCache.playerCache.findPlayerById(playerId)
    if (targetPlayer == null) {
        normalLog.error("MassGroupHelper.kt:  areaCache.playerCache.findPlayerById(playerId) == null ")
        return null
    }
    val builder = MassPlayer.newBuilder()
    builder.playerId = targetPlayer.id
    builder.name = targetPlayer.name
    builder.photo = targetPlayer.photoProtoId
    builder.allianceId = targetPlayer.allianceId
    builder.allianceName = targetPlayer.allianceName
    builder.allianceShortName = targetPlayer.allianceShortName
    return builder.build()
}

//部队转换成消息
fun forceToMsgBuilder(areaCache: AreaCache, forces: List<WalkForce>): List<MassForce.Builder> {
    val massForces = LinkedList<MassForce.Builder>()
    for (force in forces) {
        val forceBuilder = MassForce.newBuilder()
        forceBuilder.player = getMassPlayer(areaCache, force.playerId)
        forceBuilder.groupId = force.forceGroupId
        val walkLine = areaCache.walkCache.findWalkByGroupId(force.forceGroupId)
        if (walkLine != null) {
            forceBuilder.startTime = getTimeSec(walkLine.marchTimeOff)
            forceBuilder.arriveTime = getTimeSec(walkLine.marchTimeArrival)
            forceBuilder.initialWalkTime = walkLine.initialWalkTime
        }
        for (heroId in force.heroIdList) {
            val hero = areaCache.heroCache.findHeroById(force.playerId, heroId)
            if (hero == null) {
                normalLog.error("MassGroupHelper.kt:  areaCache.heroCache.findHeroById(force.playerId, heroId) == null ")
                continue
            }
            val heroBuilder = HeroForWalk.newBuilder()
            heroBuilder.protoId = hero.protoId
            heroBuilder.lv = hero.level
            heroBuilder.starLv = hero.advLv
            heroBuilder.awake = hero.awake
            heroBuilder.isLord = boolToInt(hero.mainHeroState != 0)
            forceBuilder.addHeros(heroBuilder)
        }
        for ((id, num) in force.soliderMap) {
            val soliderBuilder = SoliderForWalk.newBuilder()
            soliderBuilder.propId = id
            soliderBuilder.num = num
            forceBuilder.addSoliders(soliderBuilder)
        }

        massForces.add(forceBuilder)
    }
    return massForces
}

//更新集结坐标
fun updateMassPos(areaCache: AreaCache, oldX: Int, oldY: Int, newX: Int, newY: Int) {
    val allGotoMass = areaCache.massCache.findAllMassByPos(oldX, oldY)
    for (mass in allGotoMass) {
        if (mass.massState == Run) {
            continue
        }
        mass.gotoX = newX
        mass.gotoY = newY
        noticeMassGroup(areaCache, Update, mass)
    }
}
