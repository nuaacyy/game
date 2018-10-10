package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.NEW_PLAYER_ACTIVITY
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.math.absoluteValue

data class EventTimeResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<EventTimeProto>
) : Serializable

data class EventTimeProto(
    val id: Int,
    val eventType: Int, // 活动类型  1-个人 2-地狱 3-联盟 4-奇观 6-新手
    val eventTimeType: Int, // 1为1小时活动，2为3小时活动，3为8小时活动，4为24小时活动，5为时间不互斥
    val eventTime: Int,// 活动时长
    val eventList: String // 每周的第几个小时开启活动  小时数|小时数|小时数|小时数|小时数...
) : Serializable {
    var eventMap: Map<Int, Int> = mapOf() // <一周的第几个小时, 标记位>

}

class EventTimeProtoCache : ProtoCacheInit("eventTime.xml") {
    var protoMap: Map<Int, EventTimeProto> = mapOf()
    var protoMapByType: Map<Int, List<EventTimeProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<EventTimeResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as EventTimeResult

        val tmpProtoMap: HashMap<Int, EventTimeProto> = hashMapOf()
        val tmpProtoMapByType: HashMap<Int, LinkedList<EventTimeProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpProtoMap.containsKey(vo.id)) {
                throw RuntimeException("eventTime.xml :: id[${vo.id}]重复")
            }

            if (vo.eventTime == 0 && vo.eventType != NEW_PLAYER_ACTIVITY) {
                throw RuntimeException("eventTime.xml 的活动持续时间为0但是活动类型不是新手挑战,行ID为:${vo.id}")
            }

            if (vo.eventTime != 0 && vo.eventType == NEW_PLAYER_ACTIVITY) {
                throw RuntimeException("eventTime.xml 的活动类型是新手挑战,但是持续时间不为0,行ID为:${vo.id}")
            }

            if (vo.eventTime % 3600 != 0 && vo.eventTime != 0) {
                throw RuntimeException("eventTime.xml 的活动持续时间不为小时整${vo.eventTime}")
            }
            val eventMap = hashMapOf<Int, Int>()
            val eventLists = stringsSplit(vo.eventList, "|")

            for (e in eventLists) {
                val elId = strconvAtoi(e)
                if (elId != null) {
                    eventMap[elId] = 1
                }
            }
            vo.eventMap = eventMap

            tmpProtoMap[vo.id] = vo
            val protoListGroupByType = tmpProtoMapByType.getOrPut(vo.eventTimeType)
            {
                LinkedList()
            }
            protoListGroupByType.add(vo)
        }
        this.protoMap = tmpProtoMap
        this.protoMapByType = tmpProtoMapByType
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((t, activitys) in this.protoMapByType) {
            val useHour = hashMapOf<Int, Int>()
            for (activity in activitys) {
                if (activity.eventTimeType != 5) {
                    for ((h, _) in activity.eventMap) {
                        val useHourH = useHour[h]

                        if (useHourH != null) {
                            throw RuntimeException("eventTime.xml 同一个类型的活动配置时间冲突1,活动类型:$activity,冲突小时:$useHourH")
                        }
                        useHour[h] = 1
                    }
                }
            }

            for (activity in activitys) {
                for ((h, _) in activity.eventMap) {
                    for ((hh, _) in activity.eventMap) {
                        if (h == hh) {
                            continue
                        }
                        if (t == 1) {
                            if ((h - hh).toDouble().absoluteValue < 1) {
                                throw RuntimeException("eventTime.xml 同一个类型的活动配置时间冲突2,活动类型:$t,冲突小时是$h 与$hh")
                            }
                        } else if (t == 2) {
                            if ((h - hh).toDouble().absoluteValue < 3) {
                                throw RuntimeException("eventTime.xml 同一个类型的活动配置时间冲突2,活动类型:$t,冲突小时是$h 与$hh")
                            }
                        } else if (t == 3) {
                            if ((h - hh).toDouble().absoluteValue < 8) {
                                throw RuntimeException("eventTime.xml 同一个类型的活动配置时间冲突2,活动类型:$t,冲突小时是$h 与$hh")
                            }
                        } else if (t == 4) {
                            if ((h - hh).toDouble().absoluteValue < 24) {
                                throw RuntimeException("eventTime.xml 同一个类型的活动配置时间冲突2,活动类型:$t,冲突小时是$h 与$hh")
                            }
                        }
                    }
                }
            }
        }
    }

//    fun findNowOpenActivitys(): LinkedList<EventTimeProto> {
//        val openActivitys = LinkedList<EventTimeProto>()
//        val nowHour: Int
//        val now = LocalDateTime.now()
//        val week = now.dayOfWeek.toString()
//        var weekday = 0
//        when (week) {
//            "Sunday" -> weekday = 6
//            "Monday" -> weekday = 0
//            "Tuesday" -> weekday = 1
//            "Wednesday" -> weekday = 2
//            "Thursday" -> weekday = 3
//            "Friday" -> weekday = 4
//            "Saturday" -> weekday = 5
//        }
//        nowHour = 24 * weekday + now.hour
//        for ((_, a) in this.protoMap) {
//            val eventMapnowHour = a.eventMap[nowHour]
//
//            if (eventMapnowHour != null) {
//                openActivitys.add(a)
//            }
//        }
//        return openActivitys
//    }

    // 找到此时应该开放着的活动,用来把停服期间没开出来的活动开起来<活动类,正常结束时间>
    fun findNowOpenActivities(): MutableMap<EventTimeProto, Long> {
        val openActivities = mutableMapOf<EventTimeProto, Long>()
        val nowHour: Int
        val now = LocalDateTime.now()
        val week = now.dayOfWeek.toString()
        var weekday = 0
        when (week) {
            "SUNDAY" -> weekday = 6
            "MONDAY" -> weekday = 0
            "TUESDAY" -> weekday = 1
            "WEDNESDAY" -> weekday = 2
            "THURSDAY" -> weekday = 3
            "FRIDAY" -> weekday = 4
            "SATURDAY" -> weekday = 5
        }

        val nowSec = getNowTime()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val tt =
            "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)} ${calendar.get(
                Calendar.HOUR_OF_DAY
            )}:${0}:${0}"
        val myDate = dateFormat.parse(tt)
        val nowHourSec = myDate.time

        nowHour = 24 * weekday + now.hour + 1
        for ((_, a) in this.protoMap) {
            if (a.eventTime == 0) {
                openActivities[a] = 0
                continue
            }
            for ((openTime, _) in a.eventMap) {
                if (openTime > nowHour) {
                    // 开启小时大于了当前小时 跳过
                    continue
                } else {
                    // 19:15
                    //                                    (116    -    115)*3600*1000                + 4*3600*1000
                    val overTime =
                        (nowHourSec - (nowHour - openTime).toLong() * 3600 * 1000) + (a.eventTime.toLong() * 1000)
                    if (overTime <= nowSec) {
                        // 已经结束的活动 跳过
                        continue
                    }

                    openActivities[a] = overTime
                }
            }
        }

        return openActivities
    }

}