package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class LordEquipCardResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<LordEquipCardProto>
): Serializable

data class LordEquipCardProto(
    val id: Int,
    val propsId: Int,//要合成的宝石ID
    val itemId: Int, // 需要的宝石ID
    val itemNum: Int, // 需要的数量
    val group: Int // 卡片大类型
): Serializable

class LordEquipCardProtoCache : ProtoCacheInit("lordEquipCard.xml") {
    var lordEquipCardProtoMap: Map<Int, LordEquipCardProto> = mapOf() // A合成B  通过A找到行
    var lordEquipCardProtoMapBySelf: Map<Int, LordEquipCardProto> = mapOf() // A合成B  通过B找到行

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<LordEquipCardResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as LordEquipCardResult

        val tmpLordEquipCardProtoMap: HashMap<Int, LordEquipCardProto> = hashMapOf() // A合成B  通过A找到行
        val tmpLordEquipCardProtoMapBySelf: HashMap<Int, LordEquipCardProto> = hashMapOf() // A合成B  通过B找到行
        for (vo in readXmlResult.l) {
            tmpLordEquipCardProtoMapBySelf[vo.propsId] = vo
            if (vo.itemId == 0) {
                continue
            }
            if (tmpLordEquipCardProtoMap[vo.itemId] != null) {
                throw RuntimeException("lordEquipCard模板itemId存在重复:${vo.id}.")
            }
            tmpLordEquipCardProtoMap[vo.itemId] = vo
        }
        this.lordEquipCardProtoMapBySelf = tmpLordEquipCardProtoMapBySelf
        this.lordEquipCardProtoMap = tmpLordEquipCardProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}