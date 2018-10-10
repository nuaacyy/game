package com.point18.slg2d.world.actor

import akka.actor.AbstractActorWithStash
import akka.actor.ActorRef
import akka.actor.Props
import akka.actor.Status
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.japi.pf.ReceiveBuilder
import com.point18.slg2d.common.baseg.ACSBase
import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.common.constg.AKKA_MAILBOX_SMALL
import com.point18.slg2d.common.msgtrans.ProtoWorld
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.AllianceRankInfoVo
import com.point18.slg2d.world.MoveServerInfoVo
import com.point18.slg2d.world.wpm
import pb4server.*
import scala.concurrent.duration.Duration
import xyz.ariane.util.ActorCompletionStageFactory
import xyz.ariane.util.Worker
import xyz.ariane.util.lzInfo
import xyz.ariane.util.lzWarning
import java.util.concurrent.TimeUnit

data class PreStartChangeState(val value: Int)

// 世界专门用来接收公共管理节点的全局缓存
class WorldManagerActor : AbstractActorWithStash() {

    // 异步执行的创建工厂
    private lateinit var acsFactory: ActorCompletionStageFactory

    private val acsLogger: LoggingAdapter = Logging.getLogger(context.system(), "world_manager_acs")

    var logger: LoggingAdapter = Logging.getLogger(context.system(), javaClass)

    // ***** 起服的时候公共管理服传来的一份世界占领情况的数据 因为当时可能还未获取完配置 所以暂时缓存一次
    var temporaryAreaOcc: List<pb4server.AllianceOccupyInfo> = listOf()

    private fun baseReceiveBuilder(): ReceiveBuilder {
        return receiveBuilder()
            .match(Runnable::class.java) { dealAcsResp(it) }
    }

    // 处理ACS的返回
    private fun dealAcsResp(runnable: Runnable) {
        try {
            runnable.run()
        } catch (e: Throwable) {
            logger.error(e, "WorldManager - Runnable handle error: $runnable")
        }
    }

    // 行为：初始化
    private val init: Receive = baseReceiveBuilder()
        .match(PreStartChangeState::class.java) { msg ->
            dealInit(msg)
            stash()
        }
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
    // 这个行为之下 只会收到来自普通世界服发来的ask+tell与公共管理服节点路由广播来的消息
    private val up: Receive = baseReceiveBuilder()
        .match(World2WorldManagerAskReq::class.java) { dealWorld2WorldManagerAskReq(it) }
        .match(PublicManager2WorldManagerTell::class.java) { dealPublicManager2WorldManagerTell(this, it) }
        .match(World2WorldManagerTell::class.java) { dealWorld2WorldManagerTell(this, it) }
        .build()

    var state: Receive = init

    companion object {
        fun props(): Props {
            return Props.create(WorldManagerActor::class.java)
        }
    }

    // 创建一个ACS
    fun <T> createACS(): ACSBase<T> = WorldManagerACS(acsFactory.create())

    private fun dealInit(msg: Any) {
        try {
            logger.lzInfo { "触发世界中心服的初始化 收到初始化消息 ${msg::class.java}" }
            logger.lzInfo { "正在前往公共服中央节点请求数据缓存起来 ${msg::class.java}" }
            // 前往公共服中央节点请求数据缓存起来
            val findUseAllianceNamesReqMsg = FindUseAllianceNamesReq.newBuilder()
            createACS<WorldManager2PublicManagerAskResp>()
                .ask(
                    wpm.allianceManagerProxy,
                    wpm.fillWorldManager2PublicManagerAskMsgHeader {
                        it.setFindUseAllianceNamesReq(
                            findUseAllianceNamesReqMsg
                        )
                    },
                    WorldManager2PublicManagerAskResp::class.java
                ).whenCompleteKt { askResp, err ->
                    try {
                        when {
                            err != null -> {
                                logger.lzInfo { "获取联盟管理节点失败1:${err}" }
                            }
                            askResp == null -> {
                                logger.lzInfo { "获取联盟管理节点失败2" }
                            }
                            else -> {
                                val rt = askResp.findUseAllianceNamesRt

                                if (rt.rt != ResultCode.SUCCESS.code) {
                                    logger.lzInfo { "获取联盟管理节点失败3:${rt.rt}" }
                                } else {
                                    // 存数据
                                    for (name in rt.allianceNameList) {
                                        wpm.getWorldManagerInfoFromPublicManager().useAllianceName[name] = 1
                                    }

                                    for (shortName in rt.allianceShortNameList) {
                                        wpm.getWorldManagerInfoFromPublicManager().useAllianceShortName[shortName] = 1
                                    }

                                    wpm.getWorldManagerInfoFromPublicManager().syncVersion = rt.nowSycnNameVersion

                                    // 排行数据
                                    val rankInfo = hashMapOf<Int, MutableList<AllianceRankInfoVo>>()
                                    for (ri in rt.allianceRankInfoList) {
                                        for (r in ri.allianceRankInfoList) {
                                            rankInfo.getOrPut(ri.rankType) { mutableListOf() }.add(
                                                AllianceRankInfoVo(
                                                    r.allianceName, r.allianceShortName, r.allianceId,
                                                    r.flagColor, r.flagStyle, r.flagEffect, r.value, r.extend1
                                                )
                                            )
                                        }
                                    }
                                    wpm.getWorldManagerInfoFromPublicManager().allianceRankInfo = rankInfo

                                    // 根据联盟那边传来的占领情况列出一份跨服列表
                                    temporaryAreaOcc = rt.allianceOccupyInfosList
                                    val allAreaConfig = processConfig.findAllAreaConfigByMap()
                                    // 先填充了已经被占领的服务器
                                    val haveMasterArea = mutableMapOf<Long, Int>()
                                    for (r in rt.allianceOccupyInfosList) {
                                        if (r.worldIdList.size == 0) {
                                            continue
                                        }

                                        for (worldId in r.worldIdList) {
                                            val areaConfig = allAreaConfig[worldId]
                                            if (areaConfig == null) {
                                                continue
                                            }
                                            wpm.getWorldManagerInfoFromPublicManager().allServerInfo[worldId] =
                                                    MoveServerInfoVo(
                                                        worldId,
                                                        areaConfig.areaNo,
                                                        areaConfig.areaName, // 服务器名字
                                                        r.allianceMainMemberName,// 服务器国王名
                                                        r.allianceAreaNo,// 王国所属联盟所在服
                                                        r.allianceName,// 王国所属联盟名字
                                                        r.allianceShortName,// 王国所属联盟简称
                                                        (areaConfig.areaPublishTime / 1000).toInt()
                                                    )
                                            haveMasterArea[worldId] = 1
                                        }
                                    }

                                    for ((worldId, areaConfig) in allAreaConfig) {
                                        if (haveMasterArea[worldId] != null) {
                                            // 这个服务器有主人了 信息已经填充过了
                                            continue
                                        }
                                        wpm.getWorldManagerInfoFromPublicManager().allServerInfo[worldId] =
                                                MoveServerInfoVo(
                                                    worldId,
                                                    areaConfig.areaNo,
                                                    areaConfig.areaName, // 服务器名字
                                                    "",// 服务器国王名
                                                    0,// 王国所属联盟所在服
                                                    "",// 王国所属联盟名字
                                                    "",// 王国所属联盟简称
                                                    (areaConfig.areaPublishTime / 1000).toInt()
                                                )

                                    }

                                    logger.lzInfo { "世界公共数据收取完毕 ${msg::class.java}" }

                                }
                            }
                        }
                    } catch (e: Exception) {
                        logger.lzInfo { "获取联盟管理节点失败4:${e}" }
                    }

                }

            logger.lzInfo { "WorldManagerActor：进入 up 状态" }

            state = up
            context.become(up)

            unstashAll() // 弹出所有暂存的消息
        } catch (e: Throwable) {
            logger.error(e, "WorldManager@init - InitWorld handle error: $msg")
        }
    }

