package com.point18.slg2d.home.module.talent

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.props2GoldCost
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.TalentPlanDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.TalentLvChangeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createTalentPointChangeNotifier
import pb4client.UseTalentPlan
import pb4client.UseTalentPlanRt
import java.util.*

// 套用一个君主天赋预设
class UseTalentPlanDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val refreshResourceHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, TalentPlanDC>(
        HomePlayerDC::class.java,
        TalentPlanDC::class.java,
        Arrays.asList(
            resHelper,
            refreshResourceHelper,
            effectHelper
        )
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val planId = (msg as UseTalentPlan).planId
        prepare(session) { homePlayerDC, talentPlanDC->
            val rt = useTalentPlan(
                session,
                planId,
                homePlayerDC,
                talentPlanDC
            )
            session.sendMsg(MsgType.UseTalentPlan_1220, rt)
        }
    }

    fun useTalentPlan(session: PlayerActor, planId: Int, homePlayerDC: HomePlayerDC, talentPlanDC: TalentPlanDC): UseTalentPlanRt {
        val rt = UseTalentPlanRt.newBuilder()
        rt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code

        val playerId = session.playerId
        val player = homePlayerDC.player
        val playerPlan = talentPlanDC.findTalentPlanByPlayerIdAndId(playerId, planId)

        if (playerPlan == null) {
            rt.rt = ResultCode.KING_EQUIP_PLAN_DEL_ERROR.code
            return rt.build()
        }

        val kingExpProto = pcs.kingExpCache.kingExpMap[player.kingLv]
        if (kingExpProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }
        val allTalentPoint = hashMapOf<Int, Int>()
        allTalentPoint[MilitaryTalent] = kingExpProto.militaryTalent
        allTalentPoint[EconomyTalent] = kingExpProto.economicsTalent
        allTalentPoint[MonsterTalent] = kingExpProto.monsterTalent

        val cost = hashMapOf<Int, Int>()

        for ((talentId, talentLv) in playerPlan.planMap) {
            val talents = pcs.talentCache.talentIdMap[talentId]

            if (talents == null) {
                //天赋配置不存在
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }
            val talentLvEx = talents[talentLv]

            if (talentLvEx == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            //检查前置天赋条件
            val zeroTalentExist = talents[1]
            if (zeroTalentExist == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            if (zeroTalentExist.condition.isNotEmpty()) {
                var meetCondition = false

                for ((condKey, condVal) in zeroTalentExist.condition) {

                    if (condVal <= 0) {
                        continue
                    }
                    val lvExist = playerPlan.planMap[condKey]

                    if (lvExist != null && lvExist >= condVal) {
                        meetCondition = true
                        break
                    }
                }

                if (!meetCondition) {
                    rt.rt = (ResultCode.TALENT_PRECONDITION_DISSATIFY.code)
                    return rt.build()
                }
            }

            //计算所需花费（天赋点）
            for (lv in 1..talentLv) {
                val differTalent = talents[lv]

                if (null == differTalent) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return rt.build()
                }

                for ((costType, costVal) in differTalent.cost) {
                    val tmpCost = cost[costType] ?: 0
                    cost[costType] = costVal + tmpCost
                }
            }
        }

        //检查天赋点数
        for ((costType, costVal) in cost) {
            val currentPoint = allTalentPoint[costType]

            if (currentPoint == null || currentPoint < costVal) {
                rt.rt = (ResultCode.LESS_TALENT_POINT.code)
                return rt.build()
            }
        }

        //检查天赋重置是否需要道具
        val effectValue = effectHelper.getResearchEffectValue(session, NO_FILTER, TalentChangeWithoutCost)
        if (effectValue == 0) {
            // 检测是否拥有道具
            val resetCost = LinkedList<ResVo>()
            resetCost += pcs.basicProtoCache.allTalentResetCost
            if (!resHelper.checkRes(session, resetCost)) {
                // 没有道具，用钻石
                if (resetCost.size > 0) {
                    val (ok, needRes) = props2GoldCost(resetCost[0])

                    if (ok != ResultCode.SUCCESS || needRes.size == 0) {
                        rt.rt = ok.code
                        return rt.build()
                    }

                    //校验需要的资源
                    if (!resHelper.checkRes(session, needRes)) {
                        rt.rt = (ResultCode.LESS_RESOUCE.code)
                        return rt.build()
                    }
                    //扣除资源
                    resHelper.costRes(session, ACTION_RESET_TALENT, player, needRes)
                } else {
                    rt.rt = (ResultCode.LESS_RESOUCE.code)
                    return rt.build()
                }
            } else {
                //扣除天赋重置道具
                resHelper.costRes(session, ACTION_USE_TALENT_PLAN, player, resetCost)
            }
        }


        for ((costType, costVal) in cost) {
            allTalentPoint[costType] = (allTalentPoint[costType] ?: 0) - costVal
        }
        player.putTalentPointMap(allTalentPoint)

        val newTalentMap = hashMapOf<Int, Int>()
        for ((talentId, talentLv) in playerPlan.planMap) {
            player.unlockedTalentMap[talentId] = talentLv
        }

        //重算天赋效果
        for ((id, level) in newTalentMap) {
            val lvTalents = pcs.talentCache.talentIdMap[id]

            if (lvTalents == null) {
                continue
            }

            for ((_, idTalent) in lvTalents) {

                if (idTalent.level != level) {
                    continue
                }

                for ((effKey, effVal) in idTalent.effect) {
                    player.talentEffectInfoMap[effKey] = (player.talentEffectInfoMap[effKey] ?: 0) + effVal
                }
                break
            }
        }

        //发送天赋等级变化事件
        fireEvent(session, TalentLvChangeEvent(refreshResourceHelper, effectHelper))

        //通知天赋点变化
        createTalentPointChangeNotifier(player.talentPointMap).notice(session)

        return rt.build()
    }
}

