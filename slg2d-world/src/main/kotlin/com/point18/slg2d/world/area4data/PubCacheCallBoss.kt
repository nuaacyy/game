package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.worldentities.CALL_BOSS_NAMED_QUERY
import com.point18.slg2d.common.worldentities.CallBossEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 召唤Boss信息
class CallBoss(
    var worldId: Long,
    var id: Long,

    var bossId: Int,            // bossId
    var x: Int,                 // 所在坐标X
    var y: Int,                 // 所在坐标Y
    var protectOverTime: Long,  //保护结束时间
    var overTime: Long,         // 消失时间
    var nowHp: Int,             // 当前血量
    var atkRecordsMap: HashMap<Long, PersonalCallBossAtkRecord>, // 玩家进攻记录
    var playerId: Long,         // 玩家Id（之后可能整合联盟BOSS)
    var allianceId: Long,       // 联盟Id
    var helpMemberMap: HashSet<Long>
) : EntityWrapper<CallBossEntity>, IBossInfo {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, hashMapOf(), 0, 0, hashSetOf())

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): CallBossEntity {
        return CallBossEntity(
            worldId,
            id,
            bossId,
            x,
            y,
            protectOverTime,
            overTime,
            nowHp,
            toJson(atkRecordsMap),
            playerId,
            allianceId,
            toJson(helpMemberMap)
        )
    }

    override fun wrap(entity: CallBossEntity) {
        worldId = entity.worldId
        id = entity.id
        bossId = entity.bossId
        x = entity.x
        y = entity.y
        protectOverTime = entity.protectOverTime
        overTime = entity.overTime
        nowHp = entity.nowHp
        atkRecordsMap = toObj(entity.atkRecords)
        playerId = entity.playerId
        allianceId = entity.allianceId
        helpMemberMap = toObj(entity.helpMembers)
    }

    fun calSameAllianceAtkRecordCount(areaCache: AreaCache, allianceId: Long): Int {
        if (allianceId == 0L) {
            return 0
        }
        val allMembers = areaCache.playerCache.findPlayersByAllianceId(allianceId)
        return allMembers.count {
            this.atkRecordsMap.containsKey(it.id)
        }
    }

    override fun getPosX(): Int {
        return x
    }

    override fun getPosY(): Int {
        return y
    }

    override fun getBossProtoId(): Int {
        return bossId
    }

    override fun getCurrentHp(): Int {
        return nowHp
    }

    override fun getAtkRecordSet(): HashSet<Long> {
        return atkRecordsMap.keys.toHashSet()
    }
}

data class PersonalCallBossDamageRank(
    var x: Int,         //坐标X
    var y: Int,         //坐标Y
    var rank: RankMap<Long, Int, PersonalCallBossAtkRecord, Long>
)

data class PersonalCallBossAtkRecord(
    var playerId: Long,
    var totalDamage: Int,
    var updateTime: Long
)

fun createPersonalCallBossRank(): RankMap<Long, Int, PersonalCallBossAtkRecord, Long> {
    return RankMap(
        { it.playerId },
        { it.totalDamage },
        { it.updateTime },
        DescComparator(),
        DescComparator(),
        0
    )
}

data class AllianceCallBossDamageRank(
    var x: Int,         //坐标X
    var y: Int,         //坐标Y
    var rank: RankMap<Long, Int, AllianceCallBossAtkRecord, Long>
)

class AllianceCallBossAtkRecord(
    var allianceId: Long,
    var memberRecordMap: HashMap<Long, PersonalCallBossAtkRecord>
)

fun createAllianceCallBossRank(): RankMap<Long, Int, AllianceCallBossAtkRecord, Long> {
    return RankMap(
        { it.allianceId },
        { allianceCallBossAtkRecord ->
            var totalDamage = 0
            allianceCallBossAtkRecord.memberRecordMap.forEach { totalDamage += it.value.totalDamage }
            totalDamage
        },
        { allianceCallBossAtkRecord ->
            var maxTime = 0L
            val maxValue = allianceCallBossAtkRecord.memberRecordMap.maxBy { it.value.updateTime }
            if (maxValue != null) {
                maxTime = maxValue.value.updateTime
            }
            maxTime
        },
        DescComparator(),
        DescComparator(), 0
    )
}

