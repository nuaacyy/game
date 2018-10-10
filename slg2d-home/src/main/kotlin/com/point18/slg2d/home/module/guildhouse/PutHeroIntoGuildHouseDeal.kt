package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4client.PutHeroIntoGuildHouse
import pb4client.PutHeroIntoGuildHouseRt

class PutHeroIntoGuildHouseDeal : HomeClientMsgDeal, HomeHelperPlus1<HeroDC>(HeroDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { heroDC: HeroDC ->
            val rtBuilder = this.putHeroIntoGuildHouse(
                session, msg as PutHeroIntoGuildHouse, heroDC
            )
            session.sendMsg(MsgType.PutHeroIntoGuildHouse_1530, rtBuilder.build())
        }
    }

    private fun putHeroIntoGuildHouse(
        session: PlayerActor,
        msg: PutHeroIntoGuildHouse,
        heroDC: HeroDC
    ): PutHeroIntoGuildHouseRt.Builder {
        val rtBuilder = PutHeroIntoGuildHouseRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val heroId = msg.heroId
        val floorIdx = msg.floorIdx

        // 校验楼层上的英雄数量
        if (floorIdx != 0) { // 不是移除英雄
            val houseHeroLimit = pcs.basicProtoCache.houseHeroLimit
            val heroMap = heroDC.findHeroMapByPlayer()
            var heroNum = 0
            heroMap.values.forEach {
                if (it.onFloorIdx == floorIdx) {
                    heroNum++
                    if (heroNum >= houseHeroLimit) {
                        rtBuilder.rt = ResultCode.LIMIT_HERO_NUM.code
                        return rtBuilder
                    }
                }
            }
        }

        val hero = heroDC.findHeroById(heroId)
        if (hero == null) {
            com.point18.slg2d.common.commonfunc.normalLog.error("英雄未找到$heroId")
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        hero.onFloorIdx = floorIdx

        val notifier = createValueChgNotifier()
        notifier.append(heroId, com.point18.slg2d.common.constg.HERO_ON_FLOOR_IDX, floorIdx.toLong())
        notifier.notice(session)

        return rtBuilder
    }
}