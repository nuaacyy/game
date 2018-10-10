package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.constg.NEW_PLAYER_ACTIVITY
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.KryoAskWorldMessage
import com.point18.slg2d.common.syncdomain.MoveServerEntitysReq
import com.point18.slg2d.common.syncdomain.MoveServerEntitysRt
import com.point18.slg2d.world.World2WorldKryoAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.noticeCellUpdate

// 迁移数据
class MoveServerInfoMsgDeal : World2WorldKryoAskDealBase() {

    override fun dealWorldAsk(areaCache: AreaCache, req: KryoAskWorldMessage): KryoAskWorldMessage {
        val checkMoveServerCellLockReq = req as MoveServerEntitysReq
        val rt = MoveServerEntitysRt(ResultCode.SUCCESS.code, areaCache.areaConfig.areaNo)
        val x = checkMoveServerCellLockReq.x
        val y = checkMoveServerCellLockReq.y

        // 数据创建
        areaCache.playerCache.createPlayerByMoveServer(req.pubCachePlayer)
        areaCache.infoByHomeCache.createInfoByHomeByMoveServer(req.pubCacheInfoByHome)
        areaCache.instanceCache.createInstanceByMoveServer(req.pubCacheInstance)
        areaCache.targetCache.createMyTargetByMoveServer(req.pubCacheMyTarget)
        areaCache.playerSettingCache.createPlayerSettingByMoveServer(req.pubCachePlayerSetting)

        for (a in req.pubCacheCastle) {
            areaCache.castleCache.createCastleByMoveServer(a, x, y)
        }

        for (a in req.pubCacheAchievement) {
            areaCache.achievementCache.createAchievementByMoveServer(a)
        }

        for (a in req.pubCacheArmyPlan) {
            areaCache.armyPlanCache.createArmyPlanByMoveServer(a)
        }

        for (a in req.pubCacheBarracks) {
            areaCache.barracksCache.createBarracksByMoveServer(a)
        }

        for (a in req.pubCacheBuff) {
            areaCache.buffCache.createBuffByMoveServer(a)
        }

        for (a in req.pubCacheHero) {
            areaCache.heroCache.createHeroByMoveServer(a)
        }

        for (a in req.pubCacheFog) {
            areaCache.fogCache.createFogByMoveServer(a)
        }

        for (a in req.pubCacheHomeHeart) {
            areaCache.homeHeartCache.createHomeHeartByMoveServer(areaCache, a)
        }

        for (a in req.pubCacheIdMyAllianceGift) {
            areaCache.myAllianceGiftCache.createMyAllianceGiftByMoveServer(a)
        }

        for (a in req.pubCacheInstanceDrop) {
            areaCache.instanceDropCache.createInstanceDropByMoveServer(a)
        }

        for (a in req.pubCachePrison) {
            areaCache.prisonCache.createPrisonByMoveServer(a)
        }

        for (a in req.pubCacheTask) {
            areaCache.taskCache.createTaskByMoveServer(a)
        }

        for (a in req.pubCachePlayerActivity) {
            val eventTimeProto = pcs.eventTimeProtoCache.protoMap[a.activityId]
            if (eventTimeProto == null) {
                continue
            }

            if (eventTimeProto.eventType != NEW_PLAYER_ACTIVITY) {
                // 新手挑战才会重新记录
                continue
            }
            areaCache.playerActivityCache.createPlayerActivityMoveServer(a)
        }

        // 一些推送
        noticeCellUpdate(areaCache, x, y)

        // 迁服结束,删除之前锁定的点
        val moveCityLockXyVo = areaCache.moveServerCellLockCache.findMoveServerCellLockByXy(x, y)
        if (moveCityLockXyVo != null) {
            areaCache.moveServerCellLockCache.deleteMoveServerCellLock(moveCityLockXyVo)
        }

        return rt
    }
}