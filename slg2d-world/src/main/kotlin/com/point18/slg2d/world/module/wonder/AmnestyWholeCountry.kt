package com.point18.slg2d.world.module.wonder

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.Prison
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.walkComm.mainHeroHome
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.msgnotice.createAmnestyCountChangeNotifier
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4client.AmnestyWholeCountryRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.*
import java.util.*

class AmnestyWholeCountryDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.amnestyWholeCountry(session) ?: return
        session.sendMsg(MsgType.AmnestyWholeCountry_1458, rt)
    }

    private fun amnestyWholeCountry(session: PlayerSession): AmnestyWholeCountryRt? {
        val rtBuilder = AmnestyWholeCountryRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        val areaCache = session.areaCache
        val playerId = session.playerId

        //校验权限
        val posId = findOfficeByPlayerId(areaCache, playerId)
        val ok = checkOfficeFunction(OfficeFunction.AmnestyWholeCountry, posId)
        if (!ok) {
            rtBuilder.rt = ResultCode.LIMIT_TO_AMNESTY_WHOLE_COUNTRY.code
            return rtBuilder.build()
        }

        val bigWonder = areaCache.wonderCache.findBigWonder()
        if (bigWonder == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }

        if (!isWonderPeace(bigWonder)) {
            rtBuilder.rt = ResultCode.WONDER_NOT_PEACE.code
            return rtBuilder.build()
        }

        //校验大赦天下剩余次数
        if (bigWonder.pardonCount <= 0) {
            rtBuilder.rt = ResultCode.NO_AMNESTY_COUNT.code
            return rtBuilder.build()
        }

        bigWonder.pardonCount--

        val notifier = createAmnestyCountChangeNotifier(bigWonder.pardonCount)
        notifier.notice(session)

        //释放所有监狱
        val allPrison = areaCache.prisonCache.findAllPrisons()
        val prisonMap = hashMapOf<Long, HashMap<Long, Prison>>()
        for (prison in allPrison) {
            val bePrisonPlayer = areaCache.playerCache.findPlayerById(prison.prisonPlayerId)
            if (bePrisonPlayer == null) {
                normalLog.error("找不到被监禁玩家的数据:%d", prison.prisonPlayerId)
                return null
            }
            val hero = areaCache.heroCache.findHeroById(bePrisonPlayer.id, bePrisonPlayer.mainHeroId)
            if (hero == null) {
                normalLog.error("找不到被监禁玩家的英雄数据:%d", bePrisonPlayer.mainHeroId)
                return null
            }

            hero.mainHeroState = MAIN_HERO
            hero.mainHeroPrisonPlayerId = 0
            hero.mainHeroStateStartTime = zeroTime.time
            areaCache.heroCache.updateMainHeroStateEndTime(hero, zeroTime.time)
            hero.posState = OUT_CITY

            val loseSession = fetchOnlinePlayerSession(areaCache, prison.prisonPlayerId)
            if (loseSession != null) {
                val loseNotice = createValueChgNotifier()
                loseNotice.append(hero.id, MAIN_HERO_STATE, hero.mainHeroState.toLong())
                loseNotice.append(hero.id, MAIN_HERO_STATE_START_TIME, hero.mainHeroStateStartTime / 1000)
                loseNotice.append(hero.id, MAIN_HERO_STATE_OVER_TIME, hero.mainHeroStateEndTime / 1000)
                loseNotice.append(hero.id, MAIN_HERO_STATE_PRISON_PLAYERID, hero.mainHeroPrisonPlayerId)
                loseNotice.append(hero.id, HERO_POS_STATE, hero.posState.toLong())
                loseNotice.notice(loseSession)
            }

            val p = prisonMap.getOrPut(prison.playerId) { hashMapOf() }
            p[prison.id] = prison
        }

        for ((prisonPlayerId, prisonInfo) in prisonMap) {
            val player = areaCache.playerCache.findPlayerById(prisonPlayerId)
            if (player == null) {
                normalLog.error("找不到监禁者的玩家数据:%d", prisonPlayerId)
                return null
            }
            val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
            if (castle == null) {
                normalLog.error("找不到监禁者的城池数据:%d", player.focusCastleId)
                return null
            }
            for ((_, prison) in prisonInfo) {
                //领主回家
                mainHeroHome(
                    areaCache,
                    castle.x,
                    castle.y,
                    prison.prisonPlayerId
                )

                // 维护关人者监狱缓存
                areaCache.prisonCache.deletePrison(prison)

                // 推送给关人者监狱信息变化
                val prisonSession = fetchOnlinePlayerSession(areaCache, prison.prisonPlayerId)
                if (prisonSession != null) {
                    createPlayerPrisonChangeNotifier(areaCache, REMOVE_PRISON, prison)?.notice(prisonSession)
                }

                //发送被释放邮件
                val mailInfo = MailInfo(
                    TEXT_READ_LAN,
                    com.point18.slg2d.common.constg.BE_AMNESTY_TITLE,
                    LinkedList(),
                    com.point18.slg2d.common.constg.BE_AMNESTY_CONTENT,
                    LinkedList()
                )
                sendMail(areaCache, prison.prisonPlayerId, mailInfo)
            }

            //刷新地块
            noticeCellUpdate(areaCache, castle.x, castle.y)

            //发送释放邮件
            val mailInfo = MailInfo(
                TEXT_READ_LAN,
                AMNESTY_WHOLE_COUNTRY_TITLE,
                LinkedList(),
                AMNESTY_WHOLE_COUNTRY_CONTENT,
                LinkedList()
            )
            sendMail(areaCache, prisonPlayerId, mailInfo)
        }

        return rtBuilder.build()
    }
}
