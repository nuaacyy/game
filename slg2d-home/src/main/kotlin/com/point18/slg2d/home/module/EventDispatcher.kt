package com.point18.slg2d.home.module

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.HomeHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import java.util.*
import java.util.Arrays.asList

/**
 * 事件回调接口
 */
interface IEventHandler<in T : EventData> {
    fun handleEvent(session: PlayerActor, event: T)
}

/**
 * 事件数据类
 */
abstract class EventData(val eventType: EventType)

val eventDealsAtHome: MutableMap<EventType, ArrayList<IEventHandler<EventData>>> = mutableMapOf()

/**
 * 注册事件回调
 */
inline fun <reified T : EventData> registerEvent(eventType: EventType, handler: IEventHandler<T>) {
    val handlers = eventDealsAtHome.getOrPut(eventType) { ArrayList() }

    handlers.add(object : IEventHandler<EventData>, HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        asList(handler as HomeHelper)
    ) {

        override fun handleEvent(session: PlayerActor, event: EventData) {
            if (event !is T) {
                assert(false) { "事件{$eventType}中回调与回调数据类不一致" }
                return
            }

            requireDc(session) {
                handler.handleEvent(session, event)
            }

        }
    })

    // 初始化DC依赖
    val eventHandler = handlers.last()
    if (eventHandler is HomeHelper) {
        eventHandler.initHelper()
    }
}

/**
 * 触发事件
 */
fun <T : EventData> fireEvent(session: PlayerActor, event: T) {
    val handlers = eventDealsAtHome[event.eventType]
    if (handlers == null) {
        return
    }

    handlers.forEach {
        it.handleEvent(session, event)
    }
}