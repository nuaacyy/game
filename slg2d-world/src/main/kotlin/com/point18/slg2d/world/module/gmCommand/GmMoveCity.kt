package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.constg.RandomPointMove
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.ask4deal.moveCity
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.findFreeCastlePos
import com.point18.slg2d.world.common.noticeCellRemove
import com.point18.slg2d.world.common.noticeCellUpdate

//迁城
class GmMoveCity : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache

        val messages = message.split(" ")
        if (messages.size != 5) {
            return 2
        }

        val moveType = (messages[2].toIntOrNull())
        if (moveType != null) {
            return 2
        }

        val x = (messages[3].toIntOrNull())
        if (x == null) {
            return 2
        }

        val y = (messages[4].toIntOrNull())
        if (y == null) {
            return 2
        }

        if (moveType == RandomPointMove) {
            val (x, y) = findFreeCastlePos(areaCache)

            if (x == -1 || y == -1) {
                return 2
            }
        }

        val player = session.player

        val (rst, oldX, oldY, newX, newY) = moveCity(session.areaCache, player, x, y)
        if (rst != ResultCode.SUCCESS) {
            return 2
        }
        noticeCellUpdate(areaCache, oldX, oldY)
        noticeCellUpdate(areaCache, newX, newY)
        return 1
    }
}

