package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.SNOW_BASE
import com.point18.slg2d.common.constg.WONDER_BASE
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndexSlice
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.WALK_NAMED_QUERY
import com.point18.slg2d.common.worldentities.WalkEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

/**
一条行军信息
 */
class Walk(
    var worldId: Long,
    var id: Long,

    var marchState: Int,                //出征状态
    var marchTimeOff: Long,             //出征时间
    var marchTimeArrival: Long,         //到达时间
    var marchPlaceX: Int,               //出发地X
    var marchPlaceY: Int,               //出发地Y
    var marchAimsX: Int,                //目标地X
    var marchAimsY: Int,                //目标地Y
    var initialWalkTime: Int,           //初始的行军时间
    var walkForceGroupId: Long,          //行军线中的行军主体ID
    var isAtkMonsterHome: Int,          //是否攻击魔物回城
    var walkSpeed: Double,              //行军速度
    var nowWalkRobotX: Int,             //行军小人当前所在的大地图大坐标X
    var nowWalkRobotY: Int,             //行军小人当前所在的大地图大坐标Y
    var isConflict: Int                 //是否行军冲突 0、否 1、是
) : EntityWrapper<WalkEntity> {

    constructor() : this(
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0.0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): WalkEntity {
        return WalkEntity(
            worldId,
            id,
            marchState,
            marchTimeOff,
            marchTimeArrival,
            marchPlaceX,
            marchPlaceY,
            marchAimsX,
            marchAimsY,
            initialWalkTime,
            walkForceGroupId,
            isAtkMonsterHome,
            walkSpeed,
            nowWalkRobotX,
            nowWalkRobotY,
            isConflict
        )
    }

    override fun wrap(entity: WalkEntity) {
        worldId = entity.worldId
        id = entity.id
        marchState = entity.marchState
        marchTimeOff = entity.marchTimeOff
        marchTimeArrival = entity.marchTimeArrival
        marchPlaceX = entity.marchPlaceX
        marchPlaceY = entity.marchPlaceY
        marchAimsX = entity.marchAimsX
        marchAimsY = entity.marchAimsY
        initialWalkTime = entity.initialWalkTime
        walkForceGroupId = entity.walkForceGroupId
        isAtkMonsterHome = entity.isAtkMonsterHome
        walkSpeed = entity.walkSpeed
        nowWalkRobotX = entity.nowWalkRobotX
        nowWalkRobotY = entity.nowWalkRobotY
        isConflict = entity.isConflict
    }

}

data class Pos(
    var posX: Double,
    var posY: Double
)

fun calDistance(p1: Pos, p2: Pos): Double {
    return Math.sqrt((p1.posX - p2.posX) * (p1.posX - p2.posX) + (p1.posY - p2.posY) * (p1.posY - p2.posY))
}

