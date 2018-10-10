package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class ResPointResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<ResPointProto>
) : Serializable

data class ResPointProto(
    val id: Int, // 唯一id
    val name: String, //名称
    val resType: Int, //资源类型
    val type: Int,  //分类类型，仅撒点用
    val level: Int, //资源点的等级
    val speed: Double, //资源点采集速度，按照秒计算，如有特殊类型采集小于1则计算结果需要取整，速度会受科研，天赋，buff道具影响
    val resTotal: Int, //资源点资源总量
    val reward: String,//采集奖励
    val time: Int    //消失时间(秒)
) : Serializable {
    var resDropList: List<ResRewardDrop> = listOf()

    fun genRewardByFarmNum(farmNum: Int): LinkedList<ResVo>? {
        for (i in this.resDropList.indices) {
            if (resDropList[i].needFarmNum < farmNum) {
                if (i < this.resDropList.size - 1) {
                    //需要量<采集量，且存在下一个
                    continue
                }
                //取当前
                return selectAllPropFromDrops(resDropList[i].drops)
            }

            if (i == 0) {
                return null
            }
            //取上一个
            return selectAllPropFromDrops(this.resDropList[i - 1].drops)
        }
        return null
    }
}

data class ResRewardDrop(
    var needFarmNum: Int,  //需要采集的数量
    var drops: List<Drop>
)

class ResPointProtoCache : ProtoCacheInit("resPoint.xml") {
    var resPointList: LinkedList<ResPointProto> = LinkedList()
    var resPointMap: Map<Int, ResPointProto> = mapOf()
    var resPointProtoMap: Map<Int, Map<Int, ResPointProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ResPointResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ResPointResult

        val tmpResPointMap: HashMap<Int, ResPointProto> = hashMapOf()
        val tmpResPointProtoMap: HashMap<Int, HashMap<Int, ResPointProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpResPointMap[vo.id] = vo
            val resPointProtoMapresType = tmpResPointProtoMap.getOrPut(vo.type) {
                hashMapOf()
            }
            val resPointProtoMapresTypelevel = resPointProtoMapresType[vo.level]

            if (resPointProtoMapresTypelevel != null) {
                throw RuntimeException("resPoint模板ResType + Lv存在重复:${vo.type},${vo.level}")
            }

            val resDropList = LinkedList<ResRewardDrop>()
            if (vo.reward != "0") {
                val rewardStrs = stringsSplit(vo.reward, "$")

                for (rewardStr in rewardStrs) {
                    val needNumStrs = stringsSplit(rewardStr, "_")
                    if (needNumStrs.size != 2) {
                        throw RuntimeException("resPoint模板Reward错误:${vo.reward}")
                    }
                    val needNum =
                        strconvAtoi(needNumStrs[0]) ?: throw RuntimeException("resPoint模板Reward错误:${vo.reward}")
                    val rewardDrop = ResRewardDrop(needNum, LinkedList())

                    val drops = dropStringToDrops(needNumStrs[1])
                        ?: throw RuntimeException("dropProps.xml :: 资源点掉落包配置错误:${vo.id}")
                    rewardDrop.drops = drops

                    resDropList.add(rewardDrop)
                }
            }

            resDropList.sortBy { it.needFarmNum }
            vo.resDropList = resDropList

            resPointProtoMapresType[vo.level] = vo
            this.resPointList.add(vo)
        }
        this.resPointMap = tmpResPointMap
        this.resPointProtoMap = tmpResPointProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, rMap) in pcs.resPointProtoCache.resPointProtoMap) {
            for ((_, proto) in rMap) {
                for (reward in proto.resDropList) {
                    for (drop in reward.drops) {
                        pcs.dropPropsProtoCache.dropPropsMap[drop.id]
                            ?: throw RuntimeException("resPoint模板Reward奖励掉落包不存在:${drop.id}")
                    }
                }
            }
        }
    }

}