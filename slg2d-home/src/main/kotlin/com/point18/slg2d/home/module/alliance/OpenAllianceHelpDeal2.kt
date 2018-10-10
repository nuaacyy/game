package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.AllianceHelpVo
import pb4client.MyHelpVo
import pb4client.OpenAllianceHelpRt
import pb4server.Home2PublicAskResp
import pb4server.OpenAllianceHelpAskReq

//打开帮会帮助主界面

class OpenAllianceHelpDeal2 : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val openHelpRt = this.openAllianceHelp(session, homePlayerDC)
            if (openHelpRt != null) {
                session.sendMsg(MsgType.OpenAllianceHelp_1071, openHelpRt)
            }
        }
    }

    private fun openAllianceHelp(session: PlayerActor, homePlayerDC: HomePlayerDC): OpenAllianceHelpRt? {
        val rt = OpenAllianceHelpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.todayGetAllianceCoin = 0

        val player = homePlayerDC.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_QUERY_NOT_EXIST.code)
            return rt.build()
        }

        openHelp(session, player.allianceId, player)

        return null
    }

    // 打开联盟帮助界面
    private fun openHelp(session: PlayerActor, allianceId: Long, player: HomePlayer) {
        val askMsg = OpenAllianceHelpAskReq.newBuilder()

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setOpenAllianceHelpAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            val rt = OpenAllianceHelpRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            if (askErr != null || askResp == null) {
                // todo 重试...
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (askResp.openAllianceHelpAskRt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                rt.rt = askResp.openAllianceHelpAskRt.rt
            } else {
                rt.rt = askResp.openAllianceHelpAskRt.rt
                rt.todayGetAllianceCoin = player.allianceHelpTodayGet.toLong()

                for (info in askResp.openAllianceHelpAskRt.myHelpVoList) {
                    // 玩家自己
                    val myHelpVo = MyHelpVo.newBuilder()
                    myHelpVo.helpId = info.helpId
                    myHelpVo.helpType = info.helpType
                    myHelpVo.maxHelpNum = info.maxHelpNum
                    myHelpVo.nowHelpNum = info.nowHelpNum
                    myHelpVo.helpValue1 = info.helpValue1
                    myHelpVo.helpValue2 = info.helpValue2
                    myHelpVo.helpValue3 = info.helpValue3
                    myHelpVo.helpValue4 = info.helpValue4

                    rt.addMyHelpVo(myHelpVo)
                }

                for (info in askResp.openAllianceHelpAskRt.allianceHelpVosList) {
                    val allianceHelpVo = AllianceHelpVo.newBuilder()
                    allianceHelpVo.helpId = info.helpId
                    allianceHelpVo.helpType = info.helpType
                    allianceHelpVo.maxHelpNum = info.maxHelpNum
                    allianceHelpVo.nowHelpNum = info.nowHelpNum
                    allianceHelpVo.helpValue1 = info.helpValue1
                    allianceHelpVo.helpValue2 = info.helpValue2
                    allianceHelpVo.helpValue3 = info.helpValue3
                    allianceHelpVo.helpValue4 = info.helpValue4
                    allianceHelpVo.helpPlayerId = info.helpPlayerId
                    allianceHelpVo.helpPlayerName = info.helpPlayerName
                    allianceHelpVo.photoId = info.photoId

                    rt.addAllianceHelpVos(allianceHelpVo)
                }
            }

            session.sendMsg(MsgType.OpenAllianceHelp_1071, rt.build())
        }
    }

}


