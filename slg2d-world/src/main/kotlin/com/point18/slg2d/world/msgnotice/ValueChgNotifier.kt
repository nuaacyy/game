package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.ValueChange
import pb4client.ValuesChange

// 推送武将属性变化
class ValueChgNotifier(
    val msg: ValuesChange.Builder
) {
    fun append(heroId: Long, changeType: Int, value: Long) {
        val valueChangeBuilder = ValueChange.newBuilder()
        valueChangeBuilder.heroId = heroId
        valueChangeBuilder.changeType = changeType
        valueChangeBuilder.nowValue = value
        this.msg.addVc(valueChangeBuilder)
    }

    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.BingliChange_3004, this.msg.build())
    }
}

fun createValueChgNotifier(): ValueChgNotifier {
    val valuesChangeBuilder = ValuesChange.newBuilder()
    return ValueChgNotifier(valuesChangeBuilder)
}


