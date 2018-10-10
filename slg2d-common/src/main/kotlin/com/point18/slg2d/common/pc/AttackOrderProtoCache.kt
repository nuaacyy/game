package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

data class AttackOrderProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AttackOrderProto>
): Serializable

data class AttackOrderProto(
    val unitBaseId: Int,
    val unlocSkill: Int,
    val order: String
): Serializable {
    var orderList = listOf<Int>()
}

class AttackOrderProtoCache : ProtoCacheInit("attackOrder.xml") {
    var orderMap = mapOf<Int, Map<Int, AttackOrderProto>>()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AttackOrderProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AttackOrderProtoResult

        val tmpOrderMap = hashMapOf<Int, HashMap<Int, AttackOrderProto>>()
        for (vo in readXmlResult.l) {
            val tmpOrderList = LinkedList<Int>()
            val orderStrs = vo.order.split("-")
            for (orderStr in orderStrs) {
                val atkOrder =
                    orderStr.toIntOrNull() ?: throw RuntimeException("attackOrder.xml ::  ${vo.unitBaseId} order格式不合法.")
                tmpOrderList.add(atkOrder)
            }
            vo.orderList = tmpOrderList
            tmpOrderMap.getOrPut(vo.unitBaseId) { hashMapOf() }[vo.unlocSkill] = vo
        }
        orderMap = tmpOrderMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, vo) in pcs.unitBaseCache.protoMap) {
            if (!orderMap.containsKey(vo.id)) {
                throw RuntimeException("attackOrder.xml ::  ${vo.id} 的攻击顺序不存在")
            }
        }
    }
}