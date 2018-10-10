package com.point18.slg2d.world.module.walk

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.event.InOrOffAlliance
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import java.util.*
import java.util.Arrays.asList

// 离开公会事件的处理
class FinalQuitAllianceEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val eventData = event as InOrOffAlliance

        val player = cache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error(" WalkModule.kt WalkModule.kt 找不到玩家信息:$playerId")
            return
        }
        val myCastle = cache.castleCache.findCastleById(player.focusCastleId)
        if (myCastle == null) {
            normalLog.error(" WalkModule.kt 找不到玩家城信息:${player.focusCastleId}")
            return
        }

        //处理集结
        val allAllianceMass = cache.massCache.findMassByAllianceId(eventData.allianceId)
        allAllianceMass.forEach {
            if (playerId == it.mainPlayerId) {
                //发起集结者离开联盟的情况，直接解散
                doCancelMass(cache, playerId, it.id)
                return@forEach
            }

            //遣返集结者
            sendMassMemberHome(cache, it.id, it.mainPlayerId, asList(playerId))
        }

        //处理自身部队
        val selfGroups = cache.walkForceGroupCache.findWalkForceGroupsByPlayerId(playerId)
        selfGroups.forEach {
            //行军中部队判定能否继续行军
            val walk = cache.walkCache.findWalkByGroupId(it.id)
            if (walk != null) {
                val targetCell = cache.mapCellCache.getCellType(walk.marchAimsX, walk.marchAimsY)
                val dealMap = walkM.cellDeals[walk.marchState]
                if (dealMap == null) {
                    halfWayGoHome(cache, walk)
                    return@forEach
                }
                val deal = dealMap[targetCell]
                if (deal == null || deal.walkFinishCheck(cache, it, walk) != ResultCode.SUCCESS.code) {
                    halfWayGoHome(cache, walk)
                }
                return@forEach
            }

            //行军组回城
            goHome(cache, it.nowX, it.nowY, it)

            //发送联盟关系变化邮件
            val mailInfo = MailInfo(
                TEXT_READ_LAN,
                SYSTEM_MAIL,
                LinkedList(),
                ALLIANCE_RELATION_CHANGE_CONTENT,
                LinkedList(asList(WALK_PARAS + it.gotoRunType.toString()))
            )
            val forces = cache.walkForceCache.findWalkForceByWalkForceGroupId(it.id)
            for (force in forces) {
                sendMail(cache, force.playerId, mailInfo)
            }
        }

        //处理在城上的其他人部队
        val otherGroups = cache.walkForceGroupCache.findWalkForceGroupsByPosAndState(
            myCastle.x,
            myCastle.y,
            Reinforce or Stationed or MassWaiting
        )
        otherGroups.forEach {
            //全部遣返
            goHome(cache, it.nowX, it.nowY, it)

            //发送联盟关系变化邮件
            val mailInfo = MailInfo(
                TEXT_READ_LAN,
                SYSTEM_MAIL,
                LinkedList(),
                ALLIANCE_RELATION_CHANGE_CONTENT,
                LinkedList(asList(WALK_PARAS + it.gotoRunType.toString()))
            )
            val forces = cache.walkForceCache.findWalkForceByWalkForceGroupId(it.id)
            for (force in forces) {
                sendMail(cache, force.playerId, mailInfo)
            }
        }

        //到来的行军中部队判定能否继续行军
        val gotoWalks = cache.walkCache.findWalksByGotoXy(myCastle.x, myCastle.y)
        for (walk in gotoWalks) {
            val dealMap = walkM.cellDeals[walk.marchState]
            if (dealMap == null) {
                halfWayGoHome(cache, walk)
                continue
            }
            val group = cache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
            if (group == null) {
                continue
            }
            val deal = dealMap[CELL_CASTLE]
            if (deal == null || deal.walkFinishCheck(cache, group, walk) != ResultCode.SUCCESS.code) {
                halfWayGoHome(cache, walk)
            }
        }
    }
}