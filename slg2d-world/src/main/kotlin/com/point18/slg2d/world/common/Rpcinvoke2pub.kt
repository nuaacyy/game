package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.moduleFunClass.SimplifiedMassInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.event.ActivityScoreOver
import com.point18.slg2d.world.event.AllianceSetPosition
import com.point18.slg2d.world.event.CreateAlliance
import com.point18.slg2d.world.event.InOrOffAlliance
import com.point18.slg2d.world.gmsg.updatePlayerAllianceInfo
import com.point18.slg2d.world.msgnotice.AllianceFlagChangeNotifier
import com.point18.slg2d.world.msgnotice.createAllianceMissionGiftNumChangeNotifier
import com.point18.slg2d.world.msgnotice.createPlayerAllianceResearchChangeNotifier
import com.point18.slg2d.world.wpm
import pb4client.*
import pb4client.Notice
import pb4client.QueryInAllianceRankVo
import pb4server.*
import java.util.*
import java.util.Arrays.asList

// 创建联盟
fun createAlliance(
    session: PlayerSession,
    allianceId: Long,
    name: String,
    shortName: String,
    playerName: String,
    power: Long,
    lastLeaveTime: Long,
    honor: Long,
    canHelpNum: Int,
    allianceLan: Int,
    gamePltAreaNo: Long,
    mapPltAreaNo: Long,
    gameAreaNo: Int,
    photoProtoId: Int,
    playerCastleLv: Int,
    nickName: String
) {
    val areaCache = session.areaCache
    val playerId = session.playerId

    val createAllianceAskReq = CreateAllianceAskReq.newBuilder()
    createAllianceAskReq.name = name
    createAllianceAskReq.shortName = shortName
    createAllianceAskReq.pid = playerId
    createAllianceAskReq.allianceLan = allianceLan
    createAllianceAskReq.playerName = playerName
    createAllianceAskReq.power = power
    createAllianceAskReq.lastLeaveTime = lastLeaveTime
    createAllianceAskReq.honor = honor
    createAllianceAskReq.canHelpNum = canHelpNum
    createAllianceAskReq.gamePltAreaNo = gamePltAreaNo
    createAllianceAskReq.mapPltAreaNo = mapPltAreaNo
    createAllianceAskReq.gameAreaNo = gameAreaNo
    createAllianceAskReq.photoProtoId = photoProtoId
    createAllianceAskReq.playerCastleLv = playerCastleLv
    createAllianceAskReq.playerNickName = nickName

    areaCache.createACS(
        wpm.allianceManagerProxy,
        areaCache.fillW2PmAskMsgHeader(playerId) {
            it.createAllianceAskReq = createAllianceAskReq.build()
        },
        World2PublicManagerAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val acRt = AllianceCreateRt.newBuilder()
                    acRt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceCreate_802, acRt.build())
                    return@createACS
                }
                askResp == null -> {
                    val acRt = AllianceCreateRt.newBuilder()
                    acRt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceCreate_802, acRt.build())
                    return@createACS
                }
                else -> {
                    val rt = askResp.createAllianceAskRt

                    val player = session.player

                    val acRt = AllianceCreateRt.newBuilder()
                    acRt.rt = rt.rt

                    if (rt.rt != ResultCode.SUCCESS.code) {
                        // 前往中央节点验证名字失败,返回消息给客户端
                        session.sendMsg(MsgType.AllianceCreate_802, acRt.build())
                    } else {
                        // 名字验证通过,前往普通联盟服进行创建
                        areaCache.createACS(
                            areaCache.worldActor.publicShardRegion,
                            areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
                                it.createAllianceAskReq = createAllianceAskReq.build()
                            },
                            World2PublicAskResp::class.java
                        ) { askResp, err ->
                            try {
                                when {
                                    err != null -> {
                                        val acRt = AllianceCreateRt.newBuilder()
                                        acRt.rt = ResultCode.ASK_ERROR1.code
                                        session.sendMsg(MsgType.AllianceCreate_802, acRt.build())
                                        return@createACS
                                    }
                                    askResp == null -> {
                                        val acRt = AllianceCreateRt.newBuilder()
                                        acRt.rt = ResultCode.ASK_ERROR2.code
                                        session.sendMsg(MsgType.AllianceCreate_802, acRt.build())
                                        return@createACS
                                    }
                                    else -> {
                                        val rt = askResp.createAllianceAskRt

                                        val action = ACTION_ALLIANCE_CREATE
                                        val player = session.player

                                        val acRt = AllianceCreateRt.newBuilder()
                                        acRt.rt = rt.rt

                                        if (rt.rt != ResultCode.SUCCESS.code) {
                                        } else {
                                            // 执行建帮之后的逻辑

                                            // 更新玩家的联盟信息
                                            player.allianceAt = getNowTime()
                                            player.allianceId = allianceId
                                            areaCache.playerCache.addPlayer2Alliance(player)

                                            player.setWrapPosition(ALLIANCE_POSITION_BOSS)
                                            player.allianceName = rt.allianceName
                                            player.allianceShortName = rt.allianceShortName
                                            player.allianceRNum = 1
                                            player.flagColor = rt.flagColor
                                            player.flagEffect = rt.flagEffect
                                            player.flagStyle = rt.flagStyle

                                            updatePlayerAllianceInfo(
                                                areaCache,
                                                rt.allianceName,
                                                rt.allianceShortName,
                                                session,
                                                player
                                            )

                                            // 推送旗帜推送
                                            val flagNotifier =
                                                AllianceFlagChangeNotifier(rt.flagColor, rt.flagStyle, rt.flagEffect)
                                            flagNotifier.notice(session)

                                            // 触发创建同盟事件
                                            wpm.es.fire(
                                                areaCache,
                                                player.id,
                                                CREATE_ALLIANCE, CreateAlliance(session.channelActor, allianceId)
                                            )

                                            // 发送首次进帮的奖励
                                            var isFirst = false
                                            if (player.isFirstJoinAlliance == 0) {
                                                isFirst = true
                                                addResToHome(
                                                    areaCache,
                                                    action,
                                                    player.id,
                                                    pcs.basicProtoCache.firstJoinCripsAward
                                                )
                                                player.isFirstJoinAlliance = 1
                                            }

                                            player.allianceResearchNum = 0
                                            player.lastAllianceResearchTime = getNowTime()
                                            val n = createPlayerAllianceResearchChangeNotifier(
                                                player.allianceResearchNum,
                                                (player.lastAllianceResearchTime / 1000).toInt()
                                            )
                                            n.notice(session)

                                            val updateInfo = mutableMapOf<Int, String>()
                                            updateInfo[UpdateCanHelpNum] =
                                                    getResearchEffectValue(
                                                        areaCache,
                                                        NO_FILTER,
                                                        player,
                                                        CanHelpNum
                                                    ).toString()
                                            updateInfo[UpdateVipLv] =
                                                    (areaCache.infoByHomeCache.findInfoByHomeByPlayerId(playerId)?.vipLv
                                                        ?: 0).toString()
                                            updateInfo[UpdateAllianceNickName] = player.allianceNickName
                                            updateAllianceMemberInfo(
                                                areaCache,
                                                player.allianceId,
                                                player.id,
                                                updateInfo
                                            )

                                            // 玩家联盟发生变化,推送地图变化
                                            val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
                                            if (castle != null) {
                                                noticeCellUpdate(areaCache, castle.x, castle.y)
                                            }

                                            val tell = HaveAllianceTell.newBuilder()
                                            tell.allianceId = allianceId
                                            tell.allianceName = player.allianceName
                                            tell.allianceShortName = player.allianceShortName
                                            tell.flagColor = player.flagColor
                                            tell.flagStyle = player.flagStyle
                                            tell.flagEffect = player.flagEffect
                                            tell.isFirst = if (isFirst) TRUE else FALSE

                                            areaCache.tellHome(areaCache.fillW2HTellMsgHeader(player.id) {
                                                it.setHaveAllianceTell(tell)
                                            })
                                        }

                                        // 告诉好友
                                        val tell = FriendChangeToHomeTell.newBuilder()
                                        areaCache.tellHome(areaCache.fillW2HTellMsgHeader(playerId) {
                                            it.setFriendChangeToHomeTell(tell)
                                        })

                                        session.sendMsg(MsgType.AllianceCreate_802, acRt.build())
                                    }
                                }
                            } catch (e: Exception) {
                                normalLog.error("CreateAllianceAskReq Error!", e)
                                val acRt = AllianceCreateRt.newBuilder()
                                acRt.rt = ResultCode.ASK_ERROR3.code
                                session.sendMsg(MsgType.AllianceCreate_802, acRt.build())
                            }

                        }
                    }
                }
            }
        } catch (e: Exception) {
            normalLog.error("CreateAllianceAskReq Error!", e)
            val acRt = AllianceCreateRt.newBuilder()
            acRt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceCreate_802, acRt.build())
        }

    }
}

// 申请加入联盟
fun joinAllianceById(
    session: PlayerSession,
    allianceId: Long,
    fightValue: Long,
    playerName: String,
    playerPhoto: Int,
    pltAreaNo: Long,
    areaNo: Int,
    lastLoginTime: Long,
    offTime: Long,
    photoProtoId: Int,
    playerCastleLv: Int,
    nickName: String
) {
    val areaCache = session.areaCache
    val playerId = session.playerId

    val joinAllianceByIdAskReq = JoinAllianceByIdAskReq.newBuilder()
    joinAllianceByIdAskReq.pid = playerId
    joinAllianceByIdAskReq.fightValue = fightValue
    joinAllianceByIdAskReq.playerName = playerName
    joinAllianceByIdAskReq.playerPhoto = playerPhoto
    joinAllianceByIdAskReq.pltAreaNo = pltAreaNo
    joinAllianceByIdAskReq.areaNo = areaNo
    joinAllianceByIdAskReq.lastLoginTime = lastLoginTime
    joinAllianceByIdAskReq.offTime = offTime
    joinAllianceByIdAskReq.photoProtoId = photoProtoId
    joinAllianceByIdAskReq.playerCastleLv = playerCastleLv
    joinAllianceByIdAskReq.playerNickName = nickName

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.joinAllianceByIdAskReq = joinAllianceByIdAskReq.build()
        },
        World2PublicAskResp::class.java
    ) { resp, err ->
        try {
            when {
                err != null -> {
                    val allianceJoinByIdRt = AllianceJoinByIdRt.newBuilder()
                    allianceJoinByIdRt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceJoinById_804, allianceJoinByIdRt.build())
                    return@createACS
                }
                resp == null -> {
                    val allianceJoinByIdRt = AllianceJoinByIdRt.newBuilder()
                    allianceJoinByIdRt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceJoinById_804, allianceJoinByIdRt.build())
                    return@createACS
                }
                else -> {
                    val rt = resp.joinAllianceByIdAskRt

                    val allianceJoinByIdRt = AllianceJoinByIdRt.newBuilder()

                    allianceJoinByIdRt.rt = rt.rt
                    allianceJoinByIdRt.allianceId = allianceId
                    allianceJoinByIdRt.quitTime = 0

                    if (rt.isCreateJoinInfo == 1) {
                        val player = areaCache.playerCache.findPlayerById(playerId)
                        if (player != null) {
                            player.joinAllianceReqs[allianceId] = 1
                        }
                    }
                    session.sendMsg(MsgType.AllianceJoinById_804, allianceJoinByIdRt.build())
                }
            }

        } catch (e: Exception) {
            normalLog.error("JoinAllianceByIdAskReq Error!", e)
            val allianceJoinByIdRt = AllianceJoinByIdRt.newBuilder()
            allianceJoinByIdRt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceJoinById_804, allianceJoinByIdRt.build())
        } finally {
            // 不管处理结果如何 都应该解除玩家的入帮状态锁
            val tell = UnLockJoinAllianceStateTell.newBuilder()
            session.areaCache.tellHome(session.areaCache.fillW2HTellMsgHeader(session.playerId) {
                it.setUnLockJoinAllianceStateTell(tell)
            })
        }
    }
}