class CacheCallBoss(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val callBossByXy = TwoKeyIndex<Int, Int, CallBoss>({ it.x }, { it.y })
    private val personalDamageRank = TwoKeyIndex<Int, Int, PersonalCallBossDamageRank>({ it.x }, { it.y })
    private val allianceDamageRank = TwoKeyIndex<Int, Int, AllianceCallBossDamageRank>({ it.x }, { it.y })

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.callBossEntities =
                    session.getNamedQuery(CALL_BOSS_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.callBossEntities.forEach { entity ->
            try {
                val callBoss = db.wdb.recover(entity) { CallBoss() }

                callBossByXy.insertByKey(callBoss)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
        //初始化魔物的排行榜信息
        callBossByXy.map {
            val personalRank = PersonalCallBossDamageRank(
                it.x,
                it.y,
                createPersonalCallBossRank()
            )
            personalDamageRank.insertByKey(personalRank)

            val allianceDamageInfoMap = hashMapOf<Long, AllianceCallBossAtkRecord>()
            for ((_, record) in it.atkRecordsMap) {
                personalRank.rank.updateValue(record)
                val player = areaCache.playerCache.findPlayerById(record.playerId)
                if (player == null) {
                    normalLog.error("伤害记录里的玩家找不到")
                    continue
                }
                if (player.allianceId == 0L) {
                    //当前不在联盟中
                    continue
                }
                val allianceDamageInfo =
                    allianceDamageInfoMap.getOrPut(
                        player.allianceId
                    ) { AllianceCallBossAtkRecord(player.allianceId, hashMapOf()) }
                allianceDamageInfo.memberRecordMap[record.playerId] = record
            }

            val allianceRank = AllianceCallBossDamageRank(
                it.x,
                it.y,
                createAllianceCallBossRank()
            )
            allianceDamageRank.insertByKey(allianceRank)
            for ((_, allianceDamageInfo) in allianceDamageInfoMap) {
                allianceRank.rank.updateValue(allianceDamageInfo)
            }

            true
        }
    }

    // 根据XY找到一个召唤BOSS
    fun findCallBossByXy(x: Int, y: Int): (CallBoss?) {
        return callBossByXy.findByKey(x, y)
    }

    fun createCallBoss(
        bossId: Int,
        x: Int,
        y: Int,
        protectOverTime: Long,
        overTime: Long,
        nowHp: Int,
        callId: Long,
        allianceId: Long
    ): CallBoss {
        val id = wpm.generateObjIdNew(areaCache)
        val callBoss =
            CallBoss(
                worldId,
                id,
                bossId,
                x,
                y,
                protectOverTime,
                overTime,
                nowHp,
                hashMapOf(),
                callId,
                allianceId,
                hashSetOf()
            )

        insert(areaCache, callBoss)
        callBossByXy.insertByKey(callBoss)

        personalDamageRank.insertByKey(
            PersonalCallBossDamageRank(
                x,
                y,
                createPersonalCallBossRank()
            )
        )
        allianceDamageRank.insertByKey(
            AllianceCallBossDamageRank(
                x,
                y,
                createAllianceCallBossRank()
            )
        )
        return callBoss
    }

    fun deleteCallBoss(boss: CallBoss?) {
        if (boss == null || boss.id == 0L) {
            return
        }

        delete(areaCache, boss)

        // 从缓存中删除
        callBossByXy.deleteByKey(boss)

        val personalRank = findPersonalCallBossRank(boss)
        personalDamageRank.deleteByKey(personalRank)
        val allianceRank = findAllianceCallBossRankByXY(boss)
        allianceDamageRank.deleteByKey(allianceRank)
    }

    //根据攻击玩家Id找到所有召唤的Boss
    fun findAllCallBossByAtkPlayerId(playerId: Long): List<CallBoss> {
        val allBoss = LinkedList<CallBoss>()
        callBossByXy.map {
            if (it.atkRecordsMap[playerId] != null) {
                allBoss.add(it)
            }
            true
        }
        return allBoss
    }

    // 找出所有到点的召唤BOSS
    fun findAllTimeOverCallBoss(): (LinkedList<CallBoss>) {
        val bosses = LinkedList<CallBoss>()
        val nowTime = getNowTime()

        callBossByXy.map {
            if (it.overTime in 1..(nowTime - 1)) {
                bosses.add(it)
            }
            true
        }

        return bosses
    }

    //查找个人排行信息
    fun findPersonalCallBossRank(boss: CallBoss): PersonalCallBossDamageRank {
        var personalRank = personalDamageRank.findByKey(boss.x, boss.y)
        if (personalRank == null) {
            personalRank = PersonalCallBossDamageRank(
                boss.x,
                boss.y,
                createPersonalCallBossRank()
            )
            personalDamageRank.insertByKey(personalRank)
        }
        return personalRank
    }

    //查找联盟排行信息
    fun findAllianceCallBossRankByXY(boss: CallBoss): AllianceCallBossDamageRank {
        var allianceRank = allianceDamageRank.findByKey(boss.x, boss.y)
        if (allianceRank == null) {
            allianceRank = AllianceCallBossDamageRank(
                boss.x,
                boss.y,
                createAllianceCallBossRank()
            )
            allianceDamageRank.insertByKey(allianceRank)
        }
        return allianceRank
    }
}