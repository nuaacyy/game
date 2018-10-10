package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ALLIANCE_OPEN
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.getTotalPower
import com.point18.slg2d.world.common.joinAllianceById
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.uiCondition.uiConditionCheck
import pb4client.AllianceJoinById
import pb4client.AllianceJoinByIdRt
import pb4server.CheckJoinAllianceStateReq
import pb4server.World2HomeAskResp

//根据联盟ID申请加入联盟 804
class DealJoinAllianceById : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val allianceId = (msg as AllianceJoinById).allianceId
        val rt = this.joinById(session, allianceId)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceJoinById_804, rt)
        }
    }

    /********************************************* 804 玩家申请加入联盟 *********************************************/
    fun joinById(session: PlayerSession, aid: Long): (AllianceJoinByIdRt?) {
        val rt = AllianceJoinByIdRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.allianceId = aid
        rt.quitTime = 0
        val areaCache = session.areaCache
        val player = session.player

        // 功能开启
        val uiConditionRt = uiConditionCheck(session.areaCache, player, ALLIANCE_OPEN)
        if (uiConditionRt != ResultCode.SUCCESS.code) {
            rt.rt = uiConditionRt
            return rt.build()
        }

        //玩家是否已加入联盟
        if (player.allianceId != 0L) {
            rt.rt = (ResultCode.ALLIANCE_PLAYER_HAS_ALLIANCE.code)
            return rt.build()
        }

        val myTarget = areaCache.targetCache.findMyTargetByPlayerId(session.playerId)
        if (myTarget == null) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        val joinInfo = player.joinAllianceReqs[aid]
        if (joinInfo != null) {
            rt.rt = ResultCode.ALLIANCE_REQ_ALREADY_EXIST.code
            return rt.build()
        }

        // 查看已申请列表很耗 所以当前正在申请的量必须少,写死10
        if (player.joinAllianceReqs.size >= 10) {
            rt.rt = ResultCode.APPLY_ALLIANCE_MAX_ERROR.code
            return rt.build()
        }

        // 发ASK到home去检测玩家的入帮状态
        // 需要这么做是有可能玩家满足战斗力需求直接进帮,必须去HOME验证防止玩家被异步多个帮派加入联盟
        val reqMsg = CheckJoinAllianceStateReq.newBuilder()
        areaCache.worldActor.createACS<World2HomeAskResp>()
            .ask(
                areaCache.worldActor.homeShardRegion,
                areaCache.fillW2HAskMsgHeader(session.playerId) {
                    it.setCheckJoinAllianceStateReq(reqMsg)
                },
                World2HomeAskResp::class.java
            )
            .whenCompleteKt { hrt, err ->
                try {
                    when {
                        err != null -> {
                            rt.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.AllianceInvite_900, rt.build())
                            return@whenCompleteKt
                        }
                        hrt == null -> {
                            rt.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.AllianceInvite_900, rt.build())
                            return@whenCompleteKt
                        }
                        else -> {
                            if (hrt.checkJoinAllianceStateRt.rt != ResultCode.SUCCESS.code) {
                                rt.rt = hrt.checkJoinAllianceStateRt.rt
                                session.sendMsg(MsgType.AllianceInvite_900, rt.build())
                                return@whenCompleteKt
                            } else {
                                // home的验证通过了 并且已经把玩家状态设置成了正在尝试加入联盟中
                                val playerFightValue = myTarget.getTotalPower()
                                joinAllianceById(
                                    session,
                                    aid,
                                    playerFightValue,
                                    player.name,
                                    player.photoProtoId,
                                    player.worldId,
                                    player.areaNo,
                                    player.loginTime,
                                    player.lastLeaveTime,
                                    player.photoProtoId,
                                    castle.lv,
                                    player.allianceNickName
                                )
                            }
                        }
                    }

                } catch (e: Exception) {
                    rt.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.AllianceInvite_900, rt.build())
                    return@whenCompleteKt
                }
            }

        return null
    }
}


