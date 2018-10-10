package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.constg.SoliderStrength
import com.point18.slg2d.common.constg.TrapStrength
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier

class GmAddHurtTroops : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache
        val playerId = session.playerId
        val allBarracks = areaCache.barracksCache.findBarracksByPlayerId(playerId)
        for (b in allBarracks) {
            b.canCureNum += 100
            //重算军团实力
            targetAddVal(areaCache, b.playerId, SoliderStrength)
            targetAddVal(areaCache, b.playerId, TrapStrength)
            val notice = createBarracksChangeNotifier(b)
            notice.notice(session)
        }
        return 1
    }
}