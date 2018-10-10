package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class BuffBasicResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<BuffBasicProto>
): Serializable

data class BuffBasicProto(
    val id: Int, //唯一ID
    val typeEffect: Int, //	该BUFF归属类型
    val time: Int, //单位为秒，配置0表示无限时间	持续时间
    val effect: String, //科技效果ID：属性	普通科技效果值
    val effectSpecial: Int //	特殊效果值
): Serializable {
    var effectMap: Map<Int, Int> = mapOf() //科技效果ID：属性	普通科技效果值

}

class BuffBasicProtoCache : ProtoCacheInit("buffBasic.xml") {
    var protoMap: Map<Int, BuffBasicProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<BuffBasicResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as BuffBasicResult

        val tmpProtoMap: HashMap<Int, BuffBasicProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpProtoMap.containsKey(vo.id)) {
                throw RuntimeException("buffBasic.xml :: id[${vo.id}]重复")
            }
            val effectMap = hashMapOf<Int, Int>()
            if (vo.effect != "0") {
                val upGradeEffectStr = stringsSplit(vo.effect, "|")
                for (element in upGradeEffectStr) {
                    val b = stringsSplit(element, ";")
                    if (b.size != 2) {
                        throw RuntimeException("buffBasic.xml中的Effec解析出错1:vo.Effect ${vo.effect}.")
                    }
                    val effType =
                        strconvAtoi(b[0]) ?: throw RuntimeException("buffBasic.xml中的Effec解析出错2:vo.Effect.${vo.effect}")
                    val effValue =
                        strconvAtoi(b[1]) ?: throw RuntimeException("buffBasic.xml中的Effec解析出错3:vo.Effect.${vo.effect}")
                    effectMap[effType] = effValue
                }
            }
            vo.effectMap = effectMap
            tmpProtoMap[vo.id] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

}