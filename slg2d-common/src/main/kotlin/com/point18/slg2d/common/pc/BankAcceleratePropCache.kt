package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class BankAccelerateProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<BankAccelerateProto>
): Serializable

data class BankAccelerateProto(
    val id: Int, // 编号
    val time: Int, // 需要时间
    val buildId: Long, // 建筑类型
    val buildLv: Int, // 建筑等级
    val accelerate: Int, // 存储加速时间
    val props: String // 收益道具
): Serializable {
    var propsMap: Map<Int, Int> = mapOf()
}

class BankAccelerateProtoCache : ProtoCacheInit("bankAccelerate.xml") {
    var bankAccelerateProtoMap: Map<Int, BankAccelerateProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<BankAccelerateProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as BankAccelerateProtoResult

        val tmpBankAccelerateProtoMap: HashMap<Int, BankAccelerateProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpBankAccelerateProtoMap.containsKey(vo.id)) {
                throw RuntimeException("bankAccelerate.xml :: id[${vo.id}]重复")
            }
            tmpBankAccelerateProtoMap[vo.id] = vo
            val propsListMap = hashMapOf<Int, Int>()
            if (vo.props != "") {
                val bb = stringsSplit(vo.props, ";")
                if (bb.size != 3) {
                    throw RuntimeException("props 分析出错")
                }
                val blackType = strconvAtoi(bb[1]) ?: throw RuntimeException("props 分析出错")
                val blackValue = strconvAtoi(bb[2]) ?: throw RuntimeException("props 分析出错")
                propsListMap[blackType] = blackValue
            }
            vo.propsMap = propsListMap
        }
        this.bankAccelerateProtoMap = tmpBankAccelerateProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, p) in this.bankAccelerateProtoMap) {
            for ((key, _) in p.propsMap) {
                val props = pcs.equipCache.equipProtoMap[key]
                    ?: throw RuntimeException("bankAccelerate.xml ::表配置的props的道具id在props.xml中找不到")
                if (props.mainType != com.point18.slg2d.common.constg.PROP_QUICK) {
                    throw RuntimeException("bankAccelerate.xml ::表配置的props不是加速道具 :${props.id}")
                }
            }
        }
    }
}