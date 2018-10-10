package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.constg.CastleStateSync
import com.point18.slg2d.common.constg.PEACE
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.syncData2Home
import pb4server.FireFightAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp

class FireFightWorldDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val dealRt = FireFightAskRt.newBuilder()
        dealRt.rt = ResultCode.SUCCESS.code
        val playerId = req.playerId
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            dealRt.rt = ResultCode.NO_PLAYER.code
            resp.setFireFightAskRt(dealRt)
            return
        }

        val castles = areaCache.castleCache.findCastleFromPlayerId(playerId)
        if (castles.size != 1) {
            dealRt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            resp.setFireFightAskRt(dealRt)
            return
        }
        val castle = castles[0]

        // 灭火时间 = 0
        castle.castleState = PEACE
        syncData2Home(
            areaCache,
            player.id,
            CastleStateSync,
            castle.castleState.toString()
        )
        areaCache.castleCache.updateCastleStateEndTime(castle, 0)

        noticeCellUpdate(areaCache, castle.x, castle.x)

        resp.setFireFightAskRt(dealRt)
    }
}