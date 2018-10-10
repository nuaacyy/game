package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class FastDeadPropsResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<FastDeadPropsProto>
): Serializable

data class FastDeadPropsProto(
    val id: Int,
    val rank: String,
    val needPropsNum: Int // 需要毒蘑菇数量
) : Serializable{
    var minRank: Int = 0
    var maxRank: Int = 0
}

class FastDeadPropsProtoCache : ProtoCacheInit("fastDeadProps.xml") {
    var protoMap: Map<Int, FastDeadPropsProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return  loadConfig<FastDeadPropsResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as FastDeadPropsResult

        val tmpProtoMap: HashMap<Int, FastDeadPropsProto> = hashMapOf()//
        for (vo in readXmlResult.l) {
            if (tmpProtoMap.containsKey(vo.id)) {
                throw RuntimeException("fastDeadProps.xml :: id[${vo.id}]重复")
            }

            val ranks = stringsSplit(vo.rank, ";")

            if (ranks.size != 2) {
                throw RuntimeException("fastDeadProps.xml :表的rank字段长度不是2.${vo.rank}")
            }
            val tmpMax = ranks[1].toIntOrNull() ?: throw RuntimeException("fastDeadProps.xml :rank字段${vo.rank}err1")
            val tmpMin = ranks[0].toIntOrNull() ?: throw RuntimeException("fastDeadProps.xml :rank字段${vo.rank}err2")
            vo.maxRank = tmpMax
            vo.minRank = tmpMin
            tmpProtoMap[vo.id] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

    // 传入一个排名,获取到消耗
    fun findEventRankRewardByRank(rank: Int): Int {
        for ((_, info) in this.protoMap) {
            if (rank >= info.minRank && rank <= info.maxRank) {
                // 这个排名的就是这一档奖励了
                return info.needPropsNum
            }
        }
        return 1
    }

}