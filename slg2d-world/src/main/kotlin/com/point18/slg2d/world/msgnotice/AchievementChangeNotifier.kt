package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.area4data.PlayerSession
import pb4client.Achievement
import pb4client.AchievementChange
import pb4client.ProgressInfo

// 成就变化推送
class AchievementChangeNotifier(
    id: Long,
    protoId: Int,
    state: Int,
    progressMap: Map<Int, Long>
) {
    private val msg: AchievementChange.Builder = AchievementChange.newBuilder()

    init {
        val achievementBuilder = Achievement.newBuilder()
        achievementBuilder.id = id
        achievementBuilder.protoId = protoId
        achievementBuilder.state = state

        for ((checkType, progress) in progressMap) {
            val progressBuilder = ProgressInfo.newBuilder()
            progressBuilder.checkType = checkType
            progressBuilder.progress = progress.toInt()
            achievementBuilder.addAllProgress(progressBuilder)
        }
        msg.setAchievement(achievementBuilder)
    }

    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AchievementChange_3164, this.msg.build())
    }
}