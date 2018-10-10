package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class FurnitureProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<FurnitureProto>
): Serializable

data class FurnitureProto(
    val id: Int,  // 编号
    val type: Int,  // 商品分类
    val buycost: String, // 购买需要
    val num: Int,  // 可买数量
    val acreage: String, // 面积
    val produce: String, // 产出
    val cycle: Int,  // 产出周期
    val rate: Double,  // 舒适度参数
    val upperLim: String, // 产出上限
    val comf: Int, // 舒适度
    val subject: Int, // 主题模板id
    val subjectNum: Int  // 主题家具上限
): Serializable {
    var costRes: List<ResVo> = listOf()
    var floorSpace: List<Int> = listOf()
    var produceRes: List<ResVo> = listOf()
    var upperLimRes: List<ResVo> = listOf()
}

class FurnitureProtoCache : ProtoCacheInit("funeral.xml") {
    var furnitureProtoMap: Map<Int, FurnitureProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<FurnitureProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as FurnitureProtoResult

        val tmpFurnitureProtoMap: HashMap<Int, FurnitureProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpFurnitureProtoMap.containsKey(vo.id)) {
                throw RuntimeException("funeral.xml :: id[${vo.id}]重复")
            }

            val costRes = resStringToResVoList(vo.buycost)
                ?: throw RuntimeException("funeral.xml :: costRes ${vo.buycost} 购买需要不合法.")
            vo.costRes = costRes

            val produceRes = resStringToResVoList(vo.produce)
                ?: throw RuntimeException("funeral.xml :: produceRes ${vo.produce} 产出不合法.")
            if (produceRes.size > 1) {
                throw RuntimeException("funeral.xml :: produceRes ${vo.produce} 产出不能超过1种类型.")
            }
            vo.produceRes = produceRes

            val upperLimRes = resStringToResVoList(vo.upperLim)
                ?: throw RuntimeException("funeral.xml :: upperLimRes ${vo.produce} 产出上限不合法.")
            if (upperLimRes.size > 1) {
                throw RuntimeException("funeral.xml :: upperLimRes ${vo.produce} 产出上限不能超过1种类型.")
            }
            vo.upperLimRes = upperLimRes

            val split = stringsSplit(vo.acreage, ";")
            if (split.isEmpty() || split.size > 2) {
                throw RuntimeException("funeral.xml :: floorSpace ${vo.acreage} 面积不合法.")
            } else if (split.size == 1 && "0" != split[0]) {
                throw RuntimeException("funeral.xml :: floorSpace ${vo.acreage} 面积不合法.")
            }
            val floorSpace = LinkedList<Int>()
            split.forEach {
                val i = strconvAtoi(it) ?: throw RuntimeException("funeral.xml中的floorSpace解析出错:$it")
                floorSpace.add(i)
            }
            vo.floorSpace = floorSpace

            tmpFurnitureProtoMap[vo.id] = vo
        }
        furnitureProtoMap = tmpFurnitureProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in pcs.furnitureProtoCache.furnitureProtoMap) {
            var flag = false
            for ((_, subjectProto) in pcs.furnitureSubjectProtoCache.subjectProtoMap) {
                if (proto.subject == subjectProto.id) {
                    flag = true
                    break
                }
            }
            if (!flag) {
                throw RuntimeException("funeral.xml :: subjectId ${proto.subject} 不存在.")
            }
        }
    }

}