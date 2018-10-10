package com.point18.slg2d.home.module.talent

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.TalentPlanDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.QueryTalentPlanVo
import pb4client.QueryTalentPlansRt
import pb4client.UnlockedTalent
import java.util.*

// 查询一个君主所有的天赋预设
class QueryTalentPlansDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<TalentPlanDC>(
        TalentPlanDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { talentPlanDC ->
            val rt = queryTalentPlans(talentPlanDC, session)
            session.sendMsg(MsgType.QueryTalentPlans_1219, rt)
        }
    }
}

fun queryTalentPlans(talentPlanDC: TalentPlanDC, session: PlayerActor): QueryTalentPlansRt {
    val rt = QueryTalentPlansRt.newBuilder()
    rt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code

    val playerId = session.playerId

    val playerPlans = talentPlanDC.findTalentPlansByPlayerId(playerId)

    for (p in playerPlans) {
        val planVo = LinkedList<UnlockedTalent>()
        for ((talentId, talentLevel) in p.planMap) {
            val tmpUnlockedT = UnlockedTalent.newBuilder()
            tmpUnlockedT.talentId = talentId
            tmpUnlockedT.talentLevel = talentLevel
            planVo.add(tmpUnlockedT.build())
        }
        val pVo = QueryTalentPlanVo.newBuilder()
        pVo.planId = p.planId
        pVo.planName = p.planName
        pVo.addAllPlan(planVo)
        rt.addQueryTalentPlanVo(pVo)
    }

    return rt.build()
}
