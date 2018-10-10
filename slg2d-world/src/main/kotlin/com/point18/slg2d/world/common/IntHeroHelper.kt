package com.point18.slg2d.world.common

import com.point18.slg2d.common.pc.pcs
import java.util.*

data class IntHeroData(
    val protoId: Int,
    val star: Int,
    val rank: Int
)

// 内政信息发生变化之后需要重新刷新计算的数据
fun getIntHeroEffect(heroList: LinkedList<IntHeroData>): (HashMap<Int, Int>) {
    val effectIds = hashMapOf<Int, Int>()
    for (hero in heroList) {
        val unitBasic = pcs.unitBaseCache.fetchProtoById(hero.protoId) ?: continue

        val heroRankProtoMap = pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId] ?: continue

        val heroRankProto = heroRankProtoMap[hero.rank] ?: continue

        for ((_, IntSkillIndex) in heroRankProto.rpgSkillLockMap) {
            var skillId = 0
            when (IntSkillIndex) {
                1 -> skillId = unitBasic.intSkill1
                2 -> skillId = unitBasic.intSkill2
                3 -> skillId = unitBasic.intSkill3
            }

            val intSkill = pcs.intSkillCache.intSkillMap[skillId] ?: continue

            val intSkillProtoMap = pcs.intSkillCache.intSkillLvMap[intSkill.skillId] ?: continue

            val intSkillProto = intSkillProtoMap[hero.star] ?: continue
            for ((k, v) in intSkillProto.skillEffsMap) {
                effectIds[k] = (effectIds[k] ?: 0) + v
            }
        }
    }

    return effectIds
}
