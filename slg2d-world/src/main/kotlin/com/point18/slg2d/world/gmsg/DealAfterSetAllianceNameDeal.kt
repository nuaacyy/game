package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ALLIANCE_NAME_CHANGE
import com.point18.slg2d.common.constg.SET_ALLIANCE_NAME
import com.point18.slg2d.common.constg.SET_ALLIANCE_SHORT_NAME
import com.point18.slg2d.world.event.AllianceNameChange
import com.point18.slg2d.world.common.noticeCellUpdate
import pb4server.AllianceInfoChangeTell
import pb4server.Public2WorldTell

class DealAfterSetAllianceNameDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tellMsg = msg.dealAfterSetAllianceNameTell
        val allianceId = (tellMsg.allianceId)
        val setType = (tellMsg.setType)
        val name = (tellMsg.name)

        for (player in areaCache.playerCache.findPlayersByAllianceId(allianceId)) {
            when (setType) {
                SET_ALLIANCE_NAME -> player.allianceName = name
                SET_ALLIANCE_SHORT_NAME -> player.allianceShortName = name
            }
            val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
            if (castle == null) {
                normalLog.error("DealAfterSetAllianceNameDeal.kt 找不到玩家的主城${player.focusCastleId}")
                continue
            }

            // 同步home服数据
            val tell = AllianceInfoChangeTell.newBuilder()
            tell.allianceName = player.allianceName
            tell.allianceShortName = player.allianceShortName
            tell.flagColor = player.flagColor
            tell.flagStyle = player.flagStyle
            tell.flagEffect = player.flagEffect
            areaCache.tellHome(areaCache.fillW2HTellMsgHeader(player.id) {
                it.setAllianceInfoChangeTell(tell)
            })

            // 刷新主城
            noticeCellUpdate(areaCache, castle.x, castle.y)
            // 触发联盟改名事件
            wpm.es.fire(
                areaCache, player.id, ALLIANCE_NAME_CHANGE, AllianceNameChange(
                    allianceId,
                    setType,
                    name
                )
            )
        }
    }
}
