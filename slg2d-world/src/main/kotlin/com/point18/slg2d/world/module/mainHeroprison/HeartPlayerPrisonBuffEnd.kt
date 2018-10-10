package com.point18.slg2d.world.module.mainHeroprison

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.common.constg.REMOVE_PRISON
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier

class PlayerPrisonBuffHeartHandler : IHeartHandler<AreaCache> {
    override fun handleHeart(cache: AreaCache) {
        var i = 0
        while (true) {
            val player = cache.playerCache.peekPlayerMaxLvPrisonBuffTimeFinish()
            if (player == null) {
                // 没有需要处理的
                return
            }

            // 弹出目标元素
            cache.playerCache.popPlayerMaxLvPrisonBuffTime()

            if (player.maxLvPrisonBuffEndTime == 0L) {
                // 什么都不需要做
                continue
            }

            maxLvPrisonBuffEnd(cache, player)

            i++
            if (i > 100000) {
                return
            }
        }
    }

    // buff 时间到了，把player的buff时间字段置0，把填充的prison记录删除
    private fun maxLvPrisonBuffEnd(areaCache: AreaCache, player: Player) {
        areaCache.playerCache.updatePlayerMaxLvPrisonBuffEndTime(player, 0)

        // 这是一个buff，作为填充
        val prison = Prison(
            -1,
            0,
            -1,
            0,
            0,
            player.id
        )

        val session = fetchOnlinePlayerSession(areaCache, player.id)
        if (session != null) {
            createPlayerPrisonChangeNotifier(areaCache, REMOVE_PRISON, prison)?.notice(session)
        }

        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            assert(false) { "关押人的主城找不到了" }
            return
        }
        noticeCellUpdate(areaCache, castle.x, castle.y)
    }
}
