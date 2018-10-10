package com.point18.slg2d.common.baseg

import akka.Done
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.CoordinatedShutdown
import akka.cluster.sharding.ClusterSharding
import com.point18.slg2d.common.areaInitFromZK.BackStageZooKeeperDao
import com.point18.slg2d.common.commonfunc.ProcessStartDcLog
import com.point18.slg2d.common.commonfunc.logDc
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.configuration.ZkDataSourceConfigCache
import com.point18.slg2d.common.configuration.ZkDataSourceConfigList
import com.point18.slg2d.common.configuration.ZkDatasourceConfig
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.msgtrans.HomeMessageExtractor
import com.point18.slg2d.common.msgtrans.PublicMessageExtractor
import com.point18.slg2d.common.msgtrans.WorldMessageExtractor
import com.point18.slg2d.common.persistence.buildGameShardedSessionFactory
import com.point18.slg2d.common.zkdomain.CommCfg
import com.point18.slg2d.common.zkdomain.Platform
import com.point18.slg2d.common.zkdomain.ServerProcess
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.apache.curator.framework.CuratorFramework
import org.hibernate.cfg.Configuration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.ariane.util.*
import java.io.File
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import java.util.concurrent.TimeUnit
import java.util.function.Supplier
import kotlin.system.exitProcess

enum class ServerState {
    INIT,
    STARTING,
    STARTED,
    STOPPING,
    STOPPED
}

enum class ClusterRole(
    val tagRoles: List<TagRole>
) {
    gate,
    home,
    public,
    world,
    ;

    constructor(vararg tagRoles: TagRole) : this(tagRoles.asList())

    fun clusterType(): ClusterType {
        return ClusterType.values().find {
            this in it
        } ?: error("No cluster name specified. role=$this")
    }
}

enum class TagRole {
    duidMaster,
    duidWorker
}

enum class ClusterType(
    val roles: List<ClusterRole>
) {
    GAME_WORLD(
        listOf(
            ClusterRole.gate,
            ClusterRole.home,
            ClusterRole.public,
            ClusterRole.world
        )
    )

    ;

    operator fun contains(role: ClusterRole): Boolean = role in roles
}

/**
 * shard名称
 */
enum class GameWorldShard {
    gate,
    player,
    world,
    public
}

// 启动用的进程配置
data class StartConfig(
    val mgrHost: String,
    val mgrPort: Int,
    val createTable: Boolean,
    val ignore: Int
)

abstract class ProcessMgr(val processType: Int, val role: ClusterRole) {

    val logger: Logger = LoggerFactory.getLogger(javaClass) // 日志，slf4j的
    var processPort: Int = 0

    // 这个属性是多线程访问的？
    // 服务器阶段
    @Volatile
    var state: ServerState = ServerState.INIT
        set(value) {
            logger.info("Change state $field -> $value")
            field = value
        }

    lateinit var startConfig: StartConfig

    lateinit var actorSystem: ActorSystem private set
    private lateinit var actorSystemConfig: Config

    lateinit var ipProcess: ServerProcess private set
    lateinit var commCfg: CommCfg private set
    lateinit var platform: Platform private set

    protected abstract val includingShards: List<GameWorldShard>

    lateinit var actionActor: ActorRef

    // zk客户端
    lateinit var zkDao: BackStageZooKeeperDao private set

    /** zk数据源缓存 */
    private lateinit var datasourceCache: ZkDataSourceConfigCache

    var processNo: Int = 1 // 进程编号

    /**
     * 需要序列化/反序列化blob时使用[initBlobSerializer]初始化
     */
    lateinit var blobSerializer: BlobKryoSerializer private set

    /**
     * 需要操作数据库的服务器，使用[initCommonDao]初始化
     */
    @Volatile
    private var replaceableCommonDao: CommonDao? = null

    /**
     * 供外界代码使用，获取数据存储用的DAO。
     * 这个属性是只读的，避免外界误修改
     */
    val commonDao: CommonDao get() = requireNotNull(replaceableCommonDao)

    fun bootstrap() {
        logger.lzInfo { "ProcessMgr $role bootstrapping" }

        state = ServerState.STARTING

        try {
            start()

            state = ServerState.STARTED

            // 发送进程启动完毕日志
            val processStartDcLog = ProcessStartDcLog(role.name, ipProcess.processIp, ipProcess.seedPort)
            logDc.send(processStartDcLog)

        } catch (e: Throwable) {
            logger.error("$role boot failed.", e)

            // 出现任何异常，就以code 1退出进程
            exitProcess(1)
        }
    }

    protected fun initBlobSerializer() {
        // SS：BlobKryoSerializer相关来自 mars
        blobSerializer = BlobKryoSerializer(BlobKryoPool(actorSystemConfig))
    }

