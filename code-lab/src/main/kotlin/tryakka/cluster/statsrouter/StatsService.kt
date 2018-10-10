package tryakka.cluster.statsrouter

import akka.actor.AbstractActor
import akka.actor.Props
import akka.routing.ConsistentHashingRouter.ConsistentHashableEnvelope
import akka.routing.FromConfig

class StatsService : AbstractActor() {

    // This router is used both with lookup and deploy of routees. If you
    // have a router with only lookup of routees you can use Props.empty()
    // instead of Props.create(StatsWorker.class).
    internal var workerRouter = context.actorOf(
        FromConfig.getInstance().props(Props.create(StatsWorker::class.java)),
        "workerRouter"
    )

    override fun preStart() {
        println("StatsService 启动")
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(StatsJob::class.java, { job -> !job.text.isEmpty() }, { job ->
                println("收到 StatsJob")

                // 将文本分割为词
                val words = job.text.split(" ")
                val replyTo = sender()

                // create actor that collects replies from workers
                // 创建从workers收集回复的actor
                val aggregator = context.actorOf(
                    Props.create(StatsAggregator::class.java, words.size, replyTo)
                )

                // send each word to a worker
                // 发送每个词给worker
                for (word in words) {
                    workerRouter.tell(
                        ConsistentHashableEnvelope(word, word),
                        aggregator
                    )
                }
            })
            .build()
    }
}
