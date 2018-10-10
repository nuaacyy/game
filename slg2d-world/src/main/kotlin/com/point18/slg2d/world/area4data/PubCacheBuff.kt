package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.BUFF_EFFECT_DEF_COVER
import com.point18.slg2d.common.constg.BUFF_EFFECT_GOD_OF_WAR
import com.point18.slg2d.common.constg.BUFF_EFFECT_SNOW_COVER
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.BUFF_NAMED_QUERY
import com.point18.slg2d.common.worldentities.BuffEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 玩家的buff
class Buff(
        var worldId: Long,
        var id: Long,

        var protoId: Int,                                              // buff模板ID
        var playerId: Long,                                  // 玩家ID
        var overTime: Long,  // 过期时间
        var startTime: Long    // 开始时间
) : EntityWrapper<BuffEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): BuffEntity {
        return BuffEntity(
                worldId,
                id,
                protoId,
                playerId,
                overTime,
                startTime
        )
    }

    override fun wrap(entity: BuffEntity) {
        worldId = entity.worldId
        id = entity.id
        protoId = entity.protoId
        playerId = entity.playerId
        overTime = entity.overTime
        startTime = entity.startTime
    }
}

class CacheBuff(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    val buff = OneKeyIndexSlice({ it: Buff -> it.playerId }, { ita: Buff, itb: Buff -> ita.id == itb.id }) // 英雄缓存
    private val buff4OverTime: Queue<Buff> = PriorityQueue { c1, c2 ->
        when {
            c1.overTime > c2.overTime -> 1
            c1.overTime == c2.overTime -> 0
            else -> -1
        }
    }    // buff过期队列

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.buffEntities =
                    session.getNamedQuery(BUFF_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.buffEntities.forEach { entity ->
            try {
                val buff = db.wdb.recover(entity) { Buff() }

                cacheSingleBuff(buff)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    // 根据玩家ID + buff唯一ID获取buff
    fun findBuffsByPlayerIdAndId(playerId: Long, buffId: Long): Buff? {
        // 尝试从缓存中获取
        var specBuff: Buff? = null

        // 全部
        this.buff.findByKey(playerId) {
            if (it.id == buffId) {
                specBuff = it
            }
            it.id != buffId
        }

        return specBuff
    }

    // 获取玩家所有的buff
    fun findBuffsByPlayerId(playerId: Long): (LinkedList<Buff>) {
        // 尝试从缓存中获取
        val buffs = LinkedList<Buff>()
        // 全部
        buff.findByKey(playerId) {
            buffs.add(it)
        }

        return buffs
    }

    // 获取玩家所有的buff
    fun findBuffEntityListByPlayerId(playerId: Long): (LinkedList<BuffEntity>) {
        // 尝试从缓存中获取
        val buffs = LinkedList<BuffEntity>()
        // 全部
        buff.findByKey(playerId) {
            buffs.add(it.toEntity())
        }

        return buffs
    }

    fun createBuffByMoveServer(b: BuffEntity) {
        val buff = Buff()
        buff.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        buff.worldId = worldId
        buff.id = id

        insert(areaCache, buff)

        // 添加到缓存中
        cacheSingleBuff(buff)
    }

    // 暂时移除某个玩家的优先级队列数据,如果迁服失败再重新添回来
    fun stopBuffForMoveServer(playerId: Long) {
        val delList = findBuffsByPlayerId(playerId)
        for (del in delList) {
            buff4OverTime.remove(del)
        }
    }

    // 迁服失败,把数据重新添加到优先级队列
    fun reviveBuffForMoveServer(playerId: Long) {
        val delList = findBuffsByPlayerId(playerId)
        for (del in delList) {
            buff4OverTime.add(del)
        }
    }

    // 移除某个玩家的所有数据
    fun clearBuffForMoveServer(playerId: Long) {
        val delList = findBuffsByPlayerId(playerId)
        for (del in delList) {
            delete(areaCache, del)

            // 从缓存中删除
            buff.deleteByKey(del)
        }
    }

    // 新建buff
    fun createBuff(playerId: Long, protoId: Int, overTime: Long): (Buff) {
        val buffId = wpm.generateObjIdNew(areaCache)
        val nowSec = getNowTime()
        val buff = Buff(worldId, buffId, protoId, playerId, overTime, nowSec)

        // 添加到缓存中
        cacheSingleBuff(buff)

        // 保存
        insert(areaCache, buff)

        return buff
    }

    // 删除buff
    fun deleteBuff(buff: Buff) {
        this.buff.deleteByKey(buff)
        buff4OverTime.remove(buff)

        // 删除数据库
        delete(areaCache, buff)
    }

    private fun cacheSingleBuff(buff: Buff) {
        // 存入缓存
        this.buff.insertByKey(buff)
        buff4OverTime.add(buff)
    }

    fun peekBuffOverTimeFinish(): (Buff?) {
        val buff = buff4OverTime.peek() ?: return null

        val nowTime = getNowTime()

        if (buff.overTime < nowTime) {
            return buff
        }

        return null
    }

    fun popBuffOverTime() {
        buff4OverTime.poll()
    }

    // 获取玩家某特殊buff的效果值
    // 返回值解析: (是否拥有,效果值,buff)  原因是防护罩这样的buff没有效果值
    data class FindBuffValueByBuffTypeResult(var isHave: Boolean, var value: Int, var buffVo: Buff?)

    fun findBuffValueByBuffType(playerId: Long, buffType: Int): FindBuffValueByBuffTypeResult {
        // 尝试从缓存中获取
        val buffs = LinkedList<Buff>()

        // 全部
        buff.findByKey(playerId) {
            val buffProto = pcs.buffBasicProtoCache.protoMap[it.protoId]
            if (buffProto == null) {
                //Assert
                return@findByKey true
            }

            if (buffProto.typeEffect == buffType) {
                buffs.add(it)
            }
            buffProto.typeEffect != buffType
        }

        if (buffs.size <= 0) {
            return FindBuffValueByBuffTypeResult(false, 0, null)
        }

        var allValue = 0
        var delBuff: Buff? = null

        for (buff in buffs) {
            val buffProto = pcs.buffBasicProtoCache.protoMap[buff.protoId]
            if (buffProto == null) {
                //Asset
                continue
            }

            allValue += buffProto.effectSpecial
            delBuff = buff
        }

        return FindBuffValueByBuffTypeResult(true, allValue, delBuff)
    }

    data class CoverTypeBuff(
            val isHave: Boolean,
            val overTime: Long   //保护罩结束时间，取所有保护罩类型中最长的结束时间
    )

    private val coverTypeList = listOf(
            BUFF_EFFECT_DEF_COVER,
            BUFF_EFFECT_GOD_OF_WAR,
            BUFF_EFFECT_SNOW_COVER
    )

    fun isHaveCoverTypeBuff(playerId: Long): (CoverTypeBuff) {

        var isHaveCover = false
        var overTime = 0L
        for (coverType in coverTypeList) {
            val coverBuffRst = findBuffValueByBuffType(playerId, coverType)
            if (!coverBuffRst.isHave) {
                continue
            }
            isHaveCover = true
            val buffVo = coverBuffRst.buffVo
            if (buffVo == null) {
                continue
            }
            if (buffVo.overTime > overTime) {
                overTime = buffVo.overTime
            }
        }

        return CoverTypeBuff(isHaveCover, overTime)
    }
}