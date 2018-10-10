package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class VipSetResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<VipSetProto>
) : Serializable

data class VipSetProto(
    val id: Int, //唯一ID
    val level: Int, //vip等级
    val needExp: Int, //所需经验
    val reward: String, //升级奖励
    val vipAbility: String, //vip属性加成
    val energyDay: String   //每日免费行动力
) : Serializable

data class VipSet(
    val id: Int,
    val level: Int,
    val needExp: Int,
    val reward: List<ResVo>,
    val ability: Map<Int, Int>,
    val energyMap: Map<Int, Int>
)

class VipSetProtoCache : ProtoCacheInit("vipSet.xml") {
    var vipSetMap: Map<Int, VipSet> = mapOf()
    var list: List<VipSet> = listOf()
    lateinit var maxLvVip: VipSet

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<VipSetResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as VipSetResult

        val tmpVipSetMap: HashMap<Int, VipSet> = hashMapOf()
        val tmpList: LinkedList<VipSet> = LinkedList()
        for (proto in readXmlResult.l) {
            val reward =
                resStringToResVoList(proto.reward) ?: throw RuntimeException("VipSet中的reward字段解析出错: ${proto.reward}")
            val energyMap = hashMapOf<Int, Int>()
            val energySpilt = proto.energyDay.split(";")
            if (energySpilt.size != 2 && proto.energyDay != "0") {
                throw RuntimeException("VipSet中的reward字段解析出错: ${proto.energyDay}")
            }
            if (energySpilt.size == 2) {
                val energyPropsIdInt = energySpilt[0].toIntOrNull()
                val energyPropsNumInt = energySpilt[1].toIntOrNull()
                val energyPropsId = energyPropsIdInt
                    ?: throw RuntimeException("VipSet中的reward字段解析出错: ${proto.energyDay}")
                val energyPropsNum = energyPropsNumInt
                    ?: throw RuntimeException("VipSet中的reward字段解析出错: ${proto.energyDay}")
                energyMap[energyPropsId] = energyPropsNum
            }
            val abilityMap = parseIntMap(proto.vipAbility)
                ?: throw RuntimeException("vipSet.xml :: Alibity Config ERR,Vip Lv:${proto.level}")
            val set = VipSet(
                proto.id,
                proto.level,
                proto.needExp,
                reward,
                abilityMap,
                energyMap
            )

            tmpVipSetMap[set.level] = set
            tmpList.add(set)
        }

        if (tmpList.isEmpty()) {
            throw RuntimeException("VipSet中无任何数据")
        }

        tmpList.sortBy { it.level }
        this.list = tmpList
        this.maxLvVip = this.list.last()
        this.vipSetMap = tmpVipSetMap

    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}