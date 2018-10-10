package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class InstanceResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InstanceProto>
) : Serializable

data class InstanceProto(
    val id: Int,
    val unitId: Int, // 章节编号		1.第一章，2.第二章
    val pointId: Int, // 关卡编号
    val winStrength: Int,  // 成功体力消耗		6
    val loseStrength: Int,  // 失败体力消耗		0
    val monster: String, // 怪物配置		lineup表或者unitTeam表格，1:id%2:id%...
    val unitOpenCon: Int, // 关卡开启条件
    val randomReward: String, // 随机掉落		道具掉落包id:掉落数量:掉落几率，多个用%隔开 其中道具掉落包id对应dropProps表id字段 此处含义为每个随机，每个在10000内随机
    val heroExp: Int,  // 英雄经验		正常战斗时每个参战英雄获得的经验
    val heroExpProps: String, // 扫荡英雄经验		扫荡副本时，给经验道具
    val firstWinReward: String, // 首胜额外掉落		首胜获得的奖励固定
    val desc: String,
    val nextId: Int, // 战斗胜利之后的关卡
    val basicMorale: Double //初始怒气
) : Serializable {
    var unitOpenConMap: Map<Int, LinkedList<Int>> = mapOf()
    var randomRewardMap: List<RandomRewardVo> = listOf()  // 每次挑战胜利的奖励
    var heroExpPropsMap: List<ResVo> = listOf()                             // 扫荡英雄经验		扫荡副本时，给经验道具
    var firstWinRewardMap: List<ResVo> = listOf()                          // 首胜额外掉落		首胜获得的奖励固定
    var unitTeamList = listOf<Int>()
    var clearanceList = listOf<Int>()   //星级条件
}

data class RandomRewardVo(
    var dropPropsId: Int,  // 掉落道具id
    var dropNum: Int,  // 掉落数量
    var dropOdds: Int   // 掉落几率
)

class InstanceProtoCache : ProtoCacheInit("instance.xml") {
    var protoMap: Map<Int, InstanceProto> = mapOf()
    var protoMapByUnitIdAndPointId: Map<Int, Map<Int, InstanceProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InstanceResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InstanceResult

        val tmpProtoMap: HashMap<Int, InstanceProto> = hashMapOf()
        val tmpProtoMapByUnitIdAndPointId: HashMap<Int, HashMap<Int, InstanceProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val heroExpPropsVo = resStringToResVoList(vo.heroExpProps)
                ?: throw RuntimeException("instance.xml :: HeroExpProps ${vo.id} 奖励格式不合法.")

            vo.heroExpPropsMap = heroExpPropsVo

            val firstWinRewardMap = resStringToResVoList(vo.firstWinReward)
                ?: throw RuntimeException("instance.xml :: FirstWinReward ${vo.id} 奖励格式不合法.")

            vo.firstWinRewardMap = firstWinRewardMap

            val unitOpenConMap = hashMapOf<Int, LinkedList<Int>>()

            vo.unitOpenConMap = unitOpenConMap

            val randomRewardMap = LinkedList<RandomRewardVo>()
            if (vo.randomReward != "0") {
                val randomRewards = stringsSplit(vo.randomReward, "|")

                for (randomReward in randomRewards) {
                    val cc = stringsSplit(randomReward, ";")

                    if (cc.size != 3) {
                        throw RuntimeException("instance.xml :: RandomReward 字段:${vo.id}根据分号解析长度不为3.")
                    }

                    val dropPropsId = strconvAtoi(cc[0])
                    val dropNum = strconvAtoi(cc[1])
                    val dropOdds = strconvAtoi(cc[2])
                    if (dropPropsId == null || dropNum == null || dropOdds == null) {
                        throw RuntimeException("instance.xml :: RandomReward 字段:${vo.id}根据分号解析err")
                    }
                    randomRewardMap.add(RandomRewardVo(dropPropsId, dropNum, dropOdds))

                }
            }
            vo.randomRewardMap = randomRewardMap

            if (vo.monster != "0" && !vo.monster.isEmpty()) {
                val tmpUnitTeamList = LinkedList<Int>()
                val monsterStrs = stringsSplit(vo.monster, ";")
                monsterStrs.forEach {
                    val value = it.toIntOrNull()
                    if (value == null) {
                        throw RuntimeException("instance.xml :: monster 字段:${vo.id}根据分号解析err")
                    }
                    tmpUnitTeamList.add(value)
                }
                vo.unitTeamList = tmpUnitTeamList
            }

            if (vo.desc != "0" && !vo.desc.isEmpty()) {
                val tmpClearanceList = LinkedList<Int>()
                val clearanceStrs = stringsSplit(vo.desc, ";")
                clearanceStrs.forEach {
                    val value = it.toIntOrNull()
                    if (value == null) {
                        throw RuntimeException("instance.xml ::desc 字段:${vo.id}根据分号解析err")
                    }
                    tmpClearanceList.add(value)
                }
                vo.clearanceList = tmpClearanceList
            }

            val thisprotoMapByUnitIdAndPointIdvounitId =
                tmpProtoMapByUnitIdAndPointId.getOrPut(vo.unitId) { hashMapOf() }
            thisprotoMapByUnitIdAndPointIdvounitId[vo.pointId] = vo
            tmpProtoMap[vo.id] = vo
        }
        this.protoMapByUnitIdAndPointId = tmpProtoMapByUnitIdAndPointId
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for ((_, p) in this.protoMap) {
            pcs.instanceUnitProtoCache.protoMapByUnitId[p.unitId]
                ?: throw RuntimeException("instance.xml ::表配置的关卡所属章节:${p.unitId}在章节表中找不到.")

            for (d in p.randomRewardMap) {
                if (pcs.equipCache.equipProtoMap[d.dropPropsId] == null) {
                    throw RuntimeException("instance.xml ::表中随机掉落物品:${d.dropPropsId}在道具表里找不到.")
                }
            }

            p.unitTeamList.forEach {
                pcs.unitTeamProtoCache.protoMap[it]
                    ?: throw RuntimeException("instance.xml ::表中monster:${p.monster}在unitTeam中找不到")
            }

            p.clearanceList.forEach {
                pcs.clearanceProtoCache.clearanceMap[it]
                    ?: throw RuntimeException("instance.xml ::表中desc:${p.desc}在clearance中找不到")
            }

            if (p.loseStrength > p.winStrength) {
                throw RuntimeException("instance.xml ::表中配置的扣除体力错误")
            }
        }
    }

}