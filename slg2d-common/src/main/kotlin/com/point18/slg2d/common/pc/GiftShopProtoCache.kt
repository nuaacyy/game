package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class GiftShopProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<GiftShopProto>
): Serializable

data class GiftShopProto(
    val id: Int,   // 编号
    val type: Int, //商店类型 1=钻石商店；2=联盟商店
    val dropBagAward: Int,  // 获得的商品掉落包Id
    val buyCost: String  // 购买需要		购买需要消耗什么的奖励格式
) : Serializable{
    var buyCostResVo: List<ResVo> = listOf()                                  // 商品	奖励格式

}

class GiftShopProtoCache : ProtoCacheInit("giftShop.xml") {
    var giftShopProtoMap: Map<Int, GiftShopProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<GiftShopProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as GiftShopProtoResult

        val tmpGiftShopProtoMap: HashMap<Int, GiftShopProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpGiftShopProtoMap.containsKey(vo.id)) {
                throw RuntimeException("giftShop.xml :: id[${vo.id}]重复")
            }

            val buyCost = resStringToResVoList(vo.buyCost)
            if (buyCost != null) {
                vo.buyCostResVo = buyCost
            } else {
                throw RuntimeException("giftShop中的BuyCost字段解析出错:${vo.buyCost}")
            }
            tmpGiftShopProtoMap[vo.id] = vo
        }
        this.giftShopProtoMap = tmpGiftShopProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in this.giftShopProtoMap) {
            pcs.dropBagCache.dropBagMap[proto.dropBagAward]
                ?: throw RuntimeException("giftShop中的dropBagAward再掉落包中不存在:${proto.dropBagAward}")

            if (proto.buyCostResVo.isEmpty()) {
                throw RuntimeException("giftShop中的奖励未配置{${proto.id}}")
            }
        }
    }

}