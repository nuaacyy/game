package com.point18.slg2d.home.module.talent

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.event.TalentLvChangeEvent

class TalentLvChangeEventHandler : IEventHandler<TalentLvChangeEvent>,
    HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun handleEvent(session: PlayerActor, event: TalentLvChangeEvent) {
        prepare(session) {
            talentM.handleTalentChange(session, event.refreshResHelper, event.effectHelper)
        }
    }
}