package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_BUY_ALLIANCE_COMPETITION_QUEST_NUM
import com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BuyAllianceCompetitionQuestRt
import java.util.Arrays.asList

class BuyAllianceCompetitionQuestDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val rt = this.buyAllianceCompetitionQuest(session, homePlayerDC)
            session.sendMsg(MsgType.BuyAllianceCompetitionQuest_910, rt)
        }
    }

    private fun buyAllianceCompetitionQuest(session: PlayerActor, homePlayerDC: HomePlayerDC): BuyAllianceCompetitionQuestRt {
        val rt = BuyAllianceCompetitionQuestRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val player = homePlayerDC.player

        if (player.allianceId != player.allianceCompetitionId) {
            rt.rt = (ResultCode.ALLIANCE_COMPETITION_ALLIANCE_ERROR.code)
            return rt.build()
        }

        if (player.allianceCompetitionTicket == NO_HAVE_ALLIANCE_COMPETITION_TICKET) {
            rt.rt = (ResultCode.NO_JOIN_ALLIANCE_COMPETITION.code)
            return rt.build()
        }

        if (player.allianceCompetitionBuyTaskNum != 0) {
            rt.rt = (ResultCode.BUY_ALLIANCE_COMPETITION_QUEST_MAX.code)
            return rt.build()
        }

        if (player.allianceCompetitionGetTaskNum != 0) {
            rt.rt = (ResultCode.ALLIANCE_COMPETITION_QUEST_NUM_ENOUGH.code)
            return rt.build()
        }

        val checkCost = resHelper.checkRes(session, pcs.basicProtoCache.allianceCompetitionQuestValue)
        if (!checkCost) {
            rt.rt = (ResultCode.LESS_RESOUCE.code)
            return rt.build()
        }

        resHelper.costRes(session, ACTION_BUY_ALLIANCE_COMPETITION_QUEST_NUM, player, pcs.basicProtoCache.allianceCompetitionQuestValue)

        player.allianceCompetitionBuyTaskNum = player.allianceCompetitionBuyTaskNum + 1
        player.allianceCompetitionGetTaskNum = player.allianceCompetitionGetTaskNum + 1

        return rt.build()
    }
}

