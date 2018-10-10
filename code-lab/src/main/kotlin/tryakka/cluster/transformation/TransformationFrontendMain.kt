package tryakka.cluster.transformation

import akka.actor.ActorSystem
import akka.actor.Props
import akka.dispatch.OnSuccess
import akka.pattern.Patterns.ask
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration.Duration
import scala.runtime.BoxedUnit
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

fun frontendStart(args: Array<String>) {
    // 加载配置
    // Override the configuration of the port when specified as program argument
    val port = if (args.size > 0) args[0] else "0"
    val config = ConfigFactory.parseString(
        "akka.remote.netty.tcp.port=" + port + "\n" +
            "akka.remote.artery.canonical.port=" + port
    )
        .withFallback(ConfigFactory.parseString("akka.cluster.roles = [frontend]"))
        .withFallback(ConfigFactory.load("transformation.conf"))

    // 创建ActorSystem
    val system = ActorSystem.create("ClusterSystem", config)

    // 创建Actor
    val frontend = system.actorOf(
        Props.create(TransformationFrontend::class.java), "frontend"
    )

    // 这里模拟向front发送消息
    val interval = Duration.create(2, TimeUnit.SECONDS) // 间隔是2s
    val timeout = Timeout(Duration.create(5, TimeUnit.SECONDS)) // ask的超时是5s
    val ec = system.dispatcher()
    val counter = AtomicInteger()
    system.scheduler().schedule(interval, interval, Runnable {
        ask(
            frontend,
            TransformationJob("hello-" + counter.incrementAndGet()),
            timeout
        ).onSuccess<BoxedUnit>(object : OnSuccess<Any>() {
            override fun onSuccess(result: Any) {
                // result直接来自backend
                println("成功收到响应 " + result)
            }
        }, ec)
    }, ec)

}