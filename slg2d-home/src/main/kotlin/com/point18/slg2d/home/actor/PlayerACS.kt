package com.point18.slg2d.home.actor

import com.point18.slg2d.common.baseg.ACSBase
import xyz.ariane.util.ActorCompletionStage

class PlayerACS<T>(
    val session: PlayerActor,
    acs : ActorCompletionStage<T>,
    private val clientMsgNo: Int
): ACSBase<T>(acs) {

    override fun whenCompleteKt(handle: (T?, Throwable?) -> Unit): ACSBase<T> {
        acs = acs.whenCompleteKt { t, e ->
            // 当请求完成时，需要设回原来的消息序号。
            session.clientMsgNo = clientMsgNo

            // 进一步的处理。
            handle(t, e)
        }
        return this
    }

}