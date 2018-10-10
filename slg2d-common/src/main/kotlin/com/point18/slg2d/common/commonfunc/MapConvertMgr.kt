package com.point18.slg2d.common.commonfunc

import java.util.*

lateinit var MapMgr: MapConvertMgr

class MapConvertMgr(mapLen: Int, val w: Int = 2, val h: Int = 1) {
    private val rate = Math.sqrt(w * w + h * h.toDouble())
    private val startX = (mapLen / rate * w).toInt()
    private val gridXLen = Math.ceil(10 * (2 * w / rate)).toInt()
    private val gridYLen = Math.ceil(10 * (2 * h / rate)).toInt()

    private val gridMap = hashMapOf<Int, HashMap<Int, LinkedList<Pair<Int, Int>>>>()

    init {
        //初始化大地图格点
        for (i in 0..mapLen) {
            for (j in 0..mapLen) {
                val screenGrid = getScreenGrid(i, j)
                gridMap.getOrPut(screenGrid.first) { hashMapOf() }.getOrPut(screenGrid.second) { LinkedList() }
                    .add(Pair(i, j))
            }
        }
    }

    //屏幕坐标转换成逻辑坐标
    fun screen2LogicPos(sx: Double, sy: Double): Pair<Double, Double> {
        val x = ((sx - startX) * rate / w + sy * rate) / 2
        val y = (sy * rate - (sx - startX) * rate / w) / 2
        return Pair(x, y)
    }

    //逻辑坐标转换成屏幕坐标
    fun logic2ScreenPos(logicX: Int, logicY: Int): Pair<Double, Double> {
        val x = startX + logicX / rate * w - logicY / rate * w
        val y = logicX / rate * h + logicY / rate * h
        return Pair(x, y)
    }

    //根据逻辑坐标获取屏幕格点
    fun getScreenGrid(logicX: Int, logicY: Int): Pair<Int, Int> {
        val screenPos = logic2ScreenPos(logicX, logicY)
        val gridX = (screenPos.first / gridXLen).toInt()
        val gridY = (screenPos.second / gridYLen).toInt()
        return Pair(gridX, gridY)
    }

    //根据屏幕格点获取逻辑坐标列表
    fun getLogicPosListByGrid(screenGridX: Int, screenGridY: Int): List<Pair<Int, Int>>? {
        return gridMap[screenGridX]?.get(screenGridY)
    }
}