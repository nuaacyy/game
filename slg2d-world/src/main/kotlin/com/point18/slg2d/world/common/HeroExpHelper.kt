package com.point18.slg2d.world.common

import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import pb4client.HeroInfoForReport
import pb4server.AddHeroExpAskReq
import pb4server.World2HomeAskResp

// 增加武将经验
fun addHeroExp(areaCache: AreaCache, playerId: Long, heroId: Long, exp: Int) {
    val reqMsg = AddHeroExpAskReq.newBuilder()
    reqMsg.heroId = heroId
    reqMsg.addExp = exp
    areaCache.worldActor.createACS<World2HomeAskResp>()
        .ask(
            areaCache.worldActor.homeShardRegion,
            areaCache.fillW2HAskMsgHeader(playerId) {
                it.setAddHeroExpAskReq(reqMsg)
            },
            World2HomeAskResp::class.java
        )
        .whenCompleteKt { rt, err ->
            //todo 增加经验返回
        }
}

data class CalHeroRealAddExpRt(
    val realAddExp: Int,
    var expOverFlow: Boolean
)

//计算实际增加的英雄经验
fun calHeroRealAddExp(heroLv: Int, heroExp: Int, addExp: Int, playerLv: Int): CalHeroRealAddExpRt {
    val maxLv = pcs.kingExpCache.kingExpMap[playerLv]?.heroLevelTop ?: pcs.kingExpCache.maxLvProto.heroLevelTop
    if (heroLv >= maxLv) {
        return CalHeroRealAddExpRt(0, true)
    }
    var realAddExp = 0
    var canAddExp = addExp
    var curExp = heroExp
    var newLv = heroLv
    for (i in 0 until 200) {
        if (canAddExp <= 0) {
            break
        }

        val expProto = pcs.heroLevelUpCache.fetchLevelUpProto(newLv)
        if (expProto == null) {
            break
        }

        val maxExp = expProto.exp
        if (curExp + canAddExp >= maxExp) {
            canAddExp -= maxExp - curExp
            realAddExp += maxExp - curExp
            curExp = 0
            newLv++
        } else {
            curExp += canAddExp
            realAddExp += canAddExp
            canAddExp = 0
            break
        }

        if (newLv >= maxLv) {
            break
        }
    }
    return CalHeroRealAddExpRt(realAddExp, canAddExp != 0)
}

fun genHeroInfoForReport(areaCache: AreaCache, playerId: Long, heroId: Long, addExp: Int): HeroInfoForReport.Builder? {
    val player = areaCache.playerCache.findPlayerById(playerId) ?: return null
    val hero = areaCache.heroCache.findHeroById(playerId, heroId) ?: return null
    val heroBuilder = HeroInfoForReport.newBuilder()
    heroBuilder.protoId = hero.protoId
    heroBuilder.lv = hero.level
    heroBuilder.starLv = hero.advLv
    heroBuilder.grade = hero.awake
    heroBuilder.isLarid = boolToInt(player.mainHeroId == heroId)
    heroBuilder.curExp = hero.exp
    val calRealAddExpRt = calHeroRealAddExp(hero.level, hero.exp, addExp, player.kingLv)
    heroBuilder.addExp = calRealAddExpRt.realAddExp
    heroBuilder.expOverFlow = boolToInt(calRealAddExpRt.expOverFlow)
    return heroBuilder
}