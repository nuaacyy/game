package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.IEventHandler
import java.util.Arrays.asList

class TaskCheckEventHandler(
    private val handleTaskEffectHelper: HandleTaskEffectAtEventFire = HandleTaskEffectAtEventFire(),
    private val handleAchievement: HandleAchievementAtEventFire = HandleAchievementAtEventFire(),
    private val handleTaskStart: HandleTaskStartHelper = HandleTaskStartHelper()
) : IEventHandler<EventData>, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(handleTaskEffectHelper, handleAchievement, handleTaskStart)
) {

    override fun handleEvent(session: PlayerActor, event: EventData) {
        prepare(session) { it: HomePlayerDC ->
            handleTaskEffectHelper.taskEffect(session, event)

            // 处理成就变化
            handleAchievement.achievementEffect(session, event)

            // 处理任务开启
            handleTaskStart.startTaskEffect(session, event)
        }
    }

}