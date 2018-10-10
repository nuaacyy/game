package com.point18.slg2d.world.module.mainHeroprison

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Prison
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.GetRewardGold
import com.point18.slg2d.world.event.RescueCastlePrison
import com.point18.slg2d.world.module.walk.walkComm.mainHeroHome
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import com.point18.slg2d.world.wpm
import java.util.*
import java.util.Arrays.asList

class RescuePrisonEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val winPlayerId = (event as RescueCastlePrison).atkPlayerId
        val losePlayerId = event.defPlayerId
        dealRescueAllPrison(cache, winPlayerId, losePlayerId)
    }

    //处理解救监禁的领主
    private fun dealRescueAllPrison(areaCache: AreaCache, winPlayerId: Long, losePlayerId: Long) {
        var totalRewardGold = 0L

        val allPrison = areaCache.prisonCache.findPrisonsByPlayerId(losePlayerId)
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

        val session = fetchOnlinePlayerSession(areaCache, losePlayerId)
        val castle = areaCache.castleCache.findCastleById(losePlayer.focusCastleId)
        if (castle == null) {
            normalLog.error("MainHeroPrisonModule.kt :   castle == null")
            return
        }

        if (losePlayer.maxLvPrisonBuffEndTime != 0L) {
            // 被打破后 buff 没有了
            val prison = Prison(
                -1,
                0,
                -1,
                0,
                0,
                losePlayer.id
            ) // 这是一个buff，作为填充
            areaCache.playerCache.updatePlayerMaxLvPrisonBuffEndTime(losePlayer, 0)
            if (session != null) {
                val notifier =
                    createPlayerPrisonChangeNotifier(areaCache, REMOVE_PRISON, prison)
                if (notifier != null) {
                    notifier.notice(session)
                }
            }
        }

        for (prison in allPrison) {

            totalRewardGold += prison.rewardGold

            //===========================================行军线回家========================================================
            val prisonPlayer = areaCache.playerCache.findPlayerById(prison.prisonPlayerId)
            if (prisonPlayer == null) {
                normalLog.error("error:MainHeroPrisonModule.kt :   prisonPlayer == null")
                return
            }
            val hero = areaCache.heroCache.findHeroById(prisonPlayer.id, prisonPlayer.mainHeroId)
            if (hero == null) {
                normalLog.error("error:MainHeroPrisonModule.kt :   hero == null")
                return
            }
            hero.mainHeroState = MAIN_HERO
            hero.mainHeroPrisonPlayerId = 0
            hero.mainHeroStateStartTime = 0
            areaCache.heroCache.updateMainHeroStateEndTime(hero, 0)
            hero.posState = OUT_CITY

            val prisonSession = fetchOnlinePlayerSession(areaCache, prison.prisonPlayerId)
            if (prisonSession != null) {
                val valueChgNotifier = createValueChgNotifier()
                valueChgNotifier.append(hero.id, MAIN_HERO_STATE, hero.mainHeroState.toLong())
                valueChgNotifier.append(hero.id, MAIN_HERO_STATE_START_TIME, hero.mainHeroStateStartTime / 1000)
                valueChgNotifier.append(hero.id, MAIN_HERO_STATE_OVER_TIME, hero.mainHeroStateEndTime / 1000)
                valueChgNotifier.append(hero.id, MAIN_HERO_STATE_PRISON_PLAYERID, hero.mainHeroPrisonPlayerId)
                valueChgNotifier.append(hero.id, HERO_POS_STATE, hero.posState.toLong())
                valueChgNotifier.notice(prisonSession)
            }

            //领主回家,维护关人者监狱缓存
            mainHeroHome(
                areaCache,
                castle.x,
                castle.y,
                prison.prisonPlayerId
            )
            areaCache.prisonCache.deletePrison(prison)

            // 推送给关人者监狱信息变化
            if (session != null) {
                val notifier =
                    createPlayerPrisonChangeNotifier(areaCache, REMOVE_PRISON, prison)
                if (notifier != null) {
                    notifier.notice(session)
                }
            }

            // 邮件通知
            val mailInfoToPrison = MailInfo(
                TEXT_READ_LAN,
                TITLE_LORD_WAR_RELEASE,
                LinkedList(),
                CONTENT_LORD_WAR_RELEASE,
                LinkedList(asList(losePlayer.name))
            )
            sendMail(areaCache, prison.prisonPlayerId, mailInfoToPrison)
        }

        //刷新地块
        noticeCellUpdate(areaCache, castle.x, castle.y)

        //添加赏金奖励
        if (totalRewardGold > 0) {
            // 交税
            val tax = totalRewardGold * pcs.basicProtoCache.commissionRate / 10000
            totalRewardGold -= tax
            addResToHome(
                areaCache,
                ACTION_RESCUE_PRISON,
                winPlayer.id,
                LinkedList<ResVo>(asList(ResVo(RES_COIN, NOT_PROPS_SUB_TYPE, totalRewardGold)))
            )

            // 邮件通知奖励
            val mailInfoToPrison = MailInfo(
                TEXT_READ_LAN,
                TITLE_REWARD_GET,
                LinkedList(),
                CONTENT_REWARD_GET,
                LinkedList(asList(losePlayer.name, "$totalRewardGold", "$tax"))
            )
            sendMail(areaCache, winPlayerId, mailInfoToPrison)

            targetAddVal(areaCache, winPlayerId, GetKingRewardNum, asList(totalRewardGold))
        }

        wpm.es.fire(
            areaCache, 0, GET_RESCUE_PRISON_REWARD,
            GetRewardGold(totalRewardGold)
        )

    }
}