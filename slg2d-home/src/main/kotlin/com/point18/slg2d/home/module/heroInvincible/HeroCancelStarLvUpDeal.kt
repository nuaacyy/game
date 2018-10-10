package com.point18.slg2d.home.module.heroInvincible

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_NEWHERO_CANCEL_STAR_LV_UP
import com.point18.slg2d.common.constg.HERO_STAR_LV_UP_ENDTIME_CHANGE
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4client.InvincibleHeroCancelStarLvUp
import pb4client.InvincibleHeroCancelStarLvUpRt
import java.util.Arrays.asList

class HeroCancelStarLvUpDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, HeroDC>(
    HomePlayerDC::class.java, HeroDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC ->
            val heroId = (msg as InvincibleHeroCancelStarLvUp).heroId
            val rt = heroCancelStarLvUp(session, heroId, heroDC, homePlayerDC)
            session.sendMsg(MsgType.HeroCancelStarLvUp_1016, rt)
        }
    }

    private fun heroCancelStarLvUp(session: PlayerActor, heroId: Long, heroDc: HeroDC, playerDC: HomePlayerDC)
        : (InvincibleHeroCancelStarLvUpRt) {
        val rt = InvincibleHeroCancelStarLvUpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.heroId = heroId
        val action = ACTION_NEWHERO_CANCEL_STAR_LV_UP
        val playerId = session.playerId

        // 检测主武将是否准确
        val hero = heroDc.findHeroById(heroId)
        if (hero == null) {
            rt.rt = ResultCode.NO_HERO.code
            return rt.build()
        }

        // 检测武将状态
        if (hero.starUpEndTime == 0L) {
            rt.rt = ResultCode.HERO_CANCEL_STAR_LVUP_ERROR.code
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

        // 消费
        if (heroStarProto.starProps != "0") {
            val checkCost = resHelper.checkRes(session, heroStarProto.starPropsResVo)
            if (!checkCost) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            //
            resHelper.addRes(session, action, playerDC.player, heroStarProto.starPropsResVo)
        }

        // 数据修改
        hero.starUpEndTime = 0

        val valueChgNotifier = createValueChgNotifier()
        valueChgNotifier.append(heroId, HERO_STAR_LV_UP_ENDTIME_CHANGE, hero.starUpEndTime)
        valueChgNotifier.notice(session)

        return rt.build()
    }

}