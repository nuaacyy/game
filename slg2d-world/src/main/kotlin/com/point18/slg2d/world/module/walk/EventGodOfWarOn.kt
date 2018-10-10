package com.point18.slg2d.world.module.walk

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import java.util.*

class GodOfWarOnEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val walkForceGroups = cache.walkForceGroupCache.findWalkForceGroupsByPlayerId(playerId)

        //发送终止所有战争行为邮件
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            TERMINATE_PVP_WALK_TITLE,
            LinkedList(),
            TERMINATE_PVP_WALK_CONTENT,
            LinkedList()
        )

        for (group in walkForceGroups) {
            if (group.gotoRunType == WalkFightPlayer) {
                val walk = cache.walkCache.findWalkByGroupId(group.id)
                if (walk == null) {
                    normalLog.error(" WalkModule.kt 行军组状态是行军中，但找不到行军线:${group.id}")
                    return
                }
                halfWayGoHome(cache, walk)
                sendMail(cache, group.mainPlayerId, mailInfo)
            }

            // 侦察,判断是否对玩家侦察
            if (group.gotoRunType == WalkScout) {
                val walk = cache.walkCache.findWalkByGroupId(group.id)
                if (walk == null) {
                    normalLog.error(" WalkModule.kt 行军组状态是行军中，但找不到行军线:${group.id}")
                    return
                }
                val castle = cache.callBossCache.findCallBossByXy(walk.marchAimsX, walk.marchAimsY)
                if (castle == null) {
                    // 不是对玩家城侦察
                    continue
                }
                halfWayGoHome(cache, walk)
                sendMail(cache, group.mainPlayerId, mailInfo)
            }
        }

    }
}