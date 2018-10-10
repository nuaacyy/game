package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.TRUE
import com.point18.slg2d.common.constg.UpdateOffice
import com.point18.slg2d.common.constg.allianceChannelOf
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.updateAllianceMemberInfo
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.askDeal.W2HTell
import pb4server.World2HomeTell

class HaveAllianceDeal : W2HTell, HomeHelperPlus2<HomePlayerDC, HomeSyncDC>(
    HomePlayerDC::class.java, HomeSyncDC::class.java
) {

    override fun dealWorldTell(session: PlayerActor, playerId: Long, msg: World2HomeTell) {
        val tell = msg.haveAllianceTell
        prepare(session) { homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC ->

            val allianceId = tell.allianceId
            val allianceName = tell.allianceName
            val allianceShortName = tell.allianceShortName
            val flagColor = tell.flagColor
            val flagStyle = tell.flagStyle
            val flagEffect = tell.flagEffect
            val player = homePlayerDC.player

            player.allianceAt = getNowTime()
            player.allianceId = allianceId
            player.joinAllianceState = 0
            player.allianceName = allianceName
            player.allianceShortName = allianceShortName
            player.flagColor = flagColor
            player.flagStyle = flagStyle
            player.flagEffect = flagEffect

            // 订阅联盟频道
            session.subscribeChannel(allianceChannelOf(tell.allianceId))

            player.resetWrapPosition()

            if (tell.isFirst == TRUE) {
                player.isFirstJoinAlliance = 1
            }
            player.allianceResearchNum = 0
            player.lastAllianceResearchTime = getNowTime()

            // 同步官职到Pub
            if (player.allianceId != 0L) {
                val updateInfoMap = hashMapOf<Int, String>()
                updateInfoMap[UpdateOffice] = toJson(homeSyncDC.syncData.officeMap.toMap())
                updateAllianceMemberInfo(session, player.allianceId, player.playerId, updateInfoMap)
            }
        }
    }
}