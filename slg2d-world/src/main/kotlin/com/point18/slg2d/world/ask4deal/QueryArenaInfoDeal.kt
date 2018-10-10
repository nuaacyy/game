package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import com.point18.slg2d.common.constg.JjcAtk
import com.point18.slg2d.common.constg.JjcDef
import com.point18.slg2d.common.constg.JjcFight
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.fetchChallenge
import com.point18.slg2d.world.common.getJjcShopRefreshTime
import pb4client.HeroPos
import pb4client.JjcChallengeInfo
import pb4client.JjcQueryInfoRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.JjcInfoQueryAskRt
import java.util.*

class QueryArenaInfoDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.jjcInfoQueryAskReq
        val rtToHome = JjcInfoQueryAskRt.newBuilder()
        rtToHome.rt = ResultCode.SUCCESS.code

        val rt = createJjcQueryInfoRtBuilder()

        val nowRefreshTime = getJjcShopRefreshTime(getNowTime())
        rt.nextRefreshTime = nowRefreshTime / 1000 + 24 * 3600

        val playerId = req.playerId
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            rtToHome.rt = ResultCode.NO_PLAYER.code
            resp.setJjcInfoQueryAskRt(rtToHome)
            return
        }
        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session == null) {
            rtToHome.rt = ResultCode.NO_PLAYER.code
            resp.setJjcInfoQueryAskRt(rtToHome)
            return
        }

        val jjcInfo = areaCache.jjcCache.findJjc(playerId)
        if (jjcInfo == null) {
            rtToHome.rt = ResultCode.PARAMETER_ERROR.code
            resp.setJjcInfoQueryAskRt(rtToHome)
            return
        }

        // 获取玩家的排名及实力值
        rt.rank = player.jjcRank
        rt.buyTimes = askMsg.todayBuyCountNum

        var powerOfDefHero = 0L
        val defForces = areaCache.armyPlanCache.findArmyPlan(playerId, JjcFight, JjcDef)
        if (defForces == null) {
            rt.power = powerOfDefHero
        } else {
            val defHeroMap = defForces.heroMap
            for ((_, heroId) in defHeroMap) {
                val tmpHero = areaCache.heroCache.findHeroById(playerId, heroId) ?: continue
                powerOfDefHero += tmpHero.heroStrength
            }
            rt.power = powerOfDefHero // 实力值
        }

        // 获取玩家的布阵信息
        val armyPlan = areaCache.armyPlanCache.findArmyPlan(playerId, JjcFight, JjcAtk)
        val atkHeroList = LinkedList<HeroPos>()
        if (armyPlan != null) {
            for ((pos, heroId) in armyPlan.heroMap) {
                val heroPos = HeroPos.newBuilder()
                heroPos.pos = pos
                heroPos.heroId = heroId
                atkHeroList.add(heroPos.build())
            }
        }
        rt.addAllHeroInfo(atkHeroList)

        // 获取挑战剩余次数、挑战冷却结束时间
        val nowTime = getNowTime()
        if (isAfterGameRefTime(jjcInfo.lastFightTime)) {
            // 跨天了,次数回复
            jjcInfo.lastFightTime = nowTime
            rtToHome.todayNum = pcs.basicProtoCache.arenaFreeTimes
        }

        rt.times = askMsg.todayNum
        rtToHome.todayNum = askMsg.todayNum
        rt.coldEndTime = (jjcInfo.nextFightTime / 1000).toInt()
        rt.maxRank = jjcInfo.maxRank

        // 获取本日积分及本日已领取的积分奖励模版ID
        if (isAfterGameRefTime(jjcInfo.scoreTime)) {
            rt.score = jjcInfo.score
            val rewardIds = askMsg.scoreRewardsList
            rt.addAllScoreRewards(rewardIds)
        }

        // 获取已领取的奖励ID（最高排名）
        val rewardIds = askMsg.drawRewardsList
        rt.addAllDrawRewards(rewardIds)

        val achievementReward = askMsg.achievementRewardsList
        rt.addAllAchievementExchangeRewards(achievementReward)

        // 根据不同的情况，判断是否需要刷新3个挑战对手
        var refreshFlag = false
        if (jjcInfo.rank1 == 0 || jjcInfo.rank2 == 0 || jjcInfo.rank3 == 0) {
            // 如果还没有过挑战对手（rank1,rank2,rank3 == 0），那么在这边初始化一次3个挑战对手
            refreshFlag = true
        } else {
            // todo 玩家如果不在线被其他玩家打到其他名次，那么验证是否需要刷新
        }

        if (refreshFlag) {
            val (rank1, rank2, rank3) = pcs.jjcChallengeCache.fetchThreeRank(player.jjcRank)
            // 保存3个挑战对手
            jjcInfo.rank1 = rank1
            jjcInfo.rank2 = rank2
            jjcInfo.rank3 = rank3
        }

        // 获取竞技场的3个挑战对手
        rt.setChallenge1(fetchChallenge(areaCache, jjcInfo.rank1))
        rt.setChallenge2(fetchChallenge(areaCache, jjcInfo.rank2))
        rt.setChallenge3(fetchChallenge(areaCache, jjcInfo.rank3))

        // 获取前十名
        val tenRank = LinkedList<JjcChallengeInfo>()
        for (rank in 1..10) {
            val tmp = fetchChallenge(areaCache, rank)
            tenRank.add(tmp.build())
        }
        rt.addAllTenRank(tenRank)

        session.sendMsg(MsgType.JjcQueryInfo_711, rt.build())

        resp.setJjcInfoQueryAskRt(rtToHome)
        return
    }

    private fun createJjcQueryInfoRtBuilder(): JjcQueryInfoRt.Builder {
        val rtBuilder = JjcQueryInfoRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        rtBuilder.rank = 0
        rtBuilder.power = 0
        rtBuilder.times = 0
        rtBuilder.coldEndTime = 0
        rtBuilder.maxRank = 0
        rtBuilder.score = 0
        rtBuilder.buyTimes = 0
        return rtBuilder
    }
}