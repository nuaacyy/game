package tryakka.sample

import akka.actor.ActorSystem
import akka.event.Logging
import akka.testkit.javadsl.TestKit
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DeviceTempCollectTest {
	val system = ActorSystem.create("test-system")
	private val log = Logging.getLogger(system, this)

	@Test
	fun testCollectTemperaturesFromAllActiveDevices() {
		val probe = TestKit(system)

		// 创建Group Actor
		val groupActor = system.actorOf(DeviceGroup.props("group"))

		// 下面添加了3个设备
		groupActor.tell(RequestTrackDevice("group", "device1"), probe.getRef())
		probe.expectMsgClass(DeviceRegistered::class.java)
		val deviceActor1 = probe.getLastSender()

		groupActor.tell(RequestTrackDevice("group", "device2"), probe.getRef())
		probe.expectMsgClass(DeviceRegistered::class.java)
		val deviceActor2 = probe.getLastSender()

		groupActor.tell(RequestTrackDevice("group", "device3"), probe.getRef())
		probe.expectMsgClass(DeviceRegistered::class.java)
		val deviceActor3 = probe.getLastSender()

		// 记录温度到两个设备
		deviceActor1.tell(RecordTemperature(0L, 1.0), probe.getRef())
		assertEquals(0L, probe.expectMsgClass(TemperatureRecorded::class.java).requestId)
		deviceActor2.tell(RecordTemperature(1L, 2.0), probe.getRef())
		assertEquals(1L, probe.expectMsgClass(TemperatureRecorded::class.java).requestId)
		// No temperature for device 3

		// 查询所有设备的温度
		groupActor.tell(RequestAllTemperatures(0L), probe.getRef())
		val response = probe.expectMsgClass(RespondAllTemperatures::class.java)
		assertEquals(0L, response.requestId)

		val expectedTemperatures = hashMapOf<String, TemperatureReading>()
		expectedTemperatures.put("device1", Temperature(1.0))
		expectedTemperatures.put("device2", Temperature(2.0))
		expectedTemperatures.put("device3", TemperatureNotAvailable())

		assertEqualTemperatures(expectedTemperatures, response.temperatures)
	}

	fun assertEqualTemperatures(expected: Map<String, TemperatureReading>, actual: Map<String, TemperatureReading>) {
		for ((key, expectedReading) in expected) {
			val actualReading = actual[key]
			TestCase.assertNotNull(actualReading)
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