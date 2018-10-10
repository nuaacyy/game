package com.point18.slg2d.common.baseg

import java.util.*

/**
 * 心跳事件
 */
data class HeartEvent(val time: Long)

data class LongTimeHeartEvent(val time: Long)

interface IHeartHandler<DATA : CacheStore> {
    fun handleHeart(cache: DATA)
}

class HeartSystem<DATA : CacheStore> {
    var queues: LinkedList<IHeartHandler<DATA>> = LinkedList()

    fun registerHeartHandler(heartHandler: IHeartHandler<DATA>) {
        queues.add(heartHandler)
    }
}

