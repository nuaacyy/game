package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.ALLIANCE_COMPETITION_QUEST_REWARD_NO
import com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.randomSelect
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.AllianceCompetitionRewardVo
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.P2HTell
import pb4server.Public2HomeTell
import java.util.*
import java.util.Arrays.asList

class AllianceCompetitionInfoChangeNotice2GDeal : P2HTell, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPublicTell(session: PlayerActor, playerId: Long, msg: Public2HomeTell) {
        val tellMsg = msg.allianceCompetitionInfoChangeNotic2GTell

        prepare(session) { homePlayerDC ->
            val player = homePlayerDC.player
            player.allianceCompetitionId = tellMsg.acInfo.allianceCompetitionId
            player.allianceCompetitionTicket = tellMsg.acInfo.allianceCompetitionTicket
            player.allianceCompetitionGetTaskNum = tellMsg.acInfo.allianceCompetitionGetTaskNum
            player.allianceCompetitionBuyTaskNum = tellMsg.acInfo.allianceCompetitionBuyTaskNum
            player.allianceCompetitionRankLv = tellMsg.acInfo.allianceCompetitionRankLv

            if (tellMsg.acInfo.allianceCompetitionTicket == NO_HAVE_ALLIANCE_COMPETITION_TICKET) {
                player.allianceCompetitionMyScore = 0
            }
            player.allianceCompetitionRewardMap = hashMapOf()
            if (tellMsg.isRefReward == 1) {
                val rewardMap = hashMapOf<Int, AllianceCompetitionRewardVo>()
                // 要刷奖励
                val scoreProtoMap =
                    pcs.allianceCompetitionRewardProtoCache.allianceCompetitionRewardProtoMapByLvAndScore[tellMsg.acInfo.allianceCompetitionRankLv]
                if (scoreProtoMap == null) {
                    return@prepare
                }
                var findMaxValue = 0
                for ((_, scoreProto) in scoreProtoMap) {
                    if (findMaxValue == 0) {
                        findMaxValue = scoreProto.stage
                    } else {
                        if (scoreProto.stage > findMaxValue) {
                            findMaxValue = scoreProto.stage
                        }
                    }
                }

                for (i in 1..findMaxValue) {
                    val scoreProto = pcs.allianceCompetitionRewardProtoCache.allianceCompetitionRewardProtoMap[i]
                    if (scoreProto == null) {
                        continue
                    }
                    val reward = hashMapOf<Int, String>()
                    for (index in scoreProto.dropPropsIdMap.indices) {
                        val dropPropId = scoreProto.dropPropsIdMap[index]
                        val propMap = pcs.dropPropsProtoCache.dropPropsMap[dropPropId]
                        if (propMap == null) {
                            continue
                        }

                        val prop = randomSelect(propMap.dropMap)
                        if (prop == null) {
                            continue
                        }
                        val res = LinkedList(asList(ResVo(RES_PROPS, prop.id.toLong(), prop.num.toLong())))
                        val resString = resVoToResString(res)
                        reward[index + 1] = resString
                    }
                    rewardMap[scoreProto.score] =
                            AllianceCompetitionRewardVo(ALLIANCE_COMPETITION_QUEST_REWARD_NO, reward)

                    player.allianceCompetitionRewardMap = rewardMap
                }
            }
        }
    }
}
