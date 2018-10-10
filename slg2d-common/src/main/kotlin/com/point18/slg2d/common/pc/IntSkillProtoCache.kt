package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class IntSkillResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<IntSkillProto>
): Serializable

data class IntSkillProto(
    val id: Int, //唯一id		唯一编号
    val skillId: Int, //技能id		无视等级编号
    val name: String,//技能名称		技能名称
    val level: Int,  //技能等级		技能等级
    val maxLevel: Int, //最高等级		该技能可升的最高等级
    val fightValue: Int, //技能战力		技能增加战力值
    val skillEffs: String, //技能效果		效果代号:效果值，多个用%隔开
    val intWorthPlus: Double,  //收内政资质影响万分比  最终内政效果值=配置效果值+内政资质*内政资质加成
    val effectUse:Int   //1、内政属性 2、战斗属性
): Serializable {
    var skillEffsMap: Map<Int, Int> = mapOf() //技能效果		效果代号:效果值，多个用%隔开

}

class IntSkillProtoCache : ProtoCacheInit("intSkill.xml") {
    var intSkillMap: Map<Int, IntSkillProto> = mapOf()
    var intSkillLvMap: Map<Int, Map<Int, IntSkillProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<IntSkillResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as IntSkillResult

        val tmpIntSkillMap: HashMap<Int, IntSkillProto> = hashMapOf()
        val tmpIntSkillLvMap: HashMap<Int, HashMap<Int, IntSkillProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val skillEffsMap = hashMapOf<Int, Int>()
            val effs = stringsSplit(vo.skillEffs, "|")

            for (eff in effs) {
                val e = stringsSplit(eff, ";")

                if (e.size != 2) {
                    throw RuntimeException("intSkill.xml :: Id.${vo.id} SkillEffs:$eff 错误1")
                }

                val effId =
                    strconvAtoi(e[0]) ?: throw RuntimeException("intSkill.xml :: Id.${vo.id} SkillEffs.$eff 错误2")

                val effValue =
                    strconvAtoi(e[1]) ?: throw RuntimeException("intSkill.xml :: Id.${vo.id} SkillEffs.%$eff 错误3")

                skillEffsMap[effId] = effValue
            }
            vo.skillEffsMap = skillEffsMap

            tmpIntSkillMap[vo.id] = vo
            val tmp = tmpIntSkillLvMap.getOrPut(vo.skillId) { hashMapOf() }
            tmp[vo.level] = vo
        }
        this.intSkillMap = tmpIntSkillMap
        this.intSkillLvMap = tmpIntSkillLvMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}