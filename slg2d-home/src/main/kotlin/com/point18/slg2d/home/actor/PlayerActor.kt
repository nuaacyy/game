package com.point18.slg2d.home.actor

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
import com.point18.slg2d.common.baseg.LongTimeHeartEvent
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.constg.AKKA_MAILBOX_SMALL
import com.point18.slg2d.common.constg.LogoutReason
import com.point18.slg2d.common.msgtrans.ProtoPlayer
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.module.*
import com.point18.slg2d.home.module.askDeal.AskHomeDealBase
import com.point18.slg2d.home.module.askDeal.TellHomeDealBase
import com.point18.slg2d.home.module.realm.realmModule
import com.point18.slg2d.home.playerService
import pb4client.EnterGame
import pb4server.*
import scala.concurrent.duration.Duration
import scala.concurrent.duration.FiniteDuration
import xyz.ariane.util.*
import java.time.Clock
import java.time.Instant
import java.util.concurrent.TimeUnit

const val PLAYER_COMPUTE_DISPATCHER = "akka.actor.p-compute"
const val PLAYER_SHARD_DISPATCHER = "akka.actor.p-shard"
const val PLAYER_DB_WRITE_DISPATCHER = "akka.actor.p-db-w"
const val PLAYER_DB_READ_DISPATCHER = "akka.actor.p-db-r"
const val PLAYER_BLOCKING_RPC_DISPATCHER = "akka.actor.p-blocking-rpc"

class PlayerActor : AbstractActorWithStash() {

    val logger: LoggingAdapter = Logging.getLogger(context.system(), javaClass)

    val msgLogger: LoggingAdapter = Logging.getLogger(context.system(), "player_message")

    private val acsLogger: LoggingAdapter = Logging.getLogger(context.system(), "player_acs")

    val clock: Clock = Clock.systemDefaultZone()

    private var cancelTick: Cancellable? = null

    private var longTimeCancelTick: Cancellable? = null

    /** 玩家角色id **/
    var playerId: Long = 0

    /** 当前世界ID，也是合服后的世界ID **/
    var worldId: Long = 0
        get() {
            if (field == 0L) {
                logger.lzError { "PlayerActor's worldId is invalid! playerId: $playerId" }
                return 0L
            }
            return field
        }

    /** 当前处理的消息No **/
    var clientMsgNo: Int = 0

    /** 指向网关Actor **/
    var channelActor: ActorRef? = null
        private set

    /** 上次登出原因 **/
    var lastLogoutReason: LogoutReason? = null

    /** 用于发起异步请求，不要直接在外部使用这个[acsFactory]创建acs！**/
    private lateinit var acsFactory: ActorCompletionStageFactory

    lateinit var worldShardProxy: ActorRef
        private set

    lateinit var homeShardProxy: ActorRef
        private set

    lateinit var publicShardProxy: ActorRef
        private set

    val db = playerService.createPlayerDatabase(this)

    private fun baseReceiveBuilder(): ReceiveBuilder {
        return receiveBuilder()
            .match(Runnable::class.java) { dealAcsResp(it) }
    }

    private val up: Receive = baseReceiveBuilder()
        .match(ProtoPlayer::class.java) { dealMsgFromGateAtUp(it) }
        .match(HandoffTell::class.java) { enterHandoffState() }
        .match(HeartEvent::class.java) { dealHeart(it) }
        .match(LongTimeHeartEvent::class.java) { dealLongTimeHeart() }
        .match(MessageLite::class.java) { dealAskTellMsg(it) }
        .match(Terminated::class.java) { handleTerminated(it) }
        .build()

    private val init: Receive = baseReceiveBuilder()
        .match(ProtoPlayer::class.java) { dealMsgFromGateAtInit(it) }
        .match(HandoffTell::class.java) { enterHandoffState() }
        .match(HeartEvent::class.java) { dealHeart(it) }
        .match(MessageLite::class.java) { dealAskTellMsg(it) }
        .match(ReceiveTimeout::class.java) { passivateIfOffline() }
        .build()

    /** 跨集群迁移/停服 TODO 增加二次wdb检查 */
    private val handOff: Receive = baseReceiveBuilder()
        .match(HeartEvent::class.java) { dealHeartAtHandOff(it) }
        .build()

    private val terminated: Receive = baseReceiveBuilder().build()

    var state: Receive = init

    // SS：下面定义了一些Worker的名字，其他地方会用到
    object WorkerName {
        @JvmStatic
        val blockingRpc = "blockingRpc"

