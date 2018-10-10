package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.commonfunc.getDaysOfTwo2ByMs
import com.point18.slg2d.common.commonfunc.getNowTime
import java.io.Serializable
import java.util.*

data class MapCallResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MapCallProto>
) : Serializable

data class MapCallProto(
    val id: Int, // 唯一id
    val startDay: Int, //开服天数
    val mapLocation: Int, // 资源矿撒点类型,对应mapLocation表的【type】字段
    val mapMonster: Int, // 魔物撒点类型,对应mapMonster表的【id】字段
    val mapRelic: Int // 遗迹撒点类型,对应mapRelic表的【id】字段
) : Serializable

class MapCallProtoCache : ProtoCacheInit("mapCall.xml") {
    var mapCellProtoMap: Map<Int, MapCallProto> = mapOf()
    var listSortByStartDay: List<MapCallProto> = listOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MapCallResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MapCallResult

        var isOk = false

        val tmpMapCellProtoMap: HashMap<Int, MapCallProto> = hashMapOf()
        val tmpListSortByActivePlayerNum: LinkedList<MapCallProto> = LinkedList()
        for (vo in readXmlResult.l) {
            val existVo = tmpMapCellProtoMap[vo.id]
            if (existVo != null) {
                throw RuntimeException("monstarRefresh模板Id存在重复:${vo.id}.")
            }

            tmpMapCellProtoMap[vo.id] = vo
            tmpListSortByActivePlayerNum += vo
            if (vo.startDay == 0) {
                isOk = true
            }
        }
        mapCellProtoMap = tmpMapCellProtoMap

        // 倒序排序，最大的在最前面。
        tmpListSortByActivePlayerNum.sortByDescending { it.startDay }
        listSortByStartDay = tmpListSortByActivePlayerNum

        if (!isOk) {
            throw RuntimeException("mapCall模板startDay未配置0.")
        }

        this.mapCellProtoMap[1] ?: throw RuntimeException("mapcall表找不到ID为1的.")
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

    //根据开服时间查找配置
    fun findMapCallByOpenServerTime(openServerTime: Long): MapCallProto? {
        val nowTime = getNowTime()
        val openServerDay = getDaysOfTwo2ByMs(nowTime, openServerTime * 1000)
        var okP: MapCallProto? = null
        for (p in listSortByStartDay) {
            if (openServerDay < p.startDay) {
                continue
            }

            okP = p
            break
        }
        return okP
    }

}
