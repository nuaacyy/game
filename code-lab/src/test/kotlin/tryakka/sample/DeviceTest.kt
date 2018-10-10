package tryakka.sample

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.PoisonPill
import akka.event.Logging
import akka.testkit.javadsl.TestKit
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.util.stream.Collectors
import java.util.stream.Stream

class DeviceTest {

	val system = ActorSystem.create("test-system")
	private val log = Logging.getLogger(system, this)

	@Test
	fun testReplyWithEmptyReadingIfNoTemperatureIsKnown() {

		val probe = TestKit(system)

		// 创建Actor
		val deviceActor = system.actorOf(Device.props("group", "device"))

		// 发送读取温度消息
		deviceActor.tell(ReadTemperature(42L), probe.getRef())

		// 获取响应
		val response = probe.expectMsgClass(RespondTemperature::class.java)
		assertEquals(42L, response.requestId)
		assertEquals(-300.0, response.value)
	}

	@Test
	fun testReplyWithLatestTemperatureReading() {
		val probe = TestKit(system)

		// 创建Actor
		val deviceActor = system.actorOf(Device.props("group", "device"))

		// 发送记录温度Msg
		deviceActor.tell(RecordTemperature(1L, 24.0), probe.ref)
		assertEquals(1L, probe.expectMsgClass(TemperatureRecorded::class.java).requestId)

		// 发送读取温度Msg
		deviceActor.tell(ReadTemperature(2L), probe.ref)
		val response1 = probe.expectMsgClass(RespondTemperature::class.java)
		assertEquals(2L, response1.requestId)
		assertEquals(24.0, response1.value)

		// 发送记录温度Msg
		deviceActor.tell(RecordTemperature(3L, 55.0), probe.ref)
		assertEquals(3L, probe.expectMsgClass(TemperatureRecorded::class.java).requestId)

		// 发送读取温度Msg
		deviceActor.tell(ReadTemperature(4L), probe.ref)
		val response2 = probe.expectMsgClass(RespondTemperature::class.java)
		assertEquals(4L, response2.requestId)
		assertEquals(55.0, response2.value)
	}

	@Test
	fun testReplyToRegistrationRequests() {
		val probe = TestKit(system)
		val deviceActor = system.actorOf(Device.props("group", "device"))

		deviceActor.tell(RequestTrackDevice("group", "device"), probe.ref)
		probe.expectMsgClass(DeviceRegistered::class.java)
		assertEquals(deviceActor, probe.lastSender)

		println("成功返回 DeviceRegistered 消息")
	}

	@Test
	fun testIgnoreWrongRegistrationRequests() {
		val probe = TestKit(system)
		val deviceActor = system.actorOf(Device.props("group", "device"))

		println("发送错误的 RequestTrackDevice 消息")
		deviceActor.tell(RequestTrackDevice("wrongGroup", "device"), probe.ref)
		probe.expectNoMsg()

		println("发送错误的 RequestTrackDevice 消息")
		deviceActor.tell(RequestTrackDevice("group", "wrongDevice"), probe.ref)
		probe.expectNoMsg()
	}

	@Test
	fun testRegisterDeviceActor() {
		val probe = TestKit(system)

		// 创建Group Actor
		val groupActor = system.actorOf(DeviceGroup.props("group"))

		// 发送跟踪消息
		groupActor.tell(RequestTrackDevice("group", "device1"), probe.ref)
		probe.expectMsgClass(DeviceRegistered::class.java)
		val deviceActor1 = probe.lastSender

		// 发送跟踪消息
		groupActor.tell(RequestTrackDevice("group", "device2"), probe.ref)
		probe.expectMsgClass(DeviceRegistered::class.java)
		val deviceActor2 = probe.lastSender

		// 确认两个ActorRef不是指向一个Actor
		assertNotEquals(deviceActor1, deviceActor2)

		// 确认设备正常工作
		deviceActor1.tell(RecordTemperature(0L, 1.0), probe.ref)
		assertEquals(0L, probe.expectMsgClass(TemperatureRecorded::class.java).requestId)
		deviceActor2.tell(RecordTemperature(1L, 2.0), probe.ref)
		assertEquals(1L, probe.expectMsgClass(TemperatureRecorded::class.java).requestId)
	}

