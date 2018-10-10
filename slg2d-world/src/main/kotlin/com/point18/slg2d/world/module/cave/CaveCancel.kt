package com.point18.slg2d.world.module.cave

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.CaveCancelRt

//取消藏兵
class CaveCancelDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = caveCancel(session)
        session.sendMsg(MsgType.CaveCancel_36, rt)
    }

    private fun caveCancel(session: PlayerSession): CaveCancelRt {
        val rt = CaveCancelRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId

        val player = session.player

        val caveWrap = areaCache.caveCache.findCaveByPlayerId(playerId)
        if (caveWrap == null) {
            rt.rt = ResultCode.NO_CAVE_FORCE_GROUP.code
            return rt.build()
        }
        caveOver(areaCache, caveWrap)

        return rt.build()
    }
}