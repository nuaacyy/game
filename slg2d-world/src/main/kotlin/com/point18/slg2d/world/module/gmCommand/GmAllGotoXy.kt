package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.walk.walkComm.createMarch
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.constg.WalkWalkDrill
import java.util.*

class GmAllGotoXy : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {

        val areaCache = session.areaCache

        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }

        if (messages.size != 4) {
            return 2
        }
        val x = messages[2].toIntOrNull()
        if (x == null) {
            return 2
        }

        val y = messages[3].toIntOrNull()
        if (y == null) {
            return 2
        }

        val players = areaCache.playerCache.findAllPlayers()
        println("当前总人数:${players.size}")
        for (p in players) {
            if (p.id == session.playerId) {
                continue
            }

            // 太多了 过滤掉一些  ~挑个幸运数字~~~
            val rand = getRandInt(80)
            if (rand != 17) {
                continue
            }

            val castle = areaCache.castleCache.findCastleById(p.focusCastleId)

            val speed = Math.sqrt(((x - castle!!.x) * (x - castle.x) + (y - castle.y) * (y - castle.y)).toDouble()) / 30

            // 添加行军记录
            createMarch(
                areaCache,
                p.id,
                LinkedList(),
                hashMapOf(),
                x,
                y,
                getNowTime() + 30000,
                WalkWalkDrill,
                castle.x,
                castle.y,
                speed,
                "",
                LinkedList()
            )
        }


        return 1
    }
}