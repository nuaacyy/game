package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.event.ActivityGetRankReward
import com.point18.slg2d.world.msgnotice.createPlayerActivityChangeNotifier
import com.point18.slg2d.world.wpm
import java.util.*
import java.util.Arrays.asList

class ActivityTimeOverHeartHandler : IHeartHandler<AreaCache> {
    override fun handleHeart(cache: AreaCache) {
        val timeOverActivity = cache.serverActivityCache.findAllActivityForTimeOver()

        for (activity in timeOverActivity) {
            dealTimeOverActivity(cache, activity)
        }

        dealCheckOpenActivity(cache)
    }

    // 心跳处理开启个人活动
    private fun dealCheckOpenActivity(areaCache: AreaCache) {
        val activities = pcs.eventTimeProtoCache.findNowOpenActivities()
        for ((a, overTime) in activities) {
            if (a.eventType == ALLIANCE_ACTIVITY) {
                continue  // 属于联盟活动，不做处理
            }

            var ot = overTime

            // 奇观活动开始时间不符合地图奇观战时间，不开启
            if (a.eventType == WONDER_ACTIVITY) {
                val bigWonder = areaCache.wonderCache.findBigWonder() ?: continue
                val nowTime = getNowTime()
                if (nowTime < bigWonder.warStartTime || nowTime > bigWonder.warFinishTime) {
                    continue
                }
                ot = bigWonder.warFinishTime
            }

            if (a.eventType == NEW_PLAYER_ACTIVITY) {
                ot = 0
            }

            val nowActivity = areaCache.serverActivityCache.findServerActivityByActivityId(a.id)
            if (nowActivity == null) {
                areaCache.serverActivityCache.createServerActivity(a.id, ot)
            }
        }
    }
}

fun dealTimeOverActivity(areaCache: AreaCache, activity: ServerActivity) {
    val allJoinActivityPlayersByCastleLv =
        areaCache.playerActivityCache.findAllPlayerActivityInfoMap(activity.activityId) // 所有参加了本次活动的玩家
    // 发排行奖励
    for ((eventInformationProtoId, allJoinActivityPlayers) in allJoinActivityPlayersByCastleLv) {
        val eventInformationProto = pcs.eventInformationProtoCache.protoMapById[eventInformationProtoId]
        if (eventInformationProto == null) {
            continue
        }
        if (allJoinActivityPlayers.size > 1) {
            allJoinActivityPlayers.sortByDescending { it.score }
        }

        val rankInfoMap = hashMapOf<Int, ActivityRankVo>()

        val nowTime = getNowTime()

        val ranks = mutableMapOf<Long, Int>()
        var nowRank = 1
        for (joinActivityPlayer in allJoinActivityPlayers) {
            if (joinActivityPlayer.score < eventInformationProto.minScore) {
                // 没有达到要求
                continue
            }
            val reward = pcs.eventInformationProtoCache.findEventRankRewardByRank(
                activity.activityId,
                nowRank,
                joinActivityPlayer.castleLv
            )
            if (reward == null) {
                continue
            }

            val player = areaCache.playerCache.findPlayerById(joinActivityPlayer.playerId)
            if (player == null) {
                continue
            }

            // 找到排行的人了
            rankInfoMap[nowRank] = ActivityRankVo(
                player.id,
                player.name,
                player.allianceNickName,
                player.allianceShortName,
                joinActivityPlayer.score,
                player.photoProtoId
            )
            ranks[player.id] = nowRank
            val allReward = LinkedList<ResVo>()
            for (dropId in reward.rewardBags) {
                val drop = pcs.dropBagCache.dropBagMap[dropId]
                if (drop == null) {
                    continue
                }

                allReward += drop.dropMap
            }
            //发送邮件
            if (allReward.size > 0) {
                val senMailInfo = MailInfo(
                    TEXT_READ_LAN,
                    PLAYER_ACTIVITY_RANK_TITLE,
                    LinkedList(asList(activity.activityId.toString(), player.name, player.allianceShortName)),
                    PLAYER_ACTIVITY_RANK_CONTENT,
                    LinkedList(asList(nowRank.toString()))
                )
                sendMail(areaCache, player.id, senMailInfo, attach = allReward)
            }

            wpm.es.fire(
                areaCache, player.id, PLAYER_ACTIVITY_RANK,
                ActivityGetRankReward(nowRank)
            )

            nowRank++
        }

        // 记录下这次活动的排行榜
        val rank =
            areaCache.activityRankCache.createActivityRank(
                nowTime + eventInformationProto.eventSaveTime * 1000,
                rankInfoMap
            )

        // 清理玩家数据
        for (joinActivityPlayer in allJoinActivityPlayers) {
            // 给玩家插入一个参与记录
            val player = areaCache.playerCache.findPlayerById(joinActivityPlayer.playerId)
            if (player == null) {
                continue
            }

            if (joinActivityPlayer.score == 0) {
                continue
            }

            var myRank = ranks[joinActivityPlayer.playerId]
            if (myRank == null) {
                myRank = 0
            }

            player.joinActivityList.add(
                JoinActivity(
                    eventInformationProtoId,
                    nowTime,
                    joinActivityPlayer.score.toLong(),
                    myRank,
                    rank.id
                )
            )

            joinActivityPlayer.score = 0
            joinActivityPlayer.nowTarget = 0

            // 推送给客户端
            val session = fetchOnlinePlayerSession(areaCache, joinActivityPlayer.playerId)
            if (session != null) {
                createPlayerActivityChangeNotifier(
                    joinActivityPlayer.activityId,
                    joinActivityPlayer.score,
                    myRank,
                    1
                ).notice(session)
            }
        }
    }

    // 删除活动数据行
    areaCache.serverActivityCache.deleteServerActivity(activity)
}


