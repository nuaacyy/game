package com.point18.slg2d.home.module.getFightInfo

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReportDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.DelBattleReport
import pb4client.DelBattleReportRt
import java.util.*

// 请求简单战报
class DelBattleReportDeal : HomeClientMsgDeal, HomeHelperPlus1<BattleReportDC>(BattleReportDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { battleReportDC: BattleReportDC ->
            val reportIds = LinkedList((msg as DelBattleReport).reportIdsList)
            val delRt = delBattleReport(session, reportIds, battleReportDC)
            session.sendMsg(MsgType.DelBattleReport_130, delRt)
        }
    }

    private fun delBattleReport(
        session: PlayerActor,
        reportIds: LinkedList<Long>,
        battleReportDC: BattleReportDC
    ): DelBattleReportRt {
        val rt = DelBattleReportRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        for (eachReportId in reportIds) {
            val battleReportWrap = battleReportDC.findBattleReportById(eachReportId) ?: continue

            battleReportDC.deleteBattleReport(battleReportWrap)
        }

        return rt.build()
    }

}
