package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class SkinAttributeProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<SkinAttributeProto>
): Serializable

data class SkinAttributeProto(
    val id: Int,   // 编号
    val type: Int,  // 类型
    val star: Int,  // 星数
    val props: Int,  // 消耗道具id
    val propsNum: Int,  // 数量
    val attribute: String   // 消耗
) : Serializable{
    var effectMap: Map<Int, Int> = mapOf()//科技效果ID：属性	普通科技效果值

}

class SkinAttributeProtoCache : ProtoCacheInit("skinAttribute.xml") {
    var skinAttributeProtoMap: Map<Int, SkinAttributeProto> = mapOf()
    var skinAttributeProtoMapBySkinType: Map<Int, List<SkinAttributeProto>> = mapOf()
    var skinAttributeProtoMapBySkinTypeAndStar: Map<Int, Map<Int, SkinAttributeProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<SkinAttributeProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as SkinAttributeProtoResult

        val tmpSkinAttributeProtoMap: HashMap<Int, SkinAttributeProto> = hashMapOf()
        val tmpSkinAttributeProtoMapBySkinType: HashMap<Int, LinkedList<SkinAttributeProto>> = hashMapOf()
        val tmpSkinAttributeProtoMapBySkinTypeAndStar: HashMap<Int, HashMap<Int, SkinAttributeProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val effectMap = hashMapOf<Int, Int>()
            if (vo.attribute != "0") {
                val attributeStr = stringsSplit(vo.attribute, "|")

                for (element in attributeStr) {
                    val b = stringsSplit(element, ";")

                    if (b.size != 2) {
                        throw RuntimeException("skinAttribute.xml中的Attribute解析出错1:${vo.id}")
                    }
                    val effType =
                        strconvAtoi(b[0]) ?: throw RuntimeException("skinAttribute.xml中的Attribute解析出错2:${vo.id}")

                    val effValue =
                        strconvAtoi(b[1]) ?: throw RuntimeException("skinAttribute.xml中的Attribute解析出错3:${vo.id}")

                    effectMap[effType] = effValue
                }
            }
            vo.effectMap = effectMap

            tmpSkinAttributeProtoMap[vo.id] = vo

            val skinAttributeProtoMapBySkinTypeskinType = tmpSkinAttributeProtoMapBySkinType.getOrPut(vo.type) {
                LinkedList()
            }

            skinAttributeProtoMapBySkinTypeskinType.add(vo)

            val skinAttributeProtoMapBySkinTypeAndStartype =
                tmpSkinAttributeProtoMapBySkinTypeAndStar.getOrPut(vo.type) {
                    hashMapOf()
                }
            skinAttributeProtoMapBySkinTypeAndStartype[vo.star] = vo
        }
        this.skinAttributeProtoMap = tmpSkinAttributeProtoMap
        this.skinAttributeProtoMapBySkinType = tmpSkinAttributeProtoMapBySkinType
        this.skinAttributeProtoMapBySkinTypeAndStar = tmpSkinAttributeProtoMapBySkinTypeAndStar
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}