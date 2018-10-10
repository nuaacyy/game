package com.point18.slg2d.world.module.mainHeroprison

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.PrisonEvent
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import java.util.*
import java.util.Arrays.asList

class PrisonEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val winPlayerId = (event as PrisonEvent).winPlayerId
        val losePlayerId = event.losePlayerId
        dealMainHeroPrison(cache, winPlayerId, losePlayerId)
    }

    // 处理领主的监禁
    private fun dealMainHeroPrison(areaCache: AreaCache, winPlayerId: Long, losePlayerId: Long) {
        // 收到要关人的事件
        val winPlayer = areaCache.playerCache.findPlayerById(winPlayerId)
        if (winPlayer == null) {
            normalLog.error("MainHeroPrisonModule.kt :   winPlayer == null")
            return
        }
        val losePlayer = areaCache.playerCache.findPlayerById(losePlayerId)
        if (losePlayer == null) {
            normalLog.error("MainHeroPrisonModule.kt :   losePlayer == null")
            return
        }
        // 获取失败的人的领主信息
        val loseMainHero = areaCache.heroCache.findHeroById(losePlayerId, losePlayer.mainHeroId)
        if (loseMainHero == null) {
            normalLog.error("MainHeroPrisonModule.kt :   loseMainHero == null")
            return
        }

        // 根据不同的数据,选择不同的关人策略
        val losePlayerCastleInfo = areaCache.castleCache.findCastleById(losePlayer.focusCastleId)
        if (losePlayerCastleInfo == null) {
            normalLog.error("MainHeroPrisonModule.kt :   losePlayerCastleInfo == null")
            return
        }

        val prisonTimeProto = pcs.prisonTimeProtoCache.prisonTimeProtoMap[losePlayerCastleInfo.lv]
        if (prisonTimeProto == null) {
            assert(false) { "找不到城堡等级对应的监禁配置:${losePlayerCastleInfo.lv}" }
            return
        }

        val homeInfo = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(winPlayerId)
        if (homeInfo == null) {
            assert(false) { "找不到玩家的home同步数据" }
            return
        }

        val winPrisonBuildingInfo = homeInfo.buildInfoMap[PRISON] // 胜利方的监狱建筑信息
        val winPrisonInfo = areaCache.prisonCache.findPrisonsByPlayerId(winPlayerId)

        val nowMilliSec = getNowTime()
        var isWinnerPlayerHaveBuff = 0
        if (winPlayer.maxLvPrisonBuffEndTime > nowMilliSec) {
            isWinnerPlayerHaveBuff = 1
        }

        if (winPrisonBuildingInfo == null || prisonTimeProto.timeType == CAN_NO_PRISON) {
            // 被抓领主太弱小了,直接不执行监禁逻辑
        } else if ((winPrisonInfo.size + isWinnerPlayerHaveBuff) >= pcs.basicProtoCache.prisonNum) {
            // 邮件通知
            val mailInfoToPrison = MailInfo(
                TEXT_READ_LAN,
                TITLE_PRISON_FULL,
                LinkedList(),
                CONTENT_PRISON_FULL,
                LinkedList(asList(losePlayer.name))
            )
            sendMail(areaCache, winPlayerId, mailInfoToPrison)

        } else {
            // 进入等待逃脱机制
            var isInChujueTime = false

            if (prisonTimeProto.timeType == WAIT_ESCAPE) {
                loseMainHero.mainHeroState = PRISON_ESCAP_FROM
            } else {
                isInChujueTime = true
                loseMainHero.mainHeroState = PRISON_AWAITING_EXECUTION
            }
            loseMainHero.mainHeroPrisonPlayerId = winPlayerId
            loseMainHero.mainHeroStateStartTime = nowMilliSec
            val minusTime = getResearchEffectValue(
                areaCache,
                NO_FILTER,
                winPlayer,
                ChangeKillTime
            )
            // time2：原本需要的时间-科技减免的时间
            var time2 = prisonTimeProto.time - minusTime
            if (time2 <= pcs.basicProtoCache.killWaitTimeMin) {
                time2 = pcs.basicProtoCache.killWaitTimeMin
            }
            if (isInChujueTime) {
                areaCache.heroCache.updateMainHeroStateEndTime(
                    loseMainHero,
                    nowMilliSec + 1000 * time2
                )
            } else {
                areaCache.heroCache.updateMainHeroStateEndTime(
                    loseMainHero,
//                nowMilliSec + 1000 * 120
                    nowMilliSec + 1000 * prisonTimeProto.time
                )
            }

            // 推送给被关方领主信息变化
            val loseSession = fetchOnlinePlayerSession(areaCache, losePlayerId)
            if (loseSession != null) {
                val valueChgNotifier = createValueChgNotifier()
                valueChgNotifier.append(
                    loseMainHero.id,
                    com.point18.slg2d.common.constg.MAIN_HERO_STATE,
                    loseMainHero.mainHeroState.toLong()
                )
                valueChgNotifier.append(
                    loseMainHero.id,
                    com.point18.slg2d.common.constg.MAIN_HERO_STATE_START_TIME,
                    loseMainHero.mainHeroStateStartTime / 1000
                )
                valueChgNotifier.append(
                    loseMainHero.id,
                    com.point18.slg2d.common.constg.MAIN_HERO_STATE_OVER_TIME,
                    loseMainHero.mainHeroStateEndTime / 1000
                )
                valueChgNotifier.append(
                    loseMainHero.id,
                    com.point18.slg2d.common.constg.MAIN_HERO_STATE_PRISON_PLAYERID,
                    loseMainHero.mainHeroPrisonPlayerId
                )
                valueChgNotifier.notice(loseSession)
            }

            // 维护关人者监狱缓存
            val newPrisonInfo = areaCache.prisonCache.createPrison(winPlayerId, losePlayerId)
            targetAddVal(areaCache, winPlayerId, CatchKingNum)
            targetAddVal(areaCache, losePlayerId, KingBeKillNum)

            // 推送给关人者监狱信息变化
            val winSession = fetchOnlinePlayerSession(areaCache, winPlayerId)
            if (winSession != null) {
                createPlayerPrisonChangeNotifier(areaCache, ADD_PRISON, newPrisonInfo)?.notice(winSession)
            }

            val castle = areaCache.castleCache.findCastleById(winPlayer.focusCastleId)
            if (castle == null) {
                normalLog.error("MainHeroPrisonModule.kt :   castle == null")
                return
            }
            noticeCellUpdate(areaCache, castle.x, castle.y)
        }
    }
}