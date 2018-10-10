package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.RES_PROPS
import java.io.Serializable
import java.util.*

data class LordEquipmentResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<LordEquipmentProto>
) : Serializable

data class LordEquipmentProto(
    val id: Int, //唯一ID
    val propsID: Int, // 目标装备
    val forgeTime: Int,// 锻造基础时间	值，配置成秒
    val forgeMoney: String,// 锻造金币消耗	奖励格式
    val forgeCost: String, // 锻造材料	奖励格式
    val preEquipment: Int  // 装备前置条件,如果是新造 就配置0
) : Serializable {
    var forgeMoneyMap: List<ResVo> = listOf()
    var forgeCostMap: List<ResVo> = listOf()

}

class LordEquipmentProtoCache : ProtoCacheInit("lordEquipment.xml") {
    var lordEquipmentMap: Map<Int, LordEquipmentProto> = mapOf()
    var lordEquipmentMapByPropsId: Map<Int, LordEquipmentProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<LordEquipmentResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as LordEquipmentResult

        val tmpLordEquipmentMap: HashMap<Int, LordEquipmentProto> = hashMapOf()
        val tmpLordEquipmentMapByPropsId: HashMap<Int, LordEquipmentProto> = hashMapOf()
        for (vo in readXmlResult.l) {

            val voforgeMoneyMap = resStringToResVoList(vo.forgeMoney)
                ?: throw RuntimeException("lordEquipment.xml中的ForgeMoney字段解析出错:${vo.id}")

            vo.forgeMoneyMap = voforgeMoneyMap

            val voforgeCostMap =
                resStringToResVoList(vo.forgeCost) ?: throw RuntimeException("lordEquipment.xml中的ForgeCost字段解析出错:${vo.id}")

            vo.forgeCostMap = voforgeCostMap

            tmpLordEquipmentMap[vo.id] = vo
            tmpLordEquipmentMapByPropsId[vo.propsID] = vo
        }
        this.lordEquipmentMap = tmpLordEquipmentMap
        this.lordEquipmentMapByPropsId = tmpLordEquipmentMapByPropsId
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for ((_, proto) in this.lordEquipmentMap) {
            for (r in proto.forgeMoneyMap) {
                if (r.resType == RES_PROPS) {
                    // 检测到了道具,需要这个道具必须满足可以资源补齐
                    val prop = pcs.equipCache.equipProtoMap[r.subType.toInt()]
                    if (prop == null || prop.propertyValue == 0) {
                        throw RuntimeException("lordEquipment.xml中的forgeMoney中的道具消耗ID:${r.subType}在道具表里propertyValue为0, 行ID是:${proto.id}")
                    }
                }
            }
        }

    }

}