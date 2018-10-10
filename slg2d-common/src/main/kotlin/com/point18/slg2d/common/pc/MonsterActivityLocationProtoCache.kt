package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.constg.NORMAL_CELL
import com.point18.slg2d.common.constg.SNOW_AREA
import com.point18.slg2d.common.constg.SNOW_BASE
import com.point18.slg2d.common.constg.SNOW_MID
import java.io.Serializable
import java.util.*

data class MonsterActivityLocationResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MonsterActivityLocationProto>
) : Serializable

data class MonsterActivityLocationProto(
    val id: Int,                //int 唯一id
    val snowCoordinate: String,    //array_int 雪地左上角坐标X
    val forbiddenCoordinate: String,    //array_int 禁地左上角坐标X
    val baseCoordinate: String,    //array_int 底座左上角坐标Y
    val snowSize: String,          //array_int 雪地大小
    val forbiddenSize: String,          //array_int 雪地大小
    val baseSize: String,        //array_int 底座大小
    val incidenceBigLv: Int,     //int 资源等级
    val incidenceMidMonster: Int //雪地魔物刷新
) : Serializable {
    // 同心正方形,外层的未过滤内层的,有利有弊,用的时候注意了
    var snowMap: Map<Int, Map<Int, Int>> = mapOf()
    var midMap: Map<Int, Map<Int, Int>> = mapOf()
    var baseMap: Map<Int, Map<Int, Int>> = mapOf()
    var baseIndex: List<Int> = listOf()
    var snowIndex: List<Int> = listOf()
    var snowSizeIndex: List<Int> = listOf()
    //只有雪地区域
    var onlySnowList: Array<Pair<Int, Int>> = arrayOf()
}

class MonsterActivityLocationProtoCache : ProtoCacheInit("monsterActivityLocation.xml") {
    var monsterActivityLocationProtoMap: Map<Int, MonsterActivityLocationProto> = mapOf()
    var monsterActivityLocationProtoList: List<MonsterActivityLocationProto> = listOf()
    var monsterActivityLocationXYProtoMap = TwoKeyIndex<Int, Int, MonsterActivityLocationProto>(
        { it.baseIndex[0] }, { it.baseIndex[1] }
    )

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MonsterActivityLocationResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MonsterActivityLocationResult

