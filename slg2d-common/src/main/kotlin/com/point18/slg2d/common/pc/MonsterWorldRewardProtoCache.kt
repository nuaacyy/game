package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class MonsterWorldRewardProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MonsterWorldRewardProto>
): Serializable

data class MonsterWorldRewardProto(
    val id: Int, // 编号
    val turn: String, // 名次
    val reward: String  // 攻击奖励道具
): Serializable {
    var rewardResVo: List<ResVo> = listOf()// 攻击奖励道具

}

class MonsterWorldRewardProtoCache : ProtoCacheInit("monsterWorldReward.xml") {
    var monsterWorldRewardProtoMap: Map<Int, MonsterWorldRewardProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MonsterWorldRewardProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as  MonsterWorldRewardProtoResult

        val tmpMonsterWorldRewardProtoMap: HashMap<Int, MonsterWorldRewardProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val reward = resStringToResVoList(vo.reward)
            if (null == reward) {
                throw RuntimeException("monsterWorldReward.xml 中Reward配置错误:${vo.reward}")
            } else {
                vo.rewardResVo = reward
            }
            tmpMonsterWorldRewardProtoMap[vo.id] = vo
        }
        this.monsterWorldRewardProtoMap = tmpMonsterWorldRewardProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}