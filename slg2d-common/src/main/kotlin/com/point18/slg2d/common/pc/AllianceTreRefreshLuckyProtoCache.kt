package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AllianceTreRefreshLuckyResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AllianceTreRefreshLuckyProto>
): Serializable

data class AllianceTreRefreshLuckyProto(
    val id: Int, // 编号I
    val qualityInfo: String, // 品质组成  品质:数量%...
    val luckNum: Int,// 随机到这个任务方案增加幸运值I
    val lowReward: String // 保底奖励(冒号前面表示品质 冒号后面表示dropProps表ID)
) : Serializable{
    var lowRewardQuality: Int = 0// 保底品质
    var lowRewardDropPropsId: Int = 0    // 保底掉落
    var qualityInfoMap: Map<Int, Int> = mapOf()// 品质组成  品质:数量%...I

}

class AllianceTreRefreshLuckyProtoCache : ProtoCacheInit("allianceTreRefreshLucky.xml") {
    var allianceTreRefreshLuckyProtoMap: Map<Int, AllianceTreRefreshLuckyProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AllianceTreRefreshLuckyResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AllianceTreRefreshLuckyResult

        val tmpAllianceTreRefreshLuckyProtoMap: HashMap<Int, AllianceTreRefreshLuckyProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpAllianceTreRefreshLuckyProtoMap.containsKey(vo.id)) {
                throw RuntimeException("allianceTreRefreshLucky.xml :: id[${vo.id}]重复")
            }

            val qualityInfoMap = hashMapOf<Int, Int>()
            for (quality in vo.qualityInfo.split("|")) {
                val qqInfo = quality.split(";")
                if (qqInfo.size != 2) {
                    throw RuntimeException("allianceTreRefreshLucky.xml中的QualityInfo字段解析出来的长度不为2:${vo.qualityInfo}")
                }
                val q = (qqInfo[0].toIntOrNull())    // 品质
                val qNum = (qqInfo[1].toIntOrNull()) // 该品质数量
                if (q != null && qNum != null) {
                    qualityInfoMap[q] = qNum
                } else {
                    throw RuntimeException("allianceTreRefreshLucky.xml中的QualityInfo字段解析error")
                }
                val lowReward = vo.lowReward.split(";")
                if (lowReward.size != 2) {
                    throw RuntimeException("allianceTreRefreshLucky.xml中的LowReward字段解析出来的长度不为2:${vo.lowReward}")
                }

                val lq = (lowReward[0].toIntOrNull())            // 保底品质选择
                val lqDropPropsId = (lowReward[1].toIntOrNull()) // 保底掉落的id
                if (lq != null && lqDropPropsId != null) {
                    vo.lowRewardQuality = lq
                    vo.lowRewardDropPropsId = lqDropPropsId
                } else {
                    throw RuntimeException("allianceTreRefreshLucky.xml中的LowReward字段解析error")
                }
            }
            vo.qualityInfoMap = qualityInfoMap
            tmpAllianceTreRefreshLuckyProtoMap[vo.id] = vo
        }
        this.allianceTreRefreshLuckyProtoMap = tmpAllianceTreRefreshLuckyProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, info) in this.allianceTreRefreshLuckyProtoMap) {
            info.qualityInfoMap[info.lowRewardQuality]
                ?: throw RuntimeException("allianceTreRefreshLucky.xml中的保底奖励字段中的品质在本行品质列表里找不到,异常品质编号为:${info.lowRewardQuality}")
            pcs.dropPropsProtoCache.dropPropsMap[info.lowRewardDropPropsId]
                ?: throw RuntimeException("allianceTreRefreshLucky.xml中的保底奖励字段中的ID在dropProps.xml里找不到,异常编号为:${info.lowRewardDropPropsId}")

        }
    }
}






