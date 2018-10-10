package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.common.pc.pcs

// 验证职位是否有权限：返回true，有权限；反之，没有权限
fun hasRight(player: Player?, right: com.point18.slg2d.common.constg.RightType): Boolean {
    if (player == null) {
        return false
    }

    for ((posId, _) in player.alliancePosMap) {
        val ok = pcs.posRightCache.hasRight(posId, right)
        if (ok) {
            return true
        }
    }

    return false
}
