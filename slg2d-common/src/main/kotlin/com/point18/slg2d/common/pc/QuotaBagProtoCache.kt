package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.google.common.base.Strings
import com.point18.slg2d.common.probability.KeyProbability
import java.io.Serializable
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.HashMap

data class QoutaBagResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<QuotaBagProto>
) : Serializable

data class QuotaBagProto(
    /* 礼包唯一ID */
    val id: Int,
    /* 编号 */
    val quotaId: Int,
    /* 价格 */
    val money: Int,
    /* 触发几率（万分比） */
    val touch: Int,
    /* 每天增加几率（万分比） */
    val giftAdd: Int,
    /* 倒计时（h） */
    val giftTime: Int,
    /* 领取需要获得钻石 */
    val giftNeed: Int,
    /* 正常几率（万分比） */
    val giftNormal: Int,
    /* 每天变化几率（万分比） */
    val giftChange: Int,
    /* 奖励内容 */
    val dropId: String,
    /* 邮件内容标题 */
    val mailTitle: String,
    /* 邮件内容ID */
    val mailContent: String
) : Serializable {
    var drops = KeyProbability<Int>()

    fun isTrigger(factor: Int): Boolean = ThreadLocalRandom.current().nextInt(10000) < touch + giftAdd * factor
}

class QuotaBagProtoCache : ProtoCacheInit("quotaBag.xml") {
    var quotaBagsMap: Map<Int, QuotaBagProto> = mapOf() // 按照id的缓存
    var quotaBagsByMoney: Map<Int, KeyProbability<QuotaBagProto>> = mapOf() // 按照价格的缓存

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<QoutaBagResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as QoutaBagResult

        val tmpQuotaBagsMap: HashMap<Int, QuotaBagProto> = hashMapOf()
        val tmpQuotaBagsByMoney: HashMap<Int, KeyProbability<QuotaBagProto>> = hashMapOf()

        for (vo in readXmlResult.l) {
            if (vo.money <= 0) {
                throw RuntimeException("quotaBag.xml :: id:${vo.id} money:${vo.money} 错误")
            }

            if (vo.touch < 0) {
                throw RuntimeException("quotaBag.xml :: id:${vo.id} touch:${vo.touch} 错误")
            }

            if (vo.giftNeed < 0) {
                throw RuntimeException("quotaBag.xml :: id:${vo.id} giftNeed:${vo.giftNeed} 错误")
            }

            if ("0" == vo.mailTitle || Strings.isNullOrEmpty(vo.mailTitle)) {
                throw RuntimeException("quotaBag.xml :: id:${vo.id} mailTitle:${vo.mailTitle} 错误")
            }

            if ("0" == vo.mailContent || Strings.isNullOrEmpty(vo.mailContent)) {
                throw RuntimeException("quotaBag.xml :: id:${vo.id} mailContent:${vo.mailContent} 错误")
            }

            // 解析奖励配置
            if ("0" == vo.dropId || Strings.isNullOrEmpty(vo.dropId)) {
                throw RuntimeException("quotaBag.xml :: id:${vo.id} dropId:${vo.dropId} 错误1")
            }
            val dropsString = stringsSplit(vo.dropId, "|")
            for (drop in dropsString) {
                val e = stringsSplit(drop, ";")
                if (e.size != 2) {
                    throw RuntimeException("quotaBag.xml :: id:${vo.id} dropId:$drop 错误2")
                }

                val conType =
                    strconvAtoi(e[0]) ?: throw RuntimeException("quotaBag.xml :: id:${vo.id} dropId:$e 错误3")
                val conValue =
                    strconvAtoi(e[1]) ?: throw RuntimeException("quotaBag.xml :: id:${vo.id} dropId:$e 错误4")

                vo.drops.add(conType, conValue)
            }

            tmpQuotaBagsMap[vo.id] = vo

            tmpQuotaBagsByMoney.getOrPut(vo.money) { KeyProbability() }.add(vo, vo.giftNormal, vo.giftChange)
        }

        this.quotaBagsMap = tmpQuotaBagsMap
        this.quotaBagsByMoney = tmpQuotaBagsByMoney
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, p) in this.quotaBagsMap) {
            p.drops.getKeys().forEach {
                pcs.dropBagCache.dropBagMap[it]
                        ?: throw RuntimeException("quotaBag.xml ::表配置的reward在dropBag里找不到:$it")
            }
        }
    }
}