        @JvmStatic
        val dbRead = "dbRead"
    }

    companion object {
        fun props(): Props {
            return Props.create(PlayerActor::class.java)
        }
    }

    // 处理心跳
    private fun dealHeart(heart: HeartEvent) {
        try {
            val now = clock.instant()
            dbTick(now)
        } catch (e: Throwable) {
            logger.error(e, "Home@$state - HeartEvent handle error: $heart")
        }
    }

    // 处理心跳
    private fun dealHeartAtHandOff(heart: HeartEvent) {
        try {
            // 处理定时任务
            val now = clock.instant()
            dbTick(now)
            when {
                !db.isAllClean() -> {
                    enterTerminatedState()
                    logger.lzInfo { "World force stopped, reason: global flag." }
                }
                acsFactory.hasPendingStages() -> {
                    if (acsFactory.checkWaitingPendingTimeout(now)) {
                        logger.lzWarning { "World $worldId, waiting acs timeout, left num: ${acsFactory.countPendingStages()}" }
                        enterTerminatedState()
                    }
                }
                else -> enterTerminatedState()
            }
        } catch (e: Throwable) {
            logger.error(e, "Home@handOff - HeartEvent handle error: $heart")
        }
    }

    // 处理消息
    private fun dealMsgFromGateAtInit(protoPlayer: ProtoPlayer) {
        try {
            if (protoPlayer.msgType == MsgType.EnterGame_4) {
                this.playerId = protoPlayer.playerId

                // 切换状态
                enterUpState()

            } else {
                logger.error("Home@init - 这个阶段不应该收到 EnterGame_4 以外的消息 ${protoPlayer.msgType}")
                return
            }

            dealMsgFromGate(protoPlayer)

        } catch (e: Throwable) {
            logger.error(e, "Home@init - ProtoPlayer handle error: $protoPlayer")
        }
    }

    private fun dealMsgFromGateAtUp(protoPlayer: ProtoPlayer) {
        try {
            if (protoPlayer.msgType == MsgType.Login_1) {
                normalLog.lzWarn { " 在up阶段收到登录消息" }
                return
            }

            dealMsgFromGate(protoPlayer)

        } catch (e: Throwable) {
            logger.error(e, "Home@up - ProtoPlayer handle error: $protoPlayer")
        }
    }

    /**
     * 处理来自Gate的消息消息
     */
    private fun dealMsgFromGate(protoPlayer: ProtoPlayer) {
        val clientMsgNo = protoPlayer.clientMsgNo

        // 判断有没有消息体，默认是不可能没有消息体的
        val msg = protoPlayer.msg ?: return

        val msgNo = protoPlayer.msgType
        val msgDeals = msgDealsAtHome
        if (msgNo.msgType < 0 || msgNo.msgType >= msgDeals.size) {
            // 消息越界了
            normalLog.error("错误的消息号：$msgNo ，越界了！")
            return
        }

        if (msgNo == MsgType.EnterGame_4) {
            // 进游戏的话绑定channelActor
            bindChannelActor(sender)
        }

        val deal = msgDeals[msgNo.msgType]
        if (deal == null) {
            normalLog.lzWarn { "找不到消息号为的 $msgNo MsgDeal" }
            return
        }

        // 交给目标方法处理
        deal.dealPlayerReq(this, clientMsgNo, msg)
    }

    //处理ask消息
    private fun dealAskTellMsg(msg: MessageLite) {
        try {
            when (msg) {
                is World2HomeAskReq -> {
                    val msgCase = msg.msgCase
                    val deal = worldAskDeal[msgCase]
                    if (deal == null) {
                        normalLog.lzWarn { "Home服上，找不到消息类型为的 $msg MsgDeal" }
                        return
                    }
                    this.dealAskMsg(deal, msg)
                }
                is Home2HomeAskReq -> {
                    val msgCase = msg.msgCase
                    val deal = homeAskDeal[msgCase]
                    if (deal == null) {
                        normalLog.lzWarn { "Home服上，找不到消息类型为的 $msg MsgDeal" }
                        return
                    }
                    this.dealAskMsg(deal, msg)
                }
                is World2HomeTell -> {
                    val msgCase = msg.msgCase
                    val deal = worldTellDeal[msgCase]
                    if (deal == null) {
                        normalLog.lzWarn { "Home服上，找不到消息类型为的 $msg MsgDeal" }
                        return
                    }
                    this.dealTellMsg(deal, msg)
                }
                is Home2HomeTell -> {
                    val msgCase = msg.msgCase
                    val deal = homeTellDeal[msgCase]
                    if (deal == null) {
                        normalLog.lzWarn { "Home服上，找不到消息类型为的 $msg MsgDeal" }
                        return
                    }
                    this.dealTellMsg(deal, msg)
                }
                is Public2HomeTell -> {
                    val msgCase = msg.msgCase
                    val deal = publicTellDeal[msgCase]
                    if (deal == null) {
                        normalLog.lzWarn { "Home服上，找不到消息类型为的 $msg MsgDeal" }
                        return
                    }
                    this.dealTellMsg(deal, msg)
                }
                else -> {
                    normalLog.lzWarn { "Home服上，找不到消息类型为的 $msg MsgDeal" }
                    return
                }
            }
        } catch (e: Throwable) {
            logger.error(e, "Home@$state - MessageLite handle error: $msg")
        }
    }

