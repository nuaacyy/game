package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.intoNextStarAchievement
import pb4client.Achievement
import pb4client.AchievementInit
import pb4client.ProgressInfo

class AchievementInitNotifier(
    areaCache: AreaCache,
    playerId: Long
) {
    private val msg: AchievementInit.Builder = AchievementInit.newBuilder()

    init {
        val achievementTypeMap = areaCache.achievementCache.findAchievementTypeMapByPlayerId(playerId)
        for ((achievementType, achievements) in pcs.achievementProtoCache.achievementProtoMapByType) {
            val a1 = achievements[1] ?: continue
            val ex = achievementTypeMap[achievementType]
            if (ex == null) {
                //补全成就
                val achievementProto = pcs.achievementProtoCache.achievementProtoMap[a1.id]
                if (achievementProto != null) {
                    val progressMap = hashMapOf<Int, Long>()
                    for ((checkType, _) in achievementProto.completeCondMap) {
                        progressMap[checkType] = 0
                    }
                    val newAchievement = areaCache.achievementCache.createAchievement(playerId, a1.id, progressMap)
                    if (newAchievement != null) {
                        achievementTypeMap[achievementType] = newAchievement
                    }
                }
            }
        }

        for ((_, achievement) in achievementTypeMap) {
            var fillAchievement = achievement

            //校验已领取奖励的成就是否有下一星
            val newAchievement = intoNextStarAchievement(achievement)
            if (newAchievement != null) {
                fillAchievement = newAchievement
            }

            //填充4号消息
            val achievementBuilder = Achievement.newBuilder()

            achievementBuilder.id = fillAchievement.id
            achievementBuilder.protoId = fillAchievement.achievementId
            achievementBuilder.state = fillAchievement.state

            for ((checkType, progress) in fillAchievement.progressMap) {
                val p = ProgressInfo.newBuilder()
                p.checkType = checkType
                p.progress = progress.toInt()
                achievementBuilder.addAllProgress(p)
            }
            msg.addAchievement(achievementBuilder)
        }
    }

    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AchievementInit_3302, this.msg.build())
    }
}
