package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.MainBuilding
import java.io.Serializable
import java.util.*

data class TradShipSurpriseRefreshProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<TradShipSurpriseRefreshProto>
): Serializable

data class TradShipSurpriseRefreshProto(
    val id: Int, // 编号
    val castleLv: Int, // 玩家城堡等级
    val surprise: String  // 道具组id:几率，多个用%隔开 万分比，此处几率加起来=1万
): Serializable {
    var surpriseMap: Map<String, Int> = mapOf()// 走掉落

}

class TradShipSurpriseRefreshProtoCache : ProtoCacheInit("tradShipSurpriseRefresh.xml") {
    var tradShipSurpriseRefreshMap: HashMap<Int, TradShipSurpriseRefreshProto> = hashMapOf()
    var tradShipSurpriseRefreshs: LinkedList<TradShipSurpriseRefreshProto> = LinkedList()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<TradShipSurpriseRefreshProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as TradShipSurpriseRefreshProtoResult

        val tmpTradShipSurpriseRefreshs: LinkedList<TradShipSurpriseRefreshProto> = LinkedList()
        for (vo in readXmlResult.l) {

            val (surpriseMap, ok, sum) = resStringToDropBag(vo.surprise)

            if (!ok || surpriseMap == null) {
                throw RuntimeException("tradShipSurpriseRefresh.xml中的Surprise字段配置出错:${vo.surprise}")
            }

            if (sum < 10000) {
                throw RuntimeException("tradShipSurpriseRefresh.xml中的Surprise字段总权值少于10000:${vo.surprise},当前值是$sum")
            }

            vo.surpriseMap = surpriseMap

            tmpTradShipSurpriseRefreshs.add(vo)
        }

        tmpTradShipSurpriseRefreshs.sortBy { it.castleLv }

        this.tradShipSurpriseRefreshs = tmpTradShipSurpriseRefreshs
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        val tmpTradShipSurpriseRefreshMap: HashMap<Int, TradShipSurpriseRefreshProto> = hashMapOf()
        for (index in this.tradShipSurpriseRefreshs.indices) {
            val tradShipRefreshProto = this.tradShipSurpriseRefreshs[index]
            val startLv = tradShipRefreshProto.castleLv

            val endLv = if (this.tradShipSurpriseRefreshs.size - 1 != index) {
                // 不是最后一行配置表
                this.tradShipSurpriseRefreshs[index + 1].castleLv - 1
            } else {
                // 最后一行配置表了
                val mainBuildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[MainBuilding]
                if (mainBuildingProto == null) {
                    throw RuntimeException("tradShipSurpriseRefresh.xml中的postCheck过程中找不到主城模版")
                }
                mainBuildingProto.topLevel
            }

            for (lv in startLv..endLv) {
                tmpTradShipSurpriseRefreshMap[lv] = tradShipRefreshProto
            }
        }

        this.tradShipSurpriseRefreshMap = tmpTradShipSurpriseRefreshMap
    }

}