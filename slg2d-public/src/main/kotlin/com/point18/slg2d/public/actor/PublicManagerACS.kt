package com.point18.slg2d.public.actor

import com.point18.slg2d.common.baseg.ACSBase
import com.point18.slg2d.public.datacache.PublicManagerCache
import xyz.ariane.util.ActorCompletionStage

class PublicManagerACS<T>(
    val publicManagerCache: PublicManagerCache,
    acs : ActorCompletionStage<T>,
    private val clientMsgNo: Int
): ACSBase<T>(acs) {

    override fun whenCompleteKt(handle: (T?, Throwable?) -> Unit): ACSBase<T> {
        acs = acs.whenCompleteKt { t, e ->
            publicManagerCache.currentClientMsgNo = clientMsgNo
            handle(t, e)
        }
        return this
    }
}