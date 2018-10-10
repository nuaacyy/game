package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class MapRelicResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MapRelicProto>
) : Serializable

data class MapRelicProto(
    val id: Int, // 唯一id
    val numRelic: String //遗迹数量 遗迹等级:数量%下一个
) : Serializable {
    var numRelicMap: Map<Int, Int> = mapOf()
    var totalRelicNum: Int = 0
}

class MapRelicProtoCache : ProtoCacheInit("mapRelic.xml") {
    var mapRelicProtoMap: Map<Int, MapRelicProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MapRelicResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MapRelicResult

        val tmpMapRelicProtoMap: HashMap<Int, MapRelicProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val existVo = this.mapRelicProtoMap[vo.id]

            if (existVo != null) {
                throw RuntimeException("mapRelic模板ID存在重复:${vo.id}.")
            }

            tmpMapRelicProtoMap[vo.id] = vo

            val numRelicMap = hashMapOf<Int, Int>()
            val ratios = stringsSplit(vo.numRelic, "|")

            for (r in ratios) {
                val rr = stringsSplit(r, ";")

                if (rr.size != 2) {
                    throw RuntimeException("mapRelic中NumRelic配置错误1:${vo.id}.")
                }
                val farmLv = strconvAtoi(rr[0])
                val num = strconvAtoi(rr[1])
                if (farmLv == null || num == null) {
                    throw RuntimeException("mapRelic中NumRelic配置错误1:${vo.id}.")
                }
                numRelicMap[farmLv] = num
                vo.totalRelicNum += num
            }
            vo.numRelicMap = numRelicMap
        }
        this.mapRelicProtoMap = tmpMapRelicProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for (eachMapCallProto in pcs.mapCallProtoCache.listSortByStartDay) {
            val mapRelicType = eachMapCallProto.mapRelic
            this.mapRelicProtoMap[mapRelicType] ?: throw RuntimeException("找不到特定遗迹撒点类型${mapRelicType}的模板")
        }
    }

}