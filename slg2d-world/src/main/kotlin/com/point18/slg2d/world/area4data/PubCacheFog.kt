package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.NotWin
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.FOG_NAMED_QUERY
import com.point18.slg2d.common.worldentities.FogEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.common.calHeroPower
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 迷雾信息
class Fog(
    var worldId: Long,
    var id: Long,
    var playerId: Long,
    var fogId: Int,
    var state: Int,   //迷雾状态 1、未战胜 2、已战胜未领取奖励 3、已战胜且领取奖励
    var soliderMap: HashMap<Int, Int>

) : EntityWrapper<FogEntity> {
    constructor() : this(0, 0, 0, 0, 0, hashMapOf())

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): FogEntity {
        return FogEntity(
            worldId,
            id,
            playerId,
            fogId,
            state,
            toJson(soliderMap)
        )
    }

    override fun wrap(entity: FogEntity) {
        worldId = entity.worldId
        id = entity.id
        playerId = entity.playerId
        fogId = entity.fogId
        state = entity.state
        soliderMap = toObj(entity.soliderInfo)
    }

    /**
     * 计算迷雾战斗力
     */
    fun calFogPower(): Long {
        if (this.state != NotWin) {
            return 0
        }
        val fogProto = pcs.mapOpenProtoCache.mapOpenMap[this.fogId]
        if (fogProto == null) {
            assert(false)
            return 0
        }
        val soliderTeamProto = pcs.soliderTeamProtoCache.soliderTeamMap[fogProto.army]
        if (soliderTeamProto == null) {
            assert(false)
            return 0
        }
        var heroPower = 0L
        soliderTeamProto.heroMap.forEach { _, hero ->
            val heroProto = pcs.unitBaseCache.fetchProtoById(hero.protoId)
            if (heroProto == null) {
                return@forEach
            }
            heroPower += calHeroPower(
                hero.protoId,
                hero.lv,
                hero.star,
                hero.awake,
                heroProto.skill1,
                heroProto.skill2,
                heroProto.skill3,
                heroProto.skill4
            )
        }
        var soliderPower = 0L
        this.soliderMap.forEach { soliderId, soliderNum ->
            val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
            if (soliderProto == null) {
                return@forEach
            }
            soliderPower += soliderProto.power * soliderNum
        }
        return heroPower + soliderPower
    }
}

class CacheFog(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val fogMap = TwoKeyIndex<Long, Int, Fog>({ it.playerId }, { it.fogId })

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.fogEntities =
                session.getNamedQuery(FOG_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.fogEntities.forEach { entity ->
            try {
                val fog = db.wdb.recover(entity) { Fog() }
                fogMap.insertByKey(fog)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    /**
     * 根据玩家Id查找迷雾信息
     */
    fun findFogEntityByPlayerId(playerId: Long): LinkedList<FogEntity> {
        val fogList = LinkedList<FogEntity>()
        fogMap.findByOneKeyFilter(playerId) {
            fogList.add(it.toEntity())
        }
        return fogList
    }

    fun createFogByMoveServer(f: FogEntity) {
        val fog = Fog()
        fog.wrap(f)
        val id = wpm.generateObjIdNew(areaCache)
        fog.worldId = worldId
        fog.id = id

        insert(areaCache, fog)

        // 添加到缓存中
        fogMap.insertByKey(fog)
    }

    // 移除某个玩家的所有数据
    fun clearFogForMoveServer(playerId: Long) {
        val delList = findFogByPlayerId(playerId)
        for (del in delList) {
            delete(areaCache, del)

            // 从缓存中删除
            fogMap.deleteByKey(del)
        }
    }

    //创建迷雾
    fun createFog(playerId: Long) {
        for ((_, vo) in pcs.mapOpenProtoCache.mapOpenMap) {
            val army = pcs.soliderTeamProtoCache.soliderTeamMap[vo.army] ?: continue
            val id = wpm.generateObjIdNew(areaCache)
            val soliderMap = hashMapOf<Int, Int>()
            army.soliderMap.forEach {
                soliderMap[it.key] = it.value
            }
            val fog = Fog(worldId, id, playerId, vo.unitId, NotWin, soliderMap)
            insert(areaCache, fog)
            fogMap.insertByKey(fog)
        }
    }

    /**
     * 根据玩家Id查找迷雾信息
     */
    fun findFogByPlayerId(playerId: Long): List<Fog> {
        val fogList = LinkedList<Fog>()
        fogMap.findByOneKeyFilter(playerId) {
            fogList.add(it)
        }
        return fogList
    }

    /**
     * 根据玩家Id和迷雾Id查找迷雾
     */
    fun findFogById(playerId: Long, fogId: Int): Fog? {
        return fogMap.findByKey(playerId, fogId)
    }

}

