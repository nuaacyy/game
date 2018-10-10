package com.point18.slg2d.public.pmodule

fun initModules() {
    registerModule(AllianceM)

    // 执行初始化
    com.point18.slg2d.common.baseg.modules.forEach { it.moduleInit() }
}

fun <T> registerModule(module: T) where T : com.point18.slg2d.common.baseg.IModule {
    com.point18.slg2d.common.baseg.modules.add(module)
}