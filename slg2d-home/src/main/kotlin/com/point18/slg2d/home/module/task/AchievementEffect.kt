package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.constg.AchieveHasFinish
import com.point18.slg2d.common.constg.AchieveHasGetReward
import com.point18.slg2d.common.constg.taskCheckWhiteList
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus12
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.msgnotice.createAchievementChangeNotifier

class HandleAchievementAtEventFire : HomeHelperPlus12<HomeAchievementDC,
        HomePlayerDC,
        HomeMyTargetDC,
        HomeTaskDC,
        HeroDC,
        IconDC,
        InnerCityDC,
        LibraryDC,
        NewEquipDC,
        SkinDC,
        VipDC,
        BagDC>(
    HomeAchievementDC::class.java,
    HomePlayerDC::class.java,
    HomeMyTargetDC::class.java,
    HomeTaskDC::class.java,
    HeroDC::class.java,
    IconDC::class.java,
    InnerCityDC::class.java,
    LibraryDC::class.java,
    NewEquipDC::class.java,
    SkinDC::class.java,
    VipDC::class.java,
    BagDC::class.java
) {
    /**
     * 成就进度变化
     */
    fun achievementEffect(session: PlayerActor, event: EventData) {
        prepare(session) { homeAchievementDC: HomeAchievementDC, homePlayerDC: HomePlayerDC, homeMyTargetDC: HomeMyTargetDC, homeTaskDC: HomeTaskDC,
                           heroDC: HeroDC, iconDC: IconDC, innerCityDC: InnerCityDC, libraryDC: LibraryDC,
                           newEquipDC: NewEquipDC, skinDC: SkinDC, vipDC: VipDC, bagDC: BagDC ->
            val checkDep = CheckDep(
                homePlayerDC, bagDC, homeMyTargetDC, homeTaskDC,
                heroDC, iconDC, innerCityDC, libraryDC,
                newEquipDC, skinDC, vipDC
            )

            val achievements = homeAchievementDC.findAchievementsByPlayerId(session)

            for ((_, achievement) in achievements) {
                val achievementProto = pcs.achievementProtoCache.findSpecAchProto(achievement.achievementId)
                if (achievementProto == null) {
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
                if (whiteList == null || !whiteList.contains(event.eventType)) {
                    // 说明这个事件根本就跟这个任务无关,过滤掉
                    continue
                }

                val (isFinish, change) = achievementCheckIsFinish(session, checkDep, event, achievement)
                if (isFinish) {
                    achievement.state = AchieveHasFinish
                }
                if (isFinish || change) {
                    // 给客户端推送成就变化
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
    }

    data class AchievementCheckIsFinishReturn(var b1: Boolean, val b2: Boolean)

    /** 检测成就完成否 **/
    private fun achievementCheckIsFinish(
        session: PlayerActor,
        checkDep: CheckDep,
        event: EventData,
        achievementWrap: HomeAchievement
    ): AchievementCheckIsFinishReturn {
        val achievementProto = pcs.achievementProtoCache.achievementProtoMap[achievementWrap.achievementId]
        if (achievementProto == null) {
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
            val checkHandle = taskM.checkHandles[checkType]
            if (checkHandle == null) {
                return AchievementCheckIsFinishReturn(false, change)
            }

            var nowFinish = 0L
            val p = progressMap[checkType]
            if (p != null) {
                nowFinish = p
            }
            val (finish, num) = checkHandle.check(session, checkDep, values, event, nowFinish)
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
}