// 检测联盟名称是否可用
fun checkAllianceName(session: PlayerSession, name: String, nameType: Int) {

    val checkAllianceNameAskReq = CheckAllianceNameAskReq.newBuilder()
    checkAllianceNameAskReq.name = name
    checkAllianceNameAskReq.nameType = nameType

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(ALLIANCE_GENERAL_PID, playerId) {
            it.checkAllianceNameAskReq = checkAllianceNameAskReq.build()
        }, // TODO 这边需要修正！或者真的提供一个shardId为0的Actor！
        World2PublicAskResp::class.java
    ) { resp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = pb4client.CheckAllianceNameRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.CreateAllianceCheckAllianceName_825, rt2client.build())
                    return@createACS
                }
                resp == null -> {
                    val rt2client = pb4client.CheckAllianceNameRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.CreateAllianceCheckAllianceName_825, rt2client.build())
                    return@createACS
                }
                else -> {
                    val rt = resp.checkAllianceNameAskRt

                    val rt2client = pb4client.CheckAllianceNameRt.newBuilder()

                    rt2client.rt = ResultCode.SUCCESS.code
                    rt2client.errorType = rt.rt

                    session.sendMsg(MsgType.CreateAllianceCheckAllianceName_825, rt2client.build())
                }
            }

        } catch (e: Exception) {
            normalLog.error("CheckAllianceNameAskReq Error!", e)
            val rt2client = pb4client.CheckAllianceNameRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.CreateAllianceCheckAllianceName_825, rt2client.build())
        }
    }
}

// 邀请玩家加入联盟
fun allianceInvite(session: PlayerSession, allianceId: Long, invitePlayerId: Long) {
    val allianceInviteAskReq = AllianceInviteAskReq.newBuilder()
    allianceInviteAskReq.invitePlayerId = invitePlayerId

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.allianceInviteAskReq = allianceInviteAskReq.build()
        },
        World2PublicAskResp::class.java
    ) { resp, err ->
        try {
            when {
                err != null -> {
                    val allianceInviteRt = pb4client.AllianceInviteRt.newBuilder()
                    allianceInviteRt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceInvite_900, allianceInviteRt.build())
                    return@createACS
                }
                resp == null -> {
                    val allianceInviteRt = pb4client.AllianceInviteRt.newBuilder()
                    allianceInviteRt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceInvite_900, allianceInviteRt.build())
                    return@createACS
                }
                else -> {
                    val rt = resp.allianceInviteAskRt
                    val allianceInviteRt = pb4client.AllianceInviteRt.newBuilder()
                    allianceInviteRt.rt = rt.rt

                    if (rt.rt == ResultCode.SUCCESS.code) {
                        // 联盟验证通过,给邀请的玩家发邮件
                        val invitePlayer = areaCache.playerCache.findPlayerById(invitePlayerId)
                        if (invitePlayer == null || invitePlayer.allianceId != 0L) {
                            allianceInviteRt.rt = ResultCode.PARAMETER_ERROR.code
                            session.sendMsg(MsgType.AllianceInvite_900, allianceInviteRt.build())
                            return@createACS
                        }

                        val player = session.player
                        if (player.allianceId == 0L) {
                            allianceInviteRt.rt = ResultCode.PARAMETER_ERROR.code
                            session.sendMsg(MsgType.AllianceInvite_900, allianceInviteRt.build())
                            return@createACS
                        }

                        val messageParam = LinkedList<String>()
                        messageParam.add(player.allianceShortName)
                        messageParam.add(player.name)
                        messageParam.add(player.allianceNickName)
                        messageParam.add(player.allianceShortName)
                        messageParam.add(player.allianceName)

                        // 邮件通知
                        val mailInfo = MailInfo(
                            TEXT_READ_LAN,
                            YJ_TITLE_21,
                            LinkedList(),
                            YJ_CONTENT_35,
                            messageParam
                        )
                        com.point18.slg2d.world.common.sendMail(
                            areaCache,
                            invitePlayerId,
                            mailInfo,
                            player,
                            com.point18.slg2d.common.constg.ALLIANCE_INVITE
                        )

                        val playerSession = fetchOnlinePlayerSession(areaCache, playerId)
                        if (playerSession != null) {
                            playerSession.inviteAlliance[invitePlayerId] = 1
                        }
                    }

                    session.sendMsg(MsgType.AllianceInvite_900, allianceInviteRt.build())
                }
            }

        } catch (e: Exception) {
            normalLog.error("AllianceInviteAskReq Error!", e)
            val allianceInviteRt = pb4client.AllianceInviteRt.newBuilder()
            allianceInviteRt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceInvite_900, allianceInviteRt.build())
        }
    }
}

// 处理加入联盟申请
fun dealJoinAlliance(session: PlayerSession, reqType: Int, allianceId: Long, reqPid: Long) {
    val areaCache = session.areaCache
    val playerId = session.playerId

    val dealJoinAllianceAskReq = DealJoinAllianceAskReq.newBuilder()
    dealJoinAllianceAskReq.reqType = reqType
    dealJoinAllianceAskReq.pid = playerId
    dealJoinAllianceAskReq.reqPid = reqPid

    if (reqType == 1) {
        // 如果是同意加入,那就先去HOME服拿他的准确数据 去公共服添加
        val msg = QueryPlayerInfoAskReq.newBuilder()
        areaCache.createACS(
            areaCache.worldActor.homeShardRegion,
            areaCache.fillW2HAskMsgHeader(reqPid) {
                it.setQueryPlayerInfoAskReq(msg)
            },
            World2HomeAskResp::class.java
        ) { hrt, err ->
            try {
                when {
                    err != null -> {
                        val crt = AllianceDealJoinReqRt.newBuilder()
                        crt.rt = ResultCode.ASK_ERROR1.code
                        crt.reqPlayerId = reqPid
                        crt.reqType = reqType
                        session.sendMsg(MsgType.AllianceDealJoinReq_806, crt.build())

                        // 解除玩家的入帮状态锁
                        val tell = UnLockJoinAllianceStateTell.newBuilder()
                        session.areaCache.tellHome(session.areaCache.fillW2HTellMsgHeader(reqPid) {
                            it.setUnLockJoinAllianceStateTell(tell)
                        })
                        return@createACS
                    }
                    hrt == null -> {
                        val crt = AllianceDealJoinReqRt.newBuilder()
                        crt.rt = ResultCode.ASK_ERROR2.code
                        crt.reqPlayerId = reqPid
                        crt.reqType = reqType
                        session.sendMsg(MsgType.AllianceDealJoinReq_806, crt.build())

                        // 解除玩家的入帮状态锁
                        val tell = UnLockJoinAllianceStateTell.newBuilder()
                        session.areaCache.tellHome(session.areaCache.fillW2HTellMsgHeader(reqPid) {
                            it.setUnLockJoinAllianceStateTell(tell)
                        })
                        return@createACS
                    }
                    else -> {
                        val rt = hrt.queryPlayerInfoAskRt
                        if (rt.rt != ResultCode.SUCCESS.code) {
                            // todo 失败了 执行回滚内容
                            val crt = AllianceDealJoinReqRt.newBuilder()
                            crt.reqPlayerId = reqPid
                            crt.reqType = reqType
                            crt.rt = rt.rt
                            session.sendMsg(MsgType.AllianceDealJoinReq_806, crt.build())

                            // 解除玩家的入帮状态锁
                            val tell = UnLockJoinAllianceStateTell.newBuilder()
                            session.areaCache.tellHome(session.areaCache.fillW2HTellMsgHeader(reqPid) {
                                it.setUnLockJoinAllianceStateTell(tell)
                            })
                            return@createACS
                        }

                        // 获取信息成功,去公共服进一步操作
                        val queryPlayerInfoVo = rt.queryPlayerInfoVo
                        val queryPlayerInfoAskVo = QueryPlayerInfoAskVo.newBuilder()
                        queryPlayerInfoAskVo.gamePltAreaNo = queryPlayerInfoVo.gamePltAreaNo
                        queryPlayerInfoAskVo.playerName = queryPlayerInfoVo.playerName
                        queryPlayerInfoAskVo.photoProtoId = queryPlayerInfoVo.photoProtoId
                        queryPlayerInfoAskVo.offTime = queryPlayerInfoVo.offTime
                        queryPlayerInfoAskVo.mapPltAreaNo = queryPlayerInfoVo.mapPltAreaNo
                        queryPlayerInfoAskVo.lastLoginTime = queryPlayerInfoVo.lastLoginTime
                        queryPlayerInfoAskVo.mapAreaNo = queryPlayerInfoVo.mapAreaNo
                        queryPlayerInfoAskVo.playerCastleLv = queryPlayerInfoVo.playerCastleLv
                        queryPlayerInfoAskVo.playerNickName = queryPlayerInfoVo.playerNickName
                        dealJoinAllianceAskReq.queryPlayerInfoAskVo = queryPlayerInfoAskVo.build()

                        dealJoinAllianceAtPublic(session, dealJoinAllianceAskReq, allianceId, reqType, reqPid)
                    }
                }

            } catch (e: Exception) {
                normalLog.error("DealJoinAllianceAskReq Error!", e)
                val crt = AllianceDealJoinReqRt.newBuilder()
                crt.reqPlayerId = reqPid
                crt.reqType = reqType
                crt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.AllianceDealJoinReq_806, crt.build())

                // 解除玩家的入帮状态锁
                val tell = UnLockJoinAllianceStateTell.newBuilder()
                session.areaCache.tellHome(session.areaCache.fillW2HTellMsgHeader(reqPid) {
                    it.setUnLockJoinAllianceStateTell(tell)
                })
            }
        }

    } else {
        // 如果是拒绝,那么不需要拿了 直接去公共服处理
        dealJoinAllianceAtPublic(session, dealJoinAllianceAskReq, allianceId, reqType, reqPid)
    }
}

