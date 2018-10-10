package tryakka.stop

import akka.actor.*

fun main(args: Array<String>) {
    val system = ActorSystem.create("tryChild")

    val actor = system.actorOf(ParentOfTryActorActor.props())

    Thread.sleep(2000)

    actor.tell("StopChild", ActorRef.noSender())

    println(">>> Press ENTER to exit <<<")
    try {
        System.`in`.read()
    } finally {
        system.terminate()
    }
}

class ParentOfTryActorActor : AbstractActor() {

    companion object {
        fun props(): Props {
            return Props.create(ParentOfTryActorActor::class.java)
        }
    }

    lateinit var child: ActorRef

    override fun preStart() {
        println("ParentOfTryActorActor preStart")

        child = context.actorOf(ChildOfTryActorActor.props())

        context.watch(child)
    }

    override fun postStop() {
        context.watch(child)
    }

    override fun createReceive(): Receive {
        return receiveBuilder()
            .matchEquals("StopChild", {
                println("try stopp child")
                context.stop(child)
            })
            .match(Terminated::class.java) {
                println("child terminated!")
            }
            .build()
    }
}

class ChildOfTryActorActor : AbstractActor() {

    companion object {
        fun props(): Props {
            return Props.create(ChildOfTryActorActor::class.java)
        }
    }

    override fun preStart() {
        println("ChildOfTryActorActor preStart")
    }

    override fun postStop() {
        println("ChildOfTryActorActor postStop")
    }

    override fun createReceive(): Receive {
        return receiveBuilder()
            .build()
    }
}