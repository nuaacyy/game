package com.point18.slg2d.common.netmsg

// 枚举转换成map，提高查找速度
abstract class EnumConverter<in T, R : Enum<R>>(
    private val valueMap: Map<T, R>
) {
    fun fromValue(value: T): R? = valueMap[value]
    fun fromValue(value: T, default: R): R = valueMap[value] ?: default
}

inline fun <T, reified R : Enum<R>> buildValueMap(keySelector: (R) -> T): Map<T, R> =
    enumValues<R>().associateBy(keySelector)