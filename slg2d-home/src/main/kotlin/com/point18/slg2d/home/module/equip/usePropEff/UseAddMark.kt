package com.point18.slg2d.home.module.equip.usePropEff

import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.module.equip.Helpers
import com.point18.slg2d.home.module.equip.UseProp
import com.point18.slg2d.home.module.equip.UsePropReturn
import com.point18.slg2d.home.module.equip.UsePropsDepDcs
import com.point18.slg2d.home.msgnotice.createMarkNumChangeNotifier
import java.util.*

class UseAddMark : UseProp {

    override fun useProp(
        depDcs: UsePropsDepDcs,
        propProtoId: Int,
        session: PlayerActor,
        useNum: Int,
        extendVal: String,
        helpers: Helpers,
        costs: LinkedList<ResVo>?,
        costRes: CostResWithoutNoticeResult?,
        sendToClient: (rt: Int, s: String) -> Unit
    ): UsePropReturn? {
        val player = depDcs.homePlayerDC.player

        // 验证
        val propProto = pcs.equipCache.equipProtoMap[propProtoId]
        if (propProto == null) {
            return UsePropReturn(ResultCode.NO_BUY_PROTO, "")
        }

        val addMarkNum = propProto.extend1.toIntOrNull()
        if (addMarkNum == null) {
            return UsePropReturn(ResultCode.PARAMETER_ERROR, "")
        }

        for (i in 1..useNum) {
            val num = player.maxMark + addMarkNum
            if (num > pcs.basicProtoCache.favoritesMarkTop) {
                return UsePropReturn(ResultCode.NO_MORE_MARK, "")
            }
            // 新增收藏数量
            player.maxMark += addMarkNum

            if (player.maxMark >= pcs.basicProtoCache.favoritesMarkTop) {
                player.maxMark = pcs.basicProtoCache.favoritesMarkTop
            }
        }
        createMarkNumChangeNotifier(player.maxMark).notice(session)

        return UsePropReturn(ResultCode.SUCCESS, "")
    }
}

