package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class PrisonTimeProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<PrisonTimeProto>
): Serializable

data class PrisonTimeProto(
    val id: Int, // 编号
    val townLv: Int, // 主堡等级
    val timeType: Int, // 0表示不可抓  1表示逃脱时间  2表示处决等待时间
    val time: Int // 秒数
): Serializable

class PrisonTimeProtoCache : ProtoCacheInit("prisonTime.xml") {
    var prisonTimeProtoMap: Map<Int, PrisonTimeProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<PrisonTimeProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as PrisonTimeProtoResult

        val tmpPrisonTimeProtoMap: HashMap<Int, PrisonTimeProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpPrisonTimeProtoMap[vo.townLv] = vo
        }
        this.prisonTimeProtoMap = tmpPrisonTimeProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}