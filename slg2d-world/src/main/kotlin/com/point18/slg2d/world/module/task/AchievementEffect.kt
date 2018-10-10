package com.point18.slg2d.world.module.task

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.AchieveHasFinish
import com.point18.slg2d.common.constg.AchieveHasGetReward
import com.point18.slg2d.common.constg.taskCheckWhiteList
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.Achievement
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.AchievementChangeNotifier

fun handleStateEvent(areaCache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
    achievementEffect(eventType, event, areaCache, playerId)
}

/**
成就进度变化
 */
fun achievementEffect(eventType: EventType, event: Any, areaCache: AreaCache, playerId: Long) {
    val achievements = areaCache.achievementCache.findAchievementsByPlayerId(playerId)
    val session = fetchOnlinePlayerSession(areaCache, playerId)

    for ((_, achievement) in achievements) {
        val achievementProto = pcs.achievementProtoCache.achievementProtoMap[achievement.achievementId]
        if (achievementProto == null) {
            normalLog.error("成就模板不存在：${achievement.achievementId}")
            continue
        }
        if (achievementProto.id == 0) {
            continue
        }
        if (achievement.state == AchieveHasFinish || achievement.state == AchieveHasGetReward) {
            continue
        }

        // 获取这个任务的检测条件,过滤白名单
        var checkType = 0
        for ((ct, _) in achievementProto.completeCondMap) {
            checkType = ct
        }
        val whiteList = taskCheckWhiteList[checkType]
        if (whiteList == null || !whiteList.contains(eventType)) {
            // 说明这个事件根本就跟这个任务无关,过滤掉
            continue
        }

        val (isFinish, change) = achievementCheckIsFinish(areaCache, eventType, event, achievement, playerId)
        if (isFinish) {
            achievement.state = AchieveHasFinish
        }

        if (isFinish || change) {
            // 给客户端推送成就变化
            if (session != null) {
                val achievementChangeNotifier = AchievementChangeNotifier(
                    achievement.id,
                    achievement.achievementId,
                    achievement.state,
                    achievement.progressMap
                )
                achievementChangeNotifier.notice(session)
            }
        }
    }
}

//检测成就完成否
data class AchievementCheckIsFinishReturn(var b1: Boolean, val b2: Boolean)

fun achievementCheckIsFinish(
    areaCache: AreaCache,
    eventType: EventType,
    event: Any,
    achievementWrap: Achievement,
    playerId: Long
): (AchievementCheckIsFinishReturn) {
    val achievementProto = pcs.achievementProtoCache.achievementProtoMap[achievementWrap.achievementId]
    if (achievementProto == null) {
        normalLog.error("成就模板不存在：${achievementWrap.achievementId}")
        return AchievementCheckIsFinishReturn(false, false)
    }
    if (achievementProto.id == 0) {
        return AchievementCheckIsFinishReturn(false, false)
    }
    if (achievementWrap.state == AchieveHasFinish || achievementWrap.state == AchieveHasGetReward) {
        return AchievementCheckIsFinishReturn(false, false)
    }

    val progressMap = achievementWrap.progressMap
    var change = false
    var isFinish = true

    for ((checkType, values) in achievementProto.completeCondMap) {
        val checkHandle = TaskM.checkHandles[checkType]
        if (checkHandle == null) {
            return AchievementCheckIsFinishReturn(false, change)
        }

        var nowValue = 0L
        val p = progressMap[checkType]
        if (p != null) {
            nowValue = p
        }
        val (finish, num) = checkHandle.check(areaCache, eventType, event, playerId, values, nowValue)


        if (p == null || num != p.toLong()) {
            progressMap[checkType] = num
            change = true
        }
        if (!finish) {
            isFinish = false
        }
    }
    if (change) {
        achievementWrap.progressMap = progressMap
    }
    return AchievementCheckIsFinishReturn(isFinish, change)
}
