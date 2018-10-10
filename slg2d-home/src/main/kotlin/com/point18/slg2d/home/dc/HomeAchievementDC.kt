package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.AchieveHasGetReward
import com.point18.slg2d.common.constg.AchievegoAlong
import com.point18.slg2d.common.constg.LAST_HOME_TASK_CHECK
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.homeentities.HOME_ACHIVEMENT_NAMED_QUERY
import com.point18.slg2d.common.homeentities.HomeAchievementEntity
import com.point18.slg2d.common.homeentities.HomeAchievementPK
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.hpm
import org.hibernate.Session
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

class HomeAchievementDC : AbstractDataContainer<List<HomeAchievementEntity>>() {
    val achievementTask = mutableMapOf<Long, HomeAchievement>()

    override fun initImpl(data: List<HomeAchievementEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val task = wdb.recover(it) { HomeAchievement() }

            achievementTask[task.id] = task
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<HomeAchievementEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(HOME_ACHIVEMENT_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<HomeAchievementEntity>()
            list
        }
        return data
    }

    fun createHomeAchievement(
        session: PlayerActor,
        playerId: Long,
        achievementId: Int,
        progressMap: HashMap<Int, Long>
    ): (HomeAchievement?) {
        val taskProto = pcs.achievementProtoCache.achievementProtoMap[achievementId]
        if (taskProto == null || taskProto.completeCondMap.size != 1) {
            return null
        }

        for ((checkType, _) in taskProto.completeCondMap) {
            if (checkType > LAST_HOME_TASK_CHECK) {
                return null
            }
        }
        val id = hpm.generateObjIdNew()
        val achievement = HomeAchievement(id, achievementId, AchievegoAlong, progressMap, playerId)
        wdb.save(achievement)
        achievementTask[achievement.id] = achievement

        return achievement
    }

    //根据Id查询成就
    fun findAchievementById(session: PlayerActor, id: Long): (HomeAchievement?) {
        return achievementTask[id]
    }

    // 根据玩家ID拿成就信息
    fun findAchievementsByPlayerId(session: PlayerActor): (Map<Int, HomeAchievement>) {
        val achievementMap = hashMapOf<Int, HomeAchievement>()
        for ((_, a) in achievementTask) {
            achievementMap[a.achievementId] = a
        }
        return achievementMap
    }

    fun findAchievementTypeMapByPlayerId(session: PlayerActor): (HashMap<Int, HomeAchievement>) {
        val achievementMap = hashMapOf<Int, HomeAchievement>()

        for ((_, a) in achievementTask) {
            val achievementProto = pcs.achievementProtoCache.achievementProtoMap[a.achievementId]
            if (achievementProto == null) {
                continue
            }
            achievementMap[achievementProto.achieveType] = a
        }

        return achievementMap
    }

    //进入下一星成就
    fun intoNextStarAchievement(achievement: HomeAchievement): (HomeAchievement?) {
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

    fun initAchievementInfo(session: PlayerActor) {
        val achievementTypeMap = findAchievementTypeMapByPlayerId(session)
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
                    val newAchievement = createHomeAchievement(session, session.playerId, a1.id, progressMap)
                    if (newAchievement != null) {
                        achievementTypeMap[achievementType] = newAchievement
                    }
                }
            }
        }
    }
}

// 玩家home的成就
class HomeAchievement(
    var id: Long,

    var achievementId: Int,  // 成就配置Id
    var state: Int,  // 成就状态 0-进行中 1-已完成 2-已领取奖励
    var progressMap: HashMap<Int, Long>,

    var playerId: Long
) : EntityWrapper<HomeAchievementEntity> {
    constructor() : this(0, 0, 0, hashMapOf(), 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = HomeAchievementPK(playerId, id)

    override fun toEntity(): HomeAchievementEntity {
        return HomeAchievementEntity(
            id,
            achievementId,
            state,
            toJson(progressMap),
            playerId
        )
    }

    override fun wrap(entity: HomeAchievementEntity) {
        id = entity.id
        achievementId = entity.achievementId
        state = entity.state
        progressMap = toObj(entity.progress)
        playerId = entity.playerId
    }
}

fun createHomeAchievementByEnterGame(ioSession: Session, playerId: Long, achievementId: Int): (HomeAchievement?) {
    val taskProto = pcs.achievementProtoCache.achievementProtoMap[achievementId]
    if (taskProto == null || taskProto.completeCondMap.size != 1) {
        return null
    }

    for ((checkType, _) in taskProto.completeCondMap) {
        if (checkType > LAST_HOME_TASK_CHECK) {
            return null
        }
    }
    val id = hpm.generateObjIdNew()
    val achievement = HomeAchievement(id, achievementId, AchievegoAlong, hashMapOf(), playerId)
    ioSession.save(achievement.toEntity())

    return achievement
}
