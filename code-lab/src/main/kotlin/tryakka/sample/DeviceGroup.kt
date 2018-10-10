package tryakka.sample

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props
import akka.actor.Terminated
import akka.event.Logging
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS

class DeviceGroup(val groupId: String) : AbstractActor() {
	private val log = Logging.getLogger(context.system, this)

	val deviceIdToActor: MutableMap<String, ActorRef> = HashMap()
	val actorToDeviceId: MutableMap<ActorRef, String> = HashMap()

	override fun preStart() {
		log.info("DeviceGroup {} started", groupId)
	}

	override fun postStop() {
		log.info("DeviceGroup {} stopped", groupId)
	}

	private fun onTrackDevice(trackMsg: RequestTrackDevice) {
		if (this.groupId == trackMsg.groupId) {
			// 从map中查找
			var deviceActor = deviceIdToActor[trackMsg.deviceId]
			if (deviceActor != null) {
				// 转发消息
				deviceActor.forward(trackMsg, context)
			} else {
				log.info("Creating device actor for {}", trackMsg.deviceId)

				// 创建设备Actor
				// 跟踪这个设备
				// 并记录到Map中
				deviceActor = context.actorOf(Device.props(groupId, trackMsg.deviceId), "device-" + trackMsg.deviceId)
				context.watch(deviceActor)
				deviceIdToActor[trackMsg.deviceId] = deviceActor
				actorToDeviceId[deviceActor] = trackMsg.deviceId

				// 转发消息
				deviceActor.forward(trackMsg, context)
			}

		} else {
			// 不是一个组的
			log.warning(
				"Ignoring TrackDevice request for {}. This actor is responsible for {}.",
				groupId, this.groupId
			)
		}
	}

	private fun onDeviceList(r: RequestDeviceList) {
		log.info("处理查询设备列表的请求")
		sender.tell(ReplyDeviceList(r.requestId, deviceIdToActor.keys), self)
	}

	/**
	 * 当收到子Actor终止的消息时，执行这个方法
	 */
	private fun onTerminated(t: Terminated) {
		val deviceActor = t.actor
		val deviceId = actorToDeviceId[deviceActor]
		log.info("当前观察的目标Actor ${deviceId} 终止了")
		actorToDeviceId.remove(deviceActor)
		deviceIdToActor.remove(deviceId)
	}

	private fun onAllTemperatures(r: RequestAllTemperatures) {
		context.actorOf(DeviceGroupQuery.props(
			actorToDeviceId, r.requestId, sender, FiniteDuration(3, TimeUnit.SECONDS)))
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.match(RequestTrackDevice::class.java, this::onTrackDevice) // 跟踪设备
			.match(RequestDeviceList::class.java, this::onDeviceList) // 请求设备列表
			.match(Terminated::class.java, this::onTerminated) // 收到Actor终止的消息
			.match(RequestAllTemperatures::class.java, this::onAllTemperatures)
			.build()
	}

	companion object {

		fun props(groupId: String): Props {
			return Props.create(DeviceGroup::class.java, groupId)
		}
	}
}