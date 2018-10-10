package com.point18.slg2d.home.module.heroInvincible

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.HeroSuperUpEvent
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4client.InvincibleHeroSuperUp
import pb4client.InvincibleHeroSuperUpRt
import java.util.*
import java.util.Arrays.asList

// 武将升级
class HeroSuperLvUpDeal(
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val refreshRes: RefreshResourceHelper = RefreshResourceHelper(),
    private val mailHelper: MailHelper = MailHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HeroDC>(
    HeroDC::class.java, asList(targetHelper, effectHelper, refreshRes, mailHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { heroDC: HeroDC ->
            val heroId = (msg as InvincibleHeroSuperUp).heroId
            val rt = heroSuperLvUp(session, heroId, heroDC)
            session.sendMsg(MsgType.HeroSuperUp_1013, rt)
        }
    }

    private fun heroSuperLvUp(
        session: PlayerActor,
        heroId: Long,
        heroDC: HeroDC
    ): InvincibleHeroSuperUpRt {

        val rt = InvincibleHeroSuperUpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.heroId = heroId
        rt.endTime = 0

        // 检测主武将是否准确

        val hero = heroDC.findHeroById(heroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }

        // 检测武将状态
        if (hero.superUpEndTime != 0L) {
            rt.rt = ResultCode.HERO_SUPERLV_IN.code
            return rt.build()
        }

        // 检测武将装备是否满足要求
        for ((_, equip) in hero.heroEquipInfoMap) {
            if (equip.advLv <= hero.awake) {
                rt.rt = ResultCode.HERO_SUPERLV_EQUIP_ERROR.code
                return rt.build()
            }
        }

        // 检测配置
        val nnProtoMap = pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId]
        if (nnProtoMap == null) {
            rt.rt = ResultCode.HERO_SUPER_MAX_LV.code
            return rt.build()
        }

        val nnProto = nnProtoMap[hero.awake + 1]
        if (nnProto == null) {
            rt.rt = ResultCode.HERO_SUPER_MAX_LV.code
            return rt.build()
        }

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

        // 检测队列
        if (heroRankProto.time != 0) {
            val inHero = heroDC.findHeroInSuperLvUp()
            if (inHero.size != 0) {
                rt.rt = ResultCode.HERO_SUPER_LV_UP_QUEUE_ERROR.code
                return rt.build()
            }
        }

        val valueChgNotifier = createValueChgNotifier()

        // 数据修改
        if (heroRankProto.time == 0) {
            // 不要时间的
            hero.awake += 1
            hero.heroStrength = targetHelper.calHeroPower(
                hero.protoId, hero.level, hero.advLv,
                hero.awake, hero.skillId1, hero.skillId2, hero.skillId3, hero.skillId4, hero.heroEquipInfoMap
            )

            //刷新英雄实力
            targetHelper.targetAddVal(session, HeroStrength)

            // 触发武将推送事件
            fireEvent(session, HeroSuperUpEvent(hero.id, hero.awake - 1, hero.awake))

            // 发送新的属性给客户端
            valueChgNotifier.append(heroId, HERO_AWAKE, hero.awake.toLong())
            valueChgNotifier.append(heroId, HERO_POWER, hero.heroStrength)

            //发送升阶邮件
            val titleParams = LinkedList<String>(asList("${hero.protoId}", "${hero.awake}"))
            val messageParams = LinkedList(
                Arrays.asList(
                    hero.protoId.toString(),
                    hero.awake.toString(),
                    heroRankProto.solider.toString()
                )
            )
            val mailInfo = MailInfo(TEXT_READ_LAN, RANK_MAIL_TITLE, titleParams, RANK_MAIL, messageParams)
            mailHelper.sendMail(session, hero.playerId, mailInfo)
        } else {
            // 要时间的

            val nowTime = getNowTime()
            hero.superUpEndTime = nowTime + (heroRankProto.time * 1000)
            // todo
            forwardHeartDeal2World(session, CreateHeart, HeroSuperUp, hero.id, hero.superUpEndTime)

            rt.endTime = (hero.superUpEndTime / 1000).toInt()

            valueChgNotifier.append(heroId, HERO_SUPER_LV_UP_ENDTIME_CHANGE, hero.superUpEndTime)
        }

        valueChgNotifier.notice(session)
        val effectIds = getNeedRefreshType(hero)

        fireEvent(
            session,
            ResearchEffectChangeEvent(
                effectIds,
                targetHelper,
                effectHelper,
                refreshRes
            )
        )

        syncHero2World(session, hero)
        return rt.build()
    }

}
