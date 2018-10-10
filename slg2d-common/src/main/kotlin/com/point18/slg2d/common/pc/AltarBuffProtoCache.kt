package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AltarBuffProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AltarBuffProto>
): Serializable

data class AltarBuffProto(
    val id: Int,
    val numMin: Int,
    val numMax: Int,
    val buff: String
): Serializable {
    var buffMap = mapOf<Int, Int>()                     // 特效
}

class AltarBuffProtoCache : ProtoCacheInit("altarBuff.xml") {
    var effectMap = mapOf<Int, AltarBuffProto>()
    var altarEffectList = listOf<AltarBuffProto>()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AltarBuffProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AltarBuffProtoResult

        val tmpEffectMap = hashMapOf<Int, AltarBuffProto>()
        val tmpAltarEffectList = LinkedList<AltarBuffProto>()
        for (vo in readXmlResult.l) {
            if (tmpEffectMap.containsKey(vo.id)) {
                throw RuntimeException("altarBuff.xml :: id[${vo.id}]重复")
            }

            val tmpBuffMap = hashMapOf<Int, Int>()
            val buffSpiltFirst = vo.buff.split("|")
            for (eachBuff in buffSpiltFirst) {
                val buffSpiltSecond = eachBuff.split(";")
                if (buffSpiltSecond.size != 2) {
                    throw RuntimeException("altarBuff.xml ::  $vo buff格式不合法.")
                }
                val buffType = buffSpiltSecond[0].toIntOrNull()
                val buffValue = buffSpiltSecond[1].toIntOrNull()
                if (buffType == null || buffValue == null) {
                    throw RuntimeException("altarBuff.xml ::  $vo buff格式不合法2.")
                }
                tmpBuffMap[buffType] = buffValue
            }
            vo.buffMap = tmpBuffMap
            tmpEffectMap[vo.id] = vo
            tmpAltarEffectList.add(vo)
        }
        effectMap = tmpEffectMap
        altarEffectList = tmpAltarEffectList
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

    fun getAltarEff(killNum: Int, effType: Int): Int {

        for (each in altarEffectList) {
            if (killNum >= each.numMin && killNum <= each.numMax) {
                return each.buffMap[effType] ?: 0
            }
        }

        return 0
    }

}