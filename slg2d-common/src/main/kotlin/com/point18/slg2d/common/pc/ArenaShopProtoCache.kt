package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.constg.NOT_PROPS_SUB_TYPE
import com.point18.slg2d.common.constg.RES_JJC_COIN
import com.point18.slg2d.common.constg.RES_PROPS
import java.io.Serializable
import java.util.*
import java.util.Arrays.asList

data class ArenaShopItemRes(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<ArenaShopProto>
) : Serializable

class ArenaShopProto(
    val id: Int,
    val lordLv: Int,
    val grid: Int,
    val item: String,
    val price: String,
    val rate: Int    // 概率,万分比
) : Serializable {
    var itemVo: ResVo = ResVo(RES_PROPS, 1, 0)
    var priceVo: ResVo = ResVo(RES_JJC_COIN, NOT_PROPS_SUB_TYPE, 0)
}

class ArenaShopProtoCache : ProtoCacheInit("arenaShop.xml") {
    var mapOfItems = mapOf<Int, ArenaShopProto>()  // key是id
    var itemsList = listOf<ArenaShopProto>()
    var lvGridItemMap = mapOf<Int, Map<Int, LinkedList<ArenaShopProto>>>() // 第一个key 领主lv 第二个key 格子位置

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ArenaShopItemRes>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ArenaShopItemRes

        val tmpMapOfItems = hashMapOf<Int, ArenaShopProto>()  // key是id
        val tmpItemsList = LinkedList<ArenaShopProto>()
        val tmpLvGridItemMap = hashMapOf<Int, HashMap<Int, LinkedList<ArenaShopProto>>>() // 第一个key 领主lv 第二个key 格子位置

        for (vo in readXmlResult.l) {
            if (tmpMapOfItems.containsKey(vo.id)) {
                throw RuntimeException("arenaShop.xml :: id[${vo.id}]重复")
            }

            val itemVo = resStringToResVoList(vo.item)
            if (itemVo == null || itemVo.size != 1) {
                throw RuntimeException("arenaShop.xml  item 配置错误${vo.id} ")
            }
            vo.itemVo = itemVo[0]

            val priceVo = resStringToResVoList(vo.price)
            if (priceVo == null || itemVo.size != 1) {
                throw RuntimeException("arenaShop.xml  item 配置错误${vo.id} ")
            }
            vo.priceVo = priceVo[0]

            tmpMapOfItems[vo.id] = vo
            tmpItemsList.add(vo)

            val tmpGridItemMap = tmpLvGridItemMap[vo.lordLv]
            if (tmpGridItemMap == null) {
                val tmp = hashMapOf<Int, LinkedList<ArenaShopProto>>()
                tmp[vo.grid] = LinkedList(asList(vo))
                tmpLvGridItemMap[vo.lordLv] = tmp
            } else {
                val gridContent = tmpGridItemMap[vo.grid]
                if (gridContent == null) {
                    val tmpItemList = LinkedList(asList(vo))
                    tmpGridItemMap[vo.grid] = tmpItemList
                } else {
                    gridContent.add(vo)
                }
            }
        }
        mapOfItems = tmpMapOfItems
        itemsList = tmpItemsList
        lvGridItemMap = tmpLvGridItemMap

    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((lv, gridItemMap) in lvGridItemMap) {
            for ((grid, Items) in gridItemMap) {
                var rateSum = 0
                for (eachItem in Items) {
                    rateSum += eachItem.rate
                }
                if (rateSum != 10000) {
                    throw RuntimeException("arenaShop.xml lv = $lv,grid=${grid}配置的几率(万分比)之和不等于10000")
                }
            }
        }
    }

    fun drawOneItem(list: LinkedList<ArenaShopProto>): ArenaShopProto? {
        if (list.size == 0) {
            return null
        }

        if (list.size == 1) {
            return list[0]
        }

        val ranInt = getRandInt(10000)
        var sum = 0
        for (each in list) {
            sum += each.rate
            if (ranInt <= sum) {
                return each
            }
        }

        return null

    }
}