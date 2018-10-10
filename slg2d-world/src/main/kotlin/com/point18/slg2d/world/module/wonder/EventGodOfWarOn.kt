package com.point18.slg2d.world.module.wonder

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Prison
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.module.walk.walkComm.mainHeroHome
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import java.util.*

class GodOfWarOnEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        //释放所有监狱
        val allPrison = cache.prisonCache.findPrisonsByPlayerId(playerId)
        val prisonMap = hashMapOf<Long, HashMap<Long, Prison>>()
        for (prison in allPrison) {
            val bePrisonPlayer = cache.playerCache.findPlayerById(prison.prisonPlayerId)
            if (bePrisonPlayer == null) {
                normalLog.error("找不到被监禁玩家的数据:%d", prison.prisonPlayerId)
                return
            }
            val hero = cache.heroCache.findHeroById(bePrisonPlayer.id, bePrisonPlayer.mainHeroId)
            if (hero == null) {
                normalLog.error("找不到被监禁玩家的英雄数据:%d", bePrisonPlayer.mainHeroId)
                return
            }
            hero.mainHeroState = MAIN_HERO
            hero.mainHeroPrisonPlayerId = 0
            hero.mainHeroStateStartTime = zeroTime.time
            cache.heroCache.updateMainHeroStateEndTime(hero, zeroTime.time)
            hero.posState = OUT_CITY

            val loseSession = fetchOnlinePlayerSession(cache, prison.prisonPlayerId)
            if (loseSession != null) {
                val valueChgNotifier = createValueChgNotifier()
                valueChgNotifier.append(
                    hero.id,
                    MAIN_HERO_STATE,
                    hero.mainHeroState.toLong()
                )
                valueChgNotifier.append(
                    hero.id,
                    MAIN_HERO_STATE_START_TIME,
                    hero.mainHeroStateStartTime / 1000
                )
                valueChgNotifier.append(
                    hero.id,
                    MAIN_HERO_STATE_OVER_TIME,
                    hero.mainHeroStateEndTime / 1000
                )
                valueChgNotifier.append(
                    hero.id,
                    MAIN_HERO_STATE_PRISON_PLAYERID,
                    hero.mainHeroPrisonPlayerId
                )
                valueChgNotifier.append(hero.id, HERO_POS_STATE, hero.posState.toLong())
                valueChgNotifier.notice(loseSession)
            }

            val p = prisonMap.getOrPut(prison.playerId) { hashMapOf() }
            p[prison.id] = prison
        }

        for ((prisonPlayerId, prisonInfo) in prisonMap) {
            val player = cache.playerCache.findPlayerById(prisonPlayerId)
            if (player == null) {
                normalLog.error("找不到监禁者的玩家数据:%d", prisonPlayerId)
                return
            }
            val castle = cache.castleCache.findCastleById(player.focusCastleId)
            if (castle == null) {
                normalLog.error("找不到监禁者的城池数据:%d", player.focusCastleId)
                return
            }
            for ((_, prison) in prisonInfo) {
                //领主回家
                mainHeroHome(
                    cache,
                    castle.x,
                    castle.y,
                    prison.prisonPlayerId
                )

                // 维护关人者监狱缓存
                cache.prisonCache.deletePrison(prison)

                // 推送给关人者监狱信息变化
                val session = fetchOnlinePlayerSession(cache, prison.playerId)
                if (session != null) {
                    val notifier = createPlayerPrisonChangeNotifier(cache, REMOVE_PRISON, prison)
                    if (notifier != null) {
                        notifier.notice(session)
                    }
                }

                //发送被释放邮件
                val mailInfo = MailInfo(
                    TEXT_READ_LAN,
                    BE_AMNESTY_BY_WONDER_WAR_TITLE,
                    LinkedList(),
                    BE_AMNESTY_BY_WONDER_WAR_CONTENT,
                    LinkedList()
                )
                sendMail(cache, prison.prisonPlayerId, mailInfo)
            }

            //刷新地块
            noticeCellUpdate(cache, castle.x, castle.y)

            //发送释放邮件
            val mailInfo = MailInfo(
                TEXT_READ_LAN,
                AMNESTY_BY_WONDER_WAR_TITLE,
                LinkedList(),
                AMNESTY_BY_WONDER_WAR_CONTENT,
                LinkedList()
            )
            sendMail(cache, prisonPlayerId, mailInfo)
        }
    }
}