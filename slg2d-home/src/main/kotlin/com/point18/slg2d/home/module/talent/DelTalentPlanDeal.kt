package com.point18.slg2d.home.module.talent

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.TalentPlanDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.DelTalentPlan
import pb4client.DelTalentPlanRt

// 删除一个君主装备预设
class DelTalentPlanDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<TalentPlanDC>(
        TalentPlanDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val planId = (msg as DelTalentPlan).planId
        prepare(session) { talentPlanDC ->
            val rt = delTalentPlan(talentPlanDC, session, planId)
            session.sendMsg(MsgType.DelTalentPlan_1218, rt)
        }
    }
}

fun delTalentPlan(talentPlanDC: TalentPlanDC, session: PlayerActor, planId: Int): DelTalentPlanRt {
    val rt = DelTalentPlanRt.newBuilder()
    rt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code

    val playerId = session.playerId
    val playerPlan = talentPlanDC.findTalentPlanByPlayerIdAndId(playerId, planId)

    if (playerPlan == null) {
        rt.rt = (ResultCode.KING_EQUIP_PLAN_DEL_ERROR.code)
        return rt.build()
    }

    talentPlanDC.deleteTalentPlan(playerPlan)

    //加入日志
    return rt.build()
}
