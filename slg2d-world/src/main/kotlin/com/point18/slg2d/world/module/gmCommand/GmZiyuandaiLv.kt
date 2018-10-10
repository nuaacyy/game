package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.constg.MAX_MAP_SIZE
import com.point18.slg2d.common.pc.pcs

class GmZiyuandaiLv : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }

        if (messages.size != 4) {
            return 2
        }
        val xx = messages[2].toIntOrNull()
        if (xx == null) {
            return 2
        }

        val yy = messages[3].toIntOrNull()
        if (yy == null) {
            return 2
        }
        val ziyuandaiLv = getZiyuandaiLvByXY(xx, yy)
        println("坐标属于的资源带等级为:$ziyuandaiLv")
        return 1
    }
}

// 根据一个XY来定位隶属于哪个资源带
fun getZiyuandaiLvByXY(x: Int, y: Int): Int {
    val lv = 0
    for ((ziyuandaiLv, ziyuandaiInfo) in pcs.resZoneProtoCache.resZoneProtoMap) {
        val xInRange =
            (x >= ziyuandaiInfo.coordinateX && x <= ziyuandaiInfo.coordinateX2) || (x >= (MAX_MAP_SIZE - ziyuandaiInfo.coordinateX2 + 1) && x <= (MAX_MAP_SIZE - ziyuandaiInfo.coordinateX + 1))
        val yExt = (y >= ziyuandaiInfo.coordinateY && y <= (MAX_MAP_SIZE - ziyuandaiInfo.coordinateY + 1))
        val boolX = xInRange && yExt

        val yInRange =
            (y >= ziyuandaiInfo.coordinateY && y <= ziyuandaiInfo.coordinateY2) || (y >= (MAX_MAP_SIZE - ziyuandaiInfo.coordinateY2 + 1) && y <= (MAX_MAP_SIZE - ziyuandaiInfo.coordinateY + 1))
        val xExt = (x >= ziyuandaiInfo.coordinateX && x <= (MAX_MAP_SIZE - ziyuandaiInfo.coordinateX + 1))
        val boolY = yInRange && xExt

        val inRange = boolX || boolY

        if (inRange) {
            return ziyuandaiLv
        }
    }
    assert(false) { "有一个坐标点找不到所属的资源带:($x,$y)" }

    return lv
}
