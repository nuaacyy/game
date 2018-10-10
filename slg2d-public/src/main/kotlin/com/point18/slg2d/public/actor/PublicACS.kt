package com.point18.slg2d.public.actor

import com.point18.slg2d.common.baseg.ACSBase
import com.point18.slg2d.public.datacache.PublicCache
import xyz.ariane.util.ActorCompletionStage

class PublicACS<T>(
    val publicCache: PublicCache,
    acs : ActorCompletionStage<T>,
    private val clientMsgNo: Int
): ACSBase<T>(acs) {

    override fun whenCompleteKt(handle: (T?, Throwable?) -> Unit): ACSBase<T> {
        acs = acs.whenCompleteKt { t, e ->
            publicCache.currentClientMsgNo = clientMsgNo
            handle(t, e)
        }
        return this
    }
}