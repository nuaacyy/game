package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class LanguageZhCNProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<LanguageZhCNProto>
): Serializable

data class LanguageZhCNProto(
    val key: String,  // 编号
    val translate: String // 内容
): Serializable

class LanguageZhCNProtoCache : ILanProtoCache, ProtoCacheInit("LanguagePack_zh_CN.xml") {
    override var lanMap: Map<String, String> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<LanguageZhCNProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as LanguageZhCNProtoResult

        val tmpLanMap: HashMap<String, String> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpLanMap.containsKey(vo.key)) {
                throw RuntimeException("LanguagePack_zh_CN.xml :: 重复的key:${vo.key} ")
            }
            tmpLanMap[vo.key] = vo.translate
        }

        lanMap = tmpLanMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {}

}