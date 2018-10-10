package tryakka.cluster.statsrouter.onemastermain

import akka.actor.Props
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import tryakka.cluster.statsrouter.StatsSampleClient

fun sampleOneMasterClientStart(allSystems: MutableList<ActorSystem>, args: Array<String>) {

    // note that client is not a compute node, role not defined
    val system = ActorSystem.create(
        "ClusterSystem",
        ConfigFactory.load("stats2")
    )
    allSystems.add(system)

    // 启动 client
    // 这里的 client 使用的服务路径是代理路径
    system.actorOf(
        Props.create(StatsSampleClient::class.java, "/user/statsServiceProxy"),
        "client"
    )

}


