package com.point18.slg2d.home.module.giftBag

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.AllResYield
import com.point18.slg2d.common.constg.MONTH_CARD_REWARD
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.MonthCardReward
import pb4client.MonthCardRewardRt
import java.util.Arrays.asList

class RecvMonthCardRewardDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val refHelper: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java, asList(resHelper, refHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC ->
            msg as MonthCardReward
            val rt = deal(session, msg.monthCardId, homePlayerDC)
            session.sendMsg(MsgType.RecvMonthCardReward_1583, rt.build())
        }
    }

    private fun deal(session: PlayerActor, monthCardId: Int, homePlayerDC: HomePlayerDC): MonthCardRewardRt.Builder {
        val rt = MonthCardRewardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code


        // 月卡是否未购买或者已到期
        val monthCard = homePlayerDC.player.monthCards[monthCardId]
        if (monthCard == null || !monthCard.isActive()) {
            rt.rt = ResultCode.MONTH_CARD_INVALID.code
            return rt
        }

        // 月卡今天是否领取过
        if (monthCard.isRecved()) {
            rt.rt = ResultCode.MONTH_CARD_IS_RECVED.code
            return rt
        }

        monthCard.recordRecved()

        // 获取奖励配置
        val giftBagProto = pcs.giftBagProtoCache.giftBagMapByGiftBagIdLevel.findByKey(monthCardId, 1)
        if (giftBagProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        val drop = pcs.dropBagCache.dropBagMap[giftBagProto.reward]
        if (drop == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        // 添加礼包奖励
        resHelper.addRes(session, MONTH_CARD_REWARD, homePlayerDC.player, drop.dropMap)
        //推送资源信息
        refHelper.refreshResource(session, AllResYield)

        return rt
    }
}