package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class UnitTaskResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<UnitTaskProto>
) : Serializable

data class UnitTaskProto(
    val id: Int,
    val unitId: Int,  // 章节ID
    val task: String, // 章节全部任务
    val reward: String, // 章节奖励
    val getCon: String // getTaskCondition
) : Serializable {
    var tasks: List<Int> = listOf()
    var rewardMap: List<ResVo> = listOf()
    var getCondMap: Map<Int, List<Int>> = mapOf()
}

class UnitTaskProtoCache : ProtoCacheInit("unitTask.xml") {
    var unitTaskProtoMap: Map<Int, UnitTaskProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<UnitTaskResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as UnitTaskResult

        val tmpUnitTaskProtoMap: HashMap<Int, UnitTaskProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val ressVo = resStringToResVoList(vo.reward)

            if (null == ressVo) {
                throw RuntimeException("unitTask中的Reward字段解析出错${vo.reward}.")
            } else {
                vo.rewardMap = ressVo
            }

            val taskList = LinkedList<Int>()
            val tasks = stringsSplit(vo.task, ":")

            for (t in tasks) {
                val tId = t.toInt()
                taskList.add(tId)
            }
            vo.tasks = taskList

            val getCondMap = hashMapOf<Int, LinkedList<Int>>()
            if (vo.getCon != "0") {
                val getTaskConditionStrings = stringsSplit(vo.getCon, "%")

                for (GetTaskConditionVo in getTaskConditionStrings) {
                    val cc = stringsSplit(GetTaskConditionVo, ":")

                    if (cc.size != 2) {
                        throw RuntimeException("unitTask中的getCon字段解析出错:Id:${vo.id}, GetCond:${vo.getCon}")
                    } else {
                        val checkType = strconvAtoi(cc[0])
                            ?: throw RuntimeException("unitTask中的getCon字段解析出错Id:${vo.id}, GetCond:${vo.getCon}")

                        val getCondMapcheckType = getCondMap.getOrPut(checkType) { LinkedList() }
                        val checkValues = stringsSplit(cc[1], "_")

                        for (checkValue in checkValues) {
                            val value = strconvAtoi(checkValue)
                                ?: throw RuntimeException("QuestProtoCache中的getCon字段解析出错Id:${vo.id}, GetCond:${vo.getCon}")

                            getCondMapcheckType.add(value)
                        }
                    }
                }
            }
            vo.getCondMap = getCondMap

            tmpUnitTaskProtoMap[vo.unitId] = vo
        }
        this.unitTaskProtoMap = tmpUnitTaskProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for ((_, tt) in this.unitTaskProtoMap) {

            for (t in tt.tasks) {
                val q = pcs.questCache.findSpecTaskProto(t) ?: throw RuntimeException("unitTask中的配置的章节任务:${t}在Quest表里找不到.")
                if (q.type != com.point18.slg2d.common.constg.TaskChapter) {
                    throw RuntimeException("unitTask中的配置的章节任务:${t}在Quest表里不是章节任务!!!")
                }

            }
        }
    }

}