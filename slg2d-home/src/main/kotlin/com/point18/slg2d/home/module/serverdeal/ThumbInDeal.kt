package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.Thumb
import com.point18.slg2d.home.dc.ThumbInfoDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.askDeal.H2HAsk
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp
import pb4server.ThumbToPlayerAskRt

class ThumbInDeal : H2HAsk, HomeHelperPlus2<HomePlayerDC, ThumbInfoDC>(
    HomePlayerDC::class.java,
    ThumbInfoDC::class.java
) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        val askMsg = req.thumbToPlayerAskReq
        prepare(session) { homePlayerDC, thumbInfoDC ->
            val rt = ThumbToPlayerAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            val thumbIn = thumbInfoDC.thumbInfo.thumbIn
            val thumb = thumbIn[askMsg.sendPlayerId]
            if (thumb != null) {
                rt.rt = ResultCode.REPEAT_THUMB.code
                resp.setThumbToPlayerAskRt(rt)
                return@prepare
            }

            thumbIn[askMsg.sendPlayerId] = Thumb(
                askMsg.sendPlayerId,
                askMsg.playerName,
                askMsg.lv,
                askMsg.intro,
                askMsg.thumbTime
            )
            while (thumbIn.size >= 100 && !thumbIn.isEmpty()) {
                val removeKey = thumbIn.iterator().next().key
                thumbIn.remove(removeKey)
            }
            val player = homePlayerDC.player
            rt.playerName = player.name
            rt.lv = player.kingLv
            rt.intro = player.selfIntroduction
            resp.setThumbToPlayerAskRt(rt)
        }
    }
}