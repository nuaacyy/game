package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldAskDealBase
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class FindAllPlayersDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCahce: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val internalMessage = req.findAllPlayerAskReq
        val rt = findAllPlayers(areaCahce, req.playerId, internalMessage)
        resp.setFindAllPlayerAskRt(rt)
    }

    private fun findAllPlayers(
        areaCache: AreaCache,
        playerId: Long,
        req: FindAllPlayerAskReq
    ): FindAllPlayerAskRt.Builder {
        val rtBuilder = FindAllPlayerAskRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val playerName = req.playerName

        val players = areaCache.playerCache.findAllPlayers()

        for (player in players) {
            val playerNameTemp1 = player.name.toLowerCase()
            val playerNameTemp2 = playerName.toLowerCase()
            if (playerNameTemp1.contains(playerNameTemp2) && player.id != playerId) {
                val castle = areaCache.castleCache.findMainCastleByPlayerId(player.id)
                var castleLv = 1
                if (castle != null) {
                    castleLv = castle.lv
                }
                val infoByHome = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(player.id)
                var vipLv = 1
                if (infoByHome != null) {
                    vipLv = infoByHome.vipLv
                }

                val matePlayerBuilder = MatePlayer.newBuilder()
                matePlayerBuilder.myPlayerId = player.id
                matePlayerBuilder.photoProtoId = player.photoProtoId
                matePlayerBuilder.name = player.name
                matePlayerBuilder.areaNo = player.areaNo
                matePlayerBuilder.vipLv = vipLv
                matePlayerBuilder.allianceShortName = player.allianceShortName
                matePlayerBuilder.castleLv = castleLv
                matePlayerBuilder.shortName = player.allianceNickName
                rtBuilder.addPlayers(matePlayerBuilder)
            }
        }
        return rtBuilder
    }
}
