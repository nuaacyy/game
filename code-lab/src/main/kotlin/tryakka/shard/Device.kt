package tryakka.shard

import akka.actor.AbstractActor
import akka.event.Logging
import java.io.Serializable
import java.util.*

class Device : AbstractActor() {

    private val log = Logging.getLogger(context.system(), this)

    private val temperatures = ArrayList<Double>()

    class RecordTemperature(val deviceId: Int, val temperature: Double) : Serializable {

        override fun toString(): String {
            return "RecordTemperature($deviceId, $temperature)"
        }
    }

    override fun preStart() {
        println("在 Region 中创建 Device")
    }

    override fun createReceive(): Receive {
        return receiveBuilder()
            .match(RecordTemperature::class.java) { r ->
                // 收到记录温度的消息
                temperatures.add(r.temperature)

                println(
                    "Recording temperature ${r.temperature} for device ${r.deviceId}, average is ${sum(temperatures) / temperatures.size} " +
                        "after ${temperatures.size} readings"
                )

                sender.tell("OOO", self)
            }
            .build()
    }

    private fun sum(doubles: List<Double>): Double {
        var result = 0.0
        for (d in doubles) {
            result += d
        }
        return result
    }
}