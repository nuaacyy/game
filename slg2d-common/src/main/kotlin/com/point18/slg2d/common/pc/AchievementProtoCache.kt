package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AchievementResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AchievementProto>
) : Serializable

data class AchievementProto(
    val id: Int,
    val achieveType: Int,
    val stage: Int,
    val name: String,
    val completeCon: String, // 完成条件
    val icon: String,
    val reward: String, // 必给奖励
    val endCon: Int,
    val interfaceType: Int,
    val desc: String

) : Serializable {
    // 完成条件集合
    var completeCondMap: Map<Int, LinkedList<Int>> = mapOf()

    // 任务完成奖励
    var rewards: List<ResVo> = LinkedList()
}

class AchievementProtoCache : ProtoCacheInit("achievement.xml") {
    var achievementProtoMap: Map<Int, AchievementProto> = mapOf()
    var achievementProtoMapByType: Map<Int, Map<Int, AchievementProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AchievementResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AchievementResult

        val tmpAchievementProtoMap = hashMapOf<Int, AchievementProto>()
        val tmpAchievementProtoMapByType = hashMapOf<Int, HashMap<Int, AchievementProto>>()
        for (vo in readXmlResult.l) {
            if (tmpAchievementProtoMap.containsKey(vo.id)) {
                throw RuntimeException("achievement.xml :: id[${vo.id}]重复")
            }

            val resVo =
                resStringToResVoList(vo.reward) ?: throw RuntimeException("AchievementProtoCache中的reward字段${vo.reward}解析出错")
            vo.rewards = resVo

            val tmpCompleteCondMap = hashMapOf<Int, LinkedList<Int>>()

            val cc = vo.completeCon.split(":")
            if ((cc.size) != 2) {
                throw RuntimeException("AchievementProtoCache中的completeCon字段${vo.completeCon}解析出错")
            } else {
                val checkType = cc[0].toInt()

                val tmpIntList = tmpCompleteCondMap.getOrPut(checkType) { LinkedList() }
                val checkValues = cc[1].split("_")
                for (checkValue in checkValues) {
                    val value = checkValue.toInt()
                    tmpIntList.add(value)
                }
            }
            vo.completeCondMap = tmpCompleteCondMap

            tmpAchievementProtoMap[vo.id] = vo

            val endConMap = hashMapOf<Int, Int>()
            val tmpMap = tmpAchievementProtoMapByType.getOrPut(vo.achieveType) {
                hashMapOf()
            }

            if ((tmpMap)[vo.stage] != null) {
                throw RuntimeException("achievement.xml :: 星级${vo.stage}配置重复")
            }
            if (endConMap[vo.stage] != null) {
                throw RuntimeException("achievement.xml :: ${vo.endCon}配置重复")
            }
            endConMap[vo.stage] = vo.endCon
            tmpMap[vo.stage] = vo
        }

        this.achievementProtoMap = tmpAchievementProtoMap
        this.achievementProtoMapByType = tmpAchievementProtoMapByType
        return
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((achievementType, achievements) in pcs.achievementProtoCache.achievementProtoMapByType) {
            var i = 1
            while (i < (achievements.size)) {
                if (achievements[i] == null) {
                    throw RuntimeException("achievement.xml :: 成就类别${achievementType}中的星级${i}不存在")
                }
                i++
            }

        }
        return
    }

    /**
     * 找到特定成就的模板
     */
    fun findSpecAchProto(achievementId: Int): AchievementProto? {
        return achievementProtoMap[achievementId]
    }

}


