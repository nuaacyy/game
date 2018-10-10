package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class KingAwardProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<KingAwardProto>
): Serializable

data class KingAwardProto(
    val id: Int, // 编号
    val name: String, //名称
    val limit: Int,//人数限制
    val award: String //奖励
): Serializable {
    var awardList: List<ResVo> = listOf()

}

class KingAwardProtoCache : ProtoCacheInit("kingAward.xml") {
    var kingAwardProtoMap: Map<Int, KingAwardProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<KingAwardProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as KingAwardProtoResult

        val tmpKingAwardProtoMap: HashMap<Int, KingAwardProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpKingAwardProtoMap[vo.id] = vo

            val voaward = resStringToResVoList(vo.award) ?: throw RuntimeException("KingAward.xml 解析award字段出错:${vo.id}.")

            vo.awardList = voaward
        }
        this.kingAwardProtoMap = tmpKingAwardProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}