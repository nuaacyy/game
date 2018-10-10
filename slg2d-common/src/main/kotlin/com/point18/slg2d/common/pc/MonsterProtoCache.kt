package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.commonfunc.getRandInt
import java.io.Serializable
import java.util.*

data class MonsterResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<MonsterProto>
) : Serializable

data class MonsterProto(
    val id: Int,
    val mainId: Int,
    val level: Int,
    val type: Int, //魔物类型,不同产出的魔物
    val race: Int, //种族
    val name: String,
    val unit: Int, //魔物ID
    val unitLv: Int, //等级
    val unitStar: Int,//星级
    val unitStep: Int,  //阶级
    val might: Int,  //战斗力
    val proctTime: Int,  //保护时间（秒）
    val refreshTime: Int,    //存活时间(秒)
    val lordExp: Int, //君主经验
    val heroExp: Int,  //英雄经验
    val energy: Int, //消耗的行动力
    val dropProps: String, //攻击掉落包
    val dropCoin: String, //攻击掉落的宝石
    val dropKillProps: String, //击杀掉落
    val allianceProps: String, //击杀联盟奖励
    val callProps: String,//召唤礼
    val monsterDie: String,//转换尸体资源地
    val minHelpNum: Int, //最低协助人数
    val attackAllianceGift: Int,//攻击积分
    val killAllianceGift: Int, //击杀积分
    val libraryType: Int //图书馆类型
) : Serializable {
    var atkDrops: List<Drop> = listOf() //掉落包
    var atkCoins: List<Drop> = listOf() //攻击掉落宝石
    var killDrops: List<Drop> = listOf() //击杀掉落
    var allianceDrops: HashMap<Int, Int> = hashMapOf() //联盟礼物
    var callDrops: List<Drop> = listOf() //召唤掉落
    var dieResMap = TreeMap<Int, Int>() //尸体资源地（存在不转换的可能）

    fun selectDieRes(): Int {
        var totalRate = 0
        var maxTotalRate = 10000
        val iterator = dieResMap.iterator()
        while (iterator.hasNext()) {
            val nextVal = iterator.next()
            totalRate += nextVal.value
        }
        if (totalRate > maxTotalRate) {
            maxTotalRate = totalRate
        }
        val rand = getRandInt(maxTotalRate)
        var tempRate = 0

        while (dieResMap.iterator().hasNext()) {
            val it = dieResMap.iterator().next()
            if (rand <= tempRate + it.value) {
                return it.key
            }
            tempRate += it.value
        }
        return 0
    }
}

class MonsterProtoCache : ProtoCacheInit("monster.xml") {
    private var monsterList: List<MonsterProto> = LinkedList()
    private var monsterMap: Map<Int, MonsterProto> = hashMapOf()
    private var monsterMapByTypeAndLv: Map<Int, HashMap<Int, MutableList<MonsterProto>>> = hashMapOf()
    private var monsterMapByMainId: Map<Int, MutableList<MonsterProto>> = hashMapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<MonsterResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as MonsterResult

        val tmpMonsterList = mutableListOf<MonsterProto>()
        val tmpMonsterMap: HashMap<Int, MonsterProto> = hashMapOf()
        val tmpMonsterMapByTypeAndLv: HashMap<Int, HashMap<Int, MutableList<MonsterProto>>> = hashMapOf()
        val tmpMonsterMapByMainId: HashMap<Int, MutableList<MonsterProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpMonsterMap[vo.id] = vo
            val monsterMapByTypeAndLvMonsterType = tmpMonsterMapByTypeAndLv.getOrPut(vo.type) { hashMapOf() }
            monsterMapByTypeAndLvMonsterType.getOrPut(vo.level) { mutableListOf() }.add(vo)
            tmpMonsterMapByMainId.getOrPut(vo.mainId) { mutableListOf() }.add(vo)

