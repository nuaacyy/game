package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.commonfunc.getRandInt
import java.io.Serializable
import java.util.*

data class RelicBoxResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<RelicBoxProto>
) : Serializable

data class RelicBoxProto(
    val id: Int, //遗迹盒子ID
    val conversionTime: Int,   //转化需要时间 单位：小时
    val boxProps: String, //宝箱内道具 Map<DropPropId,Percent>
    val boxRes: String //宝箱内资源 Map<ResVo,Percent>
) : Serializable {
    var dropPropMap: Map<Int, Int> = mapOf()
    var dropResMap: List<Pair<ResVo, Int>> = listOf()

    fun selectRes(): ResVo? {
        var totalRate = 0
        for (dropResInfo in this.dropResMap) {
            totalRate += dropResInfo.second
        }

        val dropRand = getRandInt(totalRate)
        var tempRate = 0
        for (dropResInfo in this.dropResMap) {
            if (dropRand <= tempRate + dropResInfo.second) {
                return dropResInfo.first
            }
            tempRate += dropResInfo.second
        }
        return null
    }
}

class RelicBoxProtoCache : ProtoCacheInit("relicBox.xml") {
    var relicBoxMap: Map<Int, RelicBoxProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<RelicBoxResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as RelicBoxResult

        val tmpRelicBox: HashMap<Int, RelicBoxProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpRelicBox[vo.id] = vo

            vo.dropPropMap = parseIntMap(vo.boxProps) ?: throw RuntimeException("relicBox中boxProps错误:${vo.id}")

            val tmpDropResMap = LinkedList<Pair<ResVo, Int>>()
            val boxResStrList = stringsSplit(vo.boxRes, "|")
            for (boxResStr in boxResStrList) {
                val paras = stringsSplit(boxResStr, "%")
                if (paras.size < 2) {
                    throw RuntimeException("relicBox中boxRes错误:${vo.id}")
                }
                val resVo = resStringToResVo(paras[0]) ?: throw RuntimeException("relicBox中boxRes错误:${vo.id}")
                val percent = paras[1].toIntOrNull() ?: throw RuntimeException("relicBox中boxRes错误:${vo.id}")
                if (percent <= 0) {
                    throw RuntimeException("relicBox中boxRes错误:${vo.id}")
                }
                tmpDropResMap.add(Pair(resVo, percent))
            }
            vo.dropResMap = tmpDropResMap
        }
        this.relicBoxMap = tmpRelicBox
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, relicBox) in pcs.relicBoxCache.relicBoxMap) {
            for ((dropPropId, _) in relicBox.dropPropMap) {
                pcs.dropPropsProtoCache.dropPropsMap[dropPropId]
                    ?: throw RuntimeException("relicBox中dropProp不存在:${relicBox.id}")
            }
        }
    }

}