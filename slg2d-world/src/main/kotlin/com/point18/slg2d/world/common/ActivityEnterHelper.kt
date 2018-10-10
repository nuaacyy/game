package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getDaysOfTwo
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FOUR_DRAGON_ACTIVITY
import com.point18.slg2d.common.constg.FOUR_DRAGON_GATE
import com.point18.slg2d.common.constg.WONDER_WAR_ACTIVITY
import com.point18.slg2d.common.constg.WONDER_WAR_GATE
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.ActivityBossArea
import com.point18.slg2d.world.area4data.AreaCache
import pb4client.ActivityEnterTimeInfo
import xyz.ariane.util.lzWarn
import java.util.*

//通知奇观活动入口变化
fun createWonderEnterTime(
    areaCache: AreaCache,
    activityType: Int
): ActivityEnterTimeInfo.Builder? {
    val bigWonder = areaCache.wonderCache.findBigWonder()
    if (bigWonder == null) {
        normalLog.error("找不到大奇观")
        return null
    }

    var startTime = 0
    var endTime = 0
    when (activityType) {
        WONDER_WAR_GATE -> {
            val protoCache = pcs.wonderLocationProtoCache
            val startProtectTime = protoCache.startWonderProtectTime() // 战胜buff持续时间，配置是小时，需要转成毫秒
            val finishProtectTime = protoCache.finishWonderProtectTime()
            val cycle = 7 * 24 * 3600 * 1000

            val nowTime = getNowTime()
            if (nowTime > bigWonder.warFinishTime - cycle && nowTime < bigWonder.warFinishTime - cycle + finishProtectTime) {
                startTime = ((bigWonder.warStartTime - cycle - startProtectTime) / 1000).toInt()
                endTime = ((bigWonder.warFinishTime - cycle + finishProtectTime) / 1000).toInt()
            } else {
                startTime = ((bigWonder.warStartTime - startProtectTime) / 1000).toInt()
                endTime = ((bigWonder.warFinishTime + finishProtectTime) / 1000).toInt()
            }
        }
        WONDER_WAR_ACTIVITY -> {
            startTime = (bigWonder.warStartTime / 1000).toInt()
            endTime = (bigWonder.warFinishTime / 1000).toInt()
        }
    }

    val timeInfoBuilder = ActivityEnterTimeInfo.newBuilder()
    timeInfoBuilder.activityType = activityType
    timeInfoBuilder.startTime = startTime
    timeInfoBuilder.endTime = endTime

    return timeInfoBuilder
}

// 通知四天龙活动入口变化
fun createActivityBossEnterTime(
    areaCache: AreaCache,
    activityType: Int
): ActivityEnterTimeInfo.Builder? {
    var selectArea: ActivityBossArea? = null
    areaCache.activityBossAreaCache.activityBossAreaMap.map {
        selectArea = it
        false
    }
    if (selectArea == null) {
        normalLog.error("找不到活动boss底座")
        return null
    }

    val area = selectArea as ActivityBossArea
    var startTime = 0
    var endTime = 0
    var additional: List<Int>? = null
    when (activityType) {
        FOUR_DRAGON_GATE -> {
            startTime = (area.startTime / 1000).toInt() - pcs.basicProtoCache.monsterActivityAdvance
            endTime = (area.finishTime / 1000).toInt()
            additional = getActivityBossInitial(areaCache)
        }
        FOUR_DRAGON_ACTIVITY -> {
            startTime = (area.startTime / 1000).toInt()
            endTime = (area.finishTime / 1000).toInt()
        }
    }

    val timeInfoBuilder = ActivityEnterTimeInfo.newBuilder()
    timeInfoBuilder.activityType = activityType
    timeInfoBuilder.startTime = startTime
    timeInfoBuilder.endTime = endTime
    if (additional != null) {
        timeInfoBuilder.addAllMonsterActivityId(additional)
    }

    return timeInfoBuilder
}

// 获取初始化boss的配置id
fun getActivityBossInitial(areaCache: AreaCache): List<Int> {
    val initTime = areaCache.activityBossAreaCache.getInitAreaTime() // 初始化时间
    val diffDay = getDaysOfTwo(Date(initTime), Date()) // 现在距离开服多少天
    val initBossIds = mutableListOf<Int>()
    areaCache.activityBossAreaCache.activityBossAreaMap.map {
        val bossProtoList = pcs.monsterActivityProtoCache.locationMonsterMap[it.locationId]
        if (bossProtoList == null) {
            normalLog.lzWarn { "没有找到monsterActivity[locationId=${it.locationId}]的模板" }
            return@map true
        }
        var monsterActivityId = 0
        for (bossProto in bossProtoList) {
            if (diffDay >= bossProto.openingTimeInterval[0] && diffDay < bossProto.openingTimeInterval[1]
                || bossProto.openingTimeInterval[1] == 0
            ) {
                monsterActivityId = bossProto.id
                break
            }
        }
        initBossIds.add(monsterActivityId)
        true
    }
    return initBossIds
}