package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.allianceActivityScoreAdd
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.event.ActivityGetAdvReward
import com.point18.slg2d.world.event.ActivityScoreOver
import com.point18.slg2d.world.msgnotice.createNewPlayerActivityChangeNotifier
import com.point18.slg2d.world.msgnotice.createPlayerActivityChangeNotifier
import com.point18.slg2d.world.wpm
import java.util.*
import java.util.Arrays.asList

var PlayerActivityM = PlayerActivityModule()

class PlayerActivityModule : com.point18.slg2d.common.baseg.IModule {
    override fun moduleInit() {
        // 注册事件处理
        wpm.es.register(FARM_END, FarmEndEventHandler())

        wpm.es.register(FARM_EMPTY, FarmEmptyEventHandler())

        // 检测任务完成度
        wpm.es.register(PLAYER_ACTIVITY_CHANGE, ActivityChangeEventHandler())

        // 活动结束心跳
        wpm.hs.registerHeartHandler(ActivityTimeOverHeartHandler())
    }
}

fun onActivityChange(areaCache: AreaCache, playerId: Long, activityConditionType: Int, addNum: Int) {
    val player = areaCache.playerCache.findPlayerById(playerId) ?: return
    dealActivity(areaCache, player, activityConditionType, addNum)

    if (player.allianceId != 0L) {
        dealAllianceActivity(areaCache, player, activityConditionType, addNum)
    }
}

