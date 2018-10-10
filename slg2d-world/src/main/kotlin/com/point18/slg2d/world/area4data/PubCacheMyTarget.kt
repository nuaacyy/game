package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.worldentities.MY_TARGET_NAMED_QUERY
import com.point18.slg2d.common.worldentities.MyTargetEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class MyTarget(
    var worldId: Long,
    var id: Long,

    var playerId: Long,  //玩家ID
    var winRecord: Int,  //总胜场
    var failRecord: Int,  //总败场
    var attackWinRecord: Int,  //进攻胜次
    var attackFailRecord: Int, //攻击败次
    var defendWinRecord: Int,  //防御胜次
    var defendFailRecord: Int, //防御败次
    var totalHelpAlly: Int,  //帮助盟友总数
    var totalKill: Long,  //累计的击杀数
    var weakenStrength: Long,  //削弱的敌军实力
    var soliderStrength: Long,  //士兵实力
    var trapStrength: Long,  //陷阱实力
    var missionStrength: Long,  //任务实力
    var researchStrength: Long,  //研发实力
    var buildStrength: Long,  //建造实力
    var heroStrength: Long,  //英雄实力
    var kingStrength: Long,  //君主实力
    var researchCount: Int,              //研究次数
    var receiveOnlineGiftCount: Int,              //领取在线礼包个数
    var plunderResNum: Long,  //掠夺的资源数量
    var soliderDieNum: Long,  //损失的士兵数量
    var trapDieNum: Long, //损失的陷阱数量
    var cureSoliderNum: Long,  //治疗的士兵数量
    var moveCityCount: Int,              //迁城次数
    var defenseSuccess: Int, //守城成功次数
    var defenseFailed: Int,  // 守城失败次数
    var scoutNum: Int,  // 完成侦查行为次数 （成功算）
    var massNum: Int,  // 发起集结胜利次数
    var joinMassNum: Int,//参与集结胜利次数
    var atkMonsterNum: Int, // 完成魔物猎杀次数 （地图上普通魔物）
    var killMonsterScore: Long, // 击杀魔物分
    var lastPowerChangeTime: Long,  //
    var lastKillSoliderTime: Long,  //
    var lastKillMonsterScoreTime: Long,  //
    var jjcRank: Int, // 竞技场排名
    var maxJjcRank: Int,  //竞技场历史最高排名
    var jjcWinRecord: Int, //竞技场胜场
    var lastJjcRankTime: Long, // 竞技场排名最新变化时间
    var instanceFightNum: Int, // 推图总次数
    var caveSoliderNum: Int, // 藏兵总次数
    var getPrisonNum: Int, // 抓领主次数
    var bePrisonNum: Int, //领主被抓次数
    var killKingNum: Int, //击杀领主数
    var kingBeKillNum: Int, //领主被杀次数
    var catchKingEscapeNum: Int, //领主被逃脱次数
    var kingEscapeNum: Int, //领主逃脱次数
    var getRewardGoldNum: Long // 获得总赏金值

) : EntityWrapper<MyTargetEntity> {
    var makeEquipMap: HashMap<Int, Int> = hashMapOf()//锻造装备数量
    var atkMonsterMap: HashMap<Int, HashMap<Int, Long>> = hashMapOf()//攻击魔物次数
    var makeSoliderMap: HashMap<Int, HashMap<Int, Long>> = hashMapOf() //造兵数量
    var makeTrapMap: HashMap<Int, HashMap<Int, Long>> = hashMapOf() //造陷阱数量
    var killSoliderMap: HashMap<Int, HashMap<Int, Long>> = hashMapOf() //击杀士兵数量
    var damageSoliderMap: HashMap<Int, HashMap<Int, Long>> = hashMapOf() //击伤士兵数量
    var killMonsterMap: HashMap<Int, HashMap<Int, Long>> = hashMapOf() //击杀魔物数量
    var farmResNumMap: HashMap<Int, Long> = hashMapOf() //采集资源数量
    var farmResCountMap: HashMap<Int, Int> = hashMapOf() //采集次数
    var farmEmptyMap: HashMap<Int, Int> = hashMapOf() //采空资源点次数
    var activityScoreMap: HashMap<Int, Int> = hashMapOf() // 挑战类型：积分获得
    var posTypeMap: HashMap<Int, Int> = hashMapOf() //王国官职
    var transportResMap: HashMap<Int, Long> = hashMapOf() //运输资源数量

    constructor() : this(
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0
        , 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): MyTargetEntity {
        return MyTargetEntity(
            worldId,
            id,
            playerId,
            winRecord,
            failRecord,
            attackWinRecord,
            attackFailRecord,
            defendWinRecord,
            defendFailRecord,
            totalHelpAlly,
            totalKill,
            weakenStrength,
            soliderStrength,
            trapStrength,
            missionStrength,
            researchStrength,
            buildStrength,
            heroStrength,
            kingStrength,
            researchCount,
            receiveOnlineGiftCount,
            plunderResNum,
            soliderDieNum,
            trapDieNum,
            cureSoliderNum,
            moveCityCount,
            defenseSuccess,
            defenseFailed,
            scoutNum,
            massNum,
            joinMassNum,
            atkMonsterNum,
            killMonsterScore,
            lastPowerChangeTime,
            lastKillSoliderTime,
            lastKillMonsterScoreTime,
            jjcRank,
            maxJjcRank,
            jjcWinRecord,
            lastJjcRankTime,
            instanceFightNum,
            caveSoliderNum,
            getPrisonNum,
            bePrisonNum,
            killKingNum,
            kingBeKillNum,
            catchKingEscapeNum,
            kingEscapeNum,
            getRewardGoldNum,
            toJson(makeEquipMap),
            toJson(atkMonsterMap),
            toJson(makeSoliderMap),
            toJson(makeTrapMap),
            toJson(killSoliderMap),
            toJson(damageSoliderMap),
            toJson(killMonsterMap),
            toJson(farmResNumMap),
            toJson(farmResCountMap),
            toJson(farmEmptyMap),
            toJson(activityScoreMap),
            toJson(posTypeMap),
            toJson(transportResMap)
        )
    }

    override fun wrap(entity: MyTargetEntity) {
        worldId = entity.worldId
        id = entity.id
        playerId = entity.playerId
        winRecord = entity.winRecord
        failRecord = entity.failRecord
        attackWinRecord = entity.attackWinRecord
        attackFailRecord = entity.attackFailRecord
        defendWinRecord = entity.defendWinRecord
        defendFailRecord = entity.defendFailRecord
        totalHelpAlly = entity.totalHelpAlly
        totalKill = entity.totalKill
        weakenStrength = entity.weakenStrength
        soliderStrength = entity.soliderStrength
        trapStrength = entity.trapStrength
        missionStrength = entity.missionStrength
        researchStrength = entity.researchStrength
        buildStrength = entity.buildStrength
        heroStrength = entity.heroStrength
        kingStrength = entity.kingStrength
        researchCount = entity.researchCount
        receiveOnlineGiftCount = entity.receiveOnlineGiftCount
        plunderResNum = entity.plunderResNum
        soliderDieNum = entity.soliderDieNum
        trapDieNum = entity.trapDieNum
        cureSoliderNum = entity.cureSoliderNum
        moveCityCount = entity.moveCityCount
        defenseSuccess = entity.defenseSuccess
        defenseFailed = entity.defenseFailed
        scoutNum = entity.scoutNum
        massNum = entity.massNum
        joinMassNum = entity.joinMassNum
        atkMonsterNum = entity.atkMonsterNum
        killMonsterScore = entity.killMonsterScore
        lastPowerChangeTime = entity.lastPowerChangeTime
        lastKillSoliderTime = entity.lastKillSoliderTime
        lastKillMonsterScoreTime = entity.lastKillMonsterScoreTime
        jjcRank = entity.jjcRank
        maxJjcRank = entity.maxJjcRank
        jjcWinRecord = entity.jjcWinRecord
        lastJjcRankTime = entity.lastJjcRankTime
        instanceFightNum = entity.instanceFightNum
        caveSoliderNum = entity.caveSoliderNum
        getPrisonNum = entity.getPrisonNum
        bePrisonNum = entity.bePrisonNum
        killKingNum = entity.killKingNum
        kingBeKillNum = entity.kingBeKillNum
        catchKingEscapeNum = entity.catchKingEscapeNum
        kingEscapeNum = entity.kingEscapeNum
        getRewardGoldNum = entity.getRewardGoldNum
        makeEquipMap = toObj(entity.makeEquipInfo)
        atkMonsterMap = toObj(entity.atkMonsterInfo)
        makeSoliderMap = toObj(entity.makeSoliderInfo)
        makeTrapMap = toObj(entity.makeTrapInfo)
        killSoliderMap = toObj(entity.killSoliderInfo)
        damageSoliderMap = toObj(entity.damageSoliderInfo)
        killMonsterMap = toObj(entity.killMonsterInfo)
        farmResNumMap = toObj(entity.farmResNumInfo)
        farmResCountMap = toObj(entity.farmResCountInfo)
        farmEmptyMap = toObj(entity.farmEmptyInfo)
        activityScoreMap = toObj(entity.activityScoreInfo)
        posTypeMap = toObj(entity.posTypeInfo)
        transportResMap = toObj(entity.transportResInfo)
    }
}

