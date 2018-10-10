package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class KingBuffProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<KingBuffProto>
): Serializable

data class KingBuffProto(
    val id: Int, // 编号
    val buffType: Int, //对应BuffBasic中的Buff
    val buff: Int, //通用效果
    val buffRank: String // 前三效果
) : Serializable{
    var buffRankList: List<Int> = listOf()
}

class KingBuffProtoCache : ProtoCacheInit("kingBuff.xml") {
    var kingBuffProtoMap: Map<Int, KingBuffProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<KingBuffProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as KingBuffProtoResult

        val tmpKingBuffProtoMap: HashMap<Int, KingBuffProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpKingBuffProtoMap[vo.id] = vo
            val split = stringsSplit(vo.buffRank, "|")
            if (split.size != 3) {
                throw RuntimeException("KingBuff.xml buffRank字段解析出来长度不等于3")
            }
            val buffIds = LinkedList<Int>()
            for (s in split) {
                val i = strconvAtoi(s)
                if (i == null) {
                    throw RuntimeException("KingBuff.xml buffRank字段解析出错")
                }
                buffIds.add(i)
            }
            vo.buffRankList = buffIds
        }
        this.kingBuffProtoMap = tmpKingBuffProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, kingBuff) in pcs.kingBuffProtoCache.kingBuffProtoMap) {
            pcs.buffProtoCache.protoMap[kingBuff.buffType]
                ?: throw RuntimeException("KingBuff.xml Buff表中找不到对应的君主Buff:${kingBuff.buffType}")
            pcs.buffBasicProtoCache.protoMap[kingBuff.buff]
                ?: throw RuntimeException("KingBuff.xml BuffBasic表中找不到对应的君主Buff:${kingBuff.buff}")
            pcs.buffBasicProtoCache.protoMap[kingBuff.buffRankList[0]]
                ?: throw RuntimeException("KingBuff.xml BuffBasic表中找不到对应的君主RankBuff:${kingBuff.buffRankList[0]}")
            pcs.buffBasicProtoCache.protoMap[kingBuff.buffRankList[1]]
                ?: throw RuntimeException("KingBuff.xml BuffBasic表中找不到对应的君主RankBuff:${kingBuff.buffRankList[1]}")
            pcs.buffBasicProtoCache.protoMap[kingBuff.buffRankList[2]]
                ?: throw RuntimeException("KingBuff.xml BuffBasic表中找不到对应的君主RankBuff:${kingBuff.buffRankList[2]}")
        }
    }

}