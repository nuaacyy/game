package com.point18.slg2d.home.module.task

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.common.UiConditionCheckHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeTaskDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetTaskRewardEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.GetTaskReward
import pb4client.GetTaskRewardRt
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.*

// 领取任务奖励
class TaskRewardDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val uiConditionHelper: UiConditionCheckHelper = UiConditionCheckHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, HomeTaskDC>(
        HomePlayerDC::class.java,
        HomeTaskDC::class.java,
        Arrays.asList(
            resHelper,
            targetHelper,
            uiConditionHelper
        )
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, homeTaskDC ->
            val taskId = (msg as GetTaskReward).taskId

            val rt = GetTaskRewardRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            rt.taskId = taskId
            var action = ACTION_MISSION_REWARD

            val taskVo = homeTaskDC.findTaskById(taskId)

            if (taskVo == null || taskVo.taskNowState != TaskHasFinish) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.GetTaskReward_113, rt.build())
                return@prepare
            }

            val taskProto = pcs.questCache.findSpecTaskProto(taskVo.taskProtoId)
            if (taskProto == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.GetTaskReward_113, rt.build())
                return@prepare
            }

            // 这个不是联盟总动员任务的入口
            if (taskProto.type == TaskAllianceCompetition) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.GetTaskReward_113, rt.build())
                return@prepare
                
            } else if (taskProto.type == TaskNorMal) {
                // 功能开启
                val re = uiConditionHelper.uiConditionCheck(session, MAIN_TASK_OPEN)
                if (re != ResultCode.SUCCESS.code) {
                    rt.rt = re
                    session.sendMsg(MsgType.GetTaskReward_113, rt.build())
                    return@prepare
                }
            }

            val player = homePlayerDC.player

            taskVo.taskNowState = TaskHasGetReward

            if (taskProto.type == TaskChapter) {
                action = ACTION_UNIT_REWARD
            }

            val effectHelper = ResearchEffectHelper()
            resHelper.addRes(session, action, player, taskProto.rewardResVo)
            fireEvent(session, GetTaskRewardEvent(effectHelper))

            targetHelper.targetAddVal(session, MissionStrength)

            val askMsg = UpdateInfoByHomeAskReq.newBuilder()
            val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
            updateInfoByHomeVo.updateType = UPDATE_INFO_BY_TASK_FINISH
            updateInfoByHomeVo.updateValue = toJson(taskVo.taskProtoId)
            askMsg.addUpdates(updateInfoByHomeVo)


            session.createACS<Home2WorldAskResp>()
                .ask(
                    session.worldShardProxy,
                    session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
                    Home2WorldAskResp::class.java
                )
                .whenCompleteKt { askResp, askErr ->
                    try {
                        when {
                            askErr != null -> {
                                // TODO
                            }

                            askResp == null -> {
                                // TODO
                            }

                            else -> {
                                // TODO
                            }
                        }

                    } catch (e: Exception) {
                        // TODO
                    }
                }

            session.sendMsg(MsgType.GetTaskReward_113, rt.build())
            return@prepare
        }
    }
}