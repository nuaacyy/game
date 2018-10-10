package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.mcache.ThreeKeyIndex
import java.io.Serializable
import java.util.*

data class FurnitureSubjectProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<FurnitureSubjectProto>
): Serializable

data class FurnitureSubjectProto(
    val id: Int, // 编号
    val subject: String, // 主题名
    val template: String  // 主题模板
) : Serializable{
    var subjectTemplate: List<FurVo> = listOf()
}

class FurnitureSubjectProtoCache : ProtoCacheInit("funeralsubject.xml") {
    var subjectProtoMap: Map<Int, FurnitureSubjectProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<FurnitureSubjectProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as FurnitureSubjectProtoResult

        val tmpSubjectProtoMap: HashMap<Int, FurnitureSubjectProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpSubjectProtoMap.containsKey(vo.id)) {
                throw RuntimeException("funeralsubject.xml :: id[${vo.id}]重复")
            }

            val subjectTemplate = resStringToFurVo(vo.template)
                ?: throw RuntimeException("funeralsubject.xml :: subjectTemplate ${vo.template} 主题模板不合法.")
            vo.subjectTemplate = subjectTemplate

            tmpSubjectProtoMap[vo.id] = vo
        }
        subjectProtoMap = tmpSubjectProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        val furnitureProtoMap = pcs.furnitureProtoCache.furnitureProtoMap
        for ((_, proto) in pcs.furnitureSubjectProtoCache.subjectProtoMap) {
            // 占地
            val area = ThreeKeyIndex(
                { it: Array<Int> -> it[0] },
                { it: Array<Int> -> it[1] },
                { it: Array<Int> -> it[2] }
            )
            for (furVo in proto.subjectTemplate) {
                val furProto = furnitureProtoMap[furVo.protoId]
                if (furProto == null) {
                    throw RuntimeException("funeralsubject.xml :: subjectId ${proto.id} 的template字段的furniture的id不存在.")
                }
                if (furVo.direction == 0) {
                    for (x in furVo.x until furVo.x + furProto.floorSpace[0]) {
                        for (y in furVo.y until furVo.y + furProto.floorSpace[1]) {
                            // 家具类型坐标校验
                            if (furProto.type == com.point18.slg2d.common.constg.WALL_DECORATION) {
                                // 墙饰
                                if ((x >= 0 && y >= 0) || (x < 0 && y < 0)) {
                                    throw RuntimeException("funeralsubject.xml :: subjectId ${proto.id} 的template字段坐标错误.")
                                }
                            } else if (furProto.type == com.point18.slg2d.common.constg.FLOOR_DECORATION || furProto.type == com.point18.slg2d.common.constg.COMMON_FUR
                                || furProto.type == com.point18.slg2d.common.constg.FUNCTIONAL_FUR
                            ) {
                                // 装饰
                                if (x < 0 || y < 0) {
                                    throw RuntimeException("funeralsubject.xml :: subjectId ${proto.id} 的template字段坐标错误.")
                                }
                            }
                            // 占地校验
                            if (area.findByKey(1, x, y) != null) {
                                throw RuntimeException("funeralsubject.xml :: subjectId ${proto.id} 的template字段占地冲突.")
                            }
                            area.insertByKey(arrayOf(1, x, y))
                        }
                    }
                } else {
                    for (x in furVo.x until furVo.x + furProto.floorSpace[1]) {
                        for (y in furVo.y until furVo.y + furProto.floorSpace[0]) {
                            // 家具类型坐标校验
                            if (furProto.type == com.point18.slg2d.common.constg.WALL_DECORATION) {
                                // 墙饰
                                if ((x >= 0 && y >= 0) || (x < 0 && y < 0)) {
                                    throw RuntimeException("funeralsubject.xml :: subjectId ${proto.id} 的template字段坐标错误.")
                                }
                            } else if (furProto.type == com.point18.slg2d.common.constg.FLOOR_DECORATION || furProto.type == com.point18.slg2d.common.constg.COMMON_FUR
                                || furProto.type == com.point18.slg2d.common.constg.FUNCTIONAL_FUR
                            ) {
                                // 装饰
                                if (x < 0 || y < 0) {
                                    throw RuntimeException("funeralsubject.xml :: subjectId ${proto.id} 的template字段坐标错误.")
                                }
                            }
                            // 占地校验
                            if (area.findByKey(1, x, y) != null) {
                                throw RuntimeException("funeralsubject.xml :: subjectId ${proto.id} 的template字段占地冲突.")
                            }
                            area.insertByKey(arrayOf(1, x, y))
                        }
                    }
                }
            }
        }
    }

    /**
    从模板中把家具拜访信息字符串解析成切片
     */
    private fun resStringToFurVo(resString: String): List<FurVo>? {
        val furVos: LinkedList<FurVo> = LinkedList()
        if (resString == "0") {
            return furVos
        }

        val furs = resString.split("|")
        for (oneFur in furs) {
            val info = oneFur.split(";")
            if (info.size != 4) {
                //TODO 待删
                return listOf()
            }

            val x = info[0].toInt()
            val y = info[1].toInt()
            val direction = info[2].toInt()
            if (direction != 0 && direction != 1) {
                return listOf()
            }
            val protoId = info[3].toInt()

            val furVo = FurVo(x, y, direction, protoId)
            furVos.add(furVo)
        }

        return furVos
    }
}

data class FurVo(
    val x: Int,
    val y: Int,
    val direction: Int,
    val protoId: Int
)