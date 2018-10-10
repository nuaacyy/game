package com.point18.slg2d.home.module.barracks

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoAddX
import com.point18.slg2d.common.pc.resVoCombine
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.CureSolider
import pb4client.CureSoliderInfo
import pb4client.CureSoliderRt
import pb4server.CureSoliderAskReq
import pb4server.EffectVo
import pb4server.Home2WorldAskResp
import pb4server.SoliderVo
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.Arrays.asList

// 治疗兵
class CureSoliderDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper, effectHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val cureSoliderInfoList = LinkedList((msg as CureSolider).cureSoliderInfoList)
            val cureType = msg.cureType
            val trapOrSolider = msg.trapOrSolider
            val eventCure = msg.eventCure
            val rt = cureSoliderOnMap(session, cureSoliderInfoList, cureType, trapOrSolider, eventCure, homePlayerDC)
            if (rt == null) {
                return@prepare
            }
            val rtMsg = CureSoliderRt.newBuilder()
            rtMsg.rt = rt
            rtMsg.cureType = cureType
            session.sendMsg(MsgType.CureSolider_1084, rtMsg.build())
        }
    }

    private fun cureSoliderOnMap(
        session: PlayerActor,
        cureSoliderInfoList: LinkedList<CureSoliderInfo>,
        cureType: Int,
        trapOrSolider: Int,
        eventCure: Int,
        homePlayerDC: HomePlayerDC
    ): Int? {
        val player = homePlayerDC.player

        if (cureType != RESEARCH_LVUP_NORMAL && cureType != RESEARCH_LVUP_RMB) {
            return ResultCode.PARAMETER_ERROR.code
        }

        val allNeedCost = LinkedList<ResVo>()
        var allNeedTime = 0

        val addNum = effectHelper.getResearchEffectValue(session, NO_FILTER, CureQuick)

        // 检测消息正确性
        val cureMap = hashMapOf<Int, Int>()
        for (cureInfo in cureSoliderInfoList) {
            val soliderId = cureInfo.soliderId
            val cureNum = cureInfo.cureNum
            val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
            if (soliderProto == null) {
                return ResultCode.NO_PROTO.code
            }

            val isSolider = isSolider(soliderProto.armyType)
            if (isSolider && trapOrSolider != CureSolider) {
                return ResultCode.PARAMETER_ERROR.code
            }

            val isTrap = isTrap(soliderProto.armyType)
            if (isTrap && trapOrSolider != CureTrap) {
                return ResultCode.PARAMETER_ERROR.code
            }

            cureMap[cureInfo.soliderId] = (cureMap[cureInfo.soliderId] ?: 0) + cureInfo.cureNum

            var cureTime: Double
            var curePriceMap: List<ResVo>
            if (eventCure == EventCure) {
                curePriceMap = soliderProto.curePriceActivityMap
                cureTime = soliderProto.cureTimeActivity
            } else {
                curePriceMap = soliderProto.curePriceMap
                cureTime = soliderProto.cureTime
            }

            // 重新计算需要总资源量
            val (ok, newRes) = resVoAddX(curePriceMap, cureNum)
            if (!ok) {
                return ResultCode.BARRACK_GO_ALL_RES_ERROR.code
            }

            allNeedCost += newRes
            allNeedTime += Math.ceil(cureTime * cureNum.toDouble() / (1 + (addNum.toDouble() / 10000)))
                .toInt()
        }

        // 判断资源
        val costRt: CostResWithoutNoticeResult
        val action = ACTION_CURE_SOLIDER
        if (cureType == RESEARCH_LVUP_NORMAL) {
            val checkCost = resHelper.checkRes(session, allNeedCost)
            if (!checkCost) {
                return ResultCode.LESS_RESOUCE.code
            }
            // 扣除资源
            costRt = resHelper.costResWithoutNotice(session, action, player, allNeedCost)

        } else {
            // 补齐资源的模式
            var allCost = 0.0
            val (isCheck, lockVos, haveVos) = resHelper.checkAndTellRes(session, player, allNeedCost)
            if (!isCheck) {
                return ResultCode.RES_ERROR.code
            }
            for (lockVo in lockVos) {
                if (lockVo.lackType == RES_BIND_GOLD) {
                    allCost += lockVo.lackNum
                } else {
                    val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_RES, lockVo.lackType, lockVo.lackNum)
                    allCost += cost
                }
            }

            val costs = LinkedList<ResVo>()

            for (haveVo in haveVos) {
                if (haveVo.extend1 == 0) {
                    costs += ResVo(haveVo.lackType, NOT_PROPS_SUB_TYPE, haveVo.lackNum)
                } else {
                    costs += ResVo(haveVo.lackType, haveVo.extend1.toLong(), haveVo.lackNum)
                }
            }

            costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(allCost).toLong())

            val clearTimeCost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, allNeedTime)
            costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(clearTimeCost).toLong())

            val newCosts = resVoCombine(costs)
            val checkCost = resHelper.checkRes(session, newCosts)
            if (!checkCost) {
                return ResultCode.LESS_RESOUCE.code
            }

            // 扣除资源
            costRt = resHelper.costResWithoutNotice(session, action, player, newCosts)
        }

        val askMsg = CureSoliderAskReq.newBuilder()
        askMsg.cureType = cureType
        askMsg.trapOrSolider = trapOrSolider
        val soliderVo = SoliderVo.newBuilder()
        for ((soliderId, soliderNum) in cureMap) {
            soliderVo.soliderId = soliderId
            soliderVo.soliderNum = soliderNum
            askMsg.addCureMap(soliderVo)
        }
        val effectVo = EffectVo.newBuilder()
        effectVo.effectId = CureQuick
        effectVo.effectValue = addNum
        askMsg.addEffectMap(effectVo)

        askMsg.eventCure = eventCure
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setCureSoliderAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { wrt, err ->
            try {
                when {
                    err != null -> {
                        normalLog.lzWarn { "请求world治疗兵失败:{$err}" }
                        resHelper.addResWithoutNotice(session, action, player, allNeedCost)
                        val rtMsg = CureSoliderRt.newBuilder()
                        rtMsg.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.CureSolider_1084, rtMsg.build())
                        return@whenCompleteKt
                    }
                    wrt == null -> {
                        normalLog.lzWarn { "请求world治疗兵失败:{$err}" }
                        resHelper.addResWithoutNotice(session, action, player, allNeedCost)
                        val rtMsg = CureSoliderRt.newBuilder()
                        rtMsg.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.CureSolider_1084, rtMsg.build())
                        return@whenCompleteKt

                    }
                    else -> {
                        if (wrt.cureSoliderAskRt.rt != ResultCode.SUCCESS.code) {
                            normalLog.lzWarn { "请求world治疗兵失败:{$wrt.rt}" }
                            resHelper.addResWithoutNotice(session, action, player, allNeedCost)
                        } else {
                            costRt.noticeCostRes(session, player)
                        }
                        val rtMsg = CureSoliderRt.newBuilder()
                        rtMsg.rt = wrt.cureSoliderAskRt.rt
                        rtMsg.cureType = cureType
                        session.sendMsg(MsgType.CureSolider_1084, rtMsg.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("CureSoliderAskReq Error!", e)
                resHelper.addResWithoutNotice(session, action, player, allNeedCost)
                val rtMsg = CureSoliderRt.newBuilder()
                rtMsg.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.CureSolider_1084, rtMsg.build())
                return@whenCompleteKt
            }

        }
        return null
    }

}