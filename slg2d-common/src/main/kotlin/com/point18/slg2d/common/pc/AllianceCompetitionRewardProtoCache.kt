package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AllianceCompetitionRewardResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AllianceCompetitionRewardProto>
) : Serializable

data class AllianceCompetitionRewardProto(
    val id: Int,  // 编号
    val level: Int,  // 段位
    val stage: Int,  // 阶级
    val score: Int,  // 阶段积分值
    val dropPropsId: String // 3个dropPropsId,每个里面随一个奖励
) : Serializable {
    var dropPropsIdMap: List<Int> = listOf()// 走掉落

}

class AllianceCompetitionRewardProtoCache : ProtoCacheInit("allianceCompetitionReward.xml") {
    var allianceCompetitionRewardProtoMap: Map<Int, AllianceCompetitionRewardProto> = mapOf()//
    var allianceCompetitionRewardProtoMapByLvAndScore: Map<Int, HashMap<Int, AllianceCompetitionRewardProto>> =
        mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AllianceCompetitionRewardResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AllianceCompetitionRewardResult

        val tmpAllianceCompetitionRewardProtoMap = hashMapOf<Int, AllianceCompetitionRewardProto>()
        val tmpAllianceCompetitionRewardProtoMapByLvAndScore =
            hashMapOf<Int, HashMap<Int, AllianceCompetitionRewardProto>>()
        for (vo in readXmlResult.l) {
            if (tmpAllianceCompetitionRewardProtoMap.containsKey(vo.stage)) {
                throw RuntimeException("allianceCompetitionReward.xml :: stage[${vo.stage}]重复")
            }

            val dropPropsIds = vo.dropPropsId.split(";")
            if (dropPropsIds.size != 3) {
                throw RuntimeException("allianceCompetitionReward.xml中的DropPropsId字段配置出错,截取长度不是3个:${vo.dropPropsId}")
            }
            val idList = LinkedList<Int>()
            for (dpId in dropPropsIds) {
                val dId = (dpId.toInt())
                idList.add(dId)
            }
            vo.dropPropsIdMap = idList
            tmpAllianceCompetitionRewardProtoMap[vo.stage] = vo
            val tmp = tmpAllianceCompetitionRewardProtoMapByLvAndScore.getOrPut(vo.level) {
                hashMapOf()
            }
            tmp[vo.score] = vo
        }
        this.allianceCompetitionRewardProtoMap = tmpAllianceCompetitionRewardProtoMap
        this.allianceCompetitionRewardProtoMapByLvAndScore = tmpAllianceCompetitionRewardProtoMapByLvAndScore

    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, e) in this.allianceCompetitionRewardProtoMap) {
            for (dpId in e.dropPropsIdMap) {
                pcs.dropPropsProtoCache.dropPropsMap[dpId]
                    ?: throw RuntimeException("allianceCompetitionReward.xml中的DropPropsId字段配置的值在dropProps里找不到:$dpId")
            }
        }
    }

}