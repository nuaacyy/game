package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class ArenaAchievementExchangeResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<ArenaAchievementExchangeProto>
): Serializable

data class ArenaAchievementExchangeProto(
    val id: Int,
    val condition: Int,
    val goldReward: String,
    val arenaCoin: String
) : Serializable{
    var goldRewardGet: List<ResVo> = listOf()
    var arenaCoinCost: List<ResVo> = listOf()
}

class ArenaAchievementExchangeCache : ProtoCacheInit("arenaExchangeReward.xml") {
    var mapOfProto = mapOf<Int, ArenaAchievementExchangeProto>()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ArenaAchievementExchangeResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ArenaAchievementExchangeResult

        val tmpMapOfProto = hashMapOf<Int, ArenaAchievementExchangeProto>()
        for (vo in readXmlResult.l) {
            if (tmpMapOfProto.containsKey(vo.id)) {
                throw RuntimeException("arenaExchangeReward.xml :: id[${vo.id}]重复")
            }

            val goldRewardGet = resStringToResVoList(vo.goldReward)
                ?: throw RuntimeException("arenaExchangeReward.xml 的${vo.id}配置1错误  ${vo.goldReward}")

            val arenaCoinCost = resStringToResVoList(vo.arenaCoin)
                ?: throw RuntimeException("arenaExchangeReward.xml 的${vo.id}配置2错误  ${vo.arenaCoin}")

            vo.goldRewardGet = LinkedList(goldRewardGet)
            vo.arenaCoinCost = LinkedList(arenaCoinCost)

            tmpMapOfProto[vo.id] = vo
        }
        mapOfProto = tmpMapOfProto
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }
}