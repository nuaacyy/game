package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.public.datacache.AllianceWaijiao
import com.point18.slg2d.public.datacache.PublicCache

class FightInfoPastTimeHeartHandler : IHeartHandler<PublicCache> {

    override fun handleHeart(cache: PublicCache) {
        fightInfoPastTimeHeart(cache)
    }

    /**
    外交留言过期心跳.
     */
    private fun fightInfoPastTimeHeart(publicCache: PublicCache) {
        val deleteEasy = publicCache.allianceWaijiaoCache.findAllAllianceWaijiaoTimeOver()
        for (easy in deleteEasy) {
            delOvertimeFightReport(publicCache, easy)
        }
    }


    private fun delOvertimeFightReport(publicCache: PublicCache, easy: AllianceWaijiao) {
        publicCache.allianceWaijiaoCache.deleteAllianceWaijiaoById(easy)
    }

}


