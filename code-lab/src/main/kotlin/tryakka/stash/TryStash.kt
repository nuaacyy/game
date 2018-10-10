package tryakka.stash

import akka.actor.*
import tryakka.behaviorchange.TryPushPopBehActor

fun main(args: Array<String>) {
    val system = ActorSystem.create("try")

    val actor = system.actorOf(TryStashActor.props())

    Thread.sleep(2000)

    actor.tell("write", ActorRef.noSender())
    Thread.sleep(1000)

    actor.tell("write", ActorRef.noSender())
    Thread.sleep(1000)

    actor.tell("open", ActorRef.noSender())
    Thread.sleep(1000)

    actor.tell("read", ActorRef.noSender())
    Thread.sleep(1000)

    actor.tell("write", ActorRef.noSender())
    Thread.sleep(1000)

    actor.tell("close", ActorRef.noSender())
    Thread.sleep(1000)

    println(">>> Press ENTER to exit <<<")
    try {
        System.`in`.read()
    } finally {
        system.terminate()
    }

}

class TryStashActor : AbstractActorWithStash() {

    companion object {
        fun props(): Props {
            return Props.create(TryStashActor::class.java)
        }
    }

    override fun createReceive(): Receive {
        return receiveBuilder()
            .matchEquals("open", {
                println("deal open | unstash")
                unstashAll()

                println("切换行为")
                context.become(
                    receiveBuilder()
                        .matchEquals("write", {
                            println("deal write")
                        })
                        .matchEquals("close", {
                            println("deal close | unstash")

                            // 弹出保存的消息
                            unstashAll()

                            println("切换行为")
                            context.unbecome()
                        })
                        .matchAny({
                            println("deal high any | stash | ${it}")
                            stash()
                        })
                        .build(), false
                )
            })
            .matchEquals("read", {
                println("deal read")
            })
            .matchAny({
                println("deal low any | stash | ${it}")
                stash()
            })
            .build()
    }
}