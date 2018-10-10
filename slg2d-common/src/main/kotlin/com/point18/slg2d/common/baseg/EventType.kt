package com.point18.slg2d.common.baseg

typealias EventType = Int

// 系统事件
const val TIMETASK: EventType = 1 // 定时任务触发

data class TimeEvent(
    var TimeTaskType: Int
)
