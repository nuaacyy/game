package com.point18.slg2d.world

import akka.actor.ActorRef
import akka.cluster.Cluster
import akka.cluster.sharding.ClusterSharding
import akka.cluster.sharding.ClusterShardingSettings
import akka.cluster.sharding.ShardCoordinator
import akka.cluster.singleton.ClusterSingletonProxy
import akka.cluster.singleton.ClusterSingletonProxySettings
import akka.routing.FromConfig
import com.point18.slg2d.common.baseg.*
import com.point18.slg2d.common.commonfunc.AreaConfig
import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.ALLIANCE_MANAGER_PATH
import com.point18.slg2d.common.constg.GRPC_SERVICE_NODE_NAME
import com.point18.slg2d.common.constg.PROCESS_WORLD
import com.point18.slg2d.common.constg.WORLD_AREA_NODE_NAME
import com.point18.slg2d.common.msgtrans.WorldMessageExtractor
import com.point18.slg2d.common.pc.filterWordsFromGm
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.syncdomain.KryoAskWorldMessage
import com.point18.slg2d.common.zkdomain.WorldArea
import com.point18.slg2d.world.actor.WorldActor
import com.point18.slg2d.world.actor.WorldManagerActor
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.grpc.BattleServiceMgr
import com.point18.slg2d.world.module.initModules
import com.point18.slg2d.world.module.registerDeals
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.apache.curator.framework.recipes.cache.PathChildrenCache
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener
import pb4server.*
import xyz.ariane.util.concurrent.NamedThreadFactory
import xyz.ariane.util.lzInfo
import xyz.ariane.util.lzWarn
import xyz.ariane.util.tryCatch
import java.util.*
import java.util.concurrent.TimeUnit

// 来自公共管理服的世界公共数据
data class WorldManagerInfoFromPublicManager(
    var useAllianceName: HashMap<String, Int>,
    var useAllianceShortName: HashMap<String, Int>,
    var syncVersion: Int, // 版本号
    var allianceRankInfo: HashMap<Int, MutableList<AllianceRankInfoVo>>,
    var allServerInfo: HashMap<Long, MoveServerInfoVo>
)

// 迁服列表信息
data class MoveServerInfoVo(
    var worldId: Long, // 服务器id
    var areaId: Int, // 服务器区号
    var areaName: String, // 服务器名字
    var kingName: String,// 服务器国王名
    var allianceAreaId: Int,// 王国所属联盟所在服
    var allianceName: String,// 王国所属联盟名字
    var allianceShortName: String,// 王国所属联盟简称
    var serverOpenTime: Int // 服务器开服时间
)

data class AllianceRankInfoVo(
    var allianceName: String,
    var allianceShortName: String,
    var allianceId: Long,
    var flagColor: Int,
    var flagStyle: Int,
    var flagEffect: Int,
    var value: Long,
    var extend1 :String // 扩展字段,当前用法 如果排行类型是5-总动员小组排名的时候,这个字段表示组ID
)

open class WorldProcess : ProcessMgr(PROCESS_WORLD, ClusterRole.world) {

    override val includingShards = listOf(GameWorldShard.world)

    /** 每个world节点都监视服务器列表变化事件，用于发现新服配置启动新服actor */
    private lateinit var zkWorldChildCache: PathChildrenCache

    val es = EventSystem<AreaCache>()
    val hs = HeartSystem<AreaCache>()

    var worldAskMsgDeal: MutableMap<World2WorldAskReq.MsgCase, World2WorldAskDealBase> = mutableMapOf()
    var worldTellMsgDeal: MutableMap<World2WorldTell.MsgCase, World2WorldTellDealBase> = mutableMapOf()
    var homeAskMsgDeal: MutableMap<Home2WorldAskReq.MsgCase, Home2WorldAskDealBase> = mutableMapOf()
    var homeTellMsgDeal: MutableMap<Home2WorldTell.MsgCase, Home2WorldTellDealBase> = mutableMapOf()
    var publicTellMsgDeal: MutableMap<Public2WorldTell.MsgCase, Public2WorldTellDealBase> = mutableMapOf()

    // kryo
    var gMsgDeal: MutableMap<Class<out KryoAskWorldMessage>, WorldKryoAskDealBase> = mutableMapOf()

