package tryakka.behaviorchange

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import tryakka.stop.TrySelfStopActor

fun main(args: Array<String>) {
    val system = ActorSystem.create("tryStop")

    val actor = system.actorOf(TryHotSwapBehActor.props())

    Thread.sleep(2000)

    actor.tell("NowFace", ActorRef.noSender())
    actor.tell("change", ActorRef.noSender())
    actor.tell("NowFace", ActorRef.noSender())
    actor.tell("change", ActorRef.noSender())
    actor.tell("NowFace", ActorRef.noSender())

    println(">>> Press ENTER to exit <<<")
    try {
        System.`in`.read()
    } finally {
        system.terminate()
    }
}

// 这个演示了如何更换行为
// 这里有2个行为，然后通过消息让这2个行为轮流替代。
class TryHotSwapBehActor : AbstractActor() {

    private lateinit var angry: Receive
    private lateinit var happy: Receive

    init {
        angry = receiveBuilder()
            .matchEquals(
                "NowFace", {
                println("I'm angry now")
            }
            )
            .matchEquals(
                "change", {
                println("change to happy!")
                context.become(happy)
            }
            )
            .build()

        happy = receiveBuilder()
            .matchEquals(
                "NowFace", {
                println("I'm happy now")
            }
            )
            .matchEquals(
                "change", {
                println("change to angry!")
                context.become(angry)
            }
            )
            .build()
    }

    companion object {
        fun props(): Props {
            return Props.create(TryHotSwapBehActor::class.java)
        }
    }

    override fun createReceive(): Receive {
        return angry
    }
}