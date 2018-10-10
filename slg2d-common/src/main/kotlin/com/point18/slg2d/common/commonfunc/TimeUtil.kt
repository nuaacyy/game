package com.point18.slg2d.common.commonfunc

import com.point18.slg2d.common.pc.pcs
import xyz.ariane.util.toDefaultEpochMilli
import java.time.*
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.util.*

const val ONE_SECOND_MILLS = 1000L
const val ONE_MINUTE_MILLS = 60 * ONE_SECOND_MILLS
const val ONE_HOUR_MILLS = 60 * ONE_MINUTE_MILLS
const val ONE_DAY_MILLS = 24 * ONE_HOUR_MILLS


//获取当前时间
fun getNowTime(): Long {
    return System.currentTimeMillis()
}

fun getDayZeroTime(now: LocalDateTime): LocalDateTime {
    return LocalDateTime.of(now.toLocalDate(), LocalTime.of(0, 0))
}

// 获取给定时间当天的给定时分秒的时间
fun getSpecifiedTime(timestamp: Long,hour: Int, min: Int, sec: Int): Long {
    val localDate = milliToLocalDateTime(timestamp).toLocalDate()
    val localDateTime = localDate.atStartOfDay().withHour(hour).withMinute(min).withSecond(sec)
    return localDateTimeToMilli(localDateTime)
}

// 获取给定时间那周的周N(1~7)的凌晨时间
fun getWeekDateZeroTime(timeM: Long, week: Int): Long {
    val calendar = Calendar.getInstance()
    if (timeM != 0L) {
        calendar.timeInMillis = timeM
    }
    // 获取当前是周几(1~7)
    var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
    if (dayOfWeek == 0) {
        dayOfWeek = 7
    }
    calendar.add(Calendar.DATE, -dayOfWeek + week)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis
}

// 用于刷新每日重置的功能 游戏里不采用0点刷新 而是配置时间, 是否该刷
fun isAfterGameRefTime(lastRefTime: Long): Boolean {
    if (lastRefTime == 0L) {
        return true
    }

    val localTime =
        LocalTime.of(pcs.basicProtoCache.getVipRewardRefreshHour, pcs.basicProtoCache.getVipRewardRefreshMinute)
    var localDateTime = LocalDateTime.of(LocalDate.now(), localTime)
    val localNow = LocalDateTime.now()
    if (localNow < localDateTime) {
        localDateTime = localDateTime.plusDays(1)
    }
    return lastRefTime < localDateTime.toDefaultEpochMilli()

//    val cal = Calendar.getInstance()
//    cal.set(Calendar.HOUR_OF_DAY, pcs.basicProtoCache.getVipRewardRefreshHour)
//    cal.set(Calendar.MINUTE, pcs.basicProtoCache.getVipRewardRefreshMinute)
//    cal.set(Calendar.SECOND, 0)
//
//    var refTime = cal.timeInMillis
//    if (getNowTime() < refTime) {
//        // 如果当前时间 < 本日刷新时间
//        refTime -= 24 * 60 * 60 * 1000
//    }
//
//    return lastRefTime < refTime
}

// 用于刷新每日重置的功能 根据当前时间获取刷新时间
fun getNextGameRefTime(): Long {
    val refHour = pcs.basicProtoCache.getVipRewardRefreshHour
    val refMin = pcs.basicProtoCache.getVipRewardRefreshMinute

    val refDZeroTime = getDayZeroTime(LocalDateTime.now())
    val refDt = refDZeroTime.plusHours(refHour.toLong()).plusMinutes(refMin.toLong())
    val nowDt = LocalDateTime.now()
    return if (nowDt > refDt) {
        refDt.plusDays(1).toDefaultEpochMilli()
    } else {
        refDt.toDefaultEpochMilli()
    }
//    val refTime = getDayZeroTime(Date(), 0).time + refHour * 3600 * 1000 + refMin * 60 * 1000
//    val nowTime = getNowTime()
//    return if (nowTime >= refTime) {
//        refTime + 24 * 3600 * 1000
//    } else {
//        refTime
//    }
}

/**
判断两个时间相差多少天
 */
fun getDaysOfTwo(t1: Date, t2: Date): Int {
    return (Math.abs(t1.time - t2.time) / (1000 * 60 * 60 * 24)).toInt()
}

/**
求t1是t2的多少天之后
 */
fun getDaysOfTwo2(t1: Date, t2: Date): Int {
    val instant1 = t1.toInstant()
    val zone1 = ZoneId.systemDefault()
    val localDateTime1 = LocalDateTime.ofInstant(instant1, zone1)
    val localDate1 = localDateTime1.toLocalDate()

    val instant2 = t2.toInstant()
    val zone2 = ZoneId.systemDefault()
    val localDateTime2 = LocalDateTime.ofInstant(instant2, zone2)
    val localDate2 = localDateTime2.toLocalDate()
    val days = localDate2.until(localDate1, ChronoUnit.DAYS)
    if (days < 0L) {
        // t1比t2小！
        return -1
    }
    return days.toInt()
}

/**
求t1是t2的多少天之后
 */
fun getDaysOfTwo2ByMs(ms1: Long, ms2: Long): Int {
    val t1 = Date(ms1)
    val t2 = Date(ms2)
    return getDaysOfTwo2(t1, t2)
}

// 获取当前的毫秒时间
fun getNowMTime(): Long {
    return Date().time
}

/**
获取时间的秒(自从1970年1月1号到现在)
 */
fun getTimeSec(t: Date): Int {
    return getTimeSec(t.time)
}

fun getTimeSec(t: Long): Int {
    return (t / 1000).toInt()
}

fun getTime(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day, hour, minute, second)
    return calendar.timeInMillis
}

// 表示数据库中的最早时间
val zeroTime = Date(0)

// 表示最大时间
val maxTime = Date(Long.MAX_VALUE)

fun sec2MilliSec(sec: Int): Long {
    return sec.toLong() * 1000
}

/**
 * 将毫秒时间戳转换为LocalDateTime
 *
 * @param milli
 *            毫秒时间戳
 * @return
 */
fun milliToLocalDateTime(milli: Long): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneId.systemDefault())
}

/**
 * 判断指定时间戳time是否与当前时间处于同一天
 *
 * @param time
 * @return
 */
fun inDay(time: Long): Boolean {
    val day = milliToLocalDateTime(time).toLocalDate()
    return LocalDate.now().isEqual(day)
}

/**
 * 获得本年指定的月日时间
 * @param month 指定月
 * @param dayMonth 指定日
 */
fun thisYearDate(month: Int, dayMonth: Int): LocalDate {
    return LocalDate.now().withMonth(month).withDayOfMonth(dayMonth)
}

/**
 * 获得本月指定的日时间
 * @param dayMonth 指定日月
 */
fun thisMonthDate(dayMonth: Int): LocalDate {
    return LocalDate.now().withDayOfMonth(dayMonth)
}

/**
 * 获得本周指定的日时间
 * @param dayWeek 指定日周
 */
fun thisWeekDate(dayWeek: Int): LocalDate {
    return LocalDate.now().with(ChronoField.DAY_OF_WEEK, dayWeek.toLong())
}

fun localDateTimeToMilli(localDateTime: LocalDateTime): Long {
    return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

/**
 * 指定时间离现在隔了多少天(昨天今天算一天)
 */
fun betweenDays(time: Long): Int {
    val tmpLocalDate = milliToLocalDateTime(time).toLocalDate()
    return Math.abs(tmpLocalDate.until(LocalDate.now()).days)
}