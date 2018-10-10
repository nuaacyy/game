package com.point18.slg2d.home.module.photo

import com.point18.slg2d.common.constg.ICON_TYPE_HERO
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.IconDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.event.GainHeroEvent
import com.point18.slg2d.home.module.event.GetNewPhotoEvent
import com.point18.slg2d.home.module.fireEvent

class GainHeroEventHandler : IEventHandler<GainHeroEvent>, HomeHelperPlus2<HeroDC, IconDC>(
    HeroDC::class.java, IconDC::class.java
) {

    override fun handleEvent(session: PlayerActor, event: GainHeroEvent) {
        prepare(session) { heroDC, iconDC ->
            //
            // 下面的代码可能在异步情况下执行！
            //
            val gainIconByGainHeroProtoList = pcs.lordHeadIconProtoCache.iconTypeMap[ICON_TYPE_HERO]
            if (gainIconByGainHeroProtoList == null || gainIconByGainHeroProtoList.isEmpty()) {
                return@prepare
            }

            // 获取所有的英雄列表
            val heroList = heroDC.findProtoHeroMapMap()
            for (eachProto in gainIconByGainHeroProtoList) {
                if (iconDC.iconsMap[eachProto.id] != null) {
                    continue
                }

                val gainIconCondition = eachProto.activityOrHeroCheckMap[ICON_TYPE_HERO]
                if (eachProto.activityOrHeroCheckMap.size != 1 || gainIconCondition == null || gainIconCondition.size == 0) {
                    // 暂时只做获得某些英雄就能获得头像的功能，以后既要完成活动又要拥有英雄才能获得头像的功能搁置
                    continue
                }

                var willGain = true
                for (eachHeroProto in gainIconCondition) {
                    if (heroList[eachHeroProto] == null) {
                        // 没有前置英雄
                        willGain = false
                        break
                    }
                }

                if (willGain) {
                    iconDC.createIcon(0, eachProto.id)

                    // 触发得到新头像事件
                    fireEvent(session, GetNewPhotoEvent())
                }
            }
        }
    }
}