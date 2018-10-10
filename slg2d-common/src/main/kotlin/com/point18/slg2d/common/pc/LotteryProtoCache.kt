package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

data class DrawHeroResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<DrawHeroProto>
): Serializable

class DrawHeroProto(
    val id: Int,    //唯一ID
    val name: String,
    val cardType: Int,
    val timeInfo: String,
    val oneCost: String,
    val moreCost: String,
    val moreCostDisCount: String,
    val mustGet: String,
    val basicDraw: String,
    val drawProtect1: Int,
    val themDraw1: String,
    val drawProtect2: Int,
    val themDraw2: String,
    val cardShow: String
) : Serializable{
    var actStartTime = 0L
    var actEndTime = 0L
    var oneCostResVo = listOf<ResVo>()
    var moreCostResVo = listOf<ResVo>()
    var moreCostDiscountResVo = listOf<ResVo>()
    var mustGetResVo = listOf<ResVo>()
    var basicDrawMap: Map<Int, Int> = mapOf()  // 各个掉落包对应几率 万分比
    var themDraw1Map: Map<Int, Int> = mapOf()  // 各个掉落包对应几率 万分比
    var themDraw2Map: Map<Int, Int> = mapOf()  // 各个掉落包对应几率 万分比

    fun selectbasicDraw(): Int? {
        var totalRate = 0
        for (dropInfo in this.basicDrawMap) {
            totalRate += dropInfo.value
        }
        //commonfunc.debugAssert(totalRate > 0, "掉落包几率配置错误", this.id)
        val dropRand = com.point18.slg2d.common.commonfunc.getRandInt(totalRate)
        var tempRate = 0
        for (dropInfo in this.basicDrawMap) {
            if (dropRand <= tempRate + dropInfo.value) {
                return dropInfo.key
            }
            tempRate += dropInfo.value
        }
        return null
    }

    fun selectThemDraw1(): Int? {
        var totalRate = 0
        for (dropInfo in this.themDraw1Map) {
            totalRate += dropInfo.value
        }
        //commonfunc.debugAssert(totalRate > 0, "掉落包几率配置错误", this.id)
        val dropRand = com.point18.slg2d.common.commonfunc.getRandInt(totalRate)
        var tempRate = 0
        for (dropInfo in this.themDraw1Map) {
            if (dropRand <= tempRate + dropInfo.value) {
                return dropInfo.key
            }
            tempRate += dropInfo.value
        }
        return null
    }

    fun selectThemDraw2(): Int? {
        var totalRate = 0
        for (dropInfo in this.themDraw2Map) {
            totalRate += dropInfo.value
        }
        //commonfunc.debugAssert(totalRate > 0, "掉落包几率配置错误", this.id)
        val dropRand = com.point18.slg2d.common.commonfunc.getRandInt(totalRate)
        var tempRate = 0
        for (dropInfo in this.themDraw2Map) {
            if (dropRand <= tempRate + dropInfo.value) {
                return dropInfo.key
            }
            tempRate += dropInfo.value
        }
        return null
    }
}

class DrawHeroProtoCache : ProtoCacheInit("drawhero.xml") {
    var dropBagMap: Map<Int, DrawHeroProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<DrawHeroResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as DrawHeroResult

        val tmpDropBagMap: HashMap<Int, DrawHeroProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            vo.oneCostResVo = resStringToResVoList(vo.oneCost) ?: throw RuntimeException("drawhero.xml中的oneCost字段解析出错")
            vo.moreCostResVo = resStringToResVoList(vo.moreCost) ?: throw RuntimeException("drawhero.xml中的moreCost字段解析出错")
            vo.mustGetResVo = resStringToResVoList(vo.mustGet) ?: throw RuntimeException("drawhero.xml中的mustGet字段解析出错")
            vo.moreCostDiscountResVo = resStringToResVoList(vo.moreCostDisCount) ?:
                throw RuntimeException("drawhero.xml中的moreCostDisCount字段解析出错")

