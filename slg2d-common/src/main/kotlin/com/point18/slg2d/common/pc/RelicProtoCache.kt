package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class RelicResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<RelicProto>
) : Serializable

data class RelicProto(
    val id: Int, // 唯一id
    val level: Int, // 遗迹等级
    val lineUp: String, // 对应怪物阵容表id，其中怪物怪物阵容表即lineup表
    val bonuses: Int, //反侦察几率
    val dropProps: String  //时光之盒
) : Serializable {
    var drops: List<Drop> = listOf()
    var totalDropRate: Int = 0

    var soliderTeamList: List<SoliderTeam> = listOf()
    var totalTeamRate: Int = 0
}

data class SoliderTeam(
    var lineUpId: Int,
    var percent: Int
) : IPercentObj {
    override fun getRate(): Int {
        return percent
    }
}

class RelicProtoCache : ProtoCacheInit("relic.xml") {
    var relicProtoMap: HashMap<Int, RelicProto> = hashMapOf()
    var relicProtoMapByLv: HashMap<Int, LinkedList<RelicProto>> = hashMapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<RelicResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as RelicResult

        val tmpRelicProtoMap: HashMap<Int, RelicProto> = hashMapOf()
        val tmpRelicProtoMapByLv: HashMap<Int, LinkedList<RelicProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val existVo = tmpRelicProtoMap[vo.id]

            if (existVo != null) {
                throw RuntimeException("monstarRefresh模板id存在重复:${vo.id}.")
            }

            tmpRelicProtoMap[vo.id] = vo

            val relicProtoMapByLvlevel = tmpRelicProtoMapByLv.getOrPut(vo.level)
            {
                LinkedList()
            }

            relicProtoMapByLvlevel.add(vo)

            val voDrops =
                dropStringToDrops(vo.dropProps) ?: throw RuntimeException("relic.xml :: 掉落包配置错误${vo.dropProps}")

            vo.drops = voDrops
            vo.totalDropRate = vo.drops.sumBy { it.percent }
            if (vo.totalDropRate <= 0) {
                throw RuntimeException("relic.xml :: 掉落包几率配置错误${vo.dropProps}")
            }

            val teamList = parseIntPairList(vo.lineUp) ?: throw RuntimeException("relic.xml :: 部队配置错误${vo.lineUp}")
            vo.totalTeamRate = teamList.sumBy { it.second }
            if (vo.totalTeamRate <= 0) {
                throw RuntimeException("relic.xml :: 部队几率配置错误${vo.lineUp}")
            }
            val tmpTeamList = LinkedList<SoliderTeam>()
            teamList.forEach {
                tmpTeamList.add(SoliderTeam(it.first, it.second))
            }
            vo.soliderTeamList = tmpTeamList
        }
        this.relicProtoMap = tmpRelicProtoMap
        this.relicProtoMapByLv = tmpRelicProtoMapByLv
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, relicProto) in pcs.relicProtoCache.relicProtoMap) {
            for (soliderTeam in relicProto.soliderTeamList) {
                if (!pcs.soliderTeamProtoCache.soliderTeamMap.containsKey(soliderTeam.lineUpId)) {
                    throw RuntimeException("relic.xml :: 部队配置在soliderteam中找不到:${relicProto.id}")
                }
            }
        }
    }

}