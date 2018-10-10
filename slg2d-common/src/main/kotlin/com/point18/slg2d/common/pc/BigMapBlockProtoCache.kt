package com.point18.slg2d.common.pc

import java.io.Serializable

data class BigMapComposeMap(
    val bigMapCompose: Map<String, BigMapCompose> = mapOf()
): Serializable

data class BigMapCompose(
    var posMark: Map<String, Int> = mapOf()
): Serializable

class BigMapBlockProtoCache : ProtoCacheInit("bigMapFile2Block.json") {
    var bigMapComposeMapTmp: BigMapComposeMap? = BigMapComposeMap()
    var bigMapCompose: Map<Int, Map<Int, Int>> = mapOf()    //

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<BigMapComposeMap>(pcs, configFileName, LOAD_TYPE_JSON)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as BigMapComposeMap

        // 将json中数据解析出来放入map中
        this.bigMapComposeMapTmp = readXmlResult
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        val mapSelection = pcs.basicProtoCache.mapSelection
        val tmpBigMapComposeMapTmp = this.bigMapComposeMapTmp

        val cs = if (tmpBigMapComposeMapTmp == null) {
            null
        } else {
            tmpBigMapComposeMapTmp.bigMapCompose[mapSelection]
        }
        if (null == cs) {
            throw RuntimeException("找不到当前大地图选择方案的阻挡点")
        }

        val bigMap4Compose = hashMapOf<Int, HashMap<Int, Int>>()
        for ((posStr, mark) in cs.posMark) {
            val pos = strconvAtoi(posStr) ?: throw RuntimeException("转换大地图阻挡点位置时出错：$posStr")

            val x = pos / 10000
            val y = pos % 10000
            var yMap = bigMap4Compose[x]

            if (yMap == null) {
                yMap = hashMapOf()
                bigMap4Compose[x] = yMap
            }
            yMap[y] = mark
        }

        this.bigMapCompose = bigMap4Compose

        this.bigMapComposeMapTmp = null
    }

    // 传入坐标检测是否是阻挡
    fun isCompose(x: Int, y: Int): Boolean {
        val xMap = this.bigMapCompose[x] ?: return false
        xMap[y] ?: return false
        return true
    }

}