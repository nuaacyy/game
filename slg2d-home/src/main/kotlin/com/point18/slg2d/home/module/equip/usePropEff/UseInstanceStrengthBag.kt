package com.point18.slg2d.home.module.equip.usePropEff

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_USE_PROPS
import com.point18.slg2d.common.constg.RES_ACTION_NUM
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.module.equip.Helpers
import com.point18.slg2d.home.module.equip.UseProp
import com.point18.slg2d.home.module.equip.UsePropReturn
import com.point18.slg2d.home.module.equip.UsePropsDepDcs
import pb4server.AddInstanceStrengthAskReq
import pb4server.Home2WorldAskResp
import java.util.*

class UseInstanceStrengthBag : UseProp {

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

        var addValue = 0
        propProto.useGetMap.forEach {
            if (it.resType != RES_ACTION_NUM) {
                return UsePropReturn(ResultCode.PARAMETER_ERROR, "")
            }

            addValue += it.num.toInt()
        }

        var rt = ResultCode.SUCCESS.code
        val askMsg = AddInstanceStrengthAskReq.newBuilder()
        askMsg.addValue = addValue * useNum
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setAddInstanceStrengthAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            try {
                when {
                    askErr != null -> {
                        if (costs != null && costRes != null) {
                            helpers.resHelper.addResWithoutNotice(session, action, player, costs)
                        }
                        rt = ResultCode.ASK_ERROR1.code
                        sendToClient(rt, "")
                    }
                    askResp == null -> {
                        if (costs != null && costRes != null) {
                            helpers.resHelper.addResWithoutNotice(session, action, player, costs)
                        }
                        rt = ResultCode.ASK_ERROR2.code
                        sendToClient(rt, "")
                    }
                    else -> {
                        if (askResp.addInstanceStrengthAskRt.rt != ResultCode.SUCCESS.code) {
                            rt = askResp.addInstanceStrengthAskRt.rt
                            // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                            if (costs != null && costRes != null) {
                                helpers.resHelper.addResWithoutNotice(session, action, player, costs)
                            }
                        } else {
                            if (costRes != null) {
                                costRes.noticeCostRes(session, player)
                            }

                        }
                        sendToClient(rt, "")
                    }
                }

            } catch (e: Exception) {
                normalLog.error("AddInstanceStrengthAskReq Error!", e)
                if (costs != null && costRes != null) {
                    helpers.resHelper.addResWithoutNotice(session, action, player, costs)
                }
                rt = ResultCode.ASK_ERROR3.code
                sendToClient(rt, "")
            }
        }


        return null
    }
}

