package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.worldentities.MY_ALLIANCE_GIFT_NAMED_QUERY
import com.point18.slg2d.common.worldentities.MyAllianceGiftEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 玩家的联盟礼物信息
class MyAllianceGift(
    var worldId: Long,
    var id: Long,

    var overTime: Long,  // 过期时间
    var giftId: Int,                // 礼物表ID
    var isGet: Int,                // 是否已领取  0-否 1-是
    var giftInfo: String,      // 礼物内奖励
    var playerId: Long           // 玩家ID
) : EntityWrapper<MyAllianceGiftEntity> {
    constructor() : this(0, 0, 0, 0, 0, "", 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): MyAllianceGiftEntity {
        return MyAllianceGiftEntity(
            worldId,
            id,
            overTime,
            giftId,
            isGet,
            giftInfo,
            playerId
        )
    }

    override fun wrap(entity: MyAllianceGiftEntity) {
        worldId = entity.worldId
        id = entity.id
        overTime = entity.overTime
        giftId = entity.giftId
        isGet = entity.isGet
        giftInfo = entity.giftInfo
        playerId = entity.playerId
    }
}

class CacheMyAllianceGift(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val myAllianceGifts = OneKeyIndexSlice<Long, MyAllianceGift>({ it.playerId }, { pA, pB -> pA.id == pB.id })

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.myAllianceGiftEntities =
                session.getNamedQuery(MY_ALLIANCE_GIFT_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.myAllianceGiftEntities.forEach { entity ->
            try {
                val myAllianceGift = db.wdb.recover(entity) { MyAllianceGift() }

                this.myAllianceGifts.insertByKey(myAllianceGift)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    // 通过玩家ID获取玩家所有的礼物
    fun findMyAllianceGiftEntityListByPlayerId(playerId: Long): LinkedList<MyAllianceGiftEntity> {
        val allGifts = LinkedList<MyAllianceGiftEntity>()
        myAllianceGifts.findByKey(playerId) { allGifts.add(it.toEntity()) }
        return allGifts
    }

    fun createMyAllianceGiftByMoveServer(b: MyAllianceGiftEntity) {
        val myAllianceGift = MyAllianceGift()
        myAllianceGift.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        myAllianceGift.worldId = worldId
        myAllianceGift.id = id

        insert(areaCache, myAllianceGift)

        // 添加到缓存中
        myAllianceGifts.insertByKey(myAllianceGift)
    }

    // 移除某个玩家的所有数据
    fun clearMyAllianceGiftForMoveServer(playerId: Long) {
        val delList = findMyAllianceGiftsByPlayerId(playerId)
        for (del in delList) {
            delAllianceGift(del)
        }
    }

    // 通过玩家ID获取玩家所有的礼物
    fun findMyAllianceGiftsByPlayerId(playerId: Long): List<MyAllianceGift> {
        val allGifts = LinkedList<MyAllianceGift>()
        myAllianceGifts.findByKey(playerId) { allGifts.add(it) }
        return allGifts
    }

    // 通过玩家ID与唯一ID获取玩家某个礼物
    fun findMyAllianceGiftsByPlayerIdAndId(playerId: Long, id: Long): MyAllianceGift? {
        var g: MyAllianceGift? = null

        myAllianceGifts.findByKey(playerId) {
            if (it.id == id) {
                g = it
            }
            it.id != id
        }

        return g
    }

    fun createMyAllianceGift(
        playerId: Long,
        overTime: Long,
        giftId: Int,
        giftInfo: String
    ): MyAllianceGift {
        val id = wpm.generateObjIdNew(areaCache)
        val myAllianceGift = MyAllianceGift(
            worldId,
            id,
            overTime,
            giftId,
            0,
            giftInfo,
            playerId
        )

        insert(areaCache, myAllianceGift)

        // 添加到缓存
        myAllianceGifts.insertByKey(myAllianceGift)

        return myAllianceGift
    }

    //删除一条记录
    fun delAllianceGift(req: MyAllianceGift) {
        if (req.id == 0L) {
            return
        }
        delete(areaCache, req)

        // 删除缓存
        myAllianceGifts.deleteByKey(req)
    }
}
