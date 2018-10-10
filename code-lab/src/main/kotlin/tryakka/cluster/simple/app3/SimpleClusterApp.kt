package tryakka.cluster.simple.app3

import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory
import tryakka.cluster.simple.SimpleClusterListener
import tryakka.cluster.simple.SimpleClusterListener2

object SimpleClusterApp {

    @JvmStatic
    fun main(args: Array<String>) {
        startup(arrayOf("2553"))
//        if (args.size == 0)
//            startup(arrayOf("2551", "2552", "2553"))
//        else
//            startup(args)
    }

    @JvmStatic
    fun startup(ports: Array<String>) {
        for (port in ports) {
            // Override the configuration of the port
            val config = ConfigFactory.parseString(
                "akka.remote.netty.tcp.port=" + port + "\n" +
                    "akka.remote.artery.canonical.port=" + port
            )
                .withFallback(ConfigFactory.load("cluster-simple.conf"))

            // Create an Akka system
            val system = ActorSystem.create("ClusterSystem", config)

            // Create an actor that handles cluster domain events
            system.actorOf(
                Props.create(SimpleClusterListener2::class.java),
                "clusterListener-${port}"
            )

        }
    }
}
