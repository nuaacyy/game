package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class HomeAreaProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<HomeAreaProto>
): Serializable

data class HomeAreaProto(
    val id: Int,  // 编号
    val level: Int, // 后宅等级
    val acreage: String,  // 面积
    val open: Int // 楼层
) : Serializable{
    var floorSpace: List<Int> = listOf()
}

class HomeAreaProtoCache : ProtoCacheInit("homeArea.xml") {
    var homeLvProtoMap: Map<Int, HomeAreaProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<HomeAreaProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as HomeAreaProtoResult

        val tmpHomeLvProtoMap: HashMap<Int, HomeAreaProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val split = stringsSplit(vo.acreage, ";")
            if (split.isEmpty() || split.size > 2) {
                throw RuntimeException("homeArea.xml :: floorSpace ${vo.acreage} 面积不合法.")
            } else if (split.size == 1 && "0" != split[0]) {
                throw RuntimeException("homeArea.xml :: floorSpace ${vo.acreage} 面积不合法.")
            }
            val floorSpace = LinkedList<Int>()
            split.forEach {
                val i = strconvAtoi(it) ?: throw RuntimeException("homeArea.xml中的floorSpace解析出错:$it")
                floorSpace.add(i)
            }
            vo.floorSpace = floorSpace

            tmpHomeLvProtoMap[vo.level] = vo
        }
        homeLvProtoMap = tmpHomeLvProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

}