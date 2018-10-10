package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.MainBuilding
import java.io.Serializable
import java.util.*

data class InteriorTaskRewardProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InteriorTaskRewardProto>
) : Serializable

data class InteriorTaskRewardProto(
    val id: Int, // 编号
    val group: Int,  // 组
    val castleType: Int, // 城堡等级
    val reward: String // 奖励格式
) : Serializable

class InteriorTaskRewardProtoCache : ProtoCacheInit("interiorTaskReward.xml") {
    var interiorTaskRewardMap: Map<Int, InteriorTaskRewardProto> = mapOf()
    var interiorTaskRewardMapByGroupAndCastleType: Map<Int, HashMap<Int, InteriorTaskRewardProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InteriorTaskRewardProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InteriorTaskRewardProtoResult

        val tmpInteriorTaskRewardMap: HashMap<Int, InteriorTaskRewardProto> = hashMapOf()
        val tmpInteriorTaskRewardMapByGroupAndCastleType: HashMap<Int, HashMap<Int, InteriorTaskRewardProto>> =
            hashMapOf()
        for (vo in readXmlResult.l) {
            tmpInteriorTaskRewardMap[vo.id] = vo

            val interiorTaskRewardMapByGroupAndCastleTypevogroup =
                tmpInteriorTaskRewardMapByGroupAndCastleType.getOrPut(vo.group) { hashMapOf() }

            if (interiorTaskRewardMapByGroupAndCastleTypevogroup[vo.castleType] != null) {
                throw RuntimeException("interiorTaskReward.xml中星级重复${vo.castleType}.")
            } else {
                interiorTaskRewardMapByGroupAndCastleTypevogroup[vo.castleType] = vo
            }
        }
        this.interiorTaskRewardMap = tmpInteriorTaskRewardMap
        this.interiorTaskRewardMapByGroupAndCastleType = tmpInteriorTaskRewardMapByGroupAndCastleType
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in this.interiorTaskRewardMap) {
            // 检测城堡等级
            pcs.innerBuildingDataCache.fetchProtoByTypeLv(MainBuilding, proto.castleType)
                ?: throw RuntimeException("interiorTaskReward.xml中的CastleType解析出错2:$MainBuilding ${proto.castleType}. 不在存在该等级的城堡配置")
        }
    }

}