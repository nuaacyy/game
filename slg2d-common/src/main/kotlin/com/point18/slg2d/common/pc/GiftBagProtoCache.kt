package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.google.common.base.Strings
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.mcache.TwoKeyIndex
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class GiftBagResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<GiftBagProto>
) : Serializable

data class GiftBagProto(
    /* 礼包唯一ID */
    val id: Int,
    /* 礼包ID */
    val giftId: Int,
    /* 触发条件(触发条件类礼包有值) */
    val giftTouch: String,
    /* 礼包类型 */
    val giftType: Int,
    /* 礼包开放时间 */
    val bigTime: String,
    /* 礼包持续时间 */
    val duration: Int,
    /* 礼包档位 */
    val giftLevel: Int,
    /* 礼包限制购买次数 为0则无限购买 */
    val giftLimitation: Int,
    /* 奖励 */
    val reward: Int,
    /* 购买获得联盟礼物 */
    val allianceProps: String,
    /* 礼包价格 单位分 */
    val money: Int,
    /* 礼包奖励特权 */
    val giftPrivilege: String,
    /* 邮件内容标题*/
    val mailTitle: String,
    /* 邮件内容ID*/
    val mailContent: String
) : Serializable {
    /* 触发条件 */
    var giftBagTouchConMap: Map<Int, Map<Int, Int>> = mapOf()
    /* 开放大时间 */
    var giftBagBigOpenList: List<Int> = listOf()
    /* 特效 同 buildingData.xml extraEffect */
    var upEff: Map<Int, Int> = mapOf()
    /* 联盟礼物 */
    var allianceDrops: HashMap<Int, Int> = hashMapOf()

    fun isOpen(): Boolean {
        return when (this.giftType) {
            OPEN_FOREVER -> {
                true
            }
            OPEN_YEAR -> {
                val month = giftBagBigOpenList[0]
                val dayMonth = giftBagBigOpenList[1]
                val endTime = thisYearDate(month, dayMonth).plusDays(this.duration.toLong()).atStartOfDay()
                endTime.isAfter(LocalDateTime.now())
            }
            OPEN_MONTH -> {
                val dayMonth = giftBagBigOpenList[0]
                val endTime = thisMonthDate(dayMonth).plusDays(this.duration.toLong()).atStartOfDay()
                endTime.isAfter(LocalDateTime.now())
            }
            OPEN_WEEK -> {
                val dayWeek = giftBagBigOpenList[0]
                val endTime = thisWeekDate(dayWeek).plusDays(this.duration.toLong()).atStartOfDay()
                endTime.isAfter(LocalDateTime.now())
            }
            OPEN_DAILY -> {
                true
            }
            MONTH_CARD -> {
                true
            }
            TRIGGER_GIFTBAG -> {
                true
            }
            else -> {
                false
            }
        }
    }

    fun isActive(endTime: Long): Boolean {
        return when (this.giftType) {
            OPEN_FOREVER -> {
                true
            }
            OPEN_YEAR -> {
                endTime > getNowTime()
            }
            OPEN_MONTH -> {
                endTime > getNowTime()
            }
            OPEN_WEEK -> {
                endTime > getNowTime()
            }
            OPEN_DAILY -> {
                endTime > getNowTime()
            }
            MONTH_CARD -> {
                true
            }
            TRIGGER_GIFTBAG -> {
                endTime > getNowTime()
            }
            else -> {
                false
            }
        }
    }

    fun getEndTime(): Long {
        return when (this.giftType) {
            OPEN_FOREVER -> {
                0
            }
            OPEN_YEAR -> {
                val month = giftBagBigOpenList[0]
                val dayMonth = giftBagBigOpenList[1]
                val openTime = thisYearDate(month, dayMonth).plusDays(this.duration.toLong()).atStartOfDay()
                localDateTimeToMilli(openTime)
            }
            OPEN_MONTH -> {
                val dayMonth = giftBagBigOpenList[0]
                val openTime = thisMonthDate(dayMonth).plusDays(this.duration.toLong()).atStartOfDay()
                localDateTimeToMilli(openTime)
            }
            OPEN_WEEK -> {
                val dayWeek = giftBagBigOpenList[0]
                val openTime = thisWeekDate(dayWeek).plusDays(this.duration.toLong()).atStartOfDay()
                localDateTimeToMilli(openTime)
            }
            OPEN_DAILY -> {
                localDateTimeToMilli(LocalDate.now().plusDays(1).atStartOfDay())
            }
            MONTH_CARD -> {
                0
            }
            TRIGGER_GIFTBAG -> {
                0
            }
            else -> {
                0
            }
        }
    }
}

