package com.point18.slg2d.home.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_GET_JJC_RANK_REWARD
import com.point18.slg2d.common.constg.NOT_PROPS_SUB_TYPE
import com.point18.slg2d.common.constg.RES_BIND_GOLD
import com.point18.slg2d.common.constg.RES_JJC_COIN
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GetRankGoldRt
import java.util.*
import java.util.Arrays.asList

// 获取竞技场奖励
class GetRankGoldDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, HomeSyncDC>(
    HomePlayerDC::class.java, HomeSyncDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC ->
            val rt = getRankGold(session, homeSyncDC, homePlayerDC)
            session.sendMsg(MsgType.GetRankGold_725, rt)
        }

    }

    private fun getRankGold(session: PlayerActor, homeSyncDC: HomeSyncDC, homePlayerDC: HomePlayerDC): GetRankGoldRt {
        val rt = GetRankGoldRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val player = homePlayerDC.player
        if (homeSyncDC.syncData.jjcCoinReward == 0L && homeSyncDC.syncData.jjcGoldReward == 0L) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val randReward = LinkedList<ResVo>()
        randReward += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, homeSyncDC.syncData.jjcGoldReward)
        randReward += ResVo(RES_JJC_COIN, NOT_PROPS_SUB_TYPE, homeSyncDC.syncData.jjcCoinReward)

        resHelper.addRes(session, ACTION_GET_JJC_RANK_REWARD, player, randReward)

        homeSyncDC.syncData.jjcGoldReward = 0
        homeSyncDC.syncData.jjcCoinReward = 0

        return rt.build()
    }

}