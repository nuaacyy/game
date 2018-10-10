package com.point18.slg2d.home.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.TransportResource
import pb4client.TransportResourceRt
import pb4server.EffectVo
import pb4server.Home2WorldAskResp
import pb4server.TransportResAskReq
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.Arrays.asList

class TransportResDeal(
    val resHelper: ResHelper = ResHelper(),
    val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(resHelper, effectHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val reqMsg = msg as TransportResource
        val targetPlayerId = reqMsg.tarPlayerId
        val res = resStringToResVoList(reqMsg.res)

        prepare(session) { homePlayerDC: HomePlayerDC ->
            if (res == null || res.isEmpty()) {
                returnMsg(session, ResultCode.PARAMETER_ERROR.code)
                return@prepare
            }
            var totalResNum = 0L
            res.forEach {
                when (it.resType) {
                    RES_COIN,
                    RES_FOOD,
                    RES_WOOD,
                    RES_STONE,
                    RES_IRON -> {
                        totalResNum += it.num
                    }
                    else -> {
                        returnMsg(session, ResultCode.PARAMETER_ERROR.code)
                        return@forEach
                    }
                }
            }
            if (totalResNum <= 0) {
                returnMsg(session, ResultCode.PARAMETER_ERROR.code)
                return@prepare
            }

            val checkRt = resHelper.checkRes(session, res)
            if (!checkRt) {
                returnMsg(session, ResultCode.LESS_RESOUCE.code)
                return@prepare
            }

            val player = homePlayerDC.player
            val action = ACTION_TRANSPORT
            val costRt = resHelper.costResWithoutNotice(session, action, player, res)

            val transportMax = effectHelper.getResearchEffectValue(session, NO_FILTER, TransportMaxAdd)
            val transportTax = effectHelper.getResearchEffectValue(session, NO_FILTER, TransporTaxChange)
            if (transportMax * (10000L + transportTax) / 10000 < totalResNum) {
                returnMsg(session, ResultCode.TransportResUpLimit.code)
                return@prepare
            }

            val realRes = LinkedList<ResVo>()
            val rate = (10000 + transportTax) / 10000.0
            res.forEach { resVo ->
                if (resVo.num <= 0) {
                    return@forEach
                }
                val resNum = (resVo.num / rate).toLong()
                if (resNum <= 0) {
                    return@forEach
                }
                realRes += ResVo(resVo.resType, NOT_PROPS_SUB_TYPE, resNum)
            }
            if (realRes.isEmpty()) {
                returnMsg(session, ResultCode.PARAMETER_ERROR.code)
                return@prepare
            }

            askTransport(targetPlayerId, realRes, session, action, player, res, costRt)
        }
    }

    private fun askTransport(
        targetPlayerId: Long,
        realRes: LinkedList<ResVo>,
        session: PlayerActor,
        action: Int,
        player: HomePlayer,
        res: List<ResVo>,
        costRt: CostResWithoutNoticeResult
    ) {
        val askMsg = TransportResAskReq.newBuilder()
        askMsg.targetPlayerId = targetPlayerId
        askMsg.res = toJson(realRes)
        val effectVoBuilder = EffectVo.newBuilder()
        effectVoBuilder.effectId = ResearchEffectWalkQueueAdd
        effectVoBuilder.effectValue =
                effectHelper.getResearchEffectValue(session, NO_FILTER, ResearchEffectWalkQueueAdd)
        askMsg.addEffectMap(effectVoBuilder)
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { builder -> builder.setTransportResAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->

            try {
                when {
                    askErr != null -> {
                        normalLog.lzWarn { "请求world运输资源失败:{$askErr}" }
                        resHelper.addResWithoutNotice(session, action, player, res)
                        returnMsg(session, ResultCode.ASK_ERROR1.code, 0)
                        return@whenCompleteKt
                    }

                    askResp == null -> {
                        normalLog.lzWarn { "请求world运输资源失败:{$askErr}" }
                        resHelper.addResWithoutNotice(session, action, player, res)
                        returnMsg(session, ResultCode.ASK_ERROR2.code, 0)
                        return@whenCompleteKt
                    }

                    else -> {
                        if (askResp.transportResAskRt.rt != ResultCode.SUCCESS.code) {
                            normalLog.lzWarn { "请求world运输资源失败:{$askResp.rt}" }
                            resHelper.addResWithoutNotice(session, action, player, res)
                        } else {
                            costRt.noticeCostRes(session, player)
                        }
                        returnMsg(session, askResp.transportResAskRt.rt, askResp.transportResAskRt.groupId)
                    }
                }

            } catch (e: Exception) {
                normalLog.error("TransportResAskReq Error!", e)
                returnMsg(session, ResultCode.ASK_ERROR3.code, 0)
            }

        }
    }

    private fun returnMsg(session: PlayerActor, errorCode: Int, groupId: Long = 0) {
        val rt = TransportResourceRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.errorCode = errorCode
        rt.groupId = groupId
        session.sendMsg(MsgType.TransportResource_16, rt.build())
    }
}