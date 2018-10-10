package com.point18.slg2d.world.module.farm

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.CommonRes
import com.point18.slg2d.world.common.calFarmedResNum
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.refreshFarmInfo
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.FarmEmpty
import com.point18.slg2d.world.event.FarmEnd
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.wpm
import java.util.*
import java.util.Arrays.asList

class CommonResFarmHeartHandler : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        commonResFarmHeart(cache)
    }

    //普通资源地采集结束心跳
    private fun commonResFarmHeart(areaCache: AreaCache) {
        var i = 0
        while (true) {
            val res = peekCommonResOver(areaCache)
            if (res == null) {
                // 没有东西需要处理了
                return
            }

            // 弹出目标元素
            areaCache.commonResCache.farmOverPq.poll()

            if (res.farmEndTime == 0L) {
                // 什么都不需要做
                continue
            }

            onStopFarmCommonRes(areaCache, res)

            i++
            if (i > 100000) {
                return
            }
        }
    }

    private fun peekCommonResOver(areaCache: AreaCache): (CommonRes?) {
        val farmOverH = areaCache.commonResCache.farmOverPq.peek()
        if (farmOverH == null) {
            return null
        }

        val nowTime = getNowTime()
        if (farmOverH.farmEndTime < nowTime) {
            return farmOverH
        }

        return null
    }

}

fun onStopFarmCommonRes(areaCache: AreaCache, res: CommonRes, sendHome: Boolean = true) {
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

    // 部队处理
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
            force.resFromInfo = "${Farm}_${resProto.resType}_${res.lv}"
            force.resVo = totalRes

            //采集结束事件
            wpm.es.fire(
                areaCache,
                force.playerId,
                FARM_END,
                FarmEnd(resProto.resType, haveFarmedNum)
            )

            //修改资源地数据
            res.nowResNum -= haveFarmedNum
            res.groupId = 0
            res.farmStartTime = 0
            res.farmEndTime = 0

            if (res.nowResNum <= 0) {
                //资源采空事件
                wpm.es.fire(
                    areaCache,
                    force.playerId,
                    FARM_EMPTY,
                    FarmEmpty(resProto.resType, res.lv)
                )

                // 修改玩家的统计数据
                targetAddVal(
                    areaCache,
                    force.playerId,
                    FarmEmptyCount,
                    asList(resProto.resType.toLong(), 1L)
                )
            }
        }

        if (sendHome) {
            goHome(areaCache, res.x, res.y, group)
        }
    }

    // 资源地的处理
    if (res.nowResNum > 0) {
        //还有资源，刷新地块
        noticeCellUpdate(areaCache, res.x, res.y)
    } else {
        //删除资源地
        areaCache.commonResCache.deleteCommonResByXY(res.x, res.y)

        //移除地块
        noticeCellUpdate(areaCache, res.x, res.y)

        //重新刷新资源地
        refreshFarmInfo(areaCache, res.x, res.y, resProto.level)
    }
}