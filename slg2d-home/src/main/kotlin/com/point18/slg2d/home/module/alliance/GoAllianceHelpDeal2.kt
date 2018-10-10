package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GoAllianceHelpEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.AllianceHelpVo
import pb4client.GoAllianceHelp
import pb4client.GoAllianceHelpRt
import pb4server.GoAllianceHelpAskReq
import pb4server.Home2PublicAskResp
import java.util.*
import java.util.Arrays.asList

// 帮助
class GoAllianceHelpDeal2(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper, targetHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val helps = LinkedList((msg as GoAllianceHelp).allianceHelpVosList)
            val goHelpRt = this.goAllianceHelp(session, homePlayerDC, helps)

            if (goHelpRt != null) {
                session.sendMsg(MsgType.GoAllianceHelp_1073, goHelpRt)
            }
        }
    }

    private fun goAllianceHelp(
        session: PlayerActor, homePlayerDC: HomePlayerDC, helps: LinkedList<AllianceHelpVo>
    ): GoAllianceHelpRt? {
        val rt = GoAllianceHelpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val player = homePlayerDC.player
        val helpIds = LinkedList<Long>()

        for (h in helps) {
            helpIds.add(h.helpId)
        }


        helpMember(session, player.allianceId, player, helpIds, resHelper, targetHelper)
        return null
    }

    // 帮助玩家
    private fun helpMember(
        session: PlayerActor,
        allianceId: Long,
        player: HomePlayer,
        helpIds: LinkedList<Long>,
        resHelper: ResHelper,
        targetHelper: TargetHelper
    ) {

        val askMsg = GoAllianceHelpAskReq.newBuilder()
        askMsg.addAllHelpIds(helpIds)

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setGoAllianceHelpAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            val rt = GoAllianceHelpRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            if (askErr != null || askResp == null) {
                // todo 重试...
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (askResp.goAllianceHelpAskRt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                rt.rt = askResp.goAllianceHelpAskRt.rt
            } else {
                if (isAfterGameRefTime(player.lastAllianceHelpTime)) {
                    player.allianceHelpTodayGet = 0
                }
                if (player.allianceHelpTodayGet < pcs.basicProtoCache.helpRewordLimit) {
                    var canGet = helpIds.size * pcs.basicProtoCache.helpTimesReword
                    if (player.allianceHelpTodayGet + canGet > pcs.basicProtoCache.helpRewordLimit) {
                        canGet = pcs.basicProtoCache.helpRewordLimit - player.allianceHelpTodayGet
                    }

                    if (intToBool(askResp.goAllianceHelpAskRt.helpSuccess)) {
                        val addR = LinkedList<ResVo>()
                        addR += ResVo(RES_ALLIANCE_COIN, NOT_PROPS_SUB_TYPE, canGet.toLong())
                        resHelper.addRes(session, ACTION_INTERIOR_TASK, player, addR)
                        player.allianceHelpTodayGet += canGet
                        player.lastAllianceHelpTime = getNowTime()

                        fireEvent(session, GoAllianceHelpEvent(helpIds.size))
                        targetHelper.targetAddVal(session, TotalHelpAlly, LinkedList(Arrays.asList(helpIds.size)))
                    }
                }
            }

            session.sendMsg(MsgType.GoAllianceHelp_1073, rt.build())
        }
    }
}



