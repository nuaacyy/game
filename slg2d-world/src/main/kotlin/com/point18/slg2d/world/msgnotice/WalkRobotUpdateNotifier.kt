package com.point18.slg2d.world.msgnotice

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.WalkRobot
import pb4client.WalkRobotUpdate

// 行军小人的位置更新
class WalkRobotUpdateNotifier(
    val msg: WalkRobotUpdate.Builder
) {
    fun append(walkRobot: WalkRobot.Builder) {
        this.msg.addWalkRobots(walkRobot)
    }

    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.WalkRobotUpdate_3139, this.msg.build())
    }

    fun notice(areaCache: AreaCache, channelActor: ActorRef) {
        val scMsg = ScMessageAtSend(MsgType.WalkRobotUpdate_3139, areaCache.currentClientMsgNo, this.msg.build())
        channelActor.tell(scMsg, ActorRef.noSender())
    }
}

fun createWalkRobotUpdateNotifier(): WalkRobotUpdateNotifier {
    val walkRobotUpdateBuilder = WalkRobotUpdate.newBuilder()
    return WalkRobotUpdateNotifier(walkRobotUpdateBuilder)
}

