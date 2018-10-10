package com.point18.slg2d.public.actor

import akka.actor.*
import akka.cluster.sharding.ClusterSharding
import akka.cluster.sharding.ShardRegion
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.japi.pf.ReceiveBuilder
import com.point18.slg2d.common.baseg.ACSBase
import com.point18.slg2d.common.baseg.GameWorldShard
import com.point18.slg2d.common.baseg.HeartEvent
import com.google.protobuf.MessageLite
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.public.ppm
import xyz.ariane.util.*
import com.point18.slg2d.common.commonfunc.AreaConfig
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.AKKA_MAILBOX_SMALL
import pb4server.*
import scala.concurrent.duration.FiniteDuration
import java.time.Clock
import java.time.Instant
import java.util.concurrent.TimeUnit

class PublicActor : AbstractActorWithStash() {

    var logger: LoggingAdapter = Logging.getLogger(context.system(), javaClass)

    lateinit var publicCache: PublicCache

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

    val db = PublicDatabase(public = this, fetchDao = { ppm.commonDao })

    lateinit var acsFactory: ActorCompletionStageFactory
        private set

    // 创建一个ACS
    inline fun <reified T> createACS(): ACSBase<T> {
        return PublicACS(publicCache, acsFactory.create(), publicCache.currentClientMsgNo)
    }

    private fun baseReceiveBuilder(): ReceiveBuilder {
        val receive = receiveBuilder()
            .match(Runnable::class.java) { this@PublicActor.dealAcsResp(it) }
        return receive
    }

