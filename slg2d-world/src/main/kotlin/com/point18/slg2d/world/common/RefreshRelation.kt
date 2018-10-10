package com.point18.slg2d.world.common

import com.point18.slg2d.common.constg.RELATION_CHANGE_BE_OCCUPIED
import com.point18.slg2d.common.constg.RELATION_CHANGE_JOIN_ALLIANCE
import com.point18.slg2d.common.constg.RELATION_CHANGE_OUT_OCCUPIED
import com.point18.slg2d.common.constg.RELATION_CHANGE_QUIT_ALLIANCE
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.createRelationChangeNotifier
import java.util.*

// 注意: 需要在关系变化之后调用
// 1/2: aid-待加入或已退出的联盟ID
// 3/4: aid-被沦陷或已脱离沦陷的联盟ID
fun refreshRelation(
    areaCache: AreaCache,
    chg: Int,
    player: Player,
    aid: Long,
    name: String,
    shortName: String
) {

    val pbMsg = createRelationChangeNotifier(chg, player.id, aid, name, shortName)

    when (chg) {
        RELATION_CHANGE_JOIN_ALLIANCE, RELATION_CHANGE_QUIT_ALLIANCE -> {

            /**************************************** 1 自己的联盟成员 ****************************************/
            val aPlayers = LinkedList<Player>()
            val mPlayers = areaCache.playerCache.findPlayersByAllianceId(aid)
            for (mPlayer in mPlayers) {
                aPlayers.add(mPlayer) // 避免修改原有的MAP
            }

            // 如果是离开联盟，也需要向自己推送
            if (chg == RELATION_CHANGE_QUIT_ALLIANCE) {
                aPlayers.add(player)
            }

            // 向联盟成员（包括自己）推送，自己加入或离开联盟
            for (playerM in aPlayers) {
                val session = fetchOnlinePlayerSession(areaCache, playerM.id)
                if (session != null) {
                    pbMsg.notice(session)
                }
            }
        }
        RELATION_CHANGE_BE_OCCUPIED, RELATION_CHANGE_OUT_OCCUPIED -> {

            /**************************************** 1 自己的联盟成员 ****************************************/
            val aPlayers = LinkedList<Player>()
            val mPlayers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
            for (mPlayer in mPlayers) {
                aPlayers.add(mPlayer) // 避免修改原有的MAP
            }

            // 如果没有联盟，只告诉自己就可以
            if (aPlayers.count() == 0) {
                aPlayers.add(player)
            }

            for (playerM in aPlayers) {
                val session = fetchOnlinePlayerSession(areaCache, playerM.id)
                if (session != null) {
                    pbMsg.notice(session)
                }
            }

            /************************************* 2 自己的上级联盟的成员 *************************************/
            val oPlayers = areaCache.playerCache.findPlayersByAllianceId(aid)
            for (playerM in oPlayers) {
                val session = fetchOnlinePlayerSession(areaCache, playerM.id)
                if (session != null) {
                    pbMsg.notice(session)
                }
            }
        }
    }
}
