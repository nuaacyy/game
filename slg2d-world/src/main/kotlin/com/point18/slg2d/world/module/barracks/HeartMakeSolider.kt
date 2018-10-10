package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Barracks
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.syncBarrack2Home
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.MakeSoliderEvent
import com.point18.slg2d.world.event.MakeTrapEvent
import com.point18.slg2d.world.event.PlayerActivityChange
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import com.point18.slg2d.world.wpm
import java.util.Arrays.asList

class MakeSoliderHeartHandler : IHeartHandler<AreaCache> {
    override fun handleHeart(cache: AreaCache) {
        makeSoliderHeart(cache)
    }

    /**
    造兵的心跳.
     */
    private fun makeSoliderHeart(areaCache: AreaCache) {
        var i = 0
        while (true) {
            val makeSolider = areaCache.barracksCache.peekMakeSoliderFinish()
            if (makeSolider == null) {
                // 没有东西需要处理了
                return
            }

            // 弹出目标元素
            areaCache.barracksCache.popMakeSolider()

            if (makeSolider.overTime == 0L) {
                // 什么都不需要做
                continue
            }

            makeSolider(areaCache, makeSolider)

            i++
            if (i > 100000) {
                return
            }
        }
    }

    private fun makeSolider(areaCache: AreaCache, barracksWrap: Barracks) {
        val addNum = barracksWrap.nowMakeNum
        barracksWrap.soldierNum = barracksWrap.soldierNum + barracksWrap.nowMakeNum
        barracksWrap.nowMakeNum = 0
        areaCache.barracksCache.makeSoliderUpdate(barracksWrap, 0L)

        //重算军团实力
        targetAddVal(
            areaCache,
            barracksWrap.playerId,
            SoliderStrength
        )
        targetAddVal(
            areaCache,
            barracksWrap.playerId,
            TrapStrength
        )

        //刷新维持消耗
        val player = areaCache.playerCache.findPlayerById(barracksWrap.playerId)
        if (player == null) {
            return
        }

        val soliderProto = pcs.soliderCache.soliderProtoMap[barracksWrap.soldierId]
        if (soliderProto == null) {
            return
        }
        if (isSolider(soliderProto.armyType)) {
            targetAddVal(
                areaCache,
                barracksWrap.playerId,
                MakeSoliderCount,
                asList(soliderProto.armyType.toLong(), soliderProto.step.toLong(), addNum.toLong())
            )
            wpm.es.fire(
                areaCache,
                barracksWrap.playerId,
                MAKE_SOLDIER_FINISH,
                MakeSoliderEvent(soliderProto.armyType, soliderProto.step, addNum)
            )
            // 发送app通知
            areaCache.pushAppNotice(
                barracksWrap.playerId,
                SOLDIER_MAKE_SETTING,
                0,
                soliderProto.name
            )
        } else {
            targetAddVal(
                areaCache,
                barracksWrap.playerId,
                MakeTrapCount,
                asList(soliderProto.armyType.toLong(), soliderProto.step.toLong(), addNum.toLong())
            )
            wpm.es.fire(
                areaCache,
                barracksWrap.playerId,
                MAKE_TRAP_FINISH,
                MakeTrapEvent(soliderProto.armyType, soliderProto.step, addNum)
            )
            // 发送app通知
            areaCache.pushAppNotice(
                barracksWrap.playerId,
                SOLDIER_MAKE_SETTING,
                0,
                soliderProto.name
            )
        }

        // 推送给客户端变更
        val session = fetchOnlinePlayerSession(areaCache, barracksWrap.playerId)
        if (session != null) {
            val notice = createBarracksChangeNotifier(barracksWrap)
            notice.notice(session)
        }

        var makeSoliderAction = 0
        when {
            soliderProto.step == 1 -> makeSoliderAction = MAKE_SOLIDER_1
            soliderProto.step == 2 -> makeSoliderAction = MAKE_SOLIDER_2
            soliderProto.step == 3 -> makeSoliderAction = MAKE_SOLIDER_3
            soliderProto.step == 4 -> makeSoliderAction = MAKE_SOLIDER_4
        }

        wpm.es.fire(
            areaCache,
            barracksWrap.playerId,
            PLAYER_ACTIVITY_CHANGE,
            PlayerActivityChange(makeSoliderAction, addNum, 0)
        )

        //同步兵营数据至home
        syncBarrack2Home(areaCache, barracksWrap.playerId)
    }
}


