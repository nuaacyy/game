package tryakka.sample

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.PoisonPill
import akka.event.Logging
import akka.testkit.javadsl.TestKit
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

class DeviceTempQueryTest {
	val system = ActorSystem.create("test-system")
	private val log = Logging.getLogger(system, this)

	@Test
	fun testReturnTemperatureValueForWorkingDevices() {
		val requester = TestKit(system)

		val device1 = TestKit(system)
		val device2 = TestKit(system)

		// 要读取温度的设备表
		val actorToDeviceId = hashMapOf<ActorRef, String>()
		actorToDeviceId.put(device1.ref, "device1")
		actorToDeviceId.put(device2.ref, "device2")

		// 创建查询Actor
		val queryActor = system.actorOf(DeviceGroupQuery.props(
			actorToDeviceId,
			1L,
			requester.ref,
			FiniteDuration(3, TimeUnit.SECONDS)))

		// 确认收到的请求ID是0
		TestCase.assertEquals(0L, device1.expectMsgClass(ReadTemperature::class.java).requestId)
		TestCase.assertEquals(0L, device2.expectMsgClass(ReadTemperature::class.java).requestId)

		// 返回温度响应消息
		queryActor.tell(RespondTemperature(0L, 1.0), device1.ref)
		queryActor.tell(RespondTemperature(0L, 2.0), device2.ref)

		val response = requester.expectMsgClass(RespondAllTemperatures::class.java)
		TestCase.assertEquals(1L, response.requestId) // 这个和创建Query Actor时设置的ID一致

		val expectedTemperatures = hashMapOf<String, TemperatureReading>()
		expectedTemperatures.put("device1", Temperature(1.0))
		expectedTemperatures.put("device2", Temperature(2.0))

		assertEqualTemperatures(expectedTemperatures, response.temperatures)
	}

	@Test
	fun testReturnTemperatureNotAvailableForDevicesWithNoReadings() {
		val requester = TestKit(system)

		val device1 = TestKit(system)
		val device2 = TestKit(system)

		// 要读取温度的设备表
		val actorToDeviceId = hashMapOf<ActorRef, String>()
		actorToDeviceId.put(device1.ref, "device1")
		actorToDeviceId.put(device2.ref, "device2")

		// 创建查询Actor
		val queryActor = system.actorOf(DeviceGroupQuery.props(
			actorToDeviceId,
			1L,
			requester.ref,
			FiniteDuration(3, TimeUnit.SECONDS)))

		assertEquals(0L, device1.expectMsgClass(ReadTemperature::class.java).requestId)
		assertEquals(0L, device2.expectMsgClass(ReadTemperature::class.java).requestId)

		// 返回温度响应消息
		queryActor.tell(RespondTemperature(0L, TEMP_INVALID), device1.ref)
		queryActor.tell(RespondTemperature(0L, 2.0), device2.ref)

		val response = requester.expectMsgClass(RespondAllTemperatures::class.java)
		assertEquals(1L, response.requestId)

		val expectedTemperatures = hashMapOf<String, TemperatureReading>()
		expectedTemperatures.put("device1", TemperatureNotAvailable())
		expectedTemperatures.put("device2", Temperature(2.0))

		assertEqualTemperatures(expectedTemperatures, response.temperatures)
	}

	@Test
	fun testReturnDeviceNotAvailableIfDeviceStopsBeforeAnswering() {
		val requester = TestKit(system)

		val device1 = TestKit(system)
		val device2 = TestKit(system)

		val actorToDeviceId = hashMapOf<ActorRef, String>()
		actorToDeviceId.put(device1.ref, "device1")
		actorToDeviceId.put(device2.ref, "device2")

		val queryActor = system.actorOf(DeviceGroupQuery.props(
			actorToDeviceId,
			1L,
			requester.ref,
			FiniteDuration(3, TimeUnit.SECONDS)))

		assertEquals(0L, device1.expectMsgClass(ReadTemperature::class.java).requestId)
		assertEquals(0L, device2.expectMsgClass(ReadTemperature::class.java).requestId)

		queryActor.tell(RespondTemperature(0L, 1.0), device1.ref)
		device2.ref.tell(PoisonPill.getInstance(), ActorRef.noSender()) // 这里直接停止了设备2

		val response = requester.expectMsgClass(RespondAllTemperatures::class.java)
		assertEquals(1L, response.requestId)

		val expectedTemperatures = hashMapOf<String, TemperatureReading>()
		expectedTemperatures.put("device1", Temperature(1.0))
		expectedTemperatures.put("device2", DeviceNotAvailable()) // 一个设备不存在

		assertEqualTemperatures(expectedTemperatures, response.temperatures)
	}

