package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.REFRESH_INTERVAL
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.COMMON_BOSS_DATA_NAMED_QUERY
import com.point18.slg2d.common.worldentities.CommonBossDataEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 普通Boss信息
class CommonBossData(
    var worldId: Long,
    var id: Long,

    var gridId: Int,     // 生态格子id
    var bossDataMap: HashMap<Int, HashMap<Int, CommonBoss>> //X-Y-地块数据
) : EntityWrapper<CommonBossDataEntity> {

    constructor() : this(0, 0, 0, hashMapOf())

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): CommonBossDataEntity {
        return CommonBossDataEntity(
            worldId,
            id,
            gridId,
            toJson(bossDataMap)
        )
    }

    override fun wrap(entity: CommonBossDataEntity) {
        worldId = entity.worldId
        id = entity.id
        gridId = entity.gridId
        bossDataMap = toObj(entity.bossDatas)
    }
}

class CommonBoss(
    var bossId: Int,   //魔物Id
    var x: Int,         //坐标X
    var y: Int,         //坐标Y
    var nowHp: Int,  //当前血量
    val atkRecordsMap: HashMap<Long, PersonalCommonBossAtkRecord> //伤害记录
) : IBossInfo {
    fun calSameAllianceAtkRecordCount(areaCache: AreaCache, allianceId: Long): Int {
        if (allianceId == 0L) {
            return 0
        }
        val allMembers = areaCache.playerCache.findPlayersByAllianceId(allianceId)
        return allMembers.count {
            this.atkRecordsMap.containsKey(it.id)
        }
    }

    @JsonIgnore
    override fun getPosX(): Int {
        return x
    }

    @JsonIgnore
    override fun getPosY(): Int {
        return y
    }

    @JsonIgnore
    override fun getBossProtoId(): Int {
        return bossId
    }

    @JsonIgnore
    override fun getCurrentHp(): Int {
        return nowHp
    }

    @JsonIgnore
    override fun getAtkRecordSet(): HashSet<Long> {
        return atkRecordsMap.keys.toHashSet()
    }
}

data class PersonalCommonBossDamageRank(
    var x: Int,         //坐标X
    var y: Int,         //坐标Y
    var rank: RankMap<Long, Int, PersonalCommonBossAtkRecord, Long>
)

data class PersonalCommonBossAtkRecord(
    var playerId: Long,
    var totalDamage: Int,
    var updateTime: Long
)

fun createPersonalCommonBossRank(): RankMap<Long, Int, PersonalCommonBossAtkRecord, Long> {
    return RankMap(
        { it.playerId },
        { it.totalDamage },
        { it.updateTime },
        DescComparator(),
        DescComparator(),
        0
    )
}

data class AllianceCommonBossDamageRank(
    var x: Int,         //坐标X
    var y: Int,         //坐标Y
    var rank: RankMap<Long, Int, AllianceCommonBossAtkRecord, Long>
)

class AllianceCommonBossAtkRecord(
    var allianceId: Long,
    var memberRecordMap: HashMap<Long, PersonalCommonBossAtkRecord>
)

fun createAllianceCommonBossRank(): RankMap<Long, Int, AllianceCommonBossAtkRecord, Long> {
    return RankMap(
        { it.allianceId },
        { allianceCommonBossAtkRecord ->
            var totalDamage = 0
            allianceCommonBossAtkRecord.memberRecordMap.forEach { totalDamage += it.value.totalDamage }
            totalDamage
        },
        { allianceCommonBossAtkRecord ->
            var maxTime = 0L
            val maxValue = allianceCommonBossAtkRecord.memberRecordMap.maxBy { it.value.updateTime }
            if (maxValue != null) {
                maxTime = maxValue.value.updateTime
            }
            maxTime
        },
        DescComparator(),
        DescComparator(), 0
    )
}

