package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class CompoundProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<CompoundProto>
): Serializable

data class CompoundProto(
    val id: Int,
    //要合成的道具ID
    val propsId: Int,
    //合成一次消耗的铜币，数字
    val cost: Int,
    //部件配置
    val itemId: String,
    // 是否可分解  0-不可以  1-可以
    val isChange: Int
): Serializable {
    var itemIdMap: Map<Int, Int> = mapOf()

}

class CompoundProtoCache : ProtoCacheInit("compound.xml") {
    var compoundProtoMap: HashMap<Int, CompoundProto> = hashMapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<CompoundProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as CompoundProtoResult

        val tmpCompoundProtoMap: HashMap<Int, CompoundProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpCompoundProtoMap.containsKey(vo.id)) {
                throw RuntimeException("compound.xml :: id[${vo.id}]重复")
            }
            val itemIdMap = hashMapOf<Int, Int>()
            if (vo.itemId != "0") {
                val costs = stringsSplit(vo.itemId, "|")
                for (cost in costs) {
                    val c = stringsSplit(cost, ";")
                    if (c.size != 2) {
                        throw RuntimeException("Compound中的ItemId字段解析出错:${vo.itemId}")
                    }
                    val itemId = strconvAtoi(c[0])
                        ?: throw RuntimeException("解析compound.xml Cost:: ${vo.itemId} -> int 错误1:${vo.id}")

                    val num = strconvAtoi(c[1])
                        ?: throw RuntimeException("解析compound.xml Cost:: ${vo.itemId} -> int 错误2:${vo.id}")

                    itemIdMap[itemId] = num
                }
            }
            vo.itemIdMap = itemIdMap
            tmpCompoundProtoMap[vo.id] = vo
        }
        this.compoundProtoMap = tmpCompoundProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}