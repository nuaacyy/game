package com.point18.slg2d.mgr.jsonHelper

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

val mapper = jacksonObjectMapper()
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    .disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)

fun toJson(t: Any): String {
    return mapper.writeValueAsString(t)
}

inline fun <reified T : Any> toObj(json: String): T {
    return mapper.readValue(json)
}