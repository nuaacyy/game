package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.resetKing
import pb4server.Public2WorldTell

class ResetKingDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.changeKingTell
        val newKingId = tell.newKingId
        resetKing(areaCache, newKingId)
    }
}