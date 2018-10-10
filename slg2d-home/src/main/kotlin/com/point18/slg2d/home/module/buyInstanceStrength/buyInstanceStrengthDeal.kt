package com.point18.slg2d.home.module.buyInstanceStrength

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BuyInstanceStrengthRt
import pb4server.AddInstanceStrengthAskReq
import pb4server.Home2WorldAskResp
import java.util.Arrays.asList

// 购买体力
class BuyInstanceStrengthDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper, effectHelper)
) {
    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val rt = buyInstanceStrength(session, homePlayerDC)

            if (rt != null) {
                session.sendMsg(MsgType.BuyInstanceStrength_1475, rt)
            }
        }
    }

    private fun buyInstanceStrength(session: PlayerActor, homePlayerDC: HomePlayerDC): BuyInstanceStrengthRt? {
        val rtBuilder = BuyInstanceStrengthRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        // 验证一些模版基础数据

        val player = homePlayerDC.player

        if (isAfterGameRefTime(player.lastBuyStrengthTime)) {
            player.buyStrengthNum = 0
            player.lastBuyStrengthTime = getNowTime()
        }

        // 验证次数
        val maxBuyNum = effectHelper.getResearchEffectValue(session, NO_FILTER, InstanceBuyNum)

        if (player.buyStrengthNum >= maxBuyNum) {
            rtBuilder.rt = ResultCode.INSTANCE_STENGTH_BUY_NUM_ERROR.code
            return rtBuilder.build()
        }

        // 开始购买 验证资源
        val needCost = pcs.diamondConsumeCache.getBuyDecreeByTimes(player.buyStrengthNum + 1)
        val needCostResVo = ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, needCost.toLong())

        val checkCost = resHelper.checkRes(session, needCostResVo)
        if (!checkCost) {
            rtBuilder.rt = ResultCode.LESS_RESOUCE.code
            return rtBuilder.build()
        }

        // 扣除资源
        val action = ACTION_USE_BUY_JJC_COUNT
        val costRt = resHelper.costResWithoutNotice(session, action, player, needCostResVo)

        // 数据修改
        player.buyStrengthNum += 1

        val askMsg = AddInstanceStrengthAskReq.newBuilder()
        askMsg.addValue = pcs.basicProtoCache.firstInstancePower
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setAddInstanceStrengthAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            try {
                when {
                    askErr != null -> {
                        resHelper.addResWithoutNotice(session, action, player, needCostResVo)
                        val rt = BuyInstanceStrengthRt.newBuilder()
                        rt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.BuyInstanceStrength_1475, rt.build())
                    }
                    askResp == null -> {
                        resHelper.addResWithoutNotice(session, action, player, needCostResVo)
                        val rt = BuyInstanceStrengthRt.newBuilder()
                        rt.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.BuyInstanceStrength_1475, rt.build())
                    }
                    else -> {
                        val rt = BuyInstanceStrengthRt.newBuilder()
                        rt.rt = askResp.addInstanceStrengthAskRt.rt
                        if (rt.rt != ResultCode.SUCCESS.code) {
                            resHelper.addResWithoutNotice(session, action, player, needCostResVo)
                        } else {
                            costRt.noticeCostRes(session, player)
                        }
                        session.sendMsg(MsgType.BuyInstanceStrength_1475, rt.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("AddInstanceStrengthAskReq Error!", e)
                resHelper.addResWithoutNotice(session, action, player, needCostResVo)
                val rt = BuyInstanceStrengthRt.newBuilder()
                rt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.BuyInstanceStrength_1475, rt.build())
            }
        }

        return null
    }
}
