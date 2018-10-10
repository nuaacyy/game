package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.ACTION_GET_ACHIEVEMENT_REWARD
import com.point18.slg2d.common.constg.AchieveHasFinish
import com.point18.slg2d.common.constg.AchieveHasGetReward
import com.point18.slg2d.common.constg.AchievegoAlong
import com.point18.slg2d.common.pc.AchievementProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomeAchievementDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.askDeal.W2HAsk
import com.point18.slg2d.home.module.event.AchievementFinishEvent
import com.point18.slg2d.home.module.fireEvent
import pb4server.GetAchievementRewardAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp
import java.util.Arrays.asList

// 领取成就奖励
class GetAchievementRewardDeal(private val resHelper: ResHelper = ResHelper()) : W2HAsk,
    HomeHelperPlus2<HomePlayerDC, HomeAchievementDC>(
        HomePlayerDC::class.java,
        HomeAchievementDC::class.java,
        asList(resHelper)
    ) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val msg = req.getAchievementRewardAskReq

        prepare(session) { homePlayerDC: HomePlayerDC, homeAchievementDC: HomeAchievementDC ->
            val rt = GetAchievementRewardAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            val achievement = homeAchievementDC.findAchievementById(session, msg.taskId)
            if (achievement == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                resp.setGetAchievementRewardAskRt(rt)
                return@prepare
            }
            val achievementProto = pcs.achievementProtoCache.achievementProtoMap[achievement.achievementId]
            if (achievementProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                resp.setGetAchievementRewardAskRt(rt)
                return@prepare
            }

            // 检测客户端发来的数据是否准确
            if (achievement.state != AchieveHasFinish) {
                rt.rt = ResultCode.ACHIEVEMENT_NOT_FINISH.code
                resp.setGetAchievementRewardAskRt(rt)
                return@prepare
            }

            // 返回准确数据给客户端

            //添加成就奖励
            resHelper.addRes(
                session,
                ACTION_GET_ACHIEVEMENT_REWARD,
                homePlayerDC.player,
                achievementProto.rewards
            ) // 获取到本档奖励

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

                fireEvent(session, AchievementFinishEvent())
            }

            rt.id = achievement.id
            rt.protoId = achievement.achievementId
            rt.state = achievement.state
            rt.progressMap = toJson(achievement.progressMap)

            resp.setGetAchievementRewardAskRt(rt)
        }
    }
}
