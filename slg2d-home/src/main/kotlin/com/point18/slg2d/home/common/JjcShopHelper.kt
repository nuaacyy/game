package com.point18.slg2d.home.common

import com.point18.slg2d.home.dc.ItemInfo
import com.point18.slg2d.home.dc.JjcHome
import com.point18.slg2d.common.constg.HAVE_NOT_BOUGHT
import com.point18.slg2d.common.pc.pcs
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

//获取竞技场刷新时间
fun getJjcShopRefreshTime(assignTime: Long): Long {
    val refreshHour = pcs.basicProtoCache.getVipRewardRefreshHour
    val refreshMinute = pcs.basicProtoCache.getVipRewardRefreshMinute

    val instance = Instant.ofEpochMilli(assignTime)
    val localDate = ZonedDateTime.ofInstant(instance, ZoneId.systemDefault())

    val localYear = localDate.year
    val localMouth = localDate.month
    val localDay = localDate.dayOfMonth
    val localHour = localDate.hour
    val localMinute = localDate.minute
    val localSec = localDate.second

    val refreshTime1 = ZonedDateTime.of(localYear, localMouth.value, localDay
        , refreshHour, refreshMinute, 0, 0, ZoneId.systemDefault()).minusHours(24)

    val refreshTime2 = ZonedDateTime.of(localYear, localMouth.value, localDay
        , refreshHour, refreshMinute, 0, 0, ZoneId.systemDefault())

    if (localDate.isBefore(refreshTime2)) {
        return refreshTime1.toInstant().toEpochMilli()
    } else {
        return refreshTime2.toInstant().toEpochMilli()
    }

}

fun jjcShopRefresh(shop: JjcHome, playerId: Long, lordLv: Int): JjcHome? {
    val infoList: LinkedList<ItemInfo> = LinkedList()
    var lv = 0
    for (each in pcs.arenaShopProtoCache.itemsList) {
        if (each.lordLv in (lv + 1)..lordLv) {
            lv = each.lordLv
        }
    }

    val gridMapItems = pcs.arenaShopProtoCache.lvGridItemMap[lv] ?: return null
    // 对每个格子都刷新一遍
    for (grid in 1..pcs.basicProtoCache.arenaShopNum) {
        val gridMap = gridMapItems[grid] ?: return null
        val drawRes = pcs.arenaShopProtoCache.drawOneItem(gridMap) ?: return null
        infoList += ItemInfo(grid, HAVE_NOT_BOUGHT, drawRes.id)
    }

    shop.itemsInfo = infoList
    return shop
}

