package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.COMMON_RES_DATA_NAMED_QUERY
import com.point18.slg2d.common.worldentities.CommonResDataEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 普通资源点信息
data class CommonResData(
    var worldId: Long,
    var id: Long,
    var gridId: Int,     // 生态格子id
    var resDataMap: HashMap<Int, HashMap<Int, CommonRes>> //X-Y-地块数据
) : EntityWrapper<CommonResDataEntity> {

    constructor() : this(0, 0, 0, hashMapOf())

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): CommonResDataEntity {
        return CommonResDataEntity(
            worldId,
            id,
            gridId,
            toJson(resDataMap)
        )
    }

    override fun wrap(entity: CommonResDataEntity) {
        worldId = entity.worldId
        id = entity.id
        gridId = entity.gridId
        resDataMap = toObj(entity.resDatas)
    }
}

class CommonRes(
    var x: Int,         //坐标X
    var y: Int,         //坐标Y
    var resId: Int,     // 资源点配置Id
    var nowResNum: Int, // 剩余资源点值
    var lv: Int,         // 当前等级，原等级可能被黑土地改变
    var groupId: Long,   // 屯田部队ID
    var farmStartTime: Long,  //采集开始时间
    var farmEndTime: Long   // 采集结束时间
)

class CacheCommonRes(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val commonResByGrid = OneKeyIndex<Int, CommonResData> { it.gridId }
    private val commonResByXY = TwoKeyIndex({ Res: CommonRes -> Res.x }, { Res: CommonRes -> Res.y })

    val farmOverPq: Queue<CommonRes> = PriorityQueue { c1, c2 ->
        when {
            c1.farmEndTime > c2.farmEndTime -> 1
            c1.farmEndTime == c2.farmEndTime -> 0
            else -> -1
        }
    }

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.commonResDataEntities =
                    session.getNamedQuery(COMMON_RES_DATA_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.commonResDataEntities.forEach { entity ->
            try {
                val resEntity = db.wdb.recover(entity) { CommonResData() }

                this.commonResByGrid.insertByKey(resEntity)
                for ((_, yMap) in resEntity.resDataMap) {
                    for ((_, data) in yMap) {
                        this.commonResByXY.insertByKey(data)
                        if (data.groupId != 0L) {
                            this.farmOverPq.add(data)
                        }
                    }
                }

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun findCommonResCellsInUse(): List<CommonRes> {
        val farmCellsInUse = LinkedList<CommonRes>()
        commonResByXY.map {
            if (areaCache.walkCache.findWalksByGotoXy(it.x, it.y).isNotEmpty()) {
                farmCellsInUse.add(it)
            } else if (areaCache.walkForceGroupCache.findStationedWalkForceGroupsByPos(it.x, it.y).isNotEmpty()) {
                farmCellsInUse.add(it)
            }
            true
        }
        return farmCellsInUse
    }

    fun createCommonRes(
        x: Int,
        y: Int,
        resId: Int,
        nowResNum: Int,
        resLv: Int
    ): CommonRes {
        val res = CommonRes(x, y, resId, nowResNum, resLv, 0, 0, 0)
        val gridId = calResGrid(x, y)
        var resData = commonResByGrid.findByKey(gridId)
        if (resData == null) {
            // TODO 下次刷新时间
            val commonResDataId = wpm.generateObjIdNew(areaCache)
            resData = CommonResData(worldId, commonResDataId, gridId, hashMapOf())
            commonResByGrid.insertByKey(resData)

            insert(areaCache, resData)
        }

        val yMap = resData.resDataMap.getOrPut(x) { hashMapOf() }
        yMap[y] = res
        commonResByXY.insertByKey(res)

        return res
    }

    fun deleteCommonResByXY(x: Int, y: Int) {
        val gridId = calResGrid(x, y)
        val resData = commonResByGrid.findByKey(gridId)
        if (resData == null) {
            return
        }
        val yMap = resData.resDataMap[x]
        if (yMap == null) {
            return
        }
        val res = yMap[y]
        if (res == null) {
            return
        }
        if (res.groupId != 0L) {
            //有采集部队，额外删除心跳
            farmOverPq.remove(res)
        }
        yMap.remove(y)
    }

    // 根据XY找到一个Res
    fun findCommonResByXY(x: Int, y: Int): CommonRes? {
        val gridId = calResGrid(x, y)
        val resData = commonResByGrid.findByKey(gridId)
        if (resData == null) {
            return null
        }
        val yMap = resData.resDataMap[x]
        if (yMap == null) {
            return null
        }
        val res = yMap[y]
        if (res == null) {
            return null
        }
        return res
    }

    fun delAllCommonResCell() {
        commonResByGrid.map {
            it.resDataMap.clear()
            true
        }
    }

    fun updateCommonRes(commonRes: CommonRes) {
        farmOverPq.remove(commonRes)
        farmOverPq.add(commonRes)
    }
}

//根据坐标计算生态格子Id
fun calResGrid(x: Int, y: Int): Int {
    val length = pcs.basicProtoCache.resourceArea

    val xx = x / length
    val yy = y / length

    return yy * pcs.basicProtoCache.allArea / length + xx
}

