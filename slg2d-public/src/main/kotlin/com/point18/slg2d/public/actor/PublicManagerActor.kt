package com.point18.slg2d.public.actor

import akka.actor.*
import akka.cluster.sharding.ClusterSharding
import akka.cluster.sharding.ShardRegion
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.japi.pf.ReceiveBuilder
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.baseg.ACSBase
import com.point18.slg2d.common.baseg.GameWorldShard
import com.point18.slg2d.common.baseg.HeartEvent
import com.point18.slg2d.common.commonfunc.AreaConfig
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.AKKA_MAILBOX_SMALL
import com.point18.slg2d.public.PublicMenagerDatabase
import com.point18.slg2d.public.datacache.PublicManagerCache
import com.point18.slg2d.public.ppm
import pb4server.*
import scala.concurrent.duration.Duration
import scala.concurrent.duration.FiniteDuration
import xyz.ariane.util.*
import java.time.Clock
import java.time.Instant
import java.util.concurrent.TimeUnit

// 用于启动节点的时候就切换状态到up
data class PreStartChangeState(val value: Int)

class PublicManagerActor : AbstractActorWithStash() {

    var logger: LoggingAdapter = Logging.getLogger(context.system(), javaClass)

    lateinit var publicCache: PublicManagerCache

    val clock: Clock = Clock.systemDefaultZone()

    var publicId: Long = 0
        private set(pid) {
            field = pid
        }

    private var cancelTick: Cancellable? = null

    // 对世界服的访问引用
    lateinit var worldShardRegion: ActorRef
        private set

    // 对home服的访问引用
    lateinit var homeShardRegion: ActorRef
        private set

    // 对public服的访问引用
    lateinit var publicShardProxy: ActorRef
        private set

    val db = PublicMenagerDatabase(aPublic = this, fetchDao = { ppm.commonDao })

    lateinit var acsFactory: ActorCompletionStageFactory
        private set

    // 创建一个ACS
    inline fun <reified T> createACS(): ACSBase<T> {
        return PublicManagerACS(publicCache, acsFactory.create(), publicCache.currentClientMsgNo)
    }

    private fun baseReceiveBuilder(): ReceiveBuilder {
        val receive = receiveBuilder()
            .match(Runnable::class.java) { this@PublicManagerActor.dealAcsResp(it) }
        return receive
    }

    // 行为：初始化
    private val init: Receive = baseReceiveBuilder()
        .match(HandoffTell::class.java) { enterHandoffState(it) }
        .match(PreStartChangeState::class.java) { msg ->
            dealInit(msg)
            stash()
        }
        .matchAny { msg ->
            dealInit(msg)
            stash()
        }
        .build()

    // 行为：数据加载
    private val loading: Receive = baseReceiveBuilder()
        .matchAny { msg ->
            stash()
        }
        .build()

    // 行为：正式运行
    private val up: Receive = baseReceiveBuilder()
        .match(HandoffTell::class.java) { enterHandoffState(it) }
        .match(MessageLite::class.java) { this@PublicManagerActor.dealAskMsg(it) }
        .match(HeartEvent::class.java) { this@PublicManagerActor.dealHeart(it) }
        .build()

    /** 跨集群迁移/停服 TODO 增加二次wdb检查 */
    private val handoff: Receive = baseReceiveBuilder()
        .match(HeartEvent::class.java) { dealHandoff(it) }.build()

    private fun dealHandoff(it: HeartEvent) {
        try {
            // 处理定时任务
            val now = clock.instant()
            dbTick(now)
            when {
                !db.isAllClean() -> {
                    enterTerminatedState()
                    logger.lzInfo { "PublicManager force stopped, reason: global flag." }
                }

                acsFactory.hasPendingStages() -> {
                    logger.lzInfo { "handoff - hasPendingStages ${acsFactory.countPendingStages()}" }
                    if (acsFactory.checkWaitingPendingTimeout(now)) {
                        logger.lzWarning { "Public $publicId, waiting acs timeout, left num: ${acsFactory.countPendingStages()}" }
                        enterTerminatedState()
                    }
                }
                else -> enterTerminatedState()
            }
        } catch (e: Exception) {
            logger.error(e, "PublicManager@handoff - HeartEvent handle error: $it")
        }
    }

    private val terminated: Receive = baseReceiveBuilder().build()

    var state: Receive = init

    companion object {
        fun props(): Props {
            return Props.create(PublicManagerActor::class.java)
        }
    }

    object WorkerName {
        const val blockingRpc = "blockingRpc"
    }

    fun passivate() {
        // TODO 这里需要搞清楚为什么要这么写
        logger.lzInfo { "PublicManagerActor：执行 passivate，开始结束Actor" }

        context.parent().tell(ShardRegion.Passivate(HandoffTell.newBuilder().build()), self)
    }

