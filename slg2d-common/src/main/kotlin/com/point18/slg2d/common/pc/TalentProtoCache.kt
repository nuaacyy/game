package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class TalentResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<TalentProto>
) : Serializable

data class TalentProto(
    val id: Int, //唯一ID
    val talentId: Int,//天赋Id
    val talentType: Int,  //天赋类别
    val level: Int,  //天赋等级
    val talentEffect: String,//天赋效果
    val talentCondition: String, //天赋前置条件
    val talentCost: String  //天赋解锁花费
) : Serializable {
    var effect: Map<Int, Int> = mapOf()
    var condition: Map<Int, Int> = mapOf()
    var cost: Map<Int, Int> = mapOf()

}

class TalentProtoCache : ProtoCacheInit("talent.xml") {
    var talentMap: Map<Int, TalentProto> = mapOf()
    var talentIdMap: Map<Int, Map<Int, TalentProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<TalentResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as TalentResult

        val tmpTalentMap: HashMap<Int, TalentProto> = hashMapOf()
        val tmpTalentIdMap: HashMap<Int, HashMap<Int, TalentProto>> = hashMapOf()
        for (data in readXmlResult.l) {
            val talent = TalentProto(
                data.id,
                data.talentId,
                data.talentType,
                data.level,
                "",
                "",
                ""
            )
            talent.effect = parseIntMap(data.talentEffect) ?:
                    throw RuntimeException("talent.xml :: Talent EffectStr ERR:${data.talentEffect}")
            talent.condition = parseIntMap(data.talentCondition) ?:
                    throw RuntimeException("talent.xml :: Talent ConditionStr ERR:${data.talentCondition}")
            talent.cost = parseIntMap(data.talentCost) ?:
                    throw RuntimeException("talent.xml :: Talent CostStr ERR:${data.talentCost}")


            tmpTalentMap[data.id] = talent
            val talents = tmpTalentIdMap.getOrPut(data.talentId) {
                hashMapOf()
            }
            talents[data.level] = talent
        }
        this.talentMap = tmpTalentMap
        this.talentIdMap = tmpTalentIdMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}