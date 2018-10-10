package com.point18.slg2d.world.module.uiCondition

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode

//功能开启检测
fun uiConditionCheck(areaCache: AreaCache, player: Player, uiConditionId: Int): Int {
    val uiCondition = pcs.uiConditionCache.uiConditionProtoMap[uiConditionId]
    if (uiCondition == null) {
        return ResultCode.UI_CONDITION_ERROR.code
    }
    return check(areaCache, player, uiCondition)
}

fun check(areaCache: AreaCache, player: Player, uiConditionProto: com.point18.slg2d.common.pc.UiConditionProto): Int {
    //BUILDLV          = 1 //建筑等级要求
    //SHENGWANG        = 2 //声望
    //CREATPLAYERHOURS = 3 //建号到现在的小时数
    //OPENSERVERDAY    = 4 //开服到现在的天数
    //SHILI            = 5 //势力值

    // 检查开启条件1
    val result1 = startCheck(areaCache, player, uiConditionProto.checkMap)

    // 检查开启条件2
    if (uiConditionProto.checkMap2.count() != 0) {
        val result2 = startCheck(areaCache, player, uiConditionProto.checkMap2)

        // 第1个条件不满足，并且第2个条件不满足，返回第1个条件
        if (result1 != ResultCode.SUCCESS.code && result2 != ResultCode.SUCCESS.code) {
            return result1
        }

        // 剩余情况：两个条件满足任意一个，就是满足条件（李新宇:2017.01.06）
        return ResultCode.SUCCESS.code
    }

    return result1
}

fun startCheck(areaCache: AreaCache, player: Player, checkMap: Map<Int, List<Int>>): Int {
    val infoByHome = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(player.id)
    if (infoByHome == null) {
        return ResultCode.UI_CONDITION_ERROR.code
    }

    for ((_, uiCondition) in checkMap) {
        val checkType = uiCondition[0]
        if (checkType == BUILD_LV) {
            if (uiCondition.count() != 3) {
                return ResultCode.UI_CONDITION_BUILD_LV_ERROR.code
            }

            val maxLv = infoByHome.findBuildingMaxLv(uiCondition[1])
            if (maxLv < uiCondition[2]) {
                return ResultCode.UI_CONDITION_BUILD_LV_ERROR.code
            }

        } else if (checkType == VIP_LEVEL) {
            if (uiCondition.count() != 2) {
                return ResultCode.UI_CONDITION_VIPLV_ERROR.code
            }
            if (infoByHome.vipLv < uiCondition[1]) {
                return ResultCode.UI_CONDITION_VIPLV_ERROR.code
            }
        } else if (checkType == KING_LV) {
            if (uiCondition.count() != 2) {
                return ResultCode.UI_CONDITION_KINGLV_ERROR.code
            }
            if (player.kingLv < uiCondition[1]) {
                return ResultCode.UI_CONDITION_KINGLV_ERROR.code
            }
        } else if (checkType == INSTANCE_FINISH) {
            if (uiCondition.count() != 2) {
                return ResultCode.UI_CONDITION_INSTANCE_ERROR.code
            }
            val instanceVo = areaCache.instanceCache.findInstance(player.id)
            if (instanceVo == null) {
                return ResultCode.UI_CONDITION_INSTANCE_ERROR.code
            }
            if (instanceVo.nowFight < uiCondition[1]) {
                return ResultCode.UI_CONDITION_INSTANCE_ERROR.code
            }
        } else if (checkType == UNITTASK_ID) {
            if (uiCondition.count() != 2) {
                return ResultCode.UI_CONDITION_UNITTASK_ERROR.code
            }
            if (player.unitTaskId < uiCondition[1]) {
                return ResultCode.UI_CONDITION_UNITTASK_ERROR.code
            }
        } else if (checkType == MAIN_TASK_FINISH) {
            if (uiCondition.count() != 2) {
                return ResultCode.UI_CONDITION_MAINTASK_ERROR.code
            }
            if (infoByHome.finishTasks[uiCondition[1]] == null) {
                return ResultCode.UI_CONDITION_MAINTASK_ERROR.code
            }
        }

    }

    return ResultCode.SUCCESS.code
}
