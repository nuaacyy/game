package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET
import com.point18.slg2d.common.constg.TaskAllianceCompetition
import com.point18.slg2d.common.constg.TaskHasFinish
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetAllianceCompetitionQuestRewardEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.RewardAllianceCompetitionQuestRt
import pb4server.Home2PublicAskResp
import pb4server.RewardAllianceCompetitionQuestAskReq

class RewardAllianceCompetitionQuestDeal : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, HomeTaskDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, HomeTaskDC::class.java, HomeMyTargetDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, homeTaskDC: HomeTaskDC, homeMyTargetDC: HomeMyTargetDC ->
            val rt = this.rewardAllianceCompetitionQuest(
                session, homePlayerDC, homeTaskDC, homeMyTargetDC
            )
            if (rt != null) {
                session.sendMsg(MsgType.RewardAllianceCompetitionQuest_911, rt)
            }
        }
    }

    private fun rewardAllianceCompetitionQuest(
        session: PlayerActor, homePlayerDC: HomePlayerDC, homeTaskDC: HomeTaskDC, homeMyTargetDC: HomeMyTargetDC
    ): (RewardAllianceCompetitionQuestRt?) {
        val rt = RewardAllianceCompetitionQuestRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

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

        val allianceCompetitionTasks = homeTaskDC.findTaskByTaskType(TaskAllianceCompetition)
        if (allianceCompetitionTasks.size != 1) {
            rt.rt = (ResultCode.ALLIANCE_COMPETITION_QUEST_STATE_NOT_REMOVE.code)
            return rt.build()
        }

        val nowTask = allianceCompetitionTasks[0]

        if (nowTask.taskNowState != TaskHasFinish) {
            rt.rt = (ResultCode.ALLIANCE_COMPETITION_QUEST_NOT_FINISH.code)
            return rt.build()
        }

        val questProto = pcs.allianceCompetitionQuestProtoCache.protoMapByQuestId[nowTask.taskProtoId]
        if (questProto == null) {
            rt.rt = (ResultCode.NO_PROTO.code)
            return rt.build()
        }

        rewardCompetitionQuest(session, player, homeMyTargetDC, homeTaskDC, nowTask, player.allianceId, questProto.reward)

        return null
    }

    // 获取联盟总动员任务奖励,上传任务积分到联盟去
    private fun rewardCompetitionQuest(
        session: PlayerActor,
        player: HomePlayer,
        homeMyTargetDC: HomeMyTargetDC,
        homeTaskDC: HomeTaskDC,
        nowTask: HomeTask,
        allianceId: Long,
        addScore: Int
    ) {

        val askMsg = RewardAllianceCompetitionQuestAskReq.newBuilder()
        askMsg.addScore = addScore

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setRewardAllianceCompetitionQuestAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            val rt = RewardAllianceCompetitionQuestRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            if (askErr != null || askResp == null) {
                // todo 重试...
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (askResp.rewardAllianceCompetitionQuestAskRt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                rt.rt = askResp.rewardAllianceCompetitionQuestAskRt.rt
            } else {
                rt.rt = askResp.rewardAllianceCompetitionQuestAskRt.rt
                homeTaskDC.deleteTask(nowTask) // 同步完之后就删除这个任务
                homeMyTargetDC.targetInfo.allianceCompetitionTaskNum++
                homeMyTargetDC.targetInfo.allianceCompetitionScore += addScore

                player.allianceCompetitionMyScore += addScore

                fireEvent(session, GetAllianceCompetitionQuestRewardEvent(addScore))
            }

            session.sendMsg(MsgType.RewardAllianceCompetitionQuest_911, rt.build())
        }
    }

}




