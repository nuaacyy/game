package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.public.datacache.PublicCache

class TimeTaskDeleteExpireEventHandler : IHeartHandler<PublicCache> {

    override fun handleHeart(cache: PublicCache) {
        timeTaskDeleteExpireLogs(cache)
        timeTaskDeleteExpireTopics(cache)
    }
}