    /**
     * 初始化[actorSystem]依赖的全局组件
     */
    protected abstract fun beforeCreatingActorSystem()

    /**
     * 初始化[actorSystem]内部全局actor
     */
    protected open fun afterCreatingActorSystem() {
    }

    private fun start() {
        // 解析配置
        resolveConfig()

        // 清理关闭标记
        cleanGracefulShutdownFlags()

        // SS：创建前处理
        beforeCreatingActorSystem()

        // 前面的全局组件同步初始化 happens-before actor system的所有dispatcher thread的创建
        createActorSystem()

        // 添加关服时需要执行的任务
        addCoordinatedShutdownTasks()

        // 创建后处理
        afterCreatingActorSystem()

        // 初始化Kryo的序列化跟踪
        initKryoSerializerTracking()
    }

    // SS：初始化DAO
    protected fun initCommonDao(processPrototypeConfig: ((Configuration) -> Unit) = {}) {
//        val ipList = findAllLocalIp()

        // SS：创建ZK配置缓存
        // 当配置发生变化时，会重建DAO
        datasourceCache = ZkDataSourceConfigCache(zkDao.zkClient, DATASOURCE_NODE_NAME) {
            var targetConfig: ZkDataSourceConfigList? = null
            targetConfig = it.dsMap["${commCfg.id}"]
            if (targetConfig == null) {
                throw RuntimeException("无法找到数据源配置")
            }

            // SS：建立DAO
            rebuildCommonDao(
                targetConfig.datasources,
                processPrototypeConfig
            )
        }
    }

    private fun rebuildCommonDao(
        datasourceList: List<ZkDatasourceConfig>,
        processPrototypeConfig: (Configuration) -> Unit
    ) {
        val oldCommonDao = replaceableCommonDao

        // SS：这里新建了一个shard的会话工厂
        val shardedSessionFactory = buildGameShardedSessionFactory(datasourceList, processPrototypeConfig)

        // 新建并替换
        // CommonDaoHibernate是个通用的DAO类，内部有一些通用的DAO方法。
        replaceableCommonDao = CommonDaoHibernate(shardedSessionFactory)

        // SS：关闭原来的，老的有可能为null
        oldCommonDao?.close()

        logger.info("CommonDao rebuild succeeded. role=$role")
    }

    private fun initKryoSerializerTracking() {
        TrackableKryoSerializer.tracker = object : TrackableKryoSerializer.Tracker {
            override fun reportFromBinary(signature: String, length: Int, beginTimeNanos: Long) {
            }

            override fun reportToBinary(signature: String, length: Int, beginTimeNanos: Long) {
            }
        }
    }

    protected fun initZk(filename: String = "cfg/startconfig.json") {
        val file = File(filename)
        val contents = file.readText()
        startConfig = toObj(contents)

        // 初始化zookeeper。
        val addr = startConfig.mgrHost
        val port = startConfig.mgrPort
        val zkAddr = "$addr:$port"

        zkDao = BackStageZooKeeperDao(zkAddr)  // 获得后台数据权限接口
    }

    private fun resolveConfig() {
        initZk()

        // 获取commcfg
        resolveCommCfg()

        // 初始化日志
//        logDc.init(commCfg.kafkaAddrs, commCfg.dcLogTopic)

        val seedNodes = generateSortedSeedNodeList(role.clusterType(), null)

        // SS：当前节点能包含的 role 类型，是根据role和tagRole组合而成的
        val editedConfig = ConfigFactory.parseMap(
            mapOf(
                "akka.cluster.roles" to listOf(role.name),
                //"akka.cluster.roles" to listOf(role.name) + role.tagRoles.map(TagRole::name),
                "akka.remote.netty.tcp.hostname" to ipProcess.processIp,
                "akka.remote.netty.tcp.port" to processPort,
                "akka.cluster.seed-nodes" to seedNodes
                //"akka.cluster.auto-down-unreachable-after" to (if (IS_DEVELOP_MODE && !IS_AUTODOWN_OFF) "2s" else "off")
            )
        )

        logger.info("Creating ActorSystem with config: $editedConfig")

        // SS：合并配置
        val roleConfig: Config = ConfigFactory.load("${role.name}.conf")
        val mergedConfig = editedConfig.withFallback(roleConfig)
        actorSystemConfig = mergedConfig
    }

