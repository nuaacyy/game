package com.point18.slg2d.home.module.task

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeTaskDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetUnitTaskRewardEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.GetUnitTaskReward
import pb4client.GetUnitTaskRewardRt
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.*

// 领取章节任务奖励
class GetUnitTaskRewardDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC,
            HomeTaskDC>(
        HomePlayerDC::class.java,
        HomeTaskDC::class.java,
        Arrays.asList(
            resHelper,
            targetHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, homeTaskDC ->
            val unitTaskId = (msg as GetUnitTaskReward).unitTaskId

            val rt = GetUnitTaskRewardRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            rt.unitTaskId = unitTaskId

            val action = ACTION_GET_UNIT_TASK_REWARD

            // 检测是否所有的章节任务都已完成
            val unitTaskProto = pcs.unitTaskProtoCache.unitTaskProtoMap[unitTaskId]
            if (unitTaskProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                session.sendMsg(MsgType.GetUnitTaskReward_120, rt.build())
                return@prepare
            }

            for (t in unitTaskProto.tasks) {
                val taskVo = homeTaskDC.findTaskByProtoId(t)
                if (taskVo == null || taskVo.taskNowState == TaskgoAlong) {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    session.sendMsg(MsgType.GetUnitTaskReward_120, rt.build())
                    return@prepare
                }
            }

            val player = homePlayerDC.player

            if (unitTaskId != player.unitTaskId + 1) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.GetUnitTaskReward_120, rt.build())
                return@prepare
            }

            // 领取奖励,并且把章节任务中未领取的奖励也顺便领了
            val addRes = LinkedList<ResVo>()
            for (t in unitTaskProto.tasks) {
                val taskVo = homeTaskDC.findTaskByProtoId(t)
                if (taskVo == null) {
                    continue
                }
                if (taskVo.taskNowState == TaskHasFinish) {
                    val taskProto = pcs.questCache.findSpecTaskProto(taskVo.taskProtoId)
                    if (taskProto == null) {
                        continue
                    }
                    taskVo.taskNowState = TaskHasGetReward
                    addRes += taskProto.rewardResVo
                }
            }
            val rewardAction = ACTION_UNIT_REWARD
            resHelper.addRes(session, rewardAction, player, addRes)


            val rewardUnit = LinkedList<ResVo>()
            rewardUnit += unitTaskProto.rewardMap
            resHelper.addRes(session, action, player, rewardUnit)
            player.unitTaskId = unitTaskId
            val effectHelper = ResearchEffectHelper()
            fireEvent(session, GetUnitTaskRewardEvent(effectHelper))

            targetHelper.targetAddVal(session, MissionStrength)

            val askMsg = UpdateInfoByHomeAskReq.newBuilder()
            val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
            updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_UNITTASK
            updateInfoByHomeVo.updateValue = toJson(unitTaskId)
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

            session.sendMsg(MsgType.GetUnitTaskReward_120, rt.build())
            return@prepare
        }
    }
}