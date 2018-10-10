package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReport
import pb4client.*

class NewBattleReportNotifier(
    val msg: NewBattleReport.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.NewBattleReport_3173, this.msg.build())
    }
}

fun createNewBattleReportNotifier(report: BattleReport): NewBattleReportNotifier {
    val reportBuilder = BattleReportInfo.newBuilder()
    reportBuilder.id = report.id
    reportBuilder.readState = report.readState
    reportBuilder.reportType = report.reportType
    reportBuilder.fightTime = getTimeSec(report.fightTime)
    reportBuilder.fightAddressX = report.fightAddressX
    reportBuilder.fightAddressY = report.fightAddressY
    reportBuilder.archived = report.archived

    when (report.reportType) {
        FIGHT_PLAYER_REPORT ->
            reportBuilder.pvpTroopsFightReport = PvpFightReport.parseFrom(report.reportContent)
        ATK_MONSTER_REPORT ->
            reportBuilder.hunterFightReport = HunterFightReport.parseFrom(report.reportContent)
        ATK_ALLIANCE_MONSTER_REPORT ->
            reportBuilder.allianceHunterFightReport =
                HunterFightReport.parseFrom(report.reportContent)
        ATK_WORLD_MONSTER_REPORT ->
            reportBuilder.worldHunterFightReport = HunterFightReport.parseFrom(report.reportContent)
        FIGHT_RELIC_REPORT ->
            reportBuilder.massRuinsFightReport = MassRuinsFightReport.parseFrom(report.reportContent)
        SCOUT_REPORT ->
            reportBuilder.scoutReport = ScoutReport.parseFrom(report.reportContent)
        BE_SCOUT_REPORT ->
            reportBuilder.beScoutReport = BeScoutReport.parseFrom(report.reportContent)
        FARM_REPORT ->
            reportBuilder.collectReport = CollectReport.parseFrom(report.reportContent)
        TRANSPORT_REPORT ->
            reportBuilder.transportReport = TransportReport.parseFrom(report.reportContent)
    }
    val newBattleReportBuilder = NewBattleReport.newBuilder()
    newBattleReportBuilder.setReport(reportBuilder)
    return NewBattleReportNotifier(newBattleReportBuilder)
}


