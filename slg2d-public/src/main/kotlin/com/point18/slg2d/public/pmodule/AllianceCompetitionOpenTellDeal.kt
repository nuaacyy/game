package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.public.common.allianceCompetitionInfoChange
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.PublicManager2PublicTell
import java.util.*

// 该联盟获得联盟总动员门票
class AllianceCompetitionOpenTellDeal : PublicManager2PublicTellDealBase() {
    override fun dealPublicTell(publicCache: PublicCache, msg: PublicManager2PublicTell) {
        val alce = publicCache.allianceCache.findAllianceById(msg.publicId)
        if (alce == null) {
            return
        }

        val allianceMembers =
            publicCache.allianceMemberCache.findAllianceMembersByAllianceId(alce.id)


        if (msg.allianceCompetitionOpenTell.state == ALLIANCE_COMPETITION_CHANGE_OPEN) {
            alce.allianceCompetitionScore = 0
            alce.allianceCompetitionTicket = com.point18.slg2d.common.constg.HAVE_ALLIANCE_COMPETITION_TICKET

            val rankProto =
                com.point18.slg2d.common.pc.pcs.allianceCompetitionRankingProtoCache.protoMapById[alce.allianceRankLv]
            if (rankProto == null) {
                return
            }
            // 遍历有门票的联盟,把任务刷出来,该重置的数据重置掉
            val luckQuests =
                com.point18.slg2d.common.pc.pcs.allianceCompetitionQuestProtoCache.refreshAllianceCompetition(
                    com.point18.slg2d.common.pc.pcs.basicProtoCache.numberOfInitialTasks,
                    mutableMapOf()
                )
            var index = 1
            for ((_, quest) in luckQuests) {
                // 随机任务列表
                publicCache.allianceCompetitionQuestCache.createAllianceCompetitionQuest(
                    index,
                    quest.id,
                    alce.id
                )
                index += 1
            }

            // 遍历有门票的联盟,给符合要求的玩家发门票,该重置的数据重置掉,并且把奖励直接随好
            val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

            for (am in allianceMembers) {
                if (am.playerCastleLv >= com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceCompetitionOnesConditions) {
                    // 获得参赛门票
                    am.allianceCompetitionTicket = com.point18.slg2d.common.constg.HAVE_ALLIANCE_COMPETITION_TICKET

                    val ps = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
                    ps.add(am.id)
                }
            }

            for ((pltAreaId, playerIds) in pltAreas) {
                // kafuka到游戏服同步游戏服数据,奖励也到游戏服在去随好了
                allianceCompetitionInfoChange(
                    publicCache.publicActor,
                    pltAreaId, playerIds, 1, alce.allianceRankLv, alce.id,
                    HAVE_ALLIANCE_COMPETITION_TICKET, 0, 0,
                    0, rankProto.questNub, 0
                )
            }
        } else if (msg.allianceCompetitionOpenTell.state == ALLIANCE_COMPETITION_CHANGE_OVER) {
            for (am in allianceMembers) {
                com.point18.slg2d.public.common.allianceCompetitionOver(
                    publicCache.publicActor,
                    am.id,
                    msg.allianceCompetitionOpenTell.rankLv,
                    msg.allianceCompetitionOpenTell.indexLv
                )
            }
        } else if (msg.allianceCompetitionOpenTell.state == ALLIANCE_COMPETITION_CHANGE_CLOSE) {
            alce.allianceCompetitionScore = 0
            alce.allianceCompetitionScoreChangeTime = 0
            alce.allianceCompetitionTicket = com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET

            // 删除任务数据
            val allianceQuests = publicCache.allianceCompetitionQuestCache.findAllianceCompetitionQuestsByAllianceId(
                alce.id
            )
            for (q in allianceQuests) {
                publicCache.allianceCompetitionQuestCache.deleteAllianceCompetitionQuest(q)
            }

            val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

            for (am in allianceMembers) {
                val ps = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
                ps.add(am.id)
            }

            for ((pltAreaId, playerIds) in pltAreas) {
                allianceCompetitionInfoChange(
                    publicCache.publicActor,
                    pltAreaId, playerIds, 0, alce.allianceRankLv, alce.id,
                    NO_HAVE_ALLIANCE_COMPETITION_TICKET, 0, 0,
                    0, 0, 0
                )
            }

            val allianceMemberTraces =
                publicCache.allianceMemberTraceCache.findAllianceMemberTracesByAllianceId(alce.id)
            for (allianceMemberTrace in allianceMemberTraces) {
                allianceMemberTrace.allianceCompetitionScore = 0
                allianceMemberTrace.allianceCompetitionScoreChangeTime = getNowTime()
                allianceMemberTrace.allianceCompetitionQuestGetNum = 0
                allianceMemberTrace.allianceCompetitionQuestSuccessNum = 0
            }
        }


    }
}