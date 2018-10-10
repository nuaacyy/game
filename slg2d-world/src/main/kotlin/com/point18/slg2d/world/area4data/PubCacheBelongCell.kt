package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.worldentities.BELONG_CELL_NAMED_QUERY
import com.point18.slg2d.common.worldentities.BelongCellEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class BelongCell(
    var worldId: Long,
    var id: Long,

    var x: Int,         // 所在坐标
    var y: Int,         // 所在坐标
    var overTime: Long,     // 结束时间
    var playerId: Long,  // 归属玩家Id(进攻方玩家Id)
    var defPlayerId: Long, // 防守方玩家Id
    var atkBattleRs: Int,
    var defBattleRs: Int
) : EntityWrapper<BelongCellEntity> {

    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): BelongCellEntity {
        return BelongCellEntity(
            worldId,
            id,
            x,
            y,
            overTime,
            playerId,
            defPlayerId,
            atkBattleRs,
            defBattleRs
        )
    }

    override fun wrap(entity: BelongCellEntity) {
        worldId = entity.worldId
        id = entity.id
        x = entity.x
        y = entity.y
        overTime = entity.overTime
        playerId = entity.playerId
        defPlayerId = entity.defPlayerId
        atkBattleRs = entity.atkBattleRs
        defBattleRs = entity.defBattleRs
    }
}

class CacheBelongCell(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val belongCellByXy = TwoKeyIndex<Int, Int, BelongCell>({ it.x }, { it.y })

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.belongCellEntities =
                session.getNamedQuery(BELONG_CELL_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.belongCellEntities.forEach { entity ->
            try {
                val belongCell = db.wdb.recover(entity) { BelongCell() }

                belongCellByXy.insertByKey(belongCell)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    // 根据XY找到一个归属地块
    fun findBelongCellByXy(x: Int, y: Int): (BelongCell?) {
        return belongCellByXy.findByKey(x, y)
    }

    fun findAllOverBelongCell(): List<BelongCell> {
        val cells = LinkedList<BelongCell>()

        val nowTime = getNowTime()
        belongCellByXy.map {
            if (it.overTime < nowTime) {
                cells.add(it)
            }
            true
        }

        return cells
    }

    // 清除所有记录里某玩家的数据
    fun delAllBelongByPlayerId(playerId: Long) {
        belongCellByXy.map {
            if (it.playerId == playerId) {
                it.playerId = 0
            }
            if (it.defPlayerId == playerId) {
                it.defPlayerId = 0
            }
            true
        }
    }

    fun createBelongCell(
        x: Int,
        y: Int,
        overTime: Long,
        playerId: Long,
        defPlayerId: Long,
        atkBattleRs: Int,
        defBattleRs: Int
    ): BelongCell {
        val id = wpm.generateObjIdNew(areaCache)
        val belongCell = BelongCell(
            worldId,
            id,
            x,
            y,
            overTime,
            playerId,
            defPlayerId,
            atkBattleRs,
            defBattleRs
        )

        insert(areaCache, belongCell)
        belongCellByXy.insertByKey(belongCell)

        return belongCell
    }

    fun deleteBelongCell(belongCell: BelongCell?) {
        if (belongCell == null || belongCell.id == 0L) {
            return
        }

        delete(areaCache, belongCell)

        // 从缓存中删除
        belongCellByXy.deleteByKey(belongCell)
    }
}