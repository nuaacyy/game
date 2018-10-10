package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class ArmyRestrainResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<ArmyRestrainProto>
): Serializable

data class ArmyRestrainProto(
    //唯一ID
    val id: Int,
    //攻方兵种
    val attackArmy: Int,
    //受方兵种
    val defenceArmy: Int,
    //克制系数
    val restrainPara: Int
): Serializable

class ArmyRestrainProtoCache : ProtoCacheInit("armyRestrain.xml") {
    var armyRestrainMap: Map<Int, Map<Int, ArmyRestrainProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ArmyRestrainResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ArmyRestrainResult

        val tmpArmyRestrainMap: HashMap<Int, HashMap<Int, ArmyRestrainProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val tmp = tmpArmyRestrainMap.getOrPut(vo.attackArmy) { hashMapOf() }
            tmp[vo.defenceArmy] = vo
        }
        this.armyRestrainMap = tmpArmyRestrainMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

}