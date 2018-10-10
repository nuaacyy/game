package com.point18.slg2d.public

import akka.actor.ActorRef
import akka.cluster.sharding.ClusterSharding
import akka.cluster.sharding.ClusterShardingSettings
import akka.cluster.sharding.ShardCoordinator
import akka.cluster.singleton.ClusterSingletonManager
import akka.cluster.singleton.ClusterSingletonManagerSettings
import akka.cluster.singleton.ClusterSingletonProxy
import akka.cluster.singleton.ClusterSingletonProxySettings
import akka.routing.FromConfig
import com.point18.slg2d.common.baseg.*
import com.point18.slg2d.common.commonfunc.IdGenerator
import com.point18.slg2d.common.commonfunc.LockObj
import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.common.constg.ALLIANCE_MANAGER
import com.point18.slg2d.common.constg.ALLIANCE_MANAGER_PATH
import com.point18.slg2d.common.constg.PROCESS_PUBLIC
import com.point18.slg2d.common.msgtrans.PublicMessageExtractor
import com.point18.slg2d.public.actor.PublicActor
import com.point18.slg2d.public.actor.PublicManagerActor
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.public.datacache.PublicManagerCache
import com.point18.slg2d.public.pmodule.*
import pb4server.*

open class PublicProcess : ProcessMgr(PROCESS_PUBLIC, ClusterRole.public) {

    override val includingShards = listOf(GameWorldShard.public)

//    lateinit var allPublicCache: PublicCache

    // 普通联盟节点的事件与心跳
    val es = EventSystem<PublicCache>()
    val hs = HeartSystem<PublicCache>()

    // 普通联盟节点的来自世界/home的消息
    var worldMsgDeal: MutableMap<World2PublicAskReq.MsgCase, PublicAskDealBase> = mutableMapOf()
    var homeMsgDeal: MutableMap<Home2PublicAskReq.MsgCase, PublicAskDealBase> = mutableMapOf()
    var publicMsgDeal: MutableMap<Public2PublicAskReq.MsgCase, PublicAskDealBase> = mutableMapOf()
    var publicManagerMsgDeal: MutableMap<PublicManager2PublicAskReq.MsgCase, PublicAskDealBase> = mutableMapOf()
    var publicManagerTellDeal: MutableMap<PublicManager2PublicTell.MsgCase, PublicTellDealBase> = mutableMapOf()


    // 联盟管理节点的事件与心跳
    val mes = EventSystem<PublicManagerCache>()
    val mhs = HeartSystem<PublicManagerCache>()

    // 联盟管理节点的来自世界/home的消息
    var worldMsgManagerDeal: MutableMap<World2PublicManagerAskReq.MsgCase, PublicManagerAskDealBase> = mutableMapOf()
    var worldManagerMsgManagerDeal: MutableMap<WorldManager2PublicManagerAskReq.MsgCase, PublicManagerAskDealBase> =
        mutableMapOf()
    var publicTellPublicManagerMsgDeal: MutableMap<Public2PublicManagerTell.MsgCase, PublicManagerTellDealBase> =
        mutableMapOf()
    var homeMsgManagerDeal: MutableMap<Home2PublicManagerAskReq.MsgCase, PublicManagerAskDealBase> = mutableMapOf()

    private lateinit var idGen: IdGenerator
    private val idGenLockObj = LockObj()

    lateinit var allianceManagerProxy: ActorRef // 对公共服管理节点的访问

    lateinit var p2wSyncInfoRouter: ActorRef private set // 路由

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
        com.point18.slg2d.common.pc.pcs.initProtoCache()

        // 初始化模块
        initModules()

        // 注册消息处理
        registerDeals()

        // 启动公会管理器
        startAllianceManager()

        // 启动公共服的 Shard
        startPublicShardRegion()

        // 启动世界服的 Shard 代理
        startWorldShardProxy()

        // 启动home服的 Shard 代理
        startHomeShardProxy()

        // 启动公会管理器代理
        startAllianceManagerProxy()

        // 初始化路由
        initMulticastServiceRouter()

