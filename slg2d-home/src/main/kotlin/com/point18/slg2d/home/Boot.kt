package com.point18.slg2d.home

import akka.actor.ActorRef
import akka.cluster.sharding.ClusterSharding
import akka.cluster.sharding.ClusterShardingSettings
import akka.cluster.sharding.ShardCoordinator
import akka.cluster.singleton.ClusterSingletonProxy
import akka.cluster.singleton.ClusterSingletonProxySettings
import akka.routing.FromConfig
import com.point18.slg2d.common.baseg.ClusterRole
import com.point18.slg2d.common.baseg.GameWorldShard
import com.point18.slg2d.common.baseg.ProcessMgr
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.ALLIANCE_MANAGER_PATH
import com.point18.slg2d.common.constg.PROCESS_HOME
import com.point18.slg2d.common.msgtrans.HomeMessageExtractor
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.initModules
import com.point18.slg2d.home.module.registerMsgDeal
import pb4server.HandoffTell
import xyz.ariane.util.lzInfo

open class HomeProcess : ProcessMgr(PROCESS_HOME, ClusterRole.home) {

    var inited: Boolean = false // 是否初始化完毕了

    override val includingShards = listOf(GameWorldShard.player)

    private lateinit var idGen: IdGenerator
    private val idGenLockObj = LockObj()

    /**
     * 使用multicast服务时需要调用[initMulticastServiceRouter]初始化
     */
    lateinit var multicastServiceRouter: ActorRef private set

    lateinit var allianceManagerProxy: ActorRef // 对公共服管理节点的访问

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

        // 加载进程配置
        dealFetchedConfig()

        // 初始化ID生成
        initIdGen()
    }

    override fun afterCreatingActorSystem() {
        // 初始化游戏配置文件
        pcs.initProtoCache()

        // 初始化模块
        initModules()

        // 注册消息处理
        registerMsgDeal()

        // 启动玩家服的 Shard
        startHomeShardRegion()

        // 启动世界服的 Shard 代理
        startWorldShardProxy()

        // 启动公共服的 Shard 代理
        startPublicShardProxy()

        // 启动公会管理器代理
        startAllianceManagerProxy()

        // 初始化多播路由
        initMulticastServiceRouter()


        // 初始化Home
        initHomeBase()
    }

    /**
     * 启动公会管理器代理
     */
    private fun startAllianceManagerProxy() {
        val proxySettings = ClusterSingletonProxySettings.create(actorSystem).withRole(ClusterRole.public.name)
        allianceManagerProxy =
                actorSystem.actorOf(ClusterSingletonProxy.props(ALLIANCE_MANAGER_PATH, proxySettings))
    }

    // 初始化游戏区
    private fun initHomeBase(): Exception? {
        // 如果需要, 重建数据库结构, 创建完后退出
        if (startConfig.createTable) {
            // 这步只可能在【启动服务器建库】或【动态加区】时执行！其他地方就不要考虑添加这部分代码了。

        }

        val config = AreaConfig()
        config.areaId = 1

        // 标记为初始化完毕。
        // 注意：这个必须在协程开始运行前设置，否则协程逻辑拿不到areabase
        inited = true

        normalLog.lzInfo { "玩家服初始化完毕" }

        return null
    }

    // 加载配置
    private fun dealFetchedConfig() {
        val processConfig = processConfig

        processConfig.processInnerAddr = ipProcess.processIp
        processConfig.clusterId = commCfg.id
    }

    private fun initMulticastServiceRouter() {
        multicastServiceRouter = actorSystem.actorOf(FromConfig.getInstance().props(), "multicastServiceRouter")
    }

    // 创建Home的Region
    private fun startHomeShardRegion() {
        val settings = ClusterShardingSettings.create(actorSystem)
            .withRole(role.name)
        val region = ClusterSharding.get(actorSystem)
            .start(
                GameWorldShard.player.name,
                PlayerActor.props(),
                settings,
                HomeMessageExtractor(1),
                ShardCoordinator.LeastShardAllocationStrategy(5, 1),
                HandoffTell.newBuilder().build()
            )
        logger.info("SharedRegion $region started.")
    }

    private fun initIdGen() {
        idGen = IdGenerator(ipProcess.id)
    }

    // Home服的ID生成
    fun generateObjIdNew(): Long {
        return synchronized(idGenLockObj) {
            idGen.nextId()
        }
    }

}

var hpm = object : HomeProcess() {

}