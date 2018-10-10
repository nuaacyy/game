package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.constg.AchieveHasFinish
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.msgnotice.AchievementChangeNotifier
import pb4server.AchieveFinishOnWorldTell

// 成就完成
class GmAchievementOver : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache
        val playerId = session.playerId

        // 格式1： -gm add 类型 数量
        // 格式2： -gm changeCD building
        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }

        if (messages.size != 3) {
            return 2
        }

        val achievementId = (messages[2].toIntOrNull())
        if (achievementId == null) {
            return 2
        }

        val achievementMap = areaCache.achievementCache.findAchievementsByPlayerId(playerId)
        val achievement = achievementMap[achievementId]
        if (achievement == null) {
            val tell = AchieveFinishOnWorldTell.newBuilder()
            tell.achievementId = achievementId
            areaCache.tellHome(areaCache.fillW2HTellMsgHeader(playerId) {
                it.setAchieveFinishOnWorldTell(tell)
            })
        } else {
            val achievementProto = pcs.achievementProtoCache.achievementProtoMap[achievementId]
            if (achievementProto == null) {
                return 2
            }

            val progressMap = hashMapOf<Int, Long>()
            for ((checkType, progress) in achievementProto.completeCondMap) {
                progressMap[checkType] = progress[progress.size - 1].toLong()
            }
            achievement.state = AchieveHasFinish
            achievement.progressMap = progressMap

            // 推送通知
            val achievementChangeNotifier = AchievementChangeNotifier(
                achievement.id,
                achievement.achievementId,
                achievement.state,
                achievement.progressMap
            )
            achievementChangeNotifier.notice(session)
        }

        return 1
    }
}