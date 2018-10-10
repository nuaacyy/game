package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.Thumb
import com.point18.slg2d.home.dc.ThumbInfoDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ThumbUp
import pb4client.ThumbUpRt
import pb4server.Home2HomeAskResp
import pb4server.ThumbToPlayerAskReq

class ThumbUpDeal : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, ThumbInfoDC>(
    HomePlayerDC::class.java, ThumbInfoDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, thumbInfoDC: ThumbInfoDC ->
            val rtBuilder = handleThumbUp(
                session, msg as ThumbUp, homePlayerDC, thumbInfoDC
            )
            if (rtBuilder != null) {
                session.sendMsg(MsgType.ThumbUp_1531, rtBuilder.build())
            }
        }
    }

    private fun handleThumbUp(
        session: PlayerActor,
        msg: ThumbUp,
        homePlayerDC: HomePlayerDC,
        thumbInfoDC: ThumbInfoDC
    ): ThumbUpRt.Builder? {
        val rtBuilder = ThumbUpRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val toPlayerId = msg.toPlayerId

        val thumbInfo = thumbInfoDC.thumbInfo
        val thumbOut = thumbInfo.thumbOut
        val thumb = thumbOut[toPlayerId]
        if (thumb != null) {
            com.point18.slg2d.common.commonfunc.normalLog.error("不能重复给玩家[$toPlayerId]点赞")
            rtBuilder.rt = ResultCode.REPEAT_THUMB.code
            return rtBuilder
        }

        val player = homePlayerDC.player
        val thumbTime = getNowTime()

        //ask点赞其他玩家
        val askMsg = ThumbToPlayerAskReq.newBuilder()
        askMsg.sendPlayerId = session.playerId
        askMsg.lv = player.kingLv
        askMsg.intro = player.selfIntroduction
        askMsg.thumbTime = thumbTime

        session.createACS<Home2HomeAskResp>().ask(
            session.homeShardProxy,
            session.fillHome2HomeAskMsgHeader {
                it.setThumbToPlayerAskReq(askMsg)
            },
            Home2HomeAskResp::class.java
        ).whenCompleteKt { resp, err ->

            try {
                when {
                    err != null -> {
                        rtBuilder.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.ThumbUp_1531, rtBuilder.build())
                    }
                    resp == null -> {
                        rtBuilder.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.ThumbUp_1531, rtBuilder.build())
                    }
                    else -> {
                        val askRes = resp.thumbToPlayerAskRt
                        if (askRes.rt != ResultCode.SUCCESS.code) {
                            rtBuilder.rt = askRes.rt
                            session.sendMsg(MsgType.ThumbUp_1531, rtBuilder.build())
                            return@whenCompleteKt
                        }
                        thumbOut[toPlayerId] = Thumb(
                            toPlayerId,
                            askRes.playerName,
                            askRes.lv,
                            askRes.intro,
                            thumbTime
                        )
                        while (thumbOut.size >= 100 && !thumbOut.isEmpty()) {
                            val removeKey = thumbOut.iterator().next().key
                            thumbOut.remove(removeKey)
                        }
                        rtBuilder.rt = askRes.rt
                        session.sendMsg(MsgType.ThumbUp_1531, rtBuilder.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("ThumbToPlayerAskReq Error!", e)
                rtBuilder.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.ThumbUp_1531, rtBuilder.build())
            }
        }

        return null
    }

}