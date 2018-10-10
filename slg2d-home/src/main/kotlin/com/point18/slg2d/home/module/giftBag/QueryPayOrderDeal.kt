package com.point18.slg2d.home.module.giftBag

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.ONE_DAY_MILLS
import com.point18.slg2d.common.commonfunc.ONE_HOUR_MILLS
import com.point18.slg2d.common.commonfunc.betweenDays
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.hpm
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.*
import java.util.*
import java.util.Arrays.asList

class QueryPayOrderDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val refHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val mailHelper: MailHelper = MailHelper()
) : HomeClientMsgDeal, HomeHelperPlus4<HomePlayerDC, GiftBagDC, GiftBagRecordDC, QuotaBagDC>(
    HomePlayerDC::class.java, GiftBagDC::class.java, GiftBagRecordDC::class.java, QuotaBagDC::class.java,
    asList(resHelper, refHelper, targetHelper, effectHelper, mailHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, giftBagDC: GiftBagDC,
                           giftBagRecordDC: GiftBagRecordDC, quotaBagDC: QuotaBagDC ->
            msg as QueryPayOrder
            val rt = deal(
                session, msg.giftBagId, msg.giftBagLevel, giftBagDC, homePlayerDC, quotaBagDC, giftBagRecordDC
            )
            session.sendMsg(MsgType.QueryPayOrder_1582, rt.build())
        }
    }

    private fun deal(
        session: PlayerActor, giftBagId: Int, giftBagLevel: Int, giftBagDC: GiftBagDC,
        homePlayerDC: HomePlayerDC, quotaBagDC: QuotaBagDC, giftBagRecordDC: GiftBagRecordDC
    ): QueryPayOrderRt.Builder {
        val rt = QueryPayOrderRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val giftBagProto = pcs.giftBagProtoCache.giftBagMapByGiftBagIdLevel.findByKey(giftBagId, giftBagLevel)
        if (giftBagProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        // 检查月卡是否到期
        if (giftBagProto.giftType == MONTH_CARD) {
            val monthCard = homePlayerDC.player.monthCards[giftBagId]
            if (monthCard != null && monthCard.isActive()) {
                rt.rt = ResultCode.GIFTBAG_MONTH_CARD_ACTIVE.code
                return rt
            }
        } else {
            // 检查礼包活动是否开放
            if (!giftBagProto.isOpen()) {
                rt.rt = ResultCode.GIFTBAG_NOT_OPEN.code
                return rt
            }

            var giftBag = giftBagDC.getGiftBag(giftBagProto.giftId)
            if (giftBag == null) {
                if (giftBagProto.giftType == TRIGGER_GIFTBAG) {
                    rt.rt = ResultCode.TRIGGER_GIFTBAG_NOT_EXIST.code
                    return rt
                }
                giftBag = giftBagDC.createGiftBag(giftBagProto.giftId, session.playerId, giftBagProto.getEndTime())
            }

            // 检查礼包是否过期 如果过期则刷新 触发类礼包过期则不允许再购买
            if (!giftBagProto.isActive(giftBag.endTime)) {
                if (giftBagProto.giftType == TRIGGER_GIFTBAG) {
                    rt.rt = ResultCode.TRIGGER_GIFTBAG_IS_OVERDUE.code
                    return rt
                }
                giftBag.refresh(giftBagProto.getEndTime())
            }

            // 首先校验是否可以购买这一档位
            // 如果是当前档位则判断次数是否为达到上限
            // 若不是当前档位则判断是否是下一档,若是下一档则判断当前次数是否已满
            if (giftBagProto.giftLevel == giftBag.curLevel) {
                if (giftBag.curCount >= giftBagProto.giftLimitation && giftBagProto.giftLimitation > 0) {
                    rt.rt = ResultCode.GIFTBAG_BUY_COUNT_MAX.code
                    return rt
                }
            } else {
                if (giftBagProto.giftLevel - giftBag.curLevel != 1) {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    return rt
                }

                if (giftBag.curCount < giftBagProto.giftLimitation) {
                    rt.rt = ResultCode.GIFTBAG_LAST_GIFTBAG_BUY_COUNT_NOENOUGH.code
                    return rt
                }
            }
        }

        // TODO 功能测试直接发放奖励，充值做好后删除代码
        val drop = pcs.dropBagCache.dropBagMap[giftBagProto.reward]
        if (drop == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        val quotaBag = quotaBagDC.quotaBag

        var giftBag = giftBagDC.getGiftBag(giftBagProto.giftId)
        if (giftBag == null) {
            giftBag = giftBagDC.createGiftBag(giftBagProto.giftId, session.playerId, giftBagProto.getEndTime())
        }

        // 检查月卡，月卡奖励需要每天领取,不直接发放
        if (giftBagProto.giftType == MONTH_CARD) {
            val monthCardDays = pcs.basicProtoCache.monthCardDays
            var monthCard = homePlayerDC.player.monthCards[giftBagId]
            val overdueTime = getNowTime() + monthCardDays * ONE_DAY_MILLS
            if (monthCard == null) {
                monthCard = MonthCard(0, overdueTime)
                homePlayerDC.player.monthCards[giftBagId] = monthCard
            }
            monthCard.lastRecvTime = 0
            monthCard.overdueTime = overdueTime
            // 刷新月卡的购买信息
            giftBag.refresh(overdueTime)
            giftBag.recordCount(giftBagProto.giftLevel)
            // 推送购买礼包成功
            session.sendMsg(
                MsgType.PayGiftBagSuccess_3359,
                PayGiftBagSuccess.newBuilder().setGiftBagInfo(
                    GiftBagInfo.newBuilder()
                        .setGiftBagId(giftBagId)
                        .setEndTime(monthCard.overdueTime)
                        .setCurLevel(giftBag.curLevel)
                        .setCurCount(giftBag.curCount)
                        .build()
                ).build()
            )
            // 推送购买月卡成功
            session.sendMsg(
                MsgType.PayMonthCardSuccess_3360,
                PayMonthCardSuccess.newBuilder().setMonthCardInfo(
                    MonthCardInfo.newBuilder()
                        .setMonthCardId(giftBagId)
                        .setInDayRecv(0)
                        .setOverdueTime(monthCard.overdueTime)
                        .build()
                ).build()
            )
        } else {
            // 记录购买礼包
            giftBag.recordCount(giftBagProto.giftLevel)
            giftBagRecordDC.giftBagRecord.addRecord(giftBagProto.id)

            // 添加礼包奖励
            resHelper.addRes(session, RECHARGE_GIFTBAG, homePlayerDC.player, drop.dropMap)
            // 科技刷新
            fireEvent(
                session,
                ResearchEffectChangeEvent(
                    giftBagProto.upEff,
                    targetHelper,
                    effectHelper,
                    refHelper
                )
            )
            //推送资源信息
            refHelper.refreshResource(session, AllResYield)

            // 推送购买成功
            session.sendMsg(
                MsgType.PayGiftBagSuccess_3359,
                PayGiftBagSuccess.newBuilder().setGiftBagInfo(
                    GiftBagInfo.newBuilder()
                        .setGiftBagId(giftBag.giftId)
                        .setEndTime(giftBag.endTime)
                        .setCurLevel(giftBag.curLevel)
                        .setCurCount(giftBag.curCount)
                        .build()
                ).build()
            )

            // 存在满额礼包则增加满额礼包进度
            if (quotaBag.quotaBagId != 0 && !quotaBag.isOverdue()) {
                val oldDegree = quotaBag.degree
                drop.dropMap.forEach {
                    if (it.resType == RES_BIND_GOLD || it.resType == RES_GOLD) {
                        quotaBag.addDegree(it.num.toInt())
                    }
                }

                // 推送客户端满额礼包进度发生变动
                if (oldDegree != quotaBag.degree) {
                    session.sendMsg(
                        MsgType.ChangeQuotaBagDegree_3357,
                        ChangeQuotaBagDegree.newBuilder().setQuotaBagInfo(
                            QuotaBagInfo
                                .newBuilder()
                                .setQuotaBagId(quotaBag.quotaBagId)
                                .setEndTime(quotaBag.endTime)
                                .setRewardId(quotaBag.rewardId)
                                .setDegree(quotaBag.degree)
                                .build()
                        ).build()
                    )
                }

                // 满足满额赠礼条件发放奖励礼包
                val quotaBagProto = pcs.quotaBagProtoCache.quotaBagsMap[quotaBag.quotaBagId]
                if (quotaBagProto != null) {
                    if (quotaBag.degree >= quotaBagProto.giftNeed) {
                        quotaBag.finish()
                        val quotaBagDrop = pcs.dropBagCache.dropBagMap[quotaBag.rewardId]
                        if (quotaBagDrop != null) {
                            val messageParams = LinkedList<String>(
                                asList(
                                    "${quotaBagProto.quotaId}_${quotaBagProto.giftNeed}",
                                    quotaBagDrop.drop
                                )
                            )
                            val mailInfo =
                                MailInfo(TEXT_READ_LAN, quotaBagProto.mailTitle, LinkedList(), quotaBagProto.mailContent, messageParams)
                            mailHelper.sendMail(session, session.playerId, mailInfo, SYS_MAIL, LinkedList(quotaBagDrop.dropMap))
                            // 推送客户端满额礼包结束
                            session.sendMsg(
                                MsgType.FinishQuotaBag_3358,
                                FinishQuotaBag.newBuilder().setQuotaBagInfo(
                                    QuotaBagInfo
                                        .newBuilder()
                                        .setQuotaBagId(0)
                                        .build()
                                ).build()
                            )
                        }
                    }
                }
            }
        }

        // 购买成功发送邮件
        val messageParams = LinkedList<String>(asList("${giftBagProto.giftId}_${giftBagProto.giftLevel}", drop.drop))
        val mailInfo =
            MailInfo(TEXT_READ_LAN, giftBagProto.mailTitle, LinkedList(), giftBagProto.mailContent, messageParams)
        mailHelper.sendMail(session, session.playerId, mailInfo)

        // 处理触发满额礼包
        if (quotaBag.quotaBagId == 0 || quotaBag.isOverdue()) {
            val tmpKP = pcs.quotaBagProtoCache.quotaBagsByMoney[giftBagProto.money]
            if (tmpKP != null) {
                val factorDays = betweenDays(quotaBag.endTime)
                // 是否触发
                if (tmpKP.getFirstKey().isTrigger(factorDays)) {
                    // 如果触发则根据概率随即一个礼包
                    val triggerProto = tmpKP.randomKey(factorDays)
                    val rewardId = triggerProto.drops.randomKey()
                    val endTime = getNowTime() + ONE_HOUR_MILLS * triggerProto.giftTime
                    quotaBag.refresh(triggerProto.id, endTime, rewardId)
                    // 推送客户端触发满额礼包
                    session.sendMsg(
                        MsgType.TriggerQuotaBag_3355,
                        TriggerQuotaBag.newBuilder().setQuotaBagInfo(
                            QuotaBagInfo
                                .newBuilder()
                                .setQuotaBagId(triggerProto.id)
                                .setEndTime(endTime)
                                .setRewardId(rewardId)
                                .setDegree(0)
                                .build()
                        ).build()
                    )
                }
            }
        }

        // 如果玩家有联盟,则发放联盟礼物
        if (homePlayerDC.player.allianceId != 0L) {
            receiveAllianceGift(session, homePlayerDC.player.allianceId, giftBagProto.allianceDrops)
        }

        rt.orderId = hpm.generateObjIdNew()
        return rt
    }
}