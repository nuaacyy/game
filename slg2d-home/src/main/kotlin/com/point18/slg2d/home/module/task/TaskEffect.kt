package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.TaskHasFinish
import com.point18.slg2d.common.constg.TaskHasGetReward
import com.point18.slg2d.common.constg.taskCheckWhiteList
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus12
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.msgnotice.createTaskOperationNotifier
import pb4server.CreateTaskToWorldTell
import java.util.*

class HandleTaskEffectAtEventFire : HomeHelperPlus12<HomeAchievementDC,
        HomePlayerDC,
        HomeMyTargetDC,
        HomeTaskDC,
        HeroDC,
        IconDC,
        InnerCityDC,
        LibraryDC,
        NewEquipDC,
        SkinDC,
        VipDC,
        BagDC>(
    HomeAchievementDC::class.java,
    HomePlayerDC::class.java,
    HomeMyTargetDC::class.java,
    HomeTaskDC::class.java,
    HeroDC::class.java,
    IconDC::class.java,
    InnerCityDC::class.java,
    LibraryDC::class.java,
    NewEquipDC::class.java,
    SkinDC::class.java,
    VipDC::class.java,
    BagDC::class.java
) {

    /** 初始化任务 **/
    fun initTask(session: PlayerActor, questDictVoId: Int, event: EventData) {
        prepare(session) { homeAchievementDC: HomeAchievementDC, homePlayerDC: HomePlayerDC,
                           homeMyTargetDC: HomeMyTargetDC, homeTaskDC: HomeTaskDC, heroDC: HeroDC,
                           iconDC: IconDC, innerCityDC: InnerCityDC, libraryDC: LibraryDC,
                           newEquipDC: NewEquipDC, skinDC: SkinDC, vipDC: VipDC, bagDC: BagDC ->

            val checkDep = CheckDep(
                homePlayerDC, bagDC, homeMyTargetDC, homeTaskDC,
                heroDC, iconDC, innerCityDC, libraryDC,
                newEquipDC, skinDC, vipDC
            )

            // 找到任务模板
            val taskProto = pcs.questCache.findSpecTaskProto(questDictVoId)
            if (taskProto == null) {
                println("home服任务创建失败,任务ID为:$questDictVoId")
                return@prepare
            }

            // 不管是哪个服务器的任务 home都存底
            val playerTask = homeTaskDC.findTaskByProtoId(questDictVoId)
            if (playerTask != null) {
                return@prepare
            }

            // 创建任务
            val task = homeTaskDC.createTask(taskProto, 0)

            if (task.onWorld == 1) {
                // 世界任务,转移到world,home本地存一个底
                val tell = CreateTaskToWorldTell.newBuilder()
                tell.taskProtoId = questDictVoId
                session.tellWorld(session.fillHome2WorldTellMsgHeader {
                    it.setCreateTaskToWorldTell(tell)
                })

            } else {
                // home任务
                val (isOk, _) = checkIsFinish(session, checkDep, event, task)
                var overTime = -1
                if (isOk) {
                    task.taskNowState = TaskHasFinish
                    if (task.overTime != -1L) {
                        overTime = (task.overTime / 1000).toInt()
                    }
                }

                // 获得新任务已完成推送给客户端
                val taskOperationNotifier =
                    createTaskOperationNotifier(
                        1,
                        task.id,
                        task.taskProtoId,
                        task.taskNowState,
                        task.taskFinish,
                        overTime
                    )
                taskOperationNotifier.notice(session)
            }
        }

    }

    /**
    任务进度变化
     */
    fun taskEffect(session: PlayerActor, event: EventData) {
        prepare(session) { homeAchievementDC: HomeAchievementDC, homePlayerDC: HomePlayerDC,
                           homeMyTargetDC: HomeMyTargetDC, homeTaskDC: HomeTaskDC, heroDC: HeroDC,
                           iconDC: IconDC, innerCityDC: InnerCityDC, libraryDC: LibraryDC,
                           newEquipDC: NewEquipDC, skinDC: SkinDC, vipDC: VipDC, bagDC: BagDC ->
            val checkDep = CheckDep(
                homePlayerDC, bagDC, homeMyTargetDC, homeTaskDC,
                heroDC, iconDC, innerCityDC, libraryDC,
                newEquipDC, skinDC, vipDC
            )

            val playerTasks = homeTaskDC.findAllTaskByPlayerId()

            val timeOverTasks = LinkedList<Long>() // 过期了的任务
            for (playerTask in playerTasks) {
                val taskProto = pcs.questCache.findSpecTaskProto(playerTask.taskProtoId)
                if (taskProto == null) {
                    continue
                }
                if (taskProto.id == 0) {
                    continue
                }
                if (playerTask.taskNowState == TaskHasFinish || playerTask.taskNowState == TaskHasGetReward) {
                    continue
                }

                // 获取这个任务的检测条件,过滤白名单
                var checkType = 0
                for ((ct, _) in taskProto.completeCondMap) {
                    checkType = ct
                }
                val whiteList = taskCheckWhiteList[checkType]
                if (whiteList == null || !whiteList.contains(event.eventType)) {
                    // 说明这个事件根本就跟这个任务无关,过滤掉
                    continue
                }

                if (playerTask.overTime != -1L && playerTask.overTime <= getNowTime()) {
                    timeOverTasks.add(playerTask.id)
                }

                if (playerTask.onWorld == 1) {
                    // 如果这个任务在世界那边跑着并且还没过期,就不用管他
                    continue
                }

                val (isAllFinish, isCheck) = checkIsFinish(session, checkDep, event, playerTask)

                if (isAllFinish) {
                    playerTask.taskNowState = TaskHasFinish

                    // 给客户端推送任务变化
                    var overTime = -1
                    if (playerTask.overTime != -1L) {
                        overTime = (playerTask.overTime / 1000).toInt()
                    }

                    val taskOperationNotifier = createTaskOperationNotifier(
                        2, playerTask.id, playerTask.taskProtoId, playerTask.taskNowState,
                        playerTask.taskFinish, overTime
                    )

                    taskOperationNotifier.notice(session)

                } else {
                    if (isCheck) {
                        // 给客户端推送任务变化
                        var overTime = -1
                        if (playerTask.overTime != -1L) {
                            overTime = (playerTask.overTime / 1000).toInt()
                        }
                        val taskOperationNotifier = createTaskOperationNotifier(
                            3, playerTask.id, playerTask.taskProtoId, playerTask.taskNowState,
                            playerTask.taskFinish
                            , overTime
                        )

                        taskOperationNotifier.notice(session)
                    }
                }

            }

            for (delTasks in timeOverTasks) {
                val delT = homeTaskDC.findTaskById(delTasks)
                if (delT != null) {
                    homeTaskDC.deleteTask(delT)
                }
            }
        }
    }

    //检测任务完成否
    data class CheckIsFinish(val isOk: Boolean, val isChange: Boolean)

    private fun checkIsFinish(
        session: PlayerActor,
        checkDep: CheckDep,
        event: EventData,
        playerTask: HomeTask
    ): CheckIsFinish {
        val taskProto = pcs.questCache.findSpecTaskProto(playerTask.taskProtoId)
        if (taskProto == null) {
            return CheckIsFinish(false, false)
        }
        if (playerTask.taskNowState == TaskHasFinish || playerTask.taskNowState == TaskHasGetReward) {
            return CheckIsFinish(false, false)
        }

        var isAllFinish: Boolean
        for ((taskType, taskValue) in taskProto.completeCondMap) {
            val checkHandle = taskM.checkHandles[taskType]
            if (checkHandle == null) {
                return CheckIsFinish(false, false)
            }

            val (returnIsAllFinish, nowValue) = checkHandle.check(
                session,
                checkDep,
                taskValue,
                event,
                playerTask.taskFinish
            )
            isAllFinish = returnIsAllFinish
            var isCheck = false
            if (playerTask.taskFinish != nowValue) {
                playerTask.taskFinish = nowValue
                isCheck = true
            }
            if (!isAllFinish) {
                return CheckIsFinish(false, isCheck)
            }
        }
        return CheckIsFinish(true, false)
    }

}