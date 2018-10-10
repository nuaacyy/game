package com.point18.slg2d.home.module.useTimeSpeedUp

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus6
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.ClearTimeEvent
import com.point18.slg2d.home.module.event.RefreshBuildEffectEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.*
import pb4client.ClearTime
import pb4client.ClearTimeRt
import pb4server.BarrackSpeedAskReq
import pb4server.Home2WorldAskResp
import xyz.ariane.util.lzWarn
import java.util.*

// 加速
class ClearTimeDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val innerCityHelper: InnerCityHelper = InnerCityHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus6<HomePlayerDC,
            InMakeKingEquipDC,
            InnerCityDC,
            HeroDC,
            HomeSyncDC,
            HomeMyTargetDC>(
        HomePlayerDC::class.java,
        InMakeKingEquipDC::class.java,
        InnerCityDC::class.java,
        HeroDC::class.java,
        HomeSyncDC::class.java,
        HomeMyTargetDC::class.java,
        Arrays.asList(
            resHelper,
            effectHelper,
            targetHelper,
            innerCityHelper
        )
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val reqMsg = msg as ClearTime
        val clearType = reqMsg.clearType
        val propsId = reqMsg.clearPropsId
        val num = reqMsg.clearPropsNum
        val extend1 = reqMsg.extend1

        clearTime(session, clearType, propsId, num, extend1)
    }

    private fun clearTime(
        session: PlayerActor,
        clearType: Int,
        propsId: Int,
        num: Int,
        extend1: Long
    ) {
        prepare(session) { homePlayerDC, inMakeKingEquipDC, innerCityDC, heroDC, homeSyncDC, homeMyTargetDC ->
            val rtBuilder = ClearTimeRt.newBuilder()
            rtBuilder.rt = ResultCode.SUCCESS.code
            rtBuilder.clearType = clearType

            var clearTime = 0 // 真实的加速的时间秒数,免费的不算
            val rt: ClearRt
            when (clearType) {
                CLEAR_TIME_RESEARCH -> {
                    rt = clearResearchTime(session, rtBuilder, propsId, num, homePlayerDC)
                }
                CLEAR_TIME_MAKE_SOLIDER -> {
                    rt = clearMakeSoliderTime(session, rtBuilder, propsId, num, homePlayerDC, homeSyncDC)
                }
                CLEAR_TIME_CURE_SOLIDER -> {
                    rt = clearCureSoliderTime(session, rtBuilder, propsId, num, homePlayerDC, homeSyncDC)
                }
                CLEAR_TIME_EVENT_CURE_SOLIDER -> {
                    rt = clearActivityCureSoliderTime(session, rtBuilder, propsId, num, homePlayerDC, homeSyncDC)
                }
                CLEAR_TIME_MAKE_KING_EQUIP -> {
                    rt = clearMakeEquipTime(session, rtBuilder, propsId, num, homePlayerDC, inMakeKingEquipDC)
                }
                CLEAR_TIME_STAR_LV -> {
                    rt = clearStarLvUpTime(session, rtBuilder, propsId, num, homePlayerDC, heroDC)
                }
                CLEAR_TIME_SUPER_LV -> {
                    rt = clearSuperLvUpTime(session, rtBuilder, propsId, num, homePlayerDC, heroDC)
                }
                CLEAR_TIME_SOLIDER_UP -> {
                    rt = clearSoliderUpTime(session, rtBuilder, propsId, num, homePlayerDC, homeSyncDC)
                }
                CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE -> {
                    rt = clearBuildingUpgradeTime(session, rtBuilder, propsId, num, homePlayerDC, innerCityDC)
                }
                CLEAR_TIME_INNER_CITY_BUILDING_DESTROY -> {
                    rt = clearBuildingDestroyTime(session, rtBuilder, propsId, num, homePlayerDC, innerCityDC)
                }
                CLEAR_TIME_RELIC_BOX -> {
                    rt = clearRelicBoxTime(session, rtBuilder, propsId, num, homePlayerDC)
                }
                else -> {
                    rtBuilder.rt = ResultCode.SEND_ALLIANCE_HELP_TYPE_ERROR.code
                    session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                    return@prepare
                }
            }

            clearTime = rt.clearTime
            val c = homeMyTargetDC.targetInfo.clearTimeInfo[clearType]
            if (c == null) {
                homeMyTargetDC.targetInfo.clearTimeInfo[clearType] = clearTime.toLong()
            } else {
                homeMyTargetDC.targetInfo.clearTimeInfo[clearType] = c + clearTime.toLong()
            }

            fireEvent(session, ClearTimeEvent(clearType, clearTime))

            val innerRt = rt.rt
            if (innerRt == null) {
                session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                return@prepare
            } else {
                session.sendMsg(MsgType.ClearTime_1061, innerRt.build())
                return@prepare
            }
        }
    }

    data class ClearRt(var rt: ClearTimeRt.Builder?, var clearTime: Int)

    private fun clearResearchTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        val dua = pcs.basicProtoCache.upBuildingFree + effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            FreeBuildTime
        )

        val costs = LinkedList<ResVo>()

        var clearTime = 0
        val researchInfoMap = player.researchInfoMap
        for ((researchId, researchInfo) in researchInfoMap) {
            if (researchInfo.researchOverTime != zeroTime.time) {
                if (getNowTime() + dua * 1000 < researchInfo.researchOverTime) {
                    // 需要花费钻石进行加速升级
                    if (isClearAll) {
                        val diffSec =
                            getTimeSec(researchInfo.researchOverTime) - getTimeSec(getNowTime() + dua * 1000)
                        val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)

                        clearTime = diffSec
                        costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                        // 资源检测
                        val checkCost = resHelper.checkRes(session, costs)
                        if (!checkCost) {
                            rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                            return ClearRt(rtBuilder, 0)
                        }

                        // 扣除消耗
                        resHelper.costRes(session, ACTION_CLEAR_TIME_RESEARCH, player, costs)

                        forwardHeartDeal2World(
                            session,
                            UpdateHeart,
                            ResearchLvUp,
                            researchId.toLong(),
                            getNowTime()
                        )

                        player.putResearchInfoMap(researchId, researchInfo)
                    } else {
                        // 检测道具数量
                        val propProto = pcs.equipCache.equipProtoMap[propsId]
                        if (propProto == null) {
                            rtBuilder.rt = ResultCode.NO_PROTO.code
                            return ClearRt(rtBuilder, 0)
                        }
                        val oneSec = Integer.parseInt(propProto.extend1)
                        if (propProto.subType != ALL_QUICK_TIME_PROP && propProto.subType != RESEARCH_QUICK_TIME_PROP) {
                            rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                            return ClearRt(rtBuilder, 0)
                        }
                        if (num > 1) {
                            if (getNowTime() + (1000 * oneSec * (num - 1)) > researchInfo.researchOverTime) {
                                rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                                return ClearRt(rtBuilder, 0)
                            }
                        }
                        costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                        // 资源检测
                        val checkCost = resHelper.checkRes(session, costs)
                        if (!checkCost) {
                            rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                            return ClearRt(rtBuilder, 0)
                        }

                        // 扣除消耗
                        resHelper.costRes(session, ACTION_CLEAR_TIME_RESEARCH, player, costs)

                        researchInfo.researchOverTime = researchInfo.researchOverTime - oneSec * num * 1000
                        clearTime = oneSec * num
                        forwardHeartDeal2World(
                            session,
                            UpdateHeart,
                            ResearchLvUp,
                            researchId.toLong(),
                            researchInfo.researchOverTime
                        )

                        player.putResearchInfoMap(researchId, researchInfo)

                        // 推送给客户端变更
                        val notice = createResearchChangeNotifier(
                            researchId,
                            researchInfo.researchLv,
                            researchInfo.researchOverTime,
                            researchInfo.helpId
                        )
                        notice.notice(session)
                    }

                } else {
                    // 免费加速
                    forwardHeartDeal2World(
                        session,
                        UpdateHeart,
                        ResearchLvUp,
                        researchId.toLong(),
                        getNowTime()
                    )

                    player.putResearchInfoMap(researchId, researchInfo)
                }
                break
            }
        }
        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearMakeSoliderTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        homeSyncDC: HomeSyncDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        val costs = LinkedList<ResVo>()
        var clearTime = 0

        for ((_, b) in homeSyncDC.syncData.barracksMap) {
            if (b.makeOverTime == 0L) {
                continue
            }
            if (getNowTime() >= b.makeOverTime) {
                continue
            }
            // 需要花费钻石进行加速升级
            if (isClearAll) {
                val diffSec =
                    getTimeSec(b.makeOverTime) - getTimeSec(getNowTime())
                val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)
                clearTime = diffSec

                costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                // 资源检测
                val checkCost = resHelper.checkRes(session, costs)
                if (!checkCost) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return ClearRt(rtBuilder, 0)
                }

                // 扣除消耗
                val action = ACTION_CLEAR_TIME_MAKE_SOLIDER
                val costRt =
                    resHelper.costResWithoutNotice(session, action, player, costs)

                val askMsg = BarrackSpeedAskReq.newBuilder()
                askMsg.soliderId = b.soldierId
                askMsg.speedType = BarrackMakeSpeed
                askMsg.speedTime = 0
                session.createACS<Home2WorldAskResp>()
                    .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                        it.setBarrackSpeedAskReq(askMsg)
                    }, Home2WorldAskResp::class.java)
                    .whenCompleteKt { askResp, askErr ->

                        try {
                            when {
                                askErr != null -> {
                                    normalLog.lzWarn { "加速造兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                }

                                askResp == null -> {
                                    normalLog.lzWarn { "加速造兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                }

                                else -> {
                                    if (askResp.barrackSpeedAskRt.rt != ResultCode.SUCCESS.code) {
                                        normalLog.lzWarn { "加速造兵失败：{$askResp.rt}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                    } else {
                                        costRt.noticeCostRes(session, player)
                                        session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                    }
                                }
                            }

                        } catch (e: Exception) {
                            normalLog.error("BarrackSpeedAskReq Error!", e)
                            rtBuilder.rt = ResultCode.ASK_ERROR3.code
                            session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                        }

                    }
            } else {
                // 检测道具数量
                val propProto = pcs.equipCache.equipProtoMap[propsId]
                if (propProto == null) {
                    rtBuilder.rt = ResultCode.NO_PROTO.code
                    return ClearRt(rtBuilder, 0)
                }
                val oneSec = Integer.parseInt(propProto.extend1)
                if (propProto.subType != ALL_QUICK_TIME_PROP && propProto.subType != BINGYING_QUICK_TIME_PROP) {
                    rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                    return ClearRt(rtBuilder, 0)
                }
                if (num > 1) {
                    if (getNowTime() + (1000 * oneSec * (num - 1)) > b.makeOverTime) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                }
                costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                // 资源检测
                val checkCost = resHelper.checkRes(session, costs)
                if (!checkCost) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return ClearRt(rtBuilder, 0)
                }

                // 扣除消耗
                val action = ACTION_CLEAR_TIME_MAKE_SOLIDER
                val costRt =
                    resHelper.costResWithoutNotice(session, action, player, costs)

                val askMsg = BarrackSpeedAskReq.newBuilder()
                askMsg.soliderId = b.soldierId
                askMsg.speedType = BarrackMakeSpeed
                askMsg.speedTime = oneSec * num * 1000L

                clearTime = oneSec * num
                session.createACS<Home2WorldAskResp>()
                    .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                        it.setBarrackSpeedAskReq(askMsg)
                    }, Home2WorldAskResp::class.java)
                    .whenCompleteKt { askResp, askErr ->

                        try {
                            when {
                                askErr != null -> {
                                    normalLog.lzWarn { "加速造兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                }

                                askResp == null -> {
                                    normalLog.lzWarn { "加速造兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                }

                                else -> {
                                    if (askResp.barrackSpeedAskRt.rt != ResultCode.SUCCESS.code) {
                                        normalLog.lzWarn { "加速造兵失败：{$askResp.rt}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                    } else {
                                        costRt.noticeCostRes(session, player)
                                        session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                    }
                                }
                            }

                        } catch (e: Exception) {
                            normalLog.error("BarrackSpeedAskReq Error!", e)
                            rtBuilder.rt = ResultCode.ASK_ERROR3.code
                            session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                        }
                    }
            }
            return ClearRt(null, clearTime)
        }
        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearCureSoliderTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        homeSyncDC: HomeSyncDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        val costs = LinkedList<ResVo>()
        var clearTime = 0
        var oneSec = 0
        // 先看够不够资源
        for ((_, b) in homeSyncDC.syncData.barracksMap) {
            if (b.cureOverTime == 0L) {
                continue
            }
            if (getNowTime() < b.cureOverTime) {
                // 需要花费钻石进行加速升级
                if (isClearAll) {
                    val diffSec =
                        getTimeSec(b.cureOverTime) - getTimeSec(getNowTime())
                    val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)
                    clearTime = diffSec
                    costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    val action = ACTION_CLEAR_TIME_CURE_SOLIDER
                    val costRt =
                        resHelper.costResWithoutNotice(session, action, player, costs)

                    val askMsg = BarrackSpeedAskReq.newBuilder()
                    askMsg.soliderId = b.soldierId
                    askMsg.speedType = BarrackCureSpeed
                    askMsg.speedTime = 0
                    session.createACS<Home2WorldAskResp>()
                        .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                            it.setBarrackSpeedAskReq(askMsg)
                        }, Home2WorldAskResp::class.java)
                        .whenCompleteKt { askResp, askErr ->

                            try {
                                when {
                                    askErr != null -> {
                                        normalLog.lzWarn { "加速治疗兵错误：{$askErr}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                    }

                                    askResp == null -> {
                                        normalLog.lzWarn { "加速治疗兵错误：{$askErr}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                    }

                                    else -> {
                                        if (askResp.barrackSpeedAskRt.rt != ResultCode.SUCCESS.code) {
                                            normalLog.lzWarn { "加速治疗兵失败：{$askResp.rt}" }
                                            resHelper.addResWithoutNotice(
                                                session,
                                                action,
                                                player,
                                                costs
                                            )
                                        } else {
                                            costRt.noticeCostRes(session, player)
                                            session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                        }
                                    }
                                }

                            } catch (e: Exception) {
                                normalLog.error("BarrackSpeedAskReq Error!", e)
                                rtBuilder.rt = ResultCode.ASK_ERROR3.code
                                session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                            }

                        }
                } else {
                    // 检测道具数量
                    val propProto = pcs.equipCache.equipProtoMap[propsId]
                    if (propProto == null) {
                        rtBuilder.rt = ResultCode.NO_PROTO.code
                        return ClearRt(rtBuilder, 0)
                    }
                    oneSec = Integer.parseInt(propProto.extend1)
                    if (propProto.subType != ALL_QUICK_TIME_PROP && propProto.subType != SHANGBING_QUICK_TIME_PROP) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                    if (num > 1) {
                        if (getNowTime() + (1000 * oneSec * (num - 1)) > b.cureOverTime) {
                            rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                            return ClearRt(rtBuilder, 0)
                        }
                    }
                    costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    val action = ACTION_CLEAR_TIME_CURE_SOLIDER
                    val costRt =
                        resHelper.costResWithoutNotice(session, action, player, costs)

                    val askMsg = BarrackSpeedAskReq.newBuilder()
                    askMsg.soliderId = b.soldierId
                    askMsg.speedType = BarrackCureSpeed
                    askMsg.speedTime = oneSec * num * 1000L
                    clearTime = oneSec * num
                    session.createACS<Home2WorldAskResp>()
                        .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                            it.setBarrackSpeedAskReq(askMsg)
                        }, Home2WorldAskResp::class.java)
                        .whenCompleteKt { askResp, askErr ->

                            try {
                                when {
                                    askErr != null -> {
                                        normalLog.lzWarn { "加速治疗兵错误：{$askErr}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                    }

                                    askResp == null -> {
                                        normalLog.lzWarn { "加速治疗兵错误：{$askErr}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                    }

                                    else -> {
                                        if (askResp.barrackSpeedAskRt.rt != ResultCode.SUCCESS.code) {
                                            normalLog.lzWarn { "加速治疗兵失败：{$askResp.rt}" }
                                            resHelper.addResWithoutNotice(
                                                session,
                                                action,
                                                player,
                                                costs
                                            )
                                        } else {
                                            costRt.noticeCostRes(session, player)
                                            session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                        }
                                    }
                                }

                            } catch (e: Exception) {
                                normalLog.error("BarrackSpeedAskReq Error!", e)
                                rtBuilder.rt = ResultCode.ASK_ERROR3.code
                                session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                            }

                        }
                }

                return ClearRt(null, clearTime)
            }
        }

        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearActivityCureSoliderTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        homeSyncDC: HomeSyncDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        val costs = LinkedList<ResVo>()

        var clearTime = 0
        var oneSec = 0
        // 先看够不够资源
        for ((_, b) in homeSyncDC.syncData.barracksMap) {
            if (b.eventCureOverTime == 0L) {
                continue
            }
            if (getNowTime() >= b.eventCureOverTime) {
                continue
            }
            // 需要花费钻石进行加速升级
            if (isClearAll) {
                val diffSec =
                    getTimeSec(b.eventCureOverTime) - getTimeSec(getNowTime())
                val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)
                clearTime = diffSec
                costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                // 资源检测
                val checkCost = resHelper.checkRes(session, costs)
                if (!checkCost) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return ClearRt(rtBuilder, 0)
                }

                // 扣除消耗
                val action = ACTION_CLEAR_TIME_CURE_SOLIDER
                val costRt =
                    resHelper.costResWithoutNotice(session, action, player, costs)

                val askMsg = BarrackSpeedAskReq.newBuilder()
                askMsg.soliderId = b.soldierId
                askMsg.speedType = BarrackEventCureSpeed
                askMsg.speedTime = 0
                session.createACS<Home2WorldAskResp>()
                    .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                        it.setBarrackSpeedAskReq(askMsg)
                    }, Home2WorldAskResp::class.java)
                    .whenCompleteKt { askResp, askErr ->

                        try {
                            when {
                                askErr != null -> {
                                    normalLog.lzWarn { "加速治疗兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )

                                    rtBuilder.rt = ResultCode.ASK_ERROR1.code
                                    session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                }

                                askResp == null -> {
                                    normalLog.lzWarn { "加速治疗兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )

                                    rtBuilder.rt = ResultCode.ASK_ERROR2.code
                                    session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                }

                                else -> {
                                    if (askResp.barrackSpeedAskRt.rt != ResultCode.SUCCESS.code) {
                                        normalLog.lzWarn { "加速治疗兵失败：{$askResp.rt}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                        rtBuilder.rt = askResp.barrackSpeedAskRt.rt
                                        session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                    } else {
                                        costRt.noticeCostRes(session, player)
                                        session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                    }
                                }
                            }

                        } catch (e: Exception) {
                            normalLog.error("BarrackSpeedAskReq Error!", e)
                            rtBuilder.rt = ResultCode.ASK_ERROR3.code
                            session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                        }
                    }
            } else {
                // 检测道具数量
                val propProto = pcs.equipCache.equipProtoMap[propsId]
                if (propProto == null) {
                    rtBuilder.rt = ResultCode.NO_PROTO.code
                    return ClearRt(rtBuilder, 0)
                }
                oneSec = Integer.parseInt(propProto.extend1)
                if (propProto.subType != ALL_QUICK_TIME_PROP && propProto.subType != SHANGBING_QUICK_TIME_PROP) {
                    rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                    return ClearRt(rtBuilder, 0)
                }
                if (num > 1) {
                    if (getNowTime() + (1000 * oneSec * (num - 1)) > b.eventCureOverTime) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                }
                costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                // 资源检测
                val checkCost = resHelper.checkRes(session, costs)
                if (!checkCost) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return ClearRt(rtBuilder, 0)
                }

                // 扣除消耗
                val action = ACTION_CLEAR_TIME_CURE_SOLIDER
                val costRt =
                    resHelper.costResWithoutNotice(session, action, player, costs)

                val askMsg = BarrackSpeedAskReq.newBuilder()
                askMsg.soliderId = b.soldierId
                askMsg.speedType = BarrackEventCureSpeed
                askMsg.speedTime = oneSec * num * 1000L
                clearTime = oneSec * num
                session.createACS<Home2WorldAskResp>()
                    .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                        it.setBarrackSpeedAskReq(askMsg)
                    }, Home2WorldAskResp::class.java)
                    .whenCompleteKt { askResp, askErr ->

                        try {
                            when {
                                askErr != null -> {
                                    normalLog.lzWarn { "加速治疗兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                    rtBuilder.rt = ResultCode.ASK_ERROR1.code
                                    session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                }

                                askResp == null -> {
                                    normalLog.lzWarn { "加速治疗兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                    rtBuilder.rt = ResultCode.ASK_ERROR2.code
                                    session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                }

                                else -> {
                                    if (askResp.barrackSpeedAskRt.rt != ResultCode.SUCCESS.code) {
                                        normalLog.lzWarn { "加速治疗兵失败：{$askResp.rt}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                        rtBuilder.rt = askResp.barrackSpeedAskRt.rt
                                        session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                    } else {
                                        costRt.noticeCostRes(session, player)
                                        session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                    }
                                }
                            }

                        } catch (e: Exception) {
                            normalLog.error("BarrackSpeedAskReq Error!", e)
                            rtBuilder.rt = ResultCode.ASK_ERROR3.code
                            session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                        }
                    }
            }

            return ClearRt(null, clearTime)
        }
        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearMakeEquipTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        inMakeEquipDc: InMakeKingEquipDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        val costs = LinkedList<ResVo>()
        var clearTime = 0
        val inMakeKingEquips = inMakeEquipDc.findInMakeByPlayerId()
        for (b in inMakeKingEquips) {
            if (b.overTime != zeroTime.time && b.overTime > getNowTime()) {
                // 需要花费钻石进行加速升级
                if (isClearAll) {
                    val diffSec =
                        getTimeSec(b.overTime) - getTimeSec(getNowTime())
                    val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)
                    clearTime = diffSec
                    costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    resHelper.costRes(session, ACTION_CLEAR_TIME_CURE_SOLIDER, player, costs)

                    b.overTime = getNowTime()

                    val notice = createMakeKingEquipChangeNotifier(UPDATE_IN_MAKE_DUILIE, b)
                    notice.notice(session)
                } else {
                    // 检测道具数量
                    val propProto = pcs.equipCache.equipProtoMap[propsId]
                    if (propProto == null) {
                        rtBuilder.rt = ResultCode.NO_PROTO.code
                        return ClearRt(rtBuilder, 0)
                    }
                    val oneSec = Integer.parseInt(propProto.extend1)
                    if (propProto.subType != ALL_QUICK_TIME_PROP) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                    if (num > 1) {
                        if (getNowTime() + (1000 * oneSec * (num - 1)) > b.overTime) {
                            rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                            return ClearRt(rtBuilder, 0)
                        }
                    }
                    costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    resHelper.costRes(session, ACTION_CLEAR_TIME_CURE_SOLIDER, player, costs)

                    b.overTime = b.overTime - oneSec * 1000 * num
                    clearTime = oneSec * num
                    // 推送给客户端变更
                    val notice = createMakeKingEquipChangeNotifier(UPDATE_IN_MAKE_DUILIE, b)
                    notice.notice(session)
                }
                break
            }
        }

        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearStarLvUpTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        heroDC: HeroDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        val dua = pcs.basicProtoCache.upBuildingFree + effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            FreeBuildTime
        )

        val costs = LinkedList<ResVo>()
        var clearTime = 0
        val heroMap = heroDC.findHeroInStarLvUp()
        if (heroMap.count() != 1) {
            rtBuilder.rt = ResultCode.HERO_CANCEL_STAR_LVUP_ERROR.code
            return ClearRt(rtBuilder, 0)
        }
        for ((_, b) in heroMap) {
            val goTime = getNowTime() + dua * 1000
            if (b.starUpEndTime != zeroTime.time && b.starUpEndTime > goTime) {
                // 需要花费钻石进行加速升级
                if (isClearAll) {
                    val diffSec = getTimeSec(b.starUpEndTime) - getTimeSec(goTime)
                    val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)
                    clearTime = diffSec

                    costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    resHelper.costRes(session, ACTION_CLEAR_TIME_HERO_STAR_LV, player, costs)

                    b.starUpEndTime = getNowTime()
                    //updateStarUpEndTime(areaCache, b, getNowTime())
                    val valueChgNotifier = createValueChgNotifier()
                    valueChgNotifier.append(
                        b.id,
                        HERO_STAR_LV_UP_ENDTIME_CHANGE,
                        getTimeSec(b.starUpEndTime).toLong()
                    )
                    valueChgNotifier.notice(session)
                } else {
                    // 检测道具数量
                    val propProto = pcs.equipCache.equipProtoMap[propsId]
                    if (propProto == null) {
                        rtBuilder.rt = ResultCode.NO_PROTO.code
                        return ClearRt(rtBuilder, 0)
                    }
                    val oneSec = Integer.parseInt(propProto.extend1)
                    if (propProto.subType != ALL_QUICK_TIME_PROP) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                    if (num > 1) {
                        if (getNowTime() + (1000 * oneSec * (num - 1)) > b.starUpEndTime) {
                            rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                            return ClearRt(rtBuilder, 0)
                        }
                    }
                    costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    resHelper.costRes(session, ACTION_CLEAR_TIME_HERO_STAR_LV, player, costs)

                    b.starUpEndTime = b.starUpEndTime - oneSec * 1000 * num
                    clearTime = oneSec * num
                    //updateStarUpEndTime(areaCache, b, b.starUpEndTime - oneSec * 1000 * num)
                    // 发送新的属性给客户端
                    val valueChgNotifier = createValueChgNotifier()
                    valueChgNotifier.append(
                        b.id,
                        HERO_STAR_LV_UP_ENDTIME_CHANGE,
                        getTimeSec(b.starUpEndTime).toLong()
                    )
                    valueChgNotifier.notice(session)
                }

                break
            } else {
                b.starUpEndTime = getNowTime()
                //updateStarUpEndTime(areaCache, b, getNowTime())
                //val playerSession = fetchOnlinePlayerSession(areaCache, player.playerId)
                val valueChgNotifier = createValueChgNotifier()
                valueChgNotifier.append(
                    b.id,
                    HERO_STAR_LV_UP_ENDTIME_CHANGE,
                    getTimeSec(b.starUpEndTime).toLong()
                )
                valueChgNotifier.notice(session)
            }
        }

        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearSuperLvUpTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        heroDC: HeroDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        val dua = pcs.basicProtoCache.upBuildingFree + effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            FreeBuildTime
        )

        val costs = LinkedList<ResVo>()
        var clearTime = 0
        val heroMap = heroDC.findHeroInSuperLvUp()
        if (heroMap.count() != 1) {
            rtBuilder.rt = ResultCode.HERO_CANCEL_SUPER_LVUP_ERROR.code
            return ClearRt(rtBuilder, 0)
        }
        for ((_, b) in heroMap) {
            val goTime = getNowTime() + dua * 1000
            if (b.superUpEndTime != zeroTime.time && b.superUpEndTime > goTime) {
                // 需要花费钻石进行加速升级
                if (isClearAll) {
                    val diffSec = getTimeSec(b.superUpEndTime) - getTimeSec(goTime)
                    val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)
                    clearTime = diffSec
                    costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    resHelper.costRes(session, ACTION_CLEAR_TIME_HERO_SUPER_LV, player, costs)

                    forwardHeartDeal2World(
                        session,
                        UpdateHeart,
                        HeroSuperUp,
                        b.id,
                        getNowTime()
                    )
                } else {
                    // 检测道具数量
                    val propProto = pcs.equipCache.equipProtoMap[propsId]
                    if (propProto == null) {
                        rtBuilder.rt = ResultCode.NO_PROTO.code
                        return ClearRt(rtBuilder, 0)
                    }
                    val oneSec = Integer.parseInt(propProto.extend1)
                    if (propProto.subType != ALL_QUICK_TIME_PROP) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                    if (num > 1) {
                        if (getNowTime() + (1000 * oneSec * (num - 1)) > b.superUpEndTime) {
                            rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                            return ClearRt(rtBuilder, 0)
                        }
                    }
                    costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    resHelper.costRes(session, ACTION_CLEAR_TIME_HERO_SUPER_LV, player, costs)

                    forwardHeartDeal2World(
                        session,
                        UpdateHeart,
                        HeroSuperUp,
                        b.id,
                        b.superUpEndTime - oneSec * 1000 * num
                    )

                    clearTime = oneSec * num

                    // 发送新的属性给客户端
                    val valueChgNotifier = createValueChgNotifier()
                    valueChgNotifier.append(
                        b.id,
                        HERO_SUPER_LV_UP_ENDTIME_CHANGE,
                        getTimeSec(b.superUpEndTime).toLong()
                    )
                    valueChgNotifier.notice(session)
                }

                break
            } else {
                forwardHeartDeal2World(
                    session,
                    UpdateHeart,
                    HeroSuperUp,
                    b.id,
                    getNowTime()
                )
            }
        }

        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearSoliderUpTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        homeSyncDC: HomeSyncDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        val costs = LinkedList<ResVo>()
        var clearTime = 0
        for ((_, b) in homeSyncDC.syncData.barracksMap) {
            if (getNowTime() >= b.upOverTime) {
                continue
            }
            // 需要花费钻石进行加速升级
            if (isClearAll) {
                val diffSec =
                    getTimeSec(b.upOverTime) - getTimeSec(getNowTime())
                val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)

                clearTime = diffSec
                costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                // 资源检测
                val checkCost = resHelper.checkRes(session, costs)
                if (!checkCost) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return ClearRt(rtBuilder, 0)
                }

                // 扣除消耗
                val action = ACTION_CLEAR_TIME_SOLIDER_UP
                val costRt =
                    resHelper.costResWithoutNotice(session, action, player, costs)

                val askMsg = BarrackSpeedAskReq.newBuilder()
                askMsg.soliderId = b.soldierId
                askMsg.speedType = BarrackUpSpeed
                askMsg.speedTime = 0
                session.createACS<Home2WorldAskResp>()
                    .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                        it.setBarrackSpeedAskReq(askMsg)
                    }, Home2WorldAskResp::class.java)
                    .whenCompleteKt { askResp, askErr ->

                        try {
                            when {
                                askErr != null -> {
                                    normalLog.lzWarn { "加速晋升兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                }

                                askResp == null -> {
                                    normalLog.lzWarn { "加速晋升兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                }

                                else -> {
                                    if (askResp.barrackSpeedAskRt.rt != ResultCode.SUCCESS.code) {
                                        normalLog.lzWarn { "加速晋升兵失败：{$askResp.rt}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                    } else {
                                        costRt.noticeCostRes(session, player)
                                        session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                    }
                                }
                            }

                        } catch (e: Exception) {
                            normalLog.error("BarrackSpeedAskReq Error!", e)
                            rtBuilder.rt = ResultCode.ASK_ERROR3.code
                            session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                        }


                    }
            } else {
                // 检测道具数量
                val propProto = pcs.equipCache.equipProtoMap[propsId]
                if (propProto == null) {
                    rtBuilder.rt = ResultCode.NO_PROTO.code
                    return ClearRt(rtBuilder, 0)
                }
                val oneSec = Integer.parseInt(propProto.extend1)
                if (propProto.subType != ALL_QUICK_TIME_PROP && propProto.subType != BINGYING_QUICK_TIME_PROP) {
                    rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                    return ClearRt(rtBuilder, 0)
                }
                if (num > 1) {
                    if (getNowTime() + (1000 * oneSec * (num - 1)) > b.upOverTime) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                }
                costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                // 资源检测
                val checkCost = resHelper.checkRes(session, costs)
                if (!checkCost) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return ClearRt(rtBuilder, 0)
                }

                // 扣除消耗
                resHelper.costRes(session, ACTION_CLEAR_TIME_SOLIDER_UP, player, costs)
                val action = ACTION_CLEAR_TIME_SOLIDER_UP
                val costRt =
                    resHelper.costResWithoutNotice(session, action, player, costs)

                clearTime = oneSec * num

                val askMsg = BarrackSpeedAskReq.newBuilder()
                askMsg.soliderId = b.soldierId
                askMsg.speedType = BarrackUpSpeed
                askMsg.speedTime = oneSec * num * 1000L
                session.createACS<Home2WorldAskResp>()
                    .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                        it.setBarrackSpeedAskReq(askMsg)
                    }, Home2WorldAskResp::class.java)
                    .whenCompleteKt { askResp, askErr ->

                        try {
                            when {
                                askErr != null -> {
                                    normalLog.lzWarn { "加速晋升兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                }

                                askResp == null -> {
                                    normalLog.lzWarn { "加速晋升兵错误：{$askErr}" }
                                    resHelper.addResWithoutNotice(
                                        session,
                                        action,
                                        player,
                                        costs
                                    )
                                }

                                else -> {
                                    if (askResp.barrackSpeedAskRt.rt != ResultCode.SUCCESS.code) {
                                        normalLog.lzWarn { "加速晋升兵失败：{$askResp.rt}" }
                                        resHelper.addResWithoutNotice(
                                            session,
                                            action,
                                            player,
                                            costs
                                        )
                                    } else {
                                        costRt.noticeCostRes(session, player)
                                        session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                                    }
                                }
                            }

                        } catch (e: Exception) {
                            normalLog.error("BarrackSpeedAskReq Error!", e)
                            rtBuilder.rt = ResultCode.ASK_ERROR3.code
                            session.sendMsg(MsgType.ClearTime_1061, rtBuilder.build())
                        }

                    }
            }
            return ClearRt(null, clearTime)
        }

        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearBuildingUpgradeTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        // 计算免费时间
        val dua = pcs.basicProtoCache.upBuildingFree + effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            FreeBuildTime
        )

        val costs = LinkedList<ResVo>()
        var clearTime = 0

        val innerCityBuildings = innerCityDC.findInnerCityListFromCastleId(player.focusCastleId)
        for (b in innerCityBuildings) {
            if (b.state != UPGRADE) {
                continue
            }

            val completeTime = b.completeTime
            val goTime = getNowTime() + dua * 1000

            val state: Int
            val startTime: Long
            val finishTime: Long

            var isFree = false
            if (goTime >= completeTime) {
                isFree = true
            }

            if (isFree || isClearAll) {
                if (!isFree) {
                    val diffSec = getTimeSec(completeTime) - getTimeSec(goTime)
                    val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)
                    clearTime = diffSec
                    costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    resHelper.costRes(session, ACTION_CLEAR_TIME_INNER_CITY_BUILDING, player, costs)
                }

                // 建筑直接升级
                innerCityDC.updateInnerCityUpgradeState(b, STABLE, 0, 0)
                innerCityHelper.buildLvUp(session, b)
            } else {
                val propProto = pcs.equipCache.equipProtoMap[propsId]
                if (propProto == null) {
                    rtBuilder.rt = ResultCode.NO_PROTO.code
                    return ClearRt(rtBuilder, 0)
                }
                val oneSec = Integer.parseInt(propProto.extend1)
                if (propProto.subType != ALL_QUICK_TIME_PROP && propProto.subType != BUILD_QUICK_TIME_PROP) {
                    rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                    return ClearRt(rtBuilder, 0)
                }

                val complete = b.completeTime

                if (num > 1) {
                    if (getNowTime() + (1000 * oneSec * (num - 1)) > complete) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                }
                costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                // 资源检测
                val checkCost = resHelper.checkRes(session, costs)
                if (!checkCost) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return ClearRt(rtBuilder, 0)
                }

                // 扣除消耗
                resHelper.costRes(session, ACTION_CLEAR_TIME_INNER_CITY_BUILDING, player, costs)

                state = b.state
                startTime = b.startTime
                finishTime = completeTime - oneSec * 1000 * num

                clearTime = oneSec * num

                // 通知世界服心跳变化
                innerCityDC.updateInnerCityUpgradeState2World(
                    session,
                    b,
                    state,
                    finishTime,
                    startTime
                )

                //向客户端推送建筑变化
                createInnerCityInfoChangedNotifier(
                    CHANGE_INNER_CITY,
                    b.cityType,
                    b.id,
                    getTimeSec(b.startTime),
                    getTimeSec(b.completeTime),
                    b.state, b.positionIndex,
                    b.lv, b.helpId
                ).notice(session)
            }

            // 触发刷新建筑效果事件
            fireEvent(session, RefreshBuildEffectEvent(targetHelper, effectHelper))
            break
        }

        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearBuildingDestroyTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC
    ): ClearRt {
        val player = homePlayerDC.player

        var isClearAll = false
        if (propsId == 0 && num == 0) {
            isClearAll = true
        }

        val costs = LinkedList<ResVo>()
        var clearTime = 0

        val innerCityBuildings = innerCityDC.findInnerCityListFromCastleId(player.focusCastleId)
        for (b in innerCityBuildings) {
            if (b.state != DESTROY) {
                continue
            }

            val completeTime = b.destroyTime
            val goTime = getNowTime()

            var state = STABLE
            var startTime = 0L
            var finishTime = 0L
            var op = CHANGE_INNER_CITY

            var isFree = false
            if (goTime >= completeTime) {
                isFree = true
            }

            if (isFree || isClearAll) { // 秒拆除
                if (!isFree) {
                    // 扣一次钱
                    val diffSec = getTimeSec(completeTime) - getTimeSec(goTime)
                    val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)

                    clearTime = diffSec
                    costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(cost).toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    resHelper.costRes(session, ACTION_CLEAR_TIME_INNER_CITY_BUILDING, player, costs)
                }

                //删除建筑
                innerCityDC.deleteInnerCity(b)
                op = DELETE_INNER_CITY
            } else {
                // 检测道具数量
                val propProto = pcs.equipCache.equipProtoMap[propsId]
                if (propProto == null) {
                    rtBuilder.rt = ResultCode.NO_PROTO.code
                    return ClearRt(rtBuilder, 0)
                }
                val oneSec = Integer.parseInt(propProto.extend1)
                if (propProto.subType != ALL_QUICK_TIME_PROP && propProto.subType != BUILD_QUICK_TIME_PROP) {
                    rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                    return ClearRt(rtBuilder, 0)
                }

                val complete = b.destroyTime

                if (num > 1) {
                    if (getNowTime() + (1000 * oneSec * (num - 1)) > complete) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                }

                // 资源检测，扣除
                costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())
                val checkCost = resHelper.checkRes(session, costs)
                if (!checkCost) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return ClearRt(rtBuilder, 0)
                }
                resHelper.costRes(session, ACTION_CLEAR_TIME_INNER_CITY_BUILDING, player, costs)

                state = b.state
                startTime = b.startTime
                finishTime = completeTime - oneSec * 1000 * num

                clearTime = oneSec * num
            }

            // 通知世界服心跳变化
            innerCityDC.updateInnerCityDestroyState2World(
                session,
                b,
                state,
                finishTime,
                startTime
            )

            // 向客户端推送建筑变化/删除
            createInnerCityInfoChangedNotifier(
                op,
                b.cityType,
                b.id,
                getTimeSec(b.startTime),
                getTimeSec(b.destroyTime),
                b.state, b.positionIndex,
                b.lv, b.helpId
            ).notice(session)

            // 触发刷新建筑效果事件
            fireEvent(session, RefreshBuildEffectEvent(targetHelper, effectHelper))
            break
        }

        return ClearRt(rtBuilder, clearTime)
    }

    private fun clearRelicBoxTime(
        session: PlayerActor,
        rtBuilder: ClearTimeRt.Builder,
        propsId: Int,
        num: Int,
        homePlayerDC: HomePlayerDC
    ): ClearRt {
        val player = homePlayerDC.player

        val now = getNowTime()
        val costs = LinkedList<ResVo>()

        if (num < 1) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return ClearRt(rtBuilder, 0)
        }

        var clearTime = 0
        val timeBoxMap = player.timeBoxNumMap
        for ((timeBoxIndex, timeBoxInfo) in timeBoxMap) {
            if (timeBoxInfo.timeBoxTimeOver != 0L) {
                if (now < timeBoxInfo.timeBoxTimeOver) {
                    // 检测道具数量
                    val propProto = pcs.equipCache.equipProtoMap[propsId]
                    if (propProto == null) {
                        rtBuilder.rt = ResultCode.NO_PROTO.code
                        return ClearRt(rtBuilder, 0)
                    }
                    val oneSec = Integer.parseInt(propProto.extend1)
                    if (propProto.subType != TIME_BOX_QUICK_TIME_PROP) {
                        rtBuilder.rt = ResultCode.QUICK_PROPS_ERROR.code
                        return ClearRt(rtBuilder, 0)
                    }
                    if (num > 1) {
                        if (now + (1000 * oneSec * (num - 1)) > timeBoxInfo.timeBoxTimeOver) {
                            rtBuilder.rt = ResultCode.QUICK_PROPS_MAX_ERROR.code
                            return ClearRt(rtBuilder, 0)
                        }
                    }
                    costs += ResVo(RES_PROPS, propsId.toLong(), num.toLong())

                    // 资源检测
                    val checkCost = resHelper.checkRes(session, costs)
                    if (!checkCost) {
                        rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                        return ClearRt(rtBuilder, 0)
                    }

                    // 扣除消耗
                    resHelper.costRes(session, ACTION_CLEAR_TIME_RESEARCH, player, costs)

                    timeBoxInfo.timeBoxTimeOver = timeBoxInfo.timeBoxTimeOver - oneSec * num * 1000
                    clearTime = oneSec * num

                    player.putTimeBoxNumMap(timeBoxIndex, timeBoxInfo)

                    // 推送给客户端变更
                    val notice = createTimeBoxInfoChangeNotifier(timeBoxIndex, timeBoxInfo)
                    notice.notice(session)
                } else {
                    // 免费加速
                    // 推送给客户端变更
                    val notice = createTimeBoxInfoChangeNotifier(timeBoxIndex, timeBoxInfo)
                    notice.notice(session)
                }
                break
            }
        }
        return ClearRt(rtBuilder, clearTime)
    }
}