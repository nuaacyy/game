package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class EmigrationCostResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<EmigrationCostProto>
) : Serializable

data class EmigrationCostProto(
    val id: Int, // 唯一ID
    val ranking: Int, // 排名
    val cost: String // 消耗
) : Serializable {
    var costResVo: List<ResVo> = listOf() // // 商品	奖励格式
}

class EmigrationCostProtoCache : ProtoCacheInit("emigrationCost.xml") {
    var lordSuitProtoCacheMap: Map<Int, EmigrationCostProto> = mapOf()
    var lordSuitProtoCacheList: List<EmigrationCostProto> = listOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<EmigrationCostResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as EmigrationCostResult

        val tmpLordSuitProtoCacheMap = hashMapOf<Int, EmigrationCostProto>()
        val tmpLordSuitProtoCacheList = LinkedList<EmigrationCostProto>()
        for (vo in readXmlResult.l) {
            if (tmpLordSuitProtoCacheMap.containsKey(vo.id)) {
                throw RuntimeException("emigrationCost.xml :: ranking[${vo.ranking}]重复")
            }
            if (vo.cost != "0") {
                val costVo = resStringToResVoList(vo.cost)
                    ?: throw RuntimeException("emigrationCost.xml中的cost字段解析出错:${vo.cost},错误行ID是:${vo.id}")

                vo.costResVo = costVo
            }
            tmpLordSuitProtoCacheMap[vo.ranking] = vo
            tmpLordSuitProtoCacheList.add(vo)
        }
        lordSuitProtoCacheMap = tmpLordSuitProtoCacheMap
        lordSuitProtoCacheList = tmpLordSuitProtoCacheList

        if (lordSuitProtoCacheList.size == 0) {
            throw RuntimeException("emigrationCost.xml表未解析出数据")
        }
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

    fun findEmigrationCostProtoByRank(rank: Int): List<ResVo> {
        val costVo: List<ResVo>
        val info = lordSuitProtoCacheMap[rank]
        if (info != null) {
            costVo = info.costResVo
        } else {
            costVo = lordSuitProtoCacheList[lordSuitProtoCacheList.size - 1].costResVo
        }

        return costVo
    }

}