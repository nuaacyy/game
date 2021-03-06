package tryakka.sharding.start4n1

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.cluster.sharding.ClusterSharding
import akka.cluster.sharding.ClusterShardingSettings
import akka.persistence.Persistence
import com.typesafe.config.ConfigFactory
import tryakka.sharding.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * me.chaopeng.akka.Main
 *
 * @author chao
 * @version 1.0 - 2015-09-23
 */

val ActorSystemName = "ActorSystem"

fun main(args: Array<String>) {
    val system = ActorSystem.create(ActorSystemName, ConfigFactory.load("sharding-n1.conf"))
    Persistence.apply(system)

    // 分片配置
    val settings = ClusterShardingSettings.create(system)
    val extractor = ClusterExtractor()

    // 新的sharding region
    ClusterSharding.get(system).start("U", Props.create(UserActor::class.java), settings, extractor)

    // 获取region引用
    val actor = ClusterSharding.get(system).shardRegion("U")

    val systemIn = BufferedReader(InputStreamReader(System.`in`))
    while (true) {

        try {
            // 读取输入
            val s = systemIn.readLine().trim { it <= ' ' }

            val ss = s.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            if ("add" == ss[0]) {
                val id = Integer.parseInt(ss[1])
                val num = Integer.parseInt(ss[2])
                actor.tell(ClusterMessage(id, Add(num)), ActorRef.noSender())
            }

            if ("print" == ss[0]) {
                val id = Integer.parseInt(ss[1])
                actor.tell(ClusterMessage(id, Print()), ActorRef.noSender())
            }

            if ("printn" == ss[0]) {
                val n = Integer.parseInt(ss[1])
                for (i in 0 until n) {
                    actor.tell(ClusterMessage(i, Print()), ActorRef.noSender())
                }
            }

            if ("stop" == ss[0]) {
                val id = Integer.parseInt(ss[1])
                actor.tell(ClusterMessage(id, Stop()), ActorRef.noSender())
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
