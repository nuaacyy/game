package com.point18.slg2d.world.module.farm

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Farm
import com.point18.slg2d.common.constg.NOT_PROPS_SUB_TYPE
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.DeadBossRes
import com.point18.slg2d.world.common.calFarmedResNum
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.event.FarmEmpty
import com.point18.slg2d.world.event.FarmEnd
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.wpm
import java.util.*

class DeadBossResFarmHeartHandler : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        deadBossResFarmHeart(cache)
    }

    //普通资源地采集结束心跳
    private fun deadBossResFarmHeart(areaCache: AreaCache) {
        var i = 0
        while (true) {
            val res = areaCache.deadBossResCache.peekDeadBossResOver()
            if (res == null) {
                // 没有东西需要处理了
                return
            }

            // 弹出目标元素
            areaCache.deadBossResCache.popDeadBossResOver()

            if (res.farmEndTime == 0L) {
                // 什么都不需要做
                continue
            }

            onStopFarmDeadBossRes(areaCache, res)

            i++
            if (i > 100000) {
                return
            }
        }
    }
}


fun onStopFarmDeadBossRes(areaCache: AreaCache, res: DeadBossRes, sendHome: Boolean = true) {
    val resProto = pcs.resPointProtoCache.resPointMap[res.resId]
    if (resProto == null) {
        normalLog.error("找不到资源地配置:%d", res.resId)
        //删除资源地
        areaCache.commonResCache.deleteCommonResByXY(res.x, res.y)

        if (!sendHome) {
            return
        }

        //遣返部队
        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(res.groupId)
        if (group == null) {
            normalLog.error("找不到资源地上的行军组信息:%d", res.groupId)
            return
        }
        goHome(areaCache, res.x, res.y, group)
        return
    }

    val group = areaCache.walkForceGroupCache.findWalkForceGroupById(res.groupId)
    if (group != null) {
        val force = areaCache.walkForceCache.findMainForceByGroupId(res.groupId)
        if (force != null) {
            val haveFarmedNum = calFarmedResNum(areaCache, res)

            //填充部队采集资源
            val totalRes = LinkedList<ResVo>()
            if (haveFarmedNum > 0) {
                totalRes.add(
                    ResVo(resProto.resType, NOT_PROPS_SUB_TYPE, haveFarmedNum.toLong())
                )
            }
            val dropRes = resProto.genRewardByFarmNum(haveFarmedNum)
            if (dropRes != null) {
                totalRes += dropRes
            }
            force.resFromInfo = "${Farm}_${resProto.resType}_${resProto.level}"
            force.resVo = totalRes

            //采集结束事件
            wpm.es.fire(
                areaCache,
                force.playerId,
                com.point18.slg2d.common.constg.FARM_END,
                FarmEnd(resProto.resType, haveFarmedNum)
            )

            //修改部队数据
            res.nowResNum -= haveFarmedNum
            res.groupId = 0
            res.farmStartTime = 0
            res.farmEndTime = 0

            if (res.nowResNum <= 0) {
                //资源采空事件
                wpm.es.fire(
                    areaCache,
                    force.playerId,
                    com.point18.slg2d.common.constg.FARM_EMPTY,
                    FarmEmpty(resProto.resType, resProto.level)
                )
            }
        }

        if (sendHome) {
            goHome(areaCache, res.x, res.y, group)
        }
    }

    if (res.nowResNum <= 0) {
        //删除资源地
        areaCache.commonResCache.deleteCommonResByXY(res.x, res.y)
    }

    //刷新地块
    noticeCellUpdate(areaCache, res.x, res.y)
}