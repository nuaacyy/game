package com.point18.slg2d.world.module.moveCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.wpm
import pb4client.AllServerInfoRt
import pb4client.MoveServerInfoVo

// 请求迁服列表
class AllServerInfo : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = allServerInfo(session)

        session.sendMsg(MsgType.AllServerInfo_1302, rt)
    }

    fun allServerInfo(session: PlayerSession): AllServerInfoRt {

        val rt = AllServerInfoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        for ((pltAreaNo, vo) in wpm.getWorldManagerInfoFromPublicManager().allServerInfo) {
            val info = MoveServerInfoVo.newBuilder()
            info.worldId = vo.worldId
            info.areaId = vo.areaId
            info.areaName = vo.areaName
            info.kingName = vo.kingName
            info.allianceAreaId = vo.allianceAreaId
            info.allianceName = vo.allianceName
            info.allianceShortName = vo.allianceShortName
            info.serverOpenTime = vo.serverOpenTime

            val wonder = wpm.getAllWonderInfos()[pltAreaNo]
            if (wonder != null) {
                info.wonderState = wonder.wonderInfo.wonderState
            }

            rt.addMoveServerInfoVos(info.build())
        }

        return rt.build()
    }
}
