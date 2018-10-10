package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.EventCure
import com.point18.slg2d.common.constg.SoliderStrength
import com.point18.slg2d.common.constg.TrapStrength
import com.point18.slg2d.world.common.isWonderWar
import com.point18.slg2d.world.common.syncBarrack2Home
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import pb4client.DismissSolider
import pb4client.DismissSoliderRt
import com.point18.slg2d.common.resultcode.ResultCode

//遣散士兵
class DismissSoliderDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val dismissMsg = (msg as DismissSolider)
        val rt = dismissSolider(session, dismissMsg)
        session.sendMsg(MsgType.DismissSolider_1261, rt)
    }

    fun dismissSolider(session: PlayerSession, dismissMsg: DismissSolider): (DismissSoliderRt) {
        val rt = DismissSoliderRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId
        val soliderId = dismissMsg.soliderId
        val dismissNum = dismissMsg.soliderNum
        val dismissType = dismissMsg.dismissType
        val eventCure = dismissMsg.eventCure

        val barrack = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, soliderId)
        if (barrack == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }
        if (eventCure == 1 && !isWonderWar(session.areaCache)) { // 非奇迹争夺战没有活动伤兵营
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }
        if (dismissType == 1) {
            //遣散士兵
            if (barrack.soldierNum < dismissNum) {
                rt.rt = ResultCode.DISMISS_SOLIDER_OUT_MAX_ERROR.code
                return rt.build()
            }
            barrack.soldierNum -= dismissNum

            //刷新实力
            targetAddVal(areaCache, barrack.playerId, SoliderStrength)
            targetAddVal(areaCache, barrack.playerId, TrapStrength)
        } else {
            if (eventCure != EventCure) {
                //遣散伤兵
                if (barrack.canCureNum < dismissNum) {
                    rt.rt = ResultCode.DISMISS_SOLIDER_OUT_MAX_ERROR.code
                    return rt.build()
                }
                barrack.canCureNum -= dismissNum
            } else {
                //遣散活动伤兵
                if (barrack.canEventCureNum < dismissNum) {
                    rt.rt = ResultCode.DISMISS_SOLIDER_OUT_MAX_ERROR.code
                    return rt.build()
                }
                barrack.canEventCureNum -= dismissNum
            }
        }

        createBarracksChangeNotifier(barrack).notice(session)

        //同步兵营数据至home
        syncBarrack2Home(areaCache, playerId)

        return rt.build()
    }

}
