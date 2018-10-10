package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowMTime
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.ThumbInfoDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.QueryThumbInfo
import pb4client.QueryThumbInfoRt
import pb4client.Thumb

class QueryThumbInfoDeal : HomeClientMsgDeal, HomeHelperPlus1<ThumbInfoDC>(ThumbInfoDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { thumbInfoDC: ThumbInfoDC ->
            val rtBuilder = queryThumbUpInfo(session, msg as QueryThumbInfo, thumbInfoDC)
            session.sendMsg(MsgType.QueryThumbInfo_1532, rtBuilder.build())
        }
    }

    private fun queryThumbUpInfo(
        session: PlayerActor, msg: QueryThumbInfo, thumbInfoDC: ThumbInfoDC
    ): QueryThumbInfoRt.Builder {
        val rtBuilder = QueryThumbInfoRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        rtBuilder.thumbOut = 0
        rtBuilder.thumbIn = 0
        rtBuilder.thumbInTotal = 0

        val thumbInfo = thumbInfoDC.thumbInfo
        rtBuilder.thumbOut = thumbInfo.thumbOutNum
        rtBuilder.thumbIn = thumbInfo.thumbInNum
        rtBuilder.thumbInTotal = thumbInfo.thumbInTotal

        thumbInfo.thumbOut.forEach { _, thumb ->
            val thumbBuilder = Thumb.newBuilder()
            thumbBuilder.playerId = thumb.id
            thumbBuilder.name = thumb.name
            thumbBuilder.lv = thumb.lv
            thumbBuilder.intro = thumb.intro
            thumbBuilder.thumbTime = (thumb.thumbTime / 1000).toInt()
            rtBuilder.addThumbOutInfo(thumbBuilder)
        }
        thumbInfo.thumbIn.forEach { _, thumb ->
            val thumbBuilder = Thumb.newBuilder()
            thumbBuilder.playerId = thumb.id
            thumbBuilder.name = thumb.name
            thumbBuilder.lv = thumb.lv
            thumbBuilder.intro = thumb.intro
            thumbBuilder.thumbTime = (thumb.thumbTime / 1000).toInt()
            rtBuilder.addThumbInInfo(thumbBuilder)
        }
        thumbInfo.thumbInAlliance.forEach { _, thumb ->
            val thumbBuilder = Thumb.newBuilder()
            thumbBuilder.playerId = thumb.id
            thumbBuilder.name = thumb.name
            thumbBuilder.lv = thumb.lv
            thumbBuilder.intro = thumb.intro
            thumbBuilder.thumbTime = (thumb.thumbTime / 1000).toInt()
            rtBuilder.addThumbInAlliance(thumbBuilder)
        }

        thumbInfo.checkTime = getNowMTime()

        return rtBuilder
    }

}