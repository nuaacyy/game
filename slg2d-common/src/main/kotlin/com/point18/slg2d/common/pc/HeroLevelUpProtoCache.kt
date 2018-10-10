package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class HeroLevelUpResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<HeroLevelUpProto>
): Serializable

data class HeroLevelUpProto(
    val id: Int,
    val level: Int,
    val exp: Int,
    val totalExp: Int,
    val arenaExp: Int
): Serializable

class HeroLevelUpProtoCache : ProtoCacheInit("heroLevelUp.xml") {
    var protoMap: Map<Int, HeroLevelUpProto> = mapOf()
    var maxLv: Int = 0
    var protos: List<HeroLevelUpProto> = listOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<HeroLevelUpResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as HeroLevelUpResult

        val tmpProtoMap: HashMap<Int, HeroLevelUpProto> = hashMapOf()
        val tmpProtos: LinkedList<HeroLevelUpProto> = LinkedList()
        for (vo in readXmlResult.l) {
            tmpProtoMap[vo.level] = vo
            tmpProtos.add(vo)
            if (this.maxLv < vo.level) {
                this.maxLv = vo.level
            }
        }
        this.protoMap = tmpProtoMap
        this.protos = tmpProtos
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

    fun fetchLevelUpProto(lv: Int): HeroLevelUpProto? {
        val heroLevelUpProto = this.protoMap[lv] ?: return null

        return heroLevelUpProto
    }

}