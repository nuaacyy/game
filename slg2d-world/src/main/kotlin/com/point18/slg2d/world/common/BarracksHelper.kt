package com.point18.slg2d.world.common

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.Barracks
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifierByUseProps
import com.point18.slg2d.common.syncdomain.BarrackData

// 扣兵
fun costSolider(
    areaCache: AreaCache,
    playerId: Long,
    soliderId: Int,
    num: Int
): Boolean {
    val barrack = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, soliderId) ?: return false
    if (barrack.soldierNum < num) {
        return false
    }
    barrack.soldierNum -= num

    val session = fetchOnlinePlayerSession(areaCache, playerId)
    if (session != null) {
        createBarracksChangeNotifier(barrack).notice(session)
    }

    return true
}

// 加兵
fun addSolider(areaCache: AreaCache, playerId: Long, soliderId: Int, num: Int) {
    var barrack = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, soliderId)
    if (barrack == null) {
        barrack = areaCache.barracksCache.createBarracks(soliderId, playerId)
    }
    barrack.soldierNum += num

    val session = fetchOnlinePlayerSession(areaCache, playerId)
    if (session != null) {
        createBarracksChangeNotifier(barrack).notice(session)
    }
}

// 通过道具加兵
fun addSoliderByUseProps(areaCache: AreaCache, playerId: Long, soliderId: Int, num: Int) {
    var barrack = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, soliderId)
    if (barrack == null) {
        barrack = areaCache.barracksCache.createBarracks(soliderId, playerId)
    }
    barrack.soldierNum += num

    val session = fetchOnlinePlayerSession(areaCache, playerId)
    if (session != null) {
        createBarracksChangeNotifierByUseProps(barrack).notice(session)
    }
}

//计算估算量
fun getEvaluateNum(num: Int): (Int) {
    val sLen = num.toString().length
    var maxNum = 1

    for (ii in 1..(sLen - 1)) {
        maxNum *= 10
    }

    if (num % maxNum == 0) {
        return num
    } else {
        return num - num % maxNum + maxNum
    }
}

/**
 * 同步兵营数据到home服
 */
fun syncBarrack2Home(areaCache: AreaCache, playerId: Long) {
    val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(playerId)
    val syncBarrackMap = hashMapOf<Int, BarrackData>()
    barrackMap.forEach { _, barrack ->
        syncBarrackMap[barrack.soldierId] = BarrackData(
            barrack.soldierId,
            barrack.soldierNum,
            barrack.nowMakeNum,
            barrack.canCureNum,
            barrack.nowCureNum,
            barrack.canEventCureNum,
            barrack.nowEventCureNum,
            barrack.upNum,
            barrack.overTime,
            barrack.cureOverTime,
            barrack.eventCureOverTime,
            barrack.upOverTime
        )
    }
    syncData2Home(areaCache, playerId, Barracks, toJson(syncBarrackMap))
}