    private fun resolveCommCfg() {
        // 获取内网非loopback的IPv4地址
        val ipList = findAllLocalIp()

        // 找到进程配置
        var ipProcessStr: String? = null
        for (ipAddr in ipList) {
            val path = "$PROCESS_NODE_NAME/$ipAddr/$processType"
            ipProcessStr = zkDao.findNodeData(path)
            if (ipProcessStr != null) {
                break
            }
        }
        if (ipProcessStr == null) {
            throw RuntimeException("拉取配置异常,配置的节点不存在。")
        }
        ipProcess = toObj(ipProcessStr)
        processPort = ipProcess.seedPort

        // 找到通用配置
        val commCfgString = zkDao.findNodeData("$CLUSTER_NODE_NAME/${ipProcess.clusterId}")
        if (commCfgString == null || commCfgString.isBlank()) {
            throw RuntimeException("拉取配置异常,集群不存在,id是：${ipProcess.clusterId}")
        }
        commCfg = toObj(commCfgString)

        // 找到平台配置
        val platformString = zkDao.findNodeData("$PLATFORM_NODE_NAME/${ipProcess.platformId}")
        if (platformString == null || platformString.isBlank()) {
            throw RuntimeException("拉取配置异常,平台不存在。id：${ipProcess.platformId}")
        }
        platform = toObj(platformString)
    }

    protected open fun stopImpl() = Unit

    private fun createActorSystem() {
        val actorSystemName = fullClusterName(role.clusterType(), null)
        actorSystem = ActorSystem.create(actorSystemName, actorSystemConfig)
    }

    fun initProcessMgr(actor: ActorRef) {
        this.actionActor = actor
        this.actionActor.tell(StartAction(), ActorRef.noSender())
    }

    // 启动世界服 shard 的代理
    protected fun startWorldShardProxy() {
        ClusterSharding.get(actorSystem)
            .startProxy(
                GameWorldShard.world.name,
                Optional.of(ClusterRole.world.name),
                WorldMessageExtractor()
            )
            .let { logger.info("World shard proxy $it started.") }
    }

    // 启动公共服 shard 的代理
    protected fun startPublicShardProxy() {
        ClusterSharding.get(actorSystem)
            .startProxy(
                GameWorldShard.public.name,
                Optional.of(ClusterRole.public.name),
                PublicMessageExtractor(MAX_NUMBER_OF_PUBLIC_SHARD)
            )
            .let { logger.info("Public shard proxy $it started.") }
    }

    // 启动公共服 shard 的代理
    protected fun startHomeShardProxy() {
        ClusterSharding.get(actorSystem)
            .startProxy(
                GameWorldShard.player.name,
                Optional.of(ClusterRole.home.name),
                HomeMessageExtractor(MAX_NUMBER_OF_PUBLIC_SHARD)
            )
            .let { logger.lzInfo { "Public shard proxy $it started." } }
    }

    private fun writeClusterShardingGracefulShutdownFlagFiles() {
        includingShards.forEach { shard ->
            tryCatch(logger) {
                val flagDir = File(GRACEFUL_SHUTDOWN_FLAG_DIR)
                if (!flagDir.exists()) {
                    flagDir.mkdirs()
                }
                File(flagFilePath(shard.name)).apply {
                    delete()
                    createNewFile()
                    writeText("ok")
                    logger.info("Flag file $name generated.")
                }
            }
        }
    }

    private fun cleanGracefulShutdownFlags() {
        val flagFiles = includingShards.map { File(flagFilePath(it.name)) }
        for (flagFile in flagFiles) {
            if (flagFile.exists()) {
                // SS：删除标记文件
                check(flagFile.delete()) { "Delete graceful shutdown flag file failed." }
                logger.info("Graceful shutdown flag file: $flagFile deleted.")
            } else {
                logger.info("No graceful shutdown flag file found.")
            }
        }
    }

    private fun flagFilePath(flag: String): String =
        "$GRACEFUL_SHUTDOWN_FLAG_DIR/${flag}_${role}_$processPort"

