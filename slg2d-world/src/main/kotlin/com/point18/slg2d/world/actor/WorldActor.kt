package com.point18.slg2d.world.actor

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
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.common.constg.AKKA_MAILBOX_SMALL
import com.point18.slg2d.common.msgtrans.InternalMessage
import com.point18.slg2d.common.msgtrans.ProtoWorld
import com.point18.slg2d.common.syncdomain.KryoAskWorldMessage
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.channelTerminatedDeal
import com.point18.slg2d.world.worldMsgDeal
import com.point18.slg2d.world.wpm
import pb4server.*
import scala.concurrent.duration.FiniteDuration
import xyz.ariane.util.*
import java.time.Clock
import java.time.Instant
import java.util.concurrent.TimeUnit

class WorldActor : AbstractActorWithStash() {

    var logger: LoggingAdapter = Logging.getLogger(context.system(), javaClass)

    private val acsLogger: LoggingAdapter = Logging.getLogger(context.system(), "world_acs")

    lateinit var areaCache: AreaCache

    // 对公共服的访问引用
    lateinit var publicShardRegion: ActorRef

    //对home服的访问引用
    lateinit var homeShardRegion: ActorRef

    //对World服的访问引用
    lateinit var worldShardRegion: ActorRef

    // 异步执行的创建工厂
    private lateinit var acsFactory: ActorCompletionStageFactory

    val clock: Clock = Clock.systemDefaultZone()

    var worldId: Long = 0
        private set(wid) {
            field = wid
        }

    private var cancelTick: Cancellable? = null

    var wonder = 0

    // 在创建WorldActor时会创建WorldDatabase，然后会把自己填入到WorldDatabase中。
    val db = WorldDatabase(world = this, fetchDao = { wpm.commonDao })

    private fun baseReceiveBuilder(): ReceiveBuilder {
        return receiveBuilder()
            .match(Runnable::class.java) { dealAcsResp(it) }
    }

    // 行为：初始化
    private val init: Receive = baseReceiveBuilder()
        .match(HandoffTell::class.java) { context.stop(self) }
        .matchAny { msg -> dealInit(msg) }
        .build()

    private val loading = baseReceiveBuilder()
        .match(ProtoWorld::class.java) {
            // TODO 这里应该回应客户端：服务器忙
        }
        .matchAny { stash() }
        .build()

    // 行为：正式运行
    private val up: Receive = baseReceiveBuilder()
        .match(InternalMessage::class.java) { dealKryoAskTellMsg(it) }
        .match(HeartEvent::class.java) { dealHeart() }
        .match(HandoffTell::class.java) { enterHandoffState() }
        .match(MessageLite::class.java) { dealAskTellMsg(it) }
        .match(ProtoWorld::class.java) { dealMsg(it, sender) }
        .match(Terminated::class.java) { handleTerminated(it) }
        .build()

    /** 跨集群迁移/停服 TODO 增加二次wdb检查 */
    private val handoff: Receive = baseReceiveBuilder()
        .match(HeartEvent::class.java) { dealHeartAtHandoff() }
        .build()

    private val terminated: Receive = baseReceiveBuilder()
        .matchAny { println("world@terminated match any $it") }
        .build()

    var state: Receive = init

    companion object {
        fun props(): Props {
            return Props.create(WorldActor::class.java)
        }
    }

    object WorkerName {
        const val blockingRpc = "blockingRpc"
    }

    // 创建一个ACS
    fun <T> createACS(): ACSBase<T> = WorldACS(areaCache, acsFactory.create(), areaCache.currentClientMsgNo)

    private fun handleInitMsgExtractingWorldId(msg: Any): Long = when (msg) {
        is WakeUpWorld -> {
            // 这个消息不需要缓存
            msg.worldId
        }
        is Home2WorldAskReq -> {
            stash()
            msg.worldId
        }
        is Home2WorldTell -> {
            stash()
            msg.worldId
        }
        is Public2WorldTell -> {
            stash()
            msg.worldId
        }
        is World2WorldAskReq -> {
            stash()
            msg.worldId
        }
        is World2WorldTell -> {
            stash()
            msg.worldId
        }
        is ProtoWorld -> {
            // 来自客户端的消息
            msg.worldId
        }
        else -> -1L
    }

