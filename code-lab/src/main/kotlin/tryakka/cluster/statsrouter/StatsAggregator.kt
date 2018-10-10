package tryakka.cluster.statsrouter

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ReceiveTimeout
import scala.concurrent.duration.Duration
import java.util.*
import java.util.concurrent.TimeUnit

// replyTo 是原始的 job 发起者
class StatsAggregator(internal val expectedResults: Int, internal val replyTo: ActorRef) : AbstractActor() {

    internal val results: MutableList<Int> = ArrayList()

    override fun preStart() {
        // 三秒超时，超时后会发送超时消息
        context.setReceiveTimeout(Duration.create(3, TimeUnit.SECONDS))
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(StatsLength::class.java) { wordCount ->
                println("aggregator 收到Worker产生的单词计数")

                results.add(wordCount.wordCount)
                if (results.size == expectedResults) {
                    // 达到预期的结果数
                    var sum = 0
                    for (c in results)
                        sum += c

                    // 计算平均词数
                    val meanWordLength = sum.toDouble() / results.size

                    // 将结果通知replyTo
                    replyTo.tell(StatsResult(meanWordLength), self())

                    // 停止自己
                    context.stop(self())
                }
            }
            .matchEquals("lengthok") {
                println("Length OK")
            }
            .match(ReceiveTimeout::class.java) { x ->
                // 超时了
                replyTo.tell(
                    JobFailed("Service unavailable, try again later"),
                    self()
                )
                context.stop(self())
            }
            .build()
    }

}
