package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class MandatoryGuideProtoList(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MandatoryGuideProto>
) : Serializable

data class MandatoryGuideProto(
    val id: Int,
    val type: Int,
    val step: Int,
    val nextId: Int,
    val resetId: Int,
    val isForce: Int,
    val guideType: Int
) : Serializable


// 强制引导表
class MandatoryGuideProtoCache : ProtoCacheInit("playerGuide.xml") {
    var mandatoryGuideMap: Map<Int, MandatoryGuideProto> = mapOf() // id是key

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MandatoryGuideProtoList>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MandatoryGuideProtoList
        val tmpMap: HashMap<Int, MandatoryGuideProto> = hashMapOf()

        for (proto in readXmlResult.l) {
            val tmpProto = tmpMap[proto.id]
            if (tmpProto != null) {
                throw RuntimeException("playerGuide.xml 表的配置重复,id == ${proto.id}")
            }
            tmpMap[proto.id] = proto
        }

        mandatoryGuideMap = tmpMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}