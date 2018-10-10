package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.BUFF_REMOVE
import com.point18.slg2d.common.constg.GET_BUFF
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.WalkHotTimeAdd
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.event.GetNewBuff
import com.point18.slg2d.world.msgnotice.createBuffChangeNotifier
import com.point18.slg2d.world.wpm

// 触发战争狂热
fun addWalkHot(areaCache: AreaCache, player: Player) {
    val walkHotBuffBasicId = pcs.basicProtoCache.warCrazy
    val proto = pcs.buffBasicProtoCache.protoMap[walkHotBuffBasicId]
    if (proto == null) {
        assert(false) { "找不到战争狂热buff$walkHotBuffBasicId" }
    } else {
        val buffTime = getResearchEffectValue(areaCache, NO_FILTER, player, WalkHotTimeAdd)
        if (buffTime != 0) {
            val buffOverTime = getNowTime() + buffTime * 1000
            wpm.es.fire(
                areaCache, player.id, GET_BUFF,
                GetNewBuff(player.id, walkHotBuffBasicId, buffOverTime)
            )
        }
    }
}

// 去除保护罩效果
fun removeCover(areaCache: AreaCache, coverTypes: Array<Int>, player: Player, session: PlayerSession?) {
    for (coverType in coverTypes) {
        val (have, _, delBuff) =
            areaCache.buffCache.findBuffValueByBuffType(player.id, coverType)

        if (!have || delBuff == null) return

        areaCache.buffCache.deleteBuff(delBuff)

        if (session != null) {
            val notifier = createBuffChangeNotifier(
                BUFF_REMOVE,
                delBuff.id,
                delBuff.protoId,
                delBuff.overTime,
                delBuff.startTime
            )
            notifier.notice(session)
        }
    }
}