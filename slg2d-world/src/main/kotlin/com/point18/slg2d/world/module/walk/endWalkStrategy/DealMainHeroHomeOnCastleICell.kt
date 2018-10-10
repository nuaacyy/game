package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.noticeSelfWalkForceGroup

class DealMainHeroHomeOnCastleICell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        val playerId = group.mainPlayerId
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("行军组的玩家信息不存在，行军组Id:%d", group.id)
            return
        }
        val mainHero = areaCache.heroCache.findHeroById(playerId, player.mainHeroId)
        if (mainHero == null) {
            normalLog.error("找不到玩家的领主信息，玩家Id:%d", player.id)
            return
        }
        mainHero.posState = IN_CITY
        mainHero.mainHeroState = MAIN_HERO

        //推送领主属性变化
        fetchOnlinePlayerSession(areaCache, playerId)?.let {
            val valueChgNotifier = createValueChgNotifier()
            valueChgNotifier.append(
                mainHero.id,
                MAIN_HERO_STATE,
                mainHero.mainHeroState.toLong()
            )
            valueChgNotifier.append(mainHero.id, HERO_POS_STATE, mainHero.posState.toLong())
            valueChgNotifier.notice(it)
        }

        // 通知删除行军组
        noticeSelfWalkForceGroup(areaCache, Del, group)

        areaCache.walkForceGroupCache.delWalkForceGroup(group)
    }
}