package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.event.KillPrison
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.KillPrisonAskRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.common.noticeCellUpdate

class KillPrisonDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val costOrNot = req.killPrisonAskReq.costOrNot
        val prisonPlayerId = req.killPrisonAskReq.prisonPlayerId
        val rt = KillPrisonAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val prisonInfo =
            areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(req.playerId, prisonPlayerId)
        if (prisonInfo == null) {
            rt.rt = ResultCode.NO_FIND_PRISON_PLAYER.code
            resp.setKillPrisonAskRt(rt)
            return
        }

        val prisonPlayer = areaCache.playerCache.findPlayerById(prisonInfo.prisonPlayerId)
        if (prisonPlayer == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setKillPrisonAskRt(rt)
            return
        }

        val hero = areaCache.heroCache.findHeroById(prisonPlayer.id, prisonPlayer.mainHeroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            resp.setKillPrisonAskRt(rt)
            return
        }

        val player = areaCache.playerCache.findPlayerById(req.playerId)
        if (player == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setKillPrisonAskRt(rt)
            return
        }

        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            rt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            resp.setKillPrisonAskRt(rt)
            return
        }

        // 判断是否在毒蘑菇期间,杀人消耗有所不同
        if (hero.mainHeroState == PRISON_EXECUTION && costOrNot == 0) {
            // 免费杀
            player.prisonKillNum = player.prisonKillNum + 1

        } else if (
            (hero.mainHeroState == PRISON_AWAITING_EXECUTION
                || hero.mainHeroState == PRISON_EAT_MUSHROOM)
            && costOrNot == 1
        ) {
            player.prisonKillNum = player.prisonKillNum + 1
        } else {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setKillPrisonAskRt(rt)
            return
        }

        val nowTime = getNowTime()
        hero.mainHeroState = PRISON_DIE
        hero.mainHeroPrisonPlayerId = 0
        hero.mainHeroStateStartTime = nowTime
        val stateEndTime = nowTime + pcs.basicProtoCache.rebornTime * 1000
        areaCache.heroCache.updateMainHeroStateEndTime(hero, stateEndTime)

        // 推送给被关方领主信息变化
        val loseSession = fetchOnlinePlayerSession(areaCache, hero.playerId)
        if (loseSession != null) {
            val valueChgNotifier = createValueChgNotifier()
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE, hero.mainHeroState.toLong())
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE_START_TIME, hero.mainHeroStateStartTime / 1000)
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE_OVER_TIME, hero.mainHeroStateEndTime / 1000)
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE_PRISON_PLAYERID, hero.mainHeroPrisonPlayerId)
            valueChgNotifier.notice(loseSession)
        }

        // 维护关人者监狱缓存
        areaCache.prisonCache.deletePrison(prisonInfo)

        // 推送给关人者监狱信息变化
        val winSession = fetchOnlinePlayerSession(areaCache, req.playerId)
        if (winSession != null) {
            createPlayerPrisonChangeNotifier(areaCache, REMOVE_PRISON, prisonInfo)?.notice(winSession)
        }

        targetAddVal(areaCache, req.playerId, KillKingNum)
        targetAddVal(areaCache, prisonPlayer.id, KingBeKillNum)

        // 更新地图
        noticeCellUpdate(areaCache, castle.x, castle.y)

        //同步数据至home服
        syncData2Home(
            areaCache,
            prisonInfo.playerId,
            MaxLvInPrison,
            areaCache.prisonCache.findMaxLvInPrison(prisonInfo.playerId).toString()
        )
        resp.setKillPrisonAskRt(rt)

        wpm.es.fire(areaCache, player.id, KILL_PRISON, KillPrison())
    }
}