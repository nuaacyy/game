package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Mass
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.module.walk.walkComm.notice2MultiPlayerWalkRobotShow
import com.point18.slg2d.world.msgnotice.createLanMsgNotifier
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.WalkSpeedAskRt
import java.util.*
import java.util.Arrays.asList

class WalkSpeedDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.walkSpeedAskReq
        val playerId = req.playerId
        val groupId = askMsg.groupId
        val speedRate = askMsg.speedRate

        val result = walkSpeed(areaCache, playerId, groupId, speedRate)

        val rt = WalkSpeedAskRt.newBuilder()
        rt.rt = result.code

        resp.setWalkSpeedAskRt(rt)
    }

    private fun walkSpeed(areaCache: AreaCache, playerId: Long, groupId: Long, speedRate: Int): ResultCode {
        if (speedRate < 0 || speedRate >= 10000) {
            return ResultCode.PARAMETER_ERROR
        }

        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(groupId)
        if (group == null) {
            return ResultCode.PARAMETER_ERROR
        }

        val walk = areaCache.walkCache.findWalkByGroupId(groupId)
        if (walk == null) {
            return ResultCode.WalkHaveFinished
        }

        val overTime = walk.marchTimeArrival
        val nowTime = getNowTime()
        if (overTime <= nowTime) {
            return ResultCode.WalkHaveFinished
        }

        val runMass = areaCache.massCache.findMassByRunGroupId(groupId)
        if (runMass != null) {
            // 行军的集结组，需要使用集结加速道具
            return ResultCode.PARAMETER_ERROR
        }

        val posX = walk.marchAimsX
        val posY = walk.marchAimsY

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", playerId)
            return ResultCode.PARAMETER_ERROR
        }

        var findMass: Mass? = null
        var allReinforceMass: LinkedList<Mass>? = null
        when (walk.marchState) {
            WalkJoinMass,
            WalkJoinRelicMass -> {
                // 联盟内任何人都能给集结加速
                val allMass = areaCache.massCache.findMassByAllianceId(player.allianceId)
                for (mass in allMass) {
                    if (findMass != null) {
                        break
                    }
                    for (member in mass.memberInfoList) {
                        if (member.groupId == groupId) {
                            findMass = mass
                            break
                        }
                    }
                }

                // 必须是自己联盟内的加速，否则无法操作
                if (findMass == null) {
                    return ResultCode.UnableOperateGroup
                }

                // 非集结发起者进行的加速，就给集结发起者进行提示
                if (playerId != findMass.mainPlayerId) {
                    val session = fetchOnlinePlayerSession(areaCache, findMass.mainPlayerId)
                    if (session != null) {
                        createLanMsgNotifier(ALLIANCE_MEMBER_SPEED, LinkedList())
                            .notice(session)
                    }
                }
            }
            WalkReinforce -> {
                // 被增援的人能进行加速
                if (playerId != group.mainPlayerId) {
                    //如果被增援的人被人集结，则全盟人都能进行加速
                    val allMass = areaCache.massCache.findAllMassByPos(posX, posY)
                    for (mass in allMass) {
                        if (mass.fightType != WalkFightPlayer) {
                            continue
                        }
                        if (allReinforceMass == null) {
                            allReinforceMass = LinkedList()
                        }
                        allReinforceMass.add(mass)
                    }
                    if (allReinforceMass == null) {
                        return ResultCode.UnableOperateGroup
                    }

                    //非被增援这进行的加速，给被增援这进行提示
                    val castle = areaCache.castleCache.findCastleByXy(posX, posY)
                    if (castle == null) {
                        return ResultCode.PARAMETER_ERROR
                    }

                    val session = fetchOnlinePlayerSession(areaCache, castle.playerId)
                    if (session != null) {
                        createLanMsgNotifier(ALLIANCE_MEMBER_SPEED, LinkedList())
                            .notice(session)
                    }
                }
            }
            WalkTransport -> {
                //只能自己加速
                if (group.mainPlayerId != playerId) {
                    return ResultCode.UnableOperateGroup
                }

                val castle = areaCache.castleCache.findCastleByXy(posX, posY)
                if (castle != null) {
                    //给被运输者进行提示
                    val session = fetchOnlinePlayerSession(areaCache, castle.playerId)
                    if (session != null) {
                        createLanMsgNotifier(ALLIANCE_MEMBER_SPEED, LinkedList())
                            .notice(session)
                    }
                }
            }
            WalkReinforceWonder -> {
                //增援奇观，全盟人都能进行加速

                //非自己进行的加速，就给增援者进行提示
                if (playerId != group.mainPlayerId) {
                    val session = fetchOnlinePlayerSession(areaCache, group.mainPlayerId)
                    if (session != null) {
                        createLanMsgNotifier(ALLIANCE_MEMBER_SPEED, LinkedList())
                            .notice(session)
                    }
                }
            }
            else -> {
                //只能自己加速
                if (group.mainPlayerId != playerId) {
                    return ResultCode.UnableOperateGroup
                }
                val noticePlayerIds = hashMapOf<Long, Int>()
                val castle = areaCache.castleCache.findCastleByXy(posX, posY)
                if (castle != null) {
                    noticePlayerIds[castle.playerId] = 1
                }
                val groups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(
                    posX,
                    posY,
                    Farming or Stationed or Reinforce
                )
                groups.forEach {
                    noticePlayerIds[it.mainPlayerId] = 1
                }
                for ((id, _) in noticePlayerIds) {
                    if (playerId == id) {
                        continue
                    }
                    val otherPlayer = areaCache.playerCache.findPlayerById(id)
                    if (otherPlayer == null) {
                        normalLog.error("找不到对方玩家的信息:%d", id)
                        return ResultCode.PARAMETER_ERROR
                    }
                    if (player.allianceId != 0L && player.allianceId == otherPlayer.allianceId) {
                        continue
                    }

                    //给被攻击者进行提示
                    val session = fetchOnlinePlayerSession(areaCache, id)
                    if (session != null) {
                        createLanMsgNotifier(ENEMY_SPEED, LinkedList()).notice(session)
                    }
                }
            }
        }

        // 计算时间
        val leftTime = overTime - nowTime
        val speed = walk.walkSpeed
        walk.walkSpeed = speed * 10000 / (10000 - speedRate)

        val leftTimeAfterSpeed = (leftTime * speed / walk.walkSpeed).toLong()
        val newOverTime = nowTime + leftTimeAfterSpeed
        walk.marchTimeArrival = newOverTime

        val nowPos = areaCache.walkCache.calCurrentPos(walk)

        if (findMass != null) {
            for (member in findMass.memberInfoList) {
                if (member.groupId == groupId) {
                    member.arriveTime = newOverTime
                    noticeMassGroup(areaCache, Update, findMass)
                    break
                }
            }
        }
        if (allReinforceMass != null) {
            for (mass in allReinforceMass) {
                if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
                    noticeReinforceMassGroup(areaCache, Update, mass)
                }
            }
        }

        // 通知被加速
        if (group.mainPlayerId != playerId) {
            val beSpeedPlayer = areaCache.playerCache.findPlayerById(group.mainPlayerId)
            if (beSpeedPlayer == null) {
                normalLog.error("找不到被加速的玩家信息:%d", group.mainPlayerId)
                return ResultCode.PARAMETER_ERROR
            }

            val session = fetchOnlinePlayerSession(areaCache, group.mainPlayerId)
            if (session != null) {
                createLanMsgNotifier(BE_SPEED, asList(beSpeedPlayer.name)).notice(session)
            }
        }

        noticeSelfWalkForceGroup(areaCache, Update, group)

        // 如果出征状态为奇观增援
        if (walk.marchState == WalkReinforceWonder) {
            val allAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
            val memberIds = LinkedList<Long>()
            for (member in allAllianceMembers) {
                memberIds.add(member.id)
            }
            noticeWonderReinforce(areaCache, Update, group.id, memberIds)
        }

        // 如果出征状态为增援
        if (walk.marchState == WalkReinforce) {
            // 通知己方联盟成员更新
            val allMass = areaCache.massCache.findAllMassByPos(walk.marchAimsX, walk.marchAimsY)
            for (mass in allMass) {
                if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
                    noticeReinforceMassGroup(areaCache, Update, mass)
                }
            }
        }

        val posMap = hashMapOf<Int, Int>()
        posMap[nowPos.posX.toInt()] = nowPos.posY.toInt()
        posMap[walk.marchPlaceX] = walk.marchPlaceY
        posMap[posX] = posY
        notice2MultiPlayerWalkRobotShow(
            areaCache,
            walk,
            posMap,
            ADD_WALKROBOTINFO
        )

        //刷新预警
        updateWarnForWalk(areaCache, walk)
        return ResultCode.SUCCESS
    }
}