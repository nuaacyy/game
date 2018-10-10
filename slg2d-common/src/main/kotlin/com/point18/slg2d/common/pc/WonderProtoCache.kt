package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class WonderResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<WonderProto>
) : Serializable

data class WonderProto(
    val id: Int, // 唯一id
    val type: Int, //奇观类型
    val incidenceBig: String, //影响范围
    val incidenceBigLv: Int, //范围资源等级
    val incidenceMid: String, //黑土地范围
    val incidenceMidLv: Int, //黑土地资源等级
    val incidenceSmall: String, //奇观中心禁地
    val incidenceBase: String, //奇观底座
    val protectTime: Int, //保护时间,秒
    val occupyTime: String //占领时间  开战持续时间秒:需要占领秒%下一个
) : Serializable {
    var incidenceBigX: Int = 0
    var incidenceBigY: Int = 0
    var incidenceMidX: Int = 0
    var incidenceMidY: Int = 0
    var incidenceSmallX: Int = 0
    var incidenceSmallY: Int = 0
    var incidenceBaseX: Int = 0
    var incidenceBaseY: Int = 0
    var occupyTimeMap: Map<Int, Int> = mapOf()

}

class WonderProtoCache : ProtoCacheInit("wonder.xml") {
    var wonderProtoMap: Map<Int, WonderProto> = hashMapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<WonderResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as WonderResult

        val tmpWonderProtoMap: HashMap<Int, WonderProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val existVo = tmpWonderProtoMap[vo.type]

            if (existVo != null) {
                throw RuntimeException("wonder模板IncidenceType存在重复:${tmpWonderProtoMap[vo.type]}.")
            }

            tmpWonderProtoMap[vo.type] = vo

            val incidenceBigs = stringsSplit(vo.incidenceBig, ";")
            if (incidenceBigs.size != 2) {
                throw RuntimeException("wonder中IncidenceBig配置错误:${vo.incidenceBig} size != 2.")
            }
            vo.incidenceBigX = (incidenceBigs[0]).toInt()
            vo.incidenceBigY = (incidenceBigs[1]).toInt()

            val incidenceMids = stringsSplit(vo.incidenceMid, ";")
            if (incidenceMids.size != 2) {
                throw RuntimeException("wonder中IncidenceMid配置错误:${vo.incidenceMid} size != 2.")
            }
            vo.incidenceMidX = (incidenceMids[0]).toInt()
            vo.incidenceMidY = (incidenceMids[1]).toInt()

            val incidenceSmalls = stringsSplit(vo.incidenceSmall, ";")
            if (incidenceSmalls.size != 2) {
                throw RuntimeException("wonder中IncidenceSmall配置错误:${vo.incidenceSmall} size != 2.")
            }
            vo.incidenceSmallX = (incidenceSmalls[0]).toInt()
            vo.incidenceSmallY = (incidenceSmalls[1]).toInt()

            val incidenceBases = stringsSplit(vo.incidenceBase, ";")

            if (incidenceBases.size != 2) {
                throw RuntimeException("wonder中IncidenceBase配置错误:${vo.incidenceBase} size != 2.")
            }
            vo.incidenceBaseX = (incidenceBases[0]).toInt()
            vo.incidenceBaseY = (incidenceBases[1]).toInt()

            val occupyTimeMap = hashMapOf<Int, Int>()
            val ratios = stringsSplit(vo.occupyTime, "|")

            for (r in ratios) {
                val rr = stringsSplit(r, ";")

                if (rr.size != 2) {
                    throw RuntimeException("wonder中OccupyTime配置错误${vo.occupyTime} ")
                }
                val t = (rr[0]).toInt()
                val n = (rr[1]).toInt()
                occupyTimeMap[t] = n
            }
            vo.occupyTimeMap = occupyTimeMap
        }

        this.wonderProtoMap = tmpWonderProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}