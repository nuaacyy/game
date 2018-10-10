package com.point18.slg2d.home.common

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.Notice
import pb4server.Home2WorldAskResp
import pb4server.MarqueeAskReq

// 向world发送跑马灯请求
fun sendMarqueeAsk2World(
    session: PlayerActor,
    noticeType: Int,
    toPlayerId: Long,
    lanId: String,
    readType: Int,
    vararg params: String
) {
    val askMsg = MarqueeAskReq.newBuilder()
    askMsg.noticeType = noticeType
    askMsg.toPlayerId = toPlayerId
    val noticeBuilder = Notice.newBuilder()
    noticeBuilder.readType = readType
    noticeBuilder.noticeLanId = lanId
    params.forEach { noticeBuilder.addNoticeParams(it) }
    askMsg.setNoticeInfos(noticeBuilder)

    session.createACS<Home2WorldAskResp>().ask(
        session.worldShardProxy,
        session.fillHome2WorldAskMsgHeader {
            it.setMarqueeAskReq(askMsg)
        },
        Home2WorldAskResp::class.java
    ).whenCompleteKt { askResp, askErr ->
        try {
            when {
                askErr != null -> {
                }
                askResp == null -> {

                }
                else -> {
                    val rt = askResp.marqueeAskRt
                    if (rt.rt != ResultCode.SUCCESS.code) {

                    } else {

                    }
                }
            }

        } catch (e: Exception) {
        }

    }
}