package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.KingEquipPlanNum
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.PROP_KING_EQUIP
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_FORBIDDEN
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_EXCEED
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_SHORT
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.SetKingEquipPlan
import pb4client.SetKingEquipPlanRt
import java.util.*

// 设置一个君主装备预设
class SetKingEquipPlanDeal(
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus3<HomePlayerDC, NewEquipDC, KingEquipPlanDC>(
        HomePlayerDC::class.java,
        NewEquipDC::class.java,
        KingEquipPlanDC::class.java,
        Arrays.asList(effectHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, newEquipDC, kingEquipPlanDC ->
            val planId = (msg as SetKingEquipPlan).planId
            val planName = msg.planName
            val p = msg.planList
            val plan = hashMapOf<Long, Int>()

            for (planVo in p) {
                plan[planVo.equipId] = planVo.kingPort
            }

            val rt = setKingEquipPlan(session, planId, planName, plan, homePlayerDC, newEquipDC, kingEquipPlanDC)
            rt.addAllPlan(p)
            session.sendMsg(MsgType.SetKingEquipPlan_1227, rt.build())
        }
    }

    private fun setKingEquipPlan(
        session: PlayerActor,
        planId: Int,
        planName: String,
        plan: HashMap<Long, Int>,
        homePlayerDC: HomePlayerDC,
        newEquipDC: NewEquipDC,
        kingEquipPlanDC: KingEquipPlanDC
    ): (SetKingEquipPlanRt.Builder) {
        val rt = SetKingEquipPlanRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.planId = planId
        rt.planName = planName

        val playerId = session.playerId
        val player = homePlayerDC.player

        // 检测客户端发来的基本数据
        if (planId > effectHelper.getResearchEffectValue(session, NO_FILTER, KingEquipPlanNum)) {
            rt.rt = ResultCode.KING_EQUIP_PLAN_NUM_ERROR.code
            return rt
        }

        if (planName.isNotEmpty()) {
            val res1 = pcs.wordCache.check(
                planName,
                pcs.basicProtoCache.jjcPlanNameLength,
                com.point18.slg2d.common.pc.WORD_CHECK_NAME
            )
            when (res1.wordCheckResult) {
                WORD_CHECK_RESULT_FORBIDDEN -> {
                    rt.rt = ResultCode.KING_EQUIP_PLAN_NAME_ERROR.code
                    return rt
                }

                WORD_CHECK_RESULT_LENGTH_SHORT -> {
                    rt.rt = ResultCode.KING_EQUIP_PLAN_NAME_NOT_ENOUGH.code
                    return rt
                }
                WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                    rt.rt = ResultCode.KING_EQUIP_PLAN_NAME_EXCEED.code
                    return rt
                }
            }
        } else {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        // 检测预设内容是否正确
        val protoMap = hashMapOf<Int, Int>()
        for ((kingEquipId, port) in plan) {
            val ex = protoMap[port]
            if (ex != null) {
                rt.rt = ResultCode.KING_EQUIP_ON_POS_ERROR.code
                return rt
            }
            val itemVo = newEquipDC.findPropById(kingEquipId)
            if (itemVo == null) {
                rt.rt = ResultCode.NO_EQUIP_ERROR.code
                return rt
            }

            val propProto = pcs.equipCache.equipProtoMap[itemVo.equipProtoId]
            if (propProto == null || propProto.mainType != PROP_KING_EQUIP) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt
            }

            if (player.kingLv < propProto.level) {
                rt.rt = ResultCode.KING_EQUIP_SUIT_CONDITION_ERROR.code
                return rt
            }

            val lordEquipPos = pcs.lordEquipmentPositionCache.lordEquipmentPositionMap[propProto.subType]
            if (lordEquipPos == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt
            }

            val canOn = lordEquipPos.positionIdMap[port]
            if (canOn == null) {
                rt.rt = ResultCode.KING_EQUIP_PLAN_MORE_PORT_ERROR.code
                return rt
            }

            // 检测是否传来了多个装备穿一个位置
            protoMap[port] = 1

        }

        // 新建/覆盖预设
        val playerPlan = kingEquipPlanDC.findKingEquipPlanByPlayerIdAndId(planId)
        if (playerPlan == null) {
            // 预设了一个新的
            kingEquipPlanDC.createKingEquipPlan(planId, planName, plan, playerId)
        } else {
            playerPlan.planName = planName
            playerPlan.planMap = plan
        }

        return rt
    }

}