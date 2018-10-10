package com.point18.slg2d.world.module.bigMap

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowMTime
import com.point18.slg2d.common.constg.CASTLE_FIRE
import com.point18.slg2d.common.constg.CASTLE_SMOKE
import com.point18.slg2d.common.constg.CastleStateSync
import com.point18.slg2d.common.constg.PEACE
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Castle
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.syncData2Home

class CastleStateChangeHeartHandler : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        castleStateChange(cache)
    }

    // 城堡状态心跳
    private fun castleStateChange(areaCache: AreaCache) {
        var i = 0
        while (true) {
            // 保险丝
            i++
            if (i >= 10000) {
                break
            }

            val castle = areaCache.castleCache.peekCastleStateEndTimeFinish()
            if (castle == null) {
                // 没有需要处理的城堡
                return
            }

            // 弹出目标元素
            areaCache.castleCache.popCastleStateEndTime()
            if (castle.castleStatusEndTime == 0L) {
                // 什么都不需要做
                continue
            }

            // 处理这个castle
            dealCastleStateChange(areaCache, castle)

        }
    }

    private fun dealCastleStateChange(areaCache: AreaCache, castle: Castle) {
        val now = getNowMTime()

        // 冒火结束
        if (castle.castleState == CASTLE_FIRE && castle.castleStatusEndTime <= now) {
            castle.castleState = PEACE
            syncData2Home(
                areaCache,
                castle.playerId,
                CastleStateSync,
                castle.castleState.toString()
            )
            // 地图推送
            noticeCellUpdate(areaCache, castle.x, castle.x)
        }

        // 冒烟结束
        if (castle.castleState == CASTLE_SMOKE && castle.castleStatusEndTime <= now) {
            castle.castleState = PEACE
            syncData2Home(
                areaCache,
                castle.playerId,
                CastleStateSync,
                castle.castleState.toString()
            )
            areaCache.castleCache.updateCastleStateEndTime(castle, 0)

            // 地图推送
            noticeCellUpdate(areaCache, castle.x, castle.x)
        }

    }
}

