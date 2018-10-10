package com.point18.slg2d.world.area4data

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import xyz.ariane.util.CommonDao
import xyz.ariane.util.toDefaultEpochMilli
import java.time.LocalDate
import java.time.LocalDateTime

class CacheMapCell(areaCache: AreaCache) : BaseCache(areaCache) {

    var nowResRefId: Int = 0              // 当前刷新到的资源生态格子ID
    var nextResRefTime: Long = 0          // 下次刷新资源的时间
    var nowCommonBossRefId: Int = 0       // 当前刷新到的魔物生态格子Id
    var nextCommonBossRefTime: Long = 0   // 下次刷新普通魔物的时间
    var nowWonderBossRefIndex: Int = 0    // 当前刷新到的奇观标记
    var nextWonderBossRefTime: Long = 0   // 下次刷新奇观魔物的时间
    var nowSnowBossRefIndex: Int = 0         // 当前刷新到的雪地标记
    var nextSnowBossRefTime: Long = 0     // 下次刷新雪地魔物的时间

    override fun init() {
        val localDate = LocalDate.now()
        val localDateTime = LocalDateTime.of(
            localDate.year, localDate.month, localDate.dayOfMonth,
            pcs.basicProtoCache.timeZone, 0, 0
        )
        val nowLocalDateTime = LocalDateTime.now()
        if (nowLocalDateTime > localDateTime) {
            localDateTime.plusDays(1)
        }

        this.nextResRefTime = localDateTime.toDefaultEpochMilli()
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {

    }

    override fun doInitData(worldInitData: WorldInitData) {

    }

    override fun doCacheLink() {
        this.nextCommonBossRefTime = areaCache.areaOnlyCache.findAreaOnly().nextRefBossTime
    }


    // 获取地块类型
    fun getCellType(posX: Int, posY: Int): Int {
        val bossCell = areaCache.commonBossCache.findCommonBossByXY(posX, posY)
        if (bossCell != null) {
            return CELL_MONSTER
        }

        val farmCell = areaCache.commonResCache.findCommonResByXY(posX, posY)
        if (farmCell != null) {
            return CELL_RESOURCE
        }

        val mapRelic = areaCache.relicCache.findRelicByXY(posX, posY)
        if (mapRelic != null) {
            return CELL_RELIC
        }

        val castle = areaCache.castleCache.findCastleByXy(posX, posY)
        if (castle != null) {
            return CELL_CASTLE
        }

        val callBoss = areaCache.callBossCache.findCallBossByXy(posX, posY)
        if (callBoss != null) {
            return CELL_CALL_BOSS
        }

        val bossDieRes = areaCache.deadBossResCache.findDeadBossResByXy(posX, posY)
        if (bossDieRes != null) {
            return CELL_BOSS_DEAD_RESOURCE
        }

        val area = pcs.wonderLocationProtoCache.findInWonderType(posX, posY)
        if (area.int == WONDER_BASE) {
            return CELL_WONDER
        }

        val isBlock = pcs.bigMapBlockProtoCache.isCompose(posX, posY)
        if (isBlock) {
            return CELL_BLOCK
        }

        val activityBoss = areaCache.activityBossCache.findActivityBossByXy(posX, posY)
        if (activityBoss != null) {
            return CELL_ACTIVITY_BOSS
        }

        return CELL_EMPTY
    }
}