    // worldManager
    var worldManagerAskMsgDeal: MutableMap<World2WorldManagerAskReq.MsgCase, World2WorldManagerAskDealBase> =
        mutableMapOf()
    var publicManagerTellMsgDeal: MutableMap<PublicManager2WorldManagerTell.MsgCase, WorldManagerTellDealBase> =
        mutableMapOf()
    var worldManagerTellMsgDeal: MutableMap<World2WorldManagerTell.MsgCase, WorldManagerTellDealBase> =
        mutableMapOf()

    /**
     * 使用multicast服务时需要调用[initMulticastServiceRouter]初始化
     */
    lateinit var multicastServiceRouter: ActorRef private set

    lateinit var allianceManagerProxy: ActorRef private set// 对公共服管理节点的访问

    lateinit var worldManagerProxy: ActorRef private set // 对世界公共缓存节点的访问

    val battleServiceMgr = BattleServiceMgr()  //战斗服务管理

    private val worldManagerInfoFromPublicManager =
        WorldManagerInfoFromPublicManager(hashMapOf(), hashMapOf(), 0, hashMapOf(), hashMapOf())// 来自公共管理服的世界公共数据

    fun getWorldManagerInfoFromPublicManager(): WorldManagerInfoFromPublicManager {
        synchronized(worldManagerInfoFromPublicManager) {
            return worldManagerInfoFromPublicManager
        }
    }

    // 各区上报过来的各种数据
    private val wonderInfos: HashMap<Long, EveryWorldInfo> = hashMapOf() // key 服务器编号 value 区数据

    fun getAllWonderInfos(): HashMap<Long, EveryWorldInfo> {
        synchronized(wonderInfos) {
            return wonderInfos
        }
    }

    data class EveryWorldInfo(
        var worldId: Long, // 区ID
        var wonderInfo: WonderInfo // 奇观数据
    )

    data class WonderInfo(
        var wonderState: Int
    )

    init {
        println("WorldProcess init")
    }

    override fun beforeCreatingActorSystem() {
        // 初始化数据库访问
        initCommonDao {
            if (startConfig.createTable) {
                it.setProperty("hbm2ddl.auto", "create")
                it.setProperty("hibernate.hbm2ddl.auto", "create")
            }
        }

        // 初始化序列化反序列化器
        initBlobSerializer()

        // 准备进程配置
        dealFetchedConfig()

        // 初始化World节点的zk缓存
        initWorldChildCache()

        //初始化grpc服务
        initGrpcService()
    }

    override fun afterCreatingActorSystem() {
        // 注册member up后的处理，主要用于world的初始化
        wakeUpAllWorldOnMemberUp()

        // 初始化游戏配置文件
        pcs.initProtoCache()

        // 初始化模块
        initModules()

        // 注册消息处理
        registerDeals()

        // 启动公会管理器代理
        startAllianceManagerProxy()

        // 启动世界服的 Shard
        startWorldShardRegion()

        // 启动世界公共缓存akka
        startWorldManager()

        // 启动公共服的 Shard 代理
        startPublicShardProxy()

        // 启动玩家服的 Shard 代理
        startHomeShardProxy()

        // 初始化多播路由
        initMulticastServiceRouter()
    }


    /**
     * 启动公会管理器代理
     */
    private fun startAllianceManagerProxy() {
        val proxySettings = ClusterSingletonProxySettings.create(actorSystem).withRole(ClusterRole.public.name)
        allianceManagerProxy =
                actorSystem.actorOf(ClusterSingletonProxy.props(ALLIANCE_MANAGER_PATH, proxySettings))
    }

    /**
     * 这个方法是用于在world节点发生变化时初始化World
     */
    private fun initWorldChildCache() {
        // 建立监听World的Zk节点的对象
        val pathCache = PathChildrenCache(
            zkDao.zkClient,
            WORLD_AREA_NODE_NAME,
            true,
            NamedThreadFactory("game_world_path_cache")
        ).apply {
            start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE)
        }

