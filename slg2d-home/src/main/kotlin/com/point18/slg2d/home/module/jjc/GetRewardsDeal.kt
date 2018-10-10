package com.point18.slg2d.home.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.JjcRewardProto
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
import pb4client.JjcGetRewards
import pb4client.JjcGetRewardsRt
import java.util.*
import java.util.Arrays.asList

// 领取竞技场奖励
class GetRewardsDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, JjcHomeDC, HomeSyncDC>(
    HomePlayerDC::class.java, JjcHomeDC::class.java, HomeSyncDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC, homeSyncDC: HomeSyncDC ->
            val id32s = LinkedList((msg as JjcGetRewards).idsList)
            val rt = getRewards(session, id32s, homePlayerDC, jjcHomeDC, homeSyncDC)
            session.sendMsg(MsgType.JjcGetRewards_714, rt)
        }
    }

    private fun getRewards(
        session: PlayerActor,
        id32s: LinkedList<Int>,
        homePlayerDC: HomePlayerDC,
        jjcHomeDC: JjcHomeDC,
        homeSyncDC: HomeSyncDC
    ): JjcGetRewardsRt {
        val rt = JjcGetRewardsRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.addAllIds(id32s)

        val player = homePlayerDC.player
        val jjcHome = jjcHomeDC.jjcHome
        val homeSync = homeSyncDC.syncData

        // 是否为空
        if (id32s.size == 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        var drawIds = LinkedList<Int>()// 获取已领取的奖励ID
        val vos = LinkedList<JjcRewardProto>()
        var rewardType = 0
        val idMap = hashMapOf<Int, Boolean>()

        for (id in id32s) {
            // 验证奖励模版ID是否存在
            val vo = pcs.jjcRewardCache.protoMap[id]
            if (vo == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }

            when (vo.type) {
                JJC_REWARD_TYPE_RANK -> {
                    // 领取条件（最高排名）
                    if (vo.condValues[0] < homeSync.maxJjcRank) {
                        rt.rt = ResultCode.JJC_REWARD_MAX_RANK_FORBIDDEN.code
                        return rt.build()
                    }

                    drawIds = jjcHome.drawRewards
                }

                JJC_REWARD_TYPE_SCORE -> {
                    var score = 0
                    if (isAfterGameRefTime(homeSync.scoreTime)) {
                        score = homeSync.score
                        drawIds = jjcHome.scoreRewards
                    }

                    // 领取条件（积分）
                    if (vo.condValues[0] > score) {
                        rt.rt = ResultCode.JJC_REWARD_SCORE_FORBIDDEN.code
                        return rt.build()
                    }
                }

                else -> {
                    // 只有类型是最高排名和积分奖励才可以领取
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    return rt.build()
                }

            }

            // 是否已经领取
            for (drawId in drawIds) {
                if (drawId == id) {
                    rt.rt = ResultCode.JJC_REWARD_ALREADY_DRAW.code
                    return rt.build()
                }
            }

            // 奖励模版ID是否重复
            val ok = idMap[id]
            if (ok != null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }

            // 是否是同一类型的奖励
            if (rewardType != 0 && rewardType != vo.type) {
                rt.rt = ResultCode.JJC_REWARD_DIFFERENCE.code
                return rt.build()
            }

            // 添加至验证列表
            idMap[id] = true
            if (rewardType == 0) {
                rewardType = vo.type
            }

            // 添加到奖励列表中
            vos.add(vo)
        }

        // 开始领取奖励
        for (vo in vos) {
            // 添加奖励
            val reward = LinkedList<ResVo>()
            reward += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, vo.goldReward)
            reward += ResVo(RES_JJC_COIN, NOT_PROPS_SUB_TYPE, vo.arenaReward)
            resHelper.addRes(session, ACTION_JJC_REWARD, player, reward)

            drawIds.add(vo.id)
        }

        // 保存领取的奖励ID
        if (rewardType == JJC_REWARD_TYPE_RANK) {
            jjcHome.drawRewards = drawIds
        } else {
            jjcHome.scoreRewards = drawIds
        }

        return rt.build()
    }

}