package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.mcache.ThreeKeyIndexSlice
import com.point18.slg2d.common.mcache.TwoKeyIndexSlice
import com.point18.slg2d.common.worldentities.WALK_FORCE_GROUP_NAMED_QUERY
import com.point18.slg2d.common.worldentities.WalkForceGroupEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class WalkForceGroup(
    var worldId: Long,
    var id: Long,

    var isGetMainHero: Int, // 用于PVP胜利之后的回城,是否抓到了敌军领主,要出现一个囚车动画跟着 0- 没有  1-有抓到
    var runningState: Int,  // 行军中/驻扎中
    var stateChangeTime: Long, //状态变化的时间点
    var gotoRunType: Int,  // 前往时的行军类别
    var nowX: Int,  // 这个部队当前在哪里
    var nowY: Int,  // 这个部队当前在哪里
    var massId: Long, //集结Id
    var mainPlayerId: Long  // 这个行军主体的操作权玩家ID
) : EntityWrapper<WalkForceGroupEntity> {
    constructor() : this(
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): WalkForceGroupEntity {
        return WalkForceGroupEntity(
            worldId,
            id,
            isGetMainHero,
            runningState,
            stateChangeTime,
            gotoRunType,
            nowX,
            nowY,
            massId,
            mainPlayerId
        )
    }

    override fun wrap(entity: WalkForceGroupEntity) {
        worldId = entity.worldId
        id = entity.id
        isGetMainHero = entity.isGetMainHero
        runningState = entity.runningState
        stateChangeTime = entity.stateChangeTime
        gotoRunType = entity.gotoRunType
        nowX = entity.nowX
        nowY = entity.nowY
        massId = entity.massId
        mainPlayerId = entity.mainPlayerId
    }

    fun checkHaveSolider(areaCache: AreaCache): Boolean {
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(this.id)
        for (force in forces) {
            for ((_, num) in force.soliderMap) {
                if (num > 0) {
                    return true
                }
            }
        }
        return false
    }

    fun removeHero(areaCache: AreaCache, heroId: Long) {
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(this.id)
        for (force in forces) {
            force.heroIdList.remove(heroId)
        }
    }
}

