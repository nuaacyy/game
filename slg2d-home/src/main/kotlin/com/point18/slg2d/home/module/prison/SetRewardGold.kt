package com.point18.slg2d.home.module.prison

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_SET_REWARD_GOLD
import com.point18.slg2d.common.constg.NOT_PROPS_SUB_TYPE
import com.point18.slg2d.common.constg.RES_COIN
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.SetRewardGold
import pb4client.SetRewardGoldRt
import pb4server.Home2WorldAskResp
import pb4server.SetMainHeroRewardAskReq
import java.util.*

class SetRewardGold(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, HeroDC>(
        HomePlayerDC::class.java,
        HeroDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, heroDC ->
                val setNum = (msg as SetRewardGold).setNum
                val rt = setRewardGold(session, setNum, homePlayerDC, heroDC)
                if (rt != null) {
                    session.sendMsg(MsgType.SetRewardGold_1355, rt)
                }
            }
    }

    private fun setRewardGold(session: PlayerActor, numAdd: Long, homePlayerDC: HomePlayerDC, heroDC: HeroDC): SetRewardGoldRt? {
        val rt = SetRewardGoldRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.setNum = numAdd
        val homePlayer = homePlayerDC.player

        val hero = heroDC.findHeroById(homePlayer.mainHeroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }

        // 检测资源 先扣,失败了就加回去
        val costs = ResVo(RES_COIN, NOT_PROPS_SUB_TYPE, numAdd)
        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            rt.rt = (ResultCode.LESS_RESOUCE.code)
            return rt.build()
        }
        val costNotice = resHelper.costResWithoutNotice(
            session, ACTION_SET_REWARD_GOLD, homePlayer, costs
        )

        val askMsg = SetMainHeroRewardAskReq.newBuilder()
        askMsg.goldNumAdd = numAdd

        session.createACS<Home2WorldAskResp>()
            .ask(
                session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setSetMainHeroRewardAskReq(askMsg) },
                Home2WorldAskResp::class.java
            )
            .whenCompleteKt { askResp, askErr ->

                try {
                    when {
                        askErr != null -> {
                            rt.rt = ResultCode.ASK_ERROR1.code
                            resHelper.addResWithoutNotice(session, ACTION_SET_REWARD_GOLD, homePlayer, costs)
                            session.sendMsg(MsgType.SetRewardGold_1355, rt.build())
                        }

                        askResp == null -> {
                            rt.rt = ResultCode.ASK_ERROR2.code
                            resHelper.addResWithoutNotice(session, ACTION_SET_REWARD_GOLD, homePlayer, costs)
                            session.sendMsg(MsgType.SetRewardGold_1355, rt.build())
                        }

                        else -> {
                            if (askResp.setMainHeroRewardAskRt.rt != ResultCode.SUCCESS.code) {
                                resHelper.addResWithoutNotice(session, ACTION_SET_REWARD_GOLD, homePlayer, costs)
                                rt.rt = askResp.setMainHeroRewardAskRt.rt
                                session.sendMsg(MsgType.SetRewardGold_1355, rt.build())
                            } else {
                                costNotice.noticeCostRes(session, homePlayer)
                                rt.rt = askResp.setMainHeroRewardAskRt.rt
                                session.sendMsg(MsgType.SetRewardGold_1355, rt.build())
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("SetMainHeroRewardAskReq Error!", e)
                    rt.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.SetRewardGold_1355, rt.build())
                }
            }
        return rt.build()
    }
}