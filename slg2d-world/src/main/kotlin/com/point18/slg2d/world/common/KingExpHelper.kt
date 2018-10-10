package com.point18.slg2d.world.common

import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.pc.pcs

data class CalKingRealAddExpRt(
    val realAddExp: Int,
    val expOverFlow: Boolean,
    val expAfterCal: Int,
    val lvAfterCal: Int
)

//计算实际增加的领主经验
fun calKingRealAddExp(playerLv: Int, kingExp: Int, addExp: Int): CalKingRealAddExpRt {
    val maxLv = pcs.kingExpCache.maxLvProto.level

    var realAddExp = 0
    var canAddExp = addExp
    var currentExp = kingExp
    var currentLv = playerLv
    for (i in 0 until 200) {
        if (canAddExp <= 0) {
            break
        }

        if (playerLv >= maxLv) {
            break
        }

        val proto = pcs.kingExpCache.kingExpMap[currentLv]
        if (proto == null) {
            com.point18.slg2d.common.commonfunc.normalLog.lzDebug { "pcs.kingExpCache.kingExpMap[currentLv] == null" }
            continue
        }
        if (currentExp + canAddExp < proto.exp) {
            currentExp += canAddExp
            realAddExp += canAddExp
            canAddExp = 0
        } else {
            canAddExp -= proto.exp - currentExp
            realAddExp += proto.exp - currentExp
            currentExp = 0
            currentLv += 1
        }
    }
    return CalKingRealAddExpRt(realAddExp, canAddExp != 0, currentExp, currentLv)
}