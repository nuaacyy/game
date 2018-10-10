package com.point18.slg2d.world.module.boss

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.getAllianceInvites
import com.point18.slg2d.world.msgnotice.newHunterInviteNotifier

class JoinAllianceEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val player = cache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", playerId)
            return
        }
        if (player.allianceId == 0L) {
            return
        }
        //召唤魔物攻打记录
        val allCallBoss = cache.callBossCache.findAllCallBossByAtkPlayerId(playerId)
        for (callBoss in allCallBoss) {
            val personalRecord = callBoss.atkRecordsMap[playerId]
            if (personalRecord == null) {
                continue
            }

            //刷新联盟数据
            val allianceRank = cache.callBossCache.findAllianceCallBossRankByXY(callBoss)
            var rankInfo = allianceRank.rank.findByKey(player.allianceId)
            if (rankInfo == null) {
                rankInfo = AllianceCallBossAtkRecord(player.allianceId, hashMapOf())
            }
            rankInfo.memberRecordMap[playerId] = personalRecord
            allianceRank.rank.updateValue(rankInfo)
        }

        //个人魔物攻打记录
        val allCommonBoss = cache.commonBossCache.findPersonalAllAtkBoss(playerId)
        for (commonBoss in allCommonBoss) {
            val personalRecord = commonBoss.atkRecordsMap[playerId]
            if (personalRecord == null) {
                continue
            }
            var allianceRank = cache.commonBossCache.findAllianceCommonBossRankByXY(commonBoss.x, commonBoss.y)
            if (allianceRank == null) {
                allianceRank = AllianceCommonBossDamageRank(commonBoss.x, commonBoss.y, createAllianceCommonBossRank())
                cache.commonBossCache.createAllianceCommonBossRank(allianceRank)
            }
            var rankInfo = allianceRank.rank.findByKey(player.allianceId)
            if (rankInfo == null) {
                rankInfo = AllianceCommonBossAtkRecord(player.allianceId, hashMapOf())
            }
            rankInfo.memberRecordMap[playerId] = personalRecord
            allianceRank.rank.updateValue(rankInfo)
        }

        //活动魔物攻打记录
        val allActivityBoss = cache.activityBossCache.findAllActivityBossByAtkPlayerId(playerId)
        for (activityBoss in allActivityBoss) {
            val personalRecord = activityBoss.atkRecordsMap[playerId] ?: continue

            var allianceRank =
                cache.activityBossCache.findAllianceActivityBossRankByXY(activityBoss.x, activityBoss.y)
            if (allianceRank == null) {
                allianceRank =
                        AllianceActivityBossDamageRank(activityBoss.x, activityBoss.y, createAllianceActivityBossRank())
                cache.activityBossCache.createAllianceActivityBossRank(allianceRank)
            }
            var rankInfo = allianceRank.rank.findByKey(player.allianceId)
            if (rankInfo == null) {
                rankInfo = AllianceActivityBossAtkRecord(player.allianceId, hashMapOf())
            }
            rankInfo.memberRecordMap[playerId] = personalRecord
            allianceRank.rank.updateValue(rankInfo)
        }

        //邀请信息
        val session = fetchOnlinePlayerSession(cache, playerId)
        if (session != null) {
            val inviteBuilders = getAllianceInvites(cache, player)
            inviteBuilders.forEach {
                newHunterInviteNotifier(Add, it).notice(session)
            }
        }
    }
}