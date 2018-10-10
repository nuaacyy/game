package com.point18.slg2d.world.msgnotice

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.WalkRobot
import pb4client.WalkRobotShow

// 行军线的新增与删除
class WalkRobotShowNotifier(
    val msg: WalkRobotShow
) {

    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.WalkRobotShow_3140, msg)
    }

    fun notice(areaCache: AreaCache, channelActor: ActorRef) {
        val scMsg = ScMessageAtSend(MsgType.WalkRobotShow_3140, areaCache.currentClientMsgNo, this.msg)
        channelActor.tell(scMsg, ActorRef.noSender())
    }
}

fun createWalkRobotShowNotifier(showType: Int, walkRobot: WalkRobot.Builder): WalkRobotShowNotifier {
    val walkRobotShowBuilder = WalkRobotShow.newBuilder()
    walkRobotShowBuilder.showTye = showType
    walkRobotShowBuilder.setWalkRobots(walkRobot)
    return WalkRobotShowNotifier(walkRobotShowBuilder.build())
}


