package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.AchieveHasFinish
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeAchievementDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.W2HTell
import com.point18.slg2d.home.msgnotice.createAchievementChangeNotifier
import pb4server.World2HomeTell

// gm成就完成
class AchieveFinishOnWorldDeal : W2HTell, HomeHelperPlus1<HomeAchievementDC>(HomeAchievementDC::class.java) {

    override fun dealWorldTell(session: PlayerActor, playerId: Long, msg: World2HomeTell) {
        val tell = msg.achieveFinishOnWorldTell

        prepare(session) { homeAchievementDC ->
            val achievementId = tell.achievementId
            val achievementMap = homeAchievementDC.findAchievementsByPlayerId(session)
            val achievement = achievementMap[achievementId]
            if (achievement == null) {
                return@prepare
            }

            val achievementProto = pcs.achievementProtoCache.achievementProtoMap[achievementId]
            if (achievementProto == null) {
                return@prepare
            }

            val progressMap = hashMapOf<Int, Long>()
            for ((checkType, progress) in achievementProto.completeCondMap) {
                progressMap[checkType] = progress[progress.size - 1].toLong()
            }
            achievement.state = AchieveHasFinish
            achievement.progressMap = progressMap

            val achievementChangeNotifier = createAchievementChangeNotifier(
                achievement.id,
                achievement.achievementId,
                achievement.state,
                achievement.progressMap
            )

            achievementChangeNotifier.notice(session)
        }
    }
}
