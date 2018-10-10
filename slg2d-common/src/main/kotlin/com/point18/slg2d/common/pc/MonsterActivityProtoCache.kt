package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class MonsterActivityResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MonsterActivityProto>
): Serializable

data class MonsterActivityProto(
    val id: Int,                //int 唯一id
    val monsterId: Int,         //int 初始刷新的魔物id monster表唯一id
    val locationId: Int,    //int monsterActivityLocation表唯一id
    val resurrectionTime: Int,    //int boss死亡后等待刷新时间
    val afterMonsterId: Int,         //int 后置boss魔物id monsterActivity表唯一id
    val openingTime: String,         //array_int 作为初始刷新boss的开服时间区间（单位是天）
    val rankAward: String            //string 排名奖励
) : Serializable{
    // 同心正方形,外层的未过滤内层的,有利有弊,用的时候注意了
    var openingTimeInterval: List<Int> = listOf()
    var rankAwardMap: Map<String, EventPrizeVo> = mapOf()
}

class MonsterActivityProtoCache : ProtoCacheInit("monsterActivity.xml") {
    var monsterActivityProtoMap: Map<Int, MonsterActivityProto> = mapOf()
    var locationMonsterMap: Map<Int, List<MonsterActivityProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MonsterActivityResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MonsterActivityResult

        val tmpMonsterActivityProtoMap: HashMap<Int, MonsterActivityProto> = hashMapOf()
        val tmpLocationMonsterMap: HashMap<Int, LinkedList<MonsterActivityProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val existVo = tmpMonsterActivityProtoMap[vo.id]

            if (existVo != null) {
                throw RuntimeException("monsterActivity.xml::id 存在重复:${vo.id}")
            }

            /** openingTime **/
            val rs1 = stringsSplit(vo.openingTime, ";")
            val openingTime = LinkedList<Int>()
            if (rs1.size != 2) {
                throw RuntimeException("monsterActivity.xml::openingTime 格式错误 ${vo.openingTime}")
            }
            for (s in rs1) {
                val i = strconvAtoi(s) ?: throw RuntimeException("monsterActivity.xml::openingTime [$s]非数字")
                openingTime.add(i)
            }
            vo.openingTimeInterval = openingTime

            /** rankAward **/
            val rankAwardMap = hashMapOf<String, EventPrizeVo>()
            val rankAwardS = stringsSplit(vo.rankAward, "%")

            for (s in rankAwardS) {
                val awardInfo = stringsSplit(s, ":")
                if (awardInfo.size < 2 || awardInfo.size >= 4) {
                    throw RuntimeException("monsterActivity.xml::rankAward 奖励格式有误$s")
                }
                val ranks = stringsSplit(awardInfo[0], "_")
                if (ranks.size != 2) {
                    throw RuntimeException("monsterActivity.xml::rankAward 奖励格式中的排名有误${awardInfo[0]}")
                }
                val rewardBags = LinkedList<Int>()
                val tmpS = stringsSplit(awardInfo[1], "_")
                for (rewardBagS in tmpS) {
                    val rewardBagId = strconvAtoi(rewardBagS)
                        ?: throw RuntimeException("monsterActivity.xml::rankAward 奖励格式中的具体奖励有误${awardInfo[1]}")
                    rewardBags.add(rewardBagId)
                }
                rankAwardMap[awardInfo[0]] = EventPrizeVo(rewardBags, "0", 0)
            }
            vo.rankAwardMap = rankAwardMap

            /** map **/
            tmpMonsterActivityProtoMap[vo.id] = vo
            val monsters = tmpLocationMonsterMap.getOrPut(vo.locationId) {
                LinkedList()
            }
            monsters.add(vo)
        }
        this.monsterActivityProtoMap = tmpMonsterActivityProtoMap
        this.locationMonsterMap = tmpLocationMonsterMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in this.monsterActivityProtoMap) {
            pcs.monsterProtoCache.findMonsterProto(proto.monsterId)
                ?: throw RuntimeException("monsterActivity.xml 中的monsterId字段在monster.xml表里找不到:${proto.monsterId}")
            this.monsterActivityProtoMap[proto.afterMonsterId]
                    ?: throw RuntimeException("monsterActivity.xml 中的afterMonsterId字段在monsterActivity.xml表里找不到:${proto.afterMonsterId}")
            pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoMap[proto.locationId]
                ?: throw RuntimeException("monsterActivity.xml 中的locationId字段在monsterActivityLocation.xml表里找不到:${proto.locationId}")
        }
    }

    fun findEventRankRewardByRank(monsterActivityId: Int, rank: Int): EventPrizeVo? {
        val protoInfo = this.monsterActivityProtoMap[monsterActivityId]

        if (protoInfo != null) {

            for ((ranks, rankReward) in protoInfo.rankAwardMap) {
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
