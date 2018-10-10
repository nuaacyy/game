package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class InnerBuildingAreaProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InnerBuildingAreaProto>
): Serializable

data class InnerBuildingAreaProto(
    val id: Int, // 编号
    val area: Int, // 区域
    val unLockRes: String, // 解锁奖励格式
    val unLock: String // 解锁条件
): Serializable {
    var unLockResVos: List<ResVo> = listOf()
    var unLockMap: Map<Int, Map<Int, Int>> = mapOf()

}

class InnerBuildingAreaProtoCache : ProtoCacheInit("buildingArea.xml") {
    var protoMap: Map<Int, InnerBuildingAreaProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InnerBuildingAreaProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InnerBuildingAreaProtoResult

        val tmpProtoMap: HashMap<Int, InnerBuildingAreaProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val c = resStringToResVoList(vo.unLockRes)
                ?: throw RuntimeException("buildingArea.xml :: UnLockRes ${vo.unLockRes} 奖励格式不合法.")
            vo.unLockResVos = c
            tmpProtoMap[vo.area] = vo
            val unLockMap = hashMapOf<Int, HashMap<Int, Int>>()
            if (vo.unLock != "0") {
                val str1 = stringsSplit(vo.unLock, "|")

                for (element in str1) {
                    val b = stringsSplit(element, ";")

                    if (b.size != 3) {
                        throw RuntimeException("buildingArea.xml中的UnLock解析出错:$element")
                    }

                    val unlockType =
                        strconvAtoi(b[0]) ?: throw RuntimeException("buildingArea.xml中的UnLock解析出错1:$element")

                    val unlockValue1 =
                        strconvAtoi(b[1]) ?: throw RuntimeException("buildingArea.xml中的UnLock解析出错2:$element")

                    val unlockValue2 =
                        strconvAtoi(b[2]) ?: throw RuntimeException("buildingArea.xml中的UnLock解析出错3:$element")

                    val vounLockMapunlockType = unLockMap.getOrPut(unlockType) { hashMapOf() }

                    val vounLockMapunlockTypeunlockValue1 = vounLockMapunlockType[unlockValue1]
                    if (vounLockMapunlockTypeunlockValue1 == null) {
                        vounLockMapunlockType[unlockValue1] = unlockValue2
                    } else {
                        throw RuntimeException("buildingArea.xml中的UnLock重复:$vounLockMapunlockTypeunlockValue1.")
                    }

                }
            }
            vo.unLockMap = unLockMap
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}