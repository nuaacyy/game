package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class HeroSkillResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<HeroSkillProto>
) : Serializable

data class HeroSkillProto(
    val id: Int,  //唯一ID
    val skillId: Int, //技能ID
    val level: Int, //技能等级
    val name: String, //技能名称
    val type: Int,  //技能类型	1追击  2主动   3指挥  4被动  5建筑技能
    val costMorale: Float, //释放消耗的士气
    val costGold: Int, //消耗金币
    val fightValue: Int,  //技能战力
    val range: Int, //技能释放距离
    val target: Int, //寻路判定目标 1=敌人，5=友军，不包括自己，0=无需判断
    val castAnimation: Int,  //技能前摇时间(毫秒)
    val singNum: Int, //吟唱技能两波生效时间间隔，单位：秒，支持小数
    val canStop: Int, // 该吟唱技能是否会被控制技能打断(0-会.1-不会被打断)
    val singTime: Int, //每次吟唱时间(毫秒)
    val lastTime: Int, //动作持续时间(毫秒)
    val skillEffs: String //技能效果集合串ID_ID_ID
) : Serializable {
    var skillEffList: List<Int> = listOf()  //技能效果列表

}

class HeroSkillProtoCache : ProtoCacheInit("heroSkill.xml") {
    var heroSkillMap: Map<Int, HeroSkillProto> = mapOf()
    var heroSkillStudyMap: Map<Int, Map<Int, HeroSkillProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<HeroSkillResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as HeroSkillResult

        val tmpHeroSkillMap: HashMap<Int, HeroSkillProto> = hashMapOf()
        val tmpHeroSkillStudyMap: HashMap<Int, HashMap<Int, HeroSkillProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val effs = stringsSplit(vo.skillEffs, ";")

            val skillEffList = LinkedList<Int>()
            for (v in effs) {
                val skillEffId = strconvAtoi(v)
                    ?: throw RuntimeException("skill.xml :: Id.${vo.id} HeroSkillEffs.${vo.skillEffs} 错误:$v")

                skillEffList.add(skillEffId)
            }
            vo.skillEffList = skillEffList

            tmpHeroSkillMap[vo.id] = vo
            val tmp = tmpHeroSkillStudyMap.getOrPut(vo.skillId) { hashMapOf() }
            tmp[vo.level] = vo
        }
        this.heroSkillMap = tmpHeroSkillMap
        this.heroSkillStudyMap = tmpHeroSkillStudyMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        pcs.heroSkillProtoCache.heroSkillMap.forEach { _, proto ->
            proto.skillEffList.forEach {
                if (it != 0) {
                    pcs.heroSkillEffProtoCache.heroSkillEffMap[it]
                        ?: throw RuntimeException("skill.xml :: 技能{${proto.id}} 的技能效果{$it} 不存在")
                }
            }
        }
    }

    // 获取指定技能编号的配置
    fun getProtoById(skillId: Int): HeroSkillProto? {
        return this.heroSkillMap[skillId]
    }

}