class GiftBagProtoCache : ProtoCacheInit("giftBag.xml") {
    var giftBagMap: Map<Int, GiftBagProto> = mapOf()
    var giftBagMapByGiftBagIdLevel = TwoKeyIndex<Int, Int, GiftBagProto>({ it.giftId }, { it.giftLevel })
    var giftBagMapByGiftBagId: Map<Int, List<GiftBagProto>> = mapOf()
    var triggerGiftBagMap: Map<Int, List<GiftBagProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<GiftBagResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as GiftBagResult

        val tmpGiftBagMap: HashMap<Int, GiftBagProto> = hashMapOf()
        val tmpGiftBagMapByGiftBagIdLevel = TwoKeyIndex<Int, Int, GiftBagProto>({ it.giftId }, { it.giftLevel })
        val tmpGiftBagMapByGiftBagId: HashMap<Int, LinkedList<GiftBagProto>> = hashMapOf()
        val tmpTriggerGiftBagMap: HashMap<Int, LinkedList<GiftBagProto>> = hashMapOf()

        for (vo in readXmlResult.l) {
            if (tmpGiftBagMap.containsKey(vo.id)) {
                throw RuntimeException("giftBag.xml :: id[${vo.id}]重复")
            }

            if (vo.giftLevel <= 0) {
                throw RuntimeException("giftBag.xml :: id:${vo.id} giftLevel:${vo.giftLevel} 错误")
            }

            if (vo.giftLimitation < 0) {
                throw RuntimeException("giftBag.xml :: id:${vo.id} giftLimitation:${vo.giftLimitation} 错误")
            }

            if ("0" == vo.mailTitle || Strings.isNullOrEmpty(vo.mailTitle)) {
                throw RuntimeException("giftBag.xml :: id:${vo.id} mailTitle:${vo.mailTitle} 错误")
            }

            if ("0" == vo.mailContent || Strings.isNullOrEmpty(vo.mailContent)) {
                throw RuntimeException("giftBag.xml :: id:${vo.id} mailContent:${vo.mailContent} 错误")
            }

            /* 解析触发条件giftTouch */
            val giftBagTouchConMap = HashMap<Int, HashMap<Int, Int>>()
            if ("0" != vo.giftTouch) {
                val giftBagTouchCons = stringsSplit(vo.giftTouch, "|")
                for (c in giftBagTouchCons) {
                    val e = stringsSplit(c, ";")

                    if (e.size != 3) {
                        throw RuntimeException("giftBag.xml :: id:${vo.id} giftTouch:$c 错误1")
                    }

                    val conType =
                        strconvAtoi(e[0]) ?: throw RuntimeException("giftBag.xml :: id:${vo.id} giftTouch:$c 错误2")
                    val conValue1 =
                        strconvAtoi(e[1]) ?: throw RuntimeException("giftBag.xml :: id:${vo.id} giftTouch:$c 错误3")
                    val conValue2 =
                        strconvAtoi(e[2]) ?: throw RuntimeException("giftBag.xml :: id:${vo.id} giftTouch:$c 错误4")

                    val cMap = giftBagTouchConMap.getOrPut(conType) { hashMapOf() }
                    cMap[conValue1] = conValue2
                }
            }
            vo.giftBagTouchConMap = giftBagTouchConMap

            /* 解析开放时间bigTime */
            val giftBagBigOpenList = arrayListOf<Int>()
            when (vo.giftType) {
                OPEN_FOREVER -> {
                }
                OPEN_YEAR -> {
                    /* 格式：月:日 */
                    val giftBagOpenBigs = stringsSplit(vo.bigTime, ";")
                    if (giftBagOpenBigs.size != 2) {
                        throw RuntimeException("giftBag.xml :: id:${vo.id} bigTime:${vo.bigTime} 错误2")
                    }

                    val month = strconvAtoi(giftBagOpenBigs[0])
                            ?: throw RuntimeException("giftBag.xml :: id:${vo.id} bigTime:${vo.bigTime} 错误4")
                    val day = strconvAtoi(giftBagOpenBigs[1])
                            ?: throw RuntimeException("giftBag.xml :: id:${vo.id} bigTime:${vo.bigTime} 错误5")
                    // 活动结束时间不能超过明年的今天
                    val endDate = LocalDate.now().withDayOfMonth(day).withMonth(month).plusDays(vo.duration.toLong())
                    val checkDate = LocalDate.now().withDayOfMonth(day).withMonth(month).plusYears(1)
                    if (endDate.isAfter(checkDate)) {
                        throw RuntimeException("giftBag.xml :: id:${vo.id} bigTime:${vo.bigTime} 错误6")
                    }
                    giftBagBigOpenList.add(month)
                    giftBagBigOpenList.add(day)
                }
                OPEN_MONTH -> {
                    /* 格式：日 */
                    val dayMonth = strconvAtoi(vo.bigTime)
                            ?: throw RuntimeException("giftBag.xml :: id:${vo.id} bigTime:${vo.bigTime} 错误7")
                    // 活动结束时间不能超过下月的今天
                    val endDate = LocalDate.now().withDayOfMonth(dayMonth).plusDays(vo.duration.toLong())
                    val checkDate = LocalDate.now().withDayOfMonth(dayMonth).plusMonths(1)
                    if (endDate.isAfter(checkDate)) {
                        throw RuntimeException("giftBag.xml :: id:${vo.id} bigTime:${vo.bigTime} 错误8")
                    }
                    giftBagBigOpenList.add(dayMonth)
                }
                OPEN_WEEK -> {
                    /* 格式：日周 */
                    val dayWeek = strconvAtoi(vo.bigTime)
                            ?: throw RuntimeException("giftBag.xml :: id:${vo.id} bigTime:${vo.bigTime} 错误9")
                    // 活动结束时间不能超过下周的今天
                    if (vo.duration > 7) {
                        throw RuntimeException("giftBag.xml :: id:${vo.id} bigTime:${vo.bigTime} 错误10")
                    }
                    giftBagBigOpenList.add(dayWeek)
                }
                OPEN_DAILY -> {
                }
                MONTH_CARD -> {
                }
                TRIGGER_GIFTBAG -> {
                }
                else -> throw RuntimeException("giftBag.xml :: giftTime:${vo.giftType} 错误")
            }
            vo.giftBagBigOpenList = giftBagBigOpenList

            // 额外效果
            val upEff = hashMapOf<Int, Int>()
            if ("0" != vo.giftPrivilege) {
                val extraEffectStr = stringsSplit(vo.giftPrivilege, "|")

                for (element in extraEffectStr) {
                    val b = stringsSplit(element, ";")

                    if (b.size != 2) {
                        throw RuntimeException("giftBag.xml :: giftPrivilege:${vo.giftPrivilege} 错误1")
                    }

                    val effType =
                        strconvAtoi(b[0]) ?: throw RuntimeException("giftBag.xml :: giftPrivilege:${vo.giftPrivilege} 错误2")

                    val effValue =
                        strconvAtoi(b[1]) ?: throw RuntimeException("giftBag.xml :: giftPrivilege:${vo.giftPrivilege} 错误3")

                    upEff[effType] = effValue
                }
            }

            vo.upEff = upEff

            vo.allianceDrops = parseIntMap(vo.allianceProps) ?: throw RuntimeException("giftBag.xml :: 联盟礼物配置错误:${vo.id}")

            tmpGiftBagMapByGiftBagIdLevel.insertByKey(vo)
            tmpGiftBagMap[vo.id] = vo
            tmpGiftBagMapByGiftBagId.getOrPut(vo.giftId) { LinkedList() }.add(vo)
            if (vo.giftType == TRIGGER_GIFTBAG) {
                tmpTriggerGiftBagMap.getOrPut(vo.giftId) { LinkedList() }.add(vo)
            }
        }

        this.giftBagMap = tmpGiftBagMap
        this.giftBagMapByGiftBagIdLevel = tmpGiftBagMapByGiftBagIdLevel
        this.giftBagMapByGiftBagId = tmpGiftBagMapByGiftBagId
        this.triggerGiftBagMap = tmpTriggerGiftBagMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, p) in this.giftBagMap) {
            pcs.dropBagCache.dropBagMap[p.reward]
                    ?: throw RuntimeException("giftBag.xml ::表配置的reward在dropBag里找不到:${p.reward}")

            p.allianceDrops.forEach {
                pcs.allianceGiftProtoCache.allianceGiftProtoMap[it.key]
                    ?: throw RuntimeException("giftBag.xml 中的allianceProps字段在allianceGift表里找不到:${it.key}")
            }
        }
    }
}