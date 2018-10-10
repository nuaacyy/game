package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.InMakeKingEquip
import pb4client.MakeKingEquipChange
import pb4client.MakeKingEquipChangeVo

//玩家锻造君主装备队列变化
data class MakeKingEquipChangeNotifier(
    val msg: pb4client.MakeKingEquipChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.MakeKingEquipChange_3136, this.msg.build())
    }
}

fun createMakeKingEquipChangeNotifier(changeType: Int, inMakeInfo: InMakeKingEquip): MakeKingEquipChangeNotifier {
    val makeKingEquipChangeBuilder = MakeKingEquipChange.newBuilder()
    makeKingEquipChangeBuilder.changeType = changeType
    val makeKingEquipChangeVoBuilder = MakeKingEquipChangeVo.newBuilder()
    makeKingEquipChangeVoBuilder.id = inMakeInfo.id
    makeKingEquipChangeVoBuilder.makeProto = inMakeInfo.kingEquipId
    makeKingEquipChangeVoBuilder.heiyaoshiId = inMakeInfo.heiYaoId
    makeKingEquipChangeVoBuilder.overTime = com.point18.slg2d.common.commonfunc.getTimeSec(inMakeInfo.overTime)
    makeKingEquipChangeVoBuilder.equipId = inMakeInfo.costEquipId
    makeKingEquipChangeBuilder.setMakeKingEquipChangeVo(makeKingEquipChangeVoBuilder)
    return MakeKingEquipChangeNotifier(makeKingEquipChangeBuilder)
}


