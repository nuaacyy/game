package trykotlin.datetime.daysbetween

import java.time.temporal.ChronoUnit
import java.time.Period
import java.time.LocalDate

fun main(args: Array<String>) {
    val specifyDate = LocalDate.of(2015, 10, 2)
    val afterDate = LocalDate.of(2015, 10, 4)

    val period = Period.between(specifyDate, afterDate)

    println(period.days)  // 4
    println(period.months) // 1
    println(specifyDate.until(afterDate, ChronoUnit.DAYS)) // 401
    println(afterDate.until(specifyDate, ChronoUnit.DAYS)) // 401
}