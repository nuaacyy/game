package tryakka.sample

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props
import akka.actor.Terminated
import akka.event.Logging

/**
 * 设备管理器
 */
class DeviceManager : AbstractActor() {
	private val log = Logging.getLogger(context.system, this)

	val groupIdToActor: MutableMap<String, ActorRef> = HashMap()
	val actorToGroupId: MutableMap<ActorRef, String> = HashMap()

	companion object {

		fun props(): Props {
			return Props.create(DeviceManager::class.java)
		}
	}

	override fun preStart() {
		log.info("DeviceManager started")
	}

	override fun postStop() {
		log.info("DeviceManager stopped")
	}

	private fun onTrackDevice(trackMsg: RequestTrackDevice) {
		val groupId = trackMsg.groupId
		val ref = groupIdToActor[groupId]
		if (ref != null) {
			// 转发消息
			ref.forward(trackMsg, context)
		} else {
			log.info("Creating device group actor for {}", groupId)

			// 创建Group Actor
			val groupActor = context.actorOf(DeviceGroup.props(groupId), "group-" + groupId)

			// 观察Group Actor
			context.watch(groupActor)

			// 转发消息到Group Actor
			groupActor.forward(trackMsg, context)

			// 记录到组观察表中
			groupIdToActor.put(groupId, groupActor)
			actorToGroupId.put(groupActor, groupId)
		}
	}

	/**
	 * 收到终止消息时移除组设备
	 */
	private fun onTerminated(t: Terminated) {
		val groupActor = t.actor
		val groupId: String? = actorToGroupId[groupActor]

		log.info("Device group actor for {} has been terminated", groupId)

		actorToGroupId.remove(groupActor)
		groupIdToActor.remove(groupId)
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.match(RequestTrackDevice::class.java, this::onTrackDevice)
			.match(Terminated::class.java, this::onTerminated)
			.build()
	}

}