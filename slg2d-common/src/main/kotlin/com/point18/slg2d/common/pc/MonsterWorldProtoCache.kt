package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class MonsterWorldProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MonsterWorldProto>
): Serializable

data class MonsterWorldProto(
    val id: Int, // 编号
    val type: Int,   // 魔物类型
    val unitLv: Int, //等级
    val star: Int, //星级
    val awake: Int,  //阶级
    val unit: Int,  // 单位	调用unitebaseid
    val might: Int,  // 战斗力	联盟魔物战力
    val refreshTime: Int, // 持续时间	联盟魔物持续时间
    val cdTime: Int, // 间隔时间
    val energy: Int, // 消耗行动力	消耗行动力
    val lordExp: Int, // 领主经验
    val heroExp: Int, // 英雄经验
    val dropProps: String, // 攻击奖励道具
    val dropAllianceGift: Int, // 击杀联盟道具	击杀后给的联盟礼包
    val city: Int,  // 刷新对应奇观		配奇观表的奇观id
    val libraryType: Int  //图书馆类型
) : Serializable{
    var dropPropsResVo: List<ResVo> = listOf()//// 攻击奖励道具
}

class MonsterWorldProtoCache : ProtoCacheInit("monsterWorld.xml") {
    var monsterWorldProtoMap: Map<Int, MonsterWorldProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MonsterWorldProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MonsterWorldProtoResult

        val tmpMonsterWorldProtoMap: HashMap<Int, MonsterWorldProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val reward = resStringToResVoList(vo.dropProps)

            if (null == reward) {
                throw RuntimeException("monsterWorld.xml 中攻击奖励配置错误:${vo.id}")
            } else {
                vo.dropPropsResVo = reward
            }

            tmpMonsterWorldProtoMap[vo.id] = vo
        }
        this.monsterWorldProtoMap = tmpMonsterWorldProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in this.monsterWorldProtoMap) {
            pcs.unitBaseCache.protoMap[proto.unit]
                ?: throw RuntimeException("monsterWorld.xml 中的unit字段在unitbase表里找不到:${proto.id}")

            pcs.lordExpAwardProtoCache.lordExpAwardTypeMap[proto.lordExp]
                ?: throw RuntimeException("monsterWorld.xml 中的lordExp字段在lordExpAward表里找不到:${proto.id}")

            pcs.heroExpAwardProtoCache.heroExpAwardTypeMap[proto.heroExp]
                ?: throw RuntimeException("monsterWorld.xml 中的heroExp字段在heroExpAward表里找不到:${proto.id}")

        }
    }

    fun findWorldBossByCityId(cityId: Int): MonsterWorldProto? {
        for ((_, p) in this.monsterWorldProtoMap) {

            if (p.city == cityId) {
                return p
            }
        }
        return null
    }
}