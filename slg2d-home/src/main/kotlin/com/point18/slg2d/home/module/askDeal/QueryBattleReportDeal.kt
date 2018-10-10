package com.point18.slg2d.home.module.askDeal

import com.point18.slg2d.common.constg.ATK_ALLIANCE_MONSTER_REPORT
import com.point18.slg2d.common.constg.ATK_MONSTER_REPORT
import com.point18.slg2d.common.constg.ATK_WORLD_MONSTER_REPORT
import com.point18.slg2d.common.constg.JJC_FIGHT_REPORT
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReportDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import pb4client.HeroFightReport
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp
import pb4server.QueryBattleReportInfoAskReq
import pb4server.QueryBattleReportInfoAskRt

class QueryBattleReportDeal : H2HAsk, HomeHelperPlus1<BattleReportDC>(BattleReportDC::class.java) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        val internalMessage = req.queryBattleReportInfoAskReq

        prepare(session) { battleReportDC: BattleReportDC ->
            val rt = queryReport(battleReportDC, session, internalMessage)
            resp.setQueryBattleReportInfoAskRt(rt)
        }
    }

    private fun queryReport(
        battleReportDC: BattleReportDC,
        session: PlayerActor,
        req: QueryBattleReportInfoAskReq
    ): QueryBattleReportInfoAskRt.Builder {
        val rtBuilder = QueryBattleReportInfoAskRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val reportId = req.reportId

        val report = battleReportDC.findBattleReportById(reportId)
        if (report == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        rtBuilder.reportType = report.reportType
        when (report.reportType) {
            JJC_FIGHT_REPORT,
            ATK_MONSTER_REPORT,
            ATK_ALLIANCE_MONSTER_REPORT,
            ATK_WORLD_MONSTER_REPORT -> {
                val reportInfo = HeroFightReport.parseFrom(report.fightDetail)
                rtBuilder.addHeroFightReport(reportInfo)
            }
            else -> {
                rtBuilder.soliderFightReport = report.fightDetail.toString(Charsets.UTF_8)
            }
        }

        return rtBuilder
    }
}