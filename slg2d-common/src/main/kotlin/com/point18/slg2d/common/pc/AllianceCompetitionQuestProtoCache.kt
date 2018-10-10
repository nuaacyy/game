package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AllianceCompetitionQuestResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AllianceCompetitionQuestProto>
) : Serializable

data class AllianceCompetitionQuestProto(
    val id: Int,
    val name: String,
    val questId: Int, // 调用的任务ID外键
    val randomWeight: Int, // 任务刷新的权重
    val describe: String,
    val reward: Int, // 获得积分奖励
    val icon: Int

) : Serializable {
    // 完成条件集合
    var minOdds: Int = 0// 随到这个任务的最小值
    var maxOdds: Int = 0// 随到这个任务的最大值
}

class AllianceCompetitionQuestProtoCache : ProtoCacheInit("allianceCompetitionQuest.xml") {
    var protoMapById: Map<Int, AllianceCompetitionQuestProto> = mapOf()// id为主键
    var protoMapByQuestId: Map<Int, AllianceCompetitionQuestProto> = mapOf()// questId为主键
    var allOdds: Int = 0  // 随任务的总权值

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AllianceCompetitionQuestResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AllianceCompetitionQuestResult

        this.allOdds = 0

        val tmpProtoMapById: HashMap<Int, AllianceCompetitionQuestProto> = hashMapOf()
        val tmpProtoMapByQuestId: HashMap<Int, AllianceCompetitionQuestProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpProtoMapById.containsKey(vo.id)) {
                throw RuntimeException("allianceCompetitionQuest.xml :: id[${vo.id}]重复")
            }

            vo.minOdds = this.allOdds
            vo.maxOdds = this.allOdds + vo.randomWeight - 1
            this.allOdds += vo.randomWeight
            tmpProtoMapById[vo.id] = vo
            tmpProtoMapByQuestId[vo.questId] = vo
        }
        this.protoMapById = tmpProtoMapById
        this.protoMapByQuestId = tmpProtoMapByQuestId
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        if (this.protoMapById.size < pcs.basicProtoCache.numberOfInitialTasks) {
            throw RuntimeException("联盟总动员任务表的数量:${this.protoMapById}小于需求量:${pcs.basicProtoCache.numberOfInitialTasks}")
        }

        for ((id, task) in this.protoMapById) {
            val quest = pcs.questCache.findSpecTaskProto(task.questId)
            if (quest == null) {
                throw RuntimeException("联盟总动员任务表的任务ID:${task.questId}在quest表里找不到,配置行ID为:$id")
            }
        }
    }

    fun refreshAllianceCompetition(
        refNum: Int,
        nowQuestId: MutableMap<Int, Int>
    ): MutableMap<Int, AllianceCompetitionQuestProto> {
        val nowLuckQuest = hashMapOf<Int, AllianceCompetitionQuestProto>()
        var maxForNum = 0 // 保险丝

        while (true) {

            if (nowLuckQuest.size >= refNum) {
                break
            }
            if (maxForNum >= 10000) {
                com.point18.slg2d.common.commonfunc.normalLog.error("刷联盟总动员任务的时候刷了1W次没找到合适的任务:", refNum)
                break
            }
            maxForNum += 1
            val odds = com.point18.slg2d.common.commonfunc.getRandInt(this.allOdds)
            for ((_, proto) in this.protoMapById) {

                if (odds >= proto.minOdds && odds <= proto.maxOdds) {
                    // 随中了,检测这个任务是否可用
                    val nowQuestIdid = nowQuestId[proto.id] // 检测是否帮派当前已经有这个任务了

                    if (nowQuestIdid != null) {
                        break
                    }
                    val nowLuckQuestid = nowLuckQuest[proto.id] // 检测本次进这个方法是否已经随到了这个任务了

                    if (nowLuckQuestid != null) {
                        break
                    }
                    nowLuckQuest[proto.id] = proto
                }
            }
        }

        return nowLuckQuest
    }
}





