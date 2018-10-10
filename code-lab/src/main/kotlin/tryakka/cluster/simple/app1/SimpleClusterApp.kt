package tryakka.cluster.simple.app1

import akka.actor.ActorSystem
import akka.actor.Props
import akka.event.Logging
import com.typesafe.config.ConfigFactory
import tryakka.cluster.simple.SimpleClusterListener
import tryakka.cluster.simple.SimpleClusterListener2

object SimpleClusterApp {

    @JvmStatic
    fun main(args: Array<String>) {
//        startup(arrayOf("2551"))
        if (args.size == 0)
            startup(arrayOf("2551", "2552", "2553"))
        else
            startup(args)
    }

    @JvmStatic
    fun startup(ports: Array<String>) {
        val resourceBasename = "cluster-simple.conf"
        val systemName = "ClusterSystem"
        for (port in ports) {
            // 使用sleep间隔开system的创建，从而能更好的看到CurrentClusterState消息的具体作用。
            Thread.sleep(5000L)

            // Override the configuration of the port
            val config = ConfigFactory.parseString(
                "akka.remote.netty.tcp.port=" + port
//                    + "\n" +
//                    "akka.remote.artery.canonical.port=" + port
            ).withFallback(ConfigFactory.load(resourceBasename))

            // Create an Akka system
            val system = ActorSystem.create(systemName, config)

            // 创建Actor处理Cluster事件
            system.actorOf(
                Props.create(SimpleClusterListener2::class.java),
                "clusterListener-${port}"
            )

        }
    }
}
