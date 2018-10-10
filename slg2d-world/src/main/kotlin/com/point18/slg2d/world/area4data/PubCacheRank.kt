package com.point18.slg2d.world.area4data

import com.point18.slg2d.common.commonfunc.AscComparator
import com.point18.slg2d.common.commonfunc.DescComparator
import com.point18.slg2d.common.commonfunc.RankMap
import com.point18.slg2d.common.constg.JjcRank
import com.point18.slg2d.common.constg.KillMonsterScore
import com.point18.slg2d.common.constg.KillSoliderRank
import com.point18.slg2d.common.constg.PowerRank
import com.point18.slg2d.world.WorldDatabase
import xyz.ariane.util.CommonDao

// 排行榜缓存,不存数据库的
class CacheRank(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val rankMap = mutableMapOf<Int, RankMap<Long, Long, MyTarget, Long>>()

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
    }

    override fun doInitData(worldInitData: WorldInitData) {

    }

    override fun doCacheLink() {
        // 准备排行榜
        val targets = areaCache.targetCache.findAllMyTarget()
        val powerRank = findRankInfo(PowerRank)
        val killSoliderRank = findRankInfo(KillSoliderRank)
        val killMonsterRank = findRankInfo(KillMonsterScore)
        val arenaRank = findRankInfo(JjcRank)
        for (t in targets) {
            powerRank?.updateValue(t)
            killSoliderRank?.updateValue(t)
            killMonsterRank?.updateValue(t)
            arenaRank?.updateValue(t)
        }
    }

    // 从排行榜缓存里查找数据
    fun findRankInfo(rankType: Int): RankMap<Long, Long, MyTarget, Long>? {
        val rankFilter: (MyTarget) -> Long
        val rankTimeFilter: (MyTarget) -> Long
        var minValue = 0L

        when (rankType) {
            PowerRank -> {
                rankFilter = { t: MyTarget -> t.getTotalPower() }
                rankTimeFilter = { t: MyTarget -> t.lastPowerChangeTime }
                minValue = -1
            }
            KillSoliderRank -> {
                rankFilter = { t: MyTarget -> t.totalKill }
                rankTimeFilter = { t: MyTarget -> t.lastKillSoliderTime }
            }
            KillMonsterScore -> {
                rankFilter = { t: MyTarget -> t.killMonsterScore }
                rankTimeFilter = { t: MyTarget -> t.lastKillMonsterScoreTime }
            }
            JjcRank -> {
                rankFilter = { t: MyTarget -> t.jjcRank.toLong() }
                rankTimeFilter = { t: MyTarget -> t.lastJjcRankTime }
            }
            else -> // 传入了不存在的排行类型
                return null
        }

        if (rankType == JjcRank) {
            return rankMap.getOrPut(rankType) {
                RankMap({ it.playerId },
                        rankFilter,
                        rankTimeFilter,
                        AscComparator(),
                        AscComparator(),
                        minValue,
                        100
                )
            }
        }

        return rankMap.getOrPut(rankType) {
            RankMap({ it.playerId },
                    rankFilter,
                    rankTimeFilter,
                    DescComparator(),
                    DescComparator(),
                    minValue,
                    100
            )
        }
    }
}