package tryakka.sample

import akka.actor.AbstractActor
import akka.actor.Props
import akka.event.Logging

class Device(val groupId: String, val deviceId: String) : AbstractActor() {
	private val log = Logging.getLogger(context.system, this)

	var lastTemperatureReading: Double = TEMP_INVALID

	override fun preStart() {
		log.info("Device actor {}-{} started", groupId, deviceId)
	}

	override fun postStop() {
		log.info("Device actor {}-{} stopped", groupId, deviceId)
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.match(RequestTrackDevice::class.java, { r ->
				// 请求跟踪设备？
				if (this.groupId.equals(r.groupId) && this.deviceId.equals(r.deviceId)) {
					getSender().tell(DeviceRegistered(), getSelf());
				} else {
					log.warning(
						"Ignoring TrackDevice request for {}-{}.",
						r.groupId, r.deviceId
					);
					log.warning(
						"This actor is responsible for {}-{}.",
						this.groupId, this.deviceId
					);
				}
			})
			.match(RecordTemperature::class.java, { r ->
				log.info("Recorded temperature reading {} with {}", r.value, r.requestId);

				// 保存温度
				lastTemperatureReading = r.value;

				// 发送响应Msg
				getSender().tell(TemperatureRecorded(r.requestId), getSelf());
			})
			.match(ReadTemperature::class.java) { r ->
				// 发送响应Msg
				val temp = RespondTemperature(r.requestId, lastTemperatureReading)
				sender.tell(temp, self)
			}
			.build()
	}

	companion object {

		fun props(groupId: String, deviceId: String): Props {
			return Props.create(Device::class.java, groupId, deviceId)
		}
	}

}