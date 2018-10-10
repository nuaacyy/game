package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.worldentities.ARMY_PLAN_NAMED_QUERY
import com.point18.slg2d.common.worldentities.ArmyPlanEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 部队预设
class ArmyPlan(
        var worldId: Long,
        var id: Long,

        var bigTarget: Int,            // 大目标 1、竞技场 2、推图 3、魔物
        var smallTarget: Int,          // 小目标 竞技场进攻/防守 魔物1...N
        var heroMap: HashMap<Int, Long>,
        var playerId: Long            // 玩家ID
) : EntityWrapper<ArmyPlanEntity> {

    constructor() : this(0, 0, 0, 0, hashMapOf(), 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): ArmyPlanEntity {
        return ArmyPlanEntity(
                worldId,
                id,
                bigTarget,
                smallTarget,
                toJson(heroMap),
                playerId
        )
    }

    override fun wrap(entity: ArmyPlanEntity) {
        worldId = entity.worldId
        id = entity.id
        bigTarget = entity.bigTarget
        smallTarget = entity.smallTarget
        heroMap = toObj(entity.heroInfo)
        playerId = entity.playerId
    }
}

class CacheArmyPlan(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val armyPlanMap =
            OneKeyIndexSlice({ it: ArmyPlan -> it.playerId }, { ita: ArmyPlan, itb: ArmyPlan -> ita.id == itb.id })

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.armyPlanEntities =
                    session.getNamedQuery(ARMY_PLAN_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.armyPlanEntities.forEach { entity ->
            try {
                val plan = db.wdb.recover(entity) { ArmyPlan() }

                armyPlanMap.insertByKey(plan)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    //查找部队预设
    fun findMyArmyPlanEntityList(playerId: Long): (LinkedList<ArmyPlanEntity>) {
        val armyPlans = LinkedList<ArmyPlanEntity>()

        armyPlanMap.findByKey(playerId) {
            armyPlans.add(it.toEntity())
            true
        }

        return armyPlans
    }

    fun createArmyPlanByMoveServer(a: ArmyPlanEntity) {
        val armyPlan = ArmyPlan()
        armyPlan.wrap(a)
        val id = wpm.generateObjIdNew(areaCache)
        armyPlan.worldId = worldId
        armyPlan.id = id

        insert(areaCache, armyPlan)

        // 存入缓存
        armyPlanMap.insertByKey(armyPlan)
    }

    // 移除某个玩家的所有数据
    fun clearArmyPlanForMoveServer(playerId: Long) {
        val delList = findMyArmyPlans(playerId)
        for (del in delList) {
            delete(areaCache, del)

            // 从缓存中删除
            armyPlanMap.deleteByKey(del)
        }
    }

    //查找部队预设
    private fun findMyArmyPlans(playerId: Long): (LinkedList<ArmyPlan>) {
        val armyPlans = LinkedList<ArmyPlan>()

        armyPlanMap.findByKey(playerId) {
            armyPlans.add(it)
            true
        }

        return armyPlans
    }

    //创建部队预设
    fun createArmyPlan(
            playerId: Long,
            bigTarget: Int,
            smallTarget: Int,
            heroMap: HashMap<Int, Long>
    ): ArmyPlan {
        val id = wpm.generateObjIdNew(areaCache)
        val armyPlan = ArmyPlan(worldId, id, bigTarget, smallTarget, heroMap, playerId)
        insert(areaCache, armyPlan)

        // 存入缓存
        armyPlanMap.insertByKey(armyPlan)
        return armyPlan
    }

    //查找部队预设
    fun findArmyPlan(playerId: Long, bigTarget: Int, smallTarget: Int): (ArmyPlan?) {
        var armyPlan: ArmyPlan? = null

        armyPlanMap.findByKey(playerId) {
            if (it.bigTarget == bigTarget && it.smallTarget == smallTarget) {
                armyPlan = it
            }
            !(it.bigTarget == bigTarget && it.smallTarget == smallTarget)
        }

        return armyPlan
    }

    fun findFirstArmyPlan(playerId: Long, bigTarget: Int): (ArmyPlan?) {
        var armyPlan: ArmyPlan? = null

        armyPlanMap.findByKey(playerId) {
            if (it.bigTarget == bigTarget) {
                armyPlan = it
                return@findByKey false
            }
            true
        }
        return armyPlan
    }
}
