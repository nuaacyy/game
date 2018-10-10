package tryakka.cluster.factorial

import akka.actor.ActorSystem
import akka.actor.Props
import akka.cluster.Cluster
import com.typesafe.config.ConfigFactory
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

fun factorialFrontendStart(args: Array<String>) {
    val upToN = 200

    val config = ConfigFactory.parseString(
        "akka.cluster.roles = [frontend]" // role只有frontend
    ).withFallback(ConfigFactory.load(resourceBasename))

    val system = ActorSystem.create(systemName, config)

    system.log().info("Factorials will start when 2 backend members in the cluster.")

    // 在成员Up后才会创建前端
    Cluster.get(system)
        .registerOnMemberUp(Runnable {
            // 创建前端
            val repeat = true
            system.actorOf(Props.create(FactorialFrontend::class.java, upToN, repeat), "factorialFrontend")
        })

    // 注册member移除时要做的事情
    Cluster.get(system)
        .registerOnMemberRemoved(Runnable {
            // exit JVM when ActorSystem has been terminated
            // 当ActorSystem结束时推出进程
            val exit = Runnable { System.exit(0) }
            system.registerOnTermination(exit)

            // shut down ActorSystem
            system.terminate()

            // In case ActorSystem shutdown takes longer than 10 seconds,
            // exit the JVM forcefully anyway.
            // We must spawn a separate thread to not block current thread,
            // since that would have blocked the shutdown of the ActorSystem.
            object : Thread() {
                override fun run() {
                    try {
                        // 等待system结束，最多等10秒
                        Await.ready(system.whenTerminated(), Duration.create(10, TimeUnit.SECONDS))
                    } catch (e: Exception) {
                        System.exit(-1)
                    }

                }
            }.start()
        })
}


