package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.RelationChange

// 关系改变推送
class RelationChangeNotifier(
    val msg: RelationChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.RelationChange_3012, this.msg.build())
    }

}

fun createRelationChangeNotifier(t: Int, pid: Long, aid: Long, name: String, shortName: String): RelationChangeNotifier {
    val relationChangeBuilder = RelationChange.newBuilder()
    relationChangeBuilder.type = t
    relationChangeBuilder.pid = pid
    relationChangeBuilder.aid = aid
    relationChangeBuilder.name = name
    relationChangeBuilder.shortName = shortName
    return RelationChangeNotifier(relationChangeBuilder)
}