    fun passivate() {
        // TODO 这里需要搞清楚为什么要这么写
        logger.lzInfo { "WorldActor：执行 passivate，开始结束Actor" }

        context.parent().tell(ShardRegion.Passivate(HandoffTell.newBuilder().build()), self)
    }

    private fun dealInit(msg: Any) {
        try {
            logger.lzInfo { "触发世界服的初始化 收到初始化消息 ${msg::class.java}" }

            // 从消息中提取世界ID
            val worldId = handleInitMsgExtractingWorldId(msg)
            if (worldId <= 0) {
                // SS：走到这里，说明收到了无效ID
                logger.error("World 初始化失败，无效的 world id: $worldId, msg=${msg::class.java}, sender: $sender")

                passivate()
                return
            }

            logger.lzInfo { "触发世界服的初始化 解析出WorldId：$worldId" }

            // 找到这个区的配置
            val areaConfig = processConfig.findSpecAreaConfig(worldId)
            if (areaConfig == null) {
                logger.error("World 初始化失败，找不到 world id: $worldId 对应的游戏服配置")

                passivate()
                return
            }

            // TODO 判断是否已经被标记为合服，ZK中会保存特定区的信息，如果有合服时间，就认为被合服掉了，也就不应该启动了。

            // 保存AreaCache
            areaCache = AreaCache(areaConfig, this, db)

            // SS：设置worldId
            this.worldId = worldId

            logger.lzInfo { "触发世界服的初始化 尝试加载 $worldId 的数据" }

            // 进入加载阶段
            enterLoadingState()
        } catch (e: Throwable) {
            logger.error(e, "World@init - InitWorld handle error: $msg")
        }
    }

    // 处理消息
    private fun dealMsg(protoWorld: ProtoWorld, channelActor: ActorRef) {
        try {
            // 在 up 阶段 收到客户端的消息 : ${it.msgType}，处理 $sender
            // 判断有没有消息体，默认是不可能没有消息体的
            val msg = protoWorld.msg ?: return

            val msgNo = protoWorld.msgType
            val deal = worldMsgDeal.fetchDeal(msgNo)
            if (deal == null) {
                logger.lzWarning { "找不到消息号为的 $msgNo MsgDeal" }
                return
            }

            logger.lzDebug { "处理消息号:$msgNo" }

            // 存入客户端消息序号，供消息流转用
            areaCache.currentClientMsgNo = protoWorld.clientMsgNo

            // 交给目标方法处理
            deal.dealReq(areaCache, channelActor, msg, protoWorld.playerId)

        } catch (e: Throwable) {
            logger.error(e, "World@up - ProtoWorld handle error: $protoWorld")
        }
    }

    //处理ask和tell消息
    private fun dealAskTellMsg(msg: MessageLite) {
        try {
            when (msg) {
                is Home2WorldAskReq -> {
                    dealHome2WorldAskMsg(msg)
                }
                is World2WorldAskReq -> {
                    dealWorld2WorldAskMsg(msg)
                }
                is Home2WorldTell -> {
                    dealHome2WorldTellMsg(msg)
                }
                is World2WorldTell -> {
                    dealWorld2WorldTellMsg(msg)
                }
                is Public2WorldTell -> {
                    dealPublic2WorldTellMsg(msg)
                }
                else -> {
                    logger.lzWarning { "World服上，dealAskTellMsg 找不到消息类型为的 $msg MsgDeal" }
                }
            }
        } catch (e: Throwable) {
            logger.error(e, "World@@up - MessageLite handle error: $msg")
        }
    }

    //处理Kryo 的 ask和tell消息
    private fun dealKryoAskTellMsg(msg: InternalMessage) {
        try {
            // 处理Kryo消息
            when (msg) {
                is KryoAskWorldMessage -> {
                    dealWorld2WorldKryoAskMsg(msg)
                }
                else -> {
                    logger.lzWarning { "World服上，dealKryoAskTellMsg 找不到消息类型为的 $msg MsgDeal" }
                }
            }
        } catch (e: Throwable) {
            logger.error(e, "World@up - HeartEvent handle error: $msg")
        }
    }

