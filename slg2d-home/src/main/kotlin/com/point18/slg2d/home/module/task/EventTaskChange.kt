package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.IEventHandler
import java.util.Arrays.asList

class TaskChangeEventHandler(
    private val handleTaskEffectHelper: HandleTaskEffectAtEventFire = HandleTaskEffectAtEventFire(),
    private val handleAchievement: HandleAchievementAtEventFire = HandleAchievementAtEventFire()
) : IEventHandler<EventData>, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(handleTaskEffectHelper, handleAchievement)
) {

    override fun handleEvent(session: PlayerActor, event: EventData) {
        prepare(session) {
            handleTaskEffectHelper.taskEffect(session, event)

            // 处理成就变化
            handleAchievement.achievementEffect(session, event)
        }
    }

}