package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AllianceFlagResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AllianceFlagProto>
) : Serializable

data class AllianceFlagProto(
    val id: Int,
    val type: Int,
    val resource: String,
    val sequenceNumber: Int
) : Serializable

/**
联盟旗帜模版
 */
class AllianceFlagProtoCache : ProtoCacheInit("cripsFlag.xml") {
    var allianceFlagMap: Map<Int, Map<Int, AllianceFlagProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AllianceFlagResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AllianceFlagResult

        val tmpAllianceFlagMap: HashMap<Int, HashMap<Int, AllianceFlagProto>> = hashMapOf()
        val idCheckMap = hashMapOf<Int, Int>()
        for (vo in readXmlResult.l) {
            if (idCheckMap.containsKey(vo.id)) {
                throw RuntimeException("cripsFlag.xml :: id[${vo.id}]重复")
            }
            idCheckMap[vo.id] = 1

            val flags = tmpAllianceFlagMap.getOrPut(vo.type) {
                hashMapOf()
            }
            flags[vo.id] = vo
        }
        this.allianceFlagMap = tmpAllianceFlagMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

    fun existFlag(t: Int, id: Int): Boolean {
        val tmp = this.allianceFlagMap[t]
        if (tmp == null) {
            return false
        } else {
            if (tmp[id] == null) {
                return false
            }
        }
        return true
    }

}





