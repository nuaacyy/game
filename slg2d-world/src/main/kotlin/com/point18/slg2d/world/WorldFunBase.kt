package com.point18.slg2d.world

import akka.actor.Status
import com.point18.slg2d.world.area4data.AreaCache
import com.google.protobuf.MessageLite
import pb4server.*
import com.point18.slg2d.common.syncdomain.KryoAskWorldMessage
import java.io.Serializable

interface WorldAskDealBase {
    fun dealAsk(areaCache: AreaCache, askMsg: MessageLite): Serializable
}

abstract class Home2WorldAskDealBase : WorldAskDealBase {
    override fun dealAsk(areaCache: AreaCache, askMsg: MessageLite): Serializable {
        if (askMsg !is Home2WorldAskReq) {
            return Status.Failure(IllegalArgumentException("$askMsg 不是Home2WorldAskReq类型的，接收参数错误"))
        }

        areaCache.currentClientMsgNo = askMsg.clientMsgNo
        val resp = Home2WorldAskResp.newBuilder()
        resp.worldId = askMsg.worldId
        resp.playerId = askMsg.playerId
        resp.clientMsgNo = askMsg.clientMsgNo

        // 处理
        dealHomeAsk(areaCache, askMsg, resp)

        return resp.build()
    }

    abstract fun dealHomeAsk(
        areaCache: AreaCache,
        req: Home2WorldAskReq,
        resp: Home2WorldAskResp.Builder
    )
}

abstract class World2WorldAskDealBase : WorldAskDealBase {
    override fun dealAsk(areaCache: AreaCache, askMsg: MessageLite): Serializable {
        if (askMsg !is World2WorldAskReq) {
            return Status.Failure(IllegalArgumentException("$askMsg 不是World2WorldAskReq类型的，接收参数错误"))
        }

        areaCache.currentClientMsgNo = askMsg.clientMsgNo
        val resp = World2WorldAskResp.newBuilder()
        resp.worldId = askMsg.worldId
        resp.playerId = askMsg.playerId
        resp.clientMsgNo = askMsg.clientMsgNo

        // 处理
        dealWorldAsk(areaCache, askMsg, resp)

        return resp.build()
    }

    abstract fun dealWorldAsk(
        areaCache: AreaCache,
        req: World2WorldAskReq,
        resp: World2WorldAskResp.Builder
    )
}

interface WorldKryoAskDealBase {
    fun dealAsk(areaCache: AreaCache, kryoAskMsg: KryoAskWorldMessage): Serializable
}

abstract class World2WorldKryoAskDealBase : WorldKryoAskDealBase {
    override fun dealAsk(areaCache: AreaCache, kryoAskMsg: KryoAskWorldMessage): Serializable {
        // 处理
        val resp = dealWorldAsk(areaCache, kryoAskMsg)
        resp.worldId = kryoAskMsg.worldId
        resp.playerId = kryoAskMsg.playerId

        return resp
    }

    abstract fun dealWorldAsk(
        areaCache: AreaCache,
        req: KryoAskWorldMessage
    ): KryoAskWorldMessage
}

interface WorldTellDealBase {
    fun dealTell(areaCache: AreaCache, tellMsg: MessageLite)
}

abstract class Home2WorldTellDealBase : WorldTellDealBase {
    override fun dealTell(areaCache: AreaCache, tellMsg: MessageLite) {
        if (tellMsg !is Home2WorldTell) {
            throw IllegalArgumentException("$tellMsg 不是Home2WorldTell类型的，接收参数错误")
        }

        areaCache.currentClientMsgNo = tellMsg.clientMsgNo

        // 处理
        dealHomeTell(areaCache, tellMsg.worldId, tellMsg.playerId, tellMsg)
    }

    abstract fun dealHomeTell(
        areaCache: AreaCache,
        worldId: Long,
        playerId: Long,
        msg: Home2WorldTell
    )
}

abstract class Public2WorldTellDealBase : WorldTellDealBase {
    override fun dealTell(areaCache: AreaCache, tellMsg: MessageLite) {
        if (tellMsg !is Public2WorldTell) {
            throw IllegalArgumentException("$tellMsg 不是Public2WorldTell类型的，接收参数错误")
        }

        areaCache.currentClientMsgNo = tellMsg.clientMsgNo

        // 处理
        dealPublicTell(areaCache, tellMsg.worldId, tellMsg.playerId, tellMsg)
    }

    abstract fun dealPublicTell(
        areaCache: AreaCache,
        worldId: Long,
        playerId: Long,
        msg: Public2WorldTell
    )
}

abstract class World2WorldTellDealBase : WorldTellDealBase {
    override fun dealTell(areaCache: AreaCache, tellMsg: MessageLite) {
        if (tellMsg !is World2WorldTell) {
            throw IllegalArgumentException("$tellMsg 不是World2WorldTell类型的，接收参数错误")
        }

        areaCache.currentClientMsgNo = tellMsg.clientMsgNo

        // 处理
        dealWorldTell(areaCache, tellMsg.worldId, tellMsg.playerId, tellMsg)
    }

    abstract fun dealWorldTell(
        areaCache: AreaCache,
        worldId: Long,
        playerId: Long,
        msg: World2WorldTell
    )
}