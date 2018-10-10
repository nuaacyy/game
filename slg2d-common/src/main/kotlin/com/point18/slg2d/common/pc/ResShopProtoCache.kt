package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class ResShopResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<ResShopProto>
): Serializable

data class ResShopProto(
    val id: Int, //唯一ID
    val goodsId: Int, //商品ID
    val bigType: Int, //商品大类型
    val resType: Int,  //资源类型
    val num: Int, //数量
    val price: Int //需要钻石
): Serializable

class ResShopProtoCache : ProtoCacheInit("resShop.xml") {
    var resShopProtoMap: Map<Int, ResShopProto> = mapOf()
    var resShopProtoMapByResType: Map<Int, Map<Int, List<ResShopProto>>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ResShopResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ResShopResult

        val tmpResShopProtoMap: HashMap<Int, ResShopProto> = hashMapOf()
        val tmpResShopProtoMapByResType: HashMap<Int, HashMap<Int, LinkedList<ResShopProto>>> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpResShopProtoMap[vo.goodsId] = vo

            val info = tmpResShopProtoMapByResType.getOrPut(vo.bigType) {
                hashMapOf()
            }
            var info2 = info[vo.resType]
            if (null == info2) {
                info2 = LinkedList()
                info[vo.resType] = info2
            }
            info2.add(vo)
            info[vo.resType] = info2
        }
        this.resShopProtoMap = tmpResShopProtoMap
        this.resShopProtoMapByResType = tmpResShopProtoMapByResType
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, vo) in this.resShopProtoMap) {
            pcs.equipCache.equipProtoMap[vo.goodsId]
                ?: throw RuntimeException("resShop.xml中的GoodsId字段没有在props中找到,字段ID是${vo.goodsId}.")

        }
    }

    fun needCost(bigType: Int, buyType: Int, buyNum: Int): Double {
        return needCost(bigType, buyType, buyNum.toLong())
    }

    fun needCost(bigType: Int, buyType: Int, buyNum: Long): Double {
        var need = 0.0
        val resShopProtoMapByResTypebigType = this.resShopProtoMapByResType[bigType]
            ?: throw RuntimeException(" function ::needCost resShopProtoMapByResTypebigType[$bigType] == null err .")
        //commonfunc.debugAssert(ex, "在<计算秒一个商品的价格,这个方法是给自动补足缺口用的>这个方法中发生了错误0:", buyType)
        val shops = resShopProtoMapByResTypebigType[buyType]
            ?: throw RuntimeException(" function ::needCost resShopProtoMapByResTypebigType[$buyType] == null err .")
        //commonfunc.debugAssert(ex, "在<计算秒一个商品的价格,这个方法是给自动补足缺口用的>这个方法中发生了错误0:", buyType)
        //commonfunc.debugAssert(ex, "在<计算秒一个商品的价格,这个方法是给自动补足缺口用的>这个方法中发生了错误0:", buyType)
        // 找到一个合适的商品先吃一口,然后剩余的在找吃的这口的下一档吃
        var firstShopId = 0 // 能吃的最大的
        var firstShopNum = 0

        var otherShiopId = 0 //剩余的吃的
        var otherShiopNum = 0

        // 这个循环做两个事情,找到一个值的两档商品

        for (shop in shops) {

            if (firstShopId == 0) {

                if (shop.num <= buyNum) {
                    firstShopNum = shop.num
                    firstShopId = shop.goodsId
                }
            } else {

                if (shop.num in (firstShopNum + 1)..buyNum) {
                    firstShopId = shop.goodsId
                    firstShopNum = shop.num
                }
            }

            // ===================================

            if (otherShiopId == 0) {

                if (shop.num >= buyNum) {
                    otherShiopId = shop.goodsId
                    otherShiopNum = shop.num
                }
            } else {

                if (shop.num in buyNum..(otherShiopNum - 1)) {
                    otherShiopId = shop.goodsId
                    otherShiopNum = shop.num
                }
            }
        }


        if (firstShopId == 0) {
            // 这个是0说明玩家要补齐的量比最小的商品还要小,所以直接套用otherShiopId的算就行了
            val proto1 = this.resShopProtoMap[otherShiopId]
                ?: throw RuntimeException(" function ::needCost this.resShopProtoMap[$otherShiopId] == null err1 .")
            //commonfunc.debugAssert(ex, "在<计算秒一个商品的价格,这个方法是给自动补足缺口用的>这个方法中发生了错误1:", otherShiopId)
            val onePrice = (proto1.price).toDouble() / (proto1.num).toDouble()
            need = onePrice * (buyNum).toDouble()
        } else {
            val proto1 = this.resShopProtoMap[firstShopId]
                ?: throw RuntimeException(" function ::needCost this.resShopProtoMap[$firstShopId] == null err2 .")
            //commonfunc.debugAssert(ex, "在<计算秒一个商品的价格,这个方法是给自动补足缺口用的>这个方法中发生了错误2:", firstShopId)
            need += (proto1.price).toDouble()

            val diffNeedNum = buyNum - proto1.num

            if (otherShiopId == 0) {
                val proto2 = this.resShopProtoMap[firstShopId]
                    ?: throw RuntimeException(" function ::needCost this.resShopProtoMap[$firstShopId] == null err1 .")
                //commonfunc.debugAssert(ex2, "在<计算秒一个商品的价格,这个方法是给自动补足缺口用的>这个方法中发生了错误3:", firstShopId)

                val onePrice = (proto2.price).toDouble() / (proto2.num.toDouble())
                need += (onePrice * (diffNeedNum.toDouble()))

            } else {
                val proto2 = this.resShopProtoMap[otherShiopId]
                    ?: throw RuntimeException(" function ::needCost this.resShopProtoMap[$otherShiopId] == null err2 .")
                //commonfunc.debugAssert(ex2, "在<计算秒一个商品的价格,这个方法是给自动补足缺口用的>这个方法中发生了错误4:", otherShiopId)

                val onePrice = (proto2.price.toDouble()) / (proto2.num.toDouble())
                need += (onePrice * (diffNeedNum.toDouble()))

            }
        }

        return need
    }

}