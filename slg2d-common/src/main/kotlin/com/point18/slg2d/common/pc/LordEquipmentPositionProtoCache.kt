package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class LordEquipmentPositionResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<LordEquipmentPositionProto>
): Serializable

data class LordEquipmentPositionProto(
    val id: Int, // 唯一ID
    val positionSubType: Int, // 君主装备部位 1头盔、2武器、3衣服、4副手、5护腿、6鞋子
    val positionId: String  // 对应的可以装备的君主槽位,多个
) : Serializable{
    var positionIdMap: Map<Int, Int> = mapOf()

}

class LordEquipmentPositionProtoCache : ProtoCacheInit("lordEquipmentPosition.xml") {
    var lordEquipmentPositionMap: Map<Int, LordEquipmentPositionProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<LordEquipmentPositionResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as LordEquipmentPositionResult

        val tmplordEquipmentPositionMap: HashMap<Int, LordEquipmentPositionProto> = hashMapOf()
        for (vo in readXmlResult.l) {

            val positionIdMap = hashMapOf<Int, Int>()
            val ps = stringsSplit(vo.positionId, ";")

            for (p in ps) {
                val pp = strconvAtoi(p) ?: throw RuntimeException("lordEquipmentPosition.xml positionId配置错误：${vo.id}")
                positionIdMap[pp] = 1
            }
            vo.positionIdMap = positionIdMap

            tmplordEquipmentPositionMap[vo.positionSubType] = vo
        }
        this.lordEquipmentPositionMap = tmplordEquipmentPositionMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}