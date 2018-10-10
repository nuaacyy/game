package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.BagChange
import pb4client.BagChangeInfo
import pb4client.EquipProp
import pb4client.EquipProps

// 背包中物品变化推送
data class PropsChangeNotifier(
    val msg: pb4client.BagChange.Builder
) {
    fun append(
        changeType: Int,
        changeOnlyId: Long,
        changeProtoId: Int,
        changeNum: Int,
        lv: Int,
        exp: Int,
        equipProps: HashMap<Int, HashMap<Int, Int>>
    ) {
        val bagChangeInfoBuilder = BagChangeInfo.newBuilder()
        bagChangeInfoBuilder.changeType = changeType
        bagChangeInfoBuilder.changeOnlyId = changeOnlyId
        bagChangeInfoBuilder.changeProtoId = changeProtoId
        bagChangeInfoBuilder.changeNum = changeNum
        bagChangeInfoBuilder.equipLv = lv
        bagChangeInfoBuilder.equipExp = exp
        for ((address, pps) in equipProps) {
            val equipPropsBuilder = EquipProps.newBuilder()
            equipPropsBuilder.propAddress = address

            for ((ppType, ppValue) in pps) {
                val equipPropBuilder = EquipProp.newBuilder()
                equipPropBuilder.propType = ppType
                equipPropBuilder.propValue = ppValue
                equipPropsBuilder.addPropValues(equipPropBuilder)
            }
            bagChangeInfoBuilder.addProps(equipPropsBuilder)
        }

        this.msg.addChangeInfo(bagChangeInfoBuilder)
    }

    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.BagChange_3081, this.msg.build())
    }
}

fun createPropsChangeNotifier(): PropsChangeNotifier {
    val bagChangeBuilder = BagChange.newBuilder()
    return PropsChangeNotifier(bagChangeBuilder)
}


