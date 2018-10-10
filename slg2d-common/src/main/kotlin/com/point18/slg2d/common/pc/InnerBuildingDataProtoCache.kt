package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.RES_PROPS
import java.io.Serializable
import java.util.*

data class InnerBuildingDataProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InnerBuildingDataProto>
) : Serializable

data class InnerBuildingDataProto(
    val id: Int,  // 编号
    val buildType: Int,  // 建筑类型
    val level: Int, // 等级
    val levelUpConsume: String, // 升级消耗
    val levelUpTime: Int, // 升级时间
    val destoryTime: Int, // 拆除时间
    val unLock: String, // 解锁条件
    val cancel: String, // 取消返还
    val levelUpEffect: String, // 建筑效果
    val extraEffect: String, // 额外建筑效果
    val power: Int // 战斗力
) : Serializable {
    var cancelRes: List<ResVo> = listOf()
    var levelUpConsumeRes: List<ResVo> = listOf()
    var unLockMap: Map<Int, Map<Int, Int>> = mapOf()
    var upEff: Map<Int, Int> = mapOf()                     // 建筑特效
}

data class BuildMap(val buildMapInfo: LinkedList<Int>)

class InnerBuildingDataProtoCache : ProtoCacheInit("buildingData.xml") {
    var effBuildMap: Map<Int, BuildMap> = mapOf()
    private var protoMap: Map<Int, Map<Int, InnerBuildingDataProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InnerBuildingDataProtoResult>(pcs, configFileName)
    }

    fun fetchSpecBuildingMap(upEffKey: Int): List<Int>? {
        val buildMap = effBuildMap[upEffKey] ?: return null
        return buildMap.buildMapInfo
    }

    /**
     * 计算建筑效果
     */
    fun calUpdateEffMap(buildingType: Int, buildingLv: Int): Map<Int, Int> {
        val updateMap = hashMapOf<Int, Int>()
        val buildProtoMap = protoMap[buildingType]
        if (buildProtoMap == null) {
            return updateMap
        }

        for (lv in 1..buildingLv) {
            val buildingProto = buildProtoMap[lv]
            if (buildingProto == null) {
                return updateMap
            }
            buildingProto.upEff.forEach { updateMap[it.key] = 1 }
        }

        return updateMap
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InnerBuildingDataProtoResult

        val tmpEffBuildMap: HashMap<Int, BuildMap> = hashMapOf()
        val tmpProtoMap: HashMap<Int, HashMap<Int, InnerBuildingDataProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val c = resStringToResVoList(vo.levelUpConsume)
                ?: throw RuntimeException("buildingData.xml :: LevelUpConsume ${vo.levelUpConsume} 奖励格式不合法.")
            vo.levelUpConsumeRes = c

            val a = resStringToResVoList(vo.cancel)
            if (a == null) {
                throw RuntimeException("buildingData.xml :: Cancel ${vo.cancel} 奖励格式不合法.")
            }
            vo.cancelRes = a

            val unLockMap = hashMapOf<Int, HashMap<Int, Int>>()
            if (vo.unLock != "0") {
                val str1 = stringsSplit(vo.unLock, "|")

                for (element in str1) {
                    val ca = stringsSplit(element, ";")
                    if (ca.size != 3) {
                        throw RuntimeException("buildingData.xml中的UnLock解析出错:${vo.unLock}.")
                    }

                    val unlockType =
                        strconvAtoi(ca[0]) ?: throw RuntimeException("buildingData.xml中的UnLock解析出错1:$element.")

                    val unlockValue1 =
                        strconvAtoi(ca[1]) ?: throw RuntimeException("buildingData.xml中的UnLock解析出错2:$element")

                    val unlockValue2 =
                        strconvAtoi(ca[2]) ?: throw RuntimeException("buildingData.xml中的UnLock解析出错3:$element")

                    val vounLockMapunlockType = unLockMap.getOrPut(unlockType) { hashMapOf() }

                    var vounLockMapunlockTypunlockValue1 = vounLockMapunlockType[unlockValue1]

                    if (vounLockMapunlockTypunlockValue1 == null) {
                        vounLockMapunlockTypunlockValue1 = unlockValue2
                        vounLockMapunlockType[unlockValue1] = vounLockMapunlockTypunlockValue1
                    } else {
                        throw RuntimeException("buildingData.xml中的UnLock重复:$vounLockMapunlockTypunlockValue1.")
                    }

                }
            }
            vo.unLockMap = unLockMap

            // 建筑效果
            val levelUpEffect = vo.levelUpEffect
            val upEff = hashMapOf<Int, Int>()
            if (levelUpEffect != "0") {
                val levelUpEffectStr = stringsSplit(levelUpEffect, "|")

                for (element in levelUpEffectStr) {
                    val b = stringsSplit(element, ";")

                    if (b.size != 2) {
                        throw RuntimeException("BuildingDate中的LevelUpEffect字段解析出错,长度不是2位:$element")
                    }

                    val effType =
                        strconvAtoi(b[0]) ?: throw RuntimeException("BuildingDate中的LevelUpEffect字段解析出错: 错误1:$element")

                    val effValue =
                        strconvAtoi(b[1]) ?: throw RuntimeException("BuildingDate中的LevelUpEffect字段解析出错: 错误2:$element")

                    upEff[effType] = effValue

                    // 将重复的剔除，遍历
                    var pd = 0
                    val thiseffBuildMapeffType = tmpEffBuildMap.getOrPut(effType) { BuildMap(LinkedList()) }

                    for (value in thiseffBuildMapeffType.buildMapInfo) {

                        if (vo.buildType == value) {
                            pd = 1
                        }
                    }

                    if (pd == 0) {
                        thiseffBuildMapeffType.buildMapInfo.add(vo.buildType)
                    }
                }
            }

            // 额外建筑效果
            if (vo.extraEffect != "0") {
                val extraEffectStr = stringsSplit(vo.extraEffect, "|")

                for (element in extraEffectStr) {
                    val b = stringsSplit(element, ";")

                    if (b.size != 2) {
                        throw RuntimeException("BuildingDate中的ExtraEffect字段解析出错,长度不是2位:$element")
                    }

                    val effType =
                        strconvAtoi(b[0]) ?: throw RuntimeException("BuildingDate中的ExtraEffect字段解析出错: 错误1:$element")

                    val effValue =
                        strconvAtoi(b[1]) ?: throw RuntimeException("BuildingDate中的ExtraEffect字段解析出错: 错误2:$element")

                    upEff[effType] = effValue

                    // 将重复的剔除，遍历
                    var pd = 0
                    val thiseffBuildMapeffType = tmpEffBuildMap.getOrPut(effType) { BuildMap(LinkedList()) }

                    for (value in thiseffBuildMapeffType.buildMapInfo) {

                        if (vo.buildType == value) {
                            pd = 1
                        }
                    }

                    if (pd == 0) {
                        thiseffBuildMapeffType.buildMapInfo.add(vo.buildType)
                    }
                }
            }

            vo.upEff = upEff

            val typesMap = tmpProtoMap.getOrPut(vo.buildType) { hashMapOf() }
            typesMap[vo.level] = vo
        }
        this.protoMap = tmpProtoMap
        this.effBuildMap = tmpEffBuildMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for ((_, list) in this.protoMap) {

            for ((_, proto) in list) {
                val buildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[proto.buildType]
                    ?: throw RuntimeException("buildingData.xml中的BuildType中的:${proto.buildType}.在building.xml中不存在")


                if (proto.level > buildingProto.topLevel) {
                    throw RuntimeException("buildingData.xml中的Level中的:${proto.level}.超过了building.xml中的等级上限:${buildingProto.topLevel}")
                }

                for (r in proto.levelUpConsumeRes) {
                    if (r.resType == RES_PROPS) {
                        // 检测到了道具,需要这个道具必须满足可以资源补齐
                        val prop = pcs.equipCache.equipProtoMap[r.subType.toInt()]
                        if (prop == null || prop.propertyValue == 0) {
                            throw RuntimeException("buildingData.xml中的levelUpConsumeRes中的道具消耗ID:${r.subType}在道具表里propertyValue为0, 行ID是:${proto.id}")
                        }
                    }
                }
            }
        }
    }

    // 根据类型和等级获取模板
    fun fetchProtoByTypeLv(innerCityType: Int, lv: Int): InnerBuildingDataProto? {
        val lvMap = this.protoMap[innerCityType] ?: return null
        return lvMap[lv]
    }

    /**
     * 特定建筑类型是否存在
     */
    fun checkSpecBuildTypeExist(innerCityType: Int): Boolean {
        return this.protoMap[innerCityType] != null
    }

    // 根据类型和等级和升级效果类型获取升级的值
    fun getEffValue(buildingType: Int, lv: Int, effKey: Int): Int {
        val lvMap = this.protoMap[buildingType]
        if (lvMap == null) {
            return 0
        }
        val llMap = lvMap[lv]
        if (llMap == null) {
            return 0
        }

        val lllMap = llMap.upEff[effKey]
        if (lllMap == null) {
            return 0
        }
        return lllMap
    }
}