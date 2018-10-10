package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class OfficeProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<OfficeProto>
) : Serializable

data class OfficeProto(
    val id: Int, // 编号
    val name: String,  //名称
    val mainType: Int, //官职大类
    val subType: Int, //官职小类
    val power: String, //功能
    val buff: String //buff效果
) : Serializable {
    var functionList: List<Int> = listOf()
    var buffMap: Map<Int, Int> = mapOf()

}

class OfficeProtoCache : ProtoCacheInit("office.xml") {
    var officeProtoMap: Map<Int, OfficeProto> = mapOf()
    var officeProtoMapByType: Map<Int, Map<Int, OfficeProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<OfficeProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as OfficeProtoResult

        val tmpOfficeProtoMap: HashMap<Int, OfficeProto> = hashMapOf()
        val tmpOfficeProtoMapByType: HashMap<Int, HashMap<Int, OfficeProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpOfficeProtoMap[vo.id] = vo

            val vobuffMap = parseIntMap(vo.buff) ?: throw RuntimeException("office.xml 解析Buff字段出错:${vo.id}")

            vo.buffMap = vobuffMap

            val functionList = LinkedList<Int>()
            val funcStrs = stringsSplit(vo.power, ":")

            for (str in funcStrs) {
                val function = strconvAtoi(str) ?: throw RuntimeException("office.xml 解析Function字段出错:${vo.id}")

                functionList.add(function)
            }
            vo.functionList = functionList

            val officeProtoMapByTypemainType = tmpOfficeProtoMapByType.getOrPut(vo.mainType)
            {
                hashMapOf()
            }
            officeProtoMapByTypemainType[vo.id] = vo
        }
        this.officeProtoMap = tmpOfficeProtoMap
        this.officeProtoMapByType = tmpOfficeProtoMapByType
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}