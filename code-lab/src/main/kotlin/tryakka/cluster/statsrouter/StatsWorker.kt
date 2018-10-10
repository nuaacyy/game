package tryakka.cluster.statsrouter

import java.util.HashMap

import akka.actor.AbstractActor

class StatsWorker : AbstractActor() {

    internal var cache: MutableMap<String, Int> = HashMap()

    override fun preStart() {
        println("StatsWorker 启动")
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(String::class.java) { word ->
                println("worker 收到 string 请求 $word ${sender.toString()}")

                // 从 word 的长度返回给发送者。
                var length: Int? = cache[word]
                if (length == null) {
                    length = word.length
                    cache[word] = length
                }
                //sender.tell("lengthok", self)
                sender.tell(StatsLength(length), self)
            }
            .build()
    }
}
