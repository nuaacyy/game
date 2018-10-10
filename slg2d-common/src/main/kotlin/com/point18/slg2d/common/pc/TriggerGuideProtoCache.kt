package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class TriggerGuideProtoList(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<TriggerGuideProto>
) : Serializable

data class TriggerGuideProto(
    val id: Int,
    val guideSceneType: String,
    val params1: String,
    val params2: String,
    val activeCondition: String,
    val priority: Int
) : Serializable

// 事件触发新手引导表
class TriggerGuideProtoCache : ProtoCacheInit("playerGuideEvent.xml") {
    var guideMap: Map<Int, TriggerGuideProto> = mapOf() // id是key

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<TriggerGuideProtoList>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as TriggerGuideProtoList
        val tmpMap: HashMap<Int, TriggerGuideProto> = hashMapOf()

        for (proto in readXmlResult.l) {
            val tmpProto = tmpMap[proto.id]
            if (tmpProto != null) {
                throw RuntimeException("playerGuideEvent.xml 表的配置重复,id == ${proto.id}")
            }
            tmpMap[proto.id] = proto
        }

        guideMap = tmpMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}