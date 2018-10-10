package com.point18.slg2d.home.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_SCOUT
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.ResearchEffectWalkQueueAdd
import com.point18.slg2d.common.constg.ScoutResearchLv1
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.WalkScout
import pb4client.WalkScoutRt
import pb4server.EffectVo
import pb4server.Home2WorldAskResp
import pb4server.Walk4ScoutAskReq
import xyz.ariane.util.lzWarn
import java.util.Arrays.asList

class ScoutDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java, asList(resHelper, effectHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val reqMsg = msg as WalkScout
        prepare(session) { homePlayerDC: HomePlayerDC ->
            // 检查自己是否有可侦查的科技效果
            if (effectHelper.getResearchEffectValue(session, NO_FILTER, ScoutResearchLv1) == 0) {
                returnMsg(session, ResultCode.NO_SCOUT_RESEARCH.code)
                return@prepare
            }

            val res = pcs.basicProtoCache.reconConsume
            val checkRt = resHelper.checkRes(session, res)
            if (!checkRt) {
                returnMsg(session, ResultCode.LESS_RESOUCE.code)
                return@prepare
            }

            val player = homePlayerDC.player
            val action = ACTION_SCOUT
            val costRt = resHelper.costResWithoutNotice(session, action, player, res)

            val askMsg = Walk4ScoutAskReq.newBuilder()
            askMsg.aimX = reqMsg.aimsX
            askMsg.aimY = reqMsg.aimsY
            val effectBuilder = EffectVo.newBuilder()
            effectBuilder.effectId = ResearchEffectWalkQueueAdd
            effectBuilder.effectValue =
                    effectHelper.getResearchEffectValue(session, NO_FILTER, ResearchEffectWalkQueueAdd)
            askMsg.addEffectMap(effectBuilder)

            session.createACS<Home2WorldAskResp>().ask(
                session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader {
                    it.setWalk4ScoutAskReq(askMsg)
                },
                Home2WorldAskResp::class.java
            ).whenCompleteKt { askResp, askErr ->

                try {
                    when {
                        askErr != null -> {
                            normalLog.lzWarn { "请求world侦查错误:{$askErr}" }
                            resHelper.addResWithoutNotice(session, action, player, res)
                            returnMsg(session, ResultCode.ASK_ERROR1.code, 0)
                            return@whenCompleteKt
                        }

                        askResp == null -> {
                            normalLog.lzWarn { "请求world侦查错误:{$askErr}" }
                            resHelper.addResWithoutNotice(session, action, player, res)
                            returnMsg(session, ResultCode.ASK_ERROR2.code, 0)
                            return@whenCompleteKt
                        }

                        else -> {
                            val rt = askResp.walk4ScoutAskRt
                            if (rt.rt != ResultCode.SUCCESS.code) {
                                normalLog.lzWarn { "请求world侦查失败:{$askResp.rt}" }
                                resHelper.addResWithoutNotice(session, action, player, res)
                            } else {
                                costRt.noticeCostRes(session, player)
                            }
                            returnMsg(session, rt.rt, rt.groupId)
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("Walk4ScoutAskReq Error!", e)
                    returnMsg(session, ResultCode.ASK_ERROR3.code, 0)
                }
            }
        }
    }

    private fun returnMsg(session: PlayerActor, errorCode: Int, groupId: Long = 0) {
        val rt = WalkScoutRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.errorCode = errorCode
        rt.groupId = groupId
        session.sendMsg(MsgType.WalkScout_18, rt.build())
    }
}