package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.constg.CURE_SOLDIER
import com.point18.slg2d.common.constg.TotalCureNum
import com.point18.slg2d.common.constg.isSolider
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Barracks
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.syncBarrack2Home
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.CureSoliderEvent
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import com.point18.slg2d.world.wpm
import java.util.Arrays.asList

class CureSoliderHeartHandler : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        cureSoliderHeart(cache)
    }

    /**
    治疗兵的心跳.
     */
    private fun cureSoliderHeart(areaCache: AreaCache) {
        var i = 0
        while (true) {
            val cureSolider = areaCache.barracksCache.peekCureSoliderFinish()
            val eventCureSolider = areaCache.barracksCache.peekEventCureSoliderFinish()
            if (cureSolider == null && eventCureSolider == null) {
                // 没有东西需要处理了
                return
            }

            if (cureSolider != null) {
                // 弹出目标元素
                areaCache.barracksCache.popCureSolider()
                if (cureSolider.cureOverTime != 0L) {
                    cureSolider(areaCache, cureSolider)
                }
            }

            if (eventCureSolider != null) {
                // 弹出目标元素
                areaCache.barracksCache.popEventCureSolider()
                if (eventCureSolider.eventCureOverTime != 0L) {
                    eventCureSolider(areaCache, eventCureSolider)
                }
            }

            i++
            if (i > 100000) {
                return
            }
        }
    }

    private fun cureSolider(areaCache: AreaCache, barracksVo: Barracks) {
        val cureNum = barracksVo.nowCureNum
        barracksVo.soldierNum = barracksVo.soldierNum + cureNum
        barracksVo.canCureNum = barracksVo.canCureNum - cureNum
        if (barracksVo.canCureNum < 0) {
            barracksVo.canCureNum = 0
        }

        barracksVo.nowCureNum = 0
        areaCache.barracksCache.cureSoliderUpdate(barracksVo, 0)
        barracksVo.cureQueue = 0

        //添加统计
        val soliderProto = pcs.soliderCache.soliderProtoMap[barracksVo.soldierId]
        if (soliderProto == null) {
            return
        }
        val player = areaCache.playerCache.findPlayerById(barracksVo.playerId)
        if (player == null) {
            return
        }
        if (isSolider(soliderProto.armyType)) {
            targetAddVal(
                areaCache,
                barracksVo.playerId,
                TotalCureNum,
                asList(cureNum.toLong())
            )
            wpm.es.fire(
                areaCache, player.id, CURE_SOLDIER,
                CureSoliderEvent(cureNum)
            )
        }

        // 推送给客户端变更
        val session = fetchOnlinePlayerSession(areaCache, barracksVo.playerId)
        if (session != null) {
            val notice = createBarracksChangeNotifier(barracksVo)
            notice.notice(session)
        }

        //同步兵营数据至home
        syncBarrack2Home(areaCache, barracksVo.playerId)
    }

    private fun eventCureSolider(areaCache: AreaCache, barracksVo: Barracks) {
        val cureNum = barracksVo.nowEventCureNum
        barracksVo.soldierNum = barracksVo.soldierNum + cureNum
        barracksVo.canEventCureNum = barracksVo.canEventCureNum - cureNum
        if (barracksVo.canEventCureNum < 0) {
            barracksVo.canEventCureNum = 0
        }

        barracksVo.nowEventCureNum = 0
        areaCache.barracksCache.eventCureSoliderUpdate(barracksVo, 0)
        barracksVo.eventCureQueue = 0

        //添加统计
        val soliderProto = pcs.soliderCache.soliderProtoMap[barracksVo.soldierId]
        if (soliderProto == null) {
            return
        }
        val player = areaCache.playerCache.findPlayerById(barracksVo.playerId)
        if (player == null) {
            return
        }
        if (isSolider(soliderProto.armyType)) {
            targetAddVal(
                areaCache,
                barracksVo.playerId,
                TotalCureNum,
                asList(cureNum.toLong())
            )
            wpm.es.fire(
                areaCache, player.id, CURE_SOLDIER,
                CureSoliderEvent(cureNum)
            )
        }

        // 推送给客户端变更
        val session = fetchOnlinePlayerSession(areaCache, barracksVo.playerId)
        if (session != null) {
            val notice = createBarracksChangeNotifier(barracksVo)
            notice.notice(session)
        }

        //同步兵营数据至home
        syncBarrack2Home(areaCache, barracksVo.playerId)
    }
}

