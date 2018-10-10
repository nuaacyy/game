package com.point18.slg2d.world.actor

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.ACSBase
import xyz.ariane.util.ActorCompletionStage

class WorldManagerACS<T>(
    acs: ActorCompletionStage<T>
) : ACSBase<T>(acs) {

    override fun whenCompleteKt(handle: (T?, Throwable?) -> Unit): ACSBase<T> {
        acs = acs.whenCompleteKt { t, e ->
            handle(t, e)
        }
        return this
    }

}