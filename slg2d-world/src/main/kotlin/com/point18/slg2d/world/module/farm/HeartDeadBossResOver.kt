package com.point18.slg2d.world.module.farm

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.DeadBossRes
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.common.noticeCellRemove
import com.point18.slg2d.world.common.noticeCellUpdate

class DeadBossResHeartHandler: IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        deadBossResHeart(cache)
    }

    //尸体资源地消失的心跳
    private fun deadBossResHeart(areaCache: AreaCache) {
        val allTimeOverDeadBossRes = areaCache.deadBossResCache.findAllTimeOverDeadBossRes()
        for (res in allTimeOverDeadBossRes) {
            onDeadBossResOver(areaCache, res)
        }
    }

    private fun onDeadBossResOver(areaCache: AreaCache, deadBossRes: DeadBossRes) {
        val resProto = pcs.resPointProtoCache.resPointMap[deadBossRes.resId]
        if (resProto == null) {
            normalLog.error("找不到资源地配置:%d", deadBossRes.resId)
            //删除资源地
            areaCache.deadBossResCache.deleteDeadBossRes(deadBossRes)

            if (deadBossRes.groupId == 0L) {
                return
            }

            //遣返部队
            val group = areaCache.walkForceGroupCache.findWalkForceGroupById(deadBossRes.groupId)
            if (group == null) {
                normalLog.error("找不到资源地上的行军组信息:%d", deadBossRes.groupId)
                return
            }
            goHome(areaCache, deadBossRes.x, deadBossRes.y, group)
            return
        }

        if (deadBossRes.groupId != 0L) {
            //采集部队结算，并遣返
            onStopFarmDeadBossRes(areaCache, deadBossRes)
        }

        //删除资源地
        areaCache.deadBossResCache.deleteDeadBossRes(deadBossRes)

        //地块刷新
        noticeCellUpdate(areaCache, deadBossRes.x, deadBossRes.y)
    }
}


