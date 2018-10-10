package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

data class PalaceProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<PalaceProto>
): Serializable

data class PalaceProto(
    val palaceID: Int, // 地宫id
    val palaceLevel: Int, // 第几周
    var cost: String, // 消耗
    var monsterReward: String, // 打怪奖励
    var surpriseRate: Int, // 祝福概率
    var surpriseTimes: String, // 祝福次数
    var bossRate: Int, //boss概率
    var bossTimes: String, // boss次数
    var bossReward: String, // boss奖励
    var giftRate: Int // 大奖概率
): Serializable {
    var costMap: List<ResVo> = listOf()                //资源消耗
    var monsterRewardMap: Map<Int, Int> = mapOf()     // 打怪奖励概率
    var surpriseTimesMap: Map<Int, Int> = mapOf()  // 祝福次数概率
    var bossTimeMap: Map<Int, Int> = mapOf() // 打boss次数概率
    var bossRewardMap: Map<Int, Int> = mapOf()   // 打boss奖励概率
    var bossRewardNumMap: Map<Int, Int> = mapOf()   // 打boss奖励概率

    fun monsterRewardDraw(): Int? {
        var totalRate = 0
        for (dropInfo in this.monsterRewardMap) {
            totalRate += dropInfo.value
        }
        val dropRand = com.point18.slg2d.common.commonfunc.getRandInt(totalRate)
        var tempRate = 0
        for (dropInfo in this.monsterRewardMap) {
            if (dropRand <= tempRate + dropInfo.value) {
                return dropInfo.key
            }
            tempRate += dropInfo.value
        }
        return null
    }

    fun surpriseTimesDraw(): Int? {
        var totalRate = 0
        for (dropInfo in this.surpriseTimesMap) {
            totalRate += dropInfo.value
        }
        val dropRand = com.point18.slg2d.common.commonfunc.getRandInt(totalRate)
        var tempRate = 0
        for (dropInfo in this.surpriseTimesMap) {
            if (dropRand <= tempRate + dropInfo.value) {
                return dropInfo.key
            }
            tempRate += dropInfo.value
        }
        return null
    }

    fun bossTimeDraw(): Int? {
        var totalRate = 0
        for (dropInfo in this.bossTimeMap) {
            totalRate += dropInfo.value
        }
        val dropRand = com.point18.slg2d.common.commonfunc.getRandInt(totalRate)
        var tempRate = 0
        for (dropInfo in this.bossTimeMap) {
            if (dropRand <= tempRate + dropInfo.value) {
                return dropInfo.key
            }
            tempRate += dropInfo.value
        }
        return null
    }

    fun bossRewardDraw(): Int? {
        var totalRate = 0
        for (dropInfo in this.bossRewardMap) {
            totalRate += dropInfo.value
        }
        val dropRand = com.point18.slg2d.common.commonfunc.getRandInt(totalRate)
        var tempRate = 0
        for (dropInfo in this.bossRewardMap) {
            if (dropRand <= tempRate + dropInfo.value) {
                return dropInfo.key
            }
            tempRate += dropInfo.value
        }
        return null
    }

    fun bossRewardNum(id: Int): Int {
        return this.bossRewardNum(id)
    }
}

class PalaceProtoCache : ProtoCacheInit("palace.xml") {
    var palaceProtoMap: Map<Int, Map<Int, PalaceProto>> = mapOf()
    var palaces: List<PalaceProto> = listOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<PalaceProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as PalaceProtoResult

