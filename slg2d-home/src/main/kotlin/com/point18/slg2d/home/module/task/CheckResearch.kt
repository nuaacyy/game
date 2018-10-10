package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 科技研发到某个ID
class CheckResearch : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val homePlayerDC = checkDep.homePlayerDC
        val player = homePlayerDC.player

        var isHave = false
        for ((rId, rVo) in player.researchInfoMap) {
            val rps = com.point18.slg2d.common.pc.pcs.researchCache.researchProtoMapByLv[rId]
            if (rps == null) {
                continue
            }
            val rp = rps[rVo.researchLv]
            if (rp == null) {
                continue
            }

            if (rp.id == checkValue[0]) {
                isHave = true
                break
            }
        }

        if (isHave) {
            return AllCheckReturn(true, 1)
        }

        return AllCheckReturn(false, 0)
    }
}



