package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.commonfunc.getRandInt
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import kotlin.collections.HashMap

data class MonsterRefreshResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MonsterRefreshProto>
) : Serializable

data class MonsterRefreshProto(
    val id: Int, // 唯一id
    val type: Int, //魔物类型 1=常驻；2=周期
    val refreshDay: Int, // 所有服务器共同遵守的时间，以服务器指定时间为1，每多1天即加1，达到配置天数上限后则下一天继续回到1重新循环
    val level: Int, //魔物等级 各类型魔物等级
    val refreshRate: String  // 魔物编号;刷新几率，多个用|隔开，其中魔物编号对应monster表【type】字段
) : Serializable {
    var refreshRateMap: Map<Int, Int> = mapOf() // 类型:几率
    var totalRefreshRate: Int = 0
}

class MonsterRefreshProtoCache : ProtoCacheInit("monstarRefresh.xml") {
    var monsterRefreshMap: Map<Int, Map<Int, Map<Int, MonsterRefreshProto>>> = mapOf() //类型-等级-天数-配置

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MonsterRefreshResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MonsterRefreshResult

        val tmpMonsterRefreshMap: HashMap<Int, HashMap<Int, HashMap<Int, MonsterRefreshProto>>> = hashMapOf()
        for (vo in readXmlResult.l) {
            vo.refreshRateMap = parseIntMap(vo.refreshRate) ?:
                    throw RuntimeException("monstarRefresh模板MonsterTypeRate配置错误:${vo.id}")
            vo.totalRefreshRate = vo.refreshRateMap.values.sum()

            if (vo.totalRefreshRate <= 0) {
                throw RuntimeException("monstarRefresh中MonsterTypeRate配置错误,权值小于0:${vo.id}.")
            }
            tmpMonsterRefreshMap.getOrPut(vo.type) { hashMapOf() }
                .getOrPut(vo.level) { hashMapOf() }[vo.refreshDay] = vo
        }
        this.monsterRefreshMap = tmpMonsterRefreshMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, lvMap) in monsterRefreshMap) {
            for ((_, dayMap) in lvMap) {
                for ((_, vo) in dayMap) {
                    for ((type, _) in vo.refreshRateMap) {
                        pcs.monsterProtoCache.findMonsterProtoByTypeLv(type, vo.level)
                            ?: throw RuntimeException("monstarRefresh中type在moster表里找不到:$type,行ID为:${vo.id}")
                    }
                }
            }

        }
    }

    fun findMonsterType(type: Int, lv: Int): Int {
        val lvMap =
            this.monsterRefreshMap[type] ?: throw RuntimeException("findMonsterType魔物类型不存在:$type")
        val dayMap = lvMap[lv] ?: throw RuntimeException("findMonsterType等级不存在:$type,$lv")

        val basicTime = LocalDateTime.of(
            pcs.basicProtoCache.mapMonsterInitialTimesYear,
            (pcs.basicProtoCache.mapMonsterInitialTimesMonth),
            pcs.basicProtoCache.mapMonsterInitialTimesDay,
            0,
            0,
            0,
            0
        ).toEpochSecond(ZoneOffset.of("+8"))
        val now = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))
        val diffDay = ((now - basicTime) / (3600 * 24)).toInt()// 两个时间的相隔天数
        val day = diffDay % dayMap.size + 1

        val refreshInfo = dayMap[day] ?: throw RuntimeException("findMonsterType刷新日期不存在不存在:$type,$lv,$day")

        val randValue = getRandInt(refreshInfo.totalRefreshRate)

        var tempRate = 0
        for ((monsterType, rate) in refreshInfo.refreshRateMap) {
            if (randValue <= tempRate + rate) {
                return monsterType
            }
            tempRate += rate
        }
        return 0
    }
}