fun dealJoinAllianceAtPublic(
    session: PlayerSession,
    dealJoinAllianceAskReq: DealJoinAllianceAskReq.Builder,
    allianceId: Long,
    reqType: Int,
    reqPid: Long
) {
    val areaCache = session.areaCache
    val playerId = session.playerId

    val crt = AllianceDealJoinReqRt.newBuilder()

    crt.reqPlayerId = reqPid
    crt.reqType = reqType
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.dealJoinAllianceAskReq = dealJoinAllianceAskReq.build()
        },
        World2PublicAskResp::class.java
    ) { resp, err ->
        try {
            when {
                err != null -> {
                    crt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceDealJoinReq_806, crt.build())
                    return@createACS
                }
                resp == null -> {
                    crt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceDealJoinReq_806, crt.build())
                    return@createACS
                }
                else -> {
                    val rt = resp.dealJoinAllianceAskRt
                    crt.rt = rt.rt
                    session.sendMsg(MsgType.AllianceDealJoinReq_806, crt.build())

                    // 推送给被拒绝的玩家让他删除申请记录
                    val tell = JoinNoSuccessTell.newBuilder()
                    tell.allianceId = allianceId
                    session.areaCache.tellHome(session.areaCache.fillW2HTellMsgHeader(reqPid) {
                        it.setJoinNoSuccessTell(tell)
                    })

                }
            }

        } catch (e: Exception) {
            normalLog.error("AllianceDealJoinReqRt Error!", e)
            crt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceDealJoinReq_806, crt.build())
        } finally {
            // 不管处理结果如何 都应该解除玩家的入帮状态锁
            val tell = UnLockJoinAllianceStateTell.newBuilder()
            session.areaCache.tellHome(session.areaCache.fillW2HTellMsgHeader(session.playerId) {
                it.setUnLockJoinAllianceStateTell(tell)
            })
        }

    }
}

// 查询可申请联盟列表
fun queryAllianceList(
    session: PlayerSession,
    mapAreaNo: Int,
    allianceName: String,
    allianceLan: Int
) {

    val req = QueryAllianceListAskReq.newBuilder()
    req.playerMapAreaNo = mapAreaNo
    req.allianceName = allianceName
    req.allianceLan = allianceLan

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(ALLIANCE_GENERAL_PID, playerId) {
            it.setQueryAllianceListAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val crt = AllianceQueryListRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceQueryList_808, crt.build())
                }
                askResp == null -> {
                    val crt = AllianceQueryListRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceQueryList_808, crt.build())
                }
                else -> {
                    val rt = askResp.queryAllianceListAskRt

                    val crt = AllianceQueryListRt.newBuilder()

                    for (alliance in rt.alliancesList) {
                        val allianceQueryListInfo = AllianceQueryListInfo.newBuilder()
                        allianceQueryListInfo.id = alliance.id
                        allianceQueryListInfo.name = alliance.name
                        allianceQueryListInfo.shortName = alliance.shortName
                        allianceQueryListInfo.reservePlayers = alliance.reservePlayers
                        allianceQueryListInfo.powerValue = alliance.powerValue

                        //查询是否存在请求
                        val player = session.areaCache.playerCache.findPlayerById(session.playerId)
                        var op = 0
                        if (player != null) {
                            if (player.joinAllianceReqs[alliance.id] != null) {
                                op = 1
                            }
                        }

                        allianceQueryListInfo.operate = op
                        allianceQueryListInfo.allianceLan = alliance.allianceLan
                        allianceQueryListInfo.canAddPower = alliance.canAddPower
                        allianceQueryListInfo.canReqPower = alliance.canReqPower
                        allianceQueryListInfo.flagColor = alliance.flagColor
                        allianceQueryListInfo.flagStyle = alliance.flagStyle
                        allianceQueryListInfo.flagEffect = alliance.flagEffect
                        allianceQueryListInfo.alliancePower = alliance.alliancePower
                        allianceQueryListInfo.giftLv = alliance.giftLv
                        allianceQueryListInfo.areaNo = alliance.areaNo

                        crt.addAlliances(allianceQueryListInfo.build())
                    }

                    crt.rt = rt.rt
                    session.sendMsg(MsgType.AllianceQueryList_808, crt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AllianceQueryListRt Error!", e)
            val crt = AllianceQueryListRt.newBuilder()
            crt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceQueryList_808, crt.build())
        }
    }
}

// 查询联盟日志
fun queryAllianceLog(session: PlayerSession, allianceId: Long) {

    val req = QueryAllianceLogAskReq.newBuilder()

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setQueryAllianceLogAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceQueryLogRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceQueryLog_820, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceQueryLogRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceQueryLog_820, rt2client.build())
                }
                else -> {
                    val rt = askResp.queryAllianceLogAskRt

                    val rt2client = pb4client.AllianceQueryLogRt.newBuilder()

                    val ls = LinkedList<pb4client.AllianceQueryLogInfo>()
                    for (log in rt.lList) {
                        val tmp = pb4client.AllianceQueryLogInfo.newBuilder()
                        tmp.dt = log.dt.toLong()
                        tmp.typ = log.typ
                        tmp.addAllLgs(log.lgsList)
                        rt2client.addLogs(tmp)
                    }

                    rt2client.rt = rt.rt
                    rt2client.addAllLogs(ls)

                    session.sendMsg(MsgType.AllianceQueryLog_820, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("QueryAllianceLogAskReq Error!", e)
            val rt2client = pb4client.AllianceQueryLogRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceQueryLog_820, rt2client.build())
        }
    }
}

// 通过联盟名查询联盟信息
fun queryAllianceInfo(session: PlayerSession, allianceId: Long) {
    val req = QueryAllianceInfoAskReq.newBuilder()

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setQueryAllianceInfoAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val crt = AllianceQueryInfoRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceQueryInfo_809, crt.build())
                    return@createACS
                }
                askResp == null -> {
                    val crt = AllianceQueryInfoRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceQueryInfo_809, crt.build())
                    return@createACS
                }
                else -> {
                    val rt = askResp.queryAllianceInfoAskRt

                    val crt = AllianceQueryInfoRt.newBuilder()

                    crt.rt = rt.rt
                    crt.id = rt.id
                    crt.name = rt.name
                    crt.shortName = rt.shortName
                    crt.playerId = rt.alliancePlayerId
                    crt.playerName = rt.playerName
                    crt.reservePlayers = rt.reservePlayers
                    crt.limitPlayers = rt.limitPlayers
                    crt.powerValue = rt.powerValue
                    crt.description = rt.description
                    crt.relationShipId = rt.relationShipId
                    crt.rewards = rt.rewards
                    crt.powerLimit = rt.powerLimit
                    crt.hasCountyCity = rt.hasCountyCity
                    crt.flagColor = rt.flagColor
                    crt.flagStyle = rt.flagStyle
                    crt.flagEffect = rt.flagEffect
                    crt.setRelationEndTime = rt.setRelationEndTime
                    crt.canAddLimit = rt.canAddLimit
                    crt.biaoyu = rt.biaoyu
                    crt.allianceLan = rt.allianceLan
                    crt.allianceInAreaNo = rt.allianceInAreaNo
                    crt.occupyWonderCount = rt.occupyWonderCount
                    crt.isApply = rt.isApply
                    crt.giftLv = rt.giftLv

                    // 在本世界服拿名次
                    val ranks = wpm.getWorldManagerInfoFromPublicManager().allianceRankInfo[AlliancePowerRank]
                    var fightRank = 0
                    if (ranks != null) {
                        for (index in ranks.indices) {
                            val a = ranks[index]
                            if (a.allianceId == rt.id) {
                                fightRank = index + 1
                                break
                            }

                        }
                    }
                    crt.fightRank = fightRank

                    session.sendMsg(MsgType.AllianceQueryInfo_809, crt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("QueryAllianceInfoAskReq Error!", e)
            val crt = AllianceQueryInfoRt.newBuilder()
            crt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceQueryInfo_809, crt.build())
        }
    }
}

// 查询申请加入联盟的玩家信息
fun queryAllianceReqList(session: PlayerSession, allianceId: Long) {
    val req = QueryAllianceReqListAskReq.newBuilder()

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setQueryAllianceReqListAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->
        try {
            when {
                err != null -> {
                    val allianceQueryReqListRt = AllianceQueryReqListRt.newBuilder()
                    allianceQueryReqListRt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceQueryReqList_810, allianceQueryReqListRt.build())
                }
                askResp == null -> {
                    val allianceQueryReqListRt = AllianceQueryReqListRt.newBuilder()
                    allianceQueryReqListRt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceQueryReqList_810, allianceQueryReqListRt.build())
                }
                else -> {
                    val rt = askResp.queryAllianceReqListAskRt

                    val allianceQueryReqListRt = AllianceQueryReqListRt.newBuilder()
                    allianceQueryReqListRt.rt = rt.rt
                    allianceQueryReqListRt.canAddPower = rt.canAddPower
                    allianceQueryReqListRt.powerLimit = rt.powerLimit

                    for (pp in rt.playersList) {
                        val p = AllianceQueryReqListInfo.newBuilder()
                        p.id = pp.id
                        p.name = pp.name
                        p.photoProtoId = pp.photoProtoId
                        p.fightValue = pp.fightValue
                        allianceQueryReqListRt.addPlayers(p.build())
                    }

                    session.sendMsg(MsgType.AllianceQueryReqList_810, allianceQueryReqListRt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("QueryAllianceReqListAskReq Error!", e)
            val allianceQueryReqListRt = AllianceQueryReqListRt.newBuilder()
            allianceQueryReqListRt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceQueryReqList_810, allianceQueryReqListRt.build())
        }
    }
}

// 玩家离开联盟成功之后 游戏服的数据修改处理
fun playerLeaveAlliance(
    areaCache: AreaCache,
    player: Player,
    session: PlayerSession?,
    allianceId: Long,
    oldPos: LinkedList<Int>
) {
    wpm.es.fire(
        areaCache,
        player.id,
        QUIT_ALLIANCE,
        InOrOffAlliance(session?.channelActor, allianceId, oldPos)
    )

    areaCache.playerCache.removePlayer2Alliance(player)

    player.allianceId = 0
    player.allianceName = ""
    player.allianceShortName = ""
    player.allianceRNum = 0
    player.flagColor = 0
    player.flagEffect = 0
    player.flagStyle = 0
    player.clearWrapPosition()

    for (giftVo in areaCache.myAllianceGiftCache.findMyAllianceGiftsByPlayerId(player.id)) {
        areaCache.myAllianceGiftCache.delAllianceGift(giftVo)
    }

    if (session != null) {
        val msgNotifier = createAllianceMissionGiftNumChangeNotifier(RESET_ALLIANCE_INFO, 0)
        msgNotifier.notice(session)
    }

    // 推送关系变化，客户端刷新玩家视野地块信息
    refreshRelation(areaCache, RELATION_CHANGE_QUIT_ALLIANCE, player, allianceId, "", "")

    player.quitPunishTime = 0

    // 玩家联盟发生变化,推送地图变化
    val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
    if (castle != null) {
        noticeCellUpdate(areaCache, castle.x, castle.y)
    }

    wpm.es.fire(
        areaCache,
        player.id,
        FINAL_QUIT_ALLIANCE,
        InOrOffAlliance(session?.channelActor, allianceId, oldPos)
    )

    // 通知到home清楚联盟数据
    val msg = LeaveAllianceTell.newBuilder()
    areaCache.tellHome(areaCache.fillW2HTellMsgHeader(player.id) {
        it.setLeaveAllianceTell(msg)
    })
}

