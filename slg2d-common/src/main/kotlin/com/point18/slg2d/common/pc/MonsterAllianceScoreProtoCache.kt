package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class MonsterAllianceScoreProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MonsterAllianceScoreProto>
): Serializable

data class MonsterAllianceScoreProto(
    val id: Int, // 编号
    val score: Int, // 积分	积分档次
    val monsterId: String  // 开启boss	可以召唤的boss
): Serializable {
    var monsterIdMap: List<MonsterIdVo> = listOf() // key : bossId  value : BossNum

}

data class MonsterIdVo(
    var bossId: Int,
    var bossNum: Int
)

class MonsterAllianceScoreProtoCache : ProtoCacheInit("monsterAllianceScore.xml") {
    var monsterAllianceScoreProtoMap: Map<Int, MonsterAllianceScoreProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MonsterAllianceScoreProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MonsterAllianceScoreProtoResult

        val tmpMonsterAllianceScoreProtoMap: HashMap<Int, MonsterAllianceScoreProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val monsterIdMap = LinkedList<MonsterIdVo>()
            val monsts = stringsSplit(vo.monsterId, "|")

            for (m in monsts) {
                val mm = stringsSplit(m, ";")

                if (mm.size != 2) {
                    throw RuntimeException("monsterAllianceScore中的MonsterId字段解析出错:${vo.id}")
                }
                val mId = strconvAtoi(mm[0])
                val mNum = strconvAtoi(mm[1])
                if (mId == null || mNum == null) {
                    throw RuntimeException("monsterAllianceScore中的MonsterId字段解析出错:${vo.id}")
                }
                monsterIdMap.add(MonsterIdVo(mId, mNum))
            }
            vo.monsterIdMap = monsterIdMap
            tmpMonsterAllianceScoreProtoMap[vo.score] = vo
        }
        this.monsterAllianceScoreProtoMap = tmpMonsterAllianceScoreProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}