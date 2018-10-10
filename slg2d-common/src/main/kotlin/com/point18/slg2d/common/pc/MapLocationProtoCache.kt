package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class MapLocationResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MapLocationProto>
) : Serializable

data class MapLocationProto(
    val id: Int, // 唯一id
    val type: Int, // 撒点类型,type由服务器活跃程度决定
    val level: Int,   // 资源带等级
    val mapDistribution: String  //资源点等级：比例 资源点等级
) : Serializable {
    var mapDistributionMap: Map<Int, Int> = mapOf() // 资源点等级-数量
    var totalResNum: Int = 0
}

class MapLocationProtoCache : ProtoCacheInit("mapLocation.xml") {

    var mapLocationProtoMap: Map<Int, Map<Int, MapLocationProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MapLocationResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MapLocationResult

        val tmpMapLocationProtoMap: HashMap<Int, HashMap<Int, MapLocationProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val mapLocationProtoMapmapLocationType = tmpMapLocationProtoMap.getOrPut(vo.type) { hashMapOf() }
            mapLocationProtoMapmapLocationType[vo.level] = vo
            val mapDistributionMap = hashMapOf<Int, Int>()
            val ratios = stringsSplit(vo.mapDistribution, "|")
            for (r in ratios) {
                val rr = stringsSplit(r, ";")

                if (rr.size != 2) {
                    throw RuntimeException("mapLocation中MapRatio配置错误1:${vo.id}.")
                }
                val farmLv = strconvAtoi(rr[0])
                val num = strconvAtoi(rr[1])
                if (farmLv == null || num == null) {
                    throw RuntimeException("mapLocation中MapRatio配置错误1:${vo.id}.")
                }
                mapDistributionMap[farmLv] = num
                vo.totalResNum += num
            }
            vo.mapDistributionMap = mapDistributionMap
        }
        this.mapLocationProtoMap = tmpMapLocationProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}