    // 从消息中分离出publicId，以供初始化使用。
    private fun handleInitMsgExtractingPublicId(msg: Any): Long = when (msg) {
        is InitPublic -> {
            // 不需要缓存消息
            msg.publicId
        }
        is Home2PublicAskReq -> {
            stash()
            msg.publicId
        }
        is World2PublicAskReq -> {
            stash()
            msg.publicId
        }
        else -> -1L
    }

    /**
     * 加载数据
     */
    private fun dbLoad() {
        logger.lzInfo { "PublicManager $publicId bootstrapping... " }

        // 建立数据缓存
        val config = AreaConfig()
        config.areaId = 1
        val publicCache = PublicManagerCache(config, this, db)
        this.publicCache = publicCache

        // 加载数据
        db.dbLoad()
    }

    private fun dealInit(msg: Any) {
        try {
            handleInitMsgExtractingPublicId(msg)
            // 进入加载阶段
            enterLoadingState()
        } catch (e: Throwable) {
            logger.error(e, "Public@init - Any handle error: $msg")
        }
    }

    private fun dealAskMsg(msg: MessageLite) {
        try {
            when (msg) {
                is World2PublicManagerAskReq -> {
                    dealWorld2PublicManagerAsk(msg)
                }

                is WorldManager2PublicManagerAskReq -> {
                    dealWorldManager2PublicManagerAsk(msg)
                }
                is Home2PublicManagerAskReq -> {
                    dealHome2PublicManagerAsk(msg)
                }
//                is Home2PublicManagerAskReq -> {
//                    val msgCase = msg.msgCase
//                    ppm.homeMsgManagerDeal[msgCase]
//                }
                is Public2PublicManagerTell -> {
                    dealPublic2PublicManagerTell(msg)
                }
                else -> logger.lzWarning { "PublicManager 服上，dealAskMsg 找不到消息类型为的 $msg MsgDeal" }
            }

        } catch (e: Throwable) {
            logger.error(e, "PublicManager@up - MessageLite handle error: $msg")
        }

    }

    private fun dealWorldManager2PublicManagerAsk(msg: WorldManager2PublicManagerAskReq) {
        val msgCase = msg.msgCase
        val deal = ppm.worldManagerMsgManagerDeal[msgCase]
        if (deal == null) {
            normalLog.lzWarn { "PublicManager服上，在worldManagerMsgManagerDeal中找不到消息类型为的 $msg MsgDeal" }
            return
        }

        val rt = try {
            // 处理请求
            deal.dealAsk(publicCache, msg)

        } catch (e: Exception) {
            normalLog.lzWarn { "处理Ask请求出现异常:$e" }
            sender.tell(Status.Failure(e), ActorRef.noSender())
            return
        }

        sender.tell(rt, self)
    }

    private fun dealWorld2PublicManagerAsk(msg: World2PublicManagerAskReq) {
        val msgCase = msg.msgCase
        val deal = ppm.worldMsgManagerDeal[msgCase]
        if (deal == null) {
            normalLog.lzWarn { "PublicManager服上，在worldMsgManagerDeal中找不到消息类型为的 $msg MsgDeal" }
            return
        }

        val rt = try {
            // 处理请求
            deal.dealAsk(publicCache, msg)

        } catch (e: Exception) {
            normalLog.lzWarn { "处理Ask请求出现异常:$e" }
            sender.tell(Status.Failure(e), ActorRef.noSender())
            return
        }

        sender.tell(rt, self)
    }

    private fun dealHome2PublicManagerAsk(msg: Home2PublicManagerAskReq) {
        val msgCase = msg.msgCase
        val deal = ppm.homeMsgManagerDeal[msgCase]
        if (deal == null) {
            normalLog.lzWarn { "PublicManager服上，在homeMsgManagerDeal中找不到消息类型为的 $msg MsgDeal" }
            return
        }

        val rt = try {
            // 处理请求
            deal.dealAsk(publicCache, msg)

        } catch (e: Exception) {
            normalLog.lzWarn { "处理Ask请求出现异常:$e" }
            sender.tell(Status.Failure(e), ActorRef.noSender())
            return
        }

        sender.tell(rt, self)
    }

    private fun dealPublic2PublicManagerTell(msg: Public2PublicManagerTell) {
        val msgCase = msg.msgCase
        val deal = ppm.publicTellPublicManagerMsgDeal[msgCase]
        if (deal == null) {
            normalLog.lzWarn { "PublicManager服上，在publicTellPublicManagerMsgDeal中找不到消息类型为的 $msg MsgDeal" }
            return
        }

        try {
            // 处理请求
            deal.dealTell(publicCache, msg)

        } catch (e: Exception) {
            normalLog.lzWarn { "处理Tell请求出现异常:$e" }
            sender.tell(Status.Failure(e), ActorRef.noSender())
            return
        }
    }


    // 处理ACS的返回
    private fun dealAcsResp(runnable: Runnable) {
        try {
            runnable.run()
        } catch (e: Throwable) {
            logger.error(e, "PublicManager - InitWorld handle error: $runnable")
        }

    }

