package tryakka.cluster.transformation

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Terminated
import java.util.*

class TransformationFrontend : AbstractActor() {

    internal var backends: MutableList<ActorRef> = ArrayList()
    internal var jobCounter = 0

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(TransformationJob::class.java, { job ->
                println("backends为空的处理")
                backends.isEmpty()
            }) { job ->
                sender().tell(
                    JobFailed("Service unavailable, try again later", job),
                    sender()
                )
            }
            .match(TransformationJob::class.java) { job ->
                println("TransformationJob的处理")
                jobCounter++

                // 将消息转发给backend
                backends[jobCounter % backends.size]
                    .forward(job, context)
            }
            .matchEquals<Any>(BACKEND_REGISTRATION) { message ->
                context.watch(sender())
                backends.add(sender())
            }
            .match(Terminated::class.java) { terminated ->
                backends.remove(terminated.actor)
            }
            .build()
    }
}