        val tmpMonsterActivityLocationProtoMap: HashMap<Int, MonsterActivityLocationProto> = hashMapOf()
        val tmpMonsterActivityLocationProtoList: LinkedList<MonsterActivityLocationProto> = LinkedList()
        for (vo in readXmlResult.l) {
            val existVo = tmpMonsterActivityLocationProtoMap[vo.id]

            if (existVo != null) {
                throw RuntimeException("monsterActivityLocation.xml::id 存在重复:${vo.id}")
            }

            /** snowCoordinate **/
            val rs1 = stringsSplit(vo.snowCoordinate, ";")
            val snowCoordinate = LinkedList<Int>()
            if (rs1.size != 2) {
                throw RuntimeException("monsterActivityLocation.xml::snowCoordinate 格式错误${vo.snowCoordinate}")
            }
            for (s in rs1) {
                val i = strconvAtoi(s) ?: throw RuntimeException("monsterActivityLocation.xml::snowCoordinate [$s]非数字")
                snowCoordinate.add(i)
            }
            vo.snowIndex = snowCoordinate

            /** snowSize **/
            val rs2 = stringsSplit(vo.snowSize, ";")
            val snowSize = LinkedList<Int>()
            if (rs2.size != 2) {
                throw RuntimeException("monsterActivityLocation.xml::snowSize 格式错误${vo.snowSize}")
            }
            for (s in rs2) {
                val i = strconvAtoi(s) ?: throw RuntimeException("monsterActivityLocation.xml::snowSize [$s]非数字")
                snowSize.add(i)
            }
            vo.snowSizeIndex = snowSize

            /** snowMap **/
            val snowMap = hashMapOf<Int, HashMap<Int, Int>>()
            for (i in snowCoordinate[0]..(snowCoordinate[0] + snowSize[0] - 1)) {
                for (j in snowCoordinate[1]..(snowCoordinate[1] + snowSize[1] - 1)) {
                    val subMap = snowMap.getOrPut(i) {
                        hashMapOf()
                    }
                    subMap[j] = 1
                }
            }
            vo.snowMap = snowMap

            /** forbiddenCoordinate **/
            val rs5 = stringsSplit(vo.forbiddenCoordinate, ";")
            val forbiddenCoordinate = LinkedList<Int>()
            if (rs5.size != 2) {
                throw RuntimeException("monsterActivityLocation.xml::forbiddenCoordinate 格式错误${vo.forbiddenCoordinate}")
            }
            for (s in rs5) {
                val i =
                    strconvAtoi(s) ?: throw RuntimeException("monsterActivityLocation.xml::forbiddenCoordinate [$s]非数字")
                forbiddenCoordinate.add(i)
            }

            /** forbiddenSize **/
            val rs6 = stringsSplit(vo.forbiddenSize, ";")
            val forbiddenSize = LinkedList<Int>()
            if (rs6.size != 2) {
                throw RuntimeException("monsterActivityLocation.xml::forbiddenSize 格式错误${vo.forbiddenSize}")
            }
            for (s in rs6) {
                val i = strconvAtoi(s) ?: throw RuntimeException("monsterActivityLocation.xml::forbiddenSize [$s]非数字")
                forbiddenSize.add(i)
            }

            /** midMap **/
            val midMap = hashMapOf<Int, HashMap<Int, Int>>()
            for (i in forbiddenCoordinate[0]..(forbiddenCoordinate[0] + forbiddenSize[0] - 1)) {
                for (j in forbiddenCoordinate[1]..(forbiddenCoordinate[1] + forbiddenSize[1] - 1)) {
                    val subMap = midMap.getOrPut(i) {
                        hashMapOf()
                    }
                    subMap[j] = 1
                }
            }
            vo.midMap = midMap

            /** baseCoordinate **/
            val rs3 = stringsSplit(vo.baseCoordinate, ";")
            val baseCoordinate = LinkedList<Int>()
            if (rs3.size != 2) {
                throw RuntimeException("monsterActivityLocation.xml::baseCoordinate 格式错误${vo.baseCoordinate}")
            }
            for (s in rs3) {
                val i = strconvAtoi(s) ?: throw RuntimeException("monsterActivityLocation.xml::baseCoordinate [$s]非数字")
                baseCoordinate.add(i)
            }
            vo.baseIndex = baseCoordinate

            /** baseSize **/
            val rs4 = stringsSplit(vo.baseSize, ";")
            val baseSize = LinkedList<Int>()
            if (rs4.size != 2) {
                throw RuntimeException("monsterActivityLocation.xml::baseSize 格式错误${vo.baseSize}")
            }
            for (s in rs4) {
                val i = strconvAtoi(s) ?: throw RuntimeException("monsterActivityLocation.xml::baseSize [$s]非数字")
                baseSize.add(i)
            }

            /** baseMap **/
            val baseMap = hashMapOf<Int, HashMap<Int, Int>>()
            for (i in baseCoordinate[0]..(baseCoordinate[0] + baseSize[0] - 1)) {
                for (j in baseCoordinate[1]..(baseCoordinate[1] + baseSize[1] - 1)) {
                    val subMap = baseMap.getOrPut(i) {
                        hashMapOf()
                    }
                    subMap[j] = 1
                }
            }
            vo.baseMap = baseMap

            val onlySnowList = LinkedList<Pair<Int, Int>>()
            for ((x, yMap) in vo.snowMap) {
                for ((y, _) in yMap) {
                    if (vo.midMap[x]?.get(y) != null) {
                        continue
                    }
                    onlySnowList.add(Pair(x, y))
                }
            }
            vo.onlySnowList = onlySnowList.toTypedArray()

            /** map **/
            tmpMonsterActivityLocationProtoMap[vo.id] = vo
            tmpMonsterActivityLocationProtoList.add(vo)
            this.monsterActivityLocationXYProtoMap.insertByKey(vo)
        }
        this.monsterActivityLocationProtoMap = tmpMonsterActivityLocationProtoMap
        this.monsterActivityLocationProtoList = tmpMonsterActivityLocationProtoList
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

    // 查询XY坐标在雪地中的哪个部位,并且返回该活动boss区域模板
    data class ActivityBossAreaTypeRet(
        var locationProto: MonsterActivityLocationProto?,
        var int: Int
    )

    fun findActivityBossAreaType(x: Int, y: Int): ActivityBossAreaTypeRet {
        var ex: Boolean
        for ((_, proto) in this.monsterActivityLocationProtoMap) {

            val tmp1 = proto.baseMap[x]
            ex = if (tmp1 == null) {
                false
            } else {
                val tmp2 = tmp1[y]
                tmp2 != null
            }
            if (ex) {
                return ActivityBossAreaTypeRet(proto, SNOW_BASE)
            }

            val tmp5 = proto.midMap[x]
            ex = if (tmp5 == null) {
                false
            } else {
                val tmp6 = tmp5[y]
                tmp6 != null
            }
            if (ex) {
                return ActivityBossAreaTypeRet(proto, SNOW_MID)
            }

            val tmp3 = proto.snowMap[x]
            ex = if (tmp3 == null) {
                false
            } else {
                val tmp4 = tmp3[y]
                tmp4 != null
            }
            if (ex) {
                return ActivityBossAreaTypeRet(proto, SNOW_AREA)
            } else {
                continue
            }
        }
        return ActivityBossAreaTypeRet(null, NORMAL_CELL)
    }

    fun isSnowMid(x: Int, y: Int): Boolean {
        for ((_, proto) in this.monsterActivityLocationProtoMap) {
            val tmp3 = proto.midMap[x]
            var ex: Boolean
            ex = if (tmp3 == null) {
                false
            } else {
                val tmp4 = tmp3[y]
                tmp4 != null
            }

            if (ex) {
                return true
            }
        }

        return false
    }

    fun inSnowArea(x: Int, y: Int): MonsterActivityLocationProto? {
        for ((_, proto) in this.monsterActivityLocationProtoMap) {
            val tmp3 = proto.snowMap[x]
            var ex: Boolean
            ex = if (tmp3 == null) {
                false
            } else {
                val tmp4 = tmp3[y]
                tmp4 != null
            }

            if (ex) {
                return proto
            }
        }

        return null
    }
}