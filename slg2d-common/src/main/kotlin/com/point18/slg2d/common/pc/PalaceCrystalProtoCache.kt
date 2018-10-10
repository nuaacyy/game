package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

data class PalaceCrystalProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<PalaceCrystalProto>
): Serializable

data class PalaceCrystalProto(
    val crystalPool: Int, // 奖池金币区间
    val crystalIncrement: String // 金币自增长区间
): Serializable

class PalaceCrystalProtoCache : ProtoCacheInit("palaceCrystal.xml") {
    var palaceCrystalProtoMap: HashMap<Int, LinkedList<Int>> = hashMapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<PalaceCrystalProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as PalaceCrystalProtoResult

        val tmpPalaceCrystalProtoMap: HashMap<Int, LinkedList<Int>> = hashMapOf()
        for (vo in readXmlResult.l) {

            val increment = LinkedList<Int>()

            val crystalIncrement = vo.crystalIncrement.split(";")
            if (crystalIncrement.size != 2) {
                throw RuntimeException("palaceCrystal.xml中的crystalIncrement字段解析出错1")
            }
            val each0 = crystalIncrement[0].toIntOrNull()
                ?: throw RuntimeException("palaceCrystal.xml中的crystalIncrement字段解析出错1")
            val each1 = crystalIncrement[1].toIntOrNull()
                ?: throw RuntimeException("palaceCrystal.xml中的crystalIncrement字段解析出错1")
            increment.add(each0)
            increment.add(each1)

            tmpPalaceCrystalProtoMap[vo.crystalPool] = increment
        }
        this.palaceCrystalProtoMap = tmpPalaceCrystalProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }
}