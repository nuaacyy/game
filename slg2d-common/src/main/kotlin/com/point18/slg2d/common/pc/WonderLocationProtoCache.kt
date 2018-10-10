package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.*
import java.io.Serializable
import java.util.*

data class WonderLocationResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<WonderLocationProto>
) : Serializable

data class WonderLocationProto(
    val id: Int, // 唯一id
    val wondersType: Int, // 奇观类型
    val coordinateX: Int, // 奇观左上角坐标X
    val coordinateY: Int, // 奇观左上角坐标Y
    val incidenceMidMonster: Int, //黑土地魔物刷新
    val warTime: String, //争夺时间
    val buff: String, //buff效果
    val reward: String // 奇观排名奖励

) : Serializable {
    var rankPrizeMap: Map<String, EventPrizeVo> = mapOf()
    var warTimeMap: Map<Int, Int> = mapOf()
    var buffMap: Map<Int, Int> = mapOf()

    // 一开始就把这些加载好.方便使用  ,该奇观的各层的数据,************要注意的是,并没有做删选.用的时候需要注意哦  同心正方形,外层的未过滤内层的,有利有弊,用的时候注意了*************
    var areaMap: Map<Int, Map<Int, Int>> = mapOf()
    var blackMap: Map<Int, Map<Int, Int>> = mapOf()
    var midMap: Map<Int, Map<Int, Int>> = mapOf()
    var baseMap: Map<Int, Map<Int, Int>> = mapOf()
    //只有黑土地的区域
    var onlyBlackList: Array<Pair<Int, Int>> = arrayOf()

    // 获取奇观中心坐标
    data class WonderCenterPos(
        var x: Int,
        var y: Int
    )

    fun getCenterPos(): WonderCenterPos {
        val wonderProto = pcs.wonderProtoCache.wonderProtoMap[this.wondersType]
            ?: throw RuntimeException("  function :: getCenterPos   wonderProto == null err")
        return WonderCenterPos(
            this.coordinateX + wonderProto.incidenceBigX / 2 - 1,
            this.coordinateY + wonderProto.incidenceBigY / 2 - 1
        )
    }
}

class WonderLocationProtoCache : ProtoCacheInit("wonderLocation.xml") {
    var wonderLocationProtoMap: Map<Int, WonderLocationProto> = mapOf()
    var wonderLocationProtoList: List<WonderLocationProto> = listOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<WonderLocationResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as WonderLocationResult

