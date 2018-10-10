package tryakka.shard

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props
import akka.cluster.sharding.ClusterSharding
import akka.cluster.sharding.ClusterShardingSettings
import akka.cluster.sharding.ShardRegion
import akka.event.Logging
import java.util.*

class Devices : AbstractActor() {

    private val log = Logging.getLogger(context.system(), this)

    private val deviceRegion: ActorRef

    private val random = Random()

    private val numberOfDevices = 50

    enum class UpdateDevice {
        INSTANCE
    }

    init {
        // 创建 Region
        val system = context.system
        //val roleOption = Option.none()
        val settings = ClusterShardingSettings.create(system).withRole("Counter")
        deviceRegion = ClusterSharding.get(system)
            .start(
                "Counter",
                Props.create(Device::class.java),
                settings,
                messageExtractor
            )
        println("Counter Shard started")

        // 建立调度
//        context.system.scheduler().schedule(
//            Duration.create(10, TimeUnit.SECONDS),
//            Duration.create(1, TimeUnit.SECONDS),
//            self,
//            UpdateDevice.INSTANCE,
//            system.dispatcher(), null
//        )
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(UpdateDevice::class.java) { u ->
                // 收到更新设备的消息后，向region发送消息
                println("收到 UpdateDevice")
                val deviceId = random.nextInt(numberOfDevices)
                val temperature = 5 + 30 * random.nextDouble()
                val msg = Device.RecordTemperature(deviceId, temperature)
                println("发送消息到Device $msg")

                // 向 Region 发送消息
                deviceRegion.tell(msg, self)
            }
            .build()
    }

    companion object {

        // 消息解析
        internal var messageExtractor: ShardRegion.MessageExtractor = object : ShardRegion.MessageExtractor {

            override fun entityId(message: Any): String? {
                val entityId = (message as? Device.RecordTemperature)?.deviceId?.toString()
                println("生成的EntityId为 ${entityId}")
                return entityId
            }

            override fun entityMessage(message: Any): Any {
                return message as? Device.RecordTemperature ?: message
            }

            override fun shardId(message: Any): String? {
                var shardId: String? = null
                val numberOfShards = 100
                if (message is Device.RecordTemperature) {
                    val id = message.deviceId.toLong()
                    shardId = (id % numberOfShards).toString()
                    // Needed if you want to use 'remember entities':
                    // } else if (message instanceof ShardRegion.StartEntity) {
                    //   long id = ((ShardRegion.StartEntity) message).id;
                    //   return String.valueOf(id % numberOfShards)
                }

                println("生成的ShardId为 ${shardId}")
                return shardId
            }
        }
    }
}