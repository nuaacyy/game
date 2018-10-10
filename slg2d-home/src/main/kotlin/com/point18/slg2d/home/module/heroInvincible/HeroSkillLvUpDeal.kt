package com.point18.slg2d.home.module.heroInvincible

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.HeroSkillLvUpEvent
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4client.InvincibleHeroSkillLvUp
import pb4client.InvincibleHeroSkillLvUpRt
import java.util.Arrays.asList

// 武将技能升级
class HeroSkillLvUpDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val refreshRes: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, HeroDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, HeroDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper, targetHelper, effectHelper, refreshRes)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC, homeMyTargetDC: HomeMyTargetDC ->
            val heroId = (msg as InvincibleHeroSkillLvUp).heroId
            val skillIndex = msg.skillIndex
            val rt = heroSkillLvUp(
                session, heroId, skillIndex, heroDC, homePlayerDC, homeMyTargetDC
            )
            session.sendMsg(MsgType.HeroSkillLvUp_1015, rt)
        }

    }

    private fun heroSkillLvUp(
        session: PlayerActor, heroId: Long, skillIndex: Int,
        heroDc: HeroDC, homePlayerDC: HomePlayerDC, homeMyTargetDC: HomeMyTargetDC
    ): InvincibleHeroSkillLvUpRt {
        val rt = InvincibleHeroSkillLvUpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.heroId = heroId
        rt.skillIndex = skillIndex

        val action = ACTION_HERO_SKILL_LV_UP

        // 检测主武将是否准确
        val hero = heroDc.findHeroById(heroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }

        if (skillIndex < 1 || skillIndex > 4) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        var skillId = 0 // 要升级的技能ID

        if (skillIndex == 1) {
            skillId = hero.skillId1
        } else if (skillIndex == 2) {
            skillId = hero.skillId2
        } else if (skillIndex == 3) {
            skillId = hero.skillId3
        } else if (skillIndex == 4) {
            skillId = hero.skillId4
        }

        // 检测配置
        val skillInfo = pcs.heroSkillProtoCache.heroSkillMap[skillId]
        if (skillInfo == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val nextSkillProtoMap = pcs.heroSkillProtoCache.heroSkillStudyMap[skillInfo.skillId]
        if (nextSkillProtoMap == null) {
            rt.rt = ResultCode.SKILLUP_MAX_ERROR.code
            return rt.build()
        }

        val nextSkillProto = nextSkillProtoMap[skillInfo.level + 1]
        if (nextSkillProto == null) {
            rt.rt = ResultCode.SKILLUP_MAX_ERROR.code
            return rt.build()
        }

        // 检测该技能当前武将阶级是否可以升级
        val heroRankProtoMap = pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId]
        if (heroRankProtoMap == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val heroRankProto = heroRankProtoMap[hero.awake]
        if (heroRankProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val maxLv = heroRankProto.rpgSkillMaxMap[skillIndex]
        if (maxLv == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        if (skillInfo.level >= maxLv) {
            rt.rt = ResultCode.HERO_SKILL_LVUP_SUPER_LV_ERROR.code
            return rt.build()
        }

        // 消耗资源
        val costs = ResVo(RES_COIN, NOT_PROPS_SUB_TYPE, skillInfo.costGold.toLong())
        val checkTrainCost = resHelper.checkRes(session, costs)
        if (!checkTrainCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        // 扣除消耗
        resHelper.costRes(session, action, homePlayerDC.player, costs)

        // 修改数据
        if (skillIndex == 1) {
            hero.skillId1 = nextSkillProto.id
        } else if (skillIndex == 2) {
            hero.skillId2 = nextSkillProto.id
        } else if (skillIndex == 3) {
            hero.skillId3 = nextSkillProto.id
        } else if (skillIndex == 4) {
            hero.skillId4 = nextSkillProto.id
        }

        hero.heroStrength = targetHelper.calHeroPower(
            hero.protoId, hero.level, hero.advLv, hero.awake,
            hero.skillId1, hero.skillId2, hero.skillId3, hero.skillId4, hero.heroEquipInfoMap
        )

        //// 发送新的属性给客户端
        val valueChgNotifier = createValueChgNotifier()

        if (skillIndex == 1) {
            valueChgNotifier.append(heroId, HERO_SKILL_1, hero.skillId1.toLong())
        } else if (skillIndex == 2) {
            valueChgNotifier.append(heroId, HERO_SKILL_2, hero.skillId2.toLong())
        } else if (skillIndex == 3) {
            valueChgNotifier.append(heroId, HERO_SKILL_3, hero.skillId3.toLong())
        } else if (skillIndex == 4) {
            valueChgNotifier.append(heroId, HERO_SKILL_4, hero.skillId4.toLong())
        }
        valueChgNotifier.append(heroId, HERO_POWER, hero.heroStrength)

        valueChgNotifier.notice(session)

        val effectIds = getNeedRefreshType(hero)

        fireEvent(session, ResearchEffectChangeEvent(effectIds, targetHelper, effectHelper, refreshRes))

        homeMyTargetDC.targetInfo.heroSkillLvUpNum++
        fireEvent(session, HeroSkillLvUpEvent())

        syncHero2World(session, hero)
        return rt.build()
    }

}
