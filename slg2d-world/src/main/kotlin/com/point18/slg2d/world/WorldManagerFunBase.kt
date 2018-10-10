package com.point18.slg2d.world

import akka.actor.Status
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.actor.WorldManagerActor
import pb4server.PublicManager2WorldManagerTell
import pb4server.World2WorldManagerAskReq
import pb4server.World2WorldManagerAskResp
import pb4server.World2WorldManagerTell
import java.io.Serializable

interface WorldManagerAskDealBase {
    fun dealAsk(askMsg: MessageLite): Serializable
}

//abstract class Home2WorldAskDealBase : WorldAskDealBase {
//    override fun dealAsk(areaCache: AreaCache, askMsg: MessageLite): Serializable {
//        if (askMsg !is Home2WorldAskReq) {
//            return Status.Failure(IllegalArgumentException("$askMsg 不是Home2WorldAskReq类型的，接收参数错误"))
//        }
//
//        areaCache.currentClientMsgNo = askMsg.clientMsgNo
//        val resp = Home2WorldAskResp.newBuilder()
//        resp.worldId = askMsg.worldId
//        resp.playerId = askMsg.playerId
//        resp.clientMsgNo = askMsg.clientMsgNo
//
//        // 处理
//        dealHomeAsk(areaCache, askMsg, resp)
//
//        return resp.build()
//    }
//
//    abstract fun dealHomeAsk(
//        areaCache: AreaCache,
//        req: Home2WorldAskReq,
//        resp: Home2WorldAskResp.Builder
//    )
//}

abstract class World2WorldManagerAskDealBase : WorldManagerAskDealBase {
    override fun dealAsk(askMsg: MessageLite): Serializable {
        if (askMsg !is World2WorldManagerAskReq) {
            return Status.Failure(IllegalArgumentException("$askMsg 不是World2WorldManagerAskReq类型的，接收参数错误"))
        }

        val resp = World2WorldManagerAskResp.newBuilder()

        // 处理
        dealWorldAsk(askMsg, resp)

        return resp.build()
    }

    abstract fun dealWorldAsk(
        req: World2WorldManagerAskReq,
        resp: World2WorldManagerAskResp.Builder
    )
}

interface WorldManagerTellDealBase {
    fun dealTell(worldManagerActor: WorldManagerActor, tellMsg: MessageLite)
}

//abstract class Home2WorldTellDealBase : WorldManagerTellDealBase {
//    override fun dealTell(areaCache: AreaCache, tellMsg: MessageLite) {
//        if (tellMsg !is Home2WorldTell) {
//            throw IllegalArgumentException("$tellMsg 不是Home2WorldTell类型的，接收参数错误")
//        }
//
//        areaCache.currentClientMsgNo = tellMsg.clientMsgNo
//
//        // 处理
//        dealHomeTell(areaCache, tellMsg.worldId, tellMsg.playerId, tellMsg)
//    }
//
//    abstract fun dealHomeTell(
//        areaCache: AreaCache,
//        worldId: Long,
//        playerId: Long,
//        msg: Home2WorldTell
//    )
//}

abstract class PublicManager2WorldManagerTellDealBase : WorldManagerTellDealBase {
    override fun dealTell(worldManagerActor: WorldManagerActor, tellMsg: MessageLite) {
        if (tellMsg !is PublicManager2WorldManagerTell) {
            throw IllegalArgumentException("$tellMsg 不是PublicManager2WorldManagerTell类型的，接收参数错误")
        }

        // 处理
        dealPublicManagerTell(tellMsg)
    }

    abstract fun dealPublicManagerTell(
        msg: PublicManager2WorldManagerTell
    )
}

abstract class World2WorldManagerTellDealBase : WorldManagerTellDealBase {
    override fun dealTell(worldManagerActor: WorldManagerActor, tellMsg: MessageLite) {
        if (tellMsg !is World2WorldManagerTell) {
            throw IllegalArgumentException("$tellMsg 不是World2WorldManagerTell类型的，接收参数错误")
        }

        // 处理
        dealWorldTell(worldManagerActor, tellMsg)
    }

    abstract fun dealWorldTell(
        worldManagerActor: WorldManagerActor,
        msg: World2WorldManagerTell
    )
}