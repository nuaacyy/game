package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.public.datacache.deleteAllianceLog
import java.util.*

// 定时任务：删除联盟日志
fun timeTaskDeleteExpireLogs(publicCache: PublicCache) {

    // 过期时间
    val expireAt = getNowTime() - pcs.basicProtoCache.allianceLogTimeLimit

    // 所有联盟的日志
    val aLogs = publicCache.allianceLogCache.findLogsByAllianceId(publicCache.publicActor.publicId)
    val delLogs = LinkedList<Long>()
    for ((_, aLog) in aLogs) {
        if (aLog.logTime < expireAt) {
            delLogs.add(aLog.id)
        }
    }

    for (d in delLogs) {
        deleteAllianceLog(aLogs, d)
    }
}
