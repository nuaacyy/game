package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class UiConditionResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<UiConditionProto>
): Serializable

data class UiConditionProto(
    val id: Int,
    val check: String,
    val check2: String
): Serializable {
    var checkMap: Map<Int, List<Int>> = mapOf()
    var checkMap2: Map<Int, List<Int>> = mapOf()

}

class UiConditionProtoCache : ProtoCacheInit("uicondition.xml") {
    var uiConditionProtoMap: Map<Int, UiConditionProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<UiConditionResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as UiConditionResult

        val tmpUiConditionProtoMap: HashMap<Int, UiConditionProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val checkMap = hashMapOf<Int, LinkedList<Int>>()
            val checks = stringsSplit(vo.check, "%")

            for (check in checks) {
                val c = stringsSplit(check, ":")

                if (c.size < 2) {
                    throw RuntimeException("uicondition配置有错${vo.check}")
                }

                val checkType =
                    strconvAtoi(c[0]) ?: throw RuntimeException("uiCondition.xml  ${vo.check} 转换整型有误1:${c[0]}")

                checkMap[checkType] = LinkedList()
                for (cc in c) {
                    val value = strconvAtoi(cc)
                    val tmpmap = checkMap[checkType]
                    if (value == null || tmpmap == null) {
                        throw RuntimeException("uiCondition.xml  ${vo.check} 转换整型有误2:$cc")
                    }


                    tmpmap.add(value)
                }

            }
            vo.checkMap = checkMap

            val checkMap2 = hashMapOf<Int, LinkedList<Int>>()
            if (vo.check2 != "0") {
                val checks2 = stringsSplit(vo.check2, "%")

                for (check2 in checks2) {
                    val c = stringsSplit(check2, ":")

                    if (c.size < 2) {
                        throw RuntimeException("uicondition配置有错${vo.check2}")
                    }

                    val checkType = strconvAtoi(c[0])
                        ?: throw RuntimeException("uiCondition.xml :: CheckMap2 :: ${vo.check2} 转换整型有误1:${c[0]}")

                    checkMap2[checkType] = LinkedList()
                    for (cc in c) {
                        val value = strconvAtoi(cc)
                        val tmpmap = checkMap2[checkType]
                        if (value == null || tmpmap == null) {
                            throw RuntimeException("uiCondition.xml :: CheckMap2 :: ${vo.check2} 转换整型有误2:$cc")
                        }

                        tmpmap.add(value)
                    }

                }
            }
            vo.checkMap2 = checkMap2

            tmpUiConditionProtoMap[vo.id] = vo
        }
        this.uiConditionProtoMap = tmpUiConditionProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}