package com.point18.slg2d.home.module.askDeal

import com.point18.slg2d.common.constg.ATK_MONSTER_REPORT
import com.point18.slg2d.common.constg.FIGHT_PLAYER_REPORT
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReportDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.msgnotice.createAttackNotifier
import com.point18.slg2d.home.msgnotice.createNewBattleReportNotifier
import pb4client.HunterFightReport
import pb4server.CreateBattleReportAskHomeReq
import pb4server.CreateBattleReportAskHomeRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp

class AddBattleReportDeal : W2HAsk, HomeHelperPlus1<BattleReportDC>(BattleReportDC::class.java) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val internalMessage = req.createBattleReportAskHomeReq
        val rt = addReport(session, internalMessage)
        resp.setCreateBattleReportAskHomeRt(rt)
    }

    private fun addReport(
        session: PlayerActor,
        req: CreateBattleReportAskHomeReq
    ): CreateBattleReportAskHomeRt.Builder {
        val rtBuilder = CreateBattleReportAskHomeRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        return prepare(session) { battleReportDC: BattleReportDC ->
            val report = battleReportDC.createBattleReport(
                req.report.readState,
                req.report.reportType,
                req.report.fightTime,
                req.report.posX,
                req.report.posY,
                req.report.pastTime,
                req.report.reportContent,
                req.report.fightDetail
            )

            // 确定能持有的最大战报数，并删除超过上限的战报
            var maxReportNum = pcs.basicProtoCache.fightReportSaveNum
            if (report.reportType != FIGHT_PLAYER_REPORT) {
                maxReportNum = pcs.basicProtoCache.pVEReportSaveNum
            }
            if (battleReportDC.battleReportQueue[report.reportType]?.size ?: 0 > maxReportNum) {
                //删除最早的战报
                val oldReport = battleReportDC.battleReportQueue[report.reportType]?.peek()
                if (oldReport != null) {
                    battleReportDC.deleteBattleReport(oldReport)
                }
            }

            createNewBattleReportNotifier(report).notice(session)

            if (report.reportType != ATK_MONSTER_REPORT) {
                return@prepare rtBuilder
            }

            //额外推送攻击魔物消息
            val hunterFightInfo = HunterFightReport.parseFrom(report.reportContent)
            createAttackNotifier(report.id, hunterFightInfo.hunterFightInfo.monsterId).notice(session)

            return@prepare rtBuilder
        }
    }
}