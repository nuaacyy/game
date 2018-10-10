package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.getTotalPower
import com.point18.slg2d.world.common.findOfficeByPlayerId
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.PrisonInfo
import pb4server.QueryInfoByWorldAskRt

class QueryInfoByWorldDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.queryInfoByWorldAskReq
        val rt = dealQueryInfoByWorld(areaCache, askMsg.targetId)
        resp.setQueryInfoByWorldAskRt(rt)
    }

    private fun dealQueryInfoByWorld(
        areaCache: AreaCache,
        targetPlayerId: Long
    ): QueryInfoByWorldAskRt.Builder {
        val rt = QueryInfoByWorldAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val player = areaCache.playerCache.findPlayerById(targetPlayerId)
        if (player == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            return rt
        }

        val mainHero = areaCache.heroCache.findHeroById(targetPlayerId, player.mainHeroId)
        if (mainHero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt
        }

        val myTarget = areaCache.targetCache.findMyTargetByPlayerId(targetPlayerId)
        if (myTarget != null) {
            rt.fightValue = myTarget.getTotalPower()
            rt.killSoliderNum = myTarget.totalKill
        }
        rt.currentPos = findOfficeByPlayerId(areaCache, targetPlayerId)
        val mainHeroPrisonPlayerId = mainHero.mainHeroPrisonPlayerId

        val prisonInfoBuilder = PrisonInfo.newBuilder()
        if (mainHeroPrisonPlayerId != 0L) {
            val prisonPlayer = areaCache.playerCache.findPlayerById(mainHeroPrisonPlayerId)
            if (prisonPlayer == null) {
                rt.rt = ResultCode.NO_FIND_PRISON_PLAYER.code
                return rt
            }

            val prisonPlayerCastle = areaCache.castleCache.findCastleById(prisonPlayer.focusCastleId)
            if (prisonPlayerCastle == null) {
                rt.rt = ResultCode.CASTLE_DONT_EXISTED.code
                return rt
            }

            val prisonInfo = areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(
                mainHeroPrisonPlayerId,
                targetPlayerId
            )
            if (prisonInfo == null) {
                rt.rt = ResultCode.NO_FIND_PRISON_PLAYER.code
                return rt
            }

            prisonInfoBuilder.playerId = prisonPlayer.id
            prisonInfoBuilder.photoId = prisonPlayer.photoProtoId
            prisonInfoBuilder.allianceShortName = prisonPlayer.allianceShortName
            prisonInfoBuilder.playerName = prisonPlayer.name
            prisonInfoBuilder.x = prisonPlayerCastle.x
            prisonInfoBuilder.y = prisonPlayerCastle.y
            prisonInfoBuilder.areaNo = prisonPlayer.areaNo
            prisonInfoBuilder.ransom = prisonInfo.ransom
            prisonInfoBuilder.rewardGold = prisonInfo.rewardGold
        }

        rt.setPrisonInfo(prisonInfoBuilder)

        return rt
    }
}