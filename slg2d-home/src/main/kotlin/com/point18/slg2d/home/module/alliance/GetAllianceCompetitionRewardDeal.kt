package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_GET_ALLIANCE_COMPETITION_QUEST_REWARD
import com.point18.slg2d.common.constg.ALLIANCE_COMPETITION_QUEST_REWARD_GET
import com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GetAllianceCompetitionReward
import pb4client.GetAllianceCompetitionRewardRt
import pb4client.GetAllianceCompetitionRewardVo
import pb4server.GetAllianceCompetitionRewardAskReq
import pb4server.Home2PublicAskResp
import java.util.*
import java.util.Arrays.asList

class GetAllianceCompetitionRewardDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val value = LinkedList((msg as GetAllianceCompetitionReward).getAllianceCompetitionRewardVosList)
            val rt = this.getAllianceCompetitionQuestReward(session, homePlayerDC, value)
            if (rt != null) {
                session.sendMsg(MsgType.GetAllianceCompetitionReward_913, rt)
            }
        }
    }

    private fun getAllianceCompetitionQuestReward(
        session: PlayerActor,
        homePlayerDC: HomePlayerDC,
        value: LinkedList<GetAllianceCompetitionRewardVo>
    ): GetAllianceCompetitionRewardRt? {
        val rt = GetAllianceCompetitionRewardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        if (value.size == 0) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

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

        val rewardMap = hashMapOf<Int, Int>() // 把客户端传来的数据转成map进行使用
        for (v in value) {
            val ex = rewardMap[v.score]
            if (ex != null) {
                rt.rt = ResultCode.GET_ALLIANCE_COMPETITION_REWARD_ERROR.code
                return rt.build()
            }
            rewardMap[v.score] = v.index
        }
        val playerReward = player.allianceCompetitionRewardMap
        // 先检测下数据
        for ((score, index) in rewardMap) {
            val playerRewardVo = playerReward[score]
            if (playerRewardVo == null) {
                rt.rt = (ResultCode.GET_ALLIANCE_COMPETITION_NO_FIND_REWARD_ERROR.code)
                return rt.build()
            }
            if (playerRewardVo.isGet == ALLIANCE_COMPETITION_QUEST_REWARD_GET) {
                rt.rt = (ResultCode.GET_ALLIANCE_COMPETITION_NO_FIND_REWARD_ERROR.code)
                return rt.build()
            }

            val ex = playerRewardVo.reward[index]
            if (ex == null) {
                rt.rt = (ResultCode.GET_ALLIANCE_COMPETITION_NO_FIND_REWARD_ERROR.code)
                return rt.build()
            }
        }

        var maxScore = 0
        for ((score, _) in rewardMap) {
            if (maxScore == 0) {
                maxScore = score
            } else {
                if (score > maxScore) {
                    maxScore = score
                }
            }
        }

        getCompetitionReward(session, player, player.allianceId, maxScore, rewardMap, resHelper)
        return null
    }

    // 领取联盟总动员阶段奖励
    private fun getCompetitionReward(
        session: PlayerActor, player: HomePlayer, allianceId: Long, maxScore: Int
        , rewardMap: Map<Int, Int>, resHelper: ResHelper
    ) {

        val askMsg = GetAllianceCompetitionRewardAskReq.newBuilder()
        askMsg.maxScore = maxScore

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setGetAllianceCompetitionRewardAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            val rt = GetAllianceCompetitionRewardRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            if (askErr != null || askResp == null) {
                // todo 重试...
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (askResp.getAllianceCompetitionRewardAskRt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                rt.rt = askResp.getAllianceCompetitionRewardAskRt.rt
            } else {
                rt.rt = askResp.getAllianceCompetitionRewardAskRt.rt
                val playerReward = player.allianceCompetitionRewardMap
                val rewards = LinkedList<ResVo>()

                for ((score, index) in rewardMap) {
                    val tmp = playerReward[score]
                    if (tmp == null) {
                        assert(false) { "playerReward have no this vo" }
                        rt.rt = ResultCode.PARAMETER_ERROR.code
                        session.sendMsg(MsgType.GetAllianceCompetitionReward_913, rt.build())
                        return@whenCompleteKt
                    }
                    tmp.isGet = ALLIANCE_COMPETITION_QUEST_REWARD_GET
                    val reward = tmp.reward[index]
                    if (reward == null) {
                        assert(false) { " have no this reward\n" }
                        rt.rt = ResultCode.PARAMETER_ERROR.code
                        session.sendMsg(MsgType.GetAllianceCompetitionReward_913, rt.build())
                        return@whenCompleteKt
                    }
                    val rewardRes = resStringToResVoList(reward)
                    if (rewardRes == null) {
                        assert(false) { " reward format error \n" }
                        rt.rt = ResultCode.PARAMETER_ERROR.code
                        session.sendMsg(MsgType.GetAllianceCompetitionReward_913, rt.build())
                        return@whenCompleteKt
                    }
                    rewards += rewardRes
                }
                player.allianceCompetitionRewardMap = playerReward


                resHelper.addRes(session, ACTION_GET_ALLIANCE_COMPETITION_QUEST_REWARD, player, rewards)
            }

            session.sendMsg(MsgType.GetAllianceCompetitionReward_913, rt.build())
        }
    }

}


