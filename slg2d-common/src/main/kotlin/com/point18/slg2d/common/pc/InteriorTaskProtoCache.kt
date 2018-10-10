package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class InteriorTaskProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InteriorTaskProto>
): Serializable

data class InteriorTaskProto(
    val id: Int,  // 编号
    val quality: Int,// 品质
    val time: Int, // 时间
    val percent: Int,  // 权重
    val reward: String, // 基础奖励掉落包
    val drop: String // 额外奖励掉落包
) : Serializable{
    var rewardMap: Map<String, Int> = mapOf()  // 奖励权重map
    var dropMap: Map<String, Int> = mapOf()    // 掉落总权map

}

data class InteriorTaskQualityProto(
    var quality: Int,  // 品质
    var interiorTaskProtoMapByQuality: LinkedList<InteriorTaskProto>,  // 任务数组
    var totalPercent: Int                   // 总权重
)

class InteriorTaskProtoCache : ProtoCacheInit("interiorTask.xml") {
    var interiorTaskProtoMap: Map<Int, InteriorTaskProto> = mapOf()
    var interiorTaskProtoMapByQuality: Map<Int, InteriorTaskQualityProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InteriorTaskProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InteriorTaskProtoResult

        val tmpInteriorTaskProtoMap: HashMap<Int, InteriorTaskProto> = hashMapOf()
        val tmpInteriorTaskProtoMapByQuality: HashMap<Int, InteriorTaskQualityProto> = hashMapOf()
        for (vo in readXmlResult.l) {

            if (vo.reward != "0") {

                val (vorewardMap, ok, sum) = resStringToDropBag(vo.reward)

                if (vorewardMap == null || !ok) {
                    throw RuntimeException("interiorTask.xml中的Reward解析出错1:${vo.reward}.")
                }

                if (sum < 10000) {
                    throw RuntimeException("interiorTask.xml中的Reward字段总权值少于10000:${vo.reward},当前值是:$sum")
                }
            }

            if (vo.drop != "0") {
                val (vodropMap, ok, _) = resStringToDropBag(vo.drop)

                if (!ok || vodropMap == null) {
                    throw RuntimeException("interiorTask.xml中的Drop解析出错1:${vo.drop}.")
                }
            }

            tmpInteriorTaskProtoMap[vo.id] = vo

            var interiorTaskProtoMapByQualityvoquality = tmpInteriorTaskProtoMapByQuality[vo.quality]

            if (null == interiorTaskProtoMapByQualityvoquality) {
                tmpInteriorTaskProtoMapByQuality[vo.quality] =
                    InteriorTaskQualityProto(vo.quality, LinkedList(), 0)
                interiorTaskProtoMapByQualityvoquality = tmpInteriorTaskProtoMapByQuality[vo.quality]
            }
            if (null == interiorTaskProtoMapByQualityvoquality) {
                throw RuntimeException("interiorTask.xml中的this.interiorTaskProtoMapByQuality[vo.quality]解析出错1:${vo.quality}.")
            }
            interiorTaskProtoMapByQualityvoquality.interiorTaskProtoMapByQuality.add(vo)

            interiorTaskProtoMapByQualityvoquality.totalPercent += vo.percent

        }
        this.interiorTaskProtoMap = tmpInteriorTaskProtoMap
        this.interiorTaskProtoMapByQuality = tmpInteriorTaskProtoMapByQuality
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in this.interiorTaskProtoMap) {
            // 检测掉落id
            var tempStr = stringsSplit(proto.drop, ";")

            if (tempStr.size != 2) {
                throw RuntimeException("interiorTask.drop:${proto.drop}.")
            }

            val dropId = strconvAtoi(tempStr[0]) ?: throw RuntimeException("interiorTask.xml中的Drop解析出错2:${proto.drop}")

            pcs.dropBagCache.dropBagMap[dropId] ?: throw RuntimeException("interiorTask中的Dropd再掉落包中不存在:$dropId")

            //检测reward
            tempStr = stringsSplit(proto.reward, "|")

            for (res in tempStr) {
                val str = stringsSplit(res, ";")

                if (str.size != 2) {
                    throw RuntimeException("interiorTask.reward:$res")
                }

                val rewardId = strconvAtoi(str[0]) ?: throw RuntimeException("interiorTask.xml中的Reward解析出错2:$res")

                pcs.interiorTaskRewardProtoCache.interiorTaskRewardMapByGroupAndCastleType[rewardId]
                    ?: throw RuntimeException("interiorTask中的Reward再掉落包中不存在:$res")

            }

        }
    }

}