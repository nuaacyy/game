package com.point18.slg2d.world.module.instance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.costStrength
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.BeginInstanceFightRt

// 开始推图战斗
class BeginInstanceFight : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        msg as pb4client.BeginInstanceFight
        val rt = beginInstanceFight(session, msg) ?: return
        session.sendMsg(MsgType.BeginInstanceFight_1470, rt.build())
    }

    private fun beginInstanceFight(
        session: PlayerSession,
        reqMsg: pb4client.BeginInstanceFight
    ): (BeginInstanceFightRt.Builder?) {
        val floorId = reqMsg.floorId

        val rtBuilder = BeginInstanceFightRt.newBuilder()
        val checkRt = instanceFightCheck.checkInstanceFightCond(session, floorId)
        rtBuilder.rt = checkRt.rt

        if (rtBuilder.rt != ResultCode.SUCCESS.code) {
            return rtBuilder
        }

        //扣除失败的体力
        checkRt.instanceProto?.let {
            costStrength(session.areaCache, session.player, it.loseStrength)
        }
        return rtBuilder
    }
}