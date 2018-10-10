package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.*
import pb4server.AllianceActivityScoreAddAskReq
import pb4server.AllianceActivityScoreAddAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.public.common.allianceActivityScoreChange
import com.point18.slg2d.public.common.sendMailToPlayer
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*
import java.util.Arrays.asList

class AllianceActivityScoreAddOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val internalMessage = req.allianceActivityScoreAddAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setAllianceActivityScoreAddAskRt(rt)
    }

    fun dealMsg(publicCache: PublicCache,
                allianceId: Long,
                playerId: Long,
                worldId: Long,
                req: AllianceActivityScoreAddAskReq): AllianceActivityScoreAddAskRt.Builder {
        val rt = AllianceActivityScoreAddAskRt.newBuilder()
        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        val alliance = publicCache.allianceCache.findAllianceById(allianceId)

        if (player == null || alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val allianceActivityInfo =
                publicCache.everyAllianceActivityCache.findAllianceActivityInfosByAllianceId(allianceId)
        val allianceMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(alliance.id)

        for ((activityId, activityInformationProto) in pcs.eventAllianceInformationProtoCache.protoMapById) {
            // 根据配置活动ID，获取联盟活动
            if (publicCache.allianceActivityCache.findAllianceActivityByActivityId(activityId) == null) {
                continue // 该活动模板对应的活动不在进行中，因此跳过
            }

            if (activityInformationProto.eventScoreSourceMap[req.conditionType] == null) {
                continue // 该活动不支持这个活动加分的事件，跳过
            }

            val activityConditionProto = pcs.eventConditionProtoCache.protoMap[req.conditionType]
                    ?: continue // 获取加分条件的模板 (获取不到大概是conditionType传错或者配置问题)

            var isFind = false
            for (activity in allianceActivityInfo) {
                if (activity.activityId == activityId) {
                    isFind = true
                    val allTarget = activity.nowTarget + req.addScore
                    rt.successAddScore += req.addScore
                    if (allTarget >= activityConditionProto.num) {
                        // 达到条件,获取积分
                        val oldScore = activity.score
                        val scoreNum = (allTarget.toDouble() / activityConditionProto.num.toDouble()).toInt()
                        val diffTarget = allTarget % activityConditionProto.num

                        activity.score = activity.score + scoreNum * activityConditionProto.score
                        activity.nowTarget = diffTarget.toInt()

                        // 检测是否有获得新一档奖励物品
                        val canGetRewards = pcs.eventAllianceInformationProtoCache.findEventRewardByMinAndMax(
                                activity.activityId,
                                oldScore, activity.score
                        )

                        val pltAreas = mutableMapOf<Long, LinkedList<Long>>()
                        for (am in allianceMembers) {
                            val aams = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
                            aams.add(am.id)
                        }

                        var myRank = 0
                        val allJoinActivityAlliances =
                                publicCache.everyAllianceActivityCache.findAllAlliancectivityInfos(
                                        activity.activityId
                                )
                        allJoinActivityAlliances.sortByDescending { it.score }
                        var nowRank = 1
                        for (info in allJoinActivityAlliances) {
                            if (info.allianceId == allianceId) {
                                myRank = nowRank
                                break
                            }
                            nowRank++
                        }

                        for ((pltAreaId, playerIds) in pltAreas) {
                            for (reward in canGetRewards) {
                                val thisReward = LinkedList<ResVo>()
                                for (dropId in reward.rewardBags) {
                                    val drop = com.point18.slg2d.common.pc.pcs.dropBagCache.dropBagMap[dropId]
                                            ?: continue
                                    thisReward.addAll(drop.dropMap)
                                }
                                if (thisReward.size > 0) {
                                    sendMailToPlayer(
                                            publicCache.publicActor,
                                            (pltAreaId),
                                            playerIds,
                                            TEXT_READ_LAN,
                                            ALLIANCE_ACTIVITY_STAGE_TITLE,
                                            LinkedList(
                                                    asList(
                                                            activity.activityId.toString(),
                                                            alliance.name,
                                                            alliance.shortName
                                                    )
                                            ),
                                            ALLIANCE_ACTIVITY_STAGE_CONTENT,
                                            LinkedList(asList(activity.activityId.toString())),
                                            ALLIANCE_ACTIVITY_STAGE_REWARD,
                                            SYS_MAIL,
                                            resVoToResString(LinkedList(thisReward)),
                                            0,
                                            ""
                                    )
                                }
                            }

                            allianceActivityScoreChange(
                                    publicCache.publicActor,
                                    pltAreaId,
                                    playerIds,
                                    activity.activityId,
                                    activity.score,
                                    myRank,
                                    false
                            )
                        }
                    } else {
                        // 未达到条件,更新进度,未获取到积分
                        activity.nowTarget = allTarget.toInt()
                    }
                }
            }

            if (!isFind) {
                // 玩家没参加过这个活动,这是第一次
                val activity = publicCache.everyAllianceActivityCache.createAllianceActivityInfo(
                        activityId,
                        allianceId
                )
                val allTarget = activity.nowTarget + req.addScore
                rt.successAddScore += req.addScore
                if (allTarget >= activityConditionProto.num) {
                    // 达到条件,获取积分
                    val scoreNum = (allTarget.toDouble() / activityConditionProto.num.toDouble()).toInt()
                    val diffTarget = allTarget % activityConditionProto.num

                    activity.score += scoreNum * activityConditionProto.score
                    activity.nowTarget = diffTarget.toInt()

                    // 检测是否有获得新一档奖励物品
                    val canGetRewards = pcs.eventAllianceInformationProtoCache.findEventRewardByMinAndMax(
                            activity.activityId,
                            0,
                            activity.score
                    )

                    val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

                    for (am in allianceMembers) {
                        val aams = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
                        aams.add(am.id)
                    }

                    var myRank = 0
                    val allJoinActivityAlliances =
                            publicCache.everyAllianceActivityCache.findAllAlliancectivityInfos(
                                    activity.activityId
                            )
                    allJoinActivityAlliances.sortByDescending { it.score }
                    var nowRank = 1
                    for (info in allJoinActivityAlliances) {
                        if (info.allianceId == allianceId) {
                            myRank = nowRank
                            break
                        }

                        nowRank++
                    }

                    for ((pltAreaId, playerIds) in pltAreas) {
                        for (reward in canGetRewards) {
                            val thisReward = LinkedList<ResVo>()
                            for (dropId in reward.rewardBags) {
                                val drop = com.point18.slg2d.common.pc.pcs.dropBagCache.dropBagMap[dropId] ?: continue
                                thisReward.addAll(drop.dropMap)
                            }
                            if (thisReward.size > 0) {
                                sendMailToPlayer(
                                        publicCache.publicActor,
                                        (pltAreaId),
                                        playerIds,
                                        TEXT_READ_LAN,
                                        ALLIANCE_ACTIVITY_STAGE_TITLE,
                                        LinkedList(
                                                asList(activity.activityId.toString(), alliance.name, alliance.shortName)
                                        ),
                                        ALLIANCE_ACTIVITY_STAGE_CONTENT,
                                        LinkedList(asList(activity.activityId.toString())),
                                        ALLIANCE_ACTIVITY_STAGE_REWARD,
                                        SYS_MAIL,
                                        resVoToResString(thisReward),
                                        0,
                                        ""
                                )
                            }
                        }

                        allianceActivityScoreChange(
                                publicCache.publicActor,
                                pltAreaId,
                                playerIds,
                                activity.activityId,
                                activity.score,
                                myRank,
                                false
                        )
                    }

                } else {
                    // 未达到条件,更新进度,未获取到积分
                    activity.nowTarget = allTarget.toInt()
                }
            }
        }

        return rt
    }

}
