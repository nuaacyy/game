package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class PrisonBuffProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<PrisonBuffProto>
): Serializable

data class PrisonBuffProto(
    val id: Int, // 编号
    val lordLv: Int, // 监禁的领主等级
    val buff: String // buff效果
) : Serializable{
    var buffMap: Map<Int, Int> = mapOf()

}

class PrisonBuffProtoCache : ProtoCacheInit("prisonBuff.xml") {
    var prisonBuffProtoMap: Map<Int, PrisonBuffProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<PrisonBuffProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as PrisonBuffProtoResult

        val tmpPrisonBuffProtoMap: HashMap<Int, PrisonBuffProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpPrisonBuffProtoMap[vo.lordLv] = vo

            val vobuffMap = parseIntMap(vo.buff) ?: throw RuntimeException("prisonBuff.xml :: Buff效果配置错误:${vo.buff}")

            vo.buffMap = vobuffMap
        }
        this.prisonBuffProtoMap = tmpPrisonBuffProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}