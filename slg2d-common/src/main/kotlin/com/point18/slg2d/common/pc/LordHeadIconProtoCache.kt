package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

data class LordHeadIconProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<LordHeadIconProto>
): Serializable

data class LordHeadIconProto(
    val id: Int,       //模板ID
    val type: Int,   // 头像获得类型
    val name: String, // 头像名字
    val lordIconFrom: String   // 获得条件
) : Serializable{
    val activityOrHeroCheckMap: HashMap<Int, LinkedList<Int>> = hashMapOf()
}

class LordHeadIconProtoCache : ProtoCacheInit("lordHeadIcon.xml") {
    var iconTypeMap: Map<Int, List<LordHeadIconProto>> = mapOf() // 类型分类
    var lordHeadIconMap: Map<Int, LordHeadIconProto> = mapOf() // 按照id分

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<LordHeadIconProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as LordHeadIconProtoResult

        val tmpIconTypeMap: MutableMap<Int, LinkedList<LordHeadIconProto>> = hashMapOf() // 类型分类,送的,英雄获得,活动获得
        val tmpLordHeadIconMap: MutableMap<Int, LordHeadIconProto> = hashMapOf() // 按照id分
        for (eachRecord in readXmlResult.l) {

            if (eachRecord.lordIconFrom != "0") {
                val lordIconFromSpiltFirst = eachRecord.lordIconFrom.split("|")
                for (eachSpiltFirst in lordIconFromSpiltFirst) {
                    val lordIconFromSpiltSecond = eachSpiltFirst.split(";")
                    if (lordIconFromSpiltSecond.size != 2) {
                        throw  RuntimeException("lordHeadIcon.xml配置错误1，id：${eachRecord.id}")
                    }

                    val gainIconMethodType = lordIconFromSpiltSecond[0].toIntOrNull()
                    val gainIconMethodCondition = lordIconFromSpiltSecond[1].toIntOrNull()  // 英雄模板或者活动模板id
                    if (gainIconMethodType == null || gainIconMethodCondition == null) {
                        throw  RuntimeException("lordHeadIcon.xml配置错误2，id：${eachRecord.id}")
                    }
                    var nodeOfCheckMap = eachRecord.activityOrHeroCheckMap[gainIconMethodType]
                    if (nodeOfCheckMap == null) {
                        nodeOfCheckMap = LinkedList()
                        eachRecord.activityOrHeroCheckMap[gainIconMethodType] = nodeOfCheckMap
                    }

                    nodeOfCheckMap.add(gainIconMethodCondition)
                }
            }
            tmpLordHeadIconMap[eachRecord.id] = eachRecord

            // 下面进行类型的分类
            var typeMapNode = tmpIconTypeMap[eachRecord.type]
            if (typeMapNode == null) {
                typeMapNode = LinkedList()
                tmpIconTypeMap[eachRecord.type] = typeMapNode
            }
            typeMapNode.add(eachRecord)

        }
        lordHeadIconMap = tmpLordHeadIconMap
        iconTypeMap = tmpIconTypeMap

    }

    override fun postCheck(pcs: ProtoCacheStore) {
        val defaultIcon = pcs.basicProtoCache.defaultPhoto
        if (lordHeadIconMap[defaultIcon] == null) {
            throw RuntimeException("Basic.xml与lordHeadIcon.xml配置不匹配，没有这个头像模板id：$defaultIcon")
        }
    }

}