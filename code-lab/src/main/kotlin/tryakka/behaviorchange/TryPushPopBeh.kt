package tryakka.behaviorchange

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

fun main(args: Array<String>) {
    val system = ActorSystem.create("try")

    val actor = system.actorOf(TryPushPopBehActor.props())

    Thread.sleep(2000)

    actor.tell("NowFace", ActorRef.noSender())
    actor.tell("NowFace", ActorRef.noSender())
    actor.tell("NowFace", ActorRef.noSender())
    actor.tell("NowFace", ActorRef.noSender())
    actor.tell("NowFace", ActorRef.noSender())
    actor.tell("NowFace", ActorRef.noSender())
    actor.tell("NowFace", ActorRef.noSender())

    println(">>> Press ENTER to exit <<<")
    try {
        System.`in`.read()
    } finally {
        system.terminate()
    }
}

// 这里演示了如何压入和弹出行为。
// 这个属于保持原有行为的基础上增加新的功能。
class TryPushPopBehActor : AbstractActor() {

    private lateinit var angry: Receive
    private lateinit var happy: Receive

    init {
        angry = receiveBuilder()
            .matchEquals("NowFace", {
                println("I'm angry now")

                // 因为discardOld为false，所以老的angry行为不会被移除。
                context.become(happy, false)
            })
            .build()

        happy = receiveBuilder()
            .matchEquals("NowFace", {
                println("I'm happy now")

                // 这里移除自己，也就是移除happy行为
                context.unbecome()
            })
            .build()
    }

    companion object {
        fun props(): Props {
            return Props.create(TryPushPopBehActor::class.java)
        }
    }

    override fun createReceive(): Receive {
        return angry
    }
}