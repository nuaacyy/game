package com.point18.slg2d.home.module.research

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.sec2MilliSec
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus7
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.ClearTimeEvent
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createResearchChangeNotifier
import pb4client.ResearchLvUp
import pb4client.ResearchLvUpRt
import java.util.*
import java.util.Arrays.asList

// 科技研发
class ResearchDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val refreshRes: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus7<
            HomePlayerDC,
            HeroDC,
            NewEquipDC,
            BagDC,
            HomeSyncDC,
            InnerCityDC,
            HomeMyTargetDC
            >(
        HomePlayerDC::class.java,
        HeroDC::class.java,
        NewEquipDC::class.java,
        BagDC::class.java,
        HomeSyncDC::class.java,
        InnerCityDC::class.java,
        HomeMyTargetDC::class.java,
        Arrays.asList(
            resHelper,
            targetHelper,
            effectHelper,
            refreshRes
        )
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) {
                homePlayerDC,
                heroDC,
                newEquipDC,
                bagDC,
                homeSyncDC,
                innerCityDC,
                homeMyTargetDC ->
            val researchId = (msg as ResearchLvUp).researchId
            val researchType = msg.researchType
            val researchRt = researchLvUp(
                session,
                researchId,
                researchType,
                homePlayerDC,
                homeMyTargetDC,
                innerCityDC,
                heroDC,
                newEquipDC,
                bagDC,
                homeSyncDC
            )
            session.sendMsg(MsgType.ResearchLvUp_1051, researchRt)
        }
    }

    private fun researchLvUp(
        session: PlayerActor,
        researchId: Int,
        researchType: Int,
        homePlayerDC: HomePlayerDC,
        homeMyTargetDC: HomeMyTargetDC,
        innerCityDC: InnerCityDC,
        heroDC: HeroDC,
        newEquipDC: NewEquipDC,
        bagDC: BagDC,
        homeSyncDC: HomeSyncDC
    ): ResearchLvUpRt {
        val rt = pb4client.ResearchLvUpRt.newBuilder()
        rt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code
        rt.researchId = researchId
        rt.researchType = researchType

        if (researchType != RESEARCH_LVUP_NORMAL && researchType != RESEARCH_LVUP_RMB) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }
        val player = homePlayerDC.player

        val mainCastleId = player.focusCastleId
        val playerResearchInfo = player.researchInfoMap

        val researchProto = pcs.researchCache.researchProtoMap[researchId]

        if (null == researchProto) {
            rt.rt = (ResultCode.NO_PROTO.code)
            return rt.build()
        }

        if (researchType == RESEARCH_LVUP_NORMAL) {
            // 判断研发队列 todo 说是就1个,不知道是不是还没定..先这么写了
            val num = player.findResearchNum()

            if (num >= 1) {
                rt.rt = (ResultCode.RESEARCH_DUILIE_ERROR.code)
                return rt.build()
            }
        }

        // 判断前后置

        for ((conditionKey, condition) in researchProto.conditionMap) {

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
                    rt.rt = (ResultCode.RESEARCH_CODITION_ERROR.code)
                    return rt.build()
                }

            } else if (conditionKey == RESEARCH_CODITION_RESEARCH) {
                val researchType = condition[0]
                val researchLv = condition[1]
                val info = playerResearchInfo[researchType]

                if (info == null) {
                    rt.rt = (ResultCode.RESEARCH_CODITION_ERROR.code)
                    return rt.build()
                }

                if (info.researchLv < researchLv) {
                    rt.rt = (ResultCode.RESEARCH_CODITION_ERROR.code)
                    return rt.build()
                }
            }
        }

        var info = playerResearchInfo[researchProto.researchID]

        if (info != null) {

            if (info.researchLv + 1 != researchProto.level) {
                rt.rt = (ResultCode.PARAMETER_ERROR.code)
                return rt.build()
            }

            if (info.researchOverTime != zeroTime.toInstant().toEpochMilli()) {
                rt.rt = (ResultCode.CANCEL_RESEARCH_IN_ERROR.code)
                return rt.build()
            }
        } else {

            if (researchProto.level != 1) {
                rt.rt = (ResultCode.PARAMETER_ERROR.code)
                return rt.build()
            }
        }

        val addNum = effectHelper.getResearchEffectValue(session, NO_FILTER, ResearchQuick)
        // 判断资源

        if (researchType == RESEARCH_LVUP_NORMAL) {
            val checkCost = resHelper.checkRes(session, researchProto.resourcesMap)

            if (!checkCost) {
                rt.rt = (ResultCode.LESS_RESOUCE.code)
                return rt.build()
            }
            // 扣除资源
            resHelper.costRes(session, ACTION_RESEARCH_LV_UP, player, researchProto.resourcesMap)
        } else {
            // 补齐资源的模式
            var allCost = 0.0
            val (isCheck, lockVos, haveVos) = resHelper.checkAndTellRes(
                session,
                player,
                LinkedList(researchProto.resourcesMap)
            )

            if (!isCheck) {
                rt.rt = (ResultCode.RES_ERROR.code)
                return rt.build()
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

            if (((researchProto.time) / ((1) + (effectHelper.getResearchEffectValue(
                    session,
                    NO_FILTER,
                    ResearchQuick
                ) + addNum) / (10000)))
                > (pcs.basicProtoCache.upBuildingFree + effectHelper.getResearchEffectValue(
                    session,
                    NO_FILTER,
                    FreeBuildTime
                ))
            ) {
                val clearTimeCost = pcs.resShopCache.needCost(
                    RESSHOP_TYPE_CLEAN_TIME,
                    1,
                    ((researchProto.time) / ((1) + (effectHelper.getResearchEffectValue(
                        session,
                        NO_FILTER,
                        ResearchQuick
                    ) + addNum) / (10000))) - pcs.basicProtoCache.upBuildingFree - effectHelper.getResearchEffectValue(
                        session,
                        NO_FILTER,
                        FreeBuildTime
                    )
                )
                costs += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, Math.ceil(clearTimeCost).toLong())

                val c = homeMyTargetDC.targetInfo.clearTimeInfo[CLEAR_TIME_RESEARCH]
                if (c == null) {
                    homeMyTargetDC.targetInfo.clearTimeInfo[CLEAR_TIME_RESEARCH] = clearTimeCost.toLong()
                } else {
                    homeMyTargetDC.targetInfo.clearTimeInfo[CLEAR_TIME_RESEARCH] = c + clearTimeCost.toLong()
                }

                fireEvent(session, ClearTimeEvent(CLEAR_TIME_RESEARCH, clearTimeCost.toInt()))
            }
            // 资源检测
            val checkCost = resHelper.checkRes(session, costs)
            if (!checkCost) {
                rt.rt = (ResultCode.LESS_RESOUCE.code)
                return rt.build()
            }

            // 扣除消耗
            resHelper.costRes(session, ACTION_CLEAR_TIME_RESEARCH, player, costs)
        }

        // 修改数据

        if (info == null) {
            // 玩家信息中找不到这个科技,说明是从0升1的

            if (researchType == RESEARCH_LVUP_NORMAL) {
                val now = getNowTime()
                val overTime = now + sec2MilliSec(researchProto.time) / (1 + (effectHelper.getResearchEffectValue(
                    session,
                    NO_FILTER,
                    ResearchQuick
                ) + addNum) / 10000.0).toLong()
                info = ResearchVo(
                    -1,
                    0,
                    0,
                    overTime,
                    hashMapOf(),
                    0
                )
                // 开始研究
                player.researchUpdate(session, researchProto.researchID, info, overTime, getNowTime())

                player.putResearchInfoMap(researchProto.researchID, info)

            } else {
                info = ResearchVo(
                    -1,
                    1,
                    0L,
                    0L,
                    hashMapOf(),
                    0
                )
                player.putResearchInfoMap(researchProto.researchID, info)

                //添加统计
                targetHelper.targetAddVal(
                    session,
                    ResearchCount,
                    LinkedList(asList(1))
                )

                // 效果生效
                val researchEffectProto0 = pcs.researchCache.researchProtoMapByLv[researchProto.researchID]
                if (null == researchEffectProto0) {
                    rt.rt = (ResultCode.NO_PROTO.code)
                    return rt.build()
                }

                val researchEffectProto = researchEffectProto0[info.researchLv]
                if (null == researchEffectProto) {
                    rt.rt = (ResultCode.NO_PROTO.code)
                    return rt.build()
                }

                // 效果改变
                fireEvent(
                    session,
                    ResearchEffectChangeEvent(
                        researchEffectProto.effectMap,
                        targetHelper,
                        effectHelper,
                        refreshRes
                    )
                )
            }

        } else {

            if (researchType == RESEARCH_LVUP_NORMAL) {
                val now = getNowTime()
                val overTime = now + sec2MilliSec(researchProto.time) / (1 + (effectHelper.getResearchEffectValue(
                    session,
                    NO_FILTER,
                    ResearchQuick
                ) + addNum) / 10000.0).toLong()
                player.researchUpdate(session, researchProto.researchID, info, overTime, getNowTime())
                player.putResearchInfoMap(researchProto.researchID, info)

            } else {
                info.researchLv = info.researchLv + 1
                player.putResearchInfoMap(researchProto.researchID, info)

                //添加统计
                targetHelper.targetAddVal(
                    session,
                    ResearchCount,
                    LinkedList()
                )

                // 效果生效
                val researchEffectProto0 = pcs.researchCache.researchProtoMapByLv[researchProto.researchID]
                if (null == researchEffectProto0) {
                    rt.rt = (ResultCode.NO_PROTO.code)
                    return rt.build()
                }
                val researchEffectProto = researchEffectProto0[info.researchLv]
                if (null == researchEffectProto) {
                    rt.rt = (ResultCode.NO_PROTO.code)
                    return rt.build()
                }

                // 效果改变
                fireEvent(
                    session,
                    ResearchEffectChangeEvent(
                        researchEffectProto.effectMap,
                        targetHelper,
                        effectHelper,
                        refreshRes
                    )
                )
            }
        }

        // 推送给客户端变更
        val notice = createResearchChangeNotifier(
            researchProto.researchID,
            info.researchLv,
            info.researchOverTime,
            info.helpId
        )
        notice.notice(session)

        return rt.build()
    }
}
