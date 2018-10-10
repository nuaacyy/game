package tryakka.sample

import akka.actor.*
import akka.event.Logging
import scala.concurrent.duration.FiniteDuration

class CollectionTimeout

class DeviceGroupQuery(val actorToDeviceId: Map<ActorRef, String>,
					   val requestId: Long,
					   val requester: ActorRef,
					   timeout: FiniteDuration) : AbstractActor() {

	private val log = Logging.getLogger(context.system, this)

	var queryTimeoutTimer: Cancellable // 查询超时的调度计时

	init {
		queryTimeoutTimer = context.system.scheduler().scheduleOnce(
			timeout, self, CollectionTimeout(), context.dispatcher(), self
		)
	}

	companion object {
		fun props(actorToDeviceId: Map<ActorRef, String>, requestId: Long, requester: ActorRef, timeout: FiniteDuration): Props {
			return Props.create(DeviceGroupQuery::class.java, actorToDeviceId, requestId, requester, timeout)
		}
	}

	override fun preStart() {
		// 处理所有设备
		for (deviceActor in actorToDeviceId.keys) {
			// 针对所有要查询的设备，开始监控
			context.watch(deviceActor)

			// 发送温度查询消息
			deviceActor.tell(ReadTemperature(0L), self)
		}
	}

	override fun postStop() {
		// 取消超时
		queryTimeoutTimer.cancel()
	}

	override fun createReceive(): Receive {
		return waitingForReplies(HashMap(), actorToDeviceId.keys)
	}

	fun waitingForReplies(
		repliesSoFar: Map<String, TemperatureReading>,
		stillWaiting: Set<ActorRef>
	): Receive {
		return receiveBuilder()
			.match(RespondTemperature::class.java) { r ->
				// 根据响应做相关处理
				val deviceActor = sender
				val reading = if (r.value != TEMP_INVALID) {
					Temperature(r.value)
				} else {
					TemperatureNotAvailable()
				}
				receivedResponse(deviceActor, reading, stillWaiting, repliesSoFar)
			}
			.match(Terminated::class.java) { t ->
				// 根据响应做相关处理
				receivedResponse(t.actor, DeviceNotAvailable(), stillWaiting, repliesSoFar)
			}
			.match(CollectionTimeout::class.java) { t ->
				// 这个消息由调度触发
				// 到这里表示超时了
				val replies = HashMap(repliesSoFar)

				// 将剩余未查询到的标注为超时结果，填充响应
				for (deviceActor in stillWaiting) {
					val deviceId = actorToDeviceId[deviceActor]
					replies[deviceId] = DeviceTimedOut()
				}

				// 向请求者发送结果
				requester.tell(RespondAllTemperatures(requestId, replies), self)

				// 停止自己
				context.stop(self)
			}
			.build()
	}

	fun receivedResponse(deviceActor: ActorRef,
						 reading: TemperatureReading,
						 stillWaiting: Set<ActorRef>,
						 repliesSoFar: Map<String, TemperatureReading>) {
		// 解除观察目标设备
		context.unwatch(deviceActor)

		// 从剩余列表中移除
		val newStillWaiting = HashSet(stillWaiting)
		newStillWaiting.remove(deviceActor)

		// 将结果添加到结果Map中
		val newRepliesSoFar = HashMap(repliesSoFar)
		val deviceId = actorToDeviceId[deviceActor]
		newRepliesSoFar[deviceId] = reading

		if (newStillWaiting.isEmpty()) {
			// 如果剩余列表空了，发送总结果给查询者
			requester.tell(RespondAllTemperatures(requestId, newRepliesSoFar), self)

			// 停止自己
			context.stop(self)

		} else {
			// 使用新的Map构建Receive响应，然后替换现在的Receive。
			// 这个机制很重要！
			context.become(waitingForReplies(newRepliesSoFar, newStillWaiting))
		}
	}
}