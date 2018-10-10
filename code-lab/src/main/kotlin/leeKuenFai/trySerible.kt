package leeKuenFai

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.point18.slg2d.common.commonfunc.toJson

fun main(args: Array<String>) {
    val mutableListAA = mutableListOf<AA1>()

    val a1 = AA1()
    val aa1 = AA1()
    mutableListAA.add(a1)
    mutableListAA.add(aa1)
    println(toJson(mutableListAA))
//    val a1string = toMyJson(a1)
//    println(a1string)
//
//    val a3 = toMyObject<AA3>(a1string)
//    println(toMyJson(a3))
//
//    val a2 = toMyObject<AA2>(a1string)
//    println(toMyJson(a2))

}

fun toMyJson(o: Any): String {
    val mapper = jacksonObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

    return mapper.writeValueAsString(o)
}

inline fun <reified T : Any> toMyObject(s: String): T {
    val mapper = jacksonObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    return mapper.readValue<T>(s)
}

class AA1 {
    val a = 1
    val b = "aa1"
    val version = 1
}

class AA2 {
    val a = 1
    val version = 2
}

class AA3 {
    val a = 1
    val b = "aa3"
    val c = 3.14
    val version = 3
}