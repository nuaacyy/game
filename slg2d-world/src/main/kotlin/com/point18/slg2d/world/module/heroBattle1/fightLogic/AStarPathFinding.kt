package com.point18.slg2d.world.module.heroBattle1.fightLogic

import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FlyState
import com.point18.slg2d.common.constg.TouchVolume
import java.util.*

/**
 * 路径节点
 */
data class MapNode(
    var x: Int,
    var y: Int,
    var g: Double = 0.0,
    var h: Double = 0.0,
    var parent: MapNode? = null
)

/**
 *  A*寻路
 */
fun aStarPathFinding(start: Entity, target: Entity, obstruct: Obstruct): List<MapNode> {
    val touchVolume = start.getFloatProperty(TouchVolume, false).toInt()

    //找到所有的阻挡地块
    val blockMap = hashMapOf<Int, HashMap<Int, MapNode>>()
    for ((_, yMap) in obstruct.obstructMap) {
        for ((_, entity) in yMap) {
            if (entity.id == start.id || entity.id == target.id) {
                continue
            }
            if (entity.currentState == FlyState) {
                //飞行状态过滤
                continue
            }
            val volume = entity.getFloatProperty(TouchVolume, false).toInt()
            val newVolume = volume + touchVolume - 1
            for (x in (entity.gridX - newVolume + 1)..(entity.gridX + newVolume - 1)) {
                for (y in (entity.gridY - newVolume + 1)..(entity.gridY + newVolume - 1)) {
                    blockMap.getOrPut(x) { hashMapOf() }[y] = MapNode(x, y)
                }
            }
        }
    }

    //构建open队列
    val openList: Queue<MapNode> = PriorityQueue { c1, c2 ->
        when {
            c1.g + c1.h > c2.g + c2.h -> 1
            c1.g + c1.h == c2.g + c2.h -> 0
            else -> -1
        }
    }
    openList.add(
        MapNode(
            start.gridX,
            start.gridY,
            0.0,
            getH(start.gridX, start.gridY, target.gridX, target.gridY)
        )
    )
    val closeMap = hashMapOf<Int, HashMap<Int, MapNode>>()
    var index = 0
    while (closeMap[target.gridX]?.get(target.gridY) == null && openList.isNotEmpty()) {
        val curNode = openList.poll()
        closeMap.getOrPut(curNode.x) { hashMapOf() }[curNode.y] = curNode
        val nearNodes = getValidNearNode(curNode, target.gridX, target.gridY, closeMap, blockMap)
        for (node in nearNodes) {
            val openedNode = openList.firstOrNull { it.x == node.x && it.y == node.y }
            if (openedNode == null) {
                openList.add(node)
                continue
            }
            if (node.g < openedNode.g) {
                openList.remove(openedNode)
                openList.add(node)
            }
        }
        if (index++ > 10000) {
            normalLog.lzWarn { "A*寻路出错" }
            break
        }
    }
    normalLog.lzWarn { "${start.name}A*循环${index}次" }
    if (index > 100) {
        println("打印格子==================================")
        target.manager.entityList.forEach {
            println("${it.name}(${it.gridX},${it.gridY})")
        }
        println("==================================")
    }
    val pathNodeList = LinkedList<MapNode>()
    val closeTargetNode = closeMap[target.gridX]?.get(target.gridY)
    var node = closeTargetNode
    while (node != null) {
        if (node.x == start.gridX && node.y == start.gridY) {
            break
        }
        pathNodeList.add(0, node)
        node = node.parent
    }

    return pathNodeList
}

/**
 * 获取附近有效的邻节点
 */
fun getValidNearNode(
    node: MapNode,
    tx: Int,
    ty: Int,
    closeMap: HashMap<Int, HashMap<Int, MapNode>>,
    blockMap: HashMap<Int, HashMap<Int, MapNode>>
): List<MapNode> {
    val validNodes = LinkedList<MapNode>()
    for (x in (node.x - 1)..(node.x + 1)) {
        for (y in (node.y - 1)..(node.y + 1)) {
            if (!inBorder(x, y)) {
                continue
            }
            if (x == node.x && y == node.y) {
                continue
            }
            if (closeMap[x]?.get(y) != null) {
                continue
            }
            if (blockMap[x]?.get(y) != null) {
                continue
            }
            validNodes.add(
                MapNode(
                    x,
                    y,
                    node.g + calDistance(node.x, node.y, x, y),
                    getH(x, y, tx, ty),
                    node
                )
            )
        }
    }
    return validNodes
}

fun getH(fx: Int, fy: Int, tx: Int, ty: Int): Double {
    return (Math.abs(fx - tx) + Math.abs(fy - ty)).toDouble()
}