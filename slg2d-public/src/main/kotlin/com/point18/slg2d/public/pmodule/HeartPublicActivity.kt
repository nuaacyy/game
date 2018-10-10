package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.common.constg.ALLIANCE_ACTIVITY_GOIN
import com.point18.slg2d.common.constg.ALLIANCE_COMPETITION_ACTIVITY
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.public.datacache.PublicManagerCache
import com.point18.slg2d.public.ppm
import pb4server.AllianceSimpleInfoChangeTell
import xyz.ariane.util.tellNoSender
import java.util.*

class DealPublicActivityHeartHandler : IHeartHandler<PublicManagerCache> {

    override fun handleHeart(cache: PublicManagerCache) {
        dealPublicActivity(cache)
    }

    private fun dealPublicActivity(publicCache: PublicManagerCache) {
        val cal = Calendar.getInstance()
        val nowDay = cal.get(Calendar.DAY_OF_MONTH)
        val nowHour = cal.get(Calendar.HOUR_OF_DAY)
        val nowMin = cal.get(Calendar.MINUTE)
        val nowSec = cal.get(Calendar.SECOND)

        val allianceActivity = publicCache.publicActivityManagerCache.findPublicActivityByActivityId(
            ALLIANCE_COMPETITION_ACTIVITY
        )
        if (allianceActivity == null) {
            if (nowDay == pcs.basicProtoCache.allianceCompetitionOpenTime[0] &&
                nowHour == pcs.basicProtoCache.allianceCompetitionOpenTime[1] &&
                nowMin == pcs.basicProtoCache.allianceCompetitionOpenTime[2] &&
                nowSec == pcs.basicProtoCache.allianceCompetitionOpenTime[3]
            ) {

                // 联盟总动员的开启
                allianceCompetitionStart(publicCache)

                // 并且在控制时间的表里插入一行
                val rewardTime = getNowTime() + pcs.basicProtoCache.allianceCompetitionContinuedTime * 1000
                val overTime = rewardTime + pcs.basicProtoCache.allianceCompetitionAwardTime * 1000
                publicCache.publicActivityManagerCache.createPublicActivity(
                    ALLIANCE_COMPETITION_ACTIVITY,
                    ALLIANCE_ACTIVITY_GOIN,
                    rewardTime,
                    overTime
                )

                val allianceSimpleInfoChangeTell = AllianceSimpleInfoChangeTell.newBuilder()
                allianceSimpleInfoChangeTell.addAllAllianceRankInfo(makeAllianceRank(publicCache))
                ppm.p2wSyncInfoRouter.tellNoSender(
                    ppm.fillPublicManager2WorldManagerTellMsgHeader {
                        it.setAllianceSimpleInfoChangeTell(
                            allianceSimpleInfoChangeTell
                        )
                    }
                )
            }
        } else {
            // 要把这个写在前面
            if (getNowTime() > allianceActivity.overTime) {
                allianceCompetitionClose(publicCache)
                publicCache.publicActivityManagerCache.deletePublicActivity(allianceActivity)

                val allianceSimpleInfoChangeTell = AllianceSimpleInfoChangeTell.newBuilder()
                allianceSimpleInfoChangeTell.addAllAllianceRankInfo(makeAllianceRank(publicCache))
                ppm.p2wSyncInfoRouter.tellNoSender(
                    ppm.fillPublicManager2WorldManagerTellMsgHeader {
                        it.setAllianceSimpleInfoChangeTell(
                            allianceSimpleInfoChangeTell
                        )
                    }
                )

            } else if (getNowTime() > allianceActivity.rewardTime) {
                // 活动进入领奖时间
                allianceCompetitionOver(publicCache)
                allianceActivity.nowState = com.point18.slg2d.common.constg.ALLIANCE_ACTIVITY_REWARD
            }
        }
    }

}

