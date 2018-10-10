package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.event.AchievementFinish
import pb4client.Achievement
import pb4client.ProgressInfo
import pb4client.ReceiveAchievementReward
import pb4client.ReceiveAchievementRewardRt
import pb4server.GetAchievementRewardAskReq
import pb4server.World2HomeAskResp
import com.point18.slg2d.common.pc.AchievementProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.addResToHome

// 领取成就奖励
class GetAchievementRewardDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val id = (msg as ReceiveAchievementReward).id
        // 数据返回定义
        val rt = getAchievementReward(session, id)
        // 发送数据
        if (rt != null) {
            session.sendMsg(MsgType.ReceiveAchievementReward_1451, rt)
        }
    }

    // 领取奖励
    private fun getAchievementReward(session: PlayerSession, id: Long): (ReceiveAchievementRewardRt?) {
        // 准备数据
        val rt = ReceiveAchievementRewardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val achievementInfoBuilder = Achievement.newBuilder()

        val areaCache = session.areaCache
        val playerId = session.playerId

        val player = session.player

        val achievement = areaCache.achievementCache.findAchievementById(playerId, id)
        if (achievement == null) {
            // 去home继续执行
            val msg = GetAchievementRewardAskReq.newBuilder()
            msg.taskId = id
            areaCache.worldActor.createACS<World2HomeAskResp>()
                .ask(
                    areaCache.worldActor.homeShardRegion,
                    areaCache.fillW2HAskMsgHeader(session.playerId) {
                        it.setGetAchievementRewardAskReq(msg)
                    },
                    World2HomeAskResp::class.java)
                .whenCompleteKt { hrt, err ->
                    if (err != null || hrt == null) {
                        rt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.ReceiveAchievementReward_1451, rt.build())
                        return@whenCompleteKt
                    }
                    val askRt = hrt.getAchievementRewardAskRt
                    if (askRt.rt != ResultCode.SUCCESS.code) {
                        rt.rt = askRt.rt
                        session.sendMsg(MsgType.ReceiveAchievementReward_1451, rt.build())
                    } else {
                        achievementInfoBuilder.id = askRt.id
                        achievementInfoBuilder.protoId = askRt.protoId
                        achievementInfoBuilder.state = askRt.state
                        val progressMap: HashMap<Int, Long> =  toObj(askRt.progressMap)
                        for ((checkType, progress) in progressMap) {
                            val p = ProgressInfo.newBuilder()
                            p.checkType = checkType
                            p.progress = progress.toInt()
                            achievementInfoBuilder.addAllProgress(p)
                        }
                        rt.addAchieveInfo(achievementInfoBuilder)
                        session.sendMsg(MsgType.ReceiveAchievementReward_1451, rt.build())
                    }
                }
            return null
        } else {
            val achievementProto = pcs.achievementProtoCache.achievementProtoMap[achievement.achievementId]
            if (achievementProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            // 检测客户端发来的数据是否准确
            if (achievement.state != AchieveHasFinish) {
                rt.rt = ResultCode.ACHIEVEMENT_NOT_FINISH.code
                return rt.build()
            }

            // 返回准确数据给客户端

            //添加成就奖励
            addResToHome(areaCache, ACTION_GET_ACHIEVEMENT_REWARD, player.id, achievementProto.rewards) // 获取到本档奖励

            // 维护数据
            // 领取之后判断是否有下一条任务需要出现
            val nextAchievementProto: AchievementProto? =
                pcs.achievementProtoCache.achievementProtoMap[achievementProto.endCon] // 是否有下个任务
            if (nextAchievementProto == null) {
                achievement.state = AchieveHasGetReward
            } else {
                val progressMap = hashMapOf<Int, Long>()
                for ((checkType, _) in nextAchievementProto.completeCondMap) {
                    progressMap[checkType] = 0
                }
                achievement.achievementId = nextAchievementProto.id
                achievement.state = AchievegoAlong
                achievement.progressMap = progressMap

                wpm.es.fire(areaCache, playerId, ACHIEVEMENT_FINISH,
                    AchievementFinish()
                ) // 抛出事件来跟踪新任务的进度
            }

            achievementInfoBuilder.id = achievement.id
            achievementInfoBuilder.protoId = achievement.achievementId
            achievementInfoBuilder.state = achievement.state
            for ((checkType, progress) in achievement.progressMap) {
                val p = ProgressInfo.newBuilder()
                p.checkType = checkType
                p.progress = progress.toInt()
                achievementInfoBuilder.addAllProgress(p)
            }
            rt.addAchieveInfo(achievementInfoBuilder)

            return rt.build()
        }
    }
}