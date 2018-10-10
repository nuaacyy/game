package com.point18.slg2d.public.pmodule

import akka.actor.Status
import com.google.protobuf.MessageLite
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.public.datacache.PublicManagerCache
import pb4server.*
import java.io.Serializable

interface PublicAskDealBase {

    fun dealAsk(publicCache: PublicCache, askMsg: MessageLite): Serializable

}

abstract class World2PublicAskDealBase : PublicAskDealBase {

    override fun dealAsk(publicCache: PublicCache, askMsg: MessageLite): Serializable {
        if (askMsg !is World2PublicAskReq) {
            return Status.Failure(IllegalArgumentException("$askMsg 不是World2PublicAskReq类型的，接收参数错误"))
        }

        publicCache.currentClientMsgNo = askMsg.clientMsgNo
        val resp = World2PublicAskResp.newBuilder()
        resp.worldId = askMsg.worldId
        resp.playerId = askMsg.playerId
        resp.clientMsgNo = askMsg.clientMsgNo

        // 处理
        dealWorldAsk(publicCache, askMsg, resp)

        return resp.build()
    }

    abstract fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    )

}

abstract class Home2PublicAskDealBase : PublicAskDealBase {

    override fun dealAsk(publicCache: PublicCache, askMsg: MessageLite): Serializable {
        if (askMsg !is Home2PublicAskReq) {
            return Status.Failure(IllegalArgumentException("$askMsg 不是Home2PublicAskReq类型的，接收参数错误"))
        }

        publicCache.currentClientMsgNo = askMsg.clientMsgNo
        val resp = Home2PublicAskResp.newBuilder()
        resp.playerId = askMsg.playerId
        resp.clientMsgNo = askMsg.clientMsgNo

        // 处理
        dealHomeAsk(publicCache, askMsg, resp)

        return resp.build()
    }

    abstract fun dealHomeAsk(
        publicCache: PublicCache,
        req: Home2PublicAskReq,
        resp: Home2PublicAskResp.Builder
    )

}

interface PublicTellDealBase {
    fun dealTell(publicCache: PublicCache, tellMsg: MessageLite)
}


abstract class PublicManager2PublicTellDealBase : PublicTellDealBase {
    override fun dealTell(publicCache: PublicCache, tellMsg: MessageLite) {
        if (tellMsg !is PublicManager2PublicTell) {
            throw IllegalArgumentException("$tellMsg 不是PublicManager2PublicTell类型的，接收参数错误")
        }

        // 处理
        dealPublicTell(publicCache, tellMsg)
    }

    abstract fun dealPublicTell(
        publicCache: PublicCache,
        msg: PublicManager2PublicTell
    )
}