class CacheCommonBoss(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val commonBossByGrid = OneKeyIndex<Int, CommonBossData> { it.gridId }
    private val commonBossByXY = TwoKeyIndex<Int, Int, CommonBoss>({ it.x }, { it.y })

    //玩家攻打记录
    private val commonBossByPlayerId = hashMapOf<Long, TwoKeyIndex<Int, Int, CommonBoss>>()

    private val personalDamageRank = TwoKeyIndex<Int, Int, PersonalCommonBossDamageRank>({ it.x }, { it.y })
    private val allianceDamageRank = TwoKeyIndex<Int, Int, AllianceCommonBossDamageRank>({ it.x }, { it.y })

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.commonBossEntities =
                    session.getNamedQuery(COMMON_BOSS_DATA_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.commonBossEntities.forEach { entity ->
            try {
                val commonBoss = db.wdb.recover(entity) { CommonBossData() }

                this.commonBossByGrid.insertByKey(commonBoss)
                for ((_, yMap) in commonBoss.bossDataMap) {
                    for ((_, data) in yMap) {
                        this.commonBossByXY.insertByKey(data)
                    }
                }

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
        //初始化魔物的排行榜信息
        commonBossByXY.map {
            val personalRank = PersonalCommonBossDamageRank(
                it.x,
                it.y,
                createPersonalCommonBossRank()
            )
            personalDamageRank.insertByKey(personalRank)

            val allianceDamageInfoMap = hashMapOf<Long, AllianceCommonBossAtkRecord>()
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
                    ) { AllianceCommonBossAtkRecord(player.allianceId, hashMapOf()) }
                allianceDamageInfo.memberRecordMap[record.playerId] = record
            }

            val allianceRank = AllianceCommonBossDamageRank(
                it.x,
                it.y,
                createAllianceCommonBossRank()
            )
            allianceDamageRank.insertByKey(allianceRank)
            for ((_, allianceDamageInfo) in allianceDamageInfoMap) {
                allianceRank.rank.updateValue(allianceDamageInfo)
            }

            for ((playerId, _) in it.atkRecordsMap) {
                val playerRecords =
                    commonBossByPlayerId.getOrPut(playerId) { TwoKeyIndex({ boss -> boss.x }, { boss -> boss.y }) }
                playerRecords.insertByKey(it)
            }
            true
        }
    }

    fun createCommonBoss(
        bossId: Int,
        x: Int,
        y: Int,
        nowHp: Int
    ): CommonBoss {
        val gridId = calBossGrid(x, y)
        var bossData = commonBossByGrid.findByKey(gridId)
        if (bossData == null) {
            val id = wpm.generateObjIdNew(areaCache)
            bossData = CommonBossData(worldId, id, gridId, hashMapOf())
            commonBossByGrid.insertByKey(bossData)
            insert(areaCache, bossData)
        }
        val boss = CommonBoss(bossId, x, y, nowHp, hashMapOf())
        val yMap = bossData.bossDataMap.getOrPut(x) { hashMapOf() }
        yMap[y] = boss
        commonBossByXY.insertByKey(boss)
        personalDamageRank.insertByKey(
            PersonalCommonBossDamageRank(
                x,
                y,
                createPersonalCommonBossRank()
            )
        )
        allianceDamageRank.insertByKey(
            AllianceCommonBossDamageRank(
                x,
                y,
                createAllianceCommonBossRank()
            )
        )

        return boss
    }

    fun deleteCommonBossByXY(x: Int, y: Int) {
        val personalRank = findPersonalCommonBossRankByXY(x, y)
        if (personalRank != null) {
            personalDamageRank.deleteByKey(personalRank)
        }
        val allianceRank = findAllianceCommonBossRankByXY(x, y)
        if (allianceRank != null) {
            allianceDamageRank.deleteByKey(allianceRank)
        }

        val gridId = calBossGrid(x, y)
        val bossData = commonBossByGrid.findByKey(gridId) ?: return
        val yMap = bossData.bossDataMap[x] ?: return
        val boss = yMap[y] ?: return

        boss.atkRecordsMap.forEach {
            val playerRecords = commonBossByPlayerId[it.key] ?: return@forEach
            playerRecords.deleteByKey(boss)
        }
        yMap.remove(y)
        commonBossByXY.deleteByKey(boss)
    }

    // 根据XY找到一个BOSS
    fun findCommonBossByXY(x: Int, y: Int): CommonBoss? {
        val gridId = calBossGrid(x, y)
        val bossData = commonBossByGrid.findByKey(gridId) ?: return null
        val yMap = bossData.bossDataMap[x] ?: return null
        return yMap[y] ?: return null
    }

    fun delAllCommonBossCell() {
        commonBossByGrid.map {
            it.bossDataMap.clear()
            true
        }
        commonBossByPlayerId.clear()
        commonBossByXY.clear()
        personalDamageRank.clear()
        allianceDamageRank.clear()
    }

    //根据坐标查找个人排行信息
    fun findPersonalCommonBossRankByXY(x: Int, y: Int): PersonalCommonBossDamageRank? {
        return personalDamageRank.findByKey(x, y)
    }

    fun createAllianceCommonBossRank(rank: AllianceCommonBossDamageRank) {
        return allianceDamageRank.insertByKey(rank)
    }

    //根据坐标查找联盟排行信息
    fun findAllianceCommonBossRankByXY(x: Int, y: Int): AllianceCommonBossDamageRank? {
        return allianceDamageRank.findByKey(x, y)
    }

    //查找个人所有攻击记录
    fun findPersonalAllAtkBoss(playerId: Long): List<CommonBoss> {
        val allBoss = LinkedList<CommonBoss>()
        val playerRecords = commonBossByPlayerId[playerId] ?: return allBoss
        playerRecords.map {
            allBoss.add(it)
        }
        return allBoss
    }

    //更新个人攻击魔物记录
    fun updatePersonalAtkBoss(playerId: Long, boss: CommonBoss) {
        val playerRecords = commonBossByPlayerId.getOrPut(playerId) { TwoKeyIndex({ it.x }, { it.y }) }
        playerRecords.insertByKey(boss)
    }

    //计算消失时间
    fun calOverTime(x: Int, y: Int): Long {
        val gridId = calBossGrid(x, y)
        return areaCache.areaOnlyCache.findAreaOnly().nextRefBossTime + gridId * REFRESH_INTERVAL
    }
}

//根据坐标计算生态格子Id
fun calBossGrid(x: Int, y: Int): Int {
    val length = pcs.basicProtoCache.monsterArea

    val xx = x / length
    val yy = y / length
    return yy * pcs.basicProtoCache.allArea / length + xx
}