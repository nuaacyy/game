package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.Achievement
import pb4client.AchievementChange
import pb4client.ProgressInfo

// 成就变化推送
data class AchievementChangeNotifier(
    val msg: pb4client.AchievementChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.AchievementChange_3164, this.msg.build())
    }
}

fun createAchievementChangeNotifier(
    id: Long,
    protoId: Int,
    state: Int,
    progressMap: HashMap<Int, Long>
): AchievementChangeNotifier {
    val achievementChangeBuilder = AchievementChange.newBuilder()
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
    achievementChangeBuilder.setAchievement(achievementBuilder)

    return AchievementChangeNotifier(achievementChangeBuilder)
}
