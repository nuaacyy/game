package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.OffType
import com.point18.slg2d.common.constg.UpdateOnlineState
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldTellDealBase
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.updateAllianceMemberInfo
import pb4server.Home2WorldTell
import java.util.*

class OfflineDeal : Home2WorldTellDealBase() {
    override fun dealHomeTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Home2WorldTell) {
        val sessionCache = areaCache.sessionCache

        val nowTime = getNowTime()

        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            // 从会话表中移除
            sessionCache.playerSessionOffline(session)

            // - 已经进入游戏
            val player = areaCache.playerCache.findPlayerById(playerId)

            if (player == null) {
                // todo 真的可以直接return吗
                return
            }

            val pos = LinkedList<Int>()
            for ((p, _) in player.alliancePosMap) {
                pos.add(p)
            }

            // 记录玩家离线时间戳
            player.lastLeaveTime = nowTime

            if (player.allianceId != 0L) {
                val updateInfoMap = hashMapOf<Int, String>()
                updateInfoMap[UpdateOnlineState] = OffType.toString()
                updateAllianceMemberInfo(session.areaCache, player.allianceId, playerId, updateInfoMap)
            }
        }
    }
}

