package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class MonsterHurtResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MonsterHurtProto>
): Serializable

data class MonsterHurtProto(
    val id: Int = 0, //唯一ID
    val times: Int = 0, //次数
    val hurt: Int = 0 //伤害加成，百分比
): Serializable

class MonsterHurtProtoCache : ProtoCacheInit("monsterHurt.xml") {
    var monsterHurtMap: HashMap<Int, MonsterHurtProto> = hashMapOf()
    var maxHurtProto: MonsterHurtProto? = null
    var minHurtProto: MonsterHurtProto? = null

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MonsterHurtResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MonsterHurtResult

        val tmpMonsterHurtMap: HashMap<Int, MonsterHurtProto> = hashMapOf()
        for (proto in readXmlResult.l) {
            tmpMonsterHurtMap[proto.times] = proto
            if (this.maxHurtProto == null) {
                this.maxHurtProto = proto
            }
            if (this.minHurtProto == null) {
                this.minHurtProto = proto
            }
            val minHurtProto1 = this.minHurtProto
            val maxHurtProto2 = this.maxHurtProto
            if (minHurtProto1 == null || maxHurtProto2 == null) {
                throw RuntimeException("monsterHurt.xml出错:${proto.id}")
            }
            if (proto.times < minHurtProto1.times) {
                this.minHurtProto = proto
            }
            if (proto.times > maxHurtProto2.times) {
                this.maxHurtProto = proto
            }
        }
        this.monsterHurtMap = tmpMonsterHurtMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

    fun getMonsterHurt(times: Int): Int {
        val proto = this.monsterHurtMap[times]

        if (proto != null) {
            return proto.hurt
        }

        val maxHurtProtoTMP = this.maxHurtProto
        if (maxHurtProtoTMP != null) {
            if (this.maxHurtProto != null && times > maxHurtProtoTMP.times) {
                return maxHurtProtoTMP.hurt
            }
        }
        val minHurtProtoTMP = this.minHurtProto
        if (minHurtProtoTMP != null) {
            if (this.minHurtProto != null && times < minHurtProtoTMP.times) {
                return minHurtProtoTMP.hurt
            }
        }
        return 0
    }
}