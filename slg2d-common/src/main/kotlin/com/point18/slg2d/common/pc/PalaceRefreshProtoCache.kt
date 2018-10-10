package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class PalaceRefreshProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<PalaceRefreshProto>
): Serializable

data class PalaceRefreshProto(
    val weekID: Int, // 第几周
    val palaceID: Int // 地宫id
): Serializable

class PalaceRefreshProtoCache : ProtoCacheInit("palaceRefresh.xml") {
    var palaceRefreshProtoMap: Map<Int, Int> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<PalaceRefreshProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as PalaceRefreshProtoResult

        val tmpPalaceRefreshProtoMap: HashMap<Int, Int> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpPalaceRefreshProtoMap[vo.weekID] = vo.palaceID
        }
        this.palaceRefreshProtoMap = tmpPalaceRefreshProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }
}