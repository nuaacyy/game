package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AllianceTreAwardResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AllianceTreAwardProto>
): Serializable

data class AllianceTreAwardProto(
    val id: Int, // 唯一ID
    val basicRewardType: Int, // 基础奖励唯一ID
    val castleLevel: Int, // 主堡等级
    val dropAward: String // 掉落内容(奖励格式)

) : Serializable{
    var dropAwardMap: List<ResVo> = listOf()// 掉落内容(奖励格式)
}

class AllianceTreAwardProtoCache : ProtoCacheInit("allianceTreAward.xml") {
    var allianceTreAwardProtoMap: Map<Int, Map<Int, AllianceTreAwardProto>> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AllianceTreAwardResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AllianceTreAwardResult

        val tmpAllianceTreAwardProtoMap: HashMap<Int, HashMap<Int, AllianceTreAwardProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val resStringToResVoFunRet = resStringToResVoList(vo.dropAward)
                ?: throw RuntimeException("allianceTreAward.xml :: DropAward ${vo.dropAward} 奖励格式不合法.")
            vo.dropAwardMap = resStringToResVoFunRet

            val tmp = tmpAllianceTreAwardProtoMap.getOrPut(vo.basicRewardType) {
                hashMapOf()
            }
            tmp[vo.castleLevel] = vo
        }
        this.allianceTreAwardProtoMap = tmpAllianceTreAwardProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

}