        // 初始化公共
        initPublicBase()
    }

    private fun initMulticastServiceRouter() {
        p2wSyncInfoRouter = actorSystem.actorOf(FromConfig.getInstance().props(), "p2wSyncInfoRouter")
    }

    // 加载配置
    private fun dealFetchedConfig() {
        val processConfig = processConfig

        processConfig.processInnerAddr = ipProcess.processIp
        processConfig.clusterId = commCfg.id
    }

    // 初始化游戏区
    private fun initPublicBase(): Exception? {
        // 如果需要, 重建数据库结构, 创建完后退出
        if (startConfig.createTable) {
            // 这步只可能在【启动服务器建库】或【动态加区】时执行！其他地方就不要考虑添加这部分代码了。
        }

        return null
    }

    /**
     * 启动公会管理器
     */
    private fun startAllianceManager() {
        val singletonSettings = ClusterSingletonManagerSettings.create(actorSystem).withRole(ClusterRole.public.name)
        actorSystem.actorOf(
            ClusterSingletonManager.props(
                PublicManagerActor.props(),
                HandoffTell.newBuilder().build(),
                singletonSettings
            ), ALLIANCE_MANAGER
        )
    }

    /**
     * 启动公会管理器代理
     */
    private fun startAllianceManagerProxy() {
        val proxySettings = ClusterSingletonProxySettings.create(actorSystem).withRole(ClusterRole.public.name)
        allianceManagerProxy =
                actorSystem.actorOf(ClusterSingletonProxy.props(ALLIANCE_MANAGER_PATH, proxySettings))
    }

    // 创建 Public 的Region
    private fun startPublicShardRegion() {
        val settings = ClusterShardingSettings.create(actorSystem)
            .withRole(role.name)
        val region = ClusterSharding.get(actorSystem)
            .start(
                GameWorldShard.public.name,
                PublicActor.props(),
                settings,
                PublicMessageExtractor(1),
                ShardCoordinator.LeastShardAllocationStrategy(5, 1),
                HandoffTell.newBuilder().build()
            )
        logger.info("SharedRegion $region started.")
    }

    fun tell2World(publicActor: PublicActor, pwMsg: Public2WorldTell) {
        publicActor.worldShardRegion.tell(pwMsg, ActorRef.noSender())
    }

    // 填充发往联盟管理节点的消息的消息头
    fun fillPublic2PublicManagerTellMsgHeader(
        wrap: (wrapMsg: Public2PublicManagerTell.Builder) -> Unit
    ): Public2PublicManagerTell {
        val wrapMsg = Public2PublicManagerTell.newBuilder()
        wrap(wrapMsg)

        return wrapMsg.build()
    }

    fun tell2PublicManager(pwMsg: Public2PublicManagerTell) {
        ppm.allianceManagerProxy.tell(pwMsg, ActorRef.noSender())
    }

    // 填充发往世界的消息的消息头
    fun fillPublic2WorldAskMsgHeader(
        worldId: Long,
        playerId: Long,
        wrap: (wrapMsg: Public2WorldTell.Builder) -> Unit
    ): Public2WorldTell {
        val wrapMsg = Public2WorldTell.newBuilder()
        wrapMsg.worldId = worldId
        wrapMsg.playerId = playerId
        wrap(wrapMsg)

        return wrapMsg.build()
    }

    fun tell2Home(publicActor: PublicActor, pwMsg: Public2HomeTell) {
        publicActor.homeShardRegion.tell(pwMsg, ActorRef.noSender())
    }

    // 填充发往玩家服的消息的消息头
    fun fillPublic2HomeTellMsgHeader(
        playerId: Long,
        wrap: (wrapMsg: Public2HomeTell.Builder) -> Unit
    ): Public2HomeTell {
        val wrapMsg = Public2HomeTell.newBuilder()
        wrapMsg.playerId = playerId
        wrap(wrapMsg)

        return wrapMsg.build()
    }

    // 填充发往世界管理服的tell消息头
    fun fillPublicManager2WorldManagerTellMsgHeader(
        wrap: (wrapMsg: PublicManager2WorldManagerTell.Builder) -> Unit
    ): PublicManager2WorldManagerTell {
        val wrapMsg = PublicManager2WorldManagerTell.newBuilder()
        wrap(wrapMsg)

        return wrapMsg.build()
    }

    private fun initIdGen() {
        idGen = IdGenerator(ipProcess.id)
    }

    // Public服的ID生成
    fun generateObjIdNew(): Long {
        return synchronized(idGenLockObj) {
            idGen.nextId()
        }
    }
}

var ppm = object : PublicProcess() {

}