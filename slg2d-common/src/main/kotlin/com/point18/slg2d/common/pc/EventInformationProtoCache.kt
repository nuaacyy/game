package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class EventInformationResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<EventInformationProto>
): Serializable

data class EventInformationProto(
    val id: Int,
    val aid: Int,
    val eventScoreSource: String, // 活动积分来源 条件1ID%条件2ID…
    val castlelv: String,
    val eventPrize: String,// 活动积分阶段和奖励  阶段1分数:dropBag表ID_dropBag表ID_dropBag表ID%下一组
    val eventRankPrize: String, // 活动积分排名奖励  初始排名_结束排名:dropBag表ID_dropBag表ID_dropBag表ID:lan:客户端用的一个字段%下一组
    val eventSaveTime: Int
) : Serializable{
    var eventScoreSourceMap: Map<Int, Int> = mapOf()
    var eventPrizeMap: Map<Int, EventPrizeVo> = mapOf()
    var eventRankPrizeMap: Map<String, EventPrizeVo> = mapOf()
    var minScore: Int = 0// 达到这个值才会计入排行
}

data class EventPrizeVo(
    var rewardBags: List<Int>,
    var landId: String,
    var index: Int // 隶属第几阶奖励
)

class EventInformationProtoCache : ProtoCacheInit("eventInformation.xml") {
    var protoMapById: Map<Int, EventInformationProto> = mapOf()             // aid-proto
    var protoMap: Map<Int, Map<Int, EventInformationProto>> = mapOf()   // aid-<castlelv-proto>

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<EventInformationResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as EventInformationResult

        val tmpProtoMapById: HashMap<Int, EventInformationProto> = hashMapOf()             // id-proto
        val tmpProtoMap: HashMap<Int, HashMap<Int, EventInformationProto>> = hashMapOf()   // aid-<castlelv-proto>
        for (vo in readXmlResult.l) {
            if (tmpProtoMapById.containsKey(vo.id)) {
                throw RuntimeException("eventInformation.xml :: id[${vo.id}]重复")
            }

            val eventScoreSourceMap = hashMapOf<Int, Int>()
            val eventScoreSources = stringsSplit(vo.eventScoreSource, "|")

            for (e in eventScoreSources) {
                val elId =
                    strconvAtoi(e) ?: throw RuntimeException("解析 eventInformation.xml的表字段eventScoreSource时err:$e")
                eventScoreSourceMap[elId] = 1
            }
            vo.eventScoreSourceMap = eventScoreSourceMap

            val eventPrizeMap = hashMapOf<Int, EventPrizeVo>()
            val eventPrizes = stringsSplit(vo.eventPrize, "%")

            for (index in eventPrizes.indices) {
                val eventInfo = stringsSplit(eventPrizes[index], ":")

                if (eventInfo.size != 3) {
                    throw RuntimeException("解析 eventInformation.xml的表字段eventPrize时冒号分割长度不为3:${eventPrizes[index]}")
                }
                val eScore = strconvAtoi(eventInfo[0])

                if (index <= 2 && eScore != null) {
                    vo.minScore = eScore
                }
                val rewardBags = LinkedList<Int>()
                val eventRewardDropBagId = stringsSplit(eventInfo[1], "_")

                for (r in eventRewardDropBagId) {
                    val rId = strconvAtoi(r)
                    if (rId != null) {
                        rewardBags.add(rId)
                    }
                }

                if (eScore == null) {
                    throw RuntimeException("解析 eventInformation.xml的表字段error")
                }
                eventPrizeMap[eScore] = EventPrizeVo(rewardBags, eventInfo[2], index + 1)
            }
            vo.eventPrizeMap = eventPrizeMap

            val eventRankPrizeMap = hashMapOf<String, EventPrizeVo>()
            val eventRankPrizes = stringsSplit(vo.eventRankPrize, "%")

            for (e in eventRankPrizes) {
                val eventInfo = stringsSplit(e, ":")

                if (eventInfo.size != 3) {
                    throw RuntimeException("解析 eventInformation.xml的表字段EventRankPrize时冒号分割长度不为3:$e")
                }
                val rans = stringsSplit(eventInfo[0], "_")

                if (rans.size != 2) {
                    throw RuntimeException("解析 eventInformation.xml的表字段EventRankPrize时排名长度不为2:${eventInfo[0]}")
                }
                val rewardBags = LinkedList<Int>()
                val eventRewardDropBagId = stringsSplit(eventInfo[1], "_")

                for (r in eventRewardDropBagId) {
                    val rId = strconvAtoi(r)
                        ?: throw RuntimeException("解析 eventInformation.xml的表字段eventRewardDropBagId error")
                    rewardBags.add(rId)
                }

                eventRankPrizeMap[eventInfo[0]] = EventPrizeVo(rewardBags, eventInfo[2], 0)
            }
            vo.eventRankPrizeMap = eventRankPrizeMap

            tmpProtoMap.getOrPut(vo.aid) { hashMapOf() }

            val castles = stringsSplit(vo.castlelv, "|")
            if (castles.size != 2) {
                throw RuntimeException("解析 eventInformation.xml的表字段 error")
            }

            val startCastleLv = strconvAtoi(castles[0])
            val overCastleLv = strconvAtoi(castles[1])
            if (startCastleLv == null || overCastleLv == null) {
                throw RuntimeException("解析 eventInformation.xml的表字段 error")
            }


            for (i in startCastleLv..overCastleLv) {
                val tmpprotoMapvoaid =
                    tmpProtoMap[vo.aid] ?: throw RuntimeException("解析 eventInformation.xml的表字段 error")
                tmpprotoMapvoaid[i] = vo
            }
            tmpProtoMapById[vo.id] = vo
        }
        this.protoMapById = tmpProtoMapById
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for ((_, pp) in this.protoMap) {
            for ((_, p) in pp) {
                for ((_, d) in p.eventPrizeMap) {
                    for (dd in d.rewardBags) {
                        pcs.dropBagCache.dropBagMap[dd]
                            ?: throw RuntimeException("解析 eventInformation.xml的表:${pcs.dropBagCache.dropBagMap} 中的奖励从dropBag中找不到:$dd")

                    }
                }
            }
        }

