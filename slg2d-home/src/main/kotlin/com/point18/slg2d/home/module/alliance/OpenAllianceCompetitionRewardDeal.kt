package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.AllianceCompetitionRewardVo
import pb4client.AllianceCompetitionRewardsVo
import pb4client.OpenAllianceCompetitionRewardRt
import java.util.*

class OpenAllianceCompetitionRewardDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val rt = this.openAllianceCompetitionReward(session, homePlayerDC)
            session.sendMsg(MsgType.OpenAllianceCompetitionReward_907, rt)
        }
    }

    private fun openAllianceCompetitionReward(session: PlayerActor, homePlayerDC: HomePlayerDC): OpenAllianceCompetitionRewardRt {
        val rt = OpenAllianceCompetitionRewardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        if (player.allianceCompetitionTicket == NO_HAVE_ALLIANCE_COMPETITION_TICKET) {
            rt.rt = ResultCode.NO_JOIN_ALLIANCE_COMPETITION.code
            return rt.build()
        }

        for ((score, rewardInfo) in player.allianceCompetitionRewardMap) {
            val reward = LinkedList<AllianceCompetitionRewardVo>()
            for ((index, resString) in rewardInfo.reward) {
                val tmpBuilder = AllianceCompetitionRewardVo.newBuilder()
                tmpBuilder.reward = resString
                tmpBuilder.index = index
                reward.add(tmpBuilder.build())
            }
            val tmpVoBuilder = AllianceCompetitionRewardsVo.newBuilder()
            tmpVoBuilder.score = score
            tmpVoBuilder.isGet = rewardInfo.isGet
            tmpVoBuilder.addAllRs(reward)

            rt.addRewards(tmpVoBuilder)

        }

        return rt.build()
    }

}




