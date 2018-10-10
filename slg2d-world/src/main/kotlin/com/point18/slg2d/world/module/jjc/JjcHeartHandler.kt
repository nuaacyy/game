package com.point18.slg2d.world.module.jjc

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.JJC_RANK_CAN_BE_CHALLENGE
import com.point18.slg2d.common.constg.JjcRewardSync
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.syncData2Home

class JjcHeartHandler : IHeartHandler<AreaCache> {
    override fun handleHeart(cache: AreaCache) {
        val areaOnly = cache.areaOnlyCache.findAreaOnly()
        val now = getNowTime()

        if (now > areaOnly.nextJjcDayRewardTime) {
            // 关服后重新启服，计算没发的n此奖励也
            var nextTime = areaOnly.nextJjcDayRewardTime
            var rewardNum = 0
            while (nextTime <= now) {
                if (rewardNum >= 10000) {
                    normalLog.error("循环保险丝~")
                    return
                }
                nextTime = pcs.basicProtoCache.getNextJjcDayRewardTime(nextTime, now)
                rewardNum ++
            }

            // 发奖励
            val allPlayers = cache.playerCache.findAllPlayers()
            for (player in allPlayers) {
                //  20000 名以后的没有奖励
                if (player.jjcRank > JJC_RANK_CAN_BE_CHALLENGE) {
                    continue
                }

                val rewardProto = pcs.jjcRewardCache.fetchDayReward(player.jjcRank)
                if (rewardProto != null) {
                    val rewards = listOf(rewardProto.arenaReward * rewardNum, rewardProto.goldReward * rewardNum)
                    syncData2Home(
                        cache,
                        player.id,
                        JjcRewardSync,
                        toJson(rewards)
                    )
                }
            }

            // 更新下次发奖励的时间
            areaOnly.nextJjcDayRewardTime = nextTime
        }
    }
}