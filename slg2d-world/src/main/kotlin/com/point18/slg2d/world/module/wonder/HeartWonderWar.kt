package com.point18.slg2d.world.module.wonder

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Wonder
import com.point18.slg2d.world.area4data.WonderRankVo
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.event.GetNewBuff
import com.point18.slg2d.world.event.WonderOver
import com.point18.slg2d.world.wpm
import xyz.ariane.util.lzInfo

class WonderWarHeartHandler : IHeartHandler<AreaCache> {
    override fun handleHeart(cache: AreaCache) {
        val bigWonder = cache.wonderCache.findBigWonder()
        if (bigWonder == null) {
            normalLog.error("找不到大奇观")
            return
        }

        val protoCache = pcs.wonderLocationProtoCache
        val finishProtectTime = protoCache.finishWonderProtectTime()

        val wonderWarStatus = checkWonderWarTime(cache)
        if (wonderWarStatus != bigWonder.wonderWarStatus) {
            when (wonderWarStatus) {
                START_WONDER_WAR_PROTECT -> {
                    // 全服上战神buff
                    giveGodOfWarBuff(cache, bigWonder.warStartTime)

                    // 开始前保护时间，需要给客户端推送一个活动入口时间
                    // todo jh wonder 不推好像也行，先留着
                    /*val notifier = ActivityEnterTimeChangeNotifier()
                    createWonderEnterTime(cache, WONDER_WAR_GATE, notifier)
                    for ((_, targetSession) in fetchAllOnlinePlayerSessions(cache)) {
                        notifier.notice(targetSession)
                    }*/

                    bigWonder.wonderWarStatus = START_WONDER_WAR_PROTECT
                }
                IN_WONDER_WAR -> {
                    startWonderWar(cache)
                    bigWonder.wonderWarStatus = IN_WONDER_WAR
                }
                FINISH_WONDER_WAR_PROTECT -> {
                    // 全服上战神buff
                    giveGodOfWarBuff(cache, bigWonder.warFinishTime + finishProtectTime)
                    finishWonderWar(cache)
                    bigWonder.wonderWarStatus = FINISH_WONDER_WAR_PROTECT
                }
                NOT_IN_WONDER_WAR -> {
                    // noting to do

                    bigWonder.wonderWarStatus = NOT_IN_WONDER_WAR
                }
            }
        }

        // 奇观占领处理
        val overOccupiedWonders = cache.wonderCache.findOverOccupiedWonder()
        for (wonder in overOccupiedWonders) {
            wonderOverOccupied(cache, wonder)
            normalLog.lzInfo { "奇观${wonder.id}被占领" }
        }
    }

    // 开启所有奇观
    private fun startWonderWar(areaCache: AreaCache) {
        val intoWarWonders = areaCache.wonderCache.findAllWonders()
        if (intoWarWonders.size > 0) {
            for (wonder in intoWarWonders) {
                wonderIntoWar(areaCache, wonder)
            }
            normalLog.lzInfo { "奇观争夺战开始" }
        }
    }

    // 关闭所有奇观
    private fun finishWonderWar(areaCache: AreaCache) {
        val wonders = areaCache.wonderCache.findAllWonders()
        if (wonders.size > 0) {
            for (wonder in wonders) {
                wonderOverWar(areaCache, wonder)
                normalLog.lzInfo { "奇观${wonder.id}进入和平" }
            }
        }
    }

    // 全服上战神buff
    private fun giveGodOfWarBuff(areaCache: AreaCache, buffOverTime: Long) {

        val godOfWarBuffProtoId = pcs.basicProtoCache.wonderBuff
        for (player in areaCache.playerCache.findAllPlayers()) {
            wpm.es.fire(
                areaCache, player.id, GET_BUFF,
                GetNewBuff(player.id, godOfWarBuffProtoId, buffOverTime)
            )
        }
        normalLog.lzInfo { "上战神buff" }
    }

    // 奇观进入争夺状态
    private fun wonderIntoWar(areaCache: AreaCache, wonder: Wonder) {
        if (wonder.wonderProtoId == BIG_WONDER) {
            changeKing(areaCache, 0)
        }
        // 同步奇观信息至pub
        updateWonderInfo(areaCache, Del, wonder.belongToAllianceId, wonder)

        wonder.status = WAR_NORMAL
        areaCache.worldActor.wonder = wonder.status
        syncInfo2WorldManager(areaCache)
        wonder.belongToAllianceId = 0L
        wonder.rankInfoMap = hashMapOf()
        wonder.awardInfoMap = hashMapOf()

        //通知客户端刷新奇观地块
        val wonderProto =
            pcs.wonderLocationProtoCache.wonderLocationProtoMap[wonder.wonderProtoId]
        if (wonderProto === null) {
            normalLog.error("找不到奇观配置：${wonder.wonderProtoId}")
            return
        }
        val centerPos = wonderProto.getCenterPos()
        noticeCellUpdate(areaCache, centerPos.x, centerPos.y)
    }

