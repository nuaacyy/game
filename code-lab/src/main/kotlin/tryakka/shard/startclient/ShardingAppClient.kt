package tryakka.shard.startclient

import akka.actor.ActorSystem
import akka.cluster.sharding.ClusterSharding
import com.typesafe.config.ConfigFactory
import tryakka.shard.Devices.Companion.messageExtractor
import tryakka.shard.DevicesClient
import java.util.*

fun main(args: Array<String>) {
    startupClient()
}

fun startupClient() {
    // Override the configuration of the port
    val config = ConfigFactory.parseString(
        "akka.remote.netty.tcp.port=" + 2554
    ).withFallback(
        ConfigFactory.load("shard-common.conf")
    )

    // 创建在另一个JVM中访问远程Region的客户端
    val system = ActorSystem.create("Sharding4Test", config)

    // 建立代理
    ClusterSharding.get(system)
        .startProxy("Counter", Optional.of("Counter"), messageExtractor)
        .let { println("Counter shard proxy $it started.") }

    system.actorOf(DevicesClient.props())

}
