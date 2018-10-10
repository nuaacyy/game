package com.point18.slg2d.world.module.heroBattle.fightLogic

import com.point18.slg2d.common.constg.*

//状态切变化
fun onStateChange(entity: Entity, oldVal: Int, newVal: Int) {
    if (oldVal == MoveState && newVal != MoveState) {
        //移动结束日志
        entity.manager.receiveLogRequest(entity.id, entity.id, LogMoveEnd, null, null)
    }
    if (oldVal != MoveState && newVal == MoveState) {
        //移动开始日志
        val floatParas = hashMapOf<Int, Double>()
        floatParas[PosX] = entity.getFloatProperty(PosX, false)
        floatParas[PosY] = entity.getFloatProperty(PosY, false)
        entity.manager.receiveLogRequest(entity.id, entity.id, LogMove, null, floatParas)
    }
    if (oldVal == FlyState) {
        entity.manager.obstruct.toNearestGrid(entity)
    }
    when (newVal) {
        AttackState -> {
            //攻击前摇日志
            val intParas = hashMapOf<Int, Int>()
            intParas[AtkIndex] = entity.atkIndex
            entity.manager.receiveLogRequest(entity.id, entity.atkTargetId, LogStartAttack, intParas, null)
        }
        FlyState -> {
            entity.manager.obstruct.removeObstruct(entity)
        }
        DieState -> {
            if (entity.entityType == Hero) {
                if (entity.intPropertyMap[FightSide] == ATK_SIDE) {
                    entity.manager.atkHeroNum--
                } else {
                    entity.manager.defHeroNum--
                }
            }
            //死亡日志
            entity.manager.receiveLogRequest(entity.id, entity.id, LogDie, null, null)
            entity.manager.obstruct.removeObstruct(entity)

            for ((_, buffList) in entity.buffMap) {
                for (buff in buffList) {
                    val handlerList = buff.defaultHandleMap[OnDead] ?: continue
                    for (handle in handlerList) {
                        handle(buff)
                    }
                }
            }
        }
        ReviveState -> {
            if (entity.entityType == Hero) {
                if (entity.intPropertyMap[FightSide] == ATK_SIDE) {
                    entity.manager.atkHeroNum++
                } else {
                    entity.manager.defHeroNum++
                }
            }

            entity.manager.receiveLogRequest(entity.id, entity.id, LogRevive, null, null)
            entity.manager.obstruct.removeObstruct(entity)
        }
    }
}

//整数属性变化日志
fun onIntLogPropertyChange(entity: Entity, key: Int) {
    //属性日志
    val changeProperty = entity.manager.cacheChangeProperty.getOrPut(entity.id) { hashMapOf() }
    val intProperty = changeProperty.getOrPut(0) { hashSetOf() }
    intProperty.add(key)

    if (key == Hp || key == ARR_HPLIMIT) {
        for ((_, buffList) in entity.buffMap) {
            for (bf in buffList) {
                val handlerList = bf.defaultHandleMap[OnHpChange] ?: continue
                for (handle in handlerList) {
                    handle(bf)
                }
            }
        }
    }
}

//浮点数属性变化日志
fun onFloatLogPropertyChange(entity: Entity, key: Int) {
    val changeProperty = entity.manager.cacheChangeProperty.getOrPut(entity.id) { hashMapOf() }
    val floatProperty = changeProperty.getOrPut(1) { hashSetOf() }
    floatProperty.add(key)
}

