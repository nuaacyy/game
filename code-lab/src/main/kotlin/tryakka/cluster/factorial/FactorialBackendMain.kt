package tryakka.cluster.factorial

import akka.actor.Props
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

const val systemName = "ClusterSystem"
const val resourceBasename = "factorial"

fun factorialBackendStart(args: Array<String>) {
    // Override the configuration of the port when specified as program argument

    val port = if (args.size > 0) args[0] else "0"
    val config = ConfigFactory.parseString(
        "akka.remote.netty.tcp.port=" + port + "\n" +
            "akka.remote.artery.canonical.port=" + port
    )
        .withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]")) // role只有backend
        .withFallback(ConfigFactory.load(resourceBasename))


    val system = ActorSystem.create(systemName, config)

    // 创建后端
    system.actorOf(Props.create(FactorialBackend::class.java), "factorialBackend")

    // 创建Listern
    system.actorOf(Props.create(MetricsListener::class.java), "metricsListener")

}


