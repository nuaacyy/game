package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.*
import java.io.Serializable
import java.util.*

data class QuestResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<QuestProto>
) : Serializable

data class QuestProto(
    val id: Int, // 唯一ID
    val type: Int,  // 唯一ID
    val severType: Int, // 任务类型(1-个人  2-世界)
    val getCon: String,  // 接受任务条件
    val completeCon: String,// 完成条件
    val cost: Int,// 完成任务消耗
    val reward: String, // 必给奖励
    val chooseRew: String,// 可选奖励
    val power: Int,// 实力
    val limitTime: Int // 任务持续时间 0表示不限时

) : Serializable {
    var getCondMap: Map<Int, List<Int>> = mapOf()//接受任务条件
    var completeCondMap: Map<Int, List<Int>> = mapOf()//完成条件集合
    var rewardResVo: List<ResVo> = listOf()   //任务完成必给奖励
}

class QuestProtoCache : ProtoCacheInit("quest.xml") {
    var questProtoMap: Map<Int, QuestProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<QuestResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as QuestResult

        val tmpQuestProtoMap: HashMap<Int, QuestProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val ressVo = resStringToResVoList(vo.reward)

            if (null == ressVo) {
                throw RuntimeException("QuestProtoCache中的reward字段解析出错 ${vo.id}")
            } else {
                vo.rewardResVo = ressVo
            }

            val completeCondMap = hashMapOf<Int, LinkedList<Int>>()

            val cc = stringsSplit(vo.completeCon, ":")

            if (cc.size != 2) {
                throw RuntimeException("QuestProtoCache中的completeCon字段解析出错${vo.id}")
            } else {
                val checkType = strconvAtoi(cc[0])
                    ?: throw RuntimeException("QuestProtoCache中的completeCon字段解析出错Id.${vo.id},CompleteCond.${vo.completeCon}")
                val completeCondMapcheckType = completeCondMap.getOrPut(checkType) {
                    LinkedList()
                }

                val checkValues = stringsSplit(cc[1], "_")

                for (checkValue in checkValues) {
                    val value = strconvAtoi(checkValue)
                        ?: throw RuntimeException("QuestProtoCache中的completeCon字段解析出错${vo.completeCon}")
                    completeCondMapcheckType.add(value)
                }
            }
            if (completeCondMap.size != 1) {
                throw RuntimeException("QuestProtoCache中的 completeCon 配置了多个完成条件，目前只允许一个")
            }
            vo.completeCondMap = completeCondMap

            val getCondMap = hashMapOf<Int, LinkedList<Int>>()
            if (vo.getCon != "0") {
                val getTaskConditionStrings = stringsSplit(vo.getCon, "%")

                for (GetTaskConditionVo in getTaskConditionStrings) {
                    val ccStrs = stringsSplit(GetTaskConditionVo, ":")
                    if (ccStrs.size != 2) {
                        throw RuntimeException("QuestProtoCache中的getCon字段解析出错")

                    } else {
                        val checkType = strconvAtoi(ccStrs[0])
                            ?: throw RuntimeException("QuestProtoCache中的getCon字段解析出错1:Id.${vo.id},GetCond.${vo.getCon}")

                        val getCondMapcheckType = getCondMap.getOrPut(checkType) {
                            LinkedList()
                        }
                        val checkValues = stringsSplit(ccStrs[1], "_")

                        for (checkValue in checkValues) {
                            val value = strconvAtoi(checkValue)
                                ?: throw RuntimeException("QuestProtoCache中的getCon字段解析出错2:Id.${vo.id},GetCond.${vo.getCon}")

                            getCondMapcheckType.add(value)
                        }
                    }
                }
            }
            vo.getCondMap = getCondMap

            tmpQuestProtoMap[vo.id] = vo
        }
        this.questProtoMap = tmpQuestProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        // 执行一次任务常量的初始化
        initTaskCheck()

        //验证接受任务的条件
        for ((_, vQuest) in this.questProtoMap) {

            for ((kGetCond, vGetCond) in vQuest.getCondMap) {
                if (kGetCond != com.point18.slg2d.common.constg.GET_NEW_QUEST_FOR_FINISH_QUEST) {
                    continue
                }

                for (taskId in vGetCond) {
                    this.findSpecTaskProto(taskId) ?: throw RuntimeException("quest.xml getCon 中有个任务找不到:$taskId")

                }
            }

            for ((kCompCond, vCompCond) in vQuest.completeCondMap) {
                if (taskCheckWhiteList[kCompCond] == null) {
                    throw RuntimeException("任务检测ID:${kCompCond}没有注册白名单~~~~")
                }

                if (taskCheckParameterNum[kCompCond] == null) {
                    throw RuntimeException("任务检测ID:${kCompCond}没有注册长度检测~~~~")
                }

                if (taskCheckParameterNum[kCompCond] != vCompCond.size) {
                    throw RuntimeException("任务检测ID:${kCompCond}注册的长度为${taskCheckParameterNum[kCompCond]}与策划配置的${vCompCond.size}不一致")
                }

                if (kCompCond == CHECK_RESEARCH) {
                    if (vCompCond.size != 1) {
                        throw RuntimeException("任务检测ID:66类型的参数长度不为1")
                    }
                    if (pcs.researchCache.researchProtoMap[vCompCond[0]] == null) {
                        throw RuntimeException("任务ID:${vQuest.id}的任务检测为科技检测,但是参数${vCompCond[0]}在科技表中通过ID找不到行")
                    }
                } else if (kCompCond == CHECK_BUILD_RES_SPEED) {
                    if (vCompCond.size != 2) {
                        throw RuntimeException("任务检测ID:19类型的参数长度不为2")
                    }

                    if (vCompCond[0] != 0 &&
                        vCompCond[0] != WoodCost &&
                        vCompCond[0] != IronCost &&
                        vCompCond[0] != FoodCost &&
                        vCompCond[0] != StoneCost &&
                        vCompCond[0] != CoinCost) {
                        throw RuntimeException("任务ID:${vQuest.id}的任务检测为资源产量加速,但是参数${vCompCond[0]}并不是0也不是科技类型")
                    }
                }
            }
        }
    }

    /**
     * 找到特定任务模板
     */
    fun findSpecTaskProto(questProtoId: Int): QuestProto? {
        return this.questProtoMap[questProtoId]
    }

}