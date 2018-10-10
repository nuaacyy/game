package tryakka.cluster.factorial

import javax.xml.datatype.DatatypeConstants.SECONDS
import java.time.Clock.system
import akka.event.LoggingAdapter
import akka.actor.AbstractActor
import akka.event.Logging
import akka.routing.FromConfig
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import akka.actor.ReceiveTimeout

class FactorialFrontend(internal val upToN: Int, internal val repeat: Boolean) : AbstractActor() {

    internal var log = Logging.getLogger(context.system(), this)

    // 获取路由
    // 这里使用了路由来分配计算任务
    internal var backend = context.actorOf(
        FromConfig.getInstance().props(), "factorialBackendRouter"
    )

    override fun preStart() {
        // 发送任务
        sendJobs()

        // 设置收到结果的超时
        context.setReceiveTimeout(Duration.create(10, TimeUnit.SECONDS))
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(FactorialResult::class.java) { result ->
                // 收到后端的结果
                if (result.n == upToN) {
                    //log.info("factorial finish：${result.n}! = ${result.factorial}")

                    if (repeat)
                        sendJobs()
                    else
                        context.stop(self)
                }
            }
            .match(ReceiveTimeout::class.java) { message ->
                // 超时
                log.info("Timeout")

                sendJobs()
            }
            .build()
    }

    internal fun sendJobs() {
        log.info("Starting batch of factorials up to [$upToN]")

        for (n in 1..upToN) {
            // 通过路由来分配任务给后端。
            backend.tell(FactorialParam(n), self)
        }
    }

}