    // 处理消息
    private fun dealWorld2WorldManagerAskReq(msg: World2WorldManagerAskReq) {
        val msgCase = msg.msgCase
        val deal = wpm.worldManagerAskMsgDeal[msgCase]
        if (deal == null) {
            logger.lzWarning { "WorldManager服上，dealWorld2WorldManagerAskReq 找不到消息类型为的 ${msg} MsgDeal" }
            return
        }

        val rt = try {
            // 处理请求
            deal.dealAsk(msg)

        } catch (e: Exception) {
            logger.lzWarning { "处理Ask请求出现异常:${e}" }
            sender.tell(Status.Failure(e), ActorRef.noSender())
            return
        }

        sender.tell(rt, self)
    }

    private fun dealPublicManager2WorldManagerTell(
        worldManagerActor: WorldManagerActor,
        msg: PublicManager2WorldManagerTell
    ) {
        val msgCase = msg.msgCase
        val deal = wpm.publicManagerTellMsgDeal[msgCase]
        if (deal == null) {
            logger.lzWarning { "WorldManager服上，publicManagerTellMsgDeal 找不到消息类型为的 ${msg} MsgDeal" }
            return
        }

        // 处理请求
        deal.dealTell(worldManagerActor, msg)
        return
    }

    private fun dealWorld2WorldManagerTell(worldManagerActor: WorldManagerActor, msg: World2WorldManagerTell) {
        val msgCase = msg.msgCase
        val deal = wpm.worldManagerTellMsgDeal[msgCase]
        if (deal == null) {
            logger.lzWarning { "WorldManager服上，worldManagerTellMsgDeal 找不到消息类型为的 ${msg} MsgDeal" }
            return
        }

        // 处理请求
        deal.dealTell(worldManagerActor, msg)
        return
    }

    override fun preStart() {
        logger.lzInfo { "WorldManagerActor Start" }

        // SS：DB IO用的Worker Actor
        val dbIoWorker: ActorRef =
            context.actorOf(Worker.props("worldManagerIoWorker", "akka.actor.w-db-io", AKKA_MAILBOX_SMALL))

        // SS：阻塞RPC用的Worker Actor
        val blockingRpcWorker: ActorRef =
            context.actorOf(Worker.props("worldManagerIoWorker", "akka.actor.w-blocking-rpc", AKKA_MAILBOX_SMALL))

        // SS：计算用的Worker Actor
        val computationWorker: ActorRef =
            context.actorOf(Worker.props("worldManagerComputationWorker", "akka.actor.w-compute", AKKA_MAILBOX_SMALL))

        // SS：ACS异步操作工厂
        acsFactory = ActorCompletionStageFactory(
            mainActor = self,
            defaultIoWorker = dbIoWorker,
            defaultComputationWorker = computationWorker,
            namedWorkers = mapOf(WorldActor.WorkerName.blockingRpc to blockingRpcWorker),
            logger = acsLogger,
            waitingPendingTimeoutSeconds = 300L
        )

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
        logger.lzInfo { "WorldManagerActor 停止了----------------------------------------" }
    }

    override fun createReceive(): Receive {
        return state
    }

}