// 更新联盟成员属性
fun updateAllianceMemberInfo(
    areaCache: AreaCache,
    allianceId: Long,
    playerId: Long,
    updateInfo: MutableMap<Int, String>
) {
    if (allianceId == 0L) {
        return
    }

    val req = UpdateAllianceMemberInfoAskReq.newBuilder()
    for ((t, v) in updateInfo) {
        val voBuilder = UpdateAllianceMemberInfoVo.newBuilder()
        voBuilder.updateType = t
        voBuilder.info = v
        req.addUpdates(voBuilder)
    }

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setUpdateAllianceMemberInfoAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->
        try {
            when {
                err != null -> {
                }
                askResp == null -> {
                }
                else -> {
                }
            }
        } catch (e: Exception) {
        }
    }
}

// 查询联盟成员信息
fun queryAllianceMember(session: PlayerSession, allianceId: Long) {
    val req = QueryAllianceMemberAskReq.newBuilder()

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setQueryAllianceMemberAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->
        try {
            when {
                err != null -> {
                    val crt = AllianceQueryPlayerRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceQueryPlayer_816, crt.build())
                }
                askResp == null -> {
                    val crt = AllianceQueryPlayerRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceQueryPlayer_816, crt.build())
                }
                else -> {
                    val rt = askResp.queryAllianceMemberAskRt
                    val players = LinkedList<AllianceQueryPlayerInfo>()
                    if (rt.rt == ResultCode.SUCCESS.code) {
                        for (info in rt.queryAllianceMemberVosList) {
                            val p = AllianceQueryPlayerInfo.newBuilder()
                            p.id = info.id
                            p.name = info.name
                            p.photoProtoId = info.photoProtoId
                            p.ctrbtTotal = info.ctrbtTotal
                            p.ctrbtWeek = info.ctrbtWeek
                            p.powerValue = info.powerValue
                            p.landX = info.landX
                            p.landY = info.landY
                            p.fightValue = info.fightValue
                            p.isOnline = info.isOnline
                            p.mertsWeek = 0
                            p.addAllPositions(info.positionsList)
                            p.tsLandX = info.tsLandX
                            p.tsLandY = info.tsLandY
                            p.occupiedFlag = info.occupiedFlag
                            p.makeOverTime = info.makeOverTime
                            p.nickName = info.nickName
                            p.lastOffTime = (info.lastOffTime / 1000).toInt()
                            p.mapAreaNo = info.mapAreaNo
                            p.monsterScore = info.monsterScore
                            p.lastGetMonsterScore = (info.lastGetMonsterScore / 1000).toInt()
                            p.weekResearchHonor = 0
                            p.weekHonor = 0
                            p.weekShalu = 0
                            p.weekXisheng = 0
                            p.weekPohuai = 0

                            players.add(p.build())
                        }
                    }

                    val crt = AllianceQueryPlayerRt.newBuilder()

                    crt.rt = rt.rt
                    crt.onlineQty = rt.onlineQty
                    crt.addAllPlayers(players)
                    crt.allianceId = allianceId
                    session.sendMsg(MsgType.AllianceQueryPlayer_816, crt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("QueryAllianceMemberAskReq Error!", e)
            val crt = AllianceQueryPlayerRt.newBuilder()
            crt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceQueryPlayer_816, crt.build())
        }
    }
}

// 领取联盟礼物
fun getAllianceGift(
    session: PlayerSession,
    allianceId: Long,
    addExp: Int,
    addBigExp: Int,
    getGiftIds: LinkedList<Long>,
    delGiftIds: LinkedList<Long>
) {
    val req = GetAllianceGiftAskReq.newBuilder()
    req.addExp = addExp
    req.addAllGiftIds(getGiftIds)
    req.addBigExp = addBigExp

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setGetAllianceGiftAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val crt = AllianceGiftGetRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceGiftGet_902, crt.build())
                }
                askResp == null -> {
                    val crt = AllianceGiftGetRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceGiftGet_902, crt.build())
                }
                else -> {
                    val rt = askResp.getAllianceGiftAskRt

                    for (id in delGiftIds) {
                        val delGifts = areaCache.myAllianceGiftCache.findMyAllianceGiftsByPlayerIdAndId(playerId, id)
                        if (delGifts != null) {
                            areaCache.myAllianceGiftCache.delAllianceGift(delGifts)
                        }
                    }

                    for (id in getGiftIds) {
                        val getGifts = areaCache.myAllianceGiftCache.findMyAllianceGiftsByPlayerIdAndId(playerId, id)
                        if (getGifts != null) {
                            areaCache.myAllianceGiftCache.delAllianceGift(getGifts)
                        }
                    }

                    val crt = AllianceGiftGetRt.newBuilder()
                    crt.rt = rt.rt
                    crt.addAllOnlyId(getGiftIds)
                    crt.addAllOnlyId(delGiftIds)
                    session.sendMsg(MsgType.AllianceGiftGet_902, crt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("GetAllianceGiftAskReq Error!", e)
            val crt = AllianceGiftGetRt.newBuilder()
            crt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceGiftGet_902, crt.build())
        }

    }
}

// 联盟踢人
fun removeAllianceMember(session: PlayerSession, allianceId: Long, removePlayerId: Long) {
    val req = RemoveAllianceMemberAskReq.newBuilder()
    req.removePlayerId = removePlayerId

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setRemoveAllianceMemberAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val crt = AllianceRemovePlayerRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceRemovePlayer_812, crt.build())
                }
                askResp == null -> {
                    val crt = AllianceRemovePlayerRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceRemovePlayer_812, crt.build())
                }
                else -> {
                    val rt = askResp.removeAllianceMemberAskRt

                    val crt = AllianceRemovePlayerRt.newBuilder()

                    crt.rt = rt.rt
                    crt.removePlayerId = removePlayerId
                    session.sendMsg(MsgType.AllianceRemovePlayer_812, crt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("RemoveAllianceMemberAskReq Error!", e)
            val crt = AllianceRemovePlayerRt.newBuilder()
            crt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceRemovePlayer_812, crt.build())
        }

    }
}

// 任命玩家联盟职位
fun resetAlliancePos(
    session: PlayerSession,
    allianceId: Long,
    posPlayerId: Long,
    posId: LinkedList<Int>
) {
    val req = ResetAlliancePosAskReq.newBuilder()
    req.addAllPosId(posId)
    req.posPlayerId = posPlayerId

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setResetAlliancePosAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val crt = AllianceSetPosRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceSetPos_814, crt.build())
                }
                askResp == null -> {
                    val crt = AllianceSetPosRt.newBuilder()
                    crt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceSetPos_814, crt.build())
                }
                else -> {
                    val rt = askResp.resetAlliancePosAskRt

                    val crt = AllianceSetPosRt.newBuilder()

                    wpm.es.fire(
                        areaCache, posPlayerId, ALLIANCE_SET_POSITION, AllianceSetPosition(
                            allianceId,
                            posId
                        )
                    )

                    crt.rt = rt.rt
                    crt.addAllPositions(posId)
                    crt.setPlayerId = posPlayerId
                    session.sendMsg(MsgType.AllianceSetPos_814, crt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("ResetAlliancePosAskReq Error!", e)
            val crt = AllianceSetPosRt.newBuilder()
            crt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceSetPos_814, crt.build())
        }

    }

}

// 联盟解散
fun allianceDismiss(session: PlayerSession, allianceId: Long) {
    val req = AllianceDismissAskReq.newBuilder()

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setAllianceDismissAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->
        try {
            when {
                err != null -> {
                    // 失败了 执行回滚内容
                    val allianceDismissRt = pb4client.AllianceDismissRt.newBuilder()
                    allianceDismissRt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceDismiss_817, allianceDismissRt.build())
                }
                askResp == null -> {
                    val allianceDismissRt = pb4client.AllianceDismissRt.newBuilder()
                    allianceDismissRt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceDismiss_817, allianceDismissRt.build())
                }
                else -> {
                    val rt = askResp.allianceDismissAskRt
                    val allianceDismissRt = pb4client.AllianceDismissRt.newBuilder()
                    allianceDismissRt.rt = rt.rt
                    session.sendMsg(MsgType.AllianceDismiss_817, allianceDismissRt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AllianceDismissAskReq Error!", e)
            val allianceDismissRt = pb4client.AllianceDismissRt.newBuilder()
            allianceDismissRt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceDismiss_817, allianceDismissRt.build())
        }

    }
}

// 联盟标记/集合
fun setAllianceMark(
    session: PlayerSession, allianceId: Long, markType: Int, x: Int, y: Int,
    pltAreaNo: Int, title: String, desp: String
) {
    val req = SetAllianceMarkAskReq.newBuilder()
    req.markType = markType // 标记类型
    req.x = x // X
    req.y = y // Y
    req.pltAreaNo = pltAreaNo // 服务器编号
    req.title = title // 名字
    req.desp = desp // 介绍

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setSetAllianceMarkAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->
        try {
            when {
                err != null -> {
                    val allianceSetMarkRt = pb4client.AllianceSetMarkRt.newBuilder()
                    allianceSetMarkRt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceSetMark_821, allianceSetMarkRt.build())
                }
                askResp == null -> {
                    val allianceSetMarkRt = pb4client.AllianceSetMarkRt.newBuilder()
                    allianceSetMarkRt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceSetMark_821, allianceSetMarkRt.build())
                }
                else -> {
                    val rt = askResp.setAllianceMarkAskRt

                    val allianceSetMarkRt = pb4client.AllianceSetMarkRt.newBuilder()
                    allianceSetMarkRt.rt = rt.rt
                    allianceSetMarkRt.endTime = (rt.endTime / 1000).toInt()
                    session.sendMsg(MsgType.AllianceSetMark_821, allianceSetMarkRt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("SetAllianceMarkAskReq Error!", e)
            val allianceSetMarkRt = pb4client.AllianceSetMarkRt.newBuilder()
            allianceSetMarkRt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceSetMark_821, allianceSetMarkRt.build())
        }
    }
}

// 删除联盟标记/集合
fun removeAllianceMark(session: PlayerSession, allianceId: Long, markId: Long) {

    val req = RemoveAllianceMarkAskReq.newBuilder()
    req.markId = markId

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setRemoveAllianceMarkAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = AllianceRemoveMarkRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceRemoveMark_823, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = AllianceRemoveMarkRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceRemoveMark_823, rt2client.build())
                }
                else -> {
                    val rt = askResp.removeAllianceMarkAskRt

                    val rt2client = AllianceRemoveMarkRt.newBuilder()
                    rt2client.rt = rt.rt
                    session.sendMsg(MsgType.AllianceRemoveMark_823, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("RemoveAllianceMarkAskReq Error!", e)
            val rt2client = AllianceRemoveMarkRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceRemoveMark_823, rt2client.build())
        }

    }

}

