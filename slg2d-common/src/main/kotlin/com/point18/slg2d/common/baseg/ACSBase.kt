package com.point18.slg2d.common.baseg

import akka.actor.ActorRef
import akka.util.Timeout
import xyz.ariane.util.ActorCompletionStage
import java.util.function.Supplier

open class ACSBase<T>(var acs : ActorCompletionStage<T>) {

    open fun ask(actor: ActorRef, message: Any, expectedResponseClass: Class<T>): ACSBase<T> {
        acs = acs.ask(actor, message, expectedResponseClass)
        return this
    }

    open fun ask(actor: ActorRef, message: Any, timeout: Timeout, expectedResponseClass: Class<T>): ACSBase<T> {
        acs.ask(actor, message, timeout, expectedResponseClass)
        return this
    }

    open fun whenCompleteKt(handle: (T?, Throwable?) -> Unit): ACSBase<T> {
        acs = acs.whenCompleteKt(handle)
        return this
    }

    open fun computeKt(resolve: () -> T): ACSBase<T> {
        acs = acs.compute(Supplier { resolve() })
        return this
    }

    open fun supplyIoKt(resolve: () -> T): ACSBase<T> {
        acs = acs.supplyIoKt(resolve)
        return this
    }
}