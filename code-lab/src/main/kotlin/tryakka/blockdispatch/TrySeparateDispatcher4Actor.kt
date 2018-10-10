package tryakka.blockdispatch

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory
import tryakka.blockdispatch.common.CountData
import tryakka.blockdispatch.common.PrintActor

fun main(args: Array<String>) {
    val system = ActorSystem.create("try", ConfigFactory.load("separateDispatcherFuture.conf"))

    // 注意到这里，actor的props使用withDispatcher指定了要在独立的线程环境下运行。
    val actor1 = system.actorOf(SeparateDispatcher4Actor.props().withDispatcher("my-blocking-dispatcher"))
    val actor2 = system.actorOf(PrintActor.props())

    Thread.sleep(2000)

    actor2.tell(CountData(0), ActorRef.noSender())

    for (i in 1 until 100) {
        actor1.tell(CountData(i), ActorRef.noSender())
        actor2.tell(CountData(i), ActorRef.noSender())
    }

    println(">>> Press ENTER to exit <<<")
    try {
        System.`in`.read()
    } finally {
        system.terminate()
    }
}

// 这里演示了如何在独立的执行环境（dispatch）中运行Actor，这样，一些耗时的操作就不会阻塞主逻辑。
class SeparateDispatcher4Actor : AbstractActor() {

    companion object {
        fun props(): Props {
            return Props.create(SeparateDispatcherFutureActor::class.java)
        }
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(CountData::class.java) { i ->
                println("Calling blocking Future on separate dispatcher: $i")

                // 阻塞5s
                Thread.sleep(5000)

                // 打印序号
                println("Blocking future finished: " + i)
            }
            .build()
    }
}