        val tmpWonderLocationProtoMap: HashMap<Int, WonderLocationProto> = hashMapOf()
        val tmpWonderLocationProtoList: LinkedList<WonderLocationProto> = LinkedList()
        for (vo in readXmlResult.l) {
            val existVo = tmpWonderLocationProtoMap[vo.id]

            if (existVo != null) {
                throw RuntimeException("wonderLocation模板Id存在重复:${tmpWonderLocationProtoMap[vo.id]}")
            }

            val prizeMap = hashMapOf<String, EventPrizeVo>()
            if ("0" != vo.reward) {
                val prizes = stringsSplit(vo.reward, "%")
                for (prize in prizes) {
                    val prizeInfo = stringsSplit(prize, ":")
                    if (prizeInfo.size != 3) {
                        throw RuntimeException("解析 wonderLocation.xml的表字段reward时冒号分割长度不为3:$prize")
                    }

                    val rans = stringsSplit(prizeInfo[0], "_")
                    if (rans.size != 2) {
                        throw RuntimeException("解析 wonderLocation.xml的表字段reward时排名长度不为2:${prizeInfo[0]}")
                    }

                    val rewardBags = LinkedList<Int>()
                    val eventRewardDropBagId = stringsSplit(prizeInfo[1], "_")

                    for (r in eventRewardDropBagId) {
                        val rId = strconvAtoi(r)
                            ?: throw RuntimeException("解析 eventInformation.xml的表字段eventRewardDropBagId error")
                        rewardBags.add(rId)
                    }

                    prizeMap[prizeInfo[0]] = EventPrizeVo(rewardBags, prizeInfo[2], 0)
                }
            }
            vo.rankPrizeMap = prizeMap

            tmpWonderLocationProtoMap[vo.id] = vo
            tmpWonderLocationProtoList.add(vo)

            val vowarTimeMap =
                parseIntMap(vo.warTime) ?: throw RuntimeException("wonderLocation模板warTime配置错误:${vo.warTime}")

            vo.warTimeMap = vowarTimeMap
            val vobuffMap = parseIntMap(vo.buff) ?: throw RuntimeException("wonderLocation模板buff配置错误:${vo.buff}")

            vo.buffMap = vobuffMap
        }
        this.wonderLocationProtoMap = tmpWonderLocationProtoMap
        this.wonderLocationProtoList = tmpWonderLocationProtoList
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in this.wonderLocationProtoMap) {
            pcs.wonderProtoCache.wonderProtoMap[proto.wondersType]
                ?: throw RuntimeException("wonderLocation表中的type在wonder里找不到:${proto.wondersType}.")

        }


        for ((_, proto) in this.wonderLocationProtoMap) {
            val wonderProto = pcs.wonderProtoCache.wonderProtoMap[proto.wondersType]
                ?: throw RuntimeException("wonderLocation表中的type在wonderProtoMap里找不到:${proto.wondersType}.")
            val areaMap = hashMapOf<Int, HashMap<Int, Int>>()
            for (i in proto.coordinateX..(proto.coordinateX + wonderProto.incidenceBigX - 1)) {
                for (j in proto.coordinateY..(proto.coordinateY + wonderProto.incidenceBigY - 1)) {
                    val tmp2 = areaMap.getOrPut(i) {
                        hashMapOf()
                    }
                    tmp2[j] = 1
                }
            }
            proto.areaMap = areaMap

            val blackMap = hashMapOf<Int, HashMap<Int, Int>>()
            val blackXDif = (((wonderProto.incidenceBigX - wonderProto.incidenceMidX)).toDouble() / 2).toInt()
            val blackYDif = ((wonderProto.incidenceBigY - wonderProto.incidenceMidY).toDouble() / 2).toInt()
            for (i in proto.coordinateX + blackXDif..(proto.coordinateX + blackXDif + wonderProto.incidenceMidX - 1)) {
                for (j in proto.coordinateY + blackYDif..(proto.coordinateY + blackYDif + wonderProto.incidenceMidY - 1)) {
                    val tmp4 = blackMap.getOrPut(i) {
                        hashMapOf()
                    }
                    tmp4[j] = 1
                }
            }
            proto.blackMap = blackMap

            val midMap = hashMapOf<Int, HashMap<Int, Int>>()
            val midXDif = ((wonderProto.incidenceBigX - wonderProto.incidenceSmallX).toDouble() / 2).toInt()
            val midYDif = ((wonderProto.incidenceBigY - wonderProto.incidenceSmallY).toDouble() / 2).toInt()
            for (i in proto.coordinateX + midXDif..(proto.coordinateX + midXDif + wonderProto.incidenceSmallX - 1)) {
                for (j in proto.coordinateY + midYDif..(proto.coordinateY + midYDif + wonderProto.incidenceSmallY - 1)) {
                    val tmp5 = midMap.getOrPut(i) {
                        hashMapOf()
                    }
                    tmp5[j] = 1
                }
            }
            proto.midMap = midMap

            val baseMap = hashMapOf<Int, HashMap<Int, Int>>()
            val baseXDif = ((wonderProto.incidenceBigX - wonderProto.incidenceBaseX).toDouble() / 2).toInt()
            val baseYDif = ((wonderProto.incidenceBigY - wonderProto.incidenceBaseY).toDouble() / 2).toInt()
            for (i in proto.coordinateX + baseXDif..(proto.coordinateX + baseXDif + wonderProto.incidenceBaseX - 1)) {

                for (j in proto.coordinateY + baseYDif..(proto.coordinateY + baseYDif + wonderProto.incidenceBaseY - 1)) {
                    val tmp5 = baseMap.getOrPut(i) {
                        hashMapOf()
                    }
                    tmp5[j] = 1
                }
            }
            proto.baseMap = baseMap

            val onlyBlackList = LinkedList<Pair<Int, Int>>()
            for ((x, yMap) in proto.blackMap) {
                for ((y, _) in yMap) {
                    if (proto.midMap[x]?.get(y) != null) {
                        continue
                    }
                    onlyBlackList.add(Pair(x, y))
                }
            }
            proto.onlyBlackList = onlyBlackList.toTypedArray()
        }


        for ((_, proto) in this.wonderLocationProtoMap) {

            if (proto.wondersType == 1) {
                // 大奇观不会有boss
                continue
            }
            pcs.monsterWorldProtoCache.findWorldBossByCityId(proto.id)
                ?: throw RuntimeException("有个奇观找不到世界boss的刷新:${proto.id}.")

        }
    }

    // 查询某XY坐标在奇观中的哪个部位,并且返回该奇观类型
    data class FindInWonderTypeFunRet(
        var wonderLocationProto: WonderLocationProto?,
        var int: Int
    )

    fun findInWonderType(x: Int, y: Int): FindInWonderTypeFunRet {
        var ex: Boolean
        for ((_, proto) in this.wonderLocationProtoMap) {

            var tmp1 = proto.baseMap[x]
            ex = if (tmp1 == null) {
                false
            } else {
                val tmp2 = tmp1[y]
                tmp2 != null
            }
            if (ex) {
                return FindInWonderTypeFunRet(proto, WONDER_BASE)
            }

            tmp1 = proto.midMap[x]
            ex = if (tmp1 == null) {
                false
            } else {
                val tmp2 = tmp1[y]
                tmp2 != null
            }
            if (ex) {
                return FindInWonderTypeFunRet(proto, WONDER_MID)
            }

            tmp1 = proto.blackMap[x]
            ex = if (tmp1 == null) {
                false
            } else {
                val tmp2 = tmp1[y]
                tmp2 != null
            }
            if (ex) {
                return FindInWonderTypeFunRet(proto, WONDER_BLACK)
            }

            tmp1 = proto.areaMap[x]
            ex = if (tmp1 == null) {
                false
            } else {
                val tmp2 = tmp1[y]
                tmp2 != null
            }
            if (ex) {
                return FindInWonderTypeFunRet(proto, WONDER_AREA)
            } else {
                continue
            }
        }

        return FindInWonderTypeFunRet(null, NORMAL_CELL)
    }

    fun isWonderMid(x: Int, y: Int): Boolean {
        for ((_, proto) in this.wonderLocationProtoMap) {
            val tmp3 = proto.midMap[x]
            var ex: Boolean
            ex = if (tmp3 == null) {
                false
            } else {
                val tmp4 = tmp3[y]
                tmp4 != null
            }


            if (ex) {
                return true
            }
        }

        return false
    }

    fun inWonderBlack(x: Int, y: Int): WonderLocationProto? {
        for ((_, proto) in this.wonderLocationProtoMap) {
            val tmp3 = proto.blackMap[x]
            var ex: Boolean
            ex = if (tmp3 == null) {
                false
            } else {
                val tmp4 = tmp3[y]
                tmp4 != null
            }


            if (ex) {
                return proto
            }
        }

        return null
    }

    // 奇观战开启前 战神庇护的持续时间(ms)
    fun startWonderProtectTime(): Long {
        return (pcs.basicProtoCache.wonderProtect[0][2] * 3600 * 1000).toLong()
    }

    // 奇观战结束后 战神庇护的持续时间(ms)
    fun finishWonderProtectTime(): Long {
        return (pcs.basicProtoCache.wonderProtect[1][2] * 3600 * 1000).toLong()
    }

    fun findRankRewardByRank(wonderProtoId: Int, rank: Int): EventPrizeVo? {

        val protoInfo = this.wonderLocationProtoMap[wonderProtoId]

        if (protoInfo != null) {

            for ((ranks, rankReward) in protoInfo.rankPrizeMap) {
                val rs = stringsSplit(ranks, "_")
                val minRand = (rs[0]).toInt()
                val maxRand = (rs[1]).toInt()

                if (rank in minRand..maxRand) {
                    // 这个排名的就是这一档奖励了
                    return rankReward
                }
            }
        }
        return null
    }

}
