package com.point18.slg2d.home.common

import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.common.pc.pcs

// 计算玩家的政令
fun generateDecree(player: HomePlayer) {
    // 如果当前玩家的政令数量已达到政令数量上限，那么保持当前政令数量不变

    val nowTime = com.point18.slg2d.common.commonfunc.getNowTime()
    if (player.decree >= player.decreeLimit) {
        player.decreeTime = nowTime
        return
    }

    // 政令恢复速率（Num/小时）
    val rate = pcs.basicProtoCache.energyRecovery

    // 上次恢复政令到现在为止经历的时间差（单位秒）
    val sub = (nowTime - player.decreeTime) / 1000.0

    // 玩家拥有的政令与应该恢复的政令的总和，就是玩家当前的政令个数
    val addDecree = (sub / 3600 * rate).toInt()
    if (addDecree == 0) {
        return
    }

    var decree = player.decree + addDecree

    // 自然增长的政令数量不能超过政令数量上限
    if (decree > player.decreeLimit) {
        decree = player.decreeLimit
    }

    player.decree = decree
    player.decreeTime = nowTime
}