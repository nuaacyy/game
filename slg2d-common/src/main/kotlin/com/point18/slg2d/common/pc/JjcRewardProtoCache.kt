package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.JJC_REWARD_TYPE_DAY
import java.io.Serializable
import java.util.*

data class JjcRewardResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<JjcRewardProto>
): Serializable

data class JjcRewardProto(
    val id: Int,
    val type: Int, // 类型 1:最高排名奖励 2:积分奖励 3:每日排名奖励
    val condition: String, // 条件
    val goldReward: Long, // 奖励 绑钻
    val arenaReward: Long, // 竞技币奖励
    val rewardShow: String // 奖励邮件内容
): Serializable {
    var condValues: List<Int> = listOf()// 解析后的条件值

}

class JjcRewardProtoCache : ProtoCacheInit("arenaRankReward.xml") {
    var protoMap: Map<Int, JjcRewardProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<JjcRewardResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as JjcRewardResult

        val tmpProtoMap: HashMap<Int, JjcRewardProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val condValues = LinkedList<Int>()
            // 条件
            val conds = stringsSplit(vo.condition, "|")
            for (cond in conds) {
                val value = strconvAtoi(cond)
                    ?: throw RuntimeException("arenaRankReward.xml :: condition ${vo.condition}  中含有无法转换为数字的配置 ERR:$cond.")
                condValues.add(value)
            }
            vo.condValues = condValues
            // 根据不同类型的奖励，验证配置的个数是否正确

            if (vo.type == com.point18.slg2d.common.constg.JJC_REWARD_TYPE_RANK || vo.type == com.point18.slg2d.common.constg.JJC_REWARD_TYPE_SCORE) {
                if (vo.condValues.size != 1) {
                    throw RuntimeException("arenaRankReward.xml :: condition ${vo.condValues}  最高排名奖励 或 积分奖励 只能配置1个条件.")
                }
            } else {
                if (vo.condValues.size != 2) {
                    throw RuntimeException("arenaRankReward.xml :: condition ${vo.condValues}  每日排名奖励 只能配置2个条件.")
                }
            }
            tmpProtoMap[vo.id] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

    fun fetchDayReward(rank: Int): JjcRewardProto? {
        for ((_, vo) in this.protoMap) {
            if (vo.type != JJC_REWARD_TYPE_DAY) {
                continue
            }
            // 判断排名范围
            if (rank >= vo.condValues[0] && rank <= vo.condValues[1]) {
                return vo
            }
        }

        return null
    }
}