    private fun dealHome2WorldAskMsg(msg: Home2WorldAskReq) {
        val msgCase = msg.msgCase
        val deal = wpm.homeAskMsgDeal[msgCase]
        if (deal == null) {
            logger.lzWarning { "World服上，dealHome2WorldAskMsg 找不到消息类型为的 $msg MsgDeal" }
            return
        }

        val rt = try {
            // 处理请求
            deal.dealAsk(areaCache, msg)

        } catch (e: Exception) {
            logger.lzWarning { "处理Ask请求出现异常:$e" }
            sender.tell(Status.Failure(e), ActorRef.noSender())
            return
        }

        sender.tell(rt, self)
    }

    private fun dealWorld2WorldAskMsg(msg: World2WorldAskReq) {
        val msgCase = msg.msgCase
        val deal = wpm.worldAskMsgDeal[msgCase]
        if (deal == null) {
            logger.lzWarning { "World服上，dealWorld2WorldTellMsg 找不到消息类型为的 $msg MsgDeal" }
            return
        }
        val rt = try {
            // 处理请求
            deal.dealAsk(areaCache, msg)
        } catch (e: Exception) {
            logger.lzWarning { "处理Ask请求出现异常:$e" }
        }

        sender.tell(rt, self)
    }

    private fun dealWorld2WorldKryoAskMsg(msg: KryoAskWorldMessage) {
        val deal = wpm.gMsgDeal[msg::class.java]
        if (deal == null) {
            logger.lzWarning { "World服上，dealWorld2WorldKryoAskMsg 找不到消息类型为的 $msg MsgDeal" }
            return
        }
        val rt = try {
            // 处理请求
            deal.dealAsk(areaCache, msg)
        } catch (e: Exception) {
            logger.lzWarning { "处理Ask请求出现异常:$e" }
        }

        sender.tell(rt, self)
    }

    private fun dealWorld2WorldTellMsg(msg: World2WorldTell) {
        val msgCase = msg.msgCase
        val deal = wpm.worldTellMsgDeal[msgCase]
        if (deal == null) {
            logger.lzWarning { "World服上，dealWorld2WorldTellMsg 找不到消息类型为的 $msg MsgDeal" }
            return
        }
        try {
            // 处理请求
            deal.dealTell(areaCache, msg)
        } catch (e: Exception) {
            logger.lzWarning { "处理Ask请求出现异常:$e" }
        }
    }

    private fun dealHome2WorldTellMsg(msg: Home2WorldTell) {
        val msgCase = msg.msgCase
        val deal = wpm.homeTellMsgDeal[msgCase]
        if (deal == null) {
            logger.lzWarning { "World服上，dealHome2WorldTellMsg 找不到消息类型为的 $msg MsgDeal" }
            return
        }
        try {
            // 处理请求
            deal.dealTell(areaCache, msg)
        } catch (e: Exception) {
            logger.lzWarning { "处理Ask请求出现异常:$e" }
        }
    }

    private fun dealPublic2WorldTellMsg(msg: Public2WorldTell) {
        val msgCase = msg.msgCase
        val deal = wpm.publicTellMsgDeal[msgCase]
        if (deal == null) {
            logger.lzWarning { "World服上，dealPublic2WorldTellMsg 找不到消息类型为的 $msg MsgDeal" }
            return
        }
        try {
            // 处理请求
            deal.dealTell(areaCache, msg)
        } catch (e: Exception) {
            logger.lzWarning { "处理Ask请求出现异常:$e" }
        }
    }

    // 处理ACS的返回
    private fun dealAcsResp(runnable: Runnable) {
        try {
            runnable.run()
        } catch (e: Throwable) {
            logger.error(e, "World - Runnable handle error: $runnable")
        }
    }

    // 处理心跳
    private fun dealHeart() {
        try {
            // 处理心跳
            for (heartHandler in wpm.hs.queues) {
                heartHandler.handleHeart(areaCache)
            }

            val now = clock.instant()
            dbTick(now)

        } catch (e: Throwable) {
            logger.error(e, "World@up - HeartEvent handle error")
        }
    }

