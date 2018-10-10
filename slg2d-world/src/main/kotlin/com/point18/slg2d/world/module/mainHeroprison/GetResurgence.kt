package com.point18.slg2d.world.module.mainHeroprison

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.CAN_RESURGENCE
import com.point18.slg2d.common.constg.MAIN_HERO
import com.point18.slg2d.common.constg.MAIN_HERO_STATE
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4client.GetResurgenceRt
import com.point18.slg2d.common.resultcode.ResultCode

// 领取复活
class GetResurgenceDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = getResurgence(session)
        session.sendMsg(MsgType.GetResurgence_1358, rt)
    }

    private fun getResurgence(session: PlayerSession): GetResurgenceRt {
        val rt = GetResurgenceRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val areaCache = session.areaCache
        val player = session.player

        val hero = areaCache.heroCache.findHeroById(player.id, player.mainHeroId)
        if (hero == null) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }
        if (hero.mainHeroState != CAN_RESURGENCE) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        hero.mainHeroState = MAIN_HERO

        // 推送给被关方领主信息变化
        val loseSession = fetchOnlinePlayerSession(areaCache, hero.playerId)
        if (loseSession != null) {
            val valueChgNotifier = createValueChgNotifier()
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE, hero.mainHeroState.toLong())
            valueChgNotifier.notice(loseSession)
        }
       
        //加入日志
        return rt.build()
    }
}


