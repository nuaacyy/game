package com.point18.slg2d.world.area4data

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import java.util.*

class PlayerSession(
    var areaCache: AreaCache,
    var channelActor: ActorRef, // 临时的连接号
    var playerId: Long, // 玩家角色ID
    val player: Player // 玩家
) {

    // 观察信息
    var inviteAlliance: HashMap<Long, Int> = hashMapOf() // 邀请玩家加入联盟的临时缓存用来解决5分钟之内不能拉同一个玩家加入 KEY : 玩家ID

    fun sendMsg(msgNo: MsgType, msg: MessageLite) {
        assert(!(msgNo.msgType < 3000 && areaCache.currentClientMsgNo == 0)) {
            "消息号小于3000的消息,消息序号无效(为0)"
        }

        val scMsg = ScMessageAtSend(msgNo, areaCache.currentClientMsgNo, msg)
        channelActor.tell(scMsg, ActorRef.noSender())
    }

    fun sendMsg(msgNo: MsgType, msgSupply: () -> MessageLite) {
        assert(!(msgNo.msgType < 3000 && areaCache.currentClientMsgNo == 0)) {
            "消息号小于3000的消息,消息序号无效(为0)"
        }

        val msg = msgSupply()
        val scMsg = ScMessageAtSend(msgNo, areaCache.currentClientMsgNo, msg)
        channelActor.tell(scMsg, ActorRef.noSender())
    }
}
