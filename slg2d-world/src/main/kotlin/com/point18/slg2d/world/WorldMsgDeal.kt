package com.point18.slg2d.world

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.area4data.AreaCache

val worldMsgDeal = MsgSystem()

interface BaseDeal {
    fun dealReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long)
}

class MsgSystem {
    // 消息处理方法
    val msgDeals: Array<BaseDeal?> = arrayOfNulls<BaseDeal?>(3000)

    /**
     * 注册消息处理
     */
    fun registerMsgDeal(msgNo: MsgType, deal: BaseDeal) {
        msgDeals[msgNo.msgType] = deal
    }

    fun fetchDeal(msgNo: MsgType): BaseDeal? {
        if (msgNo.msgType < 0 || msgNo.msgType >= msgDeals.size) {
            // 消息越界了
            return null
        }
        val deal = msgDeals[msgNo.msgType]
        if (deal == null) {
            return null
        }

        return deal
    }
}