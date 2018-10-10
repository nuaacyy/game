package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.public.datacache.PublicCache
import java.util.*

class AllianceRankClearHeartHandler : IHeartHandler<PublicCache> {

    override fun handleHeart(cache: PublicCache) {
        allianceRankClearHeart(cache)
    }

    /**
    联盟活跃度重置.
     */
    private fun allianceRankClearHeart(publicCache: PublicCache) {
        val cal = Calendar.getInstance()
        val nowWeekDay = cal.get(Calendar.DAY_OF_WEEK)
        val nowHour = cal.get(Calendar.HOUR_OF_DAY)
        val nowMin = cal.get(Calendar.MINUTE)
        val nowSec = cal.get(Calendar.SECOND)

        if (nowWeekDay == cal.get(Calendar.MONDAY) && nowHour == 0 && nowMin == 0 && nowSec == 0) {
            val allianceMemberTraces =
                publicCache.allianceMemberTraceCache.findAllianceMemberTracesByAllianceId(publicCache.publicActor.publicId)
            for (am in allianceMemberTraces) {
                am.weekHelp = 0
                am.weekKillMonster = 0
                am.weekTransportationValue = 0
                am.weekCureSolider = 0
                am.weekKillSolider = 0
                am.weekHonor = 0
                am.weekMonsterScore = 0
            }
        }
    }
}


