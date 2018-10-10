package com.point18.slg2d.world.module.moveCity

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.SNOW_AREA
import com.point18.slg2d.common.constg.WONDER_BLACK
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.event.DefCastle

class PvpDefCastleLoseEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val eventData = event as DefCastle
        val losePlayer = cache.playerCache.findPlayerById(eventData.defPlayerId)
        if (losePlayer == null) {
            normalLog.error("moveCity : losePlayer == null ")
            return
        }
        val castle = cache.castleCache.findCastleById(losePlayer.focusCastleId)
        if (castle == null) {
            normalLog.error("moveCity : castle == null ")
            return
        }
        val oldX = castle.x
        val oldY = castle.y
        val (_, blackArea) = pcs.wonderLocationProtoCache.findInWonderType(oldX, oldY)
        val (_, snowArea) = pcs.monsterActivityLocationProtoCache.findActivityBossAreaType(oldX, oldY)

        if (blackArea != WONDER_BLACK && snowArea != SNOW_AREA) {
            return
        }

        // 随机迁城
        randomFlyCastleAway(cache, eventData.defPlayerId)

        // 根据地块类型获取归属时间
        val belongTime = when {
            blackArea == WONDER_BLACK -> getNowTime() + pcs.basicProtoCache.wonderBlackBelongTime * 1000
            snowArea == SNOW_AREA -> getNowTime() + pcs.basicProtoCache.snowBelongTime * 1000
            else -> 0
        }

        // 创建属地
        val belongCell = cache.belongCellCache.findBelongCellByXy(oldX, oldY)
        if (belongCell == null) {
            cache.belongCellCache.createBelongCell(
                oldX,
                oldY,
                belongTime,
                eventData.atkPlayerId,
                eventData.defPlayerId,
                eventData.atkBattleRs,
                eventData.defBattleRs
            )
        } else {
            belongCell.overTime = belongTime
            belongCell.playerId = eventData.atkPlayerId
        }
        noticeCellUpdate(cache, oldX, oldY)
    }
}