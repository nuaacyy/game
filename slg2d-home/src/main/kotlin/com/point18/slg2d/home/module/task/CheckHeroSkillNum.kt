package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 技能达到等级X的武将数量
class CheckHeroSkillNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val heroDC = checkDep.heroDC
        val heroMap = heroDC.findHeroMapByPlayer()
        var value = 0
        for ((_, hero) in heroMap) {
            val skillProto1 = pcs.heroSkillProtoCache.heroSkillMap[hero.skillId1]
            if (skillProto1 != null && skillProto1.level >= checkValue[0]) {
                value++
            }

            val skillProto2 = pcs.heroSkillProtoCache.heroSkillMap[hero.skillId2]
            if (skillProto2 != null && skillProto2.level >= checkValue[0]) {
                value++
            }

            val skillProto3 = pcs.heroSkillProtoCache.heroSkillMap[hero.skillId3]
            if (skillProto3 != null && skillProto3.level >= checkValue[0]) {
                value++
            }

            val skillProto4 = pcs.heroSkillProtoCache.heroSkillMap[hero.skillId4]
            if (skillProto4 != null && skillProto4.level >= checkValue[0]) {
                value++
            }
        }


        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value.toLong())
    }
}



