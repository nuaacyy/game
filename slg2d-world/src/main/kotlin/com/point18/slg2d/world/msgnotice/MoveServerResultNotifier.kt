package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.MoveServerResult
import pb4client.ServerInfo

// 迁服完毕推送
class MoveServerResultNotifier(
    val msg: MoveServerResult.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.MoveServerResult_3193, this.msg.build())
    }
}

fun createMoveServerResultNotifier(
    areaNo: Int,
    worldId: Long,
    areaName: String,
    x: Int,
    y: Int
): MoveServerResultNotifier {
    val moveServerResult = MoveServerResult.newBuilder()

    val serverInfo = ServerInfo.newBuilder()
    serverInfo.areaNo = areaNo
    serverInfo.serverName = areaName
    serverInfo.serverId = worldId
    moveServerResult.serverInfo = serverInfo.build()
    moveServerResult.x = x
    moveServerResult.y = y

    return MoveServerResultNotifier(moveServerResult)
}