    // 奇观占领和平
    private fun wonderOverOccupied(areaCache: AreaCache, wonder: Wonder) {
        val wonderProto = pcs.wonderLocationProtoCache.wonderLocationProtoMap[wonder.wonderProtoId]
        if (wonderProto === null) {
            normalLog.error("找不到奇观配置：${wonder.wonderProtoId}")
            return
        }

        // 更新排行榜
        val allianceId = wonder.belongToAllianceId
        if (allianceId != 0L) {
            val nowTime = getNowTime()
            val wonderRankVo = wonder.rankInfoMap[allianceId]
            if (wonderRankVo == null) {
                // 占领奇观后会存一个rankInfo，正常情况下不会为null
                normalLog.error("找不到联盟奇观占领排行")
            } else {
                wonderRankVo.score = wonderRankVo.score + (nowTime - wonder.occupyStartTime)
            }
        }

        wonder.status = PEACE
        areaCache.worldActor.wonder = wonder.status
        syncInfo2WorldManager(areaCache)
        //占领和平不需要更新下一次奇观战的时间,而是在奇观战结束时去更新

        // 奇观驻防部队解散
        dismissWonderArmy(areaCache, wonder)

        //同步奇观信息到pub
        updateWonderInfo(areaCache, Update, wonder.belongToAllianceId, wonder)

        //通知客户端刷新奇观地块
        val centerPos = wonderProto.getCenterPos()
        noticeCellUpdate(areaCache, centerPos.x, centerPos.y)
    }

    // 奇观战结束
    private fun wonderOverWar(areaCache: AreaCache, wonder: Wonder) {
        // 活动结束奇观都没有被占领，通过占领时间判断奇观拥有者
        if (wonder.status != PEACE) {
            // 将当前占领联盟的时间计入排行
            val belongToAllianceId = wonder.belongToAllianceId
            if (belongToAllianceId != 0L) {
                val nowTime = getNowTime()
                val wonderRankVo = wonder.rankInfoMap[belongToAllianceId]
                if (wonderRankVo == null) {
                    wonder.belongToAllianceId = 0L
                } else {
                    wonderRankVo.score = wonderRankVo.score + (nowTime - wonder.occupyStartTime)
                }
            }

            // 获得最高得分记录
            var topRank = WonderRankVo()
            wonder.rankInfoMap.values.forEach {
                if (topRank.allianceId == 0L) {
                    topRank = it
                } else if (it.score > topRank.score) {
                    topRank = it
                }
            }

            // 更换奇观拥有者
            if (topRank.allianceId != 0L && belongToAllianceId != topRank.allianceId) {

                if (wonder.wonderProtoId == BIG_WONDER) {
                    changeKing(areaCache, topRank.allianceId)
                }

                //同步奇观信息至pub
                updateWonderInfo(areaCache, Del, wonder.belongToAllianceId, wonder)
                wonder.belongToAllianceId = topRank.allianceId
                updateWonderInfo(areaCache, Add, topRank.allianceId, wonder)
            }

            //奇观部队解散
            dismissWonderArmy(areaCache, wonder)

            //同步奇观信息到pub
            updateWonderInfo(areaCache, Update, wonder.belongToAllianceId, wonder)
        }

        wonder.status = PEACE
        areaCache.worldActor.wonder = wonder.status
        syncInfo2WorldManager(areaCache)
        updateWonderNextTime(wonder)

        if (wonder.wonderProtoId == BIG_WONDER) {
            if (wonder.belongToAllianceId != 0L) {
                // 重置天下大赦，更新名人堂，发奖励等
                dealAfterWonderWar(areaCache, wonder)
            }

            // 触发奇观战结束事件
            wpm.es.fire(areaCache, 0L, WONDER_OVER, WonderOver())

            // 通知新活动时间
            // todo jh wonder
            /*val notifier = ActivityEnterTimeChangeNotifier()
            createWonderEnterTime(areaCache, WONDER_WAR_ACTIVITY, notifier)
            for ((_, targetSession) in fetchAllOnlinePlayerSessions(areaCache)) {
                notifier.notice(targetSession)
            }*/
        }

        //通知客户端刷新奇观地块
        val wonderProto =
            pcs.wonderLocationProtoCache.wonderLocationProtoMap[wonder.wonderProtoId]
        if (wonderProto === null) {
            normalLog.error("找不到奇观配置：${wonder.wonderProtoId}")
            return
        }
        val centerPos = wonderProto.getCenterPos()
        noticeCellUpdate(areaCache, centerPos.x, centerPos.y)
    }
}


