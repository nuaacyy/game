package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class EventConditionResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<EventConditionProto>
): Serializable

data class EventConditionProto(
    val id: Int,
    val num: Int, // 活动要求数量
    val score: Int,// 活动积分
    val allianceCondition: Int, // 联盟活跃礼的要求数量条件数量
    val liveValueAward: Int // 奖励的联盟活跃积分
): Serializable

class EventConditionProtoCache : ProtoCacheInit("eventCondition.xml") {
    var protoMap: Map<Int, EventConditionProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<EventConditionResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as EventConditionResult

        val tmpProtoMap: HashMap<Int, EventConditionProto> = hashMapOf()//
        for (vo in readXmlResult.l) {
            if (tmpProtoMap.containsKey(vo.id)) {
                throw RuntimeException("eventCondition.xml :: id[${vo.id}]重复")
            }
            tmpProtoMap[vo.id] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}