        // 监听变化，并处理
        pathCache.listenable.addListener(PathChildrenCacheListener { _, event ->
            when (event.type) {
                PathChildrenCacheEvent.Type.CHILD_ADDED -> {
                    logger.lzInfo { "新增游戏世界: ${event.data.path}" }

                    val byteArrayWorldArea = event.data.data
                    val jsonWorldArea = byteArrayWorldArea.toString(Charsets.UTF_8)
                    val zkWorldConfig = toObj<WorldArea>(jsonWorldArea)
                    val worldArea = zkWorldArea2WorldAreaConfig(zkWorldConfig)

                    // 如果属于当前集群
                    logger.lzInfo { " - ${worldArea.clusterId}  ${processConfig.clusterId}" }
                    if (worldArea.clusterId == processConfig.clusterId) {
                        // 添加区配置
                        processConfig.addSpecAreaCOnfig(worldArea)

                        // 唤醒目标区
                        wakeUpWorld(worldArea)
                    }
                }
                PathChildrenCacheEvent.Type.CHILD_REMOVED -> {
                    logger.warn("游戏世界配置节点${event.data.path}被删除,不做处理,游戏世界需要用显式用运维指令停止")
                }
                PathChildrenCacheEvent.Type.CHILD_UPDATED -> {
                    logger.lzInfo { "游戏世界配置节点${event.data.path}被更新 " }
                }
                else -> return@PathChildrenCacheListener
            }
        })

