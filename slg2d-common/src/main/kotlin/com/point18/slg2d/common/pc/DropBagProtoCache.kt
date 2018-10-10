package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class DropBagResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<DropBagProto>
): Serializable

data class DropBagProto(
    val id: Int,  //唯一ID
    val drop: String  //掉落内容
): Serializable {
    var dropMap: List<ResVo> = listOf()
}

class DropBagProtoCache : ProtoCacheInit("dropBag.xml") {
    var dropBagMap: Map<Int, DropBagProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<DropBagResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as DropBagResult

        val tmpDropBagMap: HashMap<Int, DropBagProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpDropBagMap.containsKey(vo.id)) {
                throw RuntimeException("dropBag.xml :: id[${vo.id}]重复")
            }
            val voDropMap = resStringToResVoList(vo.drop) ?: throw RuntimeException("dropBag.xml中的drop字段解析出错:${vo.drop}")
            vo.dropMap = voDropMap
            tmpDropBagMap[vo.id] = vo
        }
        this.dropBagMap = tmpDropBagMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

}