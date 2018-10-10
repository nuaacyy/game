package com.point18.slg2d.world.module.heroBattle1.fightLogic

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs

class Obstruct {
    val obstructMap = hashMapOf<Int, HashMap<Int, Entity>>()

    //更新阻挡
    fun updateObstruct(entity: Entity, newGridX: Int, newGridY: Int) {
        removeObstruct(entity)
        entity.setGrid(newGridX, newGridY)
        val yMap = obstructMap.getOrPut(newGridX) { hashMapOf() }
        yMap[newGridY] = entity
    }

    //移除阻挡
    fun removeObstruct(entity: Entity) {
        val yMap = obstructMap[entity.gridX] ?: return
        yMap.remove(entity.gridY)
        if (yMap.isEmpty()) {
            obstructMap.remove(entity.gridX)
        }
    }

    //校验阻挡
    fun checkObstruct(gridX: Int, gridY: Int, touchVolume: Int = 1, exceptEntityId: Int = 0): Boolean {
        for ((x, yMap) in obstructMap) {
            for ((y, entity) in yMap) {
                if (entity.id == exceptEntityId) {
                    continue
                }
                if (entity.currentState == FlyState) {
                    //飞行状态过滤
                    continue
                }
                val volume = entity.getFloatProperty(TouchVolume, false).toInt()
                val newVolume = volume + touchVolume - 1
                if (gridX !in (x - newVolume + 1)..(x + newVolume - 1)) {
                    continue
                }
                if (gridY !in (y - newVolume + 1)..(y + newVolume - 1)) {
                    continue
                }
                return true
            }
        }
        return false
    }

    //判断是否在阻挡内
    //寻找可用位置站定
    fun toNearestGrid(entity: Entity) {
        var index = 0
        val maxIndex = Math.max(pcs.basicProtoCache.mapGridX, pcs.basicProtoCache.mapGridY)
        val gridX = entity.gridX
        val gridY = entity.gridY
        while (true) {
            if (index > maxIndex) {
                break
            }
            for (x in (gridX - index)..(gridX + index)) {
                for (y in (gridY - index)..(gridY + index)) {
                    if (!inBorder(x, y)) {
                        continue
                    }
                    val inObstruct = entity.manager.obstruct.checkObstruct(
                        x,
                        y,
                        entity.getFloatProperty(TouchVolume, false).toInt(),
                        entity.id
                    )
                    if (inObstruct) {
                        continue
                    }
                    updateObstruct(entity, x, y)
                    val speed = entity.getFloatProperty(ARR_SUDU)
                    val tickNum = (calDistance(gridX, gridY, x, y) * speed).toInt()
                    if (tickNum > 0) {
                        entity.changeState(MoveState)
                        entity.actionEndTime = entity.manager.currentTime + UpdateTime * tickNum
                        return
                    }
                    entity.changeState(NullState)
                    return
                }
            }
            index++
        }
    }
}