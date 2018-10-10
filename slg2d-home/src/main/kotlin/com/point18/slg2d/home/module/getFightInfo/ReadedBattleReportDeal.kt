package com.point18.slg2d.home.module.getFightInfo

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.HasRead
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReportDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ReadedBattleReport
import pb4client.ReadedBattleReportRt
import java.util.*

// 阅读战报
class ReadedBattleReportDeal : HomeClientMsgDeal, HomeHelperPlus1<BattleReportDC>(BattleReportDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { battleReportDC: BattleReportDC ->
            val reportIds = LinkedList((msg as ReadedBattleReport).reportIdsList)
            val archivedRt = readedBattleReport(session, reportIds, battleReportDC)
            session.sendMsg(MsgType.ReadedBattleReport_132, archivedRt)
        }
    }

    private fun readedBattleReport(
        session: PlayerActor,
        reportIds: LinkedList<Long>,
        battleReportDC: BattleReportDC
    ): ReadedBattleReportRt {
        val rt = ReadedBattleReportRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        for (eachReportId in reportIds) {
            val battleReportWrap = battleReportDC.findBattleReportById(eachReportId) ?: continue

            // 设置战报收藏属性
            battleReportWrap.readState = HasRead
        }

        return rt.build()
    }

}