// 联盟标记/集合
fun setAllianceDescpt(session: PlayerSession, allianceId: Long, desp: String, despType: Int) {
    val req = SetAllianceDescptAskReq.newBuilder()
    req.desp = desp
    req.despType = despType

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setSetAllianceDescptAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceSetDescptRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceSetDescpt_813, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceSetDescptRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceSetDescpt_813, rt2client.build())
                }
                else -> {
                    val rt = askResp.setAllianceDescptAskRt
                    val rt2client = pb4client.AllianceSetDescptRt.newBuilder()
                    rt2client.rt = rt.rt
                    rt2client.description = desp
                    rt2client.desType = despType

                    session.sendMsg(MsgType.AllianceSetDescpt_813, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("SetAllianceDescptAskReq Error!", e)
            val rt2client = pb4client.AllianceSetDescptRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceSetDescpt_813, rt2client.build())
        }
    }
}

fun allianceQueryTopic(session: PlayerSession, allianceId: Long) {

    val req = AllianceQueryTopicAskReq.newBuilder()

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setAllianceQueryTopicAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceQueryTopicRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceQueryTopic_890, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceQueryTopicRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceQueryTopic_890, rt2client.build())
                }
                else -> {
                    val rt = askResp.allianceQueryTopicAskRt

                    val rt2client = pb4client.AllianceQueryTopicRt.newBuilder()
                    rt2client.rt = rt.rt

                    for (t in rt.topicsList) {
                        val allianceTopicInfo = AllianceTopicInfo.newBuilder()
                        allianceTopicInfo.topicId = t.topicId
                        allianceTopicInfo.type = t.type
                        allianceTopicInfo.playerId = t.playerId
                        allianceTopicInfo.playerName = t.playerName
                        allianceTopicInfo.photoProtoId = t.photoProtoId
                        allianceTopicInfo.title = t.title
                        allianceTopicInfo.lastAt = t.lastAt
                        allianceTopicInfo.isRead = t.isRead
                        allianceTopicInfo.isSign = t.isSign
                        rt2client.addTopics(allianceTopicInfo)
                    }

                    session.sendMsg(MsgType.AllianceQueryTopic_890, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AllianceQueryTopicAskReq Error!", e)
            val rt2client = pb4client.AllianceQueryTopicRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceQueryTopic_890, rt2client.build())
        }
    }

}

// 发布联盟邮件主题
fun alliancePublishTopic(
    session: PlayerSession,
    allianceId: Long,
    t: Int,
    title: String,
    message: String
) {

    val req = AlliancePublishTopicAskReq.newBuilder()
    req.type = t
    req.title = title
    req.message = message

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setAlliancePublishTopicAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->

        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AlliancePublishTopicRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AlliancePublishTopic_891, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AlliancePublishTopicRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AlliancePublishTopic_891, rt2client.build())
                }
                else -> {
                    val rt = askResp.alliancePublishTopicAskRt

                    val rt2client = pb4client.AlliancePublishTopicRt.newBuilder()

                    rt2client.rt = rt.rt

                    val allianceTopicInfo = AllianceTopicInfo.newBuilder()
                    allianceTopicInfo.topicId = rt.topic.topicId
                    allianceTopicInfo.type = rt.topic.type
                    allianceTopicInfo.playerId = rt.topic.playerId
                    allianceTopicInfo.playerName = rt.topic.playerName
                    allianceTopicInfo.photoProtoId = rt.topic.photoProtoId
                    allianceTopicInfo.title = rt.topic.title
                    allianceTopicInfo.lastAt = rt.topic.lastAt
                    allianceTopicInfo.isRead = rt.topic.isRead
                    allianceTopicInfo.isSign = rt.topic.isSign
                    rt2client.topic = allianceTopicInfo.build()

                    session.sendMsg(MsgType.AlliancePublishTopic_891, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AlliancePublishTopicAskReq Error!", e)
            val rt2client = pb4client.AlliancePublishTopicRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AlliancePublishTopic_891, rt2client.build())
        }

    }

}

// 对联盟邮件主题进行回复
fun allianceTopicReply(
    session: PlayerSession,
    allianceId: Long,
    topicId: Long,
    message: String
) {

    val req = AllianceTopicReplyAskReq.newBuilder()
    req.topicId = topicId
    req.message = message

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setAllianceTopicReplyAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceTopicReplyRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceTopicReply_892, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceTopicReplyRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceTopicReply_892, rt2client.build())
                }
                else -> {
                    val rt = askResp.allianceTopicReplyAskRt

                    val rt2client = pb4client.AllianceTopicReplyRt.newBuilder()
                    rt2client.rt = rt.rt

                    val l = AllianceReplyInfo.newBuilder()
                    l.replyId = rt.reply.replyId
                    l.playerId = rt.reply.playerId
                    l.playerName = rt.reply.playerName
                    l.playerShortName = rt.reply.playerShortName
                    l.photoProtoId = rt.reply.photoProtoId
                    l.message = rt.reply.message
                    l.replyAt = rt.reply.replyAt
                    l.vipLv = rt.reply.vipLv
                    l.curentPos = rt.reply.curentPos
                    l.addAllPositions(rt.reply.positionsList)

                    rt2client.reply = l.build()

                    session.sendMsg(MsgType.AllianceTopicReply_892, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AllianceTopicReplyAskReq Error!", e)
            val rt2client = pb4client.AllianceTopicReplyRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceTopicReply_892, rt2client.build())
        }
    }

}

// 对联盟邮件主题进行回复
fun allianceTopicGetReply(
    session: PlayerSession,
    allianceId: Long,
    topicId: Long,
    lastId: Long
) {
    val req = AllianceTopicGetReplyAskReq.newBuilder()
    req.topicId = topicId
    req.replyId = lastId

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setAllianceTopicGetReplyAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceTopicGetReplyRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceTopicGetReply_893, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceTopicGetReplyRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceTopicGetReply_893, rt2client.build())
                }
                else -> {
                    val rt = askResp.allianceTopicGetReplyAskRt

                    val rt2client = pb4client.AllianceTopicGetReplyRt.newBuilder()

                    rt2client.rt = rt.rt

                    val ll = LinkedList<AllianceReplyInfo>()
                    for (re in rt.replysList) {
                        val l = AllianceReplyInfo.newBuilder()

                        l.replyId = re.replyId
                        l.playerId = re.playerId
                        l.playerName = re.playerName
                        l.playerShortName = re.playerShortName
                        l.photoProtoId = re.photoProtoId
                        l.message = re.message
                        l.replyAt = re.replyAt
                        l.vipLv = re.vipLv
                        l.curentPos = re.curentPos
                        l.addAllPositions(re.positionsList)

                        ll.add(l.build())
                    }
                    rt2client.addAllReplys(ll)
                    rt2client.topicId = topicId

                    session.sendMsg(MsgType.AllianceTopicGetReply_893, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AllianceTopicGetReplyAskReq Error!", e)
            val rt2client = pb4client.AllianceTopicGetReplyRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceTopicGetReply_893, rt2client.build())
        }
    }
}

// 对联盟邮件del
fun allianceTopicDelete(session: PlayerSession, allianceId: Long, topicId: Long) {

    val req = AllianceTopicDeleteAskReq.newBuilder()
    req.topicId = topicId

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setAllianceTopicDeleteAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceTopicDeleteRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceTopicDelete_894, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceTopicDeleteRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceTopicDelete_894, rt2client.build())
                }
                else -> {
                    val rt = askResp.allianceTopicDeleteAskRt

                    val rt2client = pb4client.AllianceTopicDeleteRt.newBuilder()
                    rt2client.topicId = topicId
                    rt2client.rt = rt.rt

                    session.sendMsg(MsgType.AllianceTopicDelete_894, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AllianceTopicDeleteAskReq Error!", e)
            val rt2client = pb4client.AllianceTopicDeleteRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceTopicDelete_894, rt2client.build())
        }
    }

}

// 打开联盟外交界面
fun openAllianceWaijiao(session: PlayerSession, allianceId: Long) {

    val req = OpenAllianceWaijiaoAskReq.newBuilder()

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setOpenAllianceWaijiaoAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceOpenWaijiaoRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceOpenWaijiao_895, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceOpenWaijiaoRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceOpenWaijiao_895, rt2client.build())
                }
                else -> {
                    val rt = askResp.openAllianceWaijiaoAskRt

                    val player = areaCache.playerCache.findPlayerById(playerId)
                    if (player == null) {
                        assert(false) { "player cannot be found,playerID: $playerId" }
                        return@createACS
                    }
                    val rt2client = pb4client.AllianceOpenWaijiaoRt.newBuilder()
                    rt2client.rt = rt.rt

                    for (aa in rt.allianceWaijiaoInfosList) {
                        val allianceWaijiaoInfo = AllianceWaijiaoInfo.newBuilder()
                        allianceWaijiaoInfo.flagColor = aa.flagColor
                        allianceWaijiaoInfo.flagStyle = aa.flagStyle
                        allianceWaijiaoInfo.flagEffect = aa.flagEffect
                        allianceWaijiaoInfo.aid = aa.aid
                        allianceWaijiaoInfo.name = aa.name
                        allianceWaijiaoInfo.shortName = aa.shortName
                        allianceWaijiaoInfo.playerId = aa.playerId
                        allianceWaijiaoInfo.playerName = aa.playerName
                        allianceWaijiaoInfo.playerPositions = aa.playerPositions
                        allianceWaijiaoInfo.createTime = aa.createTime
                        allianceWaijiaoInfo.waijiaoInfo = aa.waijiaoInfo
                        allianceWaijiaoInfo.mapPltAreaNo = aa.mapPltAreaNo
                        allianceWaijiaoInfo.photoProtoId = aa.photoProtoId
                        allianceWaijiaoInfo.nickName = aa.nickName
                        allianceWaijiaoInfo.waijiaoId = aa.waijiaoId

                        rt2client.addAllianceWaijiaoInfos(allianceWaijiaoInfo)
                    }

                    rt2client.todayNum = player.waijiaoCount
                    session.sendMsg(MsgType.AllianceOpenWaijiao_895, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("OpenAllianceWaijiaoAskReq Error!", e)
            val rt2client = pb4client.AllianceOpenWaijiaoRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceOpenWaijiao_895, rt2client.build())
        }
    }
}

