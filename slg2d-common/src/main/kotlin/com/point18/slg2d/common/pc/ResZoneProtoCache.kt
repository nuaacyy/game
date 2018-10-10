package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.MAX_MAP_SIZE
import java.io.Serializable
import java.util.*

data class ResZoneResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<ResZoneProto>
) : Serializable

data class ResZoneProto(
    val id: Int, // 唯一id
    val level: Int,  // 资源带等级
    val coordinateZone: String // 坐标区域

) : Serializable {
    var coordinateX: Int = 0    //顶点坐标X，其他坐标，通过此坐标镜像获得
    var coordinateY: Int = 0    //顶点坐标Y，其他坐标，通过此坐标镜像获得
    var coordinateX2: Int = 0    //顶点坐标X2，其他坐标，通过此坐标镜像获得
    var coordinateY2: Int = 0    //顶点坐标Y2，其他坐标，通过此坐标镜像获得
}

// 资源块相关属性
data class ResGridProto(
    var id: Int,
    var resZoneProto: ResZoneProto
)

class ResZoneProtoCache : ProtoCacheInit("resZone.xml") {
    var resZoneProtoMap: Map<Int, ResZoneProto> = mapOf()
    var resGridMap: List<ResGridProto> = listOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ResZoneResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ResZoneResult

        val tmpResZoneProtoMap: HashMap<Int, ResZoneProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val proto = tmpResZoneProtoMap[vo.level]

            if (proto != null) {
                throw RuntimeException("mapLocation模板LV存在重复:${vo.level}.")
            }

            tmpResZoneProtoMap[vo.level] = vo

            val coordinate = stringsSplit(vo.coordinateZone, "|")

            if (coordinate.size != 2) {
                throw RuntimeException("resZone中Coordinate配置错误1:${vo.coordinateZone}.")
            }

            val coordinate1 = stringsSplit(coordinate[0], ";")

            if (coordinate1.size != 2) {
                throw RuntimeException("resZone中Coordinate配置错误2:${vo.coordinateZone}.")
            }
            val vocoordinateX = strconvAtoi(coordinate1[0])
            val vocoordinateY = strconvAtoi(coordinate1[1])
            if (vocoordinateX == null || vocoordinateY == null) {
                throw RuntimeException("resZone中Coordinate配置错误")
            }
            vo.coordinateX = vocoordinateX
            vo.coordinateY = vocoordinateY
            val coordinate2 = stringsSplit(coordinate[1], ";")

            if (coordinate2.size != 2) {
                throw RuntimeException("resZone中Coordinate配置错误3:${vo.coordinateZone}.")
            }

            val vocoordinateX2 = strconvAtoi(coordinate2[0])
            val vocoordinateY2 = strconvAtoi(coordinate2[1])
            if (vocoordinateX2 == null || vocoordinateY2 == null) {
                throw RuntimeException("resZone中Coordinate配置错误")
            }
            vo.coordinateX2 = vocoordinateX2
            vo.coordinateY2 = vocoordinateY2

        }
        this.resZoneProtoMap = tmpResZoneProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        // 将地图块切割，将每个大块地图和资源带关联，放入map中
        val bc = pcs.basicProtoCache
        val eachLen = bc.allArea / bc.resourceArea // 每边多个大格子

        val tmpResGridMap: LinkedList<ResGridProto> = LinkedList()
        for (i in 0..(bc.allResGridNum - 1)) {
            val row = i / eachLen
            val col = i % eachLen
            val topX = col * bc.resourceArea
            val topY = row * bc.resourceArea

            val resZoneProto = calZiYuanDaiLvByXY(pcs.resZoneProtoCache, topX, topY)
                ?: throw RuntimeException("XY找不到所属的资源带,X:$topX,Y:$topY")
            //return errors.new(fmt.sprintf("无法查找到特定资源块 ${  } ${  } ${  } ${  } 所属的资源带", bc.resourceArea, i, topX, topY))

            tmpResGridMap.add(ResGridProto(i, resZoneProto))
        }
        this.resGridMap = tmpResGridMap
    }

    // 根据资源格子ID获取资源带信息
    fun fetchResInfoByGridId(gridId: Int): ResGridProto {
        return this.resGridMap[gridId]
    }

    // 根据一个XY来定位隶属于哪个资源带
    fun calZiYuanDaiLvByXY(resZoneProtoCache: ResZoneProtoCache, x: Int, y: Int): ResZoneProto? {
        for ((_, ziyuandaiInfo) in resZoneProtoCache.resZoneProtoMap) {
            val codX = ziyuandaiInfo.coordinateX
            val codX2 = ziyuandaiInfo.coordinateX2
            val codY = ziyuandaiInfo.coordinateY
            val codY2 = ziyuandaiInfo.coordinateY2
            //println("codX=${codX},codX2= ${codX2},codY=${codY},codY2= ${codY2},x=$x,y=$y")

            val xInRange = ((x >= codX) && (x <= codX2)) || ((x >= (MAX_MAP_SIZE - codX2 - 1)) && (x <= (MAX_MAP_SIZE - codX - 1)))
            val yExt = (y >= codY && y <= (MAX_MAP_SIZE - codY - 1))
            val boolX = xInRange && yExt

            val yInRange = ((y >= codY) && (y <= codY2)) || ((y >= (MAX_MAP_SIZE - codY2 - 1)) && y <= ((MAX_MAP_SIZE - codY - 1)))
            val xExt = ((x >= codX) && (x <= (MAX_MAP_SIZE - codX - 1)))
            val boolY = yInRange && xExt

            val inRange = boolX || boolY
//			println("boolX:${boolX},boolY${boolY}")
            if (inRange) {
//				println("已经进入return ziyuandaiInfo")
                return ziyuandaiInfo
            }
        }
//		println("for end return null")
        return null
    }
}