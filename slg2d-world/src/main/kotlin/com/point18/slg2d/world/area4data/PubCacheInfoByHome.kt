package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.worldentities.INFO_BY_HOME_QUERY
import com.point18.slg2d.common.worldentities.InfoByHomeEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 玩家来自home同步的数据
class InfoByHome(
    var worldId: Long,
    var id: Long,

    var nowSkinId: Int, // 玩家当前正在使用的皮肤模版ID
    var vipLv: Int,
    var playerId: Long    // 玩家Id
) : EntityWrapper<InfoByHomeEntity> {
    var buildInfoMap = hashMapOf<Int, LinkedList<Int>>() // 一些需要用到的建筑信息  KEY:建筑类型 VALUE : 最高等级
    var effectMap: HashMap<Int, HashMap<Int, Int>> = hashMapOf()     //在home服的效果
    var finishTasks: HashMap<Int, Int> = hashMapOf() // key:任务模版ID  value :1

    constructor() : this(0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): InfoByHomeEntity {
        return InfoByHomeEntity(
            worldId,
            id,
            nowSkinId,
            vipLv,
            toJson(buildInfoMap),
            toJson(effectMap),
            toJson(finishTasks),
            playerId
        )
    }

    override fun wrap(entity: InfoByHomeEntity) {
        worldId = entity.worldId
        id = entity.id
        playerId = entity.playerId
        nowSkinId = entity.nowSkinId
        vipLv = entity.vipLv
        buildInfoMap = toObj(entity.buildInfo)
        effectMap = toObj(entity.effectInfo)
        finishTasks = toObj(entity.finishTasks)
    }

    //查找建筑最高等级
    fun findBuildingMaxLv(buildingType: Int): Int {
        val buildingList = this.buildInfoMap[buildingType] ?: return 0
        return buildingList.max() ?: 0
    }

    //获取效果值
    fun getEffectByType(effectType: Int, effectId: Int): Int {
        val effMap = effectMap[effectType] ?: return 0
        return effMap[effectId] ?: 0
    }
}

class CacheInfoByHome(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val infoByHomeMap = OneKeyIndex { it: InfoByHome -> it.playerId }          // 缓存

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.infoByHomeEntities =
                session.getNamedQuery(INFO_BY_HOME_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.infoByHomeEntities.forEach { entity ->
            try {
                val c = db.wdb.recover(entity) { InfoByHome() }

                this.infoByHomeMap.insertByKey(c)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    // 根据玩家找到数据
    fun findInfoByHomeEntityByPlayerId(playerId: Long): (InfoByHomeEntity?) {
        val info = infoByHomeMap.findByKey(playerId)
        return info?.toEntity()
    }

    fun createInfoByHomeByMoveServer(b: InfoByHomeEntity) {
        val infoByHome = InfoByHome()
        infoByHome.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        infoByHome.worldId = worldId
        infoByHome.id = id

        insert(areaCache, infoByHome)

        infoByHomeMap.insertByKey(infoByHome)
    }

    // 移除某个玩家的所有数据
    fun clearArmyPlanForMoveServer(playerId: Long) {
        val del = findInfoByHomeByPlayerId(playerId)
        if (del != null) {
            delete(areaCache, del)
            // 从缓存中删除
            infoByHomeMap.deleteByKey(del)
        }
    }

    // 创建
    fun createInfoByHome(playerId: Long): InfoByHome {
        val id = wpm.generateObjIdNew(areaCache)
        val info = InfoByHome(
            worldId,
            id,
            0,
            1,
            playerId
        )

        insert(areaCache, info)

        infoByHomeMap.insertByKey(info)

        return info
    }

    // 根据玩家找到数据
    fun findInfoByHomeByPlayerId(playerId: Long): (InfoByHome?) {
        return infoByHomeMap.findByKey(playerId)
    }
}

