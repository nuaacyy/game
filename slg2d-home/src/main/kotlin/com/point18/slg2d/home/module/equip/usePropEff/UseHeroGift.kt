package com.point18.slg2d.home.module.equip.usePropEff

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.module.equip.Helpers
import com.point18.slg2d.home.module.equip.UseProp
import com.point18.slg2d.home.module.equip.UsePropReturn
import com.point18.slg2d.home.module.equip.UsePropsDepDcs
import java.util.*

class UseHeroGift : UseProp {

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
        val propProto = pcs.equipCache.equipProtoMap[propProtoId]
        if (propProto == null) {
            return UsePropReturn(ResultCode.NO_BUY_PROTO, "")
        }

        // 校验英雄Id
        val heroId = extendVal.toLongOrNull()
        if (heroId == null) {
            return UsePropReturn(ResultCode.PARAMETER_ERROR, "")
        }

        val heroDC = depDcs.heroDC
        val rt = addIntimacy(heroDC, heroId)

        return UsePropReturn(rt, "")
    }

    // todo jh 暂时没有武将亲密度增加功能
    private fun addIntimacy(heroDC: HeroDC, heroId: Long): ResultCode {

        val hero = heroDC.findHeroById(heroId)
        if (hero == null) {
            normalLog.error("找不到英雄[$heroId]")
            return ResultCode.PARAMETER_ERROR
        }


        return ResultCode.SUCCESS
    }
}