package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class ClearanceResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<ClearanceProto>
) : Serializable

data class ClearanceProto(
    val id: Int, //唯一ID
    val typeId: Int, //统计类型Id
    val number: Int //统计数量要求
) : Serializable {
    fun checkCondition(records: HashMap<Int, Int>): Boolean {
        val value = records[typeId] ?: 0
        return when (typeId) {
            com.point18.slg2d.common.constg.AtkDeadHeroNum,
            com.point18.slg2d.common.constg.AtkTotalBeDamage -> value <= number
            com.point18.slg2d.common.constg.WinBattle -> true
            else -> value >= number
        }
    }
}

class ClearanceProtoCache : ProtoCacheInit("clearance.xml") {
    var clearanceMap: Map<Int, ClearanceProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ClearanceResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ClearanceResult

        val tmpClearanceMap: HashMap<Int, ClearanceProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpClearanceMap.containsKey(vo.id)) {
                throw RuntimeException("clearance.xml :: id[${vo.id}]重复")
            }
            tmpClearanceMap[vo.id] = vo
        }
        this.clearanceMap = tmpClearanceMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}