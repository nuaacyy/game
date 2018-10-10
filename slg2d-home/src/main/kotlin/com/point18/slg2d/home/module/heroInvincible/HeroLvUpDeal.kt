package com.point18.slg2d.home.module.heroInvincible

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.common.syncHero2World
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.HeroUpFinishEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4client.InvincibleHeroLvUp
import pb4client.InvincibleHeroLvUpRt
import java.util.Arrays.asList

class HeroLvUpDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HeroDC, HomePlayerDC>(
    HeroDC::class.java, HomePlayerDC::class.java,
    asList(resHelper, targetHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { heroDC: HeroDC, homePlayerDC: HomePlayerDC ->
            val heroId = (msg as InvincibleHeroLvUp).heroId
            val lvNum = msg.lvNum
            val rt = heroLvUp(
                session,
                heroId,
                lvNum,
                heroDC,
                homePlayerDC
            )
            session.sendMsg(MsgType.HeroLvUp_1011, rt)
        }
    }

    private fun heroLvUp(
        session: PlayerActor, heroId: Long, lvNum: Int, heroDc: HeroDC, homePlayerDC: HomePlayerDC
    ): InvincibleHeroLvUpRt {
        val rt = InvincibleHeroLvUpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.heroId = heroId
        rt.lvNum = lvNum

        if (lvNum <= 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val action = ACTION_NEW_HERO_LV_UP

        // 检测主武将是否准确
        val hero = heroDc.findHeroById(heroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }

        if (hero.level >= pcs.basicProtoCache.heroMaxLv) {
            rt.rt = ResultCode.HERO_LV_MAX.code
            return rt.build()
        }

        var maxLv = 0
        val kingProto = com.point18.slg2d.common.pc.pcs.kingExpCache.kingExpMap[homePlayerDC.player.kingLv]
        if (kingProto == null) {
            maxLv = com.point18.slg2d.common.pc.pcs.kingExpCache.maxLvProto.heroLevelTop
        } else {
            maxLv = kingProto.heroLevelTop
        }


        if (hero.level >= maxLv) {
            rt.rt = ResultCode.HERO_LV_CAN_NO_KING_LV_ERROR.code
            return rt.build()
        }

        // 检测玩家能升多少
        var costExp = 0 // 已经使用了多少经验池了
        var addLv = 0   // 已经升了几级了
        var afterChangeExp = hero.exp
        for (i in 1..lvNum) {
            if (hero.level + i > pcs.basicProtoCache.heroMaxLv) {
                // 已经升到最高等级了
                break
            }

            if (hero.level + i > maxLv) {
                // 已经升到君主等级了
                break
            }

            val nnProto = pcs.heroLevelUpCache.protoMap[hero.level + i]
            if (nnProto == null) {
                // 找不到了,升到顶级了
                break
            }

            val heroLvProto = pcs.heroLevelUpCache.protoMap[hero.level + i - 1]
            if (heroLvProto == null) {
                // 找不到了,升到顶级了
                break
            }
            var needExp = heroLvProto.exp
            if (i == 1) {
                // 第一次进来是可以使用当前的经验的
                needExp -= hero.exp
            }
            if (homePlayerDC.player.heroExpPool - costExp < needExp) {
                // 玩家资源不够了,升级到此为止
                afterChangeExp += (homePlayerDC.player.heroExpPool.toInt() - costExp)
                costExp = homePlayerDC.player.heroExpPool.toInt() // 消耗掉的就是玩家的所有
                break
            }

            // 升级
            addLv += 1
            afterChangeExp = 0
            costExp += needExp
        }

        // 消耗
        val costs = ResVo(RES_HERO_EXP_POOL, NOT_PROPS_SUB_TYPE, costExp.toLong())

        val oldLv = hero.level
        // 资源检测
        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            return rt.build()
        }

        // 扣除消耗
        resHelper.costRes(session, action, homePlayerDC.player, costs)

        // 武将重新设置
        hero.level = hero.level + addLv
        hero.exp = afterChangeExp
        hero.heroStrength = targetHelper.calHeroPower(
            hero.protoId, hero.level,
            hero.advLv, hero.awake, hero.skillId1, hero.skillId2, hero.skillId3, hero.skillId4, hero.heroEquipInfoMap
        )
        // 触发武将推送事件
        fireEvent(session, HeroUpFinishEvent(heroId, oldLv, hero.level))

        // 发送新的属性给客户端
        val valueChgNotifier = createValueChgNotifier()
        valueChgNotifier.append(heroId, HERO_LV, hero.level.toLong())
        valueChgNotifier.append(heroId, HERO_EXP, hero.exp.toLong())
        valueChgNotifier.append(heroId, HERO_POWER, hero.heroStrength)
        valueChgNotifier.notice(session)

        syncHero2World(session, hero)

        return rt.build()
    }

}
