package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class DropPropsResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<DropPropsProto>
) : Serializable

data class DropPropsProto(
    val id: Int,  //唯一ID
    val name: String, //名称
    val award: String //奖励
) : Serializable {
    var dropMap: List<Drop> = listOf()
}

data class Drop(
    var id: Int, //掉落包ID
    var num: Int, //掉落数量
    var percent: Int //掉落几率
) : IPercentObj {
    override fun getRate(): Int {
        return percent
    }
}

class DropPropsProtoCache : ProtoCacheInit("dropProps.xml") {
    var dropPropsMap: Map<Int, DropPropsProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<DropPropsResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as DropPropsResult

        val tmpDropPropsMap: HashMap<Int, DropPropsProto> = hashMapOf()//
        for (vo in readXmlResult.l) {
            if (tmpDropPropsMap.containsKey(vo.id)) {
                throw RuntimeException("dropProps.xml :: id[${vo.id}]重复")
            }
            vo.dropMap = dropStringToDrops(vo.award) ?: throw RuntimeException("dropProps.xml :: 掉落包配置错误:${vo.id}")
            tmpDropPropsMap[vo.id] = vo
        }
        this.dropPropsMap = tmpDropPropsMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, vo) in this.dropPropsMap) {
            for (vv in vo.dropMap) {
                pcs.equipCache.equipProtoMap[vv.id]
                    ?: throw RuntimeException("dropProps.xml 表中的道具在props里找不到:${vv.id}")
            }
        }
    }

}