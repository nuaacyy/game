package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AllianceCompetitionRankingResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AllianceCompetitionRankingProto>
) : Serializable

data class AllianceCompetitionRankingProto(
    val id: Int,
    val rankingLevel: Int, // 联赛段位等级
    val questNub: Int, // 可接取任务数量
    val rankingNubReward: String, // 活动积分排名奖励  初始排名_结束排名:dropBag表ID_dropBag表ID_dropBag表ID:lan:客户端用的一个字段%下一组
    val allianceCompetitionPartakeNub: Int, // 这个段位的分组人数
    val upNub: Int,// 排名上升的名次
    val downNub: Int,// 排名下降的名次
    val upStageLimit: Int // 升级所达档次
) : Serializable {
    var rankingNubRewardMap: Map<String, Int> = mapOf()

}

class AllianceCompetitionRankingProtoCache : ProtoCacheInit("allianceCompetitionRanking.xml") {
    var protoMapById: Map<Int, AllianceCompetitionRankingProto> = mapOf()// 模板map  大KEY 主堡等级 小KEY 活动ID

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AllianceCompetitionRankingResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AllianceCompetitionRankingResult

        val tmpProtoMapById: HashMap<Int, AllianceCompetitionRankingProto> = hashMapOf()
        val rMap = hashMapOf<String, Int>()
        for (vo in readXmlResult.l) {
            if (tmpProtoMapById.containsKey(vo.rankingLevel)) {
                throw RuntimeException("allianceCompetitionRanking.xml :: rankingLevel[${vo.rankingLevel}]重复")
            }

            val rankPrizes = vo.rankingNubReward.split("%")
            for (e in rankPrizes) {
                val eventInfo = e.split(":")
                if (eventInfo.size != 4) {
                    throw RuntimeException("解析 allianceCompetitionRanking.xml的表字段rankingNubReward时冒号分割长度不为4:${vo.rankingNubReward}")
                }
                val rans = eventInfo[0].split("_")
                if (rans.size != 2) {
                    throw RuntimeException("解析 allianceCompetitionRanking.xml的表字段${vo.id}  rankingNubReward时排名长度不为2")
                }
                val rId = eventInfo[1].toInt()
                rMap[eventInfo[0]] = rId
            }
            vo.rankingNubRewardMap = rMap
            tmpProtoMapById[vo.rankingLevel] = vo
        }
        this.protoMapById = tmpProtoMapById
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, pp) in this.protoMapById) {
            for ((_, d) in pp.rankingNubRewardMap) {
                pcs.dropBagCache.dropBagMap[d]
                    ?: throw RuntimeException("解析 allianceCompetitionRanking.xml的表ID:${pp.rankingNubRewardMap} 中的奖励从dropBag中找不到:$d")
            }
        }
    }

    // 传入一个排名,获取奖励dropBagId
    fun findEventRankRewardByRank(lv: Int, rank: Int): Int {
        val protoInfo = this.protoMapById[lv]

        if (protoInfo != null) {
            for ((ranks, rankReward) in protoInfo.rankingNubRewardMap) {
                val rs = stringsSplit(ranks, "_")
                val minRand = (rs[0]).toInt()
                val maxRand = (rs[1]).toInt()
                if (rank in minRand..maxRand) {
                    // 这个排名的就是这一档奖励了
                    return rankReward
                }
            }
        }
        return 0
    }

}


