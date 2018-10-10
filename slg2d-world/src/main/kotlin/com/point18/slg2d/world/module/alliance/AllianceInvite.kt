package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceInvite
import pb4client.AllianceInviteRt
import pb4server.QueryPlayerInfoAskReq
import pb4server.World2HomeAskResp
import com.point18.slg2d.common.resultcode.ResultCode

// 邀请玩家加入联盟
class AllianceInvite : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val msgTmp = msg as AllianceInvite
        val invitePlayerId = msgTmp.playerId

        val rt = this.allianceInvite(session, invitePlayerId)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceInvite_900, rt)
        }
    }

    fun allianceInvite(session: PlayerSession, invitePlayerId: Long): (AllianceInviteRt?) {
        val rt = AllianceInviteRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache

        val player = session.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        val ex = session.inviteAlliance[invitePlayerId]
        if (ex != null) {
            rt.rt = (ResultCode.INVITE_PLAYER_CD.code)
            return rt.build()
        }

        val invitePlayer = areaCache.playerCache.findPlayerById(invitePlayerId)
        if (invitePlayer == null) {
            // 邀请的是其他服的玩家,去玩家所在服拿数据
            val reqMsg = QueryPlayerInfoAskReq.newBuilder()
            areaCache.worldActor.createACS<World2HomeAskResp>()
                .ask(
                    areaCache.worldActor.homeShardRegion,
                    areaCache.fillW2HAskMsgHeader(invitePlayerId) {
                        it.setQueryPlayerInfoAskReq(reqMsg)
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
                                val aId = hrt.queryPlayerInfoAskRt.queryPlayerInfoVo.allianceId
                                if (aId != 0L || hrt.queryPlayerInfoAskRt.queryPlayerInfoVo.playerCastleLv == 0) {
                                    rt.rt = ResultCode.PARAMETER_ERROR.code
                                    session.sendMsg(MsgType.AllianceInvite_900, rt.build())
                                    return@whenCompleteKt
                                } else {
                                    com.point18.slg2d.world.common.allianceInvite(session, player.allianceId, invitePlayerId)
                                }
                            }
                        }

                    } catch (e: Exception) {
                        rt.rt = ResultCode.ASK_ERROR3.code
                        session.sendMsg(MsgType.AllianceInvite_900, rt.build())
                        return@whenCompleteKt
                    }
                }
        } else {
            // 邀请的是本服玩家 但是他已经有联盟了
            if (invitePlayer.allianceId != 0L) {
                rt.rt = (ResultCode.PARAMETER_ERROR.code)
                return rt.build()
            }
        }

        if (invitePlayer == null || invitePlayer.allianceId != 0L) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        // 然后发送到公共服去检测是否有重复的
        com.point18.slg2d.world.common.allianceInvite(session, player.allianceId, invitePlayerId)

        return null
    }
}



