package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class SoliderTeamResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<SoliderTeamProto>
): Serializable

data class SoliderTeamProto(
    val id: Int,
    val unitBase: String,
    val solider: String,
    val soliderAdd: String
) : Serializable{
    var heroMap = mapOf<Int, HeroTeam>()
    var soliderMap = mapOf<Int, Int>()
    var effectMap = mapOf<Int, Int>()
}

data class HeroTeam(
    val protoId: Int,
    val lv: Int,
    val star: Int,
    val awake: Int
)

class SoliderTeamProtoCache : ProtoCacheInit("soliderTeam.xml") {
    var soliderTeamMap: Map<Int, SoliderTeamProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<SoliderTeamResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as SoliderTeamResult

        val tmpSoliderTeamMap: HashMap<Int, SoliderTeamProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val soliderStrs = stringsSplit(vo.solider, "|")
            val tmpSoliderMap = hashMapOf<Int, Int>()
            soliderStrs.forEach {
                val strs = stringsSplit(it, ";")
                if (strs.count() != 2) {
                    throw RuntimeException("soliderTeam.xml : solider 格式错误 ${vo.solider}")
                }
                val soliderId = strs[0].toIntOrNull()
                val num = strs[1].toIntOrNull()
                if (soliderId == null || num == null) {
                    throw RuntimeException("soliderTeam.xml : solider 格式错误 ${vo.solider}")
                }
                tmpSoliderMap[soliderId] = num
            }
            vo.soliderMap = tmpSoliderMap

            if (vo.soliderAdd != "0") {
                val tmpEffectMap = hashMapOf<Int, Int>()
                val effectStrs = stringsSplit(vo.soliderAdd, "|")
                effectStrs.forEach {
                    val strs = stringsSplit(it, ";")
                    if (strs.count() != 2) {
                        throw RuntimeException("soliderTeam.xml : soliderAdd 格式错误 ${vo.soliderAdd}")
                    }
                    val effectId = strs[0].toIntOrNull()
                    val value = strs[1].toIntOrNull()
                    if (effectId == null || value == null) {
                        throw RuntimeException("soliderTeam.xml : soliderAdd 格式错误 ${vo.soliderAdd}")
                    }
                    tmpEffectMap[effectId] = value
                }
                vo.effectMap = tmpEffectMap
            }

            if (vo.unitBase != "0") {
                val tmpHeroMap = hashMapOf<Int, HeroTeam>()
                val heroStrs = stringsSplit(vo.unitBase, "|")
                heroStrs.forEach {
                    val strs = stringsSplit(it, ";")
                    if (strs.count() != 4) {
                        throw RuntimeException("soliderTeam.xml : unitBase 格式错误 ${vo.unitBase}")
                    }
                    val protoId = strs[0].toIntOrNull()
                    val lv = strs[1].toIntOrNull()
                    val star = strs[2].toIntOrNull()
                    val awake = strs[3].toIntOrNull()
                    if (protoId == null || lv == null || star == null || awake == null) {
                        throw RuntimeException("soliderTeam.xml : unitBase 格式错误 ${vo.unitBase}")
                    }
                    tmpHeroMap[protoId] = HeroTeam(protoId, lv, star, awake)
                }
                vo.heroMap = tmpHeroMap
            }

            tmpSoliderTeamMap[vo.id] = vo
        }
        soliderTeamMap = tmpSoliderTeamMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, vo) in pcs.soliderTeamProtoCache.soliderTeamMap) {
            vo.soliderMap.forEach { soliderId, _ ->
                pcs.soliderCache.soliderProtoMap[soliderId]
                    ?: throw RuntimeException("soliderTeam.xml : 士兵配置不存在 ${vo.id}")
            }
            vo.heroMap.forEach { _, hero ->
                pcs.unitBaseCache.protoMap[hero.protoId]
                    ?: throw RuntimeException("soliderTeam.xml : 英雄配置不存在 ${vo.id}")
                pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId]?.get(hero.awake)
                    ?: throw RuntimeException("soliderTeam.xml : 英雄阶级配置不存在 ${vo.id}")
                pcs.heroStarProtoCache.heroStarProtoCache[hero.protoId]?.get(hero.star)
                    ?: throw RuntimeException("soliderTeam.xml : 英雄星级配置不存在 ${vo.id}")
            }
        }
    }
}





