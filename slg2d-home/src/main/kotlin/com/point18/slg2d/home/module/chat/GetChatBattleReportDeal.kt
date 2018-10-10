package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.*
import pb4server.AskFightInfoDetailAskReq
import pb4server.Home2HomeAskResp

class GetChatBattleReportDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) {
            val reportId = (msg as GetShareBattle).battleInfoId
            val worldId = msg.worldId
            val reportOwner = msg.reportOwner

            askWorldBattleReport(session, reportId, worldId, reportOwner)
        }
    }

    private fun askWorldBattleReport(session: PlayerActor, reportId: Long, worldId: Long, ownerId: Long) {
        val askMsg = AskFightInfoDetailAskReq.newBuilder()
        askMsg.reportId = reportId
        askMsg.reportOwner = ownerId
        session.createACS<Home2HomeAskResp>().ask(
            session.homeShardProxy,
            session.fillHome2HomeAskMsgHeader {
                it.setAskFightInfoDetailAskReq(askMsg)
                it.playerId = ownerId
            },
            Home2HomeAskResp::class.java
        ).whenCompleteKt { wrt, askErr ->
            try {
                when {
                    askErr != null -> {
                        val rt = GetShareBattleRt.newBuilder()
                        rt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.ChatFightInfoDetail_106, rt.build())
                    }
                    wrt == null -> {
                        val rt = GetShareBattleRt.newBuilder()
                        rt.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.ChatFightInfoDetail_106, rt.build())
                    }
                    else -> {
                        val rt = GetShareBattleRt.newBuilder()
                        if (wrt.askFightInfoDetailAskRt.rt != ResultCode.SUCCESS.code) {
                            rt.rt = wrt.askFightInfoDetailAskRt.rt
                            session.sendMsg(MsgType.ChatFightInfoDetail_106, rt.build())
                        } else {
                            rt.rt = wrt.askFightInfoDetailAskRt.rt
                            val battleReportInfo = BattleReportInfo.newBuilder()
                            battleReportInfo.id = wrt.askFightInfoDetailAskRt.reportId
                            battleReportInfo.readState = wrt.askFightInfoDetailAskRt.readState       // 阅读状态 0-未读 1-已读
                            battleReportInfo.reportType = wrt.askFightInfoDetailAskRt.reportType      // 战报类型
                            battleReportInfo.fightTime = wrt.askFightInfoDetailAskRt.fightTime        // 战斗时间
                            battleReportInfo.fightAddressX = wrt.askFightInfoDetailAskRt.fightAddressX  // 战斗地点X坐标
                            battleReportInfo.fightAddressY = wrt.askFightInfoDetailAskRt.fightAddressY  // 战斗地点Y坐标
                            battleReportInfo.archived = wrt.askFightInfoDetailAskRt.reportId       // 是否被收藏了。时间的秒数
                            when (wrt.askFightInfoDetailAskRt.reportType) {
                                FIGHT_PLAYER_REPORT ->
                                    battleReportInfo.pvpTroopsFightReport = PvpFightReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                ATK_MONSTER_REPORT ->
                                    battleReportInfo.hunterFightReport = HunterFightReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                ATK_ALLIANCE_MONSTER_REPORT ->
                                    battleReportInfo.allianceHunterFightReport =
                                        HunterFightReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                ATK_WORLD_MONSTER_REPORT ->
                                    battleReportInfo.worldHunterFightReport = HunterFightReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                FIGHT_RELIC_REPORT ->
                                    battleReportInfo.massRuinsFightReport = MassRuinsFightReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                SCOUT_REPORT ->
                                    battleReportInfo.scoutReport = ScoutReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                BE_SCOUT_REPORT ->
                                    battleReportInfo.beScoutReport = BeScoutReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                FARM_REPORT ->
                                    battleReportInfo.collectReport = CollectReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                TRANSPORT_REPORT ->
                                    battleReportInfo.transportReport = TransportReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                HUNTER_CALL_REPORT ->
                                    battleReportInfo.hunterCallInfo =
                                        HunterCallInfo.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                JJC_FIGHT_REPORT ->
                                    battleReportInfo.jjcFightReport = JjcFightReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                                STATION_DEF_REPORT ->
                                    battleReportInfo.stationDefReport = StationDefReport.parseFrom(wrt.askFightInfoDetailAskRt.reportContent)
                            }
                            rt.reports = battleReportInfo.build()
                            session.sendMsg(MsgType.ChatFightInfoDetail_106, rt.build())
                        }
                    }
                }

            } catch (e: Exception) {
                normalLog.error("AskFightInfoDetailAskReq Error!", e)
                val rt = GetShareBattleRt.newBuilder()
                rt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.ChatFightInfoDetail_106, rt.build())
            }
        }
    }
}