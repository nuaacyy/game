package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class BankProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<BankProto>
): Serializable

data class BankProto(
    val id: Int, // 编号
    val time: Int, // 需要天数
    val minMoney: Int, // 最小金额
    val basicRate: Int // 利率
): Serializable

class BankProtoCache : ProtoCacheInit("bank.xml") {
    var bankProtoMap: Map<Int, BankProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<BankProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as BankProtoResult

        val tmpBankProtoMap: HashMap<Int, BankProto> = hashMapOf()//
        for (vo in readXmlResult.l) {
            if (tmpBankProtoMap.containsKey(vo.id)) {
                throw RuntimeException("bank.xml :: id[${vo.id}]重复")
            }
            tmpBankProtoMap[vo.id] = vo
        }
        this.bankProtoMap = tmpBankProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }
}