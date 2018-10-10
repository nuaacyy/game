package com.point18.slg2d.home.module.heroInvincible

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_HERO_EQUIP_LV_UP
import com.point18.slg2d.common.constg.ACTION_NEWHERO_ARMY_LV_UP
import com.point18.slg2d.common.constg.HERO_POWER
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.common.checkHeroInvincibleCondition
import com.point18.slg2d.home.common.syncHero2World
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.HeroEquipLvUpEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4client.HeroEquipVo
import pb4client.InvincibleHeroEquipUp
import pb4client.InvincibleHeroEquipUpRt
import java.util.Arrays.asList

// 武将装备升级
class HeroEquipLvUpDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, HeroDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, HeroDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper, targetHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val heroId = (msg as InvincibleHeroEquipUp).heroId
        val heroTrophiesId = msg.heroTrophiesId
        prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC, homeMyTargetDC: HomeMyTargetDC ->
            val rt = heroEquipLvUp(
                session, heroId, heroTrophiesId, heroDC, homePlayerDC, homeMyTargetDC
            )
            session.sendMsg(MsgType.HeroEquipUp_1014, rt)
        }
    }

    private fun heroEquipLvUp(
        session: PlayerActor,
        heroId: Long,
        heroTrophiesId: Int,
        heroDc: HeroDC,
        homePlayerDC: HomePlayerDC,
        homeMyTargetDC: HomeMyTargetDC
    ): InvincibleHeroEquipUpRt {
        val rt = InvincibleHeroEquipUpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.heroId = heroId
        rt.heroTrophiesId = heroTrophiesId

        val action = ACTION_HERO_EQUIP_LV_UP
        val playerId = session.playerId

        // 检测主武将是否准确
        val hero = heroDc.findHeroById(heroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }
        val heroTrophiesVo = hero.heroEquipInfoMap[heroTrophiesId]
        if (heroTrophiesVo == null) {
            rt.rt = ResultCode.NO_FIND_HERO_EQUIP.code
            return rt.build()
        }

        val heroTrophiesProtoMap = pcs.heroTrophiesRankProtoCache.heroTrophiesRanksMap[heroTrophiesId]
        if (heroTrophiesProtoMap == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val heroTrophiesProto = heroTrophiesProtoMap[heroTrophiesVo.advLv]
        if (heroTrophiesProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val heroTrophiesProtoNext = heroTrophiesProtoMap[heroTrophiesVo.advLv + 1]
        if (heroTrophiesProtoNext == null) {
            rt.rt = ResultCode.HERO_EQUIP_MAX_LV.code
            return rt.build()
        }

        val checkResult = checkHeroInvincibleCondition(
            homePlayerDC.player,
            hero,
            heroTrophiesProto.heroTrophiesRequireConditionMap
        )
        if (!checkResult) {
            rt.rt = ResultCode.HERO_EQUIP_LV_UP_CONDITION_ERROR.code
            return rt.build()
        }

        // 资源检测
        if (heroTrophiesProto.heroTrophiesProps != "0") {
            val checkCost = resHelper.checkRes(session, heroTrophiesProto.heroTrophiesPropsResVo)
            if (!checkCost) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            // 扣除消耗
            resHelper.costRes(session, ACTION_NEWHERO_ARMY_LV_UP, homePlayerDC.player, heroTrophiesProto.heroTrophiesPropsResVo)
        }

        // 武将重新设置
        val heroEquipInfoMap = hero.heroEquipInfoMap[heroTrophiesId]
        if (heroEquipInfoMap != null) {
            heroEquipInfoMap.advLv = heroEquipInfoMap.advLv + 1
        }

        hero.heroStrength = targetHelper.calHeroPower(
            hero.protoId, hero.level, hero.advLv,
            hero.awake, hero.skillId1, hero.skillId1, hero.skillId1, hero.skillId1, hero.heroEquipInfoMap
        )

        for ((eId, eInfo) in hero.heroEquipInfoMap) {
            val heroEquipVo = HeroEquipVo.newBuilder()
            heroEquipVo.heroTrophiesId = eId
            heroEquipVo.advLv = eInfo.advLv
            rt.addHeroEquipVos(heroEquipVo)
        }

        // 发送新的属性给客户端
        val valueChgNotifier = createValueChgNotifier()
        valueChgNotifier.append(heroId, HERO_POWER, hero.heroStrength)
        valueChgNotifier.notice(session)

        homeMyTargetDC.targetInfo.heroEquipAdvNum++
        fireEvent(session, HeroEquipLvUpEvent())

        syncHero2World(session, hero)

        return rt.build()
    }

}