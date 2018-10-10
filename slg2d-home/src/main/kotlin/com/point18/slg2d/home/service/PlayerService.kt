package com.point18.slg2d.home.service

import akka.actor.ActorRef
import akka.util.Timeout
import com.point18.slg2d.home.PlayerDatabase
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.realm.realmModule
import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzInfo
import pb4server.PingAskReq
import pb4server.PingAskRt
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

open class PlayerService {

    /**
     * 基于remote watch的ChannelActor监控有一定概率不正确(可能因为watch和terminate有race condition)
     * 用消息交互再次确认ChannelActor存活
     */
    open fun passivateIfChannelActorDead(player: PlayerActor) {
        with(player) {
            val pingMsg = PingAskReq.newBuilder()
            pingMsg.playerId = player.playerId
            val channel = requireNotNull(channelActor)

            // 异步发送Ping消息来确认远程ChannelActor是否还存活。
            createACS<PingAskRt>().ask(
                    channel,
                    pingMsg.build(),
                    Timeout(30, TimeUnit.SECONDS),
                    PingAskRt::class.java
            )
                    .whenCompleteKt { _, err ->
                        if (err != null) {
                            if (channel == channelActor) {
                                // 进行下面的处理之前，需要确认Channel是否被替换。
                                // 只有还是同一个Channel的情况下才能继续进行。
                                realmModule.dealDisconnect(this)

                                context.system().scheduler().scheduleOnce(
                                        FiniteDuration(2, TimeUnit.SECONDS),
                                        self,
                                        this::passivateIfOffline, // Will send a Function0 to self
                                        context.dispatcher(),
                                        ActorRef.noSender()
                                )

                                logger.lzInfo { "Player $playerId, ChannelActor may be dead, will passivate, e=$err, msg=${err.message}" }

                            } else {
                                logger.lzDebug { "Player $playerId, ChannelActor changed after Ping timeout" }
                            }
                        }
                    }
        }
    }

    open fun createPlayerDatabase(player: PlayerActor): PlayerDatabase = PlayerDatabase(player)
}
