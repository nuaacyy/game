package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_ALLIANCE_IMPEACH
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.AllianceImpeachRt
import pb4server.AllianceImpeachAskReq
import pb4server.Home2PublicAskResp
import java.util.*
import java.util.Arrays.asList

// 弹劾盟主
class AllianceImpeachDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val rt = allianceImpeach(session, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.AllianceImpeach_899, rt)
            }
        }
    }

    private fun allianceImpeach(session: PlayerActor, homePlayerDC: HomePlayerDC): AllianceImpeachRt? {
        val rt = AllianceImpeachRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player

        // 验证资源
        val checkCost = resHelper.checkRes(session, pcs.basicProtoCache.impeachmentConsumptionCost)
        if (!checkCost) {
            rt.rt = (ResultCode.LESS_RESOUCE.code)
            return rt.build()
        }

        // 扣除消耗
        val costResWithoutNoticeResult = resHelper.costResWithoutNotice(
            session,
            ACTION_ALLIANCE_IMPEACH,
            player,
            pcs.basicProtoCache.impeachmentConsumptionCost
        )

        // 然后发送到公共服去检测是否有重复的
        impeachChief(
            session, player, player.allianceId, LinkedList(pcs.basicProtoCache.impeachmentConsumptionCost),
            resHelper, costResWithoutNoticeResult
        )

        return null
    }

    // 弹劾帮主
    private fun impeachChief(
        session: PlayerActor, player: HomePlayer, allianceId: Long, cost: LinkedList<ResVo>,
        resHelper: ResHelper, costResWithoutNoticeResult: CostResWithoutNoticeResult
    ) {
        val askMsg = AllianceImpeachAskReq.newBuilder()

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setAllianceImpeachAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            if (askErr != null || askResp == null) {
                // todo 重试...
                val rtMsgBuilder = AllianceImpeachRt.newBuilder()
                rtMsgBuilder.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
                resHelper.addResWithoutNotice(
                    session,
                    ACTION_ALLIANCE_IMPEACH,
                    player,
                    cost
                )
                session.sendMsg(MsgType.AllianceImpeach_899, rtMsgBuilder.build())
                return@whenCompleteKt
            }
            val rt = askResp.allianceImpeachAskRt
            val rtMsgBuilder = AllianceImpeachRt.newBuilder()
            rtMsgBuilder.rt = rt.rt
            if (rt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                resHelper.addResWithoutNotice(
                    session,
                    ACTION_ALLIANCE_IMPEACH,
                    player,
                    cost
                )
            } else {
                costResWithoutNoticeResult.noticeCostRes(session, player)
            }

            session.sendMsg(MsgType.AllianceImpeach_899, rtMsgBuilder.build())
        }
    }

}


