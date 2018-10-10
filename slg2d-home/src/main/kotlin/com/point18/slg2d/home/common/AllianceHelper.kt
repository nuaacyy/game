package com.point18.slg2d.home.common

import com.point18.slg2d.common.constg.RightType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.msgnotice.createAllianceInfoChangeNotifier
import java.util.*

// 推送联盟信息变化--> 公共服rpc过来之后用的 游戏服不会有*domaingame.AllianceWrap了
fun sendAllianceInfoChange(session: PlayerActor, aid: Long, pos: LinkedList<Int>, allianceName: String, allianceShortName: String) {

    var position = LinkedList<Int>()
    if (aid != 0L) {
        position = pos
    }

    val chg = createAllianceInfoChangeNotifier(aid, allianceName, allianceShortName, position)
    chg.notice(session)
}

// 验证职位是否有权限：返回true，有权限；反之，没有权限
fun hasRight(player: HomePlayer, right: RightType): Boolean {
    for ((posId, _) in player.alliancePosMap) {
        val ok = pcs.posRightCache.hasRight(posId, right)
        if (ok) {
            return true
        }
    }

    return false
}
