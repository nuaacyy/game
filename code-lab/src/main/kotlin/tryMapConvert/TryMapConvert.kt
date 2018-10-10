package tryMapConvert

import java.util.*

val mapLen = 512
val rate = 2.236
val startX = (mapLen / rate * 2).toInt()
val gridXLen = Math.ceil(10 * (2 / rate)).toInt()
val gridYLen = Math.ceil(10 * (1 / rate)).toInt()

fun main(args: Array<String>) {
    val t1 = System.currentTimeMillis()
    val mgr = MapConvertMgr()
    val gridMap = hashMapOf<Int, HashMap<Int, LinkedList<Pair<Int, Int>>>>()
    for (i in 0..mapLen) {
        for (j in 0..mapLen) {
            val screenPos = mgr.logic2Screen(i, j)
            val screenX = screenPos.first.toInt()
            val screenY = screenPos.second.toInt()
            gridMap.getOrPut(screenX / gridXLen) { hashMapOf() }.getOrPut(screenY / gridYLen) { LinkedList() }
                .add(Pair(i, j))
        }
    }
    val t2 = System.currentTimeMillis()
    println("耗时:${t2 - t1}毫秒")
    println()
}

class MapConvertMgr {
    fun screen2Logic(sx: Double, sy: Double): Pair<Double, Double> {
        val x = ((sx - startX) * rate / 2 + sy * rate) / 2
        val y = (sy * rate - (sx - startX) * rate / 2) / 2
        return Pair(x, y)
    }

    fun logic2Screen(logicX: Int, logicY: Int): Pair<Double, Double> {
        val x = startX + logicX / rate * 2 - logicY / rate * 2
        val y = logicX / rate + logicY / rate
        return Pair(x, y)
    }
}