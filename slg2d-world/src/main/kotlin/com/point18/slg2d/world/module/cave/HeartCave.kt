package com.point18.slg2d.world.module.cave

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.constg.CaveEnd
import com.point18.slg2d.common.constg.IN_HIDE
import com.point18.slg2d.common.constg.MAIN_HERO
import com.point18.slg2d.common.constg.MAIN_HERO_STATE
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Cave
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.createCaveChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier

class CaveHeartHandler: IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        caveHeart(cache)
    }

    private fun caveHeart(areaCache: AreaCache) {
        var i = 0
        while (true) {
            val caveHeart = areaCache.caveCache.peekCaveHeart()
            if (caveHeart == null) {
                // 没有东西需要处理了
                return
            }
            if (caveHeart.hideOverTime == 0L) {
                // 弹出目标元素
                areaCache.caveCache.popCaveHeart()
                continue
            }

            // 弹出目标元素
            areaCache.caveCache.popCaveHeart()

            caveOver(areaCache, caveHeart)

            i++
            if (i > 100000) {
                return
            }
        }
    }
}


//藏兵结束
fun caveOver(areaCache: AreaCache, cave: Cave): Int {
    val player = areaCache.playerCache.findPlayerById(cave.playerId)

    if (player == null) {
        return ResultCode.PARAMETER_ERROR.code
    }

    val caveGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(cave.hideForceGroupId)
    if (caveGroup == null) {
        return ResultCode.PARAMETER_ERROR.code
    }

    // 设置领主状态
    val mainHero = areaCache.heroCache.findHeroById(player.id, player.mainHeroId)
    if (mainHero != null && mainHero.mainHeroState == IN_HIDE) {
        mainHero.mainHeroState = MAIN_HERO

        val session = fetchOnlinePlayerSession(areaCache, player.id)
        if (session != null) {
            val valueChgNotifier = createValueChgNotifier()
            valueChgNotifier.append(mainHero.id, MAIN_HERO_STATE, mainHero.mainHeroState.toLong())
            valueChgNotifier.notice(session)
        }
    }

    // 返还藏兵
    val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(cave.hideForceGroupId)
    for (force in forces) {
        for ((id, num) in force.soliderMap) {
            com.point18.slg2d.world.common.addSolider(areaCache, player.id, id, num)
        }

        // 删除部队
        areaCache.walkForceCache.delWalkForce(force)
    }
    areaCache.walkForceGroupCache.delWalkForceGroup(caveGroup)
    areaCache.caveCache.deleteCave(cave)

    val session = fetchOnlinePlayerSession(areaCache, player.id)
    if (session != null) {
        createCaveChangeNotifier(CaveEnd, null, null, null, 0).notice(session)
    }

    return ResultCode.SUCCESS.code
}
