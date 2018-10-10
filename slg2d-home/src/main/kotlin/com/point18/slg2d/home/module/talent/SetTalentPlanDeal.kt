package com.point18.slg2d.home.module.talent

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.EconomyTalent
import com.point18.slg2d.common.constg.MilitaryTalent
import com.point18.slg2d.common.constg.MonsterTalent
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.TalentPlanDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.SetTalentPlan
import pb4client.SetTalentPlanRt
import java.util.*

// 设置一个君主天赋预设
class SetTalentPlanDeal(
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, TalentPlanDC>(
        HomePlayerDC::class.java,
        TalentPlanDC::class.java,
        Arrays.asList(effectHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        val planId = (msg as SetTalentPlan).planId
        val planName = msg.planName
        val p = msg.planList
        val planMap = hashMapOf<Int, Int>()

        for (planVo in p) {
            planMap[planVo.talentId] = planVo.talentLevel
        }

        prepare(session) { homePlayerDC, talentPlanDC ->
            val rt = setTalentPlan(session, planId, planName, planMap, homePlayerDC, talentPlanDC)
            rt.addAllPlan(p)
            session.sendMsg(MsgType.SetTalentPlan_1217, rt.build())
        }
    }

    private fun setTalentPlan(
        session: PlayerActor,
        planId: Int,
        planName: String,
        plan: HashMap<Int, Int>,
        homePlayerDC: HomePlayerDC,
        talentPlanDC: TalentPlanDC
    ): SetTalentPlanRt.Builder {
        val rt = SetTalentPlanRt.newBuilder()
        rt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code
        rt.planId = planId
        rt.planName = planName

        val playerId = session.playerId
        val player = homePlayerDC.player
        // 检测客户端发来的基本数据

        if (planId > effectHelper.getResearchEffectValue(session, com.point18.slg2d.common.constg.NO_FILTER, com.point18.slg2d.common.constg.KingTalentNumAdd)) {
            rt.rt = (ResultCode.KING_EQUIP_PLAN_NUM_ERROR.code)
            return rt
        }


        if (planName.isNotEmpty()) {
            val res1 = pcs.wordCache.check(planName, pcs.basicProtoCache.jjcPlanNameLength, com.point18.slg2d.common.pc.WORD_CHECK_NAME)
            when (res1.wordCheckResult) {
                WORD_CHECK_RESULT_FORBIDDEN -> {
                    rt.rt = (ResultCode.KING_EQUIP_PLAN_NAME_ERROR.code)
                    return rt
                }
                WORD_CHECK_RESULT_LENGTH_SHORT -> {

                    rt.rt = ResultCode.KING_EQUIP_PLAN_NAME_NOT_ENOUGH.code
                    return rt
                }
                WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                    rt.rt = (ResultCode.KING_EQUIP_PLAN_NAME_EXCEED.code)
                    return rt
                }
                WORD_CHECK_RESULT_SUCCESS -> {
                    rt.rt = (ResultCode.SUCCESS.code)
                }
                else -> {
                    rt.rt = (ResultCode.PARAMETER_ERROR.code)
                    return rt
                }
            }
        }

        // 检测预设内容是否正确
        val kingExpProto = pcs.kingExpCache.kingExpMap[player.kingLv]
        if (kingExpProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }
        val allTalentPoint = hashMapOf<Int, Int>()
        allTalentPoint[MilitaryTalent] = kingExpProto.militaryTalent
        allTalentPoint[EconomyTalent] = kingExpProto.economicsTalent
        allTalentPoint[MonsterTalent] = kingExpProto.monsterTalent

        val cost = hashMapOf<Int, Int>()

        for ((talentId, talentLv) in plan) {
            val talents = pcs.talentCache.talentIdMap[talentId]

            if (talents == null) {
                //天赋配置不存在
                rt.rt = (ResultCode.NO_PROTO.code)
                return rt
            }
            val talentLvMap = talents[talentLv]

            if (null == talentLvMap) {
                rt.rt = (ResultCode.NO_PROTO.code)
                return rt
            }

            //检查前置天赋条件
            val zeroTalent = talents[1]
            if (zeroTalent == null) {
                rt.rt = (ResultCode.NO_PROTO.code)
                return rt
            }

            if (zeroTalent.condition.isNotEmpty()) {
                var meetCondition = false

                for ((condKey, condVal) in zeroTalent.condition) {

                    if (condVal <= 0) {
                        continue
                    }
                    val tLv = plan[condKey]

                    if (tLv != null && tLv >= condVal) {
                        meetCondition = true
                        break
                    }

                }

                if (!meetCondition) {
                    rt.rt = (ResultCode.TALENT_PRECONDITION_DISSATIFY.code)
                    return rt
                }
            }

            //计算所需花费

            for (lv in 1..talentLv) {
                val differTalent = talents[lv]

                if (null == differTalent) {
                    rt.rt = (ResultCode.NO_PROTO.code)
                    return rt
                }

                for ((costType, costVal) in differTalent.cost) {
                    cost[costType] = costVal
                }
            }
        }

        //检查天赋点数

        for ((costType, costVal) in cost) {
            val currentPoint = allTalentPoint[costType]

            if (null == currentPoint) {
                rt.rt = (ResultCode.LESS_TALENT_POINT.code)
                return rt
            }
            if (currentPoint < costVal) {
                rt.rt = (ResultCode.LESS_TALENT_POINT.code)
                return rt
            }
        }

        // 新建/覆盖预设
        val playerPlan = talentPlanDC.findTalentPlanByPlayerIdAndId(playerId, planId)

        if (playerPlan == null) {
            // 预设了一个新的
            talentPlanDC.createTalentPlan(planId, planName, plan, playerId)
        } else {
            playerPlan.planName = planName
            playerPlan.planMap = plan
        }

        return rt
    }
}


