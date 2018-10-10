package com.point18.slg2d.home.module.casino

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.CASINO_ID
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GetJackpotInfoRt
import pb4server.GetCasinoAskReq
import pb4server.Home2PublicAskResp

// 奖池信息
class GetJackpotInfoDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) {
            val queryCasinoInfoRt = queryCasinoInfo(session)
            if (queryCasinoInfoRt != null) {
                session.sendMsg(MsgType.GetJackpotInfo_1578, queryCasinoInfoRt)
            }
        }
    }

    private fun queryCasinoInfo(session: PlayerActor): GetJackpotInfoRt? {
        val rt = GetJackpotInfoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val askMsg = GetCasinoAskReq.newBuilder()

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(CASINO_ID) { it.setGetCasinoAskReq(askMsg) },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            if (askErr != null || askResp == null) {
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (askResp.getCasinoAskRt.rt != ResultCode.SUCCESS.code) {
                rt.rt = askResp.getCasinoAskRt.rt
            } else {
                rt.rt = askResp.getCasinoAskRt.rt

                val casinosWinners = askResp.getCasinoAskRt.casinosWinnerList
                rt.addAllCasinosWinner(casinosWinners)
                rt.totalMoney = askResp.getCasinoAskRt.totalMoney

                session.sendMsg(MsgType.GetJackpotInfo_1578, rt.build())
            }
        }

        return null
    }
}