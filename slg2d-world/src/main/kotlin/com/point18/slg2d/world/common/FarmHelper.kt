package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.GoldFarmFast
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.ResFarmFast
import com.point18.slg2d.common.constg.RES_GOLD
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.CommonRes
import com.point18.slg2d.world.area4data.DeadBossRes

//计算当前已采集的普通资源数量
fun calFarmedResNum(areaCache: AreaCache, res: CommonRes): Int {
    return calFarmedResNum(areaCache, res.groupId, res.resId, res.nowResNum, res.farmStartTime)
}

//计算当前已采集的尸体资源数量
fun calFarmedResNum(areaCache: AreaCache, res: DeadBossRes): Int {
    return calFarmedResNum(areaCache, res.groupId, res.resId, res.nowResNum, res.farmStartTime)
}

private fun calFarmedResNum(areaCache: AreaCache, forceId: Long, resId: Int, nowResNum: Int, farmStartTime: Long): Int {
    if (forceId == 0L) {
        return 0
    }

    val group = areaCache.walkForceGroupCache.findWalkForceGroupById(forceId)
    if (group == null) {
        normalLog.error("找不到采集的行军组信息:%d", forceId)
        return 0
    }

    val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
    if (player == null) {
        normalLog.error("找不到行军组对应的玩家信息,groupId:%d", group.id)
        return 0
    }

    val resProto = pcs.resPointProtoCache.resPointMap[resId]
    if (resProto == null) {
        normalLog.error("找不到资源点配置:%d", resId)
        return 0
    }

    //资源占用负重
    val resWeight = pcs.basicProtoCache.resWeight[resProto.resType] ?: 0.0
    if (resWeight <= 0.0) {
        normalLog.error("资源负重配置错误:%d", resProto.resType)
        return 0
    }

    //部队负重
    val canFarmWeight = calWeight(areaCache, forceId)

    //可采集的数量
    val canFarmNum = Math.ceil(canFarmWeight / resWeight).toInt()

    //最多可采集数量
    var maxCanFarmNum = canFarmNum
    if (maxCanFarmNum > nowResNum) {
        maxCanFarmNum = nowResNum
    }

    //采集速度
    var farmSpeed = resProto.speed
    if (resProto.resType == RES_GOLD) {
        farmSpeed *= getResearchEffPlusRate(
            areaCache,
            NO_FILTER,
            player,
            GoldFarmFast
        )
    } else {
        farmSpeed *= getResearchEffPlusRate(
            areaCache,
            NO_FILTER,
            player,
            ResFarmFast
        )
    }

    //已采集数量
    var haveFarmNum = (farmSpeed * (getNowTime() - farmStartTime) / 1000).toInt()

    //与最大采集数量做比较
    if (haveFarmNum > maxCanFarmNum) {
        haveFarmNum = maxCanFarmNum
    }
    return haveFarmNum
}


//计算采集结束时间
fun calFarmEndTime(areaCache: AreaCache, res: CommonRes): Long {
    return calFarmEndTime(areaCache, res.groupId, res.resId, res.nowResNum, res.farmStartTime)
}

//计算采集结束时间
fun calFarmEndTime(areaCache: AreaCache, res: DeadBossRes): Long {
    return calFarmEndTime(areaCache, res.groupId, res.resId, res.nowResNum, res.farmStartTime)
}

private fun calFarmEndTime(areaCache: AreaCache, forceId: Long, resId: Int, nowResNum: Int, farmStartTime: Long): Long {
    if (forceId == 0L) {
        return 0
    }

    val group = areaCache.walkForceGroupCache.findWalkForceGroupById(forceId)
    if (group == null) {
        normalLog.error("找不到采集的行军组信息:%d", forceId)
        return 0
    }

    val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
    if (player == null) {
        normalLog.error("找不到行军组对应的玩家信息,groupId:%d", group.id)
        return 0
    }

    val resProto = pcs.resPointProtoCache.resPointMap[resId]
    if (resProto == null) {
        normalLog.error("找不到资源点配置:%d", resId)
        return 0
    }

    //资源占用负重
    val resWeight = pcs.basicProtoCache.resWeight[resProto.resType] ?: 0.0
    if (resWeight <= 0.0) {
        normalLog.error("资源负重配置错误:%d", resProto.resType)
        return 0
    }

    //TODO 部队负重,科技效果加成
    val canFarmWeight = calWeight(areaCache, forceId)

    //可采集的数量
    val canFarmNum = Math.ceil(canFarmWeight / resWeight).toInt()

    //最多可采集数量
    var maxCanFarmNum = canFarmNum
    if (maxCanFarmNum > nowResNum) {
        maxCanFarmNum = nowResNum
    }

    //TODO 采集速度,科技效果加成
    val farmSpeed = resProto.speed

    //需要的采集时间
    val needFarmTime = Math.ceil(maxCanFarmNum / farmSpeed * 1000).toLong()

    return needFarmTime + farmStartTime
}