        val tmpPalaceProtoMap: HashMap<Int, HashMap<Int, PalaceProto>> = hashMapOf()
        val tmpPalaces: LinkedList<PalaceProto> = LinkedList()
        for (vo in readXmlResult.l) {
            // 消耗
            vo.costMap = LinkedList()
            val cost = resStringToResVoList(vo.cost)

            if (cost == null) {
                throw RuntimeException("research.xml中的Resources字段解析出错${vo.cost}")
            } else {
                vo.costMap = cost
            }

            // 奖励
            val monsterReward = vo.monsterReward.split("|")
            if (monsterReward.isEmpty()) {
                throw RuntimeException("palace.xml中的monsterReward字段解析出错1")
            }

            val tmpMonsterRewardMap = hashMapOf<Int, Int>()
            for (eachMonsterReward in monsterReward) {
                val eachMonsterRewardSplit = eachMonsterReward.split(";")
                if (eachMonsterRewardSplit.size != 3) {
                    throw RuntimeException("palace.xml中的monsterReward字段解析出错1")
                }
                val each0 = eachMonsterRewardSplit[0].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的monsterReward字段解析出错1")
                eachMonsterRewardSplit[1].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的monsterReward字段解析出错1")
                val each2 = eachMonsterRewardSplit[2].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的monsterReward字段解析出错1")
                tmpMonsterRewardMap[each0] = each2
            }
            vo.monsterRewardMap = tmpMonsterRewardMap

            // 打boss概率
            val bossTimes = vo.bossTimes.split("|")
            if (bossTimes.isEmpty()) {
                throw RuntimeException("palace.xml中的bossTimes字段解析出错1")
            }
            val tmpBossTimeMap = hashMapOf<Int, Int>()
            for (eachBossTime in bossTimes) {
                val eachBossTimeSplit = eachBossTime.split(";")
                if (eachBossTimeSplit.size != 2) {
                    throw RuntimeException("palace.xml中的bossTimes字段解析出错1")
                }
                val each0 = eachBossTimeSplit[0].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的bossTimes字段解析出错1")
                val each1 = eachBossTimeSplit[1].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的bossTimes字段解析出错1")
                tmpBossTimeMap[each0] = each1
            }
            vo.bossTimeMap = tmpBossTimeMap

            // 祝福概率
            val surpriseTimes = vo.surpriseTimes.split("|")
            if (surpriseTimes.isEmpty()) {
                throw RuntimeException("palace.xml中的surpriseTimes字段解析出错1")
            }
            val tmpSurpriseTimesMap = hashMapOf<Int, Int>()
            for (eachSurpriseTimes in surpriseTimes) {
                val eachSurpriseTimesSplit = eachSurpriseTimes.split(";")
                if (eachSurpriseTimesSplit.size != 2) {
                    throw RuntimeException("palace.xml中的surpriseTimes字段解析出错1")
                }
                val each0 = eachSurpriseTimesSplit[0].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的surpriseTimes字段解析出错1")
                val each1 = eachSurpriseTimesSplit[1].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的surpriseTimes字段解析出错1")
                tmpSurpriseTimesMap[each0] = each1
            }
            vo.surpriseTimesMap = tmpSurpriseTimesMap

            // 打boss奖励概率
            val bossReward = vo.bossReward.split("|")
            if (bossReward.isEmpty()) {
                throw RuntimeException("palace.xml中的BossReward字段解析出错1")
            }
            val tmpBossRewardMap = hashMapOf<Int, Int>()
            val tmpBossRewardNumMap = hashMapOf<Int, Int>()
            for (eachbossReward in bossReward) {
                val eachBossRewardSplit = eachbossReward.split(";")
                if (eachBossRewardSplit.size != 3) {
                    throw RuntimeException("palace.xml中的BossReward字段解析出错1")
                }
                val each0 = eachBossRewardSplit[0].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的BossReward字段解析出错1")
                val each1 = eachBossRewardSplit[1].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的BossReward字段解析出错1")
                val each2 = eachBossRewardSplit[2].toIntOrNull()
                    ?: throw RuntimeException("palace.xml中的BossReward字段解析出错1")
                tmpBossRewardMap[each0] = each2
                tmpBossRewardNumMap[each0] = each1
            }
            vo.bossRewardMap = tmpBossRewardMap
            vo.bossRewardNumMap = tmpBossRewardNumMap

            tmpPalaces.add(vo)

            var palaceList = hashMapOf<Int, PalaceProto>()
            val tempList = tmpPalaceProtoMap[vo.palaceID]
            if (tempList != null) {
                palaceList = tempList
            }
            palaceList[vo.palaceLevel] = vo

            tmpPalaceProtoMap[vo.palaceID] = palaceList
        }
        palaceProtoMap = tmpPalaceProtoMap
        palaces = tmpPalaces
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }
}