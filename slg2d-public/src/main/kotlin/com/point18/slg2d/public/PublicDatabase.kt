package com.point18.slg2d.public

import akka.actor.Actor
import akka.event.Logging
import akka.event.LoggingAdapter
import com.esotericsoftware.kryo.util.ListReferenceResolver
import com.point18.slg2d.common.persistence.WorldAsset
import com.point18.slg2d.public.actor.PublicActor
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.public.datacache.PublicInitData
import xyz.ariane.util.*
import xyz.ariane.util.memodb.EntityWrapper
import xyz.ariane.util.memodb.WriteOnlyInMemoryDb
import com.point18.slg2d.common.constg.JDBC_BATCH_SIZE
import com.point18.slg2d.common.constg.PublicId
import com.point18.slg2d.public.actor.PublicManagerActor
import java.sql.Timestamp
import java.time.Instant

val PUBLIC_DB_KRYO_POOL = ScanKryoPoolExecutor(
    listOf(EntityWrapper::class.java, IEntity::class.java),
    PublicCache::class.java.`package`.name,
    PublicActor::class.java.`package`.name,
    WorldAsset::class.java.`package`.name
) { kryo ->
    kryo.referenceResolver = ListReferenceResolver()
    kryo.references = false

    // 由于entity中用的都是Date，而hibernate反序列化时生成的是Timestamp，暂时不能自动注册
    kryo.register(Timestamp::class.java)
    kryo.register(LinkedHashMap::class.java)
    kryo.register(ArrayList::class.java)
}

