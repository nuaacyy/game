package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AllianceGiftProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AllianceGiftProto>
): Serializable

data class AllianceGiftProto(
    val id: Int,
    val giftType: Int,
    val props: Int,
    val bigGiftExp: Int,
    val giftExp: Int,// 完成条件
    val endTime: Int
): Serializable

class AllianceGiftProtoCache : ProtoCacheInit("allianceGift.xml") {
    var allianceGiftProtoMap: Map<Int, AllianceGiftProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AllianceGiftProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AllianceGiftProtoResult

        val tmpAllianceGiftProtoMap: HashMap<Int, AllianceGiftProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpAllianceGiftProtoMap.containsKey(vo.id)) {
                throw RuntimeException("allianceGift.xml :: id[${vo.id}]重复")
            }
            tmpAllianceGiftProtoMap[vo.id] = vo
        }
        this.allianceGiftProtoMap = tmpAllianceGiftProtoMap
        return
    }

    override fun postCheck(pcs: ProtoCacheStore): Unit {
        for ((_, vo) in pcs.allianceGiftProtoCache.allianceGiftProtoMap) {
            if (pcs.equipCache.equipProtoMap[vo.props] == null) {
                throw RuntimeException("allianceGift.xml :: 道具ID在props里找不到:${vo.props}")
            }

        }
        return
    }
}


