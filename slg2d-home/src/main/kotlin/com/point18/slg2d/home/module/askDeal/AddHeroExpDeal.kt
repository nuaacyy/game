package com.point18.slg2d.home.module.askDeal

import com.point18.slg2d.common.constg.HERO_EXP
import com.point18.slg2d.common.constg.HERO_LV
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.syncHero2World
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.event.HeroUpFinishEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4server.AddHeroExpAskReq
import pb4server.AddHeroExpAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp

class AddHeroExpDeal : W2HAsk, HomeHelperPlus2<HomePlayerDC, HeroDC>(HomePlayerDC::class.java, HeroDC::class.java) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val internalMessage = req.addHeroExpAskReq

        prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC ->
            val rt = addHeroExp(homePlayerDC, heroDC, session, internalMessage)
            resp.setAddHeroExpAskRt(rt)
        }
    }

    private fun addHeroExp(
        homePlayerDC: HomePlayerDC,
        heroDC: HeroDC,
        session: PlayerActor,
        req: AddHeroExpAskReq
    ): AddHeroExpAskRt.Builder {
        val heroId = req.heroId
        val exp = req.addExp

        val rtBuilder = AddHeroExpAskRt.newBuilder()

        val hero = heroDC.findHeroById(heroId)
        if (hero == null) {
            rtBuilder.rt = ResultCode.NO_HERO.code
            return rtBuilder
        }

        val heroLv = hero.level
        var curExp = hero.exp
        val player = homePlayerDC.player

        val kingProto = pcs.kingExpCache.kingExpMap[player.kingLv]
        var maxLv = 0
        if (kingProto == null) {
            maxLv = pcs.kingExpCache.maxLvProto.heroLevelTop
        } else {
            maxLv = kingProto.heroLevelTop
        }
        if (heroLv >= maxLv) {
            rtBuilder.rt = ResultCode.SUCCESS.code
            return rtBuilder
        }

        var addExp = exp
        val oldLv = heroLv
        var newLv = heroLv

        for (i in 0 until 200) {
            if (addExp <= 0) {
                break
            }

            val expProto = pcs.heroLevelUpCache.fetchLevelUpProto(newLv)
            if (expProto == null) {
                break
            }

            val maxExp = expProto.exp
            if (curExp + addExp >= maxExp) {
                addExp -= (maxExp - curExp)
                curExp = 0
                newLv++
            } else {
                curExp += addExp
                addExp = 0
                break
            }

            if (newLv >= maxLv) {
                break
            }
        }

        // 更新数据
        hero.exp = curExp
        hero.level = newLv

        // 触发武将推送事件
        fireEvent(session, HeroUpFinishEvent(heroId, oldLv, hero.level))

        // 发送新的属性给客户端

        val valueChgNotifier = createValueChgNotifier()
        valueChgNotifier.append(heroId, HERO_LV, hero.level.toLong())
        valueChgNotifier.append(heroId, HERO_EXP, hero.exp.toLong())
        valueChgNotifier.notice(session)

        syncHero2World(session, hero)

        rtBuilder.rt = ResultCode.SUCCESS.code
        return rtBuilder
    }
}