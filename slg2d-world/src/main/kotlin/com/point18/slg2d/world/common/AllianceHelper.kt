package com.point18.slg2d.world.common

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.msgnotice.createAllianceInfoChangeNotifier
import java.util.*

// 推送联盟信息变化--> 公共服rpc过来之后用的 游戏服不会有AllianceWrap了
fun sendAllianceInfoChange(
    areaCache: AreaCache,
    session: PlayerSession,
    aid: Long,
    pos: LinkedList<Int>,
    allianceName: String,
    allianceShortName: String
) {

    var position = LinkedList<Int>()
    if (aid != 0L) {
        position = pos
    }

    val chg = createAllianceInfoChangeNotifier(aid, allianceName, allianceShortName, position)
    chg.notice(session)
}
