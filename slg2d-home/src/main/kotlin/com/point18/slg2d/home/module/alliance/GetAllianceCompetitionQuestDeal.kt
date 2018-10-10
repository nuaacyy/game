package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET
import com.point18.slg2d.common.constg.TaskAllianceCompetition
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeTaskDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.NoHandleEvent
import com.point18.slg2d.home.module.task.HandleTaskEffectAtEventFire
import pb4client.GetAllianceCompetitionQuest
import pb4client.GetAllianceCompetitionQuestRt
import pb4server.GetAllianceCompetitionQuestAskReq
import pb4server.Home2PublicAskResp
import java.util.Arrays.asList

class GetAllianceCompetitionQuestDeal(
    private val handleTaskEffectHelper: HandleTaskEffectAtEventFire = HandleTaskEffectAtEventFire()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, HomeTaskDC>(
    HomePlayerDC::class.java, HomeTaskDC::class.java,
    asList(handleTaskEffectHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, homeTaskDC: HomeTaskDC ->
            val index = (msg as GetAllianceCompetitionQuest).index
            val rt = this.getAllianceCompetitionQuest(session, index, homePlayerDC, homeTaskDC)
            if (rt != null) {
                session.sendMsg(MsgType.GetAllianceCompetitionQuest_908, rt)
            }
        }
    }

    private fun getAllianceCompetitionQuest(
        session: PlayerActor, index: Int, homePlayerDC: HomePlayerDC, homeTaskDC: HomeTaskDC
    ): (GetAllianceCompetitionQuestRt?) {
        val rt = GetAllianceCompetitionQuestRt.newBuilder()
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

        if (homeTaskDC.findTaskByTaskType(TaskAllianceCompetition).size != 0) {
            rt.rt = (ResultCode.HAVE_ALLIANCE_COMPETITION_QUEST.code)
            return rt.build()
        }

        if (player.allianceCompetitionGetTaskNum <= 0) {
            rt.rt = (ResultCode.ALLIANCE_COMPETITION_QUEST_NO_NUM.code)
            return rt.build()
        }

        fetchCompetitionQuest(session, player, player.allianceId, index)

        return null
    }

    // 获取联盟总动员任务
    private fun fetchCompetitionQuest(
        session: PlayerActor,
        player: HomePlayer,
        allianceId: Long,
        index: Int
    ) {

        val askMsg = GetAllianceCompetitionQuestAskReq.newBuilder()
        askMsg.inedx = index

        session.createACS<Home2PublicAskResp>()
            .ask(
                session.publicShardProxy,
                session.fillHome2PublicAskMsgHeader(allianceId) {
                    it.setGetAllianceCompetitionQuestAskReq(askMsg)
                },
                Home2PublicAskResp::class.java
            )
            .whenCompleteKt { askResp, askErr ->
                val rt = GetAllianceCompetitionQuestRt.newBuilder()
                rt.rt = ResultCode.SUCCESS.code
                if (askErr != null || askResp == null) {
                    // todo 重试...
                    rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code

                } else if (askResp.getAllianceCompetitionQuestAskRt.rt != ResultCode.SUCCESS.code) {
                    // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                    rt.rt = askResp.getAllianceCompetitionQuestAskRt.rt

                } else {
                    rt.index = index
                    rt.rt = askResp.getAllianceCompetitionQuestAskRt.rt

                    // 成功接取了一个联盟任务,即将创建这个任务
                    val allianceQuestProto =
                        pcs.allianceCompetitionQuestProtoCache.protoMapById[askResp.getAllianceCompetitionQuestAskRt.questId]
                    if (allianceQuestProto != null) {
                        handleTaskEffectHelper.initTask(session, allianceQuestProto.questId, NoHandleEvent())
                    } else {
                        println("总动员任务领取之后的创建失败,原因是找不到模版:${askResp.getAllianceCompetitionQuestAskRt.questId}")
                    }

                    player.allianceCompetitionGetTaskNum = player.allianceCompetitionGetTaskNum - 1
                }

                session.sendMsg(MsgType.GetAllianceCompetitionQuest_908, rt.build())
            }
    }
}