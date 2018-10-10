package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createNewChatWindowNotifier
import pb4client.OpenNewChatWindow
import pb4client.OpenNewChatWindowRt
import pb4server.AskStrangerInfoAskReq
import pb4server.Home2HomeAskResp

class OpenChatWindowDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) {
            val playerId = (msg as OpenNewChatWindow).playerId
            val rt = openStrangerChat(session, playerId)
            if (rt != null) {
                session.sendMsg(MsgType.OpenNewChatWindow_297, rt)
            }
        }
    }

    private fun openStrangerChat(session: PlayerActor, playerId: Long): OpenNewChatWindowRt? {
        // 去 ask home 返回一些数据
        // 去home找这个player的信息
        val rt = OpenNewChatWindowRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val askMsg = AskStrangerInfoAskReq.newBuilder()
        askMsg.targetPlayerId = playerId

        session.createACS<Home2HomeAskResp>().ask(
            session.homeShardProxy,
            session.fillHome2HomeAskMsgHeader {
                it.setAskStrangerInfoAskReq(askMsg)
                it.playerId = playerId
            },
            Home2HomeAskResp::class.java
        ).whenCompleteKt { resp, err ->

            try {
                when {
                    err != null -> {
                        ResultCode.ASK_ERROR1.code
                        rt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.OpenNewChatWindow_297, rt.build())
                    }
                    resp == null -> {

                        rt.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.OpenNewChatWindow_297, rt.build())

                    }
                    else -> {
                        val nowTime = getNowTime()
                        createNewChatWindowNotifier(
                            resp.askStrangerInfoAskRt.targetPlayer.myPlayerId,
                            resp.askStrangerInfoAskRt.targetPlayer.worldId,
                            resp.askStrangerInfoAskRt.targetPlayer.name,
                            resp.askStrangerInfoAskRt.targetPlayer.photoProtoId,
                            resp.askStrangerInfoAskRt.targetPlayer.power.toLong(),
                            resp.askStrangerInfoAskRt.targetPlayer.castleLv,
                            resp.askStrangerInfoAskRt.targetPlayer.skinType,
                            resp.askStrangerInfoAskRt.targetPlayer.vipLv,
                            resp.askStrangerInfoAskRt.targetPlayer.areaNo,
                            resp.askStrangerInfoAskRt.targetPlayer.allianceShortName,
                            nowTime,
                            nowTime,
                            resp.askStrangerInfoAskRt.targetPlayer.allianceNickName
                        ).notice(session)
                        session.sendMsg(MsgType.OpenNewChatWindow_297, rt.build())

                    }
                }

            } catch (e: Exception) {
                normalLog.error("AskStrangerInfoAskReq Error!", e)
                rt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.OpenNewChatWindow_297, rt.build())
            }

        }
        return null
    }
}