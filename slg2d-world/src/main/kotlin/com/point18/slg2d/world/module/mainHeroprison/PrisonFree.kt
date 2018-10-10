package com.point18.slg2d.world.module.mainHeroprison

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.walkComm.mainHeroHome
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4client.PrisonFree
import pb4client.PrisonFreeRt
import java.util.*
import java.util.Arrays.asList

// 释放玩家
class PrisonFreeDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val prisonPlayerId = (msg as PrisonFree).prisonPlayerId
        val rt = prisonFree(session, prisonPlayerId)
        session.sendMsg(MsgType.PrisonFree_1351, rt)
    }

    private fun prisonFree(session: PlayerSession, prisonPlayerId: Long): PrisonFreeRt {
        val rt = PrisonFreeRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.prisonPlayerId = prisonPlayerId
        val areaCache = session.areaCache
        val prisonInfo =
            areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(session.playerId, prisonPlayerId)

        if (prisonInfo == null) {
            rt.rt = ResultCode.NO_FIND_PRISON_PLAYER.code
            return rt.build()
        }

        val player = session.player
        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            rt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            return rt.build()
        }

        // 被监禁玩家ID
        val prisonPlayer = areaCache.playerCache.findPlayerById(prisonInfo.prisonPlayerId)
        if (prisonPlayer == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            return rt.build()
        }

        val hero = areaCache.heroCache.findHeroById(prisonPlayer.id, prisonPlayer.mainHeroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }
        hero.mainHeroState = MAIN_HERO
        hero.mainHeroPrisonPlayerId = 0
        hero.mainHeroStateStartTime = 0
        areaCache.heroCache.updateMainHeroStateEndTime(hero, 0)
        hero.posState = OUT_CITY

        val loseSession = fetchOnlinePlayerSession(areaCache, hero.playerId)
        if (loseSession != null) {
            val valueChgNotifier = createValueChgNotifier()
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE, hero.mainHeroState.toLong())
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE_START_TIME, hero.mainHeroStateStartTime/1000)
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE_OVER_TIME, hero.mainHeroStateEndTime/1000)
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE_PRISON_PLAYERID, hero.mainHeroPrisonPlayerId)
            valueChgNotifier.append(hero.id, HERO_POS_STATE, hero.posState.toLong())
            valueChgNotifier.notice(loseSession)
        }

        //领主回家
        mainHeroHome(
            areaCache,
            castle.x,
            castle.y,
            prisonInfo.prisonPlayerId
        )

        // 维护关人者监狱缓存
        areaCache.prisonCache.deletePrison(prisonInfo)

        // 推送给关人者监狱信息变化
        val winSession = fetchOnlinePlayerSession(areaCache, session.playerId)
        if (winSession != null) {
            val notifier = createPlayerPrisonChangeNotifier(areaCache, REMOVE_PRISON, prisonInfo)
            if (notifier != null) {
                notifier.notice(winSession)
            }
        }

        // 发送app通知
        areaCache.pushAppNotice(
            prisonInfo.prisonPlayerId,
            PRISON_RELEASE_SETTING,
            0
        )

        // 邮件通知
        val mailInfoToPrison = MailInfo(
            TEXT_READ_LAN,
            TITLE_LORD_RELEASE,
            LinkedList(),
            CONTENT_LORD_RELEASE,
            LinkedList(asList(player.name))
        )
        sendMail(areaCache, prisonInfo.prisonPlayerId, mailInfoToPrison)


        noticeCellUpdate(areaCache, castle.x, castle.y)

        //同步数据至home服
        syncData2Home(
            areaCache,
            prisonInfo.playerId,
            MaxLvInPrison,
            areaCache.prisonCache.findMaxLvInPrison(prisonInfo.playerId).toString()
        )

        //加入日志
        return rt.build()
    }

}

