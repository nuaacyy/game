package com.point18.slg2d.home

import akka.actor.Actor
import akka.actor.ActorRef
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.serialization.Serializer
import com.esotericsoftware.kryo.util.ListReferenceResolver
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import xyz.ariane.util.*
import xyz.ariane.util.memodb.EntityWrapper
import xyz.ariane.util.memodb.WriteOnlyInMemoryDb
import com.point18.slg2d.common.constg.JDBC_BATCH_SIZE
import com.point18.slg2d.common.constg.PlayerId
import java.sql.Timestamp
import java.util.*

val PLAYER_DB_KRYO_POOL = ScanKryoPoolExecutor(
    listOf(EntityWrapper::class.java, IEntity::class.java),
    HomePlayerDC::class.java.`package`.name
    //PlayerActor::class.java.`package`.name
) { kryo ->

    // 大部分EntityWrapper包含对象数并不多，关闭引用共享
    kryo.referenceResolver = ListReferenceResolver()
    kryo.references = false

    // 由于entity中用的都是Date，而hibernate反序列化时生成的是Timestamp，暂时不能自动注册
    kryo.register(Timestamp::class.java)
    kryo.register(LinkedHashMap::class.java)
    kryo.register(ArrayList::class.java)
    kryo.register(HashSet::class.java)
    kryo.register(Collections.EMPTY_LIST::class.java)
}

/**
 * 配置[DataContainer]之间的依赖关系
 *
 * **注意：暂不支持多级依赖关系**
 */
val DC_DEPENDENCY_MAP: Map<Class<out DataContainer>, List<Class<out DataContainer>>> = mapOf(
)

// SS：玩家数据库
open class PlayerDatabase(
    override val owner: PlayerActor,
    fetchDao: () -> CommonDao = { hpm.commonDao }
) : LazyDataContainerManager(
    dbReadWorker = PlayerActor.WorkerName.dbRead,
    fetchDao = fetchDao,
    dependencyMap = DC_DEPENDENCY_MAP,
    kryoPoolExecutor = PLAYER_DB_KRYO_POOL,
    jdbcBatchSize = JDBC_BATCH_SIZE,
    wdbLogger = Logging.getLogger(owner.context.system(), "wdb")
) {

    override val ownerId: Any get() = owner.playerId

    /** UP状态的player一定已经加载了[HomePlayerDC] */
    val player: HomePlayer get() = getDC(HomePlayerDC::class.java).player

    val blobSerializer: Serializer get() = hpm.blobSerializer

    override fun exec(name: String, task: () -> Unit) {
        require(name.isNotBlank()) { "taskName is blank" }
        owner.self.tell(NamedRunnable(name, task), ActorRef.noSender())
//        owner.exec(name, task)
    }

    override fun <T> createACS(): ActorCompletionStage<T> = owner.createACS<T>().acs

    override fun handleInitializingException(e: Throwable) {
        enterInitIfLoading()
    }

    override fun handleLoadingException(e: Throwable) {
        enterInitIfLoading()
    }

    // 从数据库初始化失败，回到INIT状态等待新消息触发重试
    private fun enterInitIfLoading() {
        with(owner) {

        }
    }

    override fun onSyncOpSubmitted(syncOp: WriteOnlyInMemoryDb.SyncOp) {
//        val snapshotWorker = owner.snapshotWorker
//        when (syncOp.op) {
//            ModificationTracker.Op.SAVE, ModificationTracker.Op.UPDATE, ModificationTracker.Op.SAVE_OR_UPDATE -> snapshotWorker?.tellNoSender(Cmd.SaveOrUpdate(syncOp.entity))
//            ModificationTracker.Op.DELETE -> snapshotWorker?.tellNoSender(Cmd.Delete(syncOp.entity))
//            else -> Unit
//        }
    }

}

/**
 * DataContainer的抽象实现。
 */
abstract class AbstractDataContainer<DATA> : DataContainer {

    lateinit var owner: PlayerActor private set

    lateinit var logger: LoggingAdapter private set

    private var initialized: Boolean = false

    val db: PlayerDatabase get() = owner.db

    val wdb: WriteOnlyInMemoryDb get() = db.wdb

    val playerId: Long get() = owner.playerId

    final override fun init(owner: Actor, data: Any?, depDCRepo: DataContainerRepo) {
        if (initialized) {
            return //确保只初始化一次
        }

        this.owner = owner as PlayerActor
        this.logger = Logging.getLogger(owner.context.system(), javaClass)

        @Suppress("UNCHECKED_CAST")
        initImpl(data as? DATA, depDCRepo)

        // SS：初始化完成
        initialized = true
    }

    override fun load(ownerId: Any, dao: CommonDao): Any? = loadAllFromDB(ownerId as PlayerId, dao)

    abstract protected fun initImpl(data: DATA?, depDCRepo: DataContainerRepo)

    abstract protected fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): DATA?
}