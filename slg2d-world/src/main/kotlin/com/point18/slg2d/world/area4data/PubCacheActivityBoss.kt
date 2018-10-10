package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.worldentities.ACTIVITY_BOSS_NAMED_QUERY
import com.point18.slg2d.common.worldentities.ActivityBossEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class ActivityBoss(
    var worldId: Long,
    var id: Long,

    var bossId: Int,            // bossId monster配置表id
    var activityBossId: Int,        // monsterActivity配置表id
    var x: Int,                 // 所在坐标X
    var y: Int,                 // 所在坐标Y
    var nowHp: Int,             // 当前血量
    var atkRecordsMap: HashMap<Long, PersonalActivityBossAtkRecord> // 玩家进攻记录
) : EntityWrapper<ActivityBossEntity>, IBossInfo {

    constructor() : this(0, 0, 0, 0, 0, 0, 0, hashMapOf())

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): ActivityBossEntity {
        return ActivityBossEntity(
            worldId,
            id,
            bossId,
            activityBossId,
            x,
            y,
            nowHp,
            toJson(atkRecordsMap)
        )
    }

    override fun wrap(entity: ActivityBossEntity) {
        worldId = entity.worldId
        id = entity.id
        bossId = entity.bossId
        activityBossId = entity.activityBossId
        x = entity.x
        y = entity.y
        nowHp = entity.nowHp
        atkRecordsMap = toObj(entity.atkRecords)
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

data class PersonalActivityBossDamageRank(
    var x: Int,         //坐标X
    var y: Int,         //坐标Y
    var rank: RankMap<Long, Int, PersonalActivityBossAtkRecord, Long>
)

data class PersonalActivityBossAtkRecord(
    var playerId: Long,
    var totalDamage: Int,
    var updateTime: Long
)

fun createPersonalActivityBossRank(): RankMap<Long, Int, PersonalActivityBossAtkRecord, Long> {
    return RankMap(
        { it.playerId },
        { it.totalDamage },
        { it.updateTime },
        DescComparator(),
        DescComparator(),
        0
    )
}

data class AllianceActivityBossDamageRank(
    var x: Int,         //坐标X
    var y: Int,         //坐标Y
    var rank: RankMap<Long, Int, AllianceActivityBossAtkRecord, Long>
)

class AllianceActivityBossAtkRecord(
    var allianceId: Long,
    var memberRecordMap: HashMap<Long, PersonalActivityBossAtkRecord>
)

fun createAllianceActivityBossRank(): RankMap<Long, Int, AllianceActivityBossAtkRecord, Long> {
    return RankMap(
        { it.allianceId },
        { allianceActivityBossAtkRecord ->
            var totalDamage = 0
            allianceActivityBossAtkRecord.memberRecordMap.forEach { totalDamage += it.value.totalDamage }
            totalDamage
        },
        { allianceActivityBossAtkRecord ->
            var maxTime = 0L
            val maxValue = allianceActivityBossAtkRecord.memberRecordMap.maxBy { it.value.updateTime }
            if (maxValue != null) {
                maxTime = maxValue.value.updateTime
            }
            maxTime
        },
        DescComparator(),
        DescComparator(), 0
    )
}

class CacheActivityBoss(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val activityBossByXy = TwoKeyIndex<Int, Int, ActivityBoss>({ it.x }, { it.y })
    private val activityBossByMonsterId = OneKeyIndex<Int, ActivityBoss> { it.activityBossId }
    val personalDamageRank = TwoKeyIndex<Int, Int, PersonalActivityBossDamageRank>({ it.x }, { it.y })
    val allianceDamageRank = TwoKeyIndex<Int, Int, AllianceActivityBossDamageRank>({ it.x }, { it.y })

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.activityBossEntities =
                session.getNamedQuery(ACTIVITY_BOSS_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.activityBossEntities.forEach { entity ->
            try {
                val activityBoss = db.wdb.recover(entity) { ActivityBoss() }

                activityBossByXy.insertByKey(activityBoss)
                activityBossByMonsterId.insertByKey(activityBoss)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
        //初始化魔物的排行榜信息
        activityBossByXy.map {
            val personalRank = PersonalActivityBossDamageRank(
                it.x,
                it.y,
                createPersonalActivityBossRank()
            )
            personalDamageRank.insertByKey(personalRank)

            val allianceDamageInfoMap = hashMapOf<Long, AllianceActivityBossAtkRecord>()
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
                    ) { AllianceActivityBossAtkRecord(player.allianceId, hashMapOf()) }
                allianceDamageInfo.memberRecordMap[record.playerId] = record
            }

            val allianceRank = AllianceActivityBossDamageRank(
                it.x,
                it.y,
                createAllianceActivityBossRank()
            )
            allianceDamageRank.insertByKey(allianceRank)
            for ((_, allianceDamageInfo) in allianceDamageInfoMap) {
                allianceRank.rank.updateValue(allianceDamageInfo)
            }

            true
        }
    }

    // 根据XY找到一个活动BOSS
    fun findActivityBossByXy(x: Int, y: Int): ActivityBoss? {
        return activityBossByXy.findByKey(x, y)
    }

    // 根据monsterId找一个活动BOSS
    fun findActivityBossByMonsterId(monsterId: Int): ActivityBoss? {
        return activityBossByMonsterId.findByKey(monsterId)
    }

    // 清除所有记录里某玩家的数据
    fun delAllActivityBossByPlayerId(playerId: Long) {
        activityBossByXy.map {
            if (it.atkRecordsMap[playerId] != null) {
                it.atkRecordsMap.remove(playerId)
            }
            true
        }
    }

    fun createActivityBoss(
        bossId: Int,
        activityBossId: Int,
        x: Int,
        y: Int,
        nowHp: Int
    ): ActivityBoss {
        val id = wpm.generateObjIdNew(areaCache)
        val activityBoss = ActivityBoss(
            worldId,
            id,
            bossId,
            activityBossId,
            x,
            y,
            nowHp,
            hashMapOf()
        )

        insert(areaCache, activityBoss)
        activityBossByXy.insertByKey(activityBoss)
        activityBossByMonsterId.insertByKey(activityBoss)

        personalDamageRank.insertByKey(
            PersonalActivityBossDamageRank(
                x,
                y,
                createPersonalActivityBossRank()
            )
        )
        allianceDamageRank.insertByKey(
            AllianceActivityBossDamageRank(
                x,
                y,
                createAllianceActivityBossRank()
            )
        )
        return activityBoss
    }

    fun deleteActivityBoss(boss: ActivityBoss?) {
        if (boss == null || boss.id == 0L) {
            return
        }

        delete(areaCache, boss)

        // 从缓存中删除
        activityBossByXy.deleteByKey(boss)
        activityBossByMonsterId.deleteByKey(boss)

        val personalRank = findPersonalActivityBossRankByXY(boss.x, boss.y)
        if (personalRank != null) {
            personalDamageRank.deleteByKey(personalRank)
        }
        val allianceRank = findAllianceActivityBossRankByXY(boss.x, boss.y)
        if (allianceRank != null) {
            allianceDamageRank.deleteByKey(allianceRank)
        }
    }

    //根据攻击玩家Id找到所有活动Boss
    fun findAllActivityBossByAtkPlayerId(playerId: Long): List<ActivityBoss> {
        val allBoss = LinkedList<ActivityBoss>()
        activityBossByXy.map {
            if (it.atkRecordsMap[playerId] != null) {
                allBoss.add(it)
            }
            true
        }
        return allBoss
    }

    //根据坐标查找个人排行信息
    fun findPersonalActivityBossRankByXY(x: Int, y: Int): PersonalActivityBossDamageRank? {
        return personalDamageRank.findByKey(x, y)
    }

    fun createAllianceActivityBossRank(rank: AllianceActivityBossDamageRank) {
        return allianceDamageRank.insertByKey(rank)
    }

    //根据坐标查找联盟排行信息
    fun findAllianceActivityBossRankByXY(x: Int, y: Int): AllianceActivityBossDamageRank? {
        return allianceDamageRank.findByKey(x, y)
    }
}