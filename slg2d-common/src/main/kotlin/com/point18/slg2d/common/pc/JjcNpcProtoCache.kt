package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

data class JjcNpcResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<JjcNpcProto>
): Serializable

data class JjcNpcProto(
    val id: Int,
    val rank: String,  // 排名区间（闭区间）
    val npc: String,// 对应的守军
    val head: String // NPC守军头像

): Serializable {
    var rankList: List<Int> = listOf()                     // 解析后的排名区间
    var dropUnitTeamMap: List<DropUnitTeam> = listOf()
}

data class DropUnitTeam(
    var unitTeamId: Int,
    var odds: Int
)

class JjcNpcProtoCache : ProtoCacheInit("arenaNPC.xml") {
    var protoMap: Map<Int, JjcNpcProto> = mapOf()
    var jjcRobot: Map<Int, Int> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<JjcNpcResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as JjcNpcResult

        val tmpProtoMap: HashMap<Int, JjcNpcProto> = hashMapOf()
        val tmpJjcRobot: HashMap<Int, Int> = hashMapOf()
        for (vo in readXmlResult.l) {

            // 解析排名区间
            val ranklisttmp =
                parse2IntArray(vo.rank, ";") ?: throw RuntimeException("arenaNPC.xml :: rankMyself ERR:${vo.id}.")

            vo.rankList = ranklisttmp

            // 怪物
            val dropUnitTeamMap = LinkedList<DropUnitTeam>()
            val unitTeams = stringsSplit(vo.npc, "|")
            var odds = 0

            for (u in unitTeams) {
                val uu = stringsSplit(u, ";")
                // 第1个参数
                val unitTeamId =
                    strconvAtoi(uu[0]) ?: throw RuntimeException("arenaNPC 中的怪物 第1个参数 : ${uu[0]}无法转换为 INT ERR:$u.")

                // 第2个参数
                val unitTeamOdds =
                    strconvAtoi(uu[1]) ?: throw RuntimeException("arenaNPC 中的怪物 第2个参数 : ${uu[1]}无法转换为 INT ERR:$u.")

                vo.dropUnitTeamMap
                dropUnitTeamMap.add(DropUnitTeam(unitTeamId, unitTeamOdds))

                odds += unitTeamOdds
            }
            vo.dropUnitTeamMap = dropUnitTeamMap


            if (odds <= 0) {
                throw RuntimeException("arenaNPC 中的怪物 的随机权值为0")
            }
            tmpProtoMap[vo.id] = vo
        }
        this.protoMap = tmpProtoMap

        for (i in 1 until pcs.basicProtoCache.jjcInitNpcCount + 1) {
            tmpJjcRobot[i] = pcs.jjcNpcCache.fetchNpcTeamBagId(i)
        }
        this.jjcRobot = tmpJjcRobot
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        // 检查配置的竞技场NPC守军是否存在
        val rank = hashMapOf<Int, Boolean>()
        for ((_, vo) in this.protoMap) {

            for (unitTeam in vo.dropUnitTeamMap) {
                pcs.unitTeamProtoCache.protoMap[unitTeam.unitTeamId]
                    ?: throw RuntimeException("arenaNPC.xml ::npc ${unitTeam.unitTeamId} 找不到这个NPC守军怪物配置（unitTeam.xml）.")

            }

            // 排名区间是否合法

            if (vo.rankList[0] > vo.rankList[1]) {
                throw RuntimeException("arenaNPC.xml ::rankMyself  排名区间不合法（第1个不能大于第2个数字）${vo.id}.")
            }

            // 排名是否重复
            for (r in vo.rankList[0]..vo.rankList[1]) {
                val tmp2 = rank[r]

                if (tmp2 != null) {
                    throw RuntimeException("arenaNPC.xml ::rankMyself $tmp2 排名重复.")
                }

                rank[r] = true
            }
        }
    }

    fun fetchNpcTeamBagId(rank: Int): Int {
        for ((_, vo) in this.protoMap) {

            if (rank >= vo.rankList[0] && rank <= vo.rankList[1]) {
                var totalRate = 0

                for (dropInfo in vo.dropUnitTeamMap) {
                    totalRate += dropInfo.odds
                }
                val dropRand = com.point18.slg2d.common.commonfunc.getRandInt(totalRate)
                var tempRate = 0

                for (dropInfo in vo.dropUnitTeamMap) {

                    if (dropRand <= tempRate + dropInfo.odds) {
                        return dropInfo.unitTeamId
                    }
                    tempRate += dropInfo.odds
                }
            }
        }

        return 0
    }

    fun fetchProtoCacheByRank(rank: Int): JjcNpcProto? {
        for ((_, vo) in this.protoMap) {

            if (rank >= vo.rankList[0] && rank <= vo.rankList[1]) {
                return vo
            }
        }

        return null
    }

}