class PublicDatabase(
    val public: PublicActor,
    val fetchDao: () -> CommonDao
) {

    val logger: LoggingAdapter = Logging.getLogger(public.context.system(), javaClass)

    val wdb = WriteOnlyInMemoryDb(
        kryoPoolExecutor = PUBLIC_DB_KRYO_POOL,
        createACS = { public.createACS<Unit>().acs },
        fetchDao = fetchDao,
        logger = logger,
        batchSize = JDBC_BATCH_SIZE
    )

    /**
     * 数据库同步任务
     */
    fun tick(now: Instant) {
        public.execTickTask("tt_wdb_tick") {
            wdb.tick(now)
        }
    }

    fun isAllClean(): Boolean = !wdb.hasPendingSyncOps()

    /**
     * 加载数据
     */
    fun dbLoad() {
        val publicId = public.publicId
        val commonDao = public.publicCache.db.fetchDao()
        public.createACS<PublicInitData>()
            .supplyIoKt {
                val publicInitData = PublicInitData(publicId)
                for (cache in public.publicCache.baseCaches) {
                    cache.doCacheLoad(publicInitData, commonDao)
                }

                return@supplyIoKt publicInitData
            }
            .whenCompleteKt { publicInitData, err ->
                when {
                    err != null -> {
                        assert(false)
                        handleLoadingFailure(err)
                    }
                    publicInitData != null -> {
                        try {
                            for (cache in public.publicCache.baseCaches) {
                                cache.doInitData(publicInitData)
                            }

                            for (cache in public.publicCache.baseCaches) {
                                cache.doCacheLink()
                            }

                            // 标记为初始化完毕。
                            // 注意：这个必须在协程开始运行前设置，否则协程逻辑拿不到areabase
                            public.publicCache.inited = true

                            logger.lzInfo { "ID为 $publicId 的PublicActor初始化完毕" }

                            // 进入运行阶段
                            public.enterUpState()

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
        logger.error(throwable, "Public {} loading failed. Actor will stop.", public.publicId)
        public.passivate()
    }
}

class PublicMenagerDatabase(
    val aPublic: PublicManagerActor,
    val fetchDao: () -> CommonDao
) {

    val logger: LoggingAdapter = Logging.getLogger(aPublic.context.system(), javaClass)

    val wdb = WriteOnlyInMemoryDb(
        kryoPoolExecutor = PUBLIC_DB_KRYO_POOL,
        createACS = { aPublic.createACS<Unit>().acs },
        fetchDao = fetchDao,
        logger = logger,
        batchSize = JDBC_BATCH_SIZE
    )

    /**
     * 数据库同步任务
     */
    fun tick(now: Instant) {
        aPublic.execTickTask("tt_wdb_tick") {
            wdb.tick(now)
        }
    }

    fun isAllClean(): Boolean = !wdb.hasPendingSyncOps()

    /**
     * 加载数据
     */
    fun dbLoad() {
        val publicId = aPublic.publicId
        val commonDao = aPublic.publicCache.db.fetchDao()
        aPublic.createACS<PublicInitData>()
            .supplyIoKt {
                // 管理节点从数据库中load数据
                val publicInitData = PublicInitData(publicId)
                doCacheLoadManager(publicInitData, commonDao)

                return@supplyIoKt publicInitData
            }
            .whenCompleteKt { publicInitData, err ->
                when {
                    err != null -> {
                        assert(false)
                        handleLoadingFailure(err)
                    }
                    publicInitData != null -> {
                        try {
                            // 管理节点把数据库加载到的数据转换成缓存
                            doInitDataForMenager(publicInitData)

                            // 标记为初始化完毕。
                            // 注意：这个必须在协程开始运行前设置，否则协程逻辑拿不到areabase
                            aPublic.publicCache.inited = true

                            logger.lzInfo { "ID为 $publicId 的PublicActor初始化完毕" }

                            // 进入运行阶段
                            aPublic.enterUpState()

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

    // 管理节点从数据库中load数据
    private fun doCacheLoadManager(publicInitData: PublicInitData, commonDao: CommonDao) {
        aPublic.publicCache.allianceCache.doCacheLoad(publicInitData, commonDao)
        aPublic.publicCache.publicActivityManagerCache.doCacheLoad(publicInitData, commonDao)
        aPublic.publicCache.allianceCompetitionGroupCacheManager.doCacheLoad(publicInitData, commonDao)
    }

    // 管理节点把数据库加载到的数据转换成缓存
    private fun doInitDataForMenager(publicInitData: PublicInitData) {
        aPublic.publicCache.allianceCache.doInitDataForMenager(publicInitData)
        aPublic.publicCache.publicActivityManagerCache.doInitDataForMenager(publicInitData)
        aPublic.publicCache.allianceCompetitionGroupCacheManager.doInitDataForManager(publicInitData)
    }

    private fun handleLoadingFailure(throwable: Throwable?) {
        logger.error(throwable, "Public {} loading failed. Actor will stop.", aPublic.publicId)
        aPublic.passivate()
    }
}

// SS：DataContainer的抽象实现。
abstract class AbstractDataContainer<DATA> : DataContainer {

    lateinit var owner: PublicActor private set

    lateinit var logger: LoggingAdapter private set

    private var initialized: Boolean = false

    val db: PublicDatabase get() = owner.db

    val wdb: WriteOnlyInMemoryDb get() = db.wdb

    val publicId: Long get() = owner.publicId

    /** 用于记录事件日志 */
    //val playerEventInfo: PlayerEventInfo get() = owner.createPlayerEventInfo()

    override final fun init(owner: Actor, data: Any?, depDCRepo: DataContainerRepo) {
        if (initialized) {
            return //确保只初始化一次
        }

        this.owner = owner as PublicActor
        this.logger = Logging.getLogger(owner.context.system(), javaClass)

        @Suppress("UNCHECKED_CAST")
        initImpl(data as? DATA, depDCRepo)

        // SS：初始化完成
        initialized = true
    }

    override fun load(ownerId: Any, dao: CommonDao): Any? = loadAllFromDB(ownerId as PublicId, dao)

    abstract protected fun initImpl(data: DATA?, depDCRepo: DataContainerRepo)

    abstract protected fun loadAllFromDB(publicId: PublicId, dao: CommonDao): DATA?
}