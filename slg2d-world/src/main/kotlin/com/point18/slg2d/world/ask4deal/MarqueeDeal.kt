package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.sendMarqueeNotice2All
import com.point18.slg2d.world.common.sendMarqueeNotice2Player
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.MarqueeAskRt

class MarqueeDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.marqueeAskReq
        val toPlayerId = askMsg.toPlayerId
        val noticeType = askMsg.noticeType
        val noticeInfo = askMsg.noticeInfos

        val readType = noticeInfo.readType
        val noticeLanId = noticeInfo.noticeLanId
        val paramList = noticeInfo.noticeParamsList
        val params = Array<String>(paramList.size) {
            paramList[it]
        }

        val rt = MarqueeAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        if (toPlayerId == 0L) {
            sendMarqueeNotice2All(areaCache, noticeType, noticeLanId, readType, *params)
        } else {
            sendMarqueeNotice2Player(areaCache, noticeType, toPlayerId, noticeLanId, readType, *params)
        }

        resp.setMarqueeAskRt(rt)
        return
    }

}