package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.JACKPOT_QUERY
import com.point18.slg2d.common.publicentities.JackpotEntity
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.CASINO_ID
import java.io.Serializable
import java.time.Duration

class Jackpot(
    var id: Long,
    var publicId:Long,
    var totalMoney: Long,
    var lastTime: Long,
    var lastRefreshTime: Long
) : EntityWrapper<JackpotEntity> {
    constructor() : this(0, 0,0, 0,0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): JackpotEntity {
        return JackpotEntity(
            id,
            publicId,
            totalMoney,
            lastTime,
            lastRefreshTime
        )
    }

    override fun wrap(entity: JackpotEntity) {
        id = entity.id
        publicId = entity.publicId
        totalMoney = entity.totalMoney
        lastTime = entity.lastTime
        lastRefreshTime = entity.lastRefreshTime
    }

}

class CacheJackpot(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val jackpotMap = OneKeyIndex { it: Jackpot -> it.id }

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.jackpot =
                    session.getNamedQuery(JACKPOT_QUERY)
                        .setLong("publicId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.jackpot.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    Jackpot()

                }

                this.jackpotMap.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createJackpot(
        totalMoney: Long,
        lastTime: Long,
        lastRefreshTime: Long
    ): Jackpot {
        val id = ppm.generateObjIdNew()
        val publicId = CASINO_ID
        val jackpot = Jackpot(id, publicId, totalMoney, lastTime, lastRefreshTime)

        insert(publicCache, jackpot)

        jackpotMap.insertByKey(jackpot)

        return jackpot
    }

    // 查找所有的联盟
    fun findJackpot(): Jackpot? {
        jackpotMap.map {
            return it
        }
        return null
    }
}




