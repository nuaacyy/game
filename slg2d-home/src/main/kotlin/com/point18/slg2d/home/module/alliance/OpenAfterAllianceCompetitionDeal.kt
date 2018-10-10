package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.hpm
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.OpenAfterAllianceCompetitionRt
import pb4server.Home2PublicManagerAskResp
import pb4server.OpenAfterAllianceCompetitionAskReq

class OpenAfterAllianceCompetitionDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val rt = this.openAfterAllianceCompetition(session, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.OpenAfterAllianceCompetition_914, rt)
            }
        }
    }

    private fun openAfterAllianceCompetition(session: PlayerActor, homePlayerDC: HomePlayerDC): OpenAfterAllianceCompetitionRt? {
        val rt = OpenAfterAllianceCompetitionRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.beforeRankLv = 0
        rt.afterRankLv = 0
        rt.score = 0

        val player = homePlayerDC.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        if (player.allianceId != player.allianceCompetitionId) {
            rt.rt = ResultCode.ALLIANCE_COMPETITION_ALLIANCE_ERROR.code
            return rt.build()
        }

        if (player.allianceCompetitionTicket == NO_HAVE_ALLIANCE_COMPETITION_TICKET) {
            rt.rt = ResultCode.NO_JOIN_ALLIANCE_COMPETITION.code
            return rt.build()
        }

        openAfterCompetition(session, player.allianceId)

        return null
    }

    // 活动结束打开界面
    private fun openAfterCompetition(session: PlayerActor, allianceId: Long) {

        val askMsg = OpenAfterAllianceCompetitionAskReq.newBuilder()

        session.createACS<Home2PublicManagerAskResp>().ask(
            hpm.allianceManagerProxy,
            session.fillHome2PublicManagerAskMsgHeader{
                it.setOpenAfterAllianceCompetitionAskReq(askMsg)
            },
            Home2PublicManagerAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            val rt = OpenAfterAllianceCompetitionRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            if (askErr != null || askResp == null) {
                // todo 重试...
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (askResp.openAfterAllianceCompetitionAskRt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                rt.rt = askResp.openAfterAllianceCompetitionAskRt.rt
            } else {
                rt.rt = askResp.openAfterAllianceCompetitionAskRt.rt
                rt.beforeRankLv = askResp.openAfterAllianceCompetitionAskRt.beforeRankLv
                rt.afterRankLv = askResp.openAfterAllianceCompetitionAskRt.afterRankLv
                rt.score = askResp.openAfterAllianceCompetitionAskRt.score
            }

            session.sendMsg(MsgType.OpenAfterAllianceCompetition_914, rt.build())
        }
    }
}




