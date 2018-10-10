package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.Prison
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.VIRTUAL_BUFF
import com.point18.slg2d.common.pc.pcs
import pb4client.PlayerPrisonChange
import pb4client.PrisonInfo

// 玩家监狱信息变化
class PlayerPrisonChangeNotifier(
    val msg: PlayerPrisonChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.PlayerPrisonChange_3162, this.msg.build())
    }
}

fun createPlayerPrisonChangeNotifier(
    areaCache: AreaCache,
    changeType: Int,
    prison: Prison
): PlayerPrisonChangeNotifier? {

    val player = areaCache.playerCache.findPlayerById(prison.playerId)
    if (player == null) {
        normalLog.error("找不到监禁玩家信息：%d", prison.playerId)
        return null
    }

    if (prison.prisonPlayerId <= 0) {
        val playerPrisonChangeBuilder = PlayerPrisonChange.newBuilder()
        playerPrisonChangeBuilder.changeType = changeType
        val prisonInfoBuilder = PrisonInfo.newBuilder()
        prisonInfoBuilder.playerId = -1
        prisonInfoBuilder.heroProtoId = pcs.basicProtoCache.fakeLordHeroProtoId
        prisonInfoBuilder.heroLv = pcs.basicProtoCache.fakeLordLevel
        prisonInfoBuilder.heroState = VIRTUAL_BUFF
        prisonInfoBuilder.heroStateStartTime = 0
        prisonInfoBuilder.heroStateOverTime = (player.maxLvPrisonBuffEndTime / 1000).toInt()
        prisonInfoBuilder.ransom = 0
        prisonInfoBuilder.playerName = ""
        prisonInfoBuilder.allianceShortName = ""
        prisonInfoBuilder.iconId = player.photoProtoId
        playerPrisonChangeBuilder.setPrisonInfo(prisonInfoBuilder)
        return PlayerPrisonChangeNotifier(playerPrisonChangeBuilder)
    }

    val prisonPlayer = areaCache.playerCache.findPlayerById(prison.prisonPlayerId)
    if (prisonPlayer == null) {
        normalLog.error("找不到监禁玩家信息：%d", prison.prisonPlayerId)
        return null
    }
    val prisonMainHero = areaCache.heroCache.findHeroById(prison.prisonPlayerId, prisonPlayer.mainHeroId)
    if (prisonMainHero == null) {
        normalLog.error("找不到监禁的英雄信息:%d", prisonPlayer.mainHeroId)
        return null
    }
    val playerPrisonChangeBuilder = PlayerPrisonChange.newBuilder()
    playerPrisonChangeBuilder.changeType = changeType
    val prisonInfoBuilder = PrisonInfo.newBuilder()
    prisonInfoBuilder.playerId = prisonPlayer.id
    prisonInfoBuilder.heroProtoId = prisonMainHero.protoId
    prisonInfoBuilder.heroLv = prisonPlayer.kingLv
    prisonInfoBuilder.heroState = prisonMainHero.mainHeroState
    prisonInfoBuilder.heroStateStartTime = (prisonMainHero.mainHeroStateStartTime / 1000).toInt()
    prisonInfoBuilder.heroStateOverTime = (prisonMainHero.mainHeroStateEndTime / 1000).toInt()
    prisonInfoBuilder.ransom = prison.ransom
    prisonInfoBuilder.playerName = prisonPlayer.name
    prisonInfoBuilder.allianceShortName = prisonPlayer.allianceShortName
    prisonInfoBuilder.iconId = prisonPlayer.photoProtoId
    playerPrisonChangeBuilder.setPrisonInfo(prisonInfoBuilder)
    return PlayerPrisonChangeNotifier(playerPrisonChangeBuilder)
}


