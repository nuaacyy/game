package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.area4data.PlayerSession

class GmClearNewPlayerTime : GmCommand {

    override fun exec(session: PlayerSession, message: String): Int {

        // 格式1： -gm add 类型 数量
        // 格式2： -gm changeCD building
        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }

        val player = session.player

        val nowTime = getNowTime()
        player.rookieEndTime = nowTime

        return 1
    }

}

