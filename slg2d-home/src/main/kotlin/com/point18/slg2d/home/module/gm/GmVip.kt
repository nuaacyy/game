package com.point18.slg2d.home.module.gm

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus2

class GmVip : GmCommand, HomeHelperPlus2<HomePlayerDC, VipDC>(HomePlayerDC::class.java, VipDC::class.java) {

    override fun exec(session: PlayerActor, message: String) {
        prepare(session) { homePlayerDC: HomePlayerDC, vipDc ->
            // 格式1： -gm add 类型 数量
            // 格式2： -gm changeCD building
            val messages = message.split(" ")
            if (messages.size != 4) {
                return@prepare
            }

            val timeStamp = messages[2].toLongOrNull()
            val onlineDay = messages[3].toIntOrNull()
            if (timeStamp == null) {
                return@prepare
            }
            if (onlineDay == null) {
                return@prepare
            }

            val player = homePlayerDC.player

            vipDc.vipInfo.lastGetVipRewardTime = vipDc.vipInfo.lastGetVipRewardTime - 24 * 3800 * 1000
            vipDc.vipInfo.lastRefreshEnergyTime = vipDc.vipInfo.lastRefreshEnergyTime - 24 * 3800 * 1000
            player.birthTime = player.birthTime - 24 * 3600 * 1000 * 2
            vipDc.vipInfo.continueOnlineDay = onlineDay
        }

    }

}
