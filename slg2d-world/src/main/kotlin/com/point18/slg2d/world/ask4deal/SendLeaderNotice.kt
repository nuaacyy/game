package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.SEND_NOTICE_TO_LEADER_TITLE
import com.point18.slg2d.common.constg.TEXT_READ_INFO
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.sendMail
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.SendNoticeToLeaderAskRt
import java.util.*

class SendLeaderNotice : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val playerId = req.playerId
        val rtBuilder = SendNoticeToLeaderAskRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val wonder = areaCache.wonderCache.findBigWonder()
        if (wonder == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            resp.setSendNoticeToLeaderAskRt(rtBuilder)
            return
        }

        //校验文本内容
        if (wonder.notice == "") {
            rtBuilder.rt = ResultCode.NO_COUNTRY_NOTICE.code
            resp.setSendNoticeToLeaderAskRt(rtBuilder)
            return
        }

        //校验资源
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            rtBuilder.rt = ResultCode.NO_PLAYER.code
            resp.setSendNoticeToLeaderAskRt(rtBuilder)
            normalLog.error("找不到玩家数据:%d", playerId)
            return
        }

        //发送邮件通知
        val leaders = areaCache.playerCache.findAllAllianceLeader()
        for (leader in leaders) {
            val mailInfo = MailInfo(
                TEXT_READ_INFO,
                SEND_NOTICE_TO_LEADER_TITLE,
                LinkedList(),
                wonder.notice,
                LinkedList()
            )
            sendMail(areaCache, leader.id, mailInfo)
        }
        resp.setSendNoticeToLeaderAskRt(rtBuilder)
    }

}