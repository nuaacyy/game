package com.point18.slg2d.home.module.library

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.LibraryDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.event.PropChangeEvent

class PropChangeEventHandler : IEventHandler<PropChangeEvent>, HomeHelperPlus1<LibraryDC>(LibraryDC::class.java) {

    override fun handleEvent(session: PlayerActor, event: PropChangeEvent) {
        prepare(session) { libraryDC: LibraryDC ->
            val protoId = event.protoId
            libraryM.handleOpenLibItem(libraryDC, session, protoId)
        }
    }

}