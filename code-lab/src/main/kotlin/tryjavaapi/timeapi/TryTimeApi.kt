package tryjavaapi.timeapi

import xyz.ariane.util.systemDefaultZoneId
import java.text.SimpleDateFormat
import java.time.*
import java.util.*

fun main(args: Array<String>) {
    // LocalDateTime的使用
    dateTimeCal()

    instAndDateTimeCal()
}

fun dateTimeCal() {

    val localDate = LocalDate.now()
    val localDateTime = LocalDateTime.of(localDate.year, localDate.month, localDate.dayOfMonth, 23, 0, 0)
    val nowDateTime = LocalDateTime.now()
    if (nowDateTime < localDateTime) {
        println("现在比预定时间早")
    } else {
        println("现在比预定时间晚")
    }
    val beforeDateTime = localDateTime.plusDays(-1)

    println("当前时间：$nowDateTime ${nowDateTime.withHour(0)}")
    println("预定时间：$localDateTime")
    println("新的时间：$beforeDateTime")

    println("当前时间戳（新）：${nowDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()}")
    println("当前时间戳（旧）：${System.currentTimeMillis()}")
    println("${ZoneOffset.systemDefault()}")

    println("${ZoneId.systemDefault()}")
}

fun instAndDateTimeCal() {
    println("---------------------------------")
    val date = Date()
    println("Date：$date  ${date.time} ${System.currentTimeMillis()}")
    val inst = date.toInstant()
    println("Instant：$inst ${inst.toEpochMilli()}")
    val zonedDateTime = inst.atZone(systemDefaultZoneId)
    println("ZonedDateTime：$zonedDateTime")
    val plusDaysZonedDateTime = zonedDateTime.plusDays(3)
    println("ZonedDateTime Plus 3 Days：$plusDaysZonedDateTime")
    println("new instant：${plusDaysZonedDateTime.toInstant()}")
}