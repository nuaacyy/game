package tryakka.shard.startregion3

import akka.actor.Props
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import tryakka.shard.Devices

fun main(args: Array<String>) {
    startup(arrayOf("2553"))
}

fun startup(ports: Array<String>) {
    for (port in ports) {
        // Override the configuration of the port
        val config = ConfigFactory.parseMap(
            mapOf(
                "akka.remote.netty.tcp.port" to port,
                "akka.cluster.roles" to listOf("Counter")
            )
        ).withFallback(
            ConfigFactory.load("shard-common.conf")
        )

        // 针对每个ActorSystem，都会在Devices中创建Region
        val system = ActorSystem.create("Sharding4Test", config)
        system.actorOf(Props.create(Devices::class.java))
    }
}