fun dealActivity(areaCache: AreaCache, player: Player, conditionType: Int, addNum: Int) {
    val playerActivities = areaCache.playerActivityCache.findPlayerActivityListByPlayerId(player.id)
    val playerId = player.id

    val session = fetchOnlinePlayerSession(areaCache, player.id)
    var getScore = 0
    for ((activityId, activityInformationProto) in pcs.eventInformationProtoCache.protoMap) {
        val eventTimeProto = pcs.eventTimeProtoCache.protoMap[activityId]
        if (eventTimeProto == null) {
            continue
        }

        if (player.newPlayerActivity == 1 && eventTimeProto.eventType != NEW_PLAYER_ACTIVITY) {
            // 玩家当前正属于新手挑战中,普通挑战无法进行
            continue
        }

        if (player.newPlayerActivity == 0 && eventTimeProto.eventType == NEW_PLAYER_ACTIVITY) {
            // 玩家当前不属于新手挑战中,新手挑战无法进行
            continue
        }

        // 根据配置活动ID，获取玩家活动
        val playerActivity = areaCache.playerActivityCache.findPlayerActivityByPlayerIdAndActivityId(playerId, activityId)
        val castleLv = if (playerActivity == null) {
            // 找不到玩家活动数据,那就按玩家此时的主堡等级来做
            val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
                ?: continue
            castle.lv
        } else {
            playerActivity.castleLv
        }

        if (areaCache.serverActivityCache.findServerActivityByActivityId(activityId) == null) {
            continue // 该活动模板对应的活动不在进行中，因此跳过
        }

        val eventInformationProto = activityInformationProto[castleLv]
            ?: continue // 该活动不支持当前玩家主堡等级，跳过

        if (eventInformationProto.eventScoreSourceMap[conditionType] == null) {
            continue // 该活动不支持这个活动加分的事件，跳过
        }

        val activityConditionProto = pcs.eventConditionProtoCache.protoMap[conditionType]
            ?: continue // 获取加分条件的模板 (获取不到大概是conditionType传错或者配置问题)

        var isFind = false // 标记当前玩家是否已经参与该模板的活动
        for (activity in playerActivities) {
            if (activity.activityId == activityId) {
                isFind = true
                val allTarget = activity.nowTarget + addNum
                getScore += addNum
                if (allTarget >= activityConditionProto.num) {
                    // 达到条件,获取积分
                    val oldScore = activity.score
                    val scoreNum = allTarget / activityConditionProto.num
                    val diffTarget = allTarget % activityConditionProto.num

                    activity.score += (scoreNum * activityConditionProto.score)
                    activity.nowTarget = diffTarget

                    // 推送给客户端
                    var myRank = 0
                    val allJoinActivityPlayersByCastleLv = areaCache.playerActivityCache.findAllPlayerActivityInfoMap(activityId)
                    val p = allJoinActivityPlayersByCastleLv[eventInformationProto.id]
                    if (p != null) {
                        p.sortByDescending { it.score }
                        var nowRank = 1
                        for (pp in p) {
                            if (pp.playerId == player.id) {
                                myRank = nowRank
                                break
                            }

                            nowRank++
                        }
                    }

                    // 推送给客户端
                    if (session != null) {
                        val notifier = createPlayerActivityChangeNotifier(activity.activityId, activity.score, myRank, 0)
                        notifier.notice(session)
                    }

                    // 检测是否有获得新一档奖励物品
                    val canGetRewards = pcs.eventInformationProtoCache.findEventRewardByMinAndMax(
                        activity.activityId,
                        oldScore, activity.score, activity.castleLv
                    )

                    var maxIndex = 0
                    for (rs in canGetRewards) {
                        val reward = LinkedList<ResVo>()
                        for (dropId in rs.rewardBags) {
                            val drop = pcs.dropBagCache.dropBagMap[dropId] ?: continue
                            reward += drop.dropMap
                        }

                        if (reward.size > 0) {
                            //发送邮件
                            val senMailInfo = MailInfo(
                                TEXT_READ_LAN,
                                PLAYER_ACTIVITY_STAGE_TITLE,
                                LinkedList(asList(activity.activityId.toString(), player.name, player.allianceShortName)),
                                PLAYER_ACTIVITY_STAGE_CONTENT,
                                LinkedList(asList(activity.activityId.toString(), rs.index.toString()))
                            )
                            sendMail(areaCache, player.id, senMailInfo, attach = reward)

                            if (eventTimeProto.eventType == NEW_PLAYER_ACTIVITY &&
                                rs.index == eventInformationProto.eventPrizeMap.size) {
                                // 领取了新手挑战的最后一档奖励,新手挑战状态结束
                                player.newPlayerActivity = 0
                                if (session != null) {
                                    createNewPlayerActivityChangeNotifier().notice(session)
                                }
                            }
                        }

                        if (rs.index > maxIndex) {
                            maxIndex = rs.index
                        }
                    }

                    if (maxIndex != 0) {
                        wpm.es.fire(areaCache, playerId, PLAYER_ACTIVITY_ADV_REWARD,
                            ActivityGetAdvReward(maxIndex)
                        )
                    }
                } else {
                    // 未达到条件,更新进度,未获取到积分
                    activity.nowTarget = allTarget
                }
            }
        }

        if (!isFind) {
            // 玩家没参加过这个活动,这是第一次
            val activity = areaCache.playerActivityCache.createPlayerActivity(activityId, playerId, castleLv)
            val allTarget = activity.nowTarget + addNum
            getScore += addNum
            if (allTarget >= activityConditionProto.num) {
                // 达到条件,获取积分
                val scoreNum = allTarget / activityConditionProto.num
                val diffTarget = allTarget % activityConditionProto.num

                activity.score += (scoreNum * activityConditionProto.score)
                activity.nowTarget = diffTarget

                // 推送给客户端
                var myRank = 0
                val allJoinActivityPlayersByCastleLv = areaCache.playerActivityCache.findAllPlayerActivityInfoMap(activityId)
                val p = allJoinActivityPlayersByCastleLv[eventInformationProto.id]
                if (p != null) {
                    p.sortByDescending { it.score }
                    var nowRank = 1
                    for (pp in p) {
                        if (pp.playerId == player.id) {
                            myRank = nowRank
                            break
                        }

                        nowRank++
                    }
                }

                if (session != null) {
                    val notifier = createPlayerActivityChangeNotifier(activity.activityId, activity.score, myRank, 0)
                    notifier.notice(session)
                }

                // 检测是否有获得新一档奖励物品
                val canGetRewards = pcs.eventInformationProtoCache.findEventRewardByMinAndMax(
                    activity.activityId,
                    0,
                    activity.score,
                    activity.castleLv
                )
                for (rs in canGetRewards) {
                    val reward = LinkedList<ResVo>()
                    for (dropId in rs.rewardBags) {
                        val drop = pcs.dropBagCache.dropBagMap[dropId] ?: continue
                        reward += drop.dropMap
                    }

                    //发送邮件
                    if (reward.size > 0) {
                        val senMailInfo = MailInfo(
                            TEXT_READ_LAN,
                            PLAYER_ACTIVITY_STAGE_TITLE,
                            LinkedList(asList(activity.activityId.toString(), player.name, player.allianceShortName)),
                            PLAYER_ACTIVITY_STAGE_CONTENT,
                            LinkedList(asList(activity.activityId.toString(), rs.index.toString()))
                        )
                        sendMail(areaCache, player.id, senMailInfo, attach = reward)
                    }
                }
            } else {
                // 未达到条件,更新进度,未获取到积分
                activity.nowTarget = allTarget
            }
        }
    }

    if (getScore != 0) {
        val target = areaCache.targetCache.findMyTargetByPlayerId(playerId)
        if (target != null) {
            val score = target.activityScoreMap.getOrPut(PLAYER_ACTIVITY) { 0 } + getScore
            target.activityScoreMap[PLAYER_ACTIVITY] = score

            wpm.es.fire(areaCache, playerId, ACTIVITY_SCORE_OVER,
                ActivityScoreOver(conditionType)
            )
        }
    }
}

fun dealAllianceActivity(areaCache: AreaCache, player: Player, conditionType: Int, addNum: Int) {
    // 去公共服同步数据
    allianceActivityScoreAdd(areaCache, player.allianceId, player.id, addNum.toLong(), conditionType)
}