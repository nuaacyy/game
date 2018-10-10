package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.ResurgenceAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class ResurgenceAtOnceDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val rt = ResurgenceAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(req.playerId)
        if (player == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setResurgenceAskRt(rt)
            return
        }

        val hero = areaCache.heroCache.findHeroById(player.id, player.mainHeroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            resp.setResurgenceAskRt(rt)
            return
        }

        if (hero.mainHeroState != PRISON_DIE) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setResurgenceAskRt(rt)
            return
        }

        hero.mainHeroState = MAIN_HERO
        hero.mainHeroStateStartTime = 0
        areaCache.heroCache.updateMainHeroStateEndTime(hero, 0)

        // 推送给被关方领主信息变化
        val loseSession = fetchOnlinePlayerSession(areaCache, hero.playerId)
        if (loseSession != null) {
            val valueChgNotifier = createValueChgNotifier()
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE, hero.mainHeroState.toLong())
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE_START_TIME, hero.mainHeroStateStartTime / 1000)
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE_OVER_TIME, hero.mainHeroStateEndTime / 1000)
            valueChgNotifier.notice(loseSession)
        }

        resp.setResurgenceAskRt(rt)
        return
    }
}