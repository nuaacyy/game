package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.public.common.allianceActivityScoreChange
import com.point18.slg2d.public.common.sendMailToPlayer
import com.point18.slg2d.public.datacache.*
import java.util.*
import java.util.Arrays.asList

class ActivityTimeOverHeartHandler : IHeartHandler<PublicCache> {

    override fun handleHeart(cache: PublicCache) {
        activityTimeOver(cache)
    }

    private fun activityTimeOver(publicCache: PublicCache) {
        val timeOverActivity = publicCache.allianceActivityCache.findAllAllianceActivityForTimeOver()

        for (activity in timeOverActivity) {
            dealTimeOverActivity(publicCache, activity)
        }

        dealCheckOpenActivity(publicCache)
    }

    private fun dealCheckOpenActivity(publicCache: PublicCache) {
        // 如果发现此时正好是整点,检测是否有新活动要开启
        val activities = pcs.eventTimeProtoCache.findNowOpenActivities()
        for ((a, overTime) in activities) {
            if (a.eventType != ALLIANCE_ACTIVITY) {
                continue // 不是联盟活动，不处理
            }
            val nowActivity = publicCache.allianceActivityCache.findAllianceActivityByActivityId(a.id)
            if (nowActivity == null) {
                publicCache.allianceActivityCache.createAllianceActivity(a.id, overTime)
            }
        }
    }

}


fun dealTimeOverActivity(publicCache: PublicCache, activity: AllianceActivity) {
    val allJoinActivityAlliances = publicCache.everyAllianceActivityCache.findAllAlliancectivityInfos(
        activity.activityId
    ) // 所有参加了本次活动的联盟
    val allianceActivityProto = pcs.eventAllianceInformationProtoCache.protoMapById[activity.activityId]
    if (allianceActivityProto == null) {
        com.point18.slg2d.common.commonfunc.normalLog.error("联盟活动结算找不到活动模版:${activity.activityId}")
        return
    }
    // 发排行奖励
    if (allJoinActivityAlliances.size > 1) {
        allJoinActivityAlliances.sortByDescending { it.score }
    }

    val rankInfo = mutableMapOf<Int, AllianceActivityRankVo>()

    val now = getNowTime()
    val ranks = mutableMapOf<Long, Int>()
    var nowRank = 1
    for (joinActivityAlliance in allJoinActivityAlliances) {
        if (joinActivityAlliance.score < allianceActivityProto.minScore) {
            continue
        }

        val reward = pcs.eventAllianceInformationProtoCache.findEventRankRewardByRank(activity.activityId, nowRank)
        if (reward == null) {
            continue
        }

        val alce = publicCache.allianceCache.findAllianceById(joinActivityAlliance.allianceId)

        if (alce == null) {
            continue
        }
        // 找到排行的人了
        rankInfo[nowRank] = AllianceActivityRankVo(
            alce.id,
            alce.name,
            alce.shortName,
            joinActivityAlliance.score,
            alce.flagColor,
            alce.flagStyle,
            alce.flagEffect
        )
        ranks[joinActivityAlliance.allianceId] = nowRank

        val allReward = LinkedList<ResVo>()
        for (dropId in reward.rewardBags) {
            val drop = com.point18.slg2d.common.pc.pcs.dropBagCache.dropBagMap[dropId]
            if (drop == null) {
                continue
            }
            allReward.addAll(drop.dropMap)
        }

        val allianceMembers =
            publicCache.allianceMemberCache.findAllianceMembersByAllianceId(alce.id)
        val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

        for (am in allianceMembers) {
            val aams = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
            aams.add(am.id)
        }
        if (allReward.size > 0) {
            for ((pltAreaId, playerIds) in pltAreas) {
                sendMailToPlayer(
                    publicCache.publicActor,
                    (pltAreaId),
                    playerIds,
                    TEXT_READ_LAN,
                    ALLIANCE_ACTIVITY_RANK_TITLE,
                    LinkedList(asList(activity.activityId.toString(), alce.name, alce.shortName)),
                    ALLIANCE_ACTIVITY_RANK_CONTENT,
                    LinkedList(asList(nowRank.toString())),
                    ALLIANCE_ACTIVITY_RANK_REWARD,
                    SYS_MAIL,
                    resVoToResString(allReward),
                    0,
                    ""
                )
            }
        }
        nowRank++
    }

    val rank = publicCache.allianceActivityRankCache.createAllianceActivityRank(
        now + allianceActivityProto.eventSaveTime * 1000,
        rankInfo
    )

    // 清理联盟数据
    for (allJoinActivityAlliance in allJoinActivityAlliances) {
        // 给联盟插入一个参与记录
        val alliance =
            publicCache.allianceCache.findAllianceById(allJoinActivityAlliance.allianceId)
        if (alliance == null) {
            continue
        }

        if (allJoinActivityAlliance.score == 0) {
            continue
        }

        var myRank = ranks[allJoinActivityAlliance.allianceId]
        if (myRank == null) {
            myRank = 0
        }
        alliance.joinActivity.add(
            AllianceJoinActivity(
                activity.activityId,
                getNowTime(),
                allJoinActivityAlliance.score.toLong(),
                myRank,
                rank.id
            )
        )

        val allianceMembers =
            publicCache.allianceMemberCache.findAllianceMembersByAllianceId(alliance.id)
        val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

        for (am in allianceMembers) {
            val aams = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
            aams.add(am.id)
        }

        for ((pltAreaId, playerIds) in pltAreas) {
            allianceActivityScoreChange(
                publicCache.publicActor,
                pltAreaId,
                playerIds,
                allJoinActivityAlliance.activityId,
                allJoinActivityAlliance.score,
                myRank,
                true
            )
        }

        allJoinActivityAlliance.score = 0
        allJoinActivityAlliance.nowTarget = 0
    }

    // 删除活动数据行
    publicCache.allianceActivityCache.deleteAllianceActivity(activity)
}