    private fun addCoordinatedShutdownTasks() {
        with(CoordinatedShutdown.get(actorSystem)) {
            addTask(
                // SS：服务解除绑定前
                CoordinatedShutdown.PhaseBeforeServiceUnbind(),
                "enter-stopping-state",
                taskSupplier {
                    normalLog.lzInfo { "Coordinated 关闭 - PhaseBeforeServiceUnbind 阶段，服务器状态切换为STOPPING" }

                    state = ServerState.STOPPING
                }
            )

            addTask(
                // SS：服务解除绑定前
                CoordinatedShutdown.PhaseBeforeClusterShutdown(),
                "check-before-cluster-shutdown",
                taskSupplier {
                    normalLog.lzInfo { "Coordinated 关闭 - PhaseBeforeClusterShutdown 阶段，判断" }
                }
            )

            addTask(
                // SS：服务解除绑定前
                CoordinatedShutdown.PhaseClusterShardingShutdownRegion(),
                "check-cluster-sharding-shutdown-region",
                taskSupplier {
                    normalLog.lzInfo { "Coordinated 关闭 - PhaseClusterShardingShutdownRegion 阶段，判断" }
                }
            )

            // 在所有角色的节点同时开始停服时，作为proxy的ShardRegion如果在承载Shard的ShardRegion执行handoff时停止，
            // 会导致此次handoff必然超时。这里将每个承载Shard节点错开延迟开始执行GracefulShutdown规避此问题。
            if (includingShards.isNotEmpty()) {
                val delaySeconds = (GameWorldShard.values().indexOf(includingShards.first()) + 1) * 5L

                normalLog.lzInfo { "Before cluster sharding shutdown region delay seconds: $delaySeconds" }

                addTask(
                    // SS：集群shard关闭前？(看配置这个是自定义阶段)
                    "before-cluster-sharding-shutdown-region",
                    "delay-by-shard-name",
                    taskSupplier {
                        normalLog.lzInfo { "Coordinated 关闭 - before-cluster-sharding-shutdown-region 阶段，等待 $delaySeconds 秒" }

                        TimeUnit.SECONDS.sleep(delaySeconds)
                    }
                )
            }

            addTask(
                // SS：服务解除绑定前
                CoordinatedShutdown.PhaseClusterLeave(),
                "check-cluster-leave",
                taskSupplier {
                    normalLog.lzInfo { "Coordinated 关闭 - PhaseClusterLeave 阶段，判断" }
                }
            )

            addTask(
                // SS：集群退出阶段
                CoordinatedShutdown.PhaseClusterExiting(),
                "write-graceful-shutdown-region-flag-files",
                taskSupplier {
                    logger.lzInfo { "Coordinated 关闭 - PhaseClusterExiting 阶段，写集群关闭标记文件" }

                    this@ProcessMgr.writeClusterShardingGracefulShutdownFlagFiles()
                }
            )

            addTask(
                // SS：在系统终止前
                CoordinatedShutdown.PhaseBeforeActorSystemTerminate(),
                "stop-global-components",
                taskSupplier {
                    normalLog.lzInfo { "Coordinated 关闭 - PhaseBeforeActorSystemTerminate 阶段，停止全局组件..." }

                    this@ProcessMgr.stopGlobalComponents()
                }
            )

            addTask(
                // SS：在系统终止前
                CoordinatedShutdown.PhaseBeforeActorSystemTerminate(),
                "enter-stopped-state",
                taskSupplier {
                    normalLog.lzInfo { "Coordinated 关闭 - PhaseBeforeActorSystemTerminate 阶段，服务器状态切换为STOPPED" }

                    state = ServerState.STOPPED
                }
            )
        }
    }

    private fun taskSupplier(task: () -> Unit): Supplier<CompletionStage<Done>> =
        Supplier {
            CompletableFuture.supplyAsync {
                task()
                Done.getInstance()
            }
        }

    private fun stopGlobalComponents() {
        tryCatch(logger) { stopImpl() }

        tryCatch(logger) {
            // 关闭数据源缓存
            if (this::datasourceCache.isInitialized) {
                datasourceCache.close()
            }

            // 关闭zk连接
            zkDao.close()
        }

        logger.info("Global components all stopped.")
    }

    private fun generateSortedSeedNodeList(
        clusterType: ClusterType,
        instanceId: Int? = null
    ): List<String> {
        val zkClient = zkDao.zkClient
        val seedNodes: ArrayList<String> = arrayListOf()
        val actorSystemName = fullClusterName(clusterType, instanceId)
        forEachProcessConfig(zkClient) { host, zkServerProcessConfig ->
            if (zkServerProcessConfig.seedNode != 0) {
                seedNodes += "akka.tcp://$actorSystemName@$host:${zkServerProcessConfig.seedPort}"
            }
        }

        return seedNodes.apply { sort() }
    }

    private fun forEachProcessConfig(
        zkClient: CuratorFramework,
        func: (String, ServerProcess) -> Unit
    ) {
        // 获取主机节点下的所有子节点
        zkClient.children.forPath(PROCESS_NODE_NAME).forEach { host ->
            val hostPath = "$PROCESS_NODE_NAME/$host"

            // 获取特定进程路径下的子节点
            zkClient.children.forPath(hostPath).forEach { processType ->
                val processPath = "$hostPath/$processType"
                val processDataBytes: ByteArray = zkClient.data.forPath(processPath)
                val processConfig: ServerProcess = toObj(String(processDataBytes))
                if (processConfig.clusterId == commCfg.id) {
                    func(host, processConfig)
                }
            }
        }
    }
}

fun fullClusterName(clusterType: ClusterType, instanceId: Int? = null): String = "$clusterType${instanceId ?: ""}"

class StartAction

// 找到所有的内网IP
fun findAllLocalIp(): List<String> {
    // 获得本机ip
    val ipList = LinkedList<String>()
    val inetAddrList = allLocalInetAddrs()
    inetAddrList.forEach {
        if (!it.isLoopbackAddress && !it.isLinkLocalAddress) {
            ipList.add(it.hostAddress)
        }
    }

    return ipList
}