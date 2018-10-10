package com.point18.slg2d.world.module.intHero

import com.point18.slg2d.world.area4data.Hero
import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.HERO_INT_ID
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.SET_ARMY_PLAN
import com.point18.slg2d.common.constg.SET_CITY_DEF_HERO
import com.point18.slg2d.world.event.SetArmyPlanEvent
import com.point18.slg2d.world.event.SetCityDefHero
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4client.HeroPos
import pb4client.SetDefHere
import pb4client.SetDefHereRt
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

// 设置守城英雄
class SetDefHeroDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val heroList = LinkedList((msg as SetDefHere).heroIdsList)
        val rt = setDefHero(session, heroList)
        session.sendMsg(MsgType.SetDefHero_43, rt.build())
    }

    private fun setDefHero(session: PlayerSession, heroList: List<HeroPos>): (SetDefHereRt.Builder) {
        val rt = SetDefHereRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.addAllHeroIds(heroList)

        val areaCache = session.areaCache
        val playerId = session.playerId
        val player = session.player

        val mainHero = areaCache.heroCache.findHeroById(playerId, player.mainHeroId)
        if (mainHero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt
        }

        var containMainHero = false
        val allHero = LinkedList<Hero>()
        for (hId in heroList) {
            val hero = areaCache.heroCache.findHeroById(playerId, hId.heroId)
            if (hero == null) {
                rt.rt = ResultCode.NO_HERO.code
                return rt
            }

            allHero.add(hero)
            if (mainHero == hero) {
                containMainHero = true
            }
        }

        if (!containMainHero) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val changeHeroMap = hashMapOf<Long, Int>()
        // 把当前所有的守城武将设置成0
        val allDefHeroList = areaCache.heroCache.findAllDefHero(playerId)
        for (h in allDefHeroList) {
            com.point18.slg2d.world.area4data.updateIntAddressId(h, 0)
            changeHeroMap[h.id] = 0
        }

        for (hId in heroList) {
            val hero = allHero.firstOrNull { it.id == hId.heroId } ?: continue
            com.point18.slg2d.world.area4data.updateIntAddressId(hero, hId.pos)
            changeHeroMap[hero.id] = hId.pos
        }

        val heroChangeMsg = createValueChgNotifier()
        for ((heroId, pos) in changeHeroMap) {
            heroChangeMsg.append(heroId, HERO_INT_ID, pos.toLong())
        }
        heroChangeMsg.notice(session)

        wpm.es.fire(areaCache, playerId, SET_CITY_DEF_HERO, SetCityDefHero())
        wpm.es.fire(areaCache, playerId, SET_ARMY_PLAN, SetArmyPlanEvent())

        return rt
    }
}