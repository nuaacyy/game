package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldAskDealBase
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.SetMainHeroRewardAskRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.noticeCellUpdate

class AddMainHeroRewardDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder)  {
        val goldNumAdd = req.setMainHeroRewardAskReq.goldNumAdd
        val rt = SetMainHeroRewardAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(req.playerId)
        if (player == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setSetMainHeroRewardAskRt(rt)
            return
        }

        val hero = areaCache.heroCache.findHeroById(player.id, player.mainHeroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            resp.setSetMainHeroRewardAskRt(rt)
            return
        }

        if (hero.mainHeroPrisonPlayerId == 0L) {
            rt.rt = ResultCode.NO_FIND_PRISON_PLAYER.code
            resp.setSetMainHeroRewardAskRt(rt)
            return
        }

        val prisonInfo =
            areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(hero.mainHeroPrisonPlayerId, req.playerId)
        if (prisonInfo == null) {
            rt.rt = ResultCode.NO_FIND_PRISON_PLAYER.code
            resp.setSetMainHeroRewardAskRt(rt)
            return
        }

        val mainHeroPrisonPlayer = areaCache.playerCache.findPlayerById(hero.mainHeroPrisonPlayerId)
        if (mainHeroPrisonPlayer == null){
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setSetMainHeroRewardAskRt(rt)
            return
        }

        val castle = areaCache.castleCache.findCastleById(mainHeroPrisonPlayer.focusCastleId)
        if (castle == null) {
            rt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            resp.setSetMainHeroRewardAskRt(rt)
            return
        }

        val newRewardGold  = prisonInfo.rewardGold + goldNumAdd
        if (newRewardGold < pcs.basicProtoCache.commissionMin ||
            newRewardGold > pcs.basicProtoCache.commissionMax ) {
            rt.rt = ResultCode.RANSOM_ERROR.code
            resp.setSetMainHeroRewardAskRt(rt)
            return
        }

        prisonInfo.rewardGold = newRewardGold

        noticeCellUpdate(areaCache, castle.x, castle.y)

        resp.setSetMainHeroRewardAskRt(rt)
        return
    }
}