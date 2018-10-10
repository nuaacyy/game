package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.IEventHandler
import java.util.Arrays.asList

class GetUnitTaskRewardEventHandler(
    private val handleTaskStartHelper: HandleTaskStartHelper = HandleTaskStartHelper()
) : IEventHandler<EventData>, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(handleTaskStartHelper)
) {

    override fun handleEvent(session: PlayerActor, event: EventData) {
        prepare(session) {
            handleTaskStartHelper.startUnitTaskEffect(session, event)
        }
    }

}