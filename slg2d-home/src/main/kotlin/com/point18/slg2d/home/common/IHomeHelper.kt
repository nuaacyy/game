package com.point18.slg2d.home.common

import xyz.ariane.util.DataContainer

abstract class HomeHelper(private val depHhs: List<HomeHelper> = listOf(), val dataContainers: MutableSet<Class<out DataContainer>> = mutableSetOf()) {

    protected var initialized = false // 是否初始化过

    fun initHelper(): Set<Class<out DataContainer>> {
        initialized = true

        // 子模块初始化
        for (depHh in depHhs) {
            dataContainers += depHh.initHelper()
        }

        return dataContainers
    }

    fun addContainer(container: HomeHelper) {
        dataContainers += container.dataContainers
    }

    fun addDc(dc: Class<out DataContainer>) {
        dataContainers += dc
    }
}

