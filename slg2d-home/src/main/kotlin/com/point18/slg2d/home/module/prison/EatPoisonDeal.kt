package com.point18.slg2d.home.module.prison

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_EAT_POISON
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.IconDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.EatPoison
import pb4client.EatPoisonRt
import pb4server.EatPoisonNumAskReq
import pb4server.Home2WorldAskResp
import java.util.*

class EatPoisonDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
            val num = (msg as EatPoison).num
            val rt = eatPoison(session, num, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.EatPoison_1353, rt)
            }
        }
    }

    private fun eatPoison(session: PlayerActor, num: Int, homePlayerDC: HomePlayerDC): EatPoisonRt? {
        val rt = EatPoisonRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // 先检测资源
        val costs = ResVo(RES_PROPS, pcs.basicProtoCache.fastDead.toLong(), num.toLong())
        if (!resHelper.checkRes(session, costs)) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }
        val costNotice = resHelper.costResWithoutNotice(
            session,
            ACTION_EAT_POISON,
            homePlayerDC.player,
            costs
        )

        val askMsg = EatPoisonNumAskReq.newBuilder()
        askMsg.num = num

        session.createACS<Home2WorldAskResp>()
            .ask(
                session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setEatPoisonNumAskReq(askMsg) },
                Home2WorldAskResp::class.java
            )
            .whenCompleteKt { askResp, askErr ->
                try {
                    when {
                        askErr != null -> {
                            resHelper.addResWithoutNotice(session, ACTION_EAT_POISON, homePlayerDC.player, costs)
                        }

                        askResp == null -> {
                            resHelper.addResWithoutNotice(session, ACTION_EAT_POISON, homePlayerDC.player, costs)
                        }

                        else -> {
                            if (askResp.eatPoisonNumAskRt.rt != ResultCode.SUCCESS.code) {
                                resHelper.addResWithoutNotice(session, ACTION_EAT_POISON, homePlayerDC.player, costs)
                                rt.rt = askResp.eatPoisonNumAskRt.rt
                                session.sendMsg(MsgType.EatPoison_1353, rt.build())
                            } else {
                                rt.rt = askResp.eatPoisonNumAskRt.rt
                                costNotice.noticeCostRes(session, homePlayerDC.player)
                                session.sendMsg(MsgType.EatPoison_1353, rt.build())
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("EatPoisonNumAskReq Error!", e)
                    rt.rt = ResultCode.ASK_ERROR3.code
                    costNotice.noticeCostRes(session, homePlayerDC.player)
                    session.sendMsg(MsgType.EatPoison_1353, rt.build())
                }
            }


        return rt.build()
    }

}