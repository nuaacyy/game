package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.constg.*
import java.io.Serializable
import java.util.*

data class JjcChallengeResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<JjcChallengeProto>
): Serializable

data class JjcChallengeProto(
    val id: Int,
    val rankMyself: String,  // 自己的排名区间
    val rank1: String,  // 对方的排名区间（低）
    val rank2: String,  // 对方的排名区间（中）
    val rank3: String  // 对方的排名区间（高）

): Serializable {
    var rankList: List<Int> = listOf()
    var rankList1: List<Int> = listOf()
    var rankList2: List<Int> = listOf()
    var rankList3: List<Int> = listOf()
}

class JjcChallengeProtoCache : ProtoCacheInit("arenaChallenge.xml") {
    var protoMap: Map<Int, JjcChallengeProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<JjcChallengeResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as JjcChallengeResult

        val tmpProtoMap: HashMap<Int, JjcChallengeProto> = hashMapOf()
        for (vo in readXmlResult.l) {

            // 自己的排名区间
            val rank = parse2IntArray(vo.rankMyself, ";")
                ?: throw RuntimeException("arenaChallenge.xml :: rankMyself ERR:${vo.id}.")
            vo.rankList = rank

            // 对方的排名区间（低）
            val rank1 =
                vo.rank1.toIntOrNull() ?: throw RuntimeException("arenaChallenge.xml :: rank1 ERR:${vo.id}.")

            // 对方的排名区间（中）
            val rank2 =
                vo.rank2.toIntOrNull() ?: throw RuntimeException("arenaChallenge.xml :: rank2 ERR:${vo.id}.")

            // 对方的排名区间（高）
            val rank3 =
                vo.rank3.toIntOrNull() ?: throw RuntimeException("arenaChallenge.xml :: rank3 ERR:${vo.id}.")

            // 都做成闭区间
            vo.rankList1 = listOf(-1, rank1)            // 区间:[myrank-(-1),myrank-rank1]
            vo.rankList2 = listOf(rank2, 1)             // 区间:[myrank-rank2,myrank-1]
            vo.rankList3 = listOf(rank3, rank2 + 1)     // 区间:[myrank-rank3,myrank-(rank2+1)]

            if (vo.rankList[0] - vo.rankList1[0] > JJC_RANK_CAN_BE_CHALLENGE){
                throw RuntimeException("arenaChallenge.xml :id 为${vo.id}的行可以挑战的排名超过$JJC_RANK_CAN_BE_CHALLENGE")
            }

            tmpProtoMap[vo.id] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        val first = this.protoMap[1]
        if (first == null ||
            first.rankList[0] != JJC_CHALLENGE_FIRST_XML_LV
        ){
            throw RuntimeException("arenaChallenge.xml 第一行配错了 ")
        }
    }

    // 根据自己的排名获取三个对手的排名
    data class FetchThreeRankFunRet(
        var rank1: Int, // 低
        var rank2: Int, // 中
        var rank3: Int  // 高
    )

    fun fetchThreeRank(rank: Int): FetchThreeRankFunRet {
        var myRank = rank

        var rank1 = 0
        var rank2 = 0
        var rank3 = 0

        // 前3名写死
        when (rank) {
            JJC_RANK_NO1 -> {
                return FetchThreeRankFunRet(JJC_RANK_NO4, JJC_RANK_NO3, JJC_RANK_NO2)
            }
            JJC_RANK_NO2 -> {
                return FetchThreeRankFunRet(JJC_RANK_NO4, JJC_RANK_NO3, JJC_RANK_NO1)
            }
            JJC_RANK_NO3 -> {
                return FetchThreeRankFunRet(JJC_RANK_NO4, JJC_RANK_NO2, JJC_RANK_NO1)
            }
            else -> {

                // 如果18000名以后,当做是18000名
                if (myRank > JJC_CHALLENGE_FIRST_XML_LV) {
                    myRank = JJC_CHALLENGE_FIRST_XML_LV
                }

                for ((_, vo) in this.protoMap) {

                    if (rank >= vo.rankList[0] && rank <= vo.rankList[1]) {
                        // 获取三个排名
                        rank1 = this.fetchRank(vo.rankList1, myRank)
                        rank2 = this.fetchRank(vo.rankList2, myRank)
                        rank3 = this.fetchRank(vo.rankList3, myRank)
                        break
                    }
                }
                return FetchThreeRankFunRet(rank1, rank2, rank3)
            }
        }
    }

    private fun fetchRank(rankList: List<Int>, myRank: Int): Int {
        val start = rankList[0]
        val end = rankList[1]
        val startRank = myRank - start
        val endRank = myRank - end
        val delta = getRandInt(endRank - startRank + 1)
        return (startRank + delta)
    }
}