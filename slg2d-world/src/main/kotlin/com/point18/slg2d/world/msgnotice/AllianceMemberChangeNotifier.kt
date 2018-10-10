package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceMemberChange
import pb4client.MemberPlayerInfo
import java.util.*

// 玩家联盟聊天信息变化主推
class AllianceMemberChangeNotifier(
    val msg: AllianceMemberChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AllianceMemberChange_3060, this.msg.build())
    }
}

fun createAllianceMemberChangeNotifier(
    flag: Int,
    members: List<MemberPlayerInfo>
): AllianceMemberChangeNotifier {
    val allianceMemberChangeBuilder = AllianceMemberChange.newBuilder()
    allianceMemberChangeBuilder.flag = flag
    members.forEach { allianceMemberChangeBuilder.addMembers(it) }
    return AllianceMemberChangeNotifier(allianceMemberChangeBuilder)

}


