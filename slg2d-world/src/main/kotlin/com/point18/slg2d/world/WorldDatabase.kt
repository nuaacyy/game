package com.point18.slg2d.world

import akka.event.Logging
import akka.event.LoggingAdapter
import akka.serialization.Serializer
import com.esotericsoftware.kryo.util.ListReferenceResolver
import com.point18.slg2d.common.commonfunc.getNowMTime
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.JDBC_BATCH_SIZE
import com.point18.slg2d.common.persistence.WorldAsset
import com.point18.slg2d.world.actor.WorldActor
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.WorldInitData
import com.point18.slg2d.world.common.syncInfo2WorldManager
import xyz.ariane.util.*
import xyz.ariane.util.memodb.EntityWrapper
import xyz.ariane.util.memodb.WriteOnlyInMemoryDb
import java.sql.Timestamp
import java.time.Instant
import java.util.*

val WORLD_DB_KRYO_POOL = ScanKryoPoolExecutor(
    listOf(EntityWrapper::class.java, IEntity::class.java),
    AreaCache::class.java.`package`.name,
    WorldActor::class.java.`package`.name,
    WorldAsset::class.java.`package`.name
) { kryo ->
    kryo.referenceResolver = ListReferenceResolver()
    kryo.references = false

    // 由于entity中用的都是Date，而hibernate反序列化时生成的是Timestamp，暂时不能自动注册
    kryo.register(Timestamp::class.java)
    kryo.register(LinkedHashMap::class.java)
    kryo.register(ArrayList::class.java)
    kryo.register(Collections.EMPTY_LIST::class.java)
}

open class WorldDatabase(
    val world: WorldActor,
    val fetchDao: () -> CommonDao
) {

    val logger: LoggingAdapter = Logging.getLogger(this.world.context.system(), javaClass)

    val worldId: Long get() = world.worldId

    open val wdb = WriteOnlyInMemoryDb(
        kryoPoolExecutor = WORLD_DB_KRYO_POOL,
        createACS = { world.createACS<Unit>().acs },
        fetchDao = fetchDao,
        logger = logger,
        batchSize = JDBC_BATCH_SIZE
    )

    val blobSerializer: Serializer get() = wpm.blobSerializer

    fun tick(now: Instant) {
        world.execTickTask("tt_wdb_tick") {
            wdb.tick(now)
        }

        //        entityAppenderFlushTimer.whenTimeUp {
        //            world.execTickTask("tt_entity_appender_flush") { entityAppender.flush() }
        //        }
    }

    fun isAllClean(): Boolean = !wdb.hasPendingSyncOps()

    /**
    处理游戏数据
    分三步
    1.补救数据库,修改不依赖游戏逻辑的数据
    2.加载游戏缓存
    3.用于修改依赖游戏逻辑的数据,但是IO协程又还没起来,执行SQL来修改DB.另外要修改缓存数据
     */
    fun dbLoad(areaCache: AreaCache, start: Long) {

        // 第一步
        // 启动时检测维护数据库的数据,这个方法必须在加载缓存之前执行,否则数据库对了,但是加载上来的缓存全是错的
        // 在加载缓存之前执行的数据修复,更新DB,用于不需要游戏逻辑,直接改DB数据,稍后加载缓存的时候就直接能正确了
        //	repairInfoBeforeCacheInit(dbConn)

        val areaConfig = areaCache.areaConfig
        val commonDao = areaCache.db.fetchDao()
        areaCache.worldActor.createACS<WorldInitData>()
            .supplyIoKt {
                val worldInitData = WorldInitData()
                for (cache in areaCache.baseCaches) {
                    cache.doCacheLoad(worldInitData, commonDao)
                }

                return@supplyIoKt worldInitData
            }
            .whenCompleteKt { worldInitData, err ->
                when {
                    err != null -> {
                        assert(false)
                        handleLoadingFailure(err)
                    }
                    worldInitData != null -> {
                        try {
                            for (cache in areaCache.baseCaches) {
                                cache.doInitData(worldInitData)
                            }

                            for (cache in areaCache.baseCaches) {
                                cache.doCacheLink()
                            }

                            if (!areaCache.areaOnlyCache.isInitedMap()) {
                                logger.lzInfo { "开始刷新大地图:${getNowTime()}" }
                                areaCache.areaOnlyCache.initMap()
                                logger.lzInfo { "刷新大地图完毕:${getNowTime()}" }
                            }

                            logger.lzInfo {
                                "PltAreaNo.${areaConfig.pltAreaNo} AreaName.${areaConfig.areaName} " +
                                        "加载公共数据 耗时:${(getNowMTime() - start) / 1000}s"
                            }

                            areaCache.inited = true

                            logger.lzInfo { "游戏区初始化完毕" }

                            // 切换到Up状态！
                            world.enterUpState()

                            // 区启动完毕 上报给世界管理服
                            syncInfo2WorldManager(areaCache)
                        } catch (e: Exception) {
                            assert(false)
                            handleLoadingFailure(e)
                        }
                    }
                    else -> {
                        assert(false)
                        handleLoadingFailure(null)
                    }
                }

            }
    }

    private fun handleLoadingFailure(throwable: Throwable?) {
        logger.error(throwable, "World {} loading failed. Actor will stop.", world.worldId)
        world.passivate()
    }

}