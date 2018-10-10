package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class DiamondConsumeResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<DiamondConsumeProto>
): Serializable

data class DiamondConsumeProto(
    //次数
    val times: Int,
    val decree: Int,
    val taxes: Int, //税收按次数的每次消耗
    val shopRefresh: Int, //每次刷新商城消耗的元宝数量
    val allianceShopRefresh: Int, //每次刷新帮派商城消耗的元宝数量
    val towerTimes: Int, //购买爬塔券价格
    val arenaTimes: Int, //购买竞技场次数价格
    val arenaShopTimes: Int  //购买竞技场商店刷新次数价格
): Serializable

class DiamondConsumeProtoCache : ProtoCacheInit("diamondConsume.xml") {
    var diamondConsumeMap: Map<Int, DiamondConsumeProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<DiamondConsumeResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as DiamondConsumeResult

        val tmpDiamondConsumeMap: HashMap<Int, DiamondConsumeProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpDiamondConsumeMap.containsKey(vo.times)) {
                throw RuntimeException("diamondConsume.xml :: times[${vo.times}]重复")
            }
            tmpDiamondConsumeMap[vo.times] = vo
        }
        this.diamondConsumeMap = tmpDiamondConsumeMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

    fun getTaxCostByTime(times: Int): Int {
        var tmptimes = times
        if (tmptimes > this.diamondConsumeMap.size) {
            tmptimes = this.diamondConsumeMap.size
        }
        val diamondConsumeMaptimes = this.diamondConsumeMap[tmptimes]
        return if (diamondConsumeMaptimes != null) {
            diamondConsumeMaptimes.taxes
        } else {
            throw RuntimeException("diamondConsumeMaptimes.taxes 返回失败")
        }
    }

    fun getRefreshShopCostByTimes(times: Int): Int {
        var tmptimes = times
        if (tmptimes > this.diamondConsumeMap.size) {
            tmptimes = this.diamondConsumeMap.size
        }
        val diamondConsumeMaptimes = this.diamondConsumeMap[tmptimes]
        return if (diamondConsumeMaptimes != null) {
            diamondConsumeMaptimes.shopRefresh
        } else {
            throw RuntimeException("diamondConsumeMaptimes.shopRefresh返回失败")
        }
    }

    fun getRefreshAllianceShopCostByTimes(times: Int): Int {
        var tmptimes = times
        if (tmptimes > this.diamondConsumeMap.size) {
            tmptimes = this.diamondConsumeMap.size
        }
        val diamondConsumeMaptimes = this.diamondConsumeMap[tmptimes]
        return if (diamondConsumeMaptimes != null) {
            diamondConsumeMaptimes.allianceShopRefresh
        } else {
            throw RuntimeException("diamondConsumeMaptimes.allianceShopRefresh返回失败")
        }
    }

    fun getBuyDecreeByTimes(times: Int): Int {
        var tmptimes = times
        if (tmptimes > this.diamondConsumeMap.size) {
            tmptimes = this.diamondConsumeMap.size
        }
        val diamondConsumeMaptimes = this.diamondConsumeMap[tmptimes]
        return if (diamondConsumeMaptimes != null) {
            diamondConsumeMaptimes.decree
        } else {
            throw RuntimeException("diamondConsumeMaptimes.decree返回失败")
        }
    }

    fun getBuyTowerNumByTimes(times: Int): Int {
        var tmptimes = times
        if (tmptimes > this.diamondConsumeMap.size) {
            tmptimes = this.diamondConsumeMap.size
        }
        val diamondConsumeMaptimes = this.diamondConsumeMap[tmptimes]
        return if (diamondConsumeMaptimes != null) {
            diamondConsumeMaptimes.towerTimes
        } else {
            throw RuntimeException("diamondConsumeMaptimes.towerTimes返回失败")
        }
    }

    fun getBuyJjcNumByTimes(times: Int): Int {
        var tmptimes = times
        if (tmptimes > this.diamondConsumeMap.size) {
            tmptimes = this.diamondConsumeMap.size
        }
        val diamondConsumeMaptimes = this.diamondConsumeMap[tmptimes]
        return if (diamondConsumeMaptimes != null) {
            diamondConsumeMaptimes.arenaTimes
        } else {
            throw RuntimeException("diamondConsumeMaptimes.ArenaTimes返回失败")
        }
    }

}