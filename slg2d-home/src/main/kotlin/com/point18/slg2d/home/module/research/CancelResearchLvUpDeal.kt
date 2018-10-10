package com.point18.slg2d.home.module.research

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.removeAllianceHelp
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createResearchChangeNotifier
import pb4client.CancelResearchLvUp
import pb4client.CancelResearchLvUpRt
import java.util.*

// 取消科技研发
class CancelResearchDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
            val researchId = (msg as CancelResearchLvUp).researchId
            val researchRt = cancelResearchLvUp(session, researchId, homePlayerDC)
            session.sendMsg(MsgType.CancelResearchLvUp_1052, researchRt)
        }
    }

    private fun cancelResearchLvUp(session: PlayerActor, researchId: Int, homePlayerDC: HomePlayerDC): CancelResearchLvUpRt {
        val rt = pb4client.CancelResearchLvUpRt.newBuilder()
        rt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code

        val player = homePlayerDC.player
        val playerResearchInfo = player.researchInfoMap

        val info = playerResearchInfo[researchId]

        if (info == null) {
            rt.rt = (ResultCode.CANCEL_RESEARCH_NO_IN_ERROR.code)
            return rt.build()
        }

        if (info.researchOverTime == 0L) {
            rt.rt = (ResultCode.CANCEL_RESEARCH_NO_IN_ERROR.code)
            return rt.build()
        }

        val researchProto0 = pcs.researchCache.researchProtoMapByLv[researchId]
        if (researchProto0 == null) {
            rt.rt = (ResultCode.NO_PROTO.code)
            return rt.build()
        }
        val researchProto = researchProto0[info.researchLv + 1]
        if (researchProto == null) {
            rt.rt = (ResultCode.NO_PROTO.code)
            return rt.build()
        }

        // 扣除资源
        resHelper.addRes(session, com.point18.slg2d.common.constg.ACTION_CANCEL_RESEARCH_LV_UP, player, researchProto.cancelResourcesMap)


        if (info.helpId != 0.toLong()) {
            // 如果在帮助列表中的,要做处理
            removeAllianceHelp(session, player.allianceId, info.helpId)
        }

        // 修改数据
        player.researchUpdate(
            session,
            researchId,
            info,
            0L,
            0L
        )
        info.helperIds = hashMapOf()
        info.helpId = 0
        player.putResearchInfoMap(researchProto.researchID, info)

        // 推送给客户端变更
        val notice = createResearchChangeNotifier(researchId, info.researchLv, info.researchOverTime, info.helpId)
        notice.notice(session)

        return rt.build()
    }

}
