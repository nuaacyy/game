package com.point18.slg2d.home.module.getFightInfo

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReport
import com.point18.slg2d.home.dc.BattleReportDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.*
import java.util.*

// 请求简单战报
class GetBattleReportDeal : HomeClientMsgDeal, HomeHelperPlus1<BattleReportDC>(BattleReportDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { battleReportDC: BattleReportDC ->
            val getType = (msg as GetEasyFightInfo).selectType
            val lastId = msg.lastId
            val geEasyFightInfoRt = getBattleReports(session, getType, lastId, battleReportDC)

            session.sendMsg(MsgType.GetEasyFightInfo_103, geEasyFightInfoRt)
        }
    }

    private fun getBattleReports(
        session: PlayerActor,
        getType: Int,
        lastId: Long,
        battleReportDC: BattleReportDC
    ): GetEasyFightInfoRt {
        val rt = GetEasyFightInfoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        var playerBattleReports: LinkedList<BattleReport>
        val askNum = pcs.basicProtoCache.fightReportAskNum

        when (getType) {
            FIGHT_INFO_MINE -> {
                playerBattleReports = battleReportDC.findAllShowBattleReport()
            }

            FIGHT_INFO_JJC -> {
                playerBattleReports = battleReportDC.findAllJjcReport()
            }

            else -> {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }
        }

        playerBattleReports.sortBy { c1: BattleReport -> c1.id }

        // 筛选出需要显示的
        playerBattleReports = getTrueInfo(playerBattleReports, lastId, askNum)
        for (eachBattleReport in playerBattleReports) {
            val reportBuilder = BattleReportInfo.newBuilder()
            reportBuilder.id = eachBattleReport.id
            reportBuilder.readState = eachBattleReport.readState
            reportBuilder.reportType = eachBattleReport.reportType
            reportBuilder.fightTime = getTimeSec(eachBattleReport.fightTime)
            reportBuilder.fightAddressX = eachBattleReport.fightAddressX
            reportBuilder.fightAddressY = eachBattleReport.fightAddressY
            reportBuilder.archived = eachBattleReport.archived

            when (eachBattleReport.reportType) {
                FIGHT_PLAYER_REPORT ->
                    reportBuilder.pvpTroopsFightReport = PvpFightReport.parseFrom(eachBattleReport.reportContent)
                ATK_MONSTER_REPORT ->
                    reportBuilder.hunterFightReport = HunterFightReport.parseFrom(eachBattleReport.reportContent)
                ATK_ALLIANCE_MONSTER_REPORT ->
                    reportBuilder.allianceHunterFightReport =
                            HunterFightReport.parseFrom(eachBattleReport.reportContent)
                ATK_WORLD_MONSTER_REPORT ->
                    reportBuilder.worldHunterFightReport = HunterFightReport.parseFrom(eachBattleReport.reportContent)
                FIGHT_RELIC_REPORT ->
                    reportBuilder.massRuinsFightReport = MassRuinsFightReport.parseFrom(eachBattleReport.reportContent)
                SCOUT_REPORT ->
                    reportBuilder.scoutReport = ScoutReport.parseFrom(eachBattleReport.reportContent)
                BE_SCOUT_REPORT ->
                    reportBuilder.beScoutReport = BeScoutReport.parseFrom(eachBattleReport.reportContent)
                FARM_REPORT ->
                    reportBuilder.collectReport = CollectReport.parseFrom(eachBattleReport.reportContent)
                TRANSPORT_REPORT ->
                    reportBuilder.transportReport = TransportReport.parseFrom(eachBattleReport.reportContent)
                HUNTER_CALL_REPORT ->
                    reportBuilder.hunterCallInfo =
                            HunterCallInfo.parseFrom(eachBattleReport.reportContent)
                JJC_FIGHT_REPORT ->
                    reportBuilder.jjcFightReport = JjcFightReport.parseFrom(eachBattleReport.reportContent)
                STATION_DEF_REPORT ->
                    reportBuilder.stationDefReport = StationDefReport.parseFrom(eachBattleReport.reportContent)
            }

            rt.addReports(reportBuilder)
        }

        return rt.build()
    }

    private fun getTrueInfo(
        allBattleReport: LinkedList<BattleReport>,
        lastId: Long,
        askNum: Int
    ): LinkedList<BattleReport> {
        val battleReports = LinkedList<BattleReport>()

        // 进游戏请求的
        if (lastId == 0L) {
            if (allBattleReport.size <= askNum) {
                battleReports += allBattleReport
            } else {
                battleReports += allBattleReport.subList(allBattleReport.size - askNum, allBattleReport.size)
            }
            return battleReports
        }

        val nowIndex = allBattleReport.indexOfFirst { it.id == lastId }

        // 如果没找到就是已经没有比当前更早的信息了.返回空
        if (nowIndex <= 0) {
            return battleReports
        }

        val subBattleReports = allBattleReport.subList(0, nowIndex)
        if (subBattleReports.size <= askNum) {
            battleReports += subBattleReports
        } else {
            battleReports += allBattleReport.subList(nowIndex - askNum, nowIndex)
        }

        return battleReports
    }

}