	@Test
	fun testReturnTemperatureReadingEvenIfDeviceStopsAfterAnswering() {
		val requester = TestKit(system)

		val device1 = TestKit(system)
		val device2 = TestKit(system)

		val actorToDeviceId = hashMapOf<ActorRef, String>()
		actorToDeviceId.put(device1.ref, "device1")
		actorToDeviceId.put(device2.ref, "device2")

		val queryActor = system.actorOf(DeviceGroupQuery.props(
			actorToDeviceId,
			1L,
			requester.ref,
			FiniteDuration(3, TimeUnit.SECONDS)))

		assertEquals(0L, device1.expectMsgClass(ReadTemperature::class.java).requestId)
		assertEquals(0L, device2.expectMsgClass(ReadTemperature::class.java).requestId)

		// 这里模拟了设备2在返回了温度后终止了
		queryActor.tell(RespondTemperature(0L, 1.0), device1.ref)
		queryActor.tell(RespondTemperature(0L, 2.0), device2.ref)
		device2.ref.tell(PoisonPill.getInstance(), ActorRef.noSender())

		val response = requester.expectMsgClass(RespondAllTemperatures::class.java)
		assertEquals(1L, response.requestId)

		// 期望的结果是：能收到正确的温度数据
		val expectedTemperatures = hashMapOf<String, TemperatureReading>()
		expectedTemperatures.put("device1", Temperature(1.0))
		expectedTemperatures.put("device2", Temperature(2.0))

		assertEqualTemperatures(expectedTemperatures, response.temperatures)
	}

	@Test
	fun testReturnDeviceTimedOutIfDeviceDoesNotAnswerInTime() {
		val requester = TestKit(system)

		val device1 = TestKit(system)
		val device2 = TestKit(system)

		val actorToDeviceId = hashMapOf<ActorRef, String>()
		actorToDeviceId.put(device1.ref, "device1")
		actorToDeviceId.put(device2.ref, "device2")

		val queryActor = system.actorOf(DeviceGroupQuery.props(
			actorToDeviceId,
			1L,
			requester.ref,
			FiniteDuration(3, TimeUnit.SECONDS)))

		assertEquals(0L, device1.expectMsgClass(ReadTemperature::class.java).requestId)
		assertEquals(0L, device2.expectMsgClass(ReadTemperature::class.java).requestId)

		// 设备1返回了正确的温度
		queryActor.tell(RespondTemperature(0L, 1.0), device1.ref)

		// 等待了5秒，这样有足够的时间超时
		// 这里期待5秒内能得到期望的温度结果
		val response = requester.expectMsgClass(
			FiniteDuration.create(5, TimeUnit.SECONDS),
			RespondAllTemperatures::class.java)
		assertEquals(1L, response.requestId)

		// 期望的结果就是设备2超时
		val expectedTemperatures = hashMapOf<String, TemperatureReading>()
		expectedTemperatures.put("device1", Temperature(1.0))
		expectedTemperatures.put("device2", DeviceTimedOut())

		assertEqualTemperatures(expectedTemperatures, response.temperatures)
	}

	fun assertEqualTemperatures(expected: Map<String, TemperatureReading>, actual: Map<String, TemperatureReading>) {
		for ((key, expectedReading) in expected) {
			val actualReading = actual[key]
			assertNotNull(actualReading)
			if (actualReading != null) {
				assertEquals(expectedReading::class.java, actualReading::class.java)
			}
			if (expectedReading is Temperature) {
				assertEquals((expectedReading).value, (actualReading as Temperature).value, 0.01)
			}
		}
		assertEquals(expected.size, actual.size)
	}
}