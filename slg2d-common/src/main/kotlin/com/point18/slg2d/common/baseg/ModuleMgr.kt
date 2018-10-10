package com.point18.slg2d.common.baseg

import java.util.*

interface IModule {
    fun moduleInit()
}

// 模块列表
var modules = LinkedList<IModule>()