class CacheWalkForceGroup(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val walkForceGroupMapById = OneKeyIndex<Long, WalkForceGroup> { it.id }
    private val walkForceGroupMapByPlayerId =
        OneKeyIndexSlice<Long, WalkForceGroup>({ it.mainPlayerId }, { ga, gb -> ga.id == gb.id })
    private val walkForceGroupMapByPos =
        TwoKeyIndexSlice<Int, Int, WalkForceGroup>({ it.nowX }, { it.nowY }, { ga, gb -> ga.id == gb.id })
    val walkForceGroupMapByPosAndState =
        ThreeKeyIndexSlice<Int, Int, Int, WalkForceGroup>(
            { it.nowX },
            { it.nowY },
            { it.runningState },
            { ga, gb -> ga.id == gb.id })

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.walkForceGroupEntities =
                    session.getNamedQuery(WALK_FORCE_GROUP_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.walkForceGroupEntities.forEach { entity ->
            try {
                val forceGroup = db.wdb.recover(entity) { WalkForceGroup() }

                this.walkForceGroupMapByPlayerId.insertByKey(forceGroup)
                this.walkForceGroupMapById.insertByKey(forceGroup)
                this.walkForceGroupMapByPos.insertByKey(forceGroup)
                this.walkForceGroupMapByPosAndState.insertByKey(forceGroup)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
        walkForceGroupMapByPosAndState.listMap { groupList ->
            groupList.sortBy { it.stateChangeTime }
        }
    }

    // 查找所有在这个位置的驻扎部队
    fun findStationedWalkForceGroupsByPos(posX: Int, posY: Int): List<WalkForceGroup> {
        val groups = LinkedList<WalkForceGroup>()
        walkForceGroupMapByPos.findByKey(posX, posY) {
            if (it.runningState != Running) {
                groups.add(it)
            }
            true
        }
        return groups
    }

    fun changeGroupState(walkForceGroup: WalkForceGroup, state: Int) {
        walkForceGroupMapByPosAndState.deleteByKey(walkForceGroup)
        walkForceGroup.runningState = state
        walkForceGroup.stateChangeTime = getNowTime()
        walkForceGroupMapByPosAndState.insertByKey(walkForceGroup)
    }

    // 创建行军组
    fun createWalkForceGroup(
        mainPlayerId: Long,
        isGetMainHero: Int,
        runningState: Int,
        gotoRunType: Int,
        nowX: Int,
        nowY: Int,
        massId: Long = 0
    ): WalkForceGroup {
        val id = wpm.generateObjIdNew(areaCache)
        val walkForceGroup = WalkForceGroup(
            worldId,
            id,
            isGetMainHero,
            runningState,
            getNowTime(),
            gotoRunType,
            nowX,
            nowY,
            massId,
            mainPlayerId
        )

        insert(areaCache, walkForceGroup)

        // 添加到缓存中
        walkForceGroupMapByPlayerId.insertByKey(walkForceGroup)
        walkForceGroupMapById.insertByKey(walkForceGroup)
        walkForceGroupMapByPos.insertByKey(walkForceGroup)
        walkForceGroupMapByPosAndState.insertByKey(walkForceGroup)

        return walkForceGroup
    }

    fun delWalkForceGroup(walkForceGroupWrap: WalkForceGroup) {
        if (walkForceGroupWrap.id == 0L) {
            return
        }

        delete(areaCache, walkForceGroupWrap)

        // 删除缓存
        walkForceGroupMapByPlayerId.deleteByKey(walkForceGroupWrap)
        walkForceGroupMapById.deleteByKey(walkForceGroupWrap)
        walkForceGroupMapByPos.deleteByKey(walkForceGroupWrap)
        walkForceGroupMapByPosAndState.deleteByKey(walkForceGroupWrap)
    }

    // 根据遗迹战斗信息的Id获取信息
    fun findWalkForceGroupById(id: Long): WalkForceGroup? {
        return walkForceGroupMapById.findByKey(id)
    }

    // 查找所有在这个位置的部队
    fun findWalkForceGroupsByPos(posX: Int, posY: Int): List<WalkForceGroup> {
        val groups = LinkedList<WalkForceGroup>()
        walkForceGroupMapByPos.findByKey(posX, posY) { groups.add(it) }
        return groups
    }

    // 根据位置和状态查询部队
    fun findWalkForceGroupsByPosAndState(
        posX: Int,
        posY: Int,
        state: Int
    ): List<WalkForceGroup> {
        val groups = LinkedList<WalkForceGroup>()
        // 驻守中的队伍
        walkForceGroupMapByPos.findByKey(posX, posY) {
            when (it.runningState) {
                state and Stationed -> groups.add(it)
                state and Reinforce -> groups.add(it)
                state and Farming -> groups.add(it)
                state and WaitFight -> groups.add(it)
            }
            true
        }
        // 行军中的部队
        if (state and Running == Running) {
            val walks = areaCache.walkCache.findWalksByGotoXy(posX, posY)
            walks.forEach {
                val group = findWalkForceGroupById(it.walkForceGroupId)
                if (group == null) {
                    //Assert
                    return@forEach
                }
                groups.add(group)
            }
        }
        return groups
    }

    // 根据玩家Id查询行军组
    fun findWalkForceGroupsByPlayerId(playerId: Long): List<WalkForceGroup> {
        val groups = LinkedList<WalkForceGroup>()
        walkForceGroupMapByPlayerId.findByKey(playerId) { groups.add(it) }
        return groups
    }

    // 更新行军组位置
    fun updateWalkForceGroupPos(group: WalkForceGroup, posX: Int, posY: Int) {
        walkForceGroupMapByPos.updateByKey(posX, posY, group) {
            group.nowX = posX
            group.nowY = posY
        }
    }

    // 获取行军组中士兵数量
    fun getSoliderNumInGroup(groupId: Long): Int {
        var totalNum = 0
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(groupId)
        forces.forEach {
            totalNum += it.getSoliderNumInForce()
        }
        return totalNum
    }
}