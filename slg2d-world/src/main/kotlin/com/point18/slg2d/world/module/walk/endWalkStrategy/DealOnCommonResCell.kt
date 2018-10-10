package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.common.constg.Farming
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.calFarmEndTime
import com.point18.slg2d.world.common.noticeSelfWalkForceGroup
import com.point18.slg2d.world.common.noticeCellUpdate

class DealOnCommonResCell : ICellDeal {

    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        // 检测地上是否有资源点
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val commonResCell = areaCache.commonResCache.findCommonResByXY(posX, posY)
        if (commonResCell != null) {
            return checkGroupOnResCell(commonResCell.groupId)
        }

        return ResultCode.PARAMETER_ERROR.code
    }

    // 检测资源地上的部队
    private fun checkGroupOnResCell(defGroupId: Long): Int {
        if (defGroupId == 0L) {
            return ResultCode.SUCCESS.code
        }
        return ResultCode.OTHER_ALLIANCE_PLAYER_IN_FARM.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        // 记录采集信息
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val commonResCell = areaCache.commonResCache.findCommonResByXY(posX, posY)
        if (commonResCell != null) {
            //设置部队状态
            areaCache.walkForceGroupCache.changeGroupState(group, Farming)

            commonResCell.groupId = group.id
            commonResCell.farmStartTime = getNowTime()
            commonResCell.farmEndTime = calFarmEndTime(areaCache, commonResCell)

            //更新心跳
            areaCache.commonResCache.updateCommonRes(commonResCell)

            //部队通知刷新
            noticeSelfWalkForceGroup(
                areaCache,
                Add,
                group
            )

            // 刷新地块
            noticeCellUpdate(areaCache, posX, posY)
            return
        }
    }
}

