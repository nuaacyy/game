package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class TradShipProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<TradShipProto>
): Serializable

data class TradShipProto(
    val id: Int,  // 编号
    val group: Int,  // 道具组
    val grid: Int, // 道具格位	1-4分别对应4个格子
    val props: String, // 商品 奖励格式
    val isRes: Int,  // 是否资源道具	1=是(显示总资源数)，0=不是(显示道具名字)
    val starNum: Int,  // 星数	商品显示星数，最大3星
    val exchange: String,  // 兑换需求	通用奖励格式
    val surpriseRate: Int  // 出惊喜几率	万分比，兑换完对应商品出惊喜的几率
) : Serializable{
    var propsResVo: List<ResVo> = listOf() // 商品	奖励格式
    var exchangeResVo: List<ResVo> = listOf()

}

class TradShipProtoCache : ProtoCacheInit("tradShip.xml") {
    var tradShipProtoMap: Map<Int, TradShipProto> = mapOf()
    var tradShipProtoMapByGroup: Map<Int, List<TradShipProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<TradShipProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as TradShipProtoResult

        val tmpTradShipProtoMap: HashMap<Int, TradShipProto> = hashMapOf()
        val tmpTradShipProtoMapByGroup: HashMap<Int, LinkedList<TradShipProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val exchangeCost = resStringToResVoList(vo.exchange)

            if (exchangeCost != null) {
                vo.exchangeResVo = exchangeCost
            } else {
                throw RuntimeException("tradShip中的Exchange字段解析出错:${vo.exchange}")
            }

            val props = resStringToResVoList(vo.props)

            if (props != null) {
                vo.propsResVo = props
            } else {
                throw RuntimeException("tradShip中的Props字段解析出错:${vo.props}")
            }

            tmpTradShipProtoMap[vo.id] = vo

            val tradShipProtoMapByGroupgroup = tmpTradShipProtoMapByGroup.getOrPut(vo.group) {
                LinkedList()
            }

            tradShipProtoMapByGroupgroup.add(vo)
        }
        this.tradShipProtoMap = tmpTradShipProtoMap
        this.tradShipProtoMapByGroup = tmpTradShipProtoMapByGroup
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}