// 写外交界面
fun writeAllianceWaijiao(
    session: PlayerSession, player: Player, targetAllianceId: Long
    , waijiaoInfo: String, areaNo: Int, playerName: String, photoProtoId: Int, nickName: String
) {

    val req = WriteAllianceWaijiaoAskReq.newBuilder()
    req.areaNo = areaNo
    req.myAllianceId = player.allianceId
    req.flagColor = player.flagColor
    req.flagStyle = player.flagStyle
    req.flagEffect = player.flagEffect
    req.aName = player.allianceName
    req.asName = player.allianceShortName
    req.waijiaoInfo = waijiaoInfo
    req.playerName = playerName
    req.photoProtoId = photoProtoId
    req.nickName = nickName
    req.playerPos = toJson(player.alliancePosMap)

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(targetAllianceId, playerId) {
            it.setWriteAllianceWaijiaoAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = pb4client.WriteAllianceWaijiaoRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.WriteAllianceWaijiao_896, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.WriteAllianceWaijiaoRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.WriteAllianceWaijiao_896, rt2client.build())
                }
                else -> {
                    val rt = askResp.writeAllianceWaijiaoAskRt

                    val player = areaCache.playerCache.findPlayerById(playerId)
                    if (player == null) {
                        assert(false) { "player cannot be found,id:$playerId" }
                        return@createACS
                    }
                    // RPC返回失败 ,把次数还给他
                    player.waijiaoCount--
                    if (player.waijiaoCount < 0) {
                        player.waijiaoCount = 0

                    }

                    val rt2client = pb4client.WriteAllianceWaijiaoRt.newBuilder()
                    rt2client.rt = rt.rt

                    val allianceWaijiaoInfo = AllianceWaijiaoInfo.newBuilder()

                    allianceWaijiaoInfo.flagColor = rt.allianceWaijiaoInfos.flagColor
                    allianceWaijiaoInfo.flagStyle = rt.allianceWaijiaoInfos.flagStyle
                    allianceWaijiaoInfo.flagEffect = rt.allianceWaijiaoInfos.flagEffect
                    allianceWaijiaoInfo.aid = rt.allianceWaijiaoInfos.aid
                    allianceWaijiaoInfo.name = rt.allianceWaijiaoInfos.name
                    allianceWaijiaoInfo.shortName = rt.allianceWaijiaoInfos.shortName
                    allianceWaijiaoInfo.playerId = rt.allianceWaijiaoInfos.playerId
                    allianceWaijiaoInfo.playerName = rt.allianceWaijiaoInfos.playerName
                    allianceWaijiaoInfo.playerPositions = rt.allianceWaijiaoInfos.playerPositions
                    allianceWaijiaoInfo.createTime = rt.allianceWaijiaoInfos.createTime
                    allianceWaijiaoInfo.waijiaoInfo = rt.allianceWaijiaoInfos.waijiaoInfo
                    allianceWaijiaoInfo.mapPltAreaNo = rt.allianceWaijiaoInfos.mapPltAreaNo
                    allianceWaijiaoInfo.photoProtoId = rt.allianceWaijiaoInfos.photoProtoId
                    allianceWaijiaoInfo.nickName = rt.allianceWaijiaoInfos.nickName
                    allianceWaijiaoInfo.waijiaoId = rt.allianceWaijiaoInfos.waijiaoId

                    rt2client.allianceWaijiaoInfos = allianceWaijiaoInfo.build()

                    session.sendMsg(MsgType.WriteAllianceWaijiao_896, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("WriteAllianceWaijiaoAskReq Error!", e)
            val rt2client = pb4client.WriteAllianceWaijiaoRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.WriteAllianceWaijiao_896, rt2client.build())
        }

    }
}

// 取消联盟申请
fun allianceJoinCancel(session: PlayerSession, allianceId: Long) {
    val req = AllianceJoinCancelAskReq.newBuilder()

    val areaCache = session.areaCache
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, session.playerId) {
            it.setAllianceJoinCancelAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val allianceJoinCancelRt = pb4client.AllianceJoinCancelRt.newBuilder()
                    allianceJoinCancelRt.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceJoinCancel_805, allianceJoinCancelRt.build())
                }
                askResp == null -> {
                    val allianceJoinCancelRt = pb4client.AllianceJoinCancelRt.newBuilder()
                    allianceJoinCancelRt.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceJoinCancel_805, allianceJoinCancelRt.build())
                }
                else -> {
                    val rt = askResp.allianceJoinCancelAskRt

                    val allianceJoinCancelRt = pb4client.AllianceJoinCancelRt.newBuilder()

                    allianceJoinCancelRt.rt = rt.rt
                    allianceJoinCancelRt.allianceId = allianceId

                    val player = session.areaCache.playerCache.findPlayerById(session.playerId)
                    if (player != null) {
                        player.joinAllianceReqs.remove(allianceId)
                    }

                    session.sendMsg(MsgType.AllianceJoinCancel_805, allianceJoinCancelRt.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AllianceJoinCancelAskReq Error!", e)
            val allianceJoinCancelRt = pb4client.AllianceJoinCancelRt.newBuilder()
            allianceJoinCancelRt.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceJoinCancel_805, allianceJoinCancelRt.build())
        }

    }

}

//
// 设置允许申请联盟的势力最低值设置允许申请联盟的势力最低值
fun setAlliancePowerLimit(
    session: PlayerSession,
    allianceId: Long,
    power: Long,
    canAddPower: Long
) {
    val req = SetAlliancePowerLimitAskReq.newBuilder()
    req.power = power
    req.canAddPower = canAddPower

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setSetAlliancePowerLimitAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->

        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceSetPowerLimitRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.SetPowerLimit_807, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceSetPowerLimitRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.SetPowerLimit_807, rt2client.build())
                }
                else -> {
                    val rt = askResp.setAlliancePowerLimitAskRt
                    val rt2client = pb4client.AllianceSetPowerLimitRt.newBuilder()
                    rt2client.rt = rt.rt
                    rt2client.powerLimit = req.power
                    rt2client.canAddPower = req.canAddPower

                    session.sendMsg(MsgType.SetPowerLimit_807, rt2client.build())
                }
            }

        } catch (e: Exception) {
            normalLog.error("SetAlliancePowerLimitAskReq Error!", e)
            val rt2client = pb4client.AllianceSetPowerLimitRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.SetPowerLimit_807, rt2client.build())
        }

    }
}

// 罢免职位
fun allianceRecallPos(session: PlayerSession, allianceId: Long, setPid: Long, posId: Int) {

    val req = AllianceRecallPosAskReq.newBuilder()
    req.posId = posId
    req.setPid = setPid

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setAllianceRecallPosAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->

        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceRecallPosRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceRecallPos_835, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceRecallPosRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceRecallPos_835, rt2client.build())
                }
                else -> {
                    val rt = askResp.allianceRecallPosAskRt

                    val rt2client = pb4client.AllianceRecallPosRt.newBuilder()
                    val poss = asList(posId)
                    rt2client.rt = rt.rt
                    rt2client.setPlayerId = setPid
                    rt2client.addAllPositions(poss)

                    session.sendMsg(MsgType.AllianceRecallPos_835, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AllianceRecallPosAskReq Error!", e)
            val rt2client = pb4client.AllianceRecallPosRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceRecallPos_835, rt2client.build())
        }

    }
}

// 打开联盟礼物界面
fun openAllianceGift(session: PlayerSession, allianceId: Long) {

    val areaCache = session.areaCache
    val playerId = session.playerId

    val req = OpenAllianceGiftAskReq.newBuilder()

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setOpenAllianceGiftAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->

        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceGiftOpenRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceGiftOpen_901, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.AllianceGiftOpenRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceGiftOpen_901, rt2client.build())
                }
                else -> {
                    val rt = askResp.openAllianceGiftAskRt

                    val rt2client = pb4client.AllianceGiftOpenRt.newBuilder()
                    rt2client.rt = rt.rt

                    if (rt.rt == ResultCode.SUCCESS.code) {
                        val myAllianceGifts = areaCache.myAllianceGiftCache.findMyAllianceGiftsByPlayerId(playerId)
                        for (eachGift in myAllianceGifts) {
                            val tmpVO = AllianceGiftVo.newBuilder()
                            tmpVO.onlyId = eachGift.id
                            tmpVO.giftId = eachGift.giftId
                            tmpVO.isGet = eachGift.isGet
                            tmpVO.giftInfo = eachGift.giftInfo
                            tmpVO.overTime = (eachGift.overTime / 1000).toInt()
                            rt2client.addAllianceGiftVos(tmpVO)
                        }
                    }

                    if (rt.allianceBigGiftVo != null) {
                        val allianceBigGiftVo = pb4client.AllianceBigGiftVo.newBuilder()
                        allianceBigGiftVo.bigGiftId = rt.allianceBigGiftVo.bigGiftId
                        allianceBigGiftVo.bigGiftExp = rt.allianceBigGiftVo.bigGiftExp
                        allianceBigGiftVo.giftLv = rt.allianceBigGiftVo.giftLv
                        allianceBigGiftVo.giftExp = rt.allianceBigGiftVo.giftExp
                        rt2client.setAllianceBigGiftVo(allianceBigGiftVo)
                    }

                    session.sendMsg(MsgType.AllianceGiftOpen_901, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("OpenAllianceGiftAskReq Error!", e)
            val rt2client = pb4client.AllianceGiftOpenRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceGiftOpen_901, rt2client.build())
        }
    }
}

// 查询联盟内部数据排行榜
fun queryInAllianceRank(session: PlayerSession, allianceId: Long, rankType: Int) {

    val req = QueryInAllianceRankReq.newBuilder()
    req.rankType = rankType

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setQueryInAllianceRankReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->

        try {
            when {
                err != null -> {
                    val rt2client = pb4client.QueryInAllianceRankRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.QueryInAllianceRank_915, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.QueryInAllianceRankRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.QueryInAllianceRank_915, rt2client.build())
                }
                else -> {
                    val rt = askResp.queryInAllianceRankRt

                    val rt2client = pb4client.QueryInAllianceRankRt.newBuilder()

                    rt2client.rt = rt.rt


                    for (qq in rt.queryInAllianceRankVosList) {
                        val qar = QueryInAllianceRankVo.newBuilder()
                        qar.playerId = qq.playerId
                        qar.playerName = qq.playerName
                        qar.playerNickName = qq.playerNickName
                        qar.areaNo = qq.areaNo
                        qar.photo = qq.photo
                        qar.power = qq.power
                        qar.addAllValue(qq.valueList)
                        qar.addAllPos(qq.posList)
                        qar.curentPos = 0
                        rt2client.addQueryInAllianceRankVos(qar)
                    }
                    session.sendMsg(MsgType.QueryInAllianceRank_915, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("QueryInAllianceRankReq Error!", e)
            val rt2client = pb4client.QueryInAllianceRankRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.QueryInAllianceRank_915, rt2client.build())
        }
    }
}

// 查看联盟排行榜
fun queryAllianceRank(
    session: PlayerSession, allianceId: Long, page: Int, num: Int,
    rankType: Int, areaNo: Int
) {

    val req = QueryAllianceRankAskReq.newBuilder()
    req.num = num
    req.rankType = rankType
    req.areaNo = areaNo
    req.page = page

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setQueryAllianceRankAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->
        try {
            when {
                err != null -> {
                    val rt2client = QueryAllianceRankRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.QueryAllianceRank_916, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = QueryAllianceRankRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.QueryAllianceRank_916, rt2client.build())
                }
                else -> {
                    val rt = askResp.queryAllianceRankAskRt

                    val rt2client = pb4client.QueryAllianceRankRt.newBuilder()

                    rt2client.rt = rt.rt
                    rt2client.page = page


                    for (r in rt.queryAllianceRankVosList) {
                        val queryAllianceRankVo = QueryAllianceRankVo.newBuilder()

                        queryAllianceRankVo.allianceName = r.allianceName
                        queryAllianceRankVo.allianceShortName = r.allianceShortName
                        queryAllianceRankVo.allianceId = r.allianceId
                        queryAllianceRankVo.flagColor = r.flagColor
                        queryAllianceRankVo.flagStyle = r.flagStyle
                        queryAllianceRankVo.flagEffect = r.flagEffect
                        queryAllianceRankVo.value = r.value

                        rt2client.addQueryAllianceRankVos(queryAllianceRankVo)
                    }

                    rt2client.myAllianceRank = rt.myAllianceRank
                    rt2client.myAllianceScore = rt.myAllianceScore

                    session.sendMsg(MsgType.QueryAllianceRank_916, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("QueryAllianceRankAskReq Error!", e)
            val rt2client = QueryAllianceRankRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.QueryAllianceRank_916, rt2client.build())
        }
    }
}