	@Test
	fun testIgnoreRequestsForWrongGroupId() {
		val probe = TestKit(system)

		// 创建Group Actor
		val groupActor = system.actorOf(DeviceGroup.props("group"))

		// 发送消息
		groupActor.tell(RequestTrackDevice("wrongGroup", "device1"), probe.ref)
		probe.expectNoMsg()
	}

	@Test
	fun testReturnSameActorForSameDeviceId() {
		val probe = TestKit(system)

		// 创建Group Actor
		val groupActor = system.actorOf(DeviceGroup.props("group"))

		// 发送跟踪消息
		groupActor.tell(RequestTrackDevice("group", "device1"), probe.ref)
		probe.expectMsgClass(DeviceRegistered::class.java)
		val deviceActor1 = probe.lastSender

		// 发送跟踪消息
		groupActor.tell(RequestTrackDevice("group", "device1"), probe.ref)
		probe.expectMsgClass(DeviceRegistered::class.java)
		val deviceActor2 = probe.lastSender
		assertEquals(deviceActor1, deviceActor2)
	}

	@Test
	fun testListActiveDevices() {
		val probe = TestKit(system)

		// 创建Group Actor
		val groupActor = system.actorOf(DeviceGroup.props("group"))

		// 发送跟踪Msg
		groupActor.tell(RequestTrackDevice("group", "device1"), probe.ref)
		probe.expectMsgClass(DeviceRegistered::class.java)

		// 发送跟踪Msg
		groupActor.tell(RequestTrackDevice("group", "device2"), probe.ref)
		probe.expectMsgClass(DeviceRegistered::class.java)

		// 发送请求设备列表Msg
		groupActor.tell(RequestDeviceList(0L), probe.ref)
		val reply = probe.expectMsgClass(ReplyDeviceList::class.java)
		assertEquals(0L, reply.requestId)
		assertEquals(Stream.of("device1", "device2").collect(Collectors.toSet<String>()), reply.ids)
	}

	@Test
	fun testListActiveDevicesAfterOneShutsDown() {
		val probe = TestKit(system)

		// 创建Group Actor
		val groupActor = system.actorOf(DeviceGroup.props("group"))

		// 发送跟踪Msg
		groupActor.tell(RequestTrackDevice("group", "device1"), probe.ref)
		probe.expectMsgClass(DeviceRegistered::class.java)
		val toShutDown = probe.lastSender // 这里根据消息获取消息发送者，也就是device1

		// 发送跟踪Msg
		groupActor.tell(RequestTrackDevice("group", "device2"), probe.ref)
		probe.expectMsgClass(DeviceRegistered::class.java)

		// 请求设备列表
		groupActor.tell(RequestDeviceList(0L), probe.ref)
		val reply = probe.expectMsgClass(ReplyDeviceList::class.java)
		assertEquals(0L, reply.requestId)
		assertEquals(Stream.of("device1", "device2").collect(Collectors.toSet<String>()), reply.ids)

		probe.watch(toShutDown) // 观察目标设备

		// 发送消息停止目标设备
		log.info("准备发送 停止设备 消息")
		toShutDown.tell(PoisonPill.getInstance(), ActorRef.noSender())
		probe.expectTerminated(toShutDown) // 设备应该停止
		log.info("确认了 目标设备已终止")

		// using awaitAssert to retry because it might take longer for the groupActor
		// to see the Terminated, that order is undefined
		probe.awaitAssert<Any> {
			log.info("重新查询设备列表")
			groupActor.tell(RequestDeviceList(1L), probe.ref)
			val r = probe.expectMsgClass(ReplyDeviceList::class.java)
			assertEquals(1L, r.requestId)
			assertEquals(Stream.of("device2").collect(Collectors.toSet<String>()), r.ids)
			log.info("设备列表符合预期")
			null
		}
	}

}
