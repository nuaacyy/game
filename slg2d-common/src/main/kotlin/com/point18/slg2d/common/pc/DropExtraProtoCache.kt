package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class DropExtraResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<DropExtraProto>
): Serializable

data class DropExtraProto(
    val id: Int,
    val dropsId: Int,  // 道具id
    val times: Int,  // 次数
    val number: Int // 数量
): Serializable

class DropExtraProtoCache : ProtoCacheInit("dropExtra.xml") {
    var protoMap: Map<Int, DropExtraProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<DropExtraResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as DropExtraResult

        val tmpProtoMap: HashMap<Int, DropExtraProto> = hashMapOf()//
        for (vo in readXmlResult.l) {
            if (tmpProtoMap.containsKey(vo.dropsId)) {
                throw RuntimeException("dropExtra.xml :: dropsId[${vo.dropsId}]重复")
            }
            tmpProtoMap[vo.dropsId] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for ((_, p) in this.protoMap) {
            pcs.equipCache.equipProtoMap[p.dropsId]
                ?: throw RuntimeException("dropExtra.xml ::表配置的DropsId在props表里找不到:${p.dropsId}")

        }
    }

}