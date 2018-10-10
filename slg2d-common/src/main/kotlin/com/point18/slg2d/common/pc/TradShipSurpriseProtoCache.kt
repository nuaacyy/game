package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class TradShipSurpriseProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<TradShipSurpriseProto>
): Serializable

data class TradShipSurpriseProto(
    val id: Int, // 编号
    val props: String,  // 商品 奖励格式
    val starNum: Int,  // 星数	商品显示星数，最大3星
    val buyCost: Int  // 数字，即购买该礼包需要花费的RMB
): Serializable {
    var propsResVo: List<ResVo> = listOf() // // 商品	奖励格式

}

class TradShipSurpriseProtoCache : ProtoCacheInit("tradShipSurprise.xml") {
    var tradShipSurpriseProtoMap: Map<Int, TradShipSurpriseProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<TradShipSurpriseProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as TradShipSurpriseProtoResult

        val tmpTradShipSurpriseProtoMap: HashMap<Int, TradShipSurpriseProto> = hashMapOf()
        for (vo in readXmlResult.l) {

            val props = resStringToResVoList(vo.props)

            if (props != null) {
                vo.propsResVo = props
            } else {
                throw RuntimeException("tradShipSurprise中的Props字段解析出错:${vo.props}")
            }

            tmpTradShipSurpriseProtoMap[vo.id] = vo
        }
        this.tradShipSurpriseProtoMap = tmpTradShipSurpriseProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}