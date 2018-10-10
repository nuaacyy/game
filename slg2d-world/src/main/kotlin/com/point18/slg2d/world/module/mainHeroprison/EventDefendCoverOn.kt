package com.point18.slg2d.world.module.mainHeroprison

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.CONTENT_LORD_BE_RELEASE
import com.point18.slg2d.common.constg.TEXT_READ_LAN
import com.point18.slg2d.common.constg.REMOVE_PRISON
import com.point18.slg2d.common.constg.TITLE_LORD_BE_RELEASE
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Prison
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import java.util.*
import java.util.Arrays.asList

class DefendCoverOnEventHandler : IEventHandler<AreaCache> {
    
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        // player 字段
        val player = cache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return
        }

        if (player.maxLvPrisonBuffEndTime != 0L) {
            cache.playerCache.updatePlayerMaxLvPrisonBuffEndTime(player, 0)

            // 被打破后 buff 没有了
            val prison = Prison(
                -1,
                0,
                -1,
                0,
                0,
                playerId
            ) // 这是一个buff，作为填充
            cache.playerCache.updatePlayerMaxLvPrisonBuffEndTime(player, 0)
            val session = fetchOnlinePlayerSession(cache, player.id)
            if (session != null) {
                val notifier =
                    createPlayerPrisonChangeNotifier(cache, REMOVE_PRISON, prison)
                if (notifier != null) {
                    notifier.notice(session)
                }
            }
        }

        // prison 全没 并且通知
        val prisons = cache.prisonCache.findPrisonsByPlayerId(playerId)
        val session = fetchOnlinePlayerSession(cache, player.id)
        prisons.forEach {
            cache.prisonCache.deletePrison(it)

            if (session != null) {
                val notifier =
                    createPlayerPrisonChangeNotifier(cache, REMOVE_PRISON, it)
                if (notifier != null) {
                    notifier.notice(session)
                }
            }

            val mailInfoToPrison = MailInfo(
                TEXT_READ_LAN,
                TITLE_LORD_BE_RELEASE,
                LinkedList(),
                CONTENT_LORD_BE_RELEASE,
                LinkedList(asList(player.name))
            )
            sendMail(cache, it.prisonPlayerId, mailInfoToPrison)
        }

        // 刷新这块地的信息
        val castle = cache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            return
        }
        noticeCellUpdate(cache, castle.x, castle.y)
    }
}