package tryakka.blockdispatch

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.dispatch.Futures
import com.typesafe.config.ConfigFactory
import scala.concurrent.ExecutionContext
import tryakka.blockdispatch.common.CountData
import tryakka.blockdispatch.common.PrintActor

fun main(args: Array<String>) {
    val system = ActorSystem.create("try", ConfigFactory.load("separateDispatcherFuture.conf"))

    val actor1 = system.actorOf(SeparateDispatcherFutureActor.props())
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

// 这里演示了如何在独立的执行环境（dispatch）中运行Future，这样，一些耗时的操作就不会阻塞主逻辑。
// 当然，更好的做法是整个Actor也运行在独立环境。
class SeparateDispatcherFutureActor : AbstractActor() {

    // 独立的执行环境
    var ec: ExecutionContext = context.system.dispatchers().lookup("my-blocking-dispatcher")

    companion object {
        fun props(): Props {
            return Props.create(SeparateDispatcherFutureActor::class.java)
        }
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(CountData::class.java) { i ->
                println("Calling blocking Future on separate dispatcher: $i")

                val f = Futures.future<Int>({
                    // 阻塞5s
                    Thread.sleep(5000)

                    // 打印序号
                    println("Blocking future finished: " + i)
                    i.count
                }, ec)
            }
            .build()
    }
}

