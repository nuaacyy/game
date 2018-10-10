package jh

import com.point18.slg2d.common.commonfunc.localDateTimeToMilli
import com.point18.slg2d.common.commonfunc.milliToLocalDateTime

fun main(args: Array<String>) {
    val localDate = milliToLocalDateTime(System.currentTimeMillis()).toLocalDate()
    val localDateTime = localDate.atStartOfDay().withHour(0).withMinute(0).withSecond(0)
    println(localDateTimeToMilli(localDateTime))
    println(System.currentTimeMillis())
}