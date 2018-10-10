package com.point18.slg2d.gate.net4g

import akka.actor.*
import akka.cluster.sharding.ClusterSharding
import akka.japi.pf.ReceiveBuilder
import com.point18.slg2d.common.baseg.GameWorldShard
import com.point18.slg2d.common.commonfunc.abandonLog
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.msgtrans.ProtoPlayer
import com.point18.slg2d.common.msgtrans.ProtoWorld
import com.point18.slg2d.common.msgtrans.ScMessageAtReceive
import com.point18.slg2d.common.netmsg.C2SMsg
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import io.netty.channel.ChannelHandlerContext
import pb4client.*
import pb4server.ChannelExpiredTell
import pb4server.MulticastEnvelopeMsg
import pb4server.PingAskReq
import pb4server.PingAskRt
import scala.concurrent.duration.Duration
import scala.concurrent.duration.FiniteDuration
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.concurrent.TimeUnit

data class LoginTimeoutTick(val value: Int)

class ChannelActor(
    private val ctx: ChannelHandlerContext
) : AbstractActorWithTimers() {

    var currentState: Receive private set // 当前状态

    lateinit var worldShardRegion: ActorRef
    lateinit var homeShardRegion: ActorRef

    private val cacheMsg4Enter = LinkedList<C2SMsg>()
    private val cacheMsg4Watch = LinkedList<C2SMsg>()

    private var cancelCheckAuth: Cancellable? = null

    private var isBusy: Boolean = false // 当前玩家网关是否繁忙,繁忙状态拒接一些处理 , 如玩家正在迁服中 ..  todo 还没有使用

    // 下面的两个ID都是应该在EnterGame消息时才会被设置。
    var worldId: Long = 0
        private set(wid) {
            field = wid
            watchWorldId = wid
        }
    var playerId: Long = 0
    var watchWorldId: Long = 0  //观察世界的Id

    // 广播中过滤黑名单playerId
    var blackList: HashMap<Long, Int> = hashMapOf()

    // 可切换的几个状态
    private val beforeLogin: Receive = baseReceiveBuilder()
        .match(C2SMsg::class.java) { receiveC2SMsgWhenBeforeLogin(it) }
        .match(ScMessageAtReceive::class.java) { receiveS2CMsgWhenBeforeLogin(it) }
        .match(LoginTimeoutTick::class.java) { channelExpired() }
        .matchAny {
            // 其他消息（不可能收到其他消息！），直接断线
            assert(false) { "ChannelActor：beforeLogin状态下，不应该收到其他类型的消息 $it" }
            abandonLog.lzWarn { "ChannelActor：beforeLogin状态下，不应该收到其他类型的消息" }
            channelExpired()
        }
        .build()

    private val enterGame: Receive = baseReceiveBuilder()
        .match(C2SMsg::class.java) { receiveC2SMsgWhenEnterGame(it) }
        .build()

    private val waiting: Receive = baseReceiveBuilder()
        .match(PingAskReq::class.java) { receivePingMsg(it) }
        .match(ScMessageAtReceive::class.java) { receiveS2CMsgWhenWaiting(it) }
        .match(ChannelExpiredTell::class.java) { channelExpired() }
        .match(MulticastEnvelopeMsg::class.java) { receiveBroadcastMsg(it) }
        .match(C2SMsg::class.java) { msg ->
            // 将消息转发给目标区
            assert(false) { "ChannelActor：等待进入游戏期间，不应该收到其他客户端消息 ${msg.msgType}" }
            abandonLog.lzWarn { "ChannelActor：等待进入游戏期间，不应该收到其他客户端消息 ${msg.msgType}" }
        }
        .build()

    private val running: Receive = baseReceiveBuilder()
        .match(PingAskReq::class.java) { receivePingMsg(it) }
        .match(C2SMsg::class.java) { receiveC2SMsgWhenRunning(it) }
        .match(ScMessageAtReceive::class.java) { receiveS2CMsgWhenRunning(it) }
        .match(ChannelExpiredTell::class.java) { channelExpired() }
        .match(MulticastEnvelopeMsg::class.java) { receiveBroadcastMsg(it) }
        .build()

    private val expired: Receive = baseReceiveBuilder()
        .build()

    private fun baseReceiveBuilder(): ReceiveBuilder {
        return receiveBuilder()
    }

    init {
        currentState = beforeLogin
    }

    companion object {
        fun props(ctx: ChannelHandlerContext): Props {
            return Props.create { ChannelActor(ctx) }
        }
    }

    override fun preStart() {
        worldShardRegion = ClusterSharding.get(context.system).shardRegion(GameWorldShard.world.name)
        homeShardRegion = ClusterSharding.get(context.system).shardRegion(GameWorldShard.player.name)

        // 超时断线
        cancelCheckAuth = context.system().scheduler()
            .scheduleOnce(
                Duration.create(10, TimeUnit.SECONDS),
                self,
                LoginTimeoutTick(0),
                context.dispatcher(),
                ActorRef.noSender()
            )
    }

    override fun postStop() {
        // ChannelActor 结束处理

        // 取消登录超时
        cancelAuthCheck()

        // 解除所有订阅
        ProtoMulticastEventBus.unsubscribe(self)

        // 断线
        ctx.disconnect()
    }

    private fun cancelAuthCheck() {
        cancelCheckAuth?.cancel() ?: return
        cancelCheckAuth = null
    }

    // 尝试转发消息到后续服务器
    private fun forwardClientRequest(msg: C2SMsg) {
        if (msg.msgBody == null) {
            abandonLog.lzWarn { "ChannelActor：转发的消息${msg.msgType}内容为空" }
        }
        when {
            shouldForwardToHome(msg) -> { // 转发到当前玩家服
                val clientMsg = ProtoPlayer(this.playerId, msg.msgType, msg.clientMsgNo, msg.msgBody)
                homeShardRegion.tell(clientMsg, self)
            }
            shouldForwardToWatchWorld(msg) -> { // 转发到观察中的世界服。
                val clientMsg =
                    ProtoWorld(this.playerId, this.watchWorldId, msg.msgType, msg.clientMsgNo, msg.msgBody)
                worldShardRegion.tell(clientMsg, self)
            }
            else -> { // 转发到当前世界服
                val clientMsg =
                    ProtoWorld(this.playerId, this.worldId, msg.msgType, msg.clientMsgNo, msg.msgBody)
                worldShardRegion.tell(clientMsg, self)
            }
        }
    }

    // 判断消息是发送到世界服还是玩家服
    private fun shouldForwardToHome(msg: C2SMsg): Boolean {
        if (msg.msgType == MsgType.RequireModuleData_2500) {
            for (range in INIT_HOME_DATA_MSG_NO_RANGE) {
                if ((msg.msgBody as RequireModuleData).moduleId in range) {
                    return true
                }
            }
        }

        return FORWARD_TO_HOME_MSG_SET.contains(msg.msgType)
    }

    // 判断消息发送到本服还是观察服
    private fun shouldForwardToWatchWorld(msg: C2SMsg): Boolean = FORWARD_TO_WATCH_WORLD_MSG_SET.contains(msg.msgType)

    /**
     * 这个方法需要考虑下是否要向客户端推送通道失效的消息。
     */
    private fun channelExpired() {
        // 5秒后Actor关闭
        val delay = FiniteDuration(5, TimeUnit.SECONDS)
        context.system.scheduler()
            .scheduleOnce(delay, self, PoisonPill.getInstance(), context.dispatcher(), ActorRef.noSender())

        // 切换为失效状态 channelActor 切换到 expired
        chgState(expired)
    }

    private fun chgState(nextState: Receive) {
        currentState = nextState
        context.become(nextState)
    }

    override fun createReceive(): Receive {
        return beforeLogin
    }

    /**
     * 登录前收到客户端发给服务器的CS消息
     */
    private fun receiveC2SMsgWhenBeforeLogin(msg: C2SMsg) {
        // - beforeLogin - 首次收到客户端消息

        // 这里不应该收到登录以外的消息。
        if (msg.msgType != MsgType.Login_1) {
            assert(false) { "ChannelActor：登录完成前，不应该收到登录以外的消息：${msg.msgType}" }
            abandonLog.lzWarn { "ChannelActor：登录完成前，不应该收到登录以外的消息：${msg.msgType}" }
            channelExpired()

            return
        }

        // - 进入游戏的步骤。将消息转发给目标区 beforeLogin - 发送角色创建到Home服
        val loginMsg = msg.msgBody as Login
        val worldId = loginMsg.sid.toLongOrNull()
        if (worldId == null) {
            assert(false) { "ChannelActor：beforeLogin状态下，收到登录消息,客户端传来的SID有错误：${loginMsg.sid}" }
            abandonLog.lzWarn { "ChannelActor：beforeLogin状态下，收到登录消息,客户端传来的SID有错误：${loginMsg.sid}" }
            channelExpired()

            return
        }

        // 保存这个玩家的worldId
        this.worldId = worldId

        // 转发消息给后续的服务器
        forwardClientRequest(msg)

    }

    /**
     * 登录前收到服务器返回给客户端的SC消息
     */
    private fun receiveS2CMsgWhenBeforeLogin(msg: ScMessageAtReceive) {
        // - beforeLogin - 收到Home服的创建返回。。

        // 要发送给客户端的消息
        if (msg.msgType != MsgType.Login_1) {
            // 错误消息，断线
            assert(false) { "ChannelActor：beforeLogin状态下，不应该出现的发送消息号：${msg.msgType}" }
            normalLog.error("ChannelActor：beforeLogin状态下，不应该出现的发送消息号：${msg.msgType}")
            channelExpired()
            return
        }

        // 判断是否要切换行为
        val loginRt = LoginRt.newBuilder().mergeFrom(msg.msgBin).build()
        if (ResultCode.fromValue(loginRt.rt) == ResultCode.SUCCESS) {
            chgState(enterGame)

            // 推送给客户端
            val clientMsg = C2SMsg(msg.msgType, msg.clientMsgNo, msg.msgBin)
            ctx.writeAndFlush(clientMsg)

        } else {
            abandonLog.lzWarn { "ChannelActor：beforeLogin状态下，login失败，状态值：${loginRt.rt}" }

            // 推送给客户端
            val clientMsg = C2SMsg(msg.msgType, msg.clientMsgNo, msg.msgBin)
            ctx.writeAndFlush(clientMsg)

            // 断线！
            channelExpired()
        }
    }

    /**
     * 登录时收到客户端发给服务器的CS消息
     */
    private fun receiveC2SMsgWhenEnterGame(msg: C2SMsg) {
        if (msg.msgType != MsgType.EnterGame_4) {
            assert(false) { "ChannelActor：进入游戏完成前，不应该收到进入游戏以外的消息：${msg.msgType}" }
            abandonLog.lzWarn { "ChannelActor：进入游戏完成前，不应该收到进入游戏以外的消息：${msg.msgType}" }

            return
        }

        // 取消登录超时
        cancelAuthCheck()

        // 将消息转发给目标区
        val enterMsg = msg.msgBody as EnterGame
        this.playerId = enterMsg.playerId
        forwardClientRequest(msg)

        // 切换到等待 channelActor 切换到 waiting
        chgState(waiting)
    }

    /**
     * 登录时等待消息返回时收到的服务器返回给客户端的SC消息
     */
    private fun receiveS2CMsgWhenWaiting(msg: ScMessageAtReceive) {
        // - 要发送给客户端的消息  waiting - 收到Home或World服发来的返回

        // 下面是简单的消息排队机制。
        // 客户端那边，一个玩家的登录完成的判定依据是4号和3200号消息是否都收到了。
        if (msg.msgType == MsgType.EnterGameHomeRt_3200) {
            // - Home服返回的进入游戏响应消息的处理

            val enterHomeRt = EnterGameHomeRt.newBuilder().mergeFrom(msg.msgBin).build()
            this.worldId = enterHomeRt.worldId

            // 消息压入等待队列
            cacheMsg4Enter.add(C2SMsg(msg.msgType, msg.clientMsgNo, msg.msgBin))

            // 转发进入游戏消息至World服
            val worldId = this.worldId
            val worldEnterGameReq = EnterGame.newBuilder()
            worldEnterGameReq.playerId = this.playerId
            val clientMsg =
                ProtoWorld(this.playerId, worldId, MsgType.EnterGame_4, msg.clientMsgNo, worldEnterGameReq.build())
            worldShardRegion.tell(clientMsg, self)

        } else if (msg.msgType == MsgType.EnterGame_4) {
            // - World服返回的进入游戏响应消息的处理

            // 推送给客户端
            ctx.writeAndFlush(C2SMsg(msg.msgType, msg.clientMsgNo, msg.msgBin))

            // 将积压的消息一并推给客户端
            cacheMsg4Enter.forEach {
                ctx.writeAndFlush(it)
            }
            cacheMsg4Enter.clear()

            // 切换行为 channelActor 切换到 running
            chgState(running)

        } else {
            // 其他消息，一律压入等待队列
            cacheMsg4Enter.add(C2SMsg(msg.msgType, msg.clientMsgNo, msg.msgBin))
            return
        }
    }

    /**
     * 登录后收到客户端发给服务器的CS消息
     */
    private fun receiveC2SMsgWhenRunning(msg: C2SMsg) {
        if (msg.msgType == MsgType.ChangeWorldWatch_108) {
            // 切换视野消息单独处理
            if (cacheMsg4Watch.isNotEmpty()) {
                cacheMsg4Watch.add(msg)
                return
            }
        }

        // 将消息转发给目标区
        forwardClientRequest(msg)
    }

    /**
     * 登录前收到服务器返回给客户端的SC消息
     */
    private fun receiveS2CMsgWhenRunning(msg: ScMessageAtReceive) {
        if (msg.msgType == MsgType.ChangeWorldWatch_108) {
            // 收到清理视野返回
            val changeWorldWatchRt = ChangeWorldWatchRt.newBuilder().mergeFrom(msg.msgBin).build()
            val rt = ResultCode.fromValue(changeWorldWatchRt.rt) ?: ResultCode.PARAMETER_ERROR
            if (rt == ResultCode.SUCCESS) {
                // 进行切换世界视野
                watchWorldId = changeWorldWatchRt.serverInfo.serverId
            }
            if (cacheMsg4Watch.isNotEmpty()) {
                val changeWatchMsg = cacheMsg4Watch[0]
                cacheMsg4Watch.removeAt(0)
                forwardClientRequest(changeWatchMsg)
            }
        } else if (msg.msgType == MsgType.MoveServerResult_3193) {
            // 迁服成功的消息 发送一个消息到新world创建新的playerSession
            val rrt = MoveServerResult.newBuilder().mergeFrom(msg.msgBin).build()
            worldId = rrt.serverInfo.serverId

            val initPlayerSession = InitPlayerSessionAfterMoveServer.newBuilder()
            initPlayerSession.playerId = playerId

            forwardClientRequest(
                C2SMsg(
                    MsgType.InitPlayerSessionAfterMoveServer_1301,
                    initPlayerSession.build()
                )
            )

            chgState(enterGame)

        }

        // 要发送给客户端的消息，推送给客户端
        ctx.writeAndFlush(C2SMsg(msg.msgType, msg.clientMsgNo, msg.msgBin))
    }

    /**
     * 接收到广播消息
     */
    private fun receiveBroadcastMsg(msg: MulticastEnvelopeMsg) {
        // 收到广播消息 running 阶段收到MulticastEnvelopeMsg广播消息!!!!!!!!!!!!!!!!!!!!!!!!!!!

        val msgType = MsgType.fromValue(msg.msgType)

        // 收到的广播消息要判断黑名单
        if (msgType != null && msg.hasNewChatMsg()) {
            if (blackList[msg.newChatMsg.chatInfo.playerId] != null) {
                return
            }
            ctx.writeAndFlush(C2SMsg(msgType, msg.newChatMsg))
        } else if (msgType != null && msg.hasGroupChatMsg()) {
            if (blackList[msg.groupChatMsg.message.playerId] != null) {
                return
            }
            ctx.writeAndFlush(C2SMsg(msgType, msg.groupChatMsg))
        } else if (msg.hasAddMulticastBlackList()) {
            blackList[msg.addMulticastBlackList.blackPlayerId] = 1
        } else if (msg.hasDelMulticastBlackList()) {
            blackList.remove(msg.delMulticastBlackList.blackPlayerId)
        } else if (msg.hasSubscribeCmd()) {
            // 收到订阅消息
            ProtoMulticastEventBus.subscribe(self, msg.subscribeCmd.channel)
        } else if (msg.hasUnsubscribeCmd()) {
            // 收到退订消息
            ProtoMulticastEventBus.unsubscribe(self, msg.unsubscribeCmd.channel)
        }
    }

    /**
     * 收到ping消息
     */
    private fun receivePingMsg(msg: PingAskReq) {
        // 连通性测试，在后续服务器测试ChannelActor是否存活时才会用到。
        // 已经进入游戏后，后续服务器才会知晓ChannelActor的存在，才会发送这个消息。
        val rtBuilder = PingAskRt.newBuilder()
        rtBuilder.playerId = msg.playerId
        sender.tellNoSender(rtBuilder.build())
    }
}