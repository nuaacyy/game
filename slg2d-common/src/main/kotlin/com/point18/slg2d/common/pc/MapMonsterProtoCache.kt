package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.CYCLE_BOSS
import com.point18.slg2d.common.constg.PERMANENT_BOSS
import java.io.Serializable
import java.util.*

data class MapMonsterResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MapMonsterProto>
) : Serializable

data class MapMonsterProto(
    val id: Int,  // 唯一id
    val permanentNum: String,   //常驻魔物数量 魔物等级:数量
    val cycleNum: String        //周期魔物数量 魔物等级:数量
) : Serializable {
    var monsterInfoMap: Map<Int, MapMonsterInfo> = mapOf()
}

data class MapMonsterInfo(
    var monsterMap: Map<Int, Int>,
    var totalNum: Int
)

class MapMonsterProtoCache : ProtoCacheInit("mapMonster.xml") {
    var mapMonsterProtoMap: Map<Int, MapMonsterProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MapMonsterResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MapMonsterResult

        val tmpMapMonsterProtoMap: HashMap<Int, MapMonsterProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val existVo = tmpMapMonsterProtoMap[vo.id]

            if (existVo != null) {
                throw RuntimeException("mapMonster模板ID存在重复:${vo.id}.")
            }
            tmpMapMonsterProtoMap[vo.id] = vo

            val tmpMonsterInfoMap = hashMapOf<Int, MapMonsterInfo>()
            val permanentMonsterMap =
                parseIntMap(vo.permanentNum) ?: throw RuntimeException("mapMonster中permanentNum配置错误:${vo.id}.")
            val permanentTotalNum = permanentMonsterMap.values.sum()
            tmpMonsterInfoMap[PERMANENT_BOSS] = MapMonsterInfo(permanentMonsterMap, permanentTotalNum)
            val cycleMonsterMap =
                parseIntMap(vo.cycleNum) ?: throw RuntimeException("mapMonster中cycleNum配置错误:${vo.id}.")
            val cycleTotalNum = cycleMonsterMap.values.sum()
            tmpMonsterInfoMap[CYCLE_BOSS] = MapMonsterInfo(cycleMonsterMap, cycleTotalNum)
            vo.monsterInfoMap = tmpMonsterInfoMap
        }
        this.mapMonsterProtoMap = tmpMapMonsterProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}