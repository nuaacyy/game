package com.point18.slg2d.home.module.vip

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ACTION_GET_VIP_LOGIN_REWARD
import com.point18.slg2d.common.constg.GAIN_VIP_REWARD
import com.point18.slg2d.common.constg.NOT_PROPS_SUB_TYPE
import com.point18.slg2d.common.constg.RES_VIP_EXP
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.VipHelper
import com.point18.slg2d.home.common.getVipRefreshTime
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.IHeartDeal
import com.point18.slg2d.home.msgnotice.createGetVipLoginRewardNotifier
import java.util.*
import java.util.Arrays.asList

class VipHeartTriggerDeal(
    private val vipHelper: VipHelper = VipHelper(),
    private val resHelper: ResHelper = ResHelper()
) : IHeartDeal,
    HomeHelperPlus2<HomePlayerDC, VipDC>(
        HomePlayerDC::class.java, VipDC::class.java,
        asList(vipHelper, resHelper)
    ) {

    override fun dealHeart(session: PlayerActor, actionId: Long, onComplete: (rt: Int) -> Unit) {
        prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC ->
            val player = homePlayerDC.player
            val vipInfo = vipDC.vipInfo

            val nowTime = getNowTime()
            val nowRefreshTime = getVipRefreshTime(nowTime)
            val lastRefreshEnergyTime = getVipRefreshTime(vipInfo.lastRefreshEnergyTime)
            val subRefreshEnergyHours = (nowRefreshTime - lastRefreshEnergyTime).toDouble() / (1000 * 3600)
            if (subRefreshEnergyHours >= 24.0) {
                vipHelper.resetDailyEnergy(session, true)
            }

            if (vipInfo.vipLv == pcs.vipSetCache.maxLvVip.level) {
                // vip满级，没有vip经验奖励
                return@prepare
            }

            val birthDayVipTime = getVipRefreshTime(player.birthTime)
            val lastGainVipExpTime = getVipRefreshTime(vipInfo.lastGetVipRewardTime)
            val subRefreshHours = (nowRefreshTime - lastGainVipExpTime).toDouble() / (1000 * 3600)

            if (birthDayVipTime == nowRefreshTime) {
                return@prepare
            }

            if (subRefreshHours == 0.0) {
                //当天第N次登录
                return@prepare
            }

            if (subRefreshHours == 24.0) {
                //正好一个刷新周期
                vipInfo.continueOnlineDay += 1
                vipInfo.lastGetVipRewardTime = nowTime
                var nowGetExp =
                    (vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet) * (1 + 0.2 * (vipInfo.continueOnlineDay - 1))
                if (nowGetExp > pcs.basicProtoCache.vipEveryDayMost) {
                    nowGetExp = pcs.basicProtoCache.vipEveryDayMost.toDouble()
                }
                val nowReward = LinkedList<ResVo>(asList(ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nowGetExp.toLong())))

                var nextGetExp =
                    (vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet) * (1 + 0.2 * vipInfo.continueOnlineDay)
                if (nextGetExp > pcs.basicProtoCache.vipEveryDayMost) {
                    nextGetExp = pcs.basicProtoCache.vipEveryDayMost.toDouble()
                }
                val nextReward = LinkedList<ResVo>(asList(ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nextGetExp.toLong())))
                val notifier = createGetVipLoginRewardNotifier(
                    resVoToResString(nowReward), resVoToResString(nextReward),
                    vipInfo.continueOnlineDay, GAIN_VIP_REWARD
                )
                notifier.notice(session)
                resHelper.addRes(session, ACTION_GET_VIP_LOGIN_REWARD, player, nowReward)
                return@prepare
            }

            if (subRefreshHours > 24.0) {
                //两次时间超过一天
                vipInfo.continueOnlineDay = 1
                vipInfo.lastGetVipRewardTime = nowTime
                val nowGetExp = vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet
                val nowReward = LinkedList<ResVo>(asList(ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nowGetExp.toLong())))

                val nextGetExp =
                    (vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet) * (1 + 0.2 * vipInfo.continueOnlineDay)
                val nextReward = LinkedList<ResVo>(asList(ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nextGetExp.toLong())))
                val notifier = createGetVipLoginRewardNotifier(
                    resVoToResString(nowReward), resVoToResString(nextReward),
                    vipInfo.continueOnlineDay, GAIN_VIP_REWARD
                )
                notifier.notice(session)
                resHelper.addRes(session, ACTION_GET_VIP_LOGIN_REWARD, player, nowReward)
                return@prepare
            }
        }
    }
}