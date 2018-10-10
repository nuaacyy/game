package com.point18.slg2d.home.common

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus5

class UiConditionCheckHelper : HomeHelperPlus5<HomePlayerDC, InnerCityDC, VipDC, HomeSyncDC, HomeTaskDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java, VipDC::class.java, HomeSyncDC::class.java, HomeTaskDC::class.java
) {
    //功能开启检测
    fun uiConditionCheck(session: PlayerActor, uiConditionId: Int): Int {
        val uiCondition = pcs.uiConditionCache.uiConditionProtoMap[uiConditionId]
        if (uiCondition == null) {
            return ResultCode.UI_CONDITION_ERROR.code
        }
        return check(session, uiCondition)
    }

    fun check(session: PlayerActor, uiConditionProto: com.point18.slg2d.common.pc.UiConditionProto): Int {
        //BUILDLV          = 1 //建筑等级要求
        //SHENGWANG        = 2 //声望
        //CREATPLAYERHOURS = 3 //建号到现在的小时数
        //OPENSERVERDAY    = 4 //开服到现在的天数
        //SHILI            = 5 //势力值

        // 检查开启条件1
        val result1 = startCheck(session, uiConditionProto.checkMap)

        // 检查开启条件2
        if (uiConditionProto.checkMap2.count() != 0) {
            val result2 = startCheck(session, uiConditionProto.checkMap2)

            // 第1个条件不满足，并且第2个条件不满足，返回第1个条件
            if (result1 != ResultCode.SUCCESS.code && result2 != ResultCode.SUCCESS.code) {
                return result1
            }

            // 剩余情况：两个条件满足任意一个，就是满足条件（李新宇:2017.01.06）
            return ResultCode.SUCCESS.code
        }

        return result1
    }

    private fun startCheck(session: PlayerActor, checkMap: Map<Int, List<Int>>): Int {
        return prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC, vipDC: VipDC,
                                  homeSyncDC: HomeSyncDC, homeTaskDC: HomeTaskDC ->
            val player = homePlayerDC.player

            for ((_, uiCondition) in checkMap) {
                val checkType = uiCondition[0]
                if (checkType == BUILD_LV) {
                    if (uiCondition.count() != 3) {
                        return@prepare ResultCode.UI_CONDITION_BUILD_LV_ERROR.code
                    }

                    val builds = innerCityDC.findInnerCityListFromCastleIdAndType(player.focusCastleId, uiCondition[1])
                    for (build in builds) {
                        if (build.lv < uiCondition[2]) {
                            return@prepare ResultCode.UI_CONDITION_BUILD_LV_ERROR.code
                        }
                    }

                } else if (checkType == VIP_LEVEL) {
                    if (uiCondition.count() != 2) {
                        return@prepare ResultCode.UI_CONDITION_VIPLV_ERROR.code
                    }
                    if (vipDC.vipInfo.vipLv < uiCondition[1]) {
                        return@prepare ResultCode.UI_CONDITION_VIPLV_ERROR.code
                    }
                } else if (checkType == KING_LV) {
                    if (uiCondition.count() != 2) {
                        return@prepare ResultCode.UI_CONDITION_KINGLV_ERROR.code
                    }
                    if (player.kingLv < uiCondition[1]) {
                        return@prepare ResultCode.UI_CONDITION_KINGLV_ERROR.code
                    }
                } else if (checkType == INSTANCE_FINISH) {
                    if (uiCondition.count() != 2) {
                        return@prepare ResultCode.UI_CONDITION_KINGLV_ERROR.code
                    }
                    if (homeSyncDC.syncData.instanceFloor < uiCondition[1]) {
                        return@prepare ResultCode.UI_CONDITION_INSTANCE_ERROR.code
                    }
                } else if (checkType == UNITTASK_ID) {
                    if (uiCondition.count() != 2) {
                        return@prepare ResultCode.UI_CONDITION_UNITTASK_ERROR.code
                    }
                    if (player.unitTaskId < uiCondition[1]) {
                        return@prepare ResultCode.UI_CONDITION_UNITTASK_ERROR.code
                    }
                } else if (checkType == MAIN_TASK_FINISH) {
                    if (uiCondition.count() != 2) {
                        return@prepare ResultCode.UI_CONDITION_MAINTASK_ERROR.code
                    }
                    val task = homeTaskDC.findTaskByProtoId(uiCondition[1])
                    if (task == null || task.taskNowState != TaskHasGetReward) {
                        return@prepare ResultCode.UI_CONDITION_MAINTASK_ERROR.code
                    }
                }
            }

            return@prepare ResultCode.SUCCESS.code
        }
    }
}