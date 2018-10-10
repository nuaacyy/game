package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AllianceGiftLevelProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AllianceGiftLevelProto>
):Serializable

data class AllianceGiftLevelProto(
    val id: Int,  // 编号
    val level: Int,  // 等级
    val exp: Int,  // 升级所需经验
    val giftShape: String
) : Serializable{
    var giftShapeMap: Map<String, Int> = mapOf()// 走掉落

}

class AllianceGiftLevelProtoCache : ProtoCacheInit("allianceGiftLevel.xml") {
    var allianceGiftLevelProtoCacheMap: Map<Int, AllianceGiftLevelProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AllianceGiftLevelProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AllianceGiftLevelProtoResult

        val tmpAllianceGiftLevelProtoCacheMap: HashMap<Int, AllianceGiftLevelProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpAllianceGiftLevelProtoCacheMap.containsKey(vo.level)) {
                throw RuntimeException("allianceGiftLevel.xml :: level[${vo.level}]重复")
            }

            val (effectMap, ok, sum) = resStringToDropBag(vo.giftShape)
            if (!ok || effectMap == null) {
                throw RuntimeException("allianceGiftLevel.xml中的GiftShape字段配置出错:${vo.giftShape}")
            }
            if (sum < 10000) {
                throw RuntimeException("allianceGiftLevel.xml中的GiftShape字段总权值少于10000:${vo.giftShape},当前值是:$sum")
            }

            vo.giftShapeMap = effectMap

            tmpAllianceGiftLevelProtoCacheMap[vo.level] = vo
        }
        allianceGiftLevelProtoCacheMap = tmpAllianceGiftLevelProtoCacheMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, e) in this.allianceGiftLevelProtoCacheMap) {
            for ((_, dpId) in e.giftShapeMap) {
                pcs.allianceGiftProtoCache.allianceGiftProtoMap[dpId]
                    ?: throw RuntimeException("allianceGiftLevel.xml中的GiftShapeMap字段配置的礼物在礼物表中找不到:$dpId")
            }
        }
    }

    // 根据当前的等级随机一个大礼物
    fun randGetGiftProtoId(lv: Int): (AllianceGiftProto?) {
        val info = allianceGiftLevelProtoCacheMap[lv]
        if (info == null) {
            return null
        }

        val randGiftId = findValueFromDropBag(info.giftShapeMap)

        return pcs.allianceGiftProtoCache.allianceGiftProtoMap[randGiftId]
    }

}
