package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GM_REFRESH_EVENT
import com.point18.slg2d.home.module.EventData

// GM模块才会发出的事件 用来修改数据之后进行刷新之类用的,比如任务的刷新
class GmRefTaskEvent : EventData(GM_REFRESH_EVENT)