    // 处理心跳
    private fun dealHeart(it: HeartEvent) {
        try {
            // 处理心跳
            for (heartHandler in ppm.mhs.queues) {
                heartHandler.handleHeart(publicCache)
            }

            val now = clock.instant()
            dbTick(now)

        } catch (e: Exception) {
            logger.error(e, "PublicManager@up - HeartEvent handle error: $it")
        }

    }

    private fun dbTick(now: Instant) {
        tryCatch(logger) { db.tick(now) }
    }

    private fun enterLoadingState() {
        logger.lzInfo { "PublicManagerActor：进入 loading 状态" }

        state = loading
        context.become(loading)

        // 处理游戏数据
        dbLoad()
    }

    // 进入正式运行状态
    fun enterUpState() {
        logger.lzInfo { "PublicManagerActor：进入 up 状态" }

        state = up
        context.become(up)

        unstashAll() // 弹出所有暂存的消息

        // 发布心跳
        val delayDuration = FiniteDuration.apply(1L, TimeUnit.SECONDS)
        val intervalDuration = FiniteDuration.apply(1L, TimeUnit.SECONDS)
        this.cancelTick = context.system().scheduler().schedule(
            delayDuration, intervalDuration, self, HeartEvent(System.currentTimeMillis()),
            context.dispatcher(), ActorRef.noSender()
        )
    }

    private fun enterHandoffState(it: HandoffTell) {
        try {
            logger.lzInfo { "PublicManagerActor：进入 handoff 状态" }

            state = handoff

            context.become(handoff)

            // DB执行强制检测
            val now = clock.instant()
            tryCatch(logger) { db.wdb.forceCheckAll(now) }

            // 等待pending结束
            acsFactory.startWaitingPendings(now)
        } catch (e: Throwable) {
            logger.error(e, "PublicManager@init - Handoff handle error: $it")
        }

    }

    private fun enterTerminatedState() {
        logger.lzInfo { "PublicManagerActor：进入 terminated 状态" }

        state = terminated

        context.become(terminated)

        tryCatch(logger) { db.wdb.stop() }
        tryCatch(logger) { cancelTick?.cancel() }

        context.stop(self)
    }

    override fun preStart() {
        // SS：DB IO用的Worker Actor
        val dbIoWorker: ActorRef =
            context.actorOf(Worker.props("publicIoWorker", "akka.actor.p-db-io", AKKA_MAILBOX_SMALL))

        // SS：阻塞RPC用的Worker Actor
        val blockingRpcWorker: ActorRef =
            context.actorOf(Worker.props("publicRpcWorker", "akka.actor.p-blocking-rpc", AKKA_MAILBOX_SMALL))

        // SS：计算用的Worker Actor
        val computationWorker: ActorRef =
            context.actorOf(Worker.props("publicComputationWorker", "akka.actor.p-compute", AKKA_MAILBOX_SMALL))

        // SS：ACS异步操作工厂
        acsFactory = ActorCompletionStageFactory(
            mainActor = self,
            defaultIoWorker = dbIoWorker,
            defaultComputationWorker = computationWorker,
            namedWorkers = mapOf(WorkerName.blockingRpc to blockingRpcWorker),
            logger = logger,
            waitingPendingTimeoutSeconds = 300L
        )

        // 获取home的 shard
        homeShardRegion = ClusterSharding.get(context.system).shardRegion(GameWorldShard.player.name)

        // 获取世界的 shard
        worldShardRegion = ClusterSharding.get(context.system).shardRegion(GameWorldShard.world.name)

        // 获取联盟的 shard
        publicShardProxy = ClusterSharding.get(context.system()).shardRegion(GameWorldShard.public.name)

        // 调度
        context.system().scheduler()
            .scheduleOnce(
                Duration.create(1, TimeUnit.SECONDS),
                self,
                PreStartChangeState(0),
                context.dispatcher(),
                ActorRef.noSender()
            )
    }

    override fun postStop() {
        // 取消心跳
        cancelTick?.cancel()
    }

    override fun createReceive(): Receive {
        return state
    }

    fun fillPublic2PublicAskMsgHeader(
        publicId: Long,
        wrap: (wrapMsg: Public2PublicAskReq.Builder) -> Unit
    ): Public2PublicAskReq {
        assert(publicId != 0L)

        return Public2PublicAskReq.newBuilder().let {
            it.publicId = publicId

            wrap(it)

            it.build()
        }

    }

    // 填充发往联盟管理节点的消息的消息头
    fun fillPublicManager2PublicTellMsgHeader(
        publicId: Long,
        wrap: (wrapMsg: PublicManager2PublicTell.Builder) -> Unit
    ): PublicManager2PublicTell {
        assert(publicId != 0L)


        return PublicManager2PublicTell.newBuilder().let {
            it.publicId = publicId

            wrap(it)

            it.build()
        }
    }

    fun tell2Public(pwMsg: PublicManager2PublicTell) {
        this.publicShardProxy.tell(pwMsg, ActorRef.noSender())
    }

}