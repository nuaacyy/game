package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.constg.JjcDef
import com.point18.slg2d.common.constg.JjcFight
import com.point18.slg2d.common.pc.pcs

// 完成竞技场英雄星数次数
class CheckJJcDefStarNum : AllCheck {
    override fun check(
        areaCache: AreaCache,
        ventType: EventType,
        event: Any,
        playerId: Long,
        checkValue: List<Int>,
        nowValue: Long
    ): AllCheckReturn {
        if (checkValue.size < 1) {
            return AllCheckReturn(false, 0)
        }

        val starProto = pcs.starAttributeWeekProtoCache.findStarAttributeWeekProtoByWeek()
        if (starProto == null) {
            return AllCheckReturn(false, 0)
        }

        // 取竞技场防守阵容
        val defPlan = areaCache.armyPlanCache.findArmyPlan(playerId, JjcFight, JjcDef)
        if (defPlan == null || defPlan.heroMap.size == 0) {
            return AllCheckReturn(false, 0)
        }

        var starNum = 0
        // 遍历英雄阵容,检测星象
        for ((_, heroID) in defPlan.heroMap) {
            val eachHero = areaCache.heroCache.findHeroById(playerId, heroID) ?: continue
            val heroProto = pcs.unitBaseCache.protoMap[eachHero.protoId] ?: continue

            //  如果这个英雄是这一周的星象,num++
            for (eachStar in starProto.starGroupSet) {
                if (heroProto.starAttributeMap.contains(eachStar)) {
                    starNum++
                    break
                }
            }
        }

        if (starNum >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }
        return AllCheckReturn(false, starNum.toLong())
    }
}