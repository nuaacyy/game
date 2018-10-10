package com.point18.slg2d.world.module.heroBattle.fightLogic.conditionNode

import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle.fightLogic.*
import com.point18.slg2d.common.constg.*

class MoveConditionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        //检查buff状态
        for ((_, buffList) in data.buffMap) {
            for (buff in buffList) {
                val handlerList = buff.checkHandleMap[OnStartMove] ?: continue

                for (handle in handlerList) {
                    if (!handle(buff)) {
                        return ActionResult.Failure
                    }
                }
            }
        }

        val gridX = data.gridX
        val gridY = data.gridY

        //todo 以格子距离来判断最近目标
        //敌对阵营最近的，引力
        //所有在其他对象碰撞范围内的，斥力
        var minDisEntity: Entity? = null
        var minDistance = Double.MAX_VALUE
        val camp = checkCamp(data, EnemyForce)
        for (e in data.manager.entityList) {
            //过滤自己
            if (e.id == data.id) {
                continue
            }

            //过滤死亡目标
            if (e.getIntProperty(Hp, false) <= 0) {
                continue
            }

            //过滤阵营
            if (!campFilter(data, e, camp)) {
                continue
            }

            //寻找最近物体
            val distance = calXDistance(data, e) - e.getFloatProperty(TouchVolume, false)
            if (minDistance > distance) {
                minDistance = distance
                minDisEntity = e
            }
        }

        if (minDisEntity == null) {
            return ActionResult.Failure
        }

        val newGridX: Int

        val minGridX = minDisEntity.getFloatProperty(PosX, false)
        newGridX = if (Math.abs(gridX + 1 - minGridX) < Math.abs(gridX - 1 - minGridX)) {
            gridX + 1
        } else {
            gridX - 1
        }

        data.setGrid(newGridX, gridY)

        //N 个Tick后再次触发对象行为
        val speed = data.getFloatProperty(ARR_SUDU)
        val tickNum = (1 * speed).toInt()

        data.changeState(MoveState)
        data.actionEndTime = data.manager.currentTime + UpdateTime * tickNum
        return ActionResult.Success
    }
}

class DistanceInfo(
    var distance: Double,
    var offsetX: Int,
    var offsetY: Int
)