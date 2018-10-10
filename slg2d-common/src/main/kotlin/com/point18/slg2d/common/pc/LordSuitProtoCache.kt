package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class LordSuitResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<LordSuitProto>
): Serializable

data class LordSuitProto(
    val id: Int, // 唯一ID
    val suitId: Int, //套装效果ID	套装效果ID
    val suitNum: Int, // 激活件数
    val suitName: String //套装名字	套装名字
): Serializable

class LordSuitProtoCache : ProtoCacheInit("lordSuit.xml") {
    var lordSuitProtoCacheMap: Map<Int, LordSuitProto> = mapOf()
    var lordSuitProtoCacheMapByTypeAndNum: Map<Int, Map<Int, LordSuitProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<LordSuitResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as LordSuitResult

        val tmpLordSuitProtoCacheMap: HashMap<Int, LordSuitProto> = hashMapOf()
        val tmpLordSuitProtoCacheMapByTypeAndNum: HashMap<Int, HashMap<Int, LordSuitProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val lordSuitProtoCacheMapByTypeAndNumsuitId =
                tmpLordSuitProtoCacheMapByTypeAndNum.getOrPut(vo.suitId) { hashMapOf() }
            lordSuitProtoCacheMapByTypeAndNumsuitId[vo.suitNum] = vo
            tmpLordSuitProtoCacheMap[vo.id] = vo
        }
        this.lordSuitProtoCacheMapByTypeAndNum = tmpLordSuitProtoCacheMapByTypeAndNum
        this.lordSuitProtoCacheMap = tmpLordSuitProtoCacheMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}