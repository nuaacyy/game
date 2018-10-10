package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.MassGroup
import pb4client.NoticeMassGroupChange

class MassGroupChangeNotifier(
    val msg: NoticeMassGroupChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.MassGroupChange_3143, this.msg.build())
    }
}

fun createMassGroupChangeNotifier(changeType: Int, walkGroup: MassGroup.Builder): MassGroupChangeNotifier {
    val noticeMassGroupChangeBuilder = NoticeMassGroupChange.newBuilder()
    noticeMassGroupChangeBuilder.changeType = changeType
    noticeMassGroupChangeBuilder.setMassGroup(walkGroup)
    return MassGroupChangeNotifier(noticeMassGroupChangeBuilder)
}


