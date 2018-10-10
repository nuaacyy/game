package tryakka.cluster.statsrouter.normalmain

import akka.actor.Props
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import tryakka.cluster.statsrouter.StatsService
import tryakka.cluster.statsrouter.StatsWorker

fun main(args: Array<String>) {
    val allSystems: MutableList<ActorSystem> = mutableListOf()

    if (args.size == 0) {
        // 启动3个 worker 和3个 service
        startup(allSystems, arrayOf("2551", "2552", "0"))

        // 启动 client
        sampleClientMainStart(allSystems, arrayOf())

    } else {
        // 启动
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
        // 加载配置
        val config = ConfigFactory.parseString(
            "akka.remote.netty.tcp.port=" + port + "\n" +
                "akka.remote.artery.canonical.port=" + port
        )
            .withFallback(ConfigFactory.parseString("akka.cluster.roles = [compute]")) // 角色是compute
            .withFallback(ConfigFactory.load("stats1")) // 加载stats1

        // 创建系统
        val system = ActorSystem.create("ClusterSystem", config)
        allSystems.add(system)

        // 在每个system上启动worker和service
        system.actorOf(Props.create(StatsWorker::class.java), "statsWorker")
        system.actorOf(Props.create(StatsService::class.java), "statsService")
    }

}

