package com.point18.slg2d.world.module.boss

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.sec2MilliSec
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.event.BossFight

class OnBossDieEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        //魔物死亡
        val eventData = event as BossFight
        if (eventData.remainingHp > 0) {
            return
        }

        //先刷出尸体资源地
        val monsterProto = pcs.monsterProtoCache.findMonsterProto(eventData.bossId)
        if (monsterProto == null) {
            normalLog.error("找不到魔物配置:%d", eventData.bossId)
            return
        }
        val dieResId = monsterProto.selectDieRes()
        if (dieResId != 0) {
            val resProto = pcs.resPointProtoCache.resPointMap[dieResId]
            if (resProto == null) {
                normalLog.error("找不到尸体资源地配置:%d", dieResId)
            } else {
                //创建尸体资源地
                cache.deadBossResCache.createDeadBossRes(
                    dieResId,
                    eventData.disappearX,
                    eventData.disappearY,
                    getNowTime() + sec2MilliSec(resProto.time),
                    resProto.resTotal
                )
            }
        }

        //再刷魔物
        val commonBoss = cache.commonBossCache.findCommonBossByXY(eventData.disappearX, eventData.disappearY)
        if (commonBoss != null) {
            onCommonBossOver(cache, commonBoss)
        } else {
            val callBoss = cache.callBossCache.findCallBossByXy(eventData.disappearX, eventData.disappearY)
            if (callBoss != null) {
                onCallBossOver(cache, callBoss)
            } else {
                val activityBoss =
                    cache.activityBossCache.findActivityBossByXy(eventData.disappearX, eventData.disappearY)
                if (activityBoss != null) {
                    updateRefreshTime(cache, activityBoss)
                    onActivityBossOver(cache, activityBoss)
                }
            }
        }

        noticeCellUpdate(cache, eventData.disappearX, eventData.disappearY)
    }
}