package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.ResearchEffectTiliAdd
import com.point18.slg2d.common.constg.ResearchEffectTiliMaxAdd
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.createStrengthChangeNotifier

/**
 * 推图体力帮助类
 */

// 获取玩家当前的体力,是否触发存储由参数决定
fun getPlayerStrength(areaCache: AreaCache, player: Player, isSaveDb: Boolean): Int {
    val nowTime = getNowTime()
    val nowStrengthMax = pcs.basicProtoCache.maxInstancePower + getResearchEffectValue(
        areaCache,
        NO_FILTER,
        player,
        ResearchEffectTiliMaxAdd
    )
    if (player.strength >= nowStrengthMax) {
        player.decreeTime = nowTime
        return player.strength
    }

    // 上次恢复政令到现在为止经历的时间差（单位秒）
    val sub = ((nowTime - player.lastStrengthTime) / 1000).toInt()

    // 每一点体力所需的真实的回复时间
    val everyStrengthNeedSec =
        10000 / (10000 + getResearchEffectValue(areaCache, NO_FILTER, player, ResearchEffectTiliAdd)) *
                pcs.basicProtoCache.instancePowerRecover
    val addStrength = (sub / everyStrengthNeedSec)

    var strength = player.strength + addStrength

    // 自然增长的政令数量不能超过政令数量上限
    if (strength > nowStrengthMax) {
        strength = nowStrengthMax
    }

    if (isSaveDb) {
        if (strength >= nowStrengthMax) {
            // 如果达到了上限,时间就设置成此时
            player.lastStrengthTime = nowTime
        } else {
            val diffValue = strength - player.strength //  真实的增加的体力
            player.lastStrengthTime += (diffValue * everyStrengthNeedSec * 1000)
        }
        player.strength = strength
    }

    return strength
}

fun checkStrength(areaCache: AreaCache, player: Player, cost: Int): (ResultCode) {
    val nowStrength = getPlayerStrength(areaCache, player, false)
    if (nowStrength < cost) {
        return ResultCode.LESS_RESOUCE
    }
    return ResultCode.SUCCESS
}

fun costStrength(areaCache: AreaCache, player: Player, cost: Int): (ResultCode) {
    val nowStrength = getPlayerStrength(areaCache, player, true)
    if (nowStrength < cost) {
        return ResultCode.LESS_RESOUCE
    }

    // 这里变化时间不需要写了,因为上面取的时候参数是true
    player.strength -= cost

    val session = fetchOnlinePlayerSession(areaCache, player.id)
    if (session != null) {
        createStrengthChangeNotifier(player.strength, (player.lastStrengthTime / 1000).toInt()).notice(session)
    }

    return ResultCode.SUCCESS
}

fun addStrength(areaCache: AreaCache, player: Player, add: Int): (ResultCode) {
    getPlayerStrength(areaCache, player, true)

    // 这里变化时间不需要写了,因为上面取的时候参数是true
    player.strength += add

    val session = fetchOnlinePlayerSession(areaCache, player.id)
    if (session != null) {
        createStrengthChangeNotifier(player.strength, (player.lastStrengthTime / 1000).toInt()).notice(session)
    }

    return ResultCode.SUCCESS
}