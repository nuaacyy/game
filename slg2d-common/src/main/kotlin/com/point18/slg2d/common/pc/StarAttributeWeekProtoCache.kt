package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*
import kotlin.collections.HashSet

data class StarAttributeWeekResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<StarAttributeWeekProto>
): Serializable

data class StarAttributeWeekProto(
    val id: Int,
    val week: Int, // 表示当前周为今年的第几周
    val starGroup: String, // 星象组合 或的关系 星象id:星象id"
    val starNature: String // 星象属性 RPG属性ID：万分比%RPG属性ID：万分比
) : Serializable{
    var starGroupSet: Set<Int> = setOf()// 星象组合 或的关系 星象id:星象id"
    var starNatureMap: Map<Int, Int> = mapOf()
}

class StarAttributeWeekProtoCache : ProtoCacheInit("starAttributeWeek.xml") {
    var protoMap: Map<Int, StarAttributeWeekProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<StarAttributeWeekResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as StarAttributeWeekResult

        val tmpProtoMap: HashMap<Int, StarAttributeWeekProto> = hashMapOf()
        for (vo in readXmlResult.l) {

            val groups = stringsSplit(vo.starGroup, ";")

            val hashSetOfStar = hashSetOf<Int>()

            for (g in groups) {
                val gg = strconvAtoi(g)
                    ?: throw RuntimeException("starAttributeWeek.xml : starGroupMap err : ${vo.starGroup}")
                hashSetOfStar.add(gg)
            }
            val starNatureS = stringsSplit(vo.starNature, "|")

            val hashMapOfNature = hashMapOf<Int, Int>()

            for (starNaturess in starNatureS) {

                val starNaturessSecondSpilt = starNaturess.split(";")
                if (starNaturessSecondSpilt.size != 2) {
                    throw RuntimeException("starAttributeWeek.xml : propertyMap err1 : ${vo.starNature}")
                }
                val natrueKey = starNaturessSecondSpilt[0].toIntOrNull()
                    ?: throw RuntimeException("starAttributeWeek.xml : propertyMap err2 : ${vo.starNature}")

                val natrueVal = starNaturessSecondSpilt[1].toIntOrNull()
                    ?: throw RuntimeException("starAttributeWeek.xml : propertyMap err3 : ${vo.starNature}")

                hashMapOfNature[natrueKey] = natrueVal
            }

            val propertyMapNode = hashMapOf<HashSet<Int>, HashMap<Int, Int>>()
            propertyMapNode[hashSetOfStar] = hashMapOfNature
            vo.starGroupSet = hashSetOfStar
            vo.starNatureMap = hashMapOfNature

            tmpProtoMap[vo.week] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for (i in 1..53) {
            this.protoMap[i] ?: throw RuntimeException("starAttributeWeek表找不到某周的配置:$i.")

        }
    }

    // 根据当前时间获取到星象配置
    fun findStarAttributeWeekProtoByWeek(): StarAttributeWeekProto? {
        val nowWeekForYear = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)
        // 本周是今年的第几周

        return protoMap[nowWeekForYear]
    }

}