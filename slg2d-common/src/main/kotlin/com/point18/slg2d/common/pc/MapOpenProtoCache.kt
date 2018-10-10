package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

data class MapOpenResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MapOpenProto>
) : Serializable

data class MapOpenProto(
    val unitId: Int,
    val unitPos: String,
    val preconditionLv: Int,
    val preconditionUnit: String,
    val army: Int,
    val reward: String
) : Serializable {
    var posX = 0
    var posY = 0
    var unitConditionList = listOf<Int>()
    var rewards = listOf<ResVo>()
}

class MapOpenProtoCache : ProtoCacheInit("mapOpen.xml") {
    var mapOpenMap: Map<Int, MapOpenProto> = mapOf()
    var mapOpenPosMap: Map<Int, Map<Int, MapOpenProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MapOpenResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MapOpenResult

        val tmpMapOpenMap: HashMap<Int, MapOpenProto> = hashMapOf()
        val tmpObstantMap: HashMap<Int, HashMap<Int, MapOpenProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val strs = stringsSplit(vo.unitPos, ";")
            if (strs.count() != 2) {
                throw RuntimeException("mapOpen.xml : unitPos 格式错误 ${vo.unitPos}")
            }
            val x = strs[0].toIntOrNull()
            val y = strs[1].toIntOrNull()
            if (x == null || y == null) {
                throw RuntimeException("mapOpen.xml : unitPos 格式错误 ${vo.unitPos}")
            }
            vo.posX = x
            vo.posY = y
            tmpObstantMap.getOrPut(x) { hashMapOf() }[y] = vo

            if (vo.preconditionUnit != "0") {
                val tmpunitConditionList = LinkedList<Int>()
                val unitStrs = stringsSplit(vo.preconditionUnit, ";")
                unitStrs.forEach {
                    val unit = it.toIntOrNull()
                    if (unit == null) {
                        throw RuntimeException("mapOpen.xml : preconditionUnit 格式错误 ${vo.preconditionUnit}")
                    }
                    tmpunitConditionList.add(unit)
                }
                vo.unitConditionList = tmpunitConditionList
            }
            val rewards = resStringToResVoList(vo.reward)
            if (rewards == null) {
                throw RuntimeException("mapOpen.xml : reward 格式错误 ${vo.reward}")
            }
            vo.rewards = rewards

            tmpMapOpenMap[vo.unitId] = vo
        }

        mapOpenPosMap = tmpObstantMap
        mapOpenMap = tmpMapOpenMap

    }

    override fun postCheck(pcs: ProtoCacheStore) {
        pcs.mapOpenProtoCache.mapOpenMap.forEach { openMap ->
            openMap.value.unitConditionList.forEach {
                if (pcs.mapOpenProtoCache.mapOpenMap[it] == null) {
                    throw RuntimeException("mapOpen.xml : preconditionUnit 错误")
                }
            }
        }
        //todo 地图边界校验
    }
}





