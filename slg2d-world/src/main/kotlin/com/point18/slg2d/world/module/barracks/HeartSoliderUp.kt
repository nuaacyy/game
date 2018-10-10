package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.constg.SoliderStrength
import com.point18.slg2d.common.constg.TrapStrength
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Barracks
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.syncBarrack2Home
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier

class SoldierUpHeartHandler : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        soliderUpHeart(cache)
    }

    /**
    晋升的心跳.
     */
    private fun soliderUpHeart(areaCache: AreaCache) {
        var i = 0
        while (true) {
            val makeSolider = areaCache.barracksCache.peekSoliderUpFinish()
            if (makeSolider == null) {
                // 没有东西需要处理了
                return
            }

            // 弹出目标元素
            areaCache.barracksCache.popSoliderUp()

            if (makeSolider.upOverTime == 0L) {
                // 什么都不需要做
                continue
            }

            soliderUp(areaCache, makeSolider)

            i++
            if (i > 100000) {
                return
            }
        }
    }

    private fun soliderUp(areaCache: AreaCache, barracksWrap: Barracks) {
        val targetBarracksWrap =
            areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(
                barracksWrap.playerId,
                barracksWrap.upToSoliderId
            )
        if (targetBarracksWrap == null) {
            return
        }

        val player = areaCache.playerCache.findPlayerById(barracksWrap.playerId)
        if (player == null) {
            return
        }
        targetBarracksWrap.soldierNum = targetBarracksWrap.soldierNum + barracksWrap.upNum
        barracksWrap.upNum = 0
        barracksWrap.upToSoliderId = 0
        areaCache.barracksCache.soliderUpUpdate(barracksWrap, 0)

        //重算军团实力
        targetAddVal(areaCache, barracksWrap.playerId, SoliderStrength)
        targetAddVal(areaCache, barracksWrap.playerId, TrapStrength)

        // 推送给客户端变更
        val session = fetchOnlinePlayerSession(areaCache, barracksWrap.playerId)
        if (session != null) {
            val notice = createBarracksChangeNotifier(barracksWrap)
            notice.notice(session)

            val targetNotice = createBarracksChangeNotifier(targetBarracksWrap)
            targetNotice.notice(session)
        }

        //同步兵营数据至home
        syncBarrack2Home(areaCache, barracksWrap.playerId)
    }


}

