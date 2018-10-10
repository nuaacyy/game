package com.point18.slg2d.home.module.equip.usePropEff

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_USE_PROPS
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.module.equip.Helpers
import com.point18.slg2d.home.module.equip.UseProp
import com.point18.slg2d.home.module.equip.UsePropReturn
import com.point18.slg2d.home.module.equip.UsePropsDepDcs
import pb4server.BuffBagAskReq
import pb4server.Home2WorldAskResp
import java.util.*

//使用物品,获取一个buff
class UseBuffBag : UseProp {

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

        // 获得buff
        val nowTime = getNowTime()

        // 获得buff
        val buffId = propProto.extend1.toIntOrNull()
        if (buffId == null) {
            return UsePropReturn(ResultCode.PARAMETER_ERROR, "")
        }

        val buffBasicProto = pcs.buffBasicProtoCache.protoMap[buffId]
        if (buffBasicProto == null) {
            assert(false) { "使用buff类道具的时候在buffBasic表中找不到行:$buffId" }
            return UsePropReturn(ResultCode.PARAMETER_ERROR, "")
        }

        var rt = ResultCode.SUCCESS.code
        val askMsg = BuffBagAskReq.newBuilder()
        askMsg.buffBasicProtoId = buffId
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setBuffBagAskReq(askMsg) },
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
                        if (askResp.buffBagAskRt.rt != ResultCode.SUCCESS.code) {
                            // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                            if (costs != null && costRes != null) {
                                helpers.resHelper.addResWithoutNotice(session, action, player, costs)
                            }
                            rt = askResp.buffBagAskRt.rt
                        } else {
                            if (costRes != null) {
                                costRes.noticeCostRes(session, player)
                            }
                        }
                        sendToClient(rt, "")
                    }
                }

            } catch (e: Exception) {
                normalLog.error("BuffBagAskReq Error!", e)
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

