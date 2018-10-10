package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET
import com.point18.slg2d.common.constg.TaskAllianceCompetition
import com.point18.slg2d.common.constg.TaskgoAlong
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeTaskDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.RemoveAllianceCompetitionQuestRt
import pb4server.RemoveTaskToWorldTell

class RemoveAllianceCompetitionQuestDeal : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, HomeTaskDC>(
    HomePlayerDC::class.java, HomeTaskDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, homeTaskDC: HomeTaskDC ->
            val rt = this.removeAllianceCompetitionQuest(session, homePlayerDC, homeTaskDC)
            if (rt != null) {
                session.sendMsg(MsgType.RemoveAllianceCompetitionQuest_909, rt)
            }
        }
    }

    private fun removeAllianceCompetitionQuest(
        session: PlayerActor, homePlayerDC: HomePlayerDC, homeTaskDC: HomeTaskDC
    ): (RemoveAllianceCompetitionQuestRt?) {
        val rt = RemoveAllianceCompetitionQuestRt.newBuilder()
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
            rt.rt = (ResultCode.NO_HAVE_ALLIANCE_COMPETITION_QUEST.code)
            return rt.build()
        }

        val delTask = allianceCompetitionTasks[0]

        if (delTask.taskNowState != TaskgoAlong) {
            rt.rt = (ResultCode.NO_HAVE_ALLIANCE_COMPETITION_QUEST.code)
            return rt.build()
        }

        if (delTask.onWorld != 0) {
            // 任务如果是世界的话 tell过去
            val tell = RemoveTaskToWorldTell.newBuilder()
            tell.taskProtoId = delTask.taskProtoId
            session.tellWorld(session.fillHome2WorldTellMsgHeader {
                it.setRemoveTaskToWorldTell(tell)
            })
        }

        homeTaskDC.deleteTask(delTask)

        return rt.build()
    }

}

