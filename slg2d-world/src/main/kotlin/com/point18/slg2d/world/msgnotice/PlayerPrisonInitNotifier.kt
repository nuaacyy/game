package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.VIRTUAL_BUFF
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.PlayerSession
import pb4client.PlayerPrisonInit
import pb4client.PrisonInfo

class PlayerPrisonInitNotifier(
    val msg: PlayerPrisonInit.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.PlayerPrisonInit_3301, this.msg.build())
    }
}

// 初始化客户端数据的推送
fun createPlayerPrisonInitNotifier(areaCache: AreaCache, player: Player): PlayerPrisonInitNotifier {
    val notifierBuilder = PlayerPrisonInit.newBuilder()

    notifierBuilder.prisonKillNum = player.prisonKillNum

    if (player.maxLvPrisonBuffEndTime > getNowTime()) {
        val p = PrisonInfo.newBuilder()

        p.playerId = -1
        p.heroProtoId = pcs.basicProtoCache.fakeLordHeroProtoId
        p.heroLv = pcs.basicProtoCache.fakeLordLevel
        p.heroState = VIRTUAL_BUFF
        p.heroStateStartTime = 0
        p.heroStateOverTime = (player.maxLvPrisonBuffEndTime / 1000).toInt()
        p.ransom = 0
        p.playerName = ""
        p.allianceShortName = ""
        p.iconId = 1

        notifierBuilder.addPrisonInfo(p)
    }

    val prisons = areaCache.prisonCache.findPrisonsByPlayerId(player.id)
    for (prison in prisons) {
        val prisonPlayer = areaCache.playerCache.findPlayerById(prison.prisonPlayerId) ?: continue
        val prisonMainHero = areaCache.heroCache.findHeroById(prison.prisonPlayerId, prisonPlayer.mainHeroId)
            ?: continue
        val p = PrisonInfo.newBuilder()

        p.playerId = prisonPlayer.id
        p.heroProtoId = prisonMainHero.protoId
        p.heroLv = prisonPlayer.kingLv
        p.heroState = prisonMainHero.mainHeroState
        p.heroStateStartTime = (prisonMainHero.mainHeroStateStartTime / 1000).toInt()
        p.heroStateOverTime = (prisonMainHero.mainHeroStateEndTime / 1000).toInt()
        p.ransom = prison.ransom
        p.playerName = prisonPlayer.name
        p.allianceShortName = prisonPlayer.allianceShortName
        p.iconId = prisonPlayer.photoProtoId

        notifierBuilder.addPrisonInfo(p)
    }

    return PlayerPrisonInitNotifier(notifierBuilder)
}
