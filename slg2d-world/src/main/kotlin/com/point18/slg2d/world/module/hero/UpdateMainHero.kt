package com.point18.slg2d.world.module.hero

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.IN_CITY
import com.point18.slg2d.common.constg.MAIN_HERO
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.MainHeroSync
import com.point18.slg2d.common.constg.NO_MAIN_HERO
import com.point18.slg2d.world.common.syncData2Home
import pb4client.UpdateMainHero
import pb4client.UpdateMainHeroRt
import com.point18.slg2d.common.resultcode.ResultCode

// 更换领主

class UpdateMainHeroDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val heroId = (msg as UpdateMainHero).heroId

        val rt = updateMainHero(session, heroId)

        session.sendMsg(MsgType.UpdateMainHero_1006, rt)
    }
}

fun updateMainHero(session: PlayerSession, heroId: Long): (UpdateMainHeroRt) {
    val rt = UpdateMainHeroRt.newBuilder()
    rt.rt = ResultCode.SUCCESS.code
    rt.heroId = heroId

    val playerId = session.playerId
    val areaCache = session.areaCache
    val player = session.player

    // 检测新领主数据
    val newMainHero = areaCache.heroCache.findHeroById(playerId, heroId)
    if (newMainHero == null) {
        rt.rt = ResultCode.NO_HERO.code
        return rt.build()
    }

    // 检测老领主数据
    val oldMainHero = areaCache.heroCache.findHeroById(playerId, player.mainHeroId)
    if (oldMainHero == null) {
        rt.rt = ResultCode.NO_HERO.code
        return rt.build()
    }

    //英雄状态
    if (oldMainHero.posState != IN_CITY) {
        rt.rt = ResultCode.HERO_POS_STATE_ERROR.code
        return rt.build()
    }
    if (oldMainHero.mainHeroState != MAIN_HERO) {
        rt.rt = ResultCode.HERO_POS_STATE_ERROR.code
        return rt.build()
    }

    // 严重通过,修改数据
    oldMainHero.mainHeroState = NO_MAIN_HERO
    newMainHero.mainHeroState = MAIN_HERO

    player.mainHeroId = newMainHero.id
    syncData2Home(areaCache, playerId, MainHeroSync, newMainHero.id.toString())

    return rt.build()
}
