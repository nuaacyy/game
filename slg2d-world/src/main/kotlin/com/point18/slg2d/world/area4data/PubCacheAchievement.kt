package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.AchieveHasGetReward
import com.point18.slg2d.common.constg.AchievegoAlong
import com.point18.slg2d.common.constg.LAST_HOME_TASK_CHECK
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.ACHIVEMENT_NAMED_QUERY
import com.point18.slg2d.common.worldentities.AchievementEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 玩家成就数据
class Achievement(
    var worldId: Long,
    var id: Long,

    var achievementId: Int,  // 成就配置Id
    var state: Int,  // 成就状态 0-进行中 1-已完成 2-已领取奖励
    var progressMap: HashMap<Int, Long>,
    var playerId: Long           // 玩家ID
) : EntityWrapper<AchievementEntity> {
    constructor() : this(0, 0, 0, 0, hashMapOf(), 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AchievementEntity {
        return AchievementEntity(
            worldId,
            id,
            achievementId,
            state,
            toJson(progressMap),
            playerId
        )
    }

    override fun wrap(entity: AchievementEntity) {
        worldId = entity.worldId
        id = entity.id
        achievementId = entity.achievementId
        state = entity.state
        progressMap = toObj(entity.progress)
        playerId = entity.playerId
    }

}

class CacheAchievement(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private var achievementMapByPlayer = TwoKeyIndex({ achievement: Achievement -> achievement.playerId },
        { achievement: Achievement -> achievement.id })

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.achievements =
                session.getNamedQuery(ACHIVEMENT_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.achievements.forEach { entity ->
            try {
                val achievment = db.wdb.recover(entity) { Achievement() }

                this.achievementMapByPlayer.insertByKey(achievment)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun findAchievementEntitiesByPlayerId(playerId: Long): (LinkedList<AchievementEntity>) {
        val achievementMap = LinkedList<AchievementEntity>()

        achievementMapByPlayer.findByOneKeyFilter(playerId) { it: Achievement ->
            achievementMap.add(it.toEntity())
            true
        }

        return achievementMap
    }

    //创建成就
    fun createAchievementByMoveServer(achievementEntity: AchievementEntity) {
        val achievement = Achievement()
        achievement.wrap(achievementEntity)
        val id = wpm.generateObjIdNew(areaCache)
        achievement.worldId = worldId
        achievement.id = id

        insert(areaCache, achievement)

        // 存入缓存
        achievementMapByPlayer.insertByKey(achievement)
    }

    // 移除某个玩家的所有数据
    fun clearAchievementForMoveServer(playerId: Long) {
        val delMap = findAchievementsByPlayerId(playerId)
        for ((_, del) in delMap) {
            delete(areaCache, del)

            // 从缓存中删除
            achievementMapByPlayer.deleteByKey(del)
        }
    }

    //创建成就
    fun createAchievement(playerId: Long, achievementId: Int, progressMap: HashMap<Int, Long>): (Achievement?) {
        val achievementProto = pcs.achievementProtoCache.achievementProtoMap[achievementId]
        if (achievementProto == null || achievementProto.completeCondMap.size != 1) {
            return null
        }

        for ((checkType, _) in achievementProto.completeCondMap) {
            if (checkType <= LAST_HOME_TASK_CHECK) {
                return null
            }
        }

        val id = wpm.generateObjIdNew(areaCache)
        val achievement = Achievement(worldId, id, achievementId, AchievegoAlong, progressMap, playerId)

        // todo DB存储
        insert(areaCache, achievement)

        // 存入缓存
        achievementMapByPlayer.insertByKey(achievement)

        return achievement
    }

    fun initAchievementInfo(playerId: Long) {
        val achievementTypeMap = findAchievementTypeMapByPlayerId(playerId)
        for ((achievementType, achievements) in pcs.achievementProtoCache.achievementProtoMapByType) {
            val a1 = achievements[1]
            if (a1 == null) {
                continue
            }
            val ex = achievementTypeMap[achievementType]
            if (ex == null) {
                //补全成就
                val achievementProto = pcs.achievementProtoCache.achievementProtoMap[a1.id]
                if (achievementProto != null) {
                    val progressMap = hashMapOf<Int, Long>()
                    for ((checkType, _) in achievementProto.completeCondMap) {
                        progressMap[checkType] = 0
                    }
                    val newAchievement = createAchievement(playerId, a1.id, progressMap)
                    if (newAchievement != null) {
                        achievementTypeMap[achievementType] = newAchievement
                    }
                }
            }
        }
    }

    //根据Id查询成就
    fun findAchievementById(playerId: Long, id: Long): (Achievement?) {
        return achievementMapByPlayer.findByKey(playerId, id)
    }

    // 根据玩家ID拿成就信息
    fun findAchievementsByPlayerId(playerId: Long): (Map<Int, Achievement>) {
        val achievementMap = hashMapOf<Int, Achievement>()
        achievementMapByPlayer.findByOneKeyFilter(playerId) {
            achievementMap[it.achievementId] = it
            true
        }
        return achievementMap
    }

    fun findAchievementTypeMapByPlayerId(playerId: Long): (HashMap<Int, Achievement>) {
        val achievementMap = hashMapOf<Int, Achievement>()

        achievementMapByPlayer.findByOneKeyFilter(playerId) {
            val achievementProto = pcs.achievementProtoCache.achievementProtoMap[it.achievementId]
            if (achievementProto != null) {
                achievementMap[achievementProto.achieveType] = it
            }
            true
        }

        return achievementMap
    }
}

//进入下一星成就
fun intoNextStarAchievement(achievement: Achievement): (Achievement?) {
    if (achievement.state != AchieveHasGetReward) {
        return achievement
    }
    val achievementProto = pcs.achievementProtoCache.achievementProtoMap[achievement.achievementId]
    if (achievementProto == null) {
        return null
    }

    val endCon = achievementProto.endCon
    if (endCon == 0) {
        // 已經完成這個系列所有的成就任務
        return achievement
    }
    val nextAchievementProto = pcs.achievementProtoCache.achievementProtoMap[endCon]
    if (nextAchievementProto == null) {
        // 已經完成這個系列所有的成就任務
        return achievement
    }

    achievement.achievementId = nextAchievementProto.id
    achievement.state = AchievegoAlong
    achievement.progressMap = hashMapOf()

    return achievement
}