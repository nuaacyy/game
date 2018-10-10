package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class InnerBuildingLocationProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InnerBuildingLocationProto>
): Serializable

data class InnerBuildingLocationProto(
    val id: Int,// 编号
    val buildType: Int, // 建筑坑位
    val interfaceType: String, // 可造的建筑
    val area: Int // 区域
): Serializable {
    var interfaceTypeList: List<Int> = listOf()

}

class InnerBuildingLocationProtoCache : ProtoCacheInit("buildingLocation.xml") {
    var protoMap: Map<Int, InnerBuildingLocationProto> = mapOf()
    var protoList: List<InnerBuildingLocationProto> = listOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InnerBuildingLocationProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InnerBuildingLocationProtoResult

        val tmpProtoMap: HashMap<Int, InnerBuildingLocationProto> = hashMapOf()
        val tmpProtoList: LinkedList<InnerBuildingLocationProto> = LinkedList()
        for (vo in readXmlResult.l) {
            val typeList = LinkedList<Int>()
            if (vo.interfaceType != "0") {
                val str = stringsSplit(vo.interfaceType, ";")

                for (element in str) {
                    val pos = strconvAtoi(element)
                        ?: throw RuntimeException("buildingLocation.xml interfaceType${vo.interfaceType} err")

                    typeList.add(pos)
                }
            }
            vo.interfaceTypeList = typeList


            if (tmpProtoMap[vo.buildType] != null) {
                throw RuntimeException("buildingLocation.xml  BuildType${vo.buildType} 重复")
            }
            tmpProtoMap[vo.buildType] = vo

            tmpProtoList.add(vo)
        }
        this.protoMap = tmpProtoMap
        this.protoList = tmpProtoList
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for (proto in this.protoList) {

            for (innerCityType in proto.interfaceTypeList) {
                pcs.innerBuildingCache.innerBuildingProtoMap[innerCityType]
                    ?: throw RuntimeException("buildingLocation.xml中的InterfaceType中的:$innerCityType.在building.xml中不存在")

                if (proto.area > 0) {
                    pcs.innerBuildingAreaCache.protoMap[proto.area]
                        ?: throw RuntimeException("buildingLocation.xml中的Area中的:${proto.area}.在buildingArea.xml中不存在")

                }
            }

        }
    }

}