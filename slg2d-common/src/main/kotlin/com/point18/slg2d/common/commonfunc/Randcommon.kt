package com.point18.slg2d.common.commonfunc

import java.util.*

val ran = Random()
val ranLockObj = LockObj()

fun getRandInt(value: Int): Int {
    if (value <= 0) {
        return 0
    }
    synchronized(ranLockObj) {
        return ran.nextInt(value)
    }
}

fun getRandIntAtArea(seed: Random, value: Int): (Int) {
    if (value <= 0) {
        return 0
    }

    return seed.nextInt(value)
}

// 获取多个不重复的随机数 [0,value) 排除exclude
fun getMoreRandIntsExclude(value: Int, num: Int, exclude: Int): LinkedList<Int> {
    val rs = LinkedList<Int>()
    if (value <= 0) {
        return rs
    }

    if (num > value) {
        return rs
    }

    // 构造随机帮助结构体
    val randHelps = LinkedList<Int>()
    for (i in 0 until value) {
        if (i == exclude) {
            continue
        }
        randHelps.add(i)
    }

    // 开始随机
    for (i in 0 until num) {
        val r = ran.nextInt(randHelps.count())
        rs.add(randHelps[r])
        randHelps.removeAt(r)
    }

    return rs
}