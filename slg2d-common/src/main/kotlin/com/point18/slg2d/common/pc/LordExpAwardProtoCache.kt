package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class LordExpAwardResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<LordExpAwardProto>
): Serializable

data class LordExpAwardProto(
    val id: Int, //唯一ID
    val type: Int,  //类型
    val name: String,  //名字
    val lordLv: Int,  //君主等级
    val lordExp: Int  //奖励君主经验
): Serializable

class LordExpAwardProtoCache : ProtoCacheInit("lordExpAward.xml") {
    var lordExpAwardMap: Map<Int, LordExpAwardProto> = mapOf()
    var lordMinExpAwardMap: Map<Int, LordExpAwardProto> = mapOf()
    var lordMaxExpAwardMap: Map<Int, LordExpAwardProto> = mapOf()
    var lordExpAwardTypeMap: Map<Int, Map<Int, LordExpAwardProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<LordExpAwardResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as LordExpAwardResult

        val tmpLordExpAwardMap: HashMap<Int, LordExpAwardProto> = hashMapOf()
        val tmpLordMinExpAwardMap: HashMap<Int, LordExpAwardProto> = hashMapOf()
        val tmpLordMaxExpAwardMap: HashMap<Int, LordExpAwardProto> = hashMapOf()
        val tmpLordExpAwardTypeMap: HashMap<Int, HashMap<Int, LordExpAwardProto>> = hashMapOf()
        for (proto in readXmlResult.l) {
            tmpLordExpAwardMap[proto.id] = proto
            val lordExpAwardTypeMaptype = tmpLordExpAwardTypeMap.getOrPut(proto.type) { hashMapOf() }

            lordExpAwardTypeMaptype[proto.lordLv] = proto
            val minProto = this.lordMinExpAwardMap[proto.type]

            if (null == minProto || minProto.lordLv > proto.lordLv) {
                tmpLordMinExpAwardMap[proto.type] = proto
            }
            val maxProto = this.lordMaxExpAwardMap[proto.type]

            if (maxProto == null || maxProto.lordLv < proto.lordLv) {
                tmpLordMaxExpAwardMap[proto.type] = proto
            }
        }
        this.lordExpAwardMap = tmpLordExpAwardMap
        this.lordExpAwardTypeMap = tmpLordExpAwardTypeMap
        this.lordMinExpAwardMap = tmpLordMinExpAwardMap
        this.lordMaxExpAwardMap = tmpLordMaxExpAwardMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        if (this.lordMinExpAwardMap.isEmpty() || (this.lordMaxExpAwardMap.size) == 0) {
            throw RuntimeException("lordExpAward.xml :经验配置不存在")
        }
    }

    fun getProtoByLv(protoType: Int, kingLv: Int): LordExpAwardProto? {
        val awardMap = this.lordExpAwardTypeMap[protoType] ?: return null

        val proto = awardMap[kingLv]

        if (proto != null) {
            return proto
        }
        val minProto = this.lordMinExpAwardMap[protoType]

        if (minProto != null) {
            if (kingLv < minProto.lordLv) {
                return minProto
            }
        }
        val maxProto = this.lordMaxExpAwardMap[protoType]
        if (maxProto != null) {
            if (kingLv > maxProto.lordLv) {
                return maxProto
            }
        }
        return null
    }

}