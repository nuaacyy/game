package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class EventAllianceInformationResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<EventAllianceInformationProto>
): Serializable

data class EventAllianceInformationProto(
    val id: Int,
    val aid: Int,
    val eventScoreSource: String, // 活动积分来源 条件1ID%条件2ID…
    val eventPrize: String,  // 活动积分阶段和奖励  阶段1分数:dropBag表ID_dropBag表ID_dropBag表ID%下一组
    val eventRankPrize: String, // 活动积分排名奖励  初始排名_结束排名:dropBag表ID_dropBag表ID_dropBag表ID:lan:客户端用的一个字段%下一组
    val eventSaveTime: Int
) : Serializable{
    var eventScoreSourceMap: Map<Int, Int> = mapOf()
    var eventRankPrizeMap: Map<String, EventPrizeVo> = mapOf()
    var eventPrizeMap: Map<Int, EventPrizeVo> = mapOf()
    var minScore: Int = 0 // 达到这个值才会计入排行

}

class EventAllianceInformationProtoCache : ProtoCacheInit("eventAllianceInformation.xml") {
    var protoMapById: Map<Int, EventAllianceInformationProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<EventAllianceInformationResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as EventAllianceInformationResult

        val tmpProtoMapById: HashMap<Int, EventAllianceInformationProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpProtoMapById.containsKey(vo.aid)) {
                throw RuntimeException("eventAllianceInformation.xml :: aid[${vo.aid}]重复")
            }

            val eventScoreSourceMap = hashMapOf<Int, Int>()
            val eventScoreSources = stringsSplit(vo.eventScoreSource, "|")

            for (e in eventScoreSources) {
                val elId =
                    strconvAtoi(e)
                        ?: throw RuntimeException("解析 eventAllianceInformation.xml的表字段eventScoreSources时error")
                eventScoreSourceMap[elId] = 1
            }
            vo.eventScoreSourceMap = eventScoreSourceMap

            val eventPrizeMap = hashMapOf<Int, EventPrizeVo>()
            if ("0" != vo.eventPrize) {
                val eventPrizes = stringsSplit(vo.eventPrize, "%")
                for (index in eventPrizes.indices) {
                    val eventInfo = stringsSplit(eventPrizes[index], ":")

                    if (eventInfo.size != 3) {
                        throw RuntimeException("解析 eventAllianceInformation.xml的表字段eventPrize时:分割长度不为3")
                    }
                    val eScore =
                        strconvAtoi(eventInfo[0])
                            ?: throw RuntimeException("解析 eventAllianceInformation.xml的表字段eventPrize时error")

                    if (index <= 2) {
                        vo.minScore = eScore
                    }
                    val rewardBags = LinkedList<Int>()
                    val eventRewardDropBagId = stringsSplit(eventInfo[1], "_")

                    for (r in eventRewardDropBagId) {
                        val rId = strconvAtoi(r)
                            ?: throw RuntimeException("解析 eventAllianceInformation.xml的表字段eventPrize时error")
                        rewardBags.add(rId)
                    }

                    eventPrizeMap[eScore] = EventPrizeVo(rewardBags, eventInfo[2], index)
                }
            }
            vo.eventPrizeMap = eventPrizeMap

            val eventRankPrizeMap = hashMapOf<String, EventPrizeVo>()
            val eventRankPrizes = stringsSplit(vo.eventRankPrize, "%")

            for (e in eventRankPrizes) {
                val eventInfo = stringsSplit(e, ":")

                if (eventInfo.size != 3) {
                    throw RuntimeException("解析 eventAllianceInformation.xml的表字段EventRankPrize时冒号分割长度不为3")
                }
                val rans = stringsSplit(eventInfo[0], "_")

                if (rans.size != 2) {
                    throw RuntimeException("解析 eventAllianceInformation.xml的表字段EventRankPrize时排名长度不为2")
                }
                val rewardBags = LinkedList<Int>()
                val eventRewardDropBagId = stringsSplit(eventInfo[1], "_")

                for (r in eventRewardDropBagId) {
                    val rId =
                        strconvAtoi(r)
                            ?: throw RuntimeException("解析 eventAllianceInformation.xml的表字段EventRankPrize时error")
                    rewardBags.add(rId)
                }

                eventRankPrizeMap[eventInfo[0]] = EventPrizeVo(rewardBags, eventInfo[2], 0)
            }
            vo.eventRankPrizeMap = eventRankPrizeMap

            tmpProtoMapById[vo.aid] = vo
        }
        this.protoMapById = tmpProtoMapById
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for ((_, pp) in this.protoMapById) {

            for ((_, d) in pp.eventPrizeMap) {

                for (dd in d.rewardBags) {
                    pcs.dropBagCache.dropBagMap[dd]
                        ?: throw RuntimeException("解析 eventAllianceInformation.xml的表:${this.protoMapById} 中的奖励从dropBag中找不到:$dd")

                }
            }
        }
    }

    fun findEventRewardByMinAndMax(activityId: Int, oldValue: Int, nowValue: Int): LinkedList<EventPrizeVo> {
        val dropBags = LinkedList<EventPrizeVo>()
        val protoInfo = this.protoMapById[activityId]

        if (protoInfo != null) {

            for ((score, dbs) in protoInfo.eventPrizeMap) {

                if (score in (oldValue + 1)..nowValue) {
                    // 老值就已经比这一挡要大了
                    dropBags.add(dbs)
                }
            }

        }
        return dropBags
    }

    fun findEventRankRewardByRank(activityId: Int, rank: Int): EventPrizeVo? {
        val protoInfo = this.protoMapById[activityId]

        if (protoInfo != null) {

            for ((ranks, rankReward) in protoInfo.eventRankPrizeMap) {
                val rs = stringsSplit(ranks, "_")
                val minRand = (rs[0].toInt())
                val maxRand = (rs[1].toInt())


                if (rank in minRand..maxRand) {
                    // 这个排名的就是这一档奖励了
                    return rankReward
                }
            }
        }
        return null
    }

}