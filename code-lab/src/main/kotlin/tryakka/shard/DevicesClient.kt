package tryakka.shard

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props
import akka.cluster.sharding.ClusterSharding
import akka.cluster.sharding.ClusterShardingSettings
import akka.cluster.sharding.ShardRegion
import akka.event.Logging
import akka.pattern.PatternsCS
import akka.util.Timeout
import scala.concurrent.duration.Duration
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import java.util.function.BiConsumer

class DevicesClient : AbstractActor() {

    private val log = Logging.getLogger(context.system(), this)

    private val deviceRegion: ActorRef

    private val random = Random()

    private val numberOfDevices = 50

    enum class UpdateDevice {
        INSTANCE
    }

    init {
        val system = context.system

        // 获取 Region 引用
        //val roleOption = Option.none()
        val settings = ClusterShardingSettings.create(system)
        deviceRegion = ClusterSharding.get(context.system()).shardRegion("Counter")

        // 建立调度
        context.system.scheduler().schedule(
            Duration.create(5, TimeUnit.SECONDS),
            Duration.create(1, TimeUnit.SECONDS),
            self,
            UpdateDevice.INSTANCE,
            system.dispatcher(), null
        )
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(UpdateDevice::class.java) { u ->
                // 收到更新设备的消息后，向region发送消息
                println("收到 UpdateDevice")
                val deviceId = random.nextInt(numberOfDevices)
                val temperature = 5 + 30 * random.nextDouble()
                val msg = Device.RecordTemperature(deviceId, temperature)
               // println("发送消息到Device $msg")

                // 向 Region 发送消息
                //deviceRegion.tell(msg, self)

                PatternsCS.ask(deviceRegion, msg, Timeout(10, TimeUnit.MINUTES))
                    .whenCompleteAsync(BiConsumer { rt, err ->
                        println("aaaaaa $rt")
                    }, Executor { runnable ->
                        println("触发了 asExecutor $runnable")
                        self.tell(runnable, ActorRef.noSender())
                    })
            }
            .match(Runnable::class.java) {
                it.run()
            }
            .build()
    }

    companion object {
        fun props(): Props {
            return Props.create(DevicesClient::class.java)
        }
    }
}