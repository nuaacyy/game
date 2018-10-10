package com.point18.slg2d.home.module.askDeal

import akka.actor.ActorRef
import akka.actor.Status
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.HomeHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import pb4server.*
import xyz.ariane.util.lzWarn
import java.util.Arrays.asList

interface AskHomeDealBase {
    fun dealAsk(session: PlayerActor, sender: ActorRef, askMsg: MessageLite)
}

interface W2HAsk {
    fun dealWorldAsk(
        session: PlayerActor,
        req: World2HomeAskReq,
        resp: World2HomeAskResp.Builder
    )
}

class W2HAskDealWrap(private val w2HAsk: W2HAsk) : AskHomeDealBase,
    HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java, asList(w2HAsk as HomeHelper)) {

    override fun dealAsk(session: PlayerActor, sender: ActorRef, askMsg: MessageLite) {
        if (askMsg !is World2HomeAskReq) {
            sender.tell(Status.Failure(IllegalArgumentException("$askMsg 不是World2HomeAskReq类型的，接收参数错误")), session.self)
            return
        }

        // 由于PlayerActor可能是临时创建的，
        // 所以需要将消息中的playerId赋予PlayerActor。
        // 这步很重要！不要误删！
        session.playerId = askMsg.playerId

        if (w2HAsk is SyncHomeDataDeal) {
            // 这个类是要区分对待的！因为内部还有一层！

            val msg = askMsg.syncHomeAskReq

            // 提前准备返回消息
            val resp = World2HomeAskResp.newBuilder()
            resp.worldId = askMsg.worldId
            resp.playerId = askMsg.playerId
            resp.clientMsgNo = askMsg.clientMsgNo

            val deal = w2HAsk.findSpecSyncDeal(msg.dateType)

            if (deal == null) {
                normalLog.lzWarn { "同步到home的数据类型${msg.dateType}未注册" }

                val rt = SyncHomeAskRt.newBuilder()
                rt.rt = ResultCode.PARAMETER_ERROR.code
                resp.setSyncHomeAskRt(rt)

                sender.tell(resp.build(), session.self)
                return
            }

            // 内部的处理，内部是异步的，所以内部负责返回消息
            deal.dealSyncOutSide(sender, session, askMsg, resp)

        } else {

            requireDc(session) {
                session.clientMsgNo = askMsg.clientMsgNo

                val resp = World2HomeAskResp.newBuilder()
                resp.worldId = askMsg.worldId
                resp.playerId = askMsg.playerId
                resp.clientMsgNo = askMsg.clientMsgNo

                w2HAsk.dealWorldAsk(session, askMsg, resp)

                sender.tell(resp.build(), session.self)
            }
        }
    }
}

interface H2HAsk {
    fun dealHomeAsk(
        session: PlayerActor,
        req: Home2HomeAskReq,
        resp: Home2HomeAskResp.Builder
    )
}

class H2HAskDealWrap(private val h2HAsk: H2HAsk) : AskHomeDealBase, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(h2HAsk as HomeHelper)
) {

    override fun dealAsk(session: PlayerActor, sender: ActorRef, askMsg: MessageLite) {
        if (askMsg !is Home2HomeAskReq) {
            sender.tell(Status.Failure(IllegalArgumentException("$askMsg 不是Home2HomeAskReq类型的，接收参数错误")), session.self)
            return
        }

        // 由于PlayerActor可能是临时创建的，
        // 所以需要将消息中的playerId赋予PlayerActor。
        // 这步很重要！不要误删！
        session.playerId = askMsg.playerId

        requireDc(session) {
            session.clientMsgNo = askMsg.clientMsgNo

            val resp = Home2HomeAskResp.newBuilder()
            resp.playerId = askMsg.senderId
            resp.clientMsgNo = askMsg.clientMsgNo

            h2HAsk.dealHomeAsk(session, askMsg, resp)
            sender.tell(resp.build(), session.self)
        }
    }
}

interface TellHomeDealBase {
    fun dealTell(session: PlayerActor, sender: ActorRef, tellMsg: MessageLite)
}

interface H2HTell {
    fun dealHomeTell(
        session: PlayerActor,
        worldId: Long,
        playerId: Long,
        msg: Home2HomeTell
    )
}

class H2HTellDealWrap(private val h2HTell: H2HTell) : TellHomeDealBase, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(h2HTell as HomeHelper)
) {

    override fun dealTell(session: PlayerActor, sender: ActorRef, tellMsg: MessageLite) {
        if (tellMsg !is Home2HomeTell) {
            throw IllegalArgumentException("$tellMsg 不是Home2HomeTell类型的，接收参数错误")
        }

        // 由于PlayerActor可能是临时创建的，
        // 所以需要将消息中的playerId赋予PlayerActor。
        // 这步很重要！不要误删！
        session.playerId = tellMsg.playerId

        requireDc(session) {
            session.clientMsgNo = tellMsg.clientMsgNo

            // 处理
            h2HTell.dealHomeTell(session, tellMsg.worldId, tellMsg.playerId, tellMsg)
        }
    }
}

interface W2HTell {
    fun dealWorldTell(
        session: PlayerActor,
        playerId: Long,
        msg: World2HomeTell
    )
}

class W2HTellDealWrap(private val w2HTell: W2HTell) : TellHomeDealBase, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(w2HTell as HomeHelper)
) {

    override fun dealTell(session: PlayerActor, sender: ActorRef, tellMsg: MessageLite) {
        if (tellMsg !is World2HomeTell) {
            throw IllegalArgumentException("$tellMsg 不是World2HomeTell类型的，接收参数错误")
        }

        // 由于PlayerActor可能是临时创建的，
        // 所以需要将消息中的playerId赋予PlayerActor。
        // 这步很重要！不要误删！
        session.playerId = tellMsg.playerId

        requireDc(session) {
            session.clientMsgNo = tellMsg.clientMsgNo

            // 处理
            w2HTell.dealWorldTell(session, tellMsg.playerId, tellMsg)
        }

    }
}

interface P2HTell {
    fun dealPublicTell(
        session: PlayerActor,
        playerId: Long,
        msg: Public2HomeTell
    )
}

class P2HTellDealWrap(private val p2HTell: P2HTell) : TellHomeDealBase, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(p2HTell as HomeHelper)
) {

    override fun dealTell(session: PlayerActor, sender: ActorRef, tellMsg: MessageLite) {
        if (tellMsg !is Public2HomeTell) {
            throw IllegalArgumentException("$tellMsg 不是Public2HomeTell类型的，接收参数错误")
        }

        // 由于PlayerActor可能是临时创建的，
        // 所以需要将消息中的playerId赋予PlayerActor。
        // 这步很重要！不要误删！
        session.playerId = tellMsg.playerId

        requireDc(session) {
            session.clientMsgNo = tellMsg.clientMsgNo

            // 处理
            p2HTell.dealPublicTell(session, tellMsg.playerId, tellMsg)
        }
    }

}