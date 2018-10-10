package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.RemoveAllianceWaijiao
import pb4client.RemoveAllianceWaijiaoRt
import pb4server.Home2PublicAskResp
import pb4server.RemoveAllianceWaijiaoReq
import java.util.Arrays.asList

// 删除联盟外交留言
class RemoveAllianceWaijiaoDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val removeIds = (msg as RemoveAllianceWaijiao).removeIdsList
            val rt = removeAllianceWaijiao(session, homePlayerDC, removeIds)
            if (rt != null) {
                session.sendMsg(MsgType.RemoveAllianceWaijiao_919, rt)
            }
        }
    }

    private fun removeAllianceWaijiao(
        session: PlayerActor,
        homePlayerDC: HomePlayerDC,
        removeIds: List<Long>
    ): RemoveAllianceWaijiaoRt? {
        val rt = RemoveAllianceWaijiaoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player

        if (player.allianceId == 0L) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt.build()
        }

        if (removeIds.size == 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 然后发送到公共服去检测是否有重复的
        removeAllianceWaijiao2Public(session, player, removeIds)

        return null
    }

    // 删除联盟留言
    private fun removeAllianceWaijiao2Public(session: PlayerActor, player: HomePlayer, removeIds: List<Long>) {
        val askMsg = RemoveAllianceWaijiaoReq.newBuilder()
        askMsg.addAllRemoveIds(removeIds)

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(player.allianceId) {
                it.setRemoveAllianceWaijiaoReq(askMsg)
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            try {
                when {
                    askErr != null -> {
                        val rtMsgBuilder = RemoveAllianceWaijiaoRt.newBuilder()
                        rtMsgBuilder.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.RemoveAllianceWaijiao_919, rtMsgBuilder.build())
                        return@whenCompleteKt
                    }
                    askResp == null -> {
                        val rtMsgBuilder = RemoveAllianceWaijiaoRt.newBuilder()
                        rtMsgBuilder.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.RemoveAllianceWaijiao_919, rtMsgBuilder.build())
                        return@whenCompleteKt
                    }
                    else -> {
                        val rt = askResp.removeAllianceWaijiaoRt
                        val rtMsgBuilder = RemoveAllianceWaijiaoRt.newBuilder()
                        rtMsgBuilder.rt = rt.rt
                        if (rt.rt == ResultCode.SUCCESS.code) {
                            if (rt.removeIdsCount == 0) {
                                rtMsgBuilder.rt = ResultCode.WAIJIAO_REMOVE_ERROR.code
                            } else {
                                rtMsgBuilder.addAllRemoveIds(rt.removeIdsList)
                            }
                        }

                        session.sendMsg(MsgType.RemoveAllianceWaijiao_919, rtMsgBuilder.build())
                    }
                }
            } catch (e: Exception) {
                val rtMsgBuilder = RemoveAllianceWaijiaoRt.newBuilder()
                rtMsgBuilder.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.RemoveAllianceWaijiao_919, rtMsgBuilder.build())
            }
        }
    }

}


