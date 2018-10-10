package tryakka.sample

import akka.actor.*
import akka.event.Logging

const val TEMP_INVALID = -300.0

fun main(args: Array<String>) {
	// 创建ActorSystem
	val system = ActorSystem.create("iot-system")

	try {
		// 创建顶层的supervisor
		system.actorOf(IotSupervisor.props(), "iot-supervisor")

		println("Press ENTER to exit the system")
		System.`in`.read()

	} finally {
		system.terminate()
	}
}

class RequestTrackDevice(val groupId: String, val deviceId: String)

class DeviceRegistered

class RequestDeviceList(val requestId: Long)

class ReplyDeviceList(val requestId: Long, val ids: Set<String>)

class ReadTemperature(var requestId: Long)

class RespondTemperature(var requestId: Long, var value: Double)

class RecordTemperature(val requestId: Long, val value: Double)

class TemperatureRecorded(val requestId: Long)

class IotSupervisor : AbstractActor() {
	private val log = Logging.getLogger(context.system, this)

	companion object {
		fun props(): Props {
			return Props.create(IotSupervisor::class.java)
		}
	}

	override fun preStart() {
		log.info("IoT Application started")
	}

	override fun postStop() {
		log.info("IoT Application stopped")
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.build()
	}

}

