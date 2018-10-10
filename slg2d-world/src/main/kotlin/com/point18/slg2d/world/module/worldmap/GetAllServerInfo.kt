package com.point18.slg2d.world.module.worldmap

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.processConfig
import pb4client.GetAllServerInfoRt
import pb4client.ServerInfo
import com.point18.slg2d.common.resultcode.ResultCode

class GetAllServerInfoDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rtBuilder = GetAllServerInfoRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        val allAreaConfig = processConfig.findAllAreaConfig()
        allAreaConfig.forEach {
            val serverInfo = ServerInfo.newBuilder()
            serverInfo.serverId = it.pltAreaNo
            serverInfo.serverName = it.areaName
            serverInfo.areaNo = it.areaNo
            rtBuilder.addAllServerInfo(serverInfo)
        }

        session.sendMsg(MsgType.GetAllServerInfo_109, rtBuilder.build())
    }
}