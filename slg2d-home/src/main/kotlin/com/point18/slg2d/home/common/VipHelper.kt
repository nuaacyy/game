package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.NewEquipDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.msgnotice.createPropsChangeNotifier
import xyz.ariane.util.lzWarn
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Arrays.asList

//获取vip刷新时间
fun getVipRefreshTime(assignTime: Long): Long {
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

    val refreshTime1 = ZonedDateTime.of(
        localYear, localMouth.value, localDay
        , refreshHour, refreshMinute, 0, 0, ZoneId.systemDefault()
    ).minusHours(24)

    val refreshTime2 = ZonedDateTime.of(
        localYear, localMouth.value, localDay
        , refreshHour, refreshMinute, 0, 0, ZoneId.systemDefault()
    )

    if (localDate.isBefore(refreshTime2)) {
        return refreshTime1.toInstant().toEpochMilli()
    } else {
        return refreshTime2.toInstant().toEpochMilli()
    }
}

class VipHelper(
    private val propHelper: PropsHelper = PropsHelper()
) : HomeHelperPlus2<VipDC, NewEquipDC>(VipDC::class.java, NewEquipDC::class.java, asList(propHelper)) {

    //重置每日免费行动力
    fun resetDailyEnergy(session: PlayerActor, needNotice: Boolean) {
        return prepare(session) { vipDC: VipDC, newEquipDC: NewEquipDC ->
            val vipInfo = vipDC.vipInfo
            val vipProto = pcs.vipSetCache.vipSetMap[vipInfo.vipLv]
            if (vipProto == null) {
                normalLog.lzWarn { "找不到玩家vip对应的配置:${vipInfo.vipLv}" }
                return@prepare
            }

            vipInfo.lastRefreshEnergyTime = getNowTime()
            for ((protoId, num) in vipProto.energyMap) {
                val allEquipInfo = newEquipDC.findAllPropByProtoId(protoId)
                var currentTotalNum = 0
                allEquipInfo.forEach { currentTotalNum += it.haveNum }
                if (currentTotalNum >= num) {
                    continue
                }
                val lackNum = num - currentTotalNum
                val diffProps =
                    propHelper.getDiffProps(
                        session,
                        com.point18.slg2d.common.constg.ACTION_RESET_FREE_ENERGY,
                        protoId,
                        lackNum
                    )

                if (needNotice) {
                    val propsChangeNotifier = createPropsChangeNotifier()
                    for ((propId, changeNum) in diffProps) {
                        val prop = newEquipDC.findPropById(propId)
                        if (prop == null) {
                            continue
                        }
                        propsChangeNotifier.append(
                            com.point18.slg2d.common.constg.AddProps,
                            prop.id,
                            prop.equipProtoId,
                            changeNum,
                            prop.lv,
                            prop.exp,
                            prop.propertyMap
                        )
                    }
                    propsChangeNotifier.notice(session)
                }
            }
        }
    }
}