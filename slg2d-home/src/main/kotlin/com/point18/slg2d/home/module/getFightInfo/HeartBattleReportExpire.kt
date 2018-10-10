package com.point18.slg2d.home.module.getFightInfo

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BattleReport
import com.point18.slg2d.home.dc.BattleReportDC
import java.util.*

//处理战报过期
fun dealBattleReportExpire(session: PlayerActor) {

    session.db.require(BattleReportDC::class.java).handleKt { reportDc ->
        val now = getNowTime()
        var toDelReportList: LinkedList<BattleReport>? = null
        for ((_, reportQueue) in reportDc.battleReportQueue) {
            var index = 0
            while (true) {
                if (++index > 100) {
                    break
                }
                val report = reportQueue.peek() ?: break
                if (report.pastTime > now) {
                    break
                }
                if (toDelReportList == null) {
                    toDelReportList = LinkedList()
                }
                toDelReportList.add(report)
            }
        }
        toDelReportList?.forEach {
            reportDc.deleteBattleReport(it)
        }
    }
}