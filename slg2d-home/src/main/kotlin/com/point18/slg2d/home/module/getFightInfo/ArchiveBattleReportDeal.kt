package com.point18.slg2d.home.module.getFightInfo

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReportDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ArchiveBattleReport
import pb4client.ArchiveBattleReportRt
import java.util.*

// 收藏战报
class ArchiveBattleReportDeal : HomeClientMsgDeal, HomeHelperPlus1<BattleReportDC>(BattleReportDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { battleReportDC: BattleReportDC ->
            val reportIds = LinkedList((msg as ArchiveBattleReport).reportIdsList)
            val archivedRt = archiveBattleReport(session, reportIds, battleReportDC)
            session.sendMsg(MsgType.ArchiveBattleReport_131, archivedRt)
        }
    }

    private fun archiveBattleReport(
        session: PlayerActor,
        reportIds: LinkedList<Long>,
        battleReportDC: BattleReportDC
    ): ArchiveBattleReportRt {
        val rt = ArchiveBattleReportRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val nowTime = getNowTime()

        for (eachReportId in reportIds) {
            val battleReportWrap = battleReportDC.findBattleReportById(eachReportId) ?: continue

            // 设置战报收藏属性
            battleReportWrap.archived = nowTime
        }

        return rt.build()
    }

}
