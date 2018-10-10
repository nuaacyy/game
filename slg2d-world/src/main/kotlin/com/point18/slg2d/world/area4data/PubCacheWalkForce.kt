package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.worldentities.WALK_FORCE_NAMED_QUERY
import com.point18.slg2d.common.worldentities.WalkForceEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

/**
行军主体中的单个部队
 */
class WalkForce(
    var worldId: Long,
    var id: Long,

    var resFromInfo: String,  // 资源来源，掠夺/采集/运输
    var forceGroupId: Long,  // 隶属于哪个行军主体
    var playerId: Long  // 部队主人
) : EntityWrapper<WalkForceEntity> {
    var heroIdList: LinkedList<Long> = LinkedList()
    var soliderMap: HashMap<Int, Int> = hashMapOf()
    var initialSoliderMap: HashMap<Int, Int> = hashMapOf()
    var resVo: LinkedList<ResVo> = LinkedList()

    constructor() : this(
        0, 0, "", 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): WalkForceEntity {
        return WalkForceEntity(
            worldId,
            id,
            toJson(heroIdList),
            toJson(soliderMap),
            toJson(initialSoliderMap),
            resFromInfo,
            resVoToResString(resVo),
            forceGroupId,
            playerId
        )
    }

    override fun wrap(entity: WalkForceEntity) {
        worldId = entity.worldId
        id = entity.id
        resFromInfo = entity.resFromInfo
        forceGroupId = entity.forceGroupId
        playerId = entity.playerId
        heroIdList = toObj(entity.heroIds)
        soliderMap = toObj(entity.soliders)
        initialSoliderMap = toObj(entity.initialSoliders)

        val resList = resStringToResVoList(entity.res)
        if (resList == null) {
            assert(false) { "WalkForce中的res字段反编译错误:${entity.res}" }
        } else {
            resVo = LinkedList(resList)
        }

    }

    fun checkHaveSolider(): Boolean {
        for ((_, num) in this.soliderMap) {
            if (num > 0) {
                return true
            }
        }
        return false
    }

    fun putSoliderMap(soliderMap: HashMap<Int, Int>) {
        this.soliderMap = soliderMap
    }

    fun putResVo(resVo: LinkedList<ResVo>) {
        this.resVo = resVo
    }

    // 获取部队中士兵数量
    fun getSoliderNumInForce(): Int {
        var totalNum = 0
        this.soliderMap.forEach { totalNum += it.value }
        return totalNum
    }
}

class CacheWalkForce(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val walkForceMapByPlayerId = OneKeyIndexSlice<Long, WalkForce>({ it.playerId }, { fA, fB -> fA.id == fB.id })
    private val walkForceMapByForceGroupId =
        OneKeyIndexSlice<Long, WalkForce>({ it.forceGroupId }, { fA, fB -> fA.id == fB.id })

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.walkForceEntities =
                session.getNamedQuery(WALK_FORCE_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.walkForceEntities.forEach { entity ->
            try {
                val walkForce = db.wdb.recover(entity) { WalkForce() }

                this.walkForceMapByPlayerId.insertByKey(walkForce)
                this.walkForceMapByForceGroupId.insertByKey(walkForce)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    // 保存新遗迹战斗信息
    fun createWalkForce(
            heroIds: LinkedList<Long>,
            soliderMap: HashMap<Int, Int>,
            resFromInfo: String,
            res: LinkedList<ResVo>,
            walkForceGroupId: Long,
            playerId: Long
    ): WalkForce {
        val id = wpm.generateObjIdNew(areaCache)
        val walkForce = WalkForce(
            worldId,
            id,
            resFromInfo,
            walkForceGroupId,
            playerId
        )
        walkForce.resVo = res
        walkForce.heroIdList = heroIds
        walkForce.soliderMap = soliderMap
        walkForce.initialSoliderMap = soliderMap

        insert(areaCache, walkForce)

        // 添加到缓存中
        walkForceMapByPlayerId.insertByKey(walkForce)
        walkForceMapByForceGroupId.insertByKey(walkForce)

        return walkForce
    }

    fun delWalkForce(walkForce: WalkForce) {
        if (walkForce.id == 0L) {
            return
        }

        delete(areaCache, walkForce)

        // 删除缓存
        walkForceMapByPlayerId.deleteByKey(walkForce)
        walkForceMapByForceGroupId.deleteByKey(walkForce)
    }

    // 根据行军主体获取所有行军部队
    fun findWalkForceByWalkForceGroupId(walkForceGroupId: Long): List<WalkForce> {
        // 尝试从缓存中获取
        val walkForceWraps = LinkedList<WalkForce>()
        walkForceMapByForceGroupId.findByKey(walkForceGroupId) { walkForceWraps.add(it) }

        return walkForceWraps
    }

    //查找行军组的主部队
    fun findMainForceByGroupId(groupId: Long): WalkForce? {
        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(groupId)
        if (group == null) {
            return null
        }

        var mainForce: WalkForce? = null
        walkForceMapByForceGroupId.findByKey(groupId) {
            if (it.playerId == group.mainPlayerId) {
                mainForce = it
                return@findByKey false
            }
            true
        }
        return mainForce
    }

    // 修改WalkForce所绑定的Group
    fun updateWalkForceGroupId(walkForce: WalkForce, walkForceGroupId: Long) {
        // 尝试从缓存中获取
        walkForceMapByForceGroupId.updateByKey(walkForceGroupId, walkForce) {
            walkForce.forceGroupId = walkForceGroupId
        }
    }

    //查找玩家的部队
    fun findWalkForceByPlayerId(playerId: Long): List<WalkForce> {
        // 尝试从缓存中获取
        val walkForceWraps = LinkedList<WalkForce>()
        walkForceMapByPlayerId.findByKey(playerId) { walkForceWraps.add(it) }

        return walkForceWraps
    }

}
