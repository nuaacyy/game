package tryakka.cluster.statsrouter.normalmain

import akka.actor.Props
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import tryakka.cluster.statsrouter.StatsSampleClient

fun sampleClientMainStart(allSystems: MutableList<ActorSystem>, args: Array<String>) {
    // note that client is not a compute node, role not defined
    val system = ActorSystem.create(
        "ClusterSystem",
        ConfigFactory.load("stats1")
    )
    allSystems.add(system)

    // 启动 client
    // 这里的服务路径是实际路径
    system.actorOf(
        Props.create(StatsSampleClient::class.java, "/user/statsService"),
        "client"
    )
}
