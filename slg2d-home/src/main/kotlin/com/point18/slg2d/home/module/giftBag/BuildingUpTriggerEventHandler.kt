package com.point18.slg2d.home.module.giftBag

import com.point18.slg2d.common.commonfunc.ONE_HOUR_MILLS
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.UNLOCK_BY_BUILDING_LV
import com.point18.slg2d.common.constg.UNLOCK_BY_KING_LV
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.GiftBagProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.GiftBagDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.event.BuildingUpFinishEvent
import pb4client.GiftBagInfo
import pb4client.TriggerGiftBag
import java.util.*

class BuildingUpTriggerEventHandler : IEventHandler<BuildingUpFinishEvent>, HomeHelperPlus3<
        HomePlayerDC, InnerCityDC, GiftBagDC>(
    HomePlayerDC::class.java,
    InnerCityDC::class.java,
    GiftBagDC::class.java
) {

    override fun handleEvent(session: PlayerActor, event: BuildingUpFinishEvent) {
        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC, giftBagDC: GiftBagDC ->
            //
            // 下面的代码可能在异步情况下执行！
            //
            val castleId = event.castleId
            val result = triggerGiftBag(homePlayerDC, innerCityDC, castleId)
            if (!result.isEmpty()) {
                result.forEach {
                    // 如果有了这个礼包，就跳过。
                    if (giftBagDC.giftBags.containsKey(it.giftId)) {
                        return@forEach
                    }

                    val endTime = getNowTime() + it.duration * ONE_HOUR_MILLS
                    giftBagDC.createGiftBag(it.giftId, session.playerId, endTime)
                    session.sendMsg(
                        MsgType.TriggerGiftBag_3356,
                        TriggerGiftBag.newBuilder().setGiftBagInfo(
                            GiftBagInfo
                                .newBuilder()
                                .setGiftBagId(it.giftId)
                                .setEndTime(endTime)
                                .setCurLevel(it.giftLevel)
                                .setCurCount(0)
                        ).build()
                    )
                }
            }
        }
    }

    private fun triggerGiftBag(playerDC: HomePlayerDC, innerCityDC: InnerCityDC, castleId: Long): ArrayList<GiftBagProto> {
        val rt = arrayListOf<GiftBagProto>()

        val triggerGiftBags = pcs.giftBagProtoCache.triggerGiftBagMap
        for ((_, list) in triggerGiftBags) {
            val proto = list[0]
            var canTrigger = false
            for ((unlockType, subMap) in proto.giftBagTouchConMap) {
                if (unlockType == UNLOCK_BY_BUILDING_LV) {
                    canTrigger = buildingLvEnough(subMap, innerCityDC, castleId)
                } else if (unlockType == UNLOCK_BY_KING_LV) {
                    canTrigger = kingLvEnough(subMap, playerDC)
                }

                if (!canTrigger) {
                    break
                }
            }

            if (!canTrigger) {
                continue
            }
            // 条件全部达成则可以触发
            rt.add(proto)
        }
        return rt
    }

    // 判断建筑是否满足条件
    private fun buildingLvEnough(subMap: Map<Int, Int>, innerCityDC: InnerCityDC, castleId: Long): Boolean {
        for ((type, level) in subMap) {
            val innerCityInfoList = innerCityDC.findInnerCityListFromCastleIdAndType(castleId, type)

            var canUnlock = false
            for (innerCityInfo in innerCityInfoList) {
                if (innerCityInfo.lv >= level) {
                    canUnlock = true
                    break
                }
            }

            if (!canUnlock) {
                return false
            }
        }
        return true
    }

    // 判断君主等级是否满足条件
    private fun kingLvEnough(subMap: Map<Int, Int>, playerDC: HomePlayerDC): Boolean {
        for ((_, level) in subMap) {
            if (level > playerDC.player.kingLv) {
                return false
            }
        }
        return true
    }
}