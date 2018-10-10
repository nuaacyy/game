package com.point18.slg2d.home.module.heroInvincible

import com.point18.slg2d.common.constg.RANK_MAIL
import com.point18.slg2d.common.constg.RANK_MAIL_TITLE
import com.point18.slg2d.common.constg.TEXT_READ_LAN
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.MailHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.common.syncHero2World
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IHeartDeal
import com.point18.slg2d.home.module.event.HeroSuperUpEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import java.util.*
import java.util.Arrays.asList

class HeartHeroSuperUp(
    private val mailHelper: MailHelper = MailHelper(),
    private val targetHelper: TargetHelper = TargetHelper()
) : IHeartDeal, HomeHelperPlus1<HeroDC>(
    HeroDC::class.java,
    asList(mailHelper, targetHelper)
) {

    override fun dealHeart(session: PlayerActor, actionId: Long, onComplete: (rt: Int) -> Unit) {
        prepare(session) { heroDC: HeroDC ->
            val rt = heroSuperUp(heroDC, session, actionId)
            onComplete(rt)
        }
    }

    // 武将升阶结束
    private fun heroSuperUp(heroDC: HeroDC, session: PlayerActor, actionId: Long): Int {
        val hero = heroDC.findHeroById(actionId)
        if (hero == null) {
            return ResultCode.PARAMETER_ERROR.code
        }
        hero.superUpEndTime = 0
        hero.awake += 1
        hero.heroStrength = targetHelper.calHeroPower(
            hero.protoId, hero.level, hero.advLv, hero.awake,
            hero.skillId1, hero.skillId2, hero.skillId3, hero.skillId4, hero.heroEquipInfoMap
        )

        //刷新英雄实力
        targetHelper.targetAddVal(session, com.point18.slg2d.common.constg.HeroStrength)

        // 触发武将推送事件
        fireEvent(session, HeroSuperUpEvent(hero.id, hero.awake - 1, hero.awake))

        // 邮件
        val heroRankProtoMap = pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId]
        if (heroRankProtoMap == null) {
            return ResultCode.NO_PROTO.code
        }
        val heroRankProto = heroRankProtoMap[hero.awake]
        if (heroRankProto == null) {
            return ResultCode.NO_PROTO.code
        }
        val titleParams = LinkedList(asList(hero.protoId.toString(), hero.awake.toString()))
        val messageParams = LinkedList(
            asList(
                hero.protoId.toString(),
                hero.awake.toString(),
                heroRankProto.solider.toString()
            )
        )
        val mailInfo = MailInfo(TEXT_READ_LAN, RANK_MAIL_TITLE, titleParams, RANK_MAIL, messageParams)
        mailHelper.sendMail(session, hero.playerId, mailInfo)

        // 推送
        val valueChgNotifier = createValueChgNotifier()
        valueChgNotifier.append(hero.id, com.point18.slg2d.common.constg.HERO_AWAKE, hero.awake.toLong())
        valueChgNotifier.append(hero.id, com.point18.slg2d.common.constg.HERO_SUPER_LV_UP_ENDTIME_CHANGE, 0)
        valueChgNotifier.append(hero.id, com.point18.slg2d.common.constg.HERO_POWER, hero.heroStrength)
        valueChgNotifier.notice(session)

        syncHero2World(session, hero)

        return ResultCode.SUCCESS.code
    }
}



