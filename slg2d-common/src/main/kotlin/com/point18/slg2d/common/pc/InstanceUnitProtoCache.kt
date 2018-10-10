package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class InstanceUnitResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InstanceUnitProto>
): Serializable

data class InstanceUnitProto(
    val id: Int,
    val unitId: Int, // 章节编号		1.第一章，2.第二章
    val unitReward: String,// 通关奖励		直接奖励道具
    val unitStarReward: String, // 星数奖励		星数:dropId%星数:dropId%
    val mainOpenCon: String // 开启条件
): Serializable {
    var unitRewardMap: List<ResVo> = listOf()                // 通关奖励		直接奖励道具
    var unitStarRewardMap: Map<Int, Int> = mapOf()    // 星数奖励		星数:dropId%星数:dropId%
    var mainOpenConMap: Map<Int, List<Int>> = mapOf()
}

class InstanceUnitProtoCache : ProtoCacheInit("instanceUnit.xml") {
    var protoMap: Map<Int, InstanceUnitProto> = mapOf()
    var protoMapByUnitId: Map<Int, List<InstanceUnitProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InstanceUnitResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InstanceUnitResult

        val tmpProtoMap: HashMap<Int, InstanceUnitProto> = hashMapOf()
        val tmpProtoMapByUnitId: HashMap<Int, LinkedList<InstanceUnitProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val unitReward = resStringToResVoList(vo.unitReward)
                ?: throw RuntimeException("instanceUnit.xml :: UnitReward ${vo.unitReward} 奖励格式不合法.")

            vo.unitRewardMap = unitReward

            val unitStarRewardMap = hashMapOf<Int, Int>()
            val cons = stringsSplit(vo.unitStarReward, "*")

            for (con in cons) {
                val cc = stringsSplit(con, "%")

                if (cc.size != 2) {
                    throw RuntimeException("instanceUnit.xml :: UnitStarReward 字段:${con}根据冒号解析长度不为2.")
                }

                val starNum = strconvAtoi(cc[0])
                val dropId = strconvAtoi(cc[1])
                if (dropId == null || starNum == null) {
                    throw RuntimeException("instanceUnit.xml :: UnitStarReward 字段:${con}根据冒号解析err.")
                }
                unitStarRewardMap[starNum] = dropId
            }
            vo.unitStarRewardMap = unitStarRewardMap

            val mainOpenConMap = mutableMapOf<Int, LinkedList<Int>>()
            val mainOpenCons = stringsSplit(vo.mainOpenCon, "|")
            for (c in mainOpenCons) {
                val e = stringsSplit(c, ";")

                if (e.size != 3) {
                    throw RuntimeException("instanceUnit.xml :: Id.${vo.id} mainOpenCon:$c 错误1")
                }

                val conType =
                    strconvAtoi(e[0]) ?: throw RuntimeException("instanceUnit.xml :: Id.${vo.id} mainOpenCon.$c 错误2")

                val conValue1 =
                    strconvAtoi(e[1]) ?: throw RuntimeException("instanceUnit.xml :: Id.${vo.id} mainOpenCon.%$c 错误3")

                val conValue2 =
                    strconvAtoi(e[2]) ?: throw RuntimeException("instanceUnit.xml :: Id.${vo.id} mainOpenCon.%$c 错误4")

                val cMap = mainOpenConMap.getOrPut(conType) { LinkedList() }
                cMap.add(conValue1)
                cMap.add(conValue2)
            }
            vo.mainOpenConMap = mainOpenConMap

            val protoMapByUnitIdvounitId = tmpProtoMapByUnitId.getOrPut(vo.unitId) { LinkedList() }
            protoMapByUnitIdvounitId.add(vo)
            tmpProtoMap[vo.unitId] = vo
        }
        this.protoMapByUnitId = tmpProtoMapByUnitId
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for ((_, p) in this.protoMap) {
            for ((_, dropId) in p.unitStarRewardMap) {
                pcs.dropBagCache.dropBagMap[dropId]
                    ?: throw RuntimeException("instanceUnit.xml ::表配置的dropId在dropBag里找不到:$dropId")

            }
        }
    }
}