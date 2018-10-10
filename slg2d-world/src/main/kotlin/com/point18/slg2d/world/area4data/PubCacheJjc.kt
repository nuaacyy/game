package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.JJC_NAMED_QUERY
import com.point18.slg2d.common.worldentities.JjcEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

// 竞技场
class Jjc(
    var worldId: Long,
    var id: Long,
    var lastFightTime: Long,  //上次挑战时间
    var nextFightTime: Long,  //下次挑战时间  todo 现在没有cd了
    var maxRank: Int,  // 玩家竞技场历史最高排名
    var rank1: Int,  // 挑战对手排名1（低，数值大）
    var rank2: Int,  // 挑战对手排名2（中，数值中）
    var rank3: Int,  // 挑战对手排名3（高，数值小）
    var score: Int,  // 积分
    var scoreTime: Long,  // 积分计算时间
    var playerId: Long    // 玩家ID
) : EntityWrapper<JjcEntity> {

    constructor() : this(
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): JjcEntity {
        return JjcEntity(
            worldId,
            id,
            lastFightTime,
            nextFightTime,
            maxRank,
            rank1,
            rank2,
            rank3,
            score,
            scoreTime,
            playerId
        )
    }

    override fun wrap(entity: JjcEntity) {
        worldId = entity.worldId
        id = entity.id
        lastFightTime = entity.lastFightTime
        nextFightTime = entity.nextFightTime
        maxRank = entity.maxRank
        rank1 = entity.rank1
        rank2 = entity.rank2
        rank3 = entity.rank3
        score = entity.score
        scoreTime = entity.scoreTime
        playerId = entity.playerId
    }
}

class CacheJjc(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val jjcMap = OneKeyIndex { it: Jjc -> it.playerId }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.jjcEntities =
                session.getNamedQuery(JJC_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.jjcEntities.forEach { entity ->
            try {
                val jjc = db.wdb.recover(entity) { Jjc() }

                this.jjcMap.insertByKey(jjc)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun createJjc(playerId: Long, jjcRank: Int): Jjc {
        val id = wpm.generateObjIdNew(areaCache)
        val nowTime = 0L
        val jjc = Jjc(
            worldId,
            id,
            nowTime,
            0,
            jjcRank,
            0,
            0,
            0,
            0,
            nowTime,
            playerId
        )
        jjcMap.insertByKey(jjc)
        insert(areaCache, jjc)
        return jjc
    }

    fun findJjc(playerId: Long): (Jjc?) {
        return jjcMap.findByKey(playerId)
    }
}

// 根据排名获取竞技场机器人的所有组怪物的信息
fun findJjcRobotByRank(rank: Int): Int {
    return pcs.jjcNpcCache.jjcRobot[rank] ?: 0
}


