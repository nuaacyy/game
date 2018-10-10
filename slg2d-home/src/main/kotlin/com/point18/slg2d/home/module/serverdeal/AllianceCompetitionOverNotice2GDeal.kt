package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.HomeHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.P2HTell
import com.point18.slg2d.home.module.event.AllianceCompetitionOverEvent
import com.point18.slg2d.home.module.fireEvent
import pb4server.Public2HomeTell

class AllianceCompetitionOverNotice2GDeal : P2HTell, HomeHelper() {

    override fun dealPublicTell(session: PlayerActor, playerId: Long, msg: Public2HomeTell) {
        val mm = msg.allianceCompetitionOverNotic2GTell
        fireEvent(session, AllianceCompetitionOverEvent(mm.rankLv, mm.rank))
    }

}