            vo.atkDrops = dropStringToDrops(vo.dropProps) ?: throw RuntimeException("monster.xml :: 攻击掉落包配置错误:${vo.id}")
            vo.atkCoins = dropStringToDrops(vo.dropCoin) ?: throw RuntimeException("monster.xml :: 攻击掉落宝石配置错误:${vo.id}")
            vo.killDrops = dropStringToDrops(vo.dropKillProps) ?: throw RuntimeException("monster.xml :: 击杀掉落配置错误:${vo.id}")
            vo.callDrops = dropStringToDrops(vo.callProps) ?: throw RuntimeException("monster.xml :: 召唤礼掉落配置错误:${vo.id}")
            vo.allianceDrops = parseIntMap(vo.allianceProps) ?: throw RuntimeException("monster.xml :: 联盟礼物配置错误:${vo.id}")
            val dieResMap = parseIntMap(vo.monsterDie) ?: throw RuntimeException("monster.xml :: 尸体资源地配置错误:${vo.id}")
            dieResMap.forEach { vo.dieResMap[it.key] = it.value }

            tmpMonsterList.add(vo)
        }

        if (tmpMonsterList.isEmpty()) {
            throw RuntimeException("monster.xml :: 没有配置任何模板")
        }

        this.monsterList = tmpMonsterList
        this.monsterMap = tmpMonsterMap
        this.monsterMapByTypeAndLv = tmpMonsterMapByTypeAndLv
        this.monsterMapByMainId = tmpMonsterMapByMainId
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in this.monsterMap) {
            pcs.unitBaseCache.protoMap[proto.unit]
                ?: throw RuntimeException("monster.xml 中的unit字段在unitbase表里找不到:${proto.id}")

            pcs.lordExpAwardProtoCache.lordExpAwardTypeMap[proto.lordExp]
                ?: throw RuntimeException("monster.xml 中的lordExp字段在lordExpAward表里找不到:${proto.id}")

            pcs.heroExpAwardProtoCache.heroExpAwardTypeMap[proto.heroExp]
                ?: throw RuntimeException("monster.xml 中的heroExp字段在heroExpAward表里找不到:${proto.id}")

            proto.atkDrops.forEach {
                pcs.dropPropsProtoCache.dropPropsMap[it.id]
                    ?: throw RuntimeException("monster.xml 中的dropProps字段在dropProps表里找不到:${it.id}")
            }

            proto.atkCoins.forEach {
                pcs.dropPropsProtoCache.dropPropsMap[it.id]
                    ?: throw RuntimeException("monster.xml 中的dropCoin字段在dropProps表里找不到:${it.id}")
            }

            proto.killDrops.forEach {
                pcs.dropPropsProtoCache.dropPropsMap[it.id]
                    ?: throw RuntimeException("monster.xml 中的dropKillProps字段在dropProps表里找不到:${it.id}")
            }

            proto.callDrops.forEach {
                pcs.dropPropsProtoCache.dropPropsMap[it.id]
                    ?: throw RuntimeException("monster.xml 中的callProps字段在dropProps表里找不到:${it.id}")
            }

            proto.allianceDrops.forEach {
                pcs.allianceGiftProtoCache.allianceGiftProtoMap[it.key]
                    ?: throw RuntimeException("monster.xml 中的callProps字段在allianceGift表里找不到:${it.key}")
            }

            proto.dieResMap.forEach {
                pcs.resPointProtoCache.resPointMap[it.key]
                    ?: throw RuntimeException("monster.xml 中的monsterDie字段在resPoint表里找不到:${proto.id}")
            }

            pcs.monsterAllianceProtoCache.monsterAllianceProtoMap.values.forEach {
                if (it.libraryType == proto.libraryType) {
                    throw RuntimeException("monster.xml 中的libraryType字段在monsterAlliance表里重复:${proto.id}")
                }
            }
            pcs.monsterWorldProtoCache.monsterWorldProtoMap.values.forEach {
                if (it.libraryType == proto.libraryType) {
                    throw RuntimeException("monster.xml 中的libraryType字段在monsterWorld表里重复:${proto.id}")
                }
            }
        }
    }

    /**
     * 找到特定模板
     */
    fun findMonsterProto(id: Int): MonsterProto? {
        return this.monsterMap[id]
    }

    fun findMonsterProtoByTypeLv(type: Int, lv: Int): List<MonsterProto>? {
        val subMap = monsterMapByTypeAndLv[type]
        if (subMap == null) {
            return null
        }
        return subMap[lv]
    }

    fun checkMainIdExist(mainId: Int): Boolean {
        return monsterMapByMainId[mainId] != null
    }

    fun fetchRandomMonster(): MonsterProto {
        val randBoss = getRandInt(monsterList.size)
        return pcs.monsterProtoCache.monsterList[randBoss]
    }

}