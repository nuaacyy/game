package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.module.walk.walkComm.mainHeroHome
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4server.GiveRansomAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import java.util.*
import java.util.Arrays.asList

class GiveRansomDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val num = req.giveRansomAskReq.num
        val rt = GiveRansomAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(req.playerId)
        if (player == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setGiveRansomAskRt(rt)
            return
        }
        val hero = areaCache.heroCache.findHeroById(player.id, player.mainHeroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            resp.setGiveRansomAskRt(rt)
            return
        }
        if (hero.mainHeroPrisonPlayerId == 0L) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setGiveRansomAskRt(rt)
            return
        }

        val prisonInfo =
            areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(hero.mainHeroPrisonPlayerId, req.playerId)

        if (prisonInfo == null) {
            rt.rt = ResultCode.NO_FIND_PRISON_PLAYER.code
            resp.setGiveRansomAskRt(rt)
            return
        }

        if (prisonInfo.ransom == 0L) {
            rt.rt = ResultCode.NO_SET_RANSOM.code
            resp.setGiveRansomAskRt(rt)
            return
        }

        if (prisonInfo.ransom != num) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setGiveRansomAskRt(rt)
            return
        }

        // 关人方获得金钱
        val winPlayerId = hero.mainHeroPrisonPlayerId
        val winPlayer = areaCache.playerCache.findPlayerById(winPlayerId)
        if (winPlayer == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setGiveRansomAskRt(rt)
            return
        }

        val castle = areaCache.castleCache.findCastleById(winPlayer.focusCastleId)
        if (castle == null) {
            rt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            resp.setGiveRansomAskRt(rt)
            return
        }

        val reward = LinkedList<ResVo>()
        val rewardNum = (prisonInfo.ransom - prisonInfo.ransom * pcs.basicProtoCache.ransomRate / 10000)
        reward.add(
            ResVo(RES_COIN, NOT_PROPS_SUB_TYPE, rewardNum)
        )
        addResToHome(areaCache, ACTION_GO_RANSOM, winPlayer.id, reward)

        hero.mainHeroState = MAIN_HERO
        hero.mainHeroPrisonPlayerId = 0
        hero.mainHeroStateStartTime = 0
        areaCache.heroCache.updateMainHeroStateEndTime(hero, 0)
        hero.posState = OUT_CITY

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
        valueChgNotifier.append(hero.id, MAIN_HERO_STATE_PRISON_PLAYERID, hero.mainHeroPrisonPlayerId)
        valueChgNotifier.append(hero.id, HERO_POS_STATE, hero.posState.toLong())

        val worldSession = fetchOnlinePlayerSession(areaCache, req.playerId)
        if (worldSession != null) {
            valueChgNotifier.notice(worldSession)
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

        //同步数据至home服
        syncData2Home(
            areaCache,
            prisonInfo.playerId,
            MaxLvInPrison,
            areaCache.prisonCache.findMaxLvInPrison(prisonInfo.playerId).toString()
        )

        // 推送给关人者监狱信息变化
        val session = fetchOnlinePlayerSession(areaCache, winPlayerId)
        if (session != null) {
            val notifier = createPlayerPrisonChangeNotifier(areaCache, REMOVE_PRISON, prisonInfo)
            if (notifier != null) {
                notifier.notice(session)
            }
        }

        val mailInfoToPrison = MailInfo(
            TEXT_READ_LAN,
            TITLE_RANSOM_GET,
            LinkedList(),
            CONTENT_RANSOM_GET,
            LinkedList(asList("$rewardNum"))
        )
        sendMail(areaCache, winPlayerId, mailInfoToPrison)

        noticeCellUpdate(areaCache, castle.x, castle.y)

        resp.setGiveRansomAskRt(rt)
    }
}