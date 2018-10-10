package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.common.allianceMemberInfoChange
import com.point18.slg2d.public.common.playerOnlineNotic
import com.point18.slg2d.public.datacache.AllianceMember
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.public.datacache.findAllianceLastReqTimeByAllianceId
import com.point18.slg2d.public.datacache.findAllianceReqsByAllianceId
import pb4server.*
import java.util.*

class UpdateAllianceMemberInfoInHomeSyncOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.updateAllianceMemberInfoInHomeAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setUpdateAllianceMemberInfoInHomeAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: UpdateAllianceMemberInfoInHomeAskReq
    ): UpdateAllianceMemberInfoInHomeAskRt.Builder {
        val rt = UpdateAllianceMemberInfoInHomeAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (allianceMember == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        val allianceMemberTrace =
            publicCache.allianceMemberTraceCache.findAllianceMemberTraceByAllianceIdAndPlayerId(
                allianceId,
                playerId
            )
        if (allianceMemberTrace == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        for (uInfo in req.updatesList) {
            val updateType = uInfo.updateType
            val updateInfo = uInfo.info
            when (updateType) {
                UpdatePower -> {
                    val power = updateInfo.toLong()
                    allianceMember.memPower = power

                    val oldPower = allianceMember.memPower
                    allianceMember.memPower = power
                    val diffPower = power - oldPower

                    var newPower = 0L
                    val allianceMembers = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
                    for (m in allianceMembers) {
                        newPower += m.memPower
                    }
                    alce.alliancePower = newPower

                    // 推送到公共服
                    syncAllianceInfo2AM(
                        publicCache,
                        alce,
                        diffPower,
                        0,
                        0
                    )
                }
                UpdateOnlineState -> {
                    val state = updateInfo.toInt()
                    allianceMember.onlineState = state
                    var changeType = ALLIANCE_MEMBER_FLAG_LOGIN_IN

                    val allianceMembers =
                        publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
                    val nowPos = LinkedList<Int>()
                    for ((p, _) in allianceMember.alliancePosMap) {
                        nowPos.add(p)
                    }

                    if (state == OffType) {
                        allianceMember.lastLeaveTime = getNowTime()
                        changeType = ALLIANCE_MEMBER_FLAG_LOGIN_OUT
                    } else {
                        // 玩家上线,推送给玩家上线所需的帮派数据
                        playerOnlineNotic(
                            publicCache.publicActor,
                            allianceMember.mapPltAreaId, allianceMember.id,
                            enterGamePublicInfo(publicCache, allianceMember)
                        )
                    }

                    val pltAreas = mutableMapOf<Long, Int>()

                    for (am in allianceMembers) {
                        pltAreas[am.mapPltAreaId] = 1
                    }

                    for ((pltAreaId, _) in pltAreas) {
                        allianceMemberInfoChange(
                            publicCache.publicActor,
                            pltAreaId, allianceId, changeType, allianceMember.id,
                            allianceMember.name, nowPos, allianceMember.onlineState, allianceMember.photoProtoId
                        )
                    }
                }

                UpdatePhotoProtoId -> allianceMember.photoProtoId = updateInfo.toInt()

                UpdateHonor -> {
                    val honor = updateInfo.toInt()
                    allianceMemberTrace.honor += honor
                    allianceMemberTrace.weekHonor += honor
                }
                UpdateCanHelpNum -> {
                    val canHelpNum = updateInfo.toInt()
                    allianceMember.canHelpNum = canHelpNum
                }

                UpdateName -> {
                    allianceMember.name = updateInfo

                    // 推送到公共服
                    syncAllianceInfo2AM(
                        publicCache,
                        alce,
                        0,
                        0,
                        0
                    )
                }

                UpdateCastleLv -> {
                    val castleLv = updateInfo.toInt()
                    allianceMember.playerCastleLv = castleLv
                }

                UpdateKillSolider -> {
                    val killSolider = updateInfo.toInt()
                    allianceMemberTrace.killSolider += killSolider
                    allianceMemberTrace.weekKillSolider += killSolider

                    // 推送到公共服
                    syncAllianceInfo2AM(
                        publicCache,
                        alce,
                        0,
                        killSolider.toLong(),
                        0
                    )
                }

                UpdateCureSolider -> {
                    val cureSolider = updateInfo.toInt()
                    allianceMemberTrace.cureSolider += cureSolider
                    allianceMemberTrace.weekCureSolider += cureSolider
                }

                UpdateKillMonsterNum -> {
                    val killMonster = updateInfo.toInt()
                    allianceMemberTrace.killMonster += killMonster
                    allianceMemberTrace.weekKillMonster += killMonster
                }
                UpdateTransportationValue -> {
                    val transportationValue = updateInfo.toInt()
                    allianceMemberTrace.weekTransportationValue += transportationValue
                }

                UpdateVipLv -> {
                    val vipLv = updateInfo.toInt()
                    allianceMember.vipLv = vipLv
                }
                UpdateOffice -> {
                    val officeInfoData = toObj<Map<Long, Int>>(updateInfo)

                    for ((worldId, officeId) in officeInfoData) {
                        if (officeId == 0) {
                            allianceMember.officeMap.remove(worldId)
                        } else {
                            allianceMember.officeMap[worldId] = officeId
                        }
                    }
                }
                UpdateMonsterScore -> {
                    val score = updateInfo.toInt()
                    allianceMemberTrace.weekMonsterScore += score
                    allianceMemberTrace.monsterScore += score

                    // 推送到公共服
                    syncAllianceInfo2AM(
                        publicCache,
                        alce,
                        0,
                        0,
                        score.toLong()
                    )
                }
                UpdateAllianceNickName -> {
                    allianceMember.allianceNickName = updateInfo
                }
                else -> {
                    println("UpdateAllianceMemberInfoSyncOp中出现了未定义的修改类型:$updateType")
                }
            }
        }

        return rt
    }

    fun enterGamePublicInfo(publicCache: PublicCache, player: AllianceMember): EnterGamePublicRtVo {
        // val rt = EnterGamePublicRtVo()
        val rt = EnterGamePublicRtVo.newBuilder()
        if (player.allianceId == 0L) {
            return rt.build()
        }

        val alce = publicCache.allianceCache.findAllianceById(player.allianceId)
        if (alce == null) {
            return rt.build()
        }
        // val allianceInfo = AllianceInfoVo()
        val allianceInfo = AllianceInfoVo.newBuilder()
        allianceInfo.id = player.allianceId
        allianceInfo.name = alce.name
        allianceInfo.shortName = alce.shortName
        allianceInfo.color = alce.flagColor
        allianceInfo.style = alce.flagStyle
        allianceInfo.effect = alce.flagEffect

        for ((p, _) in player.alliancePosMap) {
            // allianceInfo.positions.add(p)
            allianceInfo.addPositions(p)
        }

        // rt.allianceInfo = allianceInfo
        rt.allianceInfo = allianceInfo.build()

        // 联盟成员信息
        val aPlayers = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(player.allianceId)
        for (playerM in aPlayers) {
            if (playerM.id == player.id) {
                continue
            }

            // val memberPlayerInfo = MemberPlayerInfoVo()
            val memberPlayerInfo = MemberPlayerInfoVo.newBuilder()

            memberPlayerInfo.playerId = playerM.id
            memberPlayerInfo.playerName = playerM.name
            memberPlayerInfo.isOnline = playerM.onlineState
            memberPlayerInfo.protoId = playerM.photoProtoId
            for ((pos, _) in playerM.alliancePosMap) {
                // memberPlayerInfo.positions.add(pos)
                memberPlayerInfo.addPositions(pos)
            }

            // rt.members.add(memberPlayerInfo)
            rt.addMembers(memberPlayerInfo)
        }

        for ((_, mark) in publicCache.allianceMarkCache.findMarksByAllianceId(player.allianceId)) {
            val player = publicCache.allianceMemberCache.findAllianceMemberById(mark.playerId)
            if (player == null) {
                continue
            }

            // val aPbMark = AllianceMarkInfoVo()
            val aPbMark = AllianceMarkInfoVo.newBuilder()
            aPbMark.type = mark.type
            aPbMark.playerId = player.id
            aPbMark.playerName = player.name
            aPbMark.x = mark.x
            aPbMark.y = mark.y
            aPbMark.title = mark.title
            aPbMark.desp = mark.description
            aPbMark.markTime = mark.markTime
            aPbMark.pltAreaNo = mark.pltAreaNo
            aPbMark.photoProtoId = player.photoProtoId
            aPbMark.markId = mark.id

            for ((p, _) in player.alliancePosMap) {
                // aPbMark.positions.add(p)
                aPbMark.addPositions(p)
            }
            // rt.allianceMarks.add(aPbMark)
            rt.addAllianceMarks(aPbMark)

        }

        if (player.allianceId != 0L) {
            // 联盟标记小红点
            val lastMarkTime = publicCache.allianceMarkCache.findLastMarkTimeAllianceId(player.allianceId)
            // val markRedPoint = RedPointVo()
            val markRedPoint = RedPointVo.newBuilder()

            markRedPoint.redPointType = AllianceMarkRedPoint
            markRedPoint.redPointId = player.allianceId
            markRedPoint.redPointShowTime = (lastMarkTime / 1000).toInt()
            // rt.redPoints.add(markRedPoint)
            rt.addRedPoints(markRedPoint)

            // 联盟申请小红点
            val lastJoinReqTime =
                findAllianceLastReqTimeByAllianceId(publicCache, player.allianceId)
            // val joinRedPoint = RedPointVo()
            val joinRedPoint = RedPointVo.newBuilder()
            joinRedPoint.redPointType = AllianceJoingRedPoint
            joinRedPoint.redPointId = player.allianceId
            joinRedPoint.redPointShowTime = (lastJoinReqTime / 1000).toInt()
            // rt.redPoints.add(joinRedPoint)
            rt.addRedPoints(joinRedPoint)

            // 联盟帮助小红点
            val lastHelpTime =
                publicCache.allianceHelpCache.findLastHelpTimeByAllianceId(player.allianceId)
            // val helpRedPoint = RedPointVo()
            val helpRedPoint = RedPointVo.newBuilder()
            helpRedPoint.redPointType = AllianceHelpRedPoint
            helpRedPoint.redPointId = player.allianceId
            helpRedPoint.redPointShowTime = (lastHelpTime / 1000).toInt()
            // rt.redPoints.add(helpRedPoint)
            rt.addRedPoints(helpRedPoint)

            val waijiaos =
                publicCache.allianceWaijiaoCache.findAllianceWaijiaoByAllianceId(player.allianceId)
            for (waijiao in waijiaos) {
                // rt.allianceWaijiaoTime.add((waijiao.createTime / 1000).toInt())
                rt.addAllianceWaijiaoTime((waijiao.createTime / 1000).toInt())
            }
        }

        val aTopics = publicCache.allianceTopicCache.findAllianceTopicsByAllianceId(alce.id)
        rt.noReadTopic = 0
        for ((_, aTopic) in aTopics) {
            // 筛选符合玩家应该看到的回复消息
            val replies = getPlayerTopicReply(publicCache, player, aTopic, 0, 1)
            if (replies.size > 0) {
                // 玩家是否读取邮件主题
                val m: MutableMap<Long, Int> = toObj(aTopic.read)
                val ok = m[player.id]
                if (ok == null) {
                    rt.noReadTopic = 1
                    break
                }
            }
        }

        rt.reqListNum = findAllianceReqsByAllianceId(publicCache, alce.id).size

        //todo 待删除
        // val allianceLivenessVo = syncdomain.AllianceLivenessVo()
        val allianceLivenessVo = AllianceLivenessVo.newBuilder()
        allianceLivenessVo.allianceLivenessLv = 0
        allianceLivenessVo.allianceLivenessExp = 0
        allianceLivenessVo.allianceLivenessScore = 0
        allianceLivenessVo.allianceLivenessGiftId = 0
        allianceLivenessVo.allianceLivenessTodayLv = 0
        // rt.allianceLivenessVo = allianceLivenessVo
        rt.allianceLivenessVo = allianceLivenessVo.build()
        rt.rankLv = alce.allianceRankLv

        return rt.build()
    }
}