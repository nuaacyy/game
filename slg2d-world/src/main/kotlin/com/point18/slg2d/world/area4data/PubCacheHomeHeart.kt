package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Deleted
import com.point18.slg2d.common.constg.Normal
import com.point18.slg2d.common.constg.ReplayErrorCode
import com.point18.slg2d.common.constg.TriggerNotReplay
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.worldentities.HOME_HEART_NAMED_QUERY
import com.point18.slg2d.common.worldentities.HomeHeartEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.lzWarn
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class HomeHeart(
    var worldId: Long,
    var id: Long,

    var playerId: Long,
    var homeAction: Int,
    var actionId: Long,
    var triggerTime: Long,
    var state: Int
) : EntityWrapper<HomeHeartEntity> {
    constructor() : this(
        0, 0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): HomeHeartEntity {
        return HomeHeartEntity(
            worldId,
            id,
            playerId,
            homeAction,
            actionId,
            triggerTime,
            state
        )
    }

    override fun wrap(entity: HomeHeartEntity) {
        worldId = entity.worldId
        id = entity.id
        worldId = entity.worldId
        id = entity.id
        playerId = entity.playerId
        homeAction = entity.homeAction
        actionId = entity.actionId
        triggerTime = entity.triggerTime
        state = entity.state
    }
}

class CacheHomeHeart(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val heartMap = TwoKeyIndex<Long, Long, HomeHeart>({ it.playerId }, { it.actionId })
    private val waitingReplyMap = OneKeyIndex<Long, HomeHeart> { it.playerId }
    private val replyErrorMap = TwoKeyIndex<Long, Long, HomeHeart>({ it.playerId }, { it.actionId })
    private val heartQueue: Queue<HomeHeart> = PriorityQueue { c1, c2 ->
        when {
            c1.triggerTime > c2.triggerTime -> 1
            c1.triggerTime == c2.triggerTime -> 0
            else -> -1
        }
    }

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.homeHeartEntities =
                session.getNamedQuery(HOME_HEART_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.homeHeartEntities.forEach {
            try {
                val heart = db.wdb.recover(it) {
                    HomeHeart()
                }
                cacheHomeHeart(heart)
            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", it)
            }
        }
    }

    override fun doCacheLink() {

    }

    private fun cacheHomeHeart(heart: HomeHeart) {
        heartMap.insertByKey(heart)
        when (heart.state) {
            Normal -> {
                heartQueue.add(heart)
            }
            TriggerNotReplay -> {
                waitingReplyMap.insertByKey(heart)
            }
            ReplayErrorCode -> {
                replyErrorMap.insertByKey(heart)
            }
        }
    }

    private fun cacheRemoveHomeHeart(heart: HomeHeart) {
        heartMap.deleteByKey(heart)
        when (heart.state) {
            Normal -> {
                heartQueue.remove(heart)
            }
            TriggerNotReplay -> {
                waitingReplyMap.deleteByKey(heart)
            }
            ReplayErrorCode -> {
                replyErrorMap.deleteByKey(heart)
            }
        }
    }

    fun findAllHeartEntityListByPlayerId(playerId: Long): LinkedList<HomeHeartEntity> {
        val hearts = LinkedList<HomeHeartEntity>()
        heartMap.findByOneKeyFilter(playerId) {
            hearts.add(it.toEntity())
            true
        }

        return hearts
    }

    private fun findAllHeartByPlayerId(playerId: Long): List<HomeHeart> {
        val hearts = LinkedList<HomeHeart>()
        heartMap.findByOneKeyFilter(playerId) {
            hearts.add(it)
            true
        }

        return hearts
    }

    fun createHomeHeartByMoveServer(areaCache: AreaCache, b: HomeHeartEntity) {
        val homeHeart = HomeHeart()
        homeHeart.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        homeHeart.worldId = worldId
        homeHeart.id = id

        insert(areaCache, homeHeart)

        // 添加到缓存中
        cacheHomeHeart(homeHeart)
    }

    // 把心跳全部暂停,如果失败大不了在添回去 数据先不要删除
    fun stopHomeHeartForMoveServer(playerId: Long) {
        val delList = findAllHeartByPlayerId(playerId)
        for (del in delList) {
            cacheRemoveHomeHeart(del)
        }
    }

    // 迁服失败,把暂停心跳的全部复活
    fun reviveHomeHeartForMoveServer(playerId: Long) {
        val delList = findAllHeartByPlayerId(playerId)
        for (del in delList) {
            cacheRemoveHomeHeart(del)
        }
    }

    // 移除某个玩家的所有数据
    fun clearHomeHeartForMoveServer(playerId: Long) {
        val delList = findAllHeartByPlayerId(playerId)
        for (del in delList) {
            delete(areaCache, del)
        }
    }

    /**
     * 创建home服心跳
     */
    fun createHomeHeart(playerId: Long, action: Int, actionId: Long, triggerTime: Long): HomeHeart {
        val id = wpm.generateObjIdNew(areaCache)
        val heart = HomeHeart(worldId, id, playerId, action, actionId, triggerTime, Normal)
        insert(areaCache, heart)
        cacheHomeHeart(heart)
        return heart
    }

    /**
     * 删除home服心跳
     */
    fun deleteHomeHeart(playerId: Long, actionId: Long) {
        val heart = heartMap.findByKey(playerId, actionId)
        if (heart == null) {
            normalLog.lzWarn { "deleteHomeHeart:找不到home服的心跳,playerId:{$playerId},actionId:{$actionId}" }
            return
        }

        delete(areaCache, heart)
        //通过设置心跳状态形式去剔除队列
        heart.state = Deleted
    }

    /**
     * 更新home服心跳的触发时间
     */
    fun updateHomeHeart(playerId: Long, actionId: Long, newTriggerTime: Long) {
        val heart = heartMap.findByKey(playerId, actionId)
        if (heart == null) {
            normalLog.lzWarn { "updateHomeHeart:找不到home服的心跳,playerId:{$playerId},actionId:{$actionId}" }
            return
        }
        heartQueue.remove(heart)
        heart.triggerTime = newTriggerTime
        heartQueue.add(heart)
    }

    /**
     * home心跳取出第一个
     */
    fun peekHomeHeart(): (HomeHeart?) {
        val heart = heartQueue.peek() ?: return null

        val nowTime = getNowTime()

        if (heart.triggerTime < nowTime) {
            return heart
        }

        return null
    }

    /**
     * home心跳弹出
     */
    fun popHomeHeart() {
        heartQueue.poll()
    }

    /**
     * 将心跳加入等待回复
     */
    fun addHeart2WaitReply(heart: HomeHeart) {
        heart.state = TriggerNotReplay
        heartMap.deleteByKey(heart)
        waitingReplyMap.insertByKey(heart)
    }

    /**
     * 将心跳加入等待确认
     */
    fun addHeart2ReplyError(heart: HomeHeart) {
        heart.state = ReplayErrorCode
        waitingReplyMap.deleteByKey(heart)
        replyErrorMap.insertByKey(heart)
    }

    /**
     * 将心跳移出等待
     */
    fun removeHeartFromWait(heart: HomeHeart) {
        waitingReplyMap.deleteByKey(heart)
    }
}