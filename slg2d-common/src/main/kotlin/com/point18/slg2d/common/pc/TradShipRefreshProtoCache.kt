package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.MainBuilding
import java.io.Serializable
import java.util.*

data class TradShipRefreshProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<TradShipRefreshProto>
): Serializable

data class TradShipRefreshProto(
    val id: Int, // 编号
    val castleLv: Int, // 玩家城堡等级
    val group: String // 道具组id:几率，多个用%隔开 万分比，此处几率加起来=1万
): Serializable {
    var groupMap: Map<String, Int> = mapOf() // 走掉落

}

class TradShipRefreshProtoCache : ProtoCacheInit("tradShipRefresh.xml") {
    var tradShipRefreshProtoMapByGroup: Map<Int, TradShipRefreshProto> = mapOf()
    var tradShipRefreshProtos: List<TradShipRefreshProto> = listOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<TradShipRefreshProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as TradShipRefreshProtoResult

        val tmpTradShipRefreshProtos: LinkedList<TradShipRefreshProto> = LinkedList()
        for (vo in readXmlResult.l) {

            val (groupMap, ok, sum) = resStringToDropBag(vo.group)

            if (!ok || groupMap == null) {
                throw RuntimeException("tradShipRefresh.xml中的Group字段配置出错:${vo.group}")
            }

            if (sum < 10000) {
                throw RuntimeException("tradShipRefresh.xml中的Group字段总权值少于10000:${vo.group},当前值是:$sum")
            }

            vo.groupMap = groupMap

            tmpTradShipRefreshProtos.add(vo)
        }

        tmpTradShipRefreshProtos.sortBy { it.castleLv }

        this.tradShipRefreshProtos = tmpTradShipRefreshProtos
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        val tmpTradShipRefreshProtoMapByGroup: HashMap<Int, TradShipRefreshProto> = hashMapOf()

        for (index in this.tradShipRefreshProtos.indices) {
            val tradShipRefreshProto = this.tradShipRefreshProtos[index]
            val startLv = tradShipRefreshProto.castleLv

            val endLv = if (this.tradShipRefreshProtos.size - 1 != index) {
                // 不是最后一行配置表
                this.tradShipRefreshProtos[index + 1].castleLv - 1

            } else {
                // 最后一行配置表了
                val mainBuildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[MainBuilding]
                if (mainBuildingProto == null) {
                    throw RuntimeException("tradShipRefresh.xml中的postCheck过程中找不到主城模版")
                }

                mainBuildingProto.topLevel
            }

            for (lv in startLv..endLv) {
                tmpTradShipRefreshProtoMapByGroup[lv] = tradShipRefreshProto
            }
        }

        this.tradShipRefreshProtoMapByGroup = tmpTradShipRefreshProtoMapByGroup
    }

}