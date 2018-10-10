package tryakka.blockdispatch.common

import akka.actor.AbstractActor
import akka.actor.Props

data class CountData(val count: Int)

class PrintActor : AbstractActor() {

    companion object {
        fun props(): Props {
            return Props.create(PrintActor::class.java)
        }
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(CountData::class.java) { i ->
                println("PrintActor: $i")
            }
            .build()
    }
}