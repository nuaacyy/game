package com.point18.slg2d.home.module.guideline

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.GUIDE_TYPE_SEQ
import com.point18.slg2d.common.constg.GUIDE_TYPE_TRIGGER
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GuideFin
import pb4client.GuideFinRt

class FinishGuidelineDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val type = (msg as GuideFin).type
            val guidelineId = msg.guideId

            val guideFinRt = finishOneGuideline(session, type, guidelineId, homePlayerDC)
            session.sendMsg(MsgType.GuideFin_119, guideFinRt)
        }
    }

    private fun finishOneGuideline(
        session: PlayerActor, type: Int, guidelineId: Int, homePlayerDC: HomePlayerDC
    ): GuideFinRt {
        val player = homePlayerDC.player
        val guideFinRt = GuideFinRt.newBuilder()
        guideFinRt.rt = ResultCode.SUCCESS.code
        guideFinRt.type = type
        guideFinRt.guideId = guidelineId
        if (type == GUIDE_TYPE_SEQ) {
            val proto = pcs.mandatoryGuideProtoCache.mandatoryGuideMap[guidelineId]
            if (proto == null) {
                guideFinRt.rt = ResultCode.NO_PROTO.code
            }

            player.guideStep = guidelineId
        } else if (type == GUIDE_TYPE_TRIGGER) {
            val proto = pcs.triggerGuideProtoCache.guideMap[guidelineId]
            if (proto == null) {
                guideFinRt.rt = ResultCode.NO_PROTO.code
            }

            player.haveFinishGuideLine.add(guidelineId)
        } else {
            guideFinRt.rt = ResultCode.PARAMETER_ERROR.code
        }
        return guideFinRt.build()
    }
}