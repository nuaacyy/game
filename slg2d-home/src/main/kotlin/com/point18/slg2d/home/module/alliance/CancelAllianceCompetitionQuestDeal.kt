package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.CANCEL_ALLIANCE_COMPETITION_QUEST
import com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.hasRight
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.CancelAllianceCompetitionQuest
import pb4client.CancelAllianceCompetitionQuestRt
import pb4server.CancelAllianceCompetitionQuestAskReq
import pb4server.Home2PublicAskResp

class CancelAllianceCompetitionQuestDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val index = (msg as CancelAllianceCompetitionQuest).index
            val rt = cancelAllianceCompetitionQuest(session, index, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.CancelAllianceCompetitionQuest_912, rt)
            }
        }
    }

    private fun cancelAllianceCompetitionQuest(
        session: PlayerActor, index: Int, homePlayerDC: HomePlayerDC
    ): CancelAllianceCompetitionQuestRt? {
        val rt = CancelAllianceCompetitionQuestRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.index = index
        val player = homePlayerDC.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        if (player.allianceId != player.allianceCompetitionId) {
            rt.rt = (ResultCode.ALLIANCE_COMPETITION_ALLIANCE_ERROR.code)
            return rt.build()
        }

        if (player.allianceCompetitionTicket == NO_HAVE_ALLIANCE_COMPETITION_TICKET) {
            rt.rt = (ResultCode.NO_JOIN_ALLIANCE_COMPETITION.code)
            return rt.build()
        }

        if (!hasRight(player, CANCEL_ALLIANCE_COMPETITION_QUEST)) {
            rt.rt = (ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code)
            return rt.build()
        }

        cancelCompetitionQuest(session, player.allianceId, index)

        return null
    }

    // 有权限的人取消掉任务
    private fun cancelCompetitionQuest(session: PlayerActor, allianceId: Long, index: Int) {

        val askMsg = CancelAllianceCompetitionQuestAskReq.newBuilder()
        askMsg.inedx = index

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setCancelAllianceCompetitionQuestAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            val rt = CancelAllianceCompetitionQuestRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            if (askErr != null || askResp == null) {
                // todo 重试...
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (askResp.cancelAllianceCompetitionQuestAskRt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                rt.rt = askResp.cancelAllianceCompetitionQuestAskRt.rt
            } else {
                rt.index = index
                rt.rt = askResp.cancelAllianceCompetitionQuestAskRt.rt
            }

            session.sendMsg(MsgType.CancelAllianceCompetitionQuest_912, rt.build())
        }
    }
}