    // 行为：初始化
    private val init: Receive = baseReceiveBuilder()
        .match(HandoffTell::class.java) { enterHandoffState(it) }
        .matchAny { msg ->
            dealInit(msg)
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
        .match(MessageLite::class.java) { this@PublicActor.dealAskMsg(it) }
        .match(HeartEvent::class.java) { this@PublicActor.dealHeart(it) }
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
                    logger.lzInfo { "Public force stopped, reason: global flag." }
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
            logger.error(e, "Public@handoff - HeartEvent handle error: $it")
        }
    }

    private val terminated: Receive = baseReceiveBuilder().build()

    var state: Receive = init

    companion object {
        fun props(): Props {
            return Props.create(PublicActor::class.java)
        }
    }

    object WorkerName {
        const val blockingRpc = "blockingRpc"
    }

    fun passivate() {
        // TODO 这里需要搞清楚为什么要这么写
        logger.lzInfo { "PublicActor：执行 passivate，开始结束Actor" }

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
        logger.lzInfo { "Public $publicId bootstrapping... " }

        // 建立数据缓存
        val config = AreaConfig()
        config.areaId = 1
        val publicCache = PublicCache(config, this, db)
        this.publicCache = publicCache

        // 加载数据
        db.dbLoad()
    }

    private fun dealInit(msg: Any) {
        try {
            // 从消息中提取世界ID
            val publicId = handleInitMsgExtractingPublicId(msg)
            if (publicId < 0L) {
                // SS：走到这里，说明收到了无效ID
                logger.error("Initialize failed, invalid aPublic id: $publicId, msg=$msg, sender: $sender")

                passivate()
                return
            }

            // SS：设置 publicId
            this.publicId = publicId

            // 进入加载阶段
            enterLoadingState()
        } catch (e: Throwable) {
            logger.error(e, "Public@init - Any handle error: $msg")
        }
    }

    private fun dealAskMsg(msg: MessageLite) {
        try {
            when (msg) {
                is World2PublicAskReq -> {
                    dealWorld2PublicAsk(msg)
                }
                is Home2PublicAskReq -> {
                    dealHome2PublicAsk(msg)
                }
                is Public2PublicAskReq -> {
                    dealPublic2PublicAsk(msg)
                }
                is PublicManager2PublicAskReq -> {
                    dealPublicManager2PublicAsk(msg)
                }
                is PublicManager2PublicTell -> {
                    dealPublicManager2PublicTell(msg)
                }
                else -> logger.lzWarning { "Public服上，dealAskMsg 找不到消息类型为的 $msg MsgDeal" }
            }
        } catch (e: Throwable) {
            logger.error(e, "Public@up - MessageLite handle error: $msg")
        }
    }

    private fun dealWorld2PublicAsk(msg: World2PublicAskReq) {
        try {
            val msgCase = msg.msgCase
            val deal = ppm.worldMsgDeal[msgCase]

            if (deal == null) {
                normalLog.lzWarn { "Public服上，在worldMsgDeal找不到消息类型为的 $msg MsgDeal" }
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
        } catch (e: Throwable) {
            logger.error(e, "Public@up - MessageLite handle error: $msg")
        }
    }

    private fun dealHome2PublicAsk(msg: Home2PublicAskReq) {
        try {
            val msgCase = msg.msgCase
            val deal = ppm.homeMsgDeal[msgCase]

            if (deal == null) {
                normalLog.lzWarn { "Public服上，在homeMsgDeal找不到消息类型为的 $msg MsgDeal" }
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
        } catch (e: Throwable) {
            logger.error(e, "Public@up - MessageLite handle error: $msg")
        }
    }

    private fun dealPublic2PublicAsk(msg: Public2PublicAskReq) {
        try {
            val msgCase = msg.msgCase
            val deal = ppm.publicMsgDeal[msgCase]

            if (deal == null) {
                normalLog.lzWarn { "Public服上，在publicMsgDeal找不到消息类型为的 $msg MsgDeal" }
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
        } catch (e: Throwable) {
            logger.error(e, "Public@up - MessageLite handle error: $msg")
        }
    }

    private fun dealPublicManager2PublicAsk(msg: PublicManager2PublicAskReq) {
        try {
            val msgCase = msg.msgCase
            val deal = ppm.publicManagerMsgDeal[msgCase]

            if (deal == null) {
                normalLog.lzWarn { "Public服上，在publicManagerMsgDeal找不到消息类型为的 $msg MsgDeal" }
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
        } catch (e: Throwable) {
            logger.error(e, "Public@up - MessageLite handle error: $msg")
        }
    }

    private fun dealPublicManager2PublicTell(msg: PublicManager2PublicTell) {
        try {
            val msgCase = msg.msgCase
            val deal = ppm.publicManagerTellDeal[msgCase]

            if (deal == null) {
                normalLog.lzWarn { "Public服上，在publicManagerTellDeal找不到消息类型为的 $msg MsgDeal" }
                return
            }

            val rt = try {
                // 处理请求
                deal.dealTell(publicCache, msg)

            } catch (e: Exception) {
                normalLog.lzWarn { "处理Tell请求出现异常:$e" }
                sender.tell(Status.Failure(e), ActorRef.noSender())
                return
            }

        } catch (e: Throwable) {
            logger.error(e, "Public@up - MessageLite handle error: $msg")
        }
    }

    // 处理ACS的返回
    private fun dealAcsResp(runnable: Runnable) {
        try {
            runnable.run()
        } catch (e: Throwable) {
            logger.error(e, "Public - InitWorld handle error: $runnable")
        }

    }

    // 处理心跳
    private fun dealHeart(it: HeartEvent) {
        try {
            // 处理心跳
            for (heartHandler in ppm.hs.queues) {
                heartHandler.handleHeart(publicCache)
            }

            val now = clock.instant()
            dbTick(now)

        } catch (e: Exception) {
            logger.error(e, "Public@up - HeartEvent handle error: $it")
        }

    }

    private fun dbTick(now: Instant) {
        tryCatch(logger) { db.tick(now) }
    }

    private fun enterLoadingState() {
        logger.lzInfo { "PublicActor：进入 loading 状态" }

        state = loading
        context.become(loading)

        // 处理游戏数据
        dbLoad()
    }

    // 进入正式运行状态
    fun enterUpState() {
        logger.lzInfo { "PublicActor：进入 up 状态" }

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
            logger.lzInfo { "PublicActor：进入 handoff 状态" }

            state = handoff

            context.become(handoff)

            // DB执行强制检测
            val now = clock.instant()
            tryCatch(logger) { db.wdb.forceCheckAll(now) }

            // 等待pending结束
            acsFactory.startWaitingPendings(now)
        } catch (e: Throwable) {
            logger.error(e, "Public@init - Handoff handle error: $it")
        }

    }

    private fun enterTerminatedState() {
        logger.lzInfo { "PublicActor：进入 terminated 状态" }

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

}