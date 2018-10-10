package tryakka.sample

class RequestAllTemperatures(val requestId: Long)

class RespondAllTemperatures(val requestId: Long, val temperatures: Map<String, TemperatureReading>)

interface TemperatureReading

// 下面是温度读取结果
class Temperature(val value: Double) : TemperatureReading

class TemperatureNotAvailable : TemperatureReading

class DeviceNotAvailable : TemperatureReading

class DeviceTimedOut : TemperatureReading