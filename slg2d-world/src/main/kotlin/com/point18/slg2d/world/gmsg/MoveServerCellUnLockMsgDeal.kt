package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.World2WorldTellDealBase
import pb4server.World2WorldTell

// 解锁迁服锁定地块
class MoveServerCellUnLockMsgDeal : World2WorldTellDealBase() {
    override fun dealWorldTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: World2WorldTell) {
        val tellMsg = msg.moveServerCellUnLockTell
        dealMoveServerCellUnLock(areaCache, playerId, tellMsg.x, tellMsg.y)
    }

    private fun dealMoveServerCellUnLock(areaCache: AreaCache, playerId: Long, x: Int, y: Int) {
        val vo = areaCache.moveServerCellLockCache.findMoveServerCellLockByXy(x, y)
        if (vo != null) {
            areaCache.moveServerCellLockCache.deleteMoveServerCellLock(vo)
        }
    }
}