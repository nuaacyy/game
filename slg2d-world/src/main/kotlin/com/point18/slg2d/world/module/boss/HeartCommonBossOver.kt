package com.point18.slg2d.world.module.boss

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.CommonBoss
import com.point18.slg2d.world.common.*

fun onCommonBossOver(areaCache: AreaCache, commonBoss: CommonBoss) {
    onCommonBossRemove(areaCache, commonBoss)

    //刷新魔物
    refreshMonsterInfo(areaCache, commonBoss.bossId, commonBoss.x, commonBoss.y)

    //刷新地块
    noticeCellUpdate(areaCache, commonBoss.x, commonBoss.y)
}