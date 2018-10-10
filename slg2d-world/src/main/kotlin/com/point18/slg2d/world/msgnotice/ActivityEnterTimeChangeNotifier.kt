package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.area4data.PlayerSession
import pb4client.ActivityEnterTimeChange
import pb4client.ActivityEnterTimeInfo

class ActivityEnterTimeChangeNotifier {

    private val msg: ActivityEnterTimeChange.Builder = ActivityEnterTimeChange.newBuilder()

    fun append(timeInfoBuilder: ActivityEnterTimeInfo.Builder?) {
        if (timeInfoBuilder != null) {
            msg.addEnterTimeInfo(timeInfoBuilder)
        }
    }

    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.ActivityEnterTimeChange_3187, this.msg.build())
    }
}
