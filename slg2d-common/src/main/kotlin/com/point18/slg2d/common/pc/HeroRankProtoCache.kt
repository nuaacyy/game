package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class HeroRankResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<HeroRankProto>
): Serializable

data class HeroRankProto(
    val id: Int,  // 序号
    val heroId: Int,  // 英雄ID
    val rank: Int, // 阶数
    val time: Int,  // 进阶时间（秒）
    val solider: Int, // 带兵量
    val rpgSkillMax: String, // 当前阶下战斗技能最大等级
    val effect: String, // 增加属性
    val slgSkillLock: String,  //解锁技能位置
    val power: Int //实力
) : Serializable{
    var unlockSkillPosList: List<Int> = listOf()   //
    var rpgSkillMaxMap: Map<Int, Int> = mapOf()          //// 各位置技能的上限等级
    var rpgSkillLockMap: Map<Int, Int> = mapOf()
    var rankEffectMap: Map<Int, Double> = mapOf()

}

class HeroRankProtoCache : ProtoCacheInit("heroRank.xml") {
    var heroRankProtoCache: Map<Int, Map<Int, HeroRankProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<HeroRankResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as HeroRankResult

        val tmpHeroRankProtoCache: HashMap<Int, HashMap<Int, HeroRankProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val rpgSkillMaxMap = hashMapOf<Int, Int>()
            val rpgSkillMaxss = stringsSplit(vo.rpgSkillMax, "|")

            for (maxss in rpgSkillMaxss) {
                val maxInfo = stringsSplit(maxss, ";")

                if (maxInfo.size != 2) {
                    throw RuntimeException("heroRank.xml :: RpgSkillMax : $maxss")
                }
                val skillIndex = strconvAtoi(maxInfo[0])
                val skillMaxLv = strconvAtoi(maxInfo[1])
                if (skillIndex != null && skillMaxLv != null) {
                    rpgSkillMaxMap[skillIndex] = skillMaxLv
                }
            }
            vo.rpgSkillMaxMap = rpgSkillMaxMap

            val vorankEffectMap =
                parseFloatMap(vo.effect) ?: throw RuntimeException("heroRank.xml :: rankEffectMap : ${vo.effect}")

            val unlockSkillPosList = LinkedList<Int>()
            val unlockSlgSkillStrs = stringsSplit(vo.slgSkillLock, "|")

            vo.rankEffectMap = vorankEffectMap
            for (skill in unlockSlgSkillStrs) {
                val skillId = strconvAtoi(skill) ?: throw RuntimeException("heroStar.xml :: RpgSkillMax ")
                unlockSkillPosList.add(skillId)
            }
            vo.unlockSkillPosList = unlockSkillPosList

            val tmp = tmpHeroRankProtoCache.getOrPut(vo.heroId) { hashMapOf() }
            tmp[vo.rank] = vo

        }
        this.heroRankProtoCache = tmpHeroRankProtoCache
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}