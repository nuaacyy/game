package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.DecreeData
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.createDecreeChangeNotifier

// 计算玩家的政令
data class GenerateDecreeReturn(var decreeLimit: Int, var isOk: Boolean)

fun generateDecree(areaCache: AreaCache, player: Player, isNotice: Boolean = true): (GenerateDecreeReturn) {
    // 如果当前玩家的政令数量已达到政令数量上限，那么保持当前政令数量不变
    val decreeLimit =
        pcs.basicProtoCache.energyLimit + getResearchEffectValue(areaCache, NO_FILTER, player, ActivityValueAdd)

    val nowTime = getNowTime()
    if (player.decree >= decreeLimit) {
        player.decreeTime = nowTime
        syncDecree2Home(areaCache, player, decreeLimit)
        return GenerateDecreeReturn(decreeLimit, true)
    }

    // 政令恢复速率（Num/小时）
    val rate = pcs.basicProtoCache.energyRecovery *
            getResearchEffPlusRate(
                areaCache,
                NO_FILTER,
                player,
                ActivityValueRecoverAdd
            )

    // 上次恢复政令到现在为止经历的时间差（单位秒）
    val sub = (nowTime - player.decreeTime) / 1000.0

    // 玩家拥有的政令与应该恢复的政令的总和，就是玩家当前的政令个数
    val addDecree = (sub / 3600 * rate).toInt()
    if (addDecree == 0) {
        return GenerateDecreeReturn(player.decree, false)
    }

    var decree = player.decree + addDecree

    // 自然增长的政令数量不能超过政令数量上限
    var isFull = false
    if (decree > decreeLimit) {
        decree = decreeLimit
        isFull = true
    }

    player.decree = decree
    player.decreeTime = nowTime

    syncDecree2Home(areaCache, player, decreeLimit)

    if (isNotice) {
        val session = fetchOnlinePlayerSession(areaCache, player.id)
        if (session != null) {
            createDecreeChangeNotifier(player.decreeTime, decreeLimit, player.decree).notice(session)
        }
    }

    return GenerateDecreeReturn(decreeLimit, isFull)
}

fun checkDecree(
    areaCache: AreaCache,
    player: Player,
    resList: List<ResVo>
): (ResultCode) {
    generateDecree(areaCache, player, true)
    var leftDecree = player.decree
    for (res in resList) {
        if (res.resType != RES_DECREE) {
            normalLog.error("所需的资源必须是政令，不能是${res.resType}")
            return ResultCode.PARAMETER_ERROR
        }
        if (leftDecree < res.num) {
            //屯田政令不足
            return ResultCode.LESS_RESOUCE
        }
        leftDecree -= res.num.toInt()
    }
    return ResultCode.SUCCESS
}

fun addDecree(areaCache: AreaCache, player: Player, addDecree: Int, useProp: Boolean = true): ResultCode {
    val rt = generateDecree(areaCache, player, false)

    if (useProp && player.decree >= rt.decreeLimit) {
        //判断是否超过上限
        return ResultCode.PARAMETER_ERROR
    }

    player.decree += addDecree

    syncDecree2Home(areaCache, player, rt.decreeLimit)

    val session = fetchOnlinePlayerSession(areaCache, player.id)
    if (session != null) {
        createDecreeChangeNotifier(player.decreeTime, rt.decreeLimit, player.decree)
            .notice(session)
    }

    return ResultCode.SUCCESS
}

fun costDecree(areaCache: AreaCache, player: Player, resList: List<ResVo>): ResultCode {
    val rt = generateDecree(areaCache, player, true)
    var leftDecree = player.decree
    for (res in resList) {
        if (res.resType != RES_DECREE) {
            normalLog.error("所需的资源必须是政令，不能是${res.resType}")
            return ResultCode.PARAMETER_ERROR
        }
        if (leftDecree < res.num) {
            //屯田政令不足
            return ResultCode.LESS_RESOUCE
        }
        leftDecree -= res.num.toInt()
    }
    player.decree = leftDecree

    syncDecree2Home(areaCache, player, rt.decreeLimit)

    val session = fetchOnlinePlayerSession(areaCache, player.id)
    if (session != null) {
        createDecreeChangeNotifier(player.decreeTime, rt.decreeLimit, player.decree).notice(session)
    }

    return ResultCode.SUCCESS
}

fun syncDecree2Home(areaCache: AreaCache, player: Player, decreeLimit: Int) {
    syncData2Home(
        areaCache,
        player.id,
        DecreeSync,
        toJson(DecreeData(player.decreeTime, decreeLimit, player.decree))
    )
}