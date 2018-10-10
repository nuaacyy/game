package com.point18.slg2d.public.pmodule

import akka.actor.Status
import com.google.protobuf.MessageLite
import com.point18.slg2d.public.datacache.PublicManagerCache
import pb4server.*
import java.io.Serializable

interface PublicManagerAskDealBase {

    fun dealAsk(publicCache: PublicManagerCache, askMsg: MessageLite): Serializable

}

abstract class WorldManager2PublicManagerAskDealBase : PublicManagerAskDealBase {

    override fun dealAsk(publicCache: PublicManagerCache, askMsg: MessageLite): Serializable {
        if (askMsg !is WorldManager2PublicManagerAskReq) {
            return Status.Failure(IllegalArgumentException("$askMsg 不是WorldManager2PublicManagerAskReq类型的，接收参数错误"))
        }

        val resp = WorldManager2PublicManagerAskResp.newBuilder()

        // 处理
        dealWorldAsk(publicCache, askMsg, resp)

        return resp.build()
    }

    abstract fun dealWorldAsk(
        publicCache: PublicManagerCache,
        req: WorldManager2PublicManagerAskReq,
        resp: WorldManager2PublicManagerAskResp.Builder
    )

}

abstract class World2PublicManagerAskDealBase : PublicManagerAskDealBase {

    override fun dealAsk(publicCache: PublicManagerCache, askMsg: MessageLite): Serializable {
        if (askMsg !is World2PublicManagerAskReq) {
            return Status.Failure(IllegalArgumentException("$askMsg 不是World2PublicManagerAskReq类型的，接收参数错误"))
        }

        publicCache.currentClientMsgNo = askMsg.clientMsgNo
        val resp = World2PublicManagerAskResp.newBuilder()
        resp.worldId = askMsg.worldId
        resp.playerId = askMsg.playerId
        resp.clientMsgNo = askMsg.clientMsgNo

        // 处理
        dealWorldAsk(publicCache, askMsg, resp)

        return resp.build()
    }

    abstract fun dealWorldAsk(
        publicCache: PublicManagerCache,
        req: World2PublicManagerAskReq,
        resp: World2PublicManagerAskResp.Builder
    )

}

abstract class Home2PublicManagerAskDealBase : PublicManagerAskDealBase {

    override fun dealAsk(publicCache: PublicManagerCache, askMsg: MessageLite): Serializable {
        if (askMsg !is Home2PublicManagerAskReq) {
            return Status.Failure(IllegalArgumentException("$askMsg 不是Home2PublicManagerAskReq类型的，接收参数错误"))
        }

        publicCache.currentClientMsgNo = askMsg.clientMsgNo
        val resp = Home2PublicManagerAskResp.newBuilder()
        resp.playerId = askMsg.playerId
        resp.clientMsgNo = askMsg.clientMsgNo

        // 处理
        dealHomeAsk(publicCache, askMsg, resp)

        return resp.build()
    }

    abstract fun dealHomeAsk(
        publicCache: PublicManagerCache,
        req: Home2PublicManagerAskReq,
        resp: Home2PublicManagerAskResp.Builder
    )

}


interface PublicManagerTellDealBase {
    fun dealTell(publicCache: PublicManagerCache, tellMsg: MessageLite)
}


abstract class Public2PublicManagerTellDealBase : PublicManagerTellDealBase {
    override fun dealTell(publicCache: PublicManagerCache, tellMsg: MessageLite) {
        if (tellMsg !is Public2PublicManagerTell) {
            throw IllegalArgumentException("$tellMsg 不是Public2PublicManagerTell类型的，接收参数错误")
        }

        // 处理
        dealPublicTell(publicCache, tellMsg)
    }

    abstract fun dealPublicTell(
        publicCache: PublicManagerCache,
        msg: Public2PublicManagerTell
    )
}