// 查看联盟排行榜首页
fun queryAllianceRankFirst(session: PlayerSession, allianceId: Long, areaNo: Int) {
    val req = QueryAllianceRankFirstAskReq.newBuilder()
    req.areaNo = areaNo

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setQueryAllianceRankFirstAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->

        try {
            when {
                err != null -> {
                    val rt2client = pb4client.QueryAllianceRankFirstRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.QueryAllianceRankFirst_500, rt2client.build())
                }
                askResp == null -> {
                    val rt2client = pb4client.QueryAllianceRankFirstRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.QueryAllianceRankFirst_500, rt2client.build())
                }
                else -> {
                    val rt = askResp.queryAllianceRankFirstAskRt

                    val rt2client = pb4client.QueryAllianceRankFirstRt.newBuilder()

                    rt2client.rt = rt.rt
                    val rs = LinkedList<QueryAllianceRankFirstVo>()
                    for (qrv in rt.queryAllianceRankVosList) {
                        val r = QueryAllianceRankFirstVo.newBuilder()
                        r.rankType = qrv.rankType

                        val queryAllianceRankVo = QueryAllianceRankVo.newBuilder()
                        queryAllianceRankVo.allianceName = qrv.queryAllianceRankVos.allianceName
                        queryAllianceRankVo.allianceShortName = qrv.queryAllianceRankVos.allianceShortName
                        queryAllianceRankVo.allianceId = qrv.queryAllianceRankVos.allianceId
                        queryAllianceRankVo.flagColor = qrv.queryAllianceRankVos.flagColor
                        queryAllianceRankVo.flagStyle = qrv.queryAllianceRankVos.flagStyle
                        queryAllianceRankVo.flagEffect = qrv.queryAllianceRankVos.flagEffect
                        queryAllianceRankVo.value = qrv.queryAllianceRankVos.value

                        r.queryAllianceRankVos = queryAllianceRankVo.build()


                        rs.add(r.build())
                    }

                    rt2client.addAllQueryAllianceRankFirstVos(rs)
                    session.sendMsg(MsgType.QueryAllianceRankFirst_500, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("QueryAllianceRankFirstAskReq Error!", e)
            val rt2client = pb4client.QueryAllianceRankFirstRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.QueryAllianceRankFirst_500, rt2client.build())
        }
    }
}

// 对联盟邮件主题进行回复
fun allianceTopicSign(session: PlayerSession, allianceId: Long, topicId: Long) {

    val req = AllianceTopicSignAskReq.newBuilder()
    req.topicId = topicId

    val areaCache = session.areaCache
    val playerId = session.playerId
    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setAllianceTopicSignAskReq(req)
        },
        AllianceTopicSignAskRt::class.java
    ) { rt, err ->

        try {
            when {
                err != null -> {
                    val rt2client = pb4client.AllianceTopicSignRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.AllianceTopicSign_917, rt2client.build())
                }
                rt == null -> {
                    val rt2client = pb4client.AllianceTopicSignRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.AllianceTopicSign_917, rt2client.build())
                }
                else -> {
                    val rt2client = pb4client.AllianceTopicSignRt.newBuilder()

                    rt2client.rt = rt.rt

                    session.sendMsg(MsgType.AllianceTopicSign_917, rt2client.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("AllianceTopicSignAskReq Error!", e)
            val rt2client = pb4client.AllianceTopicSignRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.AllianceTopicSign_917, rt2client.build())
        }
    }

}

// 同步奇观信息到pub
fun updateWonderInfo(areaCache: AreaCache, changeType: Int, allianceId: Long, wonder: Wonder) {
    val req = UpdateWonderInfoAskReq.newBuilder()

    req.changeType = changeType
    req.wonderProtoId = wonder.wonderProtoId
    req.status = wonder.status
    req.warStartTime = wonder.warStartTime
    req.warFinishTime = wonder.warFinishTime
    req.positionInfo = toJson(wonder.officeMap)

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, 0) {
            it.setUpdateWonderInfoAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { prt, err ->
        try {
            when {
                err != null -> {
                    // 失败了 执行回滚内容
                }
                prt == null -> {

                }
                else -> {

                }
            }
        } catch (e: Exception) {
        }

    }
}

//查询占领的奇观
fun queryOccupyWonder(session: PlayerSession, allianceId: Long) {

    val req = QueryOccupyWonderAskReq.newBuilder()
    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setQueryOccupyWonderAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { prt, err ->
        try {
            when {
                err != null -> {
                    // 失败了 执行回滚内容
                    val rt2client = pb4client.QueryOccupyWonderRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.QueryOccupyWonder_918, rt2client.build())
                }
                prt == null -> {
                    val rt2client = pb4client.QueryOccupyWonderRt.newBuilder()
                    rt2client.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.QueryOccupyWonder_918, rt2client.build())
                }
                else -> {
                    val rt = prt.queryOccupyWonderAskRt

                    val rt2client = pb4client.QueryOccupyWonderRt.newBuilder()

                    rt2client.rt = rt.rt

                    rt2client.addAllOccupyWonderInfo(rt.occupyWonderInfoList)

                    session.sendMsg(MsgType.QueryOccupyWonder_918, rt2client.build())
                }
            }

        } catch (e: Exception) {
            normalLog.error("QueryOccupyWonderAskReq Error!", e)
            val rt2client = pb4client.QueryOccupyWonderRt.newBuilder()
            rt2client.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.QueryOccupyWonder_918, rt2client.build())
        }
    }
}

// 接收联盟礼物
fun receiveAllianceGift(
    areaCache: AreaCache,
    allianceId: Long,
    giftMap: HashMap<Int, Int>
) {

    val req = ReceiveAllianceGiftAskReq.newBuilder()
    for ((gId, gNum) in giftMap) {
        val receiveAllianceGiftAskReqVo = ReceiveAllianceGiftAskReqVo.newBuilder()
        receiveAllianceGiftAskReqVo.giftId = gId
        receiveAllianceGiftAskReqVo.giftNum = gNum
        req.addGiftMap(receiveAllianceGiftAskReqVo)
    }

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, 0L) {
            it.setReceiveAllianceGiftAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { rt, err ->
        try {
            when {
                err != null -> {
                    // 失败了 执行回滚内容
                }
                rt == null -> {

                }
                else -> {

                }
            }
        } catch (e: Exception) {
        }
    }
}

// 联盟活动的积分增加
fun allianceActivityScoreAdd(
    areaCache: AreaCache,
    allianceId: Long,
    playerId: Long,
    addScore: Long,
    conditionType: Int
) {

    val req = AllianceActivityScoreAddAskReq.newBuilder()
    req.addScore = addScore
    req.conditionType = conditionType

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setAllianceActivityScoreAddAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { rt, err ->
        try {
            when {
                err != null -> {
                    // 失败了 执行回滚内容
                }
                rt == null -> {

                }
                else -> {
                    // 添加真实的积分
                    if (rt.allianceActivityScoreAddAskRt.successAddScore != 0L) {
                        val target = areaCache.targetCache.findMyTargetByPlayerId(playerId)
                        if (target != null) {
                            val score =
                                target.activityScoreMap.getOrPut(ALLIANCE_ACTIVITY) { 0 } + rt.allianceActivityScoreAddAskRt.successAddScore
                            target.activityScoreMap[ALLIANCE_ACTIVITY] = score.toInt()
                        }

                        wpm.es.fire(
                            areaCache, playerId, ACTIVITY_SCORE_OVER,
                            ActivityScoreOver(conditionType)
                        )
                    }
                }
            }
        } catch (e: Exception) {
        }

    }
}

//查询活动历史
fun selectActivityHistory(
    session: PlayerSession,
    allianceId: Long
) {
    val req = SelectActivityHistoryReq.newBuilder()
    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setSelectActivityHistoryReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->

        try {
            when {
                err != null -> {
                    val rtBuilder = pb4client.SelectActivityHistoryRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.SelectActivityHistory_1332, rtBuilder.build())
                }
                askResp == null -> {
                    val rtBuilder = pb4client.SelectActivityHistoryRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.SelectActivityHistory_1332, rtBuilder.build())
                }
                else -> {
                    val rt = askResp.selectActivityHistoryRt
                    val rtBuilder = pb4client.SelectActivityHistoryRt.newBuilder()
                    rtBuilder.rt = rt.rt

                    for (joinActivity in rt.historyInfosList) {
                        val ahVo = pb4client.ActivityHistoryVo.newBuilder()
                        ahVo.activityProtoId = joinActivity.activityProtoId
                        ahVo.overTime = joinActivity.overTime
                        ahVo.myScore = joinActivity.myScore
                        ahVo.myRank = joinActivity.myRank
                        ahVo.rankId = joinActivity.rankId
                        rtBuilder.addActivityHistoryVos(ahVo)
                    }

                    session.sendMsg(MsgType.SelectActivityHistory_1332, rtBuilder.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("SelectActivityHistoryReq Error!", e)
            val rtBuilder = pb4client.SelectActivityHistoryRt.newBuilder()
            rtBuilder.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.SelectActivityHistory_1332, rtBuilder.build())
        }
    }
}

//请求单个联盟活动的信息
fun openActivity(
    session: PlayerSession,
    allianceId: Long,
    activityInfoList: LinkedList<OpenActivityVo>
) {

    val req = OpenActivityAskReq.newBuilder()

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setOpenActivityAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val rtBuilder = pb4client.OpenActivityRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.OpenActivity_1333, rtBuilder.build())
                }
                askResp == null -> {
                    val rtBuilder = pb4client.OpenActivityRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.OpenActivity_1333, rtBuilder.build())
                }
                else -> {
                    val rt = askResp.openActivityAskRt
                    val rtBuilder = pb4client.OpenActivityRt.newBuilder()
                    rtBuilder.rt = rt.rt

                    // 联盟服返回的数据
                    for (info in rt.infosList) {
                        val openActivityVo = OpenActivityVo.newBuilder()
                        openActivityVo.myRank = info.myRank
                        openActivityVo.score = info.score
                        openActivityVo.activityId = info.activityId
                        openActivityVo.castleLv = info.castleLv
                        rtBuilder.addOpenActivityVos(openActivityVo)
                    }

                    // 个人活动数据
                    rtBuilder.addAllOpenActivityVos(activityInfoList)

                    session.sendMsg(MsgType.OpenActivity_1333, rtBuilder.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("OpenActivityAskReq Error!", e)
            val rtBuilder = pb4client.OpenActivityRt.newBuilder()
            rtBuilder.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.OpenActivity_1333, rtBuilder.build())
        }
    }
}

