package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.RELIC_DATA_NAMED_QUERY
import com.point18.slg2d.common.worldentities.RelicDataEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

// 遗迹信息
class RelicData(
    var worldId: Long,
    var id: Long,

    var gridId: Int,     // 生态格子id
    var relicDataMap: HashMap<Int, HashMap<Int, Relic>> //X-Y-地块数据
) : EntityWrapper<RelicDataEntity> {

    constructor() : this(0, 0, 0, hashMapOf())

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): RelicDataEntity {
        return RelicDataEntity(
            worldId,
            id,
            gridId,
            toJson(relicDataMap)
        )
    }

    override fun wrap(entity: RelicDataEntity) {
        worldId = entity.worldId
        id = entity.id
        gridId = entity.gridId
        relicDataMap = toObj(entity.relicDatas)
    }
}

class Relic(
    var relicId: Int,       //遗迹Id
    var x: Int,             //坐标X
    var y: Int,             //坐标Y
    var isUnScout: Boolean, //是否反侦察
    var timeBoxId: Int,     //掉落的时光之盒
    var lineUpId: Int,        //防守部队Id
    var dropRate: Int       //掉落倍率
)

class CacheRelic(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val relicByGrid = OneKeyIndex<Int, RelicData> { it.gridId }
    private val relicByXY = TwoKeyIndex({ relic: Relic -> relic.x }, { relic: Relic -> relic.y })

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.relicDataEntities =
                    session.getNamedQuery(RELIC_DATA_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.relicDataEntities.forEach { entity ->
            try {
                val relicData = db.wdb.recover(entity) { RelicData() }

                this.relicByGrid.insertByKey(relicData)
                for ((_, yMap) in relicData.relicDataMap) {
                    for ((_, data) in yMap) {
                        this.relicByXY.insertByKey(data)
                    }
                }

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun deleteRelicByXY(x: Int, y: Int) {
        val gridId = calRelicGrid(x, y)
        val relicData = relicByGrid.findByKey(gridId) ?: return
        val yMap = relicData.relicDataMap[x] ?: return
        yMap.remove(y)
    }

    // 根据XY找到一个BOSS
    fun findRelicByXY(x: Int, y: Int): Relic? {
        val gridId = calRelicGrid(x, y)
        val relicData = relicByGrid.findByKey(gridId) ?: return null
        val yMap = relicData.relicDataMap[x] ?: return null
        return yMap[y] ?: return null
    }

    fun createRelic(
        relicId: Int,
        x: Int,
        y: Int,
        isUnScout: Boolean,
        timeBoxId: Int,
        lineUpId: Int,
        dropRate: Int
    ): Relic {
        val relic = Relic(relicId, x, y, isUnScout, timeBoxId, lineUpId, dropRate)
        val gridId = calRelicGrid(x, y)
        var relicData = relicByGrid.findByKey(gridId)
        if (relicData == null) {
            val id = wpm.generateObjIdNew(areaCache)
            relicData = RelicData(worldId, id, gridId, hashMapOf())
            relicByGrid.insertByKey(relicData)
            insert(areaCache, relicData)
        }
        val yMap = relicData.relicDataMap.getOrPut(x) { hashMapOf() }
        yMap[y] = relic
        relicByXY.insertByKey(relic)
        return relic
    }
}


//根据坐标计算生态格子Id
fun calRelicGrid(x: Int, y: Int): Int {
    val length = pcs.basicProtoCache.relicArea

    val xx = x / length
    val yy = y / length
    return yy * pcs.basicProtoCache.allArea / length + xx
}