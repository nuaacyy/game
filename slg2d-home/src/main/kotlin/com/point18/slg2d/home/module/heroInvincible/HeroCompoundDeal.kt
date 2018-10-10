package com.point18.slg2d.home.module.heroInvincible

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_COMPOUND_HERO
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.Hero
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GainHeroEvent
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.HeroCompound
import pb4client.HeroCompoundRt
import java.util.*
import java.util.Arrays.asList

// 武将合成
class HeroCompoundDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val refreshResourceHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val researchEffectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, HeroDC>(
    HomePlayerDC::class.java, HeroDC::class.java,
    asList(resHelper, targetHelper, refreshResourceHelper, researchEffectHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC ->
            val heroId = (msg as HeroCompound).heroId
            val rt = heroCompound(session, heroId, homePlayerDC, heroDC)
            session.sendMsg(MsgType.HeroCompound_670, rt)
        }

    }

    private fun heroCompound(
        session: PlayerActor, heroId: Int,
        homePlayerDC: HomePlayerDC, heroDC: HeroDC
    ): HeroCompoundRt {
        val rt = HeroCompoundRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player
        val heroProto = pcs.unitBaseCache.fetchProtoById(heroId)

        // 验证配置
        if (heroProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        // 验证资源
        val checkCost = resHelper.checkRes(session, heroProto.fuseChipRes)
        if (!checkCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        val heroList = LinkedList<Hero>()
        val hero = heroDC.createHero(player.playerId, 0, heroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        syncHero2World(session, hero)

        resHelper.costRes(session, ACTION_COMPOUND_HERO, player, heroProto.fuseChipRes)

        //刷新英雄实力
        targetHelper.targetAddVal(session, com.point18.slg2d.common.constg.HeroStrength)
        targetHelper.refreshAllHeroPower(session, false)

        // 推送给客户端
        heroList.add(hero)
        sendHeroListInfo(session, heroList)

        val effectIds = getNeedRefreshType(hero)
        fireEvent(
            session,
            ResearchEffectChangeEvent(
                effectIds, targetHelper,
                researchEffectHelper, refreshResourceHelper
            )
        )

        fireEvent(session, GainHeroEvent(hero.id))

        return rt.build()
    }
}


