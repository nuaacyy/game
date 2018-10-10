package com.point18.slg2d.home.module.getFightInfo

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReportDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GetDetailFightInfo
import pb4client.GetDetailFightInfoRt
import pb4client.HeroFightReport
import pb4server.Home2HomeAskResp
import pb4server.QueryBattleReportInfoAskReq

// 请求详细战报
class GetDetailBattleReportDeal : HomeClientMsgDeal, HomeHelperPlus1<BattleReportDC>(BattleReportDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        msg as GetDetailFightInfo
        val reportId = msg.reportId
        val playerId = msg.playerId

        if (playerId == session.playerId) {
            prepare(session) { battleReportDC: BattleReportDC ->
                val geEasyFightInfoRt = this.getDetailBattleReport(session, reportId, battleReportDC)
                session.sendMsg(MsgType.GetDetailFightInfo_104, geEasyFightInfoRt)
            }
            return
        }

        val queryReportBuilder = QueryBattleReportInfoAskReq.newBuilder()
        queryReportBuilder.reportId = reportId
        session.createACS<Home2HomeAskResp>().ask(
            session.homeShardProxy,
            session.fillHome2HomeAskMsgHeader {
                it.setQueryBattleReportInfoAskReq(queryReportBuilder)
                it.playerId = playerId
            },
            Home2HomeAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            val rt = GetDetailFightInfoRt.newBuilder()
            try {
                when {
                    askErr != null -> {
                        rt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.GetDetailFightInfo_104, rt.build())
                    }
                    askResp == null -> {
                        rt.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.GetDetailFightInfo_104, rt.build())
                    }
                    else -> {

                        if (askResp.queryBattleReportInfoAskRt.rt != ResultCode.SUCCESS.code) {
                            rt.rt = askResp.queryBattleReportInfoAskRt.rt
                        } else {
                            rt.rt = ResultCode.SUCCESS.code
                            rt.reportId = reportId
                            rt.soliderFightReport = askResp.queryBattleReportInfoAskRt.soliderFightReport
                            rt.addAllHeroFightReport(askResp.queryBattleReportInfoAskRt.heroFightReportList)
                        }
                        session.sendMsg(MsgType.GetDetailFightInfo_104, rt.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("QueryBattleReportInfoAskReq Error!", e)
                rt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.GetDetailFightInfo_104, rt.build())
            }
        }
    }

    private fun getDetailBattleReport(
        session: PlayerActor, reportId: Long, battleReportDC: BattleReportDC
    ): GetDetailFightInfoRt {
        val rt = GetDetailFightInfoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.reportId = reportId

        val report = battleReportDC.findBattleReportById(reportId)
        if (report == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }
        rt.reportType = report.reportType
        when (report.reportType) {
            com.point18.slg2d.common.constg.JJC_FIGHT_REPORT,
            com.point18.slg2d.common.constg.ATK_MONSTER_REPORT,
            com.point18.slg2d.common.constg.ATK_ALLIANCE_MONSTER_REPORT,
            com.point18.slg2d.common.constg.ATK_WORLD_MONSTER_REPORT -> {
                val reportInfo = HeroFightReport.parseFrom(report.fightDetail)
                rt.addHeroFightReport(reportInfo)
            }
            else ->
                rt.soliderFightReport = report.fightDetail.toString(Charsets.UTF_8)
        }
        return rt.build()
    }

}