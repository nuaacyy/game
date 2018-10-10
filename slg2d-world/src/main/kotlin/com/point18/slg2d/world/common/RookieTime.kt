package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.area4data.Player

// 是否在新手期
fun isRookie(player: Player): Boolean {
    return player.rookieEndTime > getNowTime()
}
