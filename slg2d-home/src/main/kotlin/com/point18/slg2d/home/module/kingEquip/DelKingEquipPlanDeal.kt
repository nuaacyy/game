package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.KingEquipPlanDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.DelKingEquipPlan
import pb4client.DelKingEquipPlanRt

// 删除一个君主装备预设
class DelKingEquipPlanDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<KingEquipPlanDC>(
        KingEquipPlanDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { kingEquipPlanDC ->
            val planId = (msg as DelKingEquipPlan).planId
            val rt = delKingEquipPlan(session, planId, kingEquipPlanDC)
            session.sendMsg(MsgType.DelKingEquipPlan_1228, rt)
        }
    }

    fun delKingEquipPlan(session: PlayerActor, planId: Int, kingEquipPlan: KingEquipPlanDC): (DelKingEquipPlanRt) {
        val rt = DelKingEquipPlanRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val playerId = session.playerId

        val playerPlan = kingEquipPlan.findKingEquipPlanByPlayerIdAndId(planId)
        if (playerPlan == null) {
            rt.rt = ResultCode.KING_EQUIP_PLAN_DEL_ERROR.code
            return rt.build()
        }

        kingEquipPlan.deleteKingEquipPlan(playerPlan)

        return rt.build()
    }

}