package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class GetHitOrderResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<GetHitOrderProto>
): Serializable

data class GetHitOrderProto(
    val id: Int,   //唯一ID
    val solider: Int,   //受击方兵种
    val order: String  //攻击方顺序
) : Serializable{
    var atkOrder: List<Int> = listOf()

}

class GetHitOrderProtoCache : ProtoCacheInit("getHitOrder.xml") {
    var getHitOrderMap: HashMap<Int, GetHitOrderProto> = hashMapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<GetHitOrderResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as GetHitOrderResult

        val tmpGetHitOrderMap: HashMap<Int, GetHitOrderProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpGetHitOrderMap.containsKey(vo.solider)) {
                throw RuntimeException("getHitOrder.xml :: solider[${vo.solider}]重复")
            }

            tmpGetHitOrderMap[vo.solider] = vo
            val atkOrder = LinkedList<Int>()
            val orderStrs = stringsSplit(vo.order, ";")
            for (str in orderStrs) {
                val army = strconvAtoi(str) ?: throw RuntimeException("getHitOrder.xml 攻击顺序配置错误：${vo.order}")

                if (!com.point18.slg2d.common.constg.isSolider(army) && !com.point18.slg2d.common.constg.isTrap(army)) {
                    throw RuntimeException("getHitOrder.xml 攻击顺序配置错误：${vo.order}")
                }
                atkOrder.add(army)
            }
            vo.atkOrder = atkOrder
        }
        this.getHitOrderMap = tmpGetHitOrderMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}