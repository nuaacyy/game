package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class InstanceDropExtraResule(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InstanceDropExtraProto>
): Serializable

// 被检测道具ID  X次数 里只会产出X个
data class InstanceDropExtraProto(
    val id: Int, //       // 编号
    val dropsId: Int,  // 被检测道具ID
    val times: Int,   // 次数
    val number: Int    // 产出
): Serializable

class InstanceDropExtraProtoCache : ProtoCacheInit("instancedropExtra.xml") {
    var instanceDropExtraProtoMap: Map<Int, InstanceDropExtraProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InstanceDropExtraResule>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InstanceDropExtraResule

        val tmpInstanceDropExtraProtoMap: HashMap<Int, InstanceDropExtraProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpInstanceDropExtraProtoMap[vo.dropsId] = vo
        }
        this.instanceDropExtraProtoMap = tmpInstanceDropExtraProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((dropId, _) in this.instanceDropExtraProtoMap) {
            if (pcs.equipCache.equipProtoMap[dropId] == null) {
                throw RuntimeException("instancedropExtra.xml中的dropsId:${dropId}在道具表里找不到")
            }

        }
    }

}