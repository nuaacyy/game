package com.point18.slg2d.world.module.walk

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.common.constg.CELL_CASTLE
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.createMassGroupBuilder
import com.point18.slg2d.world.common.createReinforceMassGroupBuilder
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import com.point18.slg2d.world.msgnotice.createMassGroupChangeNotifier

// 加入公会的事件处理
class JoinAllianceEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        //发送联盟的集结、被集结信息
        val player = cache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error(" WalkModule.kt 找不到玩家信息:$playerId")
            return
        }

        val session = fetchOnlinePlayerSession(cache, playerId)
        val allMass = cache.massCache.findMassByAllianceId(player.allianceId)
        if (allMass.count() > 0) {
            for (mass in allMass) {
                val massGroup = createMassGroupBuilder(cache, mass)
                if (massGroup == null) {
                    continue
                }

                if (session != null) {
                    createMassGroupChangeNotifier(Add, massGroup).notice(session)
                }
            }
        }

        //联盟内被集结
        val allMembers = cache.playerCache.findPlayersByAllianceId(player.allianceId)
        for (member in allMembers) {
            val castle = cache.castleCache.findCastleById(member.focusCastleId)
            if (castle == null) {
                normalLog.error(" WalkModule.kt 找不到玩家城信息:${member.focusCastleId}")
                continue
            }
            val posAllMass = cache.massCache.findAllMassByPos(castle.x, castle.y)
            for (mass in posAllMass) {
                val reinforceGroup = createReinforceMassGroupBuilder(cache, mass)
                if (reinforceGroup == null) {
                    continue
                }

                if (session != null) {
                    createMassGroupChangeNotifier(Add, reinforceGroup).notice(session)
                }
            }
        }

        val myCastle = cache.castleCache.findCastleById(player.focusCastleId)
        if (myCastle == null) {
            normalLog.error(" WalkModule.kt 找不到玩家城信息:${player.focusCastleId}")
            return
        }

        val selfGroups = cache.walkForceGroupCache.findWalkForceGroupsByPlayerId(playerId)
        for (group in selfGroups) {
            //行军中部队判定能否继续行军
            val walk = cache.walkCache.findWalkByGroupId(group.id)
            if (walk != null) {
                val targetCell = cache.mapCellCache.getCellType(walk.marchAimsX, walk.marchAimsY)
                val dealMap = walkM.cellDeals[walk.marchState]
                if (dealMap == null) {
                    halfWayGoHome(cache, walk)
                    continue
                }
                val deal = dealMap[targetCell]
                if (deal == null || deal.walkFinishCheck(cache, group, walk) != ResultCode.SUCCESS.code) {
                    halfWayGoHome(cache, walk)
                }
                continue
            }

            //判断静止部队
            val castle = cache.castleCache.findCastleByXy(group.nowX, group.nowY)
            if (castle == null) {
                continue
            }
            val targetPlayer = cache.playerCache.findPlayerById(castle.playerId)
            if (targetPlayer == null) {
                normalLog.error(" WalkModule.kt 找不到对方玩家信息:${castle.playerId}")
                continue
            }
            if (player.allianceId != targetPlayer.allianceId) {
                goHome(cache, group.nowX, group.nowY, group)
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