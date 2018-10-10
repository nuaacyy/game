package com.point18.slg2d.home.module.barracks

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoAddX
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.MakeSolider
import pb4client.MakeSoliderRt
import pb4server.EffectVo
import pb4server.Home2WorldAskResp
import pb4server.MakeSoliderAskReq
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.Arrays.asList

// 造兵

class MakeSoliderDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, InnerCityDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java, asList(resHelper, effectHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC ->
            val soliderId = (msg as MakeSolider).soliderId
            val makeType = msg.makeType
            val makeNum = msg.makeNum
            val makeSoliderRt = makeSolider(
                session, soliderId, makeType, makeNum, homePlayerDC, innerCityDC
            )
            if (makeSoliderRt == null) {
                return@prepare
            }
            session.sendMsg(MsgType.MakeSolider_1081, makeSoliderRt.build())
        }
    }

    private fun makeSolider(
        session: PlayerActor,
        soliderId: Int,
        makeType: Int,
        makeNum: Int,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC
    ): MakeSoliderRt.Builder? {

        val rt = MakeSoliderRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        if (makeType != RESEARCH_LVUP_NORMAL && makeType != RESEARCH_LVUP_RMB) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
        if (soliderProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        if (isSolider(soliderProto.armyType)) {
            val rst = dealMakeSolider(session, soliderId, makeType, makeNum, homePlayerDC, innerCityDC)
            if (rst == null) {
                return null
            }
            rt.rt = rst
            return rt
        }
        if (isTrap(soliderProto.armyType)) {
            val rst = dealMakeTrap(session, soliderId, makeType, makeNum, homePlayerDC, innerCityDC)
            if (rst == null) {
                return null
            }
            rt.rt = rst
            return rt
        }
        rt.rt = ResultCode.PARAMETER_ERROR.code
        return rt
    }

    //处理造兵
    private fun dealMakeSolider(
        session: PlayerActor,
        soliderId: Int,
        makeType: Int,
        makeNum: Int,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC
    ): Int? {
        val player = homePlayerDC.player
        val mainCastleId = player.focusCastleId
        val playerResearchInfo = player.researchInfoMap

        //判断最大造兵数量
        val maxAddNum = effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            ResearchEffectMakeSoliderMaxAdd
        )
        val effValue = effectHelper.getResearchEffectValue(session, NO_FILTER, MakeSoliderNum)
        if (makeNum <= 0 || makeNum > ((effValue * (1 + (maxAddNum / 10000.0))).toInt())) {
            //commonfunc.NormalLog.Error("服务器造兵数量最大值为:%v", Int(float64(gamecommon.getResearchEffectValue(areaCache, constg.NO_FILTER, player, constg.MakeSoliderNum)) * (1 + (float64(maxAddNum) / 10000))))
            return ResultCode.PARAMETER_ERROR.code
        }

        val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
        if (soliderProto == null) {
            return ResultCode.NO_PROTO.code
        }
        // 判断前后置
        for ((conditionKey, condition) in soliderProto.conditionMap) {
            if (conditionKey == RESEARCH_CODITION_BUILDING) {
                val buildType = condition[0]
                val buildLv = condition[1]
                val buildings = innerCityDC.findInnerCityListFromCastleIdAndType(mainCastleId, buildType)
                var flag = false
                for (building in buildings) {
                    if (building.lv >= buildLv) {
                        flag = true
                        break
                    }
                }
                if (!flag) {
                    return ResultCode.RESEARCH_CODITION_ERROR.code
                }
            } else if (conditionKey == RESEARCH_CODITION_RESEARCH) {
                val researchType = condition[0]
                val researchLv = condition[1]
                val info = playerResearchInfo[researchType]
                if (info == null) {
                    return ResultCode.RESEARCH_CODITION_ERROR.code
                }
                if (info.researchLv < researchLv) {
                    return ResultCode.RESEARCH_CODITION_ERROR.code
                }
            }
        }

        // 重新计算需要总资源量
        val (ok, newRes) = resVoAddX(soliderProto.trainPriceMap, makeNum)
        if (!ok) {
            return ResultCode.BARRACK_GO_ALL_RES_ERROR.code
        }

        val addNum = effectHelper.getResearchEffectValue(session, NO_FILTER, MakeSoliderQuick)

        // 判断资源
        val costRt: CostResWithoutNoticeResult
        val allCosts: LinkedList<ResVo>
        val action = ACTION_MAKE_SOLIDER
        if (makeType == RESEARCH_LVUP_NORMAL) {
            val checkCost = resHelper.checkRes(session, newRes)
            if (!checkCost) {
                return ResultCode.LESS_RESOUCE.code
            }
            // 扣除资源
            allCosts = newRes
            costRt = resHelper.costResWithoutNotice(session, action, player, newRes)
        } else {
            // 补齐资源的模式
            var allCost = 0.0
            val (isCheck, lockVos, haveVos) = resHelper.checkAndTellRes(session, player, newRes)
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

            costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, (Math.ceil(allCost)).toLong())

            val needSec = ((soliderProto.trainTime * makeNum).toDouble() / (1.toDouble() + (addNum / 10000))).toInt()
            val clearTimeCost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, needSec)
            costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, (Math.ceil(clearTimeCost)).toLong())

            val checkCost = resHelper.checkRes(session, costs)
            if (!checkCost) {
                return ResultCode.LESS_RESOUCE.code
            }

            // 扣除资源
            allCosts = costs
            costRt = resHelper.costResWithoutNotice(session, action, player, costs)
        }

        //转发至world
        val askMsg = MakeSoliderAskReq.newBuilder()
        askMsg.soliderId = soliderId
        askMsg.makeType = makeType
        askMsg.makeNum = makeNum
        val tmpMap = EffectVo.newBuilder()
        tmpMap.effectId = MakeSoliderQuick
        tmpMap.effectValue = addNum
        askMsg.addEffectMap(tmpMap)
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setMakeSoliderAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, err ->

            try {
                when {
                    err != null -> {
                        val makeSoliderRt = MakeSoliderRt.newBuilder()
                        makeSoliderRt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.MakeSolider_1081, makeSoliderRt.build())
                        normalLog.lzWarn { "请求world造兵失败:{$err}" }
                        resHelper.addResWithoutNotice(session, action, player, allCosts)
                        return@whenCompleteKt
                    }
                    askResp == null -> {
                        val makeSoliderRt = MakeSoliderRt.newBuilder()
                        makeSoliderRt.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.MakeSolider_1081, makeSoliderRt.build())
                        resHelper.addResWithoutNotice(session, action, player, allCosts)
                        return@whenCompleteKt

                    }
                    else -> {
                        if (askResp.makeSoliderAskRt.rt != ResultCode.SUCCESS.code) {
                            normalLog.lzWarn { "请求world造兵失败:{$askResp.rt}" }
                            resHelper.addResWithoutNotice(session, action, player, allCosts)
                        } else {
                            costRt.noticeCostRes(session, player)
                        }
                        val makeSoliderRt = MakeSoliderRt.newBuilder()
                        makeSoliderRt.rt = askResp.makeSoliderAskRt.rt
                        session.sendMsg(MsgType.MakeSolider_1081, makeSoliderRt.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("MakeSoliderAskReq Error!", e)
                val makeSoliderRt = MakeSoliderRt.newBuilder()
                makeSoliderRt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.MakeSolider_1081, makeSoliderRt.build())
                resHelper.addResWithoutNotice(session, action, player, allCosts)
                return@whenCompleteKt
            }
        }

        return null
    }

    //处理造陷阱
    private fun dealMakeTrap(
        session: PlayerActor,
        soliderId: Int,
        makeType: Int,
        makeNum: Int,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC
    ): Int? {
        val player = homePlayerDC.player
        val mainCastleId = player.focusCastleId
        val playerResearchInfo = player.researchInfoMap

        val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
        if (soliderProto == null) {
            return ResultCode.NO_PROTO.code
        }
        // 判断前后置
        for ((conditionKey, condition) in soliderProto.conditionMap) {
            if (conditionKey == RESEARCH_CODITION_BUILDING) {
                val buildType = condition[0]
                val buildLv = condition[1]
                val buildings = innerCityDC.findInnerCityListFromCastleIdAndType(mainCastleId, buildType)
                var flag = false
                for (building in buildings) {
                    if (building.lv >= buildLv) {
                        flag = true
                        break
                    }
                }
                if (!flag) {
                    return ResultCode.RESEARCH_CODITION_ERROR.code
                }
            } else if (conditionKey == RESEARCH_CODITION_RESEARCH) {
                val researchType = condition[0]
                val researchLv = condition[1]
                val info = playerResearchInfo[researchType]
                if (info == null) {
                    return ResultCode.RESEARCH_CODITION_ERROR.code
                }
                if (info.researchLv < researchLv) {
                    return ResultCode.RESEARCH_CODITION_ERROR.code
                }
            }
        }

        // 重新计算需要总资源量
        val (ok, newRes) = resVoAddX(soliderProto.trainPriceMap, makeNum)
        if (!ok) {
            return ResultCode.BARRACK_GO_ALL_RES_ERROR.code
        }

        val addNum = effectHelper.getResearchEffectValue(session, NO_FILTER, MakeTrapQuick)

        // 判断资源
        val costRt: CostResWithoutNoticeResult
        val allCosts: LinkedList<ResVo>
        val action = ACTION_MAKE_SOLIDER
        if (makeType == RESEARCH_LVUP_NORMAL) {
            val checkCost = resHelper.checkRes(session, newRes)
            if (!checkCost) {
                return ResultCode.LESS_RESOUCE.code
            }
            // 扣除资源
            allCosts = newRes
            costRt = resHelper.costResWithoutNotice(session, action, player, newRes)
        } else {
            // 补齐资源的模式
            var allCost = 0.0
            val (isCheck, lockVos, haveVos) = resHelper.checkAndTellRes(session, player, newRes)
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

            costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, allCost.toLong())

            val needSec = ((soliderProto.trainTime * makeNum).toDouble() / ((1 + addNum).toDouble() / 10000)).toInt()
            val clearTimeCost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, needSec)
            costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, clearTimeCost.toLong())

            val checkCost = resHelper.checkRes(session, costs)
            if (!checkCost) {
                return ResultCode.LESS_RESOUCE.code
            }
            // 扣除资源
            allCosts = costs
            costRt = resHelper.costResWithoutNotice(session, action, player, costs)
        }

        //转发至world
        val askMsg = MakeSoliderAskReq.newBuilder()
        askMsg.soliderId = soliderId
        askMsg.makeType = makeType
        askMsg.makeNum = makeNum
        val tmpMap = EffectVo.newBuilder()
        tmpMap.effectId = MakeTrapQuick
        tmpMap.effectValue = addNum
        askMsg.addEffectMap(tmpMap)
        val tmpMap2 = EffectVo.newBuilder()
        tmpMap2.effectId = TrapNumAdd
        tmpMap2.effectValue = effectHelper.getResearchEffectValue(session, NO_FILTER, TrapNumAdd)
        askMsg.addEffectMap(tmpMap2)

        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setMakeSoliderAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, err ->
            try {
                when {
                    err != null -> {
                        val makeSoliderRt = MakeSoliderRt.newBuilder()
                        makeSoliderRt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.MakeSolider_1081, makeSoliderRt.build())
                        normalLog.lzWarn { "请求world造兵失败:{$err}" }
                        resHelper.addResWithoutNotice(session, action, player, allCosts)
                        return@whenCompleteKt
                    }
                    askResp == null -> {
                        val makeSoliderRt = MakeSoliderRt.newBuilder()
                        makeSoliderRt.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.MakeSolider_1081, makeSoliderRt.build())
                        normalLog.lzWarn { "请求world造兵失败:{$err}" }
                        resHelper.addResWithoutNotice(session, action, player, allCosts)
                        return@whenCompleteKt

                    }
                    else -> {
                        if (askResp.makeSoliderAskRt.rt != ResultCode.SUCCESS.code) {
                            normalLog.lzWarn { "请求world造兵失败:{$askResp.rt}" }
                            resHelper.addResWithoutNotice(session, action, player, allCosts)
                        } else {
                            costRt.noticeCostRes(session, player)
                        }
                        val makeSoliderRt = MakeSoliderRt.newBuilder()
                        makeSoliderRt.rt = askResp.makeSoliderAskRt.rt
                        session.sendMsg(MsgType.MakeSolider_1081, makeSoliderRt.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("MakeSoliderAskReq Error!", e)
                val makeSoliderRt = MakeSoliderRt.newBuilder()
                makeSoliderRt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.MakeSolider_1081, makeSoliderRt.build())
                resHelper.addResWithoutNotice(session, action, player, allCosts)
                return@whenCompleteKt
            }

        }

        return null
    }

}