        zkWorldChildCache = pathCache
    }

    private fun initMulticastServiceRouter() {
        multicastServiceRouter = actorSystem.actorOf(FromConfig.getInstance().props(), "multicastServiceRouter")
    }

    /**
     * 初始化grpc服务
     */
    private fun initGrpcService() {
        // 建立监听Grpc服务的Zk节点的对象
        val pathCache = PathChildrenCache(
            zkDao.zkClient,
            "$GRPC_SERVICE_NODE_NAME/${processConfig.clusterId}",
            true,
            NamedThreadFactory("grpc_service_path_cache")
        ).apply {
            start(PathChildrenCache.StartMode.NORMAL)
        }

        // 监听变化，并处理
        pathCache.listenable.addListener(PathChildrenCacheListener { _, event ->
            when (event.type) {
                PathChildrenCacheEvent.Type.CHILD_ADDED -> {
                    logger.lzWarn { "新增grpc服务: ${event.data.data.toString(charset = Charsets.UTF_8)}" }
                    battleServiceMgr.addBattleService(event.data.data.toString(charset = Charsets.UTF_8))
                }
                PathChildrenCacheEvent.Type.CHILD_REMOVED -> {
                    logger.lzWarn { "grpc服务移除：${event.data.data.toString(charset = Charsets.UTF_8)}" }
                    battleServiceMgr.removeBattleService(event.data.data.toString(charset = Charsets.UTF_8))
                }
                PathChildrenCacheEvent.Type.CHILD_UPDATED -> {
                    logger.lzWarn { "grpc服务更新：${event.data.data.toString(charset = Charsets.UTF_8)}" }
                }
                else -> return@PathChildrenCacheListener
            }
        })
    }

    private fun wakeUpAllWorldOnMemberUp() {
        Cluster.get(actorSystem).registerOnMemberUp {
            logger.lzInfo { "world member up 处理" }

            // SS：等待System节点启动完毕
            while (state == ServerState.STARTING) {
                Thread.sleep(100L)
            }

            val st = state
            if (st == ServerState.STARTED) {
                // 最小world节点个数不会配置为所有的节点个数，up时可能不是所有的world region都已经注册。
                // 等待一小段时间再发wake up，让初次分配world shard更均衡
                TimeUnit.SECONDS.sleep(10L) // SS：这里等待10s

                val allAliveWorlds = zkWorldChildCache.currentData
                    .mapNotNull { child ->
                        val byteArrayWorldArea = child.data
                        val jsonWorldArea = byteArrayWorldArea.toString(Charsets.UTF_8)
                        val zkWorldConfig = toObj<WorldArea>(jsonWorldArea)

                        // 添加区配置
                        val worldArea = zkWorldArea2WorldAreaConfig(zkWorldConfig)

                        logger.lzInfo { "已存在的游戏世界配置节点${zkWorldConfig.pltAreaNo} " }

                        worldArea
                    }
                    .filter { worldArea ->
                        worldArea.clusterId == processConfig.clusterId
                    }

                allAliveWorlds.forEach { worldArea ->
                    // 添加到区列表中
                    processConfig.addSpecAreaCOnfig(worldArea)

                    // 唤醒
                    wakeUpWorld(worldArea)
                }

                // 让世界管理节点加载配置
                val addNewAreaTell = AddNewAreaTell.newBuilder()
                addNewAreaTell.worldId = 0
                worldManagerProxy.tell(
                    fillW2WMTellMsgHeader { it.addNewAreaTell = addNewAreaTell.build() },
                    ActorRef.noSender()
                )
            }
        }
    }

    /**
     * **注意：这里的代码不在main线程执行，注意线程安全问题**
     */
    private fun wakeUpWorld(areaConfig: AreaConfig) {
        val worldId = areaConfig.pltAreaNo
        val worldShardRegion = ClusterSharding.get(wpm.actorSystem).shardRegion(GameWorldShard.world.name)

        val wakeUpServer = WakeUpWorld.newBuilder()
        wakeUpServer.worldId = worldId
        worldShardRegion.tell(wakeUpServer.build(), ActorRef.noSender())

        logger.lzInfo { "Send WakeUp to $worldId" }
    }

    // 加载配置
    private fun dealFetchedConfig() {
        val processConfig = processConfig
        processConfig.gameId = platform.gameId.toInt()
        processConfig.id = ipProcess.id.toInt()

        processConfig.tcpAddr = ipProcess.tcpAddr
        processConfig.tcpPort = ipProcess.tcpPort
        processConfig.processInnerAddr = ipProcess.processIp

        processConfig.clusterId = commCfg.id

        filterWordsFromGm = LinkedList()
    }

    private fun zkWorldArea2WorldAreaConfig(zkWorldArea: WorldArea): AreaConfig {
        val areaConfig = AreaConfig()
        areaConfig.areaId = zkWorldArea.id
        areaConfig.areaNo = zkWorldArea.areaNo
        areaConfig.areaName = zkWorldArea.gameAreaName
        areaConfig.pltAreaNo = zkWorldArea.pltAreaNo
        areaConfig.opgameId = zkWorldArea.opgameId
        areaConfig.areaState = zkWorldArea.areaState
        areaConfig.deployState = zkWorldArea.deployState == 1
        areaConfig.loginKey = zkWorldArea.loginKey
        areaConfig.payKey = zkWorldArea.payKey
        areaConfig.areaPublishTime = zkWorldArea.openAreaDate
        areaConfig.giftKey = zkWorldArea.giftKey
        areaConfig.clusterId = zkWorldArea.clusterId

        return areaConfig
    }

    // 创建World的Region
    private fun startWorldShardRegion() {
        val settings = ClusterShardingSettings.create(actorSystem)
            .withRole(role.name)
        val region = ClusterSharding.get(actorSystem)
            .start(
                GameWorldShard.world.name,
                WorldActor.props(),
                settings,
                WorldMessageExtractor(),
                ShardCoordinator.LeastShardAllocationStrategy(5, 1),
                HandoffTell.newBuilder().build()
            )
        logger.info("SharedRegion $region started.")
    }

    /**
     * 启动世界公共缓存akka
     */
    private fun startWorldManager() {
        worldManagerProxy = actorSystem.actorOf(WorldManagerActor.props(), "p2wSyncInfo")
    }

    override fun stopImpl() {
        tryCatch(logger) {
            // isInitialized比较特殊！是为了确认lateinit属性是否已经初始化。
            if (this::zkWorldChildCache.isInitialized) {
                zkWorldChildCache.close()
            }
        }
    }

    /**
     * 生成唯一ID
     */
    fun generateObjIdNew(areaCache: AreaCache): Long {
        val areaId = areaCache.areaConfig.areaId

        // 原子操作
        return synchronized(areaCache.areaConfig) {
            areaCache.currentTimeDiff += 1
            areaCache.currentTimeDiff.shl(14) + areaId
        }
    }

    // 填充世界管理服发往联盟管理服Ask消息的消息头
    fun fillWorldManager2PublicManagerAskMsgHeader(
        wrap: (wrapMsg: WorldManager2PublicManagerAskReq.Builder) -> Unit
    ): WorldManager2PublicManagerAskReq {
        return WorldManager2PublicManagerAskReq.newBuilder().let {
            wrap(it)

            it.build()
        }
    }

    // 填充发往玩家服Tell消息的消息头
    fun fillW2WMTellMsgHeader(
        wrap: (wrapMsg: World2WorldManagerTell.Builder) -> Unit
    ): World2WorldManagerTell {
        return World2WorldManagerTell.newBuilder().let { it ->
            wrap(it)

            it.build()
        }
    }
}

// 世界进程单例
var wpm = object : WorldProcess() {

}