class CacheWalk(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    val walkMap = OneKeyIndex { walk: Walk -> walk.id }
    private val walkMapByGroup = OneKeyIndex { walk: Walk -> walk.walkForceGroupId }
    private val walkMapByGotoPos = TwoKeyIndexSlice({ walk -> walk.marchAimsX },
        { walk -> walk.marchAimsY },
        { walkA, walkB: Walk -> walkA.id == walkB.id })
    private val walkMapByFromPos = TwoKeyIndexSlice({ walk -> walk.marchPlaceX },
        { walk -> walk.marchPlaceY },
        { walkA, walkB: Walk -> walkA.id == walkB.id })

    private val nowAllWalkInfo: HashMap<Int, HashMap<Int, LinkedList<Walk>>> = hashMapOf()  // KEY 地图::大::格子的XY

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.walkEntities =
                    session.getNamedQuery(WALK_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.walkEntities.forEach { entity ->
            try {
                val walk = db.wdb.recover(entity) { Walk() }

                this.walkMap.insertByKey(walk)
                this.walkMapByGroup.insertByKey(walk)
                this.walkMapByGotoPos.insertByKey(walk)
                this.walkMapByFromPos.insertByKey(walk)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    // 根据目的地的XY来找到正在过来的行军线数量
    fun findWalksByGotoXy(x: Int, y: Int): LinkedList<Walk> {
        val walks = LinkedList<Walk>()
        walkMapByGotoPos.findByKey(x, y) { walks.add(it) }
        return walks
    }

    // 虽然行军的表没换新的,仍然是这张,但是肯定字段改动挺大的,写一个新的create方法,改一下参数列表先~~
    fun createWalk(
        runType: Int, arrivalsTime: Long, walkSpeed: Double,
        placeX: Int, placeY: Int, aimsX: Int, aimsY: Int, startTime: Long, walkForceGroupId: Long, isAtkMonsterHome: Int
    ): Walk {
        val id = wpm.generateObjIdNew(areaCache)
        val walk = Walk(
            worldId,
            id,
            runType,
            startTime,
            arrivalsTime,
            placeX,
            placeY,
            aimsX,
            aimsY,
            com.point18.slg2d.common.commonfunc.getTimeSec(arrivalsTime - startTime),
            walkForceGroupId,
            isAtkMonsterHome,
            walkSpeed,
            placeX,
            placeY,
            0
        )
        insert(areaCache, walk)

        // 存入缓存
        walkMap.insertByKey(walk)
        walkMapByGotoPos.insertByKey(walk)
        walkMapByFromPos.insertByKey(walk)
        walkMapByGroup.insertByKey(walk)
        return walk
    }

    // 删除一个行军
    fun deleteWalk(walk: Walk) {
        if (walk.id == 0L) {
            return
        }
        delete(areaCache, walk)

        // 从缓存中删除
        walkMap.deleteByKey(walk)
        walkMapByGotoPos.deleteByKey(walk)
        walkMapByFromPos.deleteByKey(walk)
        walkMapByGroup.deleteByKey(walk)
    }

    // 根据行军ID拿行军信息
    fun findWalkById(id: Long): Walk? {
        return walkMap.findByKey(id)
    }

    // 找出所有到点的行军信息
    fun findAllWalk(): List<Walk> {
        val walks = LinkedList<Walk>()
        walkMap.map { walks.add(it) }
        return walks
    }

    // 找出所有到点的行军信息
    fun findAllWalkForTimeOver(): List<Walk> {
        val walks = LinkedList<Walk>()
        val nowTime = getNowTime()
        walkMap.map {
            if (it.marchTimeArrival > com.point18.slg2d.common.commonfunc.zeroTime.time && it.marchTimeArrival < nowTime) {
                walks.add(it)
            }
            true
        }
        return walks
    }

    // 根据出发地的XY来找到正在过来的行军线数量
    fun findWalksByFromXy(x: Int, y: Int): List<Walk> {
        val walks = LinkedList<Walk>()
        walkMapByFromPos.findByKey(x, y) { walks.add(it) }
        return walks
    }

    //根据行军组Id查找行军线
    fun findWalkByGroupId(groupId: Long): Walk? {
        return walkMapByGroup.findByKey(groupId)
    }

    //计算当前位置
    fun calCurrentPos(walk: Walk): Pos {
        val fromPos = calCenter(walk.marchPlaceX, walk.marchPlaceY)
        val toPos = calCenter(walk.marchAimsX, walk.marchAimsY)
        val distance =
            Math.sqrt((fromPos.posX - toPos.posX) * (fromPos.posX - toPos.posX) + (fromPos.posY - toPos.posY) * (fromPos.posY - toPos.posY))

        val leftNeedTime = walk.marchTimeArrival - getNowTime()        // 剩余所需行军时间，毫秒
        val leftDistance = walk.walkSpeed * leftNeedTime / 1000

        val progressNum = leftDistance / distance                      // 剩余的比例

        val nowX = walk.marchAimsX - (walk.marchAimsX - walk.marchPlaceX) * progressNum
        val nowY = walk.marchAimsY - (walk.marchAimsY - walk.marchPlaceY) * progressNum
        return Pos(nowX, nowY)
    }

    // 计算中心点
    fun calCenter(posX: Int, posY: Int): Pos {
        // 判断遗迹
        val relicCell = areaCache.relicCache.findRelicByXY(posX, posY)
        if (relicCell != null) {
            return Pos(posX + 0.5, posY + 0.5)
        }
        //判断奇观
        val area = pcs.wonderLocationProtoCache.findInWonderType(posX, posY)
        if (area.int == WONDER_BASE) {
            return Pos(posX + 0.5, posY + 0.5)
        }
        //判断雪地中心
        val snowArea = pcs.monsterActivityLocationProtoCache.findActivityBossAreaType(posX, posY)
        if (snowArea.int == SNOW_BASE) {
            return Pos(posX + 0.5, posY + 0.5)
        }
        return Pos(posX.toDouble(), posY.toDouble())
    }

    fun findWalksByGridXY(x: Int, y: Int): List<Walk>? {
        val yMap = this.nowAllWalkInfo[x]
        if (yMap == null) {
            return null
        }

        val list = yMap[y]
        return list
    }

    fun clearNowAllWalks() {
        return this.nowAllWalkInfo.clear()
    }

    fun addWalkByGridXY(x: Int, y: Int, walk: Walk) {
        val xMap = nowAllWalkInfo.getOrPut(x) { hashMapOf() }
        val yList = xMap.getOrPut(y) { LinkedList() }
        yList.add(walk)
    }
}
