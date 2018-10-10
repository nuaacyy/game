package com.point18.slg2d.common.baseg

import java.util.*

interface IEventHandler<DATA : CacheStore> {
    fun handleEvent(cache: DATA, event: Any, eventType: EventType, playerId: Long)
}

class EventSystem<DATA : CacheStore> {

    private val eventHandlers = hashMapOf<EventType, LinkedList<IEventHandler<DATA>>>()

    // 触发事件
    fun fire(cache: DATA, playerId: Long, eventType: EventType, event: Any) {
        val ehs = this.eventHandlers[eventType] ?: return

        // 处理事件
        for (eventHandle in ehs) {
            eventHandle.handleEvent(cache, event, eventType, playerId)
        }
    }

    fun register(eventTye: EventType, eventHandler: IEventHandler<DATA>) {
        val ehs = this.eventHandlers.getOrPut(eventTye) { LinkedList() }
        ehs.add(eventHandler)
    }
}
