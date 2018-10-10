package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class HeroExpAwardResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<HeroExpAwardProto>
): Serializable

data class HeroExpAwardProto(
    val id: Int, //唯一ID
    val type: Int, //类型
    val name: String, //名字
    val heroLv: Int,  //英雄等级
    val heroExp: Int  //奖励的英雄经验
): Serializable

class HeroExpAwardProtoCache : ProtoCacheInit("heroExpAward.xml") {
    var heroExpAwardMap: Map<Int, HeroExpAwardProto> = mapOf()
    var heroExpAwardTypeMap: Map<Int, Map<Int, HeroExpAwardProto>> = mapOf()
    var heroMinExpAwardMap: Map<Int, HeroExpAwardProto> = mapOf()
    var heroMaxExpAwardMap: Map<Int, HeroExpAwardProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<HeroExpAwardResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as HeroExpAwardResult

        val tmpHeroExpAwardMap: HashMap<Int, HeroExpAwardProto> = hashMapOf()
        val tmpHeroExpAwardTypeMap: HashMap<Int, HashMap<Int, HeroExpAwardProto>> = hashMapOf()
        val tmpHeroMinExpAwardMap: HashMap<Int, HeroExpAwardProto> = hashMapOf()
        val tmpHeroMaxExpAwardMap: HashMap<Int, HeroExpAwardProto> = hashMapOf()
        for (proto in readXmlResult.l) {
            tmpHeroExpAwardMap[proto.id] = proto
            val tmp = tmpHeroExpAwardTypeMap.getOrPut(proto.type) { hashMapOf() }
            tmp[proto.heroLv] = proto
            val minProto = tmpHeroMinExpAwardMap[proto.type]
            if (minProto == null || minProto.heroLv > proto.heroLv) {
                tmpHeroMinExpAwardMap[proto.type] = proto
            }
            val maxProto = tmpHeroMaxExpAwardMap[proto.type]
            if (maxProto == null || maxProto.heroLv < proto.heroLv) {
                tmpHeroMaxExpAwardMap[proto.type] = proto
            }
        }
        this.heroExpAwardMap = tmpHeroExpAwardMap
        this.heroExpAwardTypeMap = tmpHeroExpAwardTypeMap
        this.heroMinExpAwardMap = tmpHeroMinExpAwardMap
        this.heroMaxExpAwardMap = tmpHeroMaxExpAwardMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        if (this.heroMinExpAwardMap.isEmpty() || (this.heroMaxExpAwardMap.size) == 0) {
            throw RuntimeException("heroExpAward.xml :postCheck err")
        }
    }

    fun getProtoByLv(protoType: Int, heroLv: Int): HeroExpAwardProto? {
        val awardMap = this.heroExpAwardTypeMap[protoType] ?: return null
        val proto = awardMap[heroLv]
        if (proto != null) {
            return proto
        }
        val minProto = this.heroMinExpAwardMap[protoType]
        if (minProto != null) {
            if (heroLv < minProto.heroLv) {
                return minProto
            }
        }
        val maxProto = this.heroMaxExpAwardMap[protoType]
        if (maxProto != null) {
            if (heroLv > maxProto.heroLv) {
                return maxProto
            }
        }
        return null
    }

}