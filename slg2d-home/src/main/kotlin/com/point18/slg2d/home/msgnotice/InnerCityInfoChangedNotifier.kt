package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.InnerCityInfo
import pb4client.InnerCityInfoChanged

// 内城建筑信息变化
data class InnerCityInfoChangedNotifier(
    val msg: pb4client.InnerCityInfoChanged.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.InnerCityInfoChanged_3168, this.msg.build())
    }
}

fun createInnerCityInfoChangedNotifier(
    op: Int,
    innerCityType: Int,
    innerCityId: Long,
    startTime: Int,
    completeTime: Int,
    state: Int,
    positionIndex: Int,
    lv: Int,
    helpId: Long
): InnerCityInfoChangedNotifier {
    val innerCityInfoBuilder = InnerCityInfo.newBuilder()
    innerCityInfoBuilder.innerCityType = innerCityType
    innerCityInfoBuilder.innerCityId = innerCityId
    innerCityInfoBuilder.startTime = startTime
    innerCityInfoBuilder.completeTime = completeTime
    innerCityInfoBuilder.state = state
    innerCityInfoBuilder.positionIndex = positionIndex
    innerCityInfoBuilder.lv = lv
    innerCityInfoBuilder.helpId = helpId

    val innerCityInfoChangedBuilder = InnerCityInfoChanged.newBuilder()
    innerCityInfoChangedBuilder.op = op
    innerCityInfoChangedBuilder.setInnerCityInfo(innerCityInfoBuilder)

    return InnerCityInfoChangedNotifier(innerCityInfoChangedBuilder)
}