    private fun dealAskMsg(deal: AskHomeDealBase, msg: MessageLite) {
        deal.dealAsk(this, sender, msg)
    }

    private fun dealTellMsg(deal: TellHomeDealBase, msg: MessageLite) {
        deal.dealTell(this, sender, msg)
    }

    private fun dealLongTimeHeart() {
        try {
            longTimeHeartDealsAtHome.forEach {
                it.dealHeart(this)
            }
        } catch (e: Throwable) {
            logger.error(e, "Home@up - LongTimeHeartEvent handle error")
        }
    }

    /**
     * 已经断线并且长时间未收到消息，钝化
     */
    fun passivateIfOffline() {
        try {
            logger.lzDebug { "Player $playerId, passivateIfOffline()" }
            if (!isOnline()) {
                passivate()
            } else {
                playerService.passivateIfChannelActorDead(this)
            }
        } catch (e: Throwable) {
            logger.error(e, "Home@$state - ReceiveTimeout handle error")
        }
    }

    private fun handleTerminated(msg: Terminated) {
        try {
            if (msg.actor == channelActor) {
                // 处理断线
//                realmModule.handleClientDisconnect(this)

                // 交给目标方法处理
                // 需要注意的是，这里的EnterGame.getDefaultInstance()实际中并不会有任何用处。
                realmModule.dealDisconnect(this)

                // 切换为初始模式
                enterInitState()

                logger.lzDebug { "Player $playerId, channelActor terminated. actor=${msg.actor},addressTerminated=${msg.addressTerminated},existenceConfirmed=${msg.existenceConfirmed}" }

            } else {
                logger.lzWarning { "Unknown Terminated: $msg, actor=${msg.actor}" }
            }
        } catch (e: Throwable) {
            logger.error(e, "Home@up - Terminated handle error: $msg")
        }
    }

    private fun enterInitState() {
        logger.lzDebug { "PlayerActor：$playerId 进入 init 状态" }

        state = init
        context.become(init)
    }

    private fun enterUpState() {
        logger.lzDebug { "PlayerActor：$playerId 进入 up 状态" }

        state = up
        context.become(up)
    }

    private fun enterHandoffState() {
        try {

            logger.lzDebug { "PlayerActor：$playerId 进入 handOff 状态" }

            state = handOff
            context.become(handOff)

            // DB执行强制检测
            val now = clock.instant()
            tryCatch(logger) { db.wdb.forceCheckAll(now) }

            // 等待pending结束
            acsFactory.startWaitingPendings(now)
        } catch (e: Throwable) {
            logger.error(e, "Home@$state - Handoff handle error")
        }
    }

    private fun enterTerminatedState() {
        logger.lzDebug { "PlayerActor：$playerId 进入 terminated 状态" }

        state = terminated
        context.become(terminated)

        // 停止数据库
        db.wdb.stop()

        // 停止自己
        context.stop(self)
    }

    // 处理ACS的返回
    private fun dealAcsResp(runnable: Runnable) {
        try {
            // 处理ACS的runnable返回
            runnable.run()
        } catch (e: Throwable) {
            logger.error(e, "Home - Runnable handle error: $runnable")
        }
    }

    /**
     * 绑定新的[channelActor]
     *
     * @return 如果[channelActor]发生变化返回旧的[channelActor]，否则返回null
     */
    private fun bindChannelActor(newChannel: ActorRef): ActorRef? {
        val oldChannel = channelActor
        return if (newChannel != oldChannel) {
            channelActor = newChannel
            unbindChannel(oldChannel)
            context.watch(newChannel)
            logger.lzDebug { "Player $playerId bind channel $newChannel" }
            oldChannel
        } else {
            null
        }
    }

