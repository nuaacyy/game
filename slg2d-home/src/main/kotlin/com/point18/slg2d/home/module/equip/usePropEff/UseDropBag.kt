package com.point18.slg2d.home.module.equip.usePropEff

import com.point18.slg2d.common.constg.ACTION_USE_PROPS
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

// 使用物品,获得从props中extend2字段中随机掉落一个ID  ID对应dropBag表中id
class UseDropBag : UseProp {

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

        //验证
        val action = ACTION_USE_PROPS

        val propProto = pcs.equipCache.equipProtoMap[propProtoId]
        if (propProto == null) {
            return UsePropReturn(ResultCode.NO_BUY_PROTO, "")
        }

        //获得东西
        val resVos = LinkedList<com.point18.slg2d.common.pc.ResVo>()

        for (i in 1..useNum) {
            val addRes = com.point18.slg2d.common.pc.findValueFromDropBag(propProto.extend2DropMap)
            val addR = addRes
            val dropReward = pcs.dropBagCache.dropBagMap[addR]
            if (dropReward == null) {
                // commonfunc.debugAssert(false, "使用道具(UseDropBag接口)的时候在dropId中找不到对应的行:%v", addR)
                continue
            }
            val reward = dropReward.dropMap
            resVos += reward
        }
        if (resVos.size != 0) {
            helpers.resHelper.addRes(session, action, player, resVos)
        }
        return UsePropReturn(ResultCode.SUCCESS, resVoToResString(resVos))
    }
}

