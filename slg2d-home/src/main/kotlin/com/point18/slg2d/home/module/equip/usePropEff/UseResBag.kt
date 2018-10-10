package com.point18.slg2d.home.module.equip.usePropEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.module.equip.Helpers
import com.point18.slg2d.home.module.equip.UseProp
import com.point18.slg2d.home.module.equip.UsePropReturn
import com.point18.slg2d.home.module.equip.UsePropsDepDcs
import java.util.*

//使用物品,直接获得配置的奖励字符串(统称为使用礼包)
class UseResBag : UseProp {

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
        val action = ACTION_USE_PROPS

        val propProto = pcs.equipCache.equipProtoMap[propProtoId]
        if (propProto == null) {
            return UsePropReturn(ResultCode.NO_BUY_PROTO, "")
        }

        //获得东西
        val resVos = LinkedList<ResVo>()
        for (i in 1..useNum) {
            resVos += propProto.useGetMap
        }

        if (resVos.size != 0) {
            for (i in 0 until resVos.size) {
                if (resVos[i].resType != RES_KING_EXP) {
                    continue
                }
                val addedExp = resVos[i].num * (10000 + helpers.effectHelper.getResearchEffectValue(
                    session,
                    NO_FILTER,
                    KingExpAdd
                )) / 10000
                resVos[i] = ResVo(RES_KING_EXP, NOT_PROPS_SUB_TYPE, addedExp)
            }
            helpers.resHelper.addRes(session, action, player, resVos)
        }
        return UsePropReturn(ResultCode.SUCCESS, resVoToResString(resVos))
    }
}