    // 处理心跳
    private fun dealHeartAtHandoff() {
        try {
            // 处理定时任务
            logger.lzInfo { "handoff - HeartEvent" }

            val now = clock.instant()
            dbTick(now)
            when {
                !db.isAllClean() -> {
                    logger.lzInfo {
                        "World $worldId waiting for pending db op,syncOp:${db.wdb.countPendingSyncOp()}"
                    }
                }
                acsFactory.hasPendingStages() -> {
                    if (acsFactory.checkWaitingPendingTimeout(now)) {
                        logger.lzWarning { "World $worldId, waiting acs timeout, left num: ${acsFactory.countPendingStages()}" }
                        enterTerminatedState()
                    }
                }
                else -> {
                    logger.lzInfo { "World prepare to onEnter terminated state" }
                    enterTerminatedState()
                }
            }

        } catch (e: Throwable) {
            logger.error(e, "World@handoff - HeartEvent handle error")
        }
    }

    private fun dbTick(now: Instant) {
        tryCatch(logger) { db.tick(now) }
    }

    private fun enterLoadingState() {
        logger.lzInfo { "WorldActor：进入 loading 状态" }

        state = loading
        context.become(loading)

        val start = getNowTime()

        // 处理游戏数据
        db.dbLoad(areaCache, start)
    }

    // 进入正式运行状态
    fun enterUpState() {
        logger.lzInfo { "WorldActor：进入 up 状态" }

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

    private fun enterHandoffState() {
        try {
            logger.lzInfo { "up - HandoffTell" }

            logger.lzInfo { "WorldActor：进入 handoff 状态" }

            state = handoff

            context.become(handoff)

            // DB执行强制检测
            val now = clock.instant()
            tryCatch(logger) { db.wdb.forceCheckAll(now) }

            // 等待pending结束
            acsFactory.startWaitingPendings(now)
        } catch (e: Throwable) {
            logger.error(e, "World@up - Handoff handle error")
        }
    }

    private fun enterTerminatedState() {
        logger.lzInfo { "WorldActor：进入 terminated 状态" }

        state = terminated

        context.become(terminated)

        tryCatch(logger) { db.wdb.stop() }
        tryCatch(logger) { cancelTick?.cancel() }

        context.stop(self)
    }

    private fun handleTerminated(msg: Terminated) {
        try {
            channelTerminatedDeal.dealTerminated(areaCache, msg.actor)
        } catch (e: Throwable) {
            logger.error(e, "World@up - Terminated handle error: $msg")
        }
    }

    override fun preStart() {
        logger.lzInfo { "worldActor Start" }

        // 获取公共服的 shard
        publicShardRegion = ClusterSharding.get(context.system).shardRegion(GameWorldShard.public.name)

        // 获取home服的 shard
        homeShardRegion = ClusterSharding.get(context.system).shardRegion(GameWorldShard.player.name)

        // 获取world服的 shard
        worldShardRegion = ClusterSharding.get(context.system).shardRegion(GameWorldShard.world.name)

        // SS：DB IO用的Worker Actor
        val dbIoWorker: ActorRef =
            context.actorOf(Worker.props("worldIoWorker", "akka.actor.w-db-io", AKKA_MAILBOX_SMALL))

        // SS：阻塞RPC用的Worker Actor
        val blockingRpcWorker: ActorRef =
            context.actorOf(Worker.props("worldIoWorker", "akka.actor.w-blocking-rpc", AKKA_MAILBOX_SMALL))

        // SS：计算用的Worker Actor
        val computationWorker: ActorRef =
            context.actorOf(Worker.props("worldComputationWorker", "akka.actor.w-compute", AKKA_MAILBOX_SMALL))

        // SS：ACS异步操作工厂
        acsFactory = ActorCompletionStageFactory(
            mainActor = self,
            defaultIoWorker = dbIoWorker,
            defaultComputationWorker = computationWorker,
            namedWorkers = mapOf(WorkerName.blockingRpc to blockingRpcWorker),
            logger = acsLogger,
            waitingPendingTimeoutSeconds = 300L
        )
    }

    override fun postStop() {
        logger.lzInfo { "World Actor 停止了----------------------------------------" }
    }

    override fun createReceive(): Receive {
        return state
    }

}