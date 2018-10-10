package com.point18.slg2d.home.module.askDeal

import com.google.protobuf.ByteString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReportDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import pb4server.AskFightInfoDetailAskReq
import pb4server.AskFightInfoDetailAskRt
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp

class AskChatBattleDetail : H2HAsk, HomeHelperPlus1<BattleReportDC>(BattleReportDC::class.java) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        val internalMessage = req.askFightInfoDetailAskReq

        prepare(session) { battleReportDC: BattleReportDC ->
            val rt = queryReport(battleReportDC, session, internalMessage)
            resp.setAskFightInfoDetailAskRt(rt)
        }
    }

    private fun queryReport(
        battleReportDC: BattleReportDC,
        session: PlayerActor,
        req: AskFightInfoDetailAskReq
    ): AskFightInfoDetailAskRt.Builder {
        val rtBuilder = AskFightInfoDetailAskRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        val reportId = req.reportId

        val report = battleReportDC.findBattleReportById(reportId)
        if (report == null) {
            rtBuilder.rt = ResultCode.FIGHT_INFO_NOT_FOUND.code
            return rtBuilder
        }

        rtBuilder.reportId = report.id
        rtBuilder.readState = report.readState
        rtBuilder.reportType = report.reportType
        rtBuilder.fightTime = (report.fightTime / 1000).toInt()
        rtBuilder.fightAddressX = report.fightAddressX
        rtBuilder.fightAddressY = report.fightAddressY
        rtBuilder.archived = report.archived
        rtBuilder.reportContent = ByteString.copyFrom(report.reportContent)

        return rtBuilder
    }

}