package tryakka.cluster.statsrouter.onemastermain

import akka.cluster.singleton.ClusterSingletonProxy
import akka.cluster.singleton.ClusterSingletonProxySettings
import akka.actor.PoisonPill
import akka.actor.Props
import akka.cluster.singleton.ClusterSingletonManager
import akka.cluster.singleton.ClusterSingletonManagerSettings
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import tryakka.cluster.statsrouter.StatsService

fun main(args: Array<String>) {
    val allSystems: MutableList<ActorSystem> = mutableListOf()

    if (args.size == 0) {
        // 启动单例 StatsService和单例代理
        startup(allSystems, arrayOf("2551", "2552", "0"))

        // 启动Client
        sampleOneMasterClientStart(allSystems, arrayOf())

    } else {
        startup(allSystems, args)
    }

    println(">>> Press ENTER to exit <<<")
    try {
        System.`in`.read()
    } finally {
        for (eachSystem in allSystems) {
            eachSystem.terminate()
        }
    }
}

fun startup(allSystems: MutableList<ActorSystem>, ports: Array<String>) {
    for (port in ports) {
        // Override the configuration of the port
        val config = ConfigFactory.parseString(
            "akka.remote.netty.tcp.port=" + port + "\n" +
                "akka.remote.artery.canonical.port=" + port
        )
            .withFallback(ConfigFactory.parseString("akka.cluster.roles = [compute]"))
            .withFallback(ConfigFactory.load("stats2")) // 加载 stats2 配置

        // 创建actor系统
        val system = ActorSystem.create("ClusterSystem", config)
        allSystems.add(system)

        // - 下面的代码片段用于创建 StatsService 单例
        // 借助 system 创建单例 settings
        val settings = ClusterSingletonManagerSettings.create(system)
            .withRole("compute") // 只在compute节点上部署

        // 创建 StatsService 单例
        system.actorOf(
            ClusterSingletonManager.props(
                Props.create(StatsService::class.java), // 单例 Actor
                PoisonPill.getInstance(), // 终止消息是什么
                settings // 配置
            ),
            "statsService"
        )

        // - 下面的代码片段用于创建单例代理
        val proxySettings = ClusterSingletonProxySettings.create(system)
            .withRole("compute") // 只在compute节点上部署
        system.actorOf(
            ClusterSingletonProxy.props(
                "/user/statsService",
                proxySettings
            ), "statsServiceProxy"
        )
    }

}

