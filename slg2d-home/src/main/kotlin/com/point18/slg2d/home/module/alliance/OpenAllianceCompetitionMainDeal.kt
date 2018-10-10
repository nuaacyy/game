package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.AllianceCompetitionInfo
import pb4client.AllianceCompetitionQuest
import pb4client.OpenAllianceCompetitionMainRt
import pb4server.Home2PublicAskResp
import pb4server.OpenAllianceCompetitionMainAskReq

class OpenAllianceCompetitionMainDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val rt = openAllianceCompetitionMain(session, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.OpenAllianceCompetitionMain_906, rt)
            }
        }
    }

    private fun openAllianceCompetitionMain(session: PlayerActor, homePlayerDC: HomePlayerDC): OpenAllianceCompetitionMainRt? {
        val rt = OpenAllianceCompetitionMainRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.overTime = 0
        rt.nowScore = 0
        rt.joinPlayerNum = 0

        val player = homePlayerDC.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        openCompetitionMain(session, player, player.allianceId)

        return null
    }

    // 打开联盟总动员主界面
    private fun openCompetitionMain(session: PlayerActor, player: HomePlayer, allianceId: Long) {

        val askMsg = OpenAllianceCompetitionMainAskReq.newBuilder()

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setOpenAllianceCompetitionMainAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            val rt2client = OpenAllianceCompetitionMainRt.newBuilder()
            rt2client.rt = ResultCode.SUCCESS.code
            rt2client.overTime = 0
            rt2client.nowScore = 0
            rt2client.joinPlayerNum = 0
            if (askErr != null || askResp == null) {
                // todo 重试...
                rt2client.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (askResp.openAllianceCompetitionMainAskRt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                rt2client.rt = askResp.openAllianceCompetitionMainAskRt.rt
            } else {
                val childRt = AllianceCompetitionInfo.newBuilder()
                childRt.allianceCompetitionId = player.allianceCompetitionId
                childRt.allianceCompetitionTicket = player.allianceCompetitionTicket
                childRt.allianceCompetitionGetTaskNum = player.allianceCompetitionGetTaskNum
                childRt.allianceCompetitionBuyTaskNum = player.allianceCompetitionBuyTaskNum
                childRt.allianceCompetitionRankLv = player.allianceCompetitionRankLv


                rt2client.rt = askResp.openAllianceCompetitionMainAskRt.rt
                rt2client.overTime = askResp.openAllianceCompetitionMainAskRt.rewardTime
                rt2client.nowScore = askResp.openAllianceCompetitionMainAskRt.nowScore
                rt2client.joinPlayerNum = askResp.openAllianceCompetitionMainAskRt.joinPlayerNum
                rt2client.myQuest = childRt.build()

                for (quest in askResp.openAllianceCompetitionMainAskRt.questsList) {
                    val q = AllianceCompetitionQuest.newBuilder()
                    q.index = quest.index
                    q.questId = quest.questId
                    q.refTime = quest.refTime
                    rt2client.addQuests(q)
                }

            }

            session.sendMsg(MsgType.OpenAllianceCompetitionMain_906, rt2client.build())
        }
    }

}



