package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class ShopTotalProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<ShopTotalProto>
): Serializable

data class ShopTotalProto(
    val id: Int,// 编号
    val type: Int, //商店类型 1=钻石商店；2=联盟商店
    val props: String,  // 商品		购买可获得什么的奖励格式
    val buyCost: String,  // 购买需要		购买需要消耗什么的奖励格式
    val limitNumb: Int, //限购数量
    val openTime: String, //开始时间
    val endTime: String //结束时间
) : Serializable{
    var propsResVo: List<ResVo> = listOf() // // 商品	奖励格式
    var buyCostResVo: List<ResVo> = listOf()// 商品	奖励格式

}

class ShopTotalProtoCache : ProtoCacheInit("shopTotal.xml") {
    var shopTotalProtoMap: Map<Int, ShopTotalProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ShopTotalProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ShopTotalProtoResult

        val tmpShopTotalProtoMap: HashMap<Int, ShopTotalProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val buyCost = resStringToResVoList(vo.buyCost)

            if (buyCost != null) {
                vo.buyCostResVo = buyCost
            } else {
                throw RuntimeException("shopTotal中的BuyCost字段解析出错:${vo.buyCostResVo}")
            }

            val props = resStringToResVoList(vo.props)

            if (props != null) {
                vo.propsResVo = props
            } else {
                throw RuntimeException("shopTotal中的Props字段解析出错:${vo.propsResVo}")
            }

            tmpShopTotalProtoMap[vo.id] = vo
        }
        this.shopTotalProtoMap = tmpShopTotalProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}