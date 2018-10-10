package com.point18.slg2d.home.module.heroInvincible

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.DeleteHeart
import com.point18.slg2d.common.constg.HeroSuperUp
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.forwardHeartDeal2World
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4client.InvincibleHeroCancelSuperUp
import pb4client.InvincibleHeroCancelSuperUpRt

// 武将取消升阶
class HeroCancelSuperLvUpDeal : HomeClientMsgDeal, HomeHelperPlus1<HeroDC>(HeroDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val heroId = (msg as InvincibleHeroCancelSuperUp).heroId
        prepare(session) { heroDC: HeroDC ->
            val rt = heroCancelSuperLvUp(session, heroId, heroDC)
            session.sendMsg(MsgType.HeroCancelSuperUp_1017, rt)
        }
    }

    private fun heroCancelSuperLvUp(session: PlayerActor, heroId: Long, heroDc: HeroDC)
        : (InvincibleHeroCancelSuperUpRt) {
        val rt = InvincibleHeroCancelSuperUpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.heroId = heroId

        val playerId = session.playerId

        // 检测主武将是否准确
        val hero = heroDc.findHeroById(heroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }

        // 检测武将状态
        if (hero.superUpEndTime == 0L) {
            rt.rt = ResultCode.HERO_CANCEL_SUPER_LVUP_ERROR.code
            return rt.build()
        }

        hero.superUpEndTime = 0

        // todo 推送心跳
        forwardHeartDeal2World(session, DeleteHeart, HeroSuperUp, hero.id, hero.superUpEndTime)

        val valueChgNotifier = createValueChgNotifier()
        valueChgNotifier.append(heroId, com.point18.slg2d.common.constg.HERO_SUPER_LV_UP_ENDTIME_CHANGE, hero.superUpEndTime)
        valueChgNotifier.notice(session)

        return rt.build()
    }

}
