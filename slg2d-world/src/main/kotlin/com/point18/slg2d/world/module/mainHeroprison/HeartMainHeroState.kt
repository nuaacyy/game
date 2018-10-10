package com.point18.slg2d.world.module.mainHeroprison

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Hero
import com.point18.slg2d.world.area4data.fetchEpNo
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.module.walk.walkComm.mainHeroHome
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import java.util.*
import java.util.Arrays.asList

class MainHeroPrisonHeartHandler : IHeartHandler<AreaCache> {
    override fun handleHeart(cache: AreaCache) {
        var i = 0
        while (true) {
            val hero = cache.heroCache.peekMainHeroStateEndTimeFinish()
            if (hero == null) {
                // 没有需要处理的
                return
            }

            // 弹出目标元素
            cache.heroCache.popMainHeroStateEndTime()
            if (hero.mainHeroStateEndTime == 0L) {
                // 什么都不需要做
                continue
            }

            dealMainHeroState(cache, hero)

            i++
            if (i > 100000) {
                return
            }
        }
    }

    // 处理领主状态
    private fun dealMainHeroState(areaCache: AreaCache, hero: Hero) {
        val nowTime = getNowTime()

        // 根据当前领主的状态,选择不同的策略
        when {
            hero.mainHeroState == PRISON_ESCAP_FROM -> {
                // 关押时间结束,放回去了
                targetAddVal(areaCache, hero.mainHeroPrisonPlayerId, CatchKingEscapeNum)
                targetAddVal(areaCache, hero.playerId, KingEscapeNum)

                mainHeroFreedom(areaCache, hero)
                // 邮件通知
                val mailInfoToPrison = MailInfo(
                    TEXT_READ_LAN,
                    TITLE_LORD_RUN,
                    LinkedList(),
                    CONTENT_LORD_RUN,
                    LinkedList()
                )
                sendMail(areaCache, hero.playerId, mailInfoToPrison)
            }
            hero.mainHeroState == PRISON_AWAITING_EXECUTION -> {
                // 等待处决时间结束,即将进入处决时间
                val winPlayerId = hero.mainHeroPrisonPlayerId
                val losePlayerId = hero.playerId
                hero.mainHeroState = PRISON_EXECUTION
                hero.mainHeroStateStartTime = nowTime
                areaCache.heroCache.updateMainHeroStateEndTime(hero, nowTime + (pcs.basicProtoCache.killTime * 1000))

                // 发送app通知
                val losePlayer = areaCache.playerCache.findPlayerById(losePlayerId)
                if (losePlayer == null) {
                    assert(false) { "被关押所属玩家找不到了" }
                    return
                }
                areaCache.pushAppNotice(
                    hero.mainHeroPrisonPlayerId,
                    PRISON_EXECUTION_SETTING,
                    0,
                    losePlayer.name
                )

                // 维护关人者监狱缓存
                val delPrisonInfo =
                    areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(winPlayerId, hero.playerId)
                if (delPrisonInfo == null) {
                    assert(false) { "关押的对象找不到了" }
                    return
                }

                // 推送给关人者监狱信息变化
                val winSession = fetchOnlinePlayerSession(areaCache, winPlayerId)
                if (winSession != null) {
                    createPlayerPrisonChangeNotifier(areaCache, ADD_PRISON, delPrisonInfo)?.notice(winSession)
                }

                val winPlayer = areaCache.playerCache.findPlayerById(winPlayerId)
                if (winPlayer == null) {
                    assert(false) { "关押人找不到了" }
                    return
                }

                val castle = areaCache.castleCache.findCastleById(winPlayer.focusCastleId)
                if (castle == null) {
                    assert(false) { "关押人的主城找不到了" }
                    return
                }
                noticeCellUpdate(areaCache, castle.x, castle.y)

            }
            hero.mainHeroState == PRISON_EXECUTION -> {
                // 处决时间结束,自动放回去
                targetAddVal(areaCache, hero.mainHeroPrisonPlayerId, CatchKingEscapeNum)
                targetAddVal(areaCache, hero.playerId, KingEscapeNum)

                mainHeroFreedom(areaCache, hero)
            }
            hero.mainHeroState == PRISON_EAT_MUSHROOM -> {
                // 毒蘑菇毒发身亡,领主进入复活倒计时
                val winPlayerId = hero.mainHeroPrisonPlayerId
                hero.mainHeroState = PRISON_DIE
                hero.mainHeroStateStartTime = nowTime
                hero.mainHeroPrisonPlayerId = 0
                areaCache.heroCache.updateMainHeroStateEndTime(hero, nowTime + pcs.basicProtoCache.rebornTime * 1000)

                val winPlayer = areaCache.playerCache.findPlayerById(winPlayerId)
                if (winPlayer == null) {
                    assert(false) { "关押人找不到了" }
                    return
                }

                val losePlayer = areaCache.playerCache.findPlayerById(hero.playerId)
                if (losePlayer == null) {
                    assert(false) { "关押人找不到了" }
                    return
                }

                val castle = areaCache.castleCache.findCastleById(winPlayer.focusCastleId)
                if (castle == null) {
                    assert(false) { "关押人的主城找不到了" }
                    return
                }

                // 维护关人者监狱缓存
                val delPrisonInfo =
                    areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(winPlayerId, hero.playerId)
                if (delPrisonInfo == null) {
                    assert(false) { "关押的对象找不到了" }
                    return
                }

                // 推送给被关方领主信息变化
                val loseSession = fetchOnlinePlayerSession(areaCache, hero.playerId)
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
                    valueChgNotifier.append(hero.id, MAIN_HERO_STATE_PRISON_PLAYERID, 0)
                    valueChgNotifier.notice(loseSession)
                }

                areaCache.prisonCache.deletePrison(delPrisonInfo)

                // 推送给关人者监狱信息变化
                val winSession = fetchOnlinePlayerSession(areaCache, winPlayerId)
                if (winSession != null) {
                    val notifier = createPlayerPrisonChangeNotifier(
                        areaCache,
                        REMOVE_PRISON,
                        delPrisonInfo
                    )
                    if (notifier != null) {
                        notifier.notice(winSession)
                    }
                }

                noticeCellUpdate(areaCache, castle.x, castle.y)

                // 发邮件通知
                val mailInfoToPlayer = MailInfo(
                    TEXT_READ_LAN,
                    TITLE_POISON_DIE,
                    LinkedList(),
                    CONTENT_POISON_DIE,
                    LinkedList(asList(losePlayer.name))
                )
                sendMail(areaCache, winPlayerId, mailInfoToPlayer)

                val mailInfoToPrison = MailInfo(
                    TEXT_READ_LAN,
                    TITLE_POISON_LORD_DIE,
                    LinkedList(),
                    CONTENT_POISON_LORD_DIE,
                    LinkedList()
                )
                sendMail(areaCache, hero.playerId, mailInfoToPrison)

            }
            hero.mainHeroState == PRISON_DIE -> {
                // 死亡时间结束,领主复活
                hero.mainHeroState = CAN_RESURGENCE
                hero.mainHeroStateStartTime = 0
                areaCache.heroCache.updateMainHeroStateEndTime(hero, 0)
            }

            // 推送给被关方领主信息变化
        }

