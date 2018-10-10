package com.point18.slg2d.home.module.heroInvincible

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.MailHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.common.syncHero2World
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.HeroStarUpEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4client.InvincibleHeroGetStarLvUp
import pb4client.InvincibleHeroGetStarLvUpRt
import java.util.*
import java.util.Arrays.asList

// 领取觉醒
class HeroGetStarLvUpDeal(
    private val mailHelper: MailHelper = MailHelper(),
    private val targetHelper: TargetHelper = TargetHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HeroDC>(HeroDC::class.java, asList(mailHelper, targetHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { heroDc: HeroDC ->
            val heroId = (msg as InvincibleHeroGetStarLvUp).heroId
            val rt = getStarLvUp(session, heroId, heroDc)
            session.sendMsg(MsgType.HeroGetStarLvUp_1019, rt)
        }
    }

    private fun getStarLvUp(
        session: PlayerActor,
        heroId: Long,
        heroDc: HeroDC
    ): InvincibleHeroGetStarLvUpRt {
        val rt = InvincibleHeroGetStarLvUpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // 检测武将是否准确
        val hero = heroDc.findHeroById(heroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }

        // 检测武将是否在升星中
        if (hero.starUpEndTime == 0L) {
            rt.rt = ResultCode.HERO_CANCEL_STAR_LVUP_ERROR.code
            return rt.build()
        }

        // 是否完成升星
        val nowTime = getNowTime()
        if (hero.starUpEndTime > nowTime) {
            rt.rt = ResultCode.KING_EQUIP_TIME_NO_OVER_ERROR.code
            return rt.build()
        }

        //更改数据
        hero.starUpEndTime = 0
        hero.advLv += 1
        hero.heroStrength = targetHelper.calHeroPower(
            hero.protoId, hero.level, hero.advLv, hero.awake, hero.skillId1,
            hero.skillId2, hero.skillId3, hero.skillId4, hero.heroEquipInfoMap
        )

        //刷新英雄实力
        targetHelper.targetAddVal(session, com.point18.slg2d.common.constg.HeroStrength)

        // 触发武将推送事件
        fireEvent(session, HeroStarUpEvent(hero.id, hero.awake - 1, hero.awake))

        // 邮件
        val titleParams = LinkedList(asList(hero.protoId.toString(), hero.advLv.toString()))
        val messageParams = LinkedList(asList(hero.protoId.toString(), hero.advLv.toString()))
        val mailInfo = MailInfo(TEXT_READ_LAN, STAR_MAIL_TITLE, titleParams, STAR_MAIL, messageParams)
        mailHelper.sendMail(session, hero.playerId, mailInfo)

        // 推送
        val valueChgNotifier = createValueChgNotifier()
        valueChgNotifier.append(hero.id, HERO_STAR_LV, hero.advLv.toLong())
        valueChgNotifier.append(hero.id, HERO_STAR_LV_UP_ENDTIME_CHANGE, 0)
        valueChgNotifier.append(hero.id, HERO_POWER, hero.heroStrength)
        valueChgNotifier.notice(session)

        syncHero2World(session, hero)

        return rt.build()
    }
}
