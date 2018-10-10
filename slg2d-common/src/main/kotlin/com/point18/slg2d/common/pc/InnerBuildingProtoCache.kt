package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class InnerBuildingProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InnerBuildingProto>
) : Serializable

data class InnerBuildingProto(
    val id: Int, // 编号
    val buildType: Int,// 建筑类型
    val name: String, // 建筑名称
    val bornType: Int, // 类型
    val topLevel: Int,  // 最大等级
    val canScout: Int //能否侦查
) : Serializable

class InnerBuildingProtoCache : ProtoCacheInit("building.xml") {
    var innerBuildingProtoMap: Map<Int, InnerBuildingProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InnerBuildingProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InnerBuildingProtoResult

        val tmpInnerBuildingProtoMap: HashMap<Int, InnerBuildingProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpInnerBuildingProtoMap[vo.buildType] = vo
        }
        this.innerBuildingProtoMap = tmpInnerBuildingProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in this.innerBuildingProtoMap) {
            if (!pcs.innerBuildingDataCache.checkSpecBuildTypeExist(proto.buildType)) {
                throw RuntimeException("building.xml中的BuildType中的:${proto.buildType}.在buildingData.xml中不存在")
            }

            var find = false
            for (buildingLocationProto in pcs.innerBuildingLocationCache.protoList) {
                for (innerCityType in buildingLocationProto.interfaceTypeList) {

                    if (innerCityType == proto.buildType) {
                        find = true
                        break
                    }
                }
            }

            if (!find) {
                throw RuntimeException("building.xml中的BuildType中的:${proto.buildType}.在buildingLocation.xml中不存在")
            }

        }
    }

}