        // 推送给被关方领主信息变化
        val loseSession = fetchOnlinePlayerSession(areaCache, hero.playerId)
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
            valueChgNotifier.notice(loseSession)
        }
    }

    private fun mainHeroFreedom(areaCache: AreaCache, hero: Hero) {
        val winPlayerId = hero.mainHeroPrisonPlayerId
        val winPlayer = areaCache.playerCache.findPlayerById(winPlayerId)
        if (winPlayer == null) {
            assert(false) { "关押人找不到了" }
            return
        }
        val castle = areaCache.castleCache.findCastleById(winPlayer.focusCastleId)
        if (castle == null) {
            assert(false) { "关押人的主城找不到了" }
            return
        }

        //===========================================行军线回家========================================================
        hero.mainHeroState = MAIN_HERO
        hero.mainHeroPrisonPlayerId = 0
        hero.mainHeroStateStartTime = 0
        areaCache.heroCache.updateMainHeroStateEndTime(hero, 0)

        val loseSession = fetchOnlinePlayerSession(areaCache, hero.playerId)
        if (loseSession != null) {
            val valueChgNotifier = createValueChgNotifier()
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE, hero.mainHeroState.toLong())
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

        //领主回家 维护关人者监狱缓存
        mainHeroHome(areaCache, castle.x, castle.y, hero.playerId)
        val delPrisonInfo = areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(winPlayerId, hero.playerId)
        if (delPrisonInfo == null) {
            assert(false) { "被关押人找不到了" }
            return
        }
        areaCache.prisonCache.deletePrison(delPrisonInfo)

        // 推送给关人者监狱信息变化
        val winSession = fetchOnlinePlayerSession(areaCache, winPlayerId)
        if (winSession != null) {
            val winEpNo = fetchEpNo(areaCache, winPlayerId)
            val notifier =
                createPlayerPrisonChangeNotifier(areaCache, REMOVE_PRISON, delPrisonInfo)
            if (notifier != null) {
                notifier.notice(winSession)
            }
        }

        noticeCellUpdate(areaCache, castle.x, castle.y)
    }
}



