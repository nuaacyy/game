package com.point18.slg2d.home.module.askDeal.syncDeal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.SyncMsgDeal

class SyncMainHeroDeal : SyncMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealSync(session: PlayerActor, data: String): Int {
        return prepare(session) { homePlayerDC ->
            val mainHeroId = data.toLongOrNull()
            if (mainHeroId == null) {
                return@prepare ResultCode.PARAMETER_ERROR.code
            }

            val player = homePlayerDC.player
            player.mainHeroId = mainHeroId

            return@prepare ResultCode.SUCCESS.code
        }
    }
}