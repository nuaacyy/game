package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.TotalCureNum
import com.point18.slg2d.common.constg.isSolider
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Barracks
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.syncBarrack2Home
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import java.util.Arrays.asList

class CureAllEventSoldierEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        cureAllEventSolider(cache)
    }

    private fun cureAllEventSolider(areaCache: AreaCache) {
        areaCache.barracksCache.allBarracksMap.map {
            if (it.canEventCureNum > 0 || it.nowEventCureNum > 0) {
                eventOverCureSoliderAtOnce(areaCache, it)
            }
            true
        }
    }

    private fun eventOverCureSoliderAtOnce(areaCache: AreaCache, barracksVo: Barracks) {
        val cureNum = barracksVo.nowEventCureNum
        val canEventCureNum = barracksVo.canEventCureNum
        barracksVo.soldierNum = barracksVo.soldierNum + cureNum + canEventCureNum

        barracksVo.canEventCureNum = 0
        barracksVo.nowEventCureNum = 0
        barracksVo.eventCureNeedTime = 0
        barracksVo.eventCureOverTime = 0
        barracksVo.eventCureQueue = 0

        //添加统计
        val soliderProto = pcs.soliderCache.soliderProtoMap[barracksVo.soldierId] ?: return
        areaCache.playerCache.findPlayerById(barracksVo.playerId) ?: return
        if (isSolider(soliderProto.armyType)) {
            targetAddVal(
                areaCache,
                barracksVo.playerId,
                TotalCureNum,
                asList(cureNum.toLong())
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