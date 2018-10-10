package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.PlanVo
import pb4client.QueryKingEquipPlanVo
import pb4client.QueryKingEquipPlansRt

// 查询一个君主所有的装备预设
class QueryKingEquipPlansDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<KingEquipPlanDC>(
        KingEquipPlanDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { kingEquipPlanDC ->
            val rt = queryKingEquipPlans(session, kingEquipPlanDC)
            session.sendMsg(MsgType.QueryKingEquipPlans_1229, rt)
        }
    }

    fun queryKingEquipPlans(session: PlayerActor, kingEquipPlan: KingEquipPlanDC): (QueryKingEquipPlansRt) {
        val rt = QueryKingEquipPlansRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val playerId = session.playerId

        val playerPlans = kingEquipPlan.findKingEquipPlansByPlayerId()
        for (p in playerPlans) {
            val pVo = QueryKingEquipPlanVo.newBuilder()
            pVo.planId = p.planId
            pVo.planName = p.planName
            for ((equipId, port) in p.planMap) {
                val pv = PlanVo.newBuilder()
                pv.equipId = equipId
                pv.kingPort = port
                pVo.addPlan(pv)
            }
            rt.addQueryKingEquipPlanVos(pVo)
        }

        return rt.build()
    }

}