//查询活动排行
fun selectActivityRank(
    session: PlayerSession,
    allianceId: Long,
    rankId: Long
) {
    val req = SelectActivityRankAskReq.newBuilder()
    req.rankId = rankId

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setSelectActivityRankAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->

        try {
            when {
                err != null -> {
                    val rtBuilder = pb4client.SelectActivityRankRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.SelectActivityRank_1335, rtBuilder.build())
                }
                askResp == null -> {
                    val rtBuilder = pb4client.SelectActivityRankRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.SelectActivityRank_1335, rtBuilder.build())
                }
                else -> {
                    val rt = askResp.selectActivityRankAskRt

                    val rtBuilder = pb4client.SelectActivityRankRt.newBuilder()
                    rtBuilder.rt = rt.rt

                    for (rInfo in rt.allianceActivityRankVosList) {
                        val par = pb4client.AllianceActivityRankVo.newBuilder()
                        par.allianceId = rInfo.allianceId
                        par.allianceName = rInfo.allianceName
                        par.shortName = rInfo.shortName
                        par.myScore = rInfo.myScore
                        par.flagColor = rInfo.flagColor
                        par.flagStyle = rInfo.flagStyle
                        par.flagEffect = rInfo.flagEffect

                        rtBuilder.addAActivityRankVos(par)
                    }

                    session.sendMsg(MsgType.SelectActivityRank_1335, rtBuilder.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("SelectActivityRankAskReq Error!", e)
            val rtBuilder = pb4client.SelectActivityRankRt.newBuilder()
            rtBuilder.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.SelectActivityRank_1335, rtBuilder.build())
        }
    }
}

//查询活动此时排行
fun selectNowRank(
    session: PlayerSession,
    activityId: Int
) {

    val req = SelectNowRankReq.newBuilder()
    req.activityId = activityId
    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(ALLIANCE_GENERAL_PID, playerId) {
            it.setSelectNowRankReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->

        try {
            when {
                err != null -> {
                    val rtBuilder = pb4client.SelectNowRankRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.SelectNowRank_1336, rtBuilder.build())
                }
                askResp == null -> {
                    val rtBuilder = pb4client.SelectNowRankRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.SelectNowRank_1336, rtBuilder.build())
                }
                else -> {
                    val rt = askResp.selectNowRankRt
                    val rtBuilder = pb4client.SelectNowRankRt.newBuilder()
                    rtBuilder.rt = rt.rt

                    for (rInfo in rt.allianceActivityRankVosList) {
                        val par = pb4client.AllianceActivityRankVo.newBuilder()
                        par.allianceId = rInfo.allianceId
                        par.allianceName = rInfo.allianceName
                        par.shortName = rInfo.shortName
                        par.myScore = rInfo.myScore
                        par.flagColor = rInfo.flagColor
                        par.flagStyle = rInfo.flagStyle
                        par.flagEffect = rInfo.flagEffect

                        rtBuilder.addAActivityRankVos(par)
                    }

                    session.sendMsg(MsgType.SelectNowRank_1336, rtBuilder.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("SelectNowRankReq Error!", e)
            val rtBuilder = pb4client.SelectNowRankRt.newBuilder()
            rtBuilder.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.SelectNowRank_1336, rtBuilder.build())
        }
    }
}

// 打开活动总界面,只请求联盟活动的数据
fun selectAllianceActivityInfo(
    session: PlayerSession,
    allianceId: Long
) {
    val req = SeleteAllianceActivityInfosReq.newBuilder()
    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setSeleteAllianceActivityInfosReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->
        try {
            when {
                err != null -> {
                    val rtBuilder = pb4client.SeleteAllianceActivityInfosRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.SeleteAllianceActivityInfos_1334, rtBuilder.build())
                }
                askResp == null -> {
                    val rtBuilder = pb4client.SeleteAllianceActivityInfosRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.SeleteAllianceActivityInfos_1334, rtBuilder.build())
                }
                else -> {
                    val rt = askResp.seleteAllianceActivityInfosRt
                    val rtBuilder = pb4client.SeleteAllianceActivityInfosRt.newBuilder()
                    rtBuilder.rt = rt.rt

                    for (activity in rt.allianceActivityInfosList) {
                        val allianceActivityInfo = AllianceActivityInfo.newBuilder()
                        allianceActivityInfo.activityId = activity.activityId
                        allianceActivityInfo.score = activity.score
                        allianceActivityInfo.rank = activity.rank
                        rtBuilder.addAllianceActivityInfoss(allianceActivityInfo.build())
                    }

                    session.sendMsg(MsgType.SeleteAllianceActivityInfos_1334, rtBuilder.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("SelectAllianceActivityInfoReq Error!", e)
            val rtBuilder = pb4client.SeleteAllianceActivityInfosRt.newBuilder()
            rtBuilder.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.SeleteAllianceActivityInfos_1334, rtBuilder.build())
        }
    }
}

//发送联盟邮件
fun sendAllianceMail(
    session: PlayerSession,
    allianceId: Long,
    readType: Int,
    title: String,
    titleParas: LinkedList<String>,
    content: String,
    contentParas: LinkedList<String>
) {
    val req = SendAllianceMailAskReq.newBuilder()
    req.readType = readType
    req.title = title
    req.addAllTitleParas(titleParas)
    req.content = content
    req.addAllContentParas(contentParas)

    val areaCache = session.areaCache
    val playerId = session.playerId

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) {
            it.setSendAllianceMailAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { askResp, err ->

        try {
            when {
                err != null -> {
                    val rtBuilder = pb4client.SendAllianceMailRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR1.code
                    session.sendMsg(MsgType.SendAllianceMail_458, rtBuilder.build())
                }
                askResp == null -> {
                    val rtBuilder = pb4client.SendAllianceMailRt.newBuilder()
                    rtBuilder.rt = ResultCode.ASK_ERROR2.code
                    session.sendMsg(MsgType.SendAllianceMail_458, rtBuilder.build())
                }
                else -> {
                    val rt = askResp.sendAllianceMailAskRt

                    val rtBuilder = pb4client.SendAllianceMailRt.newBuilder()
                    rtBuilder.rt = rt.rt
                    session.sendMsg(MsgType.SendAllianceMail_458, rtBuilder.build())
                }
            }
        } catch (e: Exception) {
            normalLog.error("SendAllianceMailAskReq Error!", e)
            val rtBuilder = pb4client.SendAllianceMailRt.newBuilder()
            rtBuilder.rt = ResultCode.ASK_ERROR3.code
            session.sendMsg(MsgType.SendAllianceMail_458, rtBuilder.build())
        }
    }
}

//发送联盟奖励邮件
fun sendAllianceAwardMail(
    areaCache: AreaCache,
    allianceId: Long,
    readType: Int,
    title: String,
    titleParas: List<String>,
    content: String,
    contentParas: List<String>,
    attach: String
) {
    val req = SendAllianceAwardMailAskReq.newBuilder()
    req.readType = readType
    req.title = title
    req.addAllTitleParas(titleParas)
    req.content = content
    req.addAllContentParas(contentParas)
    req.attach = attach

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, 0) {
            it.setSendAllianceAwardMailAskReq(req)
        },
        World2PublicAskResp::class.java
    ) { rt, err ->

        try {
            when {
                err != null -> {
                }
                rt == null -> {
                }
                else -> {
                }
            }
        } catch (e: Exception) {
        }
    }
}

// 联盟广播集结
fun multicastAllianceMass(
    areaCache: AreaCache,
    allianceId: Long,
    playerId: Long,
    allianceNickName: String,
    allianceName: String,
    allianceShortName: String,
    playerName: String,
    massId: Long,
    massName: String,
    icon: Int,
    vipLv: Int,
    worldId: Long,
    areaNo: Int,
    wonderPos: Int
) {
    val req = SendAllianceMassAskReq.newBuilder()
    req.playerShortName = allianceNickName
    req.playerName = playerName
    req.messageType = MASS_INFO
    val msgMassString = toJson(SimplifiedMassInfo(massId, massName))
    req.message = msgMassString
    req.iconProtoId = icon
    req.vipLv = vipLv
    req.pltAreaId = worldId
    req.areaNo = areaNo
    req.allianceName = allianceName
    req.allianceShortName = allianceShortName

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(allianceId, playerId) { it.setSendAllianceMassAskReq(req) },
        World2PublicAskResp::class.java
    ) { prt, err ->

        try {
            when {
                err != null -> {

                }
                prt == null -> {

                }
                else -> {
                    val now = getNowTime()
                    // 组装聊天消息
                    val newChatMessageBuilder = NewChatMessage.newBuilder()
                    val chatInfoBuilder = ChatInfo.newBuilder()
                    chatInfoBuilder.id = prt.sendAllianceMassAskRt.chatId
                    chatInfoBuilder.type = CHAT_TYPE_ALLIANCE
                    chatInfoBuilder.isSystem = 1
                    chatInfoBuilder.country = 24
                    chatInfoBuilder.allianceName = allianceName
                    chatInfoBuilder.allianceShortName = allianceShortName
                    chatInfoBuilder.alliancePositions = prt.sendAllianceMassAskRt.pos.toString()
                    chatInfoBuilder.player = playerName
                    chatInfoBuilder.playerId = playerId
                    chatInfoBuilder.playerShortName = allianceNickName
                    chatInfoBuilder.playerIcon = icon
                    chatInfoBuilder.sendTime = (now / 1000).toInt()
                    chatInfoBuilder.messageType = MASS_INFO
                    chatInfoBuilder.office = wonderPos
                    chatInfoBuilder.vipLv = vipLv
                    chatInfoBuilder.areaNo = areaNo
                    val noticeBuilder = Notice.newBuilder()
                    noticeBuilder.readType = TEXT_READ_INFO
                    noticeBuilder.noticeLanId = ""
                    val simpleMassInfo = SimpleMassInfo.newBuilder()
                    simpleMassInfo.massName = massName
                    simpleMassInfo.massId = massId
                    chatInfoBuilder.massInfo = simpleMassInfo.build()
                    chatInfoBuilder.message = noticeBuilder.build()
                    newChatMessageBuilder.chatInfo = chatInfoBuilder.build()

                    // home将消息广播到联盟频道
                    println("game将集结消息广播到联盟频道")
                    val multicastMSG = MulticastEnvelopeMsg.newBuilder()
                    multicastMSG.msgType = MsgType.NewChatMessage_3080.msgType
                    multicastMSG.newChatMsg = newChatMessageBuilder.build()
                    multicastMSG.channel = allianceChannelOf(allianceId)
                    wpm.multicastServiceRouter.tellNoSender(
                        multicastMSG.build()
                    )
                }
            }
        } catch (e: Exception) {
        }
    }
}

// gm命令关闭所有联盟活动
fun gmOverAllianceActivity(areaCache: AreaCache) {
    val req = GmOverAllianceActivityAskReq.newBuilder()

    areaCache.createACS(
        areaCache.worldActor.publicShardRegion,
        areaCache.fillW2PAskMsgHeader(ALLIANCE_GENERAL_PID, 0) {
            it.setGmOverAllianceActivityAskReq(req)
        },
        World2PublicAskResp::class.java
    )
    { askResp, err ->
        try {
            when {
                err != null -> {
                }
                askResp == null -> {
                }
                else -> {
                }
            }
        } catch (e: Exception) {
        }
    }
}