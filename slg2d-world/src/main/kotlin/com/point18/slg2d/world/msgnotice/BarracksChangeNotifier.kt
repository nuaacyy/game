package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.constg.AddSoliderByUseProps
import com.point18.slg2d.common.constg.MakeSolider
import com.point18.slg2d.world.area4data.Barracks
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.BarracksChange
import pb4client.BarracksInfo

class BarracksChangeNotifier(
    val msg: BarracksChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.BarracksChange_3123, this.msg.build())
    }
}

//造兵变化
fun createBarracksChangeNotifier(barracks: Barracks): BarracksChangeNotifier {
    val barracksChangeBuilder = BarracksChange.newBuilder()
    barracksChangeBuilder.changeType = MakeSolider
    barracksChangeBuilder.setBarracks(initInfo(barracks))

    return BarracksChangeNotifier(barracksChangeBuilder)
}

//造兵变化
fun createBarracksChangeNotifierByUseProps(barracks: Barracks): BarracksChangeNotifier {
    val barracksChangeBuilder = BarracksChange.newBuilder()
    barracksChangeBuilder.changeType = AddSoliderByUseProps
    barracksChangeBuilder.setBarracks(initInfo(barracks))

    return BarracksChangeNotifier(barracksChangeBuilder)
}

fun initInfo(barracks: Barracks): BarracksInfo.Builder {
    val barracksInfoBuilder = BarracksInfo.newBuilder()
    barracksInfoBuilder.soldierId = barracks.soldierId
    barracksInfoBuilder.soldierNum = barracks.soldierNum
    barracksInfoBuilder.overTime = getTimeSec(barracks.overTime)
    barracksInfoBuilder.nowMakeNum = barracks.nowMakeNum
    barracksInfoBuilder.canCureNum = barracks.canCureNum
    barracksInfoBuilder.cureOverTime = getTimeSec(barracks.cureOverTime)
    barracksInfoBuilder.nowCureNum = barracks.nowCureNum
    barracksInfoBuilder.cureQueue = barracks.cureQueue
    barracksInfoBuilder.canEventCureNum = barracks.canEventCureNum
    barracksInfoBuilder.eventCureOverTime = getTimeSec(barracks.eventCureOverTime)
    barracksInfoBuilder.nowEventCureNum = barracks.nowEventCureNum
    barracksInfoBuilder.eventCureQueue = barracks.eventCureQueue
    barracksInfoBuilder.upNum = barracks.upNum
    barracksInfoBuilder.upToSoliderId = barracks.upToSoliderId
    barracksInfoBuilder.upOverTime = getTimeSec(barracks.upOverTime)
    barracksInfoBuilder.makeNeedTime = (barracks.needTime / 1000).toInt()
    barracksInfoBuilder.cureNeedTime = (barracks.cureNeedTime / 1000).toInt()
    barracksInfoBuilder.eventCureNeedTime = (barracks.eventCureNeedTime / 1000).toInt()
    barracksInfoBuilder.upNeedTime = (barracks.upNeedTime / 1000).toInt()

    return barracksInfoBuilder
}


