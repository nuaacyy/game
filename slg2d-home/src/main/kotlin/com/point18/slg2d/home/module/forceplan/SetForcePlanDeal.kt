package com.point18.slg2d.home.module.forceplan

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_FORBIDDEN
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_EXCEED
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_SHORT
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.ForcePlanDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ForcePlanVo
import pb4client.SetForcePlan
import pb4client.SetForcePlanRt
import java.util.*
import java.util.Arrays.asList

// 设置玩家的出征预设部队
class SetForcePlanDeal(
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<ForcePlanDC>(ForcePlanDC::class.java, asList(effectHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { forcePlanDC: ForcePlanDC ->
            val planMsg = (msg as SetForcePlan).forcePlanVo
            val rt = this.setForcePlan(session, planMsg, forcePlanDC)

            session.sendMsg(MsgType.SetForcePlan_1265, rt)
        }
    }

    private fun setForcePlan(session: PlayerActor, planMsg: ForcePlanVo, forcePlanDC: ForcePlanDC): SetForcePlanRt {
        val rtBuilder = SetForcePlanRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val planId = planMsg.planId
        val planName = planMsg.planName

        val soliderMap = hashMapOf<Int, Int>()
        for (soliderInfo in planMsg.forcePlanInfo.forcePlanSolidersList) {
            soliderMap[soliderInfo.soliderProtoId] = soliderInfo.soliderNum
        }

        val forcePlanVo = com.point18.slg2d.home.dc.ForcePlanVo(
            LinkedList(planMsg.forcePlanInfo.heroIdList),
            soliderMap
        )

        if (planId > effectHelper.getResearchEffectValue(session, com.point18.slg2d.common.constg.NO_FILTER, com.point18.slg2d.common.constg.ForcePlanNum)) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }

        // 检测名字
        if (planName.isNotEmpty()) {
            val res1 = pcs.wordCache.check(planName, pcs.basicProtoCache.jjcPlanNameLength, com.point18.slg2d.common.pc.WORD_CHECK_NAME)
            when (res1.wordCheckResult) {
                WORD_CHECK_RESULT_FORBIDDEN -> {
                    rtBuilder.rt = ResultCode.KING_EQUIP_PLAN_NAME_ERROR.code
                    return rtBuilder.build()
                }
                WORD_CHECK_RESULT_LENGTH_SHORT -> {
                    rtBuilder.rt = ResultCode.KING_EQUIP_PLAN_NAME_NOT_ENOUGH.code
                    return rtBuilder.build()
                }
                WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                    rtBuilder.rt = ResultCode.KING_EQUIP_PLAN_NAME_EXCEED.code
                    return rtBuilder.build()
                }
            }
        } else {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }

        // 进行赋值
        val plan = forcePlanDC.forcePlanMap[planId]
        if (plan != null) {
            // 存在的 实际是修改方案
            if (planName != plan.planName) {
                plan.planName = planName
            }

            plan.planMap = forcePlanVo
        } else {
            // 方案不存在,新建的
            forcePlanDC.createForcePlan(planId, planName, forcePlanVo)
        }

        return rtBuilder.build()
    }
}


