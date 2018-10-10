package com.point18.slg2d.home.module.heroInvincible

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.HeroStarUpEvent
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4client.InvincibleHeroStarLvUp
import pb4client.InvincibleHeroStarLvUpRt
import java.util.Arrays.asList

// 武将升级
class HeroStarLvUpDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val refreshRes: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, HeroDC>(
    HomePlayerDC::class.java, HeroDC::class.java,
    asList(resHelper, targetHelper, effectHelper, refreshRes)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC ->
            val heroId = (msg as InvincibleHeroStarLvUp).heroId
            val rt = heroStarLvUp(session, heroId, heroDC, homePlayerDC)
            session.sendMsg(MsgType.HeroStarLvUp_1012, rt)
        }
    }

    private fun heroStarLvUp(session: PlayerActor, heroId: Long, heroDc: HeroDC, homePlayerDC: HomePlayerDC): InvincibleHeroStarLvUpRt {
        val rt = InvincibleHeroStarLvUpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.heroId = heroId
        rt.endTime = 0

        val action = ACTION_NEWHERO_STAR_LV_UP

        // 检测主武将是否准确
        val hero = heroDc.findHeroById(heroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }

        // 检测武将状态
        if (hero.starUpEndTime != 0L) {
            rt.rt = ResultCode.HERO_STARLV_IN.code
            return rt.build()
        }

        // 检测配置
        val nnProtoMap = pcs.heroStarProtoCache.heroStarProtoCache[hero.protoId]
        if (nnProtoMap == null) {
            rt.rt = ResultCode.HERO_STAR_MAX_LV.code
            return rt.build()
        }

        val nnProto = nnProtoMap[hero.advLv + 1]
        if (nnProto == null) {
            rt.rt = ResultCode.HERO_STAR_MAX_LV.code
            return rt.build()
        }

        val heroStarProtoMap = pcs.heroStarProtoCache.heroStarProtoCache[hero.protoId]
        if (heroStarProtoMap == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val heroStarProto = heroStarProtoMap[hero.advLv]
        if (heroStarProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        // 检测队列
        if (heroStarProto.time != 0) {
            val inHero = heroDc.findHeroInStarLvUp()
            if (inHero.size != 0) {
                rt.rt = ResultCode.HERO_STAR_LV_UP_QUEUE_ERROR.code
                return rt.build()
            }
        }

        // 消费
        if (heroStarProto.starProps != "0") {
            val checkCost = resHelper.checkRes(session, heroStarProto.starPropsResVo)
            if (!checkCost) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            // 扣除消耗
            resHelper.costRes(session, action, homePlayerDC.player, heroStarProto.starPropsResVo)
        }

        // 数据修改
        val valueChgNotifier = createValueChgNotifier()
        if (heroStarProto.time == 0) {
            // 不要时间的
            hero.advLv += 1
            hero.heroStrength = targetHelper.calHeroPower(
                hero.protoId, hero.level, hero.advLv,
                hero.awake, hero.skillId1, hero.skillId2, hero.skillId3, hero.skillId4, hero.heroEquipInfoMap
            )

            //刷新英雄实力
            targetHelper.targetAddVal(session, HeroStrength, null)

            // 触发武将推送事件
            fireEvent(session, HeroStarUpEvent(hero.id, hero.awake - 1, hero.awake))

            // 发送新的属性给客户端
            valueChgNotifier.append(heroId, HERO_STAR_LV, hero.advLv.toLong())
            valueChgNotifier.append(heroId, HERO_POWER, hero.heroStrength)

        } else {
            // 要时间的

            val nowTime = getNowTime()
            val endTime = nowTime + (heroStarProto.time * 1000)
            hero.starUpEndTime = endTime
            //areaData.updateStarUpEndTime(areaCache, hero, endTime)
            rt.endTime = (endTime / 1000).toInt()

            valueChgNotifier.append(heroId, HERO_STAR_LV_UP_ENDTIME_CHANGE, hero.starUpEndTime)
        }

        valueChgNotifier.notice(session)

        val effectIds = getNeedRefreshType(hero)

        fireEvent(session, ResearchEffectChangeEvent(effectIds, targetHelper, effectHelper, refreshRes))

        syncHero2World(session, hero)

        return rt.build()
    }

}
