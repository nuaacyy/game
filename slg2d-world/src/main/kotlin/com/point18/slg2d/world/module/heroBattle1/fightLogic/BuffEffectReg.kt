package com.point18.slg2d.world.module.heroBattle1.fightLogic

import java.util.*

//注册默认buff回调
inline fun <reified T : Buff> registerDefaultBuffHandle(
    buff: T,
    handlePoint: Int,
    crossinline targetHandle: (buff: T) -> Unit
) {
    val handleList = buff.defaultHandleMap.getOrPut(handlePoint) { LinkedList() }
    handleList.add {
        if (it is T) {
            targetHandle(it)
        }
    }
}

//注册检测buff回调
inline fun <reified T : Buff> registerCheckBuffHandle(
    buff: T,
    handlePoint: Int,
    crossinline targetHandle: (buff: T) -> Boolean
) {
    val handleList = buff.checkHandleMap.getOrPut(handlePoint) { LinkedList() }
    handleList.add {
        if (it is T) {
            return@add targetHandle(it)
        }
        return@add true
    }
}

//注册反弹buff回调
inline fun <reified T : Buff> registerFantanBuffHandle(
    buff: T,
    handlePoint: Int,
    crossinline targetHandle: (atkEntity: Entity, buff: T, hurt: Int) -> Unit
) {
    val handleList = buff.afterHurtHandleMap.getOrPut(handlePoint) { LinkedList() }
    handleList.add { atkEntity, bf, hurt ->
        if (bf is T) {
            targetHandle(atkEntity, bf, hurt)
        }
    }
}

//注册伤害变化buff回调
inline fun <reified T : Buff> registerChangeHurtBuffHandle(
    buff: T,
    handlePoint: Int,
    crossinline targetHandle: (buff: T, atkEntity: Entity, hurt: Int) -> Int
) {
    val handleList = buff.beHurtHandleMap.getOrPut(handlePoint) { LinkedList() }
    handleList.add { bf, atk, hurt ->
        if (bf is T) {
            return@add targetHandle(bf, atk, hurt)
        }
        return@add 0
    }
}

//注册距离检测buff回调
inline fun <reified T : Buff> registerCheckDistanceBuffHandle(
    buff: T,
    handlePoint: Int,
    crossinline targetHandle: (buff: T, range: Double) -> Boolean
) {
    val handleList = buff.checkDistanceHandleMap.getOrPut(handlePoint) { LinkedList() }
    handleList.add { bf, range ->
        if (bf is T) {
            return@add targetHandle(bf, range)
        }
        return@add true
    }
}