//获取总实力
fun MyTarget.getTotalPower(): Long {
    return this.soliderStrength + this.trapStrength + this.missionStrength + this.researchStrength + this.buildStrength + this.heroStrength + this.kingStrength
}

class CacheTarget(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val myTargetMap = OneKeyIndex<Long, MyTarget> { it.playerId }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.myTargetEntities =
                session.getNamedQuery(MY_TARGET_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.myTargetEntities.forEach { entity ->
            try {
                val myTarget = db.wdb.recover(entity) { MyTarget() }

                myTargetMap.insertByKey(myTarget)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    // 根据玩家ID拿信息
    fun findMyTargetEntityByPlayerId(playerId: Long): MyTargetEntity? {
        val info = myTargetMap.findByKey(playerId)
        return info?.toEntity()
    }

    fun createMyTargetByMoveServer(b: MyTargetEntity) {
        val myTarget = MyTarget()
        myTarget.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        myTarget.worldId = worldId
        myTarget.id = id

        insert(areaCache, myTarget)

        // 添加到缓存中
        myTargetMap.insertByKey(myTarget)
    }

    // 移除某个玩家的所有数据
    fun clearMyTargetForMoveServer(playerId: Long) {
        val del = findMyTargetByPlayerId(playerId)
        if (del != null) {
            delete(areaCache, del)

            // 从缓存中删除
            myTargetMap.deleteByKey(del)
        }
    }

    fun createMyTarget(playerId: Long): MyTarget {
        val id = wpm.generateObjIdNew(areaCache)
        val nowTime = getNowTime()
        val myTarget = MyTarget(
            worldId,
            id,
            playerId,
            0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, nowTime, nowTime, nowTime, 0,
            0, 0, nowTime, 0, 0, 0, 0, 0, 0,
            0, 0, 0
        )

        insert(areaCache, myTarget)

        myTargetMap.insertByKey(myTarget)

        return myTarget
    }

    // 根据玩家ID拿信息
    fun findMyTargetByPlayerId(playerId: Long): MyTarget? {
        return myTargetMap.findByKey(playerId)
    }

    fun findAllMyTarget(): List<MyTarget> {
        val rank = LinkedList<MyTarget>()
        myTargetMap.map { it: MyTarget ->
            rank.add(it)
        }

        return rank
    }
}