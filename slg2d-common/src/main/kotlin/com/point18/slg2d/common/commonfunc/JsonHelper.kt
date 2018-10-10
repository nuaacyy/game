package com.point18.slg2d.common.commonfunc

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

val mapper = jacksonObjectMapper()

fun <T> toJson(t: T): String {
    return mapper.writeValueAsString(t)
}

inline fun <reified T : Any> toObj(json: String): T {
    return mapper.readValue(json)
}