        /** eventScoreSource字段校验 **/
        for ((_, proto) in this.protoMapById) {
            for (eventConditionId in proto.eventScoreSourceMap.keys) {
                var flag = false
                for (tempId in pcs.eventConditionProtoCache.protoMap.keys) {
                    if (tempId == eventConditionId) {
                        flag = true
                        break
                    }
                }
                if (!flag) {
                    throw RuntimeException("解析 eventInformation.xml的表: eventScoreSource中有无法识别的积分来源$eventConditionId")
                }
            }
        }
    }

    fun findEventRewardByMinAndMax(
        activityId: Int,
        oldValue: Int,
        nowValue: Int,
        castleLv: Int
    ): LinkedList<EventPrizeVo> {
        val dropBags = LinkedList<EventPrizeVo>()
        val protoMapactivityId = this.protoMap[activityId]
        val protoInfo: EventInformationProto?
        if (protoMapactivityId == null) {
            return dropBags
        } else {
            protoInfo = protoMapactivityId[castleLv]
        }


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

    fun findEventRankRewardByRank(activityId: Int, rank: Int, castleLv: Int): EventPrizeVo? {

        val castleLvProtoMap = this.protoMap[activityId]
        val protoInfo: EventInformationProto?
        if (castleLvProtoMap == null) {
            return null
        } else {
            protoInfo = castleLvProtoMap[castleLv]
        }

        if (protoInfo != null) {

            for ((ranks, rankReward) in protoInfo.eventRankPrizeMap) {
                val rs = stringsSplit(ranks, "_")
                val minRand = (rs[0]).toInt()
                val maxRand = (rs[1]).toInt()

                if (rank in minRand..maxRand) {
                    // 这个排名的就是这一档奖励了
                    return rankReward
                }
            }
        }
        return null
    }
}