    private fun unbindChannel(channel: ActorRef?) {
        if (channel != null) {
            context.unwatch(channel)

            // SS：通知网关，channel失效了，让网关关闭
            val channelExpired = ChannelExpiredTell.newBuilder()
            channel.tellNoSender(channelExpired.build(), msgLogger)

            logger.lzDebug { "Player $playerId unbind channel $channel" }
        }
    }

    /**
     * 解绑当前的[channelActor]
     *
     * @return 原来的[channelActor]
     */
    fun unbindChannelActor(): ActorRef? {
        val oldChannel = channelActor
        channelActor = null
        unbindChannel(oldChannel)
        return oldChannel
    }

    // 钝化关闭
    fun passivate() {
        // SS：通知上级Handoff，准备关闭当前Actor
        context.parent().tell(ShardRegion.Passivate(HandoffTell.newBuilder().build()), self)
    }

    private fun dbTick(now: Instant) {
        tryCatch(logger) { db.tick(now) }
    }

    /** 玩家是否在线，即客户端是否已连接了服务器 */
    fun isOnline(): Boolean = channelActor != null

    /**
     * 订阅某一个频道
     */
    fun subscribeChannel(channelName: String) {
        if (this.channelActor == null) {
            println("subscribeChannel$channelName,但是 channelActor == null")
        }

        this.channelActor?.let {
            val subscribeCmd = SubscribeCmd.newBuilder().setChannel(channelName).build()
            val multicastEnvelopeMsg = MulticastEnvelopeMsg.newBuilder()
            it.tellNoSender(
                multicastEnvelopeMsg.setSubscribeCmd(subscribeCmd).build()
            )
        }
    }

    /**
     * 取消订阅某一个频道
     */
    fun unsubscribeChannel(channelName: String) {
        if (this.channelActor == null) {
            println("UnsubscribeChannel $channelName,但是 channelActor == null")
        }

        this.channelActor?.let {
            val unsubscribeCmd = UnsubscribeCmd.newBuilder().setChannel(channelName).build()
            val multicastEnvelopeMsg = MulticastEnvelopeMsg.newBuilder()
            it.tellNoSender(
                multicastEnvelopeMsg.setUnsubscribeCmd(unsubscribeCmd).build()
            )
        }
    }

    fun fillHome2HomeAskMsgHeader(
        wrap: (wrapMsg: Home2HomeAskReq.Builder) -> Unit
    ): Home2HomeAskReq {
        return Home2HomeAskReq.newBuilder().let { it ->
            it.senderId = playerId
            it.clientMsgNo = clientMsgNo

            wrap(it)

            it.build()
        }
    }

    fun fillHome2WorldAskMsgHeader(
        wrap: (wrapMsg: Home2WorldAskReq.Builder) -> Unit
    ): Home2WorldAskReq {
        return Home2WorldAskReq.newBuilder().let {
            it.playerId = playerId
            it.worldId = worldId
            it.clientMsgNo = clientMsgNo

            wrap(it)

            it.build()
        }
    }

    fun fillHome2WorldTellMsgHeader(
        wrap: (wrapMsg: Home2WorldTell.Builder) -> Unit
    ): Home2WorldTell {
        return Home2WorldTell.newBuilder().let {
            it.playerId = playerId
            it.worldId = worldId
            it.clientMsgNo = clientMsgNo

            wrap(it)

            it.build()
        }
    }

    fun fillHome2HomeTellMsgHeader(
        playerId: Long,
        wrap: (wrapMsg: Home2HomeTell.Builder) -> Unit
    ): Home2HomeTell {
        return Home2HomeTell.newBuilder().let {
            it.playerId = playerId
            it.worldId = worldId
            it.clientMsgNo = clientMsgNo

            wrap(it)

            it.build()
        }
    }

    fun fillHome2PublicAskMsgHeader(
        publicId: Long,
        wrap: (wrapMsg: Home2PublicAskReq.Builder) -> Unit
    ): Home2PublicAskReq {
        assert(publicId != 0L)

        return Home2PublicAskReq.newBuilder().let {
            it.playerId = playerId
            it.publicId = publicId
            it.clientMsgNo = clientMsgNo

            wrap(it)

            it.build()
        }

    }

    fun fillHome2PublicManagerAskMsgHeader(
        wrap: (wrapMsg: Home2PublicManagerAskReq.Builder) -> Unit
    ): Home2PublicManagerAskReq {

        return Home2PublicManagerAskReq.newBuilder().let {
            it.playerId = playerId
            it.clientMsgNo = clientMsgNo

            wrap(it)

            it.build()
        }

    }

    fun <T> createACS(): ACSBase<T> {
        return PlayerACS(this, acsFactory.create(), clientMsgNo)
    }

    fun tellWorld(message: Home2WorldTell) {
        if (worldId > 0) {
            worldShardProxy.tell(message, ActorRef.noSender())
        }
    }

    fun tellHome(msg: Home2HomeTell) {
        if (msg.playerId == this.playerId) {
            self.tell(msg, self)
            return
        }
        homeShardProxy.tell(msg, self)
    }

    override fun preStart() {
        // 建立分片代理
        worldShardProxy = ClusterSharding.get(context.system()).shardRegion(GameWorldShard.world.name)

        homeShardProxy = ClusterSharding.get(context.system()).shardRegion(GameWorldShard.player.name)

        publicShardProxy = ClusterSharding.get(context.system()).shardRegion(GameWorldShard.public.name)

        // SS：ACS机制的初始化
        val dbWriteWorker: ActorRef =
            context.actorOf(Worker.props("playerDbWriteWorker", PLAYER_DB_WRITE_DISPATCHER, AKKA_MAILBOX_SMALL))
        val dbReadWorker: ActorRef =
            context.actorOf(Worker.props("playerDbReadWorker", PLAYER_DB_READ_DISPATCHER, AKKA_MAILBOX_SMALL))
        val blockingRpcWorker: ActorRef =
            context.actorOf(Worker.props("playerBlockingRpcWorker", PLAYER_BLOCKING_RPC_DISPATCHER, AKKA_MAILBOX_SMALL))
        val computationWorker: ActorRef =
            context.actorOf(Worker.props("playerComputationWorker", PLAYER_COMPUTE_DISPATCHER, AKKA_MAILBOX_SMALL))
        acsFactory = ActorCompletionStageFactory(
            mainActor = self,
            defaultIoWorker = dbWriteWorker,
            defaultComputationWorker = computationWorker,
            namedWorkers = mapOf(
                WorkerName.blockingRpc to blockingRpcWorker,
                WorkerName.dbRead to dbReadWorker
            ),
            logger = acsLogger
        )

        //设置三分钟未收到任何消息，触发TimeOut
        context.setReceiveTimeout(Duration.apply(3, TimeUnit.MINUTES))

        //启动定时心跳去检测
        val delayDuration = FiniteDuration.apply(1L, TimeUnit.SECONDS)
        val intervalDuration = FiniteDuration.apply(5L, TimeUnit.SECONDS)
        this.cancelTick = context.system().scheduler().schedule(
            delayDuration, intervalDuration, self, HeartEvent(System.currentTimeMillis()),
            context.dispatcher(), ActorRef.noSender()
        )

        val longTimeDelayDuration = FiniteDuration.apply(1L, TimeUnit.MINUTES)
        val longTimeIntervalDuration = FiniteDuration.apply(30L, TimeUnit.MINUTES)
        this.longTimeCancelTick = context.system().scheduler().schedule(
            longTimeDelayDuration, longTimeIntervalDuration, self, LongTimeHeartEvent(System.currentTimeMillis()),
            context.dispatcher(), ActorRef.noSender()
        )
    }

    override fun postStop() {
    }

    override fun createReceive(): Receive {
        return state
    }

    /**
     * 向客户端发送消息
     */
    fun sendMsg(msgNo: MsgType, msg: MessageLite) {
        val channelActor = channelActor
        if (channelActor == null) {
            return
        }

        assert(!(msgNo.msgType < 3000 && clientMsgNo == 0)) {
            "消息号小于3000的消息,消息序号无效(为0)"
        }

        val scMsg = ScMessageAtSend(msgNo, clientMsgNo, msg)
        channelActor.tell(scMsg, ActorRef.noSender())
    }

    /**
     * 向目标Actor发送消息
     */
    fun tellMsg(msgFetcher: () -> MessageLite) {
        val channelActor = channelActor
        if (channelActor == null) {
            return
        }

        val msg = msgFetcher()
        channelActor.tell(msg, ActorRef.noSender())
    }
}