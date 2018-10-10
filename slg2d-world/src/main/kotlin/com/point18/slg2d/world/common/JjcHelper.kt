package com.point18.slg2d.world.common

import com.point18.slg2d.common.constg.JjcDef
import com.point18.slg2d.common.constg.JjcFight
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.findJjcRobotByRank
import pb4client.HeroEquipVo
import pb4client.JjcChallengeInfo
import pb4client.JjcHeroGenral
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

fun fetchChallenge(areaCache: AreaCache, rank: Int): JjcChallengeInfo.Builder {
    val jjcChallengeInfoBuilder = JjcChallengeInfo.newBuilder()
    jjcChallengeInfoBuilder.rank = rank

    // 尝试根据排名去找玩家
    val playerM = areaCache.playerCache.findPlayerByJjcRank(rank)
    if (playerM == null) {
        // 如果不是玩家，那么就是竞技场NPC
        if (rank in 1..10) {
            for ((_, proto) in pcs.jjcNpcCache.protoMap) {
                if (proto.rankList[0] <= rank && rank <= proto.rankList[1]) {
                    val tmp = findJjcRobotByRank(rank)
                    jjcChallengeInfoBuilder.protoRobotId = tmp
                    jjcChallengeInfoBuilder.rank = rank
                    jjcChallengeInfoBuilder.playerId = 0
                    jjcChallengeInfoBuilder.aid = 0
                    jjcChallengeInfoBuilder.aName = ""
                    jjcChallengeInfoBuilder.aShortName = ""
                    jjcChallengeInfoBuilder.power = 0
                    jjcChallengeInfoBuilder.photoProtoId = 0
                    jjcChallengeInfoBuilder.addAllJjcHeros(LinkedList())

                    return jjcChallengeInfoBuilder
                }
            }

        }

        for ((_, proto) in pcs.jjcNpcCache.protoMap) {
            if (proto.rankList[0] <= rank && rank < proto.rankList[1]) {
                val tmp = findJjcRobotByRank(rank)
                jjcChallengeInfoBuilder.protoRobotId = tmp
                jjcChallengeInfoBuilder.rank = rank
                jjcChallengeInfoBuilder.playerId = 0
                jjcChallengeInfoBuilder.aid = 0
                jjcChallengeInfoBuilder.aName = ""
                jjcChallengeInfoBuilder.aShortName = ""
                jjcChallengeInfoBuilder.power = 0
                jjcChallengeInfoBuilder.photoProtoId = 0
                jjcChallengeInfoBuilder.addAllJjcHeros(LinkedList())
                break
            }
        }

        return jjcChallengeInfoBuilder
    }

    // 玩家ID、玩家名、头像ID
    jjcChallengeInfoBuilder.playerId = playerM.id
    jjcChallengeInfoBuilder.playerName = playerM.name
    jjcChallengeInfoBuilder.photoProtoId = playerM.photoProtoId

    // 拿出全部防守英雄计算实力
    var powerOfDefHero = 0L
    val defForces = areaCache.armyPlanCache.findArmyPlan(playerM.id, JjcFight, JjcDef)
    if (defForces == null) {
        jjcChallengeInfoBuilder.power = powerOfDefHero
    } else {
        val defHeroMap = defForces.heroMap
        for ((_, heroId) in defHeroMap) {
            val tmpHero = areaCache.heroCache.findHeroById(playerM.id, heroId) ?: continue
            powerOfDefHero += tmpHero.heroStrength
        }
        jjcChallengeInfoBuilder.power = powerOfDefHero // 实力值
    }

    // 联盟ID、联盟名称、联盟简称
    jjcChallengeInfoBuilder.aid = playerM.allianceId
    jjcChallengeInfoBuilder.aName = playerM.allianceName
    jjcChallengeInfoBuilder.aShortName = playerM.allianceShortName
    val mainHero = areaCache.heroCache.findHeroById(playerM.id, playerM.mainHeroId)
    if (mainHero != null) {
        jjcChallengeInfoBuilder.mainHeroId = mainHero.protoId
    }

    // todo 竞技场布阵英雄
    val defPlan = areaCache.armyPlanCache.findArmyPlan(playerM.id, JjcFight, JjcDef)
    if (defPlan != null) {
        for ((pos, heroId) in defPlan.heroMap) {
            val tmp = areaCache.heroCache.findHeroById(playerM.id, heroId)
            if (tmp == null) {
                continue
            }
            val heroProperty = generateHeroRefPropsByDbData(areaCache, tmp)
            val tmpHeroInfo = JjcHeroGenral.newBuilder()
            tmpHeroInfo.heroProto = tmp.protoId
            tmpHeroInfo.heroStarLv = tmp.advLv
            tmpHeroInfo.heroLv = tmp.level
            tmpHeroInfo.awake = tmp.awake
            // todo 装备技能
            tmpHeroInfo.skill1 = tmp.skillId1
            tmpHeroInfo.skill2 = tmp.skillId2
            tmpHeroInfo.skill3 = tmp.skillId3
            tmpHeroInfo.skill4 = tmp.skillId4

            for ((eId, eInfo) in heroProperty.heroEquips) {
                val heroEquipVoBuilder = HeroEquipVo.newBuilder()
                heroEquipVoBuilder.heroTrophiesId = eId
                heroEquipVoBuilder.advLv = eInfo.advLv
                tmpHeroInfo.addHeroEquipVos(heroEquipVoBuilder)
            }

            jjcChallengeInfoBuilder.addJjcHeros(tmpHeroInfo)
        }
    }

    return jjcChallengeInfoBuilder
}

//获取竞技场刷新时间
fun getJjcShopRefreshTime(assignTime: Long): Long {
    val refreshHour = pcs.basicProtoCache.getVipRewardRefreshHour
    val refreshMinute = pcs.basicProtoCache.getVipRewardRefreshMinute

    val instance = Instant.ofEpochMilli(assignTime)
    val localDate = ZonedDateTime.ofInstant(instance, ZoneId.systemDefault())
//    val zoneDateTime = ZonedDateTime.of(localDate,ZoneId.systemDefault())
//    zoneDateTime.toInstant()

    val localYear = localDate.year
    val localMouth = localDate.month
    val localDay = localDate.dayOfMonth
    val localHour = localDate.hour
    val localMinute = localDate.minute
    val localSec = localDate.second

    val refreshTime1 = ZonedDateTime.of(localYear, localMouth.value, localDay
        , refreshHour, refreshMinute, 0, 0, ZoneId.systemDefault()).minusHours(24)

    val refreshTime2 = ZonedDateTime.of(localYear, localMouth.value, localDay
        , refreshHour, refreshMinute, 0, 0, ZoneId.systemDefault())

    if (localDate.isBefore(refreshTime2)) {
        return refreshTime1.toInstant().toEpochMilli()
    } else {
        return refreshTime2.toInstant().toEpochMilli()
    }

}


