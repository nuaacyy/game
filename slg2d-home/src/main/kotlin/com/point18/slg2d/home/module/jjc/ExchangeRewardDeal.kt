package com.point18.slg2d.home.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_JJC_REWARD
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.dc.JjcHomeDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ExchangeJjcAchievementReward
import pb4client.ExchangeJjcAchievementRewardRt
import java.util.*
import java.util.Arrays.asList

class ExchangeRewardDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, JjcHomeDC, HomeSyncDC>(
    HomePlayerDC::class.java, JjcHomeDC::class.java, HomeSyncDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val protoIds = (msg as ExchangeJjcAchievementReward).idsList

        prepare(session) { homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC, homeSyncDC: HomeSyncDC ->
            val rt = dealJjcAchievementReward(
                session, protoIds, homePlayerDC, jjcHomeDC, homeSyncDC
            )
            session.sendMsg(MsgType.ExchangeJjcAchievementReward_729, rt.build())
        }
    }

    private fun dealJjcAchievementReward(
        session: PlayerActor, protoIds: List<Int>, homePlayerDC: HomePlayerDC,
        jjcHomeDC: JjcHomeDC, homeSyncDC: HomeSyncDC
    ): ExchangeJjcAchievementRewardRt.Builder {
        val dealRt = ExchangeJjcAchievementRewardRt.newBuilder()
        dealRt.rt = ResultCode.SUCCESS.code
        dealRt.addAllIds(protoIds)

        val player = homePlayerDC.player
        val jjcHome = jjcHomeDC.jjcHome
        val homeSync = homeSyncDC.syncData

        val costAll = LinkedList<ResVo>()
        val getAll = LinkedList<ResVo>()

        for (eachProtoIds in protoIds) {
            if (jjcHome.achievementRewards.contains(eachProtoIds)) {
                dealRt.rt = ResultCode.JJC_REWARD_ITEM_GOT.code
                return dealRt
            }

            val proto = pcs.arenaAchievementExchangeCache.mapOfProto[eachProtoIds]
            if (proto == null) {
                dealRt.rt = ResultCode.NO_XML_PROTO.code
                return dealRt
            }
            if (proto.condition < homeSync.maxJjcRank) {
                dealRt.rt = ResultCode.JJC_REWARD_MAX_RANK_FORBIDDEN.code
                return dealRt
            }

            costAll += proto.arenaCoinCost
            getAll += proto.goldRewardGet
        }

        if (!resHelper.checkRes(session, costAll)) {
            dealRt.rt = ResultCode.LESS_RESOUCE.code
            return dealRt
        }

        resHelper.costRes(session,ACTION_JJC_REWARD, player, costAll)
        resHelper.addRes(session, ACTION_JJC_REWARD, player, getAll)
        jjcHome.achievementRewards.addAll(protoIds)

        return dealRt
    }
}
