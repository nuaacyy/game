package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class BuffResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<BuffProto>
): Serializable

data class BuffProto(
    val id: Int, // 唯一ID
    val type: Int, // buff类型
    val blacklist: String // 黑名单,不可与配置中的buff类型并存
): Serializable {
    var blacklistMap: Map<Int, Int> = mapOf()
}

class BuffProtoCache : ProtoCacheInit("buff.xml") {
    var protoMap: HashMap<Int, BuffProto> = hashMapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<BuffResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as BuffResult

        val tmpProtoMap: HashMap<Int, BuffProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpProtoMap.containsKey(vo.type)) {
                throw RuntimeException("buff.xml :: type[${vo.type}]重复")
            }

            val blacklistMap = hashMapOf<Int, Int>()
            if (vo.blacklist != "0") {
                val bb = stringsSplit(vo.blacklist, "|")
                for (b in bb) {
                    val blackType = strconvAtoi(b) ?: throw RuntimeException("buffProtoCache 分析出错")
                    blacklistMap[blackType] = 1
                }
            }
            vo.blacklistMap = blacklistMap
            tmpProtoMap[vo.type] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}