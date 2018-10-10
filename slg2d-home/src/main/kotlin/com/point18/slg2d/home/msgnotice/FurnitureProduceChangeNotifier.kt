package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.FurnitureProduceChange
import pb4client.FurnitureTimeInfo

data class FurnitureProduceChangeNotifier(
    val msg: FurnitureProduceChange.Builder
) {
    fun append(id: Long, startTime: Long, endTime: Long) {
        val infoBuilder = FurnitureTimeInfo.newBuilder()
        infoBuilder.id = id
        infoBuilder.startTime = (startTime/1000).toInt()
        infoBuilder.endTime = (endTime/1000).toInt()
        msg.addFurnitures(infoBuilder)
    }

    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.FurnitureProduceChange_3180, this.msg.build())
    }
}

fun createFurnitureProduceChangeNotifier(): FurnitureProduceChangeNotifier {
    val furnitureProduceChangeBuilder = FurnitureProduceChange.newBuilder()

    return FurnitureProduceChangeNotifier(furnitureProduceChangeBuilder)
}