            if (vo.timeInfo != "0") {
                val timeStartEnd = vo.timeInfo.split("|")
                if (timeStartEnd.size != 2) {
                    throw RuntimeException("drawhero.xml中的timeInfo字段解析出错1")
                }

                val start = timeStartEnd[0].split(";")
                if (start.size != 6) {
                    throw RuntimeException("drawhero.xml中的timeInfo字段解析出错2")
                }

                val end = timeStartEnd[1].split(";")
                if (end.size != 6) {
                    throw RuntimeException("drawhero.xml中的timeInfo字段解析出错3")
                }

                var startY = start[0].toIntOrNull()
                var startM = start[1].toIntOrNull()
                var startD = start[2].toIntOrNull()
                val startH = start[3].toIntOrNull()
                val startMin = start[4].toIntOrNull()
                val startSec = start[5].toIntOrNull()

                if (startY == null || startM == null || startD == null || startH == null || startMin == null || startSec == null) {
                    throw RuntimeException("drawhero.xml中的timeInfo字段解析出错4")
                }

                var endY = end[0].toIntOrNull()
                var endM = end[1].toIntOrNull()
                var endD = end[2].toIntOrNull()
                val endH = end[3].toIntOrNull()
                val endMin = end[4].toIntOrNull()
                val endSec = end[5].toIntOrNull()

                if (endY == null || endM == null || endD == null || endH == null || endMin == null || endSec == null) {
                    throw RuntimeException("drawhero.xml中的timeInfo字段解析出错5")
                }

                // todo 写死了开服活动
                if (endY == 0) {
                    endY += 2018
                    endM += 5
                    endD += 10
                }

                // todo 写死了开服活动
                if (startY == 0) {
                    startY += 2018
                    startM += 5
                    startD += 1
                }

                vo.actStartTime = ZonedDateTime.of(
                    startY, startM, startD, startH, startMin, startSec, 0,
                    ZoneId.systemDefault()
                ).toInstant().toEpochMilli()

                vo.actEndTime = ZonedDateTime.of(
                    endY, endM, endD, endH, endMin, endSec, 0,
                    ZoneId.systemDefault()
                ).toInstant().toEpochMilli()
            }

            val basicDrawSpilt = vo.basicDraw.split("|")
            if (basicDrawSpilt.isEmpty()) {
                throw RuntimeException("drawhero.xml中的basicDraw字段解析出错1")
            }
            var baiscSum = 0
            // 1;4000|6;4000|11;1000|201;1000
            val tmpBasicDrawMap = hashMapOf<Int, Int>()
            for (eachDraw in basicDrawSpilt) {
                val eachDrawSpilt = eachDraw.split(";")
                if (eachDrawSpilt.size != 2) {
                    throw RuntimeException("drawhero.xml中的basicDraw字段解析出错2")
                }
                val drawBag0 = eachDrawSpilt[0].toIntOrNull()
                    ?: throw RuntimeException("drawhero.xml中的basicDraw字段解析出错3")
                val drawBag1 = eachDrawSpilt[1].toIntOrNull()
                    ?: throw RuntimeException("drawhero.xml中的basicDraw字段解析出错4")
                baiscSum += drawBag1
                tmpBasicDrawMap[drawBag0] = drawBag1
            }
            vo.basicDrawMap = tmpBasicDrawMap

            if (baiscSum != 10000) {
                throw RuntimeException("drawhero.xml中的basicDraw字段解析出错5")
            }

            val themDraw1Spilt = vo.themDraw1.split("|")
            if (themDraw1Spilt.isEmpty()) {
                throw RuntimeException("drawhero.xml中的themDraw1字段解析出错1")
            }
            var themDraw1Sum = 0
            // 1;4000|6;4000|11;1000|201;1000
            val tmpThemDraw1Map = hashMapOf<Int, Int>()
            for (eachDraw in themDraw1Spilt) {
                val eachDrawSpilt = eachDraw.split(";")
                if (eachDrawSpilt.size != 2) {
                    throw RuntimeException("drawhero.xml中的themDraw1字段解析出错2")
                }
                val drawBag0 = eachDrawSpilt[0].toIntOrNull()
                    ?: throw RuntimeException("drawhero.xml中的themDraw1字段解析出错3")
                val drawBag1 = eachDrawSpilt[1].toIntOrNull()
                    ?: throw RuntimeException("drawhero.xml中的themDraw1字段解析出错4")
                themDraw1Sum += drawBag1
                tmpThemDraw1Map[drawBag0] = drawBag1
            }
            vo.themDraw1Map = tmpThemDraw1Map

            if (themDraw1Sum != 10000) {
                throw RuntimeException("drawhero.xml中的themDraw1字段解析出错5")
            }

            val themDraw2Spilt = vo.themDraw2.split("|")
            if (themDraw2Spilt.isEmpty()) {
                throw RuntimeException("drawhero.xml中的themDraw2字段解析出错1")
            }
            var themDraw2Sum = 0
            // 1;4000|6;4000|11;1000|201;1000
            val tmpThemDraw2Map = hashMapOf<Int, Int>()
            for (eachDraw in themDraw2Spilt) {
                val eachDrawSpilt = eachDraw.split(";")
                if (eachDrawSpilt.size != 2) {
                    throw RuntimeException("drawhero.xml中的themDraw2字段解析出错2")
                }
                val drawBag0 = eachDrawSpilt[0].toIntOrNull()
                    ?: throw RuntimeException("drawhero.xml中的themDraw2字段解析出错3")
                val drawBag1 = eachDrawSpilt[1].toIntOrNull()
                    ?: throw RuntimeException("drawhero.xml中的themDraw2字段解析出错4")
                themDraw2Sum += drawBag1
                tmpThemDraw2Map[drawBag0] = drawBag1
            }
            vo.themDraw2Map = tmpThemDraw2Map

            if (themDraw2Sum != 10000) {
                throw RuntimeException("drawhero.xml中的themDraw2字段解析出错5")
            }
            tmpDropBagMap[vo.id] = vo

        }
        this.dropBagMap = tmpDropBagMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

}