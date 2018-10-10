package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeTaskDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.EventData
import java.util.Arrays.asList

class HandleTaskStartHelper(
    private val handleTaskEffectHelper: HandleTaskEffectAtEventFire = HandleTaskEffectAtEventFire()
) : HomeHelperPlus3<HomePlayerDC, HomeTaskDC, InnerCityDC>(
    HomePlayerDC::class.java, HomeTaskDC::class.java, InnerCityDC::class.java,
    asList(handleTaskEffectHelper)
) {

    /**
    检测是否获得新任务的事件方法
     */
    fun startUnitTaskEffect(session: PlayerActor, event: EventData) {
        prepare(session) { homePlayerDC: HomePlayerDC, homeTaskDC: HomeTaskDC, innerCityDC: InnerCityDC ->
            val player = homePlayerDC.player
            for ((unitTaskId, unitTaskProto) in pcs.unitTaskProtoCache.unitTaskProtoMap) {
                if (unitTaskId <= player.unitTaskId) {
                    continue
                }

                var ok = true // 是否能获取到这个任务
                for ((k, tryGetNewTask) in unitTaskProto.getCondMap) {
                    if (k == GET_NEW_QUEST_FOR_FINISH_QUEST) {
                        // 某些任务完成
                        ok = findTaskState(session, tryGetNewTask, homeTaskDC)
                        if (!ok) {
                            break
                        }

                    } else if (k == GET_NEW_QUEST_FOR_BUILDINGLV) {
                        // 建筑达成X等级
                        ok = findBuildLv(homePlayerDC, innerCityDC, tryGetNewTask)
                        if (!ok) {
                            break
                        }

                    } else if (k == GET_NEW_QUEST_FOR_POWER) {
                        // 势力值达到X
                        val shili = player.power

                        if (shili < tryGetNewTask[0]) {
                            ok = false
                            break
                        }
                    } else if (k == GET_NEW_QUEST_FOR_UNIT_TASK) {
                        // 当前章节完成到X
                        if (player.unitTaskId < tryGetNewTask[0]) {
                            ok = false
                            break
                        }
                    }
                }

                if (!ok) {
                    continue
                }
                // 初始化任务
                for (openT in unitTaskProto.tasks) {
                    handleTaskEffectHelper.initTask(session, openT, event)
                }
            }
        }

    }

    fun startTaskEffect(session: PlayerActor, event: EventData) {
        prepare(session) { homePlayerDC: HomePlayerDC, homeTaskDC: HomeTaskDC, innerCityDC: InnerCityDC ->
            val player = homePlayerDC.player
            for ((_, questDictVo) in pcs.questCache.questProtoMap) {
                // 判断玩家身上是否有目标任务
                val taskByProtoId = homeTaskDC.findTaskByProtoId(questDictVo.id)
                if (taskByProtoId != null) {
                    continue
                }

                if (questDictVo.type == TaskAllianceCompetition) {
                    continue
                }

                var ok = true // 是否满足获取这个任务的条件
                for ((k, tryGetNewTask) in questDictVo.getCondMap) {
                    if (k == GET_NEW_QUEST_FOR_FINISH_QUEST) {
                        // 某些任务完成
                        ok = findTaskState(session, tryGetNewTask, homeTaskDC)
                        if (!ok) {
                            break
                        }

                    } else if (k == GET_NEW_QUEST_FOR_BUILDINGLV) {
                        // 建筑达成X等级
                        ok = findBuildLv(homePlayerDC, innerCityDC, tryGetNewTask)
                        if (!ok) {
                            break
                        }

                    } else if (k == GET_NEW_QUEST_FOR_POWER) {
                        // 势力值达到X
                        val shili = player.power
                        if (shili < tryGetNewTask[0]) {
                            ok = false
                            break
                        }
                    } else if (k == GET_NEW_QUEST_FOR_UNIT_TASK) {
                        // 当前章节完成到X
                        if (player.unitTaskId < tryGetNewTask[0]) {
                            ok = false
                            break
                        }
                    }
                }
                if (!ok) {
                    continue
                }

                handleTaskEffectHelper.initTask(session, questDictVo.id, event)
            }
        }
    }

    /**
    检测一些任务ID是否都已经完成了
     */
    private fun findTaskState(session: PlayerActor, checkValue: List<Int>, homeTaskDC: HomeTaskDC): Boolean {
        for (value in checkValue) {
            val playerTask = homeTaskDC.findTaskByProtoId(value)
            if (playerTask == null || playerTask.taskNowState != TaskHasGetReward) {
                return false
            }
        }

        return true
    }

    /**
    检测一些建筑等级是否都已经完成
     */
    private fun findBuildLv(
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC,
        checkValue: List<Int>
    ): Boolean {
        if (checkValue.size != 2) {
            return false
        }

        val build =
            innerCityDC.findMaxLvInnerBuildingFromCastleIdAndType(homePlayerDC.player.focusCastleId, checkValue[0])
        if (build != null) {
            if (build.lv >= checkValue[1]